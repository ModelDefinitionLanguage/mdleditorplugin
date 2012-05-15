/* File			:  RetrievingStateHandler.java
 * Project		:  MangoInteroperabilityFramework
 * Created on	:  20 Mar 2012
 */
package com.mango.mif.rconnectorcli.runner.impl;

import java.io.File;
import java.net.MalformedURLException;
import java.util.Map;
import java.util.Map.Entry;

import org.apache.log4j.Logger;

import com.google.common.collect.Maps;
import com.mango.mif.core.api.MIFResource;
import com.mango.mif.core.api.ResourceComponent;
import com.mango.mif.core.api.ResourceComponentException;
import com.mango.mif.domain.ExecutionRequest;
import com.mango.mif.domain.Parameter;
import com.mango.mif.domain.ParameterType;
import com.mango.mif.rconnectorcli.internal.RScriptResultsParser;

/**
 * Retrieving state handler
 * 
 * @version $Revision: $ as of $Date: $
 * <p>SVN Entry : $HeadURL: $
 * <p>SVN ID    : $Id: $
 * <p>Last edited by : $Author: $
 */
public class RetrievingStateHandler extends RScriptStateHandler {
    /**
     * Logger
     */
    private static Logger LOG = Logger.getLogger(RetrievingStateHandler.class);
    /**
     * Resource component
     */
    private ResourceComponent resourceComponent;
    /**
     * Default Constructor
     * 
     * @param state
     * @param exitEvent
     */
    public RetrievingStateHandler(String state, String exitEvent) {
        super(state, exitEvent);
    }

    @Override
    protected void doProcessing() {
        LOG.debug("Retrieving results for job: " + jobRunner.getJob().getJobId());
        ExecutionRequest startRequest = jobRunner.getJob().getExecutionRequest();
        
        RScriptResultsParser resultParser = new RScriptResultsParser();
        resultParser.setCommandDefinition(startRequest.getCommandDefinition());
        resultParser.setParameters(startRequest.getParameters());
        resultParser.setWorkingDirectory(jobRunner.getWorkspaceManager().getWorkingDirectory());

        
        Map<String,File> resultFiles = resultParser.getResultFiles();

        Map<String,Parameter> results = Maps.newHashMap();
        
        for(Entry<String,File> en : resultFiles.entrySet()) {
            try {
                MIFResource resource = resourceComponent.addResponseResource(en.getValue().toURI().toURL(),startRequest.getRequestId().toString(), en.getValue().getName());
                results.put(en.getKey(), new Parameter(en.getKey(), en.getValue().getName(), resource.getUuid().toString(), ParameterType.File));
            } catch (MalformedURLException e) {
                throw new RuntimeException(e);
            } catch (ResourceComponentException e) {
                throw new RuntimeException(e);
            }
        }
        
        jobRunner.setResults(results);
    }

    public void setResourceComponent(ResourceComponent resourceComponent) {
        this.resourceComponent = resourceComponent;
    }
    
    public ResourceComponent getResourceComponent() {
        return resourceComponent;
    }
}