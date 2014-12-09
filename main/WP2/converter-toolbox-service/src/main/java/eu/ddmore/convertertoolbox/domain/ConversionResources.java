package eu.ddmore.convertertoolbox.domain;

import java.util.Collection;

import javax.xml.bind.annotation.XmlAnyElement;
import javax.xml.bind.annotation.XmlElementWrapper;

import org.springframework.hateoas.Resources;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;


/**
 * HAL-enabled collection of { @link ConversionResource }
 */
public class ConversionResources extends Resources<ConversionResource>{
    private final Collection<ConversionResource> content;

    @JsonCreator
    public ConversionResources(@JsonProperty("content") Collection<ConversionResource> content) {
        super();
        this.content = content;
    }

    @Override
    @XmlAnyElement
    @XmlElementWrapper
    @JsonProperty("content")
    public Collection<ConversionResource> getContent() {
        return content;
    }
}
