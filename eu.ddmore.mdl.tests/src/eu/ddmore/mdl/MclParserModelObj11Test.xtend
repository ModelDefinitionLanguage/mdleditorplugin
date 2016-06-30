package eu.ddmore.mdl

import com.google.inject.Inject
import eu.ddmore.mdl.mdl.Mcl
import org.eclipse.xtext.diagnostics.Diagnostic
import org.eclipse.xtext.junit4.InjectWith
import org.eclipse.xtext.junit4.XtextRunner
import org.eclipse.xtext.junit4.validation.ValidationTestHelper
import org.junit.Test
import org.junit.runner.RunWith

@RunWith(typeof(XtextRunner))
@InjectWith(typeof(MdlAndLibInjectorProvider))
class MclParserModelObj11Test {
	@Inject extension MdlTestHelper<Mcl>
	@Inject extension ValidationTestHelper
	
	val static CODE_SNIPPET = '''
warfarin_PK_ODE_mdl = mdlObj {
	IDV{ T }
	
	FUNCTIONS{
	   	# define a function. The return type of the function is given by it's name.
	   	# In this case it is a real. If it were a vector or matric it would use [] or [[]] 
		userFunc::function(arg1::int, arg2::real, arg3::string)::real is
			# the function can contain only a single expression
		    arg2 * arg1  # return type is Real
	}

   MODEL_PREDICTION{
	  P1 = userFunc(0, 1.2, "A")
   }# end MODEL_PREDICTION
	OBSERVATION{
		F = 1
		Y : { type is userDefined, prediction=F, value=F, weight=0 } 
	}
} # end of model object
		'''
	
	@Test
	def void testParsing(){
		val mdl = CODE_SNIPPET.parse
		mdl.assertNoErrors(Diagnostic::SYNTAX_DIAGNOSTIC)
	}
	
	@Test
	def void testValidity(){
		val mdl = CODE_SNIPPET.parse
		mdl.assertNoErrors
	}
	
//	@Test
//	// needed to stop initialisation failure
//	def void testDummy(){
//	}
}