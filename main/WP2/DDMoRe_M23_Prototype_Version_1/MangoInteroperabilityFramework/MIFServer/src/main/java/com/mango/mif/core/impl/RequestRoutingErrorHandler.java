/* File			:  RequestRoutingErrorHandler.java
 * Project		:  MIFServer
 * Created on	:  Jul 25, 2012
 */
package com.mango.mif.core.impl;

import javax.xml.bind.JAXBException;

import org.springframework.jms.core.JmsTemplate;

import com.mango.mif.connector.runner.impl.DriverProcessingStatus;
import com.mango.mif.domain.ExecutionRequest;
import com.mango.mif.domain.ExecutionResponseBuilder;

/**
 * @version $Revision: $ as of $Date: $
 * <p>SVN Entry : $HeadURL: $
 * <p>SVN ID    : $Id: $
 * <p>Last edited by : $Author: $
 * 
 * Implements error handling for Execution requests router.
 */
public class RequestRoutingErrorHandler extends BaseRoutingErrorHandler {
    /**
     * Constructor
     * @param responseQueue
     * @param jmsTemplate
     */
    public RequestRoutingErrorHandler(String responseQueue, JmsTemplate jmsTemplate) {
        super(responseQueue, jmsTemplate);
    }

    @Override
    public void destinationNotResolved(String messageText) {
        super.destinationNotResolved(messageText);
        ExecutionRequest executionRequest = MessagingHelper.parseExecutionRequest(messageText);
        ExecutionResponseBuilder executionResponseBuilder = new ExecutionResponseBuilder()
        .setStatus(DriverProcessingStatus.FAILED.getMessage()).setRequestId(executionRequest.getRequestId()).setResult("Execution type " + executionRequest.getType() + " not found");
        
        try {
            final String executionResponse = executionResponseBuilder.getExecutionResponseMsg();
            sendMessage(executionResponse);
        } catch (JAXBException e) {
            throw new RuntimeException("Could not create an exection response for " + messageText);
        }
        
    }
}
