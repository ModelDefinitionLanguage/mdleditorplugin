package eu.ddmore.converter.mdl2pharmml08

import eu.ddmore.mdl.mdl.AttributeList
import eu.ddmore.mdl.mdl.ListDefinition
import eu.ddmore.mdl.mdl.VectorLiteral
import eu.ddmore.mdl.provider.ListDefinitionProvider
import eu.ddmore.mdl.utils.MdlUtils

class ListIndivParamWriter extends AbstractIndivParamWriter {
	extension MdlUtils mu = new MdlUtils
	extension ListDefinitionProvider ldp = new ListDefinitionProvider
	extension PharmMLConverterUtils pcu = new PharmMLConverterUtils
	extension PharmMLExpressionBuilder peb = new PharmMLExpressionBuilder

	def writeIndividualParameter(ListDefinition it){
		if(attributeLists.size == 1){
			val attList = attributeLists.head
			val typeVal =  attList.getAttributeEnumValue('type')
			switch(typeVal){
				case('general'):
					attList.writeGeneralIdv(name)
				case('linear'):
					attList.writeLinearIdv(name)
				case('userDefined'):
					attList.writeUserDefinedIdv(name)
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
					<Transformation type="«trans»"/>
				«ENDIF»
				<GeneralCovariate>
					«getAttributeExpression('grp').writeAssignment»
				</GeneralCovariate>
				«getAttributeExpression('ranEff').writeRandomEffects»
			</StructuredModel>
		</IndividualParameter>
		''' 
	}
	
	def writeUserDefinedIdv(AttributeList it, String name){
		'''
		<IndividualParameter symbId="«name»">
			«IF hasAttribute('value')»
				«getAttributeExpression('value').expressionAsAssignment»
			«ELSE»
				<Error!/>
			«ENDIF»
		</IndividualParameter>
		''' 
	}
}