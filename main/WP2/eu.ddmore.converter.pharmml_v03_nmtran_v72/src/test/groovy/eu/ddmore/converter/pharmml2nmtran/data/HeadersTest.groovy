package eu.ddmore.converter.pharmml2nmtran.data;

import static org.junit.Assert.*;

import java.io.File;

import org.junit.Test;

import eu.ddmore.converter.pharmml2nmtran.statements.DataStatement
import eu.ddmore.libpharmml.PharmMlFactory
import eu.ddmore.libpharmml.dom.PharmML
import org.apache.commons.io.FileUtils;

import static eu.ddmore.converter.pharmml2nmtran.MainTest.TEST_DATA_DIR

class HeadersTest {

    private final static String WORKING_DIR = "target/HeadersTest_Working_Dir/"

    @Test
    public void testHeadersExample3() {
        String modelFileRelPath = "example3/example3.xml"
        File modelFile = getFile(modelFileRelPath)
        String dataFileRelPath = "example3/example3_data.csv"
        File dataFile = getFile(dataFileRelPath)

        def pmlAPI = PharmMlFactory.getInstance().createLibPharmML()
        def pmlDOM = pmlAPI.createDomFromResource(FileUtils.openInputStream(modelFile)).getDom()

        DataStatement dataStatement = new DataStatement(pmlDOM, WORKING_DIR + '/' + modelFileRelPath.replace(".xml", ""))
        dataStatement.createDataFile()

        List<String> headers = new ArrayList<String>()
        headers.add("ID")
        headers.add("TIME")
        headers.add("DV")
        headers.add("ARM")
        headers.add("WEIGHT")
        headers.add("AMT")
        headers.add("MDV")
        headers.add("EVID")
        assertEquals(headers, dataStatement.getHeaders())
    }

    @Test
    public void testHeadersExample5() {
        String modelFileRelPath = "example5/example5.xml"
        File modelFile = getFile(modelFileRelPath)
        String dataFileRelPath = "example3/example3_data.csv"
        File dataFile = getFile(dataFileRelPath)

        def pmlAPI = PharmMlFactory.getInstance().createLibPharmML()
        def pmlDOM = pmlAPI.createDomFromResource(FileUtils.openInputStream(modelFile)).getDom()

        DataStatement dataStatement = new DataStatement(pmlDOM, WORKING_DIR + '/' + modelFileRelPath.replace(".xml", ""))
        dataStatement.createDataFile()

        List<String> headers = new ArrayList<String>()
        headers.add("ID")
        headers.add("TIME")
        headers.add("DV")
        headers.add("ARM")
        headers.add("AMT")
        headers.add("MDV")
        headers.add("EVID")
        assertEquals(headers, dataStatement.getHeaders())
    }
    
    private File getFile(String relativePathToFile) {
        
        final URL urlToFile = HeadersTest.class.getResource(TEST_DATA_DIR + relativePathToFile);
        File destFile = new File(WORKING_DIR + relativePathToFile)
        FileUtils.copyURLToFile(urlToFile, destFile);

        return destFile
    }
}
