package eu.ddmore.converter.mdl2pharmml08

import eu.ddmore.mdl.mdl.AnonymousListStatement
import eu.ddmore.mdl.mdl.AttributeList
import eu.ddmore.mdl.mdl.ListDefinition
import eu.ddmore.mdl.mdl.ListElifClause
import eu.ddmore.mdl.mdl.ListIfClause
import eu.ddmore.mdl.mdl.ListIfExpression
import eu.ddmore.mdl.mdl.ListPWClause
import eu.ddmore.mdl.mdl.ListPiecewiseExpression
import eu.ddmore.mdl.mdl.RandomVariableDefinition
import eu.ddmore.mdl.mdl.SymbolReference
import eu.ddmore.mdl.mdl.VectorLiteral
import eu.ddmore.mdl.provider.ListDefinitionProvider
import eu.ddmore.mdllib.mdllib.SymbolDefinition

class ListIndivParamWriter extends AbstractIndivParamWriter {
	extension ListDefinitionProvider ldp = new ListDefinitionProvider
	extension PharmMLConverterUtils pcu = new PharmMLConverterUtils
	extension PharmMLExpressionBuilder peb = new PharmMLExpressionBuilder
	extension DistributionPrinter dp = new DistributionPrinter

	def writeIndividualParameter(ListDefinition it){
		val attList = it.list
		if(attList instanceof AttributeList){
			writeInivParam(it, attList, false)
		}
		else if(attList instanceof ListIfExpression){
			'''
			<IndividualParameter symbId="«name»"/>
			<ConditionalStatement>
				«FOR iec : attList.ifelseClause»
					«IF iec instanceof ListIfClause»
						<math:If>
							<math:Condition>
								«iec.cond.pharmMLExpr»
							</math:Condition>
							«writeInivParam(it, iec.value, true)»
						</math:If>
					«ELSEIF iec instanceof ListElifClause»
						<math:ElseIf>
							<math:Condition>
								«iec.cond.pharmMLExpr»
							</math:Condition>
							«writeInivParam(it, iec.value, true)»
						</math:ElseIf>
					«ELSE»
						<Error!/>
					«ENDIF»
				«ENDFOR»
				«IF attList.elseClause != null»
					<math:Else>
						«writeInivParam(it, attList.elseClause.value, true)»
					</math:Else>
				«ENDIF»
			</ConditionalStatement>
			'''
		}
		else if(attList instanceof ListPiecewiseExpression){
			var cntr = 0
			'''
			<IndividualParameter symbId="«name»"/>
			<ConditionalStatement>
				«FOR iec : attList.when»
					«IF iec instanceof ListPWClause»
						«IF cntr++ == 0»
							<math:If>
								<math:Condition>
									«iec.cond.pharmMLExpr»
								</math:Condition>
								«writeInivParam(it, iec.value, true)»
							</math:If>
						«ELSE»
							<math:ElseIf>
								<math:Condition>
									«iec.cond.pharmMLExpr»
								</math:Condition>
								«writeInivParam(it, iec.value, true)»
							</math:ElseIf>
						«ENDIF»
					«ELSE»
						<Error!/>
					«ENDIF»
						«writeInivParam(it, iec.value, true)»
				«ENDFOR»
				«IF attList.otherwise != null»
					<math:Else>
						«writeInivParam(it, attList.otherwise, true)»
					</math:Else>
				«ENDIF»
			</ConditionalStatement>
			'''
		}
	}

	def private writeInivParam(SymbolDefinition idvVar, AttributeList attList, boolean isRef){
			val typeVal =  attList.getAttributeEnumValue('type')
			switch(typeVal){
				case('general'):
					attList.writeGeneralIdv(idvVar.name, isRef)
				case('linear'):
					attList.writeLinearIdv(idvVar.name, isRef)
				default:
					'''<Error!>'''		
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
						<IndividualParameter symbId="tst">
							«rvDefn.distn.writeDistribution»
						</IndividualParameter>
						'''
				}
			}
		}
		''''''
	}

	def writeLinearIdv(AttributeList it, String name, boolean isRef){
		val fixEff = getAttributeExpression('fixEff') as VectorLiteral
		'''
		«writeTmpRandomEffect»
		<IndividualParameter symbId«IF isRef»Ref«ENDIF»="«name»">
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
	
	def writeGeneralIdv(AttributeList it, String name, boolean isRef){
		val transEnum = getAttributeEnumValue('trans')
		val trans = if(transEnum != null) getPharmMLTransFunc(transEnum) else null
		'''
		«writeTmpRandomEffect»
		<IndividualParameter symbId«IF isRef»Ref«ENDIF»="«name»">
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