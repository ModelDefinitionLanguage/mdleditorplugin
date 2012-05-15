/* File			:  RDispatcher.java
 * Project		:  MangoInteroperabilityFramework
 * Created on	:  Mar 13, 2012
 */
package com.mango.mif.rconnectorcli.dispatcher;


import com.mango.mif.connector.dispatcher.impl.Dispatcher;

/**
 * 
 * CLI R execution dispatcher 
 * 
 * @version $Revision: $ as of $Date: $
 * <p>SVN Entry : $HeadURL: $
 * <p>SVN ID    : $Id: $
 * <p>Last edited by : $Author: $
 */
public class RCliDispatcher extends Dispatcher {
    
	public String getDestination() {
		return destination;
	}

	public void setDestination(String destination) {
		this.destination = destination;
	}
	
}
