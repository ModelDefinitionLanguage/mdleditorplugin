/*******************************************************************************
 * Copyright (C) 2015 Mango Solutions Ltd - All rights reserved.
 ******************************************************************************/
package eu.ddmore.converter.mdl2json.domain

import eu.ddmore.converter.mdl2json.interfaces.MDLPrintable

/**
 * Abstract superclass representing {@link eu.ddmore.mdl.mdl.Statement} for MDL <-> JSON conversion.
 * <p>
 * Different types of Statement are represented in different ways in the JSON {@link Map}
 * but their common features are:
 * <ul>
 * <li>A attribute ".subtype" indicating the type of Statement to re-create when
 *     writing MDL back out from JSON, see {@link EStatementSubtype}; unless a
 *     Statement can be represented in a simplified representation in which case
 *     the subtype may not be written out explicitly but will be inferred
 * <li>A set of block attributes "blkAttrs" which will be populated for Statements
 *     within Blocks where the Blocks have attributes; these attributes are stored
 *     on each Statement instead in the JSON
 * </ul>
 */
public abstract class AbstractStatement extends Expando implements MDLPrintable {

    protected static final String PROPERTY_SUBTYPE = ".subtype"
    protected static final String PROPERTY_BLOCKATTRS = "blkAttrs"
    
    protected AbstractStatement() {
    }
    
    /**
     * Constructor for the abstract superclass, creating from JSON representation.
     * <p>
     * As part of this, propagates block attributes onto individual variables/items, if the
     * enclosing block type permits block-level attributes and there are any such attributes.
     * <p>
     * @param json - JSON representation of this Statement
     */
    protected AbstractStatement(final Map json) {
        getProperties().putAll(json)
    }
    
    /**
     * Tells whether this instance of the subclass of {@link AbstractStatement} can be simplified
     * as a simple String for writing out to JSON format and thus provide a more concise
     * representation when read in to an R as a graph of R objects.
     * <p>
     * Example: A symbol name is represented as a {@link eu.ddmore.mdl.mdl.domain.EquationDefinition}
     * with no expression on the RHS. By default the equivalent
     * {@link eu.ddmore.converter.mdl2json.domain.EquationDefinition} would be written out to JSON as
     * <code>{".subtype":"EquationDef","name":"GUT"}</code> but it is better,
     * for the aforementioned reason, for this to be represented just as <code>"GUT"</code>.
     * <p>
     * To be overloaded by subclasses if appropriate; returns false by default.
     * <p>
     * @return true if the representation should be simplified as a simple String when writing out
     *         to JSON, false if it has to be represented verbosely
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
     * @return simplified JSON representation, as a String
     */
    String getSimplifiedJsonRepresentation() {
        throw new IllegalStateException("Tried to obtain a simplified JSON representation of " + toString() + " but this isn't possible for this data")
    }
    
    /**
     * @see {@link eu.ddmore.converter.mdl2json.domain.StatementList#setBlockAttributesOnIndividualItems(Map)}
     * <p>
     * @param blkAttrs - Map of String->String holding the attributes of the enclosing block
     */
    void setAttributesFromBlock(final Map<String, String> blkAttrs) {
        if (blkAttrs != null) {
            setProperty(PROPERTY_BLOCKATTRS, blkAttrs)
        }
    }
    
    /**
     * @see {@link eu.ddmore.converter.mdl2json.domain.StatementList#getBlockAttributesFromIndividualItems()}
     * <p>
     * @return blkAttrs - Map of String->String holding the attributes of the enclosing block
     */
    Map<String, String> getAttributesFromBlock() {
        getProperty(PROPERTY_BLOCKATTRS)
    }
    
}
