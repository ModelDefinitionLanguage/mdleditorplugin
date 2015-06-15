package eu.ddmore.mdl

import com.google.inject.Inject
import org.junit.runner.RunWith
import org.eclipse.xtext.junit4.XtextRunner
import org.eclipse.xtext.junit4.InjectWith
import org.eclipse.xtext.junit4.util.ParseHelper
import org.eclipse.xtext.junit4.validation.ValidationTestHelper
import eu.ddmore.mdl.mdl.Mcl
import eu.ddmore.mdl.MdlInjectorProvider
import org.junit.Test
import eu.ddmore.mdl.mdl.MclObject
import eu.ddmore.mdl.mdl.DataObject
import eu.ddmore.mdl.mdl.DataInputBlock
import eu.ddmore.mdl.mdl.DataDerivedBlock
import eu.ddmore.mdl.mdl.DeclaredVariablesBlock
import eu.ddmore.mdl.mdl.SourceBlock
import eu.ddmore.mdl.mdl.ObjectBlock
import org.junit.Assert
import org.junit.Ignore

@RunWith(typeof(XtextRunner))
@InjectWith(typeof(MdlInjectorProvider))
class MclParserModelObj3Test {
	@Inject extension ParseHelper<Mcl>
	@Inject extension ValidationTestHelper
	
	val static CODE_SNIPPET = '''
warfarin_PK_2Compartments_mdl = mdlobj (idv=TIME) {
   COVARIATES{
      WT
      logtWT = logtWT(WT/70)
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
   }# end STRUCTURAL_PARAMETERS

   VARIABILITY_PARAMETERS{
      PPV_CL
      PPV_VC
      PPV_Q
      PPV_VP
      PPV_KA
      PPV_TLAG
      RUV_EPS1
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
   }# end RANDOM_VARIABLE_DEFINITION (level=ID)

   RANDOM_VARIABLE_DEFINITION (level=DV) {
      eps_RUV_EPS1 ~ Normal(mean=0, sd=RUV_EPS1)
   }# end RANDOM_VARIABLE_DEFINITION (level=DV)

   INDIVIDUAL_VARIABLES{
      CL : { type is linear, trans is log, pop = POP_CL, fixEff = [{coeff=POP_BETA_CL_WT, covariate=logtWT}] , ranEff = eta_PPV_CL }
      VC : { type is linear, trans is log, pop = POP_VC, fixEff = [{coeff=POP_BETA_V_WT, covariate=logtWT}] , ranEff = eta_PPV_VC }
      Q : { type is linear, trans is log, pop = POP_Q, fixEff = [{coeff=POP_BETA_CL_WT, covariate=logtWT}] , ranEff = eta_PPV_Q }
      VP : { type is linear, trans is log, pop = POP_VP, fixEff = [{coeff=POP_BETA_V_WT, covariate=logtWT}] , ranEff = eta_PPV_VP }
      KA : { type is linear, trans is log, pop = POP_KA, ranEff = eta_PPV_KA }
      TLAG : { type is linear, trans is log, pop = POP_TLAG, ranEff = eta_PPV_TLAG } 
      ALAG1=TLAG
      V2=VC
      V3=VP
      S2=VC

   }# end INDIVIDUAL_VARIABLES

   MODEL_PREDICTION{

   COMPARTMENT{
      ##  Parameters = CL,V2,Q,V3,KA,S2,ALAG1
      INPUT_KA:   {type is depot, cmt=1, to=CENTRAL, ka=KA, tlag=ALAG1}
      CENTRAL:    {type is compartment, cmt=2}
                  {type is elimination, cmt=2, from=CENTRAL, v=V2, cl=CL}
      PERIPHERAL: {type is distribution, cmt=3, from=CENTRAL, kin=Q/V2, kout=Q/V3}
   }# end COMPARTMENT

      F=CENTRAL/S2

      CC=F
   
   }# end MODEL_PREDICTION

   OBSERVATION{
         CC_obs : { type is combinedError1, additive = RUV_ADD, proportional = RUV_PROP, eps = eps_RUV_EPS1, prediction = CC } 
   }# end OBSERVATION
} # end of model object
		'''
	
	@Test
	def void testParsing(){
		CODE_SNIPPET.parse.assertNoErrors
		
	}

	@Ignore
	def void testExpectedAst(){
		val mcl = CODE_SNIPPET.parse
		
		val expectedVars = newLinkedList('warfarin_PK_ODE_dat', 'b', 'c')
		for(obj : mcl.objects){
			Assert::assertEquals(expectedVars.pop, obj.name)
		
			assertObject(obj)
		}
	}
	
	def dispatch assertObject(DataObject decl){
		for(blk : decl.blocks){
			assertBlock(blk);
		}
	}

	def dispatch assertObject(MclObject decl){
		Assert::fail("Don't go here!")
	}

	def dispatch assertBlock(DataInputBlock blk){
		val expectedVars = newLinkedList('ID', 'TIME', 'WT', 'AMT')
		Assert::assertEquals(expectedVars.size, blk.variables.size)
		for(stmt : blk.variables){
			Assert::assertEquals(expectedVars.pop, stmt.name)
		}
	}
	
	def dispatch assertBlock(DataDerivedBlock blk){
		val expectedVars = newLinkedList('DT')
		for(stmt : blk.variables){
			Assert::assertEquals(expectedVars.pop, stmt.name)
		}
	}
	
	def dispatch assertBlock(DeclaredVariablesBlock blk){
		val expectedVars = newLinkedList('GUT', 'Y')
		for(stmt : blk.variables){
			Assert::assertEquals(expectedVars.pop, stmt.name)
		}
	}
	
	def dispatch assertBlock(SourceBlock blk){
		val expectedVars = newLinkedList('file', 'inputformat', 'ignore')
		for(stmt : blk.statements){
			Assert::assertEquals(expectedVars.pop, stmt.argumentName)
		}
	}
	
	def dispatch assertBlock(ObjectBlock blk){
		Assert::fail("Don't go here!")
	}
	
}