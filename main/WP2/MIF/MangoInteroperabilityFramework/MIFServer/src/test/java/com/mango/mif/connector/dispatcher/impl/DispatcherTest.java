/* File			:  DispatcherTest.java
 * Project		:  MIFServer
 * Created on	:  20 Jun 2012
 */
package com.mango.mif.connector.dispatcher.impl;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.mockito.Matchers.any;
import static org.mockito.Mockito.*;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

import javax.jms.JMSException;
import javax.jms.TextMessage;
import javax.xml.bind.JAXBException;
import java.util.concurrent.ConcurrentMap;

import org.apache.commons.io.FileUtils;
import org.apache.log4j.Logger;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.core.task.AsyncTaskExecutor;
import org.springframework.jms.core.JmsTemplate;
import org.springframework.jms.core.MessageCreator;

import com.mango.mif.connector.TestsHelper;
import com.mango.mif.connector.dispatcher.dao.JobRepository;
import com.mango.mif.connector.dispatcher.domain.Job;
import com.mango.mif.connector.dispatcher.domain.JobStatus;
import com.mango.mif.connector.runner.api.JobRunner;
import com.mango.mif.connector.runner.api.JobRunnerFactory;
import com.mango.mif.connector.runner.impl.DriverProcessingStatus;
import com.mango.mif.domain.ExecutionRequestBuilder;
import com.mango.mif.domain.ExecutionResponseBuilder;
import com.mango.mif.exception.MIFException;
import com.mango.mif.utils.NewExecutionRequestHelper;


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
public class DispatcherTest {
	/**
	 * Logger
	 */
	private final static Logger LOG = Logger.getLogger(DispatcherTest.class);
	/**
	 * Mock job repository
	 */
	@Mock JobRepository jobRepository;
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
	private Dispatcher dispatcher;
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
		dispatcher = new Dispatcher();
		dispatcher.setJobRepository(jobRepository);
		dispatcher.setJobRunnerFactory(jobRunnerFactory);
		dispatcher.setTaskExecutor(taskExecutor);
		dispatcher.setJmsTemplate(jmsTemplate);
		tmpDir = TestsHelper.createTmpDirectory();
		dispatcher.setWorkingDirectory(tmpDir);
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
		dispatcher.onMessage(message);
		
	}
	
	@Test
	public void shouldCreateAJobForValidExecutionRequest() throws JAXBException, MIFException {
		ExecutionRequestBuilder executionRequestBuiler = NewExecutionRequestHelper.createMockExecutionRequestBuilder();
		Job job = dispatcher.createJobForRequest(executionRequestBuiler.getExecutionRequestMsg());
		assertNotNull(job);
		verify(jobRepository).save(job);
	}

	@Test
	public void shouldNotCreateAJobIfJobForGivenRequestAlreadyExists() throws JAXBException, MIFException, JMSException {
		Dispatcher spiedDispatcher = spy(dispatcher);
		TextMessage message = mock(TextMessage.class);
		ExecutionRequestBuilder executionRequestBuiler = NewExecutionRequestHelper.createMockExecutionRequestBuilder().setRequestId(UUID.fromString(requestUUID));
		when(jobRunnerFactory.createJobRunner((Job) any())).thenReturn(mock(JobRunner.class));
		when(message.getText()).thenReturn(executionRequestBuiler.getExecutionRequestMsg());
		Job job = createJob(requestUUID);
		spiedDispatcher.createJobWorkingDirectory(job);
		when(jobRepository.getJob(requestUUID)).thenReturn(job);
		spiedDispatcher.onMessage(message);
		assertNotNull(job);
		verify(spiedDispatcher, times(0)).createJobForRequest((String) any());
	}
	
	@Test
	public void shouldCreateAJobForGivenRequest() throws JAXBException, MIFException, JMSException {
		Dispatcher spiedDispatcher = spy(dispatcher);
		TextMessage message = mock(TextMessage.class);
		ExecutionRequestBuilder executionRequestBuiler = NewExecutionRequestHelper.createMockExecutionRequestBuilder().setRequestId(UUID.fromString(requestUUID));
		when(message.getText()).thenReturn(executionRequestBuiler.getExecutionRequestMsg());
		when(jobRunnerFactory.createJobRunner((Job) any())).thenReturn(mock(JobRunner.class));
		spiedDispatcher.onMessage(message);
		verify(spiedDispatcher, times(1)).createJobForRequest((String) any());
	}
	

	@Test
	public void shouldNotScheduleAJobRunnerIfOneAlreadyExistsForGivenJob() throws JAXBException, MIFException, JMSException {
		Dispatcher spiedDispatcher = spy(dispatcher);
		TextMessage message = mock(TextMessage.class);
		ExecutionRequestBuilder executionRequestBuiler = NewExecutionRequestHelper.createMockExecutionRequestBuilder().setRequestId(UUID.fromString(requestUUID));
		when(jobRunnerRegistry.get(requestUUID)).thenReturn(mock(JobRunner.class));
		spiedDispatcher.setJobRunnerRegistry(jobRunnerRegistry);
		when(message.getText()).thenReturn(executionRequestBuiler.getExecutionRequestMsg());
		Job job = createJob(requestUUID);
		spiedDispatcher.createJobWorkingDirectory(job);
		when(jobRepository.getJob(requestUUID)).thenReturn(job);
		spiedDispatcher.onMessage(message);
		assertNotNull(job);
		verify(spiedDispatcher, times(0)).scheduleJob((Job)any());
	}

	@Test
	public void shouldScheduleAJobRunnerOnlyIfOneAlreadyDoesNotExists() throws JAXBException, MIFException, JMSException {
		Dispatcher spiedDispatcher = spy(dispatcher);
		TextMessage message = mock(TextMessage.class);
		ExecutionRequestBuilder executionRequestBuiler = NewExecutionRequestHelper.createMockExecutionRequestBuilder().setRequestId(UUID.fromString(requestUUID));
		when(jobRunnerFactory.createJobRunner((Job) any())).thenReturn(mock(JobRunner.class));
		when(message.getText()).thenReturn(executionRequestBuiler.getExecutionRequestMsg());
		Job job = createJob(requestUUID);
		spiedDispatcher.createJobWorkingDirectory(job);
		when(jobRepository.getJob(requestUUID)).thenReturn(job);
		spiedDispatcher.onMessage(message);
		assertNotNull(job);
		verify(spiedDispatcher, times(1)).scheduleJob((Job)any());
	}

	@Test
	public void shouldScheduleJobRunnerForGivenJob() throws JMSException, MIFException, JAXBException {
		ExecutionRequestBuilder executionRequestBuiler = NewExecutionRequestHelper.createMockExecutionRequestBuilder();
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

	@Test(expected=NullPointerException.class)
	public void shoudThrowExceptionIfResultMessageDoesNotContainRequestID() throws JAXBException {
		String responseMessage = createResponseBuilder().getExecutionResponseMsg();
		responseMessage = responseMessage.replaceAll("<requestId>[\\w-]+</requestId>", "<requestId></requestId>");
		dispatcher.handleResultMessage(responseMessage);
	}

	@Test
	public void shoudPublishResultMessage() throws JAXBException {
		Job job = createJob(requestUUID);
		String responseMessage = createResponseBuilder().setRequestId(UUID.fromString(requestUUID)).getExecutionResponseMsg();
		when(jobRepository.getJob(requestUUID)).thenReturn(job);
		dispatcher.handleResultMessage(responseMessage);
		verify(jmsTemplate).send(any(String.class), any(MessageCreator.class));
	}
	

	@Test
	public void shouldCompleteAJobAndCleanUserPassword() {
		//Given
		Job job = createJob(requestUUID);
		when(jobRepository.getJob(requestUUID)).thenReturn(job);
		
		//when
		dispatcher.completeJob(requestUUID);

		//then
		assertEquals("User password was not cleaned up from job","",job.getPassword());
		assertEquals("User password was not cleaned up from execution request","",job.getExecutionRequest().getUserPassword());
		assertEquals("Job status not set to completed",JobStatus.COMPLETED,JobStatus.valueOf(job.getClientRequestStatus()));
		verify(jobRepository).save(job);
	}
	
	@Test
	public void shouldCompleteAJobButNotChangeAStateOfAJobIfAlreadyInFinalState() {
		//Given
		Job job = createJob(requestUUID);
		job.setClientRequestStatus(JobStatus.CANCELLED.name());
		when(jobRepository.getJob(requestUUID)).thenReturn(job);
		
		//when
		dispatcher.completeJob(requestUUID);

		//then
		assertEquals("Job status has been changed from cancelled.",JobStatus.CANCELLED,JobStatus.valueOf(job.getClientRequestStatus()));
	}


	@Test(expected=NullPointerException.class)
	public void shoudThrowExceptionWhenHandlingACancelMessageWithNullRequestID() {
		dispatcher.handleCancelMessage(null);
	}

	@Test
	public void shouldInvokeCancelProcessingOnJobRunner() throws JAXBException {
		JobRunner jobRunner = mock(JobRunner.class);
		Job job = createJob(requestUUID);
		when(jobRepository.getJob(requestUUID)).thenReturn(job);
		dispatcher.getJobRunnerRegistry().put(requestUUID.toString(), jobRunner);
		dispatcher.handleCancelMessage(requestUUID.toString());
		verify(jobRunner).cancelProcessing();
	}
	@Test
	public void shouldRescheduleJobRunners() {
		List<Job> uncompletedJobs = new ArrayList<Job>();
		uncompletedJobs.add(createJob(UUID.randomUUID().toString()));
		uncompletedJobs.add(createJob(UUID.randomUUID().toString()));
		uncompletedJobs.add(createJob(UUID.randomUUID().toString()));
		
		for(Job job : uncompletedJobs) {
			dispatcher.createJobWorkingDirectory(job);
			when(jobRunnerFactory.createJobRunner(job)).thenReturn(mock(JobRunner.class));
		}
		when(jobRepository.getUncompletedJobs()).thenReturn(uncompletedJobs);
		
		
		
		
		dispatcher.doFailureRecovery();
		assertEquals(3,dispatcher.getJobRunnerRegistry().size());
	}
	
	/**
	 * Creates a job
	 * @param uuid
	 * @return
	 */
	private Job createJob(String uuid) {
		Job job = TestsHelper.createJob(uuid, UUID.fromString(uuid));
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
	    	responseBuilder.setRequestId(UUID.randomUUID()).setStatus(DriverProcessingStatus.RUNNING.getMessage());
	    	return responseBuilder; 
	}
	
}
