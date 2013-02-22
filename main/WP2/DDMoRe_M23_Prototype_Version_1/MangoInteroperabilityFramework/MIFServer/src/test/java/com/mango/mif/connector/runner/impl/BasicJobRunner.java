/* File			:  BasicJobRunner.java
 * Project		:  MangoInteroperabilityFramework
 * Created on	:  21 Mar 2012
 */
package com.mango.mif.connector.runner.impl;

import com.mango.mif.connector.runner.domain.JobMessage;

/**
 * Job Runner implementation that does no processing.
 * 
 * @version $Revision: $ as of $Date: $
 * <p>SVN Entry : $HeadURL: $
 * <p>SVN ID    : $Id: $
 * <p>Last edited by : $Author: $
 */
public class BasicJobRunner extends AbstractJobRunner {

    @Override
    public void cancelProcessing() {
        //do nothing
    }

    @Override
    public void startProcessing() {
        //do nothing
    }

    @Override
    public JobMessage call() throws Exception {
        return new JobMessage();
    }

}
