/* File			:  PendingStateHandlerTest.java
 * Project		:  MIFServer
 * Created on	:  8 Jun 2012
 */
package com.mango.mif.rsgeconnector.runner.impl;

import static org.hamcrest.CoreMatchers.equalTo;
import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.assertThat;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import java.util.ArrayList;
import java.util.List;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import com.mango.mif.connector.dispatcher.domain.Job;
import com.mango.mif.connector.runner.api.SCXMLDriver;
import com.mango.mif.connector.runner.impl.JobRunnerSates;
import com.mango.mif.core.dao.ExecutionJobRepository;
import com.mango.mif.core.domain.ExecutionJobKey;
import com.mango.mif.core.domain.ExecutionJobValue;
import com.mango.mif.core.domain.JobRuntimeData;


/**
 * 
 * Tests Pending state handler
 * @version $Revision: $ as of $Date: $
 * <p>SVN Entry : $HeadURL: $
 * <p>SVN ID    : $Id: $
 * <p>Last edited by : $Author: $
 */
public class PendingStateHandlerTest {
	/**
	 * Tested instance
	 */
	private PendingStateHandler stateHanlder;
	/**
	 * Job repository
	 */
	@Mock private ExecutionJobRepository executionJobRepository;
	/**
	 * job runner
	 */
	@Mock private RSGEJobRunner rSGEJobRunner;
	/**
	 * SXCML driver
	 */
	@Mock private SCXMLDriver sCXMLDriver;
	@Before
	public void setUp() throws Exception {
		MockitoAnnotations.initMocks(this);
		stateHanlder = new PendingStateHandler(SCXMLDriver.NULL_EVENT, SCXMLDriver.NULL_EVENT);
		stateHanlder.setExecutionJobRepository(executionJobRepository);
		stateHanlder.setJobRunner(rSGEJobRunner);
		
	}

	@Test
	public void shouldGetUnCompletedJobs() {
		Job job = new Job();
		when(rSGEJobRunner.getJob()).thenReturn(job);
		
		stateHanlder.doProcessing();
		
		verify(executionJobRepository).getUncompletedJobs();
	}
	
	@Test
	public void shouldInvokeUnCompletedJobs() {
		Job job = new Job();
		
		when(rSGEJobRunner.getJob()).thenReturn(job);
		
		stateHanlder.doProcessing();
		
		verify(executionJobRepository).getUncompletedJobs();
	}
	
	@Test
	public void shouldSetHungStatusOnCurrentJob() {
		
		JobRuntimeData hungJob = new JobRuntimeData();
		hungJob.setJobId("1000");
		hungJob.setKey(ExecutionJobKey.STATUS.getMessage());
		hungJob.setValue(ExecutionJobValue.SUBMITTING.getMessage());
		List<JobRuntimeData> hungJobs = new ArrayList<JobRuntimeData>();
		hungJobs.add(hungJob);
		
		when(executionJobRepository.getUncompletedJobs()).thenReturn(hungJobs);
		when(rSGEJobRunner.getDriver()).thenReturn(sCXMLDriver);
		
		Job currentJob = new Job();
		currentJob.setJobId("1000");
		currentJob.setClientRequestStatus(ExecutionJobValue.PENDING.getMessage());
		when(rSGEJobRunner.getJob()).thenReturn(currentJob);
		
		stateHanlder.doProcessing();
		
		assertThat(currentJob.getClientRequestStatus(), is(equalTo(ExecutionJobValue.SUBMITTING.getMessage())));

	}
	
	@Test
	public void shouldFireNextStateForPendingHungJob() {
		
		JobRuntimeData hungJob = new JobRuntimeData();
		hungJob.setJobId("1000");
		hungJob.setKey(ExecutionJobKey.STATUS.getMessage());
		hungJob.setValue(ExecutionJobValue.PENDING.getMessage());
		List<JobRuntimeData> hungJobs = new ArrayList<JobRuntimeData>();
		hungJobs.add(hungJob);
		
		when(executionJobRepository.getUncompletedJobs()).thenReturn(hungJobs);
		when(rSGEJobRunner.getDriver()).thenReturn(sCXMLDriver);
		
		Job currentJob = new Job();
		currentJob.setJobId("1000");
		currentJob.setClientRequestStatus(ExecutionJobValue.PENDING.getMessage());
		when(rSGEJobRunner.getJob()).thenReturn(currentJob);
		
		stateHanlder.doProcessing();
		
		verify(rSGEJobRunner.getDriver()).fireEvent(JobRunnerSates.TASK_PREPARING.getTriggeringEvent());
	}
	
