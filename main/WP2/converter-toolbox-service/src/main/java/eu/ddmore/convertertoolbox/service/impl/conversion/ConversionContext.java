/*******************************************************************************
 * Copyright (C) 2002 Mango Solutions Ltd - All rights reserved.
 ******************************************************************************/
package eu.ddmore.convertertoolbox.service.impl.conversion;

import com.google.common.base.Preconditions;

import eu.ddmore.convertertoolbox.api.conversion.Converter;
import eu.ddmore.convertertoolbox.domain.internal.Conversion;
import eu.ddmore.convertertoolbox.service.ConversionRepository;

/**
 * Represents holds conversion context information
 */
public class ConversionContext {
    private final Converter converter;
    private Conversion conversion;
    private final ConversionRepository conversionRepository;
    /**
     * Creates new conversion context
     * @param converter
     * @param conversion
     * @param conversionRepository
     */
    public ConversionContext(Converter converter, Conversion conversion, ConversionRepository conversionRepository) {
        super();
        Preconditions.checkNotNull(converter, "Converter can't be null");
        Preconditions.checkNotNull(conversion, "Conversion can't be null");
        Preconditions.checkNotNull(conversionRepository, "Converter Repository can't be null");
        this.converter = converter;
        this.conversion = conversion;
        this.conversionRepository = conversionRepository;
    }
    
    public Conversion getConversion() {
        return conversion;
    }

    public Converter getConverter() {
        return converter;
    }
    
    void updateConversion(Conversion conversion) {
        this.conversion = conversionRepository.save(conversion);
    }
}
