/* File			:  ConnectorTest.java
 * Project		:  MIFServer
 * Created on	:  20 Jun 2012
 */
package com.mango.mif.connector;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.mockito.Matchers.any;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.spy;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;
import java.util.concurrent.ConcurrentMap;

import javax.jms.JMSException;
import javax.jms.TextMessage;
import javax.xml.bind.JAXBException;

import org.apache.commons.io.FileUtils;
import org.apache.log4j.Logger;
import org.junit.After;
import org.junit.Before;
import org.junit.Ignore;
import org.junit.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.core.task.AsyncTaskExecutor;
import org.springframework.jms.core.JmsTemplate;
import org.springframework.jms.core.MessageCreator;

import com.mango.mif.connector.domain.Job;
import com.mango.mif.connector.runner.api.JobRunner;
import com.mango.mif.connector.runner.api.JobRunnerFactory;
import com.mango.mif.connector.runner.impl.DriverProcessingStatus;
import com.mango.mif.domain.ExecutionRequestBuilder;
import com.mango.mif.domain.ExecutionResponseBuilder;
import com.mango.mif.domain.ExecutionType;
import com.mango.mif.domain.JobStatus;
import com.mango.mif.exception.MIFException;
import com.mango.mif.managers.JobManagementService;


/**
 * 
 * Tests Dispatcher class
 * 
 * @version $Revision: $ as of $Date: $
 * <p>SVN Entry : $HeadURL: $
 * <p>SVN ID    : $Id: $
 * <p>Last edited by : $Author: $
 */
/**
 * @author mrogalski
 *
 */
