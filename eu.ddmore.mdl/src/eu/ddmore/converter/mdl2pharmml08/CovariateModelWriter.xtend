package eu.ddmore.converter.mdl2pharmml08

import eu.ddmore.mdl.mdl.CategoricalDefinitionExpr
import eu.ddmore.mdl.mdl.EnumerationDefinition
import eu.ddmore.mdl.mdl.EquationDefinition
import eu.ddmore.mdl.mdl.RandomVariableDefinition
import eu.ddmore.mdl.utils.MdlUtils
import eu.ddmore.mdllib.mdllib.SymbolDefinition
import java.util.ArrayList
import java.util.List
import eu.ddmore.mdl.type.TypeSystemProvider
import eu.ddmore.mdllib.mdllib.Expression

class CovariateModelWriter {
	extension MdlUtils mu = new MdlUtils
	extension PharmMLExpressionBuilder peb = new PharmMLExpressionBuilder
	extension DistributionPrinter dp = new DistributionPrinter
	extension TypeSystemProvider tsp = new TypeSystemProvider 

	val skipped = new ArrayList<SymbolDefinition>();

	
	def writeCovariateModel(List<SymbolDefinition> covDefns){
		skipped.clear
		'''
			«IF !covDefns.isEmpty»
				<CovariateModel blkId="cm">
					«covDefns.writeTransformedCovariates»
					«covDefns.writeNonTransformedCovariates»
				</CovariateModel>
			«ENDIF»
		'''
	}
	
	
	def private writeTransformedCovariates(List<SymbolDefinition> covDefns){
		var model = ''''''
		for(s : covDefns){
			switch(s){
				EquationDefinition case(s.expression != null):{
//					var transformation = "";
					var dependencies = s.expression.getCovariateDependencies;
					var SymbolDefinition transformedCov = null 
					var continue = true; //no 'break' command in xText
					for (v: dependencies){
						if (covDefns.exists[it == v] && continue){
							transformedCov = v
							skipped.add(v); 
							continue = false;
						} 
					}
					if (transformedCov != null){
						model += transformedCov.writeTransformedContinuousCovariate(s)
//						transformation =  '''
//							<Continuous>
//								<Transformation>
//								    <TransformedCovariate symbId="«s.name»"/>
//									«s.expression.expressionAsEquation»
//								</Transformation>
//							</Continuous>
//							'''
							skipped.add(s);
					}
//					model += '''
//					«IF transformation.length > 0»
//						<Covariate symbId="«transformedCov.name»"«IF s.isIdvDepCovariate» type="timeDependent"«ENDIF»>
//							«transformation»
//						</Covariate>
//					«ENDIF»	
//					'''
				}
			}
		}
		model
	}

	def private writeNonTransformedCovariates(List<SymbolDefinition> covDefns){
		var model = ''''''
		for(s : covDefns){
			switch(s){
				EquationDefinition:{
					if (!skipped.contains(s)){
						model += s.writeNonTransdNonCatCovariate 
//						'''
//						<Covariate symbId="«s.name»"«IF s.isIdvDepCovariate» type="timeDependent"«ENDIF»>
//							<Continuous/>
//						</Covariate>
//						'''
					}
				}
				EnumerationDefinition:{
					if (!skipped.contains(s)){
						model += s.writeCategoricalCovariate 
//						'''
//						<Covariate symbId="«s.name»"«IF s.isIdvDepCovariate» type="timeDependent"«ENDIF»>
//							<Categorical>
//								«FOR c : s.catDefn.getCategoryDefinitions»
//									<Category catId="«c»"/>
//								«ENDFOR»
//								«IF s.distn != null»
//									«s.distn.writeDistribution»
//								«ENDIF»
//							</Categorical>
//						</Covariate>
//						'''
					}
				}
				RandomVariableDefinition:{
					model += writeNonTransdNonCatCovariate(s, s.distn)
//					if(s.distn.typeFor.underlyingType == TypeSystemProvider::PDF_TYPE)
//						s.writeContinuousCovariate(s.distn)
//					else s.writeCategoricalCovariate
//					val covType = if(s.distn.typeFor.underlyingType == TypeSystemProvider::PDF_TYPE) 'Continuous' else 'Categorical'
//					model += '''
//						<Covariate symbId="«s.name»">
//							<«covType»>
//								«s.distn.writeDistribution»
//							</«covType»>
//						</Covariate>
//					'''
				}
			}
		}
		model
	}
	
//	def writeCategoricalCovariate(RandomVariableDefinition it){
//		writeCovariateBoilerPlate('''
//			<Categorical>
//				«distn.writeDistribution»
//			</Categorical>
//		''')	
//	}
	
//	def writeContinuousCovariate(RandomVariableDefinition it){
//		writeCovariateBoilerPlate('''
//			<Continuous>
//				«distn.writeDistribution»
//			</Continuous>
//		''')	
//	}
	
	def writeTransformedContinuousCovariate(SymbolDefinition cov, EquationDefinition transn){
		cov.writeCovariateBoilerPlate('''
			<Continuous>
				<Transformation>
				    <TransformedCovariate symbId="«transn.name»"/>
					«transn.expression.expressionAsEquation»
				</Transformation>
			</Continuous>
		''')	
	}
	
	def CharSequence writeNonTransdNonCatCovariate(SymbolDefinition sd){
		writeNonTransdNonCatCovariate(sd, null)
	}
	
	def writeNonTransdNonCatCovariate(SymbolDefinition it, Expression rhs){
		val rhsType = rhs?.typeFor?.underlyingType ?: TypeSystemProvider::UNDEFINED_TYPE
		writeCovariateBoilerPlate('''
			«IF rhs == null»
				<Continuous/>
			«ELSE»
				«IF rhsType == TypeSystemProvider::PDF_TYPE»
					<Continuous>
						«rhs.writeDistribution»
					</Continuous>
				«ELSEIF rhsType == TypeSystemProvider::PMF_TYPE»
					<Categorical>
						«rhs.writeDistribution»
					</Categorical>
				«ELSE»
					<Continuous>
						«rhs.expressionAsAssignment»
					</Continuous>
				«ENDIF»
			«ENDIF»
		''')	
	}
	
	def writeCategoricalCovariate(EnumerationDefinition it){
		writeCovariateBoilerPlate('''
			<Categorical>
				«FOR c : catDefn.getCategoryDefinitions»
					<Category catId="«c»"/>
				«ENDFOR»
				«IF distn != null»
					«distn.writeDistribution»
				«ENDIF»
			</Categorical>
		''')
	}
	
	
	def private writeCovariateBoilerPlate(SymbolDefinition it, String content)'''
		<Covariate symbId="«name»"«IF isIdvDepCovariate» type="timeDependent"«ENDIF»>
			«content»
		</Covariate>
	'''

    def private getCategoryDefinitions(CategoricalDefinitionExpr expr){
    	val retVal = new ArrayList<String>
		expr.categories.forEach[retVal.add(name)]
    	retVal
    }
    
}