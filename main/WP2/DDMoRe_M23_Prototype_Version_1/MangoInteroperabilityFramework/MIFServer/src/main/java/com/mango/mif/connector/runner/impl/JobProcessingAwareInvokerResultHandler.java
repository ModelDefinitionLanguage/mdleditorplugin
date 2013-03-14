/* File			:  JobProcessingAwareInvokerResultHandler.java
 * Project		:  MIFServer
 * Created on	:  3 Jul 2012
 */
package com.mango.mif.connector.runner.impl;

import com.mango.mif.connector.domain.Job;
import com.mango.mif.core.exec.InvokerResultHandler;

/**
 * @version $Revision: $ as of $Date: $
 * <p>SVN Entry : $HeadURL: $
 * <p>SVN ID    : $Id: $
 * <p>Last edited by : $Author: $
 */
/**
 * @author mrogalski
 * Invoker result handler used by the state handlers
 */
public interface JobProcessingAwareInvokerResultHandler extends InvokerResultHandler {
	/**
	 * Job
	 * @param job
	 */
	void setJob(Job job);
	/**
	 * 
	 * @return an event that will be passed to SCXMLDriver to trigger a transition to appropriate state
	 */
	String getStateExitEvent();

}
