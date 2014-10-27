package eu.ddmore.converter.mdl2json

import static org.junit.Assert.*

import org.apache.log4j.Logger
import org.junit.Test
import org.junit.Ignore

import eu.ddmore.converter.mdl2json.domain.MCLFile
import eu.ddmore.converter.mdl2json.domain.Model
import groovy.json.JsonSlurper


class TestJSONModelObjectToMDL extends ConverterTestsParent {
	private static final Logger logger = Logger.getLogger(TestJSONModelObjectToMDL.class)
	
	// Using slashy strings /.../ here so we don't have to escape anything other than forward slashes
	private final static String structParamsBlockJson =
		/ {"STRUCTURAL_PARAMETERS":[{"name":"POP_CL"},{"name":"POP_V"},{"name":"POP_KA"},{"name":"POP_TLAG"},{"name":"BETA_WT_CL"},{"name":"BETA_WT_V"}]} /
	private final static String variabilityParamsBlockJson =
		/ {"VARIABILITY_PARAMETERS":[{"name":"PPV_CL"},{"name":"PPV_V"},{"name":"PPV_KA"},{"name":"PPV_TLAG"},{"name":"CORR_PPV_CL_V"},{"name":"RUV_PROP"},{"name":"RUV_ADD"}]} /
	private final static String individualVarsBlockJson =
		/ {"INDIVIDUAL_VARIABLES":[{"fixEff":"[BETA_WT_CL]","trans":"log","name":"CL","cov":"[logtWT]","ranEff":"ETA_CL","pop":"POP_CL","type":"linear"},{"fixEff":"[BETA_WT_V]","trans":"log","name":"V","cov":"[logtWT]","ranEff":"ETA_V","pop":"POP_V","type":"linear"},{"trans":"log","name":"KA","ranEff":"ETA_KA","pop":"POP_KA","type":"linear"},{"trans":"log","name":"TLAG","ranEff":"ETA_TLAG","pop":"POP_TLAG","type":"linear"}]} /
	private final static String randomVarDefinitionBlockJson =
		/ {"RANDOM_VARIABLE_DEFINITION":[{"name":"ETA_CL","complexAttrs":{"type":"normal","mean":"0","sd":"PPV_CL","level":"ID"}},{"name":"ETA_V","complexAttrs":{"type":"normal","mean":"0","sd":"PPV_V","level":"ID"}},{"name":"ETA_KA","complexAttrs":{"type":"normal","mean":"0","sd":"PPV_KA","level":"ID"}},{"name":"ETA_TLAG","complexAttrs":{"type":"normal","mean":"0","sd":"PPV_TLAG","level":"ID"}},{"attrs":{"type":"CORR","rv1":"ETA_CL","rv2":"ETA_V","level":"ID"},"name":"CORR_PPV_CL_V"}]} /
	private final static String modelOutputVarsBlockJson =
		/ {"MODEL_OUTPUT_VARIABLES":[{"name":"ID"},{"name":"TIME"},{"name":"logtWT"},{"name":"CL"},{"name":"V"},{"name":"KA"},{"name":"TLAG"},{"name":"Y"}]} /
	private final static String modelInputVarsBlockJson =
		/ {"MODEL_INPUT_VARIABLES":[{"level":"2","name":"ID","use":"id"},{"name":"TIME","use":"idv"},{"name":"logtWT","use":"covariate","type":"continuous"},{"name":"AMT","use":"amt"},{"level":"1","name":"DV","use":"dv"},{"name":"MDV","use":"mdv"}]} /
	private final static String observationBlockJson =
		/ {"OBSERVATION":[{"name":"EPS_Y","complexAttrs":{"type":"normal","mean":"0","var":"1","level":"DV"}},{"attrs":{"type":"continuous","error":"combined1(additive=RUV_ADD, proportional=RUV_PROP, f=CC)","eps":"EPS_Y","prediction":"CC"},"name":"Y"}]} /
	private final static String modelPredictionBlockJson =
		/ {"MODEL_PREDICTION":{"ODE":"            if (T>=TLAG) {\r\n\tRATEIN = GUT*KA\r\n} else {\r\n\tRATEIN = 0\r\n}\r\n            GUT : {deriv = (-RATEIN), init = 0, x0 = 0}\r\n            CENTRAL : {deriv = (RATEIN-CL*CENTRAL\/V), init = 0, x0 = 0}\r\n","content":"        CC = CENTRAL\/V\r\n"}} /
	
	@Test
	public void testStructuralParametersBlock() {

		def json = getJson(structParamsBlockJson)

		def modelObj = new Model(json)
		
		String expected = """mdlobj {

    STRUCTURAL_PARAMETERS {
        POP_CL
        POP_V
        POP_KA
        POP_TLAG
        BETA_WT_CL
        BETA_WT_V
    }

}
"""
		assertEquals(expected, modelObj.toMDL())
	}
	
