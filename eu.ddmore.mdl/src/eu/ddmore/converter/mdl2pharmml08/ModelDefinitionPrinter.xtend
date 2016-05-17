package eu.ddmore.converter.mdl2pharmml08

import eu.ddmore.mdl.mdl.AnonymousListStatement
import eu.ddmore.mdl.mdl.BlockStatement
import eu.ddmore.mdl.mdl.BlockStatementBody
import eu.ddmore.mdl.mdl.CategoricalDefinitionExpr
import eu.ddmore.mdl.mdl.EnumerationDefinition
import eu.ddmore.mdl.mdl.EquationDefinition
import eu.ddmore.mdl.mdl.EquationTypeDefinition
import eu.ddmore.mdl.mdl.ListDefinition
import eu.ddmore.mdl.mdl.MclObject
import eu.ddmore.mdl.mdl.RandomVariableDefinition
import eu.ddmore.mdl.mdl.Statement
import eu.ddmore.mdl.mdl.SymbolReference
import eu.ddmore.mdl.provider.BlockArgumentDefinitionProvider
import eu.ddmore.mdl.provider.BlockDefinitionTable
import eu.ddmore.mdl.provider.ListDefinitionProvider
import eu.ddmore.mdl.type.TypeSystemProvider
import eu.ddmore.mdl.utils.BlockUtils
import eu.ddmore.mdl.utils.DomainObjectModelUtils
import eu.ddmore.mdl.utils.ExpressionUtils
import eu.ddmore.mdl.utils.MdlUtils
import eu.ddmore.mdl.validation.MdlValidator
import eu.ddmore.mdllib.mdllib.Expression
import eu.ddmore.mdllib.mdllib.SymbolDefinition
import java.util.ArrayList
import java.util.List

import static eu.ddmore.converter.mdl2pharmml08.Constants.*

class ModelDefinitionPrinter {
	extension MdlUtils mu = new MdlUtils
	extension TypeSystemProvider tsp = new TypeSystemProvider
	extension ListDefinitionProvider ldp = new ListDefinitionProvider
	extension PharmMLExpressionBuilder peb = new PharmMLExpressionBuilder 
	extension DistributionPrinter dp = new DistributionPrinter 
	extension PKMacrosPrinter pkp = PKMacrosPrinter::INSTANCE
	extension DomainObjectModelUtils domu = new DomainObjectModelUtils
	extension BlockUtils bu = new BlockUtils
	extension ListObservationsWriter low = new ListObservationsWriter
	extension FunctionObservationsWriter fow = new FunctionObservationsWriter
	extension SimpleParameterWriter spw = new SimpleParameterWriter
	extension ListIndivParamWriter lip = new ListIndivParamWriter
	extension ExpressionUtils eu = new ExpressionUtils
	extension BlockArgumentDefinitionProvider badp = new BlockArgumentDefinitionProvider 
	
	
	var AbstractParameterWriter paramWriter;
	
	//////////////////////////////////////
	// I. Model Definition
	//////////////////////////////////////	
	def writeModelDefinition(MclObject mObj, MclObject pObj){
		if(pObj.objId.name == MdlValidator::PARAMOBJ){
			paramWriter = new StandardParameterWriter(mObj)
		}
		else{
			paramWriter = new PriorParameterWriter(mObj, pObj)
		}
		'''
		<ModelDefinition xmlns="«xmlns_mdef»">
			«paramWriter.writeVariabilityModel»
			«IF !mObj.mdlCovariateDefns.isEmpty»
				«mObj.writeCovariateModel»
			«ENDIF»
			«paramWriter.writeParameterModel»
			«IF !mObj.modelPredictionBlocks.isEmpty»
				«mObj.writeStructuralModel»
			«ENDIF»
			«IF !mObj.mdlObservations.isEmpty»
				«mObj.writeObservationModel»
			«ENDIF»
		</ModelDefinition>
		'''
	}

//	def writeAllVariabilityModels(MclObject mObj){
//		val vm_err_vars = new HashMap<String, Integer>
//		val vm_mdl_vars = new HashMap<String, Integer>
//		for(stmt : mObj.mdlVariabilityLevels){
//			switch(stmt){
//				ListDefinition:{
//					if(stmt.firstAttributeList.getAttributeEnumValue('type') == 'parameter'){
//						vm_mdl_vars.put(stmt.name, stmt.firstAttributeList.getAttributeExpression('level').convertToInteger)
//					}
//					else{
//						vm_err_vars.put(stmt.name, stmt.firstAttributeList.getAttributeExpression('level').convertToInteger)
//					}
//				}
//			}	
//		}
//		var model = "";
//		if (vm_err_vars.size() > 0){
//			model = model + vm_err_vars.writeVariabilityModel("vm_err", VAR_TYPE_ERROR);
//		}		
//		if (vm_mdl_vars.size() > 0){
//			model = model + vm_mdl_vars.writeVariabilityModel("vm_mdl", VAR_TYPE_PARAMETER);
//		}
//		return model;
//	}
//	
//	def writeVariabilityModel(Map<String, Integer> vars, String blkId, String varType){
//		var model = "";
//		if (vars.size() > 0){
//			var bvc =  new ValueComparator(vars);
//			var sorted_map = new TreeMap<String, Integer>(bvc);
//			sorted_map.putAll(vars);
//			var prev = "";
//			var levels = "";
//			for (s: sorted_map.entrySet){
//				levels = levels +	'''
//					«IF prev.length > 0»
//						<Level referenceLevel="«IF s.value == 2»true«ELSE»false«ENDIF»" symbId="«s.key»">
//							<ParentLevel>
//								<ct:SymbRef symbIdRef="«prev»"/>
//							</ParentLevel>
//						</Level>
//					«ELSE»
//						<Level referenceLevel="«IF s.value == 2»true«ELSE»false«ENDIF»" symbId="«s.key»"/>
//					«ENDIF»
//				'''
//				prev = s.key
//			}			
//			model = '''
//				<VariabilityModel blkId="«blkId»" type="«varType»">
//					«levels»
//				</VariabilityModel>
//			'''		
//		}		
//		return model;
//	}
	
