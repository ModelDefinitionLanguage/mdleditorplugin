/* File			:  QuenameAppenders.java
 * Project		:  MIFClient
 * Created on	:  May 24, 2012
 */
package com.mango.mif.client.domain;

/**
 * The Enum QueueNameLocators.
 * This holds the key that we use to get the actual value of the name of the QUEUE 
 * configured in the property file.
 *
 * @version $Revision: $ as of $Date: $
 * <p>SVN Entry : $HeadURL: $
 * <p>SVN ID    : $Id: $
 * <p>Last edited by : $Author: $
 */
public enum QueueNameLocators {
	
	CANCEL_QUEUE("JOB.CANCEL"),
	REQUEST_QUEUE("JOB.REQUEST"),
	RESPONSE_QUEUE("JOB.RESPONSE"), 
	ACK_QUEUE("JOB.RESPONSE.ACK");
	
	private String queueName;
	
	private QueueNameLocators(String queueName) {
		this.queueName = queueName;
	}
	public String getQueueName() {
		return queueName;
	}

}
