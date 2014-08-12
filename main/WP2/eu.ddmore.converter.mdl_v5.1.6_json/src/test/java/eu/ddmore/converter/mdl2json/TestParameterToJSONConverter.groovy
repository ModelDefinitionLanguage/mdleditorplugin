package eu.ddmore.converter.mdl2json;

import static org.junit.Assert.*;

import org.apache.log4j.Logger

import eu.ddmore.convertertoolbox.api.response.ConversionReport
import eu.ddmore.converter.mdl2json.domain.Parameter
import eu.ddmore.converter.mdl2json.domain.Data
import eu.ddmore.mdlparse.MdlParser
import groovy.json.JsonSlurper
import org.ddmore.mdl.mdl.Mcl
import org.junit.Test;

class TestParameterToJSONConverter extends ConverterTestsParent {
	private static Logger logger = Logger.getLogger(TestParameterToJSONConverter.class)
			
	@Test
	public void testStructuralModel() {
		def json = getJsonFromMDLFile("simpleParameter.mdl")
			
		def parameterObject = json[0].tumour_size_par // The [0] is because the JSON is enclosed within superfluous square brackets [...]
			
		def structuralModel = parameterObject[Parameter.STRUCTURAL]
		
		assertEquals("Checking number of Structural parameters", 4, structuralModel.size())
		
		def expected_POP_SIZE0 = [ 'name':'POP_SIZE0', 'value':'6.66', 'lo':'1', hi:'10' ]
		assertEquals("Checking first Structural parameter", expected_POP_SIZE0, structuralModel[0])
		
		def expected_POP_TOVER = [ 'name':'POP_TOVER', 'value':'18.9', 'lo':'1', hi:'200' ]
		assertEquals("Checking second Structural parameter", expected_POP_TOVER, structuralModel[1])
		
		def expected_POP_AE50 = [ 'name':'POP_AE50', 'value':'11700.', 'lo':'-1', hi:'100000' ]
		assertEquals("Checking third Structural parameter", expected_POP_AE50, structuralModel[2])

		def expected_POP_TEQ = [ 'name':'POP_TEQ', 'value':'8.27', 'lo':'1', hi:'100000' ]
		assertEquals("Checking fourth Structural parameter", expected_POP_TEQ, structuralModel[3])
		
	}
	
	@Test
	public void testVariabilityModel() {
		def json = getJsonFromMDLFile("simpleParameter.mdl")
			
		def parameterObject = json[0].tumour_size_par // The [0] is because the JSON is enclosed within superfluous square brackets [...]
			
		def variabilityModel = parameterObject[Parameter.VARIABILITY]
		
		def PPV_SIZE0 = variabilityModel.PPV_SIZE0

		def val = PPV_SIZE0["value"]
		
		assertEquals("0.317", val[0])
		assertEquals("VAR", PPV_SIZE0.type[0] )
		
	}
	
	@Test
	public void testParameterWithMatrix() {

		def json = getJsonFromMDLFile("parameterWithMatrix.mdl")
		
		def paramObj = json[0].ex_model7_prolactin_Jan2014_par // The [0] is because the JSON is enclosed within superfluous square brackets [...]
		
		def structuralModel = paramObj[Parameter.STRUCTURAL]
		
		def POP_KOUT = structuralModel[0]
		assertEquals(".1", POP_KOUT.lo)
		
		def POP_AMP = structuralModel[8]
		assertEquals("-.75", POP_AMP.lo)			

		def variabilityBlock = paramObj[Parameter.VARIABILITY]
		
		assertEquals(9, variabilityBlock.size())
		
		def struc1 = variabilityBlock[0]
		def attributes = struc1["matrix"]
		assertEquals("\"struc1\"", attributes["name"])
		assertEquals("VAR", attributes["type"])
		assertEquals("PPV_PRL0=.425,\n.758,PPV_KI=1.82", attributes["content"])
		
		def PPV_KOUT = variabilityBlock[1]
		attributes = PPV_KOUT["PPV_KOUT"]
		assertEquals("VAR", attributes["type"])
		assertEquals("0.589", attributes["value"])

		def sameBlk = variabilityBlock[5]
		attributes = sameBlk["same"]
		assertEquals("\"struc2\"", attributes["name"])
		assertEquals(
"""PPV_IOV_IN_PRL0_2,
PPV_IOV_IN_PRL0_2_copyfortesting""", attributes["content"])
		
		def diagBlk = variabilityBlock[4]
		attributes = diagBlk["diag"]
		assertEquals("\"struc2\"", attributes["name"])
		assertEquals("VAR", attributes["type"])
		assertEquals(
"""PPV_IOV_IN_PRL0_1=.0657,
PPV_IOV_IN_PRL0_1_copyfortesting=.0657""", attributes["content"])
	}
	
	@Test
	public void testComplexVariability() {
		def struc1 = 0
		def same1 = 2
		def PPV_CVTTK0 = 11 
		def RUV_SDCP = 20
		def json = getJsonFromMDLFile("complexParameter.mdl")[0] // The [0] is because the JSON is enclosed within superfluous square brackets [...]
		
		def paramObj = json["levodopa_variability_par"]
		assertNotEquals(null, paramObj)
		
		def structuralModel = paramObj[Parameter.STRUCTURAL]
		def POP_TTK0 = structuralModel[4]
		assertEquals("2", POP_TTK0.value)
		assertEquals("true", POP_TTK0["fix"])

		def variabilityModel = paramObj[Parameter.VARIABILITY]
		assertEquals(21, variabilityModel.size())
					
		struc1 = variabilityModel[struc1]
		def attributes = struc1["matrix"]
		String expectedStruc1Matrix =
"""BSVV1=0.015,
0.00377,BSVCL=0.0158,
0.0156,0.0127,BSVV2=0.0218,
0.0273,0.0282,0.0411,BSVQ=0.0804"""
		assertEquals(expectedStruc1Matrix, attributes["content"])
		
		same1 = variabilityModel[same1]
		attributes = same1["same"]
		assertEquals(
"""BOVCL2,
BOVV22,
BOVQ2""", attributes["content"])
	
		PPV_CVTTK0 = variabilityModel[PPV_CVTTK0]
		attributes = PPV_CVTTK0["PPV_CVTTK0"]
		assertEquals("0", attributes['value'])
		assertEquals("true", attributes.fix)
		
		RUV_SDCP = variabilityModel[RUV_SDCP]
		attributes = RUV_SDCP["RUV_SDCP"]
		assertEquals("0.0249", attributes.value)
		assertEquals("VAR", attributes.type)
	}
}
