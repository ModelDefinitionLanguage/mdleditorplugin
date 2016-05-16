package eu.ddmore.converter.mdl2pharmml08

import eu.ddmore.mdl.mdl.NamedFuncArguments
import eu.ddmore.mdl.mdl.SymbolReference
import eu.ddmore.mdl.provider.BuiltinFunctionProvider
import eu.ddmore.mdllib.mdllib.Expression
import java.util.List
import java.util.Map
import org.eclipse.xtend.lib.annotations.Data
import org.eclipse.xtend.lib.annotations.FinalFieldsConstructor

class DistributionPrinter {
	
	extension BuiltinFunctionProvider bfp = new BuiltinFunctionProvider
	extension PharmMLExpressionBuilder peb = new PharmMLExpressionBuilder
	
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
	

//	static var pharmMLMapping = #{
//		'Normal' -> new UncertMlMapping('NormalDistribution', 'http://www.uncertml.org/distributions/normal',
//							#['mean', 'var', 'sd'],
//							#{ 'mean' -> new UncertMlArgument('mean', 'rVal'),
//								'var' -> new UncertMlArgument('variance', 'prVal'),
//								'sd' -> new UncertMlArgument('stddev', 'prVal')
//							}
//		),
//		'Poisson' -> new UncertMlMapping('PoissonDistribution', 'http://www.uncertml.org/distributions/poisson',
//							#['lambda'],
//							#{ 'lambda' -> new UncertMlArgument('rate', 'prVal')
//							}
//		),
//		'Bernoulli' -> new UncertMlMapping('BernoulliDistribution', 'http://www.uncertml.org/distributions/poisson',
//							#['category', 'probability'],
//							#{ 
//								'category' -> new UncertMlArgument('name', 'ref'),
//								'probability' -> new UncertMlArgument('probability', 'pVal')
//							}
//		),
//		'Binomial' -> new UncertMlMapping('BinomialDistribution', 'http://www.uncertml.org/distributions/poisson',
//							#['numberOfTrials', 'probabilityOfSuccess'],
//							#{ 'numberOfTrials' -> new UncertMlArgument('numberOfTrials', 'nVal'),
//								'probabilityOfSuccess' -> new UncertMlArgument('probabilityOfSuccess', 'pVal')
//							}
//		)
//	}
//
//	def private writeUncertmlArg(UncertMlArgument defn, Expression argExpression){
//		if(argExpression != null){
//			'''
//			<«defn.name»>
//				«switch(argExpression){
//					IntegerLiteral,
//					RealLiteral:
//						'''
//						<«defn.typeStr»>«argExpression.convertToString»</«defn.typeStr»>
//						'''
//					SymbolReference:
//						'''
//						«argExpression.ref.uncertMLSymbolReference»
//						'''
//					default: '''<!--- ERROR! --->'''
//				}»
//			</«defn.name»>
//			'''
//		}
//	}

//	def writeUncertmlDist(SymbolReference it){
//		val mapping = pharmMLMapping.get(func)
//		// assume checked that named argument
//		val nargs = argList as NamedFuncArguments
//		'''
//			<Distribution>
//				<UncertML>
//					<«mapping.name» xmlns="http://www.uncertml.org/3.0" definition="«mapping.definition»">
//						«FOR arg : mapping.argOrder»
//							«IF nargs.getArgumentExpression(arg) != null && mapping.argMapping.containsKey(arg)»
//								«mapping.argMapping.get(arg).writeUncertmlArg(nargs.getArgumentExpression(arg))»
//							«ENDIF»
//						«ENDFOR»
//					</«mapping.name»>
//				</UncertML>
//			</Distribution>
//		''' 
//	}
	
//	public def printDiscreteDistribution(SymbolReference distnDef){
//		val typeName = distnDef.func;
//		val distn = switch(typeName){
//			case "Bernoulli1": distnDef.printBernoulliDistn
//			case "Binomial1": distnDef.printBinomialDistn
//			case "CategoricalNonordered1": distnDef.printCategoricalNonorderedDistn 
//			case "CategoricalOrdered1": distnDef.printCategoricalOrderedDistn 
//			default: ''''''
//		}
//		'''
//		<Distribution>
//			«distn»
//		</Distribution>
//		'''
//	}

//	public def printBernoulliDistn(SymbolReference randomList){
//		val probArg = 'probability'
//		val expr = randomList.getArgumentExpression(probArg)
//		'''
//			<ProbOnto xmlns="http://www.pharmml.org/probonto/ProbOnto" name="Bernoulli1">
//				<Parameter name="probability">
//					<ct:Assign>
//						«expr.singleSymbolRef.symbolReference»
//					</ct:Assign>
//				</Parameter>
//			</ProbOnto>
//		'''
//	}

//	public def printCategoricalNonorderedDistn(SymbolReference randomList){
//		val probArg = 'categoryProb'
//		val expr = randomList.getArgumentExpression(probArg)
//		'''
//			<ProbOnto xmlns="http://www.pharmml.org/probonto/ProbOnto" name="CategoricalNonordered1">
//				<Parameter name="categoryProb">
//					<ct:Assign>
//						«expr.pharmMLExpr»
//					</ct:Assign>
//				</Parameter>
//			</ProbOnto>
//		'''
//	}


	def private printProbontoNormal(SymbolReference it){
		val normName = if(getArgumentExpression('sd') != null)
							'Normal1'
						else 'Normal2'
		'''
		<ProbOnto xmlns="http://www.pharmml.org/probonto/ProbOnto" name="«normName»">
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
		</ProbOnto>
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
				«printProbontoNormal»
			«ELSE»
				«printProbontoDistn»
			«ENDIF»
		</Distribution>
	'''


	def private printProbontoDistn(SymbolReference it){
		
//		val probArg = 'categoryProb'
//		val expr = randomList.getArgumentExpression(probArg)
		val args = argList
		'''
			<ProbOnto xmlns="http://www.pharmml.org/probonto/ProbOnto" name="«ref.name»">
				«IF args instanceof NamedFuncArguments»
					«FOR a : args.arguments»
						<Parameter name="«a.argumentName»">
							«a.expression.expressionAsAssignment»
						</Parameter>
					«ENDFOR»
				«ENDIF»
			</ProbOnto>
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