/* File			:  JobRunner.java
 * Project		:  MangoInteroperabilityFramework
 * Created on	:  13 Mar 2012
 */
package com.mango.mif.connector.runner.api;

import java.util.concurrent.Callable;

import com.mango.mif.connector.runner.domain.JobMessage;
import com.mango.mif.core.exec.Invoker;

/**
 * Classes implementing the interface are responsible for execution of a job
 * 
 * @version $Revision: $ as of $Date: $
 * <p>SVN Entry : $HeadURL: $
 * <p>SVN ID    : $Id: $
 * <p>Last edited by : $Author: $
 * 
 */
public interface JobRunner extends Callable<JobMessage> {
    /**
     * Cancels processing
     */
    void cancelProcessing();
    /**
     * Starts processing.
     */
    void startProcessing();
    /**
     * Sets the job id that the runner should process
     * @param job
     */
    void setJobId(String jobId);
    /**
     * 
     * @return jobId
     */
    String getJobId();
    /**
     * Set the invoker that will be used to execute all commands associated with this job.
     * @param invoker
     */
    void setInvoker(Invoker invoker);
    /**
     * @return the invoker used to execute all commands associated with this job.
     */
    Invoker getInvoker();
}
