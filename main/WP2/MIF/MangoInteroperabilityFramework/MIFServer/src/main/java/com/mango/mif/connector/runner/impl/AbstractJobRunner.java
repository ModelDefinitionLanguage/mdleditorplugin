/* File			:  JobRunnerImpl.java
 * Project		:  MangoInteroperabilityFramework
 * Created on	:  13 Mar 2012
 */
package com.mango.mif.connector.runner.impl;

import com.google.common.base.Preconditions;
import com.mango.mif.connector.dispatcher.domain.Job;
import com.mango.mif.connector.runner.api.JobRunner;
import com.mango.mif.core.api.WorkspaceManager;


/**
 * @version $Revision: $ as of $Date: $
 * <p>SVN Entry : $HeadURL: $
 * <p>SVN ID    : $Id: $
 * <p>Last edited by : $Author: $
 * 
 * Basic Job Runner implementation
 */
public abstract class AbstractJobRunner implements JobRunner {   
    /**
     * Job being processed
     */
    protected Job job;
    /**
     * Workspace Manager 
     */
    protected WorkspaceManager workspaceManager;

    @Override
    public void setJob(Job job) {
        this.job = job;
    }

    @Override
    public Job getJob() {
        return job;
    }
    
    @Override
    public String toString() {
    	String str = (job == null)? "" : job.getExecutionRequestMsg(); 
    	return str;
    }

    @Override
    public WorkspaceManager getWorkspaceManager() {
        return workspaceManager;
    }

    @Override
    public void setWorkspaceManager(WorkspaceManager workspaceManager) {
        Preconditions.checkNotNull(workspaceManager, "Workspace manager can't be null");
        this.workspaceManager = workspaceManager;
    }
}
