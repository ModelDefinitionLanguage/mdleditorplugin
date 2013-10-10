/*******************************************************************************
 * Copyright (C) 2013 Mango Solutions Ltd - All rights reserved.
 ******************************************************************************/
package eu.ddmore.convertertoolbox.api.cli;

import java.io.File;

import eu.ddmore.convertertoolbox.response.ConversionReport;

/**
 * Command line interface to the converter toolbox, aims to provide the same (where possible) functionality to the programmatic API 
 */
public interface ConverterCLI {

    /**
     * Attempts to convert using the source file and input/output languages and versions.
     * 
     * @return a conversion report containing the details of the requested conversion 
     * @throws if there is no converter registered in the toolbox that can convert from input to output
     */
    ConversionReport convert(File src, String srcLanguage, String srcVersion, String targetLanguage, String targetVersion,
            File outputDirectory);

    /**
     * Attempts to convert using the source files and input/output languages and versions. 
     * All input files will be processed with the same input and output languages and versions.
     * 
     * @return a conversion report containing the details of the requested conversion 
     * @throws if there is no converter registered in the toolbox that can convert from input to output
     */
    ConversionReport convert(File[] src, String srcLanguage, String srcVersion, String targetLanguage, String targetVersion,
            File outputDirectory);
}
