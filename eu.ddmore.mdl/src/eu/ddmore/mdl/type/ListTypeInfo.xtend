package eu.ddmore.mdl.type

class ListTypeInfo extends AbstractListTypeInfo{
	val ListSuperTypeInfo superType
	
	new(String name){
		this(name, new PrimitiveTypeInfo(TypeInfoClass.Undefined), null)
	}
	
	new(String name, TypeInfo secondaryType){
		this(name, secondaryType, null)
	}
	
	new(String name, ListSuperTypeInfo superType){
		this(name, new PrimitiveTypeInfo(TypeInfoClass.Undefined), superType)
	}
	
	new(String name, TypeInfo secondaryType, ListSuperTypeInfo superType){
		super(name, secondaryType)
		if(superType != null){
			if(superType instanceof ListSuperTypeInfo){
				this.superType = superType
			}
			else{
				this.superType = null
				throw new IllegalArgumentException("Expect list supertype")
			}
		}
		else this.superType = null
	}
	
	def getSuperType(){
		this.superType
	}
	
	override getListSuperType() {
		this.superType
	}
	
	override getTypeName(){
		"List:" + name
	}
	
	override matchesList(AbstractListTypeInfo other) {
		other != null
		 	&& (name == other.name
				|| (listSuperType != null
					&& listSuperType == other.listSuperType)) 
	}
}

