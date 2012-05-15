/* File			:  RJobRunner.java
 * Project		:  MangoInteroperabilityFramework
 * Created on	:  Mar 13, 2012
 */
package com.mango.mif.rconnector.runner.impl;

import org.apache.log4j.Logger;

import com.mango.mif.connector.runner.domain.JobMessage;
import com.mango.mif.connector.runner.impl.SCXMLJobRunner;

/**
 * R Job Runner which will mock the R behaviour
 * Needs to be replaced with the actual R Job Runner. 
 * 
 * @version $Revision: $ as of $Date: $
 * <p>SVN Entry : $HeadURL: $
 * <p>SVN ID    : $Id: $
 * <p>Last edited by : $Author: $
 */
public class RJobRunner extends SCXMLJobRunner {
    /**
     * Logger
     */
    public static Logger LOG = Logger.getLogger(RJobRunner.class);

    @Override
    public void cancelProcessing() {
    }

    @Override
    public void startProcessing() {
    }

    @Override
    public JobMessage call() throws Exception {
        return new JobMessage();
    }

}
