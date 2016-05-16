package eu.ddmore.converter.mdl2pharmml08

import eu.ddmore.mdl.mdl.EquationDefinition

abstract class AbstractParameterWriter {
	
	abstract def String writeSimpleParameter(EquationDefinition stmt)
	
	abstract def String writeParameter(EquationDefinition stmt)
	
}