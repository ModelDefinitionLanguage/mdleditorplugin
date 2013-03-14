/* File			:  BaseRoutingErrorHandler.java
 * Project		:  MIFServer
 * Created on	:  Jul 25, 2012
 */
package com.mango.mif.core.impl;

import javax.jms.JMSException;
import javax.jms.Message;
import javax.jms.Session;
import javax.jms.TextMessage;

import org.apache.log4j.Logger;
import org.springframework.jms.core.JmsTemplate;
import org.springframework.jms.core.MessageCreator;

import com.google.common.base.Preconditions;

/**
 * @version $Revision: $ as of $Date: $
 * <p>SVN Entry : $HeadURL: $
 * <p>SVN ID    : $Id: $
 * <p>Last edited by : $Author: $
 * 
 * Basic implementation of routing error handler
 */
public class BaseRoutingErrorHandler implements RoutingErrorHandler {

    /**
     * Logger 
     */
    protected static final Logger LOG = Logger.getLogger(CancellationRequestRoutingErrorHandler.class);
    /** 
     * The jms template. 
     */
    protected JmsTemplate jmsTemplate;
    /**
     * Response queue
     */
    protected String responseQueue;

    public BaseRoutingErrorHandler(String responseQueue, JmsTemplate jmsTemplate) {
        Preconditions.checkNotNull(jmsTemplate, "JMS template not set.");
        Preconditions.checkNotNull(responseQueue, "Response queue not set.");
        this.jmsTemplate = jmsTemplate;
        this.responseQueue = responseQueue;
    }

    public void setJmsTemplate(JmsTemplate jmsTemplate) {
        this.jmsTemplate = jmsTemplate;
    }

    public void setResponseQueue(String responseQueue) {
        this.responseQueue = responseQueue;
    }
    @Override
    public void destinationNotResolved(String messageText) {
        Preconditions.checkNotNull(jmsTemplate, "JMS template not set.");
        Preconditions.checkNotNull(responseQueue, "Response queue not set.");
        Preconditions.checkNotNull(messageText, "Message is null set.");
    }
    /**
     * sends a message to the given destination queue
     */
    protected void sendMessage(final String messageText) {
        jmsTemplate.send(responseQueue, new MessageCreator() {
            @Override
            public Message createMessage(Session session) throws JMSException {
                TextMessage message = session.createTextMessage();
                message.setText(messageText);
                return message;
            }
        });
    }
}