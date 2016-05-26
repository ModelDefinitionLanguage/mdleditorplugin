package eu.ddmore.converter.mdl2pharmml08

import eu.ddmore.mdl.mdl.EquationDefinition
import eu.ddmore.mdl.mdl.EquationTypeDefinition
import eu.ddmore.mdl.mdl.ListDefinition
import eu.ddmore.mdl.mdl.MclObject
import eu.ddmore.mdl.mdl.RandomVariableDefinition
import eu.ddmore.mdl.provider.BlockDefinitionTable
import eu.ddmore.mdl.utils.BlockUtils
import eu.ddmore.mdl.utils.MdlUtils
import eu.ddmore.mdllib.mdllib.SymbolDefinition
import eu.ddmore.mdl.mdl.AnonymousListStatement
import eu.ddmore.mdl.provider.ListDefinitionProvider
import eu.ddmore.mdl.mdl.SymbolReference

class StandardParameterWriter extends AbstractParameterWriter {
	extension MdlUtils mu = new MdlUtils
	extension PharmMLExpressionBuilder peb = new PharmMLExpressionBuilder 
	extension BlockUtils bu = new BlockUtils
	extension DistributionPrinter spw = new DistributionPrinter
	extension ListDefinitionProvider ldp = new ListDefinitionProvider
	extension ListIndivParamWriter lip = new ListIndivParamWriter
	extension FunctionIndivParamWriter fip = new FunctionIndivParamWriter

	new(MclObject mdlObj){
		super(mdlObj)
	}
	
	override writeSimpleParameter(SymbolDefinition stmt)'''
		«IF stmt instanceof EquationTypeDefinition»
			«IF stmt.expression != null»
				<PopulationParameter symbId = "«stmt.name»">
					«stmt.expression.expressionAsAssignment»
				</PopulationParameter>
			«ELSE»
				<PopulationParameter symbId = "«stmt.name»"/>
			«ENDIF»
		«ENDIF»
	'''
	
	def private writePopulationParameter(AnonymousListStatement it){
		val rvDefnRef = if(list.getAttributeEnumValue('type') == 'continuous')
			 				list.getAttributeExpression('continuous')
			 			else
			 				list.getAttributeExpression('categorical')

		if(rvDefnRef instanceof SymbolReference){
			val rvDefn = rvDefnRef.ref
			if(rvDefn instanceof RandomVariableDefinition){
				return 
					'''
					<PopulationParameter id="tst">
						«rvDefn.distn.writeDistribution»
					</PopulationParameter>
					'''
			}
		}
		''''''
	}

	
	override writeParameter(SymbolDefinition stmt){
		writeSimpleParameter(stmt)
	}
	
	
	override String getTopLevelInsertion(){
		null
	}
	
	override writeParameterModel()'''		
		<ParameterModel blkId="pm">
			«IF mdlObj != null»
				«FOR b: mdlObj.blocks»
«««				//STRUCTURAL_PARAMETERS
					«IF b.identifier == BlockDefinitionTable::MDL_STRUCT_PARAMS»
						«FOR stmt: b.getNonBlockStatements»
							«switch(stmt){
								EquationDefinition:
									writeParameter(stmt)
							}»
						«ENDFOR» 
			  		«ENDIF»
«««				//VARIABILITY_PARAMETERS
					«IF b.identifier == BlockDefinitionTable::MDL_VAR_PARAMS»
						«FOR stmt: b.getNonBlockStatements»
							«switch(stmt){
								EquationDefinition:
									writeParameter(stmt)
							}»
						«ENDFOR» 
			  		«ENDIF»
«««		  		GROUP_VARIABLES (covariate parameters)
					«IF b.identifier == BlockDefinitionTable::MDL_GRP_PARAMS»
						«FOR stmt: b.getNonBlockStatements»
							«switch(stmt){
								EquationDefinition:
									writeSimpleParameter(stmt)
							}»
						«ENDFOR» 
			  		«ENDIF»
«««				//RANDOM_VARIABLES_DEFINITION
					«IF b.identifier == BlockDefinitionTable::MDL_RND_VARS»
						«FOR stmt: b.getNonBlockStatements»
							«switch(stmt){
								RandomVariableDefinition:{
									writeRandomVariable(stmt, b.getVarLevel)
								}
							}»
						«ENDFOR» 
					«ENDIF»
«««		  		//INDIVIDUAL_VARIABLES
					«IF b.identifier == BlockDefinitionTable::MDL_INDIV_PARAMS»
						«FOR stmt: b.getNonBlockStatements»
							«switch(stmt){
								EquationTypeDefinition:
									writeIndividualParameter(stmt)
								ListDefinition:
									writeIndividualParameter(stmt)
								AnonymousListStatement:
									writeIndividualParameter(stmt)
							}»
						«ENDFOR» 
					«ENDIF»
«««		  		//POPULATION_PARAMETERS
««« need to rethink this.  If writing out an anonymous pop or indiv param then we need to prevent the RV being written out.
««« likewise for the observation, this needs to prevent the RV being written out if use directly in the observation definition.
					«IF b.identifier == BlockDefinitionTable::MDL_POPULATION_BLK»
						«FOR stmt: b.getNonBlockStatements»
							«switch(stmt){
								AnonymousListStatement:
									writePopulationParameter(stmt)
							}»
						«ENDFOR» 
					«ENDIF»
				«ENDFOR»
	  		«ENDIF»
			«print_mdef_CollerationModel(mdlObj)»
		</ParameterModel>
  	'''
			
	override writeAllDatasets()''''''
			
}