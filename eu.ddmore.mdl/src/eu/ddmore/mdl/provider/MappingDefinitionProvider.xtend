package eu.ddmore.mdl.provider

import eu.ddmore.mdl.mdl.AttributeList
import eu.ddmore.mdl.mdl.MappingExpression
import eu.ddmore.mdl.type.MappingTypeInfo
import eu.ddmore.mdl.type.TypeInfo
import eu.ddmore.mdl.type.TypeSystemProvider
import org.eclipse.xtext.EcoreUtil2
import eu.ddmore.mdl.mdl.MappingPair
import eu.ddmore.mdl.type.ListSuperTypeInfo

class MappingDefinitionProvider {
	
	extension ListDefinitionProvider ldp = new ListDefinitionProvider
	
	def TypeInfo getMappingType(ListDefInfo owningList){
		val mappingAtt = owningList.attributes.findFirst[td|
			td.attType instanceof MappingTypeInfo
		]
		mappingAtt?.attType ?: TypeSystemProvider::UNDEFINED_TYPE
//		new MappingTypeInfo('DefTypeInfo', TypeSystemProvider::INT_TYPE, TypeSystemProvider::UNDEFINED_TYPE, TypeSystemProvider::REAL_TYPE)
//		val dataCol = new ListSuperTypeInfo("DataColumn")
//		new MappingTypeInfo('DefTypeInfo', TypeSystemProvider::INT_TYPE, dataCol, TypeSystemProvider::REAL_TYPE.makeReference, TypeSystemProvider::MAPPING_TYPE)
	}

	def TypeInfo getMappingType(MappingExpression it){
		val attList = EcoreUtil2.getContainerOfType(eContainer, AttributeList)
		val listDefn = attList?.listDefinition
		listDefn?.mappingType ?: TypeSystemProvider::UNDEFINED_TYPE
	}

	def TypeInfo getMappingType(MappingPair it){
		val attList = EcoreUtil2.getContainerOfType(eContainer, AttributeList)
		val listDefn = attList?.listDefinition
		listDefn?.mappingType ?: TypeSystemProvider::UNDEFINED_TYPE
	}

}