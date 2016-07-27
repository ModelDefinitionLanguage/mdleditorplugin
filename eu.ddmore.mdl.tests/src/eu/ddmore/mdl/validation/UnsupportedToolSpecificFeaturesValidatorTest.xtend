package eu.ddmore.mdl.validation

import com.google.inject.Inject
import eu.ddmore.mdl.LibraryTestHelper
import eu.ddmore.mdl.MdlAndLibInjectorProvider
import eu.ddmore.mdl.mdl.Mcl
import eu.ddmore.mdl.mdl.MdlPackage
import org.eclipse.xtext.junit4.InjectWith
import org.eclipse.xtext.junit4.XtextRunner
import org.eclipse.xtext.junit4.validation.ValidationTestHelper
import org.junit.Test
import org.junit.runner.RunWith
import org.junit.Ignore

@RunWith(typeof(XtextRunner))
@InjectWith(typeof(MdlAndLibInjectorProvider))
class UnsupportedToolSpecificFeaturesValidatorTest {
	@Inject extension LibraryTestHelper<Mcl>
	@Inject extension ValidationTestHelper
	


	@Test
	def void testMonolixSupportedIdv(){
		val mcl = '''
		foo = mdlObj{
			IDV { T }

			VARIABILITY_LEVELS{
					ID : { level=1, type is parameter }
			}

			RANDOM_VARIABLE_DEFINITION(level = ID){
				eta_a ~ Normal(mean=0, sd=1)
			}
			
			STRUCTURAL_PARAMETERS{
				d
			}

			INDIVIDUAL_VARIABLES{
				a : { type is linear, trans is ln, pop=d, ranEff = [ eta_a ] }
			}
			
			OBSERVATION{
				z : {type is additiveError, prediction = a, additive=1 + 2, eps=eta_a }
			}
		}
	'''.parse
		mcl.assertNoIssues
//		mcl.assertWarning(MdlPackage::eINSTANCE.mclObject, UnsupportedFeaturesValidator::FEATURE_NOT_SUPPORTED,
//			"Objects of type 'desObj' are not currently supported for execution in R."
//		)
	}

	@Test
	def void testMonolixNotSupportedIdvGeneral(){
		val mcl = '''
		foo = mdlObj{
			IDV { T }

			VARIABILITY_LEVELS{
					ID : { level=1, type is parameter }
			}

			RANDOM_VARIABLE_DEFINITION(level = ID){
				eta_a ~ Normal(mean=0, sd=1)
			}
			
			STRUCTURAL_PARAMETERS{
				d
			}

			INDIVIDUAL_VARIABLES{
				a : { type is general, trans is ln, grp=d, ranEff = [ eta_a ] }
			}
			
			OBSERVATION{
				z : {type is additiveError, prediction = a, additive=1 + 2, eps=eta_a }
			}
		}
	'''.parse
		mcl.assertNoErrors
		mcl.assertWarning(MdlPackage::eINSTANCE.attributeList, MdlValidator::FEATURE_NOT_SUPPORTED_MONOLIX,
			"General individual parameter definition is not currently supported by MONOLIX."
		)
	}


	@Ignore("User defined not supported now")
	def void testMonolixNotSupportedIdvUserDefined(){
		val mcl = '''
		foo = mdlObj{
			IDV { T }

			VARIABILITY_LEVELS{
					ID : { level=1, type is parameter }
			}

			RANDOM_VARIABLE_DEFINITION(level = ID){
				eta_a ~ Normal(mean=0, sd=1)
			}
			
			STRUCTURAL_PARAMETERS{
				d
			}

			INDIVIDUAL_VARIABLES{
				a : { type is userDefined, value=exp(d * eta_a) } 
			}
		}
	'''.parse
		mcl.assertNoErrors
		mcl.assertWarning(MdlPackage::eINSTANCE.attributeList, MdlValidator::FEATURE_NOT_SUPPORTED_MONOLIX,
			"User-defined individual parameter definition is not currently supported by MONOLIX."
		)
	}

	@Test
	def void testMonolixNotSupportedIdvExplicit(){
		val mcl = '''
		foo = mdlObj{
			IDV { T }

			VARIABILITY_LEVELS{
					ID : { level=1, type is parameter }
			}

			RANDOM_VARIABLE_DEFINITION(level = ID){
				eta_a ~ Normal(mean=0, sd=1)
			}
			
			STRUCTURAL_PARAMETERS{
				d
			}

			INDIVIDUAL_VARIABLES{
				a = exp(d * eta_a) 
			}
			
			OBSERVATION{
				z : {type is additiveError, prediction = a, additive=1 + 2, eps=eta_a }
			}
		}
	'''.parse
		mcl.assertNoErrors
		mcl.assertWarning(MdlPackage::eINSTANCE.equationDefinition, MdlValidator::FEATURE_NOT_SUPPORTED_MONOLIX,
			"Explicit individual parameter definition is not currently supported by MONOLIX."
		)
	}


