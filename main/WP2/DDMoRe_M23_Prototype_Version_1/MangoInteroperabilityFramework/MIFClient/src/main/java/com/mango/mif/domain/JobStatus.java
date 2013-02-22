/* File			:  DispactherExeptions.java
 * Project		:  MangoInteroperabilityFramework
 * Created on	:  Mar 26, 2012
 */
package com.mango.mif.domain;
/**
 * Enum for the status of the Job.
 * 
 * That status is set on the Execution Response and is used by JobService
 * 
 * @version $Revision: $ as of $Date: $
 * <p>SVN Entry : $HeadURL: $
 * <p>SVN ID    : $Id: $
 * <p>Last edited by : $Author: $
 */
public enum JobStatus {

      NOT_AVAILABLE(false),
	  NEW(false),
	  RUNNING(false),
	  CANCELLED(true),
	  COMPLETED(true),
      FAILED(true);
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
