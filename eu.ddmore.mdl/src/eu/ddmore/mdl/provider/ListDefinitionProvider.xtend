package eu.ddmore.mdl.provider

import eu.ddmore.mdl.mdl.AttributeList
import eu.ddmore.mdl.mdl.BlockStatement
import eu.ddmore.mdl.mdl.CategoryValueDefinition
import eu.ddmore.mdl.mdl.EnumExpression
import eu.ddmore.mdl.mdl.EnumPair
import eu.ddmore.mdl.mdl.ValuePair
import eu.ddmore.mdl.type.BuiltinEnumTypeInfo
import eu.ddmore.mdl.type.ListTypeInfo
import eu.ddmore.mdl.type.TypeInfo
import eu.ddmore.mdl.type.TypeSystemProvider
import eu.ddmore.mdl.utils.DomainObjectModelUtils
import eu.ddmore.mdl.utils.ExpressionUtils
import eu.ddmore.mdl.utils.MdlLibUtils
import eu.ddmore.mdllib.mdllib.BlockDefinition
import eu.ddmore.mdllib.mdllib.Library
import java.util.ArrayList
import java.util.Collections
import java.util.HashSet
import java.util.List
import java.util.Map
import java.util.Set
import org.eclipse.xtext.EcoreUtil2

class ListDefinitionProvider {
	extension DomainObjectModelUtils domu = new DomainObjectModelUtils
	extension MdlLibUtils mlu = new MdlLibUtils
	extension ExpressionUtils eu = new ExpressionUtils

	private def ValuePair getKeyValuePair(BlockStatement bs, AttributeList al){
		al.attributes.findFirst[argumentName == bs.blkId.keyAttName]
	}

	def getKeyAttribute(BlockDefinition it, AttributeList attList){
		val keyMapping = it.listAttMappings.findFirst[am|
			attList.attributes.exists[vp|
				vp.argumentName == am.attDefn.name
			] 
		]
		if(keyMapping != null) keyMapping.attDefn.name
		else null
	}


	def private getListDefnByAttribute(BlockDefinition it, AttributeList attList){
		val keyMapping = it.listAttMappings.findFirst[am|
			attList.attributes.exists[vp|
				vp.argumentName == am.attDefn.name
			] 
		]
		if(keyMapping != null) new ListDefInfo(keyMapping.attType)
		else null
	}
	


	def ListDefInfo getListDefinition(AttributeList it){
		val parent = parentStatement
		val blkDefn = new BlockListDefinition(parent.blkId)
		if(blkDefn.requiresNoKeyAttribute){
			blkDefn.getSglListDefn
		}
		else if(blkDefn.requiresAttributeMapping){
			parent.blkId.getListDefnByAttribute(it)
		}
		else{
			val keyAtt = parent.getKeyValuePair(it)
			if(keyAtt != null)	blkDefn.getListDefnByValue(keyAtt.expression.enumValue)
			else null
		}
	}	
	

	def TypeInfo getAttributeType(ListDefInfo it, String attName){
		attributes.findFirst[ad | 
			ad.name == attName
		]?.attType ?: TypeSystemProvider::UNDEFINED_TYPE 
	}
	
	def getAttributeDefinition(ListDefInfo it, String attName){
		attributes.findFirst[ad | ad.name == attName] 
	}
	
	
	def TypeInfo getAttributeType(ValuePair it){
		val attList = EcoreUtil2.getContainerOfType(eContainer, AttributeList)
		if(attList != null){
			val listDefn = attList.listDefinition
			listDefn?.getAttributeType(argumentName) ?: TypeSystemProvider::UNDEFINED_TYPE
		}
		else TypeSystemProvider::UNDEFINED_TYPE
	}
	
	def TypeInfo getTypeOfAttributeBuiltinEnum(EnumExpression ee){
		val attList = EcoreUtil2.getContainerOfType(ee.eContainer, AttributeList)
		if(attList != null){
			val listDefn = getListDefinition(attList)
			if(listDefn != null){
				val vp = EcoreUtil2.getContainerOfType(ee.eContainer, ValuePair)
				val enumType = listDefn.getAttributeType(vp.argumentName)
				if(enumType instanceof BuiltinEnumTypeInfo){
					val enumValue = ee.enumValue
					return if(enumType.containsValue(enumValue)) enumType else TypeSystemProvider::UNDEFINED_TYPE
				}
			} 
		}
		TypeSystemProvider::UNDEFINED_TYPE
	}

	
	
	def isKeyAttributeDefined(BlockDefinition it, AttributeList attList){
		if(!listAttMappings.isEmpty){
			listAttMappings.exists[am| 
				attList.attributes.exists[at| 
					at.argumentName == am.attDefn.name
				]
			]
		}
		else if(listType != null){
			true
		}
		else
			attList.attributes.exists[at| 
				keyAttName == at.argumentName
			]
	}
	
//	def getKeyAttribute(BlockStatement parent, AttributeList it){
//		parent.blkId.keyAttName
//	}


