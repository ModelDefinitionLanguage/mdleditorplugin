package eu.ddmore.converter.mdl2pharmml08

import eu.ddmore.mdl.mdl.EquationDefinition
import eu.ddmore.mdl.mdl.MclObject
import eu.ddmore.mdl.utils.MdlUtils

class PriorParameterWriter extends AbstractParameterWriter {

	extension PharmMLExpressionBuilder peb = new PharmMLExpressionBuilder
	extension MdlUtils mu = new MdlUtils 

	val MclObject priorObject
	
	new(MclObject pO){
		this.priorObject = pO
	}
	
	
	override writeSimpleParameter(EquationDefinition stmt){
		val priorObjDefn = priorObject.findMdlSymbolDefn(stmt.name)
		
	'''
		«IF stmt.expression != null»
			<PopulationParameter symbId = "«stmt.name»">
				«stmt.expression.expressionAsAssignment»
			</Parameter>
		«ELSE»
			<PopulationParameter symbId = "«stmt.name»"/>
		«ENDIF»
	'''
	}
	override writeParameter(EquationDefinition stmt){
		writeSimpleParameter(stmt)
	}
	
}