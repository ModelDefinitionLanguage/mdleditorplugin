/* File			:  RJobRunnerResultListner.java
 * Project		:  MangoInteroperabilityFramework
 * Created on	:  Mar 16, 2012
 */
package com.mango.mif.connector.dispatcher.impl;

import javax.jms.JMSException;
import javax.jms.Message;
import javax.jms.MessageListener;
import javax.jms.TextMessage;

import org.apache.log4j.Logger;

import com.mango.mif.connector.dispatcher.domain.DispatcherExceptions;
import com.mango.mif.connector.dispatcher.impl.Dispatcher;

/**
 * @version $Revision: $ as of $Date: $
 * <p>SVN Entry : $HeadURL: $
 * <p>SVN ID    : $Id: $
 * <p>Last edited by : $Author: $
 */
public class JobRunnerResultListener implements MessageListener {
	final static Logger LOG = Logger.getLogger(JobRunnerResultListener.class);
	
	Dispatcher dispatcher;
	
	@Override
	public void onMessage(Message message) {
		TextMessage msg = (TextMessage) message;
		String receivedText = null;
		try {
			receivedText = msg.getText();
		} catch (JMSException e) {
			LOG.error(e);
			throw new RuntimeException(DispatcherExceptions.INVALID_REQUEST_MESSAGE.getMessage());
		}
		LOG.info("Received the message " + receivedText );
		dispatcher.handleResultMessage(receivedText);
	}

	public Dispatcher getDispatcher() {
		return dispatcher;
	}

	public void setDispatcher(Dispatcher dispatcher) {
		this.dispatcher = dispatcher;
	}



}

