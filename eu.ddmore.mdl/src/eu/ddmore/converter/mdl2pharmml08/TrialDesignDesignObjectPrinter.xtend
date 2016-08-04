package eu.ddmore.converter.mdl2pharmml08

import eu.ddmore.mdl.mdl.AttributeList
import eu.ddmore.mdl.mdl.BlockStatement
import eu.ddmore.mdl.mdl.EquationDefinition
import eu.ddmore.mdl.mdl.ListDefinition
import eu.ddmore.mdl.mdl.MclObject
import eu.ddmore.mdl.mdl.PropertyStatement
import eu.ddmore.mdl.mdl.SubListExpression
import eu.ddmore.mdl.mdl.SymbolReference
import eu.ddmore.mdl.mdl.ValuePair
import eu.ddmore.mdl.provider.BlockDefinitionTable
import eu.ddmore.mdl.provider.ListDefinitionProvider
import eu.ddmore.mdl.provider.MogDefinitionProvider
import eu.ddmore.mdl.provider.SublistDefinitionProvider
import eu.ddmore.mdl.utils.BlockUtils
import eu.ddmore.mdl.utils.DomainObjectModelUtils
import eu.ddmore.mdl.utils.ExpressionUtils
import eu.ddmore.mdl.utils.MdlUtils
import eu.ddmore.mdllib.mdllib.Expression
import java.util.Collections
import org.eclipse.xtext.EcoreUtil2

import static eu.ddmore.converter.mdl2pharmml08.Constants.*

class TrialDesignDesignObjectPrinter implements TrialDesignObjectPrinter {
	extension MdlUtils mu = new MdlUtils 
	extension PharmMLExpressionBuilder peb = new PharmMLExpressionBuilder 
	extension ListDefinitionProvider ldp = new ListDefinitionProvider
	extension SublistDefinitionProvider sldp = new SublistDefinitionProvider
	extension BlockUtils bu = new BlockUtils
	extension DomainObjectModelUtils dom = new DomainObjectModelUtils
//	extension ConstantEvaluation ce = new ConstantEvaluation
//	extension TypeSystemProvider tsp = new TypeSystemProvider
//	extension MdlLibUtils mlu = new MdlLibUtils
//	extension LibraryUtils lib = new LibraryUtils
	extension ExpressionUtils eu = new ExpressionUtils
	extension MogDefinitionProvider mdp = new MogDefinitionProvider

