package eu.ddmore.mdl.validation

import com.google.inject.Inject
import eu.ddmore.mdl.tests.LibraryTestHelper
import eu.ddmore.mdl.tests.MdlAndLibInjectorProvider
import eu.ddmore.mdl.mdl.Mcl
import eu.ddmore.mdl.mdl.MdlPackage
import org.eclipse.xtext.diagnostics.Diagnostic
import org.eclipse.xtext.testing.InjectWith
import org.eclipse.xtext.testing.XtextRunner
import org.eclipse.xtext.testing.validation.ValidationTestHelper
import org.junit.Test
import org.junit.runner.RunWith
import eu.ddmore.mdllib.mdllib.MdlLibPackage
import org.junit.Ignore

@RunWith(typeof(XtextRunner))
@InjectWith(typeof(MdlAndLibInjectorProvider))
class UserFunctionValidationTest {
	@Inject extension LibraryTestHelper<Mcl>
	@Inject extension ValidationTestHelper
	

	@Test
	def void testInvalidTypeMismatchInFuncBody(){
		val mcl = '''
warfarin_PK_ODE_mdl = mdlObj {
	IDV{ T }
	
	FUNCTIONS{
	   	# define a function. The return type of the function is given by it's name.
	   	# In this case it is a real. If it were a vector or matric it would use [] or [[]] 
		userFunc::function(arg1::int, arg2::real, arg3::string)::real is
			# the function can contain only a single expression
		    arg2 * arg1 + arg3 # return type is Real
	}

} # end of model object
'''.parse
		
		mcl.assertError(MdlPackage::eINSTANCE.additiveExpression,
			MdlValidator::INCOMPATIBLE_TYPES,
			"Expected Real type, but was ref:String.")
	}
	
	@Test
	def void testInvalidDangingRefInFuncBody(){
		val mdl = '''
warfarin_PK_ODE_mdl = mdlObj {
	IDV{ T }
	
	FUNCTIONS{
	   	# define a function. The return type of the function is given by it's name.
	   	# In this case it is a real. If it were a vector or matric it would use [] or [[]] 
		userFunc::function(arg1::int, arg2::real, arg3::string)::real is
			# the function can contain only a single expression
		    arg2 * arg1 + arg4 # return type is Real
	}

} # end of model object
'''.parse
		
		mdl.assertNoErrors(Diagnostic::SYNTAX_DIAGNOSTIC)
		mdl.assertError(MdlPackage::eINSTANCE.symbolReference, Diagnostic::LINKING_DIAGNOSTIC)
	}
	
	@Ignore("Not supported yet")
	def void testInvalidRefenceVarOutsideFunction(){
		val mdl = '''
warfarin_PK_ODE_mdl = mdlObj {
	IDV{ T }
	
	FUNCTIONS{
	   	# define a function. The return type of the function is given by it's name.
	   	# In this case it is a real. If it were a vector or matric it would use [] or [[]] 
		userFunc::function(arg1::int, arg2::real, arg3::string)::real is
			# the function can contain only a single expression
		    arg2 * arg1 + foo # return type is Real
	}
	
	MODEL_PREDICTION{
		foo::real
	}

} # end of model object
'''.parse
		
		mdl.assertNoErrors(Diagnostic::SYNTAX_DIAGNOSTIC)
		mdl.assertError(MdlPackage::eINSTANCE.symbolReference, Diagnostic::LINKING_DIAGNOSTIC)
	}
	
	@Test
	def void testInvalidReturnAndExpressionTypesInconsistent(){
		val mdl = '''
warfarin_PK_ODE_mdl = mdlObj {
	IDV{ T }
	
	FUNCTIONS{
	   	# define a function. The return type of the function is given by it's name.
	   	# In this case it is a real. If it were a vector or matric it would use [] or [[]] 
		userFunc::function(arg1::int, arg2::real, arg3::string)::real is
			# the function can contain only a single expression
		    arg3 # return type is Real
	}
} # end of model object
'''.parse
		
		mdl.assertNoErrors(Diagnostic::SYNTAX_DIAGNOSTIC)
		mdl.assertError(MdlLibPackage::eINSTANCE.functionSpec,
			MdlValidator::INCOMPATIBLE_TYPES,
			"Expected function return type of 'Real' but expression was of type 'ref:String'.")
	}
	
