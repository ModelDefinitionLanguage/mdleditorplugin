package eu.ddmore.converter.pharmml2nmtran.statements;

import static org.junit.Assert.*;

import org.junit.Test;
import eu.ddmore.converter.pharmml2nmtran.utils.ConversionContext
import eu.ddmore.converter.pharmml2nmtran.utils.Parameters
import eu.ddmore.libpharmml.PharmMlFactory
import eu.ddmore.libpharmml.dom.PharmML

import org.apache.commons.io.FileUtils;
import static eu.ddmore.converter.pharmml2nmtran.MainTest.path

public class PredStatementTest {

	@Test
	public void testSimpleAssignedParameters() {
        File src = new File(Thread.currentThread().getContextClassLoader().getResource(path +'Friberg2009Prolactin_v20140506v11.xml').getPath());
        def pmlAPI = PharmMlFactory.getInstance().createLibPharmML()
		def is = FileUtils.openInputStream(src)
		def pmlDOM = pmlAPI.createDomFromResource(is).getDom()

		ConversionContext conversionContext = new ConversionContext(pmlDOM, src)
		Parameters parameters = new Parameters(pmlDOM)
		parameters.init()

		PredStatement pred = new PredStatement(pmlDOM, parameters, conversionContext)
		StringBuilder sb = pred.reportSimpleParamsWithAssigns()
		
		//assertEquals("", sb.toString() )
 	}

}
