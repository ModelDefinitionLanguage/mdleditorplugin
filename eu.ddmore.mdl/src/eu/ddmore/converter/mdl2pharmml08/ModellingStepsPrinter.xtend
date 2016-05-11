package eu.ddmore.converter.mdl2pharmml08

import eu.ddmore.converter.treerewrite.MdlRootProvider
import eu.ddmore.mdl.mdl.ListDefinition
import eu.ddmore.mdl.mdl.Mcl
import eu.ddmore.mdl.mdl.MclObject
import eu.ddmore.mdl.mdl.Statement
import eu.ddmore.mdl.provider.BlockDefinitionTable
import eu.ddmore.mdl.provider.ListDefinitionProvider
import eu.ddmore.mdl.utils.ConstantEvaluation
import eu.ddmore.mdl.utils.MdlUtils

import static eu.ddmore.converter.mdl2pharmml08.Constants.*
import eu.ddmore.mdl.mdl.EquationDefinition
import eu.ddmore.mdl.mdl.BlockStatement
import eu.ddmore.mdl.utils.BlockUtils

class ModellingStepsPrinter { 
	
	extension MdlUtils mu = new MdlUtils 
	extension MdlRootProvider mrp = new MdlRootProvider
	extension PharmMLExpressionBuilder peb = new PharmMLExpressionBuilder 
	extension ListDefinitionProvider ldp = new ListDefinitionProvider
	extension ConstantEvaluation ce = new ConstantEvaluation
	extension TaskSettingsPrinter tsp = new TaskSettingsPrinter
	extension BlockUtils bu = new BlockUtils
	

	////////////////////////////////////////////////
	// III Modelling Steps
	////////////////////////////////////////////////
	def writeModellingSteps(Mcl it){
		var mObj = mdlObj
		var pObj = paramObj
		var dObj = dataObj
		var tObj = taskObj

		var res = "";
		var dependencies = ""; 
		if (mObj != null && dObj != null && pObj != null && tObj != null) {
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
			«writeParametersToEstimate(pObj, mObj)»
			«taskBlk.statements.writeSettings»
		</EstimationStep>
	'''

	def writeSimulationStep(String oidRef, MclObject mObj, MclObject dObj, MclObject pObj, BlockStatement taskBlk)'''
		<SimulationStep oid="«oidRef»">
			«taskBlk.statements.writeSettingsFile»
			«writeExternalDataSetReference(dObj)»
			«writeParameterAssignments(pObj, mObj)»
			«taskBlk.statements.writeSettings»
		</SimulationStep>
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
							«stmt.firstAttributeList.getAttributeExpression('value').pharmMLExpr»
						</InitialEstimate>
						«IF stmt.firstAttributeList.getAttributeExpression('lo') != null»
							<LowerBound>
								«stmt.firstAttributeList.getAttributeExpression('lo').pharmMLExpr»
							</LowerBound>
						«ENDIF»
						«IF stmt.firstAttributeList.getAttributeExpression('hi') != null»
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