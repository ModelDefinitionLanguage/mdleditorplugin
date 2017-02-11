package eu.ddmore.mdl.utils

import eu.ddmore.mdl.mdl.MclObject

class MdlObjectHelper {
	val static MODEL_PREDICTION_BLK_NAME = "MODEL_PREDICTION"
	
	extension DomainObjectModelUtils domu = new DomainObjectModelUtils()

	
	def getModelPrediction(MclObject it){
		getBlocksByName(MODEL_PREDICTION_BLK_NAME)
	}
	
}