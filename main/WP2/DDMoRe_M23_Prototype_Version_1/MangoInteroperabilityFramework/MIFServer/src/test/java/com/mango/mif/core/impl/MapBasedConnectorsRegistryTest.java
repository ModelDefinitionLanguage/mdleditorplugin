/* File			:  ListBasedConnectorsRegistryTest.java
 * Project		:  MIFServer
 * Created on	:  Jul 10, 2012
 */
package com.mango.mif.core.impl;

import static org.junit.Assert.*;
import static org.mockito.Mockito.*;

import java.util.Map;

import org.junit.Before;
import org.junit.Test;

import com.google.common.collect.Maps;
import com.mango.mif.connector.Connector;
import com.mango.mif.core.impl.MapBasedConnectorsRegistry;

/**
 * @version $Revision: $ as of $Date: $
 * <p>SVN Entry : $HeadURL: $
 * <p>SVN ID    : $Id: $
 * <p>Last edited by : $Author: $
 * 
 * Tests map based connector registry
 */
public class MapBasedConnectorsRegistryTest {
	/**
	 * Connectors registry
	 */
	MapBasedConnectorsRegistry connectorsRegistry;
	
	private String mockExecutionType = "executionType";
	
	@Before
	public void setUp() throws Exception {
		Map<String,Connector> connectors = Maps.newHashMap();
		Connector connector = mock(Connector.class);
		when(connector.getConnectorId()).thenReturn("CONNECTOR-ID");
		connectors.put(mockExecutionType,connector);
		connectorsRegistry = new MapBasedConnectorsRegistry(connectors);
	}

	@Test
	public void shouldReturnConnectorIfItExists() {
		assertNotNull(connectorsRegistry.getConnector(mockExecutionType));
        assertEquals("CONNECTOR-ID", connectorsRegistry.getConnector(mockExecutionType).getConnectorId());
	}

	@Test
	public void shouldReturnNullIfConnectorIfItDoesNotExist() {
		assertNull(connectorsRegistry.getConnector("NON-BOUND-EXECUTION-TYPE"));
	}
}
