package eu.ddmore.mdl.provider

import eu.ddmore.mdl.mdl.ListDefinition
import eu.ddmore.mdl.mdl.MclObject
import eu.ddmore.mdl.utils.BlockUtils
import eu.ddmore.mdl.utils.MdlUtils
import eu.ddmore.mdl.validation.MdlValidator
import eu.ddmore.mdl.utils.DomainObjectModelUtils
import org.eclipse.xtext.EcoreUtil2
import eu.ddmore.mdl.mdl.Mcl

class MogDefinitionProvider {
	extension ListDefinitionProvider ldp = new ListDefinitionProvider
	extension BlockUtils bu = new BlockUtils
	extension DomainObjectModelUtils dom = new DomainObjectModelUtils 
	extension MdlUtils mu = new MdlUtils
	
	def boolean isDesignObjDefined(MclObject it){
		isObjDefinedInMog(MdlValidator::DESIGNOBJ)
	}
	
	def boolean isObjDefinedInMog(MclObject mogObject, String objTypeName){
		val mogObjBlk = mogObject.getBlocksByName(BlockDefinitionTable::MOG_OBJ_NAME)
		mogObjBlk.head.statementsFromBlock.exists[stmt|
			if(stmt instanceof ListDefinition){
				val objType = stmt.firstAttributeList.getAttributeEnumValue('type')
				objType == objTypeName
			}
			else false
		]
	}
	
	def Mcl getMcl(MclObject it){
		EcoreUtil2.getContainerOfType(eContainer, Mcl)
	}
	
	def MclObject getObjDefinedInMog(MclObject mogObject, String objTypeName){
		val mogObjBlk = mogObject.getBlocksByName(BlockDefinitionTable::MOG_OBJ_NAME)
		val mogInObj = mogObjBlk.head.statementsFromBlock.findFirst[stmt|
			if(stmt instanceof ListDefinition){
				val objType = stmt.firstAttributeList.getAttributeEnumValue('type')
				objType == objTypeName
			}
			else false
		]
		if(mogInObj instanceof ListDefinition)
			mogObject.mcl.getMdlObjectOfTypeByName(objTypeName, mogInObj.name)
		else null
	}
	
	def boolean isParamObjDefined(MclObject it){
		isObjDefinedInMog(MdlValidator::PARAMOBJ)
	}
	
	
	def getMdlObjectOfType(Mcl it, String mdlType){
		objects.filter[mdlObjType == mdlType]
	}

	def getMdlObjectOfTypeByName(Mcl it, String mdlType, String objName){
		getMdlObjectOfType(mdlType).findFirst[name == objName]
	}

	def getMdlObj(Mcl it, String objName){
		getMdlObjectOfTypeByName(MdlValidator::MDLOBJ, objName)
	}

	def getParamObj(Mcl it, String objName){
		getMdlObjectOfTypeByName(MdlValidator::PARAMOBJ, objName)
	}

	def getDataObj(Mcl it, String objName){
		getMdlObjectOfTypeByName(MdlValidator::DATAOBJ, objName)
	}

	def getTaskObj(Mcl it, String objName){
		getMdlObjectOfTypeByName(MdlValidator::TASKOBJ, objName)
	}

	def getDesignObj(Mcl it, String objName){
		getMdlObjectOfTypeByName(MdlValidator::DESIGNOBJ, objName)
	}

	def getPriorObj(Mcl it, String objName){
		getMdlObjectOfTypeByName(MdlValidator::PRIOROBJ, objName)
	}

	def getMdlObj(MclObject it){
		getObjDefinedInMog(MdlValidator::MDLOBJ)
	}

	def getParamObj(MclObject it){
		getObjDefinedInMog(MdlValidator::PARAMOBJ)
	}

	def getDataObj(MclObject it){
		getObjDefinedInMog(MdlValidator::DATAOBJ)
	}

	def getTaskObj(MclObject it){
		getObjDefinedInMog(MdlValidator::TASKOBJ)
	}

	def getDesignObj(MclObject it){
		getObjDefinedInMog(MdlValidator::DESIGNOBJ)
	}

	def getPriorObj(MclObject it){
		getObjDefinedInMog(MdlValidator::PRIOROBJ)
	}
}