	@Test
	public void shouldFireNextStateForPreparingHungJob() {
		
		JobRuntimeData hungJob = new JobRuntimeData();
		hungJob.setJobId("1000");
		hungJob.setKey(ExecutionJobKey.STATUS.getMessage());
		hungJob.setValue(ExecutionJobValue.PREPARING.getMessage());
		List<JobRuntimeData> hungJobs = new ArrayList<JobRuntimeData>();
		hungJobs.add(hungJob);
		
		when(executionJobRepository.getUncompletedJobs()).thenReturn(hungJobs);
		when(rSGEJobRunner.getDriver()).thenReturn(sCXMLDriver);
		
		Job currentJob = new Job();
		currentJob.setJobId("1000");
		currentJob.setClientRequestStatus(ExecutionJobValue.PENDING.getMessage());
		when(rSGEJobRunner.getJob()).thenReturn(currentJob);
		
		stateHanlder.doProcessing();
		
		verify(rSGEJobRunner.getDriver()).fireEvent(JobRunnerSates.TASK_SUBMITTING.getTriggeringEvent());
	}
	
	@Test
	public void shouldFireNextStateForSubmittingHungJob() {
		
		JobRuntimeData hungJob = new JobRuntimeData();
		hungJob.setJobId("1000");
		hungJob.setKey(ExecutionJobKey.STATUS.getMessage());
		hungJob.setValue(ExecutionJobValue.SUBMITTING.getMessage());
		List<JobRuntimeData> hungJobs = new ArrayList<JobRuntimeData>();
		hungJobs.add(hungJob);
		
		when(executionJobRepository.getUncompletedJobs()).thenReturn(hungJobs);
		when(rSGEJobRunner.getDriver()).thenReturn(sCXMLDriver);
		
		Job currentJob = new Job();
		currentJob.setJobId("1000");
		currentJob.setClientRequestStatus(ExecutionJobValue.PENDING.getMessage());
		when(rSGEJobRunner.getJob()).thenReturn(currentJob);
		
		stateHanlder.doProcessing();
		
		verify(rSGEJobRunner.getDriver()).fireEvent(JobRunnerSates.TASK_PROCESSING.getTriggeringEvent());
	}
	
	@Test
	public void shouldFireNextStateForProcessingHungJob() {
		
		JobRuntimeData hungJob = new JobRuntimeData();
		hungJob.setJobId("1000");
		hungJob.setKey(ExecutionJobKey.STATUS.getMessage());
		hungJob.setValue(ExecutionJobValue.PROCESSING.getMessage());
		List<JobRuntimeData> hungJobs = new ArrayList<JobRuntimeData>();
		hungJobs.add(hungJob);
		
		when(executionJobRepository.getUncompletedJobs()).thenReturn(hungJobs);
		when(rSGEJobRunner.getDriver()).thenReturn(sCXMLDriver);
		
		Job currentJob = new Job();
		currentJob.setJobId("1000");
		currentJob.setClientRequestStatus(ExecutionJobValue.PENDING.getMessage());
		when(rSGEJobRunner.getJob()).thenReturn(currentJob);
		
		stateHanlder.doProcessing();
		
		verify(rSGEJobRunner.getDriver()).fireEvent(JobRunnerSates.TASK_RETRIEVING.getTriggeringEvent());
	}
	
	@Test
	public void shouldFireNextStateForRetrievingHungJob() {
		
		JobRuntimeData hungJob = new JobRuntimeData();
		hungJob.setJobId("1000");
		hungJob.setKey(ExecutionJobKey.STATUS.getMessage());
		hungJob.setValue(ExecutionJobValue.RETRIEVING.getMessage());
		List<JobRuntimeData> hungJobs = new ArrayList<JobRuntimeData>();
		hungJobs.add(hungJob);
		
		when(executionJobRepository.getUncompletedJobs()).thenReturn(hungJobs);
		when(rSGEJobRunner.getDriver()).thenReturn(sCXMLDriver);
		
		Job currentJob = new Job();
		currentJob.setJobId("1000");
		currentJob.setClientRequestStatus(ExecutionJobValue.PENDING.getMessage());
		when(rSGEJobRunner.getJob()).thenReturn(currentJob);
		
		stateHanlder.doProcessing();
		
		verify(rSGEJobRunner.getDriver()).fireEvent(JobRunnerSates.TASK_POSTPROCESSING.getTriggeringEvent());
	}
	
	@Test
	public void shouldFireNextStateForPostprocessingHungJob() {
		
		JobRuntimeData hungJob = new JobRuntimeData();
		hungJob.setJobId("1000");
		hungJob.setKey(ExecutionJobKey.STATUS.getMessage());
		hungJob.setValue(ExecutionJobValue.POST_PROCESSING.getMessage());
		List<JobRuntimeData> hungJobs = new ArrayList<JobRuntimeData>();
		hungJobs.add(hungJob);
		
		when(executionJobRepository.getUncompletedJobs()).thenReturn(hungJobs);
		when(rSGEJobRunner.getDriver()).thenReturn(sCXMLDriver);
		
		Job currentJob = new Job();
		currentJob.setJobId("1000");
		currentJob.setClientRequestStatus(ExecutionJobValue.PENDING.getMessage());
		when(rSGEJobRunner.getJob()).thenReturn(currentJob);
		
		stateHanlder.doProcessing();
		
		verify(rSGEJobRunner.getDriver()).fireEvent(JobRunnerSates.TASK_FINISHED.getTriggeringEvent());
	}

}