	@Test
	def void testValidReturnTypeHonoursIntegerPromotion(){
		val mdl = '''
warfarin_PK_ODE_mdl = mdlObj {
	IDV{ T }
	
	FUNCTIONS{
	   	# define a function. The return type of the function is given by it's name.
	   	# In this case it is a real. If it were a vector or matric it would use [] or [[]] 
		userFunc::function(arg1::int, arg2::real, arg3::string)::real is
			# the function can contain only a single expression
		    arg1
	}
			OBSERVATION{
				F = 1
				Y : { type is userDefined, prediction=F, value=F, weight=0 } 
			}
} # end of model object
'''.parse
		
		mdl.assertNoErrors
	}
	
	@Test
	def void testValidReturnsMatrix(){
		val mdl = '''
warfarin_PK_ODE_mdl = mdlObj {
	IDV{ T }
	
	FUNCTIONS{
	   	# define a function. The return type of the function is given by it's name.
	   	# In this case it is a real. If it were a vector or matric it would use [] or [[]] 
		userFunc::function(arg1::int, arg2::real, arg3::string)::matrix[[::real]] is
			# the function can contain only a single expression
		    [[arg1;arg2]]
	}
			OBSERVATION{
				F = 1
				Y : { type is userDefined, prediction=F, value=F, weight=0 } 
			}
} # end of model object
'''.parse
		
		mdl.assertNoErrors
	}
	
	@Test
	def void testValidReturnsVector(){
		val mdl = '''
warfarin_PK_ODE_mdl = mdlObj {
	IDV{ T }
	
	FUNCTIONS{
	   	# define a function. The return type of the function is given by it's name.
	   	# In this case it is a real. If it were a vector or matric it would use [] or [[]] 
		userFunc::function(arg1::int, arg2::real, arg3::string)::vector[::real] is
			# the function can contain only a single expression
		    [ arg1, arg2 ]
	}
			OBSERVATION{
				F = 1
				Y : { type is userDefined, prediction=F, value=F, weight=0 } 
			}
} # end of model object
'''.parse
		
		mdl.assertNoErrors
	}
	
	@Test
	def void testValidTakesVectorArgument(){
		val mdl = '''
warfarin_PK_ODE_mdl = mdlObj {
	IDV{ T }
	
	FUNCTIONS{
	   	# define a function. The return type of the function is given by it's name.
	   	# In this case it is a real. If it were a vector or matric it would use [] or [[]] 
		userFunc::function(arg1::vector[::boolean])::boolean is
			# the function can contain only a single expression
		    arg1[1]
	}
			OBSERVATION{
				F = 1
				Y : { type is userDefined, prediction=F, value=F, weight=0 } 
			}
} # end of model object
'''.parse
		
		mdl.assertNoErrors
	}
	
	@Test
	def void testValidTakesMatrixArgument(){
		val mdl = '''
warfarin_PK_ODE_mdl = mdlObj {
	IDV{ T }
	
	FUNCTIONS{
	   	# define a function. The return type of the function is given by it's name.
	   	# In this case it is a real. If it were a vector or matric it would use [] or [[]] 
		userFunc::function(arg1::matrix[[::string]])::string is
			# the function can contain only a single expression
		    arg1[1,1]
	}
	
			OBSERVATION{
				F = 1
				Y : { type is userDefined, prediction=F, value=F, weight=0 } 
			}
} # end of model object
'''.parse
		
		mdl.assertNoErrors
	}
	
	@Test
	def void testInvalidWrongTypeNameForFunctionSpec(){
		val mdl = '''
warfarin_PK_ODE_mdl = mdlObj {
	IDV{ T }
	
	FUNCTIONS{
	   	# define a function. The return type of the function is given by it's name.
	   	# In this case it is a real. If it were a vector or matric it would use [] or [[]] 
		userFunc::real(arg1::int, arg2::real, arg3::string)::real is
			# the function can contain only a single expression
		    arg1
	}
} # end of model object
'''.parse
		
		mdl.assertError(MdlLibPackage::eINSTANCE.typeSpec,
			MdlValidator::MALFORMED_TYPE_SPEC,
			"You must use the type name 'Function' to define a function specification.")
	}
	

