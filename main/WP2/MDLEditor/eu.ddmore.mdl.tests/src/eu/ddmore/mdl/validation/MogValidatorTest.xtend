package eu.ddmore.mdl.validation

import com.google.inject.Inject
import eu.ddmore.mdl.MdlInjectorProvider
import eu.ddmore.mdl.mdl.Mcl
import eu.ddmore.mdl.mdl.MdlPackage
import org.eclipse.xtext.junit4.InjectWith
import org.eclipse.xtext.junit4.XtextRunner
import org.eclipse.xtext.junit4.util.ParseHelper
import org.eclipse.xtext.junit4.validation.ValidationTestHelper
import org.junit.Before
import org.junit.Test
import org.junit.runner.RunWith

@RunWith(typeof(XtextRunner))
@InjectWith(typeof(MdlInjectorProvider))
class MogValidatorTest {
	@Inject extension ParseHelper<Mcl>
	@Inject extension ValidationTestHelper

	int errorCount
	
	@Before
	def void setUp(){
		errorCount = 0
	}
	
	@Test
	def void testValidCovariateMatching(){
		val mcl = '''
		warfarin_PK_ODE_dat = dataObj {
		
			DATA_INPUT_VARIABLES {
				SEX : { use is catCov withCategories{male when 0, female when 1} }
				WT : { use is covariate }
			} # end DATA_INPUT_VARIABLES
			SOURCE {
			    foo : {file = "warfarin_conc.csv", 
			       		inputFormat  is nonmemFormat, 
			    		ignore = "#" } 
			} # end SOURCE
		}		
		foo = mdlObj {
				VARIABILITY_LEVELS{
				}
		
				COVARIATES{
					SEX withCategories{male, female}
					WT
					logWT = ln(WT/70)
				}
		}
		mog = mogObj{
			OBJECTS{
				warfarin_PK_ODE_dat : { type is dataObj }
				foo : { type is mdlObj }
			}
		}
		
		'''.parse
	
		mcl.assertNoErrors	
	
	}
		
	@Test
	def void testValidCovariateMatchingWithMatchingDayaVar(){
		val mcl = '''
		warfarin_PK_ODE_dat = dataObj {
			DECLARED_VARIABLES{GUT Y}
		
			DATA_INPUT_VARIABLES {
				logWT : { use is covariate }
				WT : { use is covariate }
			} # end DATA_INPUT_VARIABLES
			SOURCE {
			    foo : {file = "warfarin_conc.csv", 
			       		inputFormat  is nonmemFormat, 
			    		ignore = "#" } 
			} # end SOURCE
		}
			
		foo = mdlObj {
				VARIABILITY_LEVELS{
				}
		
				COVARIATES{
					WT
					logWT = ln(WT/70)
				}
		}
		mog = mogObj{
			OBJECTS{
				warfarin_PK_ODE_dat : { type is dataObj }
				foo : { type is mdlObj }
			}
		}
		'''.parse
	
		mcl.assertNoErrors	
	}
		
	@Test
	def void testInValidCovariateMatchingDataMissing(){
		val mcl = '''
		warfarin_PK_ODE_dat = dataObj {
		
			DATA_INPUT_VARIABLES {
				WT : { use is covariate }
			} # end DATA_INPUT_VARIABLES
			SOURCE {
			    foo : {file = "warfarin_conc.csv", 
			       		inputFormat  is nonmemFormat, 
			    		ignore = "#" } 
			} # end SOURCE
		}
				
		foo = mdlObj {
				VARIABILITY_LEVELS{
				}
		
				COVARIATES{
					WT
					logWT
				}
		}
		mog = mogObj{
			OBJECTS{
				warfarin_PK_ODE_dat : { type is dataObj }
				foo : { type is mdlObj }
			}
		}
		'''.parse
	
		mcl.assertError(MdlPackage::eINSTANCE.mclObject,
			MdlValidator::MODEL_DATA_MISMATCH,
			"covariate logWT has no match in dataObj"
		)
	}
		
	@Test
	def void testValidCovariateNoMdlCovars(){
		val mcl = '''
		warfarin_PK_ODE_dat = dataObj {
		
			DATA_INPUT_VARIABLES {
				WT : { use is covariate }
			} # end DATA_INPUT_VARIABLES
			SOURCE {
			    foo : {file = "warfarin_conc.csv", 
			       		inputFormat  is nonmemFormat, 
			    		ignore = "#" } 
			} # end SOURCE
		}
				
		foo = mdlObj {
				VARIABILITY_LEVELS{
				}
		
				COVARIATES{
				}
		}
		mog = mogObj{
			OBJECTS{
				warfarin_PK_ODE_dat : { type is dataObj }
				foo : { type is mdlObj }
			}
		}
		'''.parse
	
		mcl.assertNoErrors	
	}
		
	
	@Test
	def void testValidCovariateNoMatchingDataCovars(){
		val mcl = '''
		warfarin_PK_ODE_dat = dataObj {
		
			DATA_INPUT_VARIABLES {
			} # end DATA_INPUT_VARIABLES
			SOURCE {
			    foo : {file = "warfarin_conc.csv", 
			       		inputFormat  is nonmemFormat, 
			    		ignore = "#" } 
			} # end SOURCE
		}		
		foo = mdlObj {
				VARIABILITY_LEVELS{
				}
		
				COVARIATES{
					logWT = ln(70)
				}
		}
		mog = mogObj{
			OBJECTS{
				warfarin_PK_ODE_dat : { type is dataObj }
				foo : { type is mdlObj }
			}
		}
		'''.parse
	
		mcl.assertNoErrors	
	}
		
