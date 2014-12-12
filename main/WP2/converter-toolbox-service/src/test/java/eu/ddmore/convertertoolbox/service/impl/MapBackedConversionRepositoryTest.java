/*******************************************************************************
 * Copyright (C) 2002 Mango Solutions Ltd - All rights reserved.
 ******************************************************************************/
package eu.ddmore.convertertoolbox.service.impl;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

import org.junit.Before;
import org.junit.Test;

import com.google.common.base.Optional;

import eu.ddmore.convertertoolbox.domain.Conversion;
import eu.ddmore.convertertoolbox.domain.ConversionStatus;
import eu.ddmore.convertertoolbox.domain.LanguageVersion;
import eu.ddmore.convertertoolbox.domain.Version;
import eu.ddmore.convertertoolbox.service.ExceededCapacity;

/**
 * Tests {@link MapBackedConversionRepository}
 */
public class MapBackedConversionRepositoryTest {

    private MapBackedConversionRepository instance = new MapBackedConversionRepository();
    @Before
    public void setUp() throws Exception {
        instance.save(createTestConversion("FROM", "TO", "mock/input/file", ConversionStatus.Completed,1));
        instance.save(createTestConversion("FROM", "TO", "mock/input/file", ConversionStatus.Completed,2));
        instance.save(createTestConversion("FROM", "TO", "mock/input/file", ConversionStatus.Completed,3));
        instance.save(createTestConversion("FROM", "TO", "mock/input/file", ConversionStatus.Scheduled,0));
        instance.save(createTestConversion("FROM", "TO", "mock/input/file", ConversionStatus.Scheduled,0));
        instance.save(createTestConversion("FROM", "TO", "mock/input/file", ConversionStatus.Scheduled,0));
    }

    @Test
    public void getConversions_shouldReturnCollectionOfAllConversions() throws ExceededCapacity {
        
        assertEquals(instance.getConversions().size(),6);
    }

    @Test
    public void getCompletedConversions_shouldReturnACollectionOfCompletedConversions() throws ExceededCapacity {
        assertEquals(instance.getConversionsWithStatus(ConversionStatus.Completed).size(),3);
    }

    @Test
    public void getConversionForId_shouldReturnOptionalWithConversionForSpecifiedId() throws ExceededCapacity {
        Conversion existingConversion = instance.getConversions().iterator().next();
        Optional<Conversion> conversion = instance.getConversion(existingConversion.getId());
        assertTrue(conversion.isPresent());
        assertEquals(existingConversion, conversion.get());
    }

    @Test
    public void getConversionForId_shouldReturnOptionalWithNoConversionIfSpecifiedIdDoesntMatchConversion() {
        Optional<Conversion> conversion = instance.getConversion("non-existing-conversion");
        assertFalse(conversion.isPresent());
    }
    

    @Test
    public void getConversionsCompletedEarlierThan_shouldReturnConversionsCompletedBeforeGivenDate() {
        assertEquals(instance.getConversionsCompletedEarlierThan(3).size(),2);
    }

    private Conversion createTestConversion(String form, String to, String inputFile, ConversionStatus status, long completionTime) {
        Conversion conversion = new Conversion().setFrom(new LanguageVersion(form,new Version(1, 0, 0, "Q"))).
                setTo(new LanguageVersion(to,new Version(1, 0, 0, "Q"))).
                setInputFileName(inputFile);
        if(ConversionStatus.Completed.equals(status)) {
                conversion.setCompletionTime(completionTime);
        }
        conversion.setStatus(status);
        return conversion;
    }
    
}
