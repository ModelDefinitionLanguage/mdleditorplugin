/* File			:  ProcessingStateHandler.java
 * Project		:  MangoInteroperabilityFramework
 * Created on	:  14 May 2012
 */
package com.mango.mif.rsgeconnector.runner.impl;

import java.io.File;

import com.google.common.base.Preconditions;
import com.mango.mif.connector.runner.impl.StateHandlerException;
import com.mango.mif.rsgeconnector.internal.SGEAccountCommandBuilder;
import com.mango.mif.rsgeconnector.internal.SGEExec;
import com.mango.mif.rsgeconnector.internal.SGEExecException;
import com.mango.mif.rsgeconnector.internal.SGEStatusCommandBuilderSimpleTemplate;

/**
 * 
 * Processing state handler
 * 
 * @version $Revision: $ as of $Date: $
 * <p>SVN Entry : $HeadURL: $
 * <p>SVN ID    : $Id: $
 * <p>Last edited by : $Author: $
 */
public class ProcessingStateHandler extends RSGEStateHandler {

    private static String PROCESSOR_SCRIPT_BASENAME = "processor.sh";

    /**
     * Builder used to build status command
     */
    private SGEStatusCommandBuilderSimpleTemplate statusCommandBuilder;
    /**
     * Buildre used to build account command
     */
    private SGEAccountCommandBuilder accountCommandBuilder;
    /**
     * Job id
     */
    private String jobID;
    /**
     * SGE job working directory
     */
    private File sgeJobWorkingDirectory;
    /**
     * Grid polling rate
     */
    private Long gridPollingRate = 5000l;
    /**
     * Default constructor
     * @param state
     * @param exitEvent
     */
    public ProcessingStateHandler(String state, String exitEvent) {
        super(state, exitEvent);
    }

    @Override
    protected void doProcessing() throws StateHandlerException {
        // Polling
        sgeJobWorkingDirectory = getSgeJobWorkingDirectory();
        jobID = getSgeJobID();
        
        SGEExec gridExec = jobRunner.getSgeExec();

        gridExec.setWorkingDirectory(sgeJobWorkingDirectory);
        gridExec.setJobID(jobID);
        gridExec.setPollingRate(gridPollingRate);
        gridExec.setStatusCommand(buildStatusCommand());
        // Accounting/Summary
        gridExec.setAccountingCommand(buildAccountCommand());
		try {
			gridExec.poll();
		} catch (SGEExecException e) {
            throw new StateHandlerException("Error when preparing inputs for job execution.", e);
		}
    }

    /**
     * Builds a script that executes status command
     * @return
     */
    private String buildStatusCommand() {
        Preconditions.checkNotNull(statusCommandBuilder);
        statusCommandBuilder.setJobID(jobID);

        return statusCommandBuilder.getCommand();
    }

    /**
     * Builds a script that executes accounting command
     * @return
     */
    private String buildAccountCommand() {
        Preconditions.checkNotNull(accountCommandBuilder);
        accountCommandBuilder.setJobID(jobID);
        return accountCommandBuilder.getCommand();
    }

    public void setStatusCommandBuilder(
            SGEStatusCommandBuilderSimpleTemplate statusCommandBuilder) {
        this.statusCommandBuilder = statusCommandBuilder;
    }

    public void setAccountCommandBuilder(
            SGEAccountCommandBuilder accountCommandBuilder) {
        this.accountCommandBuilder = accountCommandBuilder;
    }


    /* (non-Javadoc)
     * @see com.mango.mif.connector.runner.impl.AbstractStateHandler#complete()
     */
    @Override
    public String complete() {
        // TODO Fix it! Returns null!
        // DC - Need to create initial JobRuntimeData somewhere otherwise there is no record to update.  See PendingStateHandler.complete()
        // JobRuntimeData execJob = executionJobRepository.getExecutionJobByRequestUuid(jobRunner.getJob().getExecutionRequest().getRequestId().toString(),ExecutionJobKey.STATUS.getMessage());
        // execJob.setValue(ExecutionJobValue.PROCESSING.getMessage());
        // execJob = executionJobRepository.save(execJob);
        // LOG.info("Persisted Job during PROCESSING state: " + execJob.toString());

        return super.complete();
    }
}