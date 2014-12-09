package eu.ddmore.convertertoolbox.domain;

import org.springframework.hateoas.ResourceSupport;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;

/**
 * HAL-enabled { @link Conversion }
 */
public class ConversionResource extends ResourceSupport {
    private final Conversion content;

    @JsonCreator
    public ConversionResource(@JsonProperty("content") Conversion content) {
        this.content = content;
    }
    
    public Conversion getContent() {
        return content;
    }
}
