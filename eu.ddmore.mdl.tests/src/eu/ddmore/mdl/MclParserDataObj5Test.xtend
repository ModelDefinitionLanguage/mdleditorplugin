package eu.ddmore.mdl

import com.google.inject.Inject
import eu.ddmore.mdl.mdl.Mcl
import eu.ddmore.mdl.mdl.MdlPackage
import eu.ddmore.mdl.validation.MdlValidator
import org.eclipse.xtext.junit4.InjectWith
import org.eclipse.xtext.junit4.XtextRunner
import org.eclipse.xtext.junit4.validation.ValidationTestHelper
import org.junit.Test
import org.junit.runner.RunWith

@RunWith(typeof(XtextRunner))
@InjectWith(typeof(MdlAndLibInjectorProvider))
class MclParserDataObj5Test {
	@Inject extension MdlTestHelper<Mcl>
	@Inject extension ValidationTestHelper
	
	val static CODE_SNIPPET = '''
warfarin_PK_v2_dat = dataObj{
	DECLARED_VARIABLES{ Y::observation; GUT::dosingTarget; PCA withCategories {dead, alive}; OTHER withCategories {dead, alive} }
	
	DATA_INPUT_VARIABLES {
		ID : { use is id }
		TIME : { use is idv }
		WT : { use is covariate }
		AGE : { use is covariate }
		SEX : { use is catCov withCategories { male when 0, female when 1,MISSING when -99} } 
		AMT : { use  is amt, variable = GUT }
		OCC : { use is varLevel }
		DVID : { use  is dvid }
		PK : { use is covariate }
		DV : { use  is dv, variable = Y  }
		MDV : { use  is mdv}
	}

	DATA_DERIVED_VARIABLES{
		OCCCOV : { use is catCov withCategories { treatAB when 1, treatBA when 2 }, column=OCC }
		DI : { use is doseInterval, dosingVar = GUT, idvColumn=TIME }
		AMTCOV : { use is covariate, column = AMT }
		PKVAR : { use is variable, column = PK }
	}
	
	SOURCE {
	    SrcFile : { file="warfarin_conc_sex.csv", inputFormat  is nonmemFormat } 
	} # end SOURCE
} # end data object
		'''
	
	@Test
	def void testParsing(){
		CODE_SNIPPET.parse.assertNoErrors
		
	}

	@Test
	def void testInvalidDvidAttribs(){
		val mcl = '''
warfarin_PK_v2_dat = dataObj{
	DECLARED_VARIABLES{ Y; D; TD; GUT; PCA}
	
	DATA_INPUT_VARIABLES {
		DVID : { use  is dvid, define= { 1 as Y, 2 as PCA } }
	}

	SOURCE {
	    SrcFile : { file="warfarin_conc_sex.csv", inputformat  is nonmemFormat } 
	} # end SOURCE
} # end data object
		'''.parse

		mcl.assertError(MdlPackage::eINSTANCE.valuePair,
			MdlValidator::UNRECOGNIZED_LIST_ATT,
			"attribute 'define' is not recognised in this context"
		)
		
	}
}