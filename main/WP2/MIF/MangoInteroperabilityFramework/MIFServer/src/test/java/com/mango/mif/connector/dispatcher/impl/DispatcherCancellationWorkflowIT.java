/* File			:  DispatcherCancellationWorkflowIT.java
 * Project		:  MIFServer
 * Created on	:  25 Jun 2012
 */
package com.mango.mif.connector.dispatcher.impl;

import static org.junit.Assert.assertEquals;
import static org.mockito.Matchers.any;
import static org.mockito.Matchers.eq;
import static org.mockito.Mockito.*;

import java.io.File;
import java.util.Map;
import java.util.UUID;
import java.util.concurrent.ConcurrentMap;
import java.util.concurrent.CountDownLatch;

import javax.jms.IllegalStateException;

import org.junit.Before;
import org.junit.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.core.task.AsyncTaskExecutor;
import org.springframework.jms.core.JmsTemplate;
import org.springframework.jms.core.MessageCreator;
import org.springframework.scheduling.concurrent.ThreadPoolTaskExecutor;

import com.mango.mif.connector.TestsHelper;
import com.mango.mif.connector.dispatcher.dao.JobRepository;
import com.mango.mif.connector.dispatcher.domain.Job;
import com.mango.mif.connector.runner.api.JobRunner;
import com.mango.mif.connector.runner.api.JobRunnerFactory;
import com.mango.mif.connector.runner.api.SCXMLDriver;
import com.mango.mif.connector.runner.api.StateHandler;
import com.mango.mif.connector.runner.impl.AwaitMockStateHandler;
import com.mango.mif.connector.runner.impl.DefaultJobRunner;
import com.mango.mif.connector.runner.impl.DefaultStateHandler;
import com.mango.mif.connector.runner.impl.DriverProcessingStatus;
import com.mango.mif.connector.runner.impl.JobRunnerSates;
import com.mango.mif.connector.runner.impl.SCXMLDriverImpl;
import com.mango.mif.core.api.WorkspaceManager;
import com.mango.mif.utils.NewExecutionRequestHelper;


/**
 * @version $Revision: $ as of $Date: $
 * <p>SVN Entry : $HeadURL: $
 * <p>SVN ID    : $Id: $
 * <p>Last edited by : $Author: $
 */
/**
 * @author mrogalski
 * 
 * An integration test, checking if cancellation request is handled properly by dispatcher, driver and job runner
 *
 */
public class DispatcherCancellationWorkflowIT {
	/**
	 * Mock job repository
	 */
	@Mock JobRepository jobRepository;
	/**
	 * Mock job repository
	 */
	@Mock JobRunnerFactory jobRunnerFactory;
	/**
	 * Mock job runners task executor
	 */
	@Mock AsyncTaskExecutor taskExecutor;
	/**
	 * Mock state handlers executor
	 */
	AsyncTaskExecutor stateHandlersExecutor;
	/**
	 * jms template
	 */
	@Mock JmsTemplate jobRunnerJmsTemplate;
	/**
	 * jms template
	 */
	@Mock JmsTemplate dispatcherJmsTemplate;
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
	 * Job
	 */
	private Job job;
	/**
	 * job runner
	 */
	private DefaultJobRunner	jobRunner;
	/**
	 * a test job runner result queue
	 */
	private final static String MOCK_JOB_RUNNER_RESULT_QUEUE = "MOCK_JOB_RUNNER_RESULT_QUEUE";
	/**
	 * a test job runner result queue
	 */
	private final static String MOCK_DISPATCHER_RESULT_QUEUE = "MOCK_DISPATCHER_RESULT_QUEUE";
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
		dispatcher.setJmsTemplate(dispatcherJmsTemplate);
		dispatcher.setJobRunnerRegistry(jobRunnerRegistry);
		dispatcher.setDestination(MOCK_DISPATCHER_RESULT_QUEUE);
		tmpDir = TestsHelper.createTmpDirectory();
		dispatcher.setWorkingDirectory(tmpDir);
		