	val public static INTVN_TYPE_ATT_NAME = 'type'
	val public static INTVN_TYPE_BOLUS_VALUE = 'bolus'
	val public static INTVN_TYPE_INFUSION_VALUE = 'infusion'
	val public static SSINTERVAL_ATT_NAME = 'ssInterval'
	val public static SSEND_ATT_NAME = 'ssEnd'
	val public static INPUT_ATT_NAME = 'input'
	val public static AMT_ATT_NAME = 'amount'
	val public static DOSE_TIME_ATT_NAME = 'doseTime'
	val public static DOSE_TIME_VAR_ATT_NAME = 'lastDoseTimeVar'
	val public static RATE_ATT_NAME = 'rate'
	val public static DURATION_ATT_NAME = 'duration'
	val public static SCALE_ATT_NAME = 'p'
	val public static INTVN_TYPE_COMBI_VALUE = 'combi'
	val public static COMBINATION_ATT_NAME = 'combination'
	val public static START_ATT_NAME = 'start'	
	val public static END_ATT_NAME = 'end'
	val public static INTVN_TYPE_RESET_ALL_VALUE = 'resetAll'	
	val public static INTVN_TYPE_RESET_VALUE = 'reset'	
	val public static RESET_VALUE_ATT = 'value'	
	val public static RESET_TIME_ATT = 'resetTime'	
	val public static RESET_VARIABLE = 'variable'
	val public static RESET_ATT = 'reset'
	val public static ARM_SIZE_ATT = 'armSize'
	val public static INTSEQ_ATT = 'interventionSequence'
	val public static INTSEQ_ADMIN_ATT = 'admin'
	val public static SAMPSEQ_ATT = 'samplingSequence'
	val public static SAMPSEQ_SAMP_ATT = 'sample'
	val public static OCC_SEQ_ATT = 'occasionSequence'
	val public static OCC_SEQ_OCC_ATT = 'occasion'
	val public static OCC_LEVEL_ATT = 'level'
	val public static SAMP_NUM_TIMES = 'numTimes'
	val public static SAMP_SAMP_TIMES = 'sampleTime'
	val public static SAMP_OUTCOME = 'outcome'
	val public static SAMP_COMBI = 'combination'
	val public static SAMP_START = 'start'
	val public static SAMP_RELATIVE = 'relative'
	val public static SAMP_TYPE_ATT_NAME = 'type'
	val public static SAMP_TYPE_SIMPLE_VALUE = 'simple'
	val public static SAMP_TYPE_COMBI_VALUE = 'combi'
	val public static SAMP_COMBI_ATT = 'combination'
	val public static DS_ELEMENT_ATT = 'element'
	val public static DS_ELEMENT_BOLUSAMT_VALUE = 'bolusAmt'
	val public static DS_ELEMENT_INFAMT_VALUE = 'infAmt'
	val public static DS_ELEMENT_DURATION_VALUE = 'duration'
	val public static DS_ELEMENT_SAMPLE_TIMES_VALUE = 'sampleTime'
	val public static DS_ELEMENT_NUMBER_TIMES_VALUE = 'numberTimes'
	val public static DS_ELEMENT_COVARIATE_VALUE = 'covariate'
	val public static DS_ELEMENT_NUMBER_ARMS_VALUE = 'numberArms'
	val public static DS_ELEMENT_ARM_SIZE_VALUE = 'armSize'
	val public static DS_ELEMENT_PARAM_VALUE = 'parameter'
	val public static DS_ELEMENT_DOSE_TIME_VALUE = 'doseTime'
	
	val public static COV_MOD_OID = "desCovModOid"

	val public static DS_DISCRETE_ATT = 'discrete'
	val public static DS_RANGE_ATT = 'range'
	val public static DS_OBJREF_ATT = 'objRef'

	val public static TOTAL_SIZE_PROP = 'totalSize'
	val public static NUM_SAMPLES_PROP = 'numSamples'
	val public static NUM_ARMS_PROP = 'numArms'
	val public static SAME_TIMES_PROP = 'sameTimes'
	val public static TOTAL_COST_PROP = 'totalCost'

	val MclObject mObj
	val MclObject designObj
	val AbstractParameterWriter priorDsWriter
	
	new(MclObject mog, AbstractParameterWriter priorDsWriter){
		this.mObj = mog.mdlObj
		this.designObj = mog.designObj
		this.priorDsWriter = priorDsWriter
	}


	override writeTrialDesign()'''
		<TrialDesign xmlns="«xmlns_design»">
			«priorDsWriter.writeAllDatasets»
			«IF mObj != null && designObj != null»
				«FOR blk : designObj.getBlocksByName(BlockDefinitionTable::DES_DESIGN_PARAMS)»
					«blk.writeDesignParameters»
				«ENDFOR»
				«FOR blk : designObj.getBlocksByName(BlockDefinitionTable::DES_INTERVENTION_BLK)»
					«blk.writeInterventions»
				«ENDFOR»
				«FOR blk : designObj.getBlocksByName(BlockDefinitionTable::DES_SAMPLING_BLK)»
					«blk.writeSampling»
				«ENDFOR»
				«FOR blk : designObj.getBlocksByName(BlockDefinitionTable::COVARIATE_BLK_NAME)»
					«blk.writeCovariates»
				«ENDFOR»
				«FOR blk : designObj.getBlocksByName(BlockDefinitionTable::DES_DESIGN_SPACE_BLK)»
					«blk.writeDesignSpaces»
				«ENDFOR»
				«FOR blk : designObj.getBlocksByName(BlockDefinitionTable::DES_STUDY_DESIGN)»
					«blk.writeStudyDesign»
				«ENDFOR»			«ENDIF»
		</TrialDesign>
	'''	

	def writeDesignParameters(BlockStatement designParamsBlk)'''
		«FOR e :designParamsBlk.statements»
			«IF e instanceof EquationDefinition»
				«e.writeDesignParameter»
			«ENDIF»
		«ENDFOR»
	'''

