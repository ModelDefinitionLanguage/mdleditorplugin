package eu.ddmore.mdl.validation

import com.google.inject.Inject
import eu.ddmore.mdl.LibraryTestHelper
import eu.ddmore.mdl.MdlAndLibInjectorProvider
import eu.ddmore.mdl.mdl.Mcl
import eu.ddmore.mdl.mdl.MdlPackage
import org.eclipse.xtext.junit4.InjectWith
import org.eclipse.xtext.junit4.XtextRunner
import org.eclipse.xtext.junit4.validation.ValidationTestHelper
import org.junit.Before
import org.junit.Test
import org.junit.runner.RunWith

@RunWith(typeof(XtextRunner))
@InjectWith(typeof(MdlAndLibInjectorProvider))
class MogValidatorTest {
	@Inject extension LibraryTestHelper<Mcl>
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
			DECLARED_VARIABLES{ Y::observation }
		
			DATA_INPUT_VARIABLES {
				T : { use is idv }
				SEX : { use is catCov withCategories{male when 0, female when 1} }
				WT : { use is covariate }
				DV : { use is dv, variable=Y }
			} # end DATA_INPUT_VARIABLES
			SOURCE {
			    foo : {file = "warfarin_conc.csv", 
			       		inputFormat  is nonmemFormat } 
			} # end SOURCE
		}		
		foo = mdlObj {
				IDV{T}
				VARIABILITY_LEVELS{
				}
		
				COVARIATES{
					SEX withCategories{male, female}
					WT
					logWT = ln(WT/70)
				}
			
			OBSERVATION{
				F = 1
				Y : { type is userDefined, prediction=F, value=F, weight=0 } 
			}
		}
		p1 = parObj{
			
		}
		
		t1 = taskObj{
			ESTIMATE{
				set algo is saem
			}
		}
		
		mog = mogObj{
			OBJECTS{
				warfarin_PK_ODE_dat : { type is dataObj }
				foo : { type is mdlObj }
				p1 : { type is parObj }
				t1 : { type is taskObj }
			}
		}
		
		'''.parse
	
		mcl.assertNoErrors	
	
	}
		
	@Test
	def void testValidCovariateMatchingWithMatchingDayaVar(){
		val mcl = '''
		warfarin_PK_ODE_dat = dataObj {
			DECLARED_VARIABLES{ Y::observation }
		
			DATA_INPUT_VARIABLES {
				T : { use is idv }
				logWT : { use is covariate }
				WT : { use is covariate }
				DV : { use is dv, variable=Y }
			} # end DATA_INPUT_VARIABLES
			SOURCE {
			    foo : {file = "warfarin_conc.csv", 
			       		inputFormat  is nonmemFormat } 
			} # end SOURCE
		}
			
		foo = mdlObj {
				IDV{T}
				VARIABILITY_LEVELS{
				}
		
				COVARIATES{
					WT
					logWT = ln(WT/70)
				}
			
			OBSERVATION{
				F = 1
				Y : { type is userDefined, prediction=F, value=F, weight=0 } 
			}
		}
		
		p1 = parObj{
			
		}
		
		t1 = taskObj{
			ESTIMATE{
				set algo is saem
			}
		}
		
		mog = mogObj{
			OBJECTS{
				warfarin_PK_ODE_dat : { type is dataObj }
				foo : { type is mdlObj }
				p1 : { type is parObj }
				t1 : { type is taskObj }
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
			       		inputFormat  is nonmemFormat } 
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
		p1 = parObj{
			
		}
		
		t1 = taskObj{
			ESTIMATE{
				set algo is saem
			}
		}
		
		mog = mogObj{
			OBJECTS{
				warfarin_PK_ODE_dat : { type is dataObj }
				foo : { type is mdlObj }
				p1 : { type is parObj }
				t1 : { type is taskObj }
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
			DECLARED_VARIABLES{ Y::observation }
			DATA_INPUT_VARIABLES {
				T : { use is idv }
				WT : { use is covariate }
				DV : { use is dv, variable=Y }
			} # end DATA_INPUT_VARIABLES
			SOURCE {
			    foo : {file = "warfarin_conc.csv", 
			       		inputFormat  is nonmemFormat } 
			} # end SOURCE
		}
				
		foo = mdlObj {
				IDV{T}
				VARIABILITY_LEVELS{
				}
		
				COVARIATES{
				}
			
			OBSERVATION{
				F = 1
				Y : { type is userDefined, prediction=F, value=F, weight=0 } 
			}
		}
		p1 = parObj{
			
		}
		
		t1 = taskObj{
			ESTIMATE{
				set algo is saem
			}
		}
		
		mog = mogObj{
			OBJECTS{
				warfarin_PK_ODE_dat : { type is dataObj }
				foo : { type is mdlObj }
				p1 : { type is parObj }
				t1 : { type is taskObj }
			}
		}
		'''.parse
	
		mcl.assertNoErrors	
	}
		
	
	@Test
	def void testValidCovariateNoMatchingDataCovars(){
		val mcl = '''
		warfarin_PK_ODE_dat = dataObj {
			DECLARED_VARIABLES{ Y::observation }
			DATA_INPUT_VARIABLES {
				T : { use is idv }
				SEX : { use is catCov withCategories{male when 0, female when 1} }
				WT : { use is covariate }
				DV : { use is dv, variable=Y }
			} # end DATA_INPUT_VARIABLES
			SOURCE {
			    foo : {file = "warfarin_conc.csv", 
			       		inputFormat  is nonmemFormat } 
			} # end SOURCE
		}		
		foo = mdlObj {
				IDV{T}
				VARIABILITY_LEVELS{
				}
		
				COVARIATES{
					logWT = 70
				}
			
			OBSERVATION{
				F = 1
				Y : { type is userDefined, prediction=F, value=F, weight=0 } 
			}
		}
		p1 = parObj{
			
		}
		
		t1 = taskObj{
			ESTIMATE{
				set algo is saem
			}
		}
		
		mog = mogObj{
			OBJECTS{
				warfarin_PK_ODE_dat : { type is dataObj }
				foo : { type is mdlObj }
				p1 : { type is parObj }
				t1 : { type is taskObj }
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
			       		inputFormat  is nonmemFormat } 
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
		p1 = parObj{
			
		}
		
		t1 = taskObj{
			ESTIMATE{
				set algo is saem
			}
		}
		
		mog = mogObj{
			OBJECTS{
				warfarin_PK_ODE_dat : { type is dataObj }
				foo : { type is mdlObj }
				p1 : { type is parObj }
				t1 : { type is taskObj }
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
			       		inputFormat  is nonmemFormat } 
			} # end SOURCE
		}
		
		foo = mdlObj {
				VARIABILITY_LEVELS{
				}
		
				COVARIATES{
					SEX withCategories{male, fem}
				}
		}
		p1 = parObj{
			
		}
		
		t1 = taskObj{
			ESTIMATE{
				set algo is saem
			}
		}
		
		mog = mogObj{
			OBJECTS{
				warfarin_PK_ODE_dat : { type is dataObj }
				foo : { type is mdlObj }
				p1 : { type is parObj }
				t1 : { type is taskObj }
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
			DECLARED_VARIABLES{ Y::observation }
		
			DATA_INPUT_VARIABLES {
				T : { use is idv }
				DV : { use is dv, variable = Y }
			} # end DATA_INPUT_VARIABLES
			SOURCE {
			    foo : {file = "warfarin_conc.csv", 
			       		inputFormat  is nonmemFormat } 
			} # end SOURCE
		}		
		foo = mdlObj {
				IDV{T}
				VARIABILITY_LEVELS{
					DV : { type is observation, level = 1 }
				}
		
				MODEL_PREDICTION{
					F = 1
				}
		
				RANDOM_VARIABLE_DEFINITION(level is DV){
					EPS ~ Normal(mean=0, sd=1)
				}
		
				OBSERVATION{
					Y : { type is additiveError, additive=1, prediction=F, eps=EPS }
				}
		}
		p1 = parObj{
			
		}
		
		t1 = taskObj{
			ESTIMATE{
				set algo is saem
			}
		}
		
		mog = mogObj{
			OBJECTS{
				warfarin_PK_ODE_dat : { type is dataObj }
				foo : { type is mdlObj }
				p1 : { type is parObj }
				t1 : { type is taskObj }
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
				T : { use is idv }
				DV:{ use is dv, define= {Y.c0 when 0, Y.c2 when 1, Y.c3 when 2, Y.c1 when 3 } }
			} # end DATA_INPUT_VARIABLES
			SOURCE {
			    foo : {file = "warfarin_conc.csv", 
			       		inputFormat  is nonmemFormat } 
			} # end SOURCE
		}		
		foo = mdlObj {
				IDV{T}
				
				VARIABILITY_LEVELS{
					DV : { type is observation, level = 1 }
				}
		
			   MODEL_PREDICTION{
				  Prob0 = 0.1
				  Prob1 = 0.2
				  Prob2 = 0.5
				  Prob3 = 1 - sum([Prob0, Prob1, Prob2]) 
			   }# end MODEL_PREDICTION
			
			   RANDOM_VARIABLE_DEFINITION(level=DV){
			   		Y withCategories{ c0, c1, c2, c3} ~ CategoricalNonordered1(categoryProb=[Prob0, Prob1, Prob2, Prob3])
			   }
			
			   OBSERVATION{
				   :: { type is categorical, variable=Y } 
			    }# end ESTIMATION
		}
		p1 = parObj{
			
		}
		
		t1 = taskObj{
			ESTIMATE{
				set algo is saem
			}
		}
		
		mog = mogObj{
			OBJECTS{
				warfarin_PK_ODE_dat : { type is dataObj }
				foo : { type is mdlObj }
				p1 : { type is parObj }
				t1 : { type is taskObj }
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
			       		inputFormat  is nonmemFormat } 
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
					Y : { type is count, distn = Poisson1(rate = F) }
				}
		}
		p1 = parObj{
			
		}
		
		t1 = taskObj{
			ESTIMATE{
				set algo is saem
			}
		}
		
		mog = mogObj{
			OBJECTS{
				warfarin_PK_ODE_dat : { type is dataObj }
				foo : { type is mdlObj }
				p1 : { type is parObj }
				t1 : { type is taskObj }
			}
		}
		'''.parse
	
		mcl.assertError(MdlPackage::eINSTANCE.mclObject,
			MdlValidator::INCOMPATIBLE_TYPES,
			"observation Y has an inconsistent type with its match in obj: 'warfarin_PK_ODE_dat'."
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
			       		inputFormat  is nonmemFormat } 
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
					Y : { type is additiveError, additive=1, prediction=F, eps=EPS }
				}
		}
		p1 = parObj{
			
		}
		
		t1 = taskObj{
			ESTIMATE{
				set algo is saem
			}
		}
		
		mog = mogObj{
			OBJECTS{
				warfarin_PK_ODE_dat : { type is dataObj }
				foo : { type is mdlObj }
				p1 : { type is parObj }
				t1 : { type is taskObj }
			}
		}
		'''.parse
		mcl.assertError(MdlPackage::eINSTANCE.mclObject,
			MdlValidator::MODEL_DATA_MISMATCH,
			"observation Y has no match in obj: 'warfarin_PK_ODE_dat'."
		)
	}

	@Test
	def void testInvalidSingleObsMismatchedNoData(){
		val mcl = '''
		warfarin_PK_ODE_dat = dataObj {
			DECLARED_VARIABLES{ Y::dosingTarget Z::observation }
		
			DATA_INPUT_VARIABLES {
				AMT : { use is amt, variable = Y }
				DV : { use is dv, variable = Z }
			} # end DATA_INPUT_VARIABLES
			SOURCE {
			    foo : {file = "warfarin_conc.csv", 
			       		inputFormat  is nonmemFormat } 
			} # end SOURCE
		}		
		foo = mdlObj {
				IDV{ T }
			
				VARIABILITY_LEVELS{
					DV : { type is observation, level = 1 }
				}
		
				MODEL_PREDICTION{
					F = 1
				}
		
				RANDOM_VARIABLE_DEFINITION(level is DV){
					EPS ~ Normal1(mean=0, stdev=1)
				}
		
				OBSERVATION{
					Y : { type is additiveError, additive=1, prediction=F, eps=EPS }
				}
		}
		p1 = parObj{
			
		}
		
		t1 = taskObj{
			ESTIMATE{
				set algo is saem
			}
		}
		
		mog = mogObj{
			OBJECTS{
				warfarin_PK_ODE_dat : { type is dataObj }
				foo : { type is mdlObj }
				p1 : { type is parObj }
				t1 : { type is taskObj }
			}
		}
		'''.parse
	
		mcl.assertError(MdlPackage::eINSTANCE.mclObject,
			MdlValidator::INCOMPATIBLE_TYPES,
			"observation Y has an inconsistent type with its match in obj: 'warfarin_PK_ODE_dat'."
		)
	}

	@Test
	def void testValidMultiObsContinuousMatching(){
		val mcl = '''
		warfarin_PK_ODE_dat = dataObj {
			DECLARED_VARIABLES{ Y::observation; Z::observation }
		
			DATA_INPUT_VARIABLES {
				T : { use is idv }
				DVID : { use is dvid }
				DV : { use is dv, define = { 1 in DVID as Y, 2 in DVID as Z } }
			} # end DATA_INPUT_VARIABLES
			SOURCE {
			    foo : {file = "warfarin_conc.csv", 
			       		inputFormat  is nonmemFormat } 
			} # end SOURCE
		}		
		foo = mdlObj {
				IDV{T}
				VARIABILITY_LEVELS{
					DV : { type is observation, level = 1 }
				}
		
				MODEL_PREDICTION{
					F = 1
				}
		
				RANDOM_VARIABLE_DEFINITION(level is DV){
					EPS ~ Normal(mean=0, sd=1)
				}
		
				OBSERVATION{
					Y : { type is additiveError, additive=1, prediction=F, eps=EPS }
					Z : { type is additiveError, additive=1, prediction=F, eps=EPS }
				}
		}
		p1 = parObj{
			
		}
		
		t1 = taskObj{
			ESTIMATE{
				set algo is saem
			}
		}
		
		mog = mogObj{
			OBJECTS{
				warfarin_PK_ODE_dat : { type is dataObj }
				foo : { type is mdlObj }
				p1 : { type is parObj }
				t1 : { type is taskObj }
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
			       		inputFormat  is nonmemFormat } 
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
					Y : { type is additiveError, additive=1, prediction=F, eps=EPS }
					Z : { type is additiveError, additive=1, prediction=F, eps=EPS }
				}
		}
		p1 = parObj{
			
		}
		
		t1 = taskObj{
			ESTIMATE{
				set algo is saem
			}
		}
		
		mog = mogObj{
			OBJECTS{
				warfarin_PK_ODE_dat : { type is dataObj }
				foo : { type is mdlObj }
				p1 : { type is parObj }
				t1 : { type is taskObj }
			}
		}
		'''.parse
	
		mcl.assertError(MdlPackage::eINSTANCE.mclObject,
			MdlValidator::MODEL_DATA_MISMATCH,
			"observation Z has no match in obj: 'warfarin_PK_ODE_dat'."
		)
	}

	@Test
	def void testInvalidMultiObsContinuousMissing(){
		val mcl = '''
		warfarin_PK_ODE_dat = dataObj {
			DECLARED_VARIABLES{ Y }
		
			DATA_INPUT_VARIABLES {
				DVID : { use is dvid }
				DV : { use is dv, define = { 1 in DVID as Y } }
			} # end DATA_INPUT_VARIABLES
			SOURCE {
			    foo : {file = "warfarin_conc.csv", 
			       		inputFormat  is nonmemFormat } 
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
					Y : { type is additiveError, additive=1, prediction=F, eps=EPS }
					Z : { type is additiveError, additive=1, prediction=F, eps=EPS }
				}
		}
		p1 = parObj{
			
		}
		
		t1 = taskObj{
			ESTIMATE{
				set algo is saem
			}
		}
		
		mog = mogObj{
			OBJECTS{
				warfarin_PK_ODE_dat : { type is dataObj }
				foo : { type is mdlObj }
				p1 : { type is parObj }
				t1 : { type is taskObj }
			}
		}
		'''.parse
	
		mcl.assertError(MdlPackage::eINSTANCE.mclObject,
			MdlValidator::MODEL_DATA_MISMATCH,
			"observation Z has no match in obj: 'warfarin_PK_ODE_dat'."
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
			       		inputFormat  is nonmemFormat } 
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
					EPS ~ Normal(mean = 0, sd = 1)
				}
		
				OBSERVATION{
					Y : { type is additiveError, additive=1, prediction=F, eps=EPS }
					Z : { type is additiveError, additive=1, prediction=F, eps=EPS }
				}
		}
		p1 = parObj{
			
		}
		
		t1 = taskObj{
			ESTIMATE{
				set algo is saem
			}
		}
		
		mog = mogObj{
			OBJECTS{
				warfarin_PK_ODE_dat : { type is dataObj }
				foo : { type is mdlObj }
				p1 : { type is parObj }
				t1 : { type is taskObj }
			}
		}
		'''.parse
	
		mcl.assertError(MdlPackage::eINSTANCE.mclObject,
			MdlValidator::INCOMPATIBLE_TYPES,
			"observation Z has an inconsistent type with its match in obj: 'warfarin_PK_ODE_dat'."
		)
	}

	@Test
	def void testValidCountType(){
		val mcl = '''
		warfarin_PK_ODE_dat = dataObj {
			DECLARED_VARIABLES{ Z::observation }
		
			DATA_INPUT_VARIABLES {
				T : { use is idv }
				DVID : { use is dvid }
				DV : { use is dv, variable = Z }
			} # end DATA_INPUT_VARIABLES
			SOURCE {
			    foo : {file = "warfarin_conc.csv", 
			       		inputFormat  is nonmemFormat } 
			} # end SOURCE
		}		
		foo = mdlObj {
				IDV{T}
				
				VARIABILITY_LEVELS{
					DV : { type is observation, level = 1 }
				}
		
				MODEL_PREDICTION{
					F = 1
				}
		
				RANDOM_VARIABLE_DEFINITION(level is DV){
					EPS ~ Normal(mean=0, sd=1)
					Z ~ Poisson1(rate=0.0)
				}
		
				OBSERVATION{
					:: { type is count, variable=Z }
				}
		}
		p1 = parObj{
			
		}
		
		t1 = taskObj{
			ESTIMATE{
				set algo is saem
			}
		}
		
		mog = mogObj{
			OBJECTS{
				warfarin_PK_ODE_dat : { type is dataObj }
				foo : { type is mdlObj }
				p1 : { type is parObj }
				t1 : { type is taskObj }
			}
		}
		'''.parse
	
		mcl.assertNoErrors
	}

	@Test
	def void testValidDiscreteType(){
		val mcl = '''
		warfarin_PK_ODE_dat = dataObj {
			DECLARED_VARIABLES{ Z withCategories {male, female} }
		
			DATA_INPUT_VARIABLES {
				T : { use is idv }
				DVID : { use is dvid }
				DV : { use is dv, define = { Z.male when 1, Z.female when 2 } }
			} # end DATA_INPUT_VARIABLES
			SOURCE {
			    foo : {file = "warfarin_conc.csv", 
			       		inputFormat  is nonmemFormat } 
			} # end SOURCE
		}		
		foo = mdlObj {
				IDV{T}
				
				VARIABILITY_LEVELS{
					DV : { type is observation, level = 1 }
				}
		
				MODEL_PREDICTION{
					F = 1
				}
		
				RANDOM_VARIABLE_DEFINITION(level is DV){
					EPS ~ Normal(mean=0, sd=1)
					Z withCategories {male, female} ~ Bernoulli1(probability=0.5)
				}
		
				OBSERVATION{
					:: { type is discrete, variable=Z }
				}
		}
		p1 = parObj{
			
		}
		
		t1 = taskObj{
			ESTIMATE{
				set algo is saem
			}
		}
		
		mog = mogObj{
			OBJECTS{
				warfarin_PK_ODE_dat : { type is dataObj }
				foo : { type is mdlObj }
				p1 : { type is parObj }
				t1 : { type is taskObj }
			}
		}
		'''.parse
	
		mcl.assertNoErrors
	}

	@Test
	def void testInvalidDiscreteInconsistentCategoriesType(){
		val mcl = '''
		warfarin_PK_ODE_dat = dataObj {
			DECLARED_VARIABLES{ Z withCategories {male, female, none} }
		
			DATA_INPUT_VARIABLES {
				T : { use is idv }
				DVID : { use is dvid }
				DV : { use is dv, define = { Z.male when 1, Z.female when 2, Z.none when 3 } }
			} # end DATA_INPUT_VARIABLES
			SOURCE {
			    foo : {file = "warfarin_conc.csv", 
			       		inputFormat  is nonmemFormat } 
			} # end SOURCE
		}		
		foo = mdlObj {
				IDV{T}
				
				VARIABILITY_LEVELS{
					DV : { type is observation, level = 1 }
				}
		
				MODEL_PREDICTION{
					F = 1
				}
		
				RANDOM_VARIABLE_DEFINITION(level is DV){
					EPS ~ Normal(mean=0, sd=1)
					Z withCategories {male, female} ~ Bernoulli1(probability=0.5)
				}
		
				OBSERVATION{
					:: { type is discrete, variable=Z }
				}
		}
		p1 = parObj{
			
		}
		
		t1 = taskObj{
			ESTIMATE{
				set algo is saem
			}
		}
		
		mog = mogObj{
			OBJECTS{
				warfarin_PK_ODE_dat : { type is dataObj }
				foo : { type is mdlObj }
				p1 : { type is parObj }
				t1 : { type is taskObj }
			}
		}
		'''.parse
	
		mcl.assertError(MdlPackage::eINSTANCE.mclObject,
			MdlValidator::INCOMPATIBLE_TYPES,
			"observation Z has an inconsistent type with its match in obj: 'warfarin_PK_ODE_dat'.")
	}

	@Test
	def void testInvalidDiscreteType(){
		val mcl = '''
		warfarin_PK_ODE_dat = dataObj {
			DECLARED_VARIABLES{ Z::observation }
		
			DATA_INPUT_VARIABLES {
				T : { use is idv }
				DVID : { use is dvid }
				DV : { use is dv, variable = Z }
			} # end DATA_INPUT_VARIABLES
			SOURCE {
			    foo : {file = "warfarin_conc.csv", 
			       		inputFormat  is nonmemFormat } 
			} # end SOURCE
		}		
		foo = mdlObj {
				IDV{T}
				
				VARIABILITY_LEVELS{
					DV : { type is observation, level = 1 }
				}
		
				MODEL_PREDICTION{
					F = 1
				}
		
				RANDOM_VARIABLE_DEFINITION(level is DV){
					EPS ~ Normal(mean=0, sd=1)
					Z withCategories {male, female} ~ Bernoulli1(probability=0.5)
				}
		
				OBSERVATION{
					:: { type is discrete, variable=Z }
				}
		}
		p1 = parObj{
			
		}
		
		t1 = taskObj{
			ESTIMATE{
				set algo is saem
			}
		}
		
		mog = mogObj{
			OBJECTS{
				warfarin_PK_ODE_dat : { type is dataObj }
				foo : { type is mdlObj }
				p1 : { type is parObj }
				t1 : { type is taskObj }
			}
		}
		'''.parse
	
		mcl.assertError(MdlPackage::eINSTANCE.mclObject,
			MdlValidator::INCOMPATIBLE_TYPES,
			"observation Z has an inconsistent type with its match in obj: 'warfarin_PK_ODE_dat'.")
	}

	@Test
	def void testValidContinuousType(){
		val mcl = '''
		warfarin_PK_ODE_dat = dataObj {
			DECLARED_VARIABLES{ Z::observation }
		
			DATA_INPUT_VARIABLES {
				T : { use is idv }
				DVID : { use is dvid }
				DV : { use is dv, variable = Z }
			} # end DATA_INPUT_VARIABLES
			SOURCE {
			    foo : {file = "warfarin_conc.csv", 
			       		inputFormat  is nonmemFormat } 
			} # end SOURCE
		}		
		foo = mdlObj {
				IDV{T}
				
				VARIABILITY_LEVELS{
					DV : { type is observation, level = 1 }
				}
		
				MODEL_PREDICTION{
					F = 1
				}
		
				RANDOM_VARIABLE_DEFINITION(level is DV){
					Z ~ Normal(mean=0, sd=1)
				}
		
				OBSERVATION{
					:: { type is continuous, variable=Z }
				}
		}
		p1 = parObj{
			
		}
		
		t1 = taskObj{
			ESTIMATE{
				set algo is saem
			}
		}
		
		mog = mogObj{
			OBJECTS{
				warfarin_PK_ODE_dat : { type is dataObj }
				foo : { type is mdlObj }
				p1 : { type is parObj }
				t1 : { type is taskObj }
			}
		}
		'''.parse
	
		mcl.assertNoErrors
	}

	@Test
	def void testInvalidContinuousType(){
		val mcl = '''
		warfarin_PK_ODE_dat = dataObj {
			DECLARED_VARIABLES{ Z::observation }
		
			DATA_INPUT_VARIABLES {
				T : { use is idv }
				DVID : { use is dvid }
				DV : { use is dv, variable = Z }
			} # end DATA_INPUT_VARIABLES
			SOURCE {
			    foo : {file = "warfarin_conc.csv", 
			       		inputFormat  is nonmemFormat } 
			} # end SOURCE
		}		
		foo = mdlObj {
				IDV{T}
				
				VARIABILITY_LEVELS{
					DV : { type is observation, level = 1 }
				}
		
				MODEL_PREDICTION{
					F = 1
				}
		
				RANDOM_VARIABLE_DEFINITION(level is DV){
					EPS ~ Normal(mean=0, sd=1)
				}
		
				OBSERVATION{
					:: { type is continuous, variable=EPS }
				}
		}
		p1 = parObj{
			
		}
		
		t1 = taskObj{
			ESTIMATE{
				set algo is saem
			}
		}
		
		mog = mogObj{
			OBJECTS{
				warfarin_PK_ODE_dat : { type is dataObj }
				foo : { type is mdlObj }
				p1 : { type is parObj }
				t1 : { type is taskObj }
			}
		}
		'''.parse
	
		mcl.assertError(MdlPackage::eINSTANCE.mclObject,
			MdlValidator::MODEL_DATA_MISMATCH,
			"observation EPS has no match in obj: 'warfarin_PK_ODE_dat'."
		)
	}

	@Test
	def void testValidVariabilityLevelsDesignMatch(){
		val mcl = '''
		d1 = designObj{
		  DECLARED_VARIABLES{
		  	INPUT_KA :: dosingTarget
		  	CP_obs :: observation
		  }
		  INTERVENTION{
		 	admin1 : {type is bolus, input=INPUT_KA, amount=100, doseTime=[0] }
		  }
		  SAMPLING{
			winPK : {type is simple, outcome=CP_obs, sampleTime = [0.0001, 24, 36, 48, 72, 96, 120] }
		  }
		  STUDY_DESIGN{
			arm1 : {
				armSize = 3,
				interventionSequence=[{
					admin=admin1,
					start=0
				}],
				samplingSequence=[{
					sample=winPK,
					start=0
				}]
			}
		  }
		}
		foo = mdlObj {
				IDV{T}
				
				VARIABILITY_LEVELS(reference=ID){
					ID : { type is parameter, level = 2 }
					DV : { type is observation, level = 1 }
				}
			
			OBSERVATION{
				F = 1
				CP_obs : { type is userDefined, prediction=F, value=F, weight=0 } 
			}
		
		}
		p1 = parObj{
			
		}
		
		t1 = taskObj{
			ESTIMATE{
				set algo is saem
			}
		}
		
		mog = mogObj{
			OBJECTS{
				d1 : { type is designObj }
				foo : { type is mdlObj }
				p1 : { type is parObj }
				t1 : { type is taskObj }
			}
		}
		'''.parse
	
		mcl.assertNoErrors
	}

	@Test
	def void testValidVariabilityLevelsDesignMatchNoId(){
		val mcl = '''
		d1 = designObj{
		  DECLARED_VARIABLES{
		  	INPUT_KA :: dosingTarget
		  	CP_obs :: observation
		  }
		  INTERVENTION{
		 	admin1 : {type is bolus, input=INPUT_KA, amount=100, doseTime=[0] }
		  }
		  SAMPLING{
			winPK : {type is simple, outcome=CP_obs, sampleTime = [0.0001, 24, 36, 48, 72, 96, 120] }
		  }
		  STUDY_DESIGN{
			arm1 : {
				armSize = 3,
				interventionSequence=[{
					admin=admin1,
					start=0
				}],
				samplingSequence=[{
					sample=winPK,
					start=0
				}]
			}
		  }
		}
		foo = mdlObj {
				IDV{T}
				
				VARIABILITY_LEVELS{
					DV : { type is observation, level = 1 }
				}
		
			
			OBSERVATION{
				F = 1
				CP_obs : { type is userDefined, prediction=F, value=F, weight=0 } 
			}
		}
		p1 = parObj{
			
		}
		
		t1 = taskObj{
			ESTIMATE{
				set algo is saem
			}
		}
		
		mog = mogObj{
			OBJECTS{
				d1 : { type is designObj }
				foo : { type is mdlObj }
				p1 : { type is parObj }
				t1 : { type is taskObj }
			}
		}
		'''.parse
	
		mcl.assertNoErrors
	}

	@Test
	def void testValidVariabilityLevelsDesignMatchUnusedLevel(){
		val mcl = '''
		d1 = designObj{
		  DECLARED_VARIABLES{
		  	INPUT_KA :: dosingTarget
		  	CP_obs :: observation
		  }
		  INTERVENTION{
		 	admin1 : {type is bolus, input=INPUT_KA, amount=100, doseTime=[0] }
		  }
		  SAMPLING{
			winPK : {type is simple, outcome=CP_obs, sampleTime = [0.0001, 24, 36, 48, 72, 96, 120] }
		  }
		  STUDY_DESIGN{
			arm1 : {
				armSize = 3,
				interventionSequence=[{
					admin=admin1,
					start=0
				}],
				samplingSequence=[{
					sample=winPK,
					start=0
				}]
			}
		  }
		}
		foo = mdlObj {
				IDV{T}
				
				VARIABILITY_LEVELS{
					ID : { type is parameter, level = 2 }
					DV : { type is observation, level = 1 }
				}
		
		}
		p1 = parObj{
			
		}
		
		t1 = taskObj{
			ESTIMATE{
				set algo is saem
			}
		}
		
		mog = mogObj{
			OBJECTS{
				d1 : { type is designObj }
				foo : { type is mdlObj }
				p1 : { type is parObj }
				t1 : { type is taskObj }
			}
		}
		'''.parse
	
		mcl.assertError(MdlPackage::eINSTANCE.mclObject,
			MdlValidator::MODEL_DATA_MISMATCH,
			"variability level ID has no match in designObj"
		)
	}

	@Test
	def void testValidVariabilityLevelsMatch(){
		val mcl = '''
		warfarin_PK_ODE_dat = dataObj {
			DECLARED_VARIABLES{ Y::observation }
		
			DATA_INPUT_VARIABLES {
				ID : { use is id }
				T : { use is idv }
				DV : { use is dv, variable = Y }
				OCC : { use is varLevel }
			} # end DATA_INPUT_VARIABLES
			SOURCE {
			    foo : {file = "warfarin_conc.csv", 
			       		inputFormat  is nonmemFormat } 
			} # end SOURCE
		}		
		foo = mdlObj {
				IDV{T}
				
				VARIABILITY_LEVELS{
					OCC : { type is parameter, level = 3 }
					ID : { type is parameter, level = 2 }
					DV : { type is observation, level = 1 }
				}
			
			OBSERVATION{
				F = 1
				Y : { type is userDefined, prediction=F, value=F, weight=0 } 
			}
		
		}
		p1 = parObj{
			
		}
		
		t1 = taskObj{
			ESTIMATE{
				set algo is saem
			}
		}
		
		mog = mogObj{
			OBJECTS{
				warfarin_PK_ODE_dat : { type is dataObj }
				foo : { type is mdlObj }
				p1 : { type is parObj }
				t1 : { type is taskObj }
			}
		}
		'''.parse
	
		mcl.assertNoErrors
	}

	@Test
	def void testValidVariabilityLevelsIdDiffNames(){
		val mcl = '''
		warfarin_PK_ODE_dat = dataObj {
			DECLARED_VARIABLES{ Y::observation }
			DATA_INPUT_VARIABLES {
				TC : { use is idv }
				ID2 : { use is id }
				DV : { use is dv, variable=Y }
			} # end DATA_INPUT_VARIABLES
			SOURCE {
			    foo : {file = "warfarin_conc.csv", 
			       		inputFormat  is nonmemFormat } 
			} # end SOURCE
		}		
		foo = mdlObj {
				IDV{T}
				VARIABILITY_LEVELS(reference=ID){
					ID : { type is parameter, level = 2 }
				}
			
			OBSERVATION{
				F = 1
				Y : { type is userDefined, prediction=F, value=F, weight=0 } 
			}
		
		}
		p1 = parObj{
			
		}
		
		t1 = taskObj{
			ESTIMATE{
				set algo is saem
			}
		}
		
		mog = mogObj{
			OBJECTS{
				warfarin_PK_ODE_dat : { type is dataObj }
				foo : { type is mdlObj }
				p1 : { type is parObj }
				t1 : { type is taskObj }
			}
		}
		'''.parse
	
		mcl.assertNoErrors
	}

	@Test
	def void testValidVariabilityLevelsMissingId(){
		val mcl = '''
		warfarin_PK_ODE_dat = dataObj {
			DECLARED_VARIABLES{ Y }
		
			DATA_INPUT_VARIABLES {
				ID2 : { use is idv }
			} # end DATA_INPUT_VARIABLES
			SOURCE {
			    foo : {file = "warfarin_conc.csv", 
			       		inputFormat  is nonmemFormat } 
			} # end SOURCE
		}		
		foo = mdlObj {
				VARIABILITY_LEVELS(reference=ID){
					ID : { type is parameter, level = 2 }
					DV : { type is observation, level = 1 }
				}
		
		}
		p1 = parObj{
			
		}
		
		t1 = taskObj{
			ESTIMATE{
				set algo is saem
			}
		}
		
		mog = mogObj{
			OBJECTS{
				warfarin_PK_ODE_dat : { type is dataObj }
				foo : { type is mdlObj }
				p1 : { type is parObj }
				t1 : { type is taskObj }
			}
		}
		'''.parse
	
		mcl.assertError(MdlPackage::eINSTANCE.mclObject,
			MdlValidator::MODEL_DATA_MISMATCH,
			"An 'id' variability level is required in dataObj: 'warfarin_PK_ODE_dat'"
		)
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
			       		inputFormat  is nonmemFormat } 
			} # end SOURCE
		}		
		foo = mdlObj {
				VARIABILITY_LEVELS{
					ID : { type is parameter, level = 2 }
					DV : { type is observation, level = 1 }
				}
		
		}
		p1 = parObj{
			
		}
		
		t1 = taskObj{
			ESTIMATE{
				set algo is saem
			}
		}
		
		mog = mogObj{
			OBJECTS{
				warfarin_PK_ODE_dat : { type is dataObj }
				foo : { type is mdlObj }
				p1 : { type is parObj }
				t1 : { type is taskObj }
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
			       		inputFormat  is nonmemFormat } 
			} # end SOURCE
		}		
		foo = mdlObj {
				VARIABILITY_LEVELS{
					ID : { type is observation, level = 2 }
				}
		
		}
		p1 = parObj{
			
		}
		
		t1 = taskObj{
			ESTIMATE{
				set algo is saem
			}
		}
		
		mog = mogObj{
			OBJECTS{
				warfarin_PK_ODE_dat : { type is dataObj }
				foo : { type is mdlObj }
				p1 : { type is parObj }
				t1 : { type is taskObj }
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
			       		inputFormat  is nonmemFormat } 
			} # end SOURCE
		}		
		foo = mdlObj {
				VARIABILITY_LEVELS{
					ID : { type is parameter, level = 2 }
				}
		
		}
		p1 = parObj{
			
		}
		
		t1 = taskObj{
			ESTIMATE{
				set algo is saem
			}
		}
		
		mog = mogObj{
			OBJECTS{
				warfarin_PK_ODE_dat : { type is dataObj }
				foo : { type is mdlObj }
				p1 : { type is parObj }
				t1 : { type is taskObj }
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
			DECLARED_VARIABLES{ Y::observation }
			
			DATA_INPUT_VARIABLES {
				T : { use is idv }
						DV : { use is dv, variable=Y }
			} # end DATA_INPUT_VARIABLES
			SOURCE {
			    foo : {file = "warfarin_conc.csv", 
			       		inputFormat  is nonmemFormat } 
			} # end SOURCE
		}		
		testMdl = mdlObj {
				VARIABILITY_LEVELS{
				}
		
				IDV{ T }
			
			OBSERVATION{
				F = 1
				Y : { type is userDefined, prediction=F, value=F, weight=0 } 
			}
		}

		p1 = parObj{
			
		}
		
		t1 = taskObj{
			ESTIMATE{
				set algo is saem
			}
		}
		
		mog = mogObj{
			OBJECTS{
				testData : { type is dataObj }
				testMdl : { type is mdlObj }
				p1 : { type is parObj }
				t1 : { type is taskObj }
			}
		}
		'''.parse
	
		mcl.assertNoErrors
	}

	@Test
	def void testValidIdvMismatchInMog(){
		val mcl = '''
		testData = dataObj {
			DECLARED_VARIABLES{ Y::observation }
			DATA_INPUT_VARIABLES {
				TTI : { use is idv }
				DV : { use is dv, variable=Y }
			} # end DATA_INPUT_VARIABLES
			SOURCE {
			    foo : {file = "warfarin_conc.csv", 
			       		inputFormat  is nonmemFormat } 
			} # end SOURCE
		}		
		testMdl = mdlObj {
				VARIABILITY_LEVELS{
				}
		
				IDV{ T }
			OBSERVATION{
				F = 1
				Y : { type is userDefined, prediction=F, value=F, weight=0 } 
			}
		}

		p1 = parObj{
			
		}
		
		t1 = taskObj{
			ESTIMATE{
				set algo is saem
			}
		}
		
		mog = mogObj{
			OBJECTS{
				testData : { type is dataObj }
				testMdl : { type is mdlObj }
				p1 : { type is parObj }
				t1 : { type is taskObj }
			}
		}
		'''.parse
	
		mcl.assertNoErrors
//		mcl.assertError(MdlPackage::eINSTANCE.mclObject,
//			MdlValidator::MODEL_DATA_MISMATCH,
//			"individual variable T has no match in dataObj")
	}

	@Test
	def void testInvalidIdvMissingInDataMismatchInMog(){
		val mcl = '''
		testData = dataObj {
			DATA_INPUT_VARIABLES {
			} # end DATA_INPUT_VARIABLES
			SOURCE {
			    foo : {file = "warfarin_conc.csv", 
			       		inputFormat  is nonmemFormat } 
			} # end SOURCE
		}		
		testMdl = mdlObj {
				VARIABILITY_LEVELS{
				}
		
				IDV{ T }
		}
		p1 = parObj{
			
		}
		
		t1 = taskObj{
			ESTIMATE{
				set algo is saem
			}
		}
		
		mog = mogObj{
			OBJECTS{
				testData : { type is dataObj }
				testMdl : { type is mdlObj }
				p1 : { type is parObj }
				t1 : { type is taskObj }
			}
		}
		'''.parse
	
		mcl.assertError(MdlPackage::eINSTANCE.mclObject,
			MdlValidator::MODEL_DATA_MISMATCH,
			"independent variable T has no match in dataObj")
	}

	@Test
	def void testValidIdvMissingInModelMog(){
		val mcl = '''
		testData = dataObj {
			DECLARED_VARIABLES { Y::observation }
			DATA_INPUT_VARIABLES {
				TTI : { use is idv }
				DV : { use is dv, variable=Y }
			} # end DATA_INPUT_VARIABLES
			SOURCE {
			    foo : {file = "warfarin_conc.csv", 
			       		inputFormat  is nonmemFormat } 
			} # end SOURCE
		}		
		testMdl = mdlObj {
				IDV{T}
				VARIABILITY_LEVELS{
				}
			OBSERVATION{
				F = 1
				Y : { type is userDefined, prediction=F, value=F, weight=0 } 
			}
		}
		p1 = parObj{
			
		}
		
		t1 = taskObj{
			ESTIMATE{
				set algo is saem
			}
		}
		
		mog = mogObj{
			OBJECTS{
				testData : { type is dataObj }
				testMdl : { type is mdlObj }
				p1 : { type is parObj }
				t1 : { type is taskObj }
			}
		}
		'''.parse
	
		mcl.assertNoErrors
	}

	@Test
	def void testValidSingleSimpleDosingMatchMog(){
		val mcl = '''
		testData = dataObj {
			DECLARED_VARIABLES { D::dosingTarget Y::observation }
			DATA_INPUT_VARIABLES {
				T : { use is idv }
				AMT : { use is amt, variable=D }
				DV : { use is dv, variable=Y }
			} # end DATA_INPUT_VARIABLES
			SOURCE {
			    foo : {file = "warfarin_conc.csv", 
			       		inputFormat  is nonmemFormat } 
			} # end SOURCE
		}		
		testMdl = mdlObj {
				IDV{T}
				
				VARIABILITY_LEVELS{
				}
				
				MODEL_PREDICTION{
					Y1 = A + D
					A = 1
					D::dosingVar
				}
			
			OBSERVATION{
				Y : { type is userDefined, prediction=Y1, value=Y1, weight=0 } 
			}
		
		}
		p1 = parObj{
			
		}
		
		t1 = taskObj{
			ESTIMATE{
				set algo is saem
			}
		}
		
		mog = mogObj{
			OBJECTS{
				testData : { type is dataObj }
				testMdl : { type is mdlObj }
				p1 : { type is parObj }
				t1 : { type is taskObj }
			}
		}
		'''.parse
	
		mcl.assertNoErrors
	}

	@Test
	def void testValidDosingNoMdlMatchMog(){
		val mcl = '''
		testData = dataObj {
			DECLARED_VARIABLES { Y1::observation }
			DATA_INPUT_VARIABLES {
				T : { use is idv }
				foo1 : { use is ignore }
				DV : { use is dv, variable=Y1 }
			} # end DATA_INPUT_VARIABLES
			SOURCE {
			    foo : {file = "warfarin_conc.csv", 
			       		inputFormat  is nonmemFormat } 
			} # end SOURCE
		}		
		testMdl = mdlObj {
				IDV{T}
				VARIABILITY_LEVELS{
				}
				
				MODEL_PREDICTION{
					Y = 1
					A = 1
					D = 1
				}
		
			
			OBSERVATION{
				F = 1
				Y1 : { type is userDefined, prediction=F, value=F, weight=0 } 
			}
		}
		p1 = parObj{
			
		}
		
		t1 = taskObj{
			ESTIMATE{
				set algo is saem
			}
		}
		
		mog = mogObj{
			OBJECTS{
				testData : { type is dataObj }
				testMdl : { type is mdlObj }
				p1 : { type is parObj }
				t1 : { type is taskObj }
			}
		}
		'''.parse
	
		mcl.assertNoErrors
	}

	@Test
	def void testValidSingleOdeDosingMatchMog(){
		val mcl = '''
		testData = dataObj {
			DECLARED_VARIABLES { D::dosingTarget Y::observation }
			DATA_INPUT_VARIABLES {
				T : { use is idv }
				AMT : { use is amt, variable=D }
				DV : { use is dv, variable=Y }
			} # end DATA_INPUT_VARIABLES
			SOURCE {
			    foo : {file = "warfarin_conc.csv", 
			       		inputFormat  is nonmemFormat } 
			} # end SOURCE
		}		
		testMdl = mdlObj {
				IDV{T}
				VARIABILITY_LEVELS{
				}
				
				MODEL_PREDICTION{
					DEQ{
						D : { deriv=1 }
						X : { deriv=1 }
					}
				}
			OBSERVATION{
				F = 1
				Y : { type is userDefined, prediction=F, value=F, weight=0 } 
			}
		}
		p1 = parObj{
			
		}
		
		t1 = taskObj{
			ESTIMATE{
				set algo is saem
			}
		}
		
		mog = mogObj{
			OBJECTS{
				testData : { type is dataObj }
				testMdl : { type is mdlObj }
				p1 : { type is parObj }
				t1 : { type is taskObj }
			}
		}
		'''.parse
	
		mcl.assertNoErrors
	}

	@Test
	def void testValidSingleCompartmentDosingMatchMog(){
		val mcl = '''
		testData = dataObj {
			DECLARED_VARIABLES { D::dosingTarget Y::observation }
			DATA_INPUT_VARIABLES {
				T : { use is idv }
				AMT : { use is amt, variable=D }
				DV : { use is dv, variable=Y }
			} # end DATA_INPUT_VARIABLES
			SOURCE {
			    foo : {file = "warfarin_conc.csv", 
			       		inputFormat  is nonmemFormat } 
			} # end SOURCE
		}		
		testMdl = mdlObj {
				IDV{T}

				VARIABILITY_LEVELS{
				}
				
				MODEL_PREDICTION{
					COMPARTMENT{
				      D:   {type is depot, modelCmt=1, to=CENTRAL, ka=1, tlag=1}
				      CENTRAL:    {type is compartment, modelCmt=2}
				             ::   {type is elimination, modelCmt=2, from=CENTRAL, v=1, cl=1}
					}
				}
			
			OBSERVATION{
				F = 1
				Y : { type is userDefined, prediction=F, value=F, weight=0 } 
			}
		
		}
		p1 = parObj{
			
		}
		
		t1 = taskObj{
			ESTIMATE{
				set algo is saem
			}
		}
		
		mog = mogObj{
			OBJECTS{
				testData : { type is dataObj }
				testMdl : { type is mdlObj }
				p1 : { type is parObj }
				t1 : { type is taskObj }
			}
		}
		'''.parse
	
		mcl.assertNoErrors
	}
	@Test
	def void testInvalidSingleSimpleDosingNoMdlVarMatchMog(){
		val mcl = '''
		testData = dataObj {
			DECLARED_VARIABLES { D::dosingTarget }
			DATA_INPUT_VARIABLES {
				AMT : { use is amt, variable=D }
			} # end DATA_INPUT_VARIABLES
			SOURCE {
			    foo : {file = "warfarin_conc.csv", 
			       		inputFormat  is nonmemFormat } 
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

		p1 = parObj{
			
		}
		
		t1 = taskObj{
			ESTIMATE{
				set algo is saem
			}
		}
		
		mog = mogObj{
			OBJECTS{
				testData : { type is dataObj }
				testMdl : { type is mdlObj }
				p1 : { type is parObj }
				t1 : { type is taskObj }
			}
		}
		'''.parse
	
		mcl.assertError(MdlPackage::eINSTANCE.mclObject,
			MdlValidator::MODEL_DATA_MISMATCH,
			"dosing variable D has no match in mdlObj")
	}

	@Test
	def void testInvalidSingleOdeDosingMissingModelMatchMog(){
		val mcl = '''
		testData = dataObj {
			DECLARED_VARIABLES { D::dosingTarget; Y::observation }
			DATA_INPUT_VARIABLES {
				AMT : { use is amt, variable=D }
				T : { use is idv }
				DV : { use is dv, variable = Y }
			} # end DATA_INPUT_VARIABLES
			SOURCE {
			    foo : {file = "warfarin_conc.csv", 
			       		inputFormat  is nonmemFormat } 
			} # end SOURCE
		}		
		testMdl = mdlObj {
				IDV{T}
				VARIABILITY_LEVELS{
				}
				
				MODEL_PREDICTION{
					DEQ{
						X : { deriv = 1 }
					}
				}
		
			OBSERVATION{
				Y : { type is userDefined, prediction=X, value=X, weight=0 } 
			}
		}
		p1 = parObj{
			
		}
		
		t1 = taskObj{
			ESTIMATE{
				set algo is saem
			}
		}
		
		mog = mogObj{
			OBJECTS{
				testData : { type is dataObj }
				testMdl : { type is mdlObj }
				p1 : { type is parObj }
				t1 : { type is taskObj }
			}
		}
		'''.parse
	
		mcl.assertError(MdlPackage::eINSTANCE.mclObject,
			MdlValidator::MODEL_DATA_MISMATCH,
			"dosing variable D has no match in mdlObj")
//		mcl.assertError(MdlPackage::eINSTANCE.mclObject,
//			MdlValidator::SYMBOL_NOT_INITIALISED,
//			"variable 'X' in object 'testMdl' is not initialised")
	}

	@Test
	def void testInvalidSingleOdeDosingMissingModelNoDEQBlkMatchMog(){
		val mcl = '''
		testData = dataObj {
			DECLARED_VARIABLES { D::dosingTarget; Y::observation }
			DATA_INPUT_VARIABLES {
				AMT : { use is amt, variable=D }
				T : { use is idv }
				DV : { use is dv, variable = Y }
			} # end DATA_INPUT_VARIABLES
			SOURCE {
			    foo : {file = "warfarin_conc.csv", 
			       		inputFormat  is nonmemFormat } 
			} # end SOURCE
		}		
		testMdl = mdlObj {
				IDV{T}
				VARIABILITY_LEVELS{
				}
				
				MODEL_PREDICTION{
					X : { deriv = 1 }
				}
				OBSERVATION{
					Y : { type is userDefined, value=X, prediction=X, weight = 0 } 
				}
		}
		p1 = parObj{
			
		}
		
		t1 = taskObj{
			ESTIMATE{
				set algo is saem
			}
		}
		
		mog = mogObj{
			OBJECTS{
				testData : { type is dataObj }
				testMdl : { type is mdlObj }
				p1 : { type is parObj }
				t1 : { type is taskObj }
			}
		}
		'''.parse
	
		mcl.assertError(MdlPackage::eINSTANCE.mclObject,
			MdlValidator::MODEL_DATA_MISMATCH,
			"dosing variable D has no match in mdlObj")
//		mcl.assertError(MdlPackage::eINSTANCE.mclObject,
//			MdlValidator::SYMBOL_NOT_INITIALISED,
//			"variable 'X' in object 'testMdl' is not initialised")
	}

	@Test
	def void testValidSingleCompartmentDosingMissingModelMatchMog(){
		val mcl = '''
		testData = dataObj {
			DECLARED_VARIABLES { D::dosingTarget Y::observation }
			DATA_INPUT_VARIABLES {
				T : { use is idv }
				AMT : { use is amt, variable=D }
				DV : { use is dv, variable=Y }
			} # end DATA_INPUT_VARIABLES
			SOURCE {
			    foo : {file = "warfarin_conc.csv", 
			       		inputFormat  is nonmemFormat } 
			} # end SOURCE
		}		
		testMdl = mdlObj {
				IDV{T}
				VARIABILITY_LEVELS{
				}
				
				MODEL_PREDICTION{
					COMPARTMENT{
				      D:   {type is depot, modelCmt=1, to=CENTRAL, ka=1, tlag=1}
				      CENTRAL:    {type is compartment, modelCmt=2}
				             ::   {type is elimination, modelCmt=2, from=CENTRAL, v=1, cl=1}
			         }
				}
			
			OBSERVATION{
				F = 1
				Y : { type is userDefined, prediction=F, value=F, weight=0 } 
			}
		}
		p1 = parObj{
			
		}
		
		t1 = taskObj{
			ESTIMATE{
				set algo is saem
			}
		}
		
		mog = mogObj{
			OBJECTS{
				testData : { type is dataObj }
				testMdl : { type is mdlObj }
				p1 : { type is parObj }
				t1 : { type is taskObj }
			}
		}
		'''.parse
	
		mcl.assertNoErrors
	}

	@Test
	def void testValidMultiDosingMatchMog(){
		val mcl = '''
		testData = dataObj {
			DECLARED_VARIABLES { D::dosingTarget; D2::dosingTarget Y::observation }
			DATA_INPUT_VARIABLES {
				T : { use is idv }
				CMT : { use is cmt }
				AMT : { use is amt, define = { 1 in CMT as D, 2 in CMT as D2 } }
				DV : { use is dv, variable=Y }
			} # end DATA_INPUT_VARIABLES
			SOURCE {
			    foo : {file = "warfarin_conc.csv", 
			       		inputFormat  is nonmemFormat } 
			} # end SOURCE
		}		
		testMdl = mdlObj {
				IDV{T}
				
				VARIABILITY_LEVELS{
				}
				
				MODEL_PREDICTION{
					COMPARTMENT{
						D:   {type is depot, to=CENTRAL, ka=1, tlag=1}
						D2:   {type is direct, to=CENTRAL, tlag=1}
						CENTRAL:    {type is compartment, modelCmt=2}
			             ::   {type is elimination, from=CENTRAL, v=1, cl=1}
						}
				}
			
			OBSERVATION{
				F = 1
				Y : { type is userDefined, prediction=F, value=F, weight=0 } 
			}
		
		}
		p1 = parObj{
			
		}
		
		t1 = taskObj{
			ESTIMATE{
				set algo is saem
			}
		}
		
		mog = mogObj{
			OBJECTS{
				testData : { type is dataObj }
				testMdl : { type is mdlObj }
				p1 : { type is parObj }
				t1 : { type is taskObj }
			}
		}
		'''.parse
	
		mcl.assertNoErrors
	}

	@Test
	def void testInvalidMultiDosingToCompMacroMog(){
		val mcl = '''
		testData = dataObj {
			DECLARED_VARIABLES { D::dosingTarget; CENTRAL::dosingTarget }
			DATA_INPUT_VARIABLES {
				T : { use is idv }
				CMT : { use is cmt }
				AMT : { use is amt, define = { 1 in CMT as D, 2 in CMT as CENTRAL } }
			} # end DATA_INPUT_VARIABLES
			SOURCE {
			    foo : {file = "warfarin_conc.csv", 
			       		inputFormat  is nonmemFormat } 
			} # end SOURCE
		}		
		testMdl = mdlObj {
				IDV{T}
				
				VARIABILITY_LEVELS{
				}
				
				MODEL_PREDICTION{
					COMPARTMENT{
						D:   {type is depot, to=CENTRAL, ka=1, tlag=1}
						CENTRAL:    {type is compartment, modelCmt=2}
			             ::   {type is elimination, from=CENTRAL, v=1, cl=1}
						}
					Y
				}
		
		}
		p1 = parObj{
			
		}
		
		t1 = taskObj{
			ESTIMATE{
				set algo is saem
			}
		}
		
		mog = mogObj{
			OBJECTS{
				testData : { type is dataObj }
				testMdl : { type is mdlObj }
				p1 : { type is parObj }
				t1 : { type is taskObj }
			}
		}
		'''.parse
	
		mcl.assertError(MdlPackage::eINSTANCE.mclObject,
			MdlValidator::INCOMPATIBLE_TYPES,
			"dosing variable 'CENTRAL' can only dose to a dosing compartment macro.")
	}

	@Test
	def void testValidMultiDosingMisMatchMog(){
		val mcl = '''
		testData = dataObj {
			DECLARED_VARIABLES { D::dosingTarget; Y::dosingTarget }
			DATA_INPUT_VARIABLES {
				CMT : { use is cmt }
				AMT : { use is amt, define = { 1 in CMT as D, 3 in CMT as Y } }
			} # end DATA_INPUT_VARIABLES
			SOURCE {
			    foo : {file = "warfarin_conc.csv", 
			       		inputFormat  is nonmemFormat } 
			} # end SOURCE
		}		
		testMdl = mdlObj {
				VARIABILITY_LEVELS{
				}
				
				MODEL_PREDICTION{
					DEQ{
						D :  {type is depot, modelCmt=1, to=CENTRAL, ka=1, tlag=1}
				     	CENTRAL:    {type is compartment, modelCmt=2}
			             ::   {type is elimination, modelCmt=2, from=CENTRAL, v=1, cl=1}
						}
				}
		
		}
		p1 = parObj{
			
		}
		
		t1 = taskObj{
			ESTIMATE{
				set algo is saem
			}
		}
		
		mog = mogObj{
			OBJECTS{
				testData : { type is dataObj }
				testMdl : { type is mdlObj }
				p1 : { type is parObj }
				t1 : { type is taskObj }
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
			DECLARED_VARIABLES { D::dosingTarget }
			DATA_INPUT_VARIABLES {
				TIME : { use is idv }
				AMT : { use is amt, variable=D }
			} # end DATA_INPUT_VARIABLES
			SOURCE {
			    foo : {file = "warfarin_conc.csv", 
			       		inputFormat  is nonmemFormat } 
			} # end SOURCE
		}		
		testMdl = mdlObj {
				IDV{ T }
			
				VARIABILITY_LEVELS{
				}
				
				MODEL_PREDICTION{
			      D::vector
				}
		
		}
		p1 = parObj{
			
		}
		
		t1 = taskObj{
			ESTIMATE{
				set algo is saem
			}
		}
		
		mog = mogObj{
			OBJECTS{
				testData : { type is dataObj }
				testMdl : { type is mdlObj }
				p1 : { type is parObj }
				t1 : { type is taskObj }
			}
		}
		'''.parse
	
		mcl.assertError(MdlPackage::eINSTANCE.mclObject,
			MdlValidator::INCOMPATIBLE_TYPES,
			"dosing variable D has an inconsistent type with its match in the mdlObj")
	}

	
	@Test
	def void testInvalidSingleDosingIntialisedIncompatibleTypesMog(){
		val mcl = '''
		testData = dataObj {
			DECLARED_VARIABLES { D::dosingTarget }
			DATA_INPUT_VARIABLES {
				TIME : { use is idv }
				AMT : { use is amt, variable=D }
			} # end DATA_INPUT_VARIABLES
			SOURCE {
			    foo : {file = "warfarin_conc.csv", 
			       		inputFormat  is nonmemFormat } 
			} # end SOURCE
		}		
		testMdl = mdlObj {
				IDV{ T }
			
				VARIABILITY_LEVELS{
				}
				
				MODEL_PREDICTION{
			      D = 1
				}
		
		}
		p1 = parObj{
			
		}
		
		t1 = taskObj{
			ESTIMATE{
				set algo is saem
			}
		}
		
		mog = mogObj{
			OBJECTS{
				testData : { type is dataObj }
				testMdl : { type is mdlObj }
				p1 : { type is parObj }
				t1 : { type is taskObj }
			}
		}
		'''.parse
	
		mcl.assertError(MdlPackage::eINSTANCE.mclObject,
			MdlValidator::INCOMPATIBLE_TYPES,
			"dosing variable D has an inconsistent type with its match in the mdlObj")
	}

	
	@Test
	def void testValidModelStructParamMatchMog(){
		val mcl = '''
		testData = dataObj {
			DECLARED_VARIABLES { D::dosingTarget Y::observation }
			DATA_INPUT_VARIABLES {
				T : { use is idv }
				AMT : { use is amt, variable=D }
				DV : { use is dv, variable=Y }
			} # end DATA_INPUT_VARIABLES
			SOURCE {
			    foo : {file = "warfarin_conc.csv", 
			       		inputFormat  is nonmemFormat } 
			} # end SOURCE
		}		
		testMdl = mdlObj {
				IDV{T}
				VARIABILITY_LEVELS{
				}
				
				STRUCTURAL_PARAMETERS { 
					POP_CL
					POP_V
				} # end STRUCTURAL_PARAMETERS

				MODEL_PREDICTION{
					COMPARTMENT{
				      D:   {type is depot, modelCmt=1, to=CENTRAL, ka=1, tlag=1}
				      CENTRAL:    {type is compartment, modelCmt=2}
				             ::   {type is elimination, modelCmt=2, from=CENTRAL, v=1, cl=1}
			         }
				}
				OBSERVATION{
					F = 1
					Y : { type is userDefined, prediction=F, value=F, weight=0 } 
				}
		
		}
		p1 = parObj{
			STRUCTURAL{
				POP_CL : {value= 1}
				POP_V : { value = 2 }
			}
		}
		
		t1 = taskObj{
			ESTIMATE{
				set algo is saem
			}
		}
		
		mog = mogObj{
			OBJECTS{
				testData : { type is dataObj }
				testMdl : { type is mdlObj }
				p1 : { type is parObj }
				t1 : { type is taskObj }
			}
		}
		'''.parse
	
		mcl.assertNoErrors
	}

	@Test
	def void testValidModelStructParamMatchMatrixTypeMog(){
		val mcl = '''
		testData = dataObj {
			DECLARED_VARIABLES { D::dosingTarget Y::observation }
			DATA_INPUT_VARIABLES {
				T : { use is idv }
				AMT : { use is amt, variable=D }
				DV : { use is dv, variable=Y }
			} # end DATA_INPUT_VARIABLES
			SOURCE {
			    foo : {file = "warfarin_conc.csv", 
			       		inputFormat  is nonmemFormat } 
			} # end SOURCE
		}		
		testMdl = mdlObj {
				IDV{T}
				VARIABILITY_LEVELS{
				}
				
				STRUCTURAL_PARAMETERS { 
					POP_CL::vector
					POP_V
				} # end STRUCTURAL_PARAMETERS

				MODEL_PREDICTION{
					COMPARTMENT{
				      D:   {type is depot, modelCmt=1, to=CENTRAL, ka=1, tlag=1}
				      CENTRAL:    {type is compartment, modelCmt=2}
				             ::   {type is elimination, modelCmt=2, from=CENTRAL, v=1, cl=1}
			         }
				}
			
			OBSERVATION{
				F = 1
				Y : { type is userDefined, prediction=F, value=F, weight=0 } 
			}
		
		}
		p1 = parObj{
			STRUCTURAL{
				POP_CL : {vectorValue = [1] }
				POP_V : { value = 2 }
			}
		}
		
		t1 = taskObj{
			ESTIMATE{
				set algo is saem
			}
		}
		
		mog = mogObj{
			OBJECTS{
				testData : { type is dataObj }
				testMdl : { type is mdlObj }
				p1 : { type is parObj }
				t1 : { type is taskObj }
			}
		}
		'''.parse
	
		mcl.assertNoErrors
	}

	@Test
	def void testValidModelStructParamWithAssignmentInModelMatchMog(){
		val mcl = '''
		testData = dataObj {
			DECLARED_VARIABLES { D::dosingTarget Y::observation }
			DATA_INPUT_VARIABLES {
				T : { use is idv }
				AMT : { use is amt, variable=D }
				DV : { use is dv, variable=Y }
			} # end DATA_INPUT_VARIABLES
			SOURCE {
			    foo : {file = "warfarin_conc.csv", 
			       		inputFormat  is nonmemFormat } 
			} # end SOURCE
		}		
		testMdl = mdlObj {
				IDV{T}

				VARIABILITY_LEVELS{
				}
				
				STRUCTURAL_PARAMETERS { 
					POP_CL = 1
					POP_V
				} # end STRUCTURAL_PARAMETERS

				MODEL_PREDICTION{
					COMPARTMENT{
				      D:   {type is depot, modelCmt=1, to=CENTRAL, ka=1, tlag=1}
				      CENTRAL:    {type is compartment, modelCmt=2}
				             ::   {type is elimination, modelCmt=2, from=CENTRAL, v=1, cl=1}
			         }
			}
			OBSERVATION{
				F = 1
				Y : { type is userDefined, prediction=F, value=F, weight=0 } 
			}
		
		}
		p1 = parObj{
			STRUCTURAL{
				POP_V : { value = 2 }
			}
		}
		
		t1 = taskObj{
			ESTIMATE{
				set algo is saem
			}
		}
		
		mog = mogObj{
			OBJECTS{
				testData : { type is dataObj }
				testMdl : { type is mdlObj }
				p1 : { type is parObj }
				t1 : { type is taskObj }
			}
		}
		'''.parse
	
		mcl.assertNoErrors
	}

	@Test
	def void testInvalidModelStructParamMatchMissingParamMog(){
		val mcl = '''
		testData = dataObj {
			DECLARED_VARIABLES { D::dosingTarget }
			DATA_INPUT_VARIABLES {
				AMT : { use is amt, variable=D }
			} # end DATA_INPUT_VARIABLES
			SOURCE {
			    foo : {file = "warfarin_conc.csv", 
			       		inputFormat  is nonmemFormat } 
			} # end SOURCE
		}		
		testMdl = mdlObj {
				VARIABILITY_LEVELS{
				}
				
				STRUCTURAL_PARAMETERS { 
					POP_CL
					POP_V
				} # end STRUCTURAL_PARAMETERS

				MODEL_PREDICTION{
					COMPARTMENT{
				      D:   {type is depot, modelCmt=1, to=CENTRAL, ka=1, tlag=1}
				      CENTRAL:    {type is compartment, modelCmt=2}
				             ::   {type is elimination, modelCmt=2, from=CENTRAL, v=1, cl=1}
			         }
				}
		
		}
		p1 = parObj{
			STRUCTURAL{
				POP_CL : { value= 1}
			}
		}
		
		t1 = taskObj{
			ESTIMATE{
				set algo is saem
			}
		}
		
		mog = mogObj{
			OBJECTS{
				testData : { type is dataObj }
				testMdl : { type is mdlObj }
				p1 : { type is parObj }
				t1 : { type is taskObj }
			}
		}
		'''.parse
	
		mcl.assertError(MdlPackage::eINSTANCE.mclObject,
			MdlValidator::MODEL_DATA_MISMATCH,
			"parameter 'POP_V' has no match in parObj")
	}

	@Test
	def void testInvalidModelStructParamMatchWrongParamBlockMog(){
		val mcl = '''
		testData = dataObj {
			DECLARED_VARIABLES { D::dosingTarget }
			DATA_INPUT_VARIABLES {
				AMT : { use is amt, variable=D }
			} # end DATA_INPUT_VARIABLES
			SOURCE {
			    foo : {file = "warfarin_conc.csv", 
			       		inputFormat  is nonmemFormat } 
			} # end SOURCE
		}		
		testMdl = mdlObj {
				VARIABILITY_LEVELS{
				}
				
				STRUCTURAL_PARAMETERS { 
					POP_CL
					POP_V
				} # end STRUCTURAL_PARAMETERS

				MODEL_PREDICTION{
					COMPARTMENT{
				      D:   {type is depot, modelCmt=1, to=CENTRAL, ka=1, tlag=1}
				      CENTRAL:    {type is compartment, modelCmt=2}
				             ::   {type is elimination, modelCmt=2, from=CENTRAL, v=1, cl=1}
			         }
				}
		
		}
		p1 = parObj{
			VARIABILITY{
				POP_V : { value = 2, type is sd }
			}
			
			STRUCTURAL{
				POP_CL: { value = 1 }
			}
		}
		
		t1 = taskObj{
			ESTIMATE{
				set algo is saem
			}
		}
		
		mog = mogObj{
			OBJECTS{
				testData : { type is dataObj }
				testMdl : { type is mdlObj }
				p1 : { type is parObj }
				t1 : { type is taskObj }
			}
		}
		'''.parse
	
		mcl.assertError(MdlPackage::eINSTANCE.mclObject,
			MdlValidator::MODEL_DATA_MISMATCH,
			"Parameter 'POP_V' in mdlObj cannot match a variability parameter in the parObj")
	}


	@Test
	def void testValidWithWarningMogModelStructParamMatchMaskedAssignmentinMdl(){
		val mcl = '''
		testData = dataObj {
			DECLARED_VARIABLES { D::dosingTarget Y::observation }
			DATA_INPUT_VARIABLES {
				T : { use is idv }
				AMT : { use is amt, variable=D }
				DV : { use is dv, variable=Y }
			} # end DATA_INPUT_VARIABLES
			SOURCE {
			    foo : {file = "warfarin_conc.csv", 
			       		inputFormat  is nonmemFormat } 
			} # end SOURCE
		}		
		testMdl = mdlObj {
				IDV{T}

				VARIABILITY_LEVELS{
				}
				
				STRUCTURAL_PARAMETERS { 
					POP_CL = 1.0
					POP_V
				} # end STRUCTURAL_PARAMETERS

				MODEL_PREDICTION{
					COMPARTMENT{
				      D:   {type is depot, modelCmt=1, to=CENTRAL, ka=1, tlag=1}
				      CENTRAL:    {type is compartment, modelCmt=2}
				             ::   {type is elimination, modelCmt=2, from=CENTRAL, v=1, cl=1}
			         }
				}
			
			OBSERVATION{
				F = 1
				Y : { type is userDefined, prediction=F, value=F, weight=0 } 
			}
		
		}
		p1 = parObj{
			VARIABILITY{
			}
			
			STRUCTURAL{
				POP_CL: { value = 1 }
				POP_V : { value = 2 }
			}
		}
		
		t1 = taskObj{
			ESTIMATE{
				set algo is saem
			}
		}
		
		mog = mogObj{
			OBJECTS{
				testData : { type is dataObj }
				testMdl : { type is mdlObj }
				p1 : { type is parObj }
				t1 : { type is taskObj }
			}
		}
		'''.parse
	
		mcl.assertNoErrors
		mcl.assertWarning(MdlPackage::eINSTANCE.mclObject,
			MdlValidator::MASKING_PARAM_ASSIGNMENT,
			"value assigned to parameter 'POP_CL' in mdlObj is overridden by value in parObj")
	}

	@Test
	def void testValidModelVarParamMatchMog(){
		val mcl = '''
		testData = dataObj {
			DECLARED_VARIABLES { D::dosingTarget Y::observation }
			DATA_INPUT_VARIABLES {
				T : { use is idv }
				AMT : { use is amt, variable=D }
				DV : { use is dv, variable=Y }
			} # end DATA_INPUT_VARIABLES
			SOURCE {
			    foo : {file = "warfarin_conc.csv", 
			       		inputFormat  is nonmemFormat } 
			} # end SOURCE
		}		
		testMdl = mdlObj {
				IDV{T}
				VARIABILITY_LEVELS{
				}
				
				VARIABILITY_PARAMETERS { 
					POP_CL
					POP_V
				} 

				MODEL_PREDICTION{
					COMPARTMENT{
				      D:   {type is depot, modelCmt=1, to=CENTRAL, ka=1, tlag=1}
				      CENTRAL:    {type is compartment, modelCmt=2}
				             ::   {type is elimination, modelCmt=2, from=CENTRAL, v=1, cl=1}
			         }
				}
			OBSERVATION{
				F = 1
				Y : { type is userDefined, prediction=F, value=F, weight=0 } 
			}
		}
		p1 = parObj{
			VARIABILITY{
				POP_CL: { value = 1, type is sd }
				POP_V : { value = 2, type is sd }
			}
		}
		
		t1 = taskObj{
			ESTIMATE{
				set algo is saem
			}
		}
		
		mog = mogObj{
			OBJECTS{
				testData : { type is dataObj }
				testMdl : { type is mdlObj }
				p1 : { type is parObj }
				t1 : { type is taskObj }
			}
		}
		'''.parse
	
		mcl.assertNoErrors
	}

	@Test
	def void testValidModelVarParamWithAssignmentInModelMatch(){
		val mcl = '''
		testData = dataObj {
			DECLARED_VARIABLES { D::dosingTarget Y::observation }
			DATA_INPUT_VARIABLES {
				T : { use is idv }
				AMT : { use is amt, variable=D }
				DV : { use is dv, variable=Y }
			} # end DATA_INPUT_VARIABLES
			SOURCE {
			    foo : {file = "warfarin_conc.csv", 
			       		inputFormat  is nonmemFormat } 
			} # end SOURCE
		}		
		testMdl = mdlObj {
				IDV{T}
				VARIABILITY_LEVELS{
				}
				
				VARIABILITY_PARAMETERS { 
					POP_CL
					POP_V = 1
				} 

				MODEL_PREDICTION{
					COMPARTMENT{
				      D:   {type is depot, modelCmt=1, to=CENTRAL, ka=1, tlag=1}
				      CENTRAL:    {type is compartment, modelCmt=2}
				             ::   {type is elimination, modelCmt=2, from=CENTRAL, v=1, cl=1}
			         }
				}
			OBSERVATION{
				F = CENTRAL
				Y : { type is userDefined, prediction=F, value=F, weight=0 } 
			}
		
		}
		p1 = parObj{
			VARIABILITY{
				POP_CL: { value = 1, type is sd }
			}
		}
		
		t1 = taskObj{
			ESTIMATE{
				set algo is saem
			}
		}
		
		mog = mogObj{
			OBJECTS{
				testData : { type is dataObj }
				testMdl : { type is mdlObj }
				p1 : { type is parObj }
				t1 : { type is taskObj }
			}
		}
		'''.parse
	
		mcl.assertNoErrors
	}

	@Test
	def void testInvalidModelVarParamsMatchMissingParamMog(){
		val mcl = '''
		testData = dataObj {
			DECLARED_VARIABLES { D::dosingTarget }
			DATA_INPUT_VARIABLES {
				TIME : { use is idv }
				AMT : { use is amt, variable=D }
			} # end DATA_INPUT_VARIABLES
			SOURCE {
			    foo : {file = "warfarin_conc.csv", 
			       		inputFormat  is nonmemFormat } 
			} # end SOURCE
		}		
		testMdl = mdlObj {
				IDV{T}
			
				VARIABILITY_LEVELS{
				}
				
				VARIABILITY_PARAMETERS { 
					POP_CL
					POP_V
				} 

				MODEL_PREDICTION{
					COMPARTMENT{
				      D:   {type is depot, modelCmt=1, to=CENTRAL, ka=1, tlag=1}
				      CENTRAL:    {type is compartment, modelCmt=2}
				             ::   {type is elimination, modelCmt=2, from=CENTRAL, v=1, cl=1}
			         }
				}
		
		}
		p1 = parObj{
			VARIABILITY{
				POP_CL: { value = 1, type is var }
			}
		}
		
		t1 = taskObj{
			ESTIMATE{
				set algo is saem
			}
		}
		
		mog = mogObj{
			OBJECTS{
				testData : { type is dataObj }
				testMdl : { type is mdlObj }
				p1 : { type is parObj }
				t1 : { type is taskObj }
			}
		}
		'''.parse
	
		mcl.assertError(MdlPackage::eINSTANCE.mclObject,
			MdlValidator::MODEL_DATA_MISMATCH,
			"parameter 'POP_V' has no match in parObj")
	}

	@Test
	def void testInvalidModelVarParamMatchWrongParamBlockMog(){
		val mcl = '''
		testData = dataObj {
			DECLARED_VARIABLES { D::dosingTarget }
			DATA_INPUT_VARIABLES {
				AMT : { use is amt, variable=D }
			} # end DATA_INPUT_VARIABLES
			SOURCE {
			    foo : {file = "warfarin_conc.csv", 
			       		inputFormat  is nonmemFormat } 
			} # end SOURCE
		}		
		testMdl = mdlObj {
				VARIABILITY_LEVELS{
				}
				
				VARIABILITY_PARAMETERS { 
					POP_CL
					POP_V
				} 

				MODEL_PREDICTION{
					COMPARTMENT{
				      D:   {type is depot, modelCmt=1, to=CENTRAL, ka=1, tlag=1}
				      CENTRAL:    {type is compartment, modelCmt=2}
				             ::   {type is elimination, modelCmt=2, from=CENTRAL, v=1, cl=1}
			         }
				}
		
		}
		p1 = parObj{
			VARIABILITY{
				POP_V : { value = 2, type is sd }
			}
			
			STRUCTURAL{
				POP_CL : { value = 1 }
			}
		}
		
		t1 = taskObj{
			ESTIMATE{
				set algo is saem
			}
		}
		
		mog = mogObj{
			OBJECTS{
				testData : { type is dataObj }
				testMdl : { type is mdlObj }
				p1 : { type is parObj }
				t1 : { type is taskObj }
			}
		}
		'''.parse
	
		mcl.assertError(MdlPackage::eINSTANCE.mclObject,
			MdlValidator::MODEL_DATA_MISMATCH,
			"Parameter 'POP_CL' in mdlObj cannot match a structural parameter in the parObj"
			)
	}

	@Test
	def void testValidWithWarningMogModelVarParamMatchMaskedAssignmentinMdl(){
		val mcl = '''
		testData = dataObj {
			DECLARED_VARIABLES { D::dosingTarget Y::observation }
			DATA_INPUT_VARIABLES {
				T : { use is idv }
				AMT : { use is amt, variable=D }
				DV : { use is dv, variable=Y }
			} # end DATA_INPUT_VARIABLES
			SOURCE {
			    foo : {file = "warfarin_conc.csv", 
			       		inputFormat  is nonmemFormat } 
			} # end SOURCE
		}		
		testMdl = mdlObj {
				IDV{T}
				VARIABILITY_LEVELS{
				}
				
				VARIABILITY_PARAMETERS { 
					POP_CL = 1.0
					POP_V
				} 

				MODEL_PREDICTION{
					COMPARTMENT{
				      D:   {type is depot, modelCmt=1, to=CENTRAL, ka=1, tlag=1}
				      CENTRAL:    {type is compartment, modelCmt=2}
				             ::   {type is elimination, modelCmt=2, from=CENTRAL, v=1, cl=1}
			         }
				}
			
			OBSERVATION{
				F = 1
				Y : { type is userDefined, prediction=F, value=F, weight=0 } 
			}
		
		}
		p1 = parObj{
			VARIABILITY{
				POP_CL: { value = 1, type is sd }
				POP_V : { value = 2, type is sd }
			}
			
			STRUCTURAL{
			}
		}
		
		t1 = taskObj{
			ESTIMATE{
				set algo is saem
			}
		}
		
		mog = mogObj{
			OBJECTS{
				testData : { type is dataObj }
				testMdl : { type is mdlObj }
				p1 : { type is parObj }
				t1 : { type is taskObj }
			}
		}
		'''.parse
	
		mcl.assertNoErrors
		mcl.assertWarning(MdlPackage::eINSTANCE.mclObject,
			MdlValidator::MASKING_PARAM_ASSIGNMENT,
			"value assigned to parameter 'POP_CL' in mdlObj is overridden by value in parObj")
	}
	
		@Test
	def void testInvalidDanglingObjectRefMog(){
		val mcl = '''
		testData = dataObj {
			DECLARED_VARIABLES { D::dosingTarget }
			DATA_INPUT_VARIABLES {
				AMT : { use is amt, variable=D }
				TIME : { use is idv }
			} # end DATA_INPUT_VARIABLES
			SOURCE {
			    foo : {file = "warfarin_conc.csv", 
			       		inputFormat  is nonmemFormat } 
			} # end SOURCE
		}		
		testMdl = mdlObj {
				IDV {T}
			
				VARIABILITY_LEVELS{
				}
				
				STRUCTURAL_PARAMETERS { 
					POP_CL
					POP_V
				} # end STRUCTURAL_PARAMETERS

				MODEL_PREDICTION{
					COMPARTMENT{
				      D:   {type is depot, modelCmt=1, to=CENTRAL, ka=1, tlag=1}
				      CENTRAL:    {type is compartment, modelCmt=2}
				             ::   {type is elimination, modelCmt=2, from=CENTRAL, v=1, cl=1}
			         }
				}
			
			OBSERVATION{
				F = 1
				Y : { type is userDefined, prediction=F, value=F, weight=0 } 
			}
		
		}
		p1 = parObj{
			STRUCTURAL{
				POP_CL : {value= 1}
				POP_V : { value = 2 }
			}
		}
		
		t1 = taskObj{
			ESTIMATE{
				set algo is saem
			}
		}
		
		mog = mogObj{
			OBJECTS{
				testData : { type is dataObj }
				missing : { type is mdlObj }
				p1 : { type is parObj }
				t1 : { type is taskObj }
			}
		}
		'''.parse
	
		mcl.assertError(MdlPackage::eINSTANCE.listDefinition,
			MdlValidator::MCLOBJ_REF_UNRESOLVED,
			"the object 'missing' cannot be found")
	}

	
	@Test
	def void testInvalidMogVarUnitialisedInMdl(){
		val mcl = '''
		testData = dataObj {
			DECLARED_VARIABLES { D::dosingTarget Y::observation }
			DATA_INPUT_VARIABLES {
				T : { use is idv }
				AMT : { use is amt, variable=D }
				DV : { use is dv, variable=Y }
			} # end DATA_INPUT_VARIABLES
			SOURCE {
			    foo : {file = "warfarin_conc.csv", 
			       		inputFormat  is nonmemFormat } 
			} # end SOURCE
		}		
		testMdl = mdlObj {
				IDV{T}
				VARIABILITY_LEVELS{
				}
				
				VARIABILITY_PARAMETERS { 
					POP_CL
					POP_V
				} 

				MODEL_PREDICTION{
					Z
					COMPARTMENT{
				      D:   {type is depot, modelCmt=1, to=CENTRAL, ka=1, tlag=1}
				      CENTRAL:    {type is compartment, modelCmt=2}
				             ::   {type is elimination, modelCmt=2, from=CENTRAL, v=1, cl=1}
			         }
				}
			
			OBSERVATION{
				F = 1
				Y : { type is userDefined, prediction=F, value=F, weight=0 } 
			}
		
		}
		p1 = parObj{
			VARIABILITY{
				POP_CL: { value = 1, type is sd }
				POP_V : { value = 2, type is sd }
			}
			
			STRUCTURAL{
			}
		}
		
		t1 = taskObj{
			ESTIMATE{
				set algo is saem
			}
		}
		
		mog = mogObj{
			OBJECTS{
				testData : { type is dataObj }
				testMdl : { type is mdlObj }
				p1 : { type is parObj }
				t1 : { type is taskObj }
			}
		}
		'''.parse
	
		mcl.assertError(MdlPackage::eINSTANCE.mclObject,
			MdlValidator::SYMBOL_NOT_INITIALISED,
			"variable 'Z' in object 'testMdl' is not initialised")
	}

	
	@Test
	def void testInvalidMogDosingVarNotCompatibleWithVariableMdl(){
		val mcl = '''
		testData = dataObj {
			DECLARED_VARIABLES { Y::observation }
			DATA_INPUT_VARIABLES {
				T : { use is idv }
				DV : { use is dv, variable=Y }
				Z : { use is variable }
			} # end DATA_INPUT_VARIABLES
			SOURCE {
			    foo : {file = "warfarin_conc.csv", 
			       		inputFormat  is nonmemFormat } 
			} # end SOURCE
		}		
		testMdl = mdlObj {
				IDV{T}
				VARIABILITY_LEVELS{
				}
				
				VARIABILITY_PARAMETERS { 
					POP_CL
					POP_V
				} 

				MODEL_PREDICTION{
					Z::dosingTarget
					F = 0
				}
			
			OBSERVATION{
				Y : { type is userDefined, prediction=F, value=F, weight=0 } 
			}
		
		}
		p1 = parObj{
			VARIABILITY{
				POP_CL: { value = 1, type is sd }
				POP_V : { value = 2, type is sd }
			}
			
			STRUCTURAL{
			}
		}
		
		t1 = taskObj{
			ESTIMATE{
				set algo is saem
			}
		}
		
		mog = mogObj{
			OBJECTS{
				testData : { type is dataObj }
				testMdl : { type is mdlObj }
				p1 : { type is parObj }
				t1 : { type is taskObj }
			}
		}
		'''.parse
	
		mcl.assertError(MdlPackage::eINSTANCE.mclObject,
			MdlValidator::INCOMPATIBLE_TYPES,
			"variable 'Z' in object 'testMdl' has an inconsistent type with its match in 'testData'.")
	}

	
	@Test
	def void testInvalidMogVarNotCompatibleWithVariableMdl(){
		val mcl = '''
		testData = dataObj {
			DECLARED_VARIABLES { Y::observation }
			DATA_INPUT_VARIABLES {
				T : { use is idv }
				DV : { use is dv, variable=Y }
				D : { use is variable }
			} # end DATA_INPUT_VARIABLES
			SOURCE {
			    foo : {file = "warfarin_conc.csv", 
			       		inputFormat  is nonmemFormat } 
			} # end SOURCE
		}		
		testMdl = mdlObj {
				IDV{T}
				VARIABILITY_LEVELS{
				}
				
				VARIABILITY_PARAMETERS { 
					POP_CL
					POP_V
				} 

				MODEL_PREDICTION{
					COMPARTMENT{
				      D:   {type is depot, modelCmt=1, to=CENTRAL, ka=1, tlag=1}
				      CENTRAL:    {type is compartment, modelCmt=2}
				             ::   {type is elimination, modelCmt=2, from=CENTRAL, v=1, cl=1}
			         }
				}
			
			OBSERVATION{
				F = 1
				Y : { type is userDefined, prediction=F, value=F, weight=0 } 
			}
		
		}
		p1 = parObj{
			VARIABILITY{
				POP_CL: { value = 1, type is sd }
				POP_V : { value = 2, type is sd }
			}
			
			STRUCTURAL{
			}
		}
		
		t1 = taskObj{
			ESTIMATE{
				set algo is saem
			}
		}
		
		mog = mogObj{
			OBJECTS{
				testData : { type is dataObj }
				testMdl : { type is mdlObj }
				p1 : { type is parObj }
				t1 : { type is taskObj }
			}
		}
		'''.parse
	
		mcl.assertError(MdlPackage::eINSTANCE.mclObject,
			MdlValidator::INCOMPATIBLE_TYPES,
			"dosing macro 'D' in object 'testMdl' has an inconsistent type with its match in 'testData'")
	}


	@Test
	def void testInvalidMogCompartmentDosingMacroNotInitialisedInData(){
		val mcl = '''
		testData = dataObj {
			DECLARED_VARIABLES { Y::observation }
			DATA_INPUT_VARIABLES {
				T : { use is idv }
				DV : { use is dv, variable=Y }
			} # end DATA_INPUT_VARIABLES
			SOURCE {
			    foo : {file = "warfarin_conc.csv", 
			       		inputFormat  is nonmemFormat } 
			} # end SOURCE
		}		
		testMdl = mdlObj {
				IDV{T}
				VARIABILITY_LEVELS{
				}
				
				VARIABILITY_PARAMETERS { 
					POP_CL
					POP_V
				} 

				MODEL_PREDICTION{
					COMPARTMENT{
				      D:   {type is depot, modelCmt=1, to=CENTRAL, ka=1, tlag=1}
				      CENTRAL:    {type is compartment, modelCmt=2}
				             ::   {type is elimination, modelCmt=2, from=CENTRAL, v=1, cl=1}
			         }
				}
			
			OBSERVATION{
				F = 1
				Y : { type is userDefined, prediction=F, value=F, weight=0 } 
			}
		
		}
		p1 = parObj{
			VARIABILITY{
				POP_CL: { value = 1, type is sd }
				POP_V : { value = 2, type is sd }
			}
			
			STRUCTURAL{
			}
		}
		
		t1 = taskObj{
			ESTIMATE{
				set algo is saem
			}
		}
		
		mog = mogObj{
			OBJECTS{
				testData : { type is dataObj }
				testMdl : { type is mdlObj }
				p1 : { type is parObj }
				t1 : { type is taskObj }
			}
		}
		'''.parse
	
		mcl.assertError(MdlPackage::eINSTANCE.mclObject,
			MdlValidator::SYMBOL_NOT_INITIALISED,
			"dosing macro 'D' in object 'testMdl' is not initialised")
	}



		@Test
	def void testInvalidDosingDesignMisMatch(){
		val mcl = '''
		d1 = designObj{
		  DECLARED_VARIABLES{
		  	INPUT_KA :: dosingTarget
		  	CP_obs :: observation
		  }
		  INTERVENTION{
		 	admin1 : {type is bolus, input=INPUT_KA, amount=100, doseTime=[0] }
		  }
		  SAMPLING{
			winPK : {type is simple, outcome=CP_obs, sampleTime = [0.0001, 24, 36, 48, 72, 96, 120] }
		  }
		  STUDY_DESIGN{
			arm1 : {
				armSize = 3,
				interventionSequence=[{
					admin=admin1,
					start=0
				}],
				samplingSequence=[{
					sample=winPK,
					start=0
				}]
			}
		  }
		}
		foo = mdlObj {
				IDV{T}
				
				VARIABILITY_LEVELS(reference=ID){
					ID : { type is parameter, level = 2 }
					DV : { type is observation, level = 1 }
				}
			
			OBSERVATION{
				F = 1
				CP_obs : { type is userDefined, prediction=F, value=F, weight=0 } 
			}
		
		}
		p1 = parObj{
			
		}
		
		t1 = taskObj{
			ESTIMATE{
				set algo is saem
			}
		}
		
		mog = mogObj{
			OBJECTS{
				d1 : { type is designObj }
				foo : { type is mdlObj }
				p1 : { type is parObj }
				t1 : { type is taskObj }
			}
		}
		'''.parse
	
		mcl.assertError(MdlPackage::eINSTANCE.mclObject,
			MdlValidator::MODEL_DATA_MISMATCH,
			"dosing variable INPUT_KA has no match in mdlObj")
	}

	@Test
	def void testInvalidDosingToCmtFromDesign(){
		val mcl = '''
		d1 = designObj{
		  DECLARED_VARIABLES{
		  	CENTRAL :: dosingTarget
		  	CP_obs :: observation
		  }
		  INTERVENTION{
		 	admin1 : {type is bolus, input=CENTRAL, amount=100, doseTime=[0] }
		  }
		  SAMPLING{
			winPK : {type is simple, outcome=CP_obs, sampleTime = [0.0001, 24, 36, 48, 72, 96, 120] }
		  }
		  STUDY_DESIGN{
			arm1 : {
				armSize = 3,
				interventionSequence=[{
					admin=admin1,
					start=0
				}],
				samplingSequence=[{
					sample=winPK,
					start=0
				}]
			}
		  }
		}
		testMdl = mdlObj {
				IDV{T}
				
				VARIABILITY_LEVELS{
				}
				
				MODEL_PREDICTION{
					COMPARTMENT{
						D:   {type is depot, to=CENTRAL, ka=1, tlag=1}
						CENTRAL:    {type is compartment, modelCmt=2}
			             ::   {type is elimination, from=CENTRAL, v=1, cl=1}
						}
					Y
				}
		
		}
		p1 = parObj{
			
		}
		
		t1 = taskObj{
			ESTIMATE{
				set algo is saem
			}
		}
		
		mog = mogObj{
			OBJECTS{
				d1 : { type is designObj }
				testMdl : { type is mdlObj }
				p1 : { type is parObj }
				t1 : { type is taskObj }
			}
		}
		'''.parse
	
		mcl.assertError(MdlPackage::eINSTANCE.mclObject,
			MdlValidator::INCOMPATIBLE_TYPES,
			"dosing variable 'CENTRAL' can only dose to a dosing compartment macro.")
		mcl.assertError(MdlPackage::eINSTANCE.mclObject,
			MdlValidator::SYMBOL_NOT_INITIALISED,
			"dosing macro 'D' in object 'testMdl' is not initialised.")
	}
	
	
}