package eu.ddmore.convertertoolbox.service.impl;

import static org.mockito.Matchers.any;
import static org.mockito.Matchers.eq;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import java.io.File;
import java.io.IOException;

import org.apache.commons.io.FileUtils;
import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.TemporaryFolder;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.powermock.api.mockito.PowerMockito;
import org.powermock.core.classloader.annotations.PrepareForTest;
import org.powermock.modules.junit4.PowerMockRunner;

import com.google.common.base.Optional;

import eu.ddmore.convertertoolbox.domain.Conversion;
import eu.ddmore.convertertoolbox.domain.ConversionStatus;
import eu.ddmore.convertertoolbox.domain.LanguageVersion;
import eu.ddmore.convertertoolbox.domain.Version;
import eu.ddmore.convertertoolbox.service.ConversionRepository;


/**
 * Tests {@link DefaultConversionRemover}
 */
@RunWith(PowerMockRunner.class)
@PrepareForTest(FileUtils.class) 
public class DefaultConversionRemoverTest {

    private DefaultConversionRemover instance;
    
    @Mock
    private ConversionRepository conversionRepository;
    
    @Rule
    private TemporaryFolder folder = new TemporaryFolder();
    
    @Before
    public void setUp() {
        instance = new DefaultConversionRemover(conversionRepository);
    }
    
    @Test(expected = NullPointerException.class)
    public void shouldThrowExceptionForNullConversion() {
        instance.remove(null);
    }

    @Test(expected = IllegalStateException.class)
    public void shouldThrowExceptionForNonExistingConversion() {
        Conversion conversion = createTestConversion("From","To","input.file", ConversionStatus.Completed);
        conversion.setId("MOCK_ID");
        Optional<Conversion> absent = Optional.absent();
        when(conversionRepository.getConversion("MOCK_ID")).thenReturn(absent);
        instance.remove(conversion);
    }

    @Test(expected = IllegalStateException.class)
    public void shouldThrowExceptionForUnFinishedConversion() {
        Conversion conversion = createTestConversion("From","To","input.file", ConversionStatus.Running);
        conversion.setId("MOCK_ID");
        Optional<Conversion> existing = Optional.of(conversion);
        when(conversionRepository.getConversion("MOCK_ID")).thenReturn(existing);
        instance.remove(conversion);
    }

    @Test(expected = RuntimeException.class)
    public void shouldThrowRuntimeExceptionIfDirectoryDeletionFails() throws IOException {
        PowerMockito.mockStatic(FileUtils.class);
        PowerMockito.doThrow(new IOException()).when(FileUtils.class);
        FileUtils.deleteDirectory(any(File.class));
        
        Conversion conversion = createTestConversion("From","To","input.file", ConversionStatus.Completed);
        conversion.setId("MOCK_ID");
        conversion.setWorkingDirectory(folder.newFolder("MOCK_ID"));
        Optional<Conversion> existing = Optional.of(conversion);
        when(conversionRepository.getConversion(eq("MOCK_ID"))).thenReturn(existing);
        
        instance.remove(conversion);
        PowerMockito.verifyStatic(times(0));
    }
    
    @Test
    public void shouldNotTryToRemoveWorkingDirectoryIfItDoesNotExist() {
        PowerMockito.mockStatic(FileUtils.class);
        
        Conversion conversion = createTestConversion("From","To","input.file", ConversionStatus.Completed);
        conversion.setId("MOCK_ID");
        conversion.setWorkingDirectory(new File(folder.getRoot(),"myFolder"));
        Optional<Conversion> existing = Optional.of(conversion);
        when(conversionRepository.getConversion(eq("MOCK_ID"))).thenReturn(existing);
        
        instance.remove(conversion);
        verify(conversionRepository).delete(conversion);
        PowerMockito.verifyStatic(times(0));
    }

    @Test
    public void shouldRemoveTheConversionTogetherWithItsWorkingDirectory() throws IOException {
        PowerMockito.mockStatic(FileUtils.class);
        
        Conversion conversion = createTestConversion("From","To","input.file", ConversionStatus.Completed);
        conversion.setId("MOCK_ID");
        conversion.setWorkingDirectory(folder.newFolder("MOCK_ID"));
        Optional<Conversion> existing = Optional.of(conversion);
        when(conversionRepository.getConversion(eq("MOCK_ID"))).thenReturn(existing);
        
        instance.remove(conversion);

        verify(conversionRepository).delete(conversion);
        PowerMockito.verifyStatic(times(1));
    }
    

    private Conversion createTestConversion(String form, String to, String inputFile, ConversionStatus status) {
        Conversion conversion = new Conversion().setFrom(new LanguageVersion(form,new Version(1, 0, 0, "Q"))).setTo(new LanguageVersion(to,new Version(1, 0, 0, "Q"))).setInputFileName(inputFile);
        conversion.setStatus(status);
        return conversion;
    }
}
