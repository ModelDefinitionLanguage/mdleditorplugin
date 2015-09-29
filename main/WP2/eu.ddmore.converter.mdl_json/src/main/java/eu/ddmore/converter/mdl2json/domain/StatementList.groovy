/*******************************************************************************
 * Copyright (C) 2015 Mango Solutions Ltd - All rights reserved.
 ******************************************************************************/
package eu.ddmore.converter.mdl2json.domain

import eu.ddmore.converter.mdl2json.interfaces.MDLPrintable

/**
 * Represents a list of {@link eu.ddmore.mdl.mdl.Statement} for MDL <-> JSON conversion.
 */
public class StatementList extends ArrayList<AbstractStatement> implements MDLPrintable {
    
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
    
    @Override
    public String toMDL() {
        collect { AbstractStatement it ->
            if (it) it.toMDL() else ""
        }.join("\n").concat("\n")
    }
    
}
