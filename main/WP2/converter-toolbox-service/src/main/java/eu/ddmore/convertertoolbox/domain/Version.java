/*******************************************************************************
 * Copyright (C) 2013 Mango Solutions Ltd - All rights reserved.
 ******************************************************************************/
package eu.ddmore.convertertoolbox.domain;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;



/**
 * REST-friendly implementation of { @link VersionImpl }
 */
public class Version implements Comparable<Version> {

    private final int major;
    private final int minor;
    private final int patch;
    private final String qualifier;

    @JsonCreator
    public Version(@JsonProperty("major") int major, @JsonProperty("minor") int minor, @JsonProperty("patch") int patch, @JsonProperty("qualifier") String qualifier) {
        this.major = major;
        this.minor = minor;
        this.patch = patch;
        this.qualifier = qualifier;
    }

    public int getMajor() {
        return major;
    }

    public int getMinor() {
        return minor;
    }

    public int getPatch() {
        return patch;
    }

    public String getQualifier() {
        return qualifier;
    }

    @Override
    public int compareTo(Version o) {
        int majorDiff = this.major - o.getMajor();
        if (majorDiff != 0) {
            return majorDiff;
        }

        int minorDiff = this.minor - o.getMinor();
        if (minorDiff != 0) {
            return minorDiff;
        }

        return this.patch - o.getPatch();
    }


    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + major;
        result = prime * result + minor;
        result = prime * result + patch;
        result = prime * result + ((qualifier == null) ? 0 : qualifier.hashCode());
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
        Version other = (Version) obj;
        if (major != other.major) {
            return false;
        }
        if (minor != other.minor) {
            return false;
        }
        if (patch != other.patch) {
            return false;
        }
        if (qualifier == null) {
            if (other.qualifier != null) {
                return false;
            }
        } else if (!qualifier.equals(other.qualifier)) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        String base = String.format("%d.%d.%d", major, minor, patch);
        if (qualifier!=null) {
            base = String.format("%s-%s", base, qualifier);
        }
        return base;
    }

    public eu.ddmore.convertertoolbox.api.domain.Version toOldAPI() {
        return new VersionImpl(major, minor, patch, qualifier);
    }

    public static Version fromOldAPI(eu.ddmore.convertertoolbox.api.domain.Version version) {
        return new Version(version.getMajor(),version.getMinor(),version.getPatch(), version.getQualifier());
    }

}