package com.mango.mif.utils;
import javax.jms.JMSException;
import javax.jms.Message;
import javax.jms.Session;
import javax.jms.TextMessage;

import org.apache.log4j.Logger;
import org.springframework.jms.core.JmsTemplate;
import org.springframework.jms.core.MessageCreator;

/**
 * Replicating navigator component Request. 
 */
public class RequestSender implements Runnable {
	final static Logger LOG = Logger.getLogger(RequestSender.class);
	
	JmsTemplate jmsTemplate;
	
	/**JMS Destination for the Execution Request Queue	 */
	private final String destination;

	private final String buildMessage;
	

	public RequestSender(String destination, String buildMessage, JmsTemplate jmsTemplate2) {
		this.jmsTemplate = jmsTemplate2;
		this.buildMessage = buildMessage;
		this.destination = destination;
	}

	/* Submits the request to MIF execution environment.
	 * @see java.lang.Runnable#run()
	 */
	public void run() {
		LOG.info("Navigator MOCK Client : Sending messages ");
		jmsTemplate.send(destination, new MessageCreator() {
			public Message createMessage(Session session) throws JMSException {
				TextMessage message = session.createTextMessage();
				message.setText(buildMessage);
				return message;
			}
		});
	}

}
