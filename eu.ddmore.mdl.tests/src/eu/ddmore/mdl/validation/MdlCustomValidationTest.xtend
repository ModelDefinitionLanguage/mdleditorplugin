package eu.ddmore.mdl.validation

import com.google.inject.Inject
import eu.ddmore.mdl.tests.LibraryTestHelper
import eu.ddmore.mdl.tests.MdlAndLibInjectorProvider
import eu.ddmore.mdl.mdl.Mcl
import eu.ddmore.mdl.mdl.MdlPackage
import eu.ddmore.mdllib.mdllib.MdlLibPackage
import org.eclipse.xtext.testing.InjectWith
import org.eclipse.xtext.testing.XtextRunner
import org.eclipse.xtext.testing.validation.ValidationTestHelper
import org.junit.Ignore
import org.junit.Test
import org.junit.runner.RunWith

@RunWith(typeof(XtextRunner))
@InjectWith(typeof(MdlAndLibInjectorProvider))
class MdlCustomValidationTest {
	@Inject extension LibraryTestHelper<Mcl>
	@Inject extension ValidationTestHelper
	


	@Test
	def void testIncorrectUseOfTransFunction(){
		val mcl = '''bar = mdlObj {
			IDV{T}
			
			COVARIATES{
			}
			
			VARIABILITY_LEVELS{
			}
			
			INDIVIDUAL_VARIABLES{
				logit(BETA_CL_WT) = 22
			}
		}'''.parse
		
		mcl.assertError(MdlPackage::eINSTANCE.transformedDefinition,
			MdlValidator::INVALID_LHS_FUNC,
			"Use of a transformation function on the LHS of the equation is not permitted in this context"
		)
	}

	@Ignore("Ignore LHS using functions is not longer supported")
	def void testInconsistentTransFunctions(){
		val mcl = '''bar = mdlObj {
			IDV{T}			
			COVARIATES{
			}
			
			VARIABILITY_LEVELS{
			}
			
			INDIVIDUAL_VARIABLES{
				logit(BETA_CL_WT) = linear(trans is ln, pop=1, ranEff = [1])
			}
		}'''.parse
		
		mcl.assertError(MdlPackage::eINSTANCE.enumPair,
			MdlValidator::INVALID_LHS_FUNC,
			"transformation used on LHS ('logit') must match the RHS ('ln')"
		)
	}

	@Ignore("Ignore LHS using functions is not longer supported")
	def void testInconsistentNoRhsFunctions(){
		val mcl = '''bar = mdlObj {
			
			
			COVARIATES{
			}
			
			VARIABILITY_LEVELS{
			}
			
			INDIVIDUAL_VARIABLES{
				probit(BETA_CL_WT) = linear(pop=1, ranEff = [1])
			}
		}'''.parse
		
		mcl.assertError(MdlPackage::eINSTANCE.transformedDefinition,
			MdlValidator::INVALID_LHS_FUNC,
			"Use of a transformation function on the LHS of the equation is not permitted in this context"
		)
	}

	@Ignore("Ignore LHS using functions is not longer supported")
	def void testInconsistentNoLhsFunctions(){
		val mcl = '''bar = mdlObj {
			
			
			COVARIATES{
			}
			
			VARIABILITY_LEVELS{
			}
			
			INDIVIDUAL_VARIABLES{
				BETA_CL_WT = linear(trans is ln, pop=1, ranEff = [1])
			}
		}'''.parse
		
		mcl.assertError(MdlPackage::eINSTANCE.valuePair,
			MdlValidator::INVALID_LHS_FUNC,
			"no transformation used on the LHS, so cannot use on the RHS of the equation"
		)
	}

	@Ignore("Ignore LHS using functions is not longer supported")
	def void testValidLhsTransLinearDefn(){
		val mcl = '''bar = mdlObj {
			IDV{T}
			
			COVARIATES{
			}
			
			VARIABILITY_LEVELS{
			}
			
			INDIVIDUAL_VARIABLES{
				BETA_CL_WT = linear(trans is ln, pop=1, ranEff = [1])
			}
		}'''.parse
		
		mcl.assertNoErrors
	}

	@Ignore("Ignore LHS using functions is not longer supported")
	def void testValidLhsNoneTransLinearDefn(){
		val mcl = '''bar = mdlObj {
			IDV{T}
			
			COVARIATES{
			}
			
			VARIABILITY_LEVELS{
			}
			
			INDIVIDUAL_VARIABLES{
				BETA_CL_WT = linear(trans is none, pop=1, ranEff = [1])
			}
		}'''.parse
		
		mcl.assertNoErrors
	}

	@Ignore("Ignore LHS using functions is not longer supported")
	def void testValidLhsNoTransLinearDefn(){
		val mcl = '''bar = mdlObj {
			IDV{T}
			
			COVARIATES{
			}
			
			VARIABILITY_LEVELS{
			}
			
			INDIVIDUAL_VARIABLES{
				BETA_CL_WT = linear(pop=1, ranEff = [1])
			}
		}'''.parse
		
		mcl.assertNoErrors
	}

	@Ignore("Ignore LHS using functions is not longer supported")
	def void testValidLhsTransGeneralDefn(){
		val mcl = '''bar = mdlObj {
			IDV{T}
			
			COVARIATES{
			}
			
			VARIABILITY_LEVELS{
			}
			
			INDIVIDUAL_VARIABLES{
				BETA_CL_WT : { type is general, grp=1, ranEff = [1]
			}
		}'''.parse
		
		mcl.assertNoErrors
	}

