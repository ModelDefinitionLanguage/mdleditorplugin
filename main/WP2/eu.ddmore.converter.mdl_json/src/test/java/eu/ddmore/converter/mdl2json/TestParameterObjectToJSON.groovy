package eu.ddmore.converter.mdl2json;

import static org.junit.Assert.*

import org.apache.log4j.Logger
import org.junit.Ignore
import org.junit.Test

import eu.ddmore.converter.mdl2json.domain.Parameter
import eu.ddmore.converter.mdl2json.domain.Variable

class TestParameterObjectToJSON extends ConverterTestsParent {
	private static Logger logger = Logger.getLogger(TestParameterObjectToJSON.class)

	@Test
	public void testStructuralBlock_Warfarin() {
		def json = getJsonFromMDLFile("Warfarin_ParameterObject.mdl")
		
		def parameterObject = json[0].warfarin_PK_ODE_par // The [0] is because the JSON is enclosed within superfluous square brackets [...]
		
		def structuralModel = parameterObject[Parameter.STRUCTURAL]
		
		assertEquals("Checking number of Structural parameters", 6, structuralModel.size())
		
		def expected_POP_CL = [ (Variable.NAME_KEY):'POP_CL', 'value':'0.1', 'lo':'0.001' ]
		assertEquals("Checking Structural parameter 1/6", expected_POP_CL, structuralModel[0])
		
		def expected_POP_V = [ (Variable.NAME_KEY):'POP_V', 'value':'8', 'lo':'0.001' ]
		assertEquals("Checking Structural parameter 2/6", expected_POP_V, structuralModel[1])
		
		def expected_POP_KA = [ (Variable.NAME_KEY):'POP_KA', 'value':'0.362', 'lo':'0.001' ]
		assertEquals("Checking Structural parameter 3/6", expected_POP_KA, structuralModel[2])

		def expected_POP_TLAG = [ (Variable.NAME_KEY):'POP_TLAG', 'value':'1', 'lo':'0.001', 'hi':'10' ]
		assertEquals("Checking Structural parameter 4/6", expected_POP_TLAG, structuralModel[3])

		def expected_BETA_CL_WT = [ (Variable.NAME_KEY):'BETA_CL_WT', 'value':'0.75', 'fix':'true' ]
		assertEquals("Checking Structural parameter 5/6", expected_BETA_CL_WT, structuralModel[4])

		def expected_BETA_V_WT = [ (Variable.NAME_KEY):'BETA_V_WT', 'value':'1', 'fix':'true' ]
		assertEquals("Checking Structural parameter 6/6", expected_BETA_V_WT, structuralModel[5])
        
	}
    
    @Test
    public void testStructuralBlock_Hansson() {
        def json = getJsonFromMDLFile("Hansson_ParameterObject.mdl")
        
        def parameterObject = json[0].Hansson2013_par // The [0] is because the JSON is enclosed within superfluous square brackets [...]
        
        def structuralModel = parameterObject[Parameter.STRUCTURAL]
        
        assertEquals("Checking number of Structural parameters", 18, structuralModel.size())
        
        def expected_POP_IMAX = [ (Variable.NAME_KEY):'POP_IMAX', 'value':'1', 'fix':'true' ]
        assertEquals("Checking Structural parameter 1/18", expected_POP_IMAX, structuralModel[0])
        
        def expected_POP_IC50 = [ (Variable.NAME_KEY):'POP_IC50', 'value':'1', 'lo':'0', 'units':'\"mg/L*h\"' ]
        assertEquals("Checking Structural parameter 2/18", expected_POP_IC50, structuralModel[1])
        
        def expected_POP_BM0 = [ (Variable.NAME_KEY):'POP_BM0', 'value':'59.7', 'lo':'0', 'units':'\"pg/mL\"' ]
        assertEquals("Checking Structural parameter 3/18", expected_POP_BM0, structuralModel[2])
        
        def expected_POP_MRT = [ (Variable.NAME_KEY):'POP_MRT', 'value':'91', 'lo':'0', 'units':'\"h\"' ]
        assertEquals("Checking Structural parameter 4/18", expected_POP_MRT, structuralModel[3])

        def expected_POP_HILL = [ (Variable.NAME_KEY):'POP_HILL', 'value':'3.31', 'lo':'0' ]
        assertEquals("Checking Structural parameter 5/18", expected_POP_HILL, structuralModel[4])

        def expected_POP_SLP = [ (Variable.NAME_KEY):'POP_SLP', 'value':'0.035', 'lo':'-0.06', 'units':'\"1/h\"' ]
        assertEquals("Checking Structural parameter 6/18", expected_POP_SLP, structuralModel[5])
        
    }
	
