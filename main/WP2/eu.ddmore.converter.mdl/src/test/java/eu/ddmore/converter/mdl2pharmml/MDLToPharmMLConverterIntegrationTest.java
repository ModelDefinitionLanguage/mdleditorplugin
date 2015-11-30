package eu.ddmore.converter.mdl2pharmml;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

import java.io.File;
import java.io.IOException;
import java.util.List;

import org.apache.commons.io.FileUtils;
import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.TemporaryFolder;

import eu.ddmore.convertertoolbox.api.response.ConversionDetail;
import eu.ddmore.convertertoolbox.api.response.ConversionDetail.Severity;
import eu.ddmore.convertertoolbox.api.response.ConversionReport;
import eu.ddmore.convertertoolbox.api.response.ConversionReport.ConversionCode;


public class MDLToPharmMLConverterIntegrationTest {

    @Rule
    public TemporaryFolder workingFolder = new TemporaryFolder();

    private File validMdlFile;
    private File validPharmmlFile;
    private File syntaxErrorsMdlFile;
    private File syntaxErrorsPharmmlFile;
    private File semanticErrorsMdlFile;
    private File semanticErrorsPharmmlFile;
    private File semanticWarningsMdlFile;
    private File semanticWarningsPharmmlFile;
    private File unsupportedFeatureMdlFile;
    private File unsupportedFeaturePharmmlFile;
    private File dataFile;
    
    private MDLToPharmMLConverter converter;

    /**
     * Set-up tasks prior to each test being run.
     * <p>
     * @throws IOException 
     */
    @Before
    public void setUp() throws IOException {
    
        validMdlFile = new File(workingFolder.getRoot(), "valid.mdl");
        validPharmmlFile = new File(workingFolder.getRoot(), "valid.xml");
        syntaxErrorsMdlFile = new File(workingFolder.getRoot(), "syntaxerrors.mdl");
        syntaxErrorsPharmmlFile = new File(workingFolder.getRoot(), "syntaxerrors.xml");
        semanticErrorsMdlFile = new File(workingFolder.getRoot(), "semanticerrors.mdl");
        semanticErrorsPharmmlFile = new File(workingFolder.getRoot(), "semanticerrors.xml");
        semanticWarningsMdlFile = new File(workingFolder.getRoot(), "semanticwarnings.mdl");
        semanticWarningsPharmmlFile = new File(workingFolder.getRoot(), "semanticwarnings.xml");
        unsupportedFeatureMdlFile = new File(workingFolder.getRoot(), "unsupportedfeature.mdl");
        unsupportedFeaturePharmmlFile = new File(workingFolder.getRoot(), "unsupportedfeature.xml");
        
        FileUtils.copyURLToFile(getClass().getResource("/valid.mdl"), validMdlFile);
        FileUtils.copyURLToFile(getClass().getResource("/syntaxerrors.mdl"), syntaxErrorsMdlFile);
        FileUtils.copyURLToFile(getClass().getResource("/semanticerrors.mdl"), semanticErrorsMdlFile);
        FileUtils.copyURLToFile(getClass().getResource("/semanticwarnings.mdl"), semanticWarningsMdlFile);
        FileUtils.copyURLToFile(getClass().getResource("/unsupportedfeature.mdl"), unsupportedFeatureMdlFile);
        
        dataFile = new File(workingFolder.getRoot(), "warfarin_conc.csv");
        FileUtils.copyURLToFile(getClass().getResource("/warfarin_conc.csv"), dataFile);
        
        this.converter = new MDLToPharmMLConverter();
    }
    
    /**
     * Test method for {@link eu.ddmore.converter.mdl2pharmml.MDLToPharmMLConverter#performConvert(java.io.File, java.io.File)}.
     * @throws IOException 
     */
    @Test
    public void testPerformConvertForValidMdlFile() throws IOException {
        assertFalse("Converted PharmML file should not initially exist", validPharmmlFile.exists());
        final ConversionReport report = converter.performConvert(validMdlFile, workingFolder.getRoot());
        assertEquals("Checking for successful return code", ConversionCode.SUCCESS, report.getReturnCode());
        assertTrue("Converted PharmML file should have been created", validPharmmlFile.exists());
        final List<ConversionDetail> errors = report.getDetails(Severity.ERROR);
        assertTrue("Checking that no errors were returned", errors.isEmpty());
        final List<ConversionDetail> warnings = report.getDetails(Severity.WARNING);
        assertTrue("Checking that no warnings were returned", warnings.isEmpty());
    }
    
