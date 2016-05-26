package eu.ddmore.converter.mdl2pharmml08

import eu.ddmore.mdl.mdl.AttributeList
import eu.ddmore.mdl.mdl.ListDefinition
import eu.ddmore.mdl.mdl.VectorLiteral
import eu.ddmore.mdl.provider.ListDefinitionProvider
import eu.ddmore.mdl.utils.MdlUtils
import eu.ddmore.mdl.mdl.SymbolReference
import eu.ddmore.mdl.mdl.RandomVariableDefinition
import eu.ddmore.mdl.mdl.AnonymousListStatement

class ListIndivParamWriter extends AbstractIndivParamWriter {
	extension MdlUtils mu = new MdlUtils
	extension ListDefinitionProvider ldp = new ListDefinitionProvider
	extension PharmMLConverterUtils pcu = new PharmMLConverterUtils
	extension PharmMLExpressionBuilder peb = new PharmMLExpressionBuilder
	extension DistributionPrinter dp = new DistributionPrinter

	def writeIndividualParameter(ListDefinition it){
		if(attributeLists.size == 1){
			val attList = attributeLists.head
			val typeVal =  attList.getAttributeEnumValue('type')
			switch(typeVal){
				case('general'):
					attList.writeGeneralIdv(name)
				case('linear'):
					attList.writeLinearIdv(name)
//				case('userDefined'):
//					attList.writeUserDefinedIdv(name)
				default:
					'''<Error!>'''		
			}
		}
	}

	def writeIndividualParameter(AnonymousListStatement it){
		if(list.getAttributeEnumValue('type') == 'rv'){
			val rvDefnRef = list.getAttributeExpression('rv')
			if(rvDefnRef instanceof SymbolReference){
				val rvDefn = rvDefnRef.ref
				if(rvDefn instanceof RandomVariableDefinition){
					return 
						'''
						<IndividualParameter id="tst">
							«rvDefn.distn.writeDistribution»
						</IndividualParameter>
						'''
				}
			}
		}
		''''''
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
						«getAttributeExpression('pop').expressionAsAssignment»
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
					«getAttributeExpression('grp').expressionAsAssignment»
				</GeneralCovariate>
				«getAttributeExpression('ranEff').writeRandomEffects»
			</StructuredModel>
		</IndividualParameter>
		''' 
	}
	
//	def writeUserDefinedIdv(AttributeList it, String name){
//		'''
//		<IndividualParameter symbId="«name»">
//			«IF hasAttribute('value')»
//				«getAttributeExpression('value').expressionAsAssignment»
//			«ELSE»
//				<Error!/>
//			«ENDIF»
//		</IndividualParameter>
//		''' 
//	}
}