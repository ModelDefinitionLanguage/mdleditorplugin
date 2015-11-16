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
 * DT : { use is doseTime, idvColumn=TIME, amtColumn=AMT }
 * </pre>
 * item within <code>DATA_DERIVED_VARIABLES</code> block within <code>dataObj</code> section.
 * <p>
 * Equivalent JSON:
 * <pre>
 * {"DT":{"use":"doseTime","idvColumn":"TIME","amtColumn":"AMT"}}
 * </pre>
 * Equivalent JSON:
 * <p>
 * Note that no <code>.subtype</code> attribute is specified in the JSON; the subtype is
 * inferred from the structure and the content of the JSON.
 */
public class ListDefinition extends AbstractStatement {

    /**
     * Constructor creating from MDL grammar objects.
     * <p>
     * To try and simplify the JSON structure and thus the R representation, we don't specify the
     * subtype here. If no subtype property is found when parsing equivalent JSON, the default
     * subtype is either ListDefinition or AnonymousListStatement, distinguished by whether
     * the variable name, the Key of the single Entry in the Map, is specified or is empty.
     * <p>
     * @param listDefn - {@link eu.ddmore.mdl.mdl.ListDefinition} object from the MDL grammar
     */
    public ListDefinition(final eu.ddmore.mdl.mdl.ListDefinition listDefn) {
        setProperty(listDefn.getName(), KeyValuePairConverter.toMap(listDefn.getList().getAttributes()))
    }
    
    /**
     * Constructor creating from JSON.
     * <p>
     * @param json - {@link Map} of content
     */
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
