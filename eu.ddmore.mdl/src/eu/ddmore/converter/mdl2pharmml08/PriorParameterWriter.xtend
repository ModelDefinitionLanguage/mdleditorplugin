package eu.ddmore.converter.mdl2pharmml08

import eu.ddmore.mdl.mdl.AnonymousListStatement
import eu.ddmore.mdl.mdl.BlockStatement
import eu.ddmore.mdl.mdl.EquationDefinition
import eu.ddmore.mdl.mdl.EquationTypeDefinition
import eu.ddmore.mdl.mdl.ListDefinition
import eu.ddmore.mdl.mdl.MclObject
import eu.ddmore.mdl.mdl.RandomVariableDefinition
import eu.ddmore.mdl.mdl.Statement
import eu.ddmore.mdl.mdl.StringLiteral
import eu.ddmore.mdl.mdl.SymbolReference
import eu.ddmore.mdl.provider.BlockDefinitionTable
import eu.ddmore.mdl.provider.BuiltinFunctionProvider
import eu.ddmore.mdl.provider.ListDefinitionProvider
import eu.ddmore.mdl.utils.DomainObjectModelUtils
import eu.ddmore.mdl.utils.ExpressionUtils
import eu.ddmore.mdl.utils.MdlUtils
import eu.ddmore.mdl.validation.MdlValidator
import eu.ddmore.mdllib.mdllib.SymbolDefinition
import java.util.ArrayList
import java.util.Collections
import java.util.HashSet
import java.util.Set

class PriorParameterWriter extends AbstractParameterWriter {

	extension PharmMLExpressionBuilder peb = new PharmMLExpressionBuilder
	extension MdlUtils mu = new MdlUtils
	extension DistributionPrinter dp = new DistributionPrinter
	extension ExpressionUtils eu = new ExpressionUtils
	extension DomainObjectModelUtils domu = new DomainObjectModelUtils
	extension ListDefinitionProvider ldp = new ListDefinitionProvider
	extension BuiltinFunctionProvider bfp = new BuiltinFunctionProvider

	val MclObject priorObject
	val Set<String> writtenParams
	
	new(MclObject mO, MclObject pO){ //}, () => SymbolDefinition findMatchingIdLevelInBlockLambda){
		super(mO) //, findMatchingIdLevelInBlockLambda)
		this.priorObject = pO
		this.writtenParams = new HashSet<String>
	}
	
	
	override writeParameter(SymbolDefinition stmt){
		val priorObjDefn = priorObject.findMdlSymbolDefn(stmt.name)
		if(!writtenParams.contains(stmt.name)){
			writtenParams.add(stmt.name)
			'''
«««				«IF priorObjDefn instanceof RandomVariableDefinition»
«««					«IF priorObjDefn.isNonParametricDistn»
«««						<PopulationParameter symbId="«priorObjDefn.name»" />
«««					«ENDIF»
«««				«ENDIF»
				«IF priorObjDefn != null»
					<PopulationParameter symbId="«stmt.name»">
						«IF priorObjDefn instanceof EquationTypeDefinition»
							«IF priorObjDefn.expression != null /*&& !priorObjDefn.expression.isLiteralExpression*/»
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
				«IF priorDefn.isPredefinedDistn»
					«priorDefn.distn.writeDistribution»
				«ELSE»
					«priorDefn.writeDataDrivenDistn»
				«ENDIF»
			'''
		}
	}
	
	def boolean isNonParametricDistn(RandomVariableDefinition rv){
		val ex = rv.distn
		if(ex instanceof SymbolReference){
			val distName = ex.ref.name
			distName == 'MultiNonParametric' ||
				distName == 'NonParametric'
		}
		else false
	}
	
	def private boolean isMultiVariate(RandomVariableDefinition rv){
		val ex = rv.distn
		if(ex instanceof SymbolReference){
			val distName = ex.ref.name
			distName == 'MultiNonParametric' ||
				distName == 'MultiEmpirical'
		}
		else false
	}
	
	def private writeDataDrivenDistn(RandomVariableDefinition rvd)'''
		«IF rvd.distn instanceof SymbolReference»
			<Distribution>
				<ProbOnto xmlns="http://www.pharmml.org/probonto/ProbOnto" name="RandomSample" type="«IF rvd.isMultiVariate»multivariate«ELSE»univariate«ENDIF»">
					<Realisation>
						«IF rvd.isNonParametricDistn»
							«rvd.distn.symbolRef.getArgumentExpression('bins').expressionAsAssignment»
						«ELSE»
							«rvd.distn.symbolRef.getArgumentExpression('data').expressionAsAssignment»
						«ENDIF»
					</Realisation>
					«IF rvd.isNonParametricDistn»
						<Weight>
							«rvd.distn.symbolRef.getArgumentExpression('probability').expressionAsAssignment»
						</Weight>
					«ENDIF»
				</ProbOnto>
			</Distribution>
		«ENDIF»
	'''
	
