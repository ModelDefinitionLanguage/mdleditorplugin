package eu.ddmore.converter.mdl2json;

import static org.junit.Assert.*

import org.apache.log4j.Logger
import org.junit.Test

class TestModelToJSONConverter extends ConverterTestsParent {
	private static Logger logger = Logger.getLogger(TestModelToJSONConverter.class)
	
	@Test
	void testModelInputVariablesBlock() {
		def json = getJsonFromMDLFile("prolactin_ModelObject.mdl")[0] // The [0] is because the JSON is enclosed within superfluous square brackets [...]
		
		def modelObject = json.ex_model7_prolactin_Jan2014_mdl
		
		def inputVariables = modelObject.MODEL_INPUT_VARIABLES
		
		logger.debug(inputVariables)
		
		assertEquals("Checking number of input variables", 23, inputVariables.size())
		assertEquals("Checking variable \"STU\"", ['name':'STU','type':'continuous','use':'covariate'], inputVariables[0])
		assertEquals("Checking variable \"ID\"", ['name':'ID','use':'id','level':'2'], inputVariables[1])
		assertEquals("Checking variable \"AMT\"", ['name':'AMT','use':'amt','units':'\"mg\"'], inputVariables[6])
	}
	
	@Test
	void testStructuralParametersBlock() {
		def json = getJsonFromMDLFile("drugX_ModelObject.mdl")[0] // The [0] is because the JSON is enclosed within superfluous square brackets [...]
		
		def modelObject = json.drugX_mdl
		
		def structuralParameters = modelObject.STRUCTURAL_PARAMETERS
		
		logger.debug(structuralParameters)

		assertEquals("Checking number of structural parameters", 8, structuralParameters.size())
		assertEquals("Checking parameter \"POP_Vc\"", ['name':'POP_Vc','units':'"L"'], structuralParameters[0])
		assertEquals("Checking variable \"POP_CL\"", ['name':'POP_CL','units':'"L/h"'], structuralParameters[2])
		assertEquals("Checking variable \"RUV_PROP\"", ['name':'RUV_PROP'], structuralParameters[7])
	}
	
	@Test
	void testVariabilityParametersBlock() {
		def json = getJsonFromMDLFile("prolactin_ModelObject.mdl")[0] // The [0] is because the JSON is enclosed within superfluous square brackets [...]
		
		def modelObject = json.ex_model7_prolactin_Jan2014_mdl
		
		def variabilityParameters = modelObject.VARIABILITY_PARAMETERS
		
		logger.debug(variabilityParameters)
		
		assertEquals("Checking the list of Variability Parameters",
			[ "PPV_PRL0", "PPV_KI", "PPV_KOUT", "PPV_AMP1", "PPV_PHS2", "PPV_IOV_IN_PRL0_1", "PPV_IOV_IN_PRL0_2", "PPV_IOV_IN_PRL0_3", "PPV_IOV_IN_PRL0_4", "RUV_EPS1" ].collect{
				[ 'name': it ]
			}, variabilityParameters)
	}
	
	@Test
	void testRandomVariableDefinitionBlock() {
		def json = getJsonFromMDLFile("drugX_ModelObject.mdl")[0] // The [0] is because the JSON is enclosed within superfluous square brackets [...]
		
		def modelObject = json.drugX_mdl
	
		def randomVariableDefinitions = modelObject.RANDOM_VARIABLE_DEFINITION
		
		logger.debug(randomVariableDefinitions)
		
		assertEquals("Checking the list of Random Variable Definitions", [
			[ 'name':'eta_PPV_Vc', 'type':'normal', 'mean':'0', 'var':'PPV_Vc', 'level':'ID' ],
			[ 'name':'eta_PPV_Vp', 'type':'normal', 'mean':'0', 'var':'PPV_Vp', 'level':'ID' ],
			[ 'name':'eta_PPV_CL', 'type':'normal', 'mean':'0', 'var':'PPV_CL', 'level':'ID' ],
			[ 'name':'eps_RUV_EPS', 'type':'normal', 'mean':'0', 'var':'RUV_EPS', 'level':'DV' ]
			], randomVariableDefinitions)
	}
	
