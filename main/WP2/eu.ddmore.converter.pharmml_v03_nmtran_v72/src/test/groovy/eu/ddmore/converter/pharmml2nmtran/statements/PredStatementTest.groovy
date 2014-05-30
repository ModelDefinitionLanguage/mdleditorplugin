package eu.ddmore.converter.pharmml2nmtran.statements;

import static org.junit.Assert.*;

import org.junit.Test;
import org.junit.Ignore;
import eu.ddmore.converter.pharmml2nmtran.utils.ConversionContext
import eu.ddmore.converter.pharmml2nmtran.utils.Parameters
import eu.ddmore.libpharmml.PharmMlFactory
import eu.ddmore.libpharmml.dom.PharmML

import org.apache.commons.io.FileUtils;
import static eu.ddmore.converter.pharmml2nmtran.MainTest.PATH

public class PredStatementTest {

	@Ignore
	@Test
	public void testSimpleAssignedParameters() {
        File src = new File(Thread.currentThread().getContextClassLoader().getResource(PATH +'Friberg2009Prolactin_v20140506v13_NONMEM.xml').getPath());
        def pmlAPI = PharmMlFactory.getInstance().createLibPharmML()
		def is = FileUtils.openInputStream(src)
		PharmML pmlDOM = pmlAPI.createDomFromResource(is).getDom()

		ConversionContext conversionContext = new ConversionContext(pmlDOM, src)

		def commonParameters = pmlDOM.modelDefinition.parameterModel.collect{ it.commonParameterElement ?: [] }.flatten()
		
		def PHS1 = commonParameters.find { it.value.symbId =="PHS1" }
		
		String pieceWiseAsNmtran = conversionContext.convert( PHS1.value.assign.equation.piecewise, "PHS1", conversionContext.simpleParameterToNmtran)
			
		assertEquals("IF(NM_PAT.EQ.NM_HV)\n	NM_PHS1=NM_PHS1\nELSE\nIF(NM_PAT.EQ.NM_PAT)\n	NM_PHS1=NM_PHS1+NM_DPHS\nENDIF\n", pieceWiseAsNmtran )
 	}

}
