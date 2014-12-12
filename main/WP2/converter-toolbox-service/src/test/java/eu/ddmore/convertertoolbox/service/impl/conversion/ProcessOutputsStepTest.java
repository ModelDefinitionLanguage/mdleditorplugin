/*******************************************************************************
 * Copyright (C) 2002 Mango Solutions Ltd - All rights reserved.
 ******************************************************************************/
package eu.ddmore.convertertoolbox.service.impl.conversion;

import static org.junit.Assert.assertTrue;
import static org.mockito.Matchers.eq;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.*;

import java.io.File;
import java.io.IOException;

import org.apache.commons.io.FileUtils;
import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.TemporaryFolder;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.runners.MockitoJUnitRunner;

import eu.ddmore.convertertoolbox.api.conversion.Converter;
import eu.ddmore.convertertoolbox.domain.Conversion;
import eu.ddmore.convertertoolbox.service.ConversionRepository;
import eu.ddmore.convertertoolbox.service.impl.ConversionResourcesConvention;

/**
 * Tests { @link ProcessOutputsStep }
 */
@RunWith(MockitoJUnitRunner.class)
public class ProcessOutputsStepTest {
    private ProcessOutputsStep instance = new ProcessOutputsStep();
    @Mock
    private ConversionRepository conversionRepository;
    
    @Mock
    private Conversion conversion;
    
    @Mock
    private Converter converter;
    
    private ConversionContext conversionContext;

    @Rule
    public TemporaryFolder tempFolder = new TemporaryFolder();
    
    @Before
    public void setUp() {
        conversionContext = new ConversionContext(converter, conversion, conversionRepository);
    }
    
    @Test
    public void shouldZipOutputs() throws IOException {
        File testResource = FileUtils.toFile(ProcessOutputsStepTest.class.getResource("test-archive-extracted"));
        File workingDirectory = tempFolder.newFolder();
        File outputsDirectory = new File(workingDirectory, ConversionResourcesConvention.OUTPUTS_DIRECTORY_NAME);
        File outputArchive = new File(workingDirectory,ConversionResourcesConvention.OUTPUTS_ARCHIVE_NAME);
        FileUtils.copyDirectory(testResource, outputsDirectory);
        when(conversion.getWorkingDirectory()).thenReturn(workingDirectory);
        instance.execute(conversionContext);
        assertTrue(outputArchive.exists());
        verify(conversion).setOutputArchive(eq(outputArchive));
        verify(conversion).setOutputFileSize(any(Long.class));
    }
}
