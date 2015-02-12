package eu.ddmore.converter.mdl2json.domain;

import org.apache.log4j.Logger
import org.ddmore.mdl.mdl.Argument

import org.ddmore.mdl.mdl.SymbolName

import eu.ddmore.converter.mdl2json.interfaces.MDLPrintable
import eu.ddmore.converter.mdl2json.utils.XtextWrapper


/**
 * Class representing a {@link SymbolDeclaration} from the MDL grammar.
 * It can represent either an expression (= operator), an attribute
 * list (: operator), or a random-variable attribute list (~ operator).
 */
public class Variable extends Expando implements MDLPrintable {

    private static Logger logger = Logger.getLogger(Variable.class)

    public static final String NAME_KEY = ".name"
    public static final String EXPRESSION_KEY = ".expr"
    public static final String RANDOMVAR_ATTRS_KEY = ".random_var_attrs"

    public Variable(final org.ddmore.mdl.mdl.SymbolDeclaration sd) {

        setProperty(NAME_KEY, sd.getSymbolName().getName())

        if (sd.getList()) {
            // 'Normal' attributes, add them to the Map of properties directly
            sd.getList().getArguments().getArguments().each {Argument a ->
                setProperty(a.getArgumentName().getName(), XtextWrapper.unwrap(a.getExpression()))
            }
        }
        else if (sd.getRandomList()) {
            // Create a nested Map of these attributes, to distinguish them from 'normal' attributes
            final Map randomVarAttrs = [:]
            sd.getRandomList().getArguments().getArguments().each {Argument a ->
                randomVarAttrs.put(a.getArgumentName().getName(), XtextWrapper.unwrap(a.getExpression()))
            }
            setProperty(RANDOMVAR_ATTRS_KEY, randomVarAttrs)
        }
        else if (sd.getExpression()) {
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

    public Map getRandomVarAttributes() {
        return (Map) getProperty(RANDOMVAR_ATTRS_KEY)
    }

    public String toMDL() {
        // NB: NAME_KEY, EXPRESSION_KEY, RANDOMVAR_ATTRS_KEY need to be in parentheses
        // in order to be treated as string variables rather than string literals
        final Map specialAttributes = [(NAME_KEY):getName(), (EXPRESSION_KEY):getExpression(), (RANDOMVAR_ATTRS_KEY):getRandomVarAttributes()]

        List attributes = []
        // Note: sorting is only done so that we get predictable MDL strings that we can compare in the tests
        getProperties().minus(specialAttributes).sort().each { k, v ->
            attributes.add("${k}=${v}")
        }

        if (getExpression()) {
            """${getName()} = ${getExpression()}"""
        }
        else if (getRandomVarAttributes()) {
            List randomVarAttrs = []
            // Note: sorting is only done so that we get predictable MDL strings that we can compare in the tests
            getRandomVarAttributes().sort().each { k, v ->
                randomVarAttrs.add("${k}=${v}")
            }
            """${getName()} ~ {${randomVarAttrs.join(", ")}}"""
        }
        else if (!attributes.isEmpty()) {
            """${getName()} : {${attributes.join(", ")}}"""
        }
        else {
            """${getName()}"""
        }
    }

}