	@Test
	void testModelOutputVariablesBlock() {
		
		def json = getJsonFromMDLFile("drugX_ModelObject.mdl")[0] // The [0] is because the JSON is enclosed within superfluous square brackets [...]
		
		def modelObject = json.drugX_mdl
		
		def modelOutputVariables = modelObject.MODEL_OUTPUT_VARIABLES
		
		logger.debug(modelOutputVariables)
		
		assertEquals("Checking the list of Model Output Variables",
			[ "ID", "TIME", "IPRED", "IWRES", "eta_PPV_Vc", "eta_PPV_Vp", "eta_PPV_CL", "WT" ].collect{
				[ 'name': it ]
		}, modelOutputVariables)
	}
	
	@Test
    void testModelObjectWithObservationBlock() {
		def json = getJsonFromMDLFile("prolactin_ModelObject.mdl")
		
		def modelObject = json.ex_model7_prolactin_Jan2014_mdl
		
		logger.debug(modelObject)
		
        def observation = modelObject.OBSERVATION
        
        assertEquals("Y = IPRED+eps_RUV_EPS1*W\n", modelObject.OBSERVATION[0].replaceAll("\r\n", "\n")) // Make the line endings consistent between Linux and Windows so tests pass on both
		
        def modelPrediction = modelObject.MODEL_PREDICTION
        
		logger.debug(modelPrediction)
		
		// (Skip checking of Model Prediction block since is tested in separate test method)
		
	}
	
	@Test
	void testModelObjectWithEstimationBlock() {
		def json = getJsonFromMDLFile("drugX_ModelObject.mdl")
		
		def modelObject = json.drugX_mdl
		
		logger.debug(modelObject)

		def groupVariables = modelObject.GROUP_VARIABLES
		
		def expectedGroupVariablesBlock = """GRPVc = POP_Vc*(WT/70)
GRPVp = POP_Vp
GRPCL = POP_CL
GRPka = POP_ka
GRPQ = POP_Q
GRPMTT = POP_MTT
GRPn = POP_n
"""
		// Note that we need to make the line endings consistent between actual vs expected
		assertEquals("Checking the Group Variables block", expectedGroupVariablesBlock, groupVariables[0].replace("\r\n", "\n"))
	
		def individualVariables = modelObject.INDIVIDUAL_VARIABLES
		
		def expectedIndividualVariablesBlock = """Vc = GRPVc*exp(eta_PPV_Vc)
        Vp = GRPVp*exp(eta_PPV_Vp)
        CL = GRPCL*exp(eta_PPV_CL)
        ka = GRPka
        Q = GRPQ
        MTT = GRPMTT
        n = GRPn
        kcp = Q/Vc
        kpc = Q/Vp
        kel = CL/Vc
        ktr = (n+1)/MTT
        LNFAC = ln(2.5066)+(n+0.5)*ln(n)-n
        F1 = 0
        if (AMT>0) {
	PODO = AMT
}
"""
		// Note that we need to make the line endings consistent between actual vs expected
		assertEquals("Checking the Individual Variables block", expectedIndividualVariablesBlock, individualVariables[0].replace("\r\n", "\n"))
		
        def modelPrediction = modelObject.MODEL_PREDICTION
        
		logger.debug(modelPrediction)
		
		// (Skip checking of Model Prediction block since is tested in separate test method)
		
		def estimationBlock = modelObject.ESTIMATION
		
		logger.debug(estimationBlock)
		
		def expectedEstimationBlock = """IPRED = CENTRAL/Vc
if (IPRED==0) {
	IPRED = 0.0001
}
W = RUV_PROP*IPRED
IRES = DV-IPRED
IWRES = IRES/W
Y = IPRED+W*eps_RUV_EPS
"""
		// Note that we need to make the line endings consistent between actual vs expected
		assertEquals("Checking the Estimation block", expectedEstimationBlock, estimationBlock[0].replace("\r\n", "\n"))
		
	}
    
