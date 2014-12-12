package eu.ddmore.convertertoolbox.rest.hal;

import org.springframework.hateoas.ResourceSupport;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;

import eu.ddmore.convertertoolbox.domain.ServiceDescriptor;

/**
 * HAL-enabled { @link ServiceDescriptor }
 */
public class ServiceDescriptorResource extends ResourceSupport {
    private final ServiceDescriptor content;
    
    @JsonCreator
    public ServiceDescriptorResource(@JsonProperty("content") ServiceDescriptor content) {
        this.content = content;
    }
    
    public ServiceDescriptor getContent() {
        return content;
    }
}
