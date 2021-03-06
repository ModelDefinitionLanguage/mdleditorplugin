package eu.ddmore.mdl.validation

import eu.ddmore.mdl.mdl.AttributeList
import eu.ddmore.mdl.mdl.BlockStatement
import eu.ddmore.mdl.mdl.BlockStatementBody
import eu.ddmore.mdl.mdl.CategoryValueDefinition
import eu.ddmore.mdl.mdl.EnumPair
import eu.ddmore.mdl.mdl.EquationDefinition
import eu.ddmore.mdl.mdl.ListDefinition
import eu.ddmore.mdl.mdl.MclObject
import eu.ddmore.mdl.mdl.MdlPackage
import eu.ddmore.mdl.mdl.RandomVariableDefinition
import eu.ddmore.mdl.mdl.RelationalExpression
import eu.ddmore.mdl.mdl.Statement
import eu.ddmore.mdl.mdl.SubListExpression
import eu.ddmore.mdl.mdl.SymbolReference
import eu.ddmore.mdl.mdl.TransformedDefinition
import eu.ddmore.mdl.mdl.ValuePair
import eu.ddmore.mdl.mdl.util.MdlSwitch
import eu.ddmore.mdl.provider.BlockDefinitionTable
import eu.ddmore.mdl.provider.BuiltinFunctionProvider
import eu.ddmore.mdl.provider.ListDefinitionProvider
import eu.ddmore.mdl.provider.ListDefinitionTable
import eu.ddmore.mdl.type.TypeInfoClass
import eu.ddmore.mdl.type.TypeSystemProvider
import eu.ddmore.mdl.utils.BlockUtils
import eu.ddmore.mdl.utils.ConstantEvaluation
import eu.ddmore.mdl.utils.CycleDetectionUtils
import eu.ddmore.mdl.utils.ExpressionUtils
import eu.ddmore.mdl.utils.MdlUtils
import eu.ddmore.mdllib.mdllib.MdlLibPackage
import eu.ddmore.mdllib.mdllib.SymbolDefinition
import java.util.Collections
import org.eclipse.xtext.EcoreUtil2
import org.eclipse.xtext.validation.Check
import org.eclipse.xtext.validation.EValidatorRegistrar
import eu.ddmore.mdl.mdl.AnonymousListStatement
import org.eclipse.emf.ecore.EObject

class MdlCustomValidator extends AbstractMdlValidator {

	extension BuiltinFunctionProvider bfp = new BuiltinFunctionProvider 
	extension ListDefinitionProvider ldp = new ListDefinitionProvider
	extension TypeSystemProvider mtp = new TypeSystemProvider
	extension MdlUtils mu = new MdlUtils
//	extension DependencyWalker dw = new DependencyWalker 
	extension ConstantEvaluation ce = new ConstantEvaluation 
	extension CycleDetectionUtils cdu = new CycleDetectionUtils
	extension BlockUtils bu = new BlockUtils
	extension ExpressionUtils eu = new ExpressionUtils

	override register(EValidatorRegistrar registrar){}

	public static val COV_ATT = 'cov'
	public static val FIX_EFF_SUBLIST = "Sublist:fixEffAtts"


	@Check
	def validateTransforms(TransformedDefinition it){
		if(!isValidTransform){
			error("'" + transform.name + "' cannot be used as a transformation function on the LHS of an equation",
				MdlPackage::eINSTANCE.transformedDefinition_Transform, MdlValidator::INVALID_LHS_FUNC, transform.name)
		}
		if(!isLhsTransformPermitted || !isValidRhsTransformPermitted){
			error("Use of a transformation function on the LHS of the equation is not permitted in this context",
				MdlPackage::eINSTANCE.transformedDefinition_Transform, MdlValidator::INVALID_LHS_FUNC, transform.name)
		}
	}
	
	@Check
	def validateIndividualParameterDefinitions(EnumPair it){
		val stmt = EcoreUtil2.getContainerOfType(eContainer, Statement)
		switch(stmt){
			TransformedDefinition:
				checkConsistentLinearTransformation(stmt, it,
					[lhs, rhs| error("transformation used on LHS ('" + lhs + "') must match the RHS ('" + rhs + "')",
						MdlPackage::eINSTANCE.valuePair_Expression, MdlValidator::INVALID_LHS_FUNC, lhs)
					]
				)
			EquationDefinition:
				checkNonTransformedIndiv(stmt, it,
					[error("no transformation used on the LHS, so cannot use on the RHS of the equation",
						MdlPackage::eINSTANCE.valuePair_Expression, MdlValidator::INVALID_LHS_FUNC, "")
					]
				)
		}
	}