	def private writeDesignParameter(EquationDefinition ed)'''
		<mdef:DesignParameter symbId="«ed.name»">
			«IF ed.expression != null»
				«ed.expression.expressionAsAssignment»
			«ENDIF»
		</mdef:DesignParameter>
	'''
	
	def writeInterventions(BlockStatement designParamsBlk)'''
		<Interventions>
			«FOR stmt : designParamsBlk.statements»
				«IF stmt instanceof ListDefinition»
					«switch(stmt.firstAttributeList.getAttributeEnumValue(INTVN_TYPE_ATT_NAME)){
						case(INTVN_TYPE_BOLUS_VALUE):
							stmt.writeBolusDosing
						case(INTVN_TYPE_INFUSION_VALUE):
							stmt.writeInfusionDosing
						case(INTVN_TYPE_COMBI_VALUE):
							stmt.writeInterventionCombination
						case(INTVN_TYPE_RESET_ALL_VALUE):
							stmt.writeResetAll
						case(INTVN_TYPE_RESET_VALUE):
							stmt.writeReset
					}»
				«ENDIF»
			«ENDFOR»
		</Interventions>
	'''
	
	def private getModelVar(SubListExpression it, String attName){
		val targetVar = getAttributeExpression(attName)
		if(targetVar instanceof SymbolReference){
			mObj.findMdlSymbolDefn(targetVar.ref.name)
		}
		else null
	}
	
	def private getModelVar(AttributeList it, String attName){
		val targetVar = getAttributeExpression(attName)
		if(targetVar instanceof SymbolReference){
			mObj.findMdlSymbolDefn(targetVar.ref.name)
		}
		else null
	}
	
	def writeTargetMapping(AttributeList it){
		val targetVar = getAttributeExpression(INPUT_ATT_NAME)
		if(targetVar instanceof SymbolReference){
			val mdlTgtVar = mObj.findMdlSymbolDefn(targetVar.ref.name)
			val mdlVarBlk = EcoreUtil2.getContainerOfType(mdlTgtVar, BlockStatement)
			'''
				<TargetMapping blkIdRef="sm">
					«IF mdlVarBlk.blkId.name == BlockDefinitionTable::MDL_CMT_BLK»
						<ds:Map admNumber="«PKMacrosPrinter::INSTANCE.getCompartmentNum(mdlTgtVar)»"/>
					«ELSE»
						<ds:Map modelSymbol="«mdlTgtVar.name»"/>
					«ENDIF»
				</TargetMapping>
			'''
		}
		else{
			'''Error!'''
		}
	}
	
	def writeCommonDosing(AttributeList it)'''
			«IF hasAttribute(AMT_ATT_NAME)»
				<DoseAmount>
					«writeTargetMapping»
					«IF hasAttribute(SCALE_ATT_NAME)»
						«IF getAttributeExpression(AMT_ATT_NAME).vector.size == 1»
							<ct:Assign>
								<math:Binop op="times">
									«getAttributeExpression(AMT_ATT_NAME).vector.get(0).pharmMLExpr»
									«getAttributeExpression(SCALE_ATT_NAME).pharmMLExpr»
								</math:Binop>
							</ct:Assign>
						«ELSE»
							<ct:Assign>
								<ct:Vector>
									<ct:VectorElements>
										«FOR e : getAttributeExpression(AMT_ATT_NAME).vector»
											<ct:Assign>
												<math:Binop op="times">
													«e.pharmMLExpr»
													«getAttributeExpression(SCALE_ATT_NAME).pharmMLExpr»
												</math:Binop>
											</ct:Assign>
										«ENDFOR»
									</ct:VectorElements>
								</ct:Vector>
							</ct:Assign>
						«ENDIF»
					«ELSE»
						«IF getAttributeExpression(AMT_ATT_NAME).vector.size == 1»
							«getAttributeExpression(AMT_ATT_NAME).vector.get(0).expressionAsAssignment»
						«ELSE»
							«getAttributeExpression(AMT_ATT_NAME).expressionAsAssignment»
						«ENDIF»
					«ENDIF»
				</DoseAmount>
			«ENDIF»
			«IF hasAttribute(SSEND_ATT_NAME)»
				<SteadyState>
					«IF hasAttribute(SSEND_ATT_NAME)»
						<EndTime>
							«getAttributeExpression(SSEND_ATT_NAME).expressionAsAssignment»
						</EndTime>
					«ENDIF»
					«IF hasAttribute(SSINTERVAL_ATT_NAME)»
						<Interval>
							«getAttributeExpression(SSINTERVAL_ATT_NAME).expressionAsAssignment»
						</Interval>
					«ENDIF»
				</SteadyState>
			«ENDIF»
			«IF hasAttribute(DOSE_TIME_ATT_NAME)»
				<DosingTimes>
					«IF hasAttribute('lastDoseTimeVar')»
						«mObj.findMdlSymbolDefn(getAttributeExpression('lastDoseTimeVar').symbolRef.ref.name).symbolReference»
					«ENDIF»
					«getAttributeExpression(DOSE_TIME_ATT_NAME).expressionAsAssignment»
				</DosingTimes>
			«ENDIF»	'''
	
	
	def writeBolusDosing(ListDefinition ld){
		ld.writeAdministration[
		'''
		<Bolus>
			«writeCommonDosing»
		</Bolus>
		'''
		]
	}
	
