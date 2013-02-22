/* File			:  TaskExecutionManager.java
 * Project		:  MangoInteroperabilityFramework
 * Created on	:  Mar 27, 2012
 */
package com.mango.mif.client.api;

import java.util.UUID;

import com.mango.mif.domain.ExecutionRequest;
import com.mango.mif.exception.MIFException;

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
	void submit(String string) throws MIFException;
	
	/**
	 * Responsible for submitting the Request to the the execution Environment.
	 *
	 * @param request the request
	 */
	void submit(ExecutionRequest request) throws MIFException;

	/**
	 * Responsible for handling cancellation of the Request to the the execution Environment.
	 *
	 * @deprecated use cancelRequest(String requestId) instead
	 *
	 * @param softwareName the software name
	 * @param requestUid the request uid.
	 * @return the string
	 * 
	 */
	@Deprecated
	void cancelRequest(UUID requestUid) throws MIFException;

    /**
     * Responsible for handling cancellation of the Request to the the execution Environment.
     *
     * @param softwareName the software name
     * @param requestUid the request uid.
     * @return the string
     * 
     */
    void cancelRequest(String requestUid) throws MIFException;
    
	/**
	 * Handles result message from the Result Listener.
	 *
	 * @param responseReceived the response received
	 * @return the string
	 */
	String handleResultMessage(String responseReceived) throws MIFException;
	
	/**
	 * Sets Execution message handler.
	 *
	 * @param executionMessageHandler the new execution message handler
	 */
	void setExecutionMessageHandler(TaskExecutionMessageHandler executionMessageHandler);

	void acknowledgeResponse(String requestUid) throws MIFException;

}