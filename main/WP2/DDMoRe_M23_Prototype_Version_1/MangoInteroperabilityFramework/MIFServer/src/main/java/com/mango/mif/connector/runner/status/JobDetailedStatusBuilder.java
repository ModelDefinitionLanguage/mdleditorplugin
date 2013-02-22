/* File			:  JobDetailedStatusBuilder.java
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
 * Components implementing that interface are responsible for generating a summary message
 * 
 */
public interface JobDetailedStatusBuilder {
    /**
     * sets a job
     * @param job
     * @throws ExecutionException 
     */
    void setJob(Job job) throws ExecutionException;
    /**
     * get a summary message
     * @return
     * @throws ExecutionException 
     */
    DetailedStatus getDetailedStatus() throws ExecutionException;
}
