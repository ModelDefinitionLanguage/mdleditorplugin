/* File			:  JobServiceImpl.java
 * Project		:  MangoInteroperabilityFramework
 * Created on	:  Apr 13, 2012
 */
package com.mango.mif.client.impl;

import java.util.UUID;

import org.apache.log4j.Logger;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;

import com.google.common.base.Preconditions;
import com.google.common.base.Strings;
import com.mango.mif.client.api.JobService;
import com.mango.mif.client.err.TaskExecutorExceptions;

/**
 * Class Responsible for managing the REST calls to MIF. 
 * @version $Revision: $ as of $Date: $
 * <p>SVN Entry : $HeadURL: $
 * <p>SVN ID    : $Id: $
 * <p>Last edited by : $Author: $
 */
@Component
public class JobServiceImpl implements JobService {
	/** The Constant LOG. */
	final static Logger LOG = Logger.getLogger(JobServiceImpl.class);

	/** The job status url. */
	private static String JOB_STATUS_URL = "/mif/jobs/{job}";
	private String baseServerUrl ;
	
	
	/** Gets the Job status for the given Job Id.
	 * @see com.mango.mif.client.api.JobService#getJobStatus(java.util.UUID)
	 */
	@Override
	public String getJobStatus(final UUID uuid) {
		Preconditions.checkNotNull(uuid);
		if(Strings.isNullOrEmpty(getBaseServerUrl())) {
			throw new RuntimeException(TaskExecutorExceptions.SERVER_BASE_URL_NOT_PRESENT.getMessage());
		}
		return new RestTemplate().getForObject(
                getBaseServerUrl() + JOB_STATUS_URL,
                String.class, uuid.toString());
	}


	/**
	 * Gets the base server url.
	 *
	 * @return the base server url
	 */
	public String getBaseServerUrl() {
		return baseServerUrl;
	}


	/**
	 * Sets the base server url.
	 *
	 * @param baseServerUrl the new base server url
	 */
	public void setBaseServerUrl(String baseServerUrl) {
		this.baseServerUrl = baseServerUrl;
	}


	

}
