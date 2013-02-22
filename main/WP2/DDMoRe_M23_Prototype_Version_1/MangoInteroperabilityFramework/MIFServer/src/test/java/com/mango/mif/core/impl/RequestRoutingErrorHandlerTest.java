/* File			:  RequestRoutingErrorHandlerTest.java
 * Project		:  MIFServer
 * Created on	:  Jul 25, 2012
 */
package com.mango.mif.core.impl;

import static org.mockito.Matchers.any;
import static org.mockito.Matchers.eq;
import static org.mockito.Mockito.verify;

import java.util.UUID;

import org.junit.Before;
import org.junit.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.jms.core.JmsTemplate;
import org.springframework.jms.core.MessageCreator;

import com.mango.mif.domain.ExecutionRequestBuilder;


/**
 * @version $Revision: $ as of $Date: $
 * <p>SVN Entry : $HeadURL: $
 * <p>SVN ID    : $Id: $
 * <p>Last edited by : $Author: $
 */
public class RequestRoutingErrorHandlerTest {
    /**
     * jms template
     */
    @Mock JmsTemplate jmsTemplate;
    /**
     * mock execution request
     */
    String executionRequest;
    /**
     * Routing error handler
     */
    RequestRoutingErrorHandler errorHandler;
    
    @Before
    public void setUp() throws Exception {
        MockitoAnnotations.initMocks(this);
        ExecutionRequestBuilder requestBuilder = new ExecutionRequestBuilder()
            .setRequestId(UUID.randomUUID().toString()).setExecutionType("MOCK-CONNECTOR").setCommand("MOCK-COMMAND");
        executionRequest = requestBuilder.getExecutionRequestMsg();
        
        errorHandler = new RequestRoutingErrorHandler("MOCK-RESPONSE-QUEUE",jmsTemplate);
        
    }

    @Test(expected=NullPointerException.class)
    public void shouldThrowExceptionIfMessageTextIsNull() {
        errorHandler.destinationNotResolved(null);
    }

    @Test(expected=NullPointerException.class)
    public void shouldThrowExceptionIfResponseQueueNotSet() {
        errorHandler.setResponseQueue(null);
        errorHandler.destinationNotResolved(executionRequest);
    }


    @Test(expected=NullPointerException.class)
    public void shouldThrowExceptionIfJMSTemplateNotSet() {
        errorHandler.setJmsTemplate(null);
        errorHandler.destinationNotResolved(executionRequest);
    }

    @Test
    public void shouldSendAResponseMessage() {
        errorHandler.destinationNotResolved(executionRequest);
        verify(jmsTemplate).send(eq("MOCK-RESPONSE-QUEUE"), (MessageCreator) any());
    }
}
