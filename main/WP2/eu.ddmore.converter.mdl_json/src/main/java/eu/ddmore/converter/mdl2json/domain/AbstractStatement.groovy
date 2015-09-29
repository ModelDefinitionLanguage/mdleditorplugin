/*******************************************************************************
 * Copyright (C) 2015 Mango Solutions Ltd - All rights reserved.
 ******************************************************************************/
package eu.ddmore.converter.mdl2json.domain

import eu.ddmore.converter.mdl2json.interfaces.MDLPrintable

/**
 * Abstract superclass representing {@link eu.ddmore.mdl.mdl.Statement} for MDL <-> JSON conversion.
 */
public abstract class AbstractStatement extends Expando implements MDLPrintable  {

    protected static final String PROPERTY_SUBTYPE = ".subtype"
    protected static final String PROPERTY_BLOCKATTRS = "blkAttrs"
    
    /**
     * Tells whether this instance of the subclass of {@link AbstractStatement} can
     * be simplified for writing out to JSON format and thus provide a more concise
     * representation when read in to an R as a graph of R objects.
     * <p>
     * Example: A symbol name is represented as a {@link eu.ddmore.mdl.mdl.domain.EquationDefinition}
     * with no expression on the RHS. By default the equivalent
     * {@link eu.ddmore.converter.mdl2json.domain.EquationDefinition} will be written out to JSON as
     * <code>{".subtype":"EquationDef","name":"GUT"}</code> but it is better,
     * for the aforementioned reason, for this to be represented just as <code>"GUT"</code>.
     * <p>
     * To be overloaded by subclasses if appropriate; returns false by default.
     * <p>
     * @return true if the representation should be simplified when writing out to JSON,
     *         false if it has to be represented verbosely
     */
    boolean hasSimplifiedJsonRepresentation() {
        return false;
    }
    
    /**
     * Returns the simplified JSON representation of this instance of the subclass of
     * {@link AbstractStatement}, if appropriate, otherwise throws {@link IllegalStateException}.
     * <p>
     * @see {@link #hasSimplifiedJsonRepresentation()} for explanation as to what a "simplified" representation means
     * <p>
     * @return simplified JSON representation, e.g. as a String
     */
    Object getSimplifiedJsonRepresentation() {
        throw new IllegalStateException("Tried to obtain a simplified JSON representation of " + toString() + " but this isn't possible for this data")
    }
    
}
