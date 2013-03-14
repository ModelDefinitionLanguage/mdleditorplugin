/* File			:  PendingStateHandler.java
 * Project		:  MIFServer
 * Created on	:  30 May 2012
 */
package com.mango.mif.sgeconnector;

import org.apache.log4j.Logger;
import com.google.common.base.Preconditions;
import com.mango.mif.connector.runner.impl.DefaultStateHandler;
import com.mango.mif.connector.runner.impl.JobRunnerState;
import com.mango.mif.connector.runner.impl.StateHandlerException;

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
public class PendingStateHandler extends DefaultStateHandler {

    public final static Logger LOG = Logger.getLogger(PendingStateHandler.class);

    /**
     * Default Constructor
     * 
     * @param state
     * @param exitEvent
     */
    public PendingStateHandler(String state, String exitEvent) {
        super(state, exitEvent);
    }

    /**
     * Checks if job is in final state and if not it proceeds with execution, if not it does nothing. JobRunner
     */
    @Override
    protected void doProcessing() {
        Preconditions.checkNotNull(job, "Job not set");
        JobRunnerState jobRunnerState = job.getJobRunnerState();
        if (!JobRunnerState.TASK_PENDING.equals(jobRunnerState)) {
            exitEvent = jobRunnerState.getTriggeringEvent();
        }
    }

    @Override
    protected void doPreprocessing() throws StateHandlerException {
        super.doPreprocessing();
        JobRunnerState runnerState = JobRunnerState.byStateName(handledState);
        if (runnerState.compareTo(job.getJobRunnerState()) > 0) {
            //save only if job has been saved last at earlier state
            this.job.setJobRunnerState(runnerState);
            this.job = jobManagementService.saveJob(job);
        } else {
            LOG.debug("Not updating STATE of the job " + job.getJobId() + ", failure recovery detected");
        }
    }

}
