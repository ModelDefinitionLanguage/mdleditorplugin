package eu.ddmore.convertertoolbox.service.impl;

import org.apache.log4j.Logger;

import com.google.common.base.Preconditions;

import eu.ddmore.convertertoolbox.api.conversion.Converter;
import eu.ddmore.convertertoolbox.domain.Conversion;
import eu.ddmore.convertertoolbox.service.ConversionRepository;

/**
 * Represents a conversion being scheduled and ready to be assigned a working thread to perform conversion
 */
public class ConversionTask implements Runnable {
    private final Logger LOG = Logger.getLogger(ConversionTask.class);
    private final Converter converter;
    private final Conversion conversion;
    private final ConversionRepository conversionRepository;
    /**
     * @param converter
     * @param conversion
     * @param conversionRepository
     */
    public ConversionTask(Converter converter, Conversion conversion, ConversionRepository conversionRepository) {
        super();
        Preconditions.checkNotNull(converter, "Converter can't be null");
        Preconditions.checkNotNull(conversion, "Conversion can't be null");
        this.converter = converter;
        this.conversion = new Conversion(conversion);
        this.conversionRepository = conversionRepository;
    }
    
    @Override
    public void run() {
        LOG.debug(String.format("Performing conversion with id %s from %s to %s using converter %s", conversion.getId(), conversion.getFrom(), conversion.getTo(), converter.toString()));
    }
}
