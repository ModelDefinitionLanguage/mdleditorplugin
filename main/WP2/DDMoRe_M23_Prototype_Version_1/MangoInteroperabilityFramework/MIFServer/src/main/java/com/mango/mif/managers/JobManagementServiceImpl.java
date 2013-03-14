package com.mango.mif.managers;

import java.util.List;
import java.util.Map;

import org.apache.commons.lang.StringUtils;
import org.apache.log4j.Logger;
import org.springframework.transaction.annotation.Isolation;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.transaction.support.TransactionSynchronizationManager;

import com.google.common.base.Preconditions;
import com.google.common.collect.Maps;
import com.mango.mif.connector.dao.JobRepository;
import com.mango.mif.connector.domain.Job;
import com.mango.mif.connector.runner.impl.JobInvokerProvider;
import com.mango.mif.connector.runner.impl.JobRunnerState;
import com.mango.mif.core.api.SharedLocationManager;
import com.mango.mif.core.exec.ExecutionException;
import com.mango.mif.domain.DetailedStatus;
import com.mango.mif.exception.MIFException;

/**
 * JobManagementService provides management services over the persistent job entity.
 */
public class JobManagementServiceImpl implements JobManagementService {
    
    private static final Logger LOG = Logger.getLogger(JobManagementServiceImpl.class);
    
    private JobRepository jobRepository;
   
    private SharedLocationManager sharedLocationManager;
    private String mifServiceAccountUserName;
    private String mifServiceAcccountPassword;
    private JobInvokerProvider jobInvokerProvider;
    private boolean runInRequestDirectory = false; 
    
    /**
     * {@inheritDoc}.
     */
    public Job createNewJob() {
        
        Preconditions.checkNotNull(mifServiceAccountUserName,"MIF service account user name not set");
        Preconditions.checkNotNull(mifServiceAcccountPassword,"MIF service account user password not set");
        Preconditions.checkNotNull(sharedLocationManager,"Shared location manager not set");
        
        Job job = new Job();
        job.setUserName(getMifServiceAccountUserName());
        job.setPassword(getMifServiceAcccountPassword());
        job.setSharedLocationManager(getSharedLocationManager());
        job.setRunInRequestDirectory(runInRequestDirectory);
        setJobInvoker(job);
        
        return job;
    }
    
    /**
     * {@inheritDoc}.
     */
    @Transactional(isolation=Isolation.READ_COMMITTED)
    public Job saveJob(Job job) {
        
        LOG.debug("Save job: " + job.toString());
        
        logTxStatus();
        
        Job saved = jobRepository.save(job);        
        initJob(saved);
        
        return saved;
    }
    
    /**
     * {@inheritDoc}.
     */
    @Transactional(readOnly=true)
    public List<Job> getUncompletedDispatcherJobs() {
        
        List<Job> jobs = jobRepository.getUncompletedDispatcherJobs();        
        for(Job job: jobs) {
            job.setSharedLocationManager(sharedLocationManager);
            initJob(job);
        }
        return jobs;
    }
    
    /**
     * {@inheritDoc}.
     */
    @Transactional(readOnly=true)
    public List<Job> getUncompletedDispatcherJobs(String connectorId) {
        
        List<Job> jobs = jobRepository.getUncompletedDispatcherJobs(connectorId);
        for(Job job: jobs) {
            initJob(job);
        }
        return jobs;
    }
    
    /**
     * {@inheritDoc}.
     */
    @Transactional(readOnly=true)
    public List<Job> getUncompletedJobRunnerJobs() {
        
        List<Job> jobs = jobRepository.getUncompletedJobRunnerJobs();
        for (Job job: jobs) {
            initJob(job);
        }
        return jobs;
    }
    
    /**
     * {@inheritDoc}.
     */
    @Transactional(readOnly=true)    
    public Job getJob(String jobId) {
        
        Job job = jobRepository.getJob(jobId);
        if (job != null) {
            initJob(job);
        }
        return job;
    }       
    
    /**
     * {@inheritDoc}.
     */
    @Transactional(readOnly=true)
    public DetailedStatus getDetailedStatus(String jobId) throws MIFException {
        
        Job job = jobRepository.getJob(jobId);
        if(job==null) {
            return null;
        }
        return job.getDetailedStatus();
    }
    
