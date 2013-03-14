/* File			:  MapBasedConnectorsRegistry.java
 * Project		:  MIFServer
 * Created on	:  Jul 10, 2012
 */
package com.mango.mif.core.impl;

import java.util.Map;
import java.util.Map.Entry;

import javax.annotation.Nullable;

import com.google.common.base.Predicate;
import com.google.common.collect.Maps;
import com.mango.mif.connector.Connector;

/**
 * @version $Revision: $ as of $Date: $
 * <p>SVN Entry : $HeadURL: $
 * <p>SVN ID    : $Id: $
 * <p>Last edited by : $Author: $
 * 
 * A Connectors registry that holds a map of connector references
 */
public class MapBasedConnectorsRegistry implements ConnectorsRegistry {
	/**
	 * Maps execution types to connectors
	 */
	private final Map<String, Connector> connectors;
	    
    /**
	 * Connector
	 * @param connectors
	 */
	public MapBasedConnectorsRegistry(Map<String, Connector> connectors) {
		this.connectors = connectors;
	}
	
	/**
     * {@inheritDoc}.
     */
	@Override
	public Connector getConnector(final String executionType) {
		
		Map<String, Connector> result = Maps.filterEntries(connectors, new Predicate<Entry<String,Connector>>() {
            @Override
            public boolean apply(@Nullable
            Entry<String, Connector> entry) {
                return entry.getKey().equals(executionType);
            }
		    
		});
		
		if(result.isEmpty()) {
			return null;
		}
		
		return result.entrySet().iterator().next().getValue();
	}
	
	/**
	 * {@inheritDoc}.
	 */
	public Map<String, Connector> getConnectors() {
        return connectors;
    }

	
}
