package eu.ddmore.converter.mdl2json.domain;

import org.ddmore.mdl.mdl.ParameterObject
import org.ddmore.mdl.mdl.ParameterObjectBlock
import org.ddmore.mdl.mdl.PriorParametersBlock
import org.ddmore.mdl.mdl.StructuralBlock
import org.ddmore.mdl.mdl.SymbolDeclaration
import org.ddmore.mdl.mdl.TargetBlock
import org.ddmore.mdl.mdl.VariabilityBlock

import eu.ddmore.converter.mdl2json.utils.XtextWrapper;

public class Parameter extends Expando {

	public Parameter(ParameterObject paramObject) {
		this.setProperty("identifier", paramObject.getIdentifier())
		
		for( ParameterObjectBlock pob : paramObject.getBlocks()) {
			if(pob.getPriorBlock()) {
				setProperty("PRIOR", makePrior(pob.getPriorBlock()))
			} else if(pob.getStructuralBlock()) {
				setProperty("STRUCTURAL", makeStructuralModel(pob.getStructuralBlock()))
			} else if(pob.getTargetBlock()) {
				setProperty("TARGET", makeTarget(pob.getTargetBlock()))
			} else if(pob.getVariabilityBlock()) {
				setProperty("VARIABILITY", makeVariability(pob.getVariabilityBlock()))
			}
		}
	}
	
	private Map makeVariability(VariabilityBlock vb) {
		return [:]
	}
	
	private Map makeTarget(TargetBlock tb) {
		return [:]
	}
	
	private Map makePrior(PriorParametersBlock ppb) {
		
		return [:]
	}
	
	private Map makeStructuralModel(StructuralBlock sb) {
		def retVal = [:]
		for( SymbolDeclaration sd : sb.getParameters() ) {
			String symbol = sd.getSymbolName().getName()
			retVal[symbol] = XtextWrapper.unwrap(sd.getExpression())	
		}	
		retVal
	}
}
