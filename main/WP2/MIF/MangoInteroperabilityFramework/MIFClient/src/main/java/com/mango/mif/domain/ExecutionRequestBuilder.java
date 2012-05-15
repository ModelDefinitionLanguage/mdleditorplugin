/* File			:  ExecutionRequestBuilder.java
 * Project		:  MangoInteroperabilityFramework-trunk
 * Created on	:  5 Apr 2012
 */
package com.mango.mif.domain;

import java.util.List;
import java.util.Map;
import java.util.UUID;

import javax.xml.bind.JAXBException;

import com.google.common.base.Preconditions;
import com.mango.mif.utils.JAXBUtils;
import com.mango.mif.utils.jaxb.ExecutionJaxbUtils;

/**
 * 
 * Builds Execution request
 * 
 * @version $Revision: $ as of $Date: $
 * <p>SVN Entry : $HeadURL: $
 * <p>SVN ID    : $Id: $
 * <p>Last edited by : $Author: $
 */
public class ExecutionRequestBuilder {
    /**
     * Execution request being build up
     */
    private ExecutionRequest executionRequest;
    /**
     * Constructor
     */
    public ExecutionRequestBuilder() {
        this.executionRequest = new ExecutionRequest();
        this.executionRequest.setCommandDefinition(new CommandDefinition());
    }
    /**
     * Sets command name
     * @param commandName
     * @return
     */
    public ExecutionRequestBuilder setCommandName(String commandName) {
        this.executionRequest.getCommandDefinition().setName(commandName);
        return this;
    }
    /**
     * Sets connector ID
     * @param type
     * @return
     */
    public ExecutionRequestBuilder setConnectorID(String connectorId) {
        this.executionRequest.setType(connectorId);
        return this;
    }
    /**
     * Sets request id
     * @param requestId
     * @return
     */
    public ExecutionRequestBuilder setRequestId(UUID requestId) {
        this.executionRequest.setRequestId(requestId);
        return this;
    }
    
    /**
     * Sets command definition, must not be null.
     * @param commandDefinition
     * @return
     */
    public ExecutionRequestBuilder setCommandDefinition(CommandDefinition commandDefinition) {
        Preconditions.checkNotNull(executionRequest.getCommandDefinition(), "Command definition is null");
        this.executionRequest.setCommandDefinition(commandDefinition);
        return this;
    }
    /**
     * Sets input parameters
     * @param inputParameters
     * @return
     */
    public ExecutionRequestBuilder setInputParameters(Map<String, Parameter> inputParameters) {
        this.executionRequest.setParameters(inputParameters);
        return this;
    }
    /**
     * Sets parameter definitions
     * @param commandParameterDefinitions
     * @return
     */
    public ExecutionRequestBuilder setCommandParameterDefinitions(List<ParameterDefinition> commandParameterDefinitions) {
        this.executionRequest.getCommandDefinition().setParameters(commandParameterDefinitions);
        return this;
    }
    /**
     * Builds execution request message
     * @return
     * @throws JAXBException
     */
    public String getExecutionRequestMsg() throws JAXBException {
        Preconditions.checkNotNull(executionRequest.getCommandDefinition().getName(), "Command Definition name not set.");
        Preconditions.checkNotNull(executionRequest.getType(), "Execution Request type not set.");
        Preconditions.checkNotNull(executionRequest.getRequestId(), "Execution Request ID not set.");
        
        return JAXBUtils.marshall(executionRequest, ExecutionJaxbUtils.EXECUTION_REQUEST_CLASS_ARRAY);
    }
    /**
     * Builds execution request message. The returned Execution Request instance is different than the one internally used.
     * @return
     * @throws JAXBException
     */
    public ExecutionRequest getExecutionRequest() throws JAXBException {
        return JAXBUtils.unmarshall(getExecutionRequestMsg(), ExecutionJaxbUtils.EXECUTION_REQUEST_CLASS_ARRAY);
    }
}
