package eu.ddmore.mdl.tests

import com.google.inject.Inject
import eu.ddmore.mdl.mdl.Mcl
import java.util.Deque
import org.eclipse.xtext.testing.InjectWith
import org.eclipse.xtext.testing.XtextRunner
import org.eclipse.xtext.testing.validation.ValidationTestHelper
import org.junit.Assert
import org.junit.Test
import org.junit.runner.RunWith
import eu.ddmore.mdl.utils.BlockUtils

@RunWith(typeof(XtextRunner))
@InjectWith(typeof(MdlAndLibInjectorProvider))
class MclParserModelObj13Test {
	@Inject extension MdlTestHelper<Mcl>
	@Inject extension ValidationTestHelper

	extension BlockUtils bu = new BlockUtils
	
	val static CODE_SNIPPET = '''
warfarin_PK_ODE_mdl = mdlObj {
	IDV{ T }
	
	COVARIATES(type is constant){
		logtWT
	}

	VARIABILITY_LEVELS{
		ID : { level=2, type is parameter }
		DV : { level=1, type is observation }
	} 

	STRUCTURAL_PARAMETERS { 
		POP_CL
		POP_V
		POP_KA
		POP_TLAG
		BETA_CL_WT
		BETA_V_WT
		RUV_PROP
		RUV_ADD
	} # end STRUCTURAL_PARAMETERS
	
	VARIABILITY_PARAMETERS {
		PPV_CL
		PPV_V
		PPV_KA
		PPV_TLAG
	} # end VARIABILITY_PARAMETERS 
	
	RANDOM_VARIABLE_DEFINITION(level=ID) {
		ETA_CL ~ Normal(mean = 0, sd = PPV_CL) 
		ETA_V ~ Normal(mean = 0, sd = PPV_V)
		ETA_KA ~ Normal(mean = 0, sd = PPV_KA)
		ETA_TLAG ~ Normal(mean = 0, sd = PPV_TLAG) # We define correlation here
	} # end RANDOM_VARIABLE_DEFINITION 

	
	INDIVIDUAL_VARIABLES { # This maps to the "Type 3" individual parameter definition in PharmML
	    CL : piecewise {{ { type is linear, trans is ln, pop=POP_CL, fixEff = {coeff=BETA_CL_WT, cov=logtWT} , ranEff = ETA_CL }
	    		when (POP_CL > 0);
	    		otherwise { type is general, trans is ln, grp=POP_CL, ranEff = ETA_CL } }}
	    V : if(POP_CL >0) then { type is linear, trans is ln, pop=POP_V, fixEff = {coeff=BETA_V_WT, cov=logtWT} , ranEff = ETA_V }
	    	else { type is general, trans is ln, grp=POP_CL, ranEff = ETA_CL } 
	    KA : { type is linear, trans is ln, pop=POP_KA, ranEff = ETA_KA }
	    TLAG : { type is linear, trans is ln, pop=POP_TLAG, ranEff = ETA_TLAG } 
	} # end INDIVIDUAL_VARIABLES

	MODEL_PREDICTION {
		DEQ{
			RATEIN = if(T >= TLAG) then GUT * KA else 0
			GUT : { deriv = (- RATEIN), init = 0, x0 = 0 } 
			CENTRAL : { deriv=RATEIN - CL * CENTRAL / V, init = 0, x0 = 0 }  
		}
	    CC = CENTRAL / V 
	} # end MODEL_PREDICTION
	
	RANDOM_VARIABLE_DEFINITION(level=DV){
	    EPS_Y ~ Normal(mean = 0, var = 1) # This maps the standard error model in PharmML. The error model is predefined.
	}

	OBSERVATION {
	    Y : { type is combinedError1, additive = RUV_ADD, proportional = RUV_PROP, eps = EPS_Y, prediction = CC } 
	} # end OBSERVATION
} # end of model object
		'''
	
	@Test
	def void testParsing(){
		CODE_SNIPPET.parse.assertNoErrors
		
	}
	
	@Test
	def void testBlocks(){
		val mcl = CODE_SNIPPET.parse
		val Deque<String> expectedBlks = newLinkedList("IDV", "COVARIATES", "VARIABILITY_LEVELS", "STRUCTURAL_PARAMETERS",
			"VARIABILITY_PARAMETERS", "RANDOM_VARIABLE_DEFINITION", "INDIVIDUAL_VARIABLES", "MODEL_PREDICTION",
			"RANDOM_VARIABLE_DEFINITION", "OBSERVATION"
		);
		for(blk : mcl.objects.last.blocks){
			Assert::assertEquals(expectedBlks.pop, blk.identifier)
		}
	}

	
}