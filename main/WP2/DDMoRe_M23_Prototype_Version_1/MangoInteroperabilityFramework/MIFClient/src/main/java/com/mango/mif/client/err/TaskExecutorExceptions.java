/* File			:  TaskExecutorExceptions.java
 * Project		:  MangoInteroperabilityFramework
 * Created on	:  Mar 27, 2012
 */
package com.mango.mif.client.err;
/**
 * @version $Revision: $ as of $Date: $
 * <p>SVN Entry : $HeadURL: $
 * <p>SVN ID    : $Id: $
 * <p>Last edited by : $Author: $
 */
public enum TaskExecutorExceptions {
	 INVALID_REQUEST_MESSAGE(1, "The Request message is not proper"),
	 INVALID_REQUEST_MESSAGE_FORMAT(2, "navigator Request is in not in expected format."),
	 INVALID_RESPONSE_MESSAGE_FORMAT(3, "Job Runner Response is in not in expected format."),
	 INVALID_RESPONSE_MESSAGE(4, "Job Runner Response is in not in expected format."),
	 INVALID_REQUEST_ID(5, "Request ID not present in the request."),
	 CONNECTOR_NOT_PRESENT(6, "Connector Not present For the Key."),
	 SERVER_BASE_URL_NOT_PRESENT(7, "Server URL not present."),
	 ERROR_DURING_SUBMISSION(8, "Error during the submission of therequest ."),
	 REQUEST_ID_NOT_PRESENT(20, "Request ID not present in the request."); 
		  //... more here ...

	  private final int id;
	  private final String message;
	
	  TaskExecutorExceptions(int id, String message) {
	     this.id = id;
	     this.message = message;
	  }
	
	  public int getId() { return id; }
	  public String getMessage() { return message; }

}
