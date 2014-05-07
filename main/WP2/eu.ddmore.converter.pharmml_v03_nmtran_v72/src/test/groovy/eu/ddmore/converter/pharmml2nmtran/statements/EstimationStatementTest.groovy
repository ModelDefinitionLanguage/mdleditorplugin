package eu.ddmore.converter.pharmml2nmtran.statements;

import static org.junit.Assert.*;

import org.junit.Test;
import eu.ddmore.converter.pharmml2nmtran.utils.ConversionContext
import eu.ddmore.libpharmml.PharmMlFactory
import eu.ddmore.libpharmml.dom.PharmML

import org.apache.commons.io.FileUtils;
import static eu.ddmore.converter.pharmml2nmtran.MainTest.path


class EstimationStatementTest {

    @Test
    public void testExample3() {
        File src = new File(Thread.currentThread().getContextClassLoader().getResource(path +'example3.xml').getPath());
        def pmlAPI = PharmMlFactory.getInstance().createLibPharmML()
        def is = FileUtils.openInputStream(src)
        def pmlDOM = pmlAPI.createDomFromResource(is).getDom()
        ConversionContext conversionContext = new ConversionContext(pmlDOM, src)        
        assertEquals("\$EST METHOD=COND INTER MAXEVALS=9999 PRINT=10 NOABORT\n\$COV\n", conversionContext.getEstimationStatement().toString())
    }

    @Test
    public void testExample5() {
        File src = new File(Thread.currentThread().getContextClassLoader().getResource(path +'example5.xml').getPath());
        def pmlAPI = PharmMlFactory.getInstance().createLibPharmML()
        def is = FileUtils.openInputStream(src)
        def pmlDOM = pmlAPI.createDomFromResource(is).getDom()
        ConversionContext conversionContext = new ConversionContext(pmlDOM, src)        
        assertEquals("\$EST METHOD=SAEM INTER NBURN=2000 NITER=1000 ISAMPLE=2 IACCEPT=0.4 PRINT=10 CTYPE=3\n\$COV\n", conversionContext.getEstimationStatement().toString())
    }
}
