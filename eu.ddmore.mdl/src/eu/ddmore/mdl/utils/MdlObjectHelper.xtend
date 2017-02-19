package eu.ddmore.mdl.utils

import eu.ddmore.mdl.mdl.MclObject

class MdlObjectHelper {
	val static MODEL_PREDICTION_BLK_NAME = "MODEL_PREDICTION"
	val static INDIV_PARAM_BLK_NAME = "INDIVIDUAL_VARIABLES"
	val static OBSERVATIONS_BLK_NAME = "OBSERVATION"
	
	extension DomainObjectModelUtils domu = new DomainObjectModelUtils()

	
	def getModelPrediction(MclObject it){
		getBlocksByName(MODEL_PREDICTION_BLK_NAME)
	}
	
	def getIndivParameters(MclObject it){
		getBlocksByName(INDIV_PARAM_BLK_NAME)
	}
	
	def getObservations(MclObject it){
		getBlocksByName(OBSERVATIONS_BLK_NAME)
	}
	
}