	def writeInfusionDosing(ListDefinition ld){
		ld.writeAdministration[
		'''
		<Infusion>
			«writeCommonDosing»
			«IF hasAttribute(RATE_ATT_NAME)»
				<Rate>
					«getAttributeExpression(RATE_ATT_NAME).expressionAsAssignment»
				</Rate>
			«ENDIF»
			«IF hasAttribute(DURATION_ATT_NAME)»
				<Duration>
					«getAttributeExpression(DURATION_ATT_NAME).expressionAsAssignment»
				</Duration>
			«ENDIF»
		</Infusion>
		'''
		]
	}

	def writeInterventionCombination(ListDefinition it)'''
		<InterventionsCombination oid="«name»">
			<Interventions>
				«IF firstAttributeList.getAttributeExpression(COMBINATION_ATT_NAME).vector != null»
					«FOR expr : firstAttributeList.getAttributeExpression(COMBINATION_ATT_NAME).vector»
						<InterventionRef oidRef="«expr.symbolRef?.ref.name»"/>
					«ENDFOR»
					«IF firstAttributeList.hasAttribute(START_ATT_NAME)»
						<Start>
							«firstAttributeList.getAttributeExpression(START_ATT_NAME).expressionAsAssignment»
						</Start>
					«ENDIF»
					«IF firstAttributeList.hasAttribute(END_ATT_NAME)»
						<End>
							«firstAttributeList.getAttributeExpression(END_ATT_NAME).expressionAsAssignment»
						</End>
					«ENDIF»
				«ENDIF»
			</Interventions>
		</InterventionsCombination>
	'''
	
	def writeAdministration(ListDefinition it, (AttributeList) => String dosingLambda)'''
		<Administration oid="«name»">
			«dosingLambda.apply(firstAttributeList)»
		</Administration>
		'''

	def writeAction(ListDefinition it, (AttributeList) => String resetLambda)'''
		<Action oid="«name»">
			«resetLambda.apply(firstAttributeList)»
		</Action>
		'''


	def writeReset(ListDefinition ld){
		ld.writeAction[
		'''
			<Washout>
				«FOR vtr : getAttributeExpression(RESET_ATT).vector»
					<VariableToReset>
						«IF vtr instanceof SubListExpression»
							«vtr.getModelVar(RESET_VARIABLE).symbolReference»
							«IF vtr.hasAttribute(RESET_VALUE_ATT)»
								<ResetValue>
									«vtr.getAttributeExpression(RESET_VALUE_ATT).pharmMLExpr»
								</ResetValue>
							«ENDIF»
							«IF vtr.hasAttribute(RESET_TIME_ATT)»
								<ResetTime>
									«vtr.getAttributeExpression(RESET_TIME_ATT).pharmMLExpr»
								</ResetTime>
							«ENDIF»
						«ENDIF»
					</VariableToReset>
				«ENDFOR»
			</Washout>
		'''
		]
	}

