/* File			:  JobStatusMonitor.java
 * Project		:  MIFClient
 * Created on	:  29 May 2012
 */
package com.mango.mif.client.cli;

import java.util.UUID;

import javax.xml.bind.JAXBException;

import org.apache.log4j.Logger;

import com.mango.mif.client.api.JobService;
import com.mango.mif.domain.ExecutionResponse;
import com.mango.mif.utils.JAXBUtils;
import com.mango.mif.utils.jaxb.ExecutionJaxbUtils;

/**
 * 
 * Responsible for polling REST service for job update
 * @version $Revision: $ as of $Date: $
 * <p>SVN Entry : $HeadURL: $
 * <p>SVN ID    : $Id: $
 * <p>Last edited by : $Author: $
 */
public class JobStatusMonitor implements Runnable {

	/** The Constant LOG. */
	final static Logger LOG = Logger.getLogger(JobStatusMonitor.class);
	/** The job id. */
	private final String jobId;

	/** The job service to handle the job requests. */
	private final JobService jobService;

	/**
	 * Instantiates a new status helper.
	 *
	 * @param jobId the job id
	 * @param latch the latch
	 * @param jobService job service
	 */
	public JobStatusMonitor(String jobId, JobService jobService) {
		this.jobId = jobId;
		this.jobService = jobService;
	}


	/* 
	 * Queries the Rest API of MIF to determine the status of the job submitted
	 * @see java.lang.Runnable#run()
	 */
	@Override
	public void run(){
		ExecutionResponse response = null;
		String result = null;
		do{
				try {
					result = jobService.getJobStatus(UUID.fromString(this.jobId));
					LOG.info(" CURRENT STATUS of the Job with ID " + this.jobId  + "   " + (result));
					response = (ExecutionResponse)JAXBUtils.unmarshall(result, ExecutionJaxbUtils.EXECUTION_RESPONSE_CLASS_ARRAY);
					Thread.sleep(3000);
					
				} catch (InterruptedException e) {
					LOG.error("Thread Interupted Error ", e);
				} catch (JAXBException e) {
					LOG.error("JAXBException ", e);
				}
		} while(response != null); 			
		
	}

}