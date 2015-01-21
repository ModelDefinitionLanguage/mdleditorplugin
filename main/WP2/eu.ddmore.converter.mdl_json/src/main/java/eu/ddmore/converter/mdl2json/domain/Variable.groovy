package eu.ddmore.converter.mdl2json.domain;

import org.apache.log4j.Logger
import org.ddmore.mdl.mdl.Argument
import org.ddmore.mdl.mdl.SymbolDeclaration
import org.ddmore.mdl.mdl.SymbolName

import eu.ddmore.converter.mdl2json.interfaces.MDLPrintable
import eu.ddmore.converter.mdl2json.utils.XtextWrapper
//import org.ddmore.mdl.mdl.SymbolModification;

public class Variable extends Expando implements MDLPrintable {

	private static Logger logger = Logger.getLogger(Variable.class)
	
	public Variable(final SymbolDeclaration sd) {
		
		setProperty("name", sd.getSymbolName().getName())
	
		if (sd.getList()) {
			sd.getList().getArguments().getArguments().each {Argument a ->
				setProperty(a.getArgumentName().getName(), XtextWrapper.unwrap(a.getExpression()))
			}
		}
	}
	
	public Variable(final SymbolName sn) {
		setProperty("name", sn.getName())
	}
	
	public Variable(final Map json) {
		getProperties().putAll(json)
	}
	
	public String getName() {
		return (String) getProperty("name")
	}
	
	public String toMDL() {
		List attributes = []
		// Note: sorting is only done so that we get predictable MDL strings that we can compare in the tests
		getProperties().minus(['name':getName()]).sort().each { k, v ->
			attributes.add("${k}=${v}")
		}
		if (attributes.isEmpty()) {
			"""${getName()}"""
		} else {
			"""${getName()} : {${attributes.join(", ")}}"""
		}
	}
	
}
