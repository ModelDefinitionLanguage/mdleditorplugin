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
	
	  STARTED(false),
	  RUNNING(false),
	  CANCELLED(true),
	  COMPLETED(true),
	  JOB_NOT_YET_CONSUMED(false);
	  //... more here ...
	  /**
	   * Flag indicating that the job execution status is final or not
	   */
	  private final boolean isFinal;
	  /**
	   * Constructor
	   * @param message
	   * @param isFinal
	   */
	  JobStatus(boolean isFinal) {
	     this.isFinal = isFinal;
	  }

	  public boolean isFinal() { 
		  return isFinal; 
	  }

}