	def writeResetAll(ListDefinition ld){
		ld.writeAction[
		'''
			<Washout>
				<VariableToReset>
					<FullReset/>
				</VariableToReset>
			</Washout>
		'''
		]
	}

	def writeStudyDesign(BlockStatement studyDesignBlk)'''
		<Arms>
			«FOR stmt : studyDesignBlk.statements.filter[it instanceof PropertyStatement]»
				«IF stmt instanceof PropertyStatement»
					«FOR prop : stmt.properties»
						«writeStudyDesignProperty(prop)»
					«ENDFOR»
				«ENDIF»
			«ENDFOR»
			«FOR stmt : studyDesignBlk.statements.filter[it instanceof ListDefinition]»
				«IF stmt instanceof ListDefinition»
					«writeArm(stmt)»
				«ENDIF»
			«ENDFOR»
		</Arms>
	'''

	def writeArm(ListDefinition it)'''
		<Arm oid="«name»">
			«IF firstAttributeList.hasAttribute(ARM_SIZE_ATT)»
				<ArmSize>
					«firstAttributeList.getAttributeExpression(ARM_SIZE_ATT).expressionAsAssignment»
				</ArmSize>
			«ENDIF»
			«IF firstAttributeList.hasAttribute(INTSEQ_ATT)»
				«FOR intSeqSl : firstAttributeList.getAttributeExpression(INTSEQ_ATT).vector»
					<InterventionSequence>
						«IF intSeqSl instanceof SubListExpression»
							<InterventionList>
								«FOR intRef : intSeqSl.getAttributeExpression(INTSEQ_ADMIN_ATT).vector»
									<InterventionRef oidRef="«intRef.symbolRef?.ref?.name?: 'Error!'»"/>
								«ENDFOR»
							</InterventionList>
							«IF intSeqSl.hasAttribute(START_ATT_NAME)»
								<Start>
									«intSeqSl.getAttributeExpression(START_ATT_NAME).expressionAsAssignment»
								</Start>
							«ENDIF»
						«ENDIF»
					</InterventionSequence>
				«ENDFOR»
			«ENDIF»
			«IF firstAttributeList.hasAttribute(SAMPSEQ_ATT)»
				«FOR obsSeqSl : firstAttributeList.getAttributeExpression(SAMPSEQ_ATT).vector»
					<ObservationSequence>
						«IF obsSeqSl instanceof SubListExpression»
							<ObservationList>
								«FOR obsRef : obsSeqSl.getAttributeExpression(TrialDesignDesignObjectPrinter::SAMPSEQ_SAMP_ATT).vector»
									<ObservationRef oidRef="«obsRef.symbolRef?.ref?.name?: 'Error!'»"/>
								«ENDFOR»
							</ObservationList>
							«IF obsSeqSl.hasAttribute(START_ATT_NAME)»
								<Start>
									«obsSeqSl.getAttributeExpression(START_ATT_NAME).expressionAsAssignment»
								</Start>
							«ENDIF»
						«ENDIF»
					</ObservationSequence>
				«ENDFOR»
			«ENDIF»
			«IF firstAttributeList.hasAttribute(OCC_SEQ_ATT)»
				«FOR occSeqSl : firstAttributeList.getAttributeExpression(OCC_SEQ_ATT).vector»
					<OccasionSequence>
						«IF occSeqSl instanceof SubListExpression»
							«occSeqSl.writeOccasionList(name)»
						«ENDIF»
					</OccasionSequence>
				«ENDFOR»
			«ENDIF»
		</Arm>
	'''
		
		
	def private writeOccasionList(SubListExpression it, String armName){
		val occList = getAttributeExpression(TrialDesignDesignObjectPrinter::OCC_SEQ_OCC_ATT).vector
		val startList = if(hasAttribute(START_ATT_NAME))
					getAttributeExpression(START_ATT_NAME).vector
				else Collections::emptyList
		var idx = 0
		'''
		<OccasionList oid="«createObsListName(armName)»">
			<ct:VariabilityReference>
				«getModelVar(OCC_LEVEL_ATT).symbolReference»
			</ct:VariabilityReference>
			«FOR obsId : occList»
				<Occasion oid="«createObsName(armName, obsId.integerValue)»">
					«IF !startList.isEmpty && idx < occList.size»
						<Start>
							«startList.get(idx++).expressionAsAssignment»
						</Start>
					«ENDIF»
				</Occasion>
			«ENDFOR»
		</OccasionList>
		'''
	}
		
