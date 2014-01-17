/*******************************************************************************
 * Copyright (C) 2013 Mango Solutions Ltd - All rights reserved.
 ******************************************************************************/
package eu.ddmore.convertertoolbox.api.cli;

import java.io.File;
import java.io.IOException;

import eu.ddmore.convertertoolbox.api.exception.ConverterNotFoundException;
import eu.ddmore.convertertoolbox.api.response.ConversionReport;

/**
 * Command line interface to the converter toolbox, aims to provide the same (where possible) functionality to the programmatic API 
 */
public interface ConverterCLI {

	/**
     * Attempts to convert using the source file and input/output languages and versions.
     * 
	 * @param src the source file to be converted
	 * @param srcLanguage the source conversion language
	 * @param srcVersion the source converter version
	 * @param targetLanguage the target conversion language
	 * @param targetVersion the target converter version
	 * @param outputDirectory the output directory where the converted file will be stored
     * @return a conversion report containing the details of the requested conversion
	 * @throws ConverterNotFoundException if there is no converter registered in the toolbox that can convert from input to output
     * @throws IOException if there is some error in file reading/writing
	 */
    ConversionReport convert(File src, String srcLanguage, String srcVersion, String targetLanguage, String targetVersion,
            File outputDirectory) throws ConverterNotFoundException, IOException;
    
    /**
     * Attempts to convert using the source files and input/output languages and versions. 
     * All input files will be processed with the same input and output languages and versions.
     * 
	 * @param src an array of source files to be converted
	 * @param srcLanguage the source conversion language
	 * @param srcVersion the source converter version
	 * @param targetLanguage the target conversion language
	 * @param targetVersion the target converter version
	 * @param outputDirectory the output directory where the converted files will be stored
     * @return an array conversion reports containing the details of the requested conversions in input file order
	 * @throws ConverterNotFoundException if there is no converter registered in the toolbox that can convert from input to output
     * @throws IOException if there is some error in file reading/writing
     */
    ConversionReport[] convert(File[] src, String srcLanguage, String srcVersion, String targetLanguage, String targetVersion,
            File outputDirectory) throws ConverterNotFoundException, IOException;
}
