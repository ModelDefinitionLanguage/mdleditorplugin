/* File			:  QstatOutputJobProperty.java
 * Project		:  MIFServer
 * Created on	:  Jul 23, 2012
 */
package com.mango.mif.sge;
/**
 * @version $Revision: $ as of $Date: $
 * <p>SVN Entry : $HeadURL: $
 * <p>SVN ID    : $Id: $
 * <p>Last edited by : $Author: $
 * 
 * Job properties found in the qstat command output
 * 
 */
public enum QstatJobOutput {
	/**
	 * job owner
	 */
	OWNER("owner"),
	/**
	 * job name
	 */
	JOB_NAME("job_name"),
	/**
	 * job number
	 */
	JOB_NUMBER("job_number"),
	/**
	 * host name
	 */
	SGE_OWNER_HOST("sge_o_host"),
	/**
	 * host name
	 */
	WORKING_DIRECTORY("cwd"),
	/**
	 * submission time OR start time
	 */
	SUBMISSION_TIME("submission_time");
	/**
	 * token found in qacct output
	 */
	private String token;
	/**
	 * Constructor
	 * @param token
	 */
	QstatJobOutput(String token) {
		this.token = token;
	}
	
	public String getToken() {
		return token;
	}
}
