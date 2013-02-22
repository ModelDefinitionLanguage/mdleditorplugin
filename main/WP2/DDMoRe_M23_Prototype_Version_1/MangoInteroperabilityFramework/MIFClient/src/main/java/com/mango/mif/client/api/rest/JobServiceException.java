/* File			:  JobServiceException.java
 * Project		:  MIFServer
 * Created on	:  10 Jul 2012
 */
package com.mango.mif.client.api.rest;


/**
 * @version $Revision: $ as of $Date: $
 * <p>SVN Entry : $HeadURL: $
 * <p>SVN ID    : $Id: $
 * <p>Last edited by : $Author: $
 */
public class JobServiceException extends RuntimeException {

	private static final long	serialVersionUID	= 1L;
	private static final String MSG = "Lookup of entry (%s) failed: %s";

	public JobServiceException(Long entryId, String reason) {
		super(getMsg(entryId, reason));
	}

	public JobServiceException(Long entryId, Throwable cause) {
		super(getMsg(entryId, (cause != null) ? cause.getMessage() : ""));
	}
	
	public JobServiceException(String msg) {
		super(msg);
	}

	private static String getMsg(Long entryId, String reason) {
		return String.format(MSG, entryId, reason);
	}

}
