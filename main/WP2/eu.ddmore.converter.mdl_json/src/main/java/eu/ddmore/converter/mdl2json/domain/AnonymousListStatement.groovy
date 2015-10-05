/*******************************************************************************
 * Copyright (C) 2015 Mango Solutions Ltd - All rights reserved.
 ******************************************************************************/
package eu.ddmore.converter.mdl2json.domain

import eu.ddmore.converter.mdl2json.utils.KeyValuePairConverter
import eu.ddmore.mdl.mdl.ValuePair

/**
 * Represents {@link eu.ddmore.mdl.mdl.AnonymousListStatement}, a subclass of
 * {@link eu.ddmore.mdl.mdl.Statement}, for MDL <-> JSON conversion.
 * <p>
 * Example MDL:
 * <pre>
 * :: {type is elimination, modelCmt=2, from=CENTRAL, v=V2, cl=CL}
 * </pre>
 * item within <code>COMPARMENT</code> sub-block within <code>MODEL_PREDICTION</code> block within <code>mdlobj</code> section.
 */
public class AnonymousListStatement extends AbstractStatement {
    
    public final static String PROPERTY_ATTRS = "attrs"
    
    public final static String UNDEF_VAR_NAME_PLACEHOLDER = ""

    public AnonymousListStatement(final eu.ddmore.mdl.mdl.AnonymousListStatement listStmt) {
        // To try and simplify the JSON structure and thus the R representation, we don't specify the
        // subtype here. If no subtype property is found when parsing equivalent JSON, the default
        // subtype is either ListDefinition or AnonymousListStatement, distinguished by whether
        // the variable name, the Key of the single Entry in the Map, is specified or is empty.
        setProperty(UNDEF_VAR_NAME_PLACEHOLDER, KeyValuePairConverter.toMap(listStmt.getList().getAttributes()))
    }
    
    public AnonymousListStatement(final Map json) {
        super(json)
    }
    
    @Override
    public String toMDL() {
        final StringBuffer sb = new StringBuffer()
        sb.append(IDT)
        sb.append(IDT)
        sb.append(" :: {")
        sb.append(KeyValuePairConverter.toMDL(getProperty(UNDEF_VAR_NAME_PLACEHOLDER)))
        sb.append("}")
        sb.toString()
    }
        
}
