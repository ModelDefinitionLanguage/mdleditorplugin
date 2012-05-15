/* File			:  SubmittingStateHandler.java
 * Project		:  MangoInteroperabilityFramework
 * Created on	:  20 Mar 2012
 */
package com.mango.mif.rconnectorcli.runner.impl;


import com.mango.mif.core.api.ResourceComponent;
import com.mango.mif.rconnectorcli.internal.RScriptExec;
import com.mango.mif.rconnectorcli.internal.RScriptExecException;

/**
 * Submitting state handler
 * 
 * @version $Revision: $ as of $Date: $
 * <p>SVN Entry : $HeadURL: $
 * <p>SVN ID    : $Id: $
 * <p>Last edited by : $Author: $
 */
public class SubmittingStateHandler extends RScriptStateHandler {

    /**
     * Resource component
     */
    private ResourceComponent resourceComponent;
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
        RScriptExec rExec = jobRunner.getExec();
        rExec.setWorkingDirectory(jobRunner.getWorkspaceManager().getWorkingDirectory());
        try {
            rExec.submit();
        } catch (RScriptExecException e) {
            LOG.error("RScriptExecException", e);
            fail();
        }
    }
    
    public void setResourceComponent(ResourceComponent resourceComponent) {
        this.resourceComponent = resourceComponent;
    }
    
    public ResourceComponent getResourceComponent() {
        return resourceComponent;
    }
}