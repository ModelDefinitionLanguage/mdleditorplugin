package eu.ddmore.convertertoolbox.cli;

import static org.junit.Assert.assertEquals;

import java.io.File;
import java.io.IOException;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.AssertThrows;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import eu.ddmore.convertertoolbox.api.cli.ConverterCLI;
import eu.ddmore.convertertoolbox.api.exception.ConverterNotFoundException;
import eu.ddmore.convertertoolbox.api.response.ConversionDetail.Severity;
import eu.ddmore.convertertoolbox.api.response.ConversionReport;
import eu.ddmore.convertertoolbox.api.response.ConversionReport.ConversionCode;

/**
 * Test for {@link ConverterCLIImpl}.
 */
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = { "/SpringBeans.xml" })
public class ConverterCLIImplTest {

    @Autowired
    private ConverterCLI cliConverter;
    private File pkPRED;
    private File outputDir;

    @Before
    public void initialize() {
        // Some file valid in source language.
        pkPRED = new File(Thread.currentThread().getContextClassLoader().getResource("files/warfarin_PK_PRED.mdl").getPath());
        outputDir = pkPRED.getParentFile();
    }

    @Test
    public void shouldUseDummyCLIConverterSingleFile() throws ConverterNotFoundException, IOException {
        ConversionReport report = cliConverter.convert(pkPRED, "MDL", "qual", "NONMEM", "qual", outputDir);
        assertEquals(ConversionCode.SUCCESS, report.getReturnCode());
        assertEquals(report.getDetails(Severity.ERROR).size(), 0);
    }

    @Test
    public void shouldUseDummyCLIConverterMultipleFiles() throws ConverterNotFoundException, IOException {
        File pkBOV = new File(Thread.currentThread().getContextClassLoader().getResource("files/warfarin_PK_BOV.mdl").getPath());
        File[] src = new File[] { pkPRED, pkBOV };
        ConversionReport[] report = cliConverter.convert(src, "MDL", "qual", "NONMEM", "qual", outputDir);
        assertEquals(ConversionCode.SUCCESS, report[0].getReturnCode());
        assertEquals(ConversionCode.SUCCESS, report[1].getReturnCode());
    }

    @Test(expected = ConverterNotFoundException.class)
    public void shouldNotFindConverter() throws ConverterNotFoundException, IOException {
        File pkBOV = new File(Thread.currentThread().getContextClassLoader().getResource("files/warfarin_PK_BOV.mdl").getPath());
        File[] src = new File[] { pkPRED, pkBOV };
        cliConverter.convert(src, "MDL x", "qual", "NONMEM", "qual", outputDir);
    }
}
