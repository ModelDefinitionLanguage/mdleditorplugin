/*******************************************************************************
 * Copyright (C) 2014-5 Mango Solutions Ltd - All rights reserved.
 ******************************************************************************/
package eu.ddmore.converter.mdl2json.domain;

import org.ddmore.mdl.mdl.PropertyDeclaration
import org.ddmore.mdl.mdl.SourceBlock

import eu.ddmore.converter.mdl2json.interfaces.MDLPrintable
import eu.ddmore.converter.mdl2json.utils.XtextWrapper

/**
 * @author jchard
 *
 */
public class Source extends Expando implements MDLPrintable {

    private static final String SOURCE = "SOURCE"

    /**
     * Constructor which unpacks all the content of the SourceBlock and adds them to the properties of this object
     * 
     * @param sourceBlock
     */
    public Source(SourceBlock sourceBlock) {
        sourceBlock.getStatements().each { PropertyDeclaration pd ->
            def expr = pd.getExpression()
            setProperty(pd.getPropertyName().getName(), XtextWrapper.unwrap(expr))
        }
        if (sourceBlock.getInlineBlock() != null) {
            setProperty("inlineBlock", sourceBlock.getInlineBlock());
        }
    }

    /**
     * Constructor from a JSON object
     * @param json
     */
    public Source(Map json) {
        getProperties().putAll(json)
    }

    /**
     * Returns the MDL equivalent of this object
     * 
     * @return
     */
    public String toMDL() {
        List properties = []
        getProperties().each{ k, v ->
            properties.add("${k}=${v}")
        }

        """${properties.join("\n        ")}"""
    }
}
