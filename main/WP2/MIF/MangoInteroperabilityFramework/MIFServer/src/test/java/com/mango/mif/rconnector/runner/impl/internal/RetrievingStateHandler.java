/* File			:  RetrievingStateHandler.java
 * Project		:  MangoInteroperabilityFramework
 * Created on	:  20 Mar 2012
 */
package com.mango.mif.rconnector.runner.impl.internal;

import org.apache.log4j.Logger;

import com.mango.mif.rconnector.runner.impl.MockRExec;

/**
 * Retrieving state handler
 * 
 * @version $Revision: $ as of $Date: $
 * <p>SVN Entry : $HeadURL: $
 * <p>SVN ID    : $Id: $
 * <p>Last edited by : $Author: $
 */
public class RetrievingStateHandler extends MockRStateHandler {
    /**
     * Logger
     */
    private static Logger LOG = Logger.getLogger(RetrievingStateHandler.class);
    
    /**
     * Default Constructor
     * 
     * @param state
     * @param exitEvent
     */
    public RetrievingStateHandler(String state, String exitEvent) {
        super(state, exitEvent);
    }

    @Override
    protected void doProcessing() {
        MockRExec rExec = rJobRunner.getRExec();
        
       LOG.info("Result of R processing is: " + rExec.getResult());
    }
    
}