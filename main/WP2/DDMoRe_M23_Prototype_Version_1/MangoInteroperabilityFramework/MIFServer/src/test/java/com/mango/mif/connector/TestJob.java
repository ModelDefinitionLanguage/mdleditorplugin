/* File			:  TestJob.java
 * Project		:  MangoInteroperabilityFramework-trunk
 * Created on	:  5 Apr 2012
 */
package com.mango.mif.connector;

import com.mango.mif.connector.domain.Job;


/**
 * 
 * A Job class used in tests that doesn't parse the request message
 * 
 * @version $Revision: $ as of $Date: $
 * <p>SVN Entry : $HeadURL: $
 * <p>SVN ID    : $Id: $
 * <p>Last edited by : $Author: $
 */
public class TestJob extends Job {
    @Override
    public void setExecutionRequestMsg(String executionRequestMsg) {
        this.executionRequestMsg = executionRequestMsg;
    }
}
