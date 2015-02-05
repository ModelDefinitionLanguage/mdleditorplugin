package eu.ddmore.converter.mdl2json;

import static org.junit.Assert.*

import org.apache.log4j.Logger
import org.junit.Test
import org.junit.Ignore

import eu.ddmore.converter.mdl2json.domain.Variable

class TestModelObjectToJSON extends ConverterTestsParent {
	private static Logger logger = Logger.getLogger(TestModelObjectToJSON.class)
	
	@Test
	void testStructuralParametersBlock() {
		def json = getJsonFromMDLFile("warfarinODE_28Oct2014_ModelObject.mdl")[0] // The [0] is because the JSON is enclosed within superfluous square brackets [...]
		
		def modelObject = json.warfarin_PK_ODE_mdl
		
		def structuralParameters = modelObject.STRUCTURAL_PARAMETERS
		
		logger.debug(structuralParameters)

		assertEquals("Checking number of structural parameters", 6, structuralParameters.size())
		assertEquals("Checking parameter 1/6", [(Variable.NAME_KEY):'POP_CL'], structuralParameters[0])
		assertEquals("Checking parameter 2/6", [(Variable.NAME_KEY):'POP_V'], structuralParameters[1])
		assertEquals("Checking parameter 3/6", [(Variable.NAME_KEY):'POP_KA'], structuralParameters[2])
		assertEquals("Checking parameter 4/6", [(Variable.NAME_KEY):'POP_TLAG'], structuralParameters[3])
		assertEquals("Checking parameter 5/6", [(Variable.NAME_KEY):'BETA_CL_WT'], structuralParameters[4])
		assertEquals("Checking parameter 6/6", [(Variable.NAME_KEY):'BETA_V_WT'], structuralParameters[5])
	}
	
	@Test
	void testVariabilityParametersBlock() {
		def json = getJsonFromMDLFile("warfarinODE_28Oct2014_ModelObject.mdl")[0] // The [0] is because the JSON is enclosed within superfluous square brackets [...]
		
		def modelObject = json.warfarin_PK_ODE_mdl
		
		def variabilityParameters = modelObject.VARIABILITY_PARAMETERS
		
		logger.debug(variabilityParameters)
		
		assertEquals("Checking number of variability parameters", 6, variabilityParameters.size())
		assertEquals("Checking parameter 1/7", [(Variable.NAME_KEY):'PPV_CL'], variabilityParameters[0])
		assertEquals("Checking parameter 2/7", [(Variable.NAME_KEY):'PPV_V'], variabilityParameters[1])
		assertEquals("Checking parameter 3/7", [(Variable.NAME_KEY):'PPV_KA'], variabilityParameters[2])
		assertEquals("Checking parameter 4/7", [(Variable.NAME_KEY):'PPV_TLAG'], variabilityParameters[3])
		assertEquals("Checking parameter 6/7", [(Variable.NAME_KEY):'RUV_PROP'], variabilityParameters[4])
		assertEquals("Checking parameter 6/7", [(Variable.NAME_KEY):'RUV_ADD'], variabilityParameters[5])
	}
	
	@Test
	void testIndividualVariablesBlock() {
		def json = getJsonFromMDLFile("warfarinODE_28Oct2014_ModelObject.mdl")[0] // The [0] is because the JSON is enclosed within superfluous square brackets [...]
		
		def modelObject = json.warfarin_PK_ODE_mdl
		
		def individualVars = modelObject.INDIVIDUAL_VARIABLES
		
		logger.debug(individualVars)
		
		assertEquals("Checking number of individual variables", 4, individualVars.size())
		assertEquals("Checking variable 1/4",
			[ (Variable.NAME_KEY):'CL', 'type':'linear', 'trans':'log', 'pop':'POP_CL', 'fixEff':'[BETA_CL_WT]', 'cov':'[logtWT]', 'ranEff':'ETA_CL' ],
			individualVars[0])
		assertEquals("Checking variable 2/4",
			[ (Variable.NAME_KEY):'V', 'type':'linear', 'trans':'log', 'pop':'POP_V', 'fixEff':'[BETA_V_WT]', 'cov':'[logtWT]', 'ranEff':'ETA_V' ],
			individualVars[1])
		assertEquals("Checking variable 3/4",
			[ (Variable.NAME_KEY):'KA', 'type':'linear', 'trans':'log', 'pop':'POP_KA', 'ranEff':'ETA_KA' ],
			individualVars[2])
		assertEquals("Checking variable 4/4",
			[ (Variable.NAME_KEY):'TLAG', 'type':'linear', 'trans':'log', 'pop':'POP_TLAG', 'ranEff':'ETA_TLAG' ],
			individualVars[3])

	}
    
