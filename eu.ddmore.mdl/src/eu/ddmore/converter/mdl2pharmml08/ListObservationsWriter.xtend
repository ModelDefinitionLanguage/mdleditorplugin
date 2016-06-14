package eu.ddmore.converter.mdl2pharmml08

import eu.ddmore.mdl.mdl.AbstractAttributeList
import eu.ddmore.mdl.mdl.AnonymousListStatement
import eu.ddmore.mdl.mdl.AttributeList
import eu.ddmore.mdl.mdl.BlockStatement
import eu.ddmore.mdl.mdl.EnumerationDefinition
import eu.ddmore.mdl.mdl.EquationDefinition
import eu.ddmore.mdl.mdl.ListDefinition
import eu.ddmore.mdl.mdl.RandomVariableDefinition
import eu.ddmore.mdl.mdl.SymbolReference
import eu.ddmore.mdl.mdl.util.MdlSwitch
import eu.ddmore.mdl.provider.BlockArgumentDefinitionProvider
import eu.ddmore.mdl.provider.ListDefinitionProvider
import eu.ddmore.mdl.provider.ListDefinitionTable
import eu.ddmore.mdl.utils.BlockUtils
import eu.ddmore.mdl.utils.DomainObjectModelUtils
import eu.ddmore.mdl.utils.MdlUtils
import eu.ddmore.mdllib.mdllib.SymbolDefinition
import java.util.HashSet
import java.util.Set
import org.eclipse.xtext.EcoreUtil2

import static extension eu.ddmore.mdl.utils.ExpressionConverter.convertToString

class ListObservationsWriter {
	static var ERROR_MSG = "<Error!>"
	
	
	extension BlockUtils bu = new BlockUtils
	extension PharmMLExpressionBuilder pem = new PharmMLExpressionBuilder
	extension MdlUtils mu = new MdlUtils
	extension ListDefinitionProvider ldp = new ListDefinitionProvider
	extension BlockArgumentDefinitionProvider badp = new BlockArgumentDefinitionProvider
	extension DistributionPrinter dp = new DistributionPrinter 
	extension PharmMLConverterUtils pcu = new PharmMLConverterUtils
	extension FunctionDefinitionPrinter fdp = new FunctionDefinitionPrinter
	extension DomainObjectModelUtils domu = new DomainObjectModelUtils
	
	
	def writeObservationModel(BlockStatement obsBlock){
		var idx = 1
		val obsStmts = obsBlock.statements 
		'''
			«IF !obsStmts.isEmpty»
				«FOR stmt : obsStmts»
					«IF stmt instanceof ListDefinition || stmt instanceof AnonymousListStatement»
						<ObservationModel blkId="om«idx»">
							«switch(stmt){
								ListDefinition:
									writeListObservations(stmt, idx += 1)
								AnonymousListStatement:
									writeListObservations(stmt, idx += 1)
							}»
						</ObservationModel>
					«ENDIF»
				«ENDFOR»
			«ENDIF»
		'''
	}
	
	def private writeDependentVariables(AbstractAttributeList it){
		val obsBlk = EcoreUtil2.getContainerOfType(eContainer, BlockStatement)
		val supplementaryDefns = new HashSet<SymbolDefinition>
		obsBlk.statements.forEach[s|
			if(s instanceof EquationDefinition) supplementaryDefns.add(s)
		]
		if(!supplementaryDefns.isEmpty){
			val Set<EquationDefinition> foundSet = new HashSet<EquationDefinition>
			for(atList :  it.attributeLists){
				val walker = new MdlSwitch<SymbolDefinition>(){
					override caseSymbolReference(SymbolReference object){
						return if(supplementaryDefns.contains(object.ref)) object.ref
					}
				}
				val iter = atList.eAllContents
				while(iter.hasNext && foundSet.size < supplementaryDefns.size){
					val node = iter.next
					val symb = walker.doSwitch(node)
					if(symb instanceof EquationDefinition) 
						foundSet.add(symb)
				}
			}
			'''
			«FOR v : foundSet»
				<ct:Variable symbId="«v.name»">
					«v.expression.expressionAsAssignment»
				</ct:Variable>
			«ENDFOR»
			'''
		}
		else ''''''
	}

