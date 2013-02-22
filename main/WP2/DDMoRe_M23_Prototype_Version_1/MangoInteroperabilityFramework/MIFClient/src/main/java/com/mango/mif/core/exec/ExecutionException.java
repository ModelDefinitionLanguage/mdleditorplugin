/* File			:  ExecutionException.java
 * Project		:  MIFServer
 * Created on	:  26 Jun 2012
 */
package com.mango.mif.core.exec;

/**
 * @version $Revision: $ as of $Date: $
 * <p>SVN Entry : $HeadURL: $
 * <p>SVN ID    : $Id: $
 * <p>Last edited by : $Author: $
 */
/**
 * @author mrogalski
 * Exception thrown by software specific components that will be included in the message for the client
 */
public class ExecutionException extends Exception {
	/**
	 * 
	 */
	private static final long	serialVersionUID	= 1L;

	/**
	 * @param message
	 * @param cause
	 */
	public ExecutionException(String message, Throwable cause) {
		super(message, cause);
	}

	/**
	 * @param message
	 */
	public ExecutionException(String message) {
		super(message);
	}

}