	@Test
	def void testInValidCovariateOfWrongType(){
		val mcl = '''
		warfarin_PK_ODE_dat = dataObj {
			DATA_INPUT_VARIABLES {
				SEX : { use is catCov withCategories{male when 0, female when 1} }
				WT : { use is covariate }
			} # end DATA_INPUT_VARIABLES

			SOURCE {
			    foo : {file = "warfarin_conc.csv", 
			       		inputFormat  is nonmemFormat, 
			    		ignore = "#" } 
			} # end SOURCE
		}		
		foo = mdlObj {
				VARIABILITY_LEVELS{
				}
		
				COVARIATES{
					WT withCategories{male, female}
					SEX
				}
		}
		mog = mogObj{
			OBJECTS{
				warfarin_PK_ODE_dat : { type is dataObj }
				foo : { type is mdlObj }
			}
		}
		'''.parse
	
		mcl.assertError(MdlPackage::eINSTANCE.mclObject,
			MdlValidator::INCOMPATIBLE_TYPES,
			"covariate WT has an inconsistent type with its match in the dataObj"
		)
		mcl.assertError(MdlPackage::eINSTANCE.mclObject,
			MdlValidator::INCOMPATIBLE_TYPES,
			"covariate SEX has an inconsistent type with its match in the dataObj"
		)
	}
		
	@Test
	def void testInValidCovariateOfInconsistentCategories(){
		val mcl = '''
		warfarin_PK_ODE_dat = dataObj {
			DATA_INPUT_VARIABLES {
				SEX : { use is catCov withCategories{male when 0, female when 1} }
			} # end DATA_INPUT_VARIABLES

			SOURCE {
			    foo : {file = "warfarin_conc.csv", 
			       		inputFormat  is nonmemFormat, 
			    		ignore = "#" } 
			} # end SOURCE
		}
		
		foo = mdlObj {
				VARIABILITY_LEVELS{
				}
		
				COVARIATES{
					SEX withCategories{male, fem}
				}
		}
				mog = mogObj{
			OBJECTS{
				warfarin_PK_ODE_dat : { type is dataObj }
				foo : { type is mdlObj }
			}
		}
		'''.parse
	
		mcl.assertError(MdlPackage::eINSTANCE.mclObject,
			MdlValidator::INCOMPATIBLE_TYPES,
			"covariate SEX has an inconsistent type with its match in the dataObj"
		)
	}
		
	@Test
	def void testValidSingleObsMatching(){
		val mcl = '''
		warfarin_PK_ODE_dat = dataObj {
			DECLARED_VARIABLES{ Y }
		
			DATA_INPUT_VARIABLES {
				DV : { use is dv, variable = Y }
			} # end DATA_INPUT_VARIABLES
			SOURCE {
			    foo : {file = "warfarin_conc.csv", 
			       		inputFormat  is nonmemFormat, 
			    		ignore = "#" } 
			} # end SOURCE
		}		
		foo = mdlObj {
				VARIABILITY_LEVELS{
					DV : { type is observation, level = 1 }
				}
		
				MODEL_PREDICTION{
					F = 1
				}
		
				RANDOM_VARIABLE_DEFINITION(level is DV){
					EPS
				}
		
				OBSERVATION{
					Y = additiveError(additive=1, prediction=F, eps=EPS)
				}
		}
		mog = mogObj{
			OBJECTS{
				warfarin_PK_ODE_dat : { type is dataObj }
				foo : { type is mdlObj }
			}
		}
		'''.parse
	
		mcl.assertNoErrors	
	}

	@Test
	def void testValidCategoricalObsMatching(){
		val mcl = '''
		warfarin_PK_ODE_dat = dataObj {
			DECLARED_VARIABLES{ Y withCategories { c1, c0, c3, c2 } }
		
			DATA_INPUT_VARIABLES {
		      DV:{ use is dv, define= {Y.c0 when 0, Y.c2 when 1, Y.c3 when 2, Y.c1 when 3 } }
			} # end DATA_INPUT_VARIABLES
			SOURCE {
			    foo : {file = "warfarin_conc.csv", 
			       		inputFormat  is nonmemFormat, 
			    		ignore = "#" } 
			} # end SOURCE
		}		
		foo = mdlObj {
				VARIABILITY_LEVELS{
					DV : { type is observation, level = 1 }
				}
		
			   MODEL_PREDICTION{
				  Prob0 = 0.1
				  Prob1 = 0.2
				  Prob2 = 0.5
				  Prob3 = 1 - sum([Prob0, Prob1, Prob2]) 
			   }# end MODEL_PREDICTION
			
			   OBSERVATION{
				   Y : {type is categorical withCategories{ c0 when Prob0, c1 when Prob1, c2 when Prob2, c3 when Prob3} } 
			    }# end ESTIMATION
		}
		mog = mogObj{
			OBJECTS{
				warfarin_PK_ODE_dat : { type is dataObj }
				foo : { type is mdlObj }
			}
		}
		'''.parse
	
		mcl.assertNoErrors	
	}

