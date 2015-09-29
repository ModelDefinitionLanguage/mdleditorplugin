/*******************************************************************************
 * Copyright (C) 2015 Mango Solutions Ltd - All rights reserved.
 ******************************************************************************/
package eu.ddmore.converter.mdl2json.domain

import eu.ddmore.converter.mdl2json.interfaces.MDLPrintable
import eu.ddmore.mdl.mdl.BlockArgument
import eu.ddmore.mdl.mdl.BlockArguments
import eu.ddmore.mdl.mdl.ValuePair
import eu.ddmore.mdl.utils.MdlExpressionConverter
import org.apache.log4j.Logger

/**
 * Represents {@link eu.ddmore.mdl.mdl.BlockStatement} for MDL <-> JSON conversion.
 * Is used as both a child object of top-level {@link MclObject} as well as being
 * used as a subclass of {@link eu.ddmore.mdl.mdl.Statement}.
 */
public class BlockStatement extends AbstractStatement {
    private final Logger LOG = Logger.getLogger(BlockStatement.class)
    
    public final static String PROPERTY_IDENTIFIER = "identifier"
    public final static String PROPERTY_CONTENT = "content"
    public final static String PROPERTY_STATEMENTS = "statements"
    public final static String PROPERTY_SYMBOLNAMES = "symbolNames"
    public final static String PROPERTY_ARGS = "args"

    public BlockStatement(final eu.ddmore.mdl.mdl.BlockStatement blkStatement) {
        setProperty(PROPERTY_SUBTYPE, EStatementSubtype.BlockStmt.getIdentifierString())
        setProperty(PROPERTY_IDENTIFIER, blkStatement.getIdentifier())
        final eu.ddmore.mdl.mdl.BlockBody blkBody = blkStatement.getBody()
        final Map<String, String> blkAttrsMap = getBlockArgumentsAsMap(blkStatement)
        if (blkBody instanceof eu.ddmore.mdl.mdl.BlockTextBody) {
            setProperty(PROPERTY_CONTENT, ((eu.ddmore.mdl.mdl.BlockTextBody) blkBody).getText())
        } else { // instance of BlockStatementBody
            StatementList stmtList = StatementList.fromMDL(((eu.ddmore.mdl.mdl.BlockStatementBody) blkBody).getStatements())
            if (stmtList.every { AbstractStatement s -> s.hasSimplifiedJsonRepresentation() }) {
                setProperty(PROPERTY_SYMBOLNAMES, stmtList.collect { AbstractStatement stmt -> stmt.getSimplifiedJsonRepresentation() })
            } else {
                setProperty(PROPERTY_STATEMENTS, stmtList)
            }
        }
        setProperty(PROPERTY_ARGS, blkAttrsMap)
    }
    
    private static Map<String, String> getBlockArgumentsAsMap(final eu.ddmore.mdl.mdl.BlockStatement blkStatement) {
        if (blkStatement.getBlkArgs().getArgs()) {
            final Map<String, String> blkArgsMap = [:]
            blkStatement.getBlkArgs().getArgs().collect { BlockArgument blkArg ->
                if (blkArg instanceof ValuePair) {
                    blkArgsMap.put(((ValuePair) blkArg).getArgumentName(), MdlExpressionConverter.convertToString(((ValuePair) blkArg).getExpression()))
                } else {
                    throw new UnsupportedOperationException("Subclass " + blkArg.getClass() + " of BlockArgument not currently supported")
                }
            }
            return blkArgsMap
        }
        return null
    }
    
    public BlockStatement(final Map json) {
        setProperty(PROPERTY_SUBTYPE, EStatementSubtype.BlockStmt.getIdentifierString())
        setProperty(PROPERTY_IDENTIFIER, json[PROPERTY_IDENTIFIER])
        if (json[PROPERTY_CONTENT]) {
            setProperty(PROPERTY_CONTENT, json[PROPERTY_CONTENT])
        }
        else if (json[PROPERTY_SYMBOLNAMES]) {
            setProperty(PROPERTY_SYMBOLNAMES, json[PROPERTY_SYMBOLNAMES])
        }
        else if (json[PROPERTY_STATEMENTS]) {
            setProperty(PROPERTY_STATEMENTS, StatementList.fromJSON(json[PROPERTY_STATEMENTS]))
        }
        if (json[PROPERTY_ARGS]) {
            setProperty(PROPERTY_ARGS, json[PROPERTY_ARGS])
        }
    }

    @Override
    public String toMDL() {
        final StringBuffer sb = new StringBuffer()
        
        sb.append(IDT)
        sb.append(getProperty(PROPERTY_IDENTIFIER))
        if (getProperty(PROPERTY_ARGS)) {
            sb.append("(")
            sb.append(getProperty(PROPERTY_ARGS).collect {
                k, v -> k + "=" + v
            }.join(", "))
            sb.append(")")
        }
        
        if (getProperty(PROPERTY_CONTENT)) {
            sb.append(getProperty(PROPERTY_CONTENT))
        } else if (getProperty(PROPERTY_SYMBOLNAMES)) {
            sb.append(" {\n")
            sb.append(getProperty(PROPERTY_SYMBOLNAMES).collect {
                IDT + IDT + it
            }.join("\n"))
            sb.append("\n${IDT}}")
        } else if (getProperty(PROPERTY_STATEMENTS)) {
            sb.append(" {\n")
            sb.append(getProperty(PROPERTY_STATEMENTS).toMDL())
            sb.append("${IDT}}")
        }
        sb.append("\n")
        
//        getProperties().each{ String key, MDLPrintable value ->
//            sb.append("\n${key} = ${value.toMDL()}")
//        }
    }
    
}
