package eu.ddmore.converter.mdl2json.domain.task;

import eu.ddmore.converter.mdl2json.domain.MDLPrintable
import eu.ddmore.converter.mdl2json.utils.XtextWrapper;
import groovy.util.Expando;

import java.util.List;
import java.util.Map;

import org.apache.log4j.Logger
import org.ddmore.mdl.mdl.AnyExpression
import org.ddmore.mdl.mdl.Argument;
import org.ddmore.mdl.mdl.EnumType;
import org.ddmore.mdl.mdl.SymbolDeclaration;
import org.ddmore.mdl.types.UseType;

/**
 * This is based on {@link Variable} - perhaps these should be consolidated.
 */
public class TaskSymbolDeclaration extends Expando implements MDLPrintable {

	private static Logger logger = Logger.getLogger(TaskSymbolDeclaration.class)
	
	private SymbolDeclaration symbolDeclaration;
	private boolean isList = false;

	public TaskSymbolDeclaration(SymbolDeclaration sd) {
		this.symbolDeclaration = sd;
		
		//setProperty("name", sd.getSymbolName().getName());
		
		if (sd.getExpression() != null) {
			
			def unwrappedExpr = XtextWrapper.unwrap(sd.getExpression())
			
			if (((AnyExpression) sd.getExpression()).getList()) { // Note that the top-level expression always seems to be an AnyExpression
				this.isList = true
				setProperty(sd.getSymbolName().getName(), "list(" + ((List)unwrappedExpr).join(",") + ")")
			} else {
				setProperty(sd.getSymbolName().getName(), unwrappedExpr)
			}
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
	
	public TaskSymbolDeclaration(Map json) {
		getProperties().putAll(json)
	}
	
	public String toMDL() {
		List attributes = []
		getProperties().each { k, v ->
			attributes.add("${k} = ${v}")
		}
		return attributes.join(",") + "\n"
	}
}
