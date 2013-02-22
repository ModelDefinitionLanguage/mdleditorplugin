/* File			:  JobFileResolver.java
 * Project		:  MIFServer
 * Created on	:  11 Sep 2012
 */
package com.mango.mif.utils;

import com.mango.mif.connector.domain.Job;

/**
 * A file resolver that takes a Job and returns a path to a file specific to a given job
 * 
 * @version $Revision: $ as of $Date: $
 * <p>SVN Entry : $HeadURL: $
 * <p>SVN ID    : $Id: $
 * <p>Last edited by : $Author: $
 */
public interface JobFileResolver {

    /**
     * Resolves to a path
     * @param job
     * @return
     */
    String getPath(Job job);
}
