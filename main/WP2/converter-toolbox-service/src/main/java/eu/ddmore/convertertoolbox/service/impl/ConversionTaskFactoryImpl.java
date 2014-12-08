package eu.ddmore.convertertoolbox.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Component;

import eu.ddmore.convertertoolbox.api.conversion.Converter;
import eu.ddmore.convertertoolbox.domain.Conversion;
import eu.ddmore.convertertoolbox.service.ConversionRepository;

/**
 * Default implementation
 */
@Component
@Qualifier("conversionTaskFactory")
public class ConversionTaskFactoryImpl implements ConversionTaskFactory {
    
    @Autowired(required=true)
    private ConversionRepository conversionRepository;
    
    @Override
    public ConversionTask create(Conversion conversion, Converter converter) {
        return new ConversionTask(converter, conversion,conversionRepository);
    }
}
