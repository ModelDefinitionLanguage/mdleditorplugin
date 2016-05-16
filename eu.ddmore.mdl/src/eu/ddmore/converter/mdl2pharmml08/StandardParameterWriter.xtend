package eu.ddmore.converter.mdl2pharmml08

import eu.ddmore.mdl.mdl.EquationDefinition

class StandardParameterWriter extends AbstractParameterWriter {

	extension PharmMLExpressionBuilder peb = new PharmMLExpressionBuilder 

	
	override writeSimpleParameter(EquationDefinition stmt)'''
		«IF stmt.expression != null»
			<Parameter symbId = "«stmt.name»">
				«stmt.expression.expressionAsAssignment»
			</Parameter>
		«ELSE»
			<Parameter symbId = "«stmt.name»"/>
		«ENDIF»
	'''
	
	override writeParameter(EquationDefinition stmt){
		writeSimpleParameter(stmt)
	}
	
}