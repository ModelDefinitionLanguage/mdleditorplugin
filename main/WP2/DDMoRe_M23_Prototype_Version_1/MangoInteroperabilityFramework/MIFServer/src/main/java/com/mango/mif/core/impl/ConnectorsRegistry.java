/* File			:  ConnectorsRegistry.java
 * Project		:  MIFServer
 * Created on	:  Jul 10, 2012
 */
package com.mango.mif.core.impl;

import java.util.Map;

import com.mango.mif.connector.Connector;

/**
 * @version $Revision: $ as of $Date: $
 * <p>SVN Entry : $HeadURL: $
 * <p>SVN ID    : $Id: $
 * <p>Last edited by : $Author: $
 * 
 * A registry holding connectors instances
 */
public interface ConnectorsRegistry {
    
	/**
	 * Return a given connector by id. 
	 */
	Connector getConnector(String connectorId);
	
	/**
	 * Return the list of all connectors. 
	 */
	Map<String, Connector> getConnectors();
}
