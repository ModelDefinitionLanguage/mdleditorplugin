/*******************************************************************************
 * Copyright (C) 2002 Mango Solutions Ltd - All rights reserved.
 ******************************************************************************/
package eu.ddmore.convertertoolbox.service.impl;

import java.io.File;

import eu.ddmore.convertertoolbox.domain.Conversion;


public class ConversionResourcesConvention {
    public static final String OUTPUTS_DIRECTORY_NAME = "outputs";
    public static final String INPUTS_DIRECTORY_NAME = "inputs";
    public static final String INPUTS_ARCHIVE_NAME = "inputs.zip";
    public static final String OUTPUTS_ARCHIVE_NAME = "outputs.zip";
    
    public File getConversionWorkingDirectory(Conversion conversion) {
        return conversion.getWorkingDirectory();
    }
}
