/* File			:  ConnectorCancellationWorkflowTest.java
 * Project		:  MIFServer
 * Created on	:  25 Jun 2012
 */
package com.mango.mif.connector;

import static org.junit.Assert.assertEquals;
import static org.mockito.Matchers.any;
import static org.mockito.Matchers.eq;
import static org.mockito.Mockito.spy;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import java.util.List;
import java.util.Map;
import java.util.Properties;
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

import com.google.common.collect.Lists;
import com.mango.mif.connector.domain.Job;
import com.mango.mif.connector.runner.api.JobRunner;
import com.mango.mif.connector.runner.api.JobRunnerFactory;
import com.mango.mif.connector.runner.api.SCXMLDriver;
import com.mango.mif.connector.runner.api.StateHandler;
import com.mango.mif.connector.runner.impl.AbstractStateHandler;
import com.mango.mif.connector.runner.impl.AwaitMockStateHandler;
import com.mango.mif.connector.runner.impl.DefaultJobRunner;
import com.mango.mif.connector.runner.impl.DefaultStateHandler;
import com.mango.mif.connector.runner.impl.DriverProcessingStatus;
import com.mango.mif.connector.runner.impl.JobRunnerState;
import com.mango.mif.connector.runner.impl.SCXMLDriverImpl;
import com.mango.mif.domain.ExecutionRequestBuilder;
import com.mango.mif.domain.ExecutionType;
import com.mango.mif.managers.JobManagementService;
import com.mango.mif.utils.MIFProperties;


/**
 * @version $Revision: $ as of $Date: $
 * <p>SVN Entry : $HeadURL: $
 * <p>SVN ID    : $Id: $
 * <p>Last edited by : $Author: $
 */
/**
 * @author mrogalski
 * 
 * A unit test, checking if cancellation request is handled properly by dispatcher, driver and job runner
 *
 */
public class ConnectorCancellationWorkflowTest {
	/**
	 * Mock job repository
	 */
	@Mock JobManagementService jobManagementService;
	/**
	 * Mock job repository
	 */
	@Mock JobRunnerFactory jobRunnerFactory;
	/**
	 * Mock job runners task executor
	 */
	@Mock AsyncTaskExecutor taskExecutor;
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
	private Connector dispatcher;
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
        Properties properties = new Properties();
        properties.load(ConnectorCancellationWorkflowTest.class.getResourceAsStream("/com/mango/mif/sgeconnector/connector.properties"));
        MIFProperties.loadProperties(properties);
		requestUUID = UUID.randomUUID().toString();
		dispatcher = new Connector();
		dispatcher.setJobManagementService(jobManagementService);
		dispatcher.setJobRunnerFactory(jobRunnerFactory);
		dispatcher.setTaskExecutor(taskExecutor);
		dispatcher.setJmsTemplate(dispatcherJmsTemplate);
		dispatcher.setJobRunnerRegistry(jobRunnerRegistry);
		dispatcher.setResponseQueue(MOCK_DISPATCHER_RESULT_QUEUE);
		
		jobRunner = new DefaultJobRunner();
		jobRunner.setDriver(configureSCXMLDriver());
		job = new Job();
		job.setJobId(requestUUID);
        when(jobManagementService.createNewJob()).thenReturn(job);
        when(jobManagementService.saveJob(job)).thenReturn(job);
        when(jobManagementService.getJob(job.getJobId())).thenReturn(job);
		job = dispatcher.createJobForRequest(new ExecutionRequestBuilder().setExecutionType(ExecutionType.Unknown.name()).setRequestId(requestUUID).getExecutionRequestMsg());

