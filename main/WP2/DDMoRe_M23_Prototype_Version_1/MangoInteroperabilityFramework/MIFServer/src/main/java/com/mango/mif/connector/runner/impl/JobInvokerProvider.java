/* File			:  JobInvokerProvider.java
 * Project		:  MIFServer
 * Created on	:  4 Sep 2012
 */
package com.mango.mif.connector.runner.impl;

import com.mango.mif.connector.domain.Job;
import com.mango.mif.core.exec.ExecutionException;
import com.mango.mif.core.exec.Invoker;

/**
 * @version $Revision: $ as of $Date: $
 * <p>SVN Entry : $HeadURL: $
 * <p>SVN ID    : $Id: $
 * <p>Last edited by : $Author: $
 */
public interface JobInvokerProvider {

	/**
	 * Creates an invoker depending on type of execution
	 * @param job
	 * @return
	 * @throws ExecutionException
	 */
	Invoker createInvoker(Job job) throws ExecutionException;

}