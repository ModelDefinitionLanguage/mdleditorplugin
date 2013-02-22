/* File			:  GenericJobStatusSummaryMessageBuilder.java
 * Project		:  MIFServer
 * Created on	:  Aug 10, 2012
 */
package com.mango.mif.connector.runner.status;

import com.mango.mif.connector.domain.Job;
import com.mango.mif.core.exec.ExecutionException;
import com.mango.mif.domain.DetailedStatus;


/**
 * @version $Revision: $ as of $Date: $
 * <p>SVN Entry : $HeadURL: $
 * <p>SVN ID    : $Id: $
 * <p>Last edited by : $Author: $
 * 
 * A generic detailed status builder, returning empty string by default
 * 
 */
public class GenericJobDetailedStatusBuilder implements JobDetailedStatusBuilder {
    /**
     * Job
     */
    protected Job job;
    
    @Override
    public void setJob(Job job) throws ExecutionException {
        this.job = job;
    }

    @Override
    public DetailedStatus getDetailedStatus() throws ExecutionException {
        DetailedStatus detailedStatus = new DetailedStatus();
        detailedStatus.setRequestId(job.getJobId());
        return detailedStatus;
    }


}