    @Test
    void testIndividualVariablesBlockContainingMixtureOfParameterListsAndSimpleExpressions() {
        def json = getJsonFromMDLFile("Nock_2013_Carboplatin_PK_ModelObject.mdl")[0] // The [0] is because the JSON is enclosed within superfluous square brackets [...]
        
        def modelObject = json.Nock_2013_Carboplatin_PK_mdl
        
        def individualVars = modelObject.INDIVIDUAL_VARIABLES
        
        logger.debug(individualVars)
        
        assertEquals("Checking number of individual variables", 8, individualVars.size())
        
        assertEquals("Checking variable 1/8",
            [ (Variable.NAME_KEY):'CL', 'type':'linear', 'trans':'log', 'pop':'THCL', 'fixEff':'[CLCLCR_COV]', 'cov':'[logtCLCR]', 'ranEff':'eta_OMCL' ],
            individualVars[0])
        assertEquals("Checking variable 2/8", [ (Variable.NAME_KEY):'V1', (Variable.EXPRESSION_KEY):'TVV1' ], individualVars[1])
        assertEquals("Checking variable 3/8", [ (Variable.NAME_KEY):'Q', (Variable.EXPRESSION_KEY):'THQ' ], individualVars[2])
        assertEquals("Checking variable 4/8", [ (Variable.NAME_KEY):'V2', (Variable.EXPRESSION_KEY):'THV2' ], individualVars[3])
        assertEquals("Checking variable 5/8", [ (Variable.NAME_KEY):'VSS', (Variable.EXPRESSION_KEY):'V1+V2' ], individualVars[4])
        assertEquals("Checking variable 6/8", [ (Variable.NAME_KEY):'K', (Variable.EXPRESSION_KEY):'CL/V1' ], individualVars[5])
        assertEquals("Checking variable 7/8", [ (Variable.NAME_KEY):'K12', (Variable.EXPRESSION_KEY):'Q/V1' ], individualVars[6])
        assertEquals("Checking variable 8/8", [ (Variable.NAME_KEY):'K21', (Variable.EXPRESSION_KEY):'Q/V2' ], individualVars[7])
    }
    
    // This test specifically picked up a bug in XtextWrapper re a multi-part expression without brackets and having a mixture of operators
    @Test
    void testIndividualVariablesBlockContainingComplexExpressions() {
        def json = getJsonFromMDLFile("Rocchetti2013_ModelObject_IndividualVariables.mdl")[0] // The [0] is because the JSON is enclosed within superfluous square brackets [...]
        
        def modelObject = json.rocchetti2013_mdl
        
        def individualVars = modelObject.INDIVIDUAL_VARIABLES
        
        logger.debug(individualVars)
        
        assertEquals("Checking number of individual variables", 10, individualVars.size())
        
        assertEquals("Checking variable 1/10",
            [ (Variable.EXPRESSION_KEY):'24*ln(2)/24', (Variable.NAME_KEY):'KA_A' ],
            individualVars[0])
        assertEquals("Checking variable 2/10",
            [ (Variable.EXPRESSION_KEY):'ln(2)/6.05', (Variable.NAME_KEY):'KE_A' ],
            individualVars[1])
        assertEquals("Checking variable 3/10",
            [ (Variable.EXPRESSION_KEY):'1/0.119', (Variable.NAME_KEY):'FV1_A' ],
            individualVars[2])
        assertEquals("Checking variable 4/10",
            [ (Variable.EXPRESSION_KEY):'18.8', (Variable.NAME_KEY):'KA_B' ],
            individualVars[3])
    }
	
