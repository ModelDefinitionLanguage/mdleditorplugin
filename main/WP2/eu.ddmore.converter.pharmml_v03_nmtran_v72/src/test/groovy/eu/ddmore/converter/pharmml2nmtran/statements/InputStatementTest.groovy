package eu.ddmore.converter.pharmml2nmtran.statements

import static org.junit.Assert.*;

import org.junit.Test;
import org.junit.Ignore;
import eu.ddmore.converter.pharmml2nmtran.utils.ConversionContext
import eu.ddmore.converter.pharmml2nmtran.utils.Parameters
import eu.ddmore.libpharmml.PharmMlFactory
import eu.ddmore.libpharmml.dom.PharmML

import org.apache.commons.io.FileUtils

class InputStatementTest {

	static final String MAIN_TEST_PATH = eu.ddmore.converter.pharmml2nmtran.MainTest.PATH

	@Ignore
	@Test
	public void testProlactinInputStatement() {
		
        File src = new File(Thread.currentThread().getContextClassLoader().getResource(MAIN_TEST_PATH +'Friberg2009Prolactin_v20140506v13_NONMEM.xml').getPath());
        def pmlAPI = PharmMlFactory.getInstance().createLibPharmML()
		def is = FileUtils.openInputStream(src)
		PharmML pmlDOM = pmlAPI.createDomFromResource(is).getDom()

		ConversionContext conversionContext = new ConversionContext(pmlDOM, src)
		DataStatement dataStatement = new DataStatement(pmlDOM, src.getName().replace(".xml", ""))
		List<String> headers = dataStatement.getHeaders()
		String inputStatement = conversionContext.getInputStatement(headers)
		assertEquals("\$INPUT ID TIME DV\n", inputStatement )
	}
	
}
