/*******************************************************************************
 * Copyright (C) 2015 Mango Solutions Ltd - All rights reserved.
 ******************************************************************************/
package eu.ddmore.converter.mdl2json.domain


/**
 *
 */
public class StatementListBlockStatement extends BlockStatement<StatementList> {
    
    public StatementListBlockStatement(final String blockName, final eu.ddmore.mdl.mdl.BlockStatementBody blkStmtBody, final Map<String, String> blkAttrsMap) {
        final StatementList stmtList = StatementList.fromMDL(blkStmtBody.getStatements())
        stmtList.setBlockAttributesOnIndividualItems(blkAttrsMap)
        setProperty(blockName, stmtList)
    }
    
    public StatementListBlockStatement(final String blockName, final List<Map> blockRepresentation) {
        setProperty(blockName, StatementList.fromJSON(blockRepresentation))
    }
    
    @Override
    public String toMDL() {
        final StringBuffer sb = new StringBuffer()
        sb.append(stmtListToMDL())
        sb.append("\n")
        sb.toString()
    }
    
    /**
     * This {@link StatementList} may actually contain statements corresponding to different
     * blocks having the same name but different block-level attributes (i.e.
     * RANDOM_VARIABLE_DEFINITION), that have been stored in the JSON representation on the
     * individual items; split these back out into individual Lists of {@link StatementList}
     * and write out each one in turn.
     * <p>
     * @return the MDL textual representation i.e. as per the original MDL file
     */
    private String stmtListToMDL() {
        getBlockRepresentation().splitByBlockAttributesFromIndividualItems().collect { StatementList stmtList ->
            final StringBuffer sb1 = new StringBuffer()
            sb1.append(IDT)
            sb1.append(getBlockName())
            // Retrieve block attributes that had been propagated onto individual items
            Map<String, String> blkAttrs = stmtList.getBlockAttributesFromIndividualItems()
            if (blkAttrs) {
                sb1.append("(")
                sb1.append(blkAttrs.collect {
                    k, v -> k + "=" + v
                }.join(", "))
                sb1.append(")")
            }
            // Now append the actual MDL block text
            sb1.append(" {\n")
            sb1.append(stmtList.toMDL())
            sb1.append("${IDT}}")
            sb1.toString()
        }.join("\n\n")
    }
        
}
