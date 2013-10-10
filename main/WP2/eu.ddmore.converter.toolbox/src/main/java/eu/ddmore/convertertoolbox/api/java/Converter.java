/*******************************************************************************
 * Copyright (C) 2013 Mango Solutions Ltd - All rights reserved.
 ******************************************************************************/
package eu.ddmore.convertertoolbox.api.java;

import java.io.File;

import eu.ddmore.convertertoolbox.response.ConversionReport;

/**
 * Represents a converter available in the toolbox
 */
public interface Converter {

    ConversionReport convert(File src, File outputDirectory);

    ConversionReport convert(File[] src, File outputDirectory);

    void convert(File src, File outputDirectory, ConversionListener listener);

    void convert(File[] src, File outputDirectory, ConversionListener listener);
}
