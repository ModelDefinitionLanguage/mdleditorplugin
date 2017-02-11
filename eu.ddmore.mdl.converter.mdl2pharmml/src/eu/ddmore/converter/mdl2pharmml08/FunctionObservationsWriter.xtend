package eu.ddmore.converter.mdl2pharmml08

import eu.ddmore.mdl.mdl.EquationDefinition
import eu.ddmore.mdl.mdl.EquationTypeDefinition
import eu.ddmore.mdl.mdl.SymbolReference
import eu.ddmore.mdl.mdl.TransformedDefinition
import eu.ddmore.mdl.provider.BuiltinFunctionProvider
import eu.ddmore.mdl.utils.MdlUtils
import eu.ddmore.mdllib.mdllib.Expression

class FunctionObservationsWriter {
	extension MdlUtils mu = new MdlUtils
	extension BuiltinFunctionProvider bfp = new BuiltinFunctionProvider
	extension PharmMLExpressionBuilder peb = new PharmMLExpressionBuilder 
	extension PharmMLConverterUtils pcu = new PharmMLConverterUtils
	extension FunctionDefinitionPrinter fdp = new FunctionDefinitionPrinter
	

	def isStandardErrorDefinition(Expression expr){
		expr != null && expr instanceof SymbolReference
	}
	
	def isTransformedBothSides(EquationTypeDefinition definition){
		definition instanceof TransformedDefinition &&
		 definition.expression instanceof SymbolReference &&
		  (definition.expression as SymbolReference).getArgumentExpression('trans') != null
	}
	
	def isTransformedOnlyRhsSide(EquationTypeDefinition definition){
		definition instanceof EquationDefinition &&
		 definition.expression instanceof SymbolReference &&
		  (definition.expression as SymbolReference).getArgumentExpression('trans') != null
	}
	
	def writeContinuousObservation(EquationTypeDefinition definition, int idx){
		val rhsExpr = definition.expression
		if(rhsExpr instanceof SymbolReference){
			val predictionExpr = rhsExpr.getArgumentExpression('prediction')
			'''
			<ObservationModel blkId="om«idx»">
				<ContinuousData>
					«IF definition.isTransformedOnlyRhsSide»
						<ct:Variable symbolType="real" symbId="«predictionExpr.singleSymbolRef?.name ?: "ERROR!"»">
							<ct:Assign>
								<math:Uniop op="log">
									«predictionExpr.pharmMLExpr»
								</math:Uniop>
							</ct:Assign>
						</ct:Variable>
					«ENDIF»
					«IF isStandardErrorDefinition(definition.expression)»
						<Standard symbId="«definition.name»">
							«IF definition instanceof TransformedDefinition»
								<Transformation>«definition.pharmMLTransFunc»</Transformation>
							«ENDIF»
							<Output>
								«IF definition.isTransformedOnlyRhsSide»
									«predictionExpr.singleSymbolRef?.localSymbolReference ?: "ERROR!"»
								«ELSE»
									«predictionExpr.pharmMLExpr»
								«ENDIF»
							</Output>
							«writeStandardErrorModel(rhsExpr)»
							<ResidualError>
								«rhsExpr.getArgumentExpression('eps').pharmMLExpr»
							</ResidualError>
						</Standard>
					«ENDIF»
				</ContinuousData>
			</ObservationModel>
			'''
		}
		else{
			'''
				<ObservationModel blkId="om«idx»">
					<ContinuousData>
						<General symbId="«definition.name»">
							«definition.expression.expressionAsAssignment»
						</General>
					</ContinuousData>
				</ObservationModel>
			'''
		}
	} 
	
	private def writeStandardErrorModel(SymbolReference it){
		'''
		<ErrorModel>
			<ct:Assign>
				<math:FunctionCall>
					<ct:SymbRef symbIdRef="«standardErrorName»"/>
					«FOR vp : getNamedArguments»
						«IF getStandardErrorArgument(vp.argumentName) != null»
							<math:FunctionArgument symbId="«getStandardErrorArgument(vp.argumentName)»">
								«IF !(vp.expression instanceof SymbolReference)»
									«vp.expression.pharmMLExpr»
								«ELSE»
									«vp.expression.pharmMLExpr»
								«ENDIF»
							</math:FunctionArgument>
						«ENDIF»
					«ENDFOR»
				</math:FunctionCall>
			</ct:Assign>
		</ErrorModel>
		'''
	}

}