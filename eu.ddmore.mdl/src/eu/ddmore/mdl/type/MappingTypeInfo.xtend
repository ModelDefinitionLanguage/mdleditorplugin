package eu.ddmore.mdl.type

import org.eclipse.xtend.lib.annotations.ToString
import eu.ddmore.mdl.provider.AttributeDefn

@ToString
class MappingTypeInfo extends SublistTypeInfo{
	public static var AS_ATT_NAME = 'as'
	public static var IN_ATT_NAME = 'in'
	public static var TGT_ATT_NAME = 'tgt'
	public static var CAT_TGT_ATT_NAME = 'catTgt'
	 
	
	new(String name, TypeInfo asType, TypeInfo inType, TypeInfo tgtVarType, TypeInfo catTgtMapping){
		super(TypeInfoClass.Mapping, name, #[new AttributeDefn(AS_ATT_NAME, asType), 
			new AttributeDefn(IN_ATT_NAME, inType),
			new AttributeDefn(TGT_ATT_NAME, tgtVarType),
			new AttributeDefn(CAT_TGT_ATT_NAME, TypeSystemProvider::MAPPING_TYPE)])
	}
	
	def TypeInfo getAsType(){
		getAttribute(AS_ATT_NAME).attType
	}

	def TypeInfo getInType(){
		getAttribute(IN_ATT_NAME).attType
	}

	def TypeInfo getTgtType(){
		getAttribute(TGT_ATT_NAME).attType
	}

	def TypeInfo getCatTgtType(){
		getAttribute(CAT_TGT_ATT_NAME).attType
	}

	override getUnderlyingType(){
		this
	}
	
//	override boolean isCompatible(TypeInfo otherType){
//		// use underlying type in case it is a reference 
////		val otherType = other.underlyingType
//		if(otherType != null){
//			switch(otherType){
//				// if both vectors then check type compatibility
//				MappingTypeInfo: mapTargetType.isCompatible(otherType.mapTargetType)
//				ReferenceTypeInfo:
//					this.isCompatible(otherType.underlyingType)
//				RandomVariableTypeInfo:
//					this.isCompatible(otherType.rvType)
//				default: false
//			}
//		}
//		else false
//	}
//	
//	override isCompatibleElement(TypeInfo otherElementType){
//		false
//	}
	
	override getTypeName(){
		'mapping:' + this.name
	}
	
//	override makeReference(){
//		new ReferenceTypeInfo(this);
//	}
//	
//	override makeVector(){
//		new VectorTypeInfo(this);
//	}
//	
//	override makeMatrix(){
//		new MatrixTypeInfo(this);
//	}


//	// equality is based on the list name
//	override boolean equals(Object other){
//		var retVal = false
//		if(other !== null){
//			if(this !== other){
//				if(other instanceof MappingTypeInfo){
//					retVal = this.mapTargetType == other.mapTargetType
//				}
//			}
//			else retVal = true
//		}
//		retVal
//	}
//
//	override int hashCode() {
//	    val int prime = 31
//	    var result = prime + if(this.mapTargetType == null)  0 else this.mapTargetType.hashCode()
//	    result
//	}
//	
//	override getTypeClass() {
//		this.typeClass
//	}
}

