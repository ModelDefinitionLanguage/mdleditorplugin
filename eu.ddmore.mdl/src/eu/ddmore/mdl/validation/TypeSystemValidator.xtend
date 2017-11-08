package eu.ddmore.mdl.validation

import eu.ddmore.mdl.mdl.AdditiveExpression
import eu.ddmore.mdl.mdl.AndExpression
import eu.ddmore.mdl.mdl.AssignPair
import eu.ddmore.mdl.mdl.AttributeList
import eu.ddmore.mdl.mdl.CatValRefMapping
import eu.ddmore.mdl.mdl.CatValRefMappingExpression
import eu.ddmore.mdl.mdl.CategoryValueDefinition
import eu.ddmore.mdl.mdl.CategoryValueReference
import eu.ddmore.mdl.mdl.ElseClause
import eu.ddmore.mdl.mdl.EnumPair
import eu.ddmore.mdl.mdl.EqualityExpression
import eu.ddmore.mdl.mdl.IfExprPart
import eu.ddmore.mdl.mdl.IndexRange
import eu.ddmore.mdl.mdl.MappingPair
import eu.ddmore.mdl.mdl.MatrixElement
import eu.ddmore.mdl.mdl.MatrixLiteral
import eu.ddmore.mdl.mdl.MdlPackage
import eu.ddmore.mdl.mdl.MultiplicativeExpression
import eu.ddmore.mdl.mdl.NamedFuncArguments
import eu.ddmore.mdl.mdl.OrExpression
import eu.ddmore.mdl.mdl.PWClause
import eu.ddmore.mdl.mdl.PiecewiseExpression
import eu.ddmore.mdl.mdl.PropertyStatement
import eu.ddmore.mdl.mdl.RandomVariableDefinition
import eu.ddmore.mdl.mdl.RelationalExpression
import eu.ddmore.mdl.mdl.SubListExpression
import eu.ddmore.mdl.mdl.TransformedDefinition
import eu.ddmore.mdl.mdl.UnaryExpression
import eu.ddmore.mdl.mdl.UnnamedArgument
import eu.ddmore.mdl.mdl.UnnamedFuncArguments
import eu.ddmore.mdl.mdl.ValuePair
import eu.ddmore.mdl.mdl.VectorElement
import eu.ddmore.mdl.mdl.VectorLiteral
import eu.ddmore.mdl.provider.BuiltinFunctionProvider
import eu.ddmore.mdl.provider.ListDefinitionProvider
import eu.ddmore.mdl.provider.MappingDefinitionProvider
import eu.ddmore.mdl.provider.SublistDefinitionProvider
import eu.ddmore.mdl.type.CategoryTypeInfo
import eu.ddmore.mdl.type.CategoryValueTypeInfo
import eu.ddmore.mdl.type.GeneralCategoryTypeInfo
import eu.ddmore.mdl.type.MappingTypeInfo
import eu.ddmore.mdl.type.MatrixTypeInfo
import eu.ddmore.mdl.type.RandomVariableTypeInfo
import eu.ddmore.mdl.type.TypeInfo
import eu.ddmore.mdl.type.TypeInfoClass
import eu.ddmore.mdl.type.TypeSystemProvider
import eu.ddmore.mdl.type.VectorTypeInfo
import eu.ddmore.mdl.utils.DomainObjectModelUtils
import eu.ddmore.mdl.utils.MdlLibUtils
import eu.ddmore.mdllib.TypeDefinitionProvider
import eu.ddmore.mdllib.mdllib.Expression
import eu.ddmore.mdllib.mdllib.FunctionDefnBody
import eu.ddmore.mdllib.mdllib.FunctionSpec
import eu.ddmore.mdllib.mdllib.MdlLibPackage
import eu.ddmore.mdllib.mdllib.TypeSpec
import org.eclipse.emf.ecore.EStructuralFeature
import org.eclipse.xtext.EcoreUtil2
import org.eclipse.xtext.validation.Check
import org.eclipse.xtext.validation.EValidatorRegistrar

class TypeSystemValidator extends AbstractMdlValidator {
	
	override register(EValidatorRegistrar registrar){}
	
	extension ListDefinitionProvider ldp = new ListDefinitionProvider
	extension TypeSystemProvider typeProvider = new TypeSystemProvider
	extension DomainObjectModelUtils domu = new DomainObjectModelUtils
	extension BuiltinFunctionProvider bfp = new BuiltinFunctionProvider
	extension SublistDefinitionProvider subListProvider = new SublistDefinitionProvider
	extension MdlLibUtils mlu = new MdlLibUtils
	extension MappingDefinitionProvider mdp = new MappingDefinitionProvider
	
