package eu.ddmore.converter.mdl2json;

import static org.junit.Assert.*;

import org.apache.log4j.Logger;
import org.junit.Test;

class TestModelToJSONConverter extends ConverterTestsParent {
	private static Logger logger = Logger.getLogger(TestModelToJSONConverter.class)
	
	@Test
    void testModelObject() {
		def json = getJsonFromMDLFile("prolactinModel.mdl")
		
		def modelObject = json.ex_model7_prolactin_Jan2014_mdl
		
		logger.debug(modelObject)
		
		def inputVariables = modelObject.MODEL_INPUT_VARIABLES
		
		def STU = inputVariables.STU
		assertEquals("continuous", STU.type[0])
		
		def structuralParameters = modelObject.STRUCTURAL_PARAMETERS
		assertEquals([ "POP_KOUT", "POP_PRL0_IN_MALE_HV", "POP_PRL0_IN_MALE_PAT", "POP_PRL0_IN_FEMALES_PAT",
			"POP_PRL0_IN_MALES_STUDY_101", "POP_PRL0_IN_FEMALES_STUDY_101", "POP_KDA", "POP_UPDA",
			"POP_AMP", "POP_PHS1", "POP_AMP2", "POP_PHS2", "POP_KI", "POP_PHASE_SHIFT_IN_PATIENTS",
			"POP_RES_ERR_IN_MALE_HV", "POP_RES_ERR_IN_MALE_PATIENTS", "POP_RES_ERR_IN_FEMALE_PATIENTS" ], structuralParameters[0])
		
        def observation = modelObject.OBSERVATION
        
        assertEquals("Y = IPRED+eps_RUV_EPS1*W\n", modelObject.OBSERVATION[0].replaceAll("\r\n", "\n")) // Make the line endings consistent between Linux and Windows so tests pass on both
		
        def modelPrediction = modelObject.MODEL_PREDICTION
        
		logger.debug(modelPrediction)
		
		def modelOutputVariables = modelObject.MODEL_OUTPUT_VARIABLES
		assertEquals(["ID", "TIME", "IPRED", "IWRES", "STU"], modelOutputVariables[0])

		def variabilityParameters = modelObject.VARIABILITY_PARAMETERS
		assertEquals([ "PPV_PRL0", "PPV_KI", "PPV_KOUT", "PPV_AMP1", "PPV_PHS2", "PPV_IOV_IN_PRL0_1", "PPV_IOV_IN_PRL0_2",
      "PPV_IOV_IN_PRL0_3", "PPV_IOV_IN_PRL0_4", "RUV_EPS1"], variabilityParameters[0])
	}
    
    @Test
    public void testModelObjectModelPredictionObject() {
        def json = getJsonFromMDLFile("prolactinModel.mdl")
        
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
	
}
