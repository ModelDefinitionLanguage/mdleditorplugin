/* File			:  StateHandlerException.java
 * Project		:  MIFServer
 * Created on	:  26 Jun 2012
 */
package com.mango.mif.connector.runner.impl;

/**
 * @version $Revision: $ as of $Date: $
 * <p>SVN Entry : $HeadURL: $
 * <p>SVN ID    : $Id: $
 * <p>Last edited by : $Author: $
 */
/**
 * 
 * An exception being thrown by state handlers if an error occurs
 * @author mrogalski
 * 
 */
public class StateHandlerException extends Exception {

	/**
	 * 
	 */
	private static final long	serialVersionUID	= 1L;

	/**
	 * @param message
	 * @param cause
	 */
	public StateHandlerException(String message, Throwable cause) {
		super(message, cause);
	}

	/**
	 * @param message
	 */
	public StateHandlerException(String message) {
		super(message);
	}

}
