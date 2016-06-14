package eu.ddmore.converter.mdl2pharmml08

import eu.ddmore.mdl.mdl.MclObject
import eu.ddmore.mdl.mdl.EquationTypeDefinition
import eu.ddmore.mdl.mdl.ListDefinition
import eu.ddmore.mdl.mdl.AnonymousListStatement
import eu.ddmore.mdl.mdl.Statement
import java.util.List

class ObservationModelWriter {
	extension ListObservationsWriter low = new ListObservationsWriter
//	extension FunctionObservationsWriter fow = new FunctionObservationsWriter



	def writeObservationModel(List<Statement> obs){
	var idx = 0
	'''«FOR stmt : obs»
		«switch(stmt){
//			EquationTypeDefinition:
//				'''
//				«writeContinuousObservation(stmt, idx += 1)»
//				'''
			ListDefinition:
				'''
				«writeListObservations(stmt, idx += 1)»
				'''
			AnonymousListStatement:
				'''
				«writeListObservations(stmt, idx += 1)»
				'''
			default:{
				idx += 1
				''''''
			}
		}»
	«ENDFOR»
	'''
}
	
	
	
}