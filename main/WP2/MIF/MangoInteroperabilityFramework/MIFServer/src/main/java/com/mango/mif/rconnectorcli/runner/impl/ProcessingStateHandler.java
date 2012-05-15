/* File			:  ProcessingStateHandler.java
 * Project		:  MangoInteroperabilityFramework
 * Created on	:  20 Mar 2012
 */
package com.mango.mif.rconnectorcli.runner.impl;

import com.mango.mif.rconnectorcli.internal.RScriptExec;
import com.mango.mif.rconnectorcli.internal.RScriptExecException;

/**
 * 
 * Processing state handler
 * 
 * @version $Revision: $ as of $Date: $
 * <p>SVN Entry : $HeadURL: $
 * <p>SVN ID    : $Id: $
 * <p>Last edited by : $Author: $
 */
public class ProcessingStateHandler extends RScriptStateHandler {

    /**
     * Default constructor
     * @param state
     * @param exitEvent
     */
    public ProcessingStateHandler(String state, String exitEvent) {
        super(state, exitEvent);
    }

    @Override
    protected void doProcessing() {
        RScriptExec rExec = jobRunner.getExec();
        try {
            rExec.waitFor();
        } catch (RScriptExecException e) {
            LOG.error(e);
            fail();
        }
    }
    
}