package com.mango.mif.core;


import static org.hamcrest.CoreMatchers.equalTo;
import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertNull;
import static org.junit.Assert.assertThat;

import java.util.Map;
import java.util.UUID;

import javax.annotation.Resource;
import javax.xml.bind.JAXBException;

import org.junit.Before;
import org.junit.Ignore;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.transaction.annotation.Transactional;

import com.mango.mif.connector.domain.Job;
import com.mango.mif.connector.runner.impl.JobInvokerProvider;
import com.mango.mif.core.api.SharedLocationManager;
import com.mango.mif.core.exec.Invoker;
import com.mango.mif.domain.DetailedStatus;
import com.mango.mif.domain.ExecutionRequestBuilder;
import com.mango.mif.domain.ExecutionType;
import com.mango.mif.domain.JobStatus;
import com.mango.mif.exception.MIFException;
import com.mango.mif.managers.JobManagementServiceImpl;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = {
		"/com/mango/mif/core/test-hibernate-context.xml",
		"/com/mango/mif/core/test-Springcontext.xml"
	 })
@Ignore("Test needs to be reworked for testing the repository instead")
public class JobPersistenceTest {
	
	// Testing the impl class here, don't want to expose the shared location manager etc.. on the interface
	@Resource(name = "jobManagementService")
	private JobManagementServiceImpl jobManagementService;
	
	@Mock SharedLocationManager sharedLocationManager;
	
	@Mock JobInvokerProvider jobInvokerProvider;
	/**
	 * Invoker
	 */
	@Mock Invoker invoker;

	@Before
	public void setUp() {
		MockitoAnnotations.initMocks(this);
		jobManagementService.setSharedLocationManager(sharedLocationManager);
		jobManagementService.setJobInvokerProvider(jobInvokerProvider);
		jobManagementService.setMifServiceAcccountPassword("MOCK_PASSWORD");
		jobManagementService.setMifServiceAccountUserName("MOCK_USER");
	}
	
	@Test
	@Transactional
	public void shouldSaveAndRetrieveJob() throws MIFException, JAXBException {
        Job job = createJob();
        Job savedJob = jobManagementService.saveJob(job);
        assertNotNull(savedJob.getSharedLocationManager());
        assertThat("Job ID", savedJob.getJobId(), is(equalTo(job.getJobId())));
        assertNotNull(savedJob.getExecutionRequest());
        assertThat("Message Must be equal ", savedJob.getExecutionRequestMsg(), is(equalTo(job.getExecutionRequestMsg())));
        assertNotNull(jobManagementService.getJob(job.getJobId()));
        assertEquals("TEST_SUMMARY_MESSAGE_OF_JOB_"+savedJob.getJobId(),job.getDetailedStatus().getSummary());
        assertNull(savedJob.getInvoker());
	}
	
    @Test
    @Transactional
    public void shouldRetrieveDetailedStatusForAGivenJob() throws MIFException, JAXBException {
        Job job = createJob();
        @SuppressWarnings("unused")
		Job savedJob = jobManagementService.saveJob(job);
        DetailedStatus status = jobManagementService.getDetailedStatus(job.getJobId());
        assertNotNull(status);
        assertEquals(job.getJobId(),status.getRequestId());
    }
    
    @Test
    @Transactional
    public void shouldRetrieveStatusMessagesForArunningJobsOnly() throws MIFException, JAXBException {
        Job job = null;
        for(int i=0; i<10;i++) {
            job = createJob();
            job.setClientRequestStatus(JobStatus.RUNNING.toString());
            jobManagementService.saveJob(job);
        }
        for(int i=0; i<10;i++) {
            job = createJob();
            job.setClientRequestStatus(JobStatus.COMPLETED.toString());
            jobManagementService.saveJob(job);
        }
        Map<String,String> statusMessages = jobManagementService.getSummaryMessagesForRunningJobs();
        assertEquals(10,statusMessages.size());
    }
    
    
    /**
     * Creates a test job
     * @return
     * @throws MIFException
     * @throws JAXBException
     */
    private Job createJob() throws MIFException, JAXBException {
        String randomUUID = UUID.randomUUID().toString();
        Job job = jobManagementService.createNewJob();
        String clientReqMessage = new ExecutionRequestBuilder().setRequestId(randomUUID).setExecutionType(ExecutionType.R_Script.name()).getExecutionRequestMsg();
        job.setExecutionRequestMsg(clientReqMessage);
        job.setJobId(randomUUID.toString());
        DetailedStatus detailedStatus = new DetailedStatus();
        detailedStatus.setRequestId(job.getJobId());
        detailedStatus.setSummary("TEST_SUMMARY_MESSAGE_OF_JOB_"+randomUUID);
        job.setDetailedStatusMsg(detailedStatus.asString());
        job.setInvoker(invoker);
        return job;
    }
    
}
