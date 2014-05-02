/*******************************************************************************
 * Copyright (C) 2013 Mango Solutions Ltd - All rights reserved.
 ******************************************************************************/
package eu.ddmore.converter.test;

import static org.junit.Assert.assertEquals;

import java.io.IOException;

import org.junit.Test;

import eu.ddmore.convertertoolbox.api.exception.ConverterNotFoundException;
import eu.ddmore.convertertoolbox.api.response.ConversionDetail.Severity;
import eu.ddmore.convertertoolbox.api.response.ConversionReport;
import eu.ddmore.convertertoolbox.api.response.ConversionReport.ConversionCode;
import eu.ddmore.convertertoolbox.cli.Main;

/**
 * Test for {@link Main}.
 */
public class CLI_IT {

    @Test
    public void shouldConvertCLI() throws ConverterNotFoundException, IOException {
        String[] args = new String[] { "-in", "files", "-out", "files", "-sn", "MDL", "-sv", "5.0.8-qualm", "-tn", "NMTRAN", "-tv", "7.2.0-qualn" };
        Main cli = new Main();
        cli.parseArguments(args);
        ConversionReport[] reports = cli.runFromCommandLine();
        assertEquals(ConversionCode.SUCCESS, reports[0].getReturnCode());
        assertEquals(reports[0].getDetails(Severity.ERROR).size(), 0);
    }
    
    @Test
    public void shouldConvertCLIDir() throws ConverterNotFoundException, IOException {
        String srcDirPath = Thread.currentThread().getContextClassLoader().getResource("files/").getPath();
        String[] args = new String[] { "-in", srcDirPath, "-out", "files", "-sn", "MDL", "-sv", "5.0.8-qualm", "-tn", "NMTRAN", "-tv", "7.2.0-qualn" };
        Main cli = new Main();
        cli.parseArguments(args);
        ConversionReport[] reports = cli.runFromCommandLine();
        assertEquals(ConversionCode.SUCCESS, reports[0].getReturnCode());
        assertEquals(reports[0].getDetails(Severity.ERROR).size(), 0);
    }
    
}
