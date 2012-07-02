/* File			:  SGEExec.java
 * Project		:  MIFServer
 * Created on	:  May 10, 2012
 */
package com.mango.mif.rsgeconnector.internal;

import java.io.File;
import java.io.FilenameFilter;
import java.io.IOException;

import org.apache.commons.exec.CommandLine;
import org.apache.commons.io.FileUtils;
import org.apache.log4j.Logger;

import com.google.common.base.Preconditions;
import com.mango.mif.utils.CommandExec;
import com.mango.mif.utils.CommandExecException;
import com.mango.mif.utils.MIFProperties;

/**
 * 
 * Executes a shell script on Grid
 * 
 * @version $Revision: $ as of $Date: $
 * <p>SVN Entry : $HeadURL: $
 * <p>SVN ID    : $Id: $
 * <p>Last edited by : $Author: $
 */
public class SGEExec {
    /**
     * Logger
     */
    private final static Logger LOG = Logger.getLogger(SGEExec.class);
    /**
     * submit command
     */
    private String submitCommand;
    /**
     * status command
     */
    private String statusCommand;
    /**
     * accounting command
     */
    private String accountingCommand;
    /**
     * accounting command
     */
    private String cancelCommand;
    /**
     * Working directory where the script will be executed
     */
    private File workingDirectory;
    /**
     * Grid job ID
     */
    private String jobID;
    /**
     * Number of millisecond between SGE requests
     */
    private Long pollingRate = 5000l;
    /**
     * Number of retries when job is expected on Finished queue.
     */
    private int numberOfRetriesWhenNotFoundOnFinishedQueue = 0;
    /**
     * flag indicating if the SGE job has been cancelled
     */
    private volatile boolean cancelled = false;
    /**
     * User name
     */
    private String userName;
    /**
     * User password
     */
    private String userPassword;
    /**
     * port for JSCH
     */
    private int port = 22;
    /**
     * the output parser.  The SGE version, of course.
     */
    private SGEOutputParser sgeOutputParser;

    /**
     * submits a given script to grid
     * @throws SGEExecException
     */
    public synchronized void submit() throws SGEExecException {
        Preconditions.checkNotNull(submitCommand, "Submit command not set");
        Preconditions.checkNotNull(workingDirectory, "Working directory not set.");

        if(cancelled) {
            return;
        }
        SGEOutput output = null;
        try {
            output = executeSubmit();
        } catch (CommandExecException e) {
            throw new SGEExecException("Submission of the job failed.",e);
        }
        jobID = output.get(SGEOutput.JOB_ID_KEY);
        if(jobSubmissionFailed(output)) {
            throw new SGEExecException("Submission of the job failed." + output.get(SGEOutput.OUTPUT_MESSAGE_KEY));
        }
        LOG.info("Grid Job ID = " + jobID);
    }

    /**
     * submits a given script to grid
     * @throws SGEExecException
     */
    public void poll() throws SGEExecException {
        Preconditions.checkNotNull(statusCommand, "Status command not set.");
        Preconditions.checkNotNull(accountingCommand, "Accounting command not set.");
        Preconditions.checkNotNull(workingDirectory, "Working directory not set.");
        Preconditions.checkNotNull(jobID, "Job ID not set.");

        try {
            while(jobIsBeingProcessed() && !cancelled) {
                try {
                    Thread.sleep(pollingRate);
                } catch (InterruptedException e) {
                    throw new RuntimeException(e);
                }
            }
        } catch (CommandExecException e) {
            LOG.error(e);
            throw new SGEExecException("Polling of the grid for job with ID " + jobID + ".",e);
        }

        if(cancelled) {
            LOG.info("Job " + jobID + " has been cancelled.");
            return;
        }
        LOG.info("Job " + jobID + " is not on input/processing queue on SGE.");

        waitForJobToBeOnFinishedQueue();

        if(cancelled) {
            LOG.info("Job " + jobID + " has been cancelled.");
            return;
        } else {
            LOG.info("Job " + jobID + " is completed on SGE.");
        }
    }

