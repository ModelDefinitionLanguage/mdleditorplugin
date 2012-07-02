package com.mango.mif.rsgeconnector.internal;

import com.google.common.base.Preconditions;
/**
 * Responsible for building account command
 * @author mrogalski
 *
 */
public class SGEAccountCommandBuilder {
	/**
	 * Job ID
	 */
	protected String jobID;

	/**
	 * Prolog
	 */
	protected String prolog = "";
	/**
	 * by default just validates attributes and returns empty string
	 * @return a command to fetch account summary data after grid finished a job
	 */
	public String getCommand() {
		Preconditions.checkNotNull(jobID,"Job ID must be set");
		return ""; 
	}

	public SGEAccountCommandBuilder setJobID(String jobID) {
		this.jobID = jobID;
		return this;
	}

	public SGEAccountCommandBuilder setProlog(String prolog) {
		this.prolog = prolog;
		return this;
	}
}