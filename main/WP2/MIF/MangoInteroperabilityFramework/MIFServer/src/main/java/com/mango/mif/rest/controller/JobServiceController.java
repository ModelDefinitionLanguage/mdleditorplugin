/* File			:  JobServiceController.java
 * Project		:  MangoInteroperabilityFramework
 * Created on	:  Mar 30, 2012
 */
package com.mango.mif.rest.controller;

import java.util.UUID;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import com.mango.mif.connector.dispatcher.dao.JobRepository;
import com.mango.mif.connector.dispatcher.domain.JobStatus;
import com.mango.mif.connector.dispatcher.impl.Dispatcher;
import com.mango.mif.domain.ExecutionResponse;


/**
 * REST API for the exposing the status of the JOB
 * @version $Revision: $ as of $Date: $
 * <p>SVN Entry : $HeadURL: $
 * <p>SVN ID    : $Id: $
 * <p>Last edited by : $Author: $
 */
@Controller
public class JobServiceController {
	final static Logger LOG = Logger.getLogger(JobServiceController.class);
	
	@Autowired
	@Qualifier("jobRepository")
	JobRepository jobRepository;
	

	/**
	 * Gets the job status.
	 * Currently it uses the {@link Dispatcher}  JobStatusRegistry for determining the status of the Job
	 * Later when persistence is implemented , It will use the appropriate service to to the JOb status from the DB. 

	 * @param jobId the job id
	 * @return the job status
	 */
	@RequestMapping(value = "/jobs/{jobId}")
	public ModelAndView getJobStatus(@PathVariable String jobId) {
		LOG.info("Processing for request " + jobId);
		ExecutionResponse executionResponse = new ExecutionResponse();
		executionResponse.setRequestId(UUID.fromString(jobId));
	
		//We must check if the job has been persisted yet because this check will commence before the dispatcher has consumed and persisted the
		//job sent to it!  If the job cannot be found, set status to JOB_NOT_YET_CONSUMED
		
		if (null == jobRepository.getJob(jobId)){
			executionResponse.setStatus(JobStatus.JOB_NOT_YET_CONSUMED.name());
		}else{
			executionResponse.setStatus(jobRepository.getJob(jobId).getClientRequestStatus());
		}

		LOG.info("Status of the Job " + jobId  + " is" + executionResponse.getStatus());
		ModelAndView mav = new ModelAndView("executionResponse", BindingResult.MODEL_KEY_PREFIX + "job",executionResponse );
		return mav;
	}
 
}