	def boolean isPredefinedDistn(RandomVariableDefinition rv){
		val ex = rv.distn
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
			<PopulationParameter symbId="«stmt.name»"«IF stmt.expression !=null»>
				«stmt.expression.expressionAsAssignment»
			</PopulationParameter>«ELSE»/>«ENDIF»
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
			«IF b.blkId.name == BlockDefinitionTable::MDL_VAR_PARAMS || b.blkId.name == BlockDefinitionTable::MDL_STRUCT_PARAMS
				|| b.blkId.name == BlockDefinitionTable::MDL_GRP_PARAMS»
				«FOR stmt : b.nonBlockStatements»
					«IF stmt instanceof SymbolDefinition»
						«writeParameter(stmt)»
					«ENDIF»
				«ENDFOR»
			«ENDIF»
	  	«ENDFOR»
	'''
	
	override String writeParameters()'''
		«writePriorParams»
		«writeModelParams»
	'''
	
//	override writeParameterModel()'''		
//		<ParameterModel blkId="pm">
//			«writeParameters
//			«FOR b: mdlObj.blocks»
//«««				//RANDOM_VARIABLES_DEFINITION
//				«IF b.blkId.name == BlockDefinitionTable::MDL_RND_VARS»
//					«FOR stmt: b.getNonBlockStatements»
//						«switch(stmt){
//							RandomVariableDefinition:{
//								writeRandomVariable(stmt, b.getVarLevel)
//							}
//						}»
//					«ENDFOR» 
//				«ENDIF»
//«««		  		//INDIVIDUAL_VARIABLES
//				«IF b.blkId.name == BlockDefinitionTable::MDL_INDIV_PARAMS»
//					«FOR stmt: b.getNonBlockStatements»
//						«switch(stmt){
//							EquationDefinition:
//								writeIndividualParameter(stmt)
//							ListDefinition:
//								writeIndividualParameter(stmt)
//						}»
//					«ENDFOR» 
//				«ENDIF»
//			«ENDFOR»
//			«print_mdef_CollerationModel(mdlObj)»
//		</ParameterModel>
//  	'''
	
	
	override writeAllDatasets(){
		val srcBlks = priorObject.getBlocksByName(BlockDefinitionTable::PRIOR_NC_DISTN)
		'''
			«FOR ncb : srcBlks»
				«FOR sb : ncb.statementsFromBlock.filter[blk|
								if(blk instanceof BlockStatement) blk.blkId.name == BlockDefinitionTable::PRIOR_SOURCE_BLK else false
						]»
					«FOR sl : (sb as BlockStatement).nonBlockStatements»
						«IF sl instanceof ListDefinition»
							«writeDataset(sl)»
						«ENDIF»
					«ENDFOR»
				«ENDFOR»
			«ENDFOR»
		'''
	}
	
	def private writeColumnMapping(ListDefinition dataList){
		val ncBlks = priorObject.getBlocksByName(BlockDefinitionTable::PRIOR_NC_DISTN)
		var matColCntr = 1
		'''
			«FOR ncBlk : ncBlks»
				«FOR blk : ncBlk.statementsFromBlock.filter[blk|
								if(blk instanceof BlockStatement) blk.blkId.name == BlockDefinitionTable::PRIOR_INPUT_DATA else false
						]»
					«IF blk instanceof BlockStatement»
						«FOR inList : blk.nonBlockStatements»
							«IF inList instanceof AnonymousListStatement»
								«IF inList.list.getAttributeExpression("src").symbolRef?.ref == dataList»
									«IF inList.list.hasAttribute('vectorVar')»
											<ColumnMapping>
												<ds:ColumnRef columnIdRef="«inList.list.getAttributeExpessionAsString('column')»"/>
												«inList.list.getAttributeExpression('vectorVar').pharmMLExpr»
											</ColumnMapping>
									«ELSE»
										«FOR col : inList.list.getAttributeExpression('column').vector»
											«IF col instanceof StringLiteral»
												<ColumnMapping>
													<ds:ColumnRef columnIdRef="«col.stringValue»"/>
													<ct:Assign>
														<ct:VectorSelector>
															«inList.list.getAttributeExpression('matrixVar').pharmMLExpr»
															<ct:Cell>
																<ct:Int>«matColCntr++»</ct:Int>
															</ct:Cell>
														</ct:VectorSelector>
													</ct:Assign>
												</ColumnMapping>
											«ENDIF»
										«ENDFOR»
									«ENDIF»
								«ENDIF»
							«ENDIF»
						«ENDFOR»
					«ENDIF»
				«ENDFOR»
			«ENDFOR»
		'''
	}
	
	def private getSyntheticFileOid(String name){
		MdlValidator::RESERVED_PREFIX + 'fileOid_' + name
	}
	
	def writeDataset(ListDefinition it){
		var colIdx = 1
		val ncBlks = priorObject.getBlocksByName(BlockDefinitionTable::PRIOR_NC_DISTN)
		val inputStmts = new ArrayList<Statement>
		ncBlks.forEach[ncBlk|
			ncBlk.statementsFromBlock.filter[blk|
					if(blk instanceof BlockStatement) blk.blkId.name == BlockDefinitionTable::PRIOR_INPUT_DATA else false
			].forEach[stmt|
				inputStmts.add(stmt)
			]
		]
		'''
			«IF !inputStmts.isEmpty»
				<ExternalDataSet oid="«name»">
					«writeColumnMapping»
					<ds:DataSet>
						<ds:Definition>
							«FOR col : firstAttributeList.getAttributeExpression('column').vector ?: Collections::emptyList»
								«IF col instanceof StringLiteral»
									<ds:Column columnId="«col.stringValue»" valueType="real" columnNum="«colIdx++»"/>
								«ENDIF»
							«ENDFOR»
						</ds:Definition>
						<ds:ExternalFile oid="«getSyntheticFileOid(name)»">
							<ds:path>«firstAttributeList.getAttributeExpression('file').stringValue ?: 'Error!'»</ds:path>
						</ds:ExternalFile>
					</ds:DataSet>
				</ExternalDataSet>
			«ENDIF»
		'''
	}
	
}