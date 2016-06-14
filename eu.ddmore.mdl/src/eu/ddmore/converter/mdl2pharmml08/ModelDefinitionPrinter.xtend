package eu.ddmore.converter.mdl2pharmml08

import eu.ddmore.mdl.mdl.BlockStatement
import eu.ddmore.mdl.mdl.BlockStatementBody
import eu.ddmore.mdl.mdl.EquationDefinition
import eu.ddmore.mdl.mdl.ListDefinition
import eu.ddmore.mdl.mdl.MclObject
import eu.ddmore.mdl.mdl.Statement
import eu.ddmore.mdl.provider.BlockDefinitionTable
import eu.ddmore.mdl.provider.ListDefinitionProvider
import eu.ddmore.mdl.type.TypeSystemProvider
import eu.ddmore.mdl.utils.BlockUtils
import eu.ddmore.mdl.utils.MdlUtils
import java.util.List

import static eu.ddmore.converter.mdl2pharmml08.Constants.*

class ModelDefinitionPrinter {
	extension MdlUtils mu = new MdlUtils
	extension TypeSystemProvider tsp = new TypeSystemProvider
	extension ListDefinitionProvider ldp = new ListDefinitionProvider
	extension PharmMLExpressionBuilder peb = new PharmMLExpressionBuilder 
	extension PKMacrosPrinter pkp = PKMacrosPrinter::INSTANCE
	extension BlockUtils bu = new BlockUtils
	extension CovariateModelWriter cmw = new CovariateModelWriter
	extension ListObservationsWriter omw = new ListObservationsWriter
	
	
	//////////////////////////////////////
	// I. Model Definition
	//////////////////////////////////////	
	def writeModelDefinition(MclObject mObj, MclObject pObj, AbstractParameterWriter paramWriter){
		'''
		<ModelDefinition xmlns="«xmlns_mdef»">
			«paramWriter.writeVariabilityModel»
			«mObj.mdlCovariateDefns.writeCovariateModel»
			«paramWriter.writeParameterModel»
			«IF !mObj.modelPredictionBlocks.isEmpty»
				«mObj.writeStructuralModel»
			«ENDIF»
			«FOR obsBlk : mObj.modelObservationsBlocks»
				«obsBlk.writeObservationModel»
			«ENDFOR»
		</ModelDefinition>
		'''
	}


		
//		
//	def private writeAssignment(Expression expr)'''
//		<ct:Assign>
//			«expr.pharmMLExpr»
//		</ct:Assign>
//	'''
		
//	def writeRandomVariable(RandomVariableDefinition stmt, SymbolReference level)'''
//		<RandomVariable symbId="«stmt.name»">
//			<ct:VariabilityReference>
//				«level.pharmMLExpr»
//			</ct:VariabilityReference>
//			«writeDistribution(stmt.distn)»
//		</RandomVariable>
//	'''
//		
//	def writeUncertMlDistribution(Expression functionCall){
//		switch(functionCall){
//			SymbolReference:
//				functionCall.writeUncertmlDist
//			default:
//				''''''
//		}
//	}
		

//	/////////////////////////////
//	// I.d Parameter Model
//	////////////////////////////	
//	def writeParameterModel(MclObject mObj, MclObject pObj)'''		
//		<ParameterModel blkId="pm">
//			«IF mObj != null»
//				«FOR b: mObj.blocks»
//«««				//STRUCTURAL_PARAMETERS
//					«IF b.identifier == BlockDefinitionTable::MDL_STRUCT_PARAMS»
//						«FOR stmt: b.getNonBlockStatements»
//							«switch(stmt){
//								EquationDefinition:
//									paramWriter.writeParameter(stmt)
//							}»
//						«ENDFOR» 
//			  		«ENDIF»
//«««				//VARIABILITY_PARAMETERS
//					«IF b.identifier == BlockDefinitionTable::MDL_VAR_PARAMS»
//						«FOR stmt: b.getNonBlockStatements»
//							«switch(stmt){
//								EquationDefinition:
//									paramWriter.writeParameter(stmt)
//							}»
//						«ENDFOR» 
//			  		«ENDIF»
//«««		  		GROUP_VARIABLES (covariate parameters)
//					«IF b.identifier == BlockDefinitionTable::MDL_GRP_PARAMS»
//						«FOR stmt: b.getNonBlockStatements»
//							«switch(stmt){
//								EquationDefinition:
//									paramWriter.writeSimpleParameter(stmt)
//							}»
//						«ENDFOR» 
//			  		«ENDIF»
//«««				//RANDOM_VARIABLES_DEFINITION
//					«IF b.identifier == BlockDefinitionTable::MDL_RND_VARS»
//						«FOR stmt: b.getNonBlockStatements»
//							«switch(stmt){
//								RandomVariableDefinition:{
//									writeRandomVariable(stmt, b.getVarLevel)
//								}
//							}»
//						«ENDFOR» 
//					«ENDIF»
//«««		  		//INDIVIDUAL_VARIABLES
//					«IF b.identifier == BlockDefinitionTable::MDL_INDIV_PARAMS»
//						«FOR stmt: b.getNonBlockStatements»
//							«switch(stmt){
//								EquationTypeDefinition:
//									writeParameter(stmt)
//								ListDefinition:
//									writeIndividualParameter(stmt)
//							}»
//						«ENDFOR» 
//					«ENDIF»
//				«ENDFOR»
//	  		«ENDIF»
//			«print_mdef_CollerationModel(mObj)»
//		</ParameterModel>
//  	'''
	
	
	def writeVariableDefinition(EquationDefinition stmt)'''
		<ct:Variable symbId="«stmt.name»" symbolType="«IF stmt.typeFor.isVector»ERROR!«ELSE»real«ENDIF»">
			«IF stmt.expression != null»
				«stmt.expression.expressionAsAssignment»
			«ENDIF»
		</ct:Variable>
	'''
	
	
	def writeAssignZero()'''
		<ct:Assign>
			<ct:Int>0</ct:Int>
		</ct:Assign>	
	'''
	
