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
