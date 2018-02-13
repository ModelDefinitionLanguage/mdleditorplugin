package eu.ddmore.mdl.utils

import com.google.inject.Inject
import eu.ddmore.mdl.tests.LibraryTestHelper
import eu.ddmore.mdl.tests.MdlAndLibInjectorProvider
import eu.ddmore.mdl.mdl.EquationDefinition
import eu.ddmore.mdl.mdl.Mcl
import eu.ddmore.mdl.provider.MogDefinitionProvider
import eu.ddmore.mdl.validation.MdlValidator
import org.eclipse.xtext.diagnostics.Diagnostic
import org.eclipse.xtext.testing.InjectWith
import org.eclipse.xtext.testing.XtextRunner
import org.eclipse.xtext.testing.validation.ValidationTestHelper
import org.junit.Test
import org.junit.runner.RunWith

import static org.junit.Assert.*

@RunWith(typeof(XtextRunner))
@InjectWith(typeof(MdlAndLibInjectorProvider))
class ConstantEvaluationTest {
	@Inject extension LibraryTestHelper<Mcl>
	@Inject extension ValidationTestHelper

	extension MdlUtils mu = new MdlUtils
	extension ConstantEvaluation ce = new ConstantEvaluation
	extension MogDefinitionProvider mdp = new MogDefinitionProvider

	@Test
	def testValidSimpleMathsContantExpression(){
		val mdl = '''
			foo = mdlObj{
				IDV { T }
				MODEL_PREDICTION{
					x = 23 * 1 / 4 + 2
				}
			OBSERVATION{
				F = 1
				Y : { type is userDefined, prediction=F, value=F, weight=0 } 
			}
			}
		'''.parse
		
		mdl.assertNoErrors
		
		val stmt = mdl.getFirstObjectOfType(MdlValidator::MDLOBJ).mdlPredictionVariables.head as EquationDefinition
		val actualResult = stmt.expression.evaluateMathsExpression
		assertEquals("Expected result", 7.75, actualResult, 0.000001)
	}
	
	@Test
	def testValidSimpleWithParenthMathsContantExpression(){
		val mdl = '''
			foo = mdlObj{
				IDV { T }
				MODEL_PREDICTION{
					x = 23 * 1 / (4 + 2)
				}
			OBSERVATION{
				F = 1
				Y : { type is userDefined, prediction=F, value=F, weight=0 } 
			}
			}
		'''.parse
		
		mdl.assertNoErrors
		
		val stmt = mdl.getFirstObjectOfType(MdlValidator::MDLOBJ).mdlPredictionVariables.head as EquationDefinition
		val actualResult = stmt.expression.evaluateMathsExpression
		assertEquals("Expected result", 23.0/6.0, actualResult, 0.000001)
	}
	
	@Test
	def testSimpleWithRefMathsContantExpression(){
		val mdl = '''
			foo = mdlObj{
				IDV { T }
				MODEL_PREDICTION{
					x = 23 * 1 / (4 + y)
					y = 2
				}
			OBSERVATION{
				F = 1
				Y : { type is userDefined, prediction=F, value=F, weight=0 } 
			}
			}
		'''.parse
		
		mdl.assertNoErrors
		
		val stmt = mdl.getFirstObjectOfType(MdlValidator::MDLOBJ).mdlPredictionVariables.head as EquationDefinition
		val actualResult = stmt.expression.evaluateMathsExpression
		assertEquals("Expected result", 23.0/6.0, actualResult, 0.000001)
	}
	
	@Test
	def testWithComplexRefMathsContantExpression(){
		val mdl = '''
			foo = mdlObj{
				IDV { T }

				STRUCTURAL_PARAMETERS{
					a = 1
				}

				MODEL_PREDICTION{
					x = 23 * 1 / (4 + y)
					y = 2 ^ a
				}
			OBSERVATION{
				F = 1
				Y : { type is userDefined, prediction=F, value=F, weight=0 } 
			}
			}
		'''.parse
		
		mdl.assertNoErrors
		
		val stmt = mdl.getFirstObjectOfType(MdlValidator::MDLOBJ).mdlPredictionVariables.head as EquationDefinition
		val actualResult = stmt.expression.evaluateMathsExpression
		assertEquals("Expected result", 23.0/6.0, actualResult, 0.000001)
	}
	
	@Test
	def testWithUninitMathsContantExpression(){
		val mdl = '''
			foo = mdlObj{
				IDV { T }
				
				STRUCTURAL_PARAMETERS{
					a
				}
				
				MODEL_PREDICTION{
					x = 23 * 1 / (4 + y)
					y = 2 ^ a
				}
			OBSERVATION{
				F = 1
				Y : { type is userDefined, prediction=F, value=F, weight=0 } 
			}
			}
		'''.parse
		
		mdl.assertNoErrors
		
		val stmt = mdl.getFirstObjectOfType(MdlValidator::MDLOBJ).mdlPredictionVariables.head as EquationDefinition
		val actualResult = stmt.expression.evaluateMathsExpression
		assertNull("Expected result", actualResult)
	}
	
