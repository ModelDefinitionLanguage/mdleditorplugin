package eu.ddmore.mdl.validation

import eu.ddmore.mdl.mdl.AttributeList
import eu.ddmore.mdl.mdl.EnumExpression
import eu.ddmore.mdl.mdl.EnumPair
import eu.ddmore.mdl.mdl.MappingPair
import eu.ddmore.mdl.mdl.MdlPackage
import eu.ddmore.mdl.mdl.PropertyStatement
import eu.ddmore.mdl.mdl.ValuePair
import eu.ddmore.mdl.provider.ListDefinitionProvider
import eu.ddmore.mdl.provider.ListDefinitionTable
import eu.ddmore.mdl.provider.PropertyDefinitionProvider
import eu.ddmore.mdl.type.TypeSystemProvider
import org.eclipse.xtext.EcoreUtil2
import org.eclipse.xtext.validation.Check
import org.eclipse.xtext.validation.EValidatorRegistrar
import eu.ddmore.mdl.mdl.AnonymousListStatement
import eu.ddmore.mdl.mdl.ListDefinition
import eu.ddmore.mdl.utils.MdlUtils
import eu.ddmore.mdl.mdl.BlockStatement
import eu.ddmore.mdl.provider.BlockListDefinition
import eu.ddmore.mdl.utils.DomainObjectModelUtils

// validates attributes in lists, functions and properties
class ListValidator extends AbstractMdlValidator {
	
	override register(EValidatorRegistrar registrar){}
	
	extension ListDefinitionProvider ldp = new ListDefinitionProvider
	extension PropertyDefinitionProvider pdp = new PropertyDefinitionProvider
	extension TypeSystemProvider mtp = new TypeSystemProvider
	extension MdlUtils mu = new MdlUtils
	extension DomainObjectModelUtils domu = new DomainObjectModelUtils
	