	@Test
	def void testInvalidSingleObsMismatchingType(){
		val mcl = '''
		warfarin_PK_ODE_dat = dataObj {
			DECLARED_VARIABLES{ Y withCategories {true, false} }
		
			DATA_INPUT_VARIABLES {
				DV : { use is dv, variable = Y }
			} # end DATA_INPUT_VARIABLES
			SOURCE {
			    foo : {file = "warfarin_conc.csv", 
			       		inputFormat  is nonmemFormat, 
			    		ignore = "#" } 
			} # end SOURCE
		}		
		foo = mdlObj {
				VARIABILITY_LEVELS{
					DV : { type is observation, level = 1 }
				}
		
				MODEL_PREDICTION{
					F = 1
				}
		
				RANDOM_VARIABLE_DEFINITION(level is DV){
					EPS
				}
		
				OBSERVATION{
					Y : { type is count, distn = Poisson(lambda = F) }
				}
		}
		mog = mogObj{
			OBJECTS{
				warfarin_PK_ODE_dat : { type is dataObj }
				foo : { type is mdlObj }
			}
		}
		'''.parse
	
		mcl.assertError(MdlPackage::eINSTANCE.mclObject,
			MdlValidator::INCOMPATIBLE_TYPES,
			"observation Y has an inconsistent type with its match in the dataObj"
		)
	}

	@Test
	def void testInvalidSingleObsMismatchedName(){
		val mcl = '''
		warfarin_PK_ODE_dat = dataObj {
			DECLARED_VARIABLES{ Z }
		
			DATA_INPUT_VARIABLES {
				DV : { use is dv, variable = Z }
			} # end DATA_INPUT_VARIABLES
			SOURCE {
			    foo : {file = "warfarin_conc.csv", 
			       		inputFormat  is nonmemFormat, 
			    		ignore = "#" } 
			} # end SOURCE
		}		
		foo = mdlObj {
				VARIABILITY_LEVELS{
					DV : { type is observation, level = 1 }
				}
		
				MODEL_PREDICTION{
					F = 1
				}
		
				RANDOM_VARIABLE_DEFINITION(level is DV){
					EPS
				}
		
				OBSERVATION{
					Y = additiveError(additive=1, prediction=F, eps=EPS)
				}
		}
		mog = mogObj{
			OBJECTS{
				warfarin_PK_ODE_dat : { type is dataObj }
				foo : { type is mdlObj }
			}
		}
		'''.parse
		mcl.assertError(MdlPackage::eINSTANCE.mclObject,
			MdlValidator::MODEL_DATA_MISMATCH,
			"observation Y has no match in dataObj"
		)
	}

	@Test
	def void testInvalidSingleObsMismatchedNoData(){
		val mcl = '''
		warfarin_PK_ODE_dat = dataObj {
			DECLARED_VARIABLES{ Y }
		
			DATA_INPUT_VARIABLES {
				AMT : { use is amt, variable = Y }
			} # end DATA_INPUT_VARIABLES
			SOURCE {
			    foo : {file = "warfarin_conc.csv", 
			       		inputFormat  is nonmemFormat, 
			    		ignore = "#" } 
			} # end SOURCE
		}		
		foo = mdlObj {
				VARIABILITY_LEVELS{
					DV : { type is observation, level = 1 }
				}
		
				MODEL_PREDICTION{
					F = 1
				}
		
				RANDOM_VARIABLE_DEFINITION(level is DV){
					EPS
				}
		
				OBSERVATION{
					Y = additiveError(additive=1, prediction=F, eps=EPS)
				}
		}
		mog = mogObj{
			OBJECTS{
				warfarin_PK_ODE_dat : { type is dataObj }
				foo : { type is mdlObj }
			}
		}
		'''.parse
	
		mcl.assertError(MdlPackage::eINSTANCE.mclObject,
			MdlValidator::MODEL_DATA_MISMATCH,
			"observation Y has no match in dataObj"
		)
	}

