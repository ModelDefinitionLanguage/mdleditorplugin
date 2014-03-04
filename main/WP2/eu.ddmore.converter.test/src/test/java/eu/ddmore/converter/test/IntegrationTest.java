/*******************************************************************************
 * Copyright (C) 2013 Mango Solutions Ltd - All rights reserved.
 ******************************************************************************/
package eu.ddmore.converter.test;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;

import java.io.IOException;
import java.util.Collection;
import java.util.Map;

import org.apache.log4j.ConsoleAppender;
import org.apache.log4j.Level;
import org.apache.log4j.Logger;
import org.apache.log4j.PatternLayout;
import org.junit.Before;
import org.junit.Test;

import eu.ddmore.convertertoolbox.api.conversion.Converter;
import eu.ddmore.convertertoolbox.api.domain.LanguageVersion;
import eu.ddmore.convertertoolbox.api.domain.Version;
import eu.ddmore.convertertoolbox.api.exception.ConverterNotFoundException;
import eu.ddmore.convertertoolbox.api.response.ConversionReport;
import eu.ddmore.convertertoolbox.api.response.ConversionDetail.Severity;
import eu.ddmore.convertertoolbox.api.response.ConversionReport.ConversionCode;
import eu.ddmore.convertertoolbox.cli.Main;
import eu.ddmore.convertertoolbox.conversion.ConverterManagerImpl;
import eu.ddmore.convertertoolbox.domain.LanguageVersionImpl;
import eu.ddmore.convertertoolbox.domain.VersionImpl;

/**
 * Test for {@link ConverterManagerImpl}.
 */
public class IntegrationTest {

    private ConverterManagerImpl converterManager;

    private LanguageVersion mdl;

    static {
        Logger rootLogger = Logger.getRootLogger();
        rootLogger.setLevel(Level.INFO);
        rootLogger.addAppender(new ConsoleAppender(new PatternLayout("%-6r [%p] %c - %m%n")));
    }

    @Before
    public void init() {
        Version mdlVersion = new VersionImpl(5, 0, 8, "qualm");
        mdl = new LanguageVersionImpl("MDL", mdlVersion);
        converterManager = new ConverterManagerImpl();
    }

    private LanguageVersion createPharmMLLanguage() {
        Version version = new VersionImpl(0, 2, 1, "qualp");
        LanguageVersion lang = new LanguageVersionImpl("PharmML", version);
        return lang;
    }

    private LanguageVersion createNONMEMLanguage() {
        Version version = new VersionImpl(7, 2, 0, "qualn");
        LanguageVersion lang = new LanguageVersionImpl("NMTRAN", version);
        return lang;
    }

    @Test
    public void shouldFindLatestConverterMDLToNONMEM() throws ConverterNotFoundException, IOException {
        LanguageVersion nonmem = createNONMEMLanguage();
        Converter converter = converterManager.getConverter(mdl, nonmem);
        Version converterVersion = new VersionImpl(1, 0, 2, null);
        assertEquals(converter.getConverterVersion(), converterVersion);
    }

    @Test
    public void shouldFindOldConverterMDLToNONMEM() throws ConverterNotFoundException, IOException {
        LanguageVersion nonmem = createNONMEMLanguage();
        Version converterVersion = new VersionImpl(1, 0, 1);
        converterManager.getConverter(mdl, nonmem, converterVersion);
    }

    @Test
    public void shouldFindConverterMDLToNONMEMWithVersion() throws ConverterNotFoundException, IOException {
        LanguageVersion nonmem = createNONMEMLanguage();
        Version converterVersion = new VersionImpl(1, 0, 2, null);
        assertNotNull(converterManager.getConverter(mdl, nonmem, converterVersion));
    }

    @Test(expected = ConverterNotFoundException.class)
    public void shouldNotFindConverterMDLToNONMEMWithVersion() throws ConverterNotFoundException, IOException {
        LanguageVersion nonmem = createNONMEMLanguage();
        Version converterVersion = new VersionImpl(1, 0, 3, null);
        converterManager.getConverter(mdl, nonmem, converterVersion);
    }

    @Test(expected = ConverterNotFoundException.class)
    public void shouldNotFindConverterMDLToNONMEMWithQualifier() throws ConverterNotFoundException, IOException {        
        Version version = new VersionImpl(7, 2, 0, "someQual");
        LanguageVersion nonmem = new LanguageVersionImpl("NMTRAN", version);
        Version converterVersion = new VersionImpl(1, 0, 2, null);
        converterManager.getConverter(mdl, nonmem, converterVersion);
    }

    @Test
    public void shouldFindConverterMDLToPharmML() throws ConverterNotFoundException, IOException {
        LanguageVersion pharmaml = createPharmMLLanguage();
        assertNotNull(converterManager.getConverter(mdl, pharmaml));
    }

    @Test(expected = ConverterNotFoundException.class)
    public void shouldNotFindConvertor() throws ConverterNotFoundException {
        LanguageVersion nonmem = createNONMEMLanguage();
        LanguageVersion pharmaml = createPharmMLLanguage();
        converterManager.getConverter(pharmaml, nonmem);
    }

    @Test
    public void shouldFindCapabilities() throws ConverterNotFoundException, IOException {
        Map<LanguageVersion, Collection<LanguageVersion>> sourceToTarget = converterManager.getCapabilities();
        assertNotNull(sourceToTarget);
        Collection<LanguageVersion> targetVersions = sourceToTarget.get(mdl);
        assertNotNull(targetVersions);
        assertNotNull(targetVersions.contains(createNONMEMLanguage()));
        assertNotNull(targetVersions.contains(createPharmMLLanguage()));
    }

    @Test
    public void shouldConvertCLI() throws ConverterNotFoundException, IOException {
        String[] args = new String[] { "files", "files", "MDL", "5.0.8-qualm", "NMTRAN", "7.2.0-qualn" };
        ConversionReport[] reports = new Main().runFromCommandLine(args);
        assertEquals(ConversionCode.SUCCESS, reports[0].getReturnCode());
        assertEquals(reports[0].getDetails(Severity.ERROR).size(), 0);
    }
}
