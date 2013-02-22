/* File			:  SCXMLJobRunner.java
 * Project		:  MangoInteroperabilityFramework
 * Created on	:  22 Mar 2012
 */
package com.mango.mif.connector.runner.impl;

import com.mango.mif.connector.runner.api.SCXMLDriver;


/**
 * 
 * A Job Driver that uses Apache SCXML to drive job processing
 * 
 * @version $Revision: $ as of $Date: $
 * <p>SVN Entry : $HeadURL: $
 * <p>SVN ID    : $Id: $
 * <p>Last edited by : $Author: $
 */
public abstract class SCXMLJobRunner extends AbstractJobRunner {
    /**
     * SCXML Driver
     */
    protected SCXMLDriver driver;

    public void setDriver(SCXMLDriver driver) {
        this.driver = driver;
    }

    public SCXMLDriver getDriver() {
        return driver;
    }
}
