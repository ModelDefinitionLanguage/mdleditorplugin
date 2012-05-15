/* File			:  MockRExec.java
 * Project		:  MangoInteroperabilityFramework
 * Created on	:  20 Mar 2012
 */
package com.mango.mif.rconnector.runner.impl;

import org.apache.log4j.Logger;

/**
 * A class representing integration with R.
 * 
 * @version $Revision: $ as of $Date: $
 * <p>SVN Entry : $HeadURL: $
 * <p>SVN ID    : $Id: $
 * <p>Last edited by : $Author: $
 */
public class MockRExec {
    /**
     * Logger
     */
    static Logger LOG = Logger.getLogger(MockRExec.class);
    /**
     * fixed result of mock R processing
     */
    public final static String MOCK_RESULT = "R_RESULT";
    private String script;
    private String result;
    /**
     * Constructor
     * 
     * @param script a script that would be executed
     */
    public MockRExec(String script) {
        this.script = script;
    }
    
    /**
     * Mocks execution of the script
     */
    public void executeRScript() {
        LOG.info("Executing " + script + " using R.");
    }
    
    /**
     * mocks waiting for R processing to finish, sets fixed result to 'AN_R_RESULT' String 
     */
    public void waitForResults() {
        int i = 1;
        while(i<10) {
            try {
                Thread.sleep(1000);
            } catch(Exception e) {
                LOG.error(e);
            }
            i++;
        }
        result = MOCK_RESULT;
    }
    
    /**
     * 
     * @return result
     */
    public String getResult() {
        return result;
    }
}
