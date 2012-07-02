/* File			:  TaskExecutionResultsListener.java
 * Project		:  MangoInteroperabilityFramework
 * Created on	:  Mar 28, 2012
 */
package com.mango.mif.client.impl;

import javax.jms.JMSException;
import javax.jms.Message;
import javax.jms.MessageListener;
import javax.jms.TextMessage;

import org.apache.log4j.Logger;

import com.mango.mif.client.api.TaskExecutionManager;
import com.mango.mif.client.err.TaskExecutorExceptions;
import com.mango.mif.exception.MIFException;

/**
 * Class registered as JMS Listener to handle the response from MIF. 
 * @version $Revision: $ as of $Date: $
 * <p>SVN Entry : $HeadURL: $
 * <p>SVN ID    : $Id: $
 * <p>Last edited by : $Author: $
 */
public class TaskExecutionResultsListener implements MessageListener{
	static final Logger LOG = Logger.getLogger(TaskExecutionResultsListener.class);
	
	/** Task Manager Note this is the same instance that is wired through Spring*/
	TaskExecutionManager taskExecutionManager;

	/**
	 * Consumes the message of the queue and delegates the message to TaskExecutionManager.
	 *
	 * @param message the message
	 */
	@Override
	public synchronized void onMessage(Message message) {
		TextMessage msg = (TextMessage) message;
		String receivedText = null;
		try {
			receivedText = msg.getText();
			LOG.info("Received and delegating the message to TaskExecution Manager " + receivedText );
			taskExecutionManager.handleResultMessage(receivedText);
		} catch (MIFException e) {
			throw new RuntimeException(TaskExecutorExceptions.INVALID_RESPONSE_MESSAGE.getMessage());
		} catch (JMSException e) {
			LOG.error(e);
			throw new RuntimeException(TaskExecutorExceptions.INVALID_RESPONSE_MESSAGE.getMessage());
		}
	}

	/**
	 * Gets the task execution manager.
	 *
	 * @return the task execution manager
	 */
	public TaskExecutionManager getTaskExecutionManager() {
		return taskExecutionManager;
	}

	/**
	 * Sets the task execution manager.
	 *
	 * @param taskExecutionManager the new task execution manager
	 */
	public void setTaskExecutionManager(TaskExecutionManager taskExecutionManager) {
		this.taskExecutionManager = taskExecutionManager;
	}

}