	// Type handling	
	private def (TypeInfo, TypeInfo) => void typeError(EStructuralFeature feature){ 
		[expectedType, actualType |error("Expected " + expectedType.typeName + " type, but was " + actualType.typeName + ".", feature, MdlValidator::INCOMPATIBLE_TYPES, expectedType.typeName) ]
	}
	
			
	@Check
	def validateCompatibleTypes(AndExpression e){
		checkBoolOp(e.leftOperand, e.rightOperand, typeError(MdlPackage::eINSTANCE.andExpression_LeftOperand),
			typeError(MdlPackage::eINSTANCE.andExpression_RightOperand))
	}
	
	@Check
	def validateCompatibleTypes(OrExpression e){
		checkBoolOp(e.leftOperand, e.rightOperand, typeError(MdlPackage::eINSTANCE.orExpression_LeftOperand),
			typeError(MdlPackage::eINSTANCE.orExpression_RightOperand))
	}
	
	@Check
	def validateCompatibleTypes(EqualityExpression e){
		checkRelationalOp(e.leftOperand, e.rightOperand, typeError(MdlPackage::eINSTANCE.equalityExpression_LeftOperand),
			typeError(MdlPackage::eINSTANCE.equalityExpression_RightOperand))
	}
		
	@Check
	def validateCompatibleTypes(RelationalExpression e){
		checkRelationalOp(e.leftOperand, e.rightOperand, typeError(MdlPackage::eINSTANCE.relationalExpression_LeftOperand),
			typeError(MdlPackage::eINSTANCE.relationalExpression_RightOperand))
	}
		
	@Check
	def validateCompatibleTypes(AdditiveExpression e){
		checkMathsOp(e.leftOperand, e.rightOperand, typeError(MdlPackage::eINSTANCE.additiveExpression_LeftOperand),
			typeError(MdlPackage::eINSTANCE.additiveExpression_RightOperand))
	}
		
	@Check
	def validateCompatibleTypes(MultiplicativeExpression e){
		checkMathsOp(e.leftOperand, e.rightOperand, typeError(MdlPackage::eINSTANCE.multiplicativeExpression_LeftOperand),
			typeError(MdlPackage::eINSTANCE.multiplicativeExpression_RightOperand))
	}
		
	@Check
	def validateCompatibleTypes(UnaryExpression e){
		checkUnaryOp(e.feature, e.operand, typeError(MdlPackage::eINSTANCE.unaryExpression_Operand))
	}
		
	@Check
	def validateCompatibleTypes(IfExprPart e){
		checkExpectedBoolean(e.cond, typeError(MdlPackage::eINSTANCE.ifExprPart_Cond))
		checkExpectedReal(e.value, typeError(MdlPackage::eINSTANCE.ifExprPart_Value))
	}
		
	@Check
	def validateElseCompatibleTypes(ElseClause e){
		checkExpectedReal(e.value, typeError(MdlPackage::eINSTANCE.elseClause_Value))
	}
		
	@Check
	def validateCompatibleTypes(PWClause e){
		checkExpectedBoolean(e.cond, typeError(MdlPackage::eINSTANCE.PWClause_Cond))
		checkExpectedReal(e.value, typeError(MdlPackage::eINSTANCE.PWClause_Value))
	}
		
	@Check
	def validateOtherwiseCompatibleTypes(PiecewiseExpression e){
		if(e.otherwise !== null){
			checkExpectedReal(e.otherwise, typeError(MdlPackage::eINSTANCE.piecewiseExpression_Otherwise))
		}
	}
		
		
	private def void checkMapTypeProperty(MappingTypeInfo mti, String attName, Expression e, (TypeInfo, TypeInfo) => void errorLambda){
		val attDefn = mti.getAttribute(attName)
		checkArgumentMatchesAndExpression(attDefn.attType, e, errorLambda)
	}
		