	@Test
	def void testInvalidFunctionTypeNameButNoFunctionSpec(){
		val mdl = '''
warfarin_PK_ODE_mdl = mdlObj {
	IDV{ T }
	
	FUNCTIONS{
	   	# define a function. The return type of the function is given by it's name.
	   	# In this case it is a real. If it were a vector or matric it would use [] or [[]] 
		userFunc::function
	}
} # end of model object
'''.parse
		
		mdl.assertError(MdlLibPackage::eINSTANCE.typeSpec,
			MdlValidator::MALFORMED_TYPE_SPEC,
			"You must define a function specification when using the type name 'Function'.")
	}

	@Test
	def void testValidReturnType(){
		val mdl = '''
warfarin_PK_ODE_mdl = mdlObj {
	IDV{ T }
	
	FUNCTIONS{
	   	# define a function. The return type of the function is given by it's name.
	   	# In this case it is a real. If it were a vector or matric it would use [] or [[]] 
		userFunc::function(arg1::int, arg2::real)::real is
			# the function can contain only a single expression
		    arg2 * arg1  # return type is Real
	}
	
	MODEL_PREDICTION{
		# refer to a user defined function as a normal function
		Z = userFunc(1, ln(2.0)) + 22.2
	}

	VARIABILITY_LEVELS{
		ID : { level=2, type is parameter }
	} 
			OBSERVATION{
				F = 1
				Y : { type is userDefined, prediction=F, value=F, weight=0 } 
			}
} # end of model object
'''.parse
		
		mdl.assertNoErrors
	}

	@Test
	def void testValidCallNoArgs(){
		val mdl = '''
warfarin_PK_ODE_mdl = mdlObj {
	IDV{ T }
	
	FUNCTIONS{
	   	# define a function. The return type of the function is given by it's name.
	   	# In this case it is a real. If it were a vector or matric it would use [] or [[]] 
		userFunc::function()::real is
			# the function can contain only a single expression
		    pi
	}
	
	MODEL_PREDICTION{
		# refer to a user defined function as a normal function
		Z = userFunc() + 22.2
	}

	VARIABILITY_LEVELS{
		ID : { level=2, type is parameter }
	} 
			OBSERVATION{
				F = 1
				Y : { type is userDefined, prediction=F, value=F, weight=0 } 
			}
} # end of model object
'''.parse
		
		mdl.assertNoErrors
	}

	@Test
	def void testInvalidReturnType(){
		val mdl = '''
warfarin_PK_ODE_mdl = mdlObj {
	IDV{ T }
	
	FUNCTIONS{
	   	# define a function. The return type of the function is given by it's name.
	   	# In this case it is a real. If it were a vector or matric it would use [] or [[]] 
		userFunc::function()::string is
			# the function can contain only a single expression
		    "A String"
	}
	
	MODEL_PREDICTION{
		# refer to a user defined function as a normal function
		Z = userFunc() + 22.2
	}

	VARIABILITY_LEVELS{
		ID : { level=2, type is parameter }
	} 
} # end of model object
'''.parse
		
		mdl.assertError(MdlPackage::eINSTANCE.additiveExpression,
			MdlValidator::INCOMPATIBLE_TYPES,
			"Expected Real type, but was ref:String.")
	}

	@Test
	def void testInvalidIncorrectArgNums(){
		val mdl = '''
warfarin_PK_ODE_mdl = mdlObj {
	IDV{ T }
	
	FUNCTIONS{
	   	# define a function. The return type of the function is given by it's name.
	   	# In this case it is a real. If it were a vector or matric it would use [] or [[]] 
		userFunc::function()::real is
			# the function can contain only a single expression
		    21
	}
	
	MODEL_PREDICTION{
		# refer to a user defined function as a normal function
		Z = userFunc(1)
	}

	VARIABILITY_LEVELS{
		ID : { level=2, type is parameter }
	} 
} # end of model object
'''.parse
		
		mdl.assertError(MdlPackage::eINSTANCE.symbolReference,
			MdlValidator::INCORRECT_NUM_FUNC_ARGS,
			"Function 'userFunc' has the wrong number of arguments. Expected 0.")
	}

}