	@Test
	def void testValidMultiObsContinuousMatching(){
		val mcl = '''
		warfarin_PK_ODE_dat = dataObj {
			DECLARED_VARIABLES{ Y; Z }
		
			DATA_INPUT_VARIABLES {
				DVID : { use is dvid }
				DV : { use is dv, define = { 1 in DVID as Y, 2 in DVID as Z } }
			} # end DATA_INPUT_VARIABLES
			SOURCE {
			    foo : {file = "warfarin_conc.csv", 
			       		inputFormat  is nonmemFormat, 
			    		ignore = "#" } 
			} # end SOURCE
		}		
		foo = mdlObj {
				VARIABILITY_LEVELS{
					DV : { type is observation, level = 1 }
				}
		
				MODEL_PREDICTION{
					F = 1
				}
		
				RANDOM_VARIABLE_DEFINITION(level is DV){
					EPS
				}
		
				OBSERVATION{
					Y = additiveError(additive=1, prediction=F, eps=EPS)
					Z = additiveError(additive=1, prediction=F, eps=EPS)
				}
		}
		mog = mogObj{
			OBJECTS{
				warfarin_PK_ODE_dat : { type is dataObj }
				foo : { type is mdlObj }
			}
		}
		'''.parse
	
		mcl.assertNoErrors	
	}

	@Test
	def void testInvalidMultiObsContinuousMisMatchedName(){
		val mcl = '''
		warfarin_PK_ODE_dat = dataObj {
			DECLARED_VARIABLES{ Y; X }
		
			DATA_INPUT_VARIABLES {
				DVID : { use is dvid }
				DV : { use is dv, define = { 1 in DVID as Y, 2 in DVID as X } }
			} # end DATA_INPUT_VARIABLES
			SOURCE {
			    foo : {file = "warfarin_conc.csv", 
			       		inputFormat  is nonmemFormat, 
			    		ignore = "#" } 
			} # end SOURCE
		}		
		foo = mdlObj {
				VARIABILITY_LEVELS{
					DV : { type is observation, level = 1 }
				}
		
				MODEL_PREDICTION{
					F = 1
				}
		
				RANDOM_VARIABLE_DEFINITION(level is DV){
					EPS
				}
		
				OBSERVATION{
					Y = additiveError(additive=1, prediction=F, eps=EPS)
					Z = additiveError(additive=1, prediction=F, eps=EPS)
				}
		}
		mog = mogObj{
			OBJECTS{
				warfarin_PK_ODE_dat : { type is dataObj }
				foo : { type is mdlObj }
			}
		}
		'''.parse
	
		mcl.assertError(MdlPackage::eINSTANCE.mclObject,
			MdlValidator::MODEL_DATA_MISMATCH,
			"observation Z has no match in dataObj"
		)
	}

	@Test
	def void testInvalidMultiObsContinuousMissing(){
		val mcl = '''
		warfarin_PK_ODE_dat = dataObj {
			DECLARED_VARIABLES{ Y; Z }
		
			DATA_INPUT_VARIABLES {
				DVID : { use is dvid }
				DV : { use is dv, define = { 1 in DVID as Y } }
			} # end DATA_INPUT_VARIABLES
			SOURCE {
			    foo : {file = "warfarin_conc.csv", 
			       		inputFormat  is nonmemFormat, 
			    		ignore = "#" } 
			} # end SOURCE
		}		
		foo = mdlObj {
				VARIABILITY_LEVELS{
					DV : { type is observation, level = 1 }
				}
		
				MODEL_PREDICTION{
					F = 1
				}
		
				RANDOM_VARIABLE_DEFINITION(level is DV){
					EPS
				}
		
				OBSERVATION{
					Y = additiveError(additive=1, prediction=F, eps=EPS)
					Z = additiveError(additive=1, prediction=F, eps=EPS)
				}
		}
		mog = mogObj{
			OBJECTS{
				warfarin_PK_ODE_dat : { type is dataObj }
				foo : { type is mdlObj }
			}
		}
		'''.parse
	
		mcl.assertError(MdlPackage::eINSTANCE.mclObject,
			MdlValidator::MODEL_DATA_MISMATCH,
			"observation Z has no match in dataObj"
		)
	}

	@Test
	def void testInvalidMultiObsContinuousMismatchType(){
		val mcl = '''
		warfarin_PK_ODE_dat = dataObj {
			DECLARED_VARIABLES{ Y; Z withCategories { a, b }}
		
			DATA_INPUT_VARIABLES {
				DVID : { use is dvid }
				DV : { use is dv, define = { 1 in DVID as Y, 2 in DVID as {Z.a when 0, Z.b when 1 } } }
			} # end DATA_INPUT_VARIABLES
			SOURCE {
			    foo : {file = "warfarin_conc.csv", 
			       		inputFormat  is nonmemFormat, 
			    		ignore = "#" } 
			} # end SOURCE
		}		
		foo = mdlObj {
				VARIABILITY_LEVELS{
					DV : { type is observation, level = 1 }
				}
		
				MODEL_PREDICTION{
					F = 1
				}
		
				RANDOM_VARIABLE_DEFINITION(level is DV){
					EPS
				}
		
				OBSERVATION{
					Y = additiveError(additive=1, prediction=F, eps=EPS)
					Z = additiveError(additive=1, prediction=F, eps=EPS)
				}
		}
		mog = mogObj{
			OBJECTS{
				warfarin_PK_ODE_dat : { type is dataObj }
				foo : { type is mdlObj }
			}
		}
		'''.parse
	
		mcl.assertError(MdlPackage::eINSTANCE.mclObject,
			MdlValidator::INCOMPATIBLE_TYPES,
			"observation Z has an inconsistent type with its match in the dataObj"
		)
	}

