/* File			:  ExecutionResponseBuilder.java
 * Project		:  MangoInteroperabilityFramework-trunk
 * Created on	:  19 Apr 2012
 */
package com.mango.mif.domain;

import javax.xml.bind.JAXBException;

import com.google.common.base.Preconditions;
import com.mango.mif.utils.JAXBUtils;
import com.mango.mif.utils.jaxb.ExecutionJaxbUtils;

/**
 * 
 * A builder that should be used to create valid Execution Response messages
 * 
 * @version $Revision: $ as of $Date: $
 * <p>SVN Entry : $HeadURL: $
 * <p>SVN ID    : $Id: $
 * <p>Last edited by : $Author: $
 */
public class ExecutionResponseBuilder {
    /**
     * Instance that is being built
     */
    private ExecutionResponse response;
    /**
     * Constructor
     */
    public ExecutionResponseBuilder() {
        response = new ExecutionResponse();
    }
    /**
     * Set Request ID on the response message
     * @param requestId
     * @return
     */
    public ExecutionResponseBuilder setRequestId(String requestId) {
        Preconditions.checkNotNull(requestId, "Request ID can't be null.");
        response.setRequestId(requestId);
        return this;
    }
    /**
     * Sets Status on the response message
     * @param status
     * @return
     */
    public ExecutionResponseBuilder setStatus(String status) {
        Preconditions.checkNotNull(status, "Status can't be null.");
        response.setStatus(status);
        return this;
    }

    /**
     * Sets Result on the response message
     * @param result
     * @return
     */
    public ExecutionResponseBuilder setResult(String result) {
        response.setResult(result);
        return this;
    }

    /**
     * Sets Result Details on the response message
     * @param result
     * @return
     */
    public ExecutionResponseBuilder setResultDetails(String resultDetails) {
        response.setResultDetails(resultDetails);
        return this;
    }
    
    /**
     * Sets the final summary message for this execution
     * 
     * @param summaryMessage the summary message to be set
     * @return this ExecutionResponseBuilder object
     */
    public ExecutionResponseBuilder setSummaryMessage(String summaryMessage) {
    	response.setSummaryMessage(summaryMessage);
    	return this;
    }

    /**
     * 
     * @return Execution response. the returned instance is a copy of the internal response object instance.
     * @throws JAXBException
     */
    public ExecutionResponse getExecutionResponse() throws JAXBException {
        return JAXBUtils.unmarshall(getExecutionResponseMsg(), ExecutionJaxbUtils.CONTEXT_CLASSES);
    }
    /**
     * 
     * @return xml representation of the execution response
     * @throws JAXBException
     */
    public String getExecutionResponseMsg() throws JAXBException {
        Preconditions.checkNotNull(response.getRequestId(), "Request ID not set");
        Preconditions.checkNotNull(response.getStatus(), "Status message not set");
        return JAXBUtils.marshall(response,ExecutionJaxbUtils.CONTEXT_CLASSES);
    }
}
