package eu.ddmore.mdl.validation

import eu.ddmore.mdl.mdl.AnonymousListStatement
import eu.ddmore.mdl.mdl.BlockStatement
import eu.ddmore.mdl.mdl.EnumerationDefinition
import eu.ddmore.mdl.mdl.EquationDefinition
import eu.ddmore.mdl.mdl.EquationTypeDefinition
import eu.ddmore.mdl.mdl.ListDefinition
import eu.ddmore.mdl.mdl.Mcl
import eu.ddmore.mdl.mdl.MclObject
import eu.ddmore.mdl.mdl.MdlPackage
import eu.ddmore.mdl.mdl.Statement
import eu.ddmore.mdl.mdl.SymbolReference
import eu.ddmore.mdl.provider.BlockArgumentDefinitionProvider
import eu.ddmore.mdl.provider.BlockDefinitionTable
import eu.ddmore.mdl.provider.ListDefinitionProvider
import eu.ddmore.mdl.provider.ListDefinitionTable
import eu.ddmore.mdl.provider.MogDefinitionProvider
import eu.ddmore.mdl.type.RandomVariableTypeInfo
import eu.ddmore.mdl.type.TypeInfo
import eu.ddmore.mdl.type.TypeSystemProvider
import eu.ddmore.mdl.utils.BlockUtils
import eu.ddmore.mdl.utils.DomainObjectModelUtils
import eu.ddmore.mdl.utils.ExpressionUtils
import eu.ddmore.mdl.utils.LibraryUtils
import eu.ddmore.mdl.utils.MdlLibUtils
import eu.ddmore.mdl.utils.MdlUtils
import eu.ddmore.mdllib.mdllib.MdlLibPackage
import eu.ddmore.mdllib.mdllib.SymbolDefinition
import java.util.ArrayList
import java.util.Collections
import java.util.List
import org.eclipse.xtext.EcoreUtil2
import org.eclipse.xtext.validation.AbstractDeclarativeValidator
import org.eclipse.xtext.validation.Check
import org.eclipse.xtext.validation.EValidatorRegistrar

class MogValidator extends AbstractDeclarativeValidator {

	override register(EValidatorRegistrar registrar){}

	extension ListDefinitionProvider listProvider = new ListDefinitionProvider 
	extension TypeSystemProvider typeProvider = new TypeSystemProvider 
	extension MdlUtils mclu = new MdlUtils
	extension DomainObjectModelUtils domu = new DomainObjectModelUtils
	extension BlockUtils bu = new BlockUtils
	extension MogDefinitionProvider mdp = new MogDefinitionProvider
	extension ExpressionUtils eu = new ExpressionUtils
	extension MdlLibUtils mlu = new MdlLibUtils 
	extension LibraryUtils lu = new LibraryUtils
	extension BlockArgumentDefinitionProvider bdp = new BlockArgumentDefinitionProvider 
	
	val static String COUNT_OBS_TYPE_NAME = 'CountObs'
//	val static String DISCRETE_OBS_TYPE_NAME = 'DiscreteObs'
	val static String CONTINUOUS_OBS_TYPE_NAME = 'ContinuousObsList'


	def findMdlObject(MclObject obj, String name, String mdlType){
		val mcl = EcoreUtil2.getContainerOfType(obj, Mcl)
		mcl.objects.findFirst[mdlObjType == mdlType && it.name == name]
	}

	
	// MOG validation rules
	// for data object
	
	// 1) All covariates (unless they have an RHS) must be matched to the data object - Done
	// 1a) All covariates must be of the same type.  -Done
	// 2) All observations must be matched to a data object - unless this is a simulation  - Done (ignore simulation case)
	// 3) All uninitialised parameters in STRUCURAL_PARAMS must match STRUCTURAL in parObj - Done
	// 4) All unitinitialised parameters in VARIABILITY_PARAMETERS must match VARIABILITY in parObj - Done
	// 5) Variability levels of type parameter must be matched to dataObj. - Done
	// 6) Variability parameters of type dv must be matched to data if an estimation (not simulation) - Done (ignore simulation case)
	// 7) If IDV is specified in the model then it must match TIME in the data. - Done 
	// 8) Dosing variables must be mapped to variables in to MODEL_PREDICTION block
	// 8a) Dosing variables in the model must have type non-vector REAL.  - Done by checks preventing vector defns
	// 9) All symbols in the model should be initialised at the end of assembly 
	// 10) Dosing time match to a variable in model prediction
	// 11) Check that eta in par object are defined in model. 
	 
