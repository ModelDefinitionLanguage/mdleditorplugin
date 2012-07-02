/* File			:  GridSubmitCommandBuilder.java
 * Project		:  MIFServer
 * Created on	:  May 10, 2012
 */
package com.mango.mif.rsgeconnector.internal;

import java.io.File;

import com.google.common.base.Preconditions;

/**
 * Builds grid submit command string
 * 
 * For all the options see manual page of qsub
 * 
 * @version $Revision: $ as of $Date: $
 * <p>SVN Entry : $HeadURL: $
 * <p>SVN ID    : $Id: $
 * <p>Last edited by : $Author: $
 */
public class SGESubmitCommandBuilder {
	/**
	 * Queue to which a job is sent
	 */
	private String queue;
	/**
	 * Job name
	 */
	private String jobName;
	/**
	 * Command file
	 */
	private File commandFile;
	/**
	 * User issuing the job
	 */
	private String user;
	/**
	 * Prolog
	 */
	protected String prolog = "";
	/**
	 * 
	 * @return a SGE submission string
	 */
	public String getCommand() {
		Preconditions.checkNotNull(queue, "Queue must be set.");
		Preconditions.checkNotNull(jobName, "Job name must be set.");
		Preconditions.checkNotNull(user, "User must be set.");
		Preconditions.checkNotNull(commandFile, "Command file must be set.");
		Preconditions.checkArgument(commandFile.exists(), "Command file does not exist.");
		
		StringBuilder builder = new StringBuilder()
				.append(" -u ").append(user)
				.append(" -cwd ")
				.append(commandFile.getAbsolutePath());
		
		return builder.toString();
	}

	public SGESubmitCommandBuilder setProlog(String prolog) {
		this.prolog = prolog;
		return this;
	}
	
	public SGESubmitCommandBuilder setUser(String user) {
		this.user = user;
		return this;
	}
	public SGESubmitCommandBuilder setQueue(String queue) {
		this.queue = queue;
		return this;
	}
	public SGESubmitCommandBuilder setCommandFile(File commandFile) {
		this.commandFile = commandFile;
		return this;
	}
	public SGESubmitCommandBuilder setJobName(String jobName) {
		this.jobName = jobName;
		return this;
	}
	
}
