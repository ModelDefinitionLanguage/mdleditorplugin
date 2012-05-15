/* File			:  SubmittingStateHandler.java
 * Project		:  MangoInteroperabilityFramework
 * Created on	:  20 Mar 2012
 */
package com.mango.mif.rconnector.runner.impl.internal;

import com.mango.mif.rconnector.runner.impl.MockRExec;

/**
 * Submitting state handler
 * 
 * @version $Revision: $ as of $Date: $
 * <p>SVN Entry : $HeadURL: $
 * <p>SVN ID    : $Id: $
 * <p>Last edited by : $Author: $
 */
public class SubmittingStateHandler extends MockRStateHandler {

    /**
     * Default Constructor
     * @param state
     * @param exitEvent
     */
    public SubmittingStateHandler(String state, String exitEvent) {
        super(state, exitEvent);
    }

    @Override
    protected void doProcessing() {
        MockRExec rExec = rJobRunner.getRExec();
        
        rExec.executeRScript();
    }
    
}