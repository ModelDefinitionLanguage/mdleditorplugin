/* File			:  MessageRouter.java
 * Project		:  MIFServer
 * Created on	:  Jul 10, 2012
 */
package com.mango.mif.core.impl;

import javax.jms.JMSException;
import javax.jms.Message;
import javax.jms.MessageListener;
import javax.jms.Session;
import javax.jms.TextMessage;

import org.apache.commons.lang.StringUtils;
import org.apache.log4j.Logger;
import org.springframework.jms.core.JmsTemplate;
import org.springframework.jms.core.MessageCreator;

import com.google.common.base.Preconditions;
import com.mango.mif.connector.ConnectorExceptions;

/**
 * @version $Revision: $ as of $Date: $
 * <p>SVN Entry : $HeadURL: $
 * <p>SVN ID    : $Id: $
 * <p>Last edited by : $Author: $
 * 
 * Component responsible for routing requests from a client to an appropriate queue
 * 
 */
public class MessageRouter implements MessageListener {
	/**
	 * Logger
	 */
	private final static Logger LOG = Logger.getLogger(MessageRouter.class);
	/**
	 * Resolver
	 */
	private QueueResolver queueResolver;
	/** 
	 * The jms template. 
	 */
	private JmsTemplate jmsTemplate;
	/**
	 * Handles routing errors
	 */
	private RoutingErrorHandler routingErrorHandler;
	
	@Override
	public void onMessage(Message message) {
		Preconditions.checkNotNull(message, "Received null message");
		Preconditions.checkNotNull(queueResolver,"No routing resolver set");
		Preconditions.checkNotNull(jmsTemplate,"Template is not set");
        Preconditions.checkNotNull(routingErrorHandler,"Routing error handler is not set");
		TextMessage msg = (TextMessage) message;
		String receivedText = null;
		try {
			receivedText = msg.getText();
		} catch (JMSException e) {
			LOG.error(e);
			throw new RuntimeException(ConnectorExceptions.INVALID_REQUEST_MESSAGE.getMessage());
		}
		Preconditions.checkArgument(StringUtils.isNotEmpty(receivedText), "Received empty request message");
		LOG.debug("Received the message from navigator" + receivedText);
		String destination = queueResolver.resolve(receivedText);
		
		if(StringUtils.isEmpty(destination)) {
            LOG.error("Destination queue could not be resolved for message " + receivedText);
		    routingErrorHandler.destinationNotResolved(receivedText);
		    return; // consume the message
		}
		publishToConnectorQueue(destination,receivedText);
	}
	/**
	 * Publishes a message to the appropriate connector cancellation queue
	 * @param message
	 */
	void publishToConnectorQueue(String destination, String message) {
		Preconditions.checkNotNull(destination,"Destination queue can't be null");
		Preconditions.checkArgument(!StringUtils.isEmpty(destination), "Destination queue could not be resolved");
		Preconditions.checkNotNull(message,"Message can't be null");
		Preconditions.checkNotNull(jmsTemplate,"Template is null");
		jmsTemplate.send(destination, new RouterMessageCreator(message));
	}

	
	public void setJmsTemplate(JmsTemplate jmsTemplate) {
		this.jmsTemplate = jmsTemplate;
	}
	
	public void setQueueResolver(QueueResolver queueResolver) {
		this.queueResolver = queueResolver;
	}
	
    public void setRoutingErrorHandler(RoutingErrorHandler routingErrorHandler) {
        this.routingErrorHandler = routingErrorHandler;
    }
	/**
	 * Message creator
	 * @author mrogalski
	 *
	 */
	private class RouterMessageCreator implements MessageCreator {
		/**
		 * message to be sent
		 */
		private String messageContent;
		/**
		 * Constructor
		 * @param message
		 */
		public RouterMessageCreator(String messageContent) {
			this.messageContent = messageContent;
		}
		@Override
		public Message createMessage(Session session) throws JMSException {
			TextMessage message = session.createTextMessage();
			message.setText(messageContent);
			return message;
		}
		
	}
}