	@Check
	def validateCategoryRelations(RelationalExpression it){
		val leftType = leftOperand?.typeFor
		val rightType = rightOperand?.typeFor
		if((leftType !== null && leftType.typeClass == TypeInfoClass.Category)  || 
			(rightType !== null && rightType.typeClass == TypeInfoClass.Category)){
			error("Cannot use inequality operators with categorical types", MdlPackage::eINSTANCE.relationalExpression_Feature,
				MdlValidator::INVALID_ENUM_RELATION_OPERATOR, feature)
		}
	}

	def checkNonTransformedIndiv(EquationDefinition transDefn, EnumPair transArg, () => void incompatibleTransforms) {
		val call = EcoreUtil2.getContainerOfType(transArg.eContainer, SymbolReference)
		if(call !== null && transDefn !== null && transArg.argumentName == 'trans'){
			val transExpr = transArg.expression
			if(transExpr !== null && isValidTransformFunction(transExpr.enumValue) && transOnBothFuncs.contains(call.func)){
				incompatibleTransforms.apply
			}
		}
	}
	
	def isLhsTransformPermitted(TransformedDefinition it) {
		val expr = expression
		switch(expr){
			SymbolReference case(transOnBothFuncs.contains(expr.func)),
			SymbolReference case(transOnLHSFuncs.contains(expr.func)):
				true
			default:
				false
		}
	}

	static val transOnBothFuncs = #{ 'linear'  }
	static val transOnLHSFuncs = #{ 'general', 'combinedError1', 'combinedError2', 'additiveError', 'proportionalError' }
	
	def isValidRhsTransformPermitted(TransformedDefinition it) {
		val expr = expression
		switch(expr){
			SymbolReference case(transOnBothFuncs.contains(expr.func)):{
				val transExpr = expr.getArgumentEnumValue('trans')
				if(transExpr !== null) transExpr.isValidTransformFunction else false
			}
				
			SymbolReference case(transOnLHSFuncs.contains(expr.func)):
				true
			default:
				false
		}
	}
	
	def checkConsistentLinearTransformation(TransformedDefinition transDefn, EnumPair transArg, (String, String) => void incompatibleTransforms) {
		val call = EcoreUtil2.getContainerOfType(transArg.eContainer, SymbolReference)
		if(call !== null && transDefn !== null && transArg.argumentName == 'trans'){
			val transExpr = transArg.expression
			if(transExpr !== null && transOnBothFuncs.contains(call.func)){
				val rhsTrans = transExpr.enumValue
				if(transDefn.transform?.name != rhsTrans){
					incompatibleTransforms.apply(transDefn.transform.name, rhsTrans)
				}
			}
		}
	}

