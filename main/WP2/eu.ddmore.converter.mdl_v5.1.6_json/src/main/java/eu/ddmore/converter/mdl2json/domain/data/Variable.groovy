package eu.ddmore.converter.mdl2json.domain.data;

import eu.ddmore.converter.mdl2json.utils.XtextWrapper;
import groovy.util.Expando;

import java.util.List;
import java.util.Map;

import org.apache.log4j.Logger
import org.ddmore.mdl.mdl.Argument;
import org.ddmore.mdl.mdl.EnumType;
import org.ddmore.mdl.mdl.SymbolDeclaration;
import org.ddmore.mdl.types.UseType;
//import org.ddmore.mdl.mdl.SymbolModification;

public class Variable extends Expando{

	private static Logger logger = Logger.getLogger(Variable.class)
	
	private SymbolDeclaration symbolDeclaration;

	public Variable(SymbolDeclaration sd) {
		this.symbolDeclaration = sd;
		
		setProperty("name", sd.getSymbolName().getName() );
		
		if(sd.getExpression()!=null) {
			Map properties = XtextWrapper.unwrap(sd.getExpression())
			getProperties().putAll(properties)
		}
		
		if(sd.getFunctionName()!=null ) {
			setProperty("functionName", XtextWrapper.unwrap(sd.getFunctionName()));
		}		
		
		if(sd.getRandomList()!=null) {
			
		}
		
		if(sd.getArgumentName()!=null) {
			logger.debug(sd.getArgumentName())
		}
	}
	
	public Variable(Map json) {
		getProperties().putAll(json)
	}
	
	public String getName() {
		return (String) getProperty("name");
	}
	
	public String toMDL() {
		List attributes = []
		getProperties().each { k, v ->
			if(k!="name") attributes.add("${k}=${v}")
		}
		"""list(${attributes.join(",")})"""
	}
}
