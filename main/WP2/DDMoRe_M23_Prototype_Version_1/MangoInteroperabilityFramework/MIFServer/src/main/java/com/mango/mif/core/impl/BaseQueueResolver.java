/* File			:  BaseQueueResolver.java
 * Project		:  MIFServer
 * Created on	:  Jul 10, 2012
 */
package com.mango.mif.core.impl;

import org.apache.log4j.Logger;

/**
 * @version $Revision: $ as of $Date: $
 * <p>SVN Entry : $HeadURL: $
 * <p>SVN ID    : $Id: $
 * <p>Last edited by : $Author: $
 * 
 * Base queue resolver defining common methods
 */
public abstract class BaseQueueResolver implements QueueResolver {
	/**
	 * Logger 
	 */
	protected final static Logger LOG = Logger.getLogger(BaseQueueResolver.class);

	/**
	 * Connectors registry
	 */
	protected ConnectorsRegistry connectorsRegistry;
	
    public void setConnectorsRegistry(ConnectorsRegistry connectorsRegistry) {
        this.connectorsRegistry = connectorsRegistry;
    }
    
    public ConnectorsRegistry getConnectorsRegistry() {
        return connectorsRegistry;
    }
}
