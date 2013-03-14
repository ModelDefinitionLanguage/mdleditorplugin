/* File			:  InvokerResultHandler.java
 * Project		:  MIFServer
 * Created on	:  29 Jun 2012
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
 * 
 * An interface of a component that is responsible for handling results from invoker
 *
 */
public interface InvokerResultHandler {
	/**
	 * Handles the results and 
	 * @return an event id that is to be sent to the scxml driver.
	 * @throws ExecutionException
	 */
	void handle(InvokerResult invokerResult) throws ExecutionException;
}
