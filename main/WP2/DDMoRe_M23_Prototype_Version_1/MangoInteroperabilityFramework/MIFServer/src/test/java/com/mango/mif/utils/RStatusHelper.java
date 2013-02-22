package com.mango.mif.utils;
import java.util.concurrent.CountDownLatch;

import javax.xml.bind.JAXBException;

import org.apache.log4j.Logger;

import com.mango.mif.client.api.rest.JobService;
import com.mango.mif.domain.ExecutionResponse;
import com.mango.mif.domain.JobStatus;
import com.mango.mif.utils.JAXBUtils;
import com.mango.mif.utils.jaxb.ExecutionJaxbUtils;

/**
 * Helper class for the integration test
 * Responsible for handling the request submitted. 
 */
public class RStatusHelper implements Runnable {
	
	/** The Constant LOG. */
	final static Logger LOG = Logger.getLogger(RStatusHelper.class);
	
	/** The job id. */
	private final String jobId;

	/** The latch needs to be passed from the Test */
	private final CountDownLatch latch;

	/** The job service to handle the job requests. */
	private final JobService jobService;

	/**
	 * Instantiates a new r status helper.
	 *
	 * @param jobId the job id
	 * @param latch the latch
	 * @param jobService job service
	 */
	public RStatusHelper(String jobId, CountDownLatch latch, JobService jobService) {
		this.jobId = jobId;
		this.latch = latch;
		this.jobService = jobService;
	}


	/* 
	 * Queries the Rest API of MIF to determine the status of the job submitted
	 * Once the job is completed It moves the decreases the latch count to let know the test 
	 * which is invoking it that it has completed execution.   
	 * @see java.lang.Runnable#run()
	 */
	@Override
	public void run(){
		ExecutionResponse response = null;
		String result = null;
		do{
			try {
				result = jobService.getJobStatus(this.jobId);
				LOG.info(" CURRENT STATUS of the Job with ID " + this.jobId  + "   " + (result));
				response = (ExecutionResponse)JAXBUtils.unmarshall(result, ExecutionJaxbUtils.CONTEXT_CLASSES);
				Thread.sleep(3000);
				
			} catch (InterruptedException e) {
				LOG.error("Thread Interupted Error ", e);
			} catch (JAXBException e) {
				LOG.error("JAXBException ", e);
			}
	} while(isRunning(response)); 			
		LOG.info("Moving the count down of the Latch From"  + latch.getCount());
		latch.countDown();
	}

	private boolean isRunning(ExecutionResponse response) {
		return !(JobStatus.COMPLETED.name().equals(response != null ? response.getStatus() : response));
	}
}