public class ConnectorTest {
	/**
	 * Logger
	 */
	private final static Logger LOG = Logger.getLogger(ConnectorTest.class);
	/**
	 * Mock job repository
	 */
	@Mock JobManagementService jobManagementService;
	/**
	 * Mock job repository
	 */
	@Mock JobRunnerFactory jobRunnerFactory;
	/**
	 * Mock job repository
	 */
	@Mock AsyncTaskExecutor taskExecutor;
	/**
	 * jms template
	 */
	@Mock JmsTemplate jmsTemplate;
	/**
	 * mock job runner registry
	 */
	@Mock ConcurrentMap<String, JobRunner> jobRunnerRegistry;
	/**
	 * Request ID
	 */
	private String requestUUID;
	/**
	 * dispatcher instance under test
	 */
	private Connector dispatcher;
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
		requestUUID = UUID.randomUUID().toString();
		dispatcher = new Connector();
		dispatcher.setJobManagementService(jobManagementService);
		dispatcher.setJobRunnerFactory(jobRunnerFactory);
		dispatcher.setTaskExecutor(taskExecutor);
		dispatcher.setJmsTemplate(jmsTemplate);
		tmpDir = TestsHelper.createTmpDirectory();
		dispatcher.setCancellationQueue("CANCELLATION-QUEUE");
		dispatcher.setRequestQueue("REQUEST-QUEUE");
		dispatcher.setConnectorId("MY_CONNECTOR_ID");
		Job job = new Job();
		when(jobManagementService.createNewJob()).thenReturn(job);
        when(jobManagementService.saveJob(job)).thenReturn(job);
	}
	@After
	public void tearDown() {
		try {
			FileUtils.deleteDirectory(tmpDir);
		} catch (IOException e) {
			LOG.error(e);
		}
	}
	
	@Test(expected=NullPointerException.class)
	public void shouldThrowExceptionIfMessageIsNull() {
		dispatcher.onMessage(null);
	}

	@SuppressWarnings("unchecked")
	@Test(expected=RuntimeException.class)
	public void shouldThrowExceptionIfMessageHasInvalidContent() throws JMSException {
		TextMessage message = mock(TextMessage.class);
		when(message.getText()).thenThrow(JMSException.class);
		dispatcher.onMessage(message);
	}

	@Test(expected=IllegalArgumentException.class)
	public void shouldThrowExceptionIfMessageHasNoContent() throws JMSException {
		TextMessage message = mock(TextMessage.class);
		when(message.getText()).thenReturn("");
		dispatcher.onMessage(message);
	}
	
	@Test(expected=IllegalArgumentException.class)
	public void shouldThrowExceptionIfMessageHasNullContent() throws JMSException {
		TextMessage message = mock(TextMessage.class);
		when(message.getText()).thenReturn(null);
		dispatcher.onMessage(message);
	}

	@Test(expected=RuntimeException.class)
	public void shouldThrowExceptionIfExecutionRequestMessageIsInvalid() throws JMSException {
		TextMessage message = mock(TextMessage.class);
		when(message.getText()).thenReturn("<invalid content>");
		
		LOG.debug("The  org.xml.sax.SAXParseException is expected to be printed out in the log");
		dispatcher.onMessage(message);
		
	}
	
	@Test
	public void shouldCreateAJobForValidExecutionRequest() throws JAXBException, MIFException {
		ExecutionRequestBuilder executionRequestBuiler = new ExecutionRequestBuilder().setExecutionType(ExecutionType.Unknown.name()).setRequestId(requestUUID);
		Job job = dispatcher.createJobForRequest(executionRequestBuiler.getExecutionRequestMsg());
		assertNotNull(job);
		verify(jobManagementService).saveJob(job);
	}

	@Test
	public void shouldNotCreateAJobIfJobForGivenRequestAlreadyExists() throws JAXBException, MIFException, JMSException {
		Connector spiedDispatcher = spy(dispatcher);
		TextMessage message = mock(TextMessage.class);
		ExecutionRequestBuilder executionRequestBuiler = new ExecutionRequestBuilder().setExecutionType(ExecutionType.Unknown.name()).setRequestId(requestUUID);
		when(jobRunnerFactory.createJobRunner((Job) any())).thenReturn(mock(JobRunner.class));
		when(message.getText()).thenReturn(executionRequestBuiler.getExecutionRequestMsg());
		Job job = createJob(requestUUID);
		when(jobManagementService.getJob(requestUUID)).thenReturn(job);
        when(jobManagementService.createNewJob()).thenReturn(job);
        when(jobManagementService.saveJob((Job) any())).thenReturn(job);
		spiedDispatcher.onMessage(message);
		assertNotNull(job);
		verify(spiedDispatcher, times(0)).createJobForRequest((String) any());
	}
	
	@Test
	public void shouldCreateAJobForGivenRequest() throws JAXBException, MIFException, JMSException {
		Connector spiedDispatcher = spy(dispatcher);
		TextMessage message = mock(TextMessage.class);
		ExecutionRequestBuilder executionRequestBuiler = new ExecutionRequestBuilder().setExecutionType(ExecutionType.Unknown.name()).setRequestId(requestUUID);
		when(message.getText()).thenReturn(executionRequestBuiler.getExecutionRequestMsg());
		when(jobRunnerFactory.createJobRunner((Job) any())).thenReturn(mock(JobRunner.class));
		spiedDispatcher.onMessage(message);
		verify(spiedDispatcher, times(1)).createJobForRequest((String) any());
	}
	

	@Test
	public void shouldNotScheduleAJobRunnerIfOneAlreadyExistsForGivenJob() throws JAXBException, MIFException, JMSException {
		Connector spiedDispatcher = spy(dispatcher);
		TextMessage message = mock(TextMessage.class);
		ExecutionRequestBuilder executionRequestBuiler = new ExecutionRequestBuilder().setExecutionType(ExecutionType.Unknown.name()).setRequestId(requestUUID);
		when(jobRunnerRegistry.get(requestUUID)).thenReturn(mock(JobRunner.class));
		spiedDispatcher.setJobRunnerRegistry(jobRunnerRegistry);
		when(message.getText()).thenReturn(executionRequestBuiler.getExecutionRequestMsg());
		Job job = createJob(requestUUID);
		when(jobManagementService.getJob(requestUUID)).thenReturn(job);
		spiedDispatcher.onMessage(message);
		assertNotNull(job);
		verify(spiedDispatcher, times(0)).scheduleJob((Job)any());
	}

	@Test
	public void shouldScheduleAJobRunnerOnlyIfOneAlreadyDoesNotExists() throws JAXBException, MIFException, JMSException {
		Connector spiedDispatcher = spy(dispatcher);
		TextMessage message = mock(TextMessage.class);
		ExecutionRequestBuilder executionRequestBuiler = new ExecutionRequestBuilder().setExecutionType(ExecutionType.Unknown.name()).setRequestId(requestUUID);
		when(jobRunnerFactory.createJobRunner((Job) any())).thenReturn(mock(JobRunner.class));
		when(message.getText()).thenReturn(executionRequestBuiler.getExecutionRequestMsg());
		Job job = createJob(requestUUID);
		when(jobManagementService.getJob(requestUUID)).thenReturn(job);
        when(jobManagementService.createNewJob()).thenReturn(job);
        when(jobManagementService.saveJob((Job) any())).thenReturn(job);
		spiedDispatcher.onMessage(message);
		assertNotNull(job);
		verify(spiedDispatcher, times(1)).scheduleJob((Job)any());
	}

	@Test
	public void shouldScheduleJobRunnerForGivenJob() throws JMSException, MIFException, JAXBException {
		ExecutionRequestBuilder executionRequestBuiler = new ExecutionRequestBuilder().setExecutionType(ExecutionType.Unknown.name()).setRequestId(requestUUID);
		Job job = dispatcher.createJobForRequest(executionRequestBuiler.getExecutionRequestMsg());
		JobRunner jobRunner = mock(JobRunner.class);
		when(jobRunnerFactory.createJobRunner(job)).thenReturn(jobRunner);
		dispatcher.scheduleJob(job);
		verify(taskExecutor).submit(jobRunner);
		assertNotNull(dispatcher.getJobRunnerRegistry().get(job.getJobId()));
	}

	@Test(expected=NullPointerException.class)
	public void shoudThrowExceptionIfResultMessageIsNull() {
		dispatcher.handleResultMessage(null);
	}

	@Test(expected=RuntimeException.class)
	public void shoudThrowExceptionIfResultMessageDoesNotContainRequestID() throws JAXBException {
		String responseMessage = createResponseBuilder().getExecutionResponseMsg();
		responseMessage = responseMessage.replaceAll("<requestId>[\\w-]+</requestId>", "<requestId></requestId>");
		dispatcher.handleResultMessage(responseMessage);
	}

	@Test
	public void shoudPublishResultMessage() throws JAXBException {
		Job job = createJob(requestUUID);
		String responseMessage = createResponseBuilder().getExecutionResponseMsg();
		when(jobManagementService.getJob(requestUUID)).thenReturn(job);
		dispatcher.handleResultMessage(responseMessage);
		verify(jmsTemplate).send(any(String.class), any(MessageCreator.class));
	}
	

	
	//FIXME:  ID 10461:  Temporarily commented this out as workaround for
	//REST service accessing filesystem after job completion - see ticket.
	@Ignore
	@Test
	public void shouldCompleteAJobAndCleanUserPassword() throws JAXBException {
		//Given
		Job job = createJob(requestUUID);
		when(jobManagementService.getJob(requestUUID)).thenReturn(job);
		
		//when
		dispatcher.completeJob(createResponseBuilder().setStatus(JobStatus.COMPLETED.name()).getExecutionResponse());

		//then
		assertEquals("User password was not cleaned up from job","",job.getPassword());
		assertEquals("User password was not cleaned up from execution request","",job.getExecutionRequest().getUserPassword());
		assertEquals("Job status not set to completed",JobStatus.COMPLETED,JobStatus.valueOf(job.getClientRequestStatus()));
		verify(jobManagementService).saveJob(job);
	}

    @Test
    public void shouldCompleteAJob() throws JAXBException {
        //Given
        Job job = createJob(requestUUID);
        when(jobManagementService.getJob(requestUUID)).thenReturn(job);

        //when
        dispatcher.completeJob(createResponseBuilder().setStatus(JobStatus.COMPLETED.name()).getExecutionResponse());

        //then
        assertEquals("Job status not set to completed", JobStatus.COMPLETED, JobStatus.valueOf(job.getClientRequestStatus()));
        verify(jobManagementService).saveJob(job);
    }
	   
	@Test
	public void shouldSetToFailedStateIfCompletedInNoneFinalState() throws JAXBException {
		//Given
		Job job = createJob(requestUUID);
		when(jobManagementService.getJob(requestUUID)).thenReturn(job);

        //when
        dispatcher.completeJob(createResponseBuilder().setStatus(JobStatus.RUNNING.name()).getExecutionResponse());
        
		//then
		assertEquals("Job status has not been set to FAILED.",JobStatus.FAILED,JobStatus.valueOf(job.getClientRequestStatus()));
	}


	@Test(expected=NullPointerException.class)
	public void shoudThrowExceptionWhenHandlingACancelMessageWithNullRequestID() {
		dispatcher.handleCancelMessage(null);
	}

	@Test
	public void shouldInvokeCancelProcessingOnJobRunner() throws JAXBException {
		JobRunner jobRunner = mock(JobRunner.class);
		Job job = createJob(requestUUID);
		when(jobManagementService.getJob(requestUUID)).thenReturn(job);
		dispatcher.getJobRunnerRegistry().put(requestUUID.toString(), jobRunner);
		dispatcher.handleCancelMessage(requestUUID.toString());
		verify(jobRunner).cancelProcessing();
	}
	@Test
	public void shouldRescheduleJobRunners() {
		List<Job> uncompletedJobs = new ArrayList<Job>();
		List<Job> uncompletedJobs2 = new ArrayList<Job>();
		
		Job connectorJob1 = createJob(UUID.randomUUID().toString());
		Job connectorJob2 = createJob(UUID.randomUUID().toString());
		Job connectorJob3 = createJob(UUID.randomUUID().toString());
		Job otherConnectorJob = createJob(UUID.randomUUID().toString());
		
		connectorJob1.setConnectorId("MY_CONNECTOR_ID");
		connectorJob2.setConnectorId("MY_CONNECTOR_ID");
		connectorJob3.setConnectorId("MY_CONNECTOR_ID");
		otherConnectorJob.setConnectorId("SOME_OTHER_CONNECTOR_ID");
		
		uncompletedJobs.add(connectorJob1);
		uncompletedJobs.add(connectorJob2);
		uncompletedJobs.add(connectorJob3);
		uncompletedJobs2.add(otherConnectorJob);
		
		for(Job job : uncompletedJobs) {
			when(jobRunnerFactory.createJobRunner(job)).thenReturn(mock(JobRunner.class));
		}
		when(jobManagementService.getUncompletedDispatcherJobs("MY_CONNECTOR_ID")).thenReturn(uncompletedJobs);
		when(jobManagementService.getUncompletedDispatcherJobs("SOME_OTHER_CONNECTOR_ID")).thenReturn(uncompletedJobs2);
		
		dispatcher.doFailureRecovery();
		
		//Should not get "SOME_OTHER_CONNECTOR_ID" job
		assertEquals(3,dispatcher.getJobRunnerRegistry().size());
	}
	
	/**
	 * Creates a job
	 * @param uuid
	 * @return
	 */
	private Job createJob(String uuid) {
		Job job = TestsHelper.createJob(uuid, uuid);
		job.getExecutionRequest().setUserPassword("mock-password");
		job.setPassword(job.getExecutionRequest().getUserPassword());
		return job;
	}
	/**
	 * Creates response builder creating mock response message
	 * @return
	 */
	private ExecutionResponseBuilder createResponseBuilder() {
	    	ExecutionResponseBuilder responseBuilder = new ExecutionResponseBuilder();
	    	responseBuilder.setRequestId(requestUUID).setStatus(DriverProcessingStatus.RUNNING.getMessage());
	    	return responseBuilder; 
	}
	
}