	@Test
	def void testValidDiscreteType(){
		val mcl = '''
		warfarin_PK_ODE_dat = dataObj {
			DECLARED_VARIABLES{ Z }
		
			DATA_INPUT_VARIABLES {
				DVID : { use is dvid }
				DV : { use is dv, variable = Z }
			} # end DATA_INPUT_VARIABLES
			SOURCE {
			    foo : {file = "warfarin_conc.csv", 
			       		inputFormat  is nonmemFormat, 
			    		ignore = "#" } 
			} # end SOURCE
		}		
		foo = mdlObj {
				VARIABILITY_LEVELS{
					DV : { type is observation, level = 1 }
				}
		
				MODEL_PREDICTION{
					F = 1
				}
		
				RANDOM_VARIABLE_DEFINITION(level is DV){
					EPS
				}
		
				OBSERVATION{
					Z : { type is count, distn = Poisson(lambda=0.0) }
				}
		}
		mog = mogObj{
			OBJECTS{
				warfarin_PK_ODE_dat : { type is dataObj }
				foo : { type is mdlObj }
			}
		}
		'''.parse
	
		mcl.assertNoErrors
	}

	@Test
	def void testValidVariabilityLevelsMatch(){
		val mcl = '''
		warfarin_PK_ODE_dat = dataObj {
			DECLARED_VARIABLES{ Y }
		
			DATA_INPUT_VARIABLES {
				ID : { use is id }
				DV : { use is dv, variable = Y }
				OCC : { use is varLevel }
			} # end DATA_INPUT_VARIABLES
			SOURCE {
			    foo : {file = "warfarin_conc.csv", 
			       		inputFormat  is nonmemFormat, 
			    		ignore = "#" } 
			} # end SOURCE
		}		
		foo = mdlObj {
				VARIABILITY_LEVELS{
					OCC : { type is parameter, level = 3 }
					ID : { type is parameter, level = 2 }
					DV : { type is observation, level = 1 }
				}
		
		}
		mog = mogObj{
			OBJECTS{
				warfarin_PK_ODE_dat : { type is dataObj }
				foo : { type is mdlObj }
			}
		}
		'''.parse
	
		mcl.assertNoErrors
	}

	@Test
	def void testInalidVariabilityLevelsMissingInData(){
		val mcl = '''
		warfarin_PK_ODE_dat = dataObj {
			DECLARED_VARIABLES{ Y }
		
			DATA_INPUT_VARIABLES {
				ID : { use is id }
			} # end DATA_INPUT_VARIABLES
			SOURCE {
			    foo : {file = "warfarin_conc.csv", 
			       		inputFormat  is nonmemFormat, 
			    		ignore = "#" } 
			} # end SOURCE
		}		
		foo = mdlObj {
				VARIABILITY_LEVELS{
					ID : { type is parameter, level = 2 }
					DV : { type is observation, level = 1 }
				}
		
		}
		mog = mogObj{
			OBJECTS{
				warfarin_PK_ODE_dat : { type is dataObj }
				foo : { type is mdlObj }
			}
		}
		'''.parse
	
		mcl.assertError(MdlPackage::eINSTANCE.mclObject,
			MdlValidator::MODEL_DATA_MISMATCH,
			"variability level DV has no match in dataObj"
		)
	}

	@Test
	def void testInvalidVariabilityLevelsWrongType(){
		val mcl = '''
		warfarin_PK_ODE_dat = dataObj {
			DECLARED_VARIABLES{ Y }
		
			DATA_INPUT_VARIABLES {
				ID : { use is varLevel }
			} # end DATA_INPUT_VARIABLES
			SOURCE {
			    foo : {file = "warfarin_conc.csv", 
			       		inputFormat  is nonmemFormat, 
			    		ignore = "#" } 
			} # end SOURCE
		}		
		foo = mdlObj {
				VARIABILITY_LEVELS{
					ID : { type is observation, level = 2 }
				}
		
		}
		mog = mogObj{
			OBJECTS{
				warfarin_PK_ODE_dat : { type is dataObj }
				foo : { type is mdlObj }
			}
		}
		'''.parse
	
		mcl.assertError(MdlPackage::eINSTANCE.mclObject,
			MdlValidator::INCOMPATIBLE_TYPES,
			"variability level ID has an inconsistent type with its match in the dataObj"
		)
	}

