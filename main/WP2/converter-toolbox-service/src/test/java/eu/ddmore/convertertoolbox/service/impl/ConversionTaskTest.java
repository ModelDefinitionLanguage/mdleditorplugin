package eu.ddmore.convertertoolbox.service.impl;

import static org.junit.Assert.fail;

import org.junit.Before;
import org.junit.Ignore;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.runners.MockitoJUnitRunner;

import eu.ddmore.convertertoolbox.api.conversion.Converter;
import eu.ddmore.convertertoolbox.domain.Conversion;
import eu.ddmore.convertertoolbox.service.ConversionRepository;


/**
 * Tests { @link ConversionTask }
 */
@RunWith(MockitoJUnitRunner.class)
@Ignore("Not implemented yet")
public class ConversionTaskTest {
    private ConversionTask instance;
    
    @Mock
    private Conversion conversion;
    
    @Mock
    private Converter converter;

    @Mock
    private ConversionRepository conversionRepository;
    
    @Before
    public void setUp() throws Exception {
        instance = new ConversionTask(converter, conversion, conversionRepository);
    }

    @Test
    public void shouldThrowExceptionIfConverterIsNull() {
        fail("Not yet implemented");
    }
    
    @Test
    public void shouldThrowExceptionIfConversionIsNull() {
        fail("Not yet implemented");
    }
    
    @Test
    public void shouldThrowExceptionIfConversionHasNoArchiveSet() {
        fail("Not yet implemented");
    }

    @Test
    public void shouldThrowExceptionIfConversionHasNoInputFileSpecified() {
        fail("Not yet implemented");
    }

    @Test
    public void shouldThrowExceptionIfConversionHasNoOutputFileName() {
        fail("Not yet implemented");
    }

    @Test
    public void shouldUnzipInputs() {
        fail("Not yet implemented");
    }
    
    @Test
    public void shouldInvokeConverterWithParametersFromConversion() {
        fail("Not yet implemented");
    }

    @Test
    public void shouldPopulateConversionWithResultsOfTheConversion_AND_persistIt() {
        fail("Not yet implemented");
    }
    
    @Test
    public void shouldFailTheConversionInCaseOfAnyException_AND_persistIt() {
        fail("Not yet implemented");
    }
}
