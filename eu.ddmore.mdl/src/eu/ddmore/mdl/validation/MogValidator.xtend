package eu.ddmore.mdl.validation

import eu.ddmore.mdl.mdl.BlockStatement
import eu.ddmore.mdl.mdl.EnumerationDefinition
import eu.ddmore.mdl.mdl.EquationDefinition
import eu.ddmore.mdl.mdl.EquationTypeDefinition
import eu.ddmore.mdl.mdl.ListDefinition
import eu.ddmore.mdl.mdl.Mcl
import eu.ddmore.mdl.mdl.MclObject
import eu.ddmore.mdl.mdl.MdlPackage
import eu.ddmore.mdl.mdl.Statement
import eu.ddmore.mdl.provider.BlockDefinitionTable
import eu.ddmore.mdl.provider.ListDefinitionProvider
import eu.ddmore.mdl.provider.ListDefinitionTable
import eu.ddmore.mdl.provider.MogDefinitionProvider
import eu.ddmore.mdl.type.TypeSystemProvider
import eu.ddmore.mdl.utils.BlockUtils
import eu.ddmore.mdl.utils.DomainObjectModelUtils
import eu.ddmore.mdl.utils.MdlUtils
import eu.ddmore.mdllib.mdllib.MdlLibPackage
import eu.ddmore.mdllib.mdllib.SymbolDefinition
import java.util.ArrayList
import java.util.Collections
import org.eclipse.xtext.EcoreUtil2
import org.eclipse.xtext.validation.AbstractDeclarativeValidator
import org.eclipse.xtext.validation.Check
import org.eclipse.xtext.validation.EValidatorRegistrar
import java.util.List
import eu.ddmore.mdl.mdl.AnonymousListStatement
import eu.ddmore.mdl.utils.ExpressionUtils
import eu.ddmore.mdl.utils.MdlLibUtils
import eu.ddmore.mdl.utils.LibraryUtils
import eu.ddmore.mdl.type.TypeInfo
import eu.ddmore.mdl.type.RandomVariableTypeInfo

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
			if(mdlObj != null && dataObj != null){
				val expectedMdlCovars = new ArrayList<SymbolDefinition>
				
				mdlObj.mdlCovariateDefns.forEach[
					switch(it){
						EquationDefinition case expression == null: expectedMdlCovars.add(it)
						EnumerationDefinition: expectedMdlCovars.add(it)
					}
				]
				val dataCovars = dataObj.dataCovariateDefns
				for(mdlCov : expectedMdlCovars){
					val dataCovar = dataCovars.findFirst[name == mdlCov.name]
					if(dataCovar == null){
						errorLambda.apply(MdlValidator::MODEL_DATA_MISMATCH, "covariate " + mdlCov.name +" has no match in dataObj");
					}
					else if(!mdlCov.typeFor.isCompatible(dataCovar.typeFor)){
						errorLambda.apply(MdlValidator::INCOMPATIBLE_TYPES, "covariate " + mdlCov.name +" has an inconsistent type with its match in the dataObj");
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
					if(dataSymb == null){
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
					if(rv != null){
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
						if(dataSymb == null){
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
			if(dataObj != null){
				val dataVarLvls = dataObj.dataVariabilityLevels
				for(mdlOb : mdlObj.mdlVariabilityLevels){
					if(mdlOb instanceof SymbolDefinition){
						val dataOb = dataVarLvls.findFirst[name == (mdlOb as ListDefinition).name]
						if(dataOb == null){
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
			else if(designObj != null){
				val libDefn = getLibraryForObject
				val varLvlTypeInfo = libDefn.getListDefinition('varLevel')?.typeInfo ?: TypeSystemProvider::UNDEFINED_TYPE
				val dvBlk = designObj.getBlocksByName(BlockDefinitionTable::DECLARED_VARS_BLK)?.head
				val dvStmts = dvBlk.statementsFromBlock
				val mVarLvls = mdlObj.mdlVariabilityLevels 
				for(mdlOb : mVarLvls){
					if(mdlOb instanceof SymbolDefinition){
						val dataOb = dvStmts.findFirst[stmt|
							if(stmt instanceof SymbolDefinition) stmt.name == mdlOb.name else false
						]
//						if(dataOb == null){
//							if(mdlOb.isParameterVarLevel)
//								errorLambda.apply(MdlValidator::MODEL_DATA_MISMATCH, "variability level " + mdlOb.name +" has no match in designObj");
//						}
//						else
						if(dataOb instanceof SymbolDefinition){
							if(dataOb.typeFor != varLvlTypeInfo){
								errorLambda.apply(MdlValidator::INCOMPATIBLE_TYPES, "variability level " + mdlOb.name +" has an inconsistent type with its match in the designObj");
							}
						}
					}
				}
				for(desStmt : dvStmts){
					if(desStmt instanceof SymbolDefinition){
						val desStmtType = desStmt.typeFor
						if(!mVarLvls.exists[mVStmt|
							if(mVStmt instanceof SymbolDefinition){
								if(desStmtType == varLvlTypeInfo)
									desStmt.name == mVStmt.name
								else true
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
		if(isMogObject && dataObj != null){
			val dataOb = dataObj.dataIdv
			val mdlOb = mdlObj.mdlIdv
			if(mdlOb != null){
				val mdlIdvName = (mdlOb as EquationDefinition).name
				// idv does not need to have the same name
				if(dataOb == null){ //} || dataOb.name != mdlIdvName){
					errorLambda.apply(MdlValidator::MODEL_DATA_MISMATCH, "independent variable " + mdlIdvName +" has no match in dataObj");
				}
			}
		}
	}
	
	@Check
	def validateDosing(MclObject it){
		val (String, String) => void errorLambda = [
					errorCode, errMsg| error(errMsg, MdlPackage.eINSTANCE.mclObject_Blocks, errorCode, '')
			]
		if(isMogObject){
			val mdlStmts = mdlObj.mdlPredictionVariables
			for(dataDose : dataObj?.dataDosingVariables ?: Collections::emptyList){
				val stmt = mdlStmts.findFirst[if(it instanceof SymbolDefinition) (it as SymbolDefinition).name == dataDose.name else false]
				if(stmt == null){
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
			EquationTypeDefinition: stmt.expression != null
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
				if(parStmt == null){
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
		if(isMogObject && priorObj != null){
			validateModelAndPriorParams(mdlObj.mdlStructuralParameters)
		}
	}
	
	@Check
	def validateVariabilityParametersWithPrior(MclObject it){
		if(isMogObject && priorObj != null){
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
		if(isMogObject && paramObj != null){
			for(mdlStmt : mdlObj.mdlStructuralParameters){
				if(mdlStmt instanceof SymbolDefinition){
					val parStmt = paramObj.findMdlSymbolDefn(mdlStmt.name)
					if(parStmt == null){
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
		if(isMogObject && paramObj != null){
			for(mdlStmt : mdlObj.mdlVariabilityParameters){
				if(mdlStmt instanceof SymbolDefinition){
					val parStmt = paramObj.findMdlSymbolDefn(mdlStmt.name)
					if(parStmt == null){
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
			if(findMdlObject(mogObj, name, objType) == null){
				error("the object '" + name + "' cannot be found",
						MdlLibPackage.eINSTANCE.symbolDefinition_Name, MdlValidator::MCLOBJ_REF_UNRESOLVED, name)
			}
		}
	}
	
	@Check
	def validateMogComposition(MclObject it){
		if(isMogObject){
			if(mdlObj == null)
				error("A mdlObj is not defined in mogObj '" + name + "'.", MdlPackage::eINSTANCE.mclObject_Blocks, MdlValidator::MOGOBJ_MALFORMED)
			if(taskObj == null)
				error("A taskObj is not defined in mogObj '" + name + "'.", MdlPackage::eINSTANCE.mclObject_Blocks, MdlValidator::MOGOBJ_MALFORMED)
			if(dataObj == null && designObj == null)
				error("Neither a dataObj not a designObj. You must define one to make mogObj '" + name + "' valid.", MdlPackage::eINSTANCE.mclObject_Blocks, MdlValidator::MOGOBJ_MALFORMED)
			if(dataObj != null && designObj != null)
				error("Both a dataObj and a designObj defined in mogObj '" + name + "'. You can only use one.", MdlPackage::eINSTANCE.mclObject_Blocks, MdlValidator::MOGOBJ_MALFORMED)
			if(paramObj == null && priorObj == null)
				error("Neither a paramObj not a priorObj. You must define one to make mogObj '" + name + "' valid.", MdlPackage::eINSTANCE.mclObject_Blocks, MdlValidator::MOGOBJ_MALFORMED)
			if(paramObj != null && priorObj != null)
				error("Both a paramObj and a priorObj defined in mogObj '" + name + "'. You can only use one.", MdlPackage::eINSTANCE.mclObject_Blocks, MdlValidator::MOGOBJ_MALFORMED)
		}
	}
	
}