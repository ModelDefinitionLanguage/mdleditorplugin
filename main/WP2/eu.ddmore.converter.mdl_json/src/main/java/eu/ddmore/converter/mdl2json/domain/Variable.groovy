package eu.ddmore.converter.mdl2json.domain;

import org.apache.log4j.Logger
import org.ddmore.mdl.mdl.Argument

import org.ddmore.mdl.mdl.SymbolName

import eu.ddmore.converter.mdl2json.interfaces.MDLPrintable
import eu.ddmore.converter.mdl2json.utils.XtextWrapper


/**
 * Class representing a {@link SymbolDeclaration} from the MDL grammar.
 */
public class Variable extends Expando implements MDLPrintable {

	private static Logger logger = Logger.getLogger(Variable.class)
    
    public static final String NAME_KEY = ".name"
    public static final String EXPRESSION_KEY = ".expr"
	
	public Variable(final org.ddmore.mdl.mdl.SymbolDeclaration sd) {
		
		setProperty(NAME_KEY, sd.getSymbolName().getName())
	
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
		setProperty(NAME_KEY, sn.getName())
	}
	
	public Variable(final Map json) {
		getProperties().putAll(json)
	}
	
	public String getName() {
		return (String) getProperty(NAME_KEY)
	}
    
    public String getExpression() {
        return (String) getProperty(EXPRESSION_KEY)
    }
	
	public String toMDL() {
	    // NB: NAME_KEY and EXPRESSION_KEY need to be in parentheses to be treated as string variables rather than string literals
        final Map specialAttributes = [(NAME_KEY):getName(), (EXPRESSION_KEY):getExpression()]
        
		List attributes = []
		// Note: sorting is only done so that we get predictable MDL strings that we can compare in the tests
		getProperties().minus(specialAttributes).sort().each { k, v ->
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
