package eu.ddmore.mdl.tests

import com.google.inject.Inject
import eu.ddmore.mdl.mdl.Mcl
import org.eclipse.xtext.testing.InjectWith
import org.eclipse.xtext.testing.XtextRunner
import org.eclipse.xtext.testing.validation.ValidationTestHelper
import org.junit.Test
import org.junit.runner.RunWith

@RunWith(typeof(XtextRunner))
@InjectWith(typeof(MdlAndLibInjectorProvider))
class MclParserModelObj4Test {
	@Inject extension MdlTestHelper<Mcl>
	@Inject extension ValidationTestHelper
	
	val static CODE_SNIPPET = '''
warfarin_PK_2Compartments_mdl = mdlObj {
   IDV { T }
	
   COVARIATES{
      WT
      logtWT = ln(WT/70)
      SEX withCategories { male, female}
   }# end COVARIATES

   VARIABILITY_LEVELS{
      ID: {type is parameter, level=2}
      DV: {type is observation, level=1 }
   }# end VARIABILITY_LEVELS

   STRUCTURAL_PARAMETERS{
      POP_CL
      POP_VC
      POP_Q
      POP_VP
      POP_KA
      POP_TLAG
      POP_BETA_CL_WT
      POP_BETA_V_WT
      RUV_PROP
      RUV_ADD
      BETA_CL_SEX
   }# end STRUCTURAL_PARAMETERS

   VARIABILITY_PARAMETERS{
      PPV_CL
      PPV_VC
      PPV_Q
      PPV_VP
      PPV_KA
      PPV_TLAG
      RUV_EPS1
      COV_CL_V
      COV_CL_KA
      COV_V_KA
      CovMat = triangle([PPV_CL^2, COV_CL_V, PPV_VC^2, COV_CL_KA, COV_V_KA, PPV_KA^2], 3, true) 
   }# end VARIABILITY_PARAMETERS

   GROUP_VARIABLES{
   }# end GROUP_VARIABLES

   RANDOM_VARIABLE_DEFINITION (level=ID) {
      eta_PPV_CL ~ Normal(mean=0, sd=PPV_CL)
      eta_PPV_VC ~ Normal(mean=0, sd=PPV_VC)
      eta_PPV_Q ~ Normal(mean=0, sd=PPV_Q)
      eta_PPV_VP ~ Normal(mean=0, sd=PPV_VP)
      eta_PPV_KA ~ Normal(mean=0, sd=PPV_KA)
      eta_PPV_TLAG ~ Normal(mean=0, sd=PPV_TLAG)
      :: { type is covariance, matrix=CovMat }
   }# end RANDOM_VARIABLE_DEFINITION (level=ID)

   RANDOM_VARIABLE_DEFINITION (level=DV) {
      eps_RUV_EPS1 ~ Normal(mean=0, sd=RUV_EPS1)
   }# end RANDOM_VARIABLE_DEFINITION (level=DV)

   INDIVIDUAL_VARIABLES{
      CL : { type is linear, trans is ln, pop=POP_CL, fixEff = [{coeff=POP_BETA_CL_WT, cov=logtWT},
      				{coeff=BETA_CL_SEX, catCov=SEX.female}
      				] , ranEff = [eta_PPV_CL] }
      VC : { type is linear, trans is ln, pop=POP_VC, fixEff = [{coeff=POP_BETA_V_WT, cov=logtWT}] , ranEff = [eta_PPV_VC]  }
      Q : { type is linear, trans is ln, pop=POP_Q, fixEff = [{coeff=POP_BETA_CL_WT, cov=logtWT}] , ranEff = [eta_PPV_Q] }
      VP : { type is linear, trans is ln, pop=POP_VP, fixEff = [{coeff=POP_BETA_V_WT, cov=logtWT}] , ranEff = [eta_PPV_VP] }
      KA : { type is linear, trans is ln, pop=POP_KA, ranEff = [eta_PPV_KA] }
      TLAG : { type is linear, trans is ln, pop=POP_TLAG, ranEff = [eta_PPV_TLAG] }
      ALAG1=TLAG
      V2=VC
      V3=VP
      S2=VC

   }# end INDIVIDUAL_VARIABLES

   MODEL_PREDICTION{

   COMPARTMENT{
      ##  Parameters = CL,V2,Q,V3,KA,S2,ALAG1
      INPUT_KA:   {type is depot, modelCmt=1, to=CENTRAL, ka=KA, tlag=ALAG1}
      CENTRAL:    {type is compartment, modelCmt=2}
             ::   {type is elimination, modelCmt=2, from=CENTRAL, v=V2, cl=CL}
      PERIPHERAL: {type is distribution, modelCmt=3, from=CENTRAL, kin=Q/V2, kout=Q/V3}
   }# end COMPARTMENT

      F=CENTRAL/S2

      CC=F
   
   }# end MODEL_PREDICTION

   OBSERVATION{
         CC_obs : { type is combinedError1, lhsTrans = true, trans is ln, additive = RUV_ADD, proportional = RUV_PROP, eps = eps_RUV_EPS1, prediction = CC }
   }# end OBSERVATION
} # end of model object
		'''
	
	@Test
	def void testParsing(){
		CODE_SNIPPET.parse.assertNoErrors
		
	}

	
}