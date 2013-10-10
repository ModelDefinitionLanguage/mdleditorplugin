/*******************************************************************************
 * Copyright (C) 2013 Mango Solutions Ltd - All rights reserved.
 ******************************************************************************/
package eu.ddmore.convertertoolbox.api.java;

import java.util.Map;

import eu.ddmore.convertertoolbox.domain.LanguageVersion;
import eu.ddmore.convertertoolbox.domain.Version;

/**
 * The manager provides JVM clients an entry point into the Converter Toolbox and in turn to the converters held within 
 */
public interface ConverterManager {

    Converter getConverter(LanguageVersion source, LanguageVersion target);

    Converter getConverter(LanguageVersion source, LanguageVersion target, Version converterVersion);

    Map<LanguageVersion, LanguageVersion> getCapabilities();
}