	@Check
	def validateCovariates(MclObject it){
		val (String, String) => void errorLambda = [
					errorCode, errMsg| error(errMsg, MdlPackage.eINSTANCE.mclObject_Blocks, errorCode, '')
			]
		if(isMogObject){
			if(mdlObj !== null && dataObj !== null){
				val expectedMdlCovars = new ArrayList<SymbolDefinition>
				
				mdlObj.mdlCovariateDefns.forEach[
					switch(it){
						EquationDefinition case expression === null: expectedMdlCovars.add(it)
						EnumerationDefinition: expectedMdlCovars.add(it)
					}
				]
				val dataCovars = dataObj.dataCovariateDefns
				val ddvCovars = dataObj.ddvCovariateDefns
				for(mdlCov : expectedMdlCovars){
					val dataCovar = dataCovars.findFirst[name == mdlCov.name]
					val ddvCovar = ddvCovars.findFirst[name == mdlCov.name]
					if(dataCovar === null && ddvCovar === null){
						errorLambda.apply(MdlValidator::MODEL_DATA_MISMATCH, "covariate " + mdlCov.name +" has no match in dataObj");
					}
					else{
						val covar = if(dataCovar !== null) dataCovar else ddvCovar
						if(!mdlCov.typeFor.isCompatible(covar.typeFor)){
							errorLambda.apply(MdlValidator::INCOMPATIBLE_TYPES, "covariate " + mdlCov.name +" has an inconsistent type with its match in the dataObj");
						}
					}
				}
			}
		}
	}
	
	@Check
	def validateObservations(MclObject it){
		val (String, String) => void errorLambda = [
					errorCode, errMsg| error(errMsg, MdlPackage.eINSTANCE.mclObject_Blocks, errorCode, '')
			]
		if(isMogObject){
			val MclObject matchingObj = dataObj ?: designObj 
			val dataStmts = matchingObj.getBlocksByName(BlockDefinitionTable::DECLARED_VARS_BLK).head?.nonBlockStatements ?: Collections.emptyList
			for(mdlOb : mdlObj.mdlObservations){
				if(mdlOb instanceof ListDefinition){
					val dataSymb = dataStmts.findFirst[st | if(st instanceof SymbolDefinition) st.name == mdlOb.name else false]
					if(dataSymb === null){
						errorLambda.apply(MdlValidator::MODEL_DATA_MISMATCH, "observation " + mdlOb.name +" has no match in obj: '" + matchingObj.name + "'.");
					}
					else if(!mdlOb.typeFor.isCompatible(dataSymb.typeFor)){
						errorLambda.apply(MdlValidator::INCOMPATIBLE_TYPES, "observation " + mdlOb.name +" has an inconsistent type with its match in obj: '" + matchingObj.name + "'.");
					}
				}
				if(mdlOb instanceof AnonymousListStatement){
					// expect all lists to have a variable att
					val rvVar = mdlOb.list.getAttributeExpression('variable').symbolRef
					val rv = rvVar?.ref
					if(rv !== null){
						val rvType = rv.typeFor
						val libDefn = getLibraryForObject
						val obsTypeName = mdlOb.list.getAttributeEnumValue('type')
						val TypeInfo obsActualType = switch(obsTypeName){
							case(ListDefinitionTable::COUNT_OBS_VALUE): libDefn.getListDefinition(COUNT_OBS_TYPE_NAME).typeInfo
							case(ListDefinitionTable::DISCRETE_OBS_VALUE): if(rvType instanceof RandomVariableTypeInfo) rvType.rvType else TypeSystemProvider::UNDEFINED_TYPE
							case(ListDefinitionTable::CATEGORICAL_OBS_VALUE): if(rvType instanceof RandomVariableTypeInfo) rvType.rvType else TypeSystemProvider::UNDEFINED_TYPE 
							case(ListDefinitionTable::CONTINUOUS_OBS_VALUE): libDefn.getListDefinition(CONTINUOUS_OBS_TYPE_NAME).typeInfo
							default: TypeSystemProvider::UNDEFINED_TYPE
						}
						val dataSymb = dataStmts.findFirst[st | if(st instanceof SymbolDefinition) st.name == rv.name else false]
						if(dataSymb === null){
							errorLambda.apply(MdlValidator::MODEL_DATA_MISMATCH, "observation " + rv.name +" has no match in obj: '" + matchingObj.name + "'.");
						}
						else if(!obsActualType.isCompatible(dataSymb.typeFor)){
							errorLambda.apply(MdlValidator::INCOMPATIBLE_TYPES, "observation " + rv.name +" has an inconsistent type with its match in obj: '" + matchingObj.name + "'.");
						}
					}
				}
			}
		}
	}
	
