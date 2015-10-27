package eu.ddmore.converter.mdl2pharmml;

import static org.junit.Assert.*;

import java.io.File;
import java.io.IOException;

import org.apache.commons.io.FileUtils;
import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.TemporaryFolder;

import eu.ddmore.convertertoolbox.api.response.ConversionReport;
import eu.ddmore.convertertoolbox.api.response.ConversionReport.ConversionCode;


public class MDLToPharmMLConverterTest {

    @Rule
    public TemporaryFolder workingFolder = new TemporaryFolder();

    private File validMdlFile;
    private File pharmmlValidMdlFile;
    private File invalidMdlFile;
    private File pharmmlInvalidMdlFile;
    
    private MDLToPharmMLConverter converter;

    /**
     * Set-up tasks prior to each test being run.
     * <p>
     * @throws IOException 
     */
    @Before
    public void setUp() throws IOException {
    
        validMdlFile = new File(workingFolder.getRoot(), "UseCase1.mdl");
        pharmmlValidMdlFile = new File(workingFolder.getRoot(), "UseCase1.xml");
        invalidMdlFile = new File(workingFolder.getRoot(), "UseCase1_1.mdl");
        pharmmlInvalidMdlFile = new File(workingFolder.getRoot(), "UseCase1_1.xml");
    
        FileUtils.copyURLToFile(getClass().getResource("/test-models/MDL/7.0.0/UseCase1.mdl"), validMdlFile);
        FileUtils.copyURLToFile(getClass().getResource("/test-models/MDL/Product4-invalid/UseCase1_1.mdl"), invalidMdlFile);
        
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
    }
    
    /**
     * Test method for {@link eu.ddmore.converter.mdl2pharmml.MDLToPharmMLConverter#performConvert(java.io.File, java.io.File)}.
     * @throws IOException 
     */
    @Test
    public void testPerformConvertForInvalidMdlFile() throws IOException {
        assertFalse("Converted PharmML file should not initially exist", pharmmlInvalidMdlFile.exists());
        final ConversionReport report = converter.performConvert(invalidMdlFile, workingFolder.getRoot());
        assertEquals("Checking for failure return code", ConversionCode.FAILURE, report.getReturnCode());
        assertFalse("No converted PharmML file should have been created", pharmmlInvalidMdlFile.exists());
    }

}
