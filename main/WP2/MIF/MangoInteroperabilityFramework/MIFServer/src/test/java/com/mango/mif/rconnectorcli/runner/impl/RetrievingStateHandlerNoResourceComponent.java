/* File			:  RetrievingStateHandler.java
 * Project		:  MangoInteroperabilityFramework
 * Created on	:  20 Mar 2012
 */
package com.mango.mif.rconnectorcli.runner.impl;

import org.apache.log4j.Logger;

/**
 * Retrieving state handler that does not depend on resource component
 * 
 * @version $Revision: $ as of $Date: $
 * <p>SVN Entry : $HeadURL: $
 * <p>SVN ID    : $Id: $
 * <p>Last edited by : $Author: $
 */
public class RetrievingStateHandlerNoResourceComponent extends RScriptStateHandler {
    /**
     * Logger
     */
    private static Logger LOG = Logger.getLogger(RetrievingStateHandlerNoResourceComponent.class);

    /**
     * Default Constructor
     * 
     * @param state
     * @param exitEvent
     */
    public RetrievingStateHandlerNoResourceComponent(String state, String exitEvent) {
        super(state, exitEvent);
    }

    @Override
    protected void doProcessing() {
        LOG.debug("Retrieving results for job: " + jobRunner.getJob().getJobId());
    }
}