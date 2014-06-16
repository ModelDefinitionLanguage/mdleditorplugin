package eu.ddmore.converter.pharmml2nmtran;

import static org.junit.Assert.*;

import java.io.File;
import java.net.URL;

import eu.ddmore.convertertoolbox.api.response.ConversionReport

import org.apache.commons.io.FileUtils;
import org.junit.Ignore;
import org.junit.Test;


class MainTest {

    private final static String TEST_DATA_DIR_v_0_3 = "/eu/ddmore/testdata/models/pharmml/v0.3/"
    private final static String WORKING_DIR = "target/MainTest_Working_Dir/"

    private final PharmMLToNMTRANConverter converter = new PharmMLToNMTRANConverter();


    @Test
    public void convertExample5() {
        def conversionReport = performConversion('example5/example5.xml')
        assertNotNull("ConversionReport should not be null", conversionReport)
    }

    @Test
    public void convertExample3() {
        def conversionReport = performConversion('example3/example3.xml')
        assertNotNull("ConversionReport should not be null", conversionReport)
    }

    @Ignore
    @Test
    public void convertExample1() {
        def conversionReport = performConversion('example1/example1.xml')
        assertNotNull("ConversionReport should not be null", conversionReport)
    }

    @Ignore
    @Test
    public void convertExample2() {
        def conversionReport = performConversion('example2/example2.xml')
        assertNotNull("ConversionReport should not be null", conversionReport)
    }

    @Ignore
    @Test
    public void convertExample4() {
        def conversionReport = performConversion('example4/example4.xml')
        assertNotNull("ConversionReport should not be null", conversionReport)
    }

    @Test
    public void convert2008ThamJCCR() {
        def conversionReport = performConversion('2008ThamCCR/2008ThamJCCR.xml')
        assertNotNull("ConversionReport should not be null", conversionReport)
    }

    @Test
    public void convert2008ThamJCCR_NONMEM() {
        def conversionReport = performConversion('2008ThamCCR/2008ThamJCCR_NONMEM.xml')
        assertNotNull("ConversionReport should not be null", conversionReport)
    }

    @Test
    public void convert2008ThamCCR_v03_NONMEM() {
        def conversionReport = performConversion('2008ThamCCR/2008ThamCCR_v03_NONMEM.xml')
        assertNotNull("ConversionReport should not be null", conversionReport)
    }

    @Test
    public void convertFriberg2009Prolactin_v20140506v11_03() {
        def conversionReport = performConversion('Friberg2009Prolactin/Friberg2009Prolactin_v20140506v11_0.3.xml')
        assertNotNull("ConversionReport should not be null", conversionReport)
    }

    @Test
    public void convertFriberg2009Prolactin_v20140506v13_NONMEM() {
        def conversionReport = performConversion('Friberg2009Prolactin/Friberg2009Prolactin_v20140506v13_NONMEM.xml')
        assertNotNull("ConversionReport should not be null", conversionReport)
    }

    @Test
    @Ignore
    public void convertDelbene() {
        def conversionReport = performConversion('Delbene/delbene.xml')
        assertNotNull("ConversionReport should not be null", conversionReport)
    }

    @Test
    @Ignore
    public void convertDeWinter_JPP_2006() {
        def conversionReport = performConversion('DeWinter/DeWinter_JPP_2006.xml')
        assertNotNull("ConversionReport should not be null", conversionReport)
    }

    @Test
    @Ignore
    public void convertFriberg_SchizophreniaModel__ClinPharmcolTher_2009_fixAUC_v2() {
        def conversionReport = performConversion('FribergSchizophreniaModel/Friberg_SchizophreniaModel__ClinPharmcolTher_2009_fixAUC_v2.xml')
        assertNotNull("ConversionReport should not be null", conversionReport)
    }

    @Test
    @Ignore
    public void convertHamren_CPT_2008() {
        def conversionReport = performConversion('Hamren/Hamren_CPT_2008.xml')
        assertNotNull("ConversionReport should not be null", conversionReport)
    }

    @Test
    @Ignore
    public void convertLledo_JPP_2013() {
        def conversionReport = performConversion('Lledo/Lledo_JPP_2013.xml')
        assertNotNull("ConversionReport should not be null", conversionReport)
    }

    @Test
    @Ignore
    public void convertRocchetti() {
        def conversionReport = performConversion('Rocchetti/rocchetti.xml')
        assertNotNull("ConversionReport should not be null", conversionReport)
    }

    @Test
    @Ignore
    public void convertSimeoni() {
        def conversionReport = performConversion('Simeoni/simeoni.xml')
        assertNotNull("ConversionReport should not be null", conversionReport)
    }


    private ConversionReport performConversion(String fileToConvert, String ... dataFiles) {
        File srcFile = getFile(fileToConvert)
        for (final String dataFile : dataFiles) {
            getFile(dataFile)
        }
        converter.performConvert(srcFile, srcFile.getParentFile())
    }

    private File getFile(String relativePathToFile) {

        final URL urlToFile = MainTest.class.getResource(TEST_DATA_DIR_v_0_3 + relativePathToFile);
        File destFile = new File(WORKING_DIR + relativePathToFile)
        FileUtils.copyURLToFile(urlToFile, destFile);

        return destFile
    }
}