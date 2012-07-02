/* File			:  GridStatusCommandBuilder.java
 * Project		:  MIFServer
 * Created on	:  May 10, 2012
 */
package com.mango.mif.rsgeconnector.internal;

import com.google.common.base.Preconditions;

/**
 * 
 * Builds a get status command
 *  
 * For all the options see manual page of qstat
 * 
 * @version $Revision: $ as of $Date: $
 * <p>SVN Entry : $HeadURL: $
 * <p>SVN ID    : $Id: $
 * <p>Last edited by : $Author: $
 */
public class SGEStatusCommandBuilder {
	/**
	 * Job ID
	 */
	private String jobID;
	/**
	 * Request xml formatted output from SGE
	 */
	private boolean xmlOutput = false;
	/**
	 * Prolog
	 */
	protected String prolog = "";
	/**
	 * 
	 * @return a command sge command string
	 */
	public String getCommand() {
		Preconditions.checkNotNull(jobID, "Job ID must be set.");
		StringBuilder builder = new StringBuilder()
			.append(" -j ").append(jobID).append(xmlOutput?" -xml ":"");
			
		return builder.toString();
	}
	
	public SGEStatusCommandBuilder setJobID(String jobID) {
		this.jobID = jobID;
		return this;
	}
	
	public SGEStatusCommandBuilder setXmlOutput(boolean xmlOutput) {
		this.xmlOutput = xmlOutput;
		return this;
	}

	public SGEStatusCommandBuilder setProlog(String prolog) {
		this.prolog = prolog;
		return this;
	}
}