		jobRunner.setJobId(job.getJobId());
		jobRunner.setJmsTemplate(jobRunnerJmsTemplate);
		jobRunner.setResultDestination(MOCK_JOB_RUNNER_RESULT_QUEUE);
		
	}
	/**
	 * Creates an SCXML driver with handlers that will be used in tests
	 * @return
	 * @throws IllegalStateException
	 */
	private SCXMLDriver configureSCXMLDriver() throws IllegalStateException {
		
	    List<AbstractStateHandler> stateHandlers = Lists.newArrayList();
	    
	    stateHandlers.add(new AwaitMockStateHandler(JobRunnerState.PENDING.getStateName(), JobRunnerState.RUNNING.getTriggeringEvent()));
	    stateHandlers.add(new DefaultStateHandler(JobRunnerState.RUNNING.getStateName(), SCXMLDriver.NULL_EVENT));
	    stateHandlers.add(new AwaitMockStateHandler(JobRunnerState.TASK_PENDING.getStateName(), JobRunnerState.TASK_PREPARING.getTriggeringEvent()));
	    stateHandlers.add(new AwaitMockStateHandler(JobRunnerState.TASK_PREPARING.getStateName(), JobRunnerState.TASK_SUBMITTING.getTriggeringEvent()));
	    stateHandlers.add(new AwaitMockStateHandler(JobRunnerState.TASK_SUBMITTING.getStateName(), JobRunnerState.TASK_PROCESSING.getTriggeringEvent()));
	    stateHandlers.add(new AwaitMockStateHandler(JobRunnerState.TASK_PROCESSING.getStateName(), JobRunnerState.TASK_RETRIEVING.getTriggeringEvent()));
	    stateHandlers.add(new AwaitMockStateHandler(JobRunnerState.TASK_RETRIEVING.getStateName(), JobRunnerState.TASK_POSTPROCESSING.getTriggeringEvent()));
	    stateHandlers.add(new AwaitMockStateHandler(JobRunnerState.TASK_POSTPROCESSING.getStateName(), JobRunnerState.TASK_FINISHED.getTriggeringEvent()));
	    stateHandlers.add(new AwaitMockStateHandler(JobRunnerState.TASK_FINISHED.getStateName(), JobRunnerState.FINISHED.getTriggeringEvent()));
	    stateHandlers.add(new DefaultStateHandler(JobRunnerState.FINISHED.getStateName(), SCXMLDriver.NULL_EVENT));
	    stateHandlers.add(new DefaultStateHandler(JobRunnerState.CANCELLED.getStateName(), SCXMLDriver.NULL_EVENT));
	    
	    
		ThreadPoolTaskExecutor stateHandlersExecutor = new ThreadPoolTaskExecutor();
		stateHandlersExecutor.initialize();
		
		SCXMLDriverImpl scxmlDriver = new SCXMLDriverImpl();
		scxmlDriver.setStateHandlersExecutor(stateHandlersExecutor);
        scxmlDriver.setSCXMLDocument(ConnectorCancellationWorkflowTest.class.getResource("/META-INF/SGEJobRunner.scxml"));
        for(AbstractStateHandler stateHandler : stateHandlers) {
            if(stateHandler instanceof DefaultStateHandler) {
                ((DefaultStateHandler)stateHandler).setJobManagementService(jobManagementService);
                ((DefaultStateHandler)stateHandler).setJobRunner(jobRunner);
            }
            stateHandler.setSCXMLDriver(scxmlDriver);
            scxmlDriver.registerHandler(spy(stateHandler));
        }
        return scxmlDriver;
	}
	
	@Test
	public void shouldAcknowledgeCancellationIfCancelledBeforeStarting() {
        when(dispatcher.getJobRunnerRegistry().get(job.getJobId())).thenReturn(jobRunner);
		dispatcher.handleCancelMessage(job.getExecutionRequest().getRequestId().toString());
		
		DefaultStateHandler cancelledStateHandler = ((DefaultStateHandler)((SCXMLDriverImpl)jobRunner.getDriver()).getStateHandlers().get(JobRunnerState.CANCELLED.getStateName()));
		
		jobRunner.call();

		assertEquals(DriverProcessingStatus.CANCELLED,jobRunner.getDriver().getProcessingStatus());
		
		verify(jobRunnerJmsTemplate).send(eq(MOCK_JOB_RUNNER_RESULT_QUEUE), (MessageCreator) any());
		verify(cancelledStateHandler).call();
	}

	@Test
	public void shouldCancelExecutionAndReceiveResultMessageOnlyOnce() throws InterruptedException {
		final DefaultJobRunner spiedInstance = spy(jobRunner);
        when(dispatcher.getJobRunnerRegistry().get(job.getJobId())).thenReturn(spiedInstance);
		Map<String,StateHandler> stateHandlers = ((SCXMLDriverImpl)jobRunner.getDriver()).getStateHandlers();
		
		final CountDownLatch signal = new CountDownLatch(1);
		
		Thread thread = new Thread("Thread assigned to Job runner") {
			public void run() {

				spiedInstance.call();
				signal.countDown();
			};
		};
		thread.start();

		((AwaitMockStateHandler)stateHandlers.get(JobRunnerState.PENDING.getStateName())).getAwaitSignal().countDown();
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
        when(dispatcher.getJobRunnerRegistry().get(job.getJobId())).thenReturn(spiedInstance);
		Map<String,StateHandler> stateHandlers = ((SCXMLDriverImpl)jobRunner.getDriver()).getStateHandlers();
		
		final CountDownLatch signal = new CountDownLatch(1);
		
		Thread thread = new Thread("Thread assigned to Job runner") {
			public void run() {
				spiedInstance.call();
				signal.countDown();
			};
		};
		thread.start();

		((AwaitMockStateHandler)stateHandlers.get(JobRunnerState.PENDING.getStateName())).getAwaitSignal().countDown();
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
		
		DefaultStateHandler cancelledStateHandler = ((DefaultStateHandler)stateHandlers.get(JobRunnerState.CANCELLED.getStateName()));
		
		verify(spiedInstance).cancelProcessing();
		assertEquals(DriverProcessingStatus.CANCELLED,spiedInstance.getDriver().getProcessingStatus());
		verify(jobRunnerJmsTemplate, times(1)).send(eq(MOCK_JOB_RUNNER_RESULT_QUEUE), (MessageCreator) any());
		verify(dispatcherJmsTemplate, times(1)).send(eq(MOCK_DISPATCHER_RESULT_QUEUE), (MessageCreator) any());
		verify(cancelledStateHandler).call();
	}
}
