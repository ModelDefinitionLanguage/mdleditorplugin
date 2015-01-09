/*******************************************************************************
 * Copyright (C) 2002 Mango Solutions Ltd - All rights reserved.
 ******************************************************************************/
package eu.ddmore.convertertoolbox.service.impl;

import java.util.Arrays;
import java.util.Collection;
import static org.mockito.Mockito.*;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.runners.MockitoJUnitRunner;

import eu.ddmore.convertertoolbox.domain.internal.Conversion;
import eu.ddmore.convertertoolbox.service.ConversionRepository;

/**
 * Tests { @link ConversionReaper }
 */
@RunWith(MockitoJUnitRunner.class)
public class ConversionReaperTest {
    @InjectMocks
    private ConversionReaper instance = new ConversionReaper();

    @Mock
    private ConversionRemover conversionRemover;
    
    @Mock
    private ConversionRepository conversionRepository;
    
    @Test
    public void shouldTriggerRemovalForEachConversionReturnedFromRepository() {
        Collection<Conversion> conversions = Arrays.asList(
            mock(Conversion.class), mock(Conversion.class), mock(Conversion.class));
        when(conversionRepository.getConversionsCompletedEarlierThan(any(Long.class))).thenReturn(conversions);
        
        instance.setConversionResultsAvailabilityTimeout(1000);
        
        instance.performCleanup();
        
        for(Conversion conversion : conversions) {
            verify(conversionRemover).remove(conversion);
        }
    }

    @Test
    public void shouldNotRethrowConversionRemoverExceptions() {
        Collection<Conversion> conversions = Arrays.asList(
            mock(Conversion.class), mock(Conversion.class), mock(Conversion.class));
        when(conversionRepository.getConversionsCompletedEarlierThan(any(Long.class))).thenReturn(conversions);
        doThrow(Exception.class).when(conversionRemover).remove(any(Conversion.class));
        
        instance.setConversionResultsAvailabilityTimeout(1000);
        
        instance.performCleanup();
    }
}
