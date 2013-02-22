/* File			:  JobStatusWaiter.java
 * Project		:  MIFServer
 * Created on	:  Sep 7, 2012
 */
package com.mango.mif.client.remote;

import java.util.concurrent.CountDownLatch;

import org.apache.log4j.Logger;

import com.mango.mif.client.api.rest.JobService;
import com.mango.mif.client.cli.JobStatusMonitor;
import com.mango.mif.domain.JobStatus;


/**
 * @version $Revision: $ as of $Date: $
 * <p>SVN Entry : $HeadURL: $
 * <p>SVN ID    : $Id: $
 * <p>Last edited by : $Author: $
 * 
 * Waits for a job to reach a given state
 */
public class JobStatusWaiter extends JobStatusMonitor {

    final static Logger LOG = Logger.getLogger(JobStatusWaiter.class);
    /**
     * status of interest
     */
    private final JobStatus jobStatus;
    /**
     * used to notify the waiting thread
     */
    private CountDownLatch signal;
    /**
     * 
     * @param jobId
     * @param status
     * @param signal
     * @param jobService
     */
    public JobStatusWaiter(String jobId, JobStatus jobStatus, CountDownLatch signal, JobService jobService) {
        super(jobId, jobService);
        this.signal = signal;
        this.jobStatus = jobStatus;
    }
    
    @Override
    public void run(){
        String result = null;
        do{
                try {
                    result = jobService.getJobStatus(this.jobId);
                    LOG.info(" CURRENT STATUS of the Job with ID " + this.jobId  + "   " + (result));
                    Thread.sleep(3000);
                    
                } catch (InterruptedException e) {
                    LOG.error("Thread Interupted Error ", e);
                } 
        } while(!isDone(result));             
        
    }
    
    @Override
    protected boolean isDone(String response) {
        if(response==null) {
            return false;
        }
        JobStatus currentStatus = JobStatus.valueOf(response);
        if(jobStatus.equals(currentStatus)|| currentStatus.isFinal()) {
            signal.countDown();
            return true;
        }
        return false;
    }
}
