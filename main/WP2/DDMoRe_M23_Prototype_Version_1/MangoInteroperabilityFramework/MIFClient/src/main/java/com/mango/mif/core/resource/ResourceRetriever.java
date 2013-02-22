/* File			:  ResourceRetriever.java
 * Project		:  MIFClient
 * Created on	:  11 Jun 2012
 */
package com.mango.mif.core.resource;


/**
 * 
 * Classes implementing this interface are responsible for downloading files from shared location
 * 
 * @version $Revision: $ as of $Date: $
 * <p>SVN Entry : $HeadURL: $
 * <p>SVN ID    : $Id: $
 * <p>Last edited by : $Author: $
 */
/**
 * @author mrogalski
 *
 */
public interface ResourceRetriever extends SharedLocationOperation {
	/**
	 * Main method that is responsible for downloading resources from shared location
	 */
	void retrieve();
	
	void setRequestID(String requestID);
}