	@Check
	def validateVariabilityLevels(MclObject it){
		val (String, String) => void errorLambda = [
					errorCode, errMsg| error(errMsg, MdlPackage.eINSTANCE.mclObject_Blocks, errorCode, '')
			]
		if(isMogObject){
			val varBlk = mdlObj.getBlocksByName(BlockDefinitionTable::VAR_LVL_BLK_NAME)
			var SymbolReference refId = null
			if(!varBlk.isEmpty){
				refId = varBlk.head.blkArgs.getArgumentExpression('reference')?.symbolRef
			}
			if(dataObj !== null){
				val dataVarLvls = dataObj.dataVariabilityLevels
				if(refId !== null){
					// check that data obj has an id level. If so then good.
					if(!dataVarLvls.exists[
						firstAttributeList.isMatchingDataUse(ListDefinitionTable::ID_USE_VALUE)
					]){
						errorLambda.apply(MdlValidator::MODEL_DATA_MISMATCH, "An 'id' variability level is required in dataObj: '" + dataObj.name + "'")
					}
				}
				for(mdlOb : mdlObj.mdlVariabilityLevels){
					if(mdlOb instanceof SymbolDefinition){
						if(refId === null || refId.ref != mdlOb){ 
							val dataOb = dataVarLvls.findFirst[name == (mdlOb as ListDefinition).name]
							if(dataOb === null){
								errorLambda.apply(MdlValidator::MODEL_DATA_MISMATCH, "variability level " + mdlOb.name +" has no match in dataObj");
							}
							else if(mdlOb.isParameterVarLevel){
								if(!dataOb.firstAttributeList.isMatchingDataUse(ListDefinitionTable::ID_USE_VALUE, ListDefinitionTable::VARLVL_USE_VALUE)){
									errorLambda.apply(MdlValidator::INCOMPATIBLE_TYPES, "variability level " + mdlOb.name +" has an inconsistent type with its match in the dataObj");
								}
							}
							else if(mdlOb.isObservationVarLevel)
								if(!dataOb.firstAttributeList.isMatchingDataUse(ListDefinitionTable::OBS_USE_VALUE)){
									errorLambda.apply(MdlValidator::INCOMPATIBLE_TYPES, "variability level " + mdlOb.name +" has an inconsistent type with its match in the dataObj");
								}
						}
					}
				}
			}
			else if(designObj !== null){
				val libDefn = getLibraryForObject
				val varLvlTypeInfo = libDefn.getListDefinition('varLevel')?.typeInfo ?: TypeSystemProvider::UNDEFINED_TYPE
				val dvBlk = designObj.getBlocksByName(BlockDefinitionTable::DECLARED_VARS_BLK)?.head
				val dvStmts = dvBlk.statementsFromBlock
				val mVarLvls = mdlObj.mdlVariabilityLevels 
				for(mdlOb : mVarLvls){
					if(refId === null || refId.ref != mdlOb){
						if(mdlOb instanceof SymbolDefinition){
							val dataOb = dvStmts.findFirst[stmt|
								if(stmt instanceof SymbolDefinition) stmt.name == mdlOb.name else false
							]
							if(dataOb === null){
								if(mdlOb.isParameterVarLevel)
									errorLambda.apply(MdlValidator::MODEL_DATA_MISMATCH, "variability level " + mdlOb.name +" has no match in designObj");
							}
							else if(dataOb instanceof SymbolDefinition){
								if(dataOb.typeFor != varLvlTypeInfo){
									errorLambda.apply(MdlValidator::INCOMPATIBLE_TYPES, "variability level " + mdlOb.name +" has an inconsistent type with its match in the designObj");
								}
							}
						}
					}
				}
				for(desStmt : dvStmts){
					if(desStmt instanceof SymbolDefinition){
						val desStmtType = desStmt.typeFor
						if(desStmtType.isCompatible(varLvlTypeInfo))
							if(!mVarLvls.exists[mVStmt|
								if(mVStmt instanceof SymbolDefinition){
									desStmt.name == mVStmt.name
								}
								else true
							]){
								errorLambda.apply(MdlValidator::MODEL_DATA_MISMATCH, "variability level " + desStmt.name +" in object '" + designObj.name + "' has no match in modelObj");
							}
					}
				}
			}
		}
	}
	
