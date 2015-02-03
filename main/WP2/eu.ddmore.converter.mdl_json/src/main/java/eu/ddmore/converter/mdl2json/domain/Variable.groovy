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
    
    private static final String EXPRESSION_KEY = "_expr"
	
	public Variable(final SymbolDeclaration sd) {
		
		setProperty("name", sd.getSymbolName().getName())
	
		if (sd.getList()) {
			sd.getList().getArguments().getArguments().each {Argument a ->
				setProperty(a.getArgumentName().getName(), XtextWrapper.unwrap(a.getExpression()))
			}
		}
        else if (sd.getExpression() != null) {
            setProperty(EXPRESSION_KEY, XtextWrapper.unwrap(sd.getExpression()))
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
    
    public String getExpression() {
        return (String) getProperty(EXPRESSION_KEY)
    }
	
	public String toMDL() {
		List attributes = []
		// Note: sorting is only done so that we get predictable MDL strings that we can compare in the tests
        // NB: EXPRESSION_KEY needs to be in parentheses to be treated as a string variable rather than a literal
		getProperties().minus( ['name':getName(), (EXPRESSION_KEY):getExpression()] ).sort().each { k, v ->
			attributes.add("${k}=${v}")
		}
        if (getExpression()) {
            """${getName()} = ${getExpression()}"""
        } else if (attributes.isEmpty()) {
			"""${getName()}"""
		} else {
			"""${getName()} : {${attributes.join(", ")}}"""
		}
	}
	
}
