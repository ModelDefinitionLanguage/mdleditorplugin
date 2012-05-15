/* File			:  TaskExecutionManager.java
 * Project		:  MangoInteroperabilityFramework
 * Created on	:  Mar 27, 2012
 */
package com.mango.mif.client.api;

import com.mango.mif.domain.ExecutionRequest;

/**
 * This interface would be responsible for defining the components managing the requests and responses that are submitted/received to the MIF. 
 *
 * @version $Revision: $ as of $Date: $
 * <p>SVN Entry : $HeadURL: $
 * <p>SVN ID    : $Id: $
 * <p>Last edited by : $Author: $
 */
public interface TaskExecutionManager {

	/**
	 * Responsible for submitting the Request to the the execution Environment. 
	 * @param string : Execution request in String.
	 * 
	 */
	void submit(String string);
	
	/**
	 * Responsible for submitting the Request to the the execution Environment. 
	 * @param string : Execution request in String.
	 */
	void submit(ExecutionRequest request);

	/**
	 * Handles result message from the Result Listener.
	 *
	 * @param responseReceived the response received
	 * @return the string
	 */
	String handleResultMessage(String responseReceived);
	/**
	 * Sets Execution message handler
	 * @param executionMessageHandler
	 */
	void setExecutionMessageHandler(TaskExecutionMessageHandler executionMessageHandler);

}