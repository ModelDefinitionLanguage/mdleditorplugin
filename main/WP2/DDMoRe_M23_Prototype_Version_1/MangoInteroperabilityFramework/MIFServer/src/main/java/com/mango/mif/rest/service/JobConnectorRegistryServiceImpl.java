package com.mango.mif.rest.service;

import java.util.Set;

import com.google.common.base.Joiner;
import com.mango.mif.client.api.rest.JobConnectorRegistryService;
import com.mango.mif.core.impl.ConnectorsRegistry;

/**
 * {@inheritDoc}.
 */
public class JobConnectorRegistryServiceImpl implements JobConnectorRegistryService {

    private ConnectorsRegistry connectorsRegistry;

    /**
     * {@inheritDoc}.
     */
    @Override
    public String getConnectorNames() {
        Set<String> connectors = connectorsRegistry.getConnectors().keySet();
        return Joiner.on(",").join(connectors).toString();
    }

    public ConnectorsRegistry getConnectorsRegistry() {
        return connectorsRegistry;
    }

    public void setConnectorsRegistry(ConnectorsRegistry connectorsRegistry) {
        this.connectorsRegistry = connectorsRegistry;
    }
}
