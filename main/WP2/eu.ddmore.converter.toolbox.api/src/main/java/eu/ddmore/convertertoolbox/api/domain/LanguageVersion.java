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
     * Sets the conversion language.
     * @param language the conversion language to set
     */
    void setLanguage(String language);

    /**
     * @return the converter version
     */
    Version getVersion();

    /**
     * Sets the converter version.
     * @param version the converter version to set
     */
    void setVersion(Version version);
}
