/*******************************************************************************
 * Copyright (C) 2013 Mango Solutions Ltd - All rights reserved.
 ******************************************************************************/
package eu.ddmore.convertertoolbox.domain;

/**
 * Bean representing a language and version of that language
 */
public interface LanguageVersion {

    String getLanguage();

    void setLanguage(String language);

    Version getVersion();

    void setVersion(Version version);
}
