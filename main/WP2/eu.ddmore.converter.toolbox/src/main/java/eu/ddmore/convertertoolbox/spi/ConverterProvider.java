/*******************************************************************************
 * Copyright (C) 2013 Mango Solutions Ltd - All rights reserved.
 ******************************************************************************/
package eu.ddmore.convertertoolbox.spi;

import java.io.File;

import eu.ddmore.convertertoolbox.domain.LanguageVersion;
import eu.ddmore.convertertoolbox.domain.Version;
import eu.ddmore.convertertoolbox.response.ConversionReport;

/**
 * Interface which Converter providers should implement to enable them to be called by clients of the Converter Toolbox. 
 */
public interface ConverterProvider {

    ConversionReport performConvert(File src, File outputDirectory);

    ConversionReport performConvert(File[] src, File outputDirectory);

    LanguageVersion getSource();

    LanguageVersion getTarget();

    Version getConverterVersion();
}
