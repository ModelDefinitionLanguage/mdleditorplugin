/* File			:  BasicResourceRetriever.java
 * Project		:  MIFClient
 * Created on	:  11 Jun 2012
 */
package com.mango.mif.core.resource;

import java.io.File;


/**
 * 
 * A basic implementation of the resource retriever
 * @version $Revision: $ as of $Date: $
 * <p>SVN Entry : $HeadURL: $
 * <p>SVN ID    : $Id: $
 * <p>Last edited by : $Author: $
 */
/**
 * @author mrogalski
 *
 */
public abstract class BaseResourceRetriever extends BaseSharedLocationOperation implements ResourceRetriever {
	/**
	 * Request ID
	 */
	protected String requestID;
	/**
	 * Destination directory where the result files should be copied into
	 */
	protected File destinationDir;
	
	/* (non-Javadoc)
	 * @see com.mango.mif.core.api.ResourceRetriever#setRequestID(java.lang.String)
	 */
	@Override
	public void setRequestID(String requestID) {
		this.requestID = requestID;
	}

	
	/**
	 * @return the requestID
	 */
	public String getRequestID() {
		return requestID;
	}

	/**
	 * @param destinationDir the destinationDir to set
	 */
	public void setDestinationDir(File destinationDir) {
		this.destinationDir = destinationDir;
	}
}
