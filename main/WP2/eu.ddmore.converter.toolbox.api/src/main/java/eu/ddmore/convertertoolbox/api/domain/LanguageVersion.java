/*******************************************************************************
 * Copyright (C) 2013 Mango Solutions Ltd - All rights reserved.
 ******************************************************************************/
package eu.ddmore.convertertoolbox.api.domain;

/**
 * Bean representing a language and version of that language
 */
public interface LanguageVersion {

	/**
	 * @return the conversion language
	 */
    String getLanguage();

    /**
     * @return the converter version
     */
    Version getVersion();

}
