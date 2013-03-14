/* File			:  RJobRunnerResultListner.java
 * Project		:  MangoInteroperabilityFramework
 * Created on	:  Mar 16, 2012
 */
package com.mango.mif.connector;

import javax.jms.JMSException;
import javax.jms.Message;
import javax.jms.MessageListener;
import javax.jms.TextMessage;

import org.apache.log4j.Logger;

import com.mango.mif.connector.Connector;

/**
 * This class is responsible for delegating handling of the result messages from Job Runner to connector 
 * 
 * @version $Revision: $ as of $Date: $
 * <p>SVN Entry : $HeadURL: $
 * <p>SVN ID    : $Id: $
 * <p>Last edited by : $Author: $
 */
public class JobRunnerResultListener implements MessageListener {
	/**
	 * Logger
	 */
	final static Logger LOG = Logger.getLogger(JobRunnerResultListener.class);
	/**
	 * Connector to which handling of the result will be delegated
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
		LOG.info(String.format("*** Execution Metrics : {ResultHandling.1} Received and delegating the message [%s] to Connector", 
        		receivedText));
		connector.handleResultMessage(receivedText);
	}

	public Connector getConnector() {
		return connector;
	}

	public void setConnector(Connector connector) {
		this.connector = connector;
	}



}

