/*******************************************************************************
 * Copyright (C) 2015 Mango Solutions Ltd - All rights reserved.
 ******************************************************************************/
package eu.ddmore.convertertoolbox.domain;

import java.util.Collection;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;

/**
 * Represents a supported conversions from given source {@link LanguageVersion} to target collection of {@link LanguageVersion}
 */
public final class ConversionCapability {
    private final LanguageVersion source;
    private final Collection<LanguageVersion> target;
    /**
     * @param source
     * @param target
     */
    @JsonCreator
    public ConversionCapability(@JsonProperty("source") LanguageVersion source, @JsonProperty("target") Collection<LanguageVersion> target) {
        super();
        this.source = source;
        this.target = target;
    }
    
    public LanguageVersion getSource() {
        return source;
    }
    
    public Collection<LanguageVersion> getTarget() {
        return target;
    }

    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + ((source == null) ? 0 : source.hashCode());
        result = prime * result + ((target == null) ? 0 : target.hashCode());
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
        ConversionCapability other = (ConversionCapability) obj;
        if (source == null) {
            if (other.source != null)
                return false;
        } else if (!source.equals(other.source))
            return false;
        if (target == null) {
            if (other.target != null)
                return false;
        } else if (!target.equals(other.target))
            return false;
        return true;
    }
    
    
}
