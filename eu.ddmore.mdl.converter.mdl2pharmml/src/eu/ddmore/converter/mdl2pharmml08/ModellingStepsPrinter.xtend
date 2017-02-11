package eu.ddmore.converter.mdl2pharmml08

import eu.ddmore.mdl.mdl.BlockStatement
import eu.ddmore.mdl.mdl.EquationDefinition
import eu.ddmore.mdl.mdl.ListDefinition
import eu.ddmore.mdl.mdl.MclObject
import eu.ddmore.mdl.mdl.RandomVariableDefinition
import eu.ddmore.mdl.mdl.Statement
import eu.ddmore.mdl.provider.BlockDefinitionTable
import eu.ddmore.mdl.provider.ListDefinitionProvider
import eu.ddmore.mdl.utils.BlockUtils
import eu.ddmore.mdl.utils.ConstantEvaluation
import eu.ddmore.mdl.utils.MdlUtils

import static eu.ddmore.converter.mdl2pharmml08.Constants.*

class ModellingStepsPrinter { 
	
	extension MdlUtils mu = new MdlUtils 
	extension PharmMLExpressionBuilder peb = new PharmMLExpressionBuilder 
	extension ListDefinitionProvider ldp = new ListDefinitionProvider
	extension ConstantEvaluation ce = new ConstantEvaluation
	extension TaskSettingsPrinter tsp = new TaskSettingsPrinter
	extension BlockUtils bu = new BlockUtils
//	extension ExpressionUtils eu = new ExpressionUtils
	

	////////////////////////////////////////////////
	// III Modelling Steps
	////////////////////////////////////////////////
	def writeModellingSteps(MclObject mObj, MclObject pObj, MclObject dObj, MclObject tObj){
//		var mObj = mdlObj
//		var pObj = paramObj
//		var dObj = dataObj
//		var tObj = taskObj
//		var desObj = designObj

		var res = "";
		var dependencies = ""; 
		var index = 1;
		for (b: tObj.blocks){
			if( b.blkId.name == BlockDefinitionTable::ESTIMATE_BLK){
				var oidRef = BLK_ESTIM_STEP + index;
				res += writeEstimationStep(oidRef, mObj, dObj, pObj, b);
				dependencies  += 
					'''
					<mstep:Step>
						<ct:OidRef oidRef="«oidRef»"/>
					</mstep:Step>
					'''
			}
			else if( b.blkId.name == BlockDefinitionTable::EVALUATE_BLK || b.blkId.name == BlockDefinitionTable::OPTIMISE_BLK){
				var oidRef = BLK_ESTIM_STEP + index;
				res += writeOptimalStep(oidRef, mObj, dObj, pObj, b);
				dependencies  += 
					'''
					<mstep:Step>
						<ct:OidRef oidRef="«oidRef»"/>
					</mstep:Step>
					'''
			}
			else if( b.blkId.name == BlockDefinitionTable::SIMULATE_BLK){
				var oidRef = BLK_SIMUL_STEP + index;
				res += writeSimulationStep(oidRef, mObj, dObj, pObj, b);
				dependencies  += 
					'''
					<mstep:Step>
						<ct:OidRef oidRef="«oidRef»"/>
					</mstep:Step>
					'''
			}
			index  = index + 1;
		}
		'''
		<ModellingSteps xmlns="«xmlns_mstep»">
			«res»
			«IF dependencies.length > 0»
				<mstep:StepDependencies>
					«dependencies»
				</mstep:StepDependencies>
			«ENDIF»
		</ModellingSteps>		
		'''	
	}

	def writeEstimationStep(String oidRef, MclObject mObj, MclObject dObj, MclObject pObj, BlockStatement taskBlk)'''
		<EstimationStep oid="«oidRef»">
			«taskBlk.statements.writeSettingsFile»
			«writeExternalDataSetReference(dObj)»
			«IF pObj.isParamObject»
				«writeParametersToEstimate(pObj, mObj)»
			«ELSE»
				«writePriorsToEstimate(pObj, mObj)»
			«ENDIF»
			«taskBlk.statements.writeSettings»
		</EstimationStep>
	'''

	def writeSimulationStep(String oidRef, MclObject mObj, MclObject dObj, MclObject pObj, BlockStatement taskBlk)'''
		<SimulationStep oid="«oidRef»">
			«taskBlk.statements.writeSettingsFile»
«««			«writeExternalDataSetReference(dObj)»
			«writeParameterAssignments(pObj, mObj)»
			«taskBlk.statements.writeSettings»
		</SimulationStep>
	'''
		
	def private boolean isEvaluation(BlockStatement blk){
		blk.blkId.name == BlockDefinitionTable::EVALUATE_BLK
	}
		
