/*******************************************************************************
 * Copyright (C) 2013 Mango Solutions Ltd - All rights reserved.
 ******************************************************************************/
package eu.ddmore.convertertoolbox.domain;

/**
 * Bean representing a version
 */
public interface Version {

    int getMajor();

    void setMajor(int major);

    int getMinor();

    void setMinor(int minor);

    int getPatch();

    void setPatch(int patch);

    String getQualifier();

    void setQualifier(String qualifier);
}