    /**
     * Test method for {@link eu.ddmore.converter.mdl2pharmml.MDLToPharmMLConverter#performConvert(java.io.File, java.io.File)}.
     * @throws IOException 
     */
    @Test
    public void testPerformConvertForMdlFileWithSyntaxErrors() throws IOException {
        assertFalse("Converted PharmML file should not initially exist", syntaxErrorsPharmmlFile.exists());
        final ConversionReport report = converter.performConvert(syntaxErrorsMdlFile, workingFolder.getRoot());
        assertEquals("Checking for failure return code", ConversionCode.FAILURE, report.getReturnCode());
        assertFalse("No converted PharmML file should have been created", syntaxErrorsPharmmlFile.exists());
        assertEquals("Checking the number of errors that were returned", 54, report.getDetails(Severity.ERROR).size());
    }
    
    /**
     * Test method for {@link eu.ddmore.converter.mdl2pharmml.MDLToPharmMLConverter#performConvert(java.io.File, java.io.File)}.
     * @throws IOException 
     */
    @Test
    public void testPerformConvertForMdlFileWithSemanticErrors() throws IOException {
        assertFalse("Converted PharmML file should not initially exist", semanticErrorsPharmmlFile.exists());
        final ConversionReport report = converter.performConvert(semanticErrorsMdlFile, workingFolder.getRoot());
        assertEquals("Checking for failure return code", ConversionCode.FAILURE, report.getReturnCode());
        assertFalse("No converted PharmML file should have been created", semanticErrorsPharmmlFile.exists());
        assertEquals("Checking the number of errors that were returned", 3, report.getDetails(Severity.ERROR).size());
    }
    
    /**
     * Test method for {@link eu.ddmore.converter.mdl2pharmml.MDLToPharmMLConverter#performConvert(java.io.File, java.io.File)}.
     * @throws IOException 
     */
    @Test
    public void testPerformConvertForMdlFileWithSemanticWarnings() throws IOException {
        assertFalse("Converted PharmML file should not initially exist", semanticWarningsPharmmlFile.exists());
        final ConversionReport report = converter.performConvert(semanticWarningsMdlFile, workingFolder.getRoot());
        assertEquals("Checking for successful return code", ConversionCode.SUCCESS, report.getReturnCode());
        assertTrue("Converted PharmML file should have been created", semanticWarningsPharmmlFile.exists());
        final List<ConversionDetail> errors = report.getDetails(Severity.ERROR);
        assertTrue("Checking that no errors were returned", errors.isEmpty());
        final List<ConversionDetail> warnings = report.getDetails(Severity.WARNING);
        assertEquals("Checking the number of errors that were returned", 2, warnings.size());
    }
    
    /**
     * Test method for {@link eu.ddmore.converter.mdl2pharmml.MDLToPharmMLConverter#performConvert(java.io.File, java.io.File)}.
     * @throws IOException 
     */
    @Test
    public void testPerformConvertForMdlFileWithUnsupportedFeatureWarningsThatShouldBeTreatedAsErrors() throws IOException {
        assertFalse("Converted PharmML file should not initially exist", unsupportedFeaturePharmmlFile.exists());
        final ConversionReport report = converter.performConvert(unsupportedFeatureMdlFile, workingFolder.getRoot());
        assertEquals("Checking for successful return code", ConversionCode.FAILURE, report.getReturnCode());
        assertFalse("No converted PharmML file should have been created", unsupportedFeaturePharmmlFile.exists());
        final List<ConversionDetail> errors = report.getDetails(Severity.ERROR);
        assertEquals("Checking that 1 error was returned", 1, errors.size());
        assertEquals("Checking the error message", "Objects of type 'desObj' are not currently supported for execution in R.", errors.get(0).getMessage());
        assertEquals("Checking the severity of the message", Severity.ERROR, errors.get(0).getServerity());
    }

}
