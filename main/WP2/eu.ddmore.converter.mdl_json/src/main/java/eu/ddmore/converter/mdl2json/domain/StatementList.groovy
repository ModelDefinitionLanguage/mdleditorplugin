/*******************************************************************************
 * Copyright (C) 2015 Mango Solutions Ltd - All rights reserved.
 ******************************************************************************/
package eu.ddmore.converter.mdl2json.domain

import eu.ddmore.converter.mdl2json.interfaces.MDLPrintable

/**
 * Represents a list of {@link eu.ddmore.mdl.mdl.Statement} for MDL <-> JSON conversion.
 */
public class StatementList extends ArrayList<AbstractStatement> implements MDLPrintable {

    private StatementList() {
        super()
    }
    
    private StatementList(final List list) {
        super(list)
    }
    
    public static StatementList fromMDL(final List<eu.ddmore.mdl.mdl.Statement> blks) {
        return new StatementList(blks.collect { eu.ddmore.mdl.mdl.Statement theirStmt ->
            StatementFactory.fromMDL(theirStmt)
        })
    }
    
    public static StatementList fromJSON(final List<Map> json) {
        return new StatementList(json.collect { StatementFactory.fromJSON(it) })
    }
    
    /**
     * The RANDOM_VARIABLE_DEFINITION block, and possibly other blocks in the future,
     * has attributes on the block itself as well as for individual variables/items.
     * To simplify the representation of these in the R objects, and make the
     * representation of different types of block more consistent, we propagate any
     * block attributes onto the individual items when converting to JSON, and do
     * the reverse when writing back out to MDL.
     * <p>
     * This method copies the single set of block attributes onto each individual variable/item.
     * It is a no-op if there are no attributes of this block (i.e. the parameter is null).
     * <p>
     * @param blkAttrs - Map of String->String holding the attributes of the enclosing block
     * @see {@link #getBlockAttributesFromIndividualItems()}
     */
    void setBlockAttributesOnIndividualItems(final Map<String, String> blkAttrs) {
        collect {
            AbstractStatement ourStmt -> ourStmt.setAttributesFromBlock(blkAttrs)
        }
    }
    
    /**
     * The RANDOM_VARIABLE_DEFINITION block, and possibly other blocks in the future,
     * has attributes on the block itself as well as for individual variables/items.
     * To simplify the representation of these in the R objects, and make the
     * representation of different types of block more consistent, we propagate any
     * block attributes onto the individual items when converting to JSON, and do
     * the reverse when writing back out to MDL.
     * <p>
     * This method retrieves the block attributes that were stored on the first
     * individual variable/item; it is assumed that all individual variables/items
     * had the same set of block attributes copied on to them.
     * <p>
     * @return Map of String->String holding the attributes of the enclosing block
     * @see {@link #setBlockAttributesOnIndividualItems(Map)}
     */
    Map<String, String> getBlockAttributesFromIndividualItems() {
        collect {
            AbstractStatement ourStmt -> ourStmt.getAttributesFromBlock()
        }.first() // All items should share the same block attributes
    }
    
    /**
     * @return List of {@link StatementList}, segregated by common block attributes
     *         that have been stored on the individual variables/items of the
     *         {@link AbstractStatement}s
     * @see {@link BlockStatement#stmtListToMDL()} from where this method is used
     */
    List<StatementList> splitByBlockAttributesFromIndividualItems() {
        final List<StatementList> stmtLists = new ArrayList<StatementList>()
        each { AbstractStatement stmt ->
            if (stmtLists.isEmpty() || !Objects.equals(stmtLists.last().getBlockAttributesFromIndividualItems(), stmt.getAttributesFromBlock())) {
                // Jump to the next StatementList
                stmtLists.add(new StatementList())
            }
            stmtLists.last().add(stmt)
        }
        stmtLists
    }
    
    @Override
    public String toMDL() {
        collect { AbstractStatement it ->
            if (it) it.toMDL() else ""
        }.join("\n").concat("\n")
    }
    
}
