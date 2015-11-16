/*******************************************************************************
 * Copyright (C) 2015 Mango Solutions Ltd - All rights reserved.
 ******************************************************************************/
package eu.ddmore.converter.mdl2json.utils

import eu.ddmore.mdl.mdl.FuncArguments;
import eu.ddmore.mdl.mdl.NamedFuncArguments
import eu.ddmore.mdl.mdl.ValuePair
import eu.ddmore.mdl.utils.MdlExpressionConverter;

/**
 * Attributes of the form <code>key = value</code> are not always represented
 * in MDL literally as this; for certain attribute names e.g. <code>type</code>
 * the MDL representation would be e.g. <code>type is corr</code>.
 * <p>
 * This class handles the printing of key-value attribute definitions according
 * to the above.
 * <p>
 * It also handles the conversion of a list of MDL Grammar {@link ValuePair}s
 * to a Map for use in the JSON representation.
 */
public class KeyValuePairConverter {
    
    final static Collection<String> IS_REPRESENTATION_ATTR_NAMES = [ "type", "use", "inputFormat", "link", "event", "trans", "target", "algo", "solver" ]

    public static String toMDL(final Map.Entry<String, String> entry) {
        toMDL(entry.getKey(), entry.getValue())
    }
    
    public static String toMDL(final Map<String, String> map) {
        map.collect { k, v -> toMDL(k, v) }.join(", ")
    }
    
    public static String toMDL(final String key, final String value) {
        if (IS_REPRESENTATION_ATTR_NAMES.contains(key)) {
            "${key} is ${value}"
        } else {
            "${key}=${value}"
        }
    }
    
    public static Map<String, String> toMap(final List<ValuePair> valuePairsList) {
        final Map<String, String> valuePairsMap = [:]
        valuePairsList.each {
            ValuePair vp -> valuePairsMap.put(vp.getArgumentName(), MdlExpressionConverter.convertToString(vp.getExpression()))
        }
        valuePairsMap
    }
    
    public static Map<String, String> toMap(final NamedFuncArguments funcArgs) {
        toMap(funcArgs.getArguments())
    }
    // else throw new UnsupportedOperationException("FuncArguments other than NamedFuncArguments not currently supported for an EquationDefinition consisting of a BuiltinFunctionCall")
    
}
