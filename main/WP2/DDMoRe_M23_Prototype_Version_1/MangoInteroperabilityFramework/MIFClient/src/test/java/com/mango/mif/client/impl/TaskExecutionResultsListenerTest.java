/* File			:  TaskExecutionResultsListenerTest.java
 * Project		:  MangoInteroperabilityFramework
 * Created on	:  Mar 28, 2012
 */
package com.mango.mif.client.impl;

import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import javax.jms.JMSException;
import javax.jms.TextMessage;

import org.apache.log4j.Logger;
import org.junit.Before;
import org.junit.Test;

import com.mango.mif.client.api.TaskExecutionManager;
import com.mango.mif.exception.MIFException;

/**
 * Test for {@link TaskExecutionResultsListener}.
 * @version $Revision: $ as of $Date: $
 * <p>SVN Entry : $HeadURL: $
 * <p>SVN ID    : $Id: $
 * <p>Last edited by : $Author: $
 */
public class TaskExecutionResultsListenerTest {
	static final Logger LOG = Logger.getLogger(TaskExecutionResultsListenerTest.class);
	
	TaskExecutionResultsListener  taskExecutionResultsListener = null;
	TaskExecutionManager taskExecutionManager = null;
	TextMessage message = null;
	String responseReceived =  "Some sample text";
	 
	@Before
	public void before() throws JMSException {
		taskExecutionManager = mock(TaskExecutionManager.class);
		message = mock(TextMessage.class);
		when(message.getText()).thenReturn(responseReceived);
		
		taskExecutionResultsListener = new TaskExecutionResultsListener();
		taskExecutionResultsListener.setTaskExecutionManager(taskExecutionManager);
		
	}
	
	@Test
	public void shouldDelegateTheRequestToTheTaskExecutionManger () throws MIFException {
		taskExecutionResultsListener.onMessage(message);
		verify(taskExecutionManager).handleResultMessage(responseReceived);
		
	}
	
}
