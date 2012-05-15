/* File			:  DispactherExeptions.java
 * Project		:  MangoInteroperabilityFramework
 * Created on	:  Mar 26, 2012
 */
package com.mango.mif.connector.dispatcher.domain;
/**
 * @version $Revision: $ as of $Date: $
 * <p>SVN Entry : $HeadURL: $
 * <p>SVN ID    : $Id: $
 * <p>Last edited by : $Author: $
 */
public enum DispatcherExceptions {
	  INVALID_REQUEST_MESSAGE(1, "The message is not proper"),
	  INVALID_REQUEST_MESSAGE_FORMAT(2, "navigator Request is in not in expected format."),
	  INVALID_RESPONSE_MESSAGE_FORMAT(3, "Job Runner Response is in not in expected format."),
	  INVALID_RESPONSE_MESSAGE(4, "Job Runner Response is in not in expected format."),
	  INVALID_REQUEST_ID(5, "Request ID not present in the request."),
	  REQUEST_ID_NOT_PRESENT(20, "Request ID not present in the request.");
	  //... more here ...

	  private final int id;
	  private final String message;

	  DispatcherExceptions(int id, String message) {
	     this.id = id;
	     this.message = message;
	  }

	  public int getId() { return id; }
	  public String getMessage() { return message; }

}