	private def getStandardErrorName(AttributeList it){
		getStandardErrorName(getAttributeEnumValue('type'), getAttributeEnumValue('trans'))
	}
	
	private def getStandardErrorArgument(AttributeList it, String argName){
		getStandardErrorArgument(getAttributeEnumValue('type'), getAttributeEnumValue('trans'), argName)
	}
	
	private def writeStandardErrorModel(AttributeList it){
		'''
		<ErrorModel>
			<ct:Assign>
				<math:FunctionCall>
					<ct:SymbRef symbIdRef="«standardErrorName»"/>
					«FOR vp : it.attributes»
						«IF getStandardErrorArgument(vp.argumentName) != null»
							<math:FunctionArgument symbId="«getStandardErrorArgument(vp.argumentName)»">
								«IF !(vp.expression instanceof SymbolReference)»
									«vp.expression.pharmMLExpr»
								«ELSE»
									«vp.expression.pharmMLExpr»
								«ENDIF»
							</math:FunctionArgument>
						«ENDIF»
					«ENDFOR»
				</math:FunctionCall>
			</ct:Assign>
		</ErrorModel>
		'''
	}


	def writeUserDefinedObservation(AttributeList attList, String name, int idx){
		'''
		<ContinuousData>
			«writeDependentVariables(attList)»
			<General symbId="«name»">
				«IF attList.hasAttribute('value')»
					«attList.getAttributeExpression('value').expressionAsAssignment»
				«ENDIF»
			</General>
		</ContinuousData>
		'''
	}

	def writeContinuousObservation(AttributeList attList, String name, int idx){
		val predictionExpr = attList.getAttributeExpression('prediction')
		'''
		<ContinuousData>
			«writeDependentVariables(attList)»
			«IF attList.isTransformedOnlyRhsSide»
				<ct:Variable symbolType="real" symbId="«predictionExpr.singleSymbolRef?.name ?: "ERROR!"»">
					<ct:Assign>
						<math:Uniop op="«attList.getAttributeEnumValue('trans').pharmMLTransFunc»">
							«predictionExpr.pharmMLExpr»
						</math:Uniop>
					</ct:Assign>
				</ct:Variable>
			«ENDIF»
			«IF attList.isStandardErrorDefinition»
				<Standard symbId="«name»">
					«IF attList.isTransformedBothSides»
						<Transformation type="«attList.getAttributeEnumValue('trans').pharmMLTransFunc»"/>
					«ENDIF»
					<Output>
						«IF attList.isTransformedOnlyRhsSide»
							«predictionExpr.singleSymbolRef?.localSymbolReference ?: "ERROR!"»
						«ELSE»
							«predictionExpr.pharmMLExpr»
						«ENDIF»
					</Output>
					«writeStandardErrorModel(attList)»
					<ResidualError>
						«attList.getAttributeExpression('eps').pharmMLExpr»
					</ResidualError>
				</Standard>
			«ENDIF»
		</ContinuousData>
		'''
	} 
	
	def writeListObservations(AnonymousListStatement it, int idx){
		val type = list.getAttributeEnumValue('type')
			switch(type){
				case ListDefinitionTable::COUNT_OBS_VALUE:
					list.writeCountObservation
				case ListDefinitionTable::DISCRETE_OBS_VALUE:
					list.writeDiscreteObservation
				case ListDefinitionTable::CATEGORICAL_OBS_VALUE:
					list.writeDiscreteObservation
				case ListDefinitionTable::CONTINUOUS_OBS_VALUE:
					list.writeContinuousObWithRv
				
				default:
					"<Error!>"
			}
	}
	
	def writeListObservations(ListDefinition s, int idx){
		if(s.attributeLists.size == 1){
			val type = s.attributeLists.head.getAttributeEnumValue('type')
				switch(type){
					case ListDefinitionTable::TTE_OBS_VALUE:
						s.print_mdef_TimeToEventObservations
					case ListDefinitionTable::USER_DEFINED_OBS_VALUE:
						writeUserDefinedObservation(s.attributeLists.head, s.name, idx)
					default:
						writeContinuousObservation(s.attributeLists.head, s.name, idx)
				}
		}
		else{
			'''<Error!>'''
		}
	}
	
	def private isStandardErrorDefinition(AttributeList attList){
		true
	}
	
