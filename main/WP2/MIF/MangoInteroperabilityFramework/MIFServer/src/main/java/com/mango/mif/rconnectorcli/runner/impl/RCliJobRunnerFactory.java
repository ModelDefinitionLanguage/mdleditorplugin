/* File			:  RJobRunnerRegistry.java
 * Project		:  MangoInteroperabilityFramework
 * Created on	:  Mar 13, 2012
 */
package com.mango.mif.rconnectorcli.runner.impl;

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
 * A factory responsible for creating Job Runners that invoke R
 * 
 * @version $Revision: $ as of $Date: $
 * <p>SVN Entry : $HeadURL: $
 * <p>SVN ID    : $Id: $
 * <p>Last edited by : $Author: $
 */
public abstract class RCliJobRunnerFactory extends JobRunnerFactoryImpl {
    /**
     * Logger
     */
    public static Logger LOG = Logger.getLogger(RCliJobRunnerFactory.class);
    /**
     * SCXML definition file
     */
    @Value("classpath:META-INF/JobRunner.scxml")
    private Resource scxmlDefinition;
    
    @Override
    public JobRunner createJobRunner(Job job) {
        RCliJobRunner jobRunner = (RCliJobRunner)super.createJobRunner(job);
        buildDriver(jobRunner);
        return jobRunner;
    }
    
    @Override
    protected abstract RCliJobRunner createJobRunner();
    
    /**
     * Method injected by spring 
     * @return new instances of drivers
     */
    public abstract SCXMLDriver createDriver();
    
    /**
     * Registers handlers
     */
    private void buildDriver(RCliJobRunner jobRunner) {
        List<RScriptStateHandler> stateHandlers = getStateHandlers();
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
        for(RScriptStateHandler handler : stateHandlers) {
            handler.setJobRunner(jobRunner);
            driver.registerHandler(handler);
        }
        jobRunner.setDriver(driver);
    }
    /**
     * 
     * @return new instances of a list of state handlers
     */
    public abstract List<RScriptStateHandler> getStateHandlers();
}
