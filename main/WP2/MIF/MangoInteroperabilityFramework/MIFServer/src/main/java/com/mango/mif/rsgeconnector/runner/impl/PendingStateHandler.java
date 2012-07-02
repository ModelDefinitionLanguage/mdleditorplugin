/* File			:  PendingStateHandler.java
 * Project		:  MIFServer
 * Created on	:  30 May 2012
 */
package com.mango.mif.rsgeconnector.runner.impl;

import java.util.List;
import org.apache.log4j.Logger;
import com.google.common.base.Preconditions;
import com.mango.mif.core.domain.JobRuntimeData;
import com.mango.mif.core.domain.ExecutionJobValue;

/**
 * 
 * Pending state handler - Pending is the initial state of the state machine.
 * Responsible for Failure recovery - checks if job received was previously being
 * processed during a system failure.
 * 
 * 
 * @version $Revision: $ as of $Date: $
 * <p>SVN Entry : $HeadURL: $
 * <p>SVN ID    : $Id: $
 * <p>Last edited by : $Author: $
 */
public class PendingStateHandler extends RSGEStateHandler {

	public final static Logger	LOG				= Logger.getLogger(PreparingStateHandler.class);

	/**
	 * Indicates if the job propagated from the dispatcher is a hung job - 
	 * One that has previously been processed but didn't complete due to 
	 * a server crash.
	 */
	private boolean				hungJobFound	= false;

	/**
	 * Default Constructor
	 * 
	 * @param state
	 * @param exitEvent
	 */
	public PendingStateHandler(String state, String exitEvent) {
		super(state, exitEvent);
	}

	@Override
	protected void doProcessing() {
		Preconditions.checkNotNull(jobRunner.getJob(), "Job not set");

		//Failure recovery code - Pending is the initial state of the state machine.
		List<JobRuntimeData> hungJobs = executionJobRepository.getUncompletedJobs();
		ExecutionJobValue status = null;

		for (JobRuntimeData hungJob : hungJobs) {
			if (hungJob.getJobId().equalsIgnoreCase(jobRunner.getJob().getJobId())) {
				hungJobFound = true;

				//Set the hung job state on the jobrunner job
				jobRunner.getJob().setClientRequestStatus(hungJob.getValue());
				status = ExecutionJobValue.valueOf(hungJob.getValue());

				break;
			}
		}

		//Fire the appropriate event to take the job to the correct handler
		String eventToFire = "";

		if (hungJobFound) {
			eventToFire = getNextEvent(status);
			jobRunner.getDriver().fireEvent(eventToFire);
		}
	}
	
	private String getNextEvent(ExecutionJobValue status){
		
		String eventToFire = ""; 
		
		//Determine the event type to fire to the jobRunner driver
		switch (status) {

		case PENDING:
			eventToFire = "task.prepare";
			break;
		case PREPARING:
			eventToFire = "task.submit";
			break;
		case SUBMITTING:
			eventToFire = "task.process";
			break;
		case PROCESSING:
			eventToFire = "task.retrieve";
			break;
		case RETRIEVING:
			eventToFire = "task.postprocess";
			break;
		case POST_PROCESSING:
			eventToFire = "task.finished";
			break;
		default:
			eventToFire = "task.prepare";
		}
		
		return eventToFire;
	}

	@Override
	public String complete() {
		
		/*FIXME:  Add this code to initially create the JobRuntimeData record that the other
		  handlers do the status updates to...
		  
		JobRuntimeData execJob = new JobRuntimeData();
		execJob.setJobId(jobRunner.getJob().getExecutionRequest().getRequestId().toString());
		execJob.setKey(ExecutionJobKey.STATUS.getMessage());
        execJob.setValue(ExecutionJobValue.PENDING.getMessage());  
        execJob = executionJobRepository.save(execJob);
        LOG.info("Create new JobRuntimeData record during PENDING state: " + execJob.toString());
        */
		
		return super.complete();
	}
}
