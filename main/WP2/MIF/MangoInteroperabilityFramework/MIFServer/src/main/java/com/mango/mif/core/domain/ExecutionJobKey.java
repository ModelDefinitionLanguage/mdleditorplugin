/* File			:  ExecutionJobKey.java
 * Project		:  MIFServer
 * Created on	:  18 May 2012
 */
package com.mango.mif.core.domain;

/**
 * Enum representing the items used to represent job keys used by the job runners.
 * As different external tools vary in their behaviour, we choose different items to use
 * as job keys and values depending on the tools.  For example, with R execution, we use 
 * "STATUS" as the key and the state machine status as the value in order to identify the job.
 * @version $Revision: $ as of $Date: $
 * <p>SVN Entry : $HeadURL: $
 * <p>SVN ID    : $Id: $
 * <p>Last edited by : $Author: $
 */
public enum ExecutionJobKey {

	STATUS("STATUS");
	//... more here ...

	private final String message;

	ExecutionJobKey(String message) {
		this.message = message;
	}

	public String getMessage() {
		return message;
	}

}
