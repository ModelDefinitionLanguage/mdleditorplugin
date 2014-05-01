package eu.ddmore.converter.pharmml2nmtran.data;

import static org.junit.Assert.*;

import org.junit.Test;

import eu.ddmore.converter.pharmml2nmtran.statements.DataStatement
import eu.ddmore.libpharmml.PharmMlFactory
import eu.ddmore.libpharmml.dom.PharmML
import org.apache.commons.io.FileUtils;

import static eu.ddmore.converter.pharmml2nmtran.MainTest.path

class HeadersTest {

    @Test
    public void testHeadersExample3() {
        File src = new File(Thread.currentThread().getContextClassLoader().getResource(path +'example3.xml').getPath());
        def pmlAPI = PharmMlFactory.getInstance().createLibPharmML()
        def is = FileUtils.openInputStream(src)
        def pmlDOM = pmlAPI.createDomFromResource(is).getDom()

        DataStatement dataStatement = new DataStatement(pmlDOM, src.getParentFile().getAbsolutePath()+'/'+ src.getName().replace(".xml", ""))
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
        File src = new File(Thread.currentThread().getContextClassLoader().getResource(path +'example5.xml').getPath());
        def pmlAPI = PharmMlFactory.getInstance().createLibPharmML()
        def is = FileUtils.openInputStream(src)
        def pmlDOM = pmlAPI.createDomFromResource(is).getDom()

        DataStatement dataStatement = new DataStatement(pmlDOM, src.getParentFile().getAbsolutePath()+'/'+ src.getName().replace(".xml", ""))
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


}
