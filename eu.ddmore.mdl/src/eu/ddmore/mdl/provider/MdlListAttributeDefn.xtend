package eu.ddmore.mdl.provider

import eu.ddmore.mdl.type.TypeInfo
import eu.ddmore.mdl.utils.MdlLibUtils
import eu.ddmore.mdllib.mdllib.ListAttributeDefn
import org.eclipse.xtend.lib.annotations.ToString
import eu.ddmore.mdl.type.TypeSystemProvider

@ToString
class MdlListAttributeDefn{
	extension MdlLibUtils mlu = new MdlLibUtils
	
	val ListAttributeDefn _lad;
	val TypeInfo _attType


	new(ListAttributeDefn lad){
		_lad = lad
		_attType = lad.attType?.typeInfo ?: TypeSystemProvider::UNDEFINED_TYPE
	}
	
	
	def String getName(){
		_lad.name
	}
	
	def TypeInfo getAttType(){
		_attType
	}

	
	// equality is based on the list name
	override boolean equals(Object other){
		var retVal = false
		if(other !== null){
			if(this !== other){
				if(other instanceof MdlListAttributeDefn){
					retVal = this._lad == other._lad
				}
			}
			else retVal = true
		}
		retVal
	}

	override int hashCode() {
	    val int prime = 31
	    var result = prime + if(this._lad === null)  0 else this._lad.hashCode()
	    result
	}
	
}
