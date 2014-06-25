package eu.ddmore.converter.pharmml2nmtran.statements;

import static org.junit.Assert.*;

import org.junit.Test;
import eu.ddmore.converter.pharmml2nmtran.utils.ConversionContext
import eu.ddmore.converter.pharmml2nmtran.utils.ParameterVariableSortHelper;
import eu.ddmore.converter.pharmml2nmtran.utils.Parameters
import eu.ddmore.libpharmml.PharmMlFactory
import eu.ddmore.libpharmml.dom.PharmML

import org.apache.commons.io.FileUtils;

import static eu.ddmore.converter.pharmml2nmtran.MainTest.TEST_DATA_DIR
import static eu.ddmore.converter.pharmml2nmtran.MainTest.V_0_3_SUBDIR

import static eu.ddmore.converter.pharmml2nmtran.statements.NMTranFormatter.PREFIX

public class PredStatementTest {

    private final String prolactinModel = TEST_DATA_DIR + V_0_3_SUBDIR + "Friberg2009Prolactin/Friberg2009Prolactin_v20140523v14_NONMEM.xml"

    @Test
    public void testSimpleAssignedParameters() {
        final URL urlToFile = PredStatementTest.class.getResource(prolactinModel)

        def pmlAPI = PharmMlFactory.getInstance().createLibPharmML()
        PharmML pmlDOM = pmlAPI.createDomFromResource(urlToFile.openStream()).getDom()

        ConversionContext conversionContext = new ConversionContext(
            pmlDOM, new File("Friberg2009Prolactin/Friberg2009Prolactin_v20140523v14_NONMEM.xml")) // Second param -> Doesn't seem to need to be an actual file!

        def commonParameters = pmlDOM.modelDefinition.parameterModel.collect{ it.commonParameterElement ?: [] }.flatten()

        def PHS1 = commonParameters.find { it.value.symbId =="PHS1" }

        String pieceWiseAsNmtran = conversionContext.convert( PHS1.value.assign.equation.piecewise, "PHS1")

        // Think this is ok - no simple parameters in structural model, so we can't name the thetas
        String expected = "IF(PAT.EQ.HV) THEN \n	${PREFIX}PHS1=THETA(9)\nELSE\nIF(PAT.EQ.PAT) THEN \n	${PREFIX}PHS1=THETA(9)+THETA(8)\nENDIF\n"
        assertEquals(expected, pieceWiseAsNmtran )
 	}

	@Test
	public void testComplexAssignedParameters() {
        final URL urlToFile = PredStatementTest.class.getResource(prolactinModel)

        def pmlAPI = PharmMlFactory.getInstance().createLibPharmML()
		PharmML pmlDOM = pmlAPI.createDomFromResource(urlToFile.openStream()).getDom()

		ConversionContext conversionContext = new ConversionContext(
            pmlDOM, new File("Friberg2009Prolactin/Friberg2009Prolactin_v20140523v14_NONMEM.xml")) // Second param -> Doesn't seem to need to be an actual file!

		def commonParameters = pmlDOM.modelDefinition.parameterModel.collect{ it.commonParameterElement ?: [] }.flatten()

		def POP_PRL0 = commonParameters.find { it.value.symbId == "pop_PRL0" }

		String pieceWiseAsNmtran = conversionContext.convert( POP_PRL0.value.assign.equation.piecewise, "POP_PRL0", conversionContext.simpleParameterToNmtran)

String expected = """IF(PAT.EQ.HV) THEN 
	${PREFIX}POP_PRL0=THETA(1)
ELSE
IF(((PAT.EQ.PAT).AND.(SEX.EQ.M)).AND.(STUDY.NE.STUD101)) THEN 
	${PREFIX}POP_PRL0=THETA(2)
ELSE
IF(((PAT.EQ.PAT).AND.(SEX.EQ.F)).AND.(STUDY.NE.STUD101)) THEN 
	${PREFIX}POP_PRL0=THETA(3)
ELSE
IF(((PAT.EQ.PAT).AND.(SEX.EQ.M)).AND.(STUDY.EQ.STUD101)) THEN 
	${PREFIX}POP_PRL0=THETA(4)
ELSE
IF(((PAT.EQ.PAT).AND.(SEX.EQ.F)).AND.(STUDY.EQ.STUD101)) THEN 
	${PREFIX}POP_PRL0=THETA(5)
ENDIF
"""
		assertEquals(expected, pieceWiseAsNmtran )
 	}

	@Test
	public void testEtaCount() {
        final URL urlToFile = PredStatementTest.class.getResource(prolactinModel)

		def pmlAPI = PharmMlFactory.getInstance().createLibPharmML()
		PharmML pmlDOM = pmlAPI.createDomFromResource(urlToFile.openStream()).getDom()

		ConversionContext conversionContext = new ConversionContext(
            pmlDOM, new File("Friberg2009Prolactin/Friberg2009Prolactin_v20140523v14_NONMEM.xml")) // Second param -> Doesn't seem to need to be an actual file!

		// Required to set up omega count
		conversionContext.getOmegasStatement()

		PredStatement ps = conversionContext.predStatement;

		String ind = ps.getIndividualsFromModel().toString()

		String[] lines = ind.split("\\n")
		String nm_koutEq = lines.findResult { it.contains("KOUT=") ? it : null }
		String nm_kout = nm_koutEq.split("=")[1]
		String expected = "${PREFIX}POP_KOUT*EXP(ETA(4))"
		assertEquals(expected, nm_kout)

		String nm_prl0Eq = lines.findResult { it.find(/\s${PREFIX}PRL0=/) ? it : null }

        // String nm_prl0Eq = lines.findResult { it.contains("=") && it?.split("=")[0]?.equals("PRL0") ? it : null }
		String nm_prl0 = nm_prl0Eq.split("=")[1]
		expected = "${PREFIX}POP_PRL0*EXP(ETA(1))*EXP(ETA(3))"
		assertEquals(expected, nm_prl0 )

		String amp1Eq = lines.findResult { it.contains("AMP1=") ? it : null }
		String amp1 = amp1Eq.split("=")[1]
		expected = "${PREFIX}POP_AMP1*EXP(ETA(5))"
		assertEquals(expected, amp1)

		String phs2Eq = lines.findResult { it.find(/\s${PREFIX}PHS2=/) ? it : null }
		String phs2 = phs2Eq.split("=")[1]
		expected = "THETA(11)+ETA(6)"
		assertEquals(expected, phs2 )

	}

}