	@Ignore("Ignore LHS using functions is not longer supported")
	def void testValidLhsNoTransGeneralDefn(){
		val mcl = '''bar = mdlObj {
			IDV{T}
			
			COVARIATES{
			}
			
			VARIABILITY_LEVELS{
			}
			
			INDIVIDUAL_VARIABLES{
				BETA_CL_WT : { type is general, grp=1, ranEff = [1]
			}
		}'''.parse
		
		mcl.assertNoErrors
	}

	@Test
	def void testValidCategorialCovEquality(){
		val mcl = '''bar = mdlObj {
			IDV{T}
			
			COVARIATES{
				sex withCategories { m, f }
			}
			
			VARIABILITY_LEVELS{
			}
			
			GROUP_VARIABLES{
				TSEX = if(sex == sex.f) then 1 else 0
			}
			
			OBSERVATION{
				F = 1
				Y : { type is userDefined, prediction=F, value=F, weight=0 } 
			}
		}'''.parse
		
		mcl.assertNoErrors
	}

	@Test
	def void testInvalidCategorialCovInEquality(){
		val mcl = '''bar = mdlObj {
			IDV{T}
			
			COVARIATES{
				sex withCategories { m, f }
			}
			
			VARIABILITY_LEVELS{
			}
			
			GROUP_VARIABLES{
				TSEX = if(sex <= sex.f) then 1 else 0
			}
			
			
			INDIVIDUAL_VARIABLES{
				BETA_CL_WT : { type is general, grp=1, ranEff = [1]
			}
		}'''.parse
		
		mcl.assertError(MdlPackage::eINSTANCE.relationalExpression,
			MdlValidator::INVALID_ENUM_RELATION_OPERATOR,
			"Cannot use inequality operators with categorical types"
		)
	}

	@Test
	def void testValidDataDefinitions(){
		val mcl = '''
warfarin_T2E_exact_dat = dataObj{

   DECLARED_VARIABLES{ Y::observation; INPUT::dosingTarget }

   DATA_INPUT_VARIABLES{
      ID: {use is id}
      TIME: {use is idv}
      TRT: {use is covariate}
      AMT: {use is amt, variable = INPUT }
      RATE: {use is rate}
      SS: {use is ss}
      ADDL: {use is addl}
      II: {use is ii}
      WT: {use is covariate}
      DVID: {use is dvid}
      DV: {use is dv, variable=Y}
      MDV: {use is mdv}
   }# end DATA_INPUT_VARIABLES

   SOURCE{
      srcFile : {file="warfarin_TTE_exact.csv",
      			inputFormat is nonmemFormat}
   }# end SOURCE
} # end data object
		'''.parse
		
		mcl.assertNoErrors
	}

	@Test
	def void testInvalidMissingAmtDataDefinitions(){
		val mcl = '''
warfarin_T2E_exact_dat = dataObj{

   DECLARED_VARIABLES{ Y; INPUT }

   DATA_INPUT_VARIABLES{
      ID: {use is id}
      TIME: {use is idv}
      TRT: {use is covariate}
      AMT: {use is ignore }
      RATE: {use is rate}
      SS: {use is ss}
      ADDL: {use is addl}
      II: {use is ii}
      WT: {use is covariate}
      DVID: {use is dvid}
      DV: {use is dv, variable=Y}
      MDV: {use is mdv}
   }# end DATA_INPUT_VARIABLES

   SOURCE{
      srcFile : {file="warfarin_TTE_exact.csv",
      			inputFormat is nonmemFormat}
   }# end SOURCE
} # end data object
		'''.parse

		mcl.assertError(MdlPackage::eINSTANCE.attributeList,
			MdlValidator::DEPENDENT_USE_MISSING,
			"A data column of use 'amt' is required by this column definition with 'use is rate'.")
		mcl.assertError(MdlPackage::eINSTANCE.attributeList,
			MdlValidator::DEPENDENT_USE_MISSING,
			"A data column of use 'amt' is required by this column definition with 'use is ss'.")
		mcl.assertError(MdlPackage::eINSTANCE.attributeList,
			MdlValidator::DEPENDENT_USE_MISSING,
			"A data column of use 'amt' is required by this column definition with 'use is addl'.")
		mcl.assertError(MdlPackage::eINSTANCE.attributeList,
			MdlValidator::DEPENDENT_USE_MISSING,
			"A data column of use 'amt' is required by this column definition with 'use is ii'.")
	}

