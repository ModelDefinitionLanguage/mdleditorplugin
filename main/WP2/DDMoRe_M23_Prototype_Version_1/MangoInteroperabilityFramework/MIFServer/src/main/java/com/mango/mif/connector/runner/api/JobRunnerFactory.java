/* File			:  JobRunnerFactory.java
 * Project		:  MangoInteroperabilityFramework
 * Created on	:  13 Mar 2012
 */
package com.mango.mif.connector.runner.api;

import com.mango.mif.connector.domain.Job;

/**
 * Classes implementing the interface are responsible for creating JobRunners
 * 
 * @version $Revision: $ as of $Date: $
 * <p>SVN Entry : $HeadURL: $
 * <p>SVN ID    : $Id: $
 * <p>Last edited by : $Author: $
 * 
 */
public interface JobRunnerFactory {
    /**
     * Creates a job runner for given job
     * @param job
     * @return
     */
    JobRunner createJobRunner(Job job);
}