	@Test
	def testValidSimpleContantLogicalExpression(){
		val mdl = '''
			foo = mdlObj{
				IDV { T }
				MODEL_PREDICTION{
					x = (23 * 1) < (4 + 2)
				}
			}
		'''.parse
		
		mdl.assertNoErrors(Diagnostic::SYNTAX_DIAGNOSTIC)
		mdl.assertNoErrors(Diagnostic::LINKING_DIAGNOSTIC)
		
		val stmt = mdl.getFirstObjectOfType(MdlValidator::MDLOBJ).mdlPredictionVariables.head as EquationDefinition
		val actualResult = stmt.expression.evaluateLogicalExpression
		assertFalse("Expected result", actualResult)
	}
	
	@Test
	def testValidSimpleWithNegationContantLogicalExpression(){
		val mdl = '''
			foo = mdlObj{
				IDV { T }
				MODEL_PREDICTION{
					x = -(23 * 1) < (4 + 2)
				}
			}
		'''.parse
		
		mdl.assertNoErrors(Diagnostic::SYNTAX_DIAGNOSTIC)
		mdl.assertNoErrors(Diagnostic::LINKING_DIAGNOSTIC)
		
		val stmt = mdl.getFirstObjectOfType(MdlValidator::MDLOBJ).mdlPredictionVariables.head as EquationDefinition
		val actualResult = stmt.expression.evaluateLogicalExpression
		assertTrue("Expected result", actualResult)
	}
	
	@Test
	def testValidContantLogicalExpression(){
		val mdl = '''
			foo = mdlObj{
				IDV { T }
				MODEL_PREDICTION{
					x = false && true || !true
				}
			}
		'''.parse
		
		mdl.assertNoErrors(Diagnostic::SYNTAX_DIAGNOSTIC)
		mdl.assertNoErrors(Diagnostic::LINKING_DIAGNOSTIC)
		
		val stmt = mdl.getFirstObjectOfType(MdlValidator::MDLOBJ).mdlPredictionVariables.head as EquationDefinition
		val actualResult = stmt.expression.evaluateLogicalExpression
		assertFalse("Expected result", actualResult)
	}
	
	@Test
	def testInvalidIncompleteContantLogicalExpression(){
		val mdl = '''
			foo = mdlObj{
				IDV { T }
				MODEL_PREDICTION{
					x = false && true || 
				}
			}
		'''.parse
		
		mdl.assertNoErrors(Diagnostic::LINKING_DIAGNOSTIC)
		
		val stmt = mdl.getFirstObjectOfType(MdlValidator::MDLOBJ).mdlPredictionVariables.head as EquationDefinition
		val actualResult = stmt.expression.evaluateLogicalExpression
		assertNull("Expected result", actualResult)
	}
	
	@Test
	def testValidConditionalContantLogicalExpression(){
		val mdl = '''
			foo = mdlObj{
				IDV { T }
				MODEL_PREDICTION{
					x = if((23 * 1) < (4 + 2)) then 0 else 1
				}
			}
		'''.parse
		
		mdl.assertNoErrors(Diagnostic::SYNTAX_DIAGNOSTIC)
		mdl.assertNoErrors(Diagnostic::LINKING_DIAGNOSTIC)
		
		val stmt = mdl.getFirstObjectOfType(MdlValidator::MDLOBJ).mdlPredictionVariables.head as EquationDefinition
		val actualResult = stmt.expression.evaluateMathsExpression
		assertEquals("Expected result", 1.0, actualResult, 0.000001)
	}
	
	@Test
	def testValidAdditionConditionalContantLogicalExpression(){
		val mdl = '''
			foo = mdlObj{
				IDV { T }
				MODEL_PREDICTION{
					x = if((23 * 1) < (4 + 2) && false || 22 == 22) then 0 else 1
				}
			}
		'''.parse
		
		mdl.assertNoErrors(Diagnostic::SYNTAX_DIAGNOSTIC)
		mdl.assertNoErrors(Diagnostic::LINKING_DIAGNOSTIC)
		
		val stmt = mdl.getFirstObjectOfType(MdlValidator::MDLOBJ).mdlPredictionVariables.head as EquationDefinition
		val actualResult = stmt.expression.evaluateMathsExpression
		assertEquals("Expected result", 0.0, actualResult, 0.000001)
	}
	
}