	def private createObsListName(SubListExpression it, String armName){
		val varLvlName = getAttributeExpression(OCC_LEVEL_ATT)?.symbolRef?.ref?.name
		armName + "_" + varLvlName
	} 
		
	def private createObsName(SubListExpression it, String armName, int obsId){
		createObsListName(armName) + '_' + obsId 
	} 
		
	def writeStudyDesignProperty(ValuePair it){
		switch(argumentName){
			case(TOTAL_COST_PROP):
				writeProperty('TotalCost', expression)
			case(TOTAL_SIZE_PROP):
				writeProperty('TotalSize', expression)
			case(NUM_ARMS_PROP):
				writeProperty('NumberArms', expression)
			case(NUM_SAMPLES_PROP):
				writeProperty('NumberSamples', expression)
			case(SAME_TIMES_PROP):
				writeProperty('SameTimes', expression)
			case(ARM_SIZE_ATT):
				writeProperty('ArmSize', expression)
		}
	}
	
	
	def private writeProperty(String element, Expression value)'''
		<«element»>
			«value.expressionAsAssignment»
		</«element»>
	'''
		
	def writeSampling(BlockStatement it)'''
		<Observations>
			«FOR stmt : statements»
				«IF stmt instanceof ListDefinition»
					«IF stmt.firstAttributeList.getAttributeEnumValue(SAMP_TYPE_ATT_NAME) == SAMP_TYPE_SIMPLE_VALUE»
						«writeSimpleSampling(stmt)»
					«ENDIF»
				«ENDIF»
			«ENDFOR»
«««			Simple first then followed by combi
			«FOR stmt : statements»
				«IF stmt instanceof ListDefinition»
					«IF stmt.firstAttributeList.getAttributeEnumValue(SAMP_TYPE_ATT_NAME) == SAMP_TYPE_COMBI_VALUE»
						«writeCombiSampling(stmt)»
					«ENDIF»
				«ENDIF»
			«ENDFOR»
		</Observations>
	'''
		
	def writeSimpleSampling(ListDefinition it)'''
		<Observation oid="«name»">
			«IF firstAttributeList.hasAttribute(SAMP_NUM_TIMES)»
				<NumberTimes>
					«firstAttributeList.getAttributeExpression(SAMP_NUM_TIMES).expressionAsAssignment»
				</NumberTimes>
			«ENDIF»
			«IF firstAttributeList.hasAttribute(SAMP_SAMP_TIMES)»
				<ObservationTimes>
					«firstAttributeList.getAttributeExpression(SAMP_SAMP_TIMES).expressionAsAssignment»
				</ObservationTimes>
			«ENDIF»
			<Continuous>
				«firstAttributeList.getModelVar(SAMP_OUTCOME).symbolReference»
			</Continuous>
		</Observation>
	'''

	def writeCombiSampling(ListDefinition it)'''
		<ObservationsCombination oid="«name»">
			<Observations>
				«FOR obRef : firstAttributeList.getAttributeExpression(SAMP_COMBI).vector»
					<ObservationRef oidRef="«obRef.symbolRef?.ref?.name ?: '<Error!/>'»"/>
				«ENDFOR»
				«IF firstAttributeList.hasAttribute(SAMP_START)»
					<Start>
						«firstAttributeList.getAttributeExpression(SAMP_START).expressionAsAssignment»
					</Start>
				«ENDIF»
			</Observations>
			«IF firstAttributeList.hasAttribute(SAMP_RELATIVE)»
				<Relative>
					«firstAttributeList.getAttributeExpression(SAMP_RELATIVE).expressionAsAssignment»
				</Relative>
			«ENDIF»
		</ObservationsCombination>
	'''


