package eu.ddmore.converter.mdl2json;

import static org.junit.Assert.*;

import org.apache.log4j.Logger;
import org.junit.Test;

class TestModelToJSONConverter extends MDLToJSONTest {

	private static Logger logger = Logger.getLogger(TestModelToJSONConverter.class)
	
	@Test
	public void testTGIModel() {
		def json = getJsonFromMDLFile("tgiModel.mdl")
			
		def modelObject = json.run_final_OGTT_mdl
			
		logger.debug(modelObject)
	}
	
	@Test void testProlactinModel() {
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
		
		def modelPrediction = modelObject.MODEL_PREDICTION
		
		logger.debug(modelPrediction)
		
		def modelOutputVariables = modelObject.MODEL_OUTPUT_VARIABLES
		assertEquals(["ID", "TIME", "IPRED", "IWRES", "STU"], modelOutputVariables[0])

		def variabilityParameters = modelObject.VARIABILITY_PARAMETERS
		assertEquals([ "PPV_PRL0", "PPV_KI", "PPV_KOUT", "PPV_AMP1", "PPV_PHS2", "PPV_IOV_IN_PRL0_1", "PPV_IOV_IN_PRL0_2",
      "PPV_IOV_IN_PRL0_3", "PPV_IOV_IN_PRL0_4", "RUV_EPS1"], variabilityParameters[0])
	}

}