	def writeOptimalStep(String oidRef, MclObject mObj, MclObject dObj, MclObject pObj, BlockStatement taskBlk)'''
		<OptimalDesignStep oid="«oidRef»">
			«taskBlk.statements.writeSettingsFile»
			«writeParametersToEstimate(pObj, mObj)»
			<Operation order="1" opType="«IF taskBlk.isEvaluation»evaluation«ELSE»optimization«ENDIF»">
				«taskBlk.statements.writeOptimalSettings»
			</Operation>
		</OptimalDesignStep>
	'''
		
	def private writeExternalDataSetReference(MclObject dObj)'''
		<ExternalDataSetReference>
			<ct:OidRef oidRef="«BLK_DS_NONMEM_DATASET»"/>
		</ExternalDataSetReference>
	'''

	def private writeParameterEstimate(Statement s, MclObject mObj){
		val stmt = s
		switch(stmt){
			ListDefinition:{
				val paramVar = mObj.findMdlSymbolDefn(stmt.name)
				'''
				«IF paramVar != null»
					<ParameterEstimation>
						«paramVar.getSymbolReference»
						<InitialEstimate fixed="«stmt.firstAttributeList.getAttributeExpression('fix')?.evaluateLogicalExpression ?: 'false'»">
							«IF stmt.firstAttributeList.hasAttribute('value')»
								«stmt.firstAttributeList.getAttributeExpression('value').pharmMLExpr»
							«ELSEIF stmt.firstAttributeList.hasAttribute('vectorValue')»
								«stmt.firstAttributeList.getAttributeExpression('vectorValue').pharmMLExpr»
							«ELSEIF stmt.firstAttributeList.hasAttribute('matrixValue')»
								«stmt.firstAttributeList.getAttributeExpression('matrixValue').pharmMLExpr»
							«ENDIF»
						</InitialEstimate>
						«IF stmt.firstAttributeList.hasAttribute('lo')»
							<LowerBound>
								«stmt.firstAttributeList.getAttributeExpression('lo').pharmMLExpr»
							</LowerBound>
						«ENDIF»
						«IF stmt.firstAttributeList.hasAttribute('hi')»
							<UpperBound>
								«stmt.firstAttributeList.getAttributeExpression('hi').pharmMLExpr»
							</UpperBound>
						«ENDIF»
					</ParameterEstimation>
				«ENDIF»
				'''
			}
			default:''''''
		}
	}
	
	def private writeParametersToEstimate(MclObject pObj, MclObject mObj)'''
		<ParametersToEstimate>
			«FOR stmt: pObj.paramStructuralParams»
				«stmt.writeParameterEstimate(mObj)»
			«ENDFOR»
			«FOR stmt: pObj.paramVariabilityParams»
				«IF (stmt as ListDefinition).firstAttributeList.getAttributeEnumValue('type') != 'corr' && (stmt as ListDefinition).firstAttributeList.getAttributeEnumValue('type') != 'cov'»
					«stmt.writeParameterEstimate(mObj)»
				«ENDIF»
			«ENDFOR»
		</ParametersToEstimate>
	'''	
	
	
	def private writePriorsToEstimate(MclObject pObj, MclObject mObj)'''
		<ParametersToEstimate>
			«FOR b : pObj.blocks»
				«IF b.blkId.name == BlockDefinitionTable::PRIOR_VAR_DEFN»
					«FOR stmt : b.nonBlockStatements»
						«IF stmt instanceof RandomVariableDefinition»
							<ParameterEstimation>
								«stmt.getSymbolReference»
							</ParameterEstimation>
						«ENDIF»
					«ENDFOR»
				«ENDIF»
			«ENDFOR»
		</ParametersToEstimate>
	'''	

	def private writeParameterAssignments(MclObject pObj, MclObject mObj)'''
		«FOR stmt: pObj.paramStructuralParams»
			«stmt.writeParameterAssignment(mObj)»
		«ENDFOR»
		«FOR stmt: pObj.paramVariabilityParams»
			«IF (stmt as ListDefinition).firstAttributeList.getAttributeEnumValue('type') != 'corr' && (stmt as ListDefinition).firstAttributeList.getAttributeEnumValue('type') != 'cov'»
				«stmt.writeParameterAssignment(mObj)»
			«ENDIF»
		«ENDFOR»
	'''	

	def private writeParameterAssignment(Statement s, MclObject mObj){
		val stmt = s
		switch(stmt){
			ListDefinition:{
				val paramVar = mObj.findMdlSymbolDefn(stmt.name)
				'''
				«IF paramVar != null»
					<ct:VariableAssignment>
						«paramVar.getSymbolReference»
						«stmt.firstAttributeList.getAttributeExpression('value').expressionAsAssignment»
					</ct:VariableAssignment>
				«ENDIF»
				'''
			}
			EquationDefinition:{
				val paramVar = mObj.findMdlSymbolDefn(stmt.name)
				'''
				«IF paramVar != null»
					<ct:VariableAssignment>
						«paramVar.getSymbolReference»
						«stmt.expression.expressionAsAssignment»
					</ct:VariableAssignment>
				«ENDIF»
				'''
			}
			default:''''''
		}
	}
		
	
}