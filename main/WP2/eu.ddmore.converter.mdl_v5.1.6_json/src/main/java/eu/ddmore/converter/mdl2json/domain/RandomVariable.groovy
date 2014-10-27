package eu.ddmore.converter.mdl2json.domain;

import eu.ddmore.converter.mdl2json.interfaces.MDLPrintable;
import eu.ddmore.converter.mdl2json.utils.XtextWrapper;
import groovy.util.Expando;

import java.util.List;
import java.util.Map;

import org.apache.log4j.Logger
import org.ddmore.mdl.mdl.Argument;
import org.ddmore.mdl.mdl.EnumType;
import org.ddmore.mdl.mdl.SymbolDeclaration;
//import org.ddmore.mdl.mdl.SymbolModification;

/**
 * Used to represent variables from the mdlobj::RANDOM_VARIABLE_DEFINITION block
 * as well as the mdlobj::OBSERVATION block.
 */
public class RandomVariable extends Expando implements MDLPrintable {

	private static Logger logger = Logger.getLogger(RandomVariable.class)
	
	final String ATTRIBUTES = "attrs" // A property of this name will be present for 'normal' parameters
	final String COMPLEX_ATTRIBUTES = "complexAttrs" // A property of this name will be present for distribution parameters

	// Do not remove this property - it is not directly used in the class
	// but the Expando-bean functionality of this class needs it
//	private SymbolDeclaration symbolDeclaration;

//	public Variable(SymbolDeclaration sd) {
//		this.symbolDeclaration = sd;
//		
//		setProperty("name", sd.getSymbolName().getName());
//		
//		if(sd.getExpression()!=null) {
//			Map properties = XtextWrapper.unwrap(sd.getExpression())
//			getProperties().putAll(properties)
//		}
//		
//		if(sd.getFunctionName()!=null ) {
//			setProperty("functionName", XtextWrapper.unwrap(sd.getFunctionName()));
//		}		
//		
//		if(sd.getRandomList()!=null) {
//			
//		}
//		
//		if(sd.getArgumentName()!=null) {
//			logger.debug(sd.getArgumentName())
//		}
//	}
	
	public RandomVariable(final SymbolDeclaration sd) {
		//this.symbolDeclaration = sd;
		
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
    		return """${getName()} ~ (${attributes.join(", ")})"""
		} else {
    		// Note: sorting is only done so that we get predictable MDL strings that we can compare in the tests
    		getProperty(ATTRIBUTES).sort().each { k, v ->
    			attributes.add("${k}=${v}")
    		}
    		return """${getName()} : {${attributes.join(", ")}}"""
		}
	}
}
