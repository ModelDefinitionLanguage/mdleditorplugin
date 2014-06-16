package eu.ddmore.converter.pharmml2nmtran.statements;

import static org.junit.Assert.*;

import org.junit.Test;
import eu.ddmore.converter.pharmml2nmtran.utils.ConversionContext
import eu.ddmore.libpharmml.PharmMlFactory
import eu.ddmore.libpharmml.dom.PharmML

import org.apache.commons.io.FileUtils;
import static eu.ddmore.converter.pharmml2nmtran.MainTest.TEST_DATA_DIR


class EstimationStatementTest {

    @Test
    public void testExample3() {
        final URL urlToFile = EstimationStatementTest.class.getResource(TEST_DATA_DIR + "example3/example3.xml");

        def pmlAPI = PharmMlFactory.getInstance().createLibPharmML()
        def pmlDOM = pmlAPI.createDomFromResource(urlToFile.openStream()).getDom()
        ConversionContext conversionContext = new ConversionContext(
            pmlDOM, new File("example3/example3.xml")) // Second param -> Doesn't seem to need to be an actual file!
        assertEquals("\$EST METHOD=COND INTER MAXEVALS=9999 PRINT=10 NOABORT\n\$COV\n", conversionContext.getEstimationStatement().toString())
    }

    @Test
    public void testExample5() {
        final URL urlToFile = EstimationStatementTest.class.getResource(TEST_DATA_DIR + "example5/example5.xml");

        def pmlAPI = PharmMlFactory.getInstance().createLibPharmML()
        def pmlDOM = pmlAPI.createDomFromResource(urlToFile.openStream()).getDom()
        ConversionContext conversionContext = new ConversionContext(
            pmlDOM, new File("example5/example5.xml")) // Second param -> Doesn't seem to need to be an actual file!
        assertEquals("\$EST METHOD=SAEM INTER NBURN=2000 NITER=1000 ISAMPLE=2 IACCEPT=0.4 PRINT=10 CTYPE=3\n\$COV\n", conversionContext.getEstimationStatement().toString())
    }
}