	@Check
	def validateCompatibleTypes(MappingPair e){
		val mapType = e.mappingType
		if(mapType instanceof MappingTypeInfo){
			checkMapTypeProperty(mapType, MappingTypeInfo::AS_ATT_NAME, e.leftOperand, typeError(MdlPackage::eINSTANCE.mappingPair_LeftOperand))
			checkMapTypeProperty(mapType, MappingTypeInfo::IN_ATT_NAME, e.srcColumn, typeError(MdlPackage::eINSTANCE.mappingPair_SrcColumn))
			if(e.rightOperand instanceof CatValRefMappingExpression){
				checkMapTypeProperty(mapType, MappingTypeInfo::CAT_TGT_ATT_NAME, e.rightOperand, typeError(MdlPackage::eINSTANCE.mappingPair_RightOperand))
			}
			else{
				checkMapTypeProperty(mapType, MappingTypeInfo::TGT_ATT_NAME, e.rightOperand, typeError(MdlPackage::eINSTANCE.mappingPair_RightOperand))
			}
		}
	}
		
	@Check
	def validateCompatibleTypes(CatValRefMapping e){
		var parentAt = EcoreUtil2.getContainerOfType(e, EnumPair)
		if(parentAt !== null)
			checkWhenOperator(parentAt, e.catRef, e.mappedTo, typeError(MdlPackage::eINSTANCE.catValRefMapping_CatRef),
				typeError(MdlPackage::eINSTANCE.catValRefMapping_MappedTo)
			)
	}
		
	@Check
	def validateCompatibleTypes(CategoryValueDefinition e){
		var parentAt = EcoreUtil2.getContainerOfType(e, EnumPair)
		if(parentAt !== null)
			checkWhenOperator(parentAt, e, typeError(MdlPackage::eINSTANCE.categoryValueDefinition_Name),
				typeError(MdlPackage::eINSTANCE.categoryValueDefinition_MappedTo)
			)
	}
		
//	@Check
//	def validateCompatibleTypes(EquationDefinition e){
//		// only check if there is an RHS to check 
//		if(e.expression !== null)
//			if(e.isVector)
//				checkExpectedVector(e.expression, typeError(MdlPackage::eINSTANCE.equationTypeDefinition_Expression))
//			else if(e.isMatrix)
//				checkExpectedMatrix(e.expression, typeError(MdlPackage::eINSTANCE.equationTypeDefinition_Expression))
//			else
//				checkExpectedReal(e.expression, typeError(MdlPackage::eINSTANCE.equationTypeDefinition_Expression))
//	}


	def private checkRandomVariableAssignmentTypes(TypeInfo lhs, Expression rhs, (TypeInfo, TypeInfo) => void errorLambda){
		val lhsRvType = lhs
		val distType = rhs.typeFor.underlyingType
		if(lhsRvType.isVector){
			if(distType instanceof VectorTypeInfo){
				if(distType.elementType == TypeSystemProvider::PDF_TYPE){
					checkExpectedAndExpression(TypeSystemProvider::PDF_TYPE.makeVector, rhs,errorLambda)
				}
				else{
					checkExpectedAndExpression(TypeSystemProvider::PMF_TYPE.makeVector, rhs,errorLambda)
				}
			}
			else{
				// assume RHS must be a vector of PDFs
				checkExpectedAndExpression(TypeSystemProvider::PDF_TYPE.makeVector, rhs,errorLambda)
			}
		}
		else if(lhsRvType.isMatrix){
			if(distType instanceof MatrixTypeInfo){
				if(distType.elementType == TypeSystemProvider::PDF_TYPE){
					checkExpectedAndExpression(TypeSystemProvider::PDF_TYPE.makeMatrix, rhs, errorLambda)
				}
				else{
					checkExpectedAndExpression(TypeSystemProvider::PMF_TYPE.makeMatrix, rhs, errorLambda)
				}
			}
			else{
				// assume RHS must be a vector of PDFs
				checkExpectedAndExpression(TypeSystemProvider::PDF_TYPE.makeMatrix, rhs, errorLambda)
			}
		}
		else if(lhsRvType instanceof RandomVariableTypeInfo){
			if(lhsRvType instanceof CategoryTypeInfo){
				// if categorical on lhs must be pmf on rhs
				checkExpectedAndExpression(TypeSystemProvider::PMF_TYPE, rhs, errorLambda)
			}
			else{
				// must be a scalar so check on that basis.
				if(distType == TypeSystemProvider::PDF_TYPE){
					checkExpectedAndExpression(TypeSystemProvider::PDF_TYPE, rhs, errorLambda)
				}
				else if(distType == TypeSystemProvider::PMF_TYPE){
					checkExpectedAndExpression(TypeSystemProvider::PMF_TYPE, rhs, errorLambda)
				}
				else if(lhsRvType.rvType == TypeSystemProvider::REAL_TYPE){
					// in order to give a more meaningful error we choose the expected type based in the lhs rv
					checkExpectedAndExpression(TypeSystemProvider::PDF_TYPE, rhs, errorLambda)
				}
				else{
					checkExpectedAndExpression(TypeSystemProvider::PMF_TYPE, rhs, errorLambda)
				}
			}
		}
	}


//	def private checkRandomVariableAssignmentTypes(RandomVariableTypeInfo lhs, Expression rhs, (TypeInfo, TypeInfo) => void errorLambda){
//		val distType = rhs.typeFor
//		if(distType.isCompatible(TypeSystemProvider::PDF_TYPE)){
//			switch(lhs.underlyingType.typeClass){
//				case(TypeInfoClass.Matrix):
//					checkExpectedAndExpression(TypeSystemProvider::PDF_TYPE.makeMatrix, rhs, errorLambda)
//				case(TypeInfoClass.Vector):
//					checkExpectedAndExpression(TypeSystemProvider::PDF_TYPE.makeVector, rhs,errorLambda)
//				default:
//					checkExpectedAndExpression(TypeSystemProvider::PDF_TYPE, rhs, errorLambda)
//			}
//		}
//		else if(distType.isCompatible(TypeSystemProvider::PMF_TYPE)){
//			switch(lhs.rvType){
//				case(TypeInfoClass.Matrix):
//					checkExpectedAndExpression(TypeSystemProvider::PMF_TYPE.makeMatrix, rhs, errorLambda)
//				case(TypeInfoClass.Vector):
//					checkExpectedAndExpression(TypeSystemProvider::PMF_TYPE.makeVector, rhs, errorLambda)
//				default:
//					checkExpectedAndExpression(TypeSystemProvider::PMF_TYPE, rhs, errorLambda)
//			}
//		}
//		else{
//			// dist type is not the correct type so let's raise an error
//			errorLambda.apply(TypeSystemProvider::PDF_TYPE, distType)
//		}
//	}

