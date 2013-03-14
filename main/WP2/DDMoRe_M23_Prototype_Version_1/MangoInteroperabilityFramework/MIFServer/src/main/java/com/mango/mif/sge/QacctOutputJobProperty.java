/* File			:  
	HOST_NAME("hostname"),.java
 * Project		:  MIFServer
 * Created on	:  Jul 20, 2012
 */
package com.mango.mif.sge;
/**
 * @version $Revision: $ as of $Date: $
 * <p>SVN Entry : $HeadURL: $
 * <p>SVN ID    : $Id: $
 * <p>Last edited by : $Author: $
 * 
 * 
 * Properties found in Qacct output
 */
public enum QacctOutputJobProperty {
	/**
	 * Queue name
	 */
	QUEUE_NAME("qname"),
	/**
	 * job owner
	 */
	OWNER("owner"),
	/**
	 * job name
	 */
	JOB_NAME("jobname"),
	/**
	 * job number
	 */
	JOB_NUMBER("jobnumber"),
	/**
	 * host name
	 */
	HOST_NAME("hostname"),
	/**
	 * submission time
	 */
	QSUB_TIME("qsub_time"),
	/**
	 * job start time
	 */
	START_TIME("start_time"),
	/**
	 * job end time
	 */
	END_TIME("end_time"),
	/**
	 * failed flag
	 */
	FAILED("failed"),
	/**
	 * exit status
	 */
	EXIT_STATUS("exit_status");
	
	/**
	 * token found in qacct output
	 */
	private String token;
	/**
	 * Constructor
	 * @param token
	 */
	QacctOutputJobProperty(String token) {
		this.token = token;
	}
	
	public String getToken() {
		return token;
	}
	
}