	@Test
	def void testInvalidDuplicateUseDefinitions(){
		val mcl = '''
warfarin_T2E_exact_dat = dataObj{

   DECLARED_VARIABLES{ Y; INPUT }

   DATA_INPUT_VARIABLES{
      ID: {use is id}
      ID2: {use is id}
      TIME: {use is idv}
      TIME2: {use is idv}
      TRT: {use is covariate}
      AMT: {use is amt, variable = INPUT }
      AMT2: {use is amt, variable = INPUT }
      RATE: {use is rate}
      RATE2: {use is rate}
      SS: {use is ss}
      SS2: {use is ss}
      ADDL: {use is addl}
      ADDL2: {use is addl}
      II: {use is ii}
      II2: {use is ii}
      WT: {use is covariate}
      DVID: {use is dvid}
      DVID2: {use is dvid}
      DV: {use is dv, variable=Y}
      DV2: {use is dv, variable=Y}
      MDV: {use is mdv}
      MDV2: {use is mdv}
   }# end DATA_INPUT_VARIABLES

   SOURCE{
      srcFile : {file="warfarin_TTE_exact.csv",
      			inputFormat is nonmemFormat}
   }# end SOURCE
} # end data object
		'''.parse

		mcl.assertError(MdlPackage::eINSTANCE.valuePair,
			MdlValidator::DUPLICATE_UNIQUE_USE_VALUE,
			"Only one column definition can have a 'use' attribute set to 'amt'.")
		mcl.assertError(MdlPackage::eINSTANCE.valuePair,
			MdlValidator::DUPLICATE_UNIQUE_USE_VALUE,
			"Only one column definition can have a 'use' attribute set to 'rate'.")
		mcl.assertError(MdlPackage::eINSTANCE.valuePair,
			MdlValidator::DUPLICATE_UNIQUE_USE_VALUE,
			"Only one column definition can have a 'use' attribute set to 'ii'.")
		mcl.assertError(MdlPackage::eINSTANCE.valuePair,
			MdlValidator::DUPLICATE_UNIQUE_USE_VALUE,
			"Only one column definition can have a 'use' attribute set to 'id'.")
		mcl.assertError(MdlPackage::eINSTANCE.valuePair,
			MdlValidator::DUPLICATE_UNIQUE_USE_VALUE,
			"Only one column definition can have a 'use' attribute set to 'idv'.")
		mcl.assertError(MdlPackage::eINSTANCE.valuePair,
			MdlValidator::DUPLICATE_UNIQUE_USE_VALUE,
			"Only one column definition can have a 'use' attribute set to 'ss'.")
		mcl.assertError(MdlPackage::eINSTANCE.valuePair,
			MdlValidator::DUPLICATE_UNIQUE_USE_VALUE,
			"Only one column definition can have a 'use' attribute set to 'addl'.")
		mcl.assertError(MdlPackage::eINSTANCE.valuePair,
			MdlValidator::DUPLICATE_UNIQUE_USE_VALUE,
			"Only one column definition can have a 'use' attribute set to 'dv'.")
		mcl.assertError(MdlPackage::eINSTANCE.valuePair,
			MdlValidator::DUPLICATE_UNIQUE_USE_VALUE,
			"Only one column definition can have a 'use' attribute set to 'dvid'.")
		mcl.assertError(MdlPackage::eINSTANCE.valuePair,
			MdlValidator::DUPLICATE_UNIQUE_USE_VALUE,
			"Only one column definition can have a 'use' attribute set to 'mdv'.")
	}

	@Test
	def void testValidNoSSWithAddlUseDefinitions(){
		val mcl = '''
warfarin_T2E_exact_dat = dataObj{

   DECLARED_VARIABLES{ Y::observation; INPUT::dosingTarget }

   DATA_INPUT_VARIABLES{
      ID: {use is id}
      TIME: {use is idv}
      TRT: {use is covariate}
      AMT: {use is amt, variable = INPUT }
      RATE: {use is rate}
      ADDL: {use is addl}
      II: {use is ii}
      WT: {use is covariate}
      DVID: {use is dvid}
      DV: {use is dv, variable=Y}
      MDV: {use is mdv}
   }# end DATA_INPUT_VARIABLES

   SOURCE{
      srcFile : {file="warfarin_TTE_exact.csv",
      			inputFormat is nonmemFormat}
   }# end SOURCE
} # end data object
		'''.parse

		mcl.assertNoErrors
	}

	@Test
	def void testInvalidNoIIWithAddlUseDefinitions(){
		val mcl = '''
warfarin_T2E_exact_dat = dataObj{

   DECLARED_VARIABLES{ Y; INPUT }

   DATA_INPUT_VARIABLES{
      ID: {use is id}
      TIME: {use is idv}
      TRT: {use is covariate}
      AMT: {use is amt, variable = INPUT }
      RATE: {use is rate}
      ADDL: {use is addl}
      WT: {use is covariate}
      DVID: {use is dvid}
      DV: {use is dv, variable=Y}
      MDV: {use is mdv}
   }# end DATA_INPUT_VARIABLES

   SOURCE{
      srcFile : {file="warfarin_TTE_exact.csv",
      			inputFormat is nonmemFormat}
   }# end SOURCE
} # end data object
		'''.parse

		mcl.assertError(MdlPackage::eINSTANCE.attributeList,
			MdlValidator::DEPENDENT_USE_MISSING,
			"A data column of use 'ii' is required by this column definition with 'use is addl'.")
	}

	@Ignore
	def void testInvalidNoAddWithIIUseDefinitions(){
		val mcl = '''
warfarin_T2E_exact_dat = dataObj{

   DECLARED_VARIABLES{ Y; INPUT }

   DATA_INPUT_VARIABLES{
      ID: {use is id}
      TIME: {use is idv}
      TRT: {use is covariate}
      AMT: {use is amt, variable = INPUT }
      RATE: {use is rate}
      II: {use is ii}
      WT: {use is covariate}
      DVID: {use is dvid}
      DV: {use is dv, variable=Y}
      MDV: {use is mdv}
   }# end DATA_INPUT_VARIABLES

   SOURCE{
      srcFile : {file="warfarin_TTE_exact.csv",
      			inputFormat is nonmemFormat}
   }# end SOURCE
} # end data object
		'''.parse

		mcl.assertError(MdlPackage::eINSTANCE.listDefinition,
			MdlValidator::DEPENDENT_USE_MISSING,
			"A data column of use 'addl' is required by this column definition with 'use is ii'.")
	}

