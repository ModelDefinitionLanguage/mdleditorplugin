package eu.ddmore.converter.mdl2json;

import static org.junit.Assert.*

import org.apache.log4j.Logger
import org.junit.Test

import eu.ddmore.converter.mdl2json.domain.Parameter

class TestParameterToJSONConverter extends ConverterTestsParent {
	private static Logger logger = Logger.getLogger(TestParameterToJSONConverter.class)

	@Test
	public void testStructuralModel() {
		def json = getJsonFromMDLFile("warfarinODE_ParameterObject.mdl")
		
		def parameterObject = json[0].warfarin_PK_ODE_par // The [0] is because the JSON is enclosed within superfluous square brackets [...]
		
		def structuralModel = parameterObject[Parameter.STRUCTURAL]
		
		assertEquals("Checking number of Structural parameters", 6, structuralModel.size())
		
		def expected_POP_CL = [ 'name':'POP_CL', 'value':'0.1', 'lo':'0.001' ]
		assertEquals("Checking Structural parameter 1/6", expected_POP_CL, structuralModel[0])
		
		def expected_POP_V = [ 'name':'POP_V', 'value':'8', 'lo':'0.001' ]
		assertEquals("Checking Structural parameter 2/6", expected_POP_V, structuralModel[1])
		
		def expected_POP_KA = [ 'name':'POP_KA', 'value':'0.362', 'lo':'0.001' ]
		assertEquals("Checking Structural parameter 3/6", expected_POP_KA, structuralModel[2])

		def expected_POP_TLAG1 = [ 'name':'POP_TLAG1', 'value':'1', 'lo':'0.001' ]
		assertEquals("Checking Structural parameter 4/6", expected_POP_TLAG1, structuralModel[3])

		def expected_BETA_CL_WT = [ 'name':'BETA_CL_WT', 'value':'0.75', 'fix':'true' ]
		assertEquals("Checking Structural parameter 5/6", expected_BETA_CL_WT, structuralModel[4])

		def expected_BETA_V_WT = [ 'name':'BETA_V_WT', 'value':'1', 'fix':'true' ]
		assertEquals("Checking Structural parameter 6/6", expected_BETA_V_WT, structuralModel[5])
		
	}
	
	@Test
	public void testVariabilityModel() {
		def json = getJsonFromMDLFile("warfarinODE_ParameterObject.mdl")
			
		def parameterObject = json[0].warfarin_PK_ODE_par // The [0] is because the JSON is enclosed within superfluous square brackets [...]
			
		def variabilityModel = parameterObject[Parameter.VARIABILITY]
		
		assertEquals("Checking the number of variables in the Variability model", 7, variabilityModel.size())
		
		def expected_PPV_CL = [ 'name':'PPV_CL', 'value':'0.1', 'type':'SD' ]
		assertEquals("Checking Variability parameter 1/7", expected_PPV_CL, variabilityModel[0])

		def expected_PPV_V = [ 'name':'PPV_V', 'value':'0.1', 'type':'SD' ]
		assertEquals("Checking Variability parameter 2/7", expected_PPV_V, variabilityModel[1])
		
		def expected_PPV_KA = [ 'name':'PPV_KA', 'value':'0.1', 'type':'SD' ]
		assertEquals("Checking Variability parameter 3/7", expected_PPV_KA, variabilityModel[2])
		
		def expected_PPV_TLAG = [ 'name':'PPV_TLAG', 'value':'0.1', 'type':'SD' ]
		assertEquals("Checking Variability parameter 4/7", expected_PPV_TLAG, variabilityModel[3])
		
		def expected_RUV_PROP = [ 'name':'RUV_PROP', 'value':'0.1', 'type':'SD' ]
		assertEquals("Checking Variability parameter 5/7", expected_RUV_PROP, variabilityModel[4])
		
		def expected_RUV_ADD = [ 'name':'RUV_ADD', 'value':'0.1', 'type':'SD' ]
		assertEquals("Checking Variability parameter 6/7", expected_RUV_ADD, variabilityModel[5])
		
		def expected_CORR_PPV_CL_V = [ 'hi':'1.0', 'lo':'-1.0', 'name':'CORR_PPV_CL_V', 'value':'0.01', 'type':'CORR' ]
		assertEquals("Checking Variability parameter 7/7", expected_CORR_PPV_CL_V, variabilityModel[6])
		
	}
	
	@Test
	public void testComplexVariability() {
		
		def json = getJsonFromMDLFile("complexParameter.mdl")[0] // The [0] is because the JSON is enclosed within superfluous square brackets [...]
		
		def paramObj = json["levodopa_variability_par"]
		assertNotNull(paramObj)
		
		def structuralModel = paramObj[Parameter.STRUCTURAL]
		def POP_TTK0 = structuralModel[4]
		assertEquals("2", POP_TTK0.value)
		assertEquals("true", POP_TTK0["fix"])

		def variabilityModel = paramObj[Parameter.VARIABILITY]
		assertEquals("Checking the number of variables in the Variability Model", 22, variabilityModel.size())

		def matrix1 = variabilityModel[0]["matrix"]
		assertEquals("Checking the name of the first of the Matrix variables", "\"struc1\"", matrix1["name"])
		assertEquals("Checking the type of the first of the Matrix variables", "VAR", matrix1["type"])
		String expectedMatrix1 =
"""BSVV1=0.015,
0.00377, BSVCL=0.0158,
0.0156, 0.0127, BSVV2=0.0218,
0.0273, 0.0282, 0.0411, BSVQ=0.0804"""
		assertEquals("Checking the content of the first of the Matrix variables", expectedMatrix1, matrix1["content"])
		
		def same1 = variabilityModel[2]["same"]
		assertEquals("Checking the name of the first of the Same variables", "\"struc2\"", same1["name"])
		String expectedSame1 = """BOVCL2,
BOVV22,
BOVQ2"""
		assertEquals("Checking the content of the first of the Same variables", expectedSame1, same1["content"])
		
		def PPV_CVTTK0 = variabilityModel[11]
		def expected_PPV_CVTTK0 = [ 'name':'PPV_CVTTK0', 'value':'0', 'type':'VAR', 'fix':'true' ]
		assertEquals("Checking the first of the 'standard' variables", expected_PPV_CVTTK0, PPV_CVTTK0)
		
		def RUV_SDCP = variabilityModel[20]
		def expected_RUV_SDCP = [ 'name':'RUV_SDCP', 'value':'0.0249', 'type':'VAR' ]
		assertEquals("Checking the last of the 'standard' variables", expected_RUV_SDCP, RUV_SDCP)
		
		def diag1 = variabilityModel[21]["diag"]
		assertEquals("Checking the name of the single Diag variable", "\"struc3\"", diag1["name"])
		assertEquals("Checking the type of the single Diag variable", "VAR", diag1["type"])
		String expectedDiag1 = """PPV_IOV_IN_PRL0_1=.0657,
PPV_IOV_IN_PRL0_2=.0757"""
		assertEquals("Checking the content of the single Diag variable", expectedDiag1, diag1["content"])
		
	}
	
}
