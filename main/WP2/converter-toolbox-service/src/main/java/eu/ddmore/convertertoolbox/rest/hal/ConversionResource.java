package eu.ddmore.convertertoolbox.rest.hal;

import org.springframework.hateoas.ResourceSupport;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;

import eu.ddmore.convertertoolbox.domain.Conversion;

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
