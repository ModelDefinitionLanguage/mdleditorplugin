/*******************************************************************************
 * Copyright (C) 2015 Mango Solutions Ltd - All rights reserved.
 ******************************************************************************/
package eu.ddmore.converter.mdl2json.domain

import eu.ddmore.converter.mdl2json.domain.BlockStatement.EBlockStatementType;
import eu.ddmore.converter.mdl2json.interfaces.MDLPrintable

/**
 * Represents a list of {@link eu.ddmore.mdl.mdl.BlockStatement} for MDL <-> JSON conversion.
 */
public class TopLevelBlockStatements extends Expando implements MDLPrintable {
    
    /**
     * Constructs the {@link TopLevelBlockStatements} Map, and populates it with the
     * provided set of Map Entries mapping block name to block representation, but
     * merging block representations where the same block appears multiple times at
     * the top level (i.e. RANDOM_VARIABLE_DEFINITION block).
     * <p>
     * @see {@link BlockStatement#merge(StatementList, StatementList)}
     * <p>
     * @param blockNamesAndTheirRepresentations - Collection of Map Entries mapping
     *        block name to block representation
     */
    private TopLevelBlockStatements(final Collection<Map.Entry<String, Object>> blockNamesAndTheirRepresentations) {
        blockNamesAndTheirRepresentations.each { Map.Entry<String, Object> e ->
            final String blockName = e.getKey()
            def thisBlkRepresentation = e.getValue() // Won't be null
            def existingBlkRepresentation = getProperty(blockName) // Often will be null
            setProperty(blockName, BlockStatement.merge(thisBlkRepresentation, existingBlkRepresentation))
        }
    }
    
    public static TopLevelBlockStatements fromMDL(final List<eu.ddmore.mdl.mdl.BlockStatement> blks) {
        new TopLevelBlockStatements(blks.collect {
            new BlockStatement(it).getBlockNameAndItsRepresentation()
        })
    }
    
    public static TopLevelBlockStatements fromJSON(final Map<String, Object> json) {
        new TopLevelBlockStatements(json.entrySet())
    }
    
    @Override
    public String toMDL() {
        getProperties().collect { String identifier, Object blockRepresentation ->
            new BlockStatement([(identifier) : (blockRepresentation)]).toMDL()
        }.join("\n")
    }
    
}
