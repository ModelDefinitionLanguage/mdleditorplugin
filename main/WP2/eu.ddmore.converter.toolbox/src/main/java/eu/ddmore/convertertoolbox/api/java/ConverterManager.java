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

    /**
     * Get the converter which is registered with the framework providing conversion from the specified input to output. 
     * If there is more than one version of the converter registered the latest one (highest version) will be returned.
     * @return a ready to use converter 
     * @throws if there is no converter registered in the toolbox that can convert from input to output
     */
    Converter getConverter(LanguageVersion source, LanguageVersion target);

    /**
     * Get the converter which is registered with the framework providing conversion from the specified input to output and the converter version matches that specified.
     * @return a ready to use converter
     * @throws if there is no converter registered in the toolbox that can convert from input to output and is of the specified version
     */
    Converter getConverter(LanguageVersion source, LanguageVersion target, Version converterVersion);

    /**
     * Get the capabilities of the toolbox, this could change over time (runtime mutable)
     * @return the list of input and output language/version pairs which are supported by the toolbox at the time of calling this method 
     */
    Map<LanguageVersion, LanguageVersion> getCapabilities();
}
