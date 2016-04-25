package eu.ddmore.mdl.provider

import eu.ddmore.mdl.type.TypeInfo
import org.eclipse.xtend.lib.annotations.Data
import org.eclipse.xtend.lib.annotations.FinalFieldsConstructor

@Data @FinalFieldsConstructor
class AttributeDefn{
	String name
	boolean mandatory
	TypeInfo attType
	
	new (String name, TypeInfo type){
		this.name = name
		this.mandatory = true
		this.attType = type
	}
}
