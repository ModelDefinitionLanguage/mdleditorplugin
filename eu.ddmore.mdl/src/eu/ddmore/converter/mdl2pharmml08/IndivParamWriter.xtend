package eu.ddmore.converter.mdl2pharmml08

import eu.ddmore.mdl.mdl.AttributeList
import eu.ddmore.mdl.mdl.CategoryValueReference
import eu.ddmore.mdl.mdl.ListDefinition
import eu.ddmore.mdl.mdl.SubListExpression
import eu.ddmore.mdl.mdl.SymbolReference
import eu.ddmore.mdl.mdl.VectorElement
import eu.ddmore.mdl.mdl.VectorLiteral
import eu.ddmore.mdl.provider.ListDefinitionProvider
import eu.ddmore.mdl.provider.SublistDefinitionProvider
import eu.ddmore.mdl.utils.MdlUtils
import eu.ddmore.mdllib.mdllib.Expression
import eu.ddmore.mdllib.mdllib.SymbolDefinition
import org.eclipse.xtext.EcoreUtil2

class IndivParamWriter {
	extension MdlUtils mu = new MdlUtils
//	extension TypeSystemProvider tsp = new TypeSystemProvider
	extension ListDefinitionProvider ldp = new ListDefinitionProvider
//	extension BuiltinFunctionProvider bfp = new BuiltinFunctionProvider
	extension PharmMLExpressionBuilder peb = new PharmMLExpressionBuilder 
//	extension DistributionPrinter dp = new DistributionPrinter 
	extension PharmMLConverterUtils pcu = new PharmMLConverterUtils
	extension SublistDefinitionProvider sdp = new SublistDefinitionProvider
//	extension PKMacrosPrinter pkp = PKMacrosPrinter::INSTANCE
//	extension DomainObjectModelUtils domu = new DomainObjectModelUtils
//	extension BlockUtils bu = new BlockUtils

	def writeIndividualParameter(ListDefinition it){
		if(attributeLists.size == 1){
			val attList = attributeLists.head
			val typeVal =  attList.getAttributeEnumValue('type')
			switch(typeVal){
				case('general'):
					attList.writeGeneralIdv(name)
				case('linear'):
					attList.writeLinearIdv(name)
				default:
					'''<Error!>'''		
			}
		}
	}

	def writeLinearIdv(AttributeList it, String name){
		val fixEff = getAttributeExpression('fixEff') as VectorLiteral
		'''
		<IndividualParameter symbId="«name»">
			<StructuredModel>
				«IF getAttributeExpression('trans') != null»
					<Transformation type="«getAttributeEnumValue('trans').getPharmMLTransFunc»" />
				«ENDIF»
				<LinearCovariate>
					<PopulationValue>
						«getAttributeExpression('pop').writeAssignment»
					</PopulationValue>
					«IF fixEff != null && !fixEff.expressions.isEmpty »
						«getAttributeExpression('fixEff').writeFixedEffects»
					«ENDIF»
				</LinearCovariate>
				«getAttributeExpression('ranEff').writeRandomEffects»
			</StructuredModel>
		</IndividualParameter>
		''' 
	}
	
	def writeGeneralIdv(AttributeList it, String name){
		val transEnum = getAttributeEnumValue('trans')
		val trans = if(transEnum != null) getPharmMLTransFunc(transEnum) else null
//		val trans = switch(it){
//			TransformedDefinition:
//				getPharmMLTransFunc(transform.name)
//			default: null
//		} 
		'''
		<IndividualParameter symbId="«name»">
			<StructuredModel>
				«IF trans!= null»
					<Transformation>«trans»</Transformation>
				«ENDIF»
				<GeneralCovariate>
					«getAttributeExpression('grp').writeAssignment»
				</GeneralCovariate>
				«getAttributeExpression('ranEff').writeRandomEffects»
			</StructuredModel>
		</IndividualParameter>
		''' 
	}
	
	def private writeRandomEffects(Expression expr)'''
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

	def writeAssignment(Expression expr)'''
		<ct:Assign>
			«expr.pharmMLExpr»
		</ct:Assign>
	'''

	def writeFixedEffects(Expression expr){
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

	def writeFixedEffectCovariate(SubListExpression it){
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
	
	def writeFixedEffectCoefficient(SubListExpression it){
		val catCov = getAttributeExpression('catCov')
		'''
		«getAttributeExpression('coeff')?.pharmMLExpr»
		«IF catCov != null»
			<Category catId="«catCov.getEnumValue.name»"/>
		«ENDIF»
		'''
	}

	def getEnumType(Expression expr){
		switch(expr){
			CategoryValueReference:{
				EcoreUtil2.getContainerOfType(expr.ref, SymbolDefinition)
			}
			default: null
		}
	}

	def getEnumValue(Expression expr){
		switch(expr){
			CategoryValueReference:	expr.ref
			default: null
		}
	}
	

}