	@Test
	def void testInvalidVariabilityLevelsWrongType2(){
		val mcl = '''
		warfarin_PK_ODE_dat = dataObj {
			DECLARED_VARIABLES{ Y }
		
			DATA_INPUT_VARIABLES {
				ID : { use is dv, variable = Y }
			} # end DATA_INPUT_VARIABLES
			SOURCE {
			    foo : {file = "warfarin_conc.csv", 
			       		inputFormat  is nonmemFormat, 
			    		ignore = "#" } 
			} # end SOURCE
		}		
		foo = mdlObj {
				VARIABILITY_LEVELS{
					ID : { type is parameter, level = 2 }
				}
		
		}
		mog = mogObj{
			OBJECTS{
				warfarin_PK_ODE_dat : { type is dataObj }
				foo : { type is mdlObj }
			}
		}
		'''.parse
	
		mcl.assertError(MdlPackage::eINSTANCE.mclObject,
			MdlValidator::INCOMPATIBLE_TYPES,
			"variability level ID has an inconsistent type with its match in the dataObj"
		)
	}

	@Test
	def void testValidIdvMatchInMog(){
		val mcl = '''
		testData = dataObj {
			DATA_INPUT_VARIABLES {
				T : { use is idv }
			} # end DATA_INPUT_VARIABLES
			SOURCE {
			    foo : {file = "warfarin_conc.csv", 
			       		inputFormat  is nonmemFormat, 
			    		ignore = "#" } 
			} # end SOURCE
		}		
		testMdl = mdlObj {
				VARIABILITY_LEVELS{
				}
		
				IDV{ T }
		}
		mog = mogObj{
			OBJECTS{
				testData : { type is dataObj }
				testMdl : { type is mdlObj }
			}
		}
		'''.parse
	
		mcl.assertNoErrors
	}

	@Test
	def void testInvalidIdvMismatchInMog(){
		val mcl = '''
		testData = dataObj {
			DATA_INPUT_VARIABLES {
				TTI : { use is idv }
			} # end DATA_INPUT_VARIABLES
			SOURCE {
			    foo : {file = "warfarin_conc.csv", 
			       		inputFormat  is nonmemFormat, 
			    		ignore = "#" } 
			} # end SOURCE
		}		
		testMdl = mdlObj {
				VARIABILITY_LEVELS{
				}
		
				IDV{ T }
		}
		mog = mogObj{
			OBJECTS{
				testData : { type is dataObj }
				testMdl : { type is mdlObj }
			}
		}
		'''.parse
	
		mcl.assertError(MdlPackage::eINSTANCE.mclObject,
			MdlValidator::MODEL_DATA_MISMATCH,
			"individual variable T has no match in dataObj")
	}

	@Test
	def void testInvalidIdvMissingInDataMismatchInMog(){
		val mcl = '''
		testData = dataObj {
			DATA_INPUT_VARIABLES {
			} # end DATA_INPUT_VARIABLES
			SOURCE {
			    foo : {file = "warfarin_conc.csv", 
			       		inputFormat  is nonmemFormat, 
			    		ignore = "#" } 
			} # end SOURCE
		}		
		testMdl = mdlObj {
				VARIABILITY_LEVELS{
				}
		
				IDV{ T }
		}
		mog = mogObj{
			OBJECTS{
				testData : { type is dataObj }
				testMdl : { type is mdlObj }
			}
		}
		'''.parse
	
		mcl.assertError(MdlPackage::eINSTANCE.mclObject,
			MdlValidator::MODEL_DATA_MISMATCH,
			"individual variable T has no match in dataObj")
	}

	@Test
	def void testValidIdvMissingInModelMog(){
		val mcl = '''
		testData = dataObj {
			DATA_INPUT_VARIABLES {
				TTI : { use is idv }
			} # end DATA_INPUT_VARIABLES
			SOURCE {
			    foo : {file = "warfarin_conc.csv", 
			       		inputFormat  is nonmemFormat, 
			    		ignore = "#" } 
			} # end SOURCE
		}		
		testMdl = mdlObj {
				VARIABILITY_LEVELS{
				}
		
		}
		mog = mogObj{
			OBJECTS{
				testData : { type is dataObj }
				testMdl : { type is mdlObj }
			}
		}
		'''.parse
	
		mcl.assertNoErrors
	}

	@Test
	def void testValidSingleSimpleDosingMatchMog(){
		val mcl = '''
		testData = dataObj {
			DECLARED_VARIABLES { D }
			DATA_INPUT_VARIABLES {
				AMT : { use is amt, variable=D }
			} # end DATA_INPUT_VARIABLES
			SOURCE {
			    foo : {file = "warfarin_conc.csv", 
			       		inputFormat  is nonmemFormat, 
			    		ignore = "#" } 
			} # end SOURCE
		}		
		testMdl = mdlObj {
				VARIABILITY_LEVELS{
				}
				
				MODEL_PREDICTION{
					Y
					A
					D
				}
		
		}
		mog = mogObj{
			OBJECTS{
				testData : { type is dataObj }
				testMdl : { type is mdlObj }
			}
		}
		'''.parse
	
		mcl.assertNoErrors
	}

