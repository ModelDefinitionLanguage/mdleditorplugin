/*******************************************************************************
 * Copyright (C) 2013 Mango Solutions Ltd - All rights reserved.
 ******************************************************************************/
package eu.ddmore.converter.test;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.Map;

import org.junit.Before;
import org.junit.Test;

import eu.ddmore.convertertoolbox.api.conversion.Converter;
import eu.ddmore.convertertoolbox.api.domain.LanguageVersion;
import eu.ddmore.convertertoolbox.api.domain.Version;
import eu.ddmore.convertertoolbox.api.exception.ConverterNotFoundException;
import eu.ddmore.convertertoolbox.api.response.ConversionDetail;
import eu.ddmore.convertertoolbox.api.response.ConversionDetail.Severity;
import eu.ddmore.convertertoolbox.api.response.ConversionReport;
import eu.ddmore.convertertoolbox.conversion.ConverterManagerImpl;
import eu.ddmore.convertertoolbox.domain.LanguageVersionImpl;
import eu.ddmore.convertertoolbox.domain.VersionImpl;
import eu.ddmore.convertertoolbox.response.ConversionDetailImpl;

/**
 * Test for {@link ConverterManagerImpl}.
 */
public class ConverterManagerImplIT {

    private ConverterManagerImpl converterManager;

    private LanguageVersion mdl;

    @Before
    public void init() {
        Version mdlVersion = new VersionImpl(5, 0, 8, "qualm");
        mdl = new LanguageVersionImpl("MDL", mdlVersion);
        converterManager = new ConverterManagerImpl();
        converterManager.discoverConverters();
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
        Version converterVersion = new VersionImpl(1, 0, 2);
        assertEquals(converter.getConverterVersion(), converterVersion);
    }

    @Test
    public void shouldFindConverterMDLToNONMEMWithVersion() throws ConverterNotFoundException, IOException {
        LanguageVersion nonmem = createNONMEMLanguage();
        Version converterVersion = new VersionImpl(1, 0, 1);
        Converter foundConverter = converterManager.getConverter(mdl, nonmem, converterVersion);
        assertNotNull(foundConverter);
        assertEquals("Should have found correct converter version", converterVersion, foundConverter.getConverterVersion());
    }

    @Test(expected = ConverterNotFoundException.class)
    public void shouldNotFindConverterMDLToNONMEMWithVersion() throws ConverterNotFoundException, IOException {
        LanguageVersion nonmem = createNONMEMLanguage();
        Version converterVersion = new VersionImpl(1, 0, 3);
        converterManager.getConverter(mdl, nonmem, converterVersion);
    }

    @Test(expected = ConverterNotFoundException.class)
    public void shouldNotFindConverterMDLToNONMEMWithQualifier() throws ConverterNotFoundException, IOException {        
        Version version = new VersionImpl(7, 2, 0, "someQual");
        LanguageVersion nonmem = new LanguageVersionImpl("NMTRAN", version);
        Version converterVersion = new VersionImpl(1, 0, 2);
        converterManager.getConverter(mdl, nonmem, converterVersion);
    }

    @Test
    public void shouldFindConverterMDLToPharmML() throws ConverterNotFoundException, IOException {
        LanguageVersion pharmml = createPharmMLLanguage();
        assertNotNull(converterManager.getConverter(mdl, pharmml));
    }

