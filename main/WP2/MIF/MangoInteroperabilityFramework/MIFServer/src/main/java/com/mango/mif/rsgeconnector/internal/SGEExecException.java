/* File			:  SGEExecException.java
 * Project		:  MIFServer
 * Created on	:  26 Jun 2012
 */
package com.mango.mif.rsgeconnector.internal;

import com.mango.mif.connector.runner.impl.ExecutionException;

/**
 * @version $Revision: $ as of $Date: $
 * <p>SVN Entry : $HeadURL: $
 * <p>SVN ID    : $Id: $
 * <p>Last edited by : $Author: $
 */
/**
 * @author mrogalski
 * An exception thrown when the sge execution fails
 */
public class SGEExecException extends ExecutionException {

	/**
	 * 
	 */
	private static final long	serialVersionUID	= 1L;

	/**
	 * @param arg0
	 * @param arg1
	 */
	public SGEExecException(String message, Throwable cause) {
		super(message,cause);
	}

	/**
	 * @param arg0
	 */
	public SGEExecException(String message) {
		super(message);
	}
	
}
