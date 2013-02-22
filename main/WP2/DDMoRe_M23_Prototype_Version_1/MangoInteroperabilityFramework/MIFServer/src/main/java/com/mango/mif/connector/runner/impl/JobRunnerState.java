/* File			:  JobRunnerState.java
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
 * Job Runners states, holding states names and events as defined in Job Runner scxml definition
 * 
 * @author mrogalski
 */
public enum JobRunnerState {

    /**
     * Use this for undefined states 
     */
    UNDEFINED("undefined", "undefined", true),
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
     * The inputs for the execution are prepared
     */
	TASK_PREPARING("task-preparing","task.prepare", false),
    /**
     * The inputs are being submitted for execution
     */
	TASK_SUBMITTING("task-submitting","task.submit", false),
    /**
     * Task is being processed
     */
	TASK_PROCESSING("task-processing","task.process", false),
    /**
     * Monitor task progress
     */
    TASK_MONITORING("task-monitoring","task.monitor", false),
    /**
     * Task monitoring finished
     */
    TASK_POST_MONITORING("task-post-monitoring","task.post.monitor", false),
    /**
     * Final state of monitoring
     */
    TASK_MONITORING_FINISHED("task-monitoring-finished","task.monitor.finished", false),
    /**
     * The processing has been completed by third party tool, and results are being retrieved
     */
	TASK_RETRIEVING("task-retrieving","task.retrieve", false),
    /**
     * Any required postprocessing of the results is being performed
     */
	TASK_POSTPROCESSING("task-postprocessing","task.postprocess", false),
    /**
     * the final sub state of the running state
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
	CANCELLED("cancelled","runner.cancel", true), 
	;
	
	/**
	 * The key used to map the status in the Job metadata Map
	 */
	public static final String STATUS = "STATUS";
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
    JobRunnerState(String stateName, String triggeringEvent, boolean isFinal) {
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
	
	/**
	 * @param name
	 * @return
	 */
	public static JobRunnerState byStateName(String name) {
		if (name != null) {
			for (JobRunnerState state : JobRunnerState.values()) {
				if (state.stateName.equals(name)) {
					return state;
				}
			}
		}
		return JobRunnerState.UNDEFINED;
	}
}
