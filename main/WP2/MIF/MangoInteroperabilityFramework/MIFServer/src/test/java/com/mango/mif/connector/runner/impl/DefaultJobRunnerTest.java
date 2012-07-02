/* File			:  DefaultJobRunnerTest.java
 * Project		:  MIFServer
 * Created on	:  25 Jun 2012
 */
package com.mango.mif.connector.runner.impl;

import static org.junit.Assert.*;

import java.io.File;
import java.io.IOException;

import javax.xml.bind.JAXBException;

import org.apache.commons.io.FileUtils;
import org.apache.log4j.Logger;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.mockito.Mock;
import static org.mockito.Matchers.any;
import static org.mockito.Mockito.*;
import org.mockito.MockitoAnnotations;
import org.springframework.jms.core.JmsTemplate;
import org.springframework.jms.core.MessageCreator;

import com.mango.mif.connector.TestsHelper;
import com.mango.mif.connector.dispatcher.domain.DispatcherExceptions;
import com.mango.mif.connector.dispatcher.domain.Job;
import com.mango.mif.connector.runner.api.SCXMLDriver;
import com.mango.mif.domain.ExecutionResponse;
import com.mango.mif.utils.JAXBUtils;
import com.mango.mif.utils.NewExecutionRequestHelper;


/**
 * @version $Revision: $ as of $Date: $
 * <p>SVN Entry : $HeadURL: $
 * <p>SVN ID    : $Id: $
 * <p>Last edited by : $Author: $
 */
/**
 * @author mrogalski
 * Tests default job runner implementation
 */
public class DefaultJobRunnerTest {
	/**
	 * Logger
	 */
	private final static Logger LOG = Logger.getLogger(DefaultJobRunnerTest.class);
	/**
	 * jms template
	 */
	@Mock JmsTemplate jmsTemplate;
	/**
	 * driver
	 */
	@Mock SCXMLDriver driver;
	/**
	 * A job
	 */
	@Mock Job job;
	/**
	 * instance being tested
	 */
	private DefaultJobRunner jobRunner;
	/**
	 * Job results queue name used in tests.
	 */
	private final static String MOCK_JOB_RESULT_QUEUE_NAME = "MOCK.DESTINATION";
	/**
	 * temporary directory
	 */
	private File tmpDir;
	/**
	 * @throws java.lang.Exception
	 */
	@Before
	public void setUp() throws Exception {
		MockitoAnnotations.initMocks(this);
		tmpDir = TestsHelper.createTmpDirectory();
		jobRunner = new DefaultJobRunner() {
			
			@Override
			public void cancelProcessing() {
				driver.fireEvent(JobRunnerSates.CANCELLED.getTriggeringEvent());
			}
			
			@Override
			protected String getWorkingDirectory() {
				return tmpDir.getAbsolutePath();
			}
		};
		jobRunner.setDriver(driver);
		jobRunner.setJmsTemplate(jmsTemplate);
		jobRunner.setResultDestination(MOCK_JOB_RESULT_QUEUE_NAME);
		jobRunner.setJob(job);
	}

	/**
	 * @throws java.lang.Exception
	 */
	@After
	public void tearDown() throws Exception {
		try {
			FileUtils.deleteDirectory(tmpDir);
		} catch (IOException e) {
			LOG.error(e);
		}
	}

	@Test(expected = RuntimeException.class)
	public void shouldThrowExceptionWhenJobIsNotSet() {
		jobRunner.setJob(null);
		jobRunner.call();
	}
	
	@Test(expected = RuntimeException.class)
	public void shouldThrowExceptionWhenWorkspaceManagerIsNotSet() {
		jobRunner.setWorkspaceManager(null);
		jobRunner.call();
	}
	
	@Test(expected = RuntimeException.class)
	public void shouldThrowExceptionWhenDriverIsNotSet() {
		jobRunner.setDriver(null);
		jobRunner.call();
	}
	
	@Test(expected = RuntimeException.class)
	public void shouldThrowExceptionWhenResultDestinationIsNotSet() {
		jobRunner.setResultDestination(null);
		jobRunner.call();
	}
	
	@Test
	public void shouldPublishResultMessageOnJobResultQueue() {
		jobRunner.publishResultsMessage();
		verify(jmsTemplate).send(eq(MOCK_JOB_RESULT_QUEUE_NAME), (MessageCreator) any());
	}

