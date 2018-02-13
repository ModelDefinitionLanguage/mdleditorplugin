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
class MclParserDataObj2Test {
	@Inject extension MdlTestHelper<Mcl>
	@Inject extension ValidationTestHelper
	
	val static CODE_SNIPPET = '''
warfarin_PK_v2_dat = dataObj{
	DECLARED_VARIABLES{ Y::observation; GUT::dosingTarget }
	
	DATA_INPUT_VARIABLES {
		ID : { use is id }
		TIME : { use is idv }
		WT : { use is covariate }
		AGE : { use is covariate  }
		SEX : { use is catCov withCategories {male when 0, female when 1} }
		CMT : { use is cmt } 
		AMT : { use  is amt , define={ 1 in CMT as GUT } }
		DVID : { use  is dvid }
		DV : { use  is dv, variable = (Y) }
		MDV : { use  is mdv}
	}

	DATA_DERIVED_VARIABLES{
		DT : { use is doseTime, idvColumn=TIME, dosingVar=GUT }
	}
	
	SOURCE {
	    foo: {file = "warfarin_conc_sex.csv",
        	inputFormat  is nonmemFormat} 
#		header = true  # or false
#		skip =  0  ##  << integer >> Skips number of rows before header / data
	} # end SOURCE
} # end data object
		'''
	
	@Test
	def void testParsing(){
		CODE_SNIPPET.parse.assertNoErrors
		
	}

	
}