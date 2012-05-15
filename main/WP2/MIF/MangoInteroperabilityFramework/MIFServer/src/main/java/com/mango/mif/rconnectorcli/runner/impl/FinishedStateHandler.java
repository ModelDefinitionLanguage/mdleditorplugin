/* File			:  FinishedStateHandler.java
 * Project		:  MangoInteroperabilityFramework
 * Created on	:  02 Apr 2012
 */
package com.mango.mif.rconnectorcli.runner.impl;

import java.util.Map;

import org.apache.log4j.Logger;

import com.mango.mif.core.api.ResourceComponent;
import com.mango.mif.domain.ExecutionResponse;
import com.mango.mif.domain.Parameter;

/**
 * Finished state handler
 * 
 * @version $Revision: $ as of $Date: $
 * <p>SVN Entry : $HeadURL: $
 * <p>SVN ID    : $Id: $
 * <p>Last edited by : $Author: $
 */
public class FinishedStateHandler extends RScriptStateHandler {
    /**
     * Logger
     */
    private static Logger LOG = Logger.getLogger(FinishedStateHandler.class);
    /**
     * Resource component
     */
    private ResourceComponent resourceComponent;
    /**
     * Finished message
     */
    private ExecutionResponse executionResponse;
    /**
     * Default Constructor
     * 
     * @param state
     */
    public FinishedStateHandler(String state) {
        super(state);
    }

    @Override
    protected void doProcessing() {
        LOG.debug("Finished Processing of job: " + jobRunner.getJob().getJobId());
        Map<String, Parameter> results = jobRunner.getResults();
        
        executionResponse = new ExecutionResponse();
        executionResponse.setRequestId(jobRunner.getJob().getExecutionRequest().getRequestId());
        executionResponse.setParameters(results);
        
    }

    public void setResourceComponent(ResourceComponent resourceComponent) {
        this.resourceComponent = resourceComponent;
    }
    
    public ResourceComponent getResourceComponent() {
        return resourceComponent;
    }
    
    
    public ExecutionResponse getFinishedMessage() {
        return executionResponse;
    }
    
}