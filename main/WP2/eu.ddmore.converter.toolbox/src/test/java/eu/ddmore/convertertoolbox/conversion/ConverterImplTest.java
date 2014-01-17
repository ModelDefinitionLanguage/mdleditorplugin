package eu.ddmore.convertertoolbox.conversion;

import static org.junit.Assert.assertEquals;

import java.io.File;
import java.io.IOException;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import eu.ddmore.convertertoolbox.api.conversion.ConversionListener;
import eu.ddmore.convertertoolbox.api.conversion.Converter;
import eu.ddmore.convertertoolbox.api.domain.Version;
import eu.ddmore.convertertoolbox.api.exception.ConverterNotFoundException;
import eu.ddmore.convertertoolbox.api.response.ConversionDetail.Severity;
import eu.ddmore.convertertoolbox.api.response.ConversionReport;
import eu.ddmore.convertertoolbox.api.response.ConversionReport.ConversionCode;
import eu.ddmore.convertertoolbox.conversion.ConverterImpl;
import eu.ddmore.convertertoolbox.domain.VersionImpl;
import eu.ddmore.convertertoolbox.spi.DummyMDLToNMTRANFailure;

/**
 * Test for {@link ConverterCLIImpl}.
 */
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = { "/SpringBeans.xml" })
public class ConverterImplTest {

    @Autowired
    private ConverterImpl converterProvider1;
    private File pkPRED;
    private File outputDir;

    @Before
    public void initialize() {
        // Some file valid in source language.
        pkPRED = new File(Thread.currentThread().getContextClassLoader().getResource("files/warfarin_PK_PRED.mdl").getPath());
        outputDir = pkPRED.getParentFile();
    }

    @Test
    public void shouldUseDummyConverterSingleFile() throws ConverterNotFoundException, IOException {
        ConversionReport report = converterProvider1.convert(pkPRED, outputDir);
        assertEquals(ConversionCode.SUCCESS, report.getReturnCode());
        assertEquals(report.getDetails(Severity.ERROR).size(), 0);
    }

    @Test
    public void shouldUseDummyConverterMultipleFiles() throws ConverterNotFoundException, IOException {
        File pkBOV = new File(Thread.currentThread().getContextClassLoader().getResource("files/warfarin_PK_BOV.mdl").getPath());
        File[] src = new File[] { pkPRED, pkBOV };
        ConversionReport[] report = converterProvider1.convert(src, outputDir);
        assertEquals(ConversionCode.SUCCESS, report[0].getReturnCode());
        assertEquals(ConversionCode.SUCCESS, report[1].getReturnCode());
        assertEquals(report[0].getDetails(Severity.ERROR).size(), 0);
        assertEquals(report[1].getDetails(Severity.ERROR).size(), 0);
    }

    @Test
    public void shouldUseDummyConverterSingleFileWithListener() throws ConverterNotFoundException, IOException {
        ConversionListener listener = new ConversionListener() {

            @Override
            public void conversionComplete(ConversionReport report) {
                assertEquals(ConversionCode.SUCCESS, report.getReturnCode());
                assertEquals(report.getDetails(Severity.ERROR).size(), 0);
            }

            @Override
            public void conversionComplete(ConversionReport[] report) {
            }
            
        };
        
        converterProvider1.convert(pkPRED, outputDir, listener);
    }
    
    @Test
    public void shouldUseDummyConverterMultipleFilesWithListener() throws ConverterNotFoundException, IOException {
        ConversionListener listener = new ConversionListener() {

            @Override
            public void conversionComplete(ConversionReport report) {
            }

            @Override
            public void conversionComplete(ConversionReport[] report) {
                assertEquals(ConversionCode.SUCCESS, report[0].getReturnCode());
                assertEquals(ConversionCode.SUCCESS, report[1].getReturnCode());
                assertEquals(report[0].getDetails(Severity.ERROR).size(), 0);
                assertEquals(report[1].getDetails(Severity.ERROR).size(), 0);
            }
            
        };
        File pkBOV = new File(Thread.currentThread().getContextClassLoader().getResource("files/warfarin_PK_BOV.mdl").getPath());
        File[] src = new File[] { pkPRED, pkBOV };
        converterProvider1.convert(src, outputDir, listener);
    }
    
    @Test
    public void shouldUseDummyConverterFailureWithListener() throws ConverterNotFoundException, IOException {
        ConversionListener listener = new ConversionListener() {

            @Override
            public void conversionComplete(ConversionReport report) {
            }

            @Override
            public void conversionComplete(ConversionReport[] report) {
                assertEquals(ConversionCode.FAILURE, report[0].getReturnCode());
                assertEquals(ConversionCode.FAILURE, report[1].getReturnCode());
            }
            
        };
        File pkBOV = new File(Thread.currentThread().getContextClassLoader().getResource("files/warfarin_PK_BOV.mdl").getPath());
        File[] src = new File[] { pkPRED, pkBOV };
        ConverterImpl converter = new ConverterImpl();
        converter.setProvider(new DummyMDLToNMTRANFailure());
        converter.convert(src, outputDir, listener);
    }

    @Test
    public void shouldFindConverterVersion() {
        Version expectedVersion = new VersionImpl();
        expectedVersion.setQualifier("qual");
        Version version = converterProvider1.getConverterVersion();
        assertEquals(version, expectedVersion);
    }
}
