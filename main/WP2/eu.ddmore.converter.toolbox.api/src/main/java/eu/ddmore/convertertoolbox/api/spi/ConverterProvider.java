/*******************************************************************************
 * Copyright (C) 2013 Mango Solutions Ltd - All rights reserved.
 ******************************************************************************/
package eu.ddmore.convertertoolbox.api.spi;

import java.io.File;
import java.io.IOException;

import eu.ddmore.convertertoolbox.api.domain.LanguageVersion;
import eu.ddmore.convertertoolbox.api.domain.Version;
import eu.ddmore.convertertoolbox.api.response.ConversionReport;

/**
 * Interface which Converter providers should implement to enable them to be called by clients of the Converter Toolbox. 
 */
public interface ConverterProvider {

	/**
	 * Converts the source file using the languages and versions associated with this ConverterProvider.
	 * @param src the source file to be converted
	 * @param outputDirectory the output directory where the converted files will be stored
	 * @return a conversion report containing the details of the requested conversion
     * @throws IOException if there is some error in file reading/writing
	 */
    ConversionReport performConvert(File src, File outputDirectory) throws IOException;

    /**
	 * Converts the source files using the languages and versions associated with this ConverterProvider.
	 * @param src an array of source files to be converted
	 * @param outputDirectory the output directory where the converted files will be stored
     * @return an array conversion reports containing the details of the requested conversions in input file order
     * @throws IOException if there is some error in file reading/writing
     */
    ConversionReport[] performConvert(File[] src, File outputDirectory) throws IOException;

    /**
     * @return the source language and version
     */
    LanguageVersion getSource();

    /**
     * @return the target language and version
     */
    LanguageVersion getTarget();

    /**
     * @return thet converter version
     */
    Version getConverterVersion();
}
