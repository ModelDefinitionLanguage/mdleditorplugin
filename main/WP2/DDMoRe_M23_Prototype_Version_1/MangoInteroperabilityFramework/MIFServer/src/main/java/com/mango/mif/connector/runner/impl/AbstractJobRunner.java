/* File			:  JobRunnerImpl.java
 * Project		:  MangoInteroperabilityFramework
 * Created on	:  13 Mar 2012
 */
package com.mango.mif.connector.runner.impl;

import com.google.common.base.Preconditions;
import com.mango.mif.connector.runner.api.JobRunner;
import com.mango.mif.core.exec.Invoker;


/**
 * @version $Revision: $ as of $Date: $
 * <p>SVN Entry : $HeadURL: $
 * <p>SVN ID    : $Id: $
 * <p>Last edited by : $Author: $
 * 
 * Basic Job Runner implementation
 */
public abstract class AbstractJobRunner implements JobRunner {
    /**
     * Invoker to be used to invoke all commands associated with this job.
     */
    protected Invoker invoker;
    /**
     * job id
     */
    protected String jobId;

    @Override
    public void setJobId(String jobId) {
        this.jobId = jobId;
    }

    @Override
    public String getJobId() {
        return jobId;
    }
    
    @Override
    public String toString() {
        String str = jobId;
        return str;
    }

    @Override
    public void setInvoker(Invoker invoker) {
        Preconditions.checkNotNull(invoker, "The invoker cannot be null");
        this.invoker = invoker;
    }

    @Override
    public Invoker getInvoker() {
        return invoker;
    }
}
