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
					var transformation = "";
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
						transformation =  '''
							<Continuous>
								<Transformation>
								    <TransformedCovariate symbId="«s.name»"/>
									«s.expression.expressionAsEquation»
								</Transformation>
							</Continuous>
							'''
							skipped.add(s);
					}
					model += '''
					«IF transformation.length > 0»
						<Covariate symbId="«transformedCov.name»"«IF s.isIdvDepCovariate» type="timeDependent"«ENDIF»>
							«transformation»
						</Covariate>
					«ENDIF»	
					'''
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
						model += '''
						<Covariate symbId="«s.name»"«IF s.isIdvDepCovariate» type="timeDependent"«ENDIF»>
							<Continuous/>
						</Covariate>
						'''
					}
				}
				EnumerationDefinition:{
					if (!skipped.contains(s)){
						model += '''
						<Covariate symbId="«s.name»"«IF s.isIdvDepCovariate» type="timeDependent"«ENDIF»>
							<Categorical>
								«FOR c : s.catDefn.getCategoryDefinitions»
									<Category catId="«c»"/>
								«ENDFOR»
								«IF s.distn != null»
									«s.distn.writeDistribution»
								«ENDIF»
							</Categorical>
						</Covariate>
						'''
					}
				}
				RandomVariableDefinition:{
					val covType = if(s.distn.typeFor.underlyingType == TypeSystemProvider::PDF_TYPE) 'Continuous' else 'Categorical'
					model += '''
						<Covariate symbId="«s.name»">
							<«covType»>
								«s.distn.writeDistribution»
							</«covType»>
						</Covariate>
					'''
				}
			}
		}
		model
	}
		

    def private getCategoryDefinitions(CategoricalDefinitionExpr expr){
    	val retVal = new ArrayList<String>
		expr.categories.forEach[retVal.add(name)]
    	retVal
    }
    
}