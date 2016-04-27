package eu.ddmore.converter.mdl2pharmml08

import eu.ddmore.mdl.mdl.AnonymousListStatement
import eu.ddmore.mdl.mdl.AttributeList
import eu.ddmore.mdl.mdl.BlockStatement
import eu.ddmore.mdl.mdl.EnumerationDefinition
import eu.ddmore.mdl.mdl.ListDefinition
import eu.ddmore.mdl.mdl.RandomVariableDefinition
import eu.ddmore.mdl.mdl.SymbolReference
import eu.ddmore.mdl.provider.BlockArgumentDefinitionProvider
import eu.ddmore.mdl.provider.ListDefinitionProvider
import eu.ddmore.mdl.provider.ListDefinitionTable
import eu.ddmore.mdl.utils.DomainObjectModelUtils
import eu.ddmore.mdl.utils.MdlUtils
import eu.ddmore.mdllib.mdllib.Expression
import eu.ddmore.mdllib.mdllib.SymbolDefinition

import static extension eu.ddmore.mdl.utils.ExpressionConverter.convertToString

class ListObservationsWriter {
	static var ERROR_MSG = "<Error!>"
	
	
	extension MdlUtils mu = new MdlUtils
	extension ListDefinitionProvider ldp = new ListDefinitionProvider
	extension BlockArgumentDefinitionProvider badp = new BlockArgumentDefinitionProvider
	extension PharmMLExpressionBuilder peb = new PharmMLExpressionBuilder 
	extension DistributionPrinter dp = new DistributionPrinter 
	extension PharmMLConverterUtils pcu = new PharmMLConverterUtils
	extension FunctionDefinitionPrinter fdp = new FunctionDefinitionPrinter
	extension DomainObjectModelUtils domu = new DomainObjectModelUtils
	
	
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
						«IF getStandardErrorArgument(vp.attributeName) != null»
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
		'''
		<ObservationModel blkId="om«idx»">
			«switch(type){
				case ListDefinitionTable::COUNT_OBS_VALUE:
					list.writeCountObservation
				case ListDefinitionTable::DISCRETE_OBS_VALUE:
					list.writeDiscreteObservation
				case ListDefinitionTable::CATEGORICAL_OBS_VALUE:
					list.writeDiscreteObservation
//					s.print_mdef_CategoricalObservations
//				case ListDefinitionTable::TTE_OBS_VALUE:
//					s.print_mdef_TimeToEventObservations
				case ListDefinitionTable::CONTINUOUS_OBS_VALUE:
					list.writeContinuousObWithRv
				
				default:
					"<Error!>"
			}»
		</ObservationModel>
		'''
	}

	def writeListObservations(ListDefinition s, int idx){
		if(s.attributeLists.size == 1){
			val type = s.attributeLists.head.getAttributeEnumValue('type')
			'''
			<ObservationModel blkId="om«idx»">
				«switch(type){
					case ListDefinitionTable::TTE_OBS_VALUE:
						s.print_mdef_TimeToEventObservations
					case ListDefinitionTable::USER_DEFINED_OBS_VALUE:
						writeUserDefinedObservation(s.attributeLists.head, s.name, idx)
					default:
						writeContinuousObservation(s.attributeLists.head, s.name, idx)
				}»
			</ObservationModel>
			'''
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
	
//	private def getInverseFunction(Expression linkFunction, Expression paramVar){
//		switch(linkFunction){
//			SymbolReference case linkFunction.func == "log": return '''
//			<math:Uniop op="exp">
//				«paramVar.pharmMLExpr»
//			</math:Uniop>
//			'''
//			SymbolReference case linkFunction.func == "identity": return '''
//				«paramVar.pharmMLExpr»
//			'''
//		}
//	}
	
	private def writeCountObservation(AttributeList it) {
		val rvSymbolRef = getAttributeExpression('variable')
		if(rvSymbolRef instanceof SymbolReference){
			val rvDefn = rvSymbolRef.ref
			if(rvDefn instanceof RandomVariableDefinition){
//				val blk = rvDefn.parentBlock
//				val linkFunction = getAttributeExpression('link');
//				val paramVar = (rvDefn.distn as SymbolReference).getFunctionArgumentValue("lambda");
				'''
					<Discrete>
						<CountData>
«««							«IF paramVar != null»
«««								<!-- Note that this parameter is local to this block, but uses the same name
«««									as the lambda argument. --> 
«««								<PopulationParameter symbId="«paramVar.convertToString»">
«««									<ct:Assign>
«««										«IF linkFunction != null»
«««											«getInverseFunction(linkFunction, paramVar)»
«««										«ELSE»
«««											«paramVar.pharmMLExpr»
«««										«ENDIF»
«««									</ct:Assign>
«««								</PopulationParameter>
«««							«ENDIF»
							<CountVariable symbId="«rvDefn.name»"/>
							<PMF transform="identity">
								«rvDefn.distn.writeUncertMlDistribution»
							</PMF>
						</CountData>
					</Discrete>
				'''
			}
			else ERROR_MSG
		}
		else ERROR_MSG
	}
	
	
//	private def getSuccessCategory(SymbolReference it){
//		switch(func){
//			case "Bernoulli":
//				getArgumentExpression('category')
//			case "Binomial":
//				getArgumentExpression('successCategory')
//		}?.convertToString
//	}
	
	
//	private def createCategoriesOrderedBySuccess(Set<String> categories, String successCategory){
//		val retVal = new ArrayList<String>(categories.size)
//		retVal.add(successCategory)
//		retVal.addAll(categories.filter[it != successCategory])
//		retVal
//	}
	
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
							<ListOfCategories>
								«FOR cat : categories»
									<Category symbId="«cat.name»"/>
								«ENDFOR»
							</ListOfCategories>
							<CategoryVariable symbId="«rvDefn.name»"/>
							<PMF>
								«IF distn instanceof SymbolReference»
									«printDiscreteDistribution(distn)»
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
	
//	private def getCategories(Expression categories){
//		val catVals = new HashMap<String, Expression>
//		switch(categories){
//			EnumExpression:{
//				val catDefnExpr = categories.catDefn as CategoricalDefinitionExpr
//				catDefnExpr.categories.forEach[
//					catVals.put(name, mappedTo)
//				]
//			}
//		}
//		catVals
//	}
	
//	private def print_mdef_CategoricalObservations(ListDefinition s) {
//			val define = column.list.getAttributeExpression(ListDefinitionTable::USE_ATT);
//			// get an EnumExpression here - use this to get the categories.
//			switch(define){
//				EnumExpression:{
//					val catDefnExpr = define.catDefn as CategoricalDefinitionExpr
//					for(catVal : catDefnExpr.categories){
//					res = res + '''
//						<ds:Map modelSymbol="«catVal.name»" dataSymbol="«catVal.mappedTo.convertToString»"/>
//						'''
//					}
//				}
//			}
//		val categories = s.firstAttributeList.getAttributeExpression(ListDefinitionTable::OBS_TYPE_ATT);
//		val listCats = new ArrayList<String>
//		val catVals = new HashMap<String, Expression>
//		switch(categories){
//			EnumExpression:{
//				val catDefnExpr = categories.catDefn as CategoricalDefinitionExpr
//				catDefnExpr.categories.forEach[
//					listCats.add(name)
//					catVals.put(name, mappedTo)
//				]
//			}
//		}
//		val catVals = categories.categories
//		'''
//			<Discrete>
//				<CategoricalData>
//					<ListOfCategories>
//						«FOR cat : catVals.keySet»
//							<Category symbId="«cat»"/>
//						«ENDFOR»
//					</ListOfCategories>
//					<CategoryVariable symbId="«s.name»"/>
//					«FOR cat : catVals.keySet»
//						<ProbabilityAssignment>
//							<Probability linkFunction="identity">
//								<math:LogicBinop op="eq">
//									<ct:SymbRef symbIdRef="«s.name»"/>
//									<ct:SymbRef symbIdRef="«cat»"/>
//								</math:LogicBinop>
//							</Probability>
//							«catVals.get(cat).expressionAsAssignment»
//						</ProbabilityAssignment>
//					«ENDFOR»
//				</CategoricalData>
//			</Discrete>
//		'''
//	}


	private def print_mdef_TimeToEventObservations(ListDefinition s) {
		var name = s.name
		val haz = s.firstAttributeList.getAttributeExpression('hazard');
		val event = s.firstAttributeList.getAttributeEnumValue('event');
		val maxEvent = s.firstAttributeList.getAttributeExpression('maxEvent');
		'''
			<Discrete>
				<TimeToEventData>
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
					<General symbId="«rvDefn.name»">
						<ct:VariabilityReference>
							«blk.varLevelFromRvBlock.symbolReference»
						</ct:VariabilityReference>
						«rvDefn.distn.writeUncertMlDistribution»
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

	def writeUncertMlDistribution(Expression functionCall){
		if(functionCall instanceof SymbolReference)
			functionCall.writeUncertmlDist
		else
			ERROR_MSG
	}
}