	@Test
	def void testValidDosingNoMdlMatchMog(){
		val mcl = '''
		testData = dataObj {
			DECLARED_VARIABLES { Y }
			DATA_INPUT_VARIABLES {
			} # end DATA_INPUT_VARIABLES
			SOURCE {
			    foo : {file = "warfarin_conc.csv", 
			       		inputFormat  is nonmemFormat, 
			    		ignore = "#" } 
			} # end SOURCE
		}		
		testMdl = mdlObj {
				VARIABILITY_LEVELS{
				}
				
				MODEL_PREDICTION{
					Y
					A
					D
				}
		
		}
		mog = mogObj{
			OBJECTS{
				testData : { type is dataObj }
				testMdl : { type is mdlObj }
			}
		}
		'''.parse
	
		mcl.assertNoErrors
	}

	@Test
	def void testValidSingleOdeDosingMatchMog(){
		val mcl = '''
		testData = dataObj {
			DECLARED_VARIABLES { D }
			DATA_INPUT_VARIABLES {
				AMT : { use is amt, variable=D }
			} # end DATA_INPUT_VARIABLES
			SOURCE {
			    foo : {file = "warfarin_conc.csv", 
			       		inputFormat  is nonmemFormat, 
			    		ignore = "#" } 
			} # end SOURCE
		}		
		testMdl = mdlObj {
				VARIABILITY_LEVELS{
				}
				
				MODEL_PREDICTION{
					DEQ{
						D : { deriv=1 }
						X : { deriv=1 }
					}
					Y
				}
		
		}
		mog = mogObj{
			OBJECTS{
				testData : { type is dataObj }
				testMdl : { type is mdlObj }
			}
		}
		'''.parse
	
		mcl.assertNoErrors
	}

	@Test
	def void testValidSingleCompartmentDosingMatchMog(){
		val mcl = '''
		testData = dataObj {
			DECLARED_VARIABLES { D }
			DATA_INPUT_VARIABLES {
				AMT : { use is amt, variable=D }
			} # end DATA_INPUT_VARIABLES
			SOURCE {
			    foo : {file = "warfarin_conc.csv", 
			       		inputFormat  is nonmemFormat, 
			    		ignore = "#" } 
			} # end SOURCE
		}		
		testMdl = mdlObj {
				VARIABILITY_LEVELS{
				}
				
				MODEL_PREDICTION{
					COMPARTMENT{
				      D:   {type is depot, modelCmt=1, to=CENTRAL, ka=1, tlag=1}
				      CENTRAL:    {type is compartment, modelCmt=2}
				             ::   {type is elimination, modelCmt=2, from=CENTRAL, v=1, cl=1}
					}
				}
		
		}
		mog = mogObj{
			OBJECTS{
				testData : { type is dataObj }
				testMdl : { type is mdlObj }
			}
		}
		'''.parse
	
		mcl.assertNoErrors
	}
	@Test
	def void testInvalidSingleSimpleDosingNoMdlVarMatchMog(){
		val mcl = '''
		testData = dataObj {
			DECLARED_VARIABLES { D }
			DATA_INPUT_VARIABLES {
				AMT : { use is amt, variable=D }
			} # end DATA_INPUT_VARIABLES
			SOURCE {
			    foo : {file = "warfarin_conc.csv", 
			       		inputFormat  is nonmemFormat, 
			    		ignore = "#" } 
			} # end SOURCE
		}		
		testMdl = mdlObj {
				VARIABILITY_LEVELS{
				}
				
				MODEL_PREDICTION{
					Y
					A
				}
		
		}
		mog = mogObj{
			OBJECTS{
				testData : { type is dataObj }
				testMdl : { type is mdlObj }
			}
		}
		'''.parse
	
		mcl.assertError(MdlPackage::eINSTANCE.mclObject,
			MdlValidator::MODEL_DATA_MISMATCH,
			"dosing variable D has no match in mdlObj")
	}

	@Test
	def void testValidSingleOdeDosingMissingModelMatchMog(){
		val mcl = '''
		testData = dataObj {
			DECLARED_VARIABLES { D }
			DATA_INPUT_VARIABLES {
				AMT : { use is amt, variable=D }
			} # end DATA_INPUT_VARIABLES
			SOURCE {
			    foo : {file = "warfarin_conc.csv", 
			       		inputFormat  is nonmemFormat, 
			    		ignore = "#" } 
			} # end SOURCE
		}		
		testMdl = mdlObj {
				VARIABILITY_LEVELS{
				}
				
				MODEL_PREDICTION{
					DEQ{
						X : { type is deriv, value=1 }
					}
					Y
				}
		
		}
		mog = mogObj{
			OBJECTS{
				testData : { type is dataObj }
				testMdl : { type is mdlObj }
			}
		}
		'''.parse
	
		mcl.assertError(MdlPackage::eINSTANCE.mclObject,
			MdlValidator::MODEL_DATA_MISMATCH,
			"dosing variable D has no match in mdlObj")
	}

