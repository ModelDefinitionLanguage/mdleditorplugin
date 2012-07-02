/* File			:  CommandBuilder.java
 * Project		:  MIFServer
 * Created on	:  22 Jun 2012
 */
package com.mango.mif.rsgeconnector.internal;

import com.mango.mif.connector.dispatcher.domain.Job;
import com.mango.mif.exception.MIFException;

/**
 * @version $Revision: $ as of $Date: $
 * <p>SVN Entry : $HeadURL: $
 * <p>SVN ID    : $Id: $
 * <p>Last edited by : $Author: $
 */
/**
 * @author mrogalski
 * Common interface of command builder
 */
public interface CommandBuilder {
	/**
	 * Builds a command
	 * @return
	 * @throws MIFException 
	 */
	public String getCommand() throws MIFException;
	/**
	 * Sets a job for which command is to be built
	 * @param job 
	 */
	public void setJob(Job job);
}