	@Check
	def validateCompatibleTypes(RandomVariableDefinition e){
		if(e.distn !== null){
			val stmtType = e.typeFor
			if(stmtType.isRandomVariable || stmtType.isVector || stmtType.isMatrix)
				checkRandomVariableAssignmentTypes(stmtType, e.distn, typeError(MdlPackage::eINSTANCE.randomVariableDefinition_Distn))
			else
				typeError(MdlPackage::eINSTANCE.randomVariableDefinition_Distn).apply(TypeSystemProvider::RV_REAL_TYPE, stmtType)
		}
	}
		
	@Check
	def validateCompatibleTypes(TransformedDefinition e){
		checkExpectedRealTransform(e.transform, typeError(MdlPackage::eINSTANCE.transformedDefinition_Transform))
		checkExpectedReal(e.expression, typeError(MdlPackage::eINSTANCE.equationTypeDefinition_Expression))
	}
		
	@Check
	def validateCompatibleVectorElement(VectorElement e){
		val vect = e.eContainer
		if(vect instanceof VectorLiteral){
			val vectType = vect.typeFor
			val exprType = e.typeFor
			if(!vectType.isCompatibleElement(exprType)){
				error("Element type '" + exprType.typeName + "' is incompatible with vector type '" + vectType.typeName + "'.",
					MdlPackage.eINSTANCE.vectorElement_Element, MdlValidator::INCOMPATIBLE_TYPES, vectType.typeName)
			}			
		}
	}
	
	@Check
	def validateCompatibleMatrixElement(MatrixElement e){
		val vect = EcoreUtil2.getContainerOfType(e.eContainer, MatrixLiteral)
		if(vect !== null){
			val vectType = vect.typeFor
			val exprType = e.typeFor
			if(!vectType.isCompatibleElement(exprType)){
				error("Cell type '" + exprType.typeName + "' is incompatible with matrix type '" + vectType.typeName + "'.",
					MdlPackage.eINSTANCE.matrixElement_Cell, MdlValidator::INCOMPATIBLE_TYPES, vectType.typeName)
			}			
		}
	}
	
	@Check
	def validateIndexSpecType(IndexRange it){
		if(begin !== null)
			checkExpectedIntl(begin, [e, a|
								error("Index value must be an 'Int' type, but was '" + a.typeName + "'.",
								MdlPackage.eINSTANCE.indexRange_Begin, MdlValidator::INCOMPATIBLE_TYPES)
								])
		if(end !== null)
			checkExpectedIntl(begin, [e, a|
								error("Index value must be an 'Int' type, but was '" + a.typeName + "'.",
								MdlPackage.eINSTANCE.indexRange_End, MdlValidator::INCOMPATIBLE_TYPES)
								])
	}
	