	@Test
	def void testValidSingleCompartmentDosingMissingModelMatchMog(){
		val mcl = '''
		testData = dataObj {
			DECLARED_VARIABLES { D }
			DATA_INPUT_VARIABLES {
				AMT : { use is amt, variable=D }
			} # end DATA_INPUT_VARIABLES
			SOURCE {
			    foo : {file = "warfarin_conc.csv", 
			       		inputFormat  is nonmemFormat, 
			    		ignore = "#" } 
			} # end SOURCE
		}		
		testMdl = mdlObj {
				VARIABILITY_LEVELS{
				}
				
				MODEL_PREDICTION{
					COMPARTMENT{
				      D:   {type is depot, modelCmt=1, to=CENTRAL, ka=1, tlag=1}
				      CENTRAL:    {type is compartment, modelCmt=2}
				             ::   {type is elimination, modelCmt=2, from=CENTRAL, v=1, cl=1}
			         }
				}
		
		}
		mog = mogObj{
			OBJECTS{
				testData : { type is dataObj }
				testMdl : { type is mdlObj }
			}
		}
		'''.parse
	
		mcl.assertNoErrors
	}

	@Test
	def void testValidMultiDosingMatchMog(){
		val mcl = '''
		testData = dataObj {
			DECLARED_VARIABLES { D; CENTRAL; Y }
			DATA_INPUT_VARIABLES {
				CMT : { use is cmt }
				AMT : { use is amt, define = { 1 in CMT as D, 2 in CMT as CENTRAL, 3 in CMT as Y } }
			} # end DATA_INPUT_VARIABLES
			SOURCE {
			    foo : {file = "warfarin_conc.csv", 
			       		inputFormat  is nonmemFormat, 
			    		ignore = "#" } 
			} # end SOURCE
		}		
		testMdl = mdlObj {
				VARIABILITY_LEVELS{
				}
				
				MODEL_PREDICTION{
					COMPARTMENT{
						D:   {type is depot, modelCmt=1, to=CENTRAL, ka=1, tlag=1}
				     	CENTRAL:    {type is compartment, modelCmt=2}
			             ::   {type is elimination, modelCmt=2, from=CENTRAL, v=1, cl=1}
						}
					Y
				}
		
		}
		mog = mogObj{
			OBJECTS{
				testData : { type is dataObj }
				testMdl : { type is mdlObj }
			}
		}
		'''.parse
	
		mcl.assertNoErrors
	}

	@Test
	def void testValidMultiDosingMisMatchMog(){
		val mcl = '''
		testData = dataObj {
			DECLARED_VARIABLES { D, CENTRAL, Y }
			DATA_INPUT_VARIABLES {
				CMT : { use is cmt }
				AMT : { use is amt, define = { 1 in CMT as D, 2 in CMT as CENTRAL, 3 in CMT as Y } }
			} # end DATA_INPUT_VARIABLES
			SOURCE {
			    foo : {file = "warfarin_conc.csv", 
			       		inputFormat  is nonmemFormat, 
			    		ignore = "#" } 
			} # end SOURCE
		}		
		testMdl = mdlObj {
				VARIABILITY_LEVELS{
				}
				
				MODEL_PREDICTION{
					DEQ{
						D:   {type is depot, modelCmt=1, to=CENTRAL, ka=1, tlag=1}
				     	CENTRAL:    {type is compartment, modelCmt=2}
			             ::   {type is elimination, modelCmt=2, from=CENTRAL, v=1, cl=1}
						}
				}
		
		}
		mog = mogObj{
			OBJECTS{
				testData : { type is dataObj }
				testMdl : { type is mdlObj }
			}
		}
		'''.parse
	
		mcl.assertError(MdlPackage::eINSTANCE.mclObject,
			MdlValidator::MODEL_DATA_MISMATCH,
			"dosing variable Y has no match in mdlObj")
	}
	
	@Test
	def void testInvalidSingleDosingIncompatibleTypesMog(){
		val mcl = '''
		testData = dataObj {
			DECLARED_VARIABLES { D }
			DATA_INPUT_VARIABLES {
				AMT : { use is amt, variable=D }
			} # end DATA_INPUT_VARIABLES
			SOURCE {
			    foo : {file = "warfarin_conc.csv", 
			       		inputFormat  is nonmemFormat, 
			    		ignore = "#" } 
			} # end SOURCE
		}		
		testMdl = mdlObj {
				VARIABILITY_LEVELS{
				}
				
				MODEL_PREDICTION{
			      D []
				}
		
		}
		mog = mogObj{
			OBJECTS{
				testData : { type is dataObj }
				testMdl : { type is mdlObj }
			}
		}
		'''.parse
	
		mcl.assertError(MdlPackage::eINSTANCE.mclObject,
			MdlValidator::INCOMPATIBLE_TYPES,
			"dosing variable D has an inconsistent type with its match in the mdlObj")
	}

	
}