	/**
	 * Note the extra layer of nesting in the 'simple' parameters; this is to be consistent with
	 * the matrix/diag/same parameters and make the post-processing in R slightly simpler.
	 */
	@Test
	public void testVariabilityBlock_Warfarin() {
		def json = getJsonFromMDLFile("Warfarin_ParameterObject.mdl")
			
		def parameterObject = json[0].warfarin_PK_ODE_par // The [0] is because the JSON is enclosed within superfluous square brackets [...]
			
		def variabilityModel = parameterObject[Parameter.VARIABILITY]
		
		assertEquals("Checking the number of variables in the Variability model", 7, variabilityModel.size())
		
		def expected_PPV_CL = [ 'PPV_CL' : [ 'value':'0.1', 'type':'SD' ] ]
		assertEquals("Checking Variability parameter 1/7", expected_PPV_CL, variabilityModel[0])

		def expected_PPV_V = [ 'PPV_V' : [ 'value':'0.1', 'type':'SD' ] ]
		assertEquals("Checking Variability parameter 2/7", expected_PPV_V, variabilityModel[1])
		
		def expected_PPV_KA = [ 'PPV_KA' : [ 'value':'0.1', 'type':'SD' ] ]
		assertEquals("Checking Variability parameter 3/7", expected_PPV_KA, variabilityModel[2])
		
		def expected_PPV_TLAG = [ 'PPV_TLAG' : [ 'value':'0.1', 'type':'SD' ] ]
		assertEquals("Checking Variability parameter 4/7", expected_PPV_TLAG, variabilityModel[3])
		
		def expected_RUV_PROP = [ 'RUV_PROP' : [ 'value':'0.1', 'type':'SD' ] ]
		assertEquals("Checking Variability parameter 5/7", expected_RUV_PROP, variabilityModel[4])
		
		def expected_RUV_ADD = [ 'RUV_ADD' : [ 'value':'0.1', 'type':'SD' ] ]
		assertEquals("Checking Variability parameter 6/7", expected_RUV_ADD, variabilityModel[5])
		
		def expected_CORR_PPV_CL_V = [ 'CORR_PPV_CL_V' : [ 'hi':'1.0', 'lo':'-1.0', 'value':'0.01', 'type':'CORR' ] ]
		assertEquals("Checking Variability parameter 7/7", expected_CORR_PPV_CL_V, variabilityModel[6])
		
	}
    
    @Test
    public void testVariabilityBlock_Hansson() {
        def json = getJsonFromMDLFile("Hansson_ParameterObject.mdl")
            
        def parameterObject = json[0].Hansson2013_par // The [0] is because the JSON is enclosed within superfluous square brackets [...]
            
        def variabilityModel = parameterObject[Parameter.VARIABILITY]
        
        assertEquals("Checking the number of variables in the Variability model", 13, variabilityModel.size())
        
        def expected_OMEGA_BM0 = [ 'OMEGA_BM0' : [ 'value':'0.252', 'type':'VAR' ] ]
        assertEquals("Checking Variability parameter 1/13", expected_OMEGA_BM0, variabilityModel[0])

        def expected_OMEGA_BM2 = [ 'OMEGA_BM2' : [ 'value':'0.0369', 'type':'VAR' ] ]
        assertEquals("Checking Variability parameter 2/13", expected_OMEGA_BM2, variabilityModel[1])
        
        def expected_OMEGA_BM3 = [ 'OMEGA_BM3' : [ 'value':'0.186', 'type':'VAR' ] ]
        assertEquals("Checking Variability parameter 3/13", expected_OMEGA_BM3, variabilityModel[2])

        def expected_OMEGA_BMS = [ 'OMEGA_BMS' : [ 'value':'0.254', 'type':'VAR' ] ]
        assertEquals("Checking Variability parameter 4/13", expected_OMEGA_BMS, variabilityModel[3])

        def expected_SIGMA_RES_W = [ 'SIGMA_RES_W' : [ 'value':'1', 'type':'VAR', 'fix':'true' ] ]
        assertEquals("Checking Variability parameter 13/13", expected_SIGMA_RES_W, variabilityModel[12])
        
    }
    
    @Test
    public void testVariabilityBlock_WarfarinPkBov() {
        def json = getJsonFromMDLFile("WarfarinPkBov_ParameterObject.mdl")
            
        def parameterObject = json[0].warfarin_PK_BOV_par // The [0] is because the JSON is enclosed within superfluous square brackets [...]
            
        def variabilityModel = parameterObject[Parameter.VARIABILITY]
        
        assertEquals("Checking the number of variables in the Variability model", 12, variabilityModel.size())
        
        def expected_BSV_CL = [ 'BSV_CL' : [ 'value':'0.1', 'type':'VAR' ] ]
        assertEquals("Checking Variability parameter 1/12", expected_BSV_CL, variabilityModel[0])
        
        def expected_BOV_CL = [ 'BOV_CL' : [ 'fix':'true', 'value':'0.1', 'type':'VAR' ] ]
        assertEquals("Checking Variability parameter 3/12", expected_BOV_CL, variabilityModel[2])

        def expected_BOV_COV_CL_V = [ 'BOV_COV_CL_V' : [ 'fix':'true', 'value':'[0.01]', 'type':'COV' ] ]
        assertEquals("Checking Variability parameter 5/12", expected_BOV_COV_CL_V, variabilityModel[4])
        
    }
	
	@Test
	@Ignore
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
		def expected_PPV_CVTTK0 = [ 'PPV_CVTTK0' : [ 'value':'0', 'type':'VAR', 'fix':'true' ] ]
		assertEquals("Checking the first of the 'standard' variables", expected_PPV_CVTTK0, PPV_CVTTK0)
		
		def RUV_SDCP = variabilityModel[20]
		def expected_RUV_SDCP = [ 'RUV_SDCP' : [ 'value':'0.0249', 'type':'VAR' ] ]
		assertEquals("Checking the last of the 'standard' variables", expected_RUV_SDCP, RUV_SDCP)
		
		def diag1 = variabilityModel[21]["diag"]
		assertEquals("Checking the name of the single Diag variable", "\"struc3\"", diag1["name"])
		assertEquals("Checking the type of the single Diag variable", "VAR", diag1["type"])
		String expectedDiag1 = """PPV_IOV_IN_PRL0_1=.0657,
PPV_IOV_IN_PRL0_2=.0757"""
		assertEquals("Checking the content of the single Diag variable", expectedDiag1, diag1["content"])
		
	}
	
}
