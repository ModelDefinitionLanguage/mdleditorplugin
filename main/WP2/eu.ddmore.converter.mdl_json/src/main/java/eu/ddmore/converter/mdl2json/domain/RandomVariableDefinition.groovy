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
 * item within <code>RANDOM_VARIABLE_DEFINITION</code> block within <code>mdlObj</code> section.
 * <p>
 * Equivalent JSON:
 * <pre>
 * {".subtype":"RandVarDefn","blkAttrs":{"level":"ID"},"args":{"mean":"0","sd":"PPV_CL"},"name":"ETA_CL","distType":"Normal"}
 * </pre>
 * Note the <code>blkAttrs</code> attributes which aren't relevant to the item/variable itself
 * but have been propagated from the parent {@link BlockStatement} - see
 * {@link AbstractStatement#setAttributesFromBlock(Map)} and {@link AbstractStatement#getAttributesFromBlock()}.
 */
public class RandomVariableDefinition extends AbstractStatement {
    
    public final static String PROPERTY_NAME = "name"
    public final static String PROPERTY_DIST_TYPE = "distType"
    public final static String PROPERTY_ARGS = "args"

    /**
     * Constructor creating from MDL grammar objects.
     * <p>
     * @param randomVarDefn - {@link eu.ddmore.mdl.mdl.RandomVariableDefinition} object from the MDL grammar
     */
    public RandomVariableDefinition(final eu.ddmore.mdl.mdl.RandomVariableDefinition randomVarDefn) {
        setProperty(PROPERTY_SUBTYPE, EStatementSubtype.RandomVarDefinition.getIdentifierString())
        setProperty(PROPERTY_NAME, randomVarDefn.getName())
        final eu.ddmore.mdl.mdl.BuiltinFunctionCall distn = randomVarDefn.getDistn();
        setProperty(PROPERTY_DIST_TYPE, distn.getFunc())
        setProperty(PROPERTY_ARGS, KeyValuePairConverter.toMap(distn.getArgList()))
    }
    
    /**
     * Constructor creating from JSON.
     * <p>
     * @param json - {@link Map} of content
     */
    public RandomVariableDefinition(final Map json) {
        super(json)
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
