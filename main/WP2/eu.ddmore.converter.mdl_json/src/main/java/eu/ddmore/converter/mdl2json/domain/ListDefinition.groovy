/*******************************************************************************
 * Copyright (C) 2015 Mango Solutions Ltd - All rights reserved.
 ******************************************************************************/
package eu.ddmore.converter.mdl2json.domain

import eu.ddmore.converter.mdl2json.utils.KeyValuePairConverter
import eu.ddmore.mdl.mdl.ValuePair

/**
 * Represents {@link eu.ddmore.mdl.mdl.ListDefinition}, a subclass of {@link eu.ddmore.mdl.mdl.Statement},
 * for MDL <-> JSON conversion.
 * <p>
 * Example MDL:
 * <pre>
 * AMT : { use is amt, variable = GUT }
 * </pre>
 * item within <code>DATA_INPUT_VARIABLES</code> block within <code>dataobj</code> section.
 */
public class ListDefinition extends AbstractStatement {

    public ListDefinition(final eu.ddmore.mdl.mdl.ListDefinition listDefn) {
        // To try and simplify the JSON structure and thus the R representation, we don't specify the
        // subtype here. If no subtype property is found when parsing equivalent JSON, the default
        // subtype is either ListDefinition or AnonymousListStatement, distinguished by whether
        // the variable name, the Key of the single Entry in the Map, is specified or is empty.
        setProperty(listDefn.getName(), KeyValuePairConverter.toMap(listDefn.getList().getAttributes()))
    }
    
    public ListDefinition(final Map json) {
        super(json)
    }
    
    @Override
    public String toMDL() {
        Map<String, String> props = getProperties()
        props.remove(PROPERTY_SUBTYPE)
        final StringBuffer sb = new StringBuffer()
        sb.append(IDT)
        sb.append(IDT)
        sb.append(props.collect { String symbolName, Map<String, String> attrsMap -> // Should just be one symbolName mapping per ListDefinition
            symbolName + " : {" + KeyValuePairConverter.toMDL(attrsMap) + "}"
        }.join(""))
//        sb.append(getProperty(PROPERTY_NAME))
//        if (getProperty(PROPERTY_ATTRS)) {
//            sb.append(" : {")
//            sb.append(getProperty(PROPERTY_ATTRS).collect {
//                Map.Entry<String, String> e -> KeyValuePairConverter.toMDL(e)
//            }.join(", "))
//            sb.append("}")
//        }
        sb.toString()
    }
        
}
