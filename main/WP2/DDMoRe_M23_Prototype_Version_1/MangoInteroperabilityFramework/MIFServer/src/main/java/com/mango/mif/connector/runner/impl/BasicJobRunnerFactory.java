/* File			:  JobRunnerFactoryImpl.java
 * Project		:  MangoInteroperabilityFramework
 * Created on	:  13 Mar 2012
 */
package com.mango.mif.connector.runner.impl;

import com.mango.mif.connector.domain.Job;
import com.mango.mif.connector.runner.api.JobRunner;
import com.mango.mif.connector.runner.api.JobRunnerFactory;

/**
 * Basic Job Runners Factory
 * 
 * @version $Revision: $ as of $Date: $
 * <p>SVN Entry : $HeadURL: $
 * <p>SVN ID    : $Id: $
 * <p>Last edited by : $Author: $
 * 
 */
public abstract class BasicJobRunnerFactory implements JobRunnerFactory {

    @Override
    public JobRunner createJobRunner(Job job) {
        JobRunner jobRunner = createJobRunner(); 
        jobRunner.setJobId(job.getJobId());
        return jobRunner;
    }

    /**
     * overwritten by spring, returns new instances of spring-managed JobRunners
     */
    protected abstract JobRunner createJobRunner();
}
