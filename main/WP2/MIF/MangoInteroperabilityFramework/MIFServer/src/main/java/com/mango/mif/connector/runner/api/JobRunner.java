/* File			:  JobRunner.java
 * Project		:  MangoInteroperabilityFramework
 * Created on	:  13 Mar 2012
 */
package com.mango.mif.connector.runner.api;

import java.util.concurrent.Callable;

import com.mango.mif.connector.dispatcher.domain.Job;
import com.mango.mif.connector.runner.domain.JobMessage;
import com.mango.mif.core.api.WorkspaceManager;

/**
 * Classes implementing the interface are responsible for execution of a job
 * 
 * @version $Revision: $ as of $Date: $
 * <p>SVN Entry : $HeadURL: $
 * <p>SVN ID    : $Id: $
 * <p>Last edited by : $Author: $
 * 
 */
public interface JobRunner extends Callable<JobMessage> {
    /**
     * Cancels processing
     */
    void cancelProcessing();
    /**
     * Starts processing.
     */
    void startProcessing();
    /**
     * Sets the job that the runner should process
     * @param job
     */
    void setJob(Job job);
    /**
     * 
     * @return job
     */
    Job getJob();
    /**
     * Assigns workspace manager to the Job Runner instance
     * @param workspaceManager
     */
    void setWorkspaceManager(WorkspaceManager workspaceManager);
    /**
     * 
     * @return workspace manager
     */
    WorkspaceManager getWorkspaceManager();
}