	@Check
	def validateIndependentVariable(MclObject it){
		val (String, String) => void errorLambda = [
					errorCode, errMsg| error(errMsg, MdlPackage.eINSTANCE.mclObject_Blocks, errorCode, '')
			]
		if(isMogObject && dataObj !== null){
			val dataOb = dataObj.dataIdv
			val mdlOb = mdlObj.mdlIdv
			if(mdlOb !== null){
				val mdlIdvName = (mdlOb as EquationDefinition).name
				// idv does not need to have the same name
				if(dataOb === null){ //} || dataOb.name != mdlIdvName){
					errorLambda.apply(MdlValidator::MODEL_DATA_MISMATCH, "independent variable " + mdlIdvName +" has no match in dataObj");
				}
			}
		}
	}
	
	def getDosingVariables(MclObject it){
		val dosingSuperType = libraryForObject.getListDefinition('dosingTarget')?.typeInfo ?: TypeSystemProvider::UNDEFINED_TYPE
		val List<SymbolDefinition> retVal = new ArrayList<SymbolDefinition>
		val blks = getBlocksByName(BlockDefinitionTable::DECLARED_VARS_BLK)
		blks.forEach[b|
			b.statements.forEach[s|
				if(s instanceof SymbolDefinition){
					if(dosingSuperType.isCompatible(s.typeFor))
						retVal.add(s)
				}
			]
		]
		retVal
	}

	@Check
	def validateDosing(MclObject it){
		val (String, String) => void errorLambda = [
					errorCode, errMsg| error(errMsg, MdlPackage.eINSTANCE.mclObject_Blocks, errorCode, '')
			]
		if(isMogObject){
			val mdlStmts = mdlObj.mdlPredictionVariables
			var obj = dataObj
			if(obj === null)
				obj = designObj
			
			for(dataDose : obj?.getDosingVariables ?: Collections::emptyList){
				val stmt = mdlStmts.findFirst[st|
					if(st instanceof SymbolDefinition) st.name == dataDose.name else false
				]
				if(stmt === null){
					errorLambda.apply(MdlValidator::MODEL_DATA_MISMATCH, "dosing variable " + dataDose.name +" has no match in mdlObj");
				}
				else if(stmt.isMdlCompartmentMacro){
					if(!stmt.isCmtDosingMacro){
						errorLambda.apply(MdlValidator::INCOMPATIBLE_TYPES, "dosing variable '" + dataDose.name +"' can only dose to a dosing compartment macro.");
					}
				}
				else if(!stmt.typeFor.isCompatible(dataDose.typeFor)){
					errorLambda.apply(MdlValidator::INCOMPATIBLE_TYPES, "dosing variable " + dataDose.name +" has an inconsistent type with its match in the mdlObj");
				}
			}
		}
	}
	
	def boolean isAssigned(Statement stmt){
		switch(stmt){
			EquationTypeDefinition: stmt.expression !== null
			default: true
		}
	}
	
