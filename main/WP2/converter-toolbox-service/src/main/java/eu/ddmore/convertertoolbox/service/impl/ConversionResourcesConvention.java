/*******************************************************************************
 * Copyright (C) 2015 Mango Solutions Ltd - All rights reserved.
 ******************************************************************************/
package eu.ddmore.convertertoolbox.service.impl;


/**
 * Holds naming convention for Conversion resources
 */
public class ConversionResourcesConvention {
    /**
     * Name of the directory where converters are requested to put conversion outputs to
     */
    public static final String OUTPUTS_DIRECTORY_NAME = "outputs";
    /**
     * Name of the directory where inputs for conversion are extracted to
     */
    public static final String INPUTS_DIRECTORY_NAME = "inputs";
    /**
     * Name of the archive file received with the conversion request
     */
    public static final String INPUTS_ARCHIVE_NAME = "inputs.phex";
    /**
     * Name of the archive file containing 'outputs' directory contents
     */
    public static final String OUTPUTS_ARCHIVE_NAME = "outputs.phex";
}
