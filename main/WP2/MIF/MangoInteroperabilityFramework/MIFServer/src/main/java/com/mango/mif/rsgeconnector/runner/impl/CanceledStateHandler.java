package com.mango.mif.rsgeconnector.runner.impl;

import java.io.File;

import com.google.common.base.Preconditions;
import com.mango.mif.connector.runner.impl.StateHandlerException;
import com.mango.mif.rsgeconnector.internal.SGECancelCommandBuilder;
import com.mango.mif.rsgeconnector.internal.SGEExec;
import com.mango.mif.rsgeconnector.internal.SGEExecException;

/**
 * Handler responsible for handling cancelled processing state
 * @author mrogalski
 *
 */
public class CanceledStateHandler extends RSGEStateHandler {
	/**
	 * SGE job id
	 */
	private String jobID;
	/**
	 * Builds a script that cancels a job on grid
	 */
	private SGECancelCommandBuilder cancelCommandBuilder;
	/**
	 * SGE working directory
	 */
	private File sgeJobWorkingDirectory;
	/**
	 * Default constructor
	 * @param state
	 */
	public CanceledStateHandler(String state) {
		super(state);
	}
	
	@Override
	protected void doProcessing() throws StateHandlerException {
		jobID = getSgeJobID();
		sgeJobWorkingDirectory = getSgeJobWorkingDirectory();

		SGEExec gridExec = jobRunner.getSgeExec();
		
		gridExec.setWorkingDirectory(sgeJobWorkingDirectory);
		gridExec.setJobID(jobID);
		gridExec.setCancelCommand(buildCancelCommand());
		
		try {
			gridExec.cancel();
		} catch (SGEExecException e) {
            throw new StateHandlerException(e.getMessage(), e);
		}
	}
	
	private String buildCancelCommand() {
		Preconditions.checkNotNull(cancelCommandBuilder, "Cancel builder not set");
		cancelCommandBuilder.setJobID(jobID);
		return cancelCommandBuilder.getCommand();
	}
	
	public void setCancelCommandBuilder(SGECancelCommandBuilder cancelCommandBuilder) {
		this.cancelCommandBuilder = cancelCommandBuilder;
	}
	
	/* (non-Javadoc)
	 * @see com.mango.mif.connector.runner.impl.AbstractStateHandler#complete()
	 */
	@Override
	public String complete() {
//		TODO Fix it! Retruns null!
//		DC - Need to create initial JobRuntimeData somewhere otherwise there is no record to update.  See PendingStateHandler.complete() 
//    	JobRuntimeData execJob = executionJobRepository.getExecutionJobByRequestUuid(jobRunner.getJob().getExecutionRequest().getRequestId().toString(), ExecutionJobKey.STATUS.getMessage());
//        execJob.setValue(ExecutionJobValue.CANCELLED.getMessage());  
//        execJob = executionJobRepository.save(execJob);
//        LOG.info("Persisted Job during CANCELLED state: " + execJob.toString());
        
		return super.complete();
	}
	
}