	def writeDsParameterBlock(AttributeList it)
	'''
		«FOR p : getAttributeExpression(DS_OBJREF_ATT).vector»
			«p.pharmMLExpr»
		«ENDFOR»
		«writeDiscreteOrRange»
	'''

//	def writeDsDosingBlock(AttributeList it)
//	'''
//		«FOR p : getAttributeExpression(DS_OBJREF_ATT).vector»
//			<InterventionRef oidRef="«p.symbolRef.ref.name»"/>
//		«ENDFOR»
//		<DoseAmount>
//			«writeDiscreteOrRange»
//		</DoseAmount>
//	'''

	def writeDsElementBlock(AttributeList it, String refName, String timesName)'''
		«FOR p : getAttributeExpression(DS_OBJREF_ATT).vector»
			<«refName» oidRef="«p.symbolRef.ref.name»"/>
		«ENDFOR»
		<«timesName»>
			«writeDiscreteOrRange»
		</«timesName»>
	'''

	def writeDsCovariateBlock(AttributeList it)'''
		«FOR p : getAttributeExpression(DS_OBJREF_ATT).vector SEPARATOR	'''

</DesignSpace>
<DesignSpace>'''»
			<CovariateModelRef oidRef="«COV_MOD_OID»"/>
			<CovariateRef symbIdRef="«p.symbolRef.ref.name»">
				<mdef:Continuous>
					«writeDiscreteOrRange»
				</mdef:Continuous>
			</CovariateRef>
		«ENDFOR»
	'''

	def writeDiscreteOrRange(AttributeList it)'''
		«IF hasAttribute(DS_DISCRETE_ATT)»
			«getAttributeExpression(DS_DISCRETE_ATT).expressionAsAssignment»
		«ELSEIF hasAttribute(DS_RANGE_ATT)»
			«getAttributeExpression(DS_RANGE_ATT).expressionAsRange»
		«ENDIF»
	'''

	def writeDesignSpace(AttributeList it)'''
		<DesignSpace>
			«switch(getAttributeEnumValue(DS_ELEMENT_ATT)){
				case(DS_ELEMENT_BOLUSAMT_VALUE),
				case(DS_ELEMENT_INFAMT_VALUE):
					writeDsElementBlock('InterventionRef', 'DoseAmount')
				case(DS_ELEMENT_DURATION_VALUE):
					writeDsElementBlock('InterventionRef', 'Duration')
				case(DS_ELEMENT_SAMPLE_TIMES_VALUE):
					writeDsElementBlock('ObservationRef', 'ObservationTimes')
				case(DS_ELEMENT_NUMBER_TIMES_VALUE):
					writeDsElementBlock('ObservationRef', 'NumberTimes')
				case(DS_ELEMENT_COVARIATE_VALUE):
					writeDsCovariateBlock
				case(DS_ELEMENT_NUMBER_ARMS_VALUE):
					writeDsElementBlock('ArmRef', 'NumberArms')
				case(DS_ELEMENT_ARM_SIZE_VALUE):
					writeDsElementBlock('ArmRef', 'ArmSize')
				case(DS_ELEMENT_PARAM_VALUE):
					writeDsParameterBlock
				case(DS_ELEMENT_DOSE_TIME_VALUE):
					writeDsElementBlock('InterventionRef', 'DosingTimes')
			}»
		</DesignSpace>
	'''

	def writeDesignSpaces(BlockStatement it)'''
		<DesignSpaces>
			«FOR stmt : statements»
				«IF stmt instanceof ListDefinition»
					«writeDesignSpace(stmt.firstAttributeList)»
				«ENDIF»
			«ENDFOR»
		</DesignSpaces>
	'''
		
	def writeCovariate(EquationDefinition it)'''
		<Covariate symbId="«name»">
			<mdef:Continuous>
				«expression.expressionAsAssignment»
			</mdef:Continuous>
		</Covariate>
	'''

	def writeCovariates(BlockStatement it)'''
		<Covariates>
			<CovariateModel oid="«COV_MOD_OID»">
				<CovariateModelRef blkIdRef="cm"/>
				«FOR stmt : statements»
					«IF stmt instanceof EquationDefinition»
						«writeCovariate(stmt)»
					«ENDIF»
				«ENDFOR»
			</CovariateModel>
		</Covariates>
	'''
}