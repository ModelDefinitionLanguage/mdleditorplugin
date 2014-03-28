/*******************************************************************************
 * Copyright (C) 2013 Mango Solutions Ltd - All rights reserved.
 ******************************************************************************/
package eu.ddmore.convertertoolbox.api.conversion;

import java.util.Collection;
import java.util.Map;

import eu.ddmore.convertertoolbox.api.domain.LanguageVersion;
import eu.ddmore.convertertoolbox.api.domain.Version;
import eu.ddmore.convertertoolbox.api.exception.ConverterNotFoundException;

/**
 * The manager provides JVM clients an entry point into the Converter Toolbox and in turn to the converters held within 
 */
public interface ConverterManager {
    
    /**
     * Discovers Converter Provider implementations available in the classpath. 
     * This method should be called by every ConverterManager implementation immediately after the constructor call. 
     * After that, it does not make any sense to call that again. The JVM classpath is static, so exactly the same 
     * Converter Providers will be discovered irrespective if you added or removed any of them inbetween. 
     */
    void discoverConverters();
    
	/**
     * Get the converter which is registered with the framework providing conversion from the specified input to output. 
     * If there is more than one version of the converter registered the latest one (highest version) will be returned, 
     * where higher is defined on 'major.minor.patch' format, e.g. '1.2.1' is later that '1.1.3'. 
     * Version quilifier is not taken into consideration for this comparison.
	 * @param source the conversion source language and version
	 * @param target the conversion target language and version
     * @return a ready to use converter 
     * @throws if there is no converter registered in the toolbox that can convert from input to output
	 */
    Converter getConverter(LanguageVersion source, LanguageVersion target) throws ConverterNotFoundException;

    /**
     * Get the converter which is registered with the framework providing conversion from the specified input to output and the converter version matches that specified.
	 * @param source the conversion source language and version
	 * @param target the conversion target language and version
     * @param converterVersion the converter version
     * @return a ready to use converter
     * @throws if there is no converter registered in the toolbox that can convert from input to output and is of the specified version
     */
    Converter getConverter(LanguageVersion source, LanguageVersion target, Version converterVersion) throws ConverterNotFoundException;

    /**
     * Get the capabilities of the toolbox, this could change over time (runtime mutable).
     * @return the list of input and output language/version pairs which are supported by the toolbox at the time of calling this method 
     */
    Map<LanguageVersion, Collection<LanguageVersion>> getCapabilities();
}