	@Check
	def validUnnamedFuctionArgumentType(UnnamedArgument it){
		if(eContainer instanceof UnnamedFuncArguments){
			checkFunctionArgumentTyping([e, a|
				error("argument '" + (funcArgNum + 1) + "' expected value of type '" + e.typeName + "' but was '" + a.typeName + "'.",
						MdlPackage.eINSTANCE.unnamedArgument_Argument, MdlValidator::INCOMPATIBLE_TYPES, a.typeName)
			])
		}
	}
	
	@Check
	def validateListAttributeTypes(ValuePair vp){
		val parent = vp.eContainer
		val rvErrorLambda = [TypeInfo attType|
			error("Inappropriate use of '~' assignment. Expected a '" + attType.typeName + "' on the LHS of assignment.",
				MdlPackage.eINSTANCE.valuePair_Expression, MdlValidator::INCOMPATIBLE_TYPES, attType.typeName
			)
		]
		switch(parent){
			AttributeList:
				parent.checkAttributeTyping(vp, [e, a|
					error("attribute '" + vp.argumentName + "' expected value of type '" + e.typeName + "' but was '" + a.typeName + "'.",
							MdlPackage.eINSTANCE.valuePair_Expression, MdlValidator::INCOMPATIBLE_TYPES, a.typeName)
				], rvErrorLambda)
			NamedFuncArguments:
				vp.checkNamedFunctionArgumentTyping([e, a|
					error("argument '" + vp.argumentName + "' expected value of type '" + e.typeName + "' but was '" + a.typeName + "'.",
							MdlPackage.eINSTANCE.valuePair_Expression, MdlValidator::INCOMPATIBLE_TYPES, a.typeName)
				])
			SubListExpression:
				parent.checkSublistAttributeTyping(vp, [e, a|
					error("attribute '" + vp.argumentName + "' expected value of type '" + e.typeName + "' but was '" + a.typeName + "'.",
							MdlPackage.eINSTANCE.valuePair_Expression, MdlValidator::INCOMPATIBLE_TYPES, a.typeName)
				], rvErrorLambda)
			PropertyStatement:
				parent.checkPropertyAttributeTyping(vp, [e, a|
					error("property '" + vp.argumentName + "' expected value of type '" + e.typeName + "' but was '" + a.typeName + "'.",
							MdlPackage.eINSTANCE.valuePair_Expression, MdlValidator::INCOMPATIBLE_TYPES, a.typeName)
				], rvErrorLambda)
		}
	}

	@Check
	def validateFunctionBodyAndReturnCompatible(FunctionSpec it){
		val expectedRtnType = returnType.typeInfo
		checkExpectedAndExpression(expectedRtnType, body, 
			[e, a|
					error("Expected function return type of '" + e.typeName + "' but expression was of type '" + a.typeName + "'.",
							MdlLibPackage.eINSTANCE.functionSpec_Body, MdlValidator::INCOMPATIBLE_TYPES, a.typeName)
			]
		)
	}

	@Check
	def validateTypeSpecWellFormed(TypeSpec it){
		if(typeName.name == TypeDefinitionProvider::FUNCTION_TYPE && functionSpec === null){
			error("You must define a function specification when using the type name 'Function'.",
					MdlLibPackage.eINSTANCE.typeSpec_TypeName, 
					MdlValidator::MALFORMED_TYPE_SPEC, typeName.name)
		}
		if(typeName.name != TypeDefinitionProvider::FUNCTION_TYPE && functionSpec !== null){
			error("You must use the type name 'Function' to define a function specification.",
					MdlLibPackage.eINSTANCE.typeSpec_TypeName, 
					MdlValidator::MALFORMED_TYPE_SPEC, typeName.name)
		}
	}

	def checkExpectedBoolean(Expression exp, (TypeInfo, TypeInfo) => void errorLambda){
		checkExpectedAndExpression(TypeSystemProvider::BOOLEAN_TYPE, exp, errorLambda)
	}
	
	def checkExpectedReal(Expression exp, (TypeInfo, TypeInfo) => void errorLambda){
		checkExpectedAndExpression(TypeSystemProvider::REAL_TYPE, exp, errorLambda)
	}
	
	def checkExpectedVector(Expression exp, (TypeInfo, TypeInfo) => void errorLambda){
		checkExpectedAndExpression(TypeSystemProvider.REAL_VECTOR_TYPE, exp, errorLambda)
	}
	
