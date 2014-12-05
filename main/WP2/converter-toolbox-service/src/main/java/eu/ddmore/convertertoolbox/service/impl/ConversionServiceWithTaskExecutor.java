package eu.ddmore.convertertoolbox.service.impl;

import java.util.Collection;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.task.TaskExecutor;
import org.springframework.stereotype.Service;

import com.google.common.base.Optional;
import com.google.common.base.Preconditions;

import eu.ddmore.convertertoolbox.api.conversion.Converter;
import eu.ddmore.convertertoolbox.api.conversion.ConverterManager;
import eu.ddmore.convertertoolbox.api.exception.ConverterNotFoundException;
import eu.ddmore.convertertoolbox.domain.Conversion;
import eu.ddmore.convertertoolbox.domain.ConversionStatus;
import eu.ddmore.convertertoolbox.service.ConversionRepository;
import eu.ddmore.convertertoolbox.service.ConversionService;
import eu.ddmore.convertertoolbox.service.ExceededCapacity;

/**
 * ConversionService implementation that uses a TaskExecutor for executing conversions
 */
@Service
public class ConversionServiceWithTaskExecutor implements ConversionService {
    @Autowired(required=true)
    private ConverterManager converterManager;
    @Autowired(required=true)
    @Qualifier("conversionTaskExecutor")
    private TaskExecutor conversionTaskExecutor;
    @Autowired(required=true)
    private ConversionRepository conversionRepository;
    
    @Value("${cts.serviceCapacity}")
    private int serviceCapacity = -1;
    
    @Override
    public synchronized Conversion schedule(Conversion conversion) throws ExceededCapacity {
        Preconditions.checkNotNull(conversion,"Conversion was null");

        if(isFull()) {
            throw new ExceededCapacity("Exceeded capacity.");
        }
        
        Converter converter = null;
        try {
            converter = converterManager.getConverter(conversion.getFrom(), conversion.getTo());
        } catch (ConverterNotFoundException e) {
            throw new IllegalArgumentException("Conversion not supported", e);
        }
        Preconditions.checkNotNull(converter, "Converter returned by Converter Manager was null");
        
        Conversion persistedConversion = conversionRepository.save(conversion);
        
        ConversionTask conversionTask = createConversionTask(persistedConversion, converter);
        
        conversionTaskExecutor.execute(conversionTask);
        
        return persistedConversion;
    }

    private ConversionTask createConversionTask(Conversion conversion, Converter converter) {
        return new ConversionTask(converter, conversion);
    }

    @Override
    public boolean isFull() {
        return conversionRepository.countUncompletedConversions()>=serviceCapacity;
    }

    @Override
    public Collection<Conversion> getConversions() {
        return conversionRepository.getConversions();
    }

    @Override
    public Collection<Conversion> getCompletedConversions() {
        return conversionRepository.getConversionsWithStatus(ConversionStatus.Completed);
    }

    @Override
    public Optional<Conversion> getConversionForId(String id) {
        return conversionRepository.getConversion(id);
    }

    void complete(Conversion conversion) {
        
    }
    
    
    public void setServiceCapacity(int serviceCapacity) {
        this.serviceCapacity = serviceCapacity;
    }
}
