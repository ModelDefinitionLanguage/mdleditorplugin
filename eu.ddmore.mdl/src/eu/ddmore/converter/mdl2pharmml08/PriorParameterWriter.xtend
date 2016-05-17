package eu.ddmore.converter.mdl2pharmml08

import eu.ddmore.mdl.mdl.EquationDefinition
import eu.ddmore.mdl.mdl.EquationTypeDefinition
import eu.ddmore.mdl.mdl.ListDefinition
import eu.ddmore.mdl.mdl.MclObject
import eu.ddmore.mdl.mdl.RandomVariableDefinition
import eu.ddmore.mdl.mdl.SymbolReference
import eu.ddmore.mdl.provider.BlockDefinitionTable
import eu.ddmore.mdl.utils.ExpressionUtils
import eu.ddmore.mdl.utils.MdlUtils
import eu.ddmore.mdllib.mdllib.Expression
import eu.ddmore.mdllib.mdllib.SymbolDefinition
import java.util.HashSet
import java.util.Set

class PriorParameterWriter extends AbstractParameterWriter {

	extension PharmMLExpressionBuilder peb = new PharmMLExpressionBuilder
	extension MdlUtils mu = new MdlUtils
	extension DistributionPrinter dp = new DistributionPrinter
	extension ListIndivParamWriter lip = new ListIndivParamWriter
	extension ExpressionUtils eu = new ExpressionUtils

	val MclObject priorObject
	val Set<String> writtenParams
	
	new(MclObject mO, MclObject pO){
		super(mO)
		this.priorObject = pO
		this.writtenParams = new HashSet<String>
	}
	
	
	override writeParameter(SymbolDefinition stmt){
		val priorObjDefn = priorObject.findMdlSymbolDefn(stmt.name)
		if(!writtenParams.contains(stmt.name)){
			writtenParams.add(stmt.name)
			'''
				«IF priorObjDefn != null»
					<PopulationParameter symbId="«stmt.name»">
						«IF priorObjDefn instanceof EquationTypeDefinition»
							«IF priorObjDefn.expression != null && !priorObjDefn.expression.isLiteralExpression»
								«priorObjDefn.priorExpression»
							«ENDIF»
						«ELSE»
							«priorObjDefn.priorExpression»
						«ENDIF»
					</PopulationParameter>
				«ELSE»
					«writeSimpleParameter(stmt)»
				«ENDIF»
			'''
		}
		else
			''''''
	}
	
	def private priorExpression(SymbolDefinition priorDefn){
		if(priorDefn instanceof EquationDefinition){
			priorDefn.expression.expressionAsAssignment
		}
		else if(priorDefn instanceof RandomVariableDefinition){
			'''
				<ct:VariabilityReference>
					<ct:SymbRef blkIdRef="vm_mdl" symbIdRef="MDL__prior"/>
				</ct:VariabilityReference>
				«IF priorDefn.distn.isPredefinedDistn»
					«priorDefn.distn.writeDistribution»
				«ELSE»
					«priorDefn.distn.writeDataDrivenDistn»
				«ENDIF»
			'''
		}
	}
	
	def private writeDataDrivenDistn(Expression ex){
		
	}
	
	def boolean isPredefinedDistn(Expression ex){
		if(ex instanceof SymbolReference){
			val distName = ex.ref.name
			distName != 'MultiNonParametric' &&
				distName != 'NonParametric' &&
				distName != 'Empirical' &&
				distName != 'MultiEmpirical'
		}
		else false
	}
	
	override writeSimpleParameter(SymbolDefinition stmt)'''
		«IF stmt instanceof EquationTypeDefinition»
			<Parameter symbId="«stmt.name»"«IF stmt.expression !=null»>
				«stmt.expression.expressionAsAssignment»
			</Parameter>«ELSE»/>«ENDIF»
		«ENDIF»
	'''


	override String getTopLevelInsertion(){
		'MDL__prior'
	}


	// need to handle non-canonical distns

	def private writePriorParams()'''
		«FOR b : priorObject.blocks»
			«IF b.blkId.name != BlockDefinitionTable::PRIOR_SOURCE_BLK»
				«FOR stmt : b.nonBlockStatements»
					«IF stmt instanceof SymbolDefinition»
						«writeParameter(stmt)»
					«ENDIF»
				«ENDFOR»
			«ENDIF»
		«ENDFOR»
	'''
	
	def private writeModelParams()'''
		«FOR b: mdlObj.blocks»
			«FOR stmt : b.nonBlockStatements»
				«IF stmt instanceof SymbolDefinition»
					«writeParameter(stmt)»
				«ENDIF»
			«ENDFOR»
	  	«ENDFOR»
	'''
	
	override writeParameterModel()'''		
		<ParameterModel blkId="pm">
			«writePriorParams»
			«writeModelParams»
			«FOR b: mdlObj.blocks»
«««				//RANDOM_VARIABLES_DEFINITION
				«IF b.blkId.name == BlockDefinitionTable::MDL_RND_VARS»
					«FOR stmt: b.getNonBlockStatements»
						«switch(stmt){
							RandomVariableDefinition:{
								writeRandomVariable(stmt, b.getVarLevel)
							}
						}»
					«ENDFOR» 
				«ENDIF»
«««		  		//INDIVIDUAL_VARIABLES
				«IF b.blkId.name == BlockDefinitionTable::MDL_INDIV_PARAMS»
					«FOR stmt: b.getNonBlockStatements»
						«switch(stmt){
							EquationDefinition:
								writeParameter(stmt)
							ListDefinition:
								writeIndividualParameter(stmt)
						}»
					«ENDFOR» 
				«ENDIF»
			«ENDFOR»
			«print_mdef_CollerationModel(mdlObj)»
		</ParameterModel>
  	'''
	
}