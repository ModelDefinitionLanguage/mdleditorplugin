/*******************************************************************************
 * Copyright (C) 2013 Mango Solutions Ltd - All rights reserved.
 ******************************************************************************/
package eu.ddmore.convertertoolbox.api.domain;

/**
 * Bean representing a version
 */
public interface Version extends Comparable<Version> {

	/**
	 * @return the major version number
	 */
    int getMajor();

    /**
     * Sets the major version number.
     * @param major the major version number to set
     */
    void setMajor(int major);

    /**
     * 
     * @return the minor version number
     */
    int getMinor();

    /**
     * Sets the minor version number.
     * @param minor the minor version number to set
     */
    void setMinor(int minor);

    /**
     * 
     * @return the patch version number
     */
    int getPatch();

    /**
     * Sets the patch version number.
     * @param patch the patch version number to set
     */
    void setPatch(int patch);

    /**
     * 
     * @return the version qualifier
     */
    String getQualifier();

    /**
     * Sets the version qualifier.
     * @param qualifier the version qualifier to set
     */
    void setQualifier(String qualifier);
}