	@Test
	void testRandomVariableDefinitionBlock() {
		def json = getJsonFromMDLFile("warfarinODE_28Oct2014_ModelObject.mdl")[0] // The [0] is because the JSON is enclosed within superfluous square brackets [...]
		
		def modelObject = json.warfarin_PK_ODE_mdl
	
		def randomVariableDefinitions = modelObject.RANDOM_VARIABLE_DEFINITION
		
		logger.debug(randomVariableDefinitions)
		
		assertEquals("Checking number of random variable definitions", 5, randomVariableDefinitions.size())
		
		assertEquals("Checking name of distribution-variable 1/4", 'ETA_CL', randomVariableDefinitions[0][(Variable.NAME_KEY)])
		assertEquals("Checking distribution-variable 1/4", [ 'type':'normal', 'mean':'0', 'sd':'PPV_CL', 'level':'ID' ], randomVariableDefinitions[0][Variable.RANDOMVAR_ATTRS_KEY])
		assertEquals("Checking name of distribution-variable 2/4", 'ETA_V', randomVariableDefinitions[1][(Variable.NAME_KEY)])
		assertEquals("Checking distribution-variable 2/4", [ 'type':'normal', 'mean':'0', 'sd':'PPV_V', 'level':'ID' ], randomVariableDefinitions[1][Variable.RANDOMVAR_ATTRS_KEY])
		assertEquals("Checking name of distribution-variable 3/4", 'ETA_KA', randomVariableDefinitions[2][(Variable.NAME_KEY)])
		assertEquals("Checking distribution-variable 3/4", [ 'type':'normal', 'mean':'0', 'sd':'PPV_KA', 'level':'ID' ], randomVariableDefinitions[2][Variable.RANDOMVAR_ATTRS_KEY])
		assertEquals("Checking name of distribution-variable 4/4", 'ETA_TLAG', randomVariableDefinitions[3][(Variable.NAME_KEY)])
		assertEquals("Checking distribution-variable 4/4", [ 'type':'normal', 'mean':'0', 'sd':'PPV_TLAG', 'level':'ID' ], randomVariableDefinitions[3][Variable.RANDOMVAR_ATTRS_KEY])
		assertEquals("Checking name of variable-definition 1/1", 'CORR_PPV_CL_V', randomVariableDefinitions[4][(Variable.NAME_KEY)])
		assertEquals("Checking variable-definition 1/1", [ (Variable.NAME_KEY):'CORR_PPV_CL_V', 'type':'CORR', 'rv1':'ETA_CL', 'rv2':'ETA_V', 'level':'ID' ], randomVariableDefinitions[4])
	}
	
	@Test
	void testModelOutputVariablesBlock() {
		
		def json = getJsonFromMDLFile("warfarinODE_28Oct2014_ModelObject.mdl")[0] // The [0] is because the JSON is enclosed within superfluous square brackets [...]
		
		def modelObject = json.warfarin_PK_ODE_mdl
				
		def modelOutputVariables = modelObject.MODEL_OUTPUT_VARIABLES
		
		logger.debug(modelOutputVariables)
		
		assertEquals("Checking the list of Model Output Variables",
			[ "ID", "TIME", "logtWT", "CL", "V", "KA", "TLAG", "Y" ].collect{
				[ (Variable.NAME_KEY): it ]
		}, modelOutputVariables)
	}
	
	@Test
	void testModelInputVariablesBlock() {
		def json = getJsonFromMDLFile("warfarinODE_28Oct2014_ModelObject.mdl")[0] // The [0] is because the JSON is enclosed within superfluous square brackets [...]
		
		def modelObject = json.warfarin_PK_ODE_mdl
		
		def inputVariables = modelObject.MODEL_INPUT_VARIABLES
		
		logger.debug(inputVariables)
		
		assertEquals("Checking number of input variables", 6, inputVariables.size())
		assertEquals("Checking variable 1/6", [ (Variable.NAME_KEY):'ID', 'use':'id', 'level':'2' ], inputVariables[0])
		assertEquals("Checking variable 2/6", [ (Variable.NAME_KEY):'TIME', 'use':'idv' ], inputVariables[1])
		assertEquals("Checking variable 3/6", [ (Variable.NAME_KEY):'AMT', 'use':'amt', 'administration':'GUT' ], inputVariables[2])
		assertEquals("Checking variable 4/6", [ (Variable.NAME_KEY):'DV', 'use':'dv', 'level':'1', 'prediction':'Y' ], inputVariables[3])
		assertEquals("Checking variable 5/6", [ (Variable.NAME_KEY):'MDV', 'use':'mdv' ], inputVariables[4])
		assertEquals("Checking variable 6/6", [ (Variable.NAME_KEY):'logtWT', 'use':'covariate', 'type':'continuous' ], inputVariables[5])
	}
	
