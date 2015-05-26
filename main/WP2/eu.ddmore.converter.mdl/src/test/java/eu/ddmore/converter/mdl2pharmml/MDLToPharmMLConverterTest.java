package eu.ddmore.converter.mdl2pharmml;

import static org.junit.Assert.*;

import java.io.File;
import java.io.IOException;

import org.apache.commons.io.FileUtils;
import org.junit.Before;
import org.junit.Test;

import eu.ddmore.convertertoolbox.api.response.ConversionReport;
import eu.ddmore.convertertoolbox.api.response.ConversionReport.ConversionCode;


public class MDLToPharmMLConverterTest {

    private static final File validMdlFile = new File(FileUtils.getTempDirectory(), "UseCase1.mdl");
    private static final File pharmmlValidMdlFile = new File(FileUtils.getTempDirectory(), "UseCase1.xml");
    private static final File invalidMdlFile = new File(FileUtils.getTempDirectory(), "UseCase1_1.mdl");
    private static final File pharmmlInvalidMdlFile = new File(FileUtils.getTempDirectory(), "UseCase1_1.xml");
    
    private MDLToPharmMLConverter converter;

    /**
     * Set-up tasks prior to each test being run.
     * <p>
     * @throws IOException 
     */
    @Before
    public void setUp() throws IOException {
    
        FileUtils.copyURLToFile(getClass().getResource("/test-models/MDL/Product4/UseCase1.mdl"), validMdlFile);
        FileUtils.copyURLToFile(getClass().getResource("/test-models/MDL/Product4-invalid/UseCase1_1.mdl"), invalidMdlFile);
        
        FileUtils.deleteQuietly(pharmmlValidMdlFile);
        FileUtils.deleteQuietly(pharmmlInvalidMdlFile);
        
        this.converter = new MDLToPharmMLConverter();
    }
    
    /**
     * Test method for {@link eu.ddmore.converter.mdl2pharmml.MDLToPharmMLConverter#performConvert(java.io.File, java.io.File)}.
     * @throws IOException 
     */
    @Test
    public void testPerformConvertForValidMdlFile() throws IOException {
        assertFalse("Converted PharmML file should not initially exist", pharmmlValidMdlFile.exists());
        final ConversionReport report = converter.performConvert(validMdlFile, FileUtils.getTempDirectory());
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
        final ConversionReport report = converter.performConvert(invalidMdlFile, FileUtils.getTempDirectory());
        assertEquals("Checking for failure return code", ConversionCode.FAILURE, report.getReturnCode());
        assertFalse("No converted PharmML file should have been created", pharmmlInvalidMdlFile.exists());
    }

}
