/* File			:  DefaultStateHandler.java
 * Project		:  MangoInteroperabilityFramework
 * Created on	:  20 Mar 2012
 */
package com.mango.mif.connector.runner.impl;

import org.springframework.beans.factory.annotation.Required;

import com.mango.mif.connector.dispatcher.dao.JobRepository;
import com.mango.mif.core.api.SharedLocationManager;
import com.mango.mif.core.dao.ExecutionJobRepository;
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
    /**
     * The dao used for persistence of the jobs being run by external tools.
     */
    protected ExecutionJobRepository executionJobRepository;
    
    /**
     * The dao used for persistence of the dispatcher jobs.
     */
    protected JobRepository jobRepository;
    /**
     * Shared location manager
     */
    protected SharedLocationManager sharedLocationManager;
    /**
     * encrypter
     */
    protected Encrypter encrypter;
    /**
     * Constructor
     * @param state
     * @param exitEvent
     */
    public DefaultStateHandler(String state, String exitEvent) {
        super(state, exitEvent);
    }

    @Override
    protected void doProcessing() throws StateHandlerException {
        //Do nothing
        LOG.debug("Processing " + getState());
    }

	/**
	 * @return the executionJobRepository
	 */
	public ExecutionJobRepository getExecutionJobRepository() {
		return executionJobRepository;
	}

	/**
	 * @param jobRepository the executionJobRepository to set
	 */
	@Required
	public void setExecutionJobRepository(ExecutionJobRepository executionJobRepository) {
		this.executionJobRepository = executionJobRepository;
	}
	
	/**
	 * @return the jobRepository
	 */
	public JobRepository getJobRepository() {
		return jobRepository;
	}
	
	/**
	 * @param jobRepository the jobRepository to set
	 */
	@Required
	public void setJobRepository(JobRepository jobRepository) {
		this.jobRepository = jobRepository;
	}
	
    @Override
    public String failed() {
        return JobRunnerSates.FAILED.getTriggeringEvent();
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
}