	@Test
	public void shouldPrepareCancelResultMsgIfJobHasBeenCancelled() throws JAXBException {
		when(driver.getProcessingStatus()).thenReturn(DriverProcessingStatus.CANCELLED);
		when(job.getExecutionRequest()).thenReturn(NewExecutionRequestHelper.createMockExecutionRequestBuilder().getExecutionRequest());
		
		String resultMsg = jobRunner.prepareResponse();

		ExecutionResponse response = unmarshallExecutionResponse(resultMsg);
		
		assertNotNull(response);
		assertEquals(DriverProcessingStatus.CANCELLED.name(),response.getStatus());
	}

	@Test
	public void shouldPrepareFailureResultMsgIfJobHasFailed() throws JAXBException {
		when(driver.getProcessingStatus()).thenReturn(DriverProcessingStatus.FAILED);
		when(job.getExecutionRequest()).thenReturn(NewExecutionRequestHelper.createMockExecutionRequestBuilder().getExecutionRequest());
		
		String resultMsg = jobRunner.prepareResponse();

		ExecutionResponse response = unmarshallExecutionResponse(resultMsg);
		
		assertNotNull(response);
		assertEquals(DriverProcessingStatus.FAILED.name(),response.getStatus());

	}

	@Test
	public void shouldPrepareResultMsgIfJobHasBeenCompleted() throws JAXBException {
		when(driver.getProcessingStatus()).thenReturn(DriverProcessingStatus.FINISHED);
		when(job.getExecutionRequest()).thenReturn(NewExecutionRequestHelper.createMockExecutionRequestBuilder().getExecutionRequest());
		
		String resultMsg = jobRunner.prepareResponse();

		ExecutionResponse response = unmarshallExecutionResponse(resultMsg);
		assertNotNull(response);
		assertEquals(DriverProcessingStatus.FINISHED.name(),response.getStatus());
	}
	
	@Test
	public void shouldIncludeAnyErrorsThatWereThrownDuringExecutionInTheResultElement() throws JAXBException {
		when(driver.getProcessingStatus()).thenReturn(DriverProcessingStatus.FAILED);
		when(job.getExecutionRequest()).thenReturn(NewExecutionRequestHelper.createMockExecutionRequestBuilder().getExecutionRequest());
		StateHandlerException e = mock(StateHandlerException.class);
		when(e.getMessage()).thenReturn("Job execution exception");
		when(driver.getException()).thenReturn(e);
		String resultMsg = jobRunner.prepareResponse();

		ExecutionResponse response = unmarshallExecutionResponse(resultMsg);
		assertNotNull(response);
		assertEquals(DriverProcessingStatus.FAILED.name(),response.getStatus());
		assertNotNull(response.getResult());
	}
	
	@Test
	public void shouldReturnAMessageFromExecutionException() {
		String clientErrorMessage = "This is the real cause to be displayed to the user";
		Exception e = new ExecutionException(clientErrorMessage);
		e = new Exception("Any interim exception",e);
		e = new StateHandlerException("State Handler Exception message", e);
		
		assertEquals("State Handler Exception message"+
					"\n" +
					"Any interim exception"+
					"\n" +
					"This is the real cause to be displayed to the user"+
					"\n"
					,jobRunner.getErrorMessage(e));
	}
	
	@Test
	public void shouldReturnMostParentExceptionMessageIfExecutionExceptionNotFound() {
		String clientErrorMessage = "State Handler Exception message.";
		Exception e = new Exception("Random Exception");
		e = new Exception("Any interim exception",e);
		e = new StateHandlerException(clientErrorMessage, e);
		assertEquals(clientErrorMessage,jobRunner.getErrorMessage(e));
	}
	/**
	 * unmarshalls response message 
	 * @param jobRunnerResponse
	 * @return
	 */
	private ExecutionResponse unmarshallExecutionResponse(String jobRunnerResponse) {
		ExecutionResponse response = null;
		try {
			response = JAXBUtils.unmarshall(ExecutionResponse.class , jobRunnerResponse);
		} catch (JAXBException e) {
			LOG.error("JAXB Exception for the string " + jobRunnerResponse);
			throw new RuntimeException(DispatcherExceptions.INVALID_RESPONSE_MESSAGE_FORMAT.getMessage());
		}
		return response;
	}
	
	
}