	def checkExpectedMatrix(Expression exp, (TypeInfo, TypeInfo) => void errorLambda){
		checkExpectedAndExpression(TypeSystemProvider.REAL_MATRIX_TYPE, exp, errorLambda)
	}
	
	def checkExpectedIntl(Expression exp, (TypeInfo, TypeInfo) => void errorLambda){
		checkExpectedAndExpression(TypeSystemProvider::INT_TYPE, exp, errorLambda)
	}
	
//	def checkExpectedPdf(Expression exp, (TypeInfo, TypeInfo) => void errorLambda){
//		checkExpectedAndExpression(TypeSystemProvider::PDF_TYPE, exp, errorLambda)
//	}
//
//	def checkExpectedPdfVector(Expression exp, (TypeInfo, TypeInfo) => void errorLambda){
//		checkExpectedAndExpression(TypeSystemProvider::PDF_TYPE.makeVector, exp, errorLambda)
//	}

	def checkExpectedRealTransform(FunctionDefnBody transform, (TypeInfo, TypeInfo) => void errorLambda){
		val actualType = transform.funcSpec.funcDefn?.returnType ?: TypeSystemProvider::UNDEFINED_TYPE
		val expectedType = TypeSystemProvider::REAL_TYPE
		if(actualType != expectedType)
			errorLambda.apply(expectedType, actualType ?: TypeSystemProvider::UNDEFINED_TYPE)
	}
	
	def checkExpectedString(Expression exp, (TypeInfo, TypeInfo) => void errorLambda){
		checkExpectedAndExpression(TypeSystemProvider::STRING_TYPE, exp, errorLambda)
	}
	
	def checkExpectedCategoryValueMatches(CategoryTypeInfo catType, CategoryValueTypeInfo catValType, (TypeInfo, TypeInfo) => void errorLambda){
		if(catType != catValType.owningCategory)
		  // assume that linking has taken care of ensuring that the value is correct
			errorLambda.apply(catType, catValType)
	}
	
	def checkRelationalOp(Expression lhs, Expression rhs, (TypeInfo, TypeInfo) => void leftErrorLambda,
			(TypeInfo, TypeInfo) => void rightErrorLambda){
		val lhsType = lhs?.typeFor ?: TypeSystemProvider::UNDEFINED_TYPE
		val rhsType = rhs?.typeFor ?: TypeSystemProvider::UNDEFINED_TYPE
		if(lhsType.underlyingType instanceof CategoryTypeInfo && rhsType.underlyingType instanceof CategoryValueTypeInfo){
			checkExpectedCategoryValueMatches(lhsType.underlyingType as CategoryTypeInfo, rhsType.underlyingType as CategoryValueTypeInfo, leftErrorLambda)
		}
		else if(rhsType.underlyingType instanceof CategoryTypeInfo && lhsType.underlyingType instanceof CategoryValueTypeInfo){
			checkExpectedCategoryValueMatches(rhsType.underlyingType as CategoryTypeInfo, lhsType.underlyingType as CategoryValueTypeInfo, rightErrorLambda)
		}
		else if(lhsType.underlyingType instanceof CategoryTypeInfo || lhsType.underlyingType instanceof CategoryValueTypeInfo){
			checkExpectedAndExpression(lhsType.underlyingType, rhs, rightErrorLambda)
		}
		else{
			checkExpectedAndExpression(TypeSystemProvider::REAL_TYPE, lhs, leftErrorLambda)
			checkExpectedAndExpression(TypeSystemProvider::REAL_TYPE, rhs, rightErrorLambda)
		}
	}

	def checkMathsOp(Expression lhs, Expression rhs, (TypeInfo, TypeInfo) => void leftErrorLambda,
		(TypeInfo, TypeInfo) => void rightErrorLambda){
		checkExpectedAndExpression(TypeSystemProvider::REAL_TYPE, lhs, leftErrorLambda)
		checkExpectedAndExpression(TypeSystemProvider::REAL_TYPE, rhs, rightErrorLambda)
	}

	def checkBoolOp(Expression lhs, Expression rhs, (TypeInfo, TypeInfo) => void leftErrorLambda,
				(TypeInfo, TypeInfo) => void rightErrorLambda){
		checkExpectedAndExpression(TypeSystemProvider::BOOLEAN_TYPE, lhs, leftErrorLambda)
		checkExpectedAndExpression(TypeSystemProvider::BOOLEAN_TYPE, rhs, rightErrorLambda)
	}

