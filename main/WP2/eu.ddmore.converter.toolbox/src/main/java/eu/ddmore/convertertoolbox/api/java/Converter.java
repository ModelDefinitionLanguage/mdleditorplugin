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

    /**
     * Convert the source file and put the output file(s) in the output directory. 
     * @return a conversion report containing the details of the requested conversion 
     */
    ConversionReport convert(File src, File outputDirectory);

    /**
     * Convert each of the source files and put the output file(s) in the output directory. 
     * @return a conversion report containing the details of the requested conversion 
     */
    ConversionReport convert(File[] src, File outputDirectory);

    /**
     * Convert the source file and put the output file(s) in the output directory. 
     * @param listener a callback hook, if specified the listener will be notified by the converter when certain states are reached  
     */
    void convert(File src, File outputDirectory, ConversionListener listener);

    /**
     * Convert each of the source files and put the output file(s) in the output directory. 
     * @param listener a callback hook, if specified the listener will be notified by the converter when certain states are reached
     */
    void convert(File[] src, File outputDirectory, ConversionListener listener);
}
