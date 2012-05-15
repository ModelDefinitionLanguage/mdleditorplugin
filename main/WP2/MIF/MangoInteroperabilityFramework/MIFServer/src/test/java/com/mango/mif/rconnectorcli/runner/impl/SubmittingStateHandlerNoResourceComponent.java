/* File			:  SubmittingStateHandler.java
 * Project		:  MangoInteroperabilityFramework
 * Created on	:  20 Mar 2012
 */
package com.mango.mif.rconnectorcli.runner.impl;

import com.mango.mif.rconnectorcli.internal.RScriptExec;
import com.mango.mif.rconnectorcli.internal.RScriptExecException;

/**
 * Submitting state handler that does not depend on resource component
 * 
 * @version $Revision: $ as of $Date: $
 * <p>SVN Entry : $HeadURL: $
 * <p>SVN ID    : $Id: $
 * <p>Last edited by : $Author: $
 */
public class SubmittingStateHandlerNoResourceComponent extends RScriptStateHandler {

    /**
     * Default Constructor
     * @param state
     * @param exitEvent
     */
    public SubmittingStateHandlerNoResourceComponent(String state, String exitEvent) {
        super(state, exitEvent);
    }

    @Override
    protected void doProcessing() {
        RScriptExec rExec = jobRunner.getExec();
        try {
            rExec.submit();
        } catch (RScriptExecException e) {
            LOG.error("RScriptExecException", e);
            fail();
        }
    }
}