	@Test
	def void testValidCovariateFixedEff(){
		val mcl = '''bar = mdlObj {
			IDV { T }
			
			COVARIATES{
				W
			}
			
			
			STRUCTURAL_PARAMETERS{
				BETA_W
			}
			
			VARIABILITY_LEVELS{
				ID : { level = 1, type is parameter}
			}
			
			RANDOM_VARIABLE_DEFINITION(level=ID){
				ETA ~ Normal1(mean=0, stdev=1)
			}
			INDIVIDUAL_VARIABLES{
				BETA_CL_WT : { type is linear, pop=1, fixEff=[{cov=W, coeff=BETA_W}], ranEff = ETA }
			}
			
			OBSERVATION{
				F = 1
				Y : { type is userDefined, prediction=F, value=F, weight=0 } 
			}
		}'''.parse
		
		mcl.assertNoErrors
	}

	@Test
	def void testInValidNotCovariateFixedEff(){
		val mcl = '''bar = mdlObj {
			IDV { T }
			
			COVARIATES{
			#	W
			}
			
			
			STRUCTURAL_PARAMETERS{
				BETA_W
				W
			}
			
			VARIABILITY_LEVELS{
				ID : { level = 1, type is parameter}
			}
			
			RANDOM_VARIABLE_DEFINITION(level=ID){
				ETA ~ Normal1(mean=0, stdev=1)
			}
			INDIVIDUAL_VARIABLES{
				BETA_CL_WT : { type is linear, pop=1, fixEff=[{cov=W, coeff=BETA_W}], ranEff = ETA }
			}
		}'''.parse
		
		mcl.assertError(MdlPackage::eINSTANCE.valuePair,
			MdlValidator::INCOMPATIBLE_VARIABLE_REF,
			"Attribute 'cov' expects a reference to a covariate. 'W' is not a covariate.")
	}

	@Test
	def void testInValidNotConstantCovariateFixedEff(){
		val mcl = '''bar = mdlObj {
			IDV { T }
			
			COVARIATES(type is idvDependent){
				W
			}
			
			
			STRUCTURAL_PARAMETERS{
				BETA_W
			}
			
			VARIABILITY_LEVELS{
				ID : { level = 1, type is parameter}
			}
			
			RANDOM_VARIABLE_DEFINITION(level=ID){
				ETA ~ Normal1(mean=0, stdev=1)
			}
			INDIVIDUAL_VARIABLES{
				BETA_CL_WT : { type is linear, pop=1, fixEff=[{cov=W, coeff=BETA_W}], ranEff = ETA }
			}
		}'''.parse
		
		mcl.assertError(MdlPackage::eINSTANCE.valuePair,
			MdlValidator::INCOMPATIBLE_VARIABLE_REF,
			"Attribute 'cov' expects a reference to a constant covariate. 'W' is not constant.")
	}

	@Test
	def void testValidNoCycle(){
		val mcl = '''bar = mdlObj {
			IDV { T }
			
			COVARIATES{
				W
			}
			
			
			STRUCTURAL_PARAMETERS{
				BETA_W
			}
			
			VARIABILITY_LEVELS{
				ID : { level = 1, type is parameter}
			}
			
			RANDOM_VARIABLE_DEFINITION(level=ID){
				ETA ~ Normal1(mean=0, stdev=1)
			}
			
			INDIVIDUAL_VARIABLES{
				BETA_CL_WT : { type is linear, pop=1, fixEff=[{cov=W, coeff=BETA_W}], ranEff = ETA }
			}
			
			OBSERVATION{
				F = 1
				Y : { type is userDefined, prediction=F, value=F, weight=0 } 
			}
		}'''.parse
		
		mcl.assertNoErrors
	}

	@Test
	def void testInValidCycleInFunction(){
		val mcl = '''bar = mdlObj {
			IDV { T }
			
			COVARIATES{
				W
			}
			
			
			STRUCTURAL_PARAMETERS{
				BETA_W = BETA_CL_WT
			}
			
			VARIABILITY_LEVELS{
				ID : { level = 1, type is parameter}
			}
			
			RANDOM_VARIABLE_DEFINITION(level=ID){
				ETA ~ Normal1(mean=0, stdev=1)
			}
			INDIVIDUAL_VARIABLES{
				BETA_CL_WT : { type is linear, pop=1, fixEff=[{cov=W, coeff=BETA_W}], ranEff = ETA }
			}
		}'''.parse
		
		mcl.assertError(MdlLibPackage::eINSTANCE.symbolDefinition,
			MdlValidator::INVALID_CYCLE,
			"Symbol 'BETA_W' contains an expression that refers to itself.")
		mcl.assertError(MdlLibPackage::eINSTANCE.symbolDefinition,
			MdlValidator::INVALID_CYCLE,
			"Symbol 'BETA_CL_WT' contains an expression that refers to itself.")
	}

