/*******************************************************************************
 * Copyright (C) 2015 Mango Solutions Ltd - All rights reserved.
 ******************************************************************************/
package eu.ddmore.converter.mdl2json.domain

import eu.ddmore.converter.mdl2json.interfaces.MDLPrintable

/**
 * Represents a list of {@link eu.ddmore.mdl.mdl.BlockStatement} for MDL <-> JSON conversion.
 */
public class BlockStatementList extends ArrayList<BlockStatement> implements MDLPrintable {
    
    private BlockStatementList(final List list) {
        super(list)
    }
    
    public static BlockStatementList fromMDL(final List<eu.ddmore.mdl.mdl.BlockStatement> blks) {
        return new BlockStatementList(blks.collect { new BlockStatement(it) })
    }
    
    public static BlockStatementList fromJSON(final List<Map> json) {
        return new BlockStatementList(json.collect { new BlockStatement(it) })
    }
    
    @Override
    public String toMDL() {
        collect { BlockStatement it ->
            it.toMDL()
        }.join("\n")
    }
    
}
