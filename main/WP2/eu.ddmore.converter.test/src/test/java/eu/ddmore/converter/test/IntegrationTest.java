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

    static
    {
        Logger rootLogger = Logger.getRootLogger();
        rootLogger.setLevel(Level.INFO);
        rootLogger.addAppender(new ConsoleAppender(
                   new PatternLayout("%-6r [%p] %c - %m%n")));
    }
    
    @Before
    public void init() {
        mdl = new LanguageVersionImpl();
        mdl.setLanguage("MDL");
        Version mdlVersion = new VersionImpl();
        mdlVersion.setMajor(5);
        mdlVersion.setMinor(0);
        mdlVersion.setPatch(8);
        mdlVersion.setQualifier("qualm");
        mdl.setVersion(mdlVersion);

        converterManager = new ConverterManagerImpl();
    }

    private LanguageVersion createPharmMLLanguage() {
        LanguageVersion lang = new LanguageVersionImpl();
        lang.setLanguage("PharmML");
        Version version = new VersionImpl();
        version.setMajor(0);
        version.setMinor(2);
        version.setPatch(1);
        version.setQualifier("qualp");
        lang.setVersion(version);
        return lang;
    }

    private LanguageVersion createNONMEMLanguage() {
        LanguageVersion lang = new LanguageVersionImpl();
        lang.setLanguage("NMTRAN");
        Version version = new VersionImpl();
        version.setMajor(7);
        version.setMinor(2);
        version.setQualifier("qualn");
        lang.setVersion(version);
        return lang;
    }

    @Test
    public void shouldFindLatestConverterMDLToNONMEM() throws ConverterNotFoundException, IOException {
        LanguageVersion nonmem = createNONMEMLanguage();
        Converter converter = converterManager.getConverter(mdl, nonmem);
        Version converterVersion = new VersionImpl();
        converterVersion.setMajor(1);
        converterVersion.setMinor(0);
        converterVersion.setPatch(2);

        assertEquals(converter.getConverterVersion(), converterVersion);
    }

    @Test
    public void shouldFindOldConverterMDLToNONMEM() throws ConverterNotFoundException, IOException {
        LanguageVersion nonmem = createNONMEMLanguage();
        Version converterVersion = new VersionImpl();
        converterVersion.setMajor(1);
        converterVersion.setMinor(0);
        converterVersion.setPatch(1);
        converterManager.getConverter(mdl, nonmem, converterVersion);
    }

    @Test
    public void shouldFindConverterMDLToNONMEMWithVersion() throws ConverterNotFoundException, IOException {
        LanguageVersion nonmem = createNONMEMLanguage();
        Version converterVersion = new VersionImpl();
        converterVersion.setMajor(1);
        converterVersion.setMinor(0);
        converterVersion.setPatch(2);
        assertNotNull(converterManager.getConverter(mdl, nonmem, converterVersion));
    }

    @Test(expected = ConverterNotFoundException.class)
    public void shouldNotFindConverterMDLToNONMEMWithVersion() throws ConverterNotFoundException, IOException {
        LanguageVersion nonmem = createNONMEMLanguage();
        Version converterVersion = new VersionImpl();
        converterVersion.setMajor(1);
        converterVersion.setMinor(0);
        converterVersion.setPatch(3);
        converterManager.getConverter(mdl, nonmem, converterVersion);
    }

    @Test(expected = ConverterNotFoundException.class)
    public void shouldNotFindConverterMDLToNONMEMWithQualifier() throws ConverterNotFoundException, IOException {
        LanguageVersion nonmem = createNONMEMLanguage();
        nonmem.getVersion().setQualifier("someQual");
        Version converterVersion = new VersionImpl();
        converterVersion.setMajor(1);
        converterVersion.setMinor(0);
        converterVersion.setPatch(2);
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
