package eu.ddmore.mdl.provider

import eu.ddmore.mdl.mdl.BlockStatement
import eu.ddmore.mdllib.mdllib.BlockDefinition
import java.util.ArrayList
import java.util.Collections
import java.util.HashMap
import java.util.List
import java.util.Map

class BlockListDefinition {
//	extension ListDefinitionProvider ldp = new ListDefinitionProvider

	val String _key
	val Map<String, ListDefInfo> _listDefns
	val Map<String, ListDefInfo> _attMappingDefns
	val ListDefInfo _sglListDefn


	static def create(BlockStatement bs){
		if(bs !== null){
			new BlockListDefinition(bs.blkId)
		}
	}

	new(BlockDefinition bd){
		_key = bd.keyAttName
		if(bd.listType !== null){
			_sglListDefn = new ListDefInfo(bd.listType)
			_listDefns = Collections::emptyMap
			_attMappingDefns = Collections::emptyMap 
		}
		else if(!bd.listAttMappings.isEmpty){
			_sglListDefn = null
			_listDefns = Collections::emptyMap
			_attMappingDefns = new HashMap<String, ListDefInfo>
			bd.listAttMappings.forEach[
				_attMappingDefns.put(attDefn.name, new ListDefInfo(attType))
			]
		}
		else{
			_sglListDefn = null
			_listDefns = new HashMap<String, ListDefInfo>
			_attMappingDefns = Collections::emptyMap
			bd.listTypeMappings.forEach[
				_listDefns.put(attDefn.name, new ListDefInfo(attType))
			]
		}
	}
	
	def requiresAttributeMapping(){
		_sglListDefn === null && !_attMappingDefns.isEmpty && _listDefns.isEmpty
	}
	
	def requiresNoKeyAttribute(){
		_sglListDefn !== null && _attMappingDefns.isEmpty && _listDefns.isEmpty
	}
	
	def getSglListDefn(){
		_sglListDefn
	}

	def List<ListDefInfo> getListDefns(){
		if(_sglListDefn !== null)
			newArrayList(_sglListDefn)
		else
			new ArrayList<ListDefInfo>(this._listDefns.values)
	}
	
	def getKey(){
		_key
	}

	def ListDefInfo getListDefnByValue(String attVal) {
		_listDefns.get(attVal)
	}

	def getListDefnByAttribute(String keyAttribute){
		_attMappingDefns.get(keyAttribute)
	}


//	def getListDefnByAttribute(AttributeList it){
//		val vp = attributes.findFirst[
//			_attMappingDefns.containsKey(argumentName)
//		]
//		if(vp !== null) _attMappingDefns.get(vp.argumentName) else null
//	}
	
}
