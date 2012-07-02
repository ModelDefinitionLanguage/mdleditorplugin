/* File			:  RetrievingStateHandler.java
 * Project		:  MangoInteroperabilityFramework
 * Created on	:  14 May 2012
 */
package com.mango.mif.rsgeconnector.runner.impl;

import java.io.File;
import java.util.Map;
import java.util.UUID;

import org.apache.log4j.Logger;

import com.google.common.collect.Maps;
import com.mango.mif.connector.dispatcher.domain.Job;
import com.mango.mif.connector.runner.impl.StateHandlerException;
import com.mango.mif.core.jsch.JschHelper;
import com.mango.mif.core.jsch.JschParameterBuilder;
import com.mango.mif.core.jsch.JschResults;
import com.mango.mif.domain.Parameter;
import com.mango.mif.exception.MIFException;
import com.mango.mif.utils.encrypt.Encrypter;
import com.mango.mif.utils.encrypt.EncrypterFactory;

/**
 * Retrieving state handler
 * 
 * @version $Revision: $ as of $Date: $
 * <p>SVN Entry : $HeadURL: $
 * <p>SVN ID    : $Id: $
 * <p>Last edited by : $Author: $
 */
public class RetrievingStateHandler extends RSGEStateHandler {
    /**
     * Logger
     */
    private static Logger LOG = Logger.getLogger(RetrievingStateHandler.class);
    /**
     * port for JSCH
     */
    private int port;
    /**
     * Default Constructor
     * 
     * @param state
     * @param exitEvent
     */
    public RetrievingStateHandler(String state, String exitEvent) {
        super(state, exitEvent);
    }

    @Override
    protected void doProcessing() throws StateHandlerException {
        LOG.debug("Retrieving results for job: " + jobRunner.getJob().getJobId());

        Map<String,Parameter> results = Maps.newHashMap();
        
        Job job = jobRunner.getJob();
        String userName = job.getExecutionRequest().getUserName();
        String password = job.getExecutionRequest().getUserPassword();
        String clearTextPassword = encrypter.decrypt(password);

        JschParameterBuilder builder = new JschParameterBuilder(userName, clearTextPassword).port(port);
        JschHelper helper = builder.buildHelper();

        try {
            JschResults result = helper.runAndReportFailures(buildRetrieveCommand());

            LOG.debug("Stdout: " + result.getStdout());
            LOG.debug("Stderr: " + result.getStderr());
            LOG.debug("Exit: " + result.getExitStatus());
        } catch (MIFException e) {
            LOG.error("Error when invoking a retrieve script.",e);
            throw new StateHandlerException("Error when invoking a retrieve script." + e.getMessage(),e);
        }

        jobRunner.setResults(results);
    }


    String buildRetrieveCommand() {
        File from = getSgeJobWorkingDirectory();
        UUID requestId = jobRunner.getJob().getExecutionRequest().getRequestId();
        File to = sharedLocationManager.getRequestOutputDirectory(requestId.toString());

        StringBuilder sb = new StringBuilder();
        sb.append("cd \"");
        sb.append(from.getAbsolutePath());
        sb.append("\"; find . ! -name 'MIF-*' -print | cpio -pdum ");
        sb.append(to.getAbsolutePath());

        return sb.toString();
    }

    /* (non-Javadoc)
     * @see com.mango.mif.connector.runner.impl.AbstractStateHandler#complete()
     */
    @Override
    public String complete() {

        //		TODO Fix it! Returns null!
        //		DC - Need to create initial JobRuntimeData somewhere otherwise there is no record to update.  See PendingStateHandler.complete()
        //		JobRuntimeData execJob = executionJobRepository.getExecutionJobByRequestUuid(jobRunner.getJob().getExecutionRequest().getRequestId().toString(), ExecutionJobKey.STATUS.getMessage());
        //        execJob.setValue(ExecutionJobValue.RETRIEVING.getMessage());
        //        execJob = executionJobRepository.save(execJob);
        //        LOG.info("Persisted Job during RETRIEVING state: " + execJob.toString());
        return super.complete();
    }


    public void setPort(int port) {
        this.port = port;
    }
}