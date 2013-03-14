/* File			:  DataPurgeService.java
 * Project		:  MIFServer
 * Created on	:  21 Sep 2012
 */
package com.mango.mif.managers;

import java.util.Date;
import java.util.List;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Required;
import org.springframework.scheduling.annotation.Scheduled;

import com.mango.mif.connector.domain.Job;

/**
 * @version $Revision: $ as of $Date: $
 * <p>SVN Entry : $HeadURL: $
 * <p>SVN ID    : $Id: $
 * <p>Last edited by : $Author: $
 */
//@Service(DataPurgeService.BEAN_ID)
public class DataPurgeService {

	public static final String BEAN_ID 			= "DataPurgeService";
	public static final String DAILY_OFF_PEAK 	= "0 23 * * *";
	
	private static final String MARKER = "####################";
	private static final Logger LOG = Logger.getLogger(DataPurgeService.class);
	
	/**
	 * The maximum number of days after which the data for finished job will be purged 
	 */
	private Integer maxRetainDays;
	
	private JobManagementService jobManagementService;
	
	@Scheduled(cron = DAILY_OFF_PEAK)
	public void purgeData() {
		logInfo("Starting Job Data Purge");
		
		Date date = new Date();
		//List<Job> jobsToPurge =  jobManagementService.getJobsFinishedBefore(date);
	}

	private void logInfo(String msg) {
		LOG.info(String.format("%s %s %s", MARKER, BEAN_ID, MARKER));
		LOG.info("		" + msg);
	}

	@Required
	public void setMaxRetainDays(Integer maxRetainDays) {
		logInfo(String.format("Keeping job data for a maximum of %s days", maxRetainDays));
		this.maxRetainDays = maxRetainDays;
	}

	public Integer getMaxRetainDays() {
		return maxRetainDays;
	}

	@Required
	public void setJobManagementService(JobManagementService jobManagementService) {
		this.jobManagementService = jobManagementService;
	}

	public JobManagementService getJobManagementService() {
		return jobManagementService;
	}
}
