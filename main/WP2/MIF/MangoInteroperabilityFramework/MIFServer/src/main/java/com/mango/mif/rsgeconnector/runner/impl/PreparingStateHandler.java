/* File         :  PreparingStateHandler.java
 * Project      :  MangoInteroperabilityFramework
 * Created on   :  14 May 2012
 */
package com.mango.mif.rsgeconnector.runner.impl;

import java.io.File;
import java.io.IOException;

import org.apache.commons.io.FileUtils;
import org.apache.log4j.Logger;

import com.google.common.base.Preconditions;
import com.google.common.io.Files;
import com.mango.mif.connector.dispatcher.domain.Job;
import com.mango.mif.connector.runner.impl.StateHandlerException;
import com.mango.mif.core.jsch.JschHelper;
import com.mango.mif.core.jsch.JschParameterBuilder;
import com.mango.mif.core.jsch.JschResults;
import com.mango.mif.domain.ExecutionRequest;
import com.mango.mif.exception.MIFException;
import com.mango.mif.r.RScriptBuilder;
import com.mango.mif.rsgeconnector.internal.SGEExecRScriptCommandBuilder;
import com.mango.mif.rsgeconnector.internal.SGESubmitCommandBuilder;
import com.mango.mif.utils.encrypt.Encrypter;
import com.mango.mif.utils.encrypt.EncrypterFactory;

/**
 * Preparing state handler.
 * 
 * Takes Job details held by JobRunner and prepares input files (script/datasets) in working directory for R
 * 
 * @version $Revision: $ as of $Date: $
 * <p>SVN Entry : $HeadURL: $
 * <p>SVN ID    : $Id: $
 * <p>Last edited by : $Author: $
 */
public class PreparingStateHandler extends RSGEStateHandler {
	/**
	 * LOG
	 */
    public final static Logger LOG = Logger.getLogger(PreparingStateHandler.class);

    private final static String PREPARER_SCRIPT_BASENAME = "prepare.sh";
    /**
     * SGE working directory
     */
    private File sgeJobWorkingDirectory;
    /**
     * Execute R shell script
     */
    private File execRShellScript;
    /**
     * A Shared location with SGE
     */
    private String sgeSharedLocation;
    /**
     * User name
     */
    private String userName;
    /**
     * responsible for building SGE submit command
     */
    private SGESubmitCommandBuilder submitCommandBuilder;
    /**
     * Responsible for building a shell script that executes R script
     */
    private SGEExecRScriptCommandBuilder rCommandBuilder;
    /**
     * The JSCH port number
     */
    private int port;
    /**
     * Default Constructor
     * 
     * @param state
     * @param exitEvent
     */
    public PreparingStateHandler(String state, String exitEvent) {
        super(state, exitEvent);
    }

