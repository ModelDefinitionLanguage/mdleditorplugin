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

abstract class AbstractIndivParamWriter {
	extension PharmMLExpressionBuilder peb = new PharmMLExpressionBuilder 
	extension SublistDefinitionProvider sdp = new SublistDefinitionProvider

	
	def protected writeRandomEffects(Expression expr)'''
		«IF expr instanceof VectorLiteral»
			«FOR e : (expr as VectorLiteral).expressions»
				<RandomEffects>
					«IF (e as VectorElement).element instanceof SymbolReference»
						«(e as VectorElement).element.pharmMLExpr»
					«ELSE»
						<ERROR!>
					«ENDIF»
				</RandomEffects>
			«ENDFOR»
		«ENDIF»
		'''

//	def protected writeAssignment(Expression expr)'''
//		<ct:Assign>
//			«expr.pharmMLExpr»
//		</ct:Assign>
//	'''

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