    @Test(expected = ConverterNotFoundException.class)
    public void shouldNotFindConverter() throws ConverterNotFoundException {
        LanguageVersion nonmem = createNONMEMLanguage();
        LanguageVersion pharmml = createPharmMLLanguage();
        converterManager.getConverter(pharmml, nonmem);
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
    public void shouldFindInfoDetails() throws ConverterNotFoundException, IOException {
        File src = new File("i");
        ConversionReport report = getReportFromSuccessfulConversion(src);
        assertEquals(createDetailsFromCode(src.getName()), report.getDetails(Severity.INFO));
    }

    private ConversionReport getReportFromSuccessfulConversion(File src) throws ConverterNotFoundException, IOException {
        LanguageVersion nonmem = createNONMEMLanguage();
        Converter converter = converterManager.getConverter(mdl, nonmem);
        ConversionReport report = converter.convert(src, null);
        return report;
    }

    @Test
    public void shouldFindInfoAndWarningsDetails() throws ConverterNotFoundException, IOException {
        File src = new File("iw");
        ConversionReport report = getReportFromSuccessfulConversion(src);
        assertEquals(createDetailsFromCode(src.getName()), report.getDetails(Severity.INFO));
    }

    @Test
    public void shouldFindInfoAndWarningsAndDebugDetails() throws ConverterNotFoundException, IOException {
        File src = new File("iwd"); 
        ConversionReport report = getReportFromSuccessfulConversion(src);
        assertEquals(createDetailsFromCode(src.getName()), report.getDetails(Severity.DEBUG));
    }
    
    @Test
    public void shouldFindAllDetails() throws ConverterNotFoundException, IOException {
        File src = new File("iwd");
        ConversionReport report = getReportFromSuccessfulConversion(src);
        assertEquals(createDetailsFromCode(src.getName()), report.getDetails(Severity.ALL));
    }
    
    @Test
    public void shouldFindOnlyInfoAndWarningButNotDebugDetails() throws ConverterNotFoundException, IOException {
        File src = new File("iwd");
        ConversionReport report = getReportFromSuccessfulConversion(src);
        assertEquals(createDetailsFromCode("iw"), report.getDetails(Severity.INFO));
    }
    
    @Test
    public void shouldFindOnlyWarningsButNotInfoNorDebugDetails() throws ConverterNotFoundException, IOException {
        File src = new File("iwd");
        ConversionReport report = getReportFromSuccessfulConversion(src);
        assertEquals(createDetailsFromCode("w"), report.getDetails(Severity.WARNING));
    }
        
    @Test
    public void shouldFindErrorsDetails() throws ConverterNotFoundException, IOException {
        File src = new File("e");
        ConversionReport report = getReportFromFailedConversion(src);
        assertEquals(createDetailsFromCode(src.getName()), report.getDetails(Severity.ERROR));
    }
    
    private ConversionReport getReportFromFailedConversion(File src) throws ConverterNotFoundException, IOException {
        LanguageVersion nonmem = createNONMEMLanguage();
        Converter converter = converterManager.getConverter(mdl, nonmem, new VersionImpl(1, 0, 1));
        ConversionReport report = converter.convert(src, null);
        return report;
    }
    
    @Test
    public void shouldFindErrorsAndWarningsDetails() throws ConverterNotFoundException, IOException {
        File src = new File("ew");
        ConversionReport report = getReportFromFailedConversion(src);
        assertEquals(createDetailsFromCode(src.getName()), report.getDetails(Severity.WARNING));
    }
    
    @Test
    public void shouldFindErrorsAndWarningsButNotInfoDetails() throws ConverterNotFoundException, IOException {
        File src = new File("ewi");
        ConversionReport report = getReportFromFailedConversion(src);
        assertEquals(createDetailsFromCode("ew"), report.getDetails(Severity.WARNING));
    }
    
    @Test
    public void shouldAllWithErrorsDetails() throws ConverterNotFoundException, IOException {
        File src = new File("ewd");
        ConversionReport report = getReportFromFailedConversion(src);
        assertEquals(createDetailsFromCode(src.getName()), report.getDetails(Severity.ALL));
    }
    
    private List<ConversionDetail> createDetailsFromCode(String code) {
        List<ConversionDetail> details = new ArrayList<ConversionDetail>();
        if (code.contains("e")) {
            details.add(createConversionDetail(Severity.ERROR));
        }
        if (code.contains("w")) {
            details.add(createConversionDetail(Severity.WARNING));
        }
        if (code.contains("i")) {
            details.add(createConversionDetail(Severity.INFO));
        }
        if (code.contains("d")) {
            details.add(createConversionDetail(Severity.DEBUG));
        }
        return details;
    }
    
    private ConversionDetail createConversionDetail(Severity severity) {
        ConversionDetail conversionDetail = new ConversionDetailImpl();
        conversionDetail.setSeverity(severity);
        if (severity.equals(Severity.ERROR)) {
            conversionDetail.addInfo("error1", "error1");
            conversionDetail.addInfo("error2", "error2");
            conversionDetail.setMessage("ERROR message");
        } else if (severity.equals(Severity.WARNING)) {
            conversionDetail.addInfo("warning1", "warning1");
            conversionDetail.addInfo("warning2", "warning2");
            conversionDetail.setMessage("WARNING message");
        } else if (severity.equals(Severity.INFO)) {
            conversionDetail.addInfo("info1", "info1");
            conversionDetail.addInfo("info2", "info2");
            conversionDetail.setMessage("INFO message");
        } else if (severity.equals(Severity.DEBUG)) {
            conversionDetail.addInfo("debug1", "debug1");
            conversionDetail.addInfo("debug2", "debug2");
            conversionDetail.setMessage("DEBUG message");
        }
        return conversionDetail;
    }
}
