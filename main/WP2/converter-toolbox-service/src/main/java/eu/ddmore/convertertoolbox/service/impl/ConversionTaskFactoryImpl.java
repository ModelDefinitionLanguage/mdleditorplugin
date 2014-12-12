/*******************************************************************************
 * Copyright (C) 2002 Mango Solutions Ltd - All rights reserved.
 ******************************************************************************/
package eu.ddmore.convertertoolbox.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Component;

import eu.ddmore.convertertoolbox.api.conversion.Converter;
import eu.ddmore.convertertoolbox.domain.Conversion;
import eu.ddmore.convertertoolbox.service.ConversionRepository;
import eu.ddmore.convertertoolbox.service.impl.conversion.ConversionContext;
import eu.ddmore.convertertoolbox.service.impl.conversion.ConversionStep;
import eu.ddmore.convertertoolbox.service.impl.conversion.ConversionTask;

/**
 * Default implementation of { @link ConversionTaskFactory }
 */
@Component
@Qualifier("conversionTaskFactory")
public class ConversionTaskFactoryImpl implements ConversionTaskFactory {
    
    @Autowired(required=true)
    private ConversionRepository conversionRepository;

    @Autowired(required=true)
    private List<ConversionStep> conversionSteps;
    
    @Override
    public ConversionTask create(Conversion conversion, Converter converter) {
        ConversionTask conversionTask = new ConversionTask(new ConversionContext(converter, new Conversion(conversion), conversionRepository));
        conversionTask.setConversionSteps(getConversionSteps());
        return conversionTask;
    }

    private List<ConversionStep> getConversionSteps() {
        return conversionSteps;
    }
}
