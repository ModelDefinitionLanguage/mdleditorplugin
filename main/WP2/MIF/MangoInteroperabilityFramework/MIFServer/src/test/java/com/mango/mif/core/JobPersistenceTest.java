package com.mango.mif.core;


import static org.hamcrest.CoreMatchers.equalTo;
import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertThat;

import java.util.UUID;

import javax.annotation.Resource;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import com.mango.mif.connector.dispatcher.dao.JobRepository;
import com.mango.mif.connector.dispatcher.domain.Job;
import com.mango.mif.exception.MIFException;
import com.mango.mif.utils.ExecutionUtilsTest;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = {
		"/spring/test-hibernate-context.xml",
		"/spring/test-Springcontext.xml"
	 })
public class JobPersistenceTest {
	
	@Resource(name = "jobRepository")
	private JobRepository executionRequestDao;

	@Test
	public void saveAndRetrieveProfileAndWorkFlowInfo() throws MIFException {
		UUID randomUUID = UUID.randomUUID();
		Job job = new Job();
		String clientReqMessage = ExecutionUtilsTest.buildMessage(randomUUID);
		job.setExecutionRequestMsg(clientReqMessage);
		job.setJobId(randomUUID.toString());
		Job savedJob = executionRequestDao.save(job);
		
		assertThat("Job ID", savedJob.getJobId(), is(equalTo(randomUUID.toString())));
		assertNotNull(savedJob.getExecutionRequest());
		assertThat("Message Must be equal ", savedJob.getExecutionRequestMsg(), is(equalTo(clientReqMessage)));
		assertNotNull(executionRequestDao.getJob(randomUUID.toString()));
	}

}