		jobRunner = new DefaultJobRunner() {
			
			@Override
			public void cancelProcessing() {
				driver.fireEvent(JobRunnerSates.CANCELLED.getTriggeringEvent());
			}
			
			@Override
			protected String getWorkingDirectory() {
				return null;
			}
		};
		jobRunner.setDriver(configureSCXMLDriver());
		job = dispatcher.createJobForRequest(NewExecutionRequestHelper.createMockExecutionRequestBuilder().setRequestId(UUID.fromString(requestUUID)).getExecutionRequestMsg());
		jobRunner.setJob(job);
		jobRunner.setJmsTemplate(jobRunnerJmsTemplate);
		jobRunner.setResultDestination(MOCK_JOB_RUNNER_RESULT_QUEUE);
		jobRunner.setWorkspaceManager(mock(WorkspaceManager.class));
		
	}
	/**
	 * Creates an SCXML driver with handlers that will be used in tests
	 * @return
	 * @throws IllegalStateException
	 */
	private SCXMLDriver configureSCXMLDriver() throws IllegalStateException {
		
		ThreadPoolTaskExecutor stateHandlersExecutor = new ThreadPoolTaskExecutor();
		stateHandlersExecutor.initialize();
		
		SCXMLDriverImpl scxmlDriver = new SCXMLDriverImpl();
		scxmlDriver.setStateHandlersExecutor(stateHandlersExecutor);
        scxmlDriver.setSCXMLDocument(DispatcherCancellationWorkflowIT.class.getResource("/META-INF/JobRunnerWithFailover.scxml"));

        scxmlDriver.registerHandler(spy(new AwaitMockStateHandler(JobRunnerSates.PENDING.getStateName(), JobRunnerSates.RUNNING.getTriggeringEvent())));
        scxmlDriver.registerHandler(spy(new DefaultStateHandler(JobRunnerSates.RUNNING.getStateName(), SCXMLDriver.NULL_EVENT)));
        scxmlDriver.registerHandler(spy(new AwaitMockStateHandler("task-pending", "task.prepare")));
        scxmlDriver.registerHandler(spy(new AwaitMockStateHandler("task-preparing", "task.submit")));
        scxmlDriver.registerHandler(spy(new AwaitMockStateHandler("task-submitting", "task.process")));
        scxmlDriver.registerHandler(spy(new AwaitMockStateHandler("task-processing", "task.retrieve")));
        scxmlDriver.registerHandler(spy(new AwaitMockStateHandler("task-retrieving", "task.postprocess")));
        scxmlDriver.registerHandler(spy(new AwaitMockStateHandler("task-postprocessing", "task.finished")));
        scxmlDriver.registerHandler(spy(new AwaitMockStateHandler("task-finished", JobRunnerSates.FINISHED.getTriggeringEvent())));
        scxmlDriver.registerHandler(spy(new DefaultStateHandler(JobRunnerSates.FINISHED.getStateName(), SCXMLDriver.NULL_EVENT)));
        scxmlDriver.registerHandler(spy(new DefaultStateHandler(JobRunnerSates.CANCELLED.getStateName(), SCXMLDriver.NULL_EVENT)));
        
        return scxmlDriver;
	}
	
	@Test
	public void shouldAcknowledgeCancellationIfCancelledBeforeStarting() {
		final DefaultJobRunner spiedInstance = spy(jobRunner);
		when(dispatcher.getJobRunnerRegistry().get(job.getExecutionRequest().getRequestId().toString())).thenReturn(spiedInstance);
		when(jobRepository.getJob(job.getExecutionRequest().getRequestId().toString())).thenReturn(job);
		dispatcher.handleCancelMessage(job.getExecutionRequest().getRequestId().toString());
		
		verify(spiedInstance).cancelProcessing();
		DefaultStateHandler cancelledStateHandler = ((DefaultStateHandler)((SCXMLDriverImpl)jobRunner.getDriver()).getStateHandlers().get("cancelled"));
		
		spiedInstance.call();

		assertEquals(DriverProcessingStatus.CANCELLED,spiedInstance.getDriver().getProcessingStatus());
		
		verify(jobRunnerJmsTemplate).send(eq(MOCK_JOB_RUNNER_RESULT_QUEUE), (MessageCreator) any());
		verify(cancelledStateHandler).call();
	}

	@Test
	public void shouldCancelExecutionAndReceiveResultMessageOnlyOnce() throws InterruptedException {
		final DefaultJobRunner spiedInstance = spy(jobRunner);
		when(dispatcher.getJobRunnerRegistry().get(job.getExecutionRequest().getRequestId().toString())).thenReturn(spiedInstance);
		when(jobRepository.getJob(job.getExecutionRequest().getRequestId().toString())).thenReturn(job);
		Map<String,StateHandler> stateHandlers = ((SCXMLDriverImpl)jobRunner.getDriver()).getStateHandlers();
		
		final CountDownLatch signal = new CountDownLatch(1);
		
		Thread thread = new Thread("Thread assigned to Job runner") {
			public void run() {

				spiedInstance.call();
				signal.countDown();
			};
		};
		thread.start();

		((AwaitMockStateHandler)stateHandlers.get(JobRunnerSates.PENDING.getStateName())).getAwaitSignal().countDown();
		((AwaitMockStateHandler)stateHandlers.get("task-pending")).getAwaitSignal().countDown();
		
		AwaitMockStateHandler handler = ((AwaitMockStateHandler)stateHandlers.get("task-pending"));
		
		while(!handler.isDone()) 
		{
			Thread.sleep(1000);
		}
		
		dispatcher.handleCancelMessage(job.getExecutionRequest().getRequestId().toString());
		
		signal.await();
		dispatcher.handleResultMessage(spiedInstance.getResultMessage());
		
		verify(spiedInstance).cancelProcessing();
		assertEquals(DriverProcessingStatus.CANCELLED,spiedInstance.getDriver().getProcessingStatus());
		verify(jobRunnerJmsTemplate, times(1)).send(eq(MOCK_JOB_RUNNER_RESULT_QUEUE), (MessageCreator) any());
		verify(dispatcherJmsTemplate, times(1)).send(eq(MOCK_DISPATCHER_RESULT_QUEUE), (MessageCreator) any());
	}
	
	@Test
	public void shouldCancelExecutionAndReceiveResultMessageOnlyOnceShouldInvokeACancelledStateHandlerIfStateHandlerOnWhichTheCancellationHasBeenReceivedIsNotifiedInternallyOnCancellation() throws InterruptedException {
		final DefaultJobRunner spiedInstance = spy(jobRunner);
		when(dispatcher.getJobRunnerRegistry().get(job.getExecutionRequest().getRequestId().toString())).thenReturn(spiedInstance);
		when(jobRepository.getJob(job.getExecutionRequest().getRequestId().toString())).thenReturn(job);
		Map<String,StateHandler> stateHandlers = ((SCXMLDriverImpl)jobRunner.getDriver()).getStateHandlers();
		
		final CountDownLatch signal = new CountDownLatch(1);
		
		Thread thread = new Thread("Thread assigned to Job runner") {
			public void run() {
				spiedInstance.call();
				signal.countDown();
			};
		};
		thread.start();

		((AwaitMockStateHandler)stateHandlers.get(JobRunnerSates.PENDING.getStateName())).getAwaitSignal().countDown();
		((AwaitMockStateHandler)stateHandlers.get("task-pending")).getAwaitSignal().countDown();
		
		AwaitMockStateHandler handler = ((AwaitMockStateHandler)stateHandlers.get("task-pending"));
		
		while(!handler.isDone()) 
		{
			Thread.sleep(1000);
		}
		
		dispatcher.handleCancelMessage(job.getExecutionRequest().getRequestId().toString());
		
		((AwaitMockStateHandler)stateHandlers.get("task-preparing")).getAwaitSignal().countDown();
		
		signal.await();
		dispatcher.handleResultMessage(spiedInstance.getResultMessage());
		
		DefaultStateHandler cancelledStateHandler = ((DefaultStateHandler)stateHandlers.get(JobRunnerSates.CANCELLED.getStateName()));
		
		verify(spiedInstance).cancelProcessing();
		assertEquals(DriverProcessingStatus.CANCELLED,spiedInstance.getDriver().getProcessingStatus());
		verify(jobRunnerJmsTemplate, times(1)).send(eq(MOCK_JOB_RUNNER_RESULT_QUEUE), (MessageCreator) any());
		verify(dispatcherJmsTemplate, times(1)).send(eq(MOCK_DISPATCHER_RESULT_QUEUE), (MessageCreator) any());
		verify(cancelledStateHandler).call();
	}
}
