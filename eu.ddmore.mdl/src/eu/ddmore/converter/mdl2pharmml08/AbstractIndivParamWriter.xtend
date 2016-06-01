package eu.ddmore.converter.mdl2pharmml08

import eu.ddmore.mdl.mdl.CategoryValueReference
import eu.ddmore.mdl.mdl.SubListExpression
import eu.ddmore.mdl.mdl.SymbolReference
import eu.ddmore.mdl.mdl.VectorElement
import eu.ddmore.mdl.mdl.VectorLiteral
import eu.ddmore.mdl.provider.SublistDefinitionProvider
import eu.ddmore.mdllib.mdllib.Expression
import eu.ddmore.mdllib.mdllib.SymbolDefinition
import org.eclipse.xtext.EcoreUtil2
import eu.ddmore.mdl.utils.ExpressionUtils
import java.util.HashMap
import java.util.Map
import eu.ddmore.mdl.mdl.AttributeList
import eu.ddmore.mdl.provider.ListDefinitionProvider

abstract class AbstractIndivParamWriter {
	extension PharmMLExpressionBuilder peb = new PharmMLExpressionBuilder 
	extension SublistDefinitionProvider sdp = new SublistDefinitionProvider
	extension ListDefinitionProvider ldp = new ListDefinitionProvider
	extension ExpressionUtils eu = new ExpressionUtils

	
	def protected writeRandomEffects(Expression expr)'''
		«FOR e : expr.vector»
			<RandomEffects>
				«IF e instanceof SymbolReference»
					<ct:SymbRef blkIdRef="pm" symbIdRef="«e.tmpRvVar»"/>
				«ENDIF»
			</RandomEffects>
		«ENDFOR»
	'''

	val Map<SymbolReference, String> rvMap = new HashMap<SymbolReference, String>
	
	def createTmpRvVar(SymbolReference it){
		val retVal = 'MDL__' + ref.name + '_' + indexExpr.rowIdx.begin?.integerValue ?: 'b' + '_' +  indexExpr.rowIdx.end?.integerValue ?: 'e' + '_'
			+ if(indexExpr.colIdx != null) indexExpr.colIdx.begin?.integerValue ?: 'b' + '_' +  indexExpr.colIdx.end?.integerValue ?: 'e'
		rvMap.put(it, retVal)
		retVal
	}
	
	def getTmpRvVar(SymbolReference it){
		if(rvMap.containsKey(it)){
			rvMap.get(it)
		}
		else it.ref.name
	}

	def CharSequence writeTmpRandomEffect(AttributeList it){
		val ranEff = getAttributeExpression('ranEff')
		'''
			«FOR v : ranEff.vector»
				«IF v instanceof SymbolReference»
					«IF v.indexExpr != null»
						<PopulationParameter symbId="«v.createTmpRvVar»">
							«v.expressionAsAssignment»
						</PopulationParameter>
					«ENDIF»
				«ENDIF»
			«ENDFOR»
		'''
	}

	def protected writeFixedEffects(Expression expr){
		val it = expr as VectorLiteral
		'''
		«FOR el : expressions»
			<Covariate>
				«((el as VectorElement).element as SubListExpression).writeFixedEffectCovariate»
				<FixedEffect>
					«((el as VectorElement).element as SubListExpression).writeFixedEffectCoefficient»
				</FixedEffect>
			</Covariate>
		«ENDFOR»
		'''
	}

	def protected writeFixedEffectCovariate(SubListExpression it){
		val cov = getAttributeExpression('cov')
		if(cov != null){
			'''
			«cov.pharmMLExpr»
			'''
		}
		else{
			val catCov = getAttributeExpression('catCov')
			'''
			«catCov.getEnumType.symbolReference»
			'''
		}
	}
	
	def protected writeFixedEffectCoefficient(SubListExpression it){
		val catCov = getAttributeExpression('catCov')
		'''
		«getAttributeExpression('coeff')?.pharmMLExpr»
		«IF catCov != null»
			<Category catId="«catCov.getEnumValue.name»"/>
		«ENDIF»
		'''
	}

	def protected getEnumType(Expression expr){
		switch(expr){
			CategoryValueReference:{
				EcoreUtil2.getContainerOfType(expr.ref, SymbolDefinition)
			}
			default: null
		}
	}

	def protected getEnumValue(Expression expr){
		switch(expr){
			CategoryValueReference:	expr.ref
			default: null
		}
	}
	

}