    /**
     * {@inheritDoc}.
     */    
    @Transactional(readOnly=true)
    public Map<String,String> getSummaryMessagesForRunningJobs() {
        
        List<Job> jobs = jobRepository.getUncompletedDispatcherJobs();
        
        Map<String,String> result = Maps.newHashMap();
        int failureCount = 0;
        for (Job job: jobs) {
            try {
                DetailedStatus detailedStatus = job.getDetailedStatus();
                if(hasSummaryMessage(detailedStatus)) {
                	result.put(job.getJobId(), detailedStatus.getSummary());
                }
            } catch (MIFException mife) {
                // Each job might have some failure  which should not affect
                // retrieval of summary messages for other jobs.
                LOG.warn("Failed in getting detailed status for the Job with jobId - " + job.getJobId(), mife);
                failureCount++;
            }
        }
        if(failureCount > 0)
            LOG.warn("Total no. of failed summary message retrievals = " + failureCount);
        return result;
    }
    
    /**
     * Check if the detailed status has a non empty summary in it
     */
    private boolean hasSummaryMessage(DetailedStatus detailedStatus) {
    	return (detailedStatus != null && StringUtils.isNotEmpty(detailedStatus.getSummary()));
    }
    
    /**
     * {@inheritDoc}.
     */
    @Transactional(isolation=Isolation.READ_COMMITTED)
    public Job saveJobRunnerState(String jobId, JobRunnerState internalJobState) {
                
        logTxStatus();
        
        Job job = jobRepository.getJob(jobId);
        
        job.addToDataMap(JobRunnerState.STATUS, internalJobState.getStateName());          
        
        Job saved = jobRepository.save(job);
        initJob(saved);      
         
        LOG.info("Persisted internal JOB RUNNER STATE as " + internalJobState.getStateName() 
            + " on job : " + saved.toString() 
            + " with version " + saved.getVersion());
        
        return saved;
    }
    
    /**
     * {@inheritDoc}.
     */
    @Transactional(isolation=Isolation.READ_COMMITTED)       
    public Job saveJobStatus(String jobId, String status) {
        
        logTxStatus();
        
        Job job = jobRepository.getJob(jobId);
        
        job.setClientRequestStatus(status);
        
        Job saved = jobRepository.save(job);
        initJob(saved);
        
        LOG.info("Persisted job status as " + status 
            + " on job : " + saved.toString() 
            + " with version " + saved.getVersion());
        
        return saved;
    }
    
    /**
     * {@inheritDoc}.
     */
    @Transactional(readOnly=true)
    public String[] getAcknowledgedJobsToDate(Long uptoTime) {
        
        return jobRepository.getAcknowledgedJobsToDate(uptoTime);     
    }
    
    @Override
    @Transactional
    public Job saveJobData(Job job) {
       
        Job updatedJob = this.getJob(job.getJobId());
        updatedJob.setData(job.getData());
        return this.saveJob(updatedJob);
    }
       
    public JobRepository getJobRepository() {
        return jobRepository;
    }

    public void setJobRepository(JobRepository jobRepository) {
        this.jobRepository = jobRepository;
    }
    
    public SharedLocationManager getSharedLocationManager() {
        return sharedLocationManager;
    }
    
    public void setSharedLocationManager(SharedLocationManager sharedLocationManager) {
        this.sharedLocationManager = sharedLocationManager;
    }
    
    public String getMifServiceAccountUserName() {
        return mifServiceAccountUserName;
    }
    
    public void setMifServiceAccountUserName(String mifServiceAccountUserName) {
        this.mifServiceAccountUserName = mifServiceAccountUserName;
    }
    
    public String getMifServiceAcccountPassword() {
        return mifServiceAcccountPassword;
    }
    
    public void setMifServiceAcccountPassword(String mifServiceAcccountPassword) {
        this.mifServiceAcccountPassword = mifServiceAcccountPassword;
    }
    
    public JobInvokerProvider getJobInvokerProvider() {
        return jobInvokerProvider;
    }
    
    public void setJobInvokerProvider(JobInvokerProvider jobInvokerProvider) {
        this.jobInvokerProvider = jobInvokerProvider;
    }
    
    private void initJob(Job job) {
        if (job != null) {
            job.setSharedLocationManager(getSharedLocationManager());
            job.setRunInRequestDirectory(runInRequestDirectory);
            setJobInvoker(job);
        }
    }
    
    private void setJobInvoker(Job job) {
        try {
            getJobInvokerProvider().createInvoker(job);
        } catch (ExecutionException e) {
            LOG.error(String.format("Could not create invoker for job(%s)", job.getJobId()), e);
        }
    }
        
    private void logTxStatus() {
        if (TransactionSynchronizationManager.isActualTransactionActive()) {
            LOG.warn("Tx active");
        } else {
            LOG.warn("No tx Active");
        }
    }
    
    public void setRunInRequestDirectory(boolean runInRequestDirectory) {
        this.runInRequestDirectory = runInRequestDirectory;
    }
    
    public boolean isRunInRequestDirectory() {
        return runInRequestDirectory;
    }
}
