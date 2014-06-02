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
			
		// Think this is ok - no simple parameters in structural model, so we can't name the thetas
		assertEquals("IF(NM_PAT.EQ.NM_HV)\n	NM_PHS1=THETA(9)\nELSE\nIF(NM_PAT.EQ.NM_PAT)\n	NM_PHS1=THETA(9)+THETA(8)\nENDIF\n", pieceWiseAsNmtran )
 	}

	@Test
	public void testEtaCount() {
		File src = new File(Thread.currentThread().getContextClassLoader().getResource(PATH +'Friberg2009Prolactin_v20140506v13_NONMEM.xml').getPath());
		def pmlAPI = PharmMlFactory.getInstance().createLibPharmML()
		def is = FileUtils.openInputStream(src)
		PharmML pmlDOM = pmlAPI.createDomFromResource(is).getDom()

		ConversionContext conversionContext = new ConversionContext(pmlDOM, src)
	
		// Required to set up omega count
		conversionContext.getOmegasStatement()
		
		PredStatement ps = conversionContext.predStatement;
		
		String ind = ps.getIndividualsFromModel().toString()
		
		String[] lines = ind.split("\\n")
		String nm_kout = lines[0].split("=")[1]
		assertEquals(nm_kout, "NM_POP_KOUT*EXP(ETA(4))")
		
		String nm_prl0 = lines[1].split("=")[1]
		assertEquals(nm_prl0, "NM_POP_PRL0*EXP(ETA(1))*EXP(ETA(3))")
		
	}
	
	/**
	 * 	NM_KOUT=NM_POP_KOUT*EXP(ETA(4))
	NM_PRL0=NM_POP_PRL0*EXP(ETA(1))*EXP(ETA(3))
	NM_AMP1=NM_POP_AMP1*EXP(ETA(5))
	NM_PHS2=THETA(11)+ETA(5)
	NM_KI=NM_POP_KI*EXP(ETA(2))
	NM_D2=0.5
	NM_F2=1.0
	IF(NM_PAT.EQ.NM_HV)
	NM_PHS1=THETA(9)
ELSE
IF(NM_PAT.EQ.NM_PAT)
	NM_PHS1=THETA(9)+THETA(8)
ENDIF

	IF(NM_PAT.EQ.NM_HV)
	NM_POP_PRL0=THETA(1)
ELSE
IF(IF(IF(NM_PAT.EQ.NM_PAT).AND.IF(NM_SEX.EQ.NM_M)).AND.IF(NM_STUDY.NE.NM_STU101))
	NM_POP_PRL0=THETA(2)
ELSE
IF(IF(IF(NM_PAT.EQ.NM_PAT).AND.IF(NM_SEX.EQ.NM_F)).AND.IF(NM_STUDY.NE.NM_STU101))
	NM_POP_PRL0=THETA(3)
ELSE
IF(IF(IF(NM_PAT.EQ.NM_PAT).AND.IF(NM_SEX.EQ.NM_M)).AND.IF(NM_STUDY.EQ.NM_STU101))
	NM_POP_PRL0=THETA(4)
ELSE
IF(IF(IF(NM_PAT.EQ.NM_PAT).AND.IF(NM_SEX.EQ.NM_F)).AND.IF(NM_STUDY.EQ.NM_STU101))
	NM_POP_PRL0=THETA(5)
ENDIF

	IF(NM_PAT.EQ.NM_HV)
	NM_POP_PHS2=THETA(11)
ELSE
IF(NM_PAT.EQ.NM_PAT)
	NM_POP_PHS2=THETA(11)+THETA(8)
ENDIF
	 */
}
