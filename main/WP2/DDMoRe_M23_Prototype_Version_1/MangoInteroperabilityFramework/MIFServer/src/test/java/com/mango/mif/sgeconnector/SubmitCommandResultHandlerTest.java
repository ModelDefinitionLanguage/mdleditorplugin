/* File			:  SubmitResultHandlerTest.java
 * Project		:  MIFServer
 * Created on	:  2 Jul 2012
 */
package com.mango.mif.sgeconnector;

import org.junit.Before;
import org.junit.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import static org.mockito.Mockito.*;

import com.mango.mif.connector.domain.Job;
import com.mango.mif.core.exec.ExecutionException;
import com.mango.mif.core.exec.InvokerResult;
import com.mango.mif.sge.SGEOutput;
import com.mango.mif.sge.SGEOutputParser;


/**
 * @version $Revision: $ as of $Date: $
 * <p>SVN Entry : $HeadURL: $
 * <p>SVN ID    : $Id: $
 * <p>Last edited by : $Author: $
 */
/**
 * @author mrogalski
 * Submit result handler test
 */
public class SubmitCommandResultHandlerTest {
	/**
	 * Submit result handler
	 */
	private SGECommandResultHandler submitResultHandler;
	/**
	 * Output parser
	 */
	@Mock SGEOutputParser outputParser;
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
		submitResultHandler = new SubmitCommandResultHandler("TEST.EVENT");
		submitResultHandler.setOutputParser(outputParser);
		submitResultHandler.setJob(job);
	}

	@Test(expected=NullPointerException.class)
	public void shouldThrowExceptionIfOutputParserNotSet() throws ExecutionException {
		submitResultHandler.setOutputParser(null);
		submitResultHandler.handle(invokerResult);
	}

	@Test(expected=ExecutionException.class)
	public void shouldThrowExceptionIfGridJobIdNotFoundInOutput() throws ExecutionException {
		when(invokerResult.getExitCode()).thenReturn(0); //exit code
		SGEOutput output = mock(SGEOutput.class);
		when(output.get(SGEOutput.JOB_ID_KEY)).thenReturn(null);
		when(outputParser.parseSubmitMessage((String)any())).thenReturn(output);
		submitResultHandler.handle(invokerResult);
	}

	@Test(expected=ExecutionException.class)
	public void shouldThrowExceptionIfGridJobIdIsEmpty() throws ExecutionException {
		when(invokerResult.getExitCode()).thenReturn(0); //exit code
		SGEOutput output = mock(SGEOutput.class);
		when(output.get(SGEOutput.JOB_ID_KEY)).thenReturn("");
		when(outputParser.parseSubmitMessage((String)any())).thenReturn(output);
		submitResultHandler.handle(invokerResult);
	}
	
	@Test
	public void shouldHandleResult() throws ExecutionException {
		when(invokerResult.getExitCode()).thenReturn(0); //exit code
		SGEOutput output = mock(SGEOutput.class);
		when(output.get(SGEOutput.JOB_ID_KEY)).thenReturn("1234");
		when(outputParser.parseSubmitMessage((String)any())).thenReturn(output);
		submitResultHandler.handle(invokerResult);
	}
}