	@Test
	def void testInValidnIndex(){
		val mcl = '''bar = mdlObj {
			IDV { T }
			
			COVARIATES{
				W
			}
			
			
			STRUCTURAL_PARAMETERS{
				BETA_W = BETA_CL_WT
			}
			
			VARIABILITY_LEVELS{
				ID : { level = 1, type is parameter}
			}
			
			MODEL_PREDICTION{
				a = X
				V = [1.0, 2.0]
				X = V[a]
			}
		}'''.parse
		
		mcl.assertError(MdlLibPackage::eINSTANCE.symbolDefinition,
			MdlValidator::INVALID_CYCLE,
			"Symbol 'a' contains an expression that refers to itself.")
		mcl.assertError(MdlLibPackage::eINSTANCE.symbolDefinition,
			MdlValidator::INVALID_CYCLE,
			"Symbol 'X' contains an expression that refers to itself.")
	}

	@Test
	def void testInValidInVectorRange(){
		val mcl = '''bar = mdlObj {
			IDV { T }
			
			COVARIATES{
				W
			}
			
			
			STRUCTURAL_PARAMETERS{
				BETA_W = BETA_CL_WT
			}
			
			VARIABILITY_LEVELS{
				ID : { level = 1, type is parameter}
			}
			
			MODEL_PREDICTION{
				a = X[1]
				V = [1.0, 2.0]
				X = V[a:2]
			}
		}'''.parse
		
		mcl.assertError(MdlLibPackage::eINSTANCE.symbolDefinition,
			MdlValidator::INVALID_CYCLE,
			"Symbol 'a' contains an expression that refers to itself.")
		mcl.assertError(MdlLibPackage::eINSTANCE.symbolDefinition,
			MdlValidator::INVALID_CYCLE,
			"Symbol 'X' contains an expression that refers to itself.")
	}

	@Test
	def void testInValidInMatrixIndex(){
		val mcl = '''bar = mdlObj {
			IDV { T }
			
			COVARIATES{
				W
			}
			
			
			STRUCTURAL_PARAMETERS{
				BETA_W = BETA_CL_WT
			}
			
			VARIABILITY_LEVELS{
				ID : { level = 1, type is parameter}
			}
			
			MODEL_PREDICTION{
				a = X
				V = [[1.0, 2.0;3.0, 4.0]]
				X = V[a,2]
			}
		}'''.parse
		
		mcl.assertError(MdlLibPackage::eINSTANCE.symbolDefinition,
			MdlValidator::INVALID_CYCLE,
			"Symbol 'a' contains an expression that refers to itself.")
		mcl.assertError(MdlLibPackage::eINSTANCE.symbolDefinition,
			MdlValidator::INVALID_CYCLE,
			"Symbol 'X' contains an expression that refers to itself.")
	}

	@Test
	def void testInValidCycleInExpr(){
		val mcl = '''bar = mdlObj {
			IDV { T }
			
			COVARIATES{
				W
			}
			
			
			STRUCTURAL_PARAMETERS{
				BETA_W
			}
			
			VARIABILITY_LEVELS{
				ID : { level = 1, type is parameter}
			}
			
			INDIVIDUAL_VARIABLES{
				BETA_CL_WT = 1 + 4 * BETA_CL_WT
			}
		}'''.parse
		
		mcl.assertError(MdlLibPackage::eINSTANCE.symbolDefinition,
			MdlValidator::INVALID_CYCLE,
			"Symbol 'BETA_CL_WT' contains an expression that refers to itself.")
	}

	@Test
	def void testInValidCycleInList(){
		val mcl = '''bar = mdlObj {
			IDV { T }
			
			COVARIATES{
				W
			}
			
			
			STRUCTURAL_PARAMETERS{
				BETA_W
			}
			
			VARIABILITY_LEVELS{
				ID : { level = 1, type is parameter}
			}
			
			INDIVIDUAL_VARIABLES{
				BETA_CL_WT = 1 + 4 * Y
			}
			
			OBSERVATION{
				Y : { type is tte, hazard = BETA_CL_WT }
			}
			
		}'''.parse
		
		mcl.assertError(MdlLibPackage::eINSTANCE.symbolDefinition,
			MdlValidator::INVALID_CYCLE,
			"Symbol 'BETA_CL_WT' contains an expression that refers to itself.")
		mcl.assertError(MdlLibPackage::eINSTANCE.symbolDefinition,
			MdlValidator::INVALID_CYCLE,
			"Symbol 'Y' contains an expression that refers to itself.")
	}

	@Test
	def void testValidCycleInDeriv(){
		val mcl = '''bar = mdlObj {
			IDV { T }
			
			COVARIATES{
				W
			}
			
			
			STRUCTURAL_PARAMETERS{
				BETA_W
			}
			
			VARIABILITY_LEVELS{
				ID : { level = 1, type is parameter}
			}
			
			INDIVIDUAL_VARIABLES{
				BETA_CL_WT = 1 + 4 * Y
			}
			
			MODEL_PREDICTION{
				Y : { deriv = -BETA_CL_WT }
				RATE : { deriv = -RATE }
			}
			
			OBSERVATION{
				F = 1
				Z : { type is userDefined, prediction=F, value=F, weight=0 } 
			}
		}'''.parse
		
		mcl.assertNoErrors
	}

