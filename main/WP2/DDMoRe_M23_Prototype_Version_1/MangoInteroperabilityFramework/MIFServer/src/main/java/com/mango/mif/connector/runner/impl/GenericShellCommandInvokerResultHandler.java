/* File			:  GenericShellCommandInvokerResultHandler.java
 * Project		:  MIFServer
 * Created on	:  2 Jul 2012
 */
package com.mango.mif.connector.runner.impl;

import com.google.common.base.Preconditions;
import com.mango.mif.connector.domain.Job;
import com.mango.mif.core.exec.ExecutionException;
import com.mango.mif.core.exec.InvokerResult;


/**
 * @version $Revision: $ as of $Date: $
 * <p>SVN Entry : $HeadURL: $
 * <p>SVN ID    : $Id: $
 * <p>Last edited by : $Author: $
 */
/**
 * @author mrogalski
 * 
 * Base implementation of invoker result parser common methods
 *
 */
public class GenericShellCommandInvokerResultHandler extends ShellCommandInvokerResultHandler implements JobProcessingAwareInvokerResultHandler {
	/**
	 * A job
	 */
	protected Job job;
	/**
	 * state exit event
	 */
	protected String stateExitEvent;

	/**
	 * Constructor
	 * @param exitEvent
	 */
	public GenericShellCommandInvokerResultHandler(String stateExitEvent) {
		this.stateExitEvent = stateExitEvent;
	}
	
	@Override
	public void setJob(Job job) {
		this.job = job;
	}

	@Override
	public void handle(InvokerResult invokerResult) throws ExecutionException {
        Preconditions.checkNotNull(job);
		super.handle(invokerResult);
	}
	
	@Override
	public String getStateExitEvent() {
		return stateExitEvent;
	}

}
