/* File			:  MockRStateHandler.java
 * Project		:  MangoInteroperabilityFramework
 * Created on	:  20 Mar 2012
 */
package com.mango.mif.rconnector.runner.impl.internal;

import com.mango.mif.connector.runner.impl.AbstractStateHandler;
import com.mango.mif.rconnector.runner.impl.MockRJobRunner;


/**
 * A handler used by Mock R Job Runner that holds rJobRunner reference
 * 
 * @version $Revision: $ as of $Date: $
 * <p>SVN Entry : $HeadURL: $
 * <p>SVN ID    : $Id: $
 * <p>Last edited by : $Author: $
 */
public abstract class MockRStateHandler extends AbstractStateHandler {

    /**
     * Job Runner that this state handler is associated with
     */
    protected MockRJobRunner rJobRunner;
    /**
     * Event that to move to faild state
     */
    public final static String FAILED_EVENT = "task.failed";
    /**
     * Default Constructor
     * @param state
     * @param exitEvent
     */
    public MockRStateHandler(String state, String exitEvent) {
        super(state, exitEvent);
    }
    
    /**
     * Sets R job runner
     * @param rJobRunner
     */
    public void setRJobRunner(MockRJobRunner rJobRunner) {
        this.rJobRunner = rJobRunner;
    }
    
    @Override
    public String failed() {
        return FAILED_EVENT;
    }

}
