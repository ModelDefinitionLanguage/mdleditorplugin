/* File			:  GenericShellCommandInvokerResultHandlerTest.java
 * Project		:  MIFServer
 * Created on	:  24 Jul 2012
 */
package com.mango.mif.connector.runner.impl;

import org.junit.Before;
import org.junit.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import static org.mockito.Mockito.*;

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
 * Tests GenericShellCommandInvokerResultHandler
 */
public class GenericShellCommandInvokerResultHandlerTest {
	/**
	 * Submit result handler
	 */
	private GenericShellCommandInvokerResultHandler resultHandler;
	/**
	 * job
	 */
	@Mock Job job;
	/**
	 * job
	 */
	@Mock InvokerResult invokerResult;
	/**
	 * @throws java.lang.Exception
	 */
	@Before
	public void setUp() throws Exception {
		MockitoAnnotations.initMocks(this);
		resultHandler = new GenericShellCommandInvokerResultHandler("TEST.EVENT");
		resultHandler.setJob(job);
	}

	@Test(expected=NullPointerException.class)
	public void shouldThrowExceptionIfJobIsNotSet() throws ExecutionException {
		resultHandler.setJob(null);
		resultHandler.handle(invokerResult);
	}

	@Test(expected=NullPointerException.class)
	public void shouldThrowExceptionIfInvokerResultHandlerIsNotSet() throws ExecutionException {
		resultHandler.handle(null);
	}

	@Test(expected=ExecutionException.class)
	public void shouldThrowExceptionIfInvokerResultRepresentsFailedExecution() throws ExecutionException {
		when(invokerResult.getExitCode()).thenReturn(1); //error exit code
		resultHandler.handle(invokerResult);
	}
}
