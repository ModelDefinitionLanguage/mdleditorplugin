package eu.ddmore.converter.mdl2pharmml08

import eu.ddmore.mdl.mdl.NamedFuncArguments
import eu.ddmore.mdl.mdl.SymbolReference
import eu.ddmore.mdl.provider.BuiltinFunctionProvider
import eu.ddmore.mdllib.mdllib.Expression
import java.util.List
import java.util.Map
import org.eclipse.xtend.lib.annotations.Data
import org.eclipse.xtend.lib.annotations.FinalFieldsConstructor
import eu.ddmore.mdl.utils.ExpressionUtils
import eu.ddmore.mdl.mdl.FuncArguments

class DistributionPrinter {
	
	extension BuiltinFunctionProvider bfp = new BuiltinFunctionProvider
	extension PharmMLExpressionBuilder peb = new PharmMLExpressionBuilder
	extension ExpressionUtils eu = new ExpressionUtils
	
	@Data @FinalFieldsConstructor
	static class UncertMlMapping{
		String name
		String definition
		List<String> argOrder 
		Map<String, UncertMlArgument> argMapping
	}
	
	@Data @FinalFieldsConstructor
	static class UncertMlArgument{
		String name
		String typeStr
	}
	
	
	def private printProbontoNormal(SymbolReference it, boolean isMixture){
		val normName = if(getArgumentExpression('sd') != null)
							'Normal1'
						else 'Normal2'
		'''
		«IF isMixture»
			<MixtureComponent name="«normName»">
		«ELSE»
			<ProbOnto xmlns="http://www.pharmml.org/probonto/ProbOnto" name="«normName»">
		«ENDIF»
			<Parameter name="mean">
				«getArgumentExpression('mean')?.expressionAsAssignment ?: '<Error!>'»
			</Parameter>
			«IF getArgumentExpression('sd') != null»
				<Parameter name="stdev">
					«getArgumentExpression('sd').expressionAsAssignment»
				</Parameter>
			«ELSE»
				<Parameter name="var">
					«getArgumentExpression('var')?.expressionAsAssignment ?: '<Error!>'»
				</Parameter>
			«ENDIF»
		«IF isMixture»
			</MixtureComponent>
		«ELSE»
			</ProbOnto>
		«ENDIF»
		'''
	}

	def writeDistribution(Expression ex){
		if(ex instanceof SymbolReference)
			ex.writeDistribution
		else
			'''<Error!>'''
	}


	def writeDistribution(SymbolReference it)'''
		<Distribution>
			«IF ref.name == 'Normal'»
				«printProbontoNormal(false)»
			«ELSEIF ref.name == 'MixtureDistribution'»
				«printProbontoMixture»
			«ELSE»
				«printProbontoDistn(false)»
			«ENDIF»
		</Distribution>
	'''

	def private printMixtureComponent(Expression symbRef){
		'''
		«IF symbRef instanceof SymbolReference»
			«IF symbRef.ref.name == 'Normal'»
				«symbRef.printProbontoNormal(true)»
			«ELSE»
				«symbRef.printProbontoDistn(true)»
			«ENDIF»
		«ENDIF»
		'''
	}


	def private printProbontoMixture(SymbolReference it){
		val args = argList
		'''
			<ProbOnto xmlns="http://www.pharmml.org/probonto/ProbOnto" name="«ref.name»">
				«IF args instanceof NamedFuncArguments»
					«FOR a : args.arguments»
						«IF a.argumentName != 'distributions'»
							<Parameter name="«a.argumentName»">
								«a.expression.expressionAsAssignment»
							</Parameter>
						«ENDIF»
					«ENDFOR»
					«FOR a : args.arguments»
						«IF a.argumentName == 'distributions'»
							«FOR mc : a.expression.vector»
								«mc.printMixtureComponent»
							«ENDFOR»
						«ENDIF»
					«ENDFOR»
				«ENDIF»
			</ProbOnto>
		'''
	}
	
	def private printProbontoParameters(FuncArguments args){
		'''
		«IF args instanceof NamedFuncArguments»
			«FOR a : args.arguments»
				<Parameter name="«a.argumentName»">
					«a.expression.expressionAsAssignment»
				</Parameter>
			«ENDFOR»
		«ENDIF»
		'''
	}
	

	def private printProbontoDistn(SymbolReference it, boolean isMixture){
		'''
			«IF isMixture»
				<MixtureComponent name="«ref.name»">
			«ELSE»
				<ProbOnto xmlns="http://www.pharmml.org/probonto/ProbOnto" name="«ref.name»">
			«ENDIF»
				«printProbontoParameters(argList)»
			«IF isMixture»
				</MixtureComponent>
			«ELSE»
				</ProbOnto>
			«ENDIF»
		'''
	}




//	public def printCategoricalOrderedDistn(SymbolReference randomList){
//		val probArg = 'categoryProb'
//		val expr = randomList.getArgumentExpression(probArg)
//		'''
//			<ProbOnto xmlns="http://www.pharmml.org/probonto/ProbOnto" name="CategoricalOrdered1">
//				<Parameter name="categoryProb">
//					<ct:Assign>
//						«expr.pharmMLExpr»
//					</ct:Assign>
//				</Parameter>
//			</ProbOnto>
//		'''
//	}
//
//	public def printBinomialDistn(SymbolReference randomList){
//		val mapping = pharmMLMapping.get(randomList.func)
////		var typeName = randomList.func;
////		val recognizedArgs = distribution_attrs.get(typeName);
////		val numTrials = randomList.arguments.getAttributeExpression(DistributionValidator::attr_numberOfTrials.name);
////		val probs = randomList.arguments.getAttributeExpression(DistributionValidator::attr_probabilityOfSuccess.name);
//		val numTrials = randomList.getArgumentExpression('numberOfTrials')
//		val trialsArg = 'numberOfTrials'
//		val probs = randomList.getArgumentExpression('probabilityOfSuccess')
//		val probArg = 'probabilityOfSuccess'
//		'''
//		<UncertML>
//			<BinomialDistribution xmlns="http://www.uncertml.org/3.0" definition="http://www.uncertml.org/3.0">
//				«mapping.argMapping.get(trialsArg).writeUncertmlArg(numTrials)»
//				«mapping.argMapping.get(probArg).writeUncertmlArg(probs)»
//			</BinomialDistribution>
//		</UncertML>
//		'''
//		}
	
}