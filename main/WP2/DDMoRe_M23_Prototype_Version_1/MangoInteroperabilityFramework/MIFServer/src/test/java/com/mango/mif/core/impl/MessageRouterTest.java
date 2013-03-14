/* File			:  RequestRouterTest.java
 * Project		:  MIFServer
 * Created on	:  Jul 10, 2012
 */
package com.mango.mif.core.impl;

import javax.jms.TextMessage;

import org.junit.Before;
import org.junit.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.jms.core.JmsTemplate;
import org.springframework.jms.core.MessageCreator;

import static org.mockito.Mockito.*;

import com.mango.mif.core.impl.MessageRouter;
import com.mango.mif.core.impl.QueueResolver;
import com.mango.mif.core.impl.RoutingErrorHandler;

/**
 * @version $Revision: $ as of $Date: $
 * <p>SVN Entry : $HeadURL: $
 * <p>SVN ID    : $Id: $
 * <p>Last edited by : $Author: $
 * 
 * Tests the message router
 */
public class MessageRouterTest {
	/**
	 * request router
	 */
	private MessageRouter messageRouter;
	/**
	 * jms template
	 */
	@Mock JmsTemplate jmsTemplate;
	/**
	 * queue resolver
	 */
	@Mock QueueResolver queueResolver;
	/**
	 * JMS text message
	 */
	@Mock TextMessage textMessage;
    /**
     * Routing error handler
     */
    @Mock RoutingErrorHandler errorHandler;
	
	@Before
	public void setUp() throws Exception {
		MockitoAnnotations.initMocks(this);
		
		messageRouter = new MessageRouter();
		messageRouter.setJmsTemplate(jmsTemplate);
		messageRouter.setQueueResolver(queueResolver);
        messageRouter.setRoutingErrorHandler(errorHandler);
		when(textMessage.getText()).thenReturn("MOCK-MESSAGE");
	}

	@Test
	public void shouldSendAMessageToADestinationQueueResolvedByQueueResolver() {
		when(queueResolver.resolve("MOCK-MESSAGE")).thenReturn("MOCK-QUEUE");
		messageRouter.onMessage(textMessage);
		verify(jmsTemplate).send(eq("MOCK-QUEUE"), (MessageCreator) any());
	}

	@Test(expected=NullPointerException.class)
	public void shouldThrowExceptionIfDestinationQueueIsNotResolvedToNull() {
        messageRouter.publishToConnectorQueue(null,"MOCK-MESSAGE");
	}
	
	@Test(expected=IllegalArgumentException.class)
	public void shouldThrowExceptionIfDestinationQueueIsResolvedToEmptyQueueName() {
		messageRouter.publishToConnectorQueue("","MOCK-MESSAGE");
	}

    @Test
    public void shouldInvokeErrorHandlerIfDestinationEmptyOrNull() {
        when(queueResolver.resolve("MOCK-MESSAGE")).thenReturn("");
        messageRouter.onMessage(textMessage);
        verify(errorHandler).destinationNotResolved("MOCK-MESSAGE");
    }
	
	
    @Test(expected=NullPointerException.class)
    public void shouldThrowExceptionIfRoutingErrorHandlerIsNotSet() {
        messageRouter.setRoutingErrorHandler(null);
        when(queueResolver.resolve("MOCK-MESSAGE")).thenReturn("");
        messageRouter.onMessage(textMessage);
        verify(jmsTemplate).send(eq("MOCK-QUEUE"), (MessageCreator) any());
    }
}
