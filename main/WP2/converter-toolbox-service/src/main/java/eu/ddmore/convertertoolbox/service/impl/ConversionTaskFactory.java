/*******************************************************************************
 * Copyright (C) 2002 Mango Solutions Ltd - All rights reserved.
 ******************************************************************************/
package eu.ddmore.convertertoolbox.service.impl;

import eu.ddmore.convertertoolbox.api.conversion.Converter;
import eu.ddmore.convertertoolbox.domain.internal.Conversion;
import eu.ddmore.convertertoolbox.service.impl.conversion.ConversionTask;


/**
 * Creates instances of { @link ConversionTask }
 */
public interface ConversionTaskFactory {
    
    /**
     * Creates new instance of ConversionTask 
     * @param conversion 
     * @param converter
     * @return conversion task
     */
    ConversionTask create(Conversion conversion, Converter converter);
}
