package eu.ddmore.convertertoolbox.domain;

import org.springframework.hateoas.ResourceSupport;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;

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
