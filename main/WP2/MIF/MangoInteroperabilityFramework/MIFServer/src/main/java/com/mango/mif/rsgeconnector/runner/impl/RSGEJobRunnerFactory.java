/* File			:  RJobRunnerRegistry.java
 * Project		:  MangoInteroperabilityFramework
 * Created on	:  Mar 13, 2012
 */
package com.mango.mif.rsgeconnector.runner.impl;

import java.io.IOException;
import java.util.List;

import javax.jms.IllegalStateException;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.io.Resource;

import com.google.common.base.Preconditions;
import com.mango.mif.connector.dispatcher.domain.Job;
import com.mango.mif.connector.runner.api.JobRunner;
import com.mango.mif.connector.runner.api.SCXMLDriver;
import com.mango.mif.connector.runner.impl.JobRunnerFactoryImpl;

/**
 * 
 * A factory responsible for creating Job Runners that execute SGE
 * 
 * @version $Revision: $ as of $Date: $
 * <p>SVN Entry : $HeadURL: $
 * <p>SVN ID    : $Id: $
 * <p>Last edited by : $Author: $
 */
public abstract class RSGEJobRunnerFactory extends JobRunnerFactoryImpl {
    /**
     * Logger
     */
    public static Logger LOG = Logger.getLogger(RSGEJobRunnerFactory.class);
    /**
     * SCXML definition file
     */
    @Value("classpath:META-INF/JobRunnerWithFailover.scxml")
    private Resource scxmlDefinition;
    
    @Override
    public JobRunner createJobRunner(Job job) {
    	RSGEJobRunner jobRunner = (RSGEJobRunner)super.createJobRunner(job);
        buildDriver(jobRunner);
        return jobRunner;
    }
    
    @Override
    protected abstract RSGEJobRunner createJobRunner();
    
    /**
     * Method injected by spring 
     * @return new instances of drivers
     */
    public abstract SCXMLDriver createDriver();
    
    /**
     * Registers handlers
     */
    private void buildDriver(RSGEJobRunner jobRunner) {
        List<RSGEStateHandler> stateHandlers = getStateHandlers();
        Preconditions.checkNotNull(stateHandlers, "Driver factory incorrectly configured - no state handlers registered.");
        Preconditions.checkNotNull(scxmlDefinition, "Driver factory incorrectly configured - SCXML definition not set.");

        SCXMLDriver driver = createDriver();
        try {
            driver.setSCXMLDocument(scxmlDefinition.getURL());
        } catch (IOException e) {
            LOG.error(e);
            throw new RuntimeException("Could not load JobRunner SCXML definition file",e);
        } catch (IllegalStateException e) {
            //the driver is defined as a prototype, so the exception should never be thrown, if so it is runtime exception
            LOG.error("Could not initialize the driver.",e);
            throw new RuntimeException(e);
        }
        for(RSGEStateHandler handler : stateHandlers) {
            handler.setJobRunner(jobRunner);
            // replacing a cancelled state handler with an empty handler and passing the original handler to job runner
            // job runner then will call the call method itself.
        	if(handler.getState().equals("cancelled")) {
        		handler.setSCXMLDriver(driver);
        		jobRunner.setCancelledStateHandler(handler);

                driver.registerHandler(new RSGEStateHandler(handler.getState()));
        	} else {
                driver.registerHandler(handler);
        	}
        }
        jobRunner.setDriver(driver);
    }
    /**
     * 
     * @return new instances of a list of state handlers
     */
    public abstract List<RSGEStateHandler> getStateHandlers();
}