	def private validateModelAndPriorParams(MclObject it, List<Statement> mdlParamStatements){
		val (String, String) => void errorLambda = [ 
					errorCode, errMsg| error(errMsg, MdlPackage.eINSTANCE.mclObject_Blocks, errorCode, '')
				]
		val (String, String) => void warningLambda = [
					warningCode, errMsg| warning(errMsg, MdlPackage.eINSTANCE.mclObject_Blocks, warningCode, '')
				]
		for(mdlStmt : mdlParamStatements){
			if(mdlStmt instanceof SymbolDefinition){
				val parStmt = priorObj.findMdlSymbolDefn(mdlStmt.name)
				if(parStmt === null){
					if(!mdlStmt.isAssigned) 
						errorLambda.apply(MdlValidator::MODEL_DATA_MISMATCH, "parameter '" + mdlStmt.name +"' has no match in priorObj");
				}
				else if(!mdlStmt.typeFor.isCompatible(parStmt.typeFor)){
					errorLambda.apply(MdlValidator::INCOMPATIBLE_TYPES, "parameter '" + parStmt.name +"' has an inconsistent type with its match in the priorObj");
				}
				else if(mdlStmt.isAssigned){
					warningLambda.apply(MdlValidator::MASKING_PARAM_ASSIGNMENT, "value assigned to parameter '" + parStmt.name +"' in mdlObj is overridden by value in priorObj");
				}
			}
		}
	}
	
	@Check
	def validateStructuralParametersWithPrior(MclObject it){
		if(isMogObject && priorObj !== null){
			validateModelAndPriorParams(mdlObj.mdlStructuralParameters)
		}
	}
	
	@Check
	def validateVariabilityParametersWithPrior(MclObject it){
		if(isMogObject && priorObj !== null){
			validateModelAndPriorParams(mdlObj.mdlVariabilityParameters)
		}
	}

	@Check
	def validateStructuralParameters(MclObject it){
		val (String, String) => void errorLambda = [ 
					errorCode, errMsg| error(errMsg, MdlPackage.eINSTANCE.mclObject_Blocks, errorCode, '')
				]
		val (String, String) => void warningLambda = [
					warningCode, errMsg| warning(errMsg, MdlPackage.eINSTANCE.mclObject_Blocks, warningCode, '')
				]
		if(isMogObject && paramObj !== null){
			for(mdlStmt : mdlObj.mdlStructuralParameters){
				if(mdlStmt instanceof SymbolDefinition){
					val parStmt = paramObj.findMdlSymbolDefn(mdlStmt.name)
					if(parStmt === null){
						if(!mdlStmt.isAssigned) 
							errorLambda.apply(MdlValidator::MODEL_DATA_MISMATCH, "parameter '" + mdlStmt.name +"' has no match in parObj");
					}
					if((parStmt as Statement).isParVariabilityParam){
						errorLambda.apply(MdlValidator::MODEL_DATA_MISMATCH, "Parameter '" + mdlStmt.name +"' in mdlObj cannot match a variability parameter in the parObj");
					}
					else if(!mdlStmt.typeFor.isCompatible(parStmt.typeFor)){
						errorLambda.apply(MdlValidator::INCOMPATIBLE_TYPES, "parameter '" + parStmt.name +"' has an inconsistent type with its match in the parObj");
					}
					else if(mdlStmt.isAssigned){
						warningLambda.apply(MdlValidator::MASKING_PARAM_ASSIGNMENT, "value assigned to parameter '" + parStmt.name +"' in mdlObj is overridden by value in parObj");
					}
				}
			}
		}
	}
	
