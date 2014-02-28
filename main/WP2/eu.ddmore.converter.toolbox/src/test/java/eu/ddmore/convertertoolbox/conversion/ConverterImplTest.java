package eu.ddmore.convertertoolbox.conversion;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;

import java.io.File;
import java.io.IOException;
import java.util.List;

import org.junit.Before;
import org.junit.Test;

import eu.ddmore.convertertoolbox.api.conversion.ConversionListener;
import eu.ddmore.convertertoolbox.api.domain.Version;
import eu.ddmore.convertertoolbox.api.exception.ConverterNotFoundException;
import eu.ddmore.convertertoolbox.api.response.ConversionDetail.Severity;
import eu.ddmore.convertertoolbox.api.response.ConversionDetail;
import eu.ddmore.convertertoolbox.api.response.ConversionReport;
import eu.ddmore.convertertoolbox.api.response.ConversionReport.ConversionCode;
import eu.ddmore.convertertoolbox.cli.Main;
import eu.ddmore.convertertoolbox.domain.VersionImpl;
import eu.ddmore.convertertoolbox.spi.DummyMDLToNMTRAN;
import eu.ddmore.convertertoolbox.spi.DummyMDLToNMTRANFailure;

/**
 * Test for {@link Main}.
 */
public class ConverterImplTest {
    
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
        ConverterImpl converter = new ConverterImpl();
        converter.setProvider(new DummyMDLToNMTRAN());
        ConversionReport report = converter.convert(pkPRED, outputDir);
        assertEquals(ConversionCode.SUCCESS, report.getReturnCode());
        assertEquals(report.getDetails(Severity.ERROR).size(), 0);
        List<ConversionDetail> details = report.getDetails(Severity.ALL);
        assertNotNull(details);
        assertEquals(details.get(0).getInfo().get("INFO"), "What a nice conversion!");
        assertEquals(details.get(0).getMessage(), "Some message");
        
    }

    @Test
    public void shouldUseDummyConverterMultipleFiles() throws ConverterNotFoundException, IOException {
        File pkBOV = new File(Thread.currentThread().getContextClassLoader().getResource("files/warfarin_PK_BOV.mdl").getPath());
        File[] src = new File[] { pkPRED, pkBOV };
        ConverterImpl converter = new ConverterImpl();
        converter.setProvider(new DummyMDLToNMTRAN());
        ConversionReport[] report = converter.convert(src, outputDir);
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
        ConverterImpl converter = new ConverterImpl();
        converter.setProvider(new DummyMDLToNMTRAN());
        converter.convert(pkPRED, outputDir, listener);
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
        ConverterImpl converter = new ConverterImpl();
        converter.setProvider(new DummyMDLToNMTRAN());
        converter.convert(src, outputDir, listener);
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
        Version expectedVersion = new VersionImpl(1,0,2,null);
        ConverterImpl converter = new ConverterImpl();
        converter.setProvider(new DummyMDLToNMTRAN());
        Version version = converter.getConverterVersion();
        assertEquals(version, expectedVersion);
    }
}