	@Test
	def void testValidVariabilityLevelDefinition1(){
		val mcl = '''bar = mdlObj {
			IDV { T }
			
			VARIABILITY_LEVELS{
				ID : { level = 1, type is parameter}
			}
			
			OBSERVATION{
				F = 1
				Y : { type is userDefined, prediction=F, value=F, weight=0 } 
			}
		}'''.parse
		
		mcl.assertNoErrors
	}

	@Test
	def void testValidVariabilityLevelDefinition2(){
		val mcl = '''bar = mdlObj {
			IDV { T }
			
			VARIABILITY_LEVELS{
				a : { level = 2, type is parameter}
				b : { level = 1, type is observation}
			}
			
			OBSERVATION{
				F = 1
				Y : { type is userDefined, prediction=F, value=F, weight=0 } 
			}
		}'''.parse
		
		mcl.assertNoErrors
	}

	@Test
	def void testValidVariabilityLevelDefinition3(){
		val mcl = '''bar = mdlObj {
			IDV { T }
			
			VARIABILITY_LEVELS{
				a : { level = 3, type is parameter}
				b : { level = 2, type is parameter}
				c : { level = 1, type is observation}
			}
			
			OBSERVATION{
				F = 1
				Y : { type is userDefined, prediction=F, value=F, weight=0 } 
			}
		}'''.parse
		
		mcl.assertNoErrors
	}

	@Test
	def void testValidVariabilityLevelDefinition4(){
		val mcl = '''bar = mdlObj {
			IDV { T }
			
			VARIABILITY_LEVELS{
				a : { level = 13, type is parameter}
				b : { level = 21, type is parameter}
				c : { level = 1, type is observation}
			}
			
			OBSERVATION{
				F = 1
				Y : { type is userDefined, prediction=F, value=F, weight=0 } 
			}
		}'''.parse
		
		mcl.assertNoErrors
	}

	@Test
	def void testValidVariabilityLevelDefinition5(){
		val mcl = '''bar = mdlObj {
			IDV { T }
			
			VARIABILITY_LEVELS{
				c : { level = 1, type is observation}
			}
			
			OBSERVATION{
				F = 1
				Y : { type is userDefined, prediction=F, value=F, weight=0 } 
			}
		}'''.parse
		
		mcl.assertNoErrors
	}

	@Test
	def void testInvalidVariabilityLevelDefinition1(){
		val mcl = '''bar = mdlObj {
			IDV { T }
			
			VARIABILITY_LEVELS{
				a : { level = 1, type is parameter}
				b : { level = 2, type is observation}
			}
			
			OBSERVATION{
				F = 1
				Y : { type is userDefined, prediction=F, value=F, weight=0 } 
			}
		}'''.parse
		
		mcl.assertError(MdlLibPackage::eINSTANCE.symbolDefinition,
			MdlValidator::VARIABILITY_LEVELS_MALFORMED,
			"Variability Level definition 'a': an observation level is present so the level cannot be 1.")
	}

	@Test
	def void testInvalidVariabilityLevelDefinition2(){
		val mcl = '''bar = mdlObj {
			IDV { T }
			
			VARIABILITY_LEVELS{
				a : { level = 2, type is parameter}
				b : { level = 2, type is parameter}
				c : { level = 1, type is observation}
			}
			
			OBSERVATION{
				F = 1
				Y : { type is userDefined, prediction=F, value=F, weight=0 } 
			}
		}'''.parse
		
		mcl.assertError(MdlLibPackage::eINSTANCE.symbolDefinition,
			MdlValidator::VARIABILITY_LEVELS_MALFORMED,
			"Variability Level definition 'a': the level value of 2 is used by another definition.")
		mcl.assertError(MdlLibPackage::eINSTANCE.symbolDefinition,
			MdlValidator::VARIABILITY_LEVELS_MALFORMED,
			"Variability Level definition 'b': the level value of 2 is used by another definition.")
	}

	@Test
	def void testInvalidVariabilityLevelDefinition3(){
		val mcl = '''bar = mdlObj {
			IDV { T }
			
			VARIABILITY_LEVELS{
				a : { level = -2, type is parameter}
				b : { level = 0, type is parameter}
				c : { level = 1, type is observation}
			}
		}'''.parse
		
		mcl.assertError(MdlLibPackage::eINSTANCE.symbolDefinition,
			MdlValidator::VARIABILITY_LEVELS_MALFORMED,
			"Variability Level definition 'a': level cannot be less than 1.")
		mcl.assertError(MdlLibPackage::eINSTANCE.symbolDefinition,
			MdlValidator::VARIABILITY_LEVELS_MALFORMED,
			"Variability Level definition 'b': level cannot be less than 1.")
	}


	@Test
	def void testInvalidVariabilityLevelDefinition4(){
		val mcl = '''bar = mdlObj {
			IDV { T }
			
			VARIABILITY_LEVELS{
				a : { level = 3, type is parameter}
				b : { level = 1, type is parameter}
				c : { level = 2, type is observation}
			}
		}'''.parse
		
		mcl.assertError(MdlLibPackage::eINSTANCE.symbolDefinition,
			MdlValidator::VARIABILITY_LEVELS_MALFORMED,
			"Variability Level definition 'b': an observation level is present so the level cannot be 1.")
		mcl.assertError(MdlLibPackage::eINSTANCE.symbolDefinition,
			MdlValidator::VARIABILITY_LEVELS_MALFORMED,
			"Variability Level definition 'c': this is an observation level and so should have a level value of 1.")
	}

