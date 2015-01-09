/*******************************************************************************
 * Copyright (C) 2013 Mango Solutions Ltd - All rights reserved.
 ******************************************************************************/
package eu.ddmore.convertertoolbox.domain;

import com.fasterxml.jackson.annotation.JsonProperty;

/**
 * REST-friendly implementation of { @link LanguageVersionImpl }
 */
public class LanguageVersion {

    private final String language;
    private final Version version;

    public LanguageVersion(@JsonProperty("language") String language, @JsonProperty("version") Version version) {
        this.language = language;
        this.version = version;
    }
    
    public String getLanguage() {
        return language;
    }

    public Version getVersion() {
        return version;
    }

    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + ((language == null) ? 0 : language.hashCode());
        result = prime * result + ((version == null) ? 0 : version.hashCode());
        return result;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null) {
            return false;
        }
        if (getClass() != obj.getClass()) {
            return false;
        }
        LanguageVersion other = (LanguageVersion) obj;
        if (language == null) {
            if (other.language != null) {
                return false;
            }
        } else if (!language.equals(other.language)) {
            return false;
        }
        if (version == null) {
            if (other.version != null) {
                return false;
            }
        } else if (!version.equals(other.version)) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return String.format("%s %s", language, version);
    }
}
