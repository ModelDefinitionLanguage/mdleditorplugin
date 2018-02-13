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
class MclParserMogObj1Test {
	@Inject extension MdlTestHelper<Mcl>
	@Inject extension ValidationTestHelper
	
	val static CODE_SNIPPET = '''
mobj = mdlObj {
	IDV{T}
	VARIABILITY_LEVELS{
	} 
			OBSERVATION{
				F = 1
				Y : { type is userDefined, prediction=F, value=F, weight=0 } 
			}
}

pobj = parObj {
}

dobj = dataObj {
	DECLARED_VARIABLES{ Y::observation }
	DATA_INPUT_VARIABLES{
		foo : { use is idv }
		DV : { use is dv, variable = Y }
	}
	SOURCE{  SrcFile : { file="warfarin_conc_sex.csv", inputFormat  is nonmemFormat } }
}

tobj = taskObj {
}

mgobj = mogObj {
	OBJECTS{
		mobj : { type is mdlObj }
		pobj : { type is parObj }
		dobj : { type is dataObj }
		tobj : { type is taskObj }
	}
}
		'''
	
	@Test
	def void testParsing(){
		CODE_SNIPPET.parse.assertNoErrors
		
	}
	
}