	@Test
	def void testValidVariabilityLevelDefinition6(){
		val mcl = '''bar = mdlObj {
			IDV { T }
			
			VARIABILITY_LEVELS{
				a : { level = 2, type is parameter}
			}
			
			OBSERVATION{
				F = 1
				Y : { type is userDefined, prediction=F, value=F, weight=0 } 
			}
		}'''.parse
		
		mcl.assertNoErrors
	}

	@Test
	def void testInvalidVariabilityLevelDefinition6(){
		val mcl = '''bar = mdlObj {
			IDV { T }
			
			VARIABILITY_LEVELS{
				a : { level = 4, type is parameter}
				b : { level = 3, type is parameter}
				c : { level = 1, type is observation}
				d : { level = 2, type is observation}
			}
		}'''.parse
		
		mcl.assertError(MdlLibPackage::eINSTANCE.symbolDefinition,
			MdlValidator::VARIABILITY_LEVELS_MALFORMED,
			"Variability Level definition 'c': an observation definition already exists. There can be only one.")
		mcl.assertError(MdlLibPackage::eINSTANCE.symbolDefinition,
			MdlValidator::VARIABILITY_LEVELS_MALFORMED,
			"Variability Level definition 'd': an observation definition already exists. There can be only one.")
		mcl.assertError(MdlLibPackage::eINSTANCE.symbolDefinition,
			MdlValidator::VARIABILITY_LEVELS_MALFORMED,
			"Variability Level definition 'd': this is an observation level and so should have a level value of 1.")
	}

	@Test
	def void testUseReservedVariableAsPrefix(){
		val mcl = '''bar = mdlObj {
			IDV { T }
			
			VARIABILITY_LEVELS{
				a : { level = 2, type is parameter}
				b : { level = 1, type is observation}
			}
			MODEL_PREDICTION{
				MDL__foo = 1
			}
		}'''.parse
		
		mcl.assertError(MdlLibPackage::eINSTANCE.symbolDefinition,
			MdlValidator::RESERVED_PREFIX_USED,
			"Variable names starting with 'MDL__' are reserved for internal use.")
	}

	@Test
	def void testUseReservedVariableAsWord(){
		val mcl = '''bar = mdlObj {
			IDV { T }
			
			VARIABILITY_LEVELS{
				a : { level = 2, type is parameter}
				b : { level = 1, type is observation}
			}
			MODEL_PREDICTION{
				MDL__ = 1
			}
		}'''.parse
		
		mcl.assertError(MdlLibPackage::eINSTANCE.symbolDefinition,
			MdlValidator::RESERVED_PREFIX_USED,
			"Variable names starting with 'MDL__' are reserved for internal use.")
	}

	@Test
	def void testNotUseReservedVariablePrefix(){
		val mcl = '''bar = mdlObj {
			IDV { T }
			
			VARIABILITY_LEVELS{
				a : { level = 2, type is parameter}
				b : { level = 1, type is observation}
			}
			
			MODEL_PREDICTION{
				MDL_ = 1
			}
			
			
			OBSERVATION{
				F = 1
				Y : { type is userDefined, prediction=F, value=F, weight=0 } 
			}
		}'''.parse
		
		mcl.assertNoErrors
	}

	@Ignore
	def void testUseReservedWord(){
		val mcl = '''bar = mdlObj {
			IDV { T }
			
			VARIABILITY_LEVELS{
				a : { level = 2, type is parameter}
				b : { level = 1, type is observation}
			}
			MODEL_PREDICTION{
				ordered = 1
			}
		}'''.parse
		
		mcl.assertError(MdlLibPackage::eINSTANCE.symbolDefinition,
			MdlValidator::RESERVED_WORD_USED,
			"The keyword 'ordered' is reserved for future use in MDL.")
	}

	@Ignore
	def void testUseReservedWordInCategories(){
		val mcl = '''bar = mdlObj {
			IDV { T }
			
			VARIABILITY_LEVELS{
				a : { level = 2, type is parameter}
				b : { level = 1, type is observation}
			}
			COVARIATES{
				c withCategories { ordered, not }
			}
		}'''.parse
		
		mcl.assertError(MdlPackage::eINSTANCE.categoryValueDefinition,
			MdlValidator::RESERVED_WORD_USED,
			"The keyword 'ordered' is reserved for future use in MDL.")
	}

	@Ignore
	def void testUseReservedWord2(){
		val mcl = '''bar = mdlObj {
			IDV { T }
			
			VARIABILITY_LEVELS{
				a : { level = 2, type is parameter}
				b : { level = 1, type is observation}
			}
			MODEL_PREDICTION{
				withOrderedCategories = 1
			}
		}'''.parse
		
		mcl.assertError(MdlLibPackage::eINSTANCE.symbolDefinition,
			MdlValidator::RESERVED_WORD_USED,
			"The keyword 'withOrderedCategories' is reserved for future use in MDL.")
	}

	@Ignore
	def void testUseReservedWordInCategories2(){
		val mcl = '''bar = mdlObj {
			IDV { T }
			
			VARIABILITY_LEVELS{
				a : { level = 2, type is parameter}
				b : { level = 1, type is observation}
			}
			COVARIATES{
				c withCategories { withOrderedCategories, not }
			}
		}'''.parse
		
		mcl.assertError(MdlPackage::eINSTANCE.categoryValueDefinition,
			MdlValidator::RESERVED_WORD_USED,
			"The keyword 'withOrderedCategories' is reserved for future use in MDL.")
	}

