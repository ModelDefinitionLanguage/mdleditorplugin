package eu.ddmore.mdl.utils

import eu.ddmore.mdllib.mdllib.Library
import eu.ddmore.mdllib.mdllib.ListTypeDefinition
import eu.ddmore.mdllib.mdllib.FunctionDefnBody
import eu.ddmore.mdllib.mdllib.ObjectDefinition
import eu.ddmore.mdllib.mdllib.BlockDefinition

class LibraryUtils {
	
	def BlockDefinition getBlockDefinition(Library lib, String blkName){
		lib.blockDefns.findFirst[name == blkName]
	}
	
	def ObjectDefinition getObjectDefinition(Library lib, String objName){
		lib.objectDefns.findFirst[name == objName]
	}
	
	def FunctionDefnBody getFunctionDefinition(Library lib, String funcName){
		lib.funcDefns.findFirst[name == funcName]
	}

	def ListTypeDefinition getListDefinition(Library lib, String listTypeName){
		val retVal = lib.typeDefns.findFirst[name == listTypeName]
		if(retVal instanceof ListTypeDefinition){
			retVal as ListTypeDefinition
		}
		else null
	}

}