    def getCategoryDefinitions(CategoricalDefinitionExpr expr){
    	val retVal = new ArrayList<String>
		expr.categories.forEach[retVal.add(name)]
    	retVal
    }
    
    
	def writeCovariateModel(MclObject mObj){
		var model = "";
		var skipped = new ArrayList<SymbolDefinition>();
		val covDefns = mObj.mdlCovariateDefns
		//First print transformed covariates (and exclude them from the list to avoid double defintiion)
		for(s : covDefns){
			switch(s){
				EquationDefinition case(s.expression != null):{
					var transformation = "";
					var dependencies = s.expression.getCovariateDependencies;
					var SymbolDefinition transformedCov = null 
					var continue = true; //no 'break' command in xText
					for (v: dependencies){
						if (covDefns.exists[it == v] && continue){
							transformedCov = v
							skipped.add(v); 
							continue = false;
						} 
					}
					if (transformedCov != null){
						transformation =  '''
							<Continuous>
								<Transformation>
								    <TransformedCovariate symbId="«s.name»"/>
									«s.expression.expressionAsEquation»
								</Transformation>
							</Continuous>
							'''
							skipped.add(s);
					}
					model = model + '''
					«IF transformation.length > 0»
						<Covariate symbId="«transformedCov.name»">
							«transformation»
						</Covariate>
					«ENDIF»	
					'''
				}
			}
		} 
		//Then print all remaining covariates
		for(s : mObj.mdlCovariateDefns){
			switch(s){
				EquationDefinition:{
					if (!skipped.contains(s)){
						model = model + '''
						<Covariate symbId="«s.name»">
							<Continuous/>
						</Covariate>
						'''
					}
				}
				EnumerationDefinition:{
					if (!skipped.contains(s)){
						model = model + '''
						<Covariate symbId="«s.name»">
							<Categorical>
								«FOR c : s.catDefn.getCategoryDefinitions»
									<Category catId="«c»"/>
								«ENDFOR»
							</Categorical>
						</Covariate>
						'''
					}
				}
			}
		}
		if (model.length > 0){
			model = '''
				<CovariateModel blkId="cm">
					«model»
				</CovariateModel>
			'''
		}
		return model;
	}	
		
		
	def private writeAssignment(Expression expr)'''
		<ct:Assign>
			«expr.pharmMLExpr»
		</ct:Assign>
	'''
		
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
				«stmt.expression.writeAssignment»
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
			«stmt.firstAttributeList.getAttributeExpression("deriv").writeAssignment»
			<ct:IndependentVariable>
				«stmt.firstAttributeList.getAttributeExpression("wrt")?.pharmMLExpr ?: defaultWrt.writeDefaultWrt»
			</ct:IndependentVariable>
			<ct:InitialCondition>
				<ct:InitialValue>
					«stmt.firstAttributeList.getAttributeExpression("init")?.writeAssignment ?: writeAssignZero »
				</ct:InitialValue>
				<ct:InitialTime>
					«stmt.firstAttributeList.getAttributeExpression("x0")?.writeAssignment ?: writeAssignZero »
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
	
	
	def writeObservationModel(MclObject mdlObject){
		var idx = 0
		'''«FOR stmt : mdlObject.mdlObservations»
			«switch(stmt){
				EquationTypeDefinition:
					'''
					«writeContinuousObservation(stmt, idx += 1)»
					'''
				ListDefinition:
					'''
					«writeListObservations(stmt, idx += 1)»
					'''
				AnonymousListStatement:
					'''
					«writeListObservations(stmt, idx += 1)»
					'''
				default:{
					idx += 1
					''''''
				}
			}»
		«ENDFOR»
		'''
	}
	
	

}

