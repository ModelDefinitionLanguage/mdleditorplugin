/* File			:  QuenameAppenders.java
 * Project		:  MIFClient
 * Created on	:  May 24, 2012
 */
package com.mango.mif.client.domain;
/**
 * @version $Revision: $ as of $Date: $
 * <p>SVN Entry : $HeadURL: $
 * <p>SVN ID    : $Id: $
 * <p>Last edited by : $Author: $
 */
public enum QuenameAppenders {
	
	CANCEL_QUEUE_APPENDER(".JOB.CANCEL"),
	REQUEST_QUEUE_APPENDER(".JOB.REQUEST"),
	RESPONSE_QUEUE_APPENDER(".JOB.RESPONSE");
	
	private String queueName;
	
	private QuenameAppenders(String queueName) {
		this.queueName = queueName;
	}
	public String getQueueName() {
		return queueName;
	}

}
