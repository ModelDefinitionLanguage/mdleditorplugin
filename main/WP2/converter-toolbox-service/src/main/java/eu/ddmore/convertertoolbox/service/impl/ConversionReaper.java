/*******************************************************************************
 * Copyright (C) 2002 Mango Solutions Ltd - All rights reserved.
 ******************************************************************************/
package eu.ddmore.convertertoolbox.service.impl;

import java.util.Collection;
import java.util.Date;
import java.util.concurrent.TimeUnit;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import eu.ddmore.convertertoolbox.domain.Conversion;
import eu.ddmore.convertertoolbox.service.ConversionRepository;


/**
 * Component responsible for cleaning up old Conversions and data
 */
@Component
public class ConversionReaper {

    private static final Logger LOG = Logger.getLogger(ConversionReaper.class);
    
    @Autowired(required=true)
    private ConversionRepository conversionRepository;
    
    @Autowired(required=true)
    private ConversionRemover conversionRemover;
    
    @Value("${cts.conversionResultsAvailabilityTimeout}")
    private long conversionResultsAvailabilityTimeout = TimeUnit.HOURS.toMillis(2);
    
    @Scheduled(fixedRateString = "${cts.cleanupRate:300000}")
    public void performCleanup() {
        Collection<Conversion> forDeletion = conversionRepository.
                getConversionsCompletedEarlierThan(new Date().getTime()-conversionResultsAvailabilityTimeout);
        for(Conversion conversion : forDeletion) {
            try {
                conversionRemover.remove(conversion);
            } catch(Exception ex) {
                LOG.error("Error when trying to remove old conversion",ex);
            }
        }
    }

    public void setConversionResultsAvailabilityTimeout(long conversionResultsAvailabilityTimeout) {
        this.conversionResultsAvailabilityTimeout = conversionResultsAvailabilityTimeout;
    }
}
