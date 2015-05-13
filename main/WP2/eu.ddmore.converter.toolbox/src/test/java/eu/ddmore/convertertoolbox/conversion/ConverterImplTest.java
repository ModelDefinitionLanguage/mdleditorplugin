package eu.ddmore.convertertoolbox.conversion;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;

import java.io.File;
import java.io.IOException;
import java.util.List;

import org.apache.commons.io.FileUtils;
import org.junit.Before;
import org.junit.Test;

import eu.ddmore.convertertoolbox.api.conversion.ConversionListener;
import eu.ddmore.convertertoolbox.api.domain.Version;
import eu.ddmore.convertertoolbox.api.exception.ConverterNotFoundException;
import eu.ddmore.convertertoolbox.api.response.ConversionDetail;
import eu.ddmore.convertertoolbox.api.response.ConversionDetail.Severity;
import eu.ddmore.convertertoolbox.api.response.ConversionReport;
import eu.ddmore.convertertoolbox.api.response.ConversionReport.ConversionCode;
import eu.ddmore.convertertoolbox.domain.VersionImpl;
import eu.ddmore.convertertoolbox.spi.DummyMDLToNMTRAN;
import eu.ddmore.convertertoolbox.spi.DummyMDLToNMTRANFailure;


public class ConverterImplTest {

    private static final String TEST_DATA_DIR = "/test-models/MDL/Product4/";
    private static final String TEST_FILE = "UseCase1.mdl";
    private static final String WORKING_DIR = "target/ConverterImplTest_Working_Dir/";

    private File pkPRED;
    private File outputDir;

    @Before
    public void setUp() throws IOException {
        // Some file valid in source language.
        FileUtils.copyInputStreamToFile(
            ConverterImplTest.class.getResourceAsStream(TEST_DATA_DIR + TEST_FILE),
            new File(WORKING_DIR, TEST_FILE));
        pkPRED = new File(TEST_FILE);
        outputDir = pkPRED.getParentFile();
    }

    @Test
    public void shouldUseDummyConverterSingleFile() throws ConverterNotFoundException, IOException {
        ConverterImpl converter = new ConverterImpl();
        converter.setProvider(new DummyMDLToNMTRAN());
        ConversionReport report = converter.convert(pkPRED, outputDir);
        assertEquals(ConversionCode.SUCCESS, report.getReturnCode());
        assertEquals(0, report.getDetails(Severity.ERROR).size());
        List<ConversionDetail> details = report.getDetails(Severity.ALL);
        assertNotNull(details);
        assertEquals("What a nice conversion!", details.get(0).getInfo().get("INFO"));
        assertEquals("Some message", details.get(0).getMessage());
    }

    @Test
    public void shouldUseDummyConverterSingleFileWithListener() throws ConverterNotFoundException, IOException {
        ConversionListener listener = new ConversionListener() {

            @Override
            public void conversionComplete(ConversionReport report) {
                assertEquals(ConversionCode.SUCCESS, report.getReturnCode());
                assertEquals(0, report.getDetails(Severity.ERROR).size());
            }

        };
        ConverterImpl converter = new ConverterImpl();
        converter.setProvider(new DummyMDLToNMTRAN());
        converter.convert(pkPRED, outputDir, listener);
    }

    @Test
    public void shouldUseDummyConverterFailureWithListener() throws ConverterNotFoundException, IOException {
        ConversionListener listener = new ConversionListener() {

            @Override
            public void conversionComplete(ConversionReport report) {
                assertEquals(ConversionCode.FAILURE, report.getReturnCode());
            }

        };
        ConverterImpl converter = new ConverterImpl();
        converter.setProvider(new DummyMDLToNMTRANFailure());
        converter.convert(pkPRED, outputDir, listener);
    }

    @Test
    public void shouldFindConverterVersion() {
        Version expectedVersion = new VersionImpl(1, 0, 2);
        ConverterImpl converter = new ConverterImpl();
        converter.setProvider(new DummyMDLToNMTRAN());
        Version version = converter.getConverterVersion();
        assertEquals(expectedVersion, version);
    }

}
