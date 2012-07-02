/* File			:  RScriptStateHandler.java
 * Project		:  MangoInteroperabilityFramework
 * Created on	:  20 Mar 2012
 */
package com.mango.mif.rsgeconnector.runner.impl;

import java.io.File;

import com.mango.mif.connector.runner.api.SCXMLDriver;
import com.mango.mif.connector.runner.impl.DefaultStateHandler;

/**
 * A handler used by R SGE Job Runner that holds rJobRunner reference
 * 
 * @version $Revision: $ as of $Date: $
 * <p>SVN Entry : $HeadURL: $
 * <p>SVN ID    : $Id: $
 * <p>Last edited by : $Author: $
 */
public class RSGEStateHandler extends DefaultStateHandler {

    /**
     * Job Runner that this state handler is associated with
     */
    protected RSGEJobRunner jobRunner;
    
 
    /**
     * Constructs a state handler with no exit event
     * @param state
     */
    public RSGEStateHandler(String state) {
        super(state, SCXMLDriver.NULL_EVENT);
    }
    
    /**
     * Default Constructor
     * @param state
     * @param exitEvent
     */
    public RSGEStateHandler(String state, String exitEvent) {
        super(state, exitEvent);
    }
    
    /**
     * Sets job runner
     * @param rJobRunner
     */
    public void setJobRunner(RSGEJobRunner jobRunner) {
        this.jobRunner = jobRunner;
    }
    
	/**
	 * SGE Job id
	 * @return
	 */
	protected String getSgeJobID() {
		return jobRunner.getJob().getData().get(RSGEJobRunner.SGE_JOB_ID);
	}
	/**
	 * SGE job working directory
	 * @return
	 */
	protected File getSgeJobWorkingDirectory() {
		return new File(jobRunner.getJob().getData().get(RSGEJobRunner.SGE_JOB_WORKING_DIRECTORY));
	}
}