	@Test
    void testObservationBlock() {
		def json = getJsonFromMDLFile("warfarinODE_28Oct2014_ModelObject.mdl")[0] // The [0] is because the JSON is enclosed within superfluous square brackets [...]
		
		def modelObject = json.warfarin_PK_ODE_mdl
		
		logger.debug(modelObject)
		
        def observation = modelObject.OBSERVATION
        
		assertEquals("Checking number of statements within the observation block", 2, observation.size())
		assertEquals("Checking name of variable in observation statement 1/2", "EPS_Y", observation[0][Variable.NAME_KEY])
		assertEquals("Checking variable in observation statement 1/2",
			[ 'type':'normal', 'mean':'0', 'var':'1', 'level':'DV' ],
			observation[0][Variable.RANDOMVAR_ATTRS_KEY])
		assertEquals("Checking name of variable in observation statement 2/2", "Y", observation[1][Variable.NAME_KEY])
		assertEquals("Checking variable in observation statement 2/2",
			[ (Variable.NAME_KEY):'Y', 'type':'continuous', 'error':'combinedError1(additive=RUV_ADD, proportional=RUV_PROP, f=CC)', 'eps':'EPS_Y', 'prediction':'CC' ],
			observation[1])
	}
	
    @Test
    public void testModelPredictionBlock() {
		def json = getJsonFromMDLFile("warfarinODE_28Oct2014_ModelObject.mdl")[0] // The [0] is because the JSON is enclosed within superfluous square brackets [...]
		
		def modelObject = json.warfarin_PK_ODE_mdl
        
        def modPred = modelObject.MODEL_PREDICTION
        
        logger.debug('ModPred = ' + modPred)
        
        def ode = modPred.ODE
        def library = modPred.LIBRARY
        def content = modPred.content
        
        logger.debug("Ode = " + ode)
        logger.debug("Library = " + library)
        logger.debug("content = " + content)

		def expectedOdeBlock = """            RATEIN = GUT*KA when T>=TLAG otherwise 0
            GUT : {deriv = (-RATEIN), init = 0, x0 = 0}
            CENTRAL : {deriv = (RATEIN-CL*CENTRAL/V), init = 0, x0 = 0}"""
        // Note that we need to make the line endings consistent between actual vs expected
        assertEquals("Checking the ODE block", expectedOdeBlock, ode.replace("\r\n", "\n"))
        
		// TODO: Need to test the Library block

		def expectedContentBlock = """        CC = CENTRAL/V"""
		// Note that we need to make the line endings consistent between actual vs expected
        assertEquals("Checking the content block", expectedContentBlock, content.replace("\r\n", "\n"))
        
    }
	
	@Test
	void testGroupVariablesBlock() {
        def json = getJsonFromMDLFile("Nock_2013_Carboplatin_PK_ModelObject.mdl")[0] // The [0] is because the JSON is enclosed within superfluous square brackets [...]
        
        def modelObject = json.Nock_2013_Carboplatin_PK_mdl
        
        def groupVars = modelObject.GROUP_VARIABLES
        
        logger.debug('Group Variables = ' + groupVars)
        
        assertEquals("Should be two Group Variables", 2, groupVars.size())
        Map gv1 = groupVars[0]
        Map gv2 = groupVars[1]
        
        assertEquals("Checking Group Variable variable name 1/2", "V1KG", gv1.keySet().toArray()[0])
        assertEquals("Checking Group Variable variable name 2/2", "TVV1", gv2.keySet().toArray()[0])
        assertEquals("Checking Group Variable expression 1/2", "(KG/80)^V1KG_COV", gv1.get("V1KG"))
        assertEquals("Checking Group Variable expression 2/2", "THV1*V1KG", gv2.get("TVV1"))
	}
	
    @Test
    @Ignore
    void testEstimationBlock() {
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
    
}
