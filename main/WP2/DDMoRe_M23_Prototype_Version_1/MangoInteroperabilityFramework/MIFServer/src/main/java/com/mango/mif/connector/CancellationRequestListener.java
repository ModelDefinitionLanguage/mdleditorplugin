/* File			:  CancellationRequestListener.java
 * Project		:  MangoInteroperabilityFramework
 * Created on	:  May 22, 2012
 */
package com.mango.mif.connector;

import javax.jms.JMSException;
import javax.jms.Message;
import javax.jms.MessageListener;
import javax.jms.TextMessage;

import org.apache.log4j.Logger;


/**
 * 
 * This component is responsible for delegating handling of a cancellation request to the connector
 * 
 * @version $Revision: $ as of $Date: $
 * <p>SVN Entry : $HeadURL: $
 * <p>SVN ID    : $Id: $
 * <p>Last edited by : $Author: $
 */
public class CancellationRequestListener implements MessageListener {
	/**
	 * Logger
	 */
	final static Logger LOG = Logger.getLogger(CancellationRequestListener.class);
	/**
	 * Connector to which the handling of cancellatin request will be delegated
	 */
	private Connector connector;
	
	@Override
	public void onMessage(Message message) {
		LOG.info(message);
		TextMessage msg = (TextMessage) message;
		String receivedText = null;
		try {
			receivedText = msg.getText();
		} catch (JMSException e) {
			LOG.error(e);
			throw new RuntimeException(ConnectorExceptions.INVALID_REQUEST_MESSAGE.getMessage());
		}
		LOG.debug("Cancellation for " + connector.getConnectorId() + " received the message " + receivedText );
		connector.handleCancelMessage(receivedText);
	}

	public Connector getConnector() {
		return connector;
	}

	public void setConnector(Connector connector) {
		this.connector = connector;
	}

}