	def private Set<String> findUnusedMandatoryArguments(AttributeList sle, ListDefInfo listDefn){
		val unusedMandatoryAtts = new HashSet<String>()
		for(nameSet : listDefn.attributeSets){
			unusedMandatoryAtts.clear
			nameSet.forEach[name, mand| if(mand) unusedMandatoryAtts.add(name)]
			val iter = sle.attributes.iterator
			while(iter.hasNext && !unusedMandatoryAtts.isEmpty){
				val vp = iter.next
				unusedMandatoryAtts.remove(vp.argumentName)
			}
			// all mandatory atts used up so report this 
			if(unusedMandatoryAtts.isEmpty) return unusedMandatoryAtts
		}
		// normally get here is can't match any list in which case report the unused mandatory attributes
		unusedMandatoryAtts
	}

	def getUnusedMandatoryAttributes(AttributeList it) {
		// expect AttributeList->ListDefinition|AnaonolymousListStatement->BlockStatement
//		val parent = parentStatement
//		val unused = new HashSet<String>
//		val blockDefn = new BlockListDefinition(parent.blkId)
		val listDefn = listDefinition
		findUnusedMandatoryArguments(listDefn)
//		val iter = attributes.iterator
//		var expectedAttributes = new ArrayList<ListDefInfo>()
//		while(iter.hasNext && expectedAttributes.isEmpty){
//			val att = iter.next
//			val keyVal = att.expression.enumValue
//			val ld = blockDefn.getListDefnByValue(keyVal)
//			if(ld != null) expectedAttributes.add(ld)
//		}
//		if(!expectedAttributes.isEmpty){
//			val listDefinition = expectedAttributes.head
//			unused.addAll(findUnusedMandatoryArguments(listDefinition))
//		}
//		unused
	}


	def isAttributeDuplicated(AttributeList owningList, ValuePair it){
		if(owningList != null){
			return owningList.attributes.filter[a| a.argumentName == argumentName].size > 1 
		}
		false
	}
	
	
	private def attributeExists(List<ValuePair> attributes, String queryName){
		attributes.exists[attrib|
			switch(attrib){
				ValuePair case attrib.argumentName == queryName: true
//				CategoricalDefinitionExpr case CATEGORIES_KWD == queryName: true 
				default: false
			}
		]
	}
	
	def hasAttribute(AttributeList it, String name){
		attributeExists(attributes, name)
	}
	
//	def getAttributeName(ValuePair attrib){
//		switch(attrib){
//			ValuePair: attrib.argumentName
////			CategoricalDefinitionExpr: CATEGORIES_KWD 
//		}
//	}

	def List<String> getAttributeNames(AttributeList it){
		val attNames = new ArrayList<String>
		attributes.forEach([at|attNames.add(at.argumentName)])
		attNames
	}

	def attributeDefnExists(ListDefInfo it, ValuePair att){
		attributes.exists[attrib|
			attrib.name == att.argumentName
		]
	}
	
	private def attributeExistsInAnyListDefn(BlockListDefinition it, ValuePair att){
		it.listDefns.exists[ld| ld.attributeDefnExists(att)]
	}

//	// gets a list definition based on a key value combination 
//	def ListDefInfo getMatchingListDefn(AttributeList attList){
//		val owningBlock = EcoreUtil2.getContainerOfType(attList.eContainer, BlockStatement)
//		val blkDefn = new BlockListDefinition(owningBlock.blkId)
//		if(blkDefn.requiresNoKeyAttribute){
//			blkDefn.sglListDefn
//		}
//		else{
//			val keyVal = attList.getAttributeEnumValue(blkDefn.key)
//			if(keyVal != null){
//				blkDefn.getListDefnByValue(keyVal)
//			}
//		} 
//	}


	def getAllMatchingListDefns(BlockStatement owningBlock, String attName){
		val retVal = new ArrayList<MdlListAttributeDefn>
		val blkDefn = new BlockListDefinition(owningBlock.blkId)
		for(ld : blkDefn?.listDefns ?: Collections::emptyList){
			ld.attributes.forEach[ad|
				if(ad.name == attName) retVal.add(ad) 
			]
		}
		retVal
	}
	
	
	def TypeInfo getListDefinitionByTypeName(BlockDefinition bd, String lstTypeName){
		val lib = EcoreUtil2.getContainerOfType(bd.eContainer, Library)
		val retVal = lib.typeDefns.findFirst[
			name == lstTypeName
		]
		val ti = retVal?.typeInfo
		if(ti instanceof ListTypeInfo){
			ti
		}
		else null
	}

