package eu.ddmore.mdl.validation

import eu.ddmore.mdl.mdl.MclObject
import java.util.HashSet
import eu.ddmore.mdl.mdl.BlockStatement

class BlockValidationHelper {
	val static BlkData = #{
		MdlValidator::MDLOBJ -> #{
			"COVARIATES" -> false, "VARIABILITY_LEVELS" -> true, "STRUCTURAL_PARAMETERS" -> false,
			"VARIABILITY_PARAMETERS" -> false, "RANDOM_VARIABLE_DEFINITION" -> false,
			"INDIVIDUAL_VARIABLES" -> false, "MODEL_PREDICTION" -> false,
			"OBSERVATION" -> false, "GROUP_VARIABLES" -> false, "IDV" -> false
		},
		MdlValidator::DATAOBJ -> #{
			"DATA_INPUT_VARIABLES" -> true, "DECLARED_VARIABLES" -> false, "DATA_DERIVED_VARIABLES" -> false,
			"SOURCE" -> true
		},
		MdlValidator::PARAMOBJ -> #{
			"VARIABILITY" -> false, "DECLARED_VARIABLES" -> false, "STRUCTURAL" -> false
		},
		MdlValidator::TASKOBJ -> #{
			"ESTIMATE" -> false, "SIMULATE" -> false, "OPTIMISE" -> false
		},
		MdlValidator::MOGOBJ -> #{
			"OBJECTS" -> true
		}
	}

	val static SubBlkData = #{
		"DEQ" -> "MODEL_PREDICTION", "COMPARTMENT" -> "MODEL_PREDICTION"
	}
	
	new(){
			
	}
	
	def isModelBlock(BlockStatement it){
		if(eContainer instanceof MclObject){
			val objType = (eContainer as MclObject).mdlObjType
			return BlkData.containsKey(objType) && BlkData.get(objType).containsKey(identifier)
		}
		false
	}
	
	def isModelSubBlock(BlockStatement it){
		SubBlkData.containsKey(identifier)
	}
	
	def subBlockHasCorrectParent(BlockStatement it, BlockStatement parent){
		SubBlkData.containsKey(identifier) && SubBlkData.get(identifier) == parent.identifier
	}
	
	def getUnusedMandatoryBlocks(MclObject it){
		// map of just the mandatory blocks
		val mandatoryBlock = new HashSet<String>
		if(BlkData.containsKey(mdlObjType)){
			mandatoryBlock.addAll(BlkData.get(mdlObjType).filter[blk, mand|mand].keySet)
			for(blk : blocks){
				// remove mandatory block if it exists
				mandatoryBlock.remove(blk.identifier)
			}
		}
		mandatoryBlock
	}
	
	

}