	static val UseDeps = #{
		'rate' -> #{'amt'},
		'ii' -> #{'amt' },
		'ss' -> #{ 'amt' },
		'addl' -> #{ 'amt', 'ii' }
	}


	@Check
	def validateDataUseHasDependecies(AttributeList it){
		val block = EcoreUtil2.getContainerOfType(eContainer, BlockStatement)
		if(block.identifier == BlockDefinitionTable::DIV_BLK_NAME){
			val enumVal = getAttributeEnumValue(ListDefinitionTable::USE_ATT)
			if(enumVal !== null && UseDeps.containsKey(enumVal)){
				for(depUse : UseDeps.get(enumVal)){
					if(!block.hasListWithUse(depUse)){
						error("A data column of use '" + depUse + "' is required by this column definition with 'use is " + enumVal + "'.",
							MdlPackage::eINSTANCE.attributeList_Attributes, MdlValidator::DEPENDENT_USE_MISSING, enumVal)
					}
				}
			}
		}
	}
	
	
	private def hasListWithUse(BlockStatement it, String useValue){
		val bdy = body
		if(bdy instanceof BlockStatementBody){
			for(stmt : bdy.statements){
				if(stmt instanceof ListDefinition){
					val useVal = stmt.firstAttributeList.getAttributeEnumValue(ListDefinitionTable::USE_ATT)
					if(useVal == useValue) return true
				}
			}
		}
		else false
	}
	
	
	@Check
	// check that a covariate is used in the fixed eff sublist
	def validateCovariateFixedEffect(ValuePair it){
		if(argumentName == COV_ATT){
			val expr = expression
			if(expr instanceof SymbolReference){
				val subList = EcoreUtil2.getContainerOfType(eContainer, SubListExpression)
				if(subList !== null && subList.typeFor.typeName == FIX_EFF_SUBLIST){
					// now check reference variable belongs to covariates block
					val refBlk = EcoreUtil2.getContainerOfType(expr.ref.eContainer, BlockStatement)
					if(refBlk !== null && refBlk.identifier != BlockDefinitionTable::COVARIATE_BLK_NAME){
						// ref cov is not in cov block so assume it not a covariate
						error("Attribute '" + argumentName + "' expects a reference to a covariate. '" + expr.ref.name + "' is not a covariate.",
							MdlPackage::eINSTANCE.valuePair_Expression,
							MdlValidator::INCOMPATIBLE_VARIABLE_REF, expr.ref.name)
					}
					else{
						val listDefn = EcoreUtil2.getContainerOfType(eContainer, AttributeList)
						if(listDefn.getAttributeEnumValue('type') == 'linear'){
							// now check that the cov is constant. If not then raise an error
							if(expr.ref.isIdvDepCovariate){
								error("Attribute '" + argumentName + "' expects a reference to a constant covariate. '" + expr.ref.name + "' is not constant.",
									MdlPackage::eINSTANCE.valuePair_Expression,
									MdlValidator::INCOMPATIBLE_VARIABLE_REF, expr.ref.name)
							}
						}
					} 
				}
			}
		}
	}
	
	
	val UniqueUseValue = #{
		ListDefinitionTable::AMT_USE_VALUE,
		ListDefinitionTable::CMT_USE_VALUE,
		ListDefinitionTable::DVID_USE_VALUE,
		ListDefinitionTable::OBS_USE_VALUE,
		ListDefinitionTable::ID_USE_VALUE,
		ListDefinitionTable::ADDL_USE_VALUE,
		ListDefinitionTable::II_USE_VALUE,
		ListDefinitionTable::SS_USE_VALUE,
		ListDefinitionTable::IDV_USE_VALUE,
		ListDefinitionTable::RATE_USE_VALUE,
		ListDefinitionTable::MDV_USE_VALUE
	}
	
	
	def isDuplicateUse(BlockStatement owningBlock, AttributeList refList, ValuePair queryUse){
		for (stmt : owningBlock.nonBlockStatements){
			if(stmt instanceof ListDefinition){
				val matchExpr = stmt.firstAttributeList.getAttributeExpression(ListDefinitionTable::USE_ATT)
				val enumVal = queryUse.expression.enumValue
				if(matchExpr != queryUse.expression &&  enumVal !== null && enumVal == matchExpr.enumValue){
					return true
				} 
			}
		}
		false
	}
	
	@Check
	// check if certain uses are already used in data definition
	def validateUseValueNotDuplicated(ValuePair it){
		val owningBlock = EcoreUtil2.getContainerOfType(eContainer, BlockStatement)
		val owningList = EcoreUtil2.getContainerOfType(eContainer, ListDefinition)
		if(owningBlock !== null && owningBlock.identifier == BlockDefinitionTable::DIV_BLK_NAME &&
			owningList !== null && argumentName == ListDefinitionTable::USE_ATT){
			val enumVal = owningList.firstAttributeList.getAttributeEnumValue(ListDefinitionTable::USE_ATT)
			if(UniqueUseValue.contains(enumVal)){
				if(owningBlock.isDuplicateUse(owningList.firstAttributeList, it)){
					error("Only one column definition can have a 'use' attribute set to '" + enumVal + "'.",
						MdlPackage::eINSTANCE.valuePair_Expression,
						MdlValidator::DUPLICATE_UNIQUE_USE_VALUE, enumVal)
				}
			}
		}
	}
	
	@Check
	def validateReferenceNotCircular(SymbolDefinition sd){
		hasNonDerivCycle(sd, [error("Symbol '" + sd.name + "' contains an expression that refers to itself.",
								MdlLibPackage::eINSTANCE.symbolDefinition_Name,
								MdlValidator::INVALID_CYCLE, name)], [!it.isDerivativeDefinition])
	}
	
	
	def findVarLevelDefnsOfType(BlockStatement blk, String type){
		blk.nonBlockStatements.filter[l|
			if(l instanceof ListDefinition)
				l.firstAttributeList.getAttributeEnumValue(ListDefinitionTable::VAR_LVL_TYPE_ATT) == type 
			else false
		]
	}
	
	def getLevelValue(ListDefinition it){
		val valExpr = firstAttributeList.getAttributeExpression(ListDefinitionTable::VAR_LVL_LEVEL_ATT)
		if(valExpr !== null){
			val i = valExpr.evaluateMathsExpression
			if(i !== null) i.intValue
			else Integer.MIN_VALUE
		}
		else Integer.MIN_VALUE
	}
	
	def isAnotherPresentWithSameLevel(BlockStatement blk, ListDefinition currDefn, int testLevel){
		blk.nonBlockStatements.exists[l|
			if(l instanceof ListDefinition)
				l != currDefn && l.levelValue == testLevel  
			else false
		]
	}
	
	@Check
	def validateVariabilityLevels(ListDefinition it){
		// what are the rules?
		// no duplicate numbers
		// number start at 1
		// if observation type then this must be 1
		// only one observation type permitted
		val owningBlock = EcoreUtil2.getContainerOfType(eContainer, BlockStatement)
		if(owningBlock?.identifier == BlockDefinitionTable::VAR_LVL_BLK_NAME){
			// check if permitted level value
			if(levelValue < 1){
				error("Variability Level definition '" + name + "': level cannot be less than 1.",
					MdlPackage::eINSTANCE.listDefinition_List, MdlValidator::VARIABILITY_LEVELS_MALFORMED, name
				)
			}
			if(owningBlock.isAnotherPresentWithSameLevel(it, levelValue)){
				error("Variability Level definition '" + name + "': the level value of " + levelValue + " is used by another definition.",
					MdlPackage::eINSTANCE.listDefinition_List, MdlValidator::VARIABILITY_LEVELS_MALFORMED, name
				)
			}
			// check that this is an obs defn first then check for duplicates
			if(firstAttributeList.getAttributeEnumValue(ListDefinitionTable::VAR_LVL_TYPE_ATT) == ListDefinitionTable::VAR_LVL_OBS_VALUE){
				val obsDefns = owningBlock.findVarLevelDefnsOfType(ListDefinitionTable::VAR_LVL_OBS_VALUE)
				if(obsDefns.size > 1){
					error("Variability Level definition '" + name + "': an observation definition already exists. There can be only one.",
							MdlPackage::eINSTANCE.listDefinition_List, MdlValidator::VARIABILITY_LEVELS_MALFORMED, name
					)
				}
				if(levelValue != 1){
					error("Variability Level definition '" + name + "': this is an observation level and so should have a level value of 1.",
							MdlPackage::eINSTANCE.listDefinition_List, MdlValidator::VARIABILITY_LEVELS_MALFORMED, name
					)
				}
			}
			else if(firstAttributeList.getAttributeEnumValue(ListDefinitionTable::VAR_LVL_TYPE_ATT) == ListDefinitionTable::VAR_LVL_PARAM_VALUE){
				 if(levelValue == 1 && owningBlock.findVarLevelDefnsOfType(ListDefinitionTable::VAR_LVL_OBS_VALUE).size > 0){
				 	// this is level 1, but also obs blocks
				 	error("Variability Level definition '" + name + "': an observation level is present so the level cannot be 1.",
				 			MdlPackage::eINSTANCE.listDefinition_List, MdlValidator::VARIABILITY_LEVELS_MALFORMED, name
				 	)
				 }
			}
			
		}
	}
	
	
	def private isSymbolReferenced(EObject containerToSearch, SymbolDefinition it, (SymbolReference) => boolean predicate){
		val visitor = new MdlSwitch<SymbolReference>(){
			override caseSymbolReference(SymbolReference sr){
				if(sr.ref.name == name && predicate.apply(sr)) sr else null
			}
		}
		val iter = EcoreUtil2.getAllContents(containerToSearch, true)
		var SymbolReference sr = null
		while(iter.hasNext && sr === null){
			sr = visitor.doSwitch(iter.next)
		}
		sr !== null
	}
	
	@Check
	def validateDeclaredVariablesUsed(EquationDefinition it){
		val owningBlock = EcoreUtil2.getContainerOfType(eContainer, BlockStatement)
		if(owningBlock.blkId.name == BlockDefinitionTable::DECLARED_VARS_BLK){
			// check design obj
//			val owningObj = EcoreUtil2.getContainerOfType(eContainer, MclObject)
//			if(owningObj.name == MdlValidator::DESIGNOBJ){
//				val visitor = new MdlSwitch<SymbolReference>(){
//					override caseSymbolReference(SymbolReference sr){
//						if(sr.ref.name == name) sr else null
//					}
//				}
//				val iter = EcoreUtil2.getAllContents(owningBlock.eContainer, true)
//				var SymbolReference sr = null
//				while(iter.hasNext && sr === null){
//					sr = visitor.doSwitch(iter.next)
//				}
//				if(sr === null){
				if(!isSymbolReferenced(owningBlock.eContainer, it, [true])){
				 	error("Declared variable '" + name + "' must be used within the object.",
				 			MdlLibPackage::eINSTANCE.symbolDefinition_Name, MdlValidator::UNUSED_VARIABLE, name)
				}
//			}
		}
		
	}
	
	@Check
	def validateCompartmentMacroInitialised(ListDefinition it){
		val owningBlock = EcoreUtil2.getContainerOfType(eContainer, BlockStatement)
		if(owningBlock.blkId.name == BlockDefinitionTable::MDL_CMT_BLK){
			if(firstAttributeList.getAttributeEnumValue(ListDefinitionTable::CMT_TYPE_ATT) == ListDefinitionTable::CMT_CMT_VALUE){
				val owningObj = EcoreUtil2.getContainerOfType(owningBlock.eContainer, MclObject)
				if(owningObj !== null && !isSymbolReferenced(owningObj, it, [sr|
					val localBlk = EcoreUtil2.getContainerOfType(sr.eContainer, BlockStatement)
					if(localBlk !== null && localBlk.blkId.name == BlockDefinitionTable::MDL_CMT_BLK){
						//is a compartment macro so now look for a to attribute
						val kvp =  EcoreUtil2.getContainerOfType(sr.eContainer, ValuePair)
						kvp.argumentName == ListDefinitionTable::CMT_TO_ATT
					}
					else
						// if not references in a compartment macro then this is initialising the macro
					 	true
				])){
				 	error("Compartment macro '" + name + "' is not initialised.",
				 			MdlLibPackage::eINSTANCE.symbolDefinition_Name, MdlValidator::SYMBOL_NOT_INITIALISED, name)
				}
			} 
		}
		
	}
	
