/*******************************************************************************
 * Copyright (C) 2015 Mango Solutions Ltd - All rights reserved.
 ******************************************************************************/
package eu.ddmore.converter.mdl2json.domain

import eu.ddmore.converter.mdl2json.utils.KeyValuePairConverter;
import eu.ddmore.mdl.mdl.PropertyStatement
import eu.ddmore.mdl.mdl.ValuePair


/**
 *
 */
public class TaskObjectBlockStatement extends BlockStatement<String> {
    
    public TaskObjectBlockStatement(final String blockName, final eu.ddmore.mdl.mdl.BlockStatementBody blkStmtBody) {
        def taskAttrsMap = [:]
        blkStmtBody.getStatements().collect { PropertyStatement stmt ->
            taskAttrsMap.putAll(KeyValuePairConverter.toMap(stmt.getProperties()))
        }
        setProperty(blockName, taskAttrsMap)
    }
    
    public TaskObjectBlockStatement(final String blockName, final Map<String, String> taskAttrs) {
        setProperty(blockName, taskAttrs)
    }
    
    @Override
    public String toMDL() {
        final StringBuffer sb = new StringBuffer()
        sb.append(IDT)
        sb.append(getBlockName())
        sb.append(" {\n")
        sb.append(getBlockRepresentation().collect {
            k, v -> IDT + IDT + "set " + KeyValuePairConverter.toMDL(k, v)
        }.join("\n"))
        sb.append("\n${IDT}}\n")
        sb.toString()
    }
        
}
