/* File			:  PendingStateHandlerTest.java
 * Project		:  MIFServer
 * Created on	:  8 Jun 2012
 */
package com.mango.mif.sgeconnector;

import static org.junit.Assert.assertEquals;
import static org.mockito.Matchers.any;
import static org.mockito.Mockito.when;


import org.junit.Before;
import org.junit.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.core.task.AsyncTaskExecutor;

import com.mango.mif.connector.domain.Job;
import com.mango.mif.connector.runner.api.SCXMLDriver;
import com.mango.mif.connector.runner.impl.DefaultJobRunner;
import com.mango.mif.connector.runner.impl.DefaultStateHandler;
import com.mango.mif.connector.runner.impl.JobRunnerState;
import com.mango.mif.connector.runner.impl.SCXMLDriverImpl;
import com.mango.mif.managers.JobManagementService;


/**
 * 
 * Tests Pending state handler
 * @version $Revision: $ as of $Date: $
 * <p>SVN Entry : $HeadURL: $
 * <p>SVN ID    : $Id: $
 * <p>Last edited by : $Author: $
 */
public class PendingStateHandlerTest {
	
	private PendingStateHandler stateHandler;
	@Mock private JobManagementService jobManagementService;
	@Mock private DefaultJobRunner jobRunner;
    @Mock private AsyncTaskExecutor stateHandlersExecutor;
	 private SCXMLDriverImpl sCXMLDriver;
	
	@Before
	public void setUp() throws Exception {
		MockitoAnnotations.initMocks(this);
		sCXMLDriver = new SCXMLDriverImpl();
		sCXMLDriver.setStateHandlersExecutor(stateHandlersExecutor);
		sCXMLDriver.setSCXMLDocument(PendingStateHandlerTest.class.getResource("/META-INF/JobRunnerWithFailover.scxml"));
		sCXMLDriver.registerHandler(new DefaultStateHandler(JobRunnerState.PENDING.getStateName(), SCXMLDriver.NULL_EVENT));
        sCXMLDriver.registerHandler(new DefaultStateHandler(JobRunnerState.RUNNING.getStateName(), SCXMLDriver.NULL_EVENT));
		stateHandler = new PendingStateHandler(JobRunnerState.TASK_PENDING.getStateName(), JobRunnerState.TASK_PREPARING.getTriggeringEvent());
		stateHandler.setJobManagementService(jobManagementService);
		stateHandler.setJobRunner(jobRunner);
		stateHandler.setSCXMLDriver(sCXMLDriver);
		sCXMLDriver.registerHandler(stateHandler);
		sCXMLDriver.fireEvent(JobRunnerState.RUNNING.getTriggeringEvent());
	}

	@Test
	public void shouldResultInTaskPrepareEventIfNew() {
		Job job = new Job();
        job.setJobId("1000");
        job.setJobRunnerState(JobRunnerState.UNDEFINED);
        sCXMLDriver.registerHandler(new DefaultStateHandler(JobRunnerState.TASK_PREPARING.getStateName(), SCXMLDriver.NULL_EVENT));
        when(jobRunner.getJobId()).thenReturn(job.getJobId());
		when(jobManagementService.getJob((String)any())).thenReturn(job);
        when(jobManagementService.saveJob(job)).thenReturn(job);
		stateHandler.call();
		
		assertEquals(JobRunnerState.TASK_PREPARING.getTriggeringEvent(),stateHandler.complete());
        assertEquals(JobRunnerState.TASK_PENDING,job.getJobRunnerState());
        sCXMLDriver.inState(JobRunnerState.TASK_PREPARING.getStateName());
		
	}
	
	@Test
	public void shouldResultInTaskMonitoringEvent() {
		
		Job job = new Job();
		job.setJobId("1000");
		job.setJobRunnerState(JobRunnerState.TASK_MONITORING);
        sCXMLDriver.registerHandler(new DefaultStateHandler(JobRunnerState.TASK_MONITORING.getStateName(), SCXMLDriver.NULL_EVENT));
        when(jobRunner.getJobId()).thenReturn(job.getJobId());
        when(jobManagementService.getJob((String)any())).thenReturn(job);
        when(jobManagementService.saveJob(job)).thenReturn(job);
		
		stateHandler.call();

        assertEquals(JobRunnerState.TASK_MONITORING.getTriggeringEvent(),stateHandler.complete());
        assertEquals(JobRunnerState.TASK_MONITORING,job.getJobRunnerState());
        sCXMLDriver.inState(JobRunnerState.TASK_MONITORING.getStateName());
	}
	
	   @Test
	    public void  shouldResultInTaskPostProcessingEvent() {
	        
	        Job job = new Job();
	        job.setJobId("1000");
	        job.setJobRunnerState(JobRunnerState.TASK_POSTPROCESSING);
	        sCXMLDriver.registerHandler(new DefaultStateHandler(JobRunnerState.TASK_POSTPROCESSING.getStateName(), SCXMLDriver.NULL_EVENT));
	        when(jobRunner.getJobId()).thenReturn(job.getJobId());
	        when(jobManagementService.getJob((String)any())).thenReturn(job);
	        when(jobManagementService.saveJob(job)).thenReturn(job);
	        
	        stateHandler.call();

	        assertEquals(JobRunnerState.TASK_POSTPROCESSING.getTriggeringEvent(),stateHandler.complete());
	        assertEquals(JobRunnerState.TASK_POSTPROCESSING,job.getJobRunnerState());
	        sCXMLDriver.inState(JobRunnerState.TASK_POSTPROCESSING.getStateName());
	    }
	
	@Test
	public void shouldResultInFinalFinishedEvent() {

        Job job = new Job();
        job.setJobId("1000");
        job.setJobRunnerState(JobRunnerState.FINISHED);
        sCXMLDriver.registerHandler(new DefaultStateHandler(JobRunnerState.FINISHED.getStateName(), SCXMLDriver.NULL_EVENT));
        when(jobRunner.getJobId()).thenReturn(job.getJobId());
        when(jobManagementService.getJob((String)any())).thenReturn(job);
        when(jobManagementService.saveJob(job)).thenReturn(job);
        
        stateHandler.call();

        assertEquals(JobRunnerState.FINISHED.getTriggeringEvent(),stateHandler.complete());
        assertEquals(JobRunnerState.FINISHED,job.getJobRunnerState());
        sCXMLDriver.inState(JobRunnerState.FINISHED.getStateName());
	}
	
	@Test
	public void shouldResultInFailedEvent() {

        Job job = new Job();
        job.setJobId("1000");
        job.setJobRunnerState(JobRunnerState.FAILED);
        sCXMLDriver.registerHandler(new DefaultStateHandler(JobRunnerState.FAILED.getStateName(), SCXMLDriver.NULL_EVENT));
        when(jobRunner.getJobId()).thenReturn(job.getJobId());
        when(jobManagementService.getJob((String)any())).thenReturn(job);
        when(jobManagementService.saveJob(job)).thenReturn(job);
        
        stateHandler.call();

        assertEquals(JobRunnerState.FAILED.getTriggeringEvent(),stateHandler.complete());
        assertEquals(JobRunnerState.FAILED,job.getJobRunnerState());
        sCXMLDriver.inState(JobRunnerState.FAILED.getStateName());
	}

}