    /**
     * @throws CommandExecException
     * 
     */
    private void waitForJobToBeOnFinishedQueue() throws SGEExecException {
        int retries = 0;
        SGEOutput output = null;
        while((output == null || !isJobCompleted(output)) && !cancelled) {
            try {
                Thread.sleep(pollingRate);
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
            try {
                output = retrieveAccountingMessage();
            } catch (CommandExecException e) {
                LOG.error(e);
                throw new SGEExecException("Retrieving of the accounting information for " + jobID + " failed.",e);
            }

            if(!isJobOnTheFinishedQueue(output)) {
                LOG.debug("Job " + jobID + " not found on SGE finished jobs queue. Retries so far " + retries);
                retries++;
                if(numberOfRetriesWhenNotFoundOnFinishedQueue>0) {
                    if(retries>=numberOfRetriesWhenNotFoundOnFinishedQueue) {
                        throw new SGEExecException("Job " + jobID + " not found on the finished jobs queue. Exceeted number of retries " +  numberOfRetriesWhenNotFoundOnFinishedQueue);
                    }
                }
            }
        }
    }

    /**
     * @param output
     * @return true if SGE output indicates that job is found on the finished queue
     */
    private static boolean isJobCompleted(SGEOutput output) {
        return output.get(SGEOutput.SGE_COMMAND_RESULT_KEY).equals(SGEOutput.JOB_FINISHED);
    }

    /**
     * 
     * @param output
     * @return true if given output represents a failure of job submission
     */
    private static boolean jobSubmissionFailed(SGEOutput output) {
        return output.get(SGEOutput.SGE_COMMAND_RESULT_KEY).equals(SGEOutput.JOB_SUBMISSION_FAILED);
    }

    /**
     * 
     * @param output
     * @return true if SGE output indicates that job was not found on the completed queue
     */
    private static boolean isJobOnTheFinishedQueue(SGEOutput output) {
        return !output.get(SGEOutput.SGE_COMMAND_RESULT_KEY).equals(SGEOutput.JOB_NOT_FOUND);
    }

    /**
     * @param output
     * @return true if the SGE output indicates that job is being processed
     * @throws CommandExecException
     */
    private boolean jobIsBeingProcessed() throws CommandExecException {
        SGEOutput output = pollTheGrid();
        return output.get(SGEOutput.SGE_COMMAND_RESULT_KEY).equals(SGEOutput.JOB_PROCESSING);
    }

    /**
     * Prepares command exec
     * @param workingDirectory
     * @param command
     * @param executionIdPostfix
     * @return
     */
    private CommandExec prepareCommandExec(String command, String executionIdPostfix) {
        CommandExec commandExec = new CommandExec();
        commandExec.setExecutionID(commandExec.getExecutionID() + executionIdPostfix);
        commandExec.setWorkingDirectory(workingDirectory);
        commandExec.setCommandLine(new CommandLine(command));
        commandExec.setUserName(userName);
        commandExec.setClearTextPassword(userPassword);
        commandExec.setPort(port);
        return commandExec;

    }

    /**
     * Requests Account information from grid for the given job
     * @return error output object or output object;
     * @throws CommandExecException
     */
    private SGEOutput retrieveAccountingMessage() throws CommandExecException {
        Preconditions.checkNotNull(sgeOutputParser, "The output parser must have been injected by this point");
        LOG.info("Retrieving accounting info for " + jobID);
        CommandExec commandExec = prepareCommandExec(accountingCommand, "-acct");
        commandExec.runAsUser();
        LOG.debug("qacct ERR stream: " + commandExec.getErrorOutput());
        LOG.debug("qacct STD stream: " + commandExec.getStandardOutput());
        if(commandExec.getExitCode() != CommandExec.SUCCESS_EXIT_VALUE) {
            return sgeOutputParser.parseAccountingMessage(commandExec.getErrorOutput());
        }
        return sgeOutputParser.parseAccountingMessage(commandExec.getStandardOutput());
    }

    /**
     * Requests status information from grid for the given job
     * @return error output object or output object;
     * @throws CommandExecException
     */
    private SGEOutput pollTheGrid() throws CommandExecException {
        Preconditions.checkNotNull(sgeOutputParser, "The output parser must have been injected by this point");
        CommandExec commandExec = prepareCommandExec(statusCommand, "-poll");
        LOG.info("Polling grid for status update" + jobID);
        commandExec.runAsUser();
        LOG.debug("qstat ERR stream: " + commandExec.getErrorOutput());
        LOG.debug("qstat STD stream: " + commandExec.getStandardOutput());
        if(commandExec.getExitCode() != CommandExec.SUCCESS_EXIT_VALUE) {
            return sgeOutputParser.parseStatusMessage(commandExec.getErrorOutput());
        }
        return sgeOutputParser.parseStatusMessage(commandExec.getStandardOutput());
    }
    /**
     * executes submission command
     * @return
     * @throws CommandExecException
     */
    private SGEOutput executeSubmit() throws CommandExecException {
        Preconditions.checkNotNull(sgeOutputParser, "The output parser must have been injected by this point");
        CommandExec commandExec = prepareCommandExec(submitCommand, "-submit");
        commandExec.runAsUser();
        LOG.debug("qsub ERR stream: " + commandExec.getErrorOutput());
        LOG.debug("qsub STD stream: " + commandExec.getStandardOutput());

        if(commandExec.getExitCode() != CommandExec.SUCCESS_EXIT_VALUE) {
            return sgeOutputParser.parseSubmitMessage(commandExec.getErrorOutput());
        }
        return sgeOutputParser.parseSubmitMessage(commandExec.getStandardOutput());
    }
    /**
     * executes deletion command
     * @return
     * @throws CommandExecException
     */
    private SGEOutput executeDelete() throws CommandExecException {
        Preconditions.checkNotNull(sgeOutputParser, "The output parser must have been injected by this point");
        CommandExec commandExec = prepareCommandExec(cancelCommand, "-del");
        LOG.info("Cancelling job " + jobID);
        commandExec.runAsUser();
        LOG.debug("qdel ERR stream: " + commandExec.getErrorOutput());
        LOG.debug("qdel STD stream: " + commandExec.getStandardOutput());
        if(commandExec.getExitCode() != CommandExec.SUCCESS_EXIT_VALUE) {
            return sgeOutputParser.parseDeleteMessage(commandExec.getErrorOutput());
        }
        return sgeOutputParser.parseDeleteMessage(commandExec.getStandardOutput());
    }
    /**
     * Cancels a job running on grid (qdel)
     * 
     * @throws CommandExecException
     */
    public synchronized void cancel() throws SGEExecException {
        Preconditions.checkNotNull(cancelCommand, "Command can't be null");
        Preconditions.checkNotNull(workingDirectory, "Working directory not set.");

        cancelled = true;
        if(jobID == null) {
            LOG.warn("Job might have not yet been submitted");
            return;
        }
        SGEOutput output = null;
        try {
            output = executeDelete();
        } catch (CommandExecException e) {
            throw new SGEExecException("Cancellation of job " + jobID + " failed.");
        }

        if(!output.get(SGEOutput.SGE_COMMAND_RESULT_KEY).equals(SGEOutput.JOB_DELETED)) {
            LOG.error("Job " + jobID + " was not deleted, because it was not found.");
        }
        LOG.info("Job " + jobID + " deleted from SGE.");
    }
    /**
     * dumps the streams to MIF format
     */
    public void dumpStreams() {
        File[] gridFiles = workingDirectory.listFiles(new FilenameFilter() {
            @Override
            public boolean accept(File dir, String name) {
                return name.endsWith(jobID);
            }
        });
        for(File f : gridFiles) {
            try {
                if(f.getName().endsWith(".e" + jobID)) {
                    File mifErrorStream = new File(f.getParentFile(), MIFProperties.MIF_ERROR_STREAM);
                    FileUtils.copyFile(f, mifErrorStream);
                    LOG.info("SGE job error stream saved as:" + mifErrorStream);
                } else if(f.getName().endsWith(".o" + jobID)) {
                    File mifOutputStream = new File(f.getParentFile(), MIFProperties.MIF_OUTPUT_STREAM);
                    FileUtils.copyFile(f, new File(f.getParentFile(), MIFProperties.MIF_OUTPUT_STREAM));
                    LOG.info("SGE job output stream saved as:" + mifOutputStream);
                }
            } catch (IOException e) {
                throw new RuntimeException("Could not copy SGE output stream file.", e);
            }
        }
    }

    public void setWorkingDirectory(File workingDirectory) {
        this.workingDirectory = workingDirectory;
    }

    public void setSubmitCommand(String submitCommand) {
        this.submitCommand = submitCommand;
    }

    public void setStatusCommand(String statusCommand) {
        this.statusCommand = statusCommand;
    }

    public void setAccountingCommand(String accoutingCommand) {
        this.accountingCommand = accoutingCommand;
    }

    public String getJobID() {
        return jobID;
    }

    public void setCancelCommand(String cancelCommand) {
        this.cancelCommand = cancelCommand;
    }

    public void setJobID(String jobID) {
        this.jobID = jobID;
    }

    public void setPollingRate(Long pollingRate) {
        Preconditions.checkNotNull(pollingRate);
        Preconditions.checkArgument(pollingRate > 0, "Grid polling rate should be > 0.");
        this.pollingRate = pollingRate;
    }

    /**
     * 0 - no limit(default),
     * greater than 0 - number of retries;
     * @param numberOfRetriesWhenNotFoundOnFinishedQueue the numberOfRetriesWhenNotFoundOnFinishedQueue to set
     */
    public void setNumberOfRetriesWhenNotFoundOnFinishedQueue(int numberOfRetriesWhenNotFoundOnFinishedQueue) {
        this.numberOfRetriesWhenNotFoundOnFinishedQueue = numberOfRetriesWhenNotFoundOnFinishedQueue;
    }

    /**
     * @param userName the userName to set
     */
    public void setUserName(String userName) {
        this.userName = userName;
    }

    /**
     * @param userPassword the userPassword to set
     */
    public void setUserPassword(String userPassword) {
        this.userPassword = userPassword;
    }

    /**
     * @param port the JSCH port to set
     */
    public void setPort(int port) {
        this.port = port;
    }

    /**
     * @param sgeOutputParser the SGE output parser to set
     */
    public void setSgeOutputParser(SGEOutputParser sgeOutputParser) {
        this.sgeOutputParser = sgeOutputParser;
    }
}
