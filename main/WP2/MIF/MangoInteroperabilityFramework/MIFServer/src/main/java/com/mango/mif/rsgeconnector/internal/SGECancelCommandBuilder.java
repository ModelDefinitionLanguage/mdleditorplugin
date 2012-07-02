package com.mango.mif.rsgeconnector.internal;

import com.google.common.base.Preconditions;

/**
 * Cancel command builder
 * @author mrogalski
 *
 */
public class SGECancelCommandBuilder {
	/**
	 * Job ID
	 */
	protected String jobID;

	/**
	 * Prolog
	 */
	protected String prolog = "";
	/**
	 * 
	 * by default just validates attributes and returns empty string
	 * @return a command used to cancel a job on a grid
	 */
	public String getCommand() {
		Preconditions.checkNotNull(jobID,"Job ID not set");
		return "";
	}
	
	public SGECancelCommandBuilder setJobID(String jobID) {
		this.jobID = jobID;
		return this;
	}
	public SGECancelCommandBuilder setProlog(String prolog) {
		this.prolog = prolog;
		return this;
	}
}
