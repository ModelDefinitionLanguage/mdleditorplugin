package eu.ddmore.mdl.tests

import com.google.inject.Inject
import eu.ddmore.mdl.mdl.Mcl
import eu.ddmore.mdl.mdl.MdlPackage
import eu.ddmore.mdl.validation.MdlValidator
import org.eclipse.xtext.testing.InjectWith
import org.eclipse.xtext.testing.XtextRunner
import org.eclipse.xtext.testing.validation.ValidationTestHelper
import org.junit.Test
import org.junit.runner.RunWith

@RunWith(typeof(XtextRunner))
@InjectWith(typeof(MdlAndLibInjectorProvider))
class MclParserDataObj4Test {
	@Inject extension MdlTestHelper<Mcl>
	@Inject extension ValidationTestHelper
	
	val static CODE_SNIPPET = '''
warfarin_PK_v2_dat = dataObj{
	DECLARED_VARIABLES{ Y::observation; GUT::dosingTarget; PCA withCategories {dead, alive}; OTHER withCategories {dead, alive} }
	
	FUNCTIONS{
		myInterp::function(t::real, x0::real, t0::real, x1::real, t1::real)::real is
				    x0
	}
	
	DATA_INPUT_VARIABLES {
		ID : { use is id }
		TIME : { use is idv }
		WT : { use is covariate, interp=&constInterp }
		AGE : { use is covariate, interp=&myInterp }
		SEX : { use is catCov withCategories { male when 0, female when 1,MISSING when -99} } 
		AMT : { use  is amt, variable = GUT }
		DVID : { use  is dvid }
		DV : { use  is dv, define =  {
					1 in DVID as Y,
					2 in DVID as { PCA.dead when 1, PCA.alive when 2},
					3 in DVID as { OTHER.dead when 1, OTHER.alive when 2}
				}
			  }
		MDV : { use  is mdv}
	}

	DATA_DERIVED_VARIABLES{
		DT : { use is doseTime, idvColumn=TIME, dosingVar=GUT }
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