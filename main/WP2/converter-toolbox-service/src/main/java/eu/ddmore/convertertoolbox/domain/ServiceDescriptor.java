package eu.ddmore.convertertoolbox.domain;

import java.util.Collection;
import java.util.Map;

import eu.ddmore.convertertoolbox.api.domain.LanguageVersion;

/**
 * Holds information available to the service clients
 */
public class ServiceDescriptor {
    private final String name;
    private final String version;
    private final Map<LanguageVersion, Collection<LanguageVersion>> capabilities;
    /**
     * @param name
     * @param version
     * @param capabilities
     */
    public ServiceDescriptor(String name, String version, Map<LanguageVersion, Collection<LanguageVersion>> capabilities) {
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
    
    public Map<LanguageVersion, Collection<LanguageVersion>> getCapabilities() {
        return capabilities;
    }
}