	def private isTransformedBothSides(AttributeList attList){
		attList.getAttributeEnumValue('lhsTrans') != null &&
		 attList.getAttributeEnumValue('trans') != null
	}

	def private isTransformedOnlyRhsSide(AttributeList attList){
		attList.getAttributeEnumValue('lhsTrans') == null &&
		 attList.getAttributeEnumValue('trans') != null
	}
	
	private def writeCountObservation(AttributeList it) {
		val rvSymbolRef = getAttributeExpression('variable')
		if(rvSymbolRef instanceof SymbolReference){
			val rvDefn = rvSymbolRef.ref
			if(rvDefn instanceof RandomVariableDefinition){
				'''
					<Discrete>
						<CountData>
							«writeDependentVariables(it)»
							<CountVariable symbId="«rvDefn.name»"/>
							<PMF transform="identity">
								«rvDefn.distn.writeDistribution»
							</PMF>
						</CountData>
					</Discrete>
				'''
			}
			else ERROR_MSG
		}
		else ERROR_MSG
	}
	
	
	private def writeDiscreteObservation(AttributeList it) {
		val rvSymbolRef = getAttributeExpression('variable')
		if(rvSymbolRef instanceof SymbolReference){
			val rvDefn = rvSymbolRef.ref
			if(rvDefn instanceof EnumerationDefinition){
				val categories = rvDefn.catDefn.categories
				val distn = rvDefn.distn
				'''
					<Discrete>
						<CategoricalData ordered="no">
							«writeDependentVariables(it)»
							<ListOfCategories>
								«FOR cat : categories»
									<Category symbId="«cat.name»"/>
								«ENDFOR»
							</ListOfCategories>
							<CategoryVariable symbId="«rvDefn.name»"/>
							<PMF>
								«IF distn instanceof SymbolReference»
									«writeDistribution(distn)»
								«ELSE»
									«ERROR_MSG»
								«ENDIF»
							</PMF>
						</CategoricalData>
					</Discrete>
				'''
			}
			else ERROR_MSG
		}
		else ERROR_MSG
	}
	
	private def print_mdef_TimeToEventObservations(ListDefinition s) {
		var name = s.name
		val haz = s.firstAttributeList.getAttributeExpression('hazard');
		val event = s.firstAttributeList.getAttributeEnumValue('event');
		val maxEvent = s.firstAttributeList.getAttributeExpression('maxEvent');
		'''
			<Discrete>
				<TimeToEventData>
					«writeDependentVariables(s.list)»
					<EventVariable symbId="«name»"/>
					<HazardFunction symbId="«haz.convertToString»">
						<ct:Assign>
							«haz.pharmMLExpr»
						</ct:Assign>
					</HazardFunction>
					«IF event != null»
						<Censoring censoringType="«event.getEventType»"/>
					«ENDIF»
					«IF maxEvent != null»
						<MaximumNumberEvents>
							«maxEvent.expressionAsAssignment»
						</MaximumNumberEvents>
					«ENDIF»
				</TimeToEventData>
			</Discrete>
		'''
	}
	
	
	def private SymbolDefinition getVarLevelFromRvBlock(BlockStatement it){
		val rvRef = blkArgs?.getArgumentExpression('level')
		if(rvRef instanceof SymbolReference){
			rvRef.ref
		}
		else null
	}
	
	
	def private writeContinuousObWithRv(AttributeList it){
		val rvSymbolRef = getAttributeExpression('variable')
		if(rvSymbolRef instanceof SymbolReference){
			val rvDefn = rvSymbolRef.ref
			if(rvDefn instanceof RandomVariableDefinition){
				val blk = rvDefn.parentBlock
				'''
				<ContinuousData>
					«writeDependentVariables(it)»
					<General symbId="«rvDefn.name»">
						<ct:VariabilityReference>
							«blk.varLevelFromRvBlock.symbolReference»
						</ct:VariabilityReference>
						«rvDefn.distn.writeDistribution»
					</General>
				</ContinuousData>
				'''
			}
			else ERROR_MSG
		}
		else ERROR_MSG
	} 
	
	
	def getEventType(String eventType){
		switch(eventType){
			case 'exact': '''rightCensored'''
			case 'intervalCensored': '''intervalCensored'''
			default: ''''''
		}
	}
}