    @Test
    public void testModelObjectModelPredictionObject() {
        def json = getJsonFromMDLFile("prolactin_ModelObject.mdl")
        
        def modelObject = json.ex_model7_prolactin_Jan2014_mdl
        
        def modPred = modelObject.MODEL_PREDICTION
        
        logger.debug(modPred[0])
        
        def ode = modPred.ODE
        def library = modPred.LIBRARY
        def content = modPred.content
        
        logger.debug("Ode :- " + ode[0])
        logger.debug("Library :- " + library[0])
        logger.debug("content := " + content[0])
        
        def expectedOdeBlock = """            CP = 1000*CENT/V2
            DIU1 = AMP1*cos(2*PI*(T-PHS1)/24)
            DIU2 = AMP2*cos(2*PI*(T-PHS2)/12)
            DIU = DIU1+DIU2
            RSTR = 1
            if (DA>0) {
	RSTR = DA/(1+CP/KI+DA)
}
            KIN = KINM*(1-RSTR)+KINB*DIU
            FEED = 1
            if (PROL>0) {
	FEED = (PROL/PRL0)^UPDA
}
            ABS = ode(deriv = -KA*ABS)
            CENT = ode(deriv = KA*ABS-(K23+K)*CENT+K32*PERI)
            PERI = ode(deriv = K23*CENT-K32*PERI)
            PROL = ode(deriv = KIN-KOUT*PROL)
            DA = ode(deriv = KDA*F5*FEED-KDA*DA)
"""
        // Note that we need to make the line endings consistent between actual vs expected
        assertEquals("Checking the ODE block", expectedOdeBlock, ode[0].replace("\r\n", "\n"))
        
        assertEquals("Checking the LIBRARY block", "            amount=nmadvan(model = 6, output = list(A, F))\n", library[0])
        
        def expectedContentBlock = """        IPRED = 0
        if (PROL>0) {
	IPRED = ln(PROL)
}
        W = POP_RES_ERR_IN_MALE_HV
        if (PAT==1) {
	W = POP_RES_ERR_IN_MALE_PATIENTS
}
        if (SEX==1) {
	W = POP_RES_ERR_IN_FEMALE_PATIENTS
}
        IRES = DV-IPRED
        IWRES = IRES/W
        STRT = TRT
"""
		// Note that we need to make the line endings consistent between actual vs expected
        assertEquals("Checking the content block", expectedContentBlock, content[0].replace("\r\n", "\n"))
        
    }
	
	@Test
	public void testThatConditionalStatementWrittenOutWithBraces() {
		def json = getJsonFromMDLFile("conditionalStmtWithBraces.mdl")
		
		def modelObject = json.Hamren2008_mdl
		
		def expectedContentBlock = """if (SEX==2) {
	POP_EC_50_FPG = POP_EC_50_FPG_F
	POP_K_IN_RBC = POP_K_IN_RBC_F
} else {
	POP_EC_50_FPG = POP_EC_50_FPG_F+BETA_EC_50_FPG
	POP_K_IN_RBC = POP_K_IN_RBC_F+BETA_K_IN_RBC
}
if (TREAT==1) {
	POP_FPG_BASELINE = POP_FPG_BASELINE_N
	if (TREAT==1) {
		POP_FPG_WASHOUT = POP_FPG_WASHOUT_N
	}
} else {
	POP_FPG_BASELINE = POP_FPG_BASELINE_N+BETA_FPG_BASELINE
	POP_FPG_WASHOUT = POP_FPG_WASHOUT_N+BETA_FPG_WASHOUT
}
"""
		
		assertEquals("Checking the content of the block", expectedContentBlock, modelObject.GROUP_VARIABLES[0].replace("\r\n", "\n"))
		
	}
	
}