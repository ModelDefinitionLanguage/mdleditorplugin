/* File			:  AbstractStateHandler.java
 * Project		:  MangoInteroperabilityFramework
 * Created on	:  20 Mar 2012
 */
package com.mango.mif.connector.runner.impl;


import org.apache.log4j.Logger;

import com.mango.mif.connector.runner.api.SCXMLDriver;
import com.mango.mif.connector.runner.api.StateHandler;

/**
 * Abstract State Handler providing an implementation common to all state handlers
 * @version $Revision: $ as of $Date: $
 * <p>SVN Entry : $HeadURL: $
 * <p>SVN ID    : $Id: $
 * <p>Last edited by : $Author: $
 */
public abstract class AbstractStateHandler implements StateHandler {
    /**
     * Logger
     */
    protected static final Logger LOG = Logger.getLogger(AbstractStateHandler.class);
    /**
     * State that the handler is associated with
     */
    final protected String handledState;
    /**
     * Event that will be sent to driver when the handler finishes its job
     */
    final protected String exitEvent;
    /**
     * Driver that the handler is registered to
     */
    protected SCXMLDriver driver;
    /**
     * Constant indicating that no event is expected to be sent at the end of processing
     */
    public final static String NULL_EVENT = "NONE";
    /**
     * Failure cause
     */
    private Exception exception;
    
    /**
     * Constructs a new state handler for given state and given exit event
     * @param state
     * @param exitEvent - exit event name or <code>NONE</code> if there is no exit event expected
     */
    public AbstractStateHandler(String state, String exitEvent) {
        this.handledState = state;
        this.exitEvent = exitEvent;
    }

    @Override
    public String call() {
        try {
            doProcessing();
            driver.complete(this);
        } catch(Exception e) {
            LOG.error("Error processing state handler.",e);
            exception = e;
            fail();
        }
        return "DONE-" + handledState;
    }

    /**
     * A method responsible for performing processing that should happen when the state is triggered
     */
    abstract protected void doProcessing();
    
    @Override
    public String getState() {
        return handledState;
    }

    @Override
    public void setSCXMLDriver(SCXMLDriver driver) {
        this.driver = driver;
    }

    /**
     * {@inheritDoc}
     * 
     * This method should be invoked last if overridden.
     */
    @Override
    public String complete() {
        return ("NONE".equals(exitEvent)?null:exitEvent);
    }
    
    /**
     * initiates state processing failure handling procedure
     */
    protected void fail() {
        StringBuilder builder = new StringBuilder();
        builder.append("Processing of a handler for state " + handledState + " failed. ");
        if(exception!=null) {
            LOG.error(builder.toString(),exception);
        } else {
            LOG.error(builder.toString());
        }
        driver.failed(this);
    }
    
    @Override
    public Exception getException() {
        return exception;
    }
}