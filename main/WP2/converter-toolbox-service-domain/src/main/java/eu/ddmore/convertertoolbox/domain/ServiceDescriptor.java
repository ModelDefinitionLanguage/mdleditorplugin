/*******************************************************************************
 * Copyright (C) 2015 Mango Solutions Ltd - All rights reserved.
 ******************************************************************************/
package eu.ddmore.convertertoolbox.domain;

import java.util.Collection;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;

/**
 * Holds information available to the service clients
 */
public class ServiceDescriptor {
    private final String name;
    private final String version;
    private final Collection<ConversionCapability> capabilities;
    /**
     * @param name
     * @param version
     * @param capabilities
     */
    @JsonCreator
    public ServiceDescriptor(@JsonProperty("name") String name, @JsonProperty("version") String version, @JsonProperty("capabilities") Collection<ConversionCapability> capabilities) {
        super();
        this.name = name;
        this.version = version;
        this.capabilities = capabilities;
    }

    public String getVersion() {
        return version;
    }
    
    public String getName() {
        return name;
    }
    
    public Collection<ConversionCapability> getCapabilities() {
        return capabilities;
    }

    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + ((capabilities == null) ? 0 : capabilities.hashCode());
        result = prime * result + ((name == null) ? 0 : name.hashCode());
        result = prime * result + ((version == null) ? 0 : version.hashCode());
        return result;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj)
            return true;
        if (obj == null)
            return false;
        if (getClass() != obj.getClass())
            return false;
        ServiceDescriptor other = (ServiceDescriptor) obj;
        if (capabilities == null) {
            if (other.capabilities != null)
                return false;
        } else if (!capabilities.equals(other.capabilities))
            return false;
        if (name == null) {
            if (other.name != null)
                return false;
        } else if (!name.equals(other.name))
            return false;
        if (version == null) {
            if (other.version != null)
                return false;
        } else if (!version.equals(other.version))
            return false;
        return true;
    }

    @Override
    public String toString() {
        return String.format("ServiceDescriptor [name=%s, version=%s, capabilities=%s]", name, version, capabilities);
    }
    
}