	def checkUnaryOp(String feature, Expression operand, (TypeInfo, TypeInfo) => void errorLambda){
		if(feature == '!') checkExpectedAndExpression(TypeSystemProvider::BOOLEAN_TYPE, operand, errorLambda)
		else checkExpectedAndExpression(TypeSystemProvider::REAL_TYPE, operand, errorLambda)
	}
	
	
//	def private checkAsOperator(Expression lhs, Expression rhs,  (TypeInfo, TypeInfo) => void leftErrorLambda,
//				(TypeInfo, TypeInfo) => void rightErrorLambda){
//		checkExpectedAndExpression(TypeSystemProvider::INT_TYPE, lhs, leftErrorLambda)
//		if(rhs?.typeFor ?: TypeSystemProvider::UNDEFINED_TYPE == TypeSystemProvider::MAPPING_TYPE){
//			checkArgumentMatchesAndExpression(TypeSystemProvider::MAPPING_TYPE, rhs, rightErrorLambda)
//		}
//		else{
//			checkArgumentMatchesAndExpression(TypeSystemProvider::REAL_TYPE.makeReference, rhs, rightErrorLambda)
//		}
//	}
	
	def checkWhenOperator(EnumPair at, CategoryValueReference lhs, Expression rhs,  (TypeInfo, TypeInfo) => void leftErrorLambda,
				(TypeInfo, TypeInfo) => void rightErrorLambda){
		checkExpectedEnumType(lhs, leftErrorLambda)
		if(rhs !== null){
			validateCategoricalMappingType(at, rhs, rightErrorLambda)
//			val attList = at.eContainer as AttributeList
//			val listDefn = attList.matchingListDefn
//			var expectedType = listDefn?.getCategoryMappingType(at.attributeName) ?: UNDEFINED_TYPE
//			checkExpectedAndExpression(expectedType, rhs, rightErrorLambda)
		}
	}
	
	def checkWhenOperator(EnumPair at, CategoryValueDefinition catValDefn,  (TypeInfo, TypeInfo) => void leftErrorLambda,
				(TypeInfo, TypeInfo) => void rightErrorLambda){
		val actualType = catValDefn.typeFor
		if(actualType.typeClass != TypeInfoClass.CategoryValue){
			leftErrorLambda.apply(new GeneralCategoryTypeInfo, actualType)
		}
		if(catValDefn.mappedTo !== null){
			validateCategoricalMappingType(at, catValDefn.mappedTo, rightErrorLambda)
//			val attList = at.eContainer as AttributeList
//			val listDefn = attList.matchingListDefn
//			var expectedType = listDefn?.getCategoryMappingType(at.attributeName) ?: UNDEFINED_TYPE
//			checkExpectedAndExpression(expectedType, catValDefn.mappedTo, rightErrorLambda)
		}
	}

	private  def void validateCategoricalMappingType(EnumPair at, Expression mappingExpr, (TypeInfo, TypeInfo) => void typeErrorLambda){
		val attList = at.eContainer as AttributeList
		val listDefn = attList.listDefinition
		if(listDefn.isCatMappingPossible(at.argumentName)){
			val expectedType = listDefn.catMappingType ?: TypeSystemProvider::UNDEFINED_TYPE
			checkArgumentMatchesAndExpression(expectedType, mappingExpr, typeErrorLambda)
		}
	}

	def dispatch void checkExpectedAndExpression(CategoryTypeInfo expectedType, Expression exp, (TypeInfo, TypeInfo) => void errorLambda){
		val actualType = exp?.typeFor ?: TypeSystemProvider::UNDEFINED_TYPE
		if(!expectedType.isCompatible(actualType)){
			errorLambda.apply(expectedType, actualType)
		} 
	}
	
	def void checkExpectedEnumType(Expression exp, (TypeInfo, TypeInfo) => void errorLambda){
		val actualType = exp?.typeFor ?: TypeSystemProvider::UNDEFINED_TYPE
		if(actualType.typeClass != TypeInfoClass.Category){
			errorLambda.apply(new GeneralCategoryTypeInfo, actualType)
		}
	}
	
	def dispatch void checkExpectedAndExpression(TypeInfo expectedType, Expression exp, (TypeInfo, TypeInfo) => void errorLambda){
		val actualType = exp?.typeFor ?: TypeSystemProvider::UNDEFINED_TYPE
		var testActual = actualType
//		if(expectedType.underlyingType instanceof VectorTypeInfo && !(actualType.underlyingType instanceof VectorTypeInfo)){
//			testActual = actualType.makeVector
//		}
		if(!expectedType.isCompatible(testActual)){
			errorLambda.apply(expectedType, actualType)
		} 
	}
	
