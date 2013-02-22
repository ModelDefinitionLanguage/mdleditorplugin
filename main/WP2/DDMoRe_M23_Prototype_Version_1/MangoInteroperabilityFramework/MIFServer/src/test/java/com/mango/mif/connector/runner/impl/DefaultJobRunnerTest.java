/* File			:  DefaultJobRunnerTest.java
 * Project		:  MIFServer
 * Created on	:  25 Jun 2012
 */
package com.mango.mif.connector.runner.impl;

import static org.junit.Assert.*;

import java.io.File;
import java.io.IOException;
import java.util.Properties;

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

import com.mango.mif.connector.ConnectorExceptions;
import com.mango.mif.connector.TestsHelper;
import com.mango.mif.connector.domain.Job;
import com.mango.mif.connector.runner.api.SCXMLDriver;
import com.mango.mif.core.api.WorkspaceManager;
import com.mango.mif.core.exec.ExecutionException;
import com.mango.mif.domain.DetailedStatus;
import com.mango.mif.domain.ExecutionResponse;
import com.mango.mif.domain.JobStatus;
import com.mango.mif.utils.JAXBUtils;
import com.mango.mif.utils.MIFProperties;


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
	 * A job
	 */
	@Mock WorkspaceManager workspaceManager;
	/**
	 * detailed status
	 */
	@Mock DetailedStatus detailedStatus;
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
        Properties properties = new Properties();
        properties.load(DefaultJobRunnerTest.class.getResourceAsStream("/com/mango/mif/sgeconnector/connector.properties"));
        MIFProperties.loadProperties(properties);
		tmpDir = TestsHelper.createTmpDirectory();
		jobRunner = new DefaultJobRunner() {
			
			@Override
			public void cancelProcessing() {
				driver.fireEvent(JobRunnerState.CANCELLED.getTriggeringEvent());
			}
			
		};
		jobRunner.setDriver(driver);
		jobRunner.setJmsTemplate(jmsTemplate);
		jobRunner.setResultDestination(MOCK_JOB_RESULT_QUEUE_NAME);
		jobRunner.setJobId("JOB_ID");
		when(job.getDetailedStatus()).thenReturn(detailedStatus);
		when(detailedStatus.getSummary()).thenReturn("SUMMARY_MESSAGE");
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

	@Test(expected = NullPointerException.class)
	public void shouldThrowExceptionWhenJobIsNotSet() throws InterruptedException {
		jobRunner.setJobId(null);
		jobRunner.main();
	}
	
	@Test(expected = NullPointerException.class)
	public void shouldThrowExceptionWhenDriverIsNotSet() throws InterruptedException {
		jobRunner.setDriver(null);
		jobRunner.main();
	}
	
	@Test(expected = NullPointerException.class)
	public void shouldThrowExceptionWhenResultDestinationIsNotSet() throws InterruptedException {
		jobRunner.setResultDestination(null);
		jobRunner.main();
	}
	
	@Test
	public void shouldPublishResultMessageOnJobResultQueue() {
		jobRunner.publishResultsMessage();
		verify(jmsTemplate).send(eq(MOCK_JOB_RESULT_QUEUE_NAME), (MessageCreator) any());
	}

	@Test
	public void shouldPrepareCancelResultMsgIfJobHasBeenCancelled() throws JAXBException {
		when(driver.getProcessingStatus()).thenReturn(DriverProcessingStatus.CANCELLED);
		
		String resultMsg = jobRunner.prepareResponse();

		ExecutionResponse response = unmarshallExecutionResponse(resultMsg);
		
		assertNotNull(response);
		assertEquals(JobStatus.CANCELLED.name(),response.getStatus());
	}

	@Test
	public void shouldPrepareFailureResultMsgIfJobHasFailed() throws JAXBException {
		when(driver.getProcessingStatus()).thenReturn(DriverProcessingStatus.FAILED);
		
		String resultMsg = jobRunner.prepareResponse();

		ExecutionResponse response = unmarshallExecutionResponse(resultMsg);
		
		assertNotNull(response);
		assertEquals(JobStatus.FAILED.name(),response.getStatus());

	}

	@Test
	public void shouldPrepareResultMsgIfJobHasBeenCompleted() throws JAXBException {
		when(driver.getProcessingStatus()).thenReturn(DriverProcessingStatus.FINISHED);
		
		String resultMsg = jobRunner.prepareResponse();

		ExecutionResponse response = unmarshallExecutionResponse(resultMsg);
		assertNotNull(response);
		assertEquals(JobStatus.COMPLETED.name(),response.getStatus());
	}
	
	@Test
	public void shouldIncludeAnyErrorsThatWereThrownDuringExecutionInTheResultElement() throws JAXBException {
		when(driver.getProcessingStatus()).thenReturn(DriverProcessingStatus.FAILED);
		when(driver.getException()).thenReturn(buildExceptionTree());
		String resultMsg = jobRunner.prepareResponse();

		ExecutionResponse response = unmarshallExecutionResponse(resultMsg);
		assertNotNull(response);
		assertEquals(JobStatus.FAILED.name(),response.getStatus());
		assertNotNull(response.getResult());
	}
	
	@Test
	public void shouldReturnAMessageFromExecutionException() {
		Exception e = buildExceptionTree();
		
		assertEquals("State Handler Exception message"+
					"\n" +
					"Any interim exception"+
					"\n" +
					"This is the real cause to be displayed to the user"+
					"\n"
					,jobRunner.prepareResultMessage(e));
	}
	
	
	@Test
	public void shouldReturnADetailedMessageFromExecutionException() {
		Exception e = buildExceptionTree();
		assertNotNull(jobRunner.prepareDetailedResultMessage(e));
	}
	/**
	 * Builds example exception tree
	 * @return
	 */
	private Exception buildExceptionTree() {
		String clientErrorMessage = "This is the real cause to be displayed to the user";
		Exception e = new Exception("A cause");
		e = new ExecutionException(clientErrorMessage,e);
		e = new Exception("Any interim exception",e);
		e = new StateHandlerException("State Handler Exception message", e);
		return e;
	}
	@Test
	public void shouldReturnMostParentExceptionMessageIfExecutionExceptionNotFound() {
		String clientErrorMessage = "State Handler Exception message.";
		Exception e = new Exception("Random Exception");
		e = new Exception("Any interim exception",e);
		e = new StateHandlerException(clientErrorMessage, e);
		assertEquals(clientErrorMessage,jobRunner.prepareResultMessage(e));
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
			throw new RuntimeException(ConnectorExceptions.INVALID_RESPONSE_MESSAGE_FORMAT.getMessage());
		}
		return response;
	}
	
	
}
