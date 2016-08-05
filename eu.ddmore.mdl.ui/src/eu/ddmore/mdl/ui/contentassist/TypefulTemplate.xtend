package eu.ddmore.mdl.ui.contentassist

import org.eclipse.jface.text.templates.Template
import eu.ddmore.mdl.type.TypeInfo
import eu.ddmore.mdl.type.TypeSystemProvider

class TypefulTemplate extends Template {
	
	val TypeInfo matchType
	
	new(){
		super()
		this.matchType = TypeSystemProvider::UNDEFINED_TYPE
	}
	
	new(TypefulTemplate other){
		super(other)
		this.matchType = other.matchType
	}
	
	
	new(String name, String description, String contextTypeId, String pattern, boolean isAutoInsertable, TypeInfo matchType) {
		super(name, description, contextTypeId, pattern, isAutoInsertable)
		this.matchType = matchType
	}
	
	
	def TypeInfo getMatchType(){
		this.matchType
	}
	
}