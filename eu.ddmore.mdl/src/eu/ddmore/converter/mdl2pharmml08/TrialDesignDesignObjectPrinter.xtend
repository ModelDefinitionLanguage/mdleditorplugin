package eu.ddmore.converter.mdl2pharmml08

import eu.ddmore.mdl.mdl.AttributeList
import eu.ddmore.mdl.mdl.BlockStatement
import eu.ddmore.mdl.mdl.EquationDefinition
import eu.ddmore.mdl.mdl.ListDefinition
import eu.ddmore.mdl.mdl.Mcl
import eu.ddmore.mdl.mdl.MclObject
import eu.ddmore.mdl.mdl.SymbolReference
import eu.ddmore.mdl.provider.BlockDefinitionTable
import eu.ddmore.mdl.provider.ListDefinitionProvider
import eu.ddmore.mdl.type.TypeSystemProvider
import eu.ddmore.mdl.utils.BlockUtils
import eu.ddmore.mdl.utils.ConstantEvaluation
import eu.ddmore.mdl.utils.DomainObjectModelUtils
import eu.ddmore.mdl.utils.ExpressionUtils
import eu.ddmore.mdl.utils.LibraryUtils
import eu.ddmore.mdl.utils.MdlLibUtils
import eu.ddmore.mdl.utils.MdlUtils
import org.eclipse.xtext.EcoreUtil2

import static eu.ddmore.converter.mdl2pharmml08.Constants.*

class TrialDesignDesignObjectPrinter implements TrialDesignObjectPrinter {
	extension MdlUtils mu = new MdlUtils 
	extension PharmMLExpressionBuilder peb = new PharmMLExpressionBuilder 
	extension ListDefinitionProvider ldp = new ListDefinitionProvider
	extension BlockUtils bu = new BlockUtils
	extension DomainObjectModelUtils dom = new DomainObjectModelUtils
	extension ConstantEvaluation ce = new ConstantEvaluation
	extension TypeSystemProvider tsp = new TypeSystemProvider
	extension MdlLibUtils mlu = new MdlLibUtils
	extension LibraryUtils lib = new LibraryUtils
	extension ExpressionUtils eu = new ExpressionUtils

	val public static INTVN_TYPE_ATT_NAME = 'type'
	val public static INTVN_TYPE_BOLUS_VALUE = 'bolus'
	val public static INTVN_TYPE_INFUSION_VALUE = 'infusion'
	val public static SSINTERVAL_ATT_NAME = 'ssInterval'
	val public static SSEND_ATT_NAME = 'ssEnd'
	val public static INPUT_ATT_NAME = 'input'
	val public static AMT_ATT_NAME = 'amount'
	val public static DOSE_TIME_ATT_NAME = 'doseTime'
	val public static RATE_ATT_NAME = 'rate'
	val public static DURATION_ATT_NAME = 'duration'
	val public static SCALE_ATT_NAME = 'p'
	val public static INTVN_TYPE_COMBI_VALUE = 'combi'
	val public static COMBINATION_ATT_NAME = 'combination'
	val public static START_ATT_NAME = 'start'	
	val public static END_ATT_NAME = 'end'
	val public static INTVN_TYPE_RESET_ALL_VALUE = 'resetAll'	

	val MclObject mObj
	val MclObject designObj

	new(Mcl mdl){
		this.mObj = mdl.modelObject
		this.designObj = mdl.designObject
	}




	override writeTrialDesign()'''
		<TrialDesign xmlns="«xmlns_design»">
			«IF mObj != null && designObj != null»
				«designObj.getBlocksByName(BlockDefinitionTable::DES_DESIGN_PARAMS).forEach[writeDesignParameters]»
				«designObj.getBlocksByName(BlockDefinitionTable::DES_INTERVENTION_BLK).forEach[writeInterventions]»
			«ENDIF»
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
					«IF stmt.firstAttributeList.isAdministration»
						«stmt.writeAdministration»
					«ELSE»
						«stmt.writeInterventionCombination»
					«ENDIF»
				«ENDIF»
			«ENDFOR»
		</Interventions>
	'''
	
	def isBolusAdministration(AttributeList it){
		getAttributeEnumValue(INTVN_TYPE_ATT_NAME) == INTVN_TYPE_BOLUS_VALUE
	}
	
	def isAdministration(AttributeList it){
		val attVal = getAttributeEnumValue(INTVN_TYPE_ATT_NAME)
		attVal == INTVN_TYPE_BOLUS_VALUE || attVal == INTVN_TYPE_INFUSION_VALUE
	}
	
	
//	def private Library getLibrary(AttributeList it){
//		val blk = EcoreUtil2.getContainerOfType(eContainer, BlockStatement)
//		getLibraryFromBlock(blk)
//	}
//	
//	def getInputTargetType(AttributeList it){
//		val targetVar = getAttributeExpression(AMT_ATT_NAME)
//		if(targetVar instanceof SymbolReference){
//			val mdlTgtVar = mObj.findMdlSymbolDefn(targetVar.ref.name)
//			val lib = library
//			val mdlType = mdlTgtVar.typeFor
//			switch(mdlType){
//				case(lib.getListDefinition('Deriv')):
//					'derivative'
//				default:'error'
//			}
//		}
//		else{
//			'Error!'
//		}
//	}
	
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
						<ct:Assign>
							<math:Binop op="times">
								«getAttributeExpression(AMT_ATT_NAME).pharmMLExpr»
								«getAttributeExpression(SCALE_ATT_NAME).pharmMLExpr»
							</math:Binop>
						</ct:Assign>
					«ELSE»
						«getAttributeExpression(AMT_ATT_NAME).expressionAsAssignment»
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
					«getAttributeExpression(DOSE_TIME_ATT_NAME).expressionAsAssignment»
				</DosingTimes>
			«ENDIF»	'''
	
	
	def writeBolusDosing(AttributeList it)'''
		<Bolus>
			«writeCommonDosing»
		</Bolus>
	'''
	
	def writeInfusionDosing(AttributeList it)'''
		<Infusion>
			«writeCommonDosing»
			«IF hasAttribute(RATE_ATT_NAME)»
				<Rate>
					«getAttributeExpression(RATE_ATT_NAME).expressionAsAssignment»
				</RATE>
			«ENDIF»
			«IF hasAttribute(DURATION_ATT_NAME)»
				<Rate>
					«getAttributeExpression(DURATION_ATT_NAME).expressionAsAssignment»
				</RATE>
			«ENDIF»
		</Infusion>
	'''

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
	
	def writeAdministration(ListDefinition it)'''
		<Administration oid="«name»">
			«IF firstAttributeList.isBolusAdministration»
				«firstAttributeList.writeBolusDosing»
			«ELSE»
				«firstAttributeList.writeInfusionDosing»
			«ENDIF»
		</Administration>
		'''
}