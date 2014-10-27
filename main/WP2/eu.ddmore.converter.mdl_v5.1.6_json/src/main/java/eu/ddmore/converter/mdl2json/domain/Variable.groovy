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
import org.ddmore.mdl.mdl.SymbolName
//import org.ddmore.mdl.mdl.SymbolModification;

public class Variable extends Expando implements MDLPrintable {

	private static Logger logger = Logger.getLogger(Variable.class)

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
	
	public Variable(final SymbolDeclaration sd) {
		//this.symbolDeclaration = sd;
		
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
