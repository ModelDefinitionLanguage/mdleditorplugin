package eu.ddmore.converter.pharmml2nmtran.statements

import static org.junit.Assert.*;

import org.junit.Test;
import org.junit.Ignore;
import eu.ddmore.converter.pharmml2nmtran.utils.ConversionContext
import eu.ddmore.converter.pharmml2nmtran.utils.Parameters
import eu.ddmore.libpharmml.PharmMlFactory
import eu.ddmore.libpharmml.dom.PharmML

import org.apache.commons.io.FileUtils

import static eu.ddmore.converter.pharmml2nmtran.MainTest.TEST_DATA_DIR
import static eu.ddmore.converter.pharmml2nmtran.MainTest.V_0_3_SUBDIR

class InputStatementTest {

    private final String fribergModel = TEST_DATA_DIR + V_0_3_SUBDIR + 'FribergCPT2009/Friberg2009Prolactin_v20140506v13_NONMEM.xml'

    @Test
    @Ignore
    public void testProlactinInputStatement() {
        final URL urlToFile = PredStatementTest.class.getResource(fribergModel)

        def pmlAPI = PharmMlFactory.getInstance().createLibPharmML()
        PharmML pmlDOM = pmlAPI.createDomFromResource(urlToFile.openStream()).getDom()

        ConversionContext conversionContext = new ConversionContext(
                pmlDOM, new File("FribergCPT2009/Friberg2009Prolactin_v20140506v13_NONMEM.xml")) // Second param -> Doesn't seem to need to be an actual file!
        DataStatement dataStatement = new DataStatement(pmlDOM, "Friberg2009Prolactin_v20140506v13_NONMEM")
        List<String> headers = dataStatement.getHeaders()
        String inputStatement = conversionContext.getInputStatement(headers)
        assertEquals("\$INPUT ID TIME DV\n", inputStatement )
    }
}
