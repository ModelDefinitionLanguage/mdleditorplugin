package eu.ddmore.converter.mdl2json;

import static org.junit.Assert.*;

import java.io.File;
import java.io.IOException;

import org.apache.commons.io.FileUtils;
import org.junit.Before;
import org.junit.Test;

import eu.ddmore.convertertoolbox.api.response.ConversionReport;
import eu.ddmore.convertertoolbox.api.response.ConversionReport.ConversionCode;


public class MDLToJSONConverterTest {

    private static final File validMdlFile = new File(FileUtils.getTempDirectory(), "UseCase1.mdl");
    private static final File jsonValidMdlFile = new File(FileUtils.getTempDirectory(), "UseCase1.json");
    private static final File invalidMdlFile = new File(FileUtils.getTempDirectory(), "UseCase1_1.mdl");
    private static final File jsonInvalidMdlFile = new File(FileUtils.getTempDirectory(), "UseCase1_1.json");
    
    private MDLToJSONConverter converter;

    /**
     * Set-up tasks prior to each test being run.
     * <p>
     * @throws IOException 
     */
    @Before
    public void setUp() throws IOException {
    
        FileUtils.copyURLToFile(getClass().getResource("/test-models/MDL/Product4/UseCase1.mdl"), validMdlFile);
        FileUtils.copyURLToFile(getClass().getResource("/test-models/MDL/Product4-invalid/UseCase1_1.mdl"), invalidMdlFile);
        
        FileUtils.deleteQuietly(jsonValidMdlFile);
        FileUtils.deleteQuietly(jsonInvalidMdlFile);
        
        this.converter = new MDLToJSONConverter();
    }

    /**
     * Test method for {@link MDLToJSONConverter#performConvert(java.io.File, java.io.File)}.
     * @throws IOException 
     */
    @Test
    public void testPerformConvertForValidMdlFile() throws IOException {
        assertFalse("Converted JSON file should not initially exist", jsonValidMdlFile.exists());
        final ConversionReport report = converter.performConvert(validMdlFile, FileUtils.getTempDirectory());
        assertEquals("Checking for successful return code", ConversionCode.SUCCESS, report.getReturnCode());
        assertTrue("Converted JSON file should have been created", jsonValidMdlFile.exists());
    }
    
    /**
     * Test method for {@link MDLToJSONConverter#performConvert(java.io.File, java.io.File)}.
     * @throws IOException 
     */
    @Test
    public void testPerformConvertForInvalidMdlFile() throws IOException {
        assertFalse("Converted JSON file should not initially exist", jsonInvalidMdlFile.exists());
        final ConversionReport report = converter.performConvert(invalidMdlFile, FileUtils.getTempDirectory());
        assertEquals("Checking for failure return code", ConversionCode.FAILURE, report.getReturnCode());
        assertFalse("No converted JSON file should have been created", jsonInvalidMdlFile.exists());
    }

}