//	static val ReservedWord = #{ 'ordered', 'withOrderedCategories' }
	static val ReservedWord = Collections::emptySet
	
	@Check
	def validateReservedNamesNotUsed(SymbolDefinition it){
		if(name.startsWith(MdlValidator::RESERVED_PREFIX)){
			error("Variable names starting with '" + MdlValidator::RESERVED_PREFIX + "' are reserved for internal use.",
				MdlLibPackage::eINSTANCE.symbolDefinition_Name, MdlValidator::RESERVED_PREFIX_USED
			)
		}
		else if(ReservedWord.contains(name)){
			error("The keyword '" + name + "' is reserved for future use in MDL.",
				MdlLibPackage::eINSTANCE.symbolDefinition_Name, MdlValidator::RESERVED_WORD_USED
			)
		}
	}
	
	@Check
	def validateReservedNamesNotUsed(CategoryValueDefinition it){
		if(name.startsWith(MdlValidator::RESERVED_PREFIX)){
			error("Variable names starting with '" + MdlValidator::RESERVED_PREFIX + "' are reserved for internal use.",
				MdlPackage::eINSTANCE.categoryValueDefinition_Name, MdlValidator::RESERVED_PREFIX_USED
			)
		}
		else if(ReservedWord.contains(name)){
			error("The keyword '" + name + "' is reserved for future use in MDL.",
				MdlPackage::eINSTANCE.categoryValueDefinition_Name, MdlValidator::RESERVED_WORD_USED
			)
		}
	}
	
	@Check
	def warnUnusedRandomVariable(RandomVariableDefinition it){
		// find references to this rv. If none them warn
		val owner = EcoreUtil2.getContainerOfType(eContainer, MclObject)
		if(owner.isModelObject){
			val visitor = new MdlSwitch<Boolean>(){
				override Boolean caseSymbolReference(SymbolReference ref){
					if(ref.ref.name == name){
						Boolean.TRUE
					}
					else Boolean.FALSE
				}
			}
			val parent = EcoreUtil2.getContainerOfType(eContainer, MclObject)
			val iter = parent?.eAllContents ?: Collections::emptyIterator
			var found = false
			while(iter.hasNext && !found){
				val node = iter.next
				if(visitor.doSwitch(node) == Boolean.TRUE)
					found = true	
			}
			if(!found){
				// RV not used so
				warning("Random Variable '" + name + "' is not used and may be omitted from a generated model.",
						MdlLibPackage::eINSTANCE.symbolDefinition_Name, MdlValidator::UNUSED_VARIABLE);
			}
		}
	}

	@Check
	def validatedObservationPresent(BlockStatement it){
		if(blkId.name == BlockDefinitionTable::OBS_BLK_NAME){
			if(!statements.exists[s|
				if(s instanceof ListDefinition || s instanceof AnonymousListStatement) return true
				return false
			]){
				error("Observation block must contain at least one observation.",
					MdlPackage::eINSTANCE.blockStatement_BlkId, MdlValidator::OBS_MISSING
				)
			}
		}
	}
	
}