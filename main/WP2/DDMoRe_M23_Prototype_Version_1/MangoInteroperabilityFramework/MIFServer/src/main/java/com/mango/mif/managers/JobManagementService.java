package com.mango.mif.managers;

import java.util.List;
import java.util.Map;

import com.mango.mif.connector.domain.Job;
import com.mango.mif.connector.runner.impl.JobRunnerState;
import com.mango.mif.domain.DetailedStatus;
import com.mango.mif.exception.MIFException;

/**
 * JobManagementService provides management services over the persistent job entity. 
 */
public interface JobManagementService {

    /**
     * Creates a job object.
     * @param job is the job to create
     * @return the newly created job
     */
    Job saveJob(Job job);

    /**
     * Save the state of the job runner, see {@link JobRunnerState}.
     * @param jobId is the id of the job
     * @param internalJobState is the internal SCXML job state to save
     */
    Job saveJobRunnerState(String jobId, JobRunnerState internalJobState);
    
    /**
     * Save the status of the job
     * @param jobId is the id of the job
     * @param status is the status of the job
     */
    Job saveJobStatus(String jobId, String status);
    
    /**
     * Get the job given the job id
     * @param jobId is the id of the job
     * @return the job
     */
    Job getJob(String jobId);
    
    /**
     * Get the uncompleted dispatcher jobs for a given connector
     * @param connectorId is the id of the connector
     * @return the list of jobs
     */
    List<Job> getUncompletedDispatcherJobs(String connectorId);
    
    /**
     * Get the uncompleted jobs for all dispatchers.
     * @return the list of jobs
     */
    List<Job> getUncompletedDispatcherJobs();
    
    /**
     * Get the uncompleted job runner jobs.
     * @return the list of uncompleted runner jobs
     */
    List<Job> getUncompletedJobRunnerJobs();
    
    /**
     * Get the summary messages for the running jobs
     * @return a map of jobid:summary messages
     */
    Map<String,String> getSummaryMessagesForRunningJobs();
    
    /**
     * Get the detailed status from a persistent job object
     * @param jobId is the job id
     * @return the XML detailed status
     * @throws MIFException
     */
    DetailedStatus getDetailedStatus(String jobId) throws MIFException;
    
    /**
     * Create a new non-persistent job.
     * @return a job
     */
    Job createNewJob();

    /**
     * Save job data for a given job
     * @param job is the job
     * @return the job data
     */
    Job saveJobData(Job job);
    
    /**
     * Get all the jobs that have been marked as acknowledged.
     */
    String[] getAcknowledgedJobsToDate(Long uptoTime);
    
}