	@Ignore
	def void testUseReservedWordInEnumList(){
		val mcl = '''
warfarin_T2E_exact_dat = dataObj{

   DECLARED_VARIABLES{ Y; INPUT }

   DATA_INPUT_VARIABLES{
      ID: {use is id}
      TIME: {use is idv}
      TRT: {use is cotCov withCategories { ordered, other }}
   }# end DATA_INPUT_VARIABLES

   SOURCE{
      srcFile : {file="warfarin_TTE_exact.csv",
      			inputFormat is nonmemFormat}
   }# end SOURCE
} # end data object
		'''.parse
		
		mcl.assertError(MdlPackage::eINSTANCE.categoryValueDefinition,
			MdlValidator::RESERVED_WORD_USED,
			"The keyword 'ordered' is reserved for future use in MDL.")
	}

	@Test
	def void testRandomVariableUsed(){
		val mcl = '''bar = mdlObj {
			IDV { T }
			
			VARIABILITY_LEVELS{
				a : { level = 2, type is parameter}
			}
			RANDOM_VARIABLE_DEFINITION(level=a){
				ETA ~ Normal1(mean=0, stdev=1)
			}
			
			INDIVIDUAL_VARIABLES{
				:: { type is rv, variable=ETA }
			}	
			
			OBSERVATION{
				F = 1
				Y : { type is additiveError, prediction=F, additive=0, eps=ETA } 
			}
		}'''.parse
		
		mcl.assertNoIssues
	}

	@Test
	def void testRandomVariableUnUsed(){
		val mcl = '''bar = mdlObj {
			IDV { T }
			
			VARIABILITY_LEVELS{
				a : { level = 2, type is parameter}
			}
			RANDOM_VARIABLE_DEFINITION(level=a){
				ETA ~ Normal1(mean=0, stdev=1)
			}
			
			OBSERVATION{
				F = 1
				Y : { type is userDefined, prediction=F, value=F, weight=0 } 
			}
		}'''.parse
		
		mcl.assertNoErrors
		mcl.assertWarning(MdlLibPackage::eINSTANCE.symbolDefinition,
							MdlValidator::UNUSED_VARIABLE, "Random Variable 'ETA' is not used and may be omitted from a generated model.")
	}

	@Test
	def void testObservationDoesNotContainObsDefn(){
		val mcl = '''bar = mdlObj {
			IDV { T }
			
			VARIABILITY_LEVELS{
				a : { level = 2, type is parameter}
			}
			RANDOM_VARIABLE_DEFINITION(level=a){
				ETA ~ Normal1(mean=0, stdev=1)
			}
			
			OBSERVATION{
				X = ETA
			}
			
			
		}'''.parse
		
		mcl.assertError(MdlPackage::eINSTANCE.blockStatement,
							MdlValidator::OBS_MISSING, "Observation block must contain at least one observation.")
	}

	@Test
	def void testInvalidMogCompartmentVarNotInitialisedInData(){
		val mcl = '''
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
				      CENTRAL:    {type is compartment, modelCmt=2}
				             ::   {type is elimination, modelCmt=2, from=CENTRAL, v=1, cl=1}
			         }
				}
			
			OBSERVATION{
				F = 1
				Y : { type is userDefined, prediction=F, value=F, weight=0 } 
			}
		
		}
		'''.parse
	
		mcl.assertError(MdlLibPackage::eINSTANCE.symbolDefinition,
			MdlValidator::SYMBOL_NOT_INITIALISED,
			"compartment macro 'CENTRAL' is not initialised.")
	}
	
	@Test
	def void testValidUnusedObsDeclaration(){
		val mcl = '''
		d1 = designObj{
		  DECLARED_VARIABLES{
		  	INPUT_KA :: dosingTarget
		  	Y :: observation
		  	Y1 :: observation
		  }
		  INTERVENTION{
		 	admin1 : {type is bolus, input=INPUT_KA, amount=100, doseTime=[0] }
		  }
		  SAMPLING{
			winPK : {type is simple, outcome=Y, sampleTime = [0.0001, 24, 36, 48, 72, 96, 120] }
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
		'''.parse
	
		mcl.assertError(MdlLibPackage::eINSTANCE.symbolDefinition,
			MdlValidator::UNUSED_VARIABLE,
			"Declared variable 'Y1' must be used within the object.")
	}
	
	@Test
	def void testInvalidUnusedVarLvlDeclaration(){
		val mcl = '''
		d1 = designObj{
		  DECLARED_VARIABLES{
		  	INPUT_KA :: dosingTarget
		  	Y :: observation
		  	Y1 :: varLevel
		  }
		  INTERVENTION{
		 	admin1 : {type is bolus, input=INPUT_KA, amount=100, doseTime=[0] }
		  }
		  SAMPLING{
			winPK : {type is simple, outcome=Y, sampleTime = [0.0001, 24, 36, 48, 72, 96, 120] }
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
		'''.parse
	
		mcl.assertError(MdlLibPackage::eINSTANCE.symbolDefinition,
			MdlValidator::UNUSED_VARIABLE,
			"Declared variable 'Y1' must be used within the object.")
	}
}