    /**
     * TODO massacred by Tony B desperately needs refactoring to be leaner and meaner
     * FIXME break into separate functions, this is TOO LONG
     */
    @Override
    protected void doProcessing() throws StateHandlerException {
        Preconditions.checkNotNull(jobRunner.getJob(), "Job not set");
        Preconditions.checkNotNull(rCommandBuilder, "R command builder is not set");

        sgeJobWorkingDirectory = new File(sgeSharedLocation,"MIF-" + jobRunner.getJob().getJobId());

        File tempDirectory = Files.createTempDir();
        try {
            File tempScript = new File(tempDirectory, PREPARER_SCRIPT_BASENAME);

            try {
                FileUtils.copyURLToFile(this.getClass().getResource(PREPARER_SCRIPT_BASENAME), tempScript);
            } catch (IOException ioe) {
                throw new StateHandlerException("Failed to copy "
                        + PREPARER_SCRIPT_BASENAME
                        + " to "
                        + tempScript.getAbsolutePath(),
                        ioe);
            }

            StringBuilder sb = new StringBuilder();
            sb.append("/bin/bash ");
            sb.append(tempScript.getAbsolutePath());

            sb.append(" -d \"");
            sb.append(sgeJobWorkingDirectory.getAbsolutePath());
            sb.append("\"");

            ExecutionRequest executionRequest = jobRunner.getJob().getExecutionRequest();
            File inputDirectory = sharedLocationManager.getRequestInputDirectory(executionRequest.getRequestId().toString());

            sb.append(" -C \"");
            sb.append(inputDirectory.getAbsolutePath());
            sb.append("\" \"");
            sb.append(sgeJobWorkingDirectory.getAbsolutePath());
            sb.append("\"");

            File scriptFile = prepareScript(executionRequest);
            LOG.info("R script file that will be invoked: " + scriptFile.getAbsolutePath());

            rCommandBuilder.setScriptFile(scriptFile);
            execRShellScript = new File(sgeJobWorkingDirectory,"MIF-Rexec.sh");
            File tempRShellScript = new File(tempDirectory, "MIF-Rexec.sh");

            try {
                FileUtils.writeStringToFile(tempRShellScript, rCommandBuilder.getCommand());
            } catch (IOException ioe) {
                throw new StateHandlerException("Error trying to write r script, "
                        + tempRShellScript.getAbsolutePath(),
                        ioe);
            }

            sb.append(" -c \"");
            sb.append(tempRShellScript.getAbsolutePath());
            sb.append("\" \"");
            sb.append(execRShellScript.getAbsolutePath());
            sb.append("\"");

            LOG.info("Exec R script: " + execRShellScript);
            LOG.debug("About to execute command: " + sb.toString());

            Job job = jobRunner.getJob();
            String userName = job.getExecutionRequest().getUserName();
            String password = job.getExecutionRequest().getUserPassword();
            String clearTextPassword = encrypter.decrypt(password);

            JschParameterBuilder builder = new JschParameterBuilder(userName, clearTextPassword).port(port);
            JschHelper helper = new JschHelper(builder);

            try {
                JschResults results = helper.runAndReportFailures(sb.toString());

                LOG.debug("Stdout: " + results.getStdout());
                LOG.debug("Stderr: " + results.getStderr());
                LOG.debug("Exit: " + results.getExitStatus());
            } catch (MIFException e) {
            	LOG.error(e);
                throw new StateHandlerException("Error when preparing inputs for job execution." + e.getMessage(), e);
            }
        } finally {
            try {
                FileUtils.deleteDirectory(tempDirectory);
            } catch (IOException ioe) {
                LOG.error("Caught exception deleting temporary directory "
                        + tempDirectory
                        + " You will have to delete it manually",
                        ioe);
            }
        }
    }

    /**
     * Prepares R script file that will be executed by R. The returned script file won't be the one
     * selected by a user, it will a new r script file that references/includes the user script file
     * @return
     */
    private File prepareScript(ExecutionRequest executionRequest) {
        RScriptBuilder scriptBuilder = new RScriptBuilder();
        scriptBuilder.setCommandDefinition(executionRequest.getCommandDefinition());
        scriptBuilder.setParameters(executionRequest.getParameters());
        scriptBuilder.setWorkingDirectory(sgeJobWorkingDirectory);
        return scriptBuilder.getScriptFile();
    }

    @Override
    public String complete() {

        // TODO Fix it! Retruns null!
        // JobRuntimeData execJob = executionJobRepository.getExecutionJobByRequestUuid(jobRunner.getJob().getExecutionRequest().getRequestId().toString(), ExecutionJobKey.STATUS.getMessage());
        // execJob.setValue(ExecutionJobValue.PREPARING.getMessage());
        // execJob = executionJobRepository.save(execJob);
        // LOG.info("Persisted Job during PREPARING state: " + execJob.toString());

        Job job = jobRunner.getJob();
        job.addToDataMap(RSGEJobRunner.SGE_JOB_WORKING_DIRECTORY,sgeJobWorkingDirectory.getAbsolutePath());
        job.addToDataMap(RSGEJobRunner.EXEC_R_SCRIPT,execRShellScript.getAbsolutePath());

        return super.complete();
    }

    public void setSgeSharedLocation(String sgeSharedLocation) {
        this.sgeSharedLocation = sgeSharedLocation;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public void setSubmitCommandBuilder(SGESubmitCommandBuilder submitCommandBuilder) {
        this.submitCommandBuilder = submitCommandBuilder;
    }

    public void setrCommandBuilder(SGEExecRScriptCommandBuilder rCommandBuilder) {
        this.rCommandBuilder = rCommandBuilder;
    }

    public void setPort(int port) {
        this.port = port;
    }
}