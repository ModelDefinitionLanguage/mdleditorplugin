package eu.ddmore.mdl.utils

import eu.ddmore.mdllib.mdllib.MdlLibFactory
import eu.ddmore.mdllib.mdllib.Library
import eu.ddmore.mdllib.mdllib.AbstractTypeDefinition
import eu.ddmore.mdllib.mdllib.ListAttributeDefn
import java.util.List
import java.util.ArrayList
import eu.ddmore.mdllib.mdllib.ListSignature
import eu.ddmore.mdllib.mdllib.TypeSpec
import eu.ddmore.mdllib.mdllib.TypeClass
import eu.ddmore.mdllib.mdllib.TypeDefinition
import eu.ddmore.mdllib.mdllib.ListTypeDefinition
import java.util.Map

class MDLLibraryBuildFixture {
	
	
	def createLibraryRoot(){
		MdlLibFactory::eINSTANCE.createLibrary
	}
	
	def createListTypeDefinition(Library root, AbstractTypeDefinition altType, AbstractTypeDefinition superRef, List<ListAttributeDefn> attributes){
		val retVal = MdlLibFactory::eINSTANCE.createListTypeDefinition
		retVal.altType = altType
		retVal.superRef = superRef
		retVal.attributes.addAll(attributes)
		val sig = MdlLibFactory::eINSTANCE.createListSignature
		attributes.forEach[
			val attRef = MdlLibFactory::eINSTANCE.createListAttributeRef
			attRef.attRef = it
			attRef.optional = false
			sig.attRefs.add(attRef)
		]
		retVal.sigLists.add(sig)
		root.typeDefns.add(retVal)
		retVal
	}

	def createAttributeDefn(String name, TypeSpec attType){
		val retVal = MdlLibFactory::eINSTANCE.createListAttributeDefn
		retVal.attType = attType
		retVal.name = name
		retVal
	}

	def TypeSpec createTypeSpec(AbstractTypeDefinition typeName, TypeSpec elementSpec){
		val retVal = MdlLibFactory::eINSTANCE.createTypeSpec
		
		retVal.typeName = typeName
		retVal.elementType = elementSpec
		retVal	
	}

	def TypeDefinition createTypeDefinition(Library it, String name, TypeClass typeClass){
		val retVal = MdlLibFactory::eINSTANCE.createTypeDefinition
		retVal.name = name
		retVal.typeClass = typeClass
		typeDefns.add(retVal)
		retVal	
	}
	
	def TypeDefinition createEnumTypeDefinition(Library it, String name, TypeClass typeClass, List<String> enumValues){
		val retVal = createTypeDefinition(name, typeClass)
		enumValues.forEach[
			val ev = MdlLibFactory::eINSTANCE.createEnumValue
			ev.name = it
			retVal.enumArgs.add(ev)
		]
		retVal.enumArgs
		retVal	
	}

	def createBlockDefn(Library it, String name, ListTypeDefinition sglListDefn){
		val retVal = MdlLibFactory::eINSTANCE.createBlockDefinition
		retVal.name = name
		retVal.listType = sglListDefn
		
		blockDefns.add(retVal)
		retVal
	}
	
	def createBlockDefnWithKeyValues(Library it, String name, String keyAtt, Map<String, ListTypeDefinition> listMapping){
		val retVal = MdlLibFactory::eINSTANCE.createBlockDefinition
		retVal.name = name
		retVal.keyAttName = keyAtt
		listMapping.forEach[p1, p2|
			val attValMap = MdlLibFactory::eINSTANCE.createAttValListMap
			attValMap.attDefn = MdlLibFactory::eINSTANCE.createEnumValue
			attValMap.attDefn.name = p1
			attValMap.attType = p2
			retVal.listTypeMappings.add(attValMap)
		]
		
		blockDefns.add(retVal)
		retVal
	}
	
}