	static val MappingToColumn = #{
		ListDefinitionTable::AMT_USE_VALUE -> ListDefinitionTable::CMT_COL_TYPE,
		ListDefinitionTable::OBS_USE_VALUE -> ListDefinitionTable::DVID_COL_TYPE
	} 
	
	@Check
	def validateInReferenceCorrect(MappingPair it){
		val attList = EcoreUtil2.getContainerOfType(eContainer, AttributeList)
		if(attList != null){
			// check use type
			val useVal = attList.getAttributeEnumValue(ListDefinitionTable::USE_ATT)
			if(useVal != null){
//				val expectedSrcType = MappingToColumn.get(useVal) 
				val expectedSrcTypeName = MappingToColumn.get(useVal) 
				val blk = EcoreUtil2.getContainerOfType(eContainer, BlockStatement)
				val expectedSrcType = blk.blkId.getListDefinitionByTypeName(expectedSrcTypeName)
				val srcColType = srcColumn?.ref?.typeFor
//				if(expectedSrcType != null && srcColType != null && !expectedSrcType.isCompatible(srcColType)){
//					error("Expected source column of type '" + expectedSrcType.typeName + "', but was '" + srcColType.typeName + "'.",
//						MdlPackage.eINSTANCE.mappingPair_SrcColumn, MdlValidator::INCOMPATIBLE_TYPES, srcColType.typeName)
//				}
				if(expectedSrcType != null && srcColType != null && !expectedSrcType.isArgumentCompatible(srcColType)){
					error("Expected source column of type '" + expectedSrcType.typeName + "', but was '" + srcColType.typeName + "'.",
						MdlPackage.eINSTANCE.mappingPair_SrcColumn, MdlValidator::INCOMPATIBLE_TYPES, srcColType.typeName)
				}
			}
		}
	}
	
	
	@Check
	def validateAttribute(ValuePair it){
		val parent = eContainer
		switch(parent){
			AttributeList:{
				val attList = owningAttributeList
				if(!attributeRecognised){
					error("attribute '" + argumentName + "' is not recognised in this context.",
							MdlPackage.eINSTANCE.valuePair_ArgumentName, MdlValidator.UNRECOGNIZED_LIST_ATT, argumentName)
				}
			}
			PropertyStatement:{
				val blk = EcoreUtil2.getContainerOfType(parent.eContainer, BlockStatement)
				if(!blk.blkId.isFreeProps && !isPropertyKnown){
					error("property '" + argumentName + "' is not recognised in this context.",
							MdlPackage.eINSTANCE.valuePair_ArgumentName, MdlValidator.UNRECOGNIZED_PROPERTY_ATT, argumentName)
				}
			}
			
		}
	}

	@Check
	def validateDuplicateAttributes(ValuePair it){
		val attList = EcoreUtil2.getContainerOfType(eContainer, AttributeList)
		if(attList.isAttributeDuplicated(it)){
			error("List attribute '" + argumentName + "' is used more than once.",
				MdlPackage.eINSTANCE.valuePair_ArgumentName, MdlValidator::DUPLICATE_ATTRIBUTE_NAME, argumentName
			)
		}
	}
	
	@Check
	def validateAttributeList(AttributeList it){
		val owningBlock = EcoreUtil2.getContainerOfType(eContainer, BlockStatement)
		if(owningBlock != null && owningBlock.blkId != null){
			if(owningBlock.blkId.isKeyAttributeDefined(it)){
				val blkDefn = BlockListDefinition::create(owningBlock)
				var otherError = false
				if(!blkDefn.requiresNoKeyAttribute){
					if(blkDefn.requiresAttributeMapping){
						if(owningBlock.blkId.getKeyAttribute(it) == null){
							error("Attribute list does not have a recognised key value.",
								MdlPackage.eINSTANCE.attributeList_Attributes, MdlValidator::LIST_KEY_VAL_UNRECOGNISED, "")
							otherError = true
						}
					}
					else{
						val keyVal = getAttributeEnumValue(owningBlock.blkId.keyAttName)
						if(blkDefn.getListDefnByValue(keyVal) == null){
							error("Attribute list key value '" + keyVal + "' is not recognised.",
								MdlPackage.eINSTANCE.attributeList_Attributes, MdlValidator::LIST_KEY_VAL_UNRECOGNISED, "")
							otherError = true
						}
					}
				}
				if(!otherError)
					unusedMandatoryAttributes.forEach[name| error("mandatory attribute '" + name + "' is missing in list.",
						MdlPackage.eINSTANCE.attributeList_Attributes, MdlValidator::MANDATORY_LIST_ATT_MISSING, name) ]
			}		
			else if(owningBlock.blkId != null && owningBlock.blkId.listType == null){
				error("mandatory key attribute is missing in list.",
					MdlPackage.eINSTANCE.attributeList_Attributes, MdlValidator::MANDATORY_LIST_KEY_ATT_MISSING, "")
			}
		}
		
	}

	@Check
	def validateListAnonymisation(AnonymousListStatement it){
		if(list.isNamedListExpected){
			error("a list with this key cannot be anonymous in this context",
				MdlPackage.eINSTANCE.anonymousListStatement_List, MdlValidator::LIST_NOT_NAMED, "")
		}
	}

	@Check
	def validateListAnonymisation(ListDefinition it){
		for(list : getAttributeLists){
			if(list.isAnonymousListExpected){
				error("a list with this key cannot have a name in this context",
					MdlPackage.eINSTANCE.listDefinition_List, MdlValidator::LIST_NOT_ANONYMOUS, "")
			}
		}
	}

	@Check
	def validateCategoryDefinitionWellFormed(EnumPair parentAt){
		if(parentAt != null)
			checkCategoryDefinitionWellFormed(parentAt,
				[error("Unexpected category definition.", 
					MdlPackage::eINSTANCE.valuePair_Expression, MdlValidator::INVALID_CATEGORY_DEFINITION, "") ],
				[error("Category definition is missing.", 
					MdlPackage::eINSTANCE.valuePair_Expression, MdlValidator::INVALID_CATEGORY_DEFINITION, "") ]
			)
	}
		
	def checkCategoryDefinitionWellFormed(EnumPair ep, () => void unexpectedCatDefnErrorLambda, () => void missingCatErrorLambda){
		val attList = EcoreUtil2.getContainerOfType(ep.eContainer, AttributeList)
		if(attList != null){
			val listDefn = attList.listDefinition
			if(listDefn != null){
//				val attDefn = listDefn?.getAttributeDefinition(ep.argumentName)
				val mappingExpr = ep.expression
				if(mappingExpr instanceof EnumExpression){//} && attDefn != null){
//					val mappingExpr = ep.expression as EnumExpression
					if(listDefn.isCatMappingPossible(ep.argumentName) && mappingExpr.catDefn == null){
						missingCatErrorLambda.apply
					}
					else if(!listDefn.isCatMappingPossible(ep.argumentName) && mappingExpr.catDefn != null){
						unexpectedCatDefnErrorLambda.apply
					}
				}
			}
		}
	}
	

}