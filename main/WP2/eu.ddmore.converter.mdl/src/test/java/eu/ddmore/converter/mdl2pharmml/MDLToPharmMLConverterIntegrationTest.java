package eu.ddmore.converter.mdl2pharmml;

import static org.junit.Assert.*;

import java.io.File;
import java.io.IOException;
import java.util.Iterator;
import java.util.List;

import org.apache.commons.io.FileUtils;
import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.TemporaryFolder;

import eu.ddmore.convertertoolbox.api.response.ConversionDetail.Severity;
import eu.ddmore.convertertoolbox.api.response.ConversionReport;
import eu.ddmore.convertertoolbox.api.response.ConversionReport.ConversionCode;
import eu.ddmore.convertertoolbox.api.response.ConversionDetail;


public class MDLToPharmMLConverterIntegrationTest {

    @Rule
    public TemporaryFolder workingFolder = new TemporaryFolder();

    private File validMdlFile;
    private File pharmmlValidMdlFile;
    private File syntaxErrorsMdlFile;
    private File pharmmlSyntaxErrorsMdlFile;
    private File semanticErrorsMdlFile;
    private File pharmmlSemanticErrorsMdlFile;
    private File semanticWarningsMdlFile;
    private File pharmmlSemanticWarningsMdlFile;
    
    private MDLToPharmMLConverter converter;

    /**
     * Set-up tasks prior to each test being run.
     * <p>
     * @throws IOException 
     */
    @Before
    public void setUp() throws IOException {
    
        validMdlFile = new File(workingFolder.getRoot(), "valid.mdl");
        pharmmlValidMdlFile = new File(workingFolder.getRoot(), "valid.xml");
        syntaxErrorsMdlFile = new File(workingFolder.getRoot(), "syntaxerrors.mdl");
        pharmmlSyntaxErrorsMdlFile = new File(workingFolder.getRoot(), "syntaxerrors.xml");
        semanticErrorsMdlFile = new File(workingFolder.getRoot(), "semanticerrors.mdl");
        pharmmlSemanticErrorsMdlFile = new File(workingFolder.getRoot(), "semanticerrors.xml");
        semanticWarningsMdlFile = new File(workingFolder.getRoot(), "semanticwarnings.mdl");
        pharmmlSemanticWarningsMdlFile = new File(workingFolder.getRoot(), "semanticwarnings.xml");
    
        FileUtils.copyURLToFile(getClass().getResource("/valid.mdl"), validMdlFile);
        FileUtils.copyURLToFile(getClass().getResource("/syntaxerrors.mdl"), syntaxErrorsMdlFile);
        FileUtils.copyURLToFile(getClass().getResource("/semanticerrors.mdl"), semanticErrorsMdlFile);
        FileUtils.copyURLToFile(getClass().getResource("/semanticwarnings.mdl"), semanticWarningsMdlFile);
        
        this.converter = new MDLToPharmMLConverter();
    }
    
    /**
     * Test method for {@link eu.ddmore.converter.mdl2pharmml.MDLToPharmMLConverter#performConvert(java.io.File, java.io.File)}.
     * @throws IOException 
     */
    @Test
    public void testPerformConvertForValidMdlFile() throws IOException {
        assertFalse("Converted PharmML file should not initially exist", pharmmlValidMdlFile.exists());
        final ConversionReport report = converter.performConvert(validMdlFile, workingFolder.getRoot());
        assertEquals("Checking for successful return code", ConversionCode.SUCCESS, report.getReturnCode());
        assertTrue("Converted PharmML file should have been created", pharmmlValidMdlFile.exists());
        final List<ConversionDetail> errors = report.getDetails(Severity.ERROR);
        assertTrue("Checking that no errors were returned", errors.isEmpty());
        final List<ConversionDetail> warnings = report.getDetails(Severity.WARNING);
        removeDataFileValidationWarning(warnings); // TODO: Drop this once Stuart has fixed the data file validation check
        assertTrue("Checking that no warnings were returned", warnings.isEmpty());
    }
    
    /**
     * Test method for {@link eu.ddmore.converter.mdl2pharmml.MDLToPharmMLConverter#performConvert(java.io.File, java.io.File)}.
     * @throws IOException 
     */
    @Test
    public void testPerformConvertForMdlFileWithSyntaxErrors() throws IOException {
        assertFalse("Converted PharmML file should not initially exist", pharmmlSyntaxErrorsMdlFile.exists());
        final ConversionReport report = converter.performConvert(syntaxErrorsMdlFile, workingFolder.getRoot());
        assertEquals("Checking for failure return code", ConversionCode.FAILURE, report.getReturnCode());
        assertFalse("No converted PharmML file should have been created", pharmmlSyntaxErrorsMdlFile.exists());
        assertEquals("Checking the number of errors that were returned", 54, report.getDetails(Severity.ERROR).size());
    }
    
    /**
     * Test method for {@link eu.ddmore.converter.mdl2pharmml.MDLToPharmMLConverter#performConvert(java.io.File, java.io.File)}.
     * @throws IOException 
     */
    @Test
    public void testPerformConvertForMdlFileWithSemanticErrors() throws IOException {
        assertFalse("Converted PharmML file should not initially exist", pharmmlSemanticErrorsMdlFile.exists());
        final ConversionReport report = converter.performConvert(semanticErrorsMdlFile, workingFolder.getRoot());
        assertEquals("Checking for failure return code", ConversionCode.FAILURE, report.getReturnCode());
        assertFalse("No converted PharmML file should have been created", pharmmlSemanticErrorsMdlFile.exists());
        assertEquals("Checking the number of errors that were returned", 3, report.getDetails(Severity.ERROR).size());
    }
    
    /**
     * Test method for {@link eu.ddmore.converter.mdl2pharmml.MDLToPharmMLConverter#performConvert(java.io.File, java.io.File)}.
     * @throws IOException 
     */
    @Test
    public void testPerformConvertForMdlFileWithSemanticWarnings() throws IOException {
        assertFalse("Converted PharmML file should not initially exist", pharmmlSemanticWarningsMdlFile.exists());
        final ConversionReport report = converter.performConvert(semanticWarningsMdlFile, workingFolder.getRoot());
        assertEquals("Checking for successful return code", ConversionCode.SUCCESS, report.getReturnCode());
        assertTrue("Converted PharmML file should have been created", pharmmlSemanticWarningsMdlFile.exists());
        final List<ConversionDetail> errors = report.getDetails(Severity.ERROR);
        assertTrue("Checking that no errors were returned", errors.isEmpty());
        final List<ConversionDetail> warnings = report.getDetails(Severity.WARNING);
        removeDataFileValidationWarning(warnings); // TODO: Drop this once Stuart has fixed the data file validation check
        assertEquals("Checking the number of errors that were returned", 2, warnings.size());
    }
    
    // TODO: This method can be dropped once Stuart has fixed the data file validation check
    private void removeDataFileValidationWarning(final Iterable<ConversionDetail> list) {
        final String WARNING_MSG_TO_IGNORE = "Cannot find data file: path may be incorrect.";
        final Iterator<ConversionDetail> iter = list.iterator();
        while (iter.hasNext()) {
            final ConversionDetail thisMsg = iter.next();
            if (thisMsg.getServerity().equals(Severity.WARNING) && thisMsg.getMessage().equals(WARNING_MSG_TO_IGNORE)) {
                iter.remove();
            }
        }
        
    }

}
