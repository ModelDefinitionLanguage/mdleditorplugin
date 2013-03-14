/* File			:  QueueResolver.java
 * Project		:  MIFServer
 * Created on	:  Jul 10, 2012
 */
package com.mango.mif.core.impl;
/**
 * @version $Revision: $ as of $Date: $
 * <p>SVN Entry : $HeadURL: $
 * <p>SVN ID    : $Id: $
 * <p>Last edited by : $Author: $
 */
public interface QueueResolver {
	/**
	 * 
	 * @param message
	 * @return a queue that the given message should be routed to
	 */
	String resolve(String message);
}
