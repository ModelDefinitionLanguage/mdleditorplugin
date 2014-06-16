/*******************************************************************************
 * Copyright (C) 2013 Mango Solutions Ltd - All rights reserved.
 ******************************************************************************/
package eu.ddmore.converter.test;

import static org.junit.Assert.assertEquals;

import java.io.File;
import java.io.IOException;

import org.apache.commons.io.FileUtils;
import org.junit.Before;
import org.junit.Test;

import eu.ddmore.convertertoolbox.api.exception.ConverterNotFoundException;
import eu.ddmore.convertertoolbox.api.response.ConversionDetail.Severity;
import eu.ddmore.convertertoolbox.api.response.ConversionReport;
import eu.ddmore.convertertoolbox.api.response.ConversionReport.ConversionCode;
import eu.ddmore.convertertoolbox.cli.Main;

/**
 * Test for {@link Main}.
 */
public class MainIT {

    private final static String modelFilePath = "/eu/ddmore/testdata/models/mdl/warfarin_PK_PRED/warfarin_PK_PRED.mdl";
    private final static String WORKING_DIRECTORY = "target/MainIT_Working_Dir/";

    @Before
    public void setUp() throws IOException {
        FileUtils.deleteDirectory(new File(WORKING_DIRECTORY));
        FileUtils.copyInputStreamToFile(
            MainIT.class.getResourceAsStream(modelFilePath),
            new File(WORKING_DIRECTORY, "warfarin_PK_PRED.mdl"));
    }

    @Test
    public void shouldConvertCLI() throws ConverterNotFoundException, IOException {
        String[] args = new String[] { "-in", WORKING_DIRECTORY, "-out", WORKING_DIRECTORY + "output", "-sn", "MDL", "-sv", "5.0.8-qualm", "-tn", "NMTRAN", "-tv", "7.2.0-qualn" };
        Main cli = new Main();
        cli.parseArguments(args);
        ConversionReport[] reports = cli.runFromCommandLine();
        assertEquals(ConversionCode.SUCCESS, reports[0].getReturnCode());
        assertEquals(reports[0].getDetails(Severity.ERROR).size(), 0);
    }

    @Test
    public void shouldConvertCLI_AbsolutePath() throws ConverterNotFoundException, IOException {
        String[] args = new String[] { "-in", new File(WORKING_DIRECTORY).getAbsolutePath(), "-out", new File(WORKING_DIRECTORY, "output").getAbsolutePath(), "-sn", "MDL", "-sv", "5.0.8-qualm", "-tn", "NMTRAN", "-tv", "7.2.0-qualn" };
        Main cli = new Main();
        cli.parseArguments(args);
        ConversionReport[] reports = cli.runFromCommandLine();
        assertEquals(ConversionCode.SUCCESS, reports[0].getReturnCode());
        assertEquals(reports[0].getDetails(Severity.ERROR).size(), 0);
    }

}
