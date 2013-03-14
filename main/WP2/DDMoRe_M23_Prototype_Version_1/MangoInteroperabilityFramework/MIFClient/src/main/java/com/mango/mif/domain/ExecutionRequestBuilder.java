/* File			:  ExecutionRequestBuilder.java
 * Project		:  MangoInteroperabilityFramework-trunk
 * Created on	:  5 Apr 2012
 */
package com.mango.mif.domain;

import java.util.Map;

import javax.xml.bind.JAXBException;

import com.google.common.base.Preconditions;
import com.mango.mif.utils.JAXBUtils;
import com.mango.mif.utils.jaxb.ExecutionJaxbUtils;

/**
 * Builds Execution request.
 *
 * @version $Revision: $ as of $Date: $
 * <p>SVN Entry : $HeadURL: $
 * <p>SVN ID    : $Id: $
 * <p>Last edited by : $Author: $
 */
public class ExecutionRequestBuilder {

    /** Execution request being build up. */
    private ExecutionRequest executionRequest;

    /**
     * Constructor.
     */
    public ExecutionRequestBuilder() {
        this.executionRequest = new ExecutionRequest();
    }

    /**
     * Sets command name.
     *
     * @param commandName the command name
     * @return the execution request builder
     */
    public ExecutionRequestBuilder setCommand(String command) {
        this.executionRequest.setCommand(command);
        return this;
    }

    public ExecutionRequestBuilder setName(String name) {
        executionRequest.setName(name);
        return this;
    }

    /**
     * Sets the user name.
     *
     * @param userName the user name
     * @return the execution request builder
     */
    public ExecutionRequestBuilder setUserName(String userName) {
        this.executionRequest.setUserName(userName);
        return this;
    }

    /**
     * Sets the user password.
     *
     * @param userPassword the user password
     * @return the execution request builder
     */
    public ExecutionRequestBuilder setUserPassword(String userPassword) {
        this.executionRequest.setUserPassword(userPassword);
        return this;
    }

    /**
     * Sets the execution parameters.
     *
     * @param executionParameters the execution parameters
     * @return the execution request builder
     */
    public ExecutionRequestBuilder setExecutionParameters(String executionParameters) {
        this.executionRequest.setExecutionParameters(executionParameters);
        return this;
    }

    /**
     * Previously known as setRunAsUserMode...
     * @param submitAsUserMode flag indicating whether we want run as user or run as navplus
     * @return the execution request builder
     */
    public ExecutionRequestBuilder setSubmitAsUserMode(boolean submitAsUserMode) {
        this.executionRequest.setSubmitAsUserMode(submitAsUserMode);
        return this;
    }

    /**
     * Sets the execution file.
     *
     * @param executionFile the execution file
     * @return the execution request builder
     */
    public ExecutionRequestBuilder setExecutionFile(String executionFile) {
        this.executionRequest.setExecutionFile(executionFile);
        return this;
    }

    /**
     * Sets execution type.
     *
     * @param executionType the execution type
     * @return the execution request builder
     */
    public ExecutionRequestBuilder setExecutionType(String executionType) {
        this.executionRequest.setType(executionType);
        return this;
    }

    /**
     * Sets request id.
     *
     * @param requestId the request id
     * @return the execution request builder
     */
    public ExecutionRequestBuilder setRequestId(String requestId) {
        this.executionRequest.setRequestId(requestId);
        return this;
    }

    /**
     * Sets the script preamble.
     * 
     * @param gridHostPreamble the grid preamble as a string.
     * @return the execution request builder
     */
    public ExecutionRequestBuilder setSubmitHostPreamble(String submitHostPreamble) {
        if (submitHostPreamble == null) {
            submitHostPreamble = "";
        }
        this.executionRequest.setSubmitHostPreamble(submitHostPreamble);
        return this;
    }

    /**
     * Sets the grid preamble.
     * 
     * @param gridHostPreamble the grid preamble as a string.
     * @return the execution request builder
     */
    public ExecutionRequestBuilder setGridHostPreamble(String gridHostPreamble) {
        if (gridHostPreamble == null) {
            gridHostPreamble = "";
        }
        this.executionRequest.setGridHostPreamble(gridHostPreamble);
        return this;
    }

    /**
     * Add a name/value pair to the request attributes.
     * @param name The (shell) variable name
     * @param value The value
     */
    public void addAttribute(String name, String value) {
        this.executionRequest.addRequestAttribute(name, value);
    }

    /**
     * Sets the request attributes.
     * 
     * @param requestAttributes the map of Name/Value pairs that are the request attributes.
     * @return the execution request builder
     */
    public ExecutionRequestBuilder setRequestAttributes(Map<String, String> requestAttributes) {
        this.executionRequest.setRequestAttributes(requestAttributes);
        return this;
    }

    /**
     * Builds execution request message.
     *
     * @return the execution request msg
     * @throws JAXBException the jAXB exception
     */
    public String getExecutionRequestMsg() throws JAXBException {
        Preconditions.checkNotNull(executionRequest.getType(), "Execution Request type not set.");
        Preconditions.checkNotNull(executionRequest.getRequestId(), "Execution Request ID not set.");

        return JAXBUtils.marshall(executionRequest, ExecutionJaxbUtils.CONTEXT_CLASSES);
    }

    /**
     * Builds execution request message. The returned Execution Request instance is different than the one internally used.
     *
     * @return the execution request
     * @throws JAXBException the jAXB exception
     */
    public ExecutionRequest getExecutionRequest() {
        return this.executionRequest;
    }
}