	@Check
	def validateVariabilityParameters(MclObject it){
		val (String, String) => void errorLambda = [ 
					errorCode, errMsg| error(errMsg, MdlPackage.eINSTANCE.mclObject_Blocks, errorCode, '')
				]
		val (String, String) => void warningLambda = [
					warningCode, errMsg| warning(errMsg, MdlPackage.eINSTANCE.mclObject_Blocks, warningCode, '')
				]
		if(isMogObject && paramObj !== null){
			for(mdlStmt : mdlObj.mdlVariabilityParameters){
				if(mdlStmt instanceof SymbolDefinition){
					val parStmt = paramObj.findMdlSymbolDefn(mdlStmt.name)
					if(parStmt === null){
						if(!mdlStmt.isAssigned) 
							errorLambda.apply(MdlValidator::MODEL_DATA_MISMATCH, "parameter '" + mdlStmt.name +"' has no match in parObj");
					}
					else{
						if((parStmt as Statement).isParStructuralParam){
							errorLambda.apply(MdlValidator::MODEL_DATA_MISMATCH, "Parameter '" + mdlStmt.name +"' in mdlObj cannot match a structural parameter in the parObj");
						}
						else if(!mdlStmt.typeFor.isCompatible(parStmt.typeFor)){
							errorLambda.apply(MdlValidator::INCOMPATIBLE_TYPES, "parameter '" + parStmt.name +"' has an inconsistent type with its match in the parObj");
						}
						else if(mdlStmt.isAssigned){
							warningLambda.apply(MdlValidator::MASKING_PARAM_ASSIGNMENT, "value assigned to parameter '" + parStmt.name +"' in mdlObj is overridden by value in parObj");
						}
					}
				}
			}
		}
	}
	
	@Check
	def validateObjectReferenceInMog(ListDefinition it){
		val blk = EcoreUtil2.getContainerOfType(eContainer, BlockStatement)
		if(blk?.identifier == BlockDefinitionTable::MOG_OBJ_NAME){
			val mogObj = EcoreUtil2.getContainerOfType(eContainer, MclObject)
			val objType = firstAttributeList.getAttributeEnumValue('type')
			if(findMdlObject(mogObj, name, objType) === null){
				error("the object '" + name + "' cannot be found",
						MdlLibPackage.eINSTANCE.symbolDefinition_Name, MdlValidator::MCLOBJ_REF_UNRESOLVED, name)
			}
		}
	}
	
	
	def private getDataVariableTypes(MclObject dataObject){
		val List<SymbolDefinition> retVal = new ArrayList<SymbolDefinition>
		dataObject.getBlocksByName(BlockDefinitionTable::DECLARED_VARS_BLK).forEach[blk|
			blk.statements.forEach[st|
				if(st instanceof SymbolDefinition) retVal.add(st)
			]
		]
		dataObject.getBlocksByName(BlockDefinitionTable::DIV_BLK_NAME, BlockDefinitionTable::DATA_DERIV_BLK_NAME).forEach[blk|
			blk.statements.filter[st|
				if(st instanceof ListDefinition) 
					st.firstAttributeList.getAttributeEnumValue(ListDefinitionTable::USE_ATT) == ListDefinitionTable::VARIABLE_USE_VALUE
					|| st.firstAttributeList.getAttributeEnumValue(ListDefinitionTable::USE_ATT) == ListDefinitionTable::DOSE_TIME_USE_VALUE
					|| st.firstAttributeList.getAttributeEnumValue(ListDefinitionTable::USE_ATT) == ListDefinitionTable::DOSE_INTERVAL_USE_VALUE
				else false
			].forEach[st|
				if(st instanceof SymbolDefinition) retVal.add(st)
				]
		]
		retVal
	}
	
	
	def private getDesignVariableTypes(MclObject desObject){
		val List<SymbolDefinition> retVal = new ArrayList<SymbolDefinition>
		desObject.getBlocksByName(BlockDefinitionTable::DECLARED_VARS_BLK).forEach[blk|
			blk.statements.forEach[st|
				if(st instanceof SymbolDefinition) retVal.add(st)
			]
		]
		retVal
	}
	
	
	def private checkInitialisingVars(MclObject it, SymbolDefinition stmt, MclObject otherObject, List<SymbolDefinition> matchingVars, 
										(String, String) => void uninitLambda,
										(String, String, String) => void typeErrorLambda){
		// check if initialised elsewhere.
		val matchingVar = matchingVars.findFirst[
			name == stmt.name
		]
		if(matchingVar === null){
			uninitLambda.apply(stmt.name, mdlObj.name)
		}
		else{
			if(!matchingVar.typeFor.underlyingType.isCompatible(stmt.typeFor.underlyingType)){
				typeErrorLambda.apply(stmt.name, mdlObj.name, otherObject.name)
			}
		}
	}
	
