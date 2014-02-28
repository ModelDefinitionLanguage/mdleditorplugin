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
     * 
     * @return the minor version number
     */
    int getMinor();

    /**
     * 
     * @return the patch version number
     */
    int getPatch();

    /**
     * 
     * @return the version qualifier
     */
    String getQualifier();

}
