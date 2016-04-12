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

class ModellingStepsPrinter { 
	
	extension MdlUtils mu = new MdlUtils 
	extension MdlRootProvider mrp = new MdlRootProvider
	extension PharmMLExpressionBuilder peb = new PharmMLExpressionBuilder 
	extension ListDefinitionProvider ldp = new ListDefinitionProvider
	extension ConstantEvaluation ce = new ConstantEvaluation
	extension TaskSettingsPrinter tsp = new TaskSettingsPrinter
	

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
					res += writeEstimationStep(oidRef, index, mObj, dObj, pObj, tObj);
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

	////////////////////////////////////////////////
	// III.a Estimation Step
	////////////////////////////////////////////////
	def writeEstimationStep(String oidRef, Integer order, MclObject mObj, MclObject dObj, MclObject pObj, MclObject tObj)'''
		«FOR blk : tObj.blocks»
			<EstimationStep oid="«BLK_ESTIM_STEP»«order»">
				«dObj.writeExternalDataSetReference»
				«pObj.writeParametersToEstimate»
				«blk.nonBlockStatements.writeSettingsFile»
				«blk.nonBlockStatements.writeSettings»
			</EstimationStep>
		«ENDFOR»
	'''
		
		
	def private writeExternalDataSetReference(MclObject dObj)'''
		<ExternalDataSetReference>
			<ct:OidRef oidRef="«BLK_DS_NONMEM_DATASET»"/>
		</ExternalDataSetReference>
	'''

	def private writeParameterEstimate(Statement s, MclObject pObj){
		val stmt = s
		switch(stmt){
			ListDefinition:{
				val paramVar = pObj.findMdlSymbolDefn(stmt.name)
				'''
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
				'''
			}
			default:''''''
		}
	}
		
	def private writeParametersToEstimate(MclObject pObj)'''
		<ParametersToEstimate>
			«FOR stmt: pObj.paramStructuralParams»
				«stmt.writeParameterEstimate(pObj)»
			«ENDFOR»
			«FOR stmt: pObj.paramVariabilityParams»
				«IF (stmt as ListDefinition).firstAttributeList.getAttributeEnumValue('type') != 'corr' && (stmt as ListDefinition).firstAttributeList.getAttributeEnumValue('type') != 'cov'»
					«stmt.writeParameterEstimate(pObj)»
				«ENDIF»
			«ENDFOR»
		</ParametersToEstimate>
	'''	

	
}