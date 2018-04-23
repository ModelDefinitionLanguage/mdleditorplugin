/*
 * generated by Xtext
 */
package eu.ddmore.mdl.tests

import com.google.inject.Inject
import eu.ddmore.mdl.mdl.Mcl
import org.eclipse.xtext.testing.InjectWith
import org.eclipse.xtext.testing.XtextRunner
import org.eclipse.xtext.testing.util.ParseHelper
import org.junit.Assert
import org.junit.Test
import org.junit.runner.RunWith

/**
 * Class is auto-generated if absent so putting a test here that will work.  Duplicate of another at the moment,
 * may make it useful in the future.  
 */

@RunWith(XtextRunner)
@InjectWith(MdlInjectorProvider)
class MdlParsingTest {
	@Inject
	ParseHelper<Mcl> parseHelper
	
	@Test
	def void loadModel() {
		val result = parseHelper.parse('''
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
		''')
		Assert.assertNotNull(result)
		Assert.assertTrue(result.eResource.errors.isEmpty)
	}
}