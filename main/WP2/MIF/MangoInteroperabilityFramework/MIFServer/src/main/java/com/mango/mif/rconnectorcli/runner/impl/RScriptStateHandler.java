/* File			:  RScriptStateHandler.java
 * Project		:  MangoInteroperabilityFramework
 * Created on	:  20 Mar 2012
 */
package com.mango.mif.rconnectorcli.runner.impl;

import com.mango.mif.connector.runner.impl.DefaultStateHandler;

/**
 * A handler used by R CLI Job Runner that holds rJobRunner reference
 * 
 * @version $Revision: $ as of $Date: $
 * <p>SVN Entry : $HeadURL: $
 * <p>SVN ID    : $Id: $
 * <p>Last edited by : $Author: $
 */
public class RScriptStateHandler extends DefaultStateHandler {

    /**
     * Job Runner that this state handler is associated with
     */
    protected RCliJobRunner jobRunner;
    /**
     * Constructs a state handler with no exit event
     * @param state
     */
    public RScriptStateHandler(String state) {
        super(state, NULL_EVENT);
    }
    
    /**
     * Default Constructor
     * @param state
     * @param exitEvent
     */
    public RScriptStateHandler(String state, String exitEvent) {
        super(state, exitEvent);
    }
    
    /**
     * Sets job runner
     * @param rJobRunner
     */
    public void setJobRunner(RCliJobRunner jobRunner) {
        this.jobRunner = jobRunner;
    }
}