	@Check
	def validateVariablesInitialised(MclObject it){
		if(isMogObject){
			
			val mObj = mdlObj
			if(mObj !== null){
				for(mBlk : mObj.modelPredictionBlocks){
					for(stmt : mBlk.statements){
						if(stmt instanceof EquationTypeDefinition){
							if(stmt.expression === null){
								if(dataObj !== null){
									val dObj = dataObj
									checkInitialisingVars(stmt, dObj, dObj.dataVariableTypes, 
										[s, o|
										error("variable '" + s + "' in object '" + o + "' is not initialised",
												MdlPackage.eINSTANCE.mclObject_Name, MdlValidator::SYMBOL_NOT_INITIALISED, s)
									],
									 [s, o, d|
										error("variable '" + s +"' in object '" + o + "' has an inconsistent type with its match in '" + d + "'.",
											MdlPackage.eINSTANCE.mclObject_Name, MdlValidator::INCOMPATIBLE_TYPES, s)
									])
								}
								else if(designObj !== null){
									val dObj = designObj
									checkInitialisingVars(stmt, dObj, dObj.designVariableTypes, 
										[s, o|
										error("variable '" + s + "' in object '" + o + "' is not initialised",
												MdlPackage.eINSTANCE.mclObject_Name, MdlValidator::SYMBOL_NOT_INITIALISED, s)
									],
									 [s, o, d|
										error("variable '" + s +"' in object '" + o + "' has an inconsistent type with its match in '" + d + "'.",
											MdlPackage.eINSTANCE.mclObject_Name, MdlValidator::INCOMPATIBLE_TYPES, s)
									])
								}
							}
						}
						else if(stmt instanceof BlockStatement){
							if(stmt.blkId.name == BlockDefinitionTable::MDL_CMT_BLK){
								for(cmtStmt : stmt.statements){
									if(cmtStmt instanceof ListDefinition){
										if(cmtStmt.isCmtDosingMacro){
											if(dataObj !== null){
												val dObj = dataObj
												checkInitialisingVars(cmtStmt, dObj, dObj.dataVariableTypes,
													[s, o|
														error("dosing macro '" + s + "' in object '" + o + "' is not initialised",
																MdlPackage.eINSTANCE.mclObject_Name, MdlValidator::SYMBOL_NOT_INITIALISED, s)
													],
													 [s, o, d|
														error("dosing macro '" + s +"' in object '" + o + "' has an inconsistent type with its match in '" + d + "'.",
															MdlPackage.eINSTANCE.mclObject_Name, MdlValidator::INCOMPATIBLE_TYPES, s)
													]
												)
											}
											else if(designObj !== null){
												val dObj = designObj
												checkInitialisingVars(cmtStmt, dObj, dObj.designVariableTypes, 
													[s, o|
													error("variable '" + s + "' in object '" + o + "' is not initialised",
															MdlPackage.eINSTANCE.mclObject_Name, MdlValidator::SYMBOL_NOT_INITIALISED, s)
												],
												 [s, o, d|
													error("variable '" + s +"' in object '" + o + "' has an inconsistent type with its match in '" + d + "'.",
														MdlPackage.eINSTANCE.mclObject_Name, MdlValidator::INCOMPATIBLE_TYPES, s)
												])
											}
										}
									}
								}
							}
							else if(stmt.blkId.name == BlockDefinitionTable::MDL_DEQ_BLK){
								for(cmtStmt : stmt.statements){
									if(cmtStmt instanceof ListDefinition){
										if(dataObj !== null){
											val dObj = dataObj
											checkDerivInitialised(cmtStmt, dObj, dObj.dataVariableTypes,
												[s, o, d|
													error("variable '" + s +"' in object '" + o + "' has an inconsistent type with its match in '" + d + "'.",
														MdlPackage.eINSTANCE.mclObject_Name, MdlValidator::INCOMPATIBLE_TYPES, s)
												]
											)
										}
										else if(designObj !== null){
											val dObj = designObj
											checkDerivInitialised(cmtStmt, dObj, dObj.designVariableTypes,
												[s, o, d|
													error("variable '" + s +"' in object '" + o + "' has an inconsistent type with its match in '" + d + "'.",
														MdlPackage.eINSTANCE.mclObject_Name, MdlValidator::INCOMPATIBLE_TYPES, s)
												]
											)
										}
									}
								}
							}
						}
						else if(stmt instanceof ListDefinition){
							if(dataObj !== null){
								val dObj = dataObj
								checkDerivInitialised(stmt, dObj, dObj.dataVariableTypes,
									[s, o, d|
										error("variable '" + s +"' in object '" + o + "' has an inconsistent type with its match in '" + d + "'.",
											MdlPackage.eINSTANCE.mclObject_Name, MdlValidator::INCOMPATIBLE_TYPES, s)
									]
								)
							}
							else if(designObj !== null){
								val dObj = designObj
								checkDerivInitialised(stmt, dObj, dObj.designVariableTypes,
									[s, o, d|
										error("variable '" + s +"' in object '" + o + "' has an inconsistent type with its match in '" + d + "'.",
											MdlPackage.eINSTANCE.mclObject_Name, MdlValidator::INCOMPATIBLE_TYPES, s)
									]
								)
							}
						}
					}
				}
			}
			
		}
	}
	