	def writeDefaultWrt(EquationDefinition it)'''
		<ct:SymbRef symbIdRef="«name»"/>
	'''
	
	
	def writeDerivativeDefinition(ListDefinition stmt, EquationDefinition defaultWrt)'''
		<ct:DerivativeVariable symbId="«stmt.name»" symbolType="real">
			«stmt.firstAttributeList.getAttributeExpression("deriv").expressionAsAssignment»
			<ct:IndependentVariable>
				«stmt.firstAttributeList.getAttributeExpression("wrt")?.pharmMLExpr ?: defaultWrt.writeDefaultWrt»
			</ct:IndependentVariable>
			<ct:InitialCondition>
				<ct:InitialValue>
					«stmt.firstAttributeList.getAttributeExpression("init")?.expressionAsAssignment ?: writeAssignZero »
				</ct:InitialValue>
				<ct:InitialTime>
					«stmt.firstAttributeList.getAttributeExpression("x0")?.expressionAsAssignment ?: writeAssignZero »
				</ct:InitialTime>
			</ct:InitialCondition>
		</ct:DerivativeVariable>
	'''
	
	
	def CharSequence writeModelPredictionBlock(MclObject mdlObject, BlockStatementBody currBlkBody)'''
		«FOR stmt : currBlkBody.statements»
			«switch(stmt){
				BlockStatement:
					if(stmt.identifier == BlockDefinitionTable::MDL_DEQ_BLK && stmt.body instanceof BlockStatementBody){
						'''
						«mdlObject.writeModelPredictionBlock(stmt.body as BlockStatementBody)»
						'''
					}
				ListDefinition:
					'''
					«stmt.writeDerivativeDefinition(mdlObject.mdlIdv)»
					'''
				EquationDefinition:
					'''
					«stmt.writeVariableDefinition»
					'''
				default:
					'''
					<ERROR!>
					'''
			}»
		«ENDFOR»
	'''
	
	def writeStructuralModel(MclObject mdlObject){
		'''
			<StructuralModel blkId="sm">
				«FOR blk : mdlObject.modelPredictionBlocks»
					«IF blk.body instanceof BlockStatementBody»
						«mdlObject.writeModelPredictionBlock(blk.body as BlockStatementBody)»
					«ENDIF»
				«ENDFOR»
				«IF !mdlObject.mdlCompartmentStatements.isEmpty»
					«mdlObject.mdlCompartmentStatements.writeCompartmentMacros»
				«ENDIF»
			</StructuralModel>
		'''
	}
	
	def writeCompartmentMacros(List<Statement> stmts){
		'''
		«stmts.printCompartmentDefinitions»
		«stmts.printMacros»
		'''
	}
	
	

}

