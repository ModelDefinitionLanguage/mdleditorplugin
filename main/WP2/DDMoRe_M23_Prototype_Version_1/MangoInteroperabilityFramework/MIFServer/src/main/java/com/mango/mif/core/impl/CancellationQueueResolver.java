/* File			:  CancellationQueueResolver.java
 * Project		:  MIFServer
 * Created on	:  Jul 10, 2012
 */
package com.mango.mif.core.impl;

import com.mango.mif.connector.Connector;
import com.mango.mif.connector.domain.Job;
import com.mango.mif.managers.JobManagementService;

/**
 * @version $Revision: $ as of $Date: $
 * <p>SVN Entry : $HeadURL: $
 * <p>SVN ID    : $Id: $
 * <p>Last edited by : $Author: $
 * 
 * Resolves a cancellation queue based on the job id and connectors registry
 */
public class CancellationQueueResolver extends BaseQueueResolver {
	
	private JobManagementService jobManagementService;
	
	@Override
	public String resolve(String message) {
		
		Job job = jobManagementService.getJob(message);
		if(job == null) {
			LOG.warn("Received cancellation request for a job that has not yet been scheduled.");
			return "";
		}
		Connector connector = connectorsRegistry.getConnector(job.getExecutionRequest().getType());
		return connector.getCancellationQueue();
	}

	public JobManagementService getJobManagementService() {
		return jobManagementService;
	}

	public void setJobManagementService(JobManagementService jobManagementService) {
		this.jobManagementService = jobManagementService;
	}	
}
