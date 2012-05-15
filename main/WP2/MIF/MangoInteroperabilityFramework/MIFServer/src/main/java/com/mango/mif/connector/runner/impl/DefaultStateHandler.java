/* File			:  DefaultStateHandler.java
 * Project		:  MangoInteroperabilityFramework
 * Created on	:  20 Mar 2012
 */
package com.mango.mif.connector.runner.impl;

/**
 * A default state handler that does not do any processing
 * 
 * @version $Revision: $ as of $Date: $
 * <p>SVN Entry : $HeadURL: $
 * <p>SVN ID    : $Id: $
 * <p>Last edited by : $Author: $
 */
public class DefaultStateHandler extends AbstractStateHandler {
    /**
     * Event that to move to faild state
     */
    public final static String FAILED_EVENT = "task.failed";
    /**
     * Constructor
     * @param state
     * @param exitEvent
     */
    public DefaultStateHandler(String state, String exitEvent) {
        super(state, exitEvent);
    }

    @Override
    protected void doProcessing() {
        //Do nothing
        LOG.debug("Processing " + getState());
    }

    @Override
    public String failed() {
        return FAILED_EVENT;
    }

}
