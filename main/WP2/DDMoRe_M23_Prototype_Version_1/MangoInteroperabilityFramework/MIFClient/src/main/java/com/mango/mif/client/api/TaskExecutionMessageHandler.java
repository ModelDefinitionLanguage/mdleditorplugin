/* File			:  TaskExecutionMessageHandler.java
 * Project		:  MangoInteroperabilityFramework-trunk
 * Created on	:  17 Apr 2012
 */
package com.mango.mif.client.api;

import com.mango.mif.domain.ExecutionResponse;

/**
 * 
 * Interface that should be implemented by the MIF-client components 
 * 
 * @version $Revision: $ as of $Date: $
 * <p>SVN Entry : $HeadURL: $
 * <p>SVN ID    : $Id: $
 * <p>Last edited by : $Author: $
 */
public interface TaskExecutionMessageHandler {
    /**
     * executed when execution response is consumed
     * @param executionResponse
     */
    public void handle(ExecutionResponse executionResponse);
}