	// gets a list definition based on its key 
//	def getListDefnByKeyValue(BlockListDefinition it, String keyValQuery){
//		getListDefnByValue(keyValQuery)
//	}

	def getAttributeExpression(AttributeList it, String attName){
		attributes.findFirst[argumentName == attName]?.expression
	}

	def getAttributeEnumValue(AttributeList it, String attName){
		getAttributeExpression(attName)?.enumValue
	}
	
	def getAttributeExpessionAsString(AttributeList it, String attName){
		getAttributeExpression(attName)?.stringValue
	}

	def Map<String, Boolean> findMatchingAttributeSet(AttributeList it, ListDefInfo defn){
		// find the best matching att set
		var Map<String, Boolean> retVal = Collections::emptyMap 
		var maxMatchCnt = -1 // make it -1 so that if no matches are found later the max value is still set
		for(attSet : defn.attributeSets){
			val nameList = new HashSet<String>(attSet.keySet)
			var matchCnt = 0;
			for(attName : attributeNames){
				if(nameList.remove(attName)){
					matchCnt += 1
				}
			}
			if(matchCnt > maxMatchCnt){
				// store best match to be returned a better one not found
				maxMatchCnt = matchCnt
				retVal = attSet
			}
		}
		retVal
	}

	def isAttributeRecognised(ValuePair it){
		// expect Attribute->AttributeList->ListDefInfo|AnaonolymousListStatement->BlockStatement
		// get key from parent list
		val parent = EcoreUtil2.getContainerOfType(eContainer, BlockStatement)
		if(parent.blkId != null){
			val blkDefn = new BlockListDefinition(parent.blkId)
//			val listDefn =  if(blkDefn.requiresNoKeyAttribute){
//								blkDefn.sglListDefn
//							}
//							else if(blkDefn.requiresAttributeMapping){
//								
//							}
//							else{
//								val keyVal = parentList.getAttributeEnumValue(blkDefn.key)
//								blkDefn.getListDefnByValue(keyVal)
//							} 
			val listDefn = owningAttributeList.listDefinition
			if(listDefn != null){
				// there is a listDefn so check if attribute is in list
				parentList.findMatchingAttributeSet(listDefn).keySet.exists[n|argumentName == n]
			}
			else{
				// no list definition so need to check all attributes in list definition
				blkDefn.attributeExistsInAnyListDefn(it)
			} 
		}
		else{
			false
		}
	}


//	def isAttributeRecognised(ListDefInfo ld, ValuePair it){
//		// expect Attribute->AttributeList->ListDefInfo|AnaonolymousListStatement->BlockStatement
//		// get key from parent list
//		val parent = EcoreUtil2.getContainerOfType(eContainer, BlockStatement)
//		if(parent.blkId != null){
//			val blkDefn = new BlockListDefinition(parent.blkId)
//			val listDefn =  if(!blkDefn.requiresNoKeyAttribute){
//								val keyVal = parentList.getAttributeEnumValue(blkDefn.key)
//								blkDefn.getListDefnByValue(keyVal)
//							}
//							else blkDefn.sglListDefn
//			
//			if(listDefn != null){
//				// there is a listDefn so check if attribute is in list
//				parentList.findMatchingAttributeSet(listDefn).keySet.exists[n|argumentName == n]
//			}
//			else{
//				// no list definition so need to check all attributes in list definition
//				blkDefn.attributeExistsInAnyListDefn(it)
//			} 
//		}
//		else{
//			false
//		}
//	}


	def isMappingMandatory(CategoryValueDefinition it){
		val attList = EcoreUtil2.getContainerOfType(eContainer, AttributeList)
		val ep = EcoreUtil2.getContainerOfType(eContainer, EnumPair)
		val listDefn = attList?.listDefinition
		if(listDefn != null && ep != null){
			return listDefn.isCatMappingMandatory(ep.argumentName)
		}
		false
	}

	def isMappingForbidden(CategoryValueDefinition it){
		val attList = EcoreUtil2.getContainerOfType(eContainer, AttributeList)
		val ep = EcoreUtil2.getContainerOfType(eContainer, EnumPair)
		val listDefn = attList?.listDefinition
		if(listDefn != null && ep != null){
			return listDefn.isCatMappingForbidden(ep.argumentName)
		}
		false
	}

	def isAnonymousListExpected(AttributeList it){
		val listDefn = listDefinition
		if(listDefn != null){
			listDefn.isAnonymous
		}
		else{
			// is can't get the definition (presumably because the list is mal-formed) then assume no.
			false
		}
	}

	def isNamedListExpected(AttributeList it){
		val listDefn = listDefinition
		if(listDefn != null){
			!listDefn.isAnonymous
		}
		else{
			// is can't get the definition (presumably because the list is mal-formed) then no.
			false
		}
	}

}
