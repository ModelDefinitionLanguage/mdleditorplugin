/* File			:  DefaultStateHandler.java
 * Project		:  MangoInteroperabilityFramework
 * Created on	:  20 Mar 2012
 */
package com.mango.mif.connector.runner.impl;

import org.springframework.beans.factory.annotation.Required;

import com.mango.mif.connector.domain.Job;
import com.mango.mif.core.api.SharedLocationManager;
import com.mango.mif.managers.JobManagementService;
import com.mango.mif.utils.encrypt.Encrypter;

/**
 * A default state handler that does not do any processing
 * 
 * @version $Revision: $ as of $Date: $
 * <p>SVN Entry : $HeadURL: $
 * <p>SVN ID    : $Id: $
 * <p>Last edited by : $Author: $
 */
public class DefaultStateHandler extends AbstractStateHandler {
    
    protected JobManagementService jobManagementService;    
	protected SharedLocationManager sharedLocationManager;
    protected Encrypter encrypter;
    private String sgeSharedLocation;
    
    protected Job job;
    
    protected DefaultJobRunner jobRunner;
    
    /**
     * Constructor
     * @param state
     * @param exitEvent
     */
    public DefaultStateHandler(String state, String exitEvent) {
        super(state, exitEvent);
    }

    /**
     * Constructor
	 * @param state
	 */
	public DefaultStateHandler(String state) {
		super(state);
	}

	@Override
    protected void doProcessing() throws StateHandlerException {
        LOG.debug("Processing " + getState());
    }	
	
    @Override
    public String failed() {
        return JobRunnerState.FAILED.getTriggeringEvent();
    }

    /**
     * @param sharedLocationManager the sharedLocationManager to set
     */
    public void setSharedLocationManager(SharedLocationManager sharedLocationManager) {
        this.sharedLocationManager = sharedLocationManager;
    }
    
	/**
	 * @param encrypter the encrypter to set
	 */
	public void setEncrypter(Encrypter encrypter) {
		this.encrypter = encrypter;
	}
    
    @Override
    protected void doPreprocessing() throws StateHandlerException {
        LOG.debug("Preprocessing " + getState());
        job = jobManagementService.getJob(jobRunner.getJobId());
    }

    @Override
    protected void doPostProcessing() throws StateHandlerException {
        LOG.debug("PostProcessing " + getState());
    }

    @Override
    public void cancelProcessing() {
        LOG.debug("cancelProcessing " + getState());
    }
    
    public JobManagementService getJobManagementService() {
		return jobManagementService;
	}

    @Required
	public void setJobManagementService(JobManagementService jobManagementService) {
		this.jobManagementService = jobManagementService;
	}
    
    @Required
	public void setSgeSharedLocation(String sgeSharedLocation) {
		this.sgeSharedLocation = sgeSharedLocation;
	}

	public String getSgeSharedLocation() {
		return sgeSharedLocation;
	}

    public void setJobRunner(DefaultJobRunner jobRunner) {
        this.jobRunner = jobRunner;
    }
    
    
    public DefaultJobRunner getJobRunner() {
        return jobRunner;
    }

    protected Job getJob() {
        return job;
    }
    
}
