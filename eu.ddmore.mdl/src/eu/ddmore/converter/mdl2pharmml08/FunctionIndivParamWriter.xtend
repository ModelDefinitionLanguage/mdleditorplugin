package eu.ddmore.converter.mdl2pharmml08

import eu.ddmore.mdl.mdl.EquationTypeDefinition
import eu.ddmore.mdl.mdl.NamedFuncArguments
import eu.ddmore.mdl.mdl.SymbolReference
import eu.ddmore.mdl.mdl.TransformedDefinition
import eu.ddmore.mdl.mdl.VectorLiteral
import eu.ddmore.mdl.provider.BuiltinFunctionProvider
import eu.ddmore.mdl.utils.MdlUtils

import static extension eu.ddmore.mdl.utils.ExpressionConverter.convertToString

class FunctionIndivParamWriter extends AbstractIndivParamWriter {
	extension MdlUtils mu = new MdlUtils
	extension BuiltinFunctionProvider bfp = new BuiltinFunctionProvider
	extension PharmMLConverterUtils pcu = new PharmMLConverterUtils
	extension PharmMLExpressionBuilder peb = new PharmMLExpressionBuilder

	def writeIndividualParameter(EquationTypeDefinition it){
		val expr = it.expression
		switch(expr){
			SymbolReference case(expr.func == 'general'):
				writeGeneralIdv
			SymbolReference case(expr.func == 'linear'):
				writeLinearIdv
			default:
				writeExplicitIdv			
		}
	}

	def writeLinearIdv(EquationTypeDefinition it){
		var funcExpr = expression as SymbolReference
		var namedArgList = funcExpr.argList as NamedFuncArguments 
		val fixEff = namedArgList.getArgumentExpression('fixEff') as VectorLiteral
		'''
		<IndividualParameter symbId="«name»">
			<StructuredModel>
				«IF namedArgList.getArgumentExpression('trans') != null»
					<Transformation type="«namedArgList.getArgumentExpression('trans').convertToString.getPharmMLTransFunc»" />
				«ENDIF»
				<LinearCovariate>
					<PopulationValue>
						«namedArgList.getArgumentExpression('pop').expressionAsAssignment»
					</PopulationValue>
					«IF fixEff != null && !fixEff.expressions.isEmpty »
						«namedArgList.getArgumentExpression('fixEff').writeFixedEffects»
					«ENDIF»
				</LinearCovariate>
				«namedArgList.getArgumentExpression('ranEff').writeRandomEffects»
			</StructuredModel>
		</IndividualParameter>
		''' 
	}
	
	def writeExplicitIdv(EquationTypeDefinition it)'''
		<IndividualParameter symbId="«name»">
			«expression.expressionAsAssignment»
		</IndividualParameter>
	''' 
	
	def writeGeneralIdv(EquationTypeDefinition it){
		var funcExpr = expression as SymbolReference
		var namedArgList = funcExpr.argList as NamedFuncArguments
		val trans = switch(it){
			TransformedDefinition:
				getPharmMLTransFunc(transform.name)
			default: null
		} 
		'''
		<IndividualParameter symbId="«name»">
			<StructuredModel>
				«IF trans!= null»
					<Transformation type="«trans»"/>
				«ENDIF»
				<GeneralCovariate>
					«namedArgList.getArgumentExpression('grp').expressionAsAssignment»
				</GeneralCovariate>
				«namedArgList.getArgumentExpression('ranEff').writeRandomEffects»
			</StructuredModel>
		</IndividualParameter>
		''' 
	}
	
}