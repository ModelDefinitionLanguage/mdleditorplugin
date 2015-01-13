/*******************************************************************************
 * Copyright (C) 2015 Mango Solutions Ltd - All rights reserved.
 ******************************************************************************/
package eu.ddmore.convertertoolbox.service.impl.conversion;

import static org.mockito.Matchers.any;
import static org.mockito.Matchers.eq;
import static org.mockito.Mockito.doThrow;
import static org.mockito.Mockito.mock;
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
import org.mockito.runners.MockitoJUnitRunner;

import eu.ddmore.convertertoolbox.api.conversion.Converter;
import eu.ddmore.convertertoolbox.api.response.ConversionReport.ConversionCode;
import eu.ddmore.convertertoolbox.domain.ConversionReport;
import eu.ddmore.convertertoolbox.domain.internal.Conversion;
import eu.ddmore.convertertoolbox.service.ConversionRepository;
import eu.ddmore.convertertoolbox.service.impl.ConversionResourcesConvention;


/**
 * Tests { @link ExecuteConversionStep }
 */
@RunWith(MockitoJUnitRunner.class)
public class ExecuteConversionStepTest {
    private ConversionStep instance = new ExecuteConversionStep();
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
    public void shouldInvokeConverterWithConversionParameters() throws IOException {
        String inputFileName = "inputFile";
        File inputsDirectory = tempFolder.newFolder(ConversionResourcesConvention.INPUTS_DIRECTORY_NAME);
        File outputsDirectory = tempFolder.newFolder(ConversionResourcesConvention.OUTPUTS_DIRECTORY_NAME);
        File inputFile = new File(inputsDirectory,inputFileName);
        FileUtils.writeStringToFile(inputFile, "input");
        
        when(conversion.getInputFileName()).thenReturn(inputFileName);
        when(conversion.getWorkingDirectory()).thenReturn(tempFolder.getRoot());
        eu.ddmore.convertertoolbox.api.response.ConversionReport conversionReport = mock(eu.ddmore.convertertoolbox.api.response.ConversionReport.class);
        when(conversionReport.getReturnCode()).thenReturn(ConversionCode.SUCCESS);
        
        when(converter.convert(any(File.class), any(File.class))).thenReturn(conversionReport);
        
        instance.execute(conversionContext);
        
        verify(converter).convert(eq(inputFile), eq(outputsDirectory));
        verify(conversion).setConversionReport(any(ConversionReport.class));
    }

    @Test(expected=IllegalStateException.class)
    public void shouldWrapAnyExceptionInRuntimeExceptionAndRethrow() throws IOException {
        String inputFileName = "inputFile";
        File inputsDirectory = tempFolder.newFolder(ConversionResourcesConvention.INPUTS_DIRECTORY_NAME);
        File inputFile = new File(inputsDirectory,inputFileName);
        FileUtils.writeStringToFile(inputFile, "input");
        when(conversion.getInputFileName()).thenReturn(inputFileName);
        when(conversion.getWorkingDirectory()).thenReturn(tempFolder.getRoot());
        
        doThrow(Exception.class).when(converter).convert(any(File.class), any(File.class));

        instance.execute(conversionContext);
    }

}
