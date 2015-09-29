/*******************************************************************************
 * Copyright (C) 2015 Mango Solutions Ltd - All rights reserved.
 ******************************************************************************/
package eu.ddmore.converter.mdl2json.domain

import eu.ddmore.converter.mdl2json.utils.KeyValuePairConverter
import eu.ddmore.mdl.mdl.NamedFuncArguments
import eu.ddmore.mdl.mdl.ValuePair

/**
 * Represents {@link eu.ddmore.mdl.mdl.RandomVariableDefinition}, a subclass of {@link eu.ddmore.mdl.mdl.Statement},
 * for MDL <-> JSON conversion.
 * <p>
 * Example MDL:
 * <pre>
 * ETA_CL ~ Normal(mean = 0, sd = PPV_CL)
 * </pre>
 * item within <code>RANDOM_VARIABLE_DEFINITION</code> block within <code>mdlobj</code> section.
 */
public class RandomVariableDefinition extends AbstractStatement {
    
    public final static String PROPERTY_NAME = "name"
    public final static String PROPERTY_DIST_TYPE = "distType"
    public final static String PROPERTY_ARGS = "args"

    public RandomVariableDefinition(final eu.ddmore.mdl.mdl.RandomVariableDefinition randomVarDefn) {
        setProperty(PROPERTY_SUBTYPE, EStatementSubtype.RandomVarDefinition.getIdentifierString())
        setProperty(PROPERTY_NAME, randomVarDefn.getName())
        final eu.ddmore.mdl.mdl.BuiltinFunctionCall distn = randomVarDefn.getDistn();
        setProperty(PROPERTY_DIST_TYPE, distn.getFunc())
        setProperty(PROPERTY_ARGS, KeyValuePairConverter.toMap(distn.getArgList()))
    }
    
    public RandomVariableDefinition(final Map json) {
        setProperty(PROPERTY_SUBTYPE, EStatementSubtype.RandomVarDefinition.getIdentifierString())
        setProperty(PROPERTY_NAME, json[PROPERTY_NAME])
        setProperty(PROPERTY_DIST_TYPE, json[PROPERTY_DIST_TYPE])
        setProperty(PROPERTY_ARGS, json[PROPERTY_ARGS])
    }
    
    @Override
    public String toMDL() {
        final StringBuffer sb = new StringBuffer()
        sb.append(IDT)
        sb.append(IDT)
        sb.append(getProperty(PROPERTY_NAME))
        sb.append(" ~ ")
        sb.append(getProperty(PROPERTY_DIST_TYPE))
        sb.append("(")
        sb.append(KeyValuePairConverter.toMDL(getProperty(PROPERTY_ARGS)))
        sb.append(")")
//        if (getProperty(PROPERTY_LIST)) {
//            sb.append(" : {")
//            sb.append(getProperty(PROPERTY_LIST).collect { // List of Maps, each Map containing one Entry
//                Map m -> m.collect {
//                    Map.Entry ent -> KeyValuePairMdlPrinter.toMDL(ent)
//                }.join("")
//            }.join(", "))
//            sb.append("}")
//        }
        sb.toString()
    }
        
}
