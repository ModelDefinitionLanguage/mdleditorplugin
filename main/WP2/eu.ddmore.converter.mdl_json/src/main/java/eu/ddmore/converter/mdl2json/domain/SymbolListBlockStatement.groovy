/*******************************************************************************
 * Copyright (C) 2015 Mango Solutions Ltd - All rights reserved.
 ******************************************************************************/
package eu.ddmore.converter.mdl2json.domain


/**
 *
 */
public class SymbolListBlockStatement extends BlockStatement<List<String>> {
    
    public SymbolListBlockStatement(final String blockName, final eu.ddmore.mdl.mdl.BlockStatementBody blkStmtBody) {
        final StatementList stmtList = StatementList.fromMDL(blkStmtBody.getStatements())
        setProperty(blockName, stmtList.collect { AbstractStatement stmt -> stmt.getSimplifiedJsonRepresentation() })
    }
    
    public SymbolListBlockStatement(final String blockName, final List<String> blockRepresentation) {
        setProperty(blockName, blockRepresentation)
    }
    
    @Override
    public String toMDL() {
        final StringBuffer sb = new StringBuffer()
        sb.append(IDT)
        sb.append(getBlockName())
        sb.append(" {\n")
        sb.append(getBlockRepresentation().collect {
            IDT + IDT + it
        }.join("\n"))
        sb.append("\n${IDT}}\n")
        sb.toString()
    }
    
}
