/* File			:  JobRunnerSates.java
 * Project		:  MIFServer
 * Created on	:  27 Jun 2012
 */
package com.mango.mif.connector.runner.impl;

import com.mango.mif.connector.runner.api.SCXMLDriver;

/**
 * @version $Revision: $ as of $Date: $
 * <p>SVN Entry : $HeadURL: $
 * <p>SVN ID    : $Id: $
 * <p>Last edited by : $Author: $
 */
/**
 * 
 * Job Runners states, holding sates names and events as defined in Job Runner scxml definition
 * 
 * @author mrogalski
 */
public enum JobRunnerSates {
    /**
     * The processing hasn't yet started
     */
	PENDING("pending",SCXMLDriver.NULL_EVENT, false),
    /**
     * The processing is performed
     */
	RUNNING("running","runner.start", false),
	/*
	 * Running state child states
	 */
    /**
     * Initial state of the Running child states
     */
	TASK_PENDING("task-pending",SCXMLDriver.NULL_EVENT, false),
    /**
     * The processing is performed
     */
	TASK_PREPARING("task-preparing","task.prepare", false),
    /**
     * The processing is performed
     */
	TASK_SUBMITTING("task-submitting","task.submit", false),
    /**
     * The processing is performed
     */
	TASK_PROCESSING("task-processing","task.process", false),
    /**
     * The processing is performed
     */
	TASK_RETRIEVING("task-retrieving","task.retrieve", false),
    /**
     * The processing is performed
     */
	TASK_POSTPROCESSING("task-postprocessing","task.postprocess", false),
    /**
     * The processing is performed
     */
	TASK_FINISHED("task-finished","task.finished", false),
	/*
	 * final states
	 */
    /**
     * The processing reached the failed state
     */
	FAILED("failed","task.failed", true),
    /**
     * The processing finished
     */
	FINISHED("finished","done.state.running", true),
    /**
     * The processing reached the cancelled state
     */
	CANCELLED("cancelled","runner.cancel", true)
	;
    /**
     * is the status final
     */
    private final boolean isFinal;
    /**
     * A state name from scxml definition
     */
    private final String stateName;
    /**
     * A name of the event that triggers transition to the state
     */
    private final String triggeringEvent;
    /**
     * Constructor
     * @param stateName
     * @param triggeringEvent
     * @param isFinal
     */
    JobRunnerSates(String stateName, String triggeringEvent, boolean isFinal) {
        this.stateName = stateName;
        this.isFinal = isFinal;
        this.triggeringEvent = triggeringEvent;
    }
    /**
     * 
     * @return true if that is the final state
     */
    public boolean isFinal() {
        return isFinal;
    }

	/**
	 * @return the stateName
	 */
	public String getStateName() {
		return stateName;
	}
	
	/**
	 * @return the triggeringEvent
	 */
	public String getTriggeringEvent() {
		return triggeringEvent;
	}
}