	def private checkDerivInitialised(MclObject it, ListDefinition stmt, MclObject otherObject, List<SymbolDefinition> matchingVars, 
										(String, String, String) => void typeErrorLambda){
		val mObj = mdlObj
		val derivListType = libraryForObject.getListDefinition('DerivList').typeInfo
		val listType = stmt.typeFor
		if(listType == derivListType){
				val matchingVar = matchingVars.findFirst[
					name == stmt.name
				]
//				if(matchingVar === null){
//					error("variable '" + stmt.name + "' in object '" + mObj.name + "' is not initialised",
//							MdlPackage.eINSTANCE.mclObject_Name, MdlValidator::SYMBOL_NOT_INITIALISED, stmt.name)
//				}
//				else{
					if(matchingVar !== null && !matchingVar.typeFor.underlyingType.isCompatible(stmt.typeFor.underlyingType)){
						typeErrorLambda.apply(stmt.name, mObj.name, otherObject.name)
//						error("variable '" + stmt.name +"' in object '" + mObj.name + "' has an inconsistent type with its match in '" + dObj.name + "'.",
//							MdlPackage.eINSTANCE.mclObject_Name, MdlValidator::INCOMPATIBLE_TYPES, stmt.name)
					}
//				}
		}
	}
	
	
	@Check
	def validateMogComposition(MclObject it){
		if(isMogObject){
			if(mdlObj === null)
				error("A mdlObj is not defined in mogObj '" + name + "'.", MdlPackage::eINSTANCE.mclObject_Blocks, MdlValidator::MOGOBJ_MALFORMED)
			if(taskObj === null)
				error("A taskObj is not defined in mogObj '" + name + "'.", MdlPackage::eINSTANCE.mclObject_Blocks, MdlValidator::MOGOBJ_MALFORMED)
			if(dataObj === null && designObj === null)
				error("Neither a dataObj not a designObj. You must define one to make mogObj '" + name + "' valid.", MdlPackage::eINSTANCE.mclObject_Blocks, MdlValidator::MOGOBJ_MALFORMED)
			if(dataObj !== null && designObj !== null)
				error("Both a dataObj and a designObj defined in mogObj '" + name + "'. You can only use one.", MdlPackage::eINSTANCE.mclObject_Blocks, MdlValidator::MOGOBJ_MALFORMED)
			if(paramObj === null && priorObj === null)
				error("Neither a paramObj not a priorObj. You must define one to make mogObj '" + name + "' valid.", MdlPackage::eINSTANCE.mclObject_Blocks, MdlValidator::MOGOBJ_MALFORMED)
			if(paramObj !== null && priorObj !== null)
				error("Both a paramObj and a priorObj defined in mogObj '" + name + "'. You can only use one.", MdlPackage::eINSTANCE.mclObject_Blocks, MdlValidator::MOGOBJ_MALFORMED)
		}
	}
	
}