	@Test
	public void testVariabilityParametersBlock() {
		def json = getJson(variabilityParamsBlockJson)

		def modelObj = new Model(json)
		
		String expected = """mdlobj {

    VARIABILITY_PARAMETERS {
        PPV_CL
        PPV_V
        PPV_KA
        PPV_TLAG
        CORR_PPV_CL_V
        RUV_PROP
        RUV_ADD
    }

}
"""
		assertEquals(expected, modelObj.toMDL())
	}
	
	@Test
	void testIndividualVariablesBlock() {
		def json = getJson(individualVarsBlockJson)

		def modelObj = new Model(json)
		
		String expected = """mdlobj {

    INDIVIDUAL_VARIABLES {
        CL : {cov=[logtWT], fixEff=[BETA_WT_CL], pop=POP_CL, ranEff=ETA_CL, trans=log, type=linear}
        V : {cov=[logtWT], fixEff=[BETA_WT_V], pop=POP_V, ranEff=ETA_V, trans=log, type=linear}
        KA : {pop=POP_KA, ranEff=ETA_KA, trans=log, type=linear}
        TLAG : {pop=POP_TLAG, ranEff=ETA_TLAG, trans=log, type=linear}
    }

}
"""
		assertEquals(expected, modelObj.toMDL())
	}
	
	@Test
	public void testRandomVariableDefinitionBlock() {

		def json = getJson(randomVarDefinitionBlockJson)

		def modelObj = new Model(json)
		
		String expected = """mdlobj {

    RANDOM_VARIABLE_DEFINITION {
        ETA_CL ~ (level=ID, mean=0, sd=PPV_CL, type=normal)
        ETA_V ~ (level=ID, mean=0, sd=PPV_V, type=normal)
        ETA_KA ~ (level=ID, mean=0, sd=PPV_KA, type=normal)
        ETA_TLAG ~ (level=ID, mean=0, sd=PPV_TLAG, type=normal)
        CORR_PPV_CL_V : {level=ID, rv1=ETA_CL, rv2=ETA_V, type=CORR}
    }

}
"""
		assertEquals(expected, modelObj.toMDL())
	}
	
	@Test
	public void testModelOutputVariablesBlock() {

		def json = getJson(modelOutputVarsBlockJson)

		def modelObj = new Model(json)
		
		String expected = """mdlobj {

    MODEL_OUTPUT_VARIABLES {
        ID
        TIME
        logtWT
        CL
        V
        KA
        TLAG
        Y
    }

}
"""
		assertEquals(expected, modelObj.toMDL())
	}
	
	@Test
	public void testModelInputVariablesBlock() {

		def json = getJson(modelInputVarsBlockJson)

		def modelObj = new Model(json)
		
		String expected = """mdlobj {

    MODEL_INPUT_VARIABLES {
        ID : {level=2, use=id}
        TIME : {use=idv}
        logtWT : {type=continuous, use=covariate}
        AMT : {use=amt}
        DV : {level=1, use=dv}
        MDV : {use=mdv}
    }

}
"""
		assertEquals(expected, modelObj.toMDL())
	}
	
	@Test
	void testObservationBlock() {
		def json = getJson(observationBlockJson)

		def modelObj = new Model(json)
		
		String expected = """mdlobj {

    OBSERVATION {
        EPS_Y ~ (level=DV, mean=0, type=normal, var=1)
        Y : {eps=EPS_Y, error=combined1(additive=RUV_ADD, proportional=RUV_PROP, f=CC), prediction=CC, type=continuous}
    }

}
"""
		assertEquals(expected, modelObj.toMDL())
	}
	
	@Test
	public void testModelPredictionBlock() {
		def json = getJson(modelPredictionBlockJson)

		def modelObj = new Model(json)
		
		String expected = """mdlobj {

    MODEL_PREDICTION {
        
        ODE {
            if (T>=TLAG) {
	RATEIN = GUT*KA
} else {
	RATEIN = 0
}
            GUT : {deriv = (-RATEIN), init = 0, x0 = 0}
            CENTRAL : {deriv = (RATEIN-CL*CENTRAL/V), init = 0, x0 = 0}
        }
        
        CC = CENTRAL/V

    }

}
"""
		// Note: the replace makes the line endings consistent so text compare passes
		assertEquals(expected, modelObj.toMDL().replace("\r\n", "\n"))
	}
	
	@Test
	@Ignore
	void testGroupVariablesBlock() {
		fail("Not implemented yet")
	}
	
	@Test
	@Ignore
	void testEstimationBlock() {
		fail("Not implemented yet")
	}
	
}
