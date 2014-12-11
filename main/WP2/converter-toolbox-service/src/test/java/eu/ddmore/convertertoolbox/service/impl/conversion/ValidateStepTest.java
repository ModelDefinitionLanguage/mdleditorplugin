package eu.ddmore.convertertoolbox.service.impl.conversion;

import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

import java.io.File;

import org.apache.commons.io.FileUtils;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.runners.MockitoJUnitRunner;

import eu.ddmore.convertertoolbox.api.conversion.Converter;
import eu.ddmore.convertertoolbox.domain.Conversion;
import eu.ddmore.convertertoolbox.domain.ConversionStatus;
import eu.ddmore.convertertoolbox.service.ConversionRepository;

/**
 * Tests { @list ValidateStep }
 */
@RunWith(MockitoJUnitRunner.class)
public class ValidateStepTest {

    private ValidateStep instance = new ValidateStep();
    @Mock
    private ConversionRepository conversionRepository;
    
    @Mock
    private Conversion conversion;
    
    @Mock
    private Converter converter;
    
    private ConversionContext conversionContext;
    
    @Before
    public void setUp() {
        conversionContext = new ConversionContext(converter, conversion, conversionRepository);
    }
    
    @Test(expected=IllegalStateException.class)
    public void shouldThrowExceptionIfConversionHasNoArchiveSet() {
        when(conversion.getInputFileName()).thenReturn("input-file.txt");
        when(conversion.getStatus()).thenReturn(ConversionStatus.Scheduled);
        instance.execute(conversionContext);
    }

    @Test(expected=IllegalStateException.class)
    public void shouldThrowExceptionIfConversionArchiveDoesNotExist() {
        when(conversion.getInputArchive()).thenReturn(mock(File.class));
        when(conversion.getInputFileName()).thenReturn("input-file.txt");
        when(conversion.getStatus()).thenReturn(ConversionStatus.Scheduled);
        instance.execute(conversionContext);
    }

    @Test(expected=IllegalStateException.class)
    public void shouldThrowExceptionIfConversionHasNoInputFileSpecified() {
        File archive = FileUtils.toFile(ValidateStepTest.class.getResource("/mock-archives/test-input.zip"));
        when(conversion.getInputArchive()).thenReturn(archive);
        when(conversion.getStatus()).thenReturn(ConversionStatus.Scheduled);
        instance.execute(conversionContext);
    }

    @Test(expected=IllegalStateException.class)
    public void shouldThrowExceptionIfConversionIsNotInScheduledState() {
        File archive = FileUtils.toFile(ValidateStepTest.class.getResource("/mock-archives/test-input.zip"));
        when(conversion.getInputArchive()).thenReturn(archive);
        when(conversion.getInputFileName()).thenReturn("input-file.txt");
        when(conversion.getStatus()).thenReturn(ConversionStatus.New);
        instance.execute(conversionContext);
    }

    @Test
    public void shouldPassValidation() {
        File archive = FileUtils.toFile(ValidateStepTest.class.getResource("/mock-archives/test-input.zip"));
        when(conversion.getInputArchive()).thenReturn(archive);
        when(conversion.getInputFileName()).thenReturn("input-file.txt");
        when(conversion.getStatus()).thenReturn(ConversionStatus.Scheduled);
        instance.execute(conversionContext);
    }
}
