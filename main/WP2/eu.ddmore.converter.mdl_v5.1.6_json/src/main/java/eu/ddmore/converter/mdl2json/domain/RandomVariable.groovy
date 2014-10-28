package eu.ddmore.converter.mdl2json.domain;

import org.apache.log4j.Logger
import org.ddmore.mdl.mdl.Argument
import org.ddmore.mdl.mdl.SymbolDeclaration

import eu.ddmore.converter.mdl2json.interfaces.MDLPrintable
import eu.ddmore.converter.mdl2json.utils.XtextWrapper

/**
 * Used to represent variables from the mdlobj::RANDOM_VARIABLE_DEFINITION block
 * as well as the mdlobj::OBSERVATION block.
 */
public class RandomVariable extends Expando implements MDLPrintable {

	private static Logger logger = Logger.getLogger(RandomVariable.class)
	
	final String ATTRIBUTES = "attrs" // A property of this name will be present for 'normal' parameters
	final String COMPLEX_ATTRIBUTES = "complexAttrs" // A property of this name will be present for distribution parameters
	
	public RandomVariable(final SymbolDeclaration sd) {
		
		setProperty("name", sd.getSymbolName().getName())
	
		if (sd.getRandomList()) {
			final Map complexAttrs = [:]
			sd.getRandomList().getArguments().getArguments().each {Argument a ->
				complexAttrs.put(a.getArgumentName().getName(), XtextWrapper.unwrap(a.getExpression()))
			}
			setProperty(COMPLEX_ATTRIBUTES, complexAttrs)
		} else if (sd.getList()) {
			final Map attrs = [:]
			sd.getList().getArguments().getArguments().each {Argument a ->
				attrs.put(a.getArgumentName().getName(), XtextWrapper.unwrap(a.getExpression()))
			}
			setProperty(ATTRIBUTES, attrs)
		}
	}
	
	public RandomVariable(final Map json) {
		getProperties().putAll(json)
	}
	
	public String getName() {
		return (String) getProperty("name")
	}
	
	public String toMDL() {
		List attributes = []
		if (getProperty(COMPLEX_ATTRIBUTES)) {
    		// Note: sorting is only done so that we get predictable MDL strings that we can compare in the tests
    		getProperty(COMPLEX_ATTRIBUTES).sort().each { k, v ->
    			attributes.add("${k}=${v}")
    		}
    		return """${getName()} ~ {${attributes.join(", ")}}"""
		} else {
    		// Note: sorting is only done so that we get predictable MDL strings that we can compare in the tests
    		getProperty(ATTRIBUTES).sort().each { k, v ->
    			attributes.add("${k}=${v}")
    		}
    		return """${getName()} : {${attributes.join(", ")}}"""
		}
	}
	
}