	def checkCatgoricalMappingMatches(TypeInfo expectedType, Expression exp, (TypeInfo, TypeInfo) => void errorLambda){
		val actualType = exp?.typeFor ?: TypeSystemProvider::UNDEFINED_TYPE
		if(expectedType instanceof MappingTypeInfo){
			if(!expectedType.catTgtType.isArgumentCompatible(actualType)){
				errorLambda.apply(expectedType, actualType)
			}
		} 
	}

	def checkArgumentMatchesAndExpression(TypeInfo expectedType, Expression exp, (TypeInfo, TypeInfo) => void errorLambda){
		val actualType = exp?.typeFor ?: TypeSystemProvider::UNDEFINED_TYPE
		var testActual = actualType
		if(expectedType.underlyingType instanceof VectorTypeInfo && !(actualType.underlyingType instanceof VectorTypeInfo)){
			testActual = actualType.makeVector
		}
		if(!expectedType.isArgumentCompatible(testActual)){
			errorLambda.apply(expectedType, actualType)
		} 
	}

	def checkAttributeTyping(AttributeList attList, ValuePair at, (TypeInfo, TypeInfo) => void errorLambda, (TypeInfo) => void rvErrorLambda){
		val listDefn = attList.listDefinition
		if(listDefn !== null && at !== null){
			val attType = listDefn.getAttributeType(at.argumentName)
			checkValuePairTyping(at, attType, errorLambda, rvErrorLambda)
		}
	}

//		val owningBlk = EcoreUtil2.getContainerOfType(eContainer, BlockStatement)
//		if(owningBlk.blkId.name == BlockDefinitionTable::TARGET_SETTINGS){
//			
//		}
//		else{
//			matchingPropertyDefn?.propRef.propType.typeInfo ?: TypeSystemProvider::UNDEFINED_TYPE
//		}

	def checkPropertyAttributeTyping(PropertyStatement stmt, ValuePair at, (TypeInfo, TypeInfo) => void errorLambda, (TypeInfo) => void rvErrorLambda){
//		val owningBlk = EcoreUtil2.getContainerOfType(stmt.eContainer, BlockStatement)
//		val attType =  if(owningBlk.blkId.name == BlockDefinitionTable::TARGET_SETTINGS)
//			at.expression.typeFor
//		else at.typeForProperty
		val attType = at.typeFor
		if(at instanceof ValuePair){
			checkValuePairTyping(at, attType, errorLambda, rvErrorLambda)				
		}
	}

	def checkValuePairTyping(ValuePair at, TypeInfo attType, (TypeInfo, TypeInfo) => void errorLambda, (TypeInfo) => void rvErrorLambda){
			if(at instanceof AssignPair){
				if(at.assignOp == '='){
					if(at.expression instanceof CatValRefMappingExpression)
						checkCatgoricalMappingMatches(attType, at.expression, errorLambda)
					else checkArgumentMatchesAndExpression(attType, at.expression, errorLambda)
				}
				else{
					if(attType instanceof RandomVariableTypeInfo){
						checkRandomVariableAssignmentTypes(attType, at.expression, errorLambda)
					}
					else{
						rvErrorLambda.apply(attType)
					}
				}
			}
			else{
				checkArgumentMatchesAndExpression(attType, at.expression, errorLambda)
			}
	}


	def checkSublistAttributeTyping(SubListExpression it, ValuePair at, (TypeInfo, TypeInfo) => void errorLambda, (TypeInfo) => void rvErrorLambda){
		val subListDefn = findSublistMatch

		if(subListDefn !== null){
			val attDefn = subListDefn.attributes.findFirst[name == at.argumentName]
			checkValuePairTyping(at, attDefn.attType, errorLambda, rvErrorLambda)
//			checkArgumentMatchesAndExpression(attDefn.attType, at.expression, errorLambda)
		}
	}

	def checkNamedFunctionArgumentTyping(ValuePair at, (TypeInfo, TypeInfo) => void errorLambda){
		checkArgumentMatchesAndExpression(at.namedArgumentType, at.expression, errorLambda)				
	}

	def checkFunctionArgumentTyping(UnnamedArgument at, (TypeInfo, TypeInfo) => void errorLambda){
		checkArgumentMatchesAndExpression(at.unamedArgumentType, at.argument, errorLambda)				
	}

	
}