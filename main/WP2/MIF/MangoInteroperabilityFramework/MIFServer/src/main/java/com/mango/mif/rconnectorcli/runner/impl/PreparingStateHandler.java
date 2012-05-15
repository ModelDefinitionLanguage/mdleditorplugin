/* File			:  RetrievingStateHandler.java
 * Project		:  MangoInteroperabilityFramework
 * Created on	:  20 Mar 2012
 */
package com.mango.mif.rconnectorcli.runner.impl;

import java.io.File;
import java.io.IOException;

import javax.xml.bind.JAXBException;

import org.apache.log4j.Logger;

import com.google.common.base.Preconditions;
import com.mango.mif.core.api.MIFResource;
import com.mango.mif.core.api.ResourceComponent;
import com.mango.mif.core.api.ResourceComponentException;
import com.mango.mif.core.api.ResourceUtils;
import com.mango.mif.domain.ExecutionRequest;
import com.mango.mif.domain.Parameter;
import com.mango.mif.domain.ParameterDefinition;
import com.mango.mif.domain.ParameterDirection;
import com.mango.mif.domain.ParameterType;
import com.mango.mif.rconnectorcli.internal.RScriptBuilder;
import com.mango.mif.rconnectorcli.internal.RScriptExec;

/**
 * Preparing state handler.
 * 
 * Takes Job details held by JobRunner and prepares input files (script/datasets) in working directory for R
 * 
 * @version $Revision: $ as of $Date: $
 * <p>SVN Entry : $HeadURL: $
 * <p>SVN ID    : $Id: $
 * <p>Last edited by : $Author: $
 */
public class PreparingStateHandler extends RScriptStateHandler {
    
    public final static Logger LOG = Logger.getLogger(PreparingStateHandler.class);
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
    public PreparingStateHandler(String state, String exitEvent) {
        super(state, exitEvent);
    }

    @Override
    protected void doProcessing() {
        Preconditions.checkNotNull(resourceComponent, "Resource Component not set.");
        Preconditions.checkNotNull(jobRunner.getJob(), "Job not set");

        ExecutionRequest executionRequest = jobRunner.getJob().getExecutionRequest();
        copyInputs(executionRequest);
        File scriptFile = prepareScript(executionRequest);
        LOG.info("R script file that will be invoked: " + scriptFile.getAbsolutePath());
        RScriptExec rExec = jobRunner.getExec();
        rExec.setScriptFile(scriptFile);
    }
    /**
     * Copies inputs to working directory
     * @throws JAXBException 
     * @throws IOException 
     */
    private void copyInputs(ExecutionRequest executionRequest) {
        Preconditions.checkNotNull(executionRequest, "Execution Request can't be null.");
        for(ParameterDefinition pDef : executionRequest.getCommandDefinition().getParameters()) {
            if(pDef.getDirection().equals(ParameterDirection.IN)) {
                if(pDef.getType().equals(ParameterType.File)) {
                    Parameter parameter = executionRequest.getParameters().get(pDef.getName());
                    Preconditions.checkNotNull(parameter, "Parameter for command " + executionRequest.getCommandDefinition().getName() +" can't be null.");
                    try {
                        MIFResource from = resourceComponent.getResource(parameter.getResourceUuid());
                        File to = new File(jobRunner.getWorkspaceManager().getWorkingDirectory(),parameter.getValue());
                        LOG.debug("Copying " + from.getContent() + " to " + to.getAbsolutePath());
                        ResourceUtils.copy(from,to );
                    } catch(IOException e) {
                        throw new RuntimeException("Error copying command input files.",e);
                    } catch (ResourceComponentException e) {
                        throw new RuntimeException("Error retrieving file from the Resource Component.",e);
                    }
                }
            }
        }
    }
    /**
     * Prepares R script file that will be executed by R. The returned script file won't be the one
     * selected by a user, it will a new r script file that references/includes the user script file
     * @return
     */
    private File prepareScript(ExecutionRequest executionRequest) {
        RScriptBuilder scriptBuilder = new RScriptBuilder();
        scriptBuilder.setCommandDefinition(executionRequest.getCommandDefinition());
        scriptBuilder.setParameters(executionRequest.getParameters());
        scriptBuilder.setWorkingDirectory(jobRunner.getWorkspaceManager().getWorkingDirectory());
        return scriptBuilder.getScriptFile();
    }
    
    public void setResourceComponent(ResourceComponent resourceComponent) {
        this.resourceComponent = resourceComponent;
    }
    
    public ResourceComponent getResourceComponent() {
        return resourceComponent;
    }
}