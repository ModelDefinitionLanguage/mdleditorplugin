/* File			:  DispactherExeptions.java
 * Project		:  MangoInteroperabilityFramework
 * Created on	:  Mar 26, 2012
 */
package com.mango.mif.connector.dispatcher.domain;
/**
 * Enum for the status of the Job.
 * @version $Revision: $ as of $Date: $
 * <p>SVN Entry : $HeadURL: $
 * <p>SVN ID    : $Id: $
 * <p>Last edited by : $Author: $
 */
public enum JobStatus {
	
	  STARTED("STARTED"),
	  RUNNING("RUNNING"),
	  COMPLETED("COMPLETED");
	  //... more here ...

	  private final String message;

	  JobStatus( String message) {
	     this.message = message;
	  }

	  public String getMessage() { return message; }

}