	@Test
	def void testMonolixSupportedIfOnlyParamAssignment(){
		val mcl = '''
		foo = mdlObj{
			IDV { T }

			VARIABILITY_LEVELS{
					ID : { level=1, type is parameter }
			}

			RANDOM_VARIABLE_DEFINITION(level = ID){
				eta_a ~ Normal(mean=0, sd=1)
			}
			
			STRUCTURAL_PARAMETERS{
				d
			}

			INDIVIDUAL_VARIABLES{
				a = d 
			}
			
			OBSERVATION{
				z : {type is additiveError, prediction = a, additive=1 + 2, eps=eta_a }
			}
		}
	'''.parse
		mcl.assertNoErrors
		mcl.assertWarning(MdlPackage::eINSTANCE.equationTypeDefinition, MdlValidator::FEATURE_NOT_SUPPORTED_MONOLIX,
			"Explicit individual parameter definition is not currently supported by MONOLIX."
		)
	}

	@Test
	def void testMonolixSupportedObs(){
		val mcl = '''
		foo = mdlObj{
			IDV { T }

			VARIABILITY_LEVELS{
				ID : { level=2, type is parameter }
				o : { level=1, type is observation }
			}

			RANDOM_VARIABLE_DEFINITION(level = ID){
				eta_a ~ Normal(mean=0, sd=1)
			}
			
			RANDOM_VARIABLE_DEFINITION(level = o){
				eps_a ~ Normal(mean=0, sd=1)
			}
			
			STRUCTURAL_PARAMETERS{
				d
			}

			INDIVIDUAL_VARIABLES{
				a : { type is linear, trans is ln, pop=d, ranEff = [ eta_a ] }
			}
			
			OBSERVATION{
				z : { type is combinedError2, additive=1, proportional=2, prediction=a, eps=eps_a }
			}
		}
	'''.parse
		mcl.assertNoIssues
//		mcl.assertWarning(MdlPackage::eINSTANCE.mclObject, UnsupportedFeaturesValidator::FEATURE_NOT_SUPPORTED,
//			"Objects of type 'desObj' are not currently supported for execution in R."
//		)
	}

	@Test
	def void testMonolixUnsupportedExplicitObs(){
		val mcl = '''
		foo = mdlObj{
			IDV { T }

			VARIABILITY_LEVELS{
					ID : { level=2, type is parameter }
					o : { level=1, type is observation }
			}

			RANDOM_VARIABLE_DEFINITION(level = ID){
				eta_a ~ Normal(mean=0, sd=1)
			}
			
			RANDOM_VARIABLE_DEFINITION(level = o){
				eps_a ~ Normal(mean=0, sd=1)
			}
			
			STRUCTURAL_PARAMETERS{
				d
			}

			INDIVIDUAL_VARIABLES{
				a : { type is linear, trans is ln, pop=d, ranEff = [ eta_a ] }
			}
			
			OBSERVATION{
				z : { type is userDefined, value=1 + 2 * a + eps_a, weight=2*2, prediction=1 }
			}
		}
	'''.parse
		mcl.assertNoErrors
		mcl.assertWarning(MdlPackage::eINSTANCE.attributeList, MdlValidator::FEATURE_NOT_SUPPORTED_MONOLIX,
			"Only the pre-defined error models are currently supported by MONOLIX."
		)
	}

	@Test
	def void testMonolixNoWarningUnsupportedExplicitObs(){
		val mcl = '''
		foo = mdlObj{
			IDV { T }

			VARIABILITY_LEVELS{
					ID : { level=2, type is parameter }
					o : { level=1, type is observation }
			}

			RANDOM_VARIABLE_DEFINITION(level = ID){
				eta_a ~ Normal(mean=0, sd=1)
			}
			
			RANDOM_VARIABLE_DEFINITION(level = o){
				eps_a ~ Normal(mean=0, sd=1)
			}
			
			STRUCTURAL_PARAMETERS{
				d
			}

			INDIVIDUAL_VARIABLES{
				a : { type is linear, trans is ln, pop=d, ranEff = [ eta_a ] }
			}
			
			OBSERVATION{
				z : {type is additiveError, prediction = a, additive=1 + 2, eps=eps_a }
			}
		}
	'''.parse
		mcl.assertNoIssues
//		mcl.assertWarning(MdlPackage::eINSTANCE.equationTypeDefinition, MdlValidator::FEATURE_NOT_SUPPORTED_MONOLIX,
//			"Only the pre-defined error models are currently supported by MONOLIX."
//		)
	}

	@Test
	def void testPharmMLUnsupportedDoseInterval(){
		val mcl = '''
		warfarin_PK_IVPO_dat = dataObj {
			DECLARED_VARIABLES{
				INPUT_KA::dosingTarget
				Y::observation
				FOO::dosingTarget
			}
		
		  DATA_INPUT_VARIABLES{
		      ID: {use is id}
		      TIME: {use is idv}
		      WT: {use is covariate}
		      AMT: {use is amt, define= {1 in CMT as INPUT_KA, 2 in CMT as FOO} }
		      RATE: {use is rate}
		      CMT: {use is cmt}
		      DV: {use is dv, variable=Y}
		      logtWT: {use is covariate}
		   }# end DATA_INPUT_VARIABLES
			DATA_DERIVED_VARIABLES{
				DI : { use is doseInterval, dosingVar=INPUT_KA, idvColumn = TIME }
			}
			
			
		   SOURCE{
		      srcFile : {file="warfarin_infusion_oral.csv",
		      			inputFormat is nonmemFormat}
		   }# end SOURCE
		} # end data object
	'''.parse
		mcl.assertNoErrors
		mcl.assertWarning(MdlPackage::eINSTANCE.attributeList, MdlValidator::FEATURE_NOT_SUPPORTED_PHARMML,
			"The doseInterval use type is not currently supported by PharmML."
		)
	}

}
