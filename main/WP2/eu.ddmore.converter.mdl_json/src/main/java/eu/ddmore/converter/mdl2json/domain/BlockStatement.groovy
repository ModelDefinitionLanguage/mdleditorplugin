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

    public BlockStatement(final eu.ddmore.mdl.mdl.BlockStatement blkStatement) {
        final String identifier = blkStatement.getIdentifier()
        setProperty(PROPERTY_SUBTYPE, EStatementSubtype.BlockStmt.getIdentifierString())
        final eu.ddmore.mdl.mdl.BlockBody blkBody = blkStatement.getBody()
        final Map<String, String> blkAttrsMap = getBlockArgumentsAsMap(blkStatement)
        if (blkBody instanceof eu.ddmore.mdl.mdl.BlockTextBody) {
            setProperty(identifier, ((eu.ddmore.mdl.mdl.BlockTextBody) blkBody).getText())
        } else { // instance of BlockStatementBody
            StatementList stmtList = StatementList.fromMDL(((eu.ddmore.mdl.mdl.BlockStatementBody) blkBody).getStatements())
            stmtList.setBlockAttributesOnIndividualItems(blkAttrsMap)
            if (stmtList.every { AbstractStatement s -> s.hasSimplifiedJsonRepresentation() }) {
                setProperty(identifier, stmtList.collect { AbstractStatement stmt -> stmt.getSimplifiedJsonRepresentation() })
            } else {
                setProperty(identifier, stmtList)
            }
        }
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
        super(json)
        // Statements type needs special parsing
        if (getRepresentationType().equals(EBlockStatementType.STATEMENTS)) {
            final Map.Entry<String, Object> identifierAndItsBlockRepresentation = getIdentifierAndItsBlockRepresentation()
            final String identifier = identifierAndItsBlockRepresentation.getKey()
            final Object blkRepresentation = identifierAndItsBlockRepresentation.getValue()
            setProperty(identifier, StatementList.fromJSON(json[identifier]))
        }
    }
    
    EBlockStatementType getRepresentationType() {
        final Object blkRepresentation = getIdentifierAndItsBlockRepresentation().getValue()
        if (blkRepresentation instanceof List) {
            if (!blkRepresentation.isEmpty() && blkRepresentation.get(0) instanceof String) {
                return EBlockStatementType.SYMBOL_NAMES
            } else {
                return EBlockStatementType.STATEMENTS
            }
        } else if (blkRepresentation instanceof Map) {
            return EBlockStatementType.NESTED_STATEMENTS
        } else if (blkRepresentation instanceof String) {
            return EBlockStatementType.CONTENT
        }
    }
    
    private Map.Entry<String, Object> getIdentifierAndItsBlockRepresentation() {
        getProperties().find{ k, v -> !k.equals(PROPERTY_SUBTYPE) }
    }

    @Override
    public String toMDL() {
        final StringBuffer sb = new StringBuffer()
        
        final Map.Entry<String, Object> identifierAndItsBlockRepresentation = getIdentifierAndItsBlockRepresentation()
        final String identifier = identifierAndItsBlockRepresentation.getKey()
        final Object blkRepresentation = identifierAndItsBlockRepresentation.getValue()
        EBlockStatementType type = getRepresentationType()
    
        sb.append(IDT)
        sb.append(identifier)
        
        switch (type) {
            case EBlockStatementType.CONTENT:
                sb.append(blkRepresentation)
                break
            case EBlockStatementType.SYMBOL_NAMES:
                sb.append(" {\n")
                sb.append(blkRepresentation.collect {
                    IDT + IDT + it
                }.join("\n"))
                sb.append("\n${IDT}}")
                break
            case EBlockStatementType.STATEMENTS:
                StatementList stmtList = blkRepresentation
                // Retrieve block attributes that had been propagated onto individual items
                Map<String, String> blkAttrs = stmtList.getBlockAttributesFromIndividualItems()
                if (blkAttrs) {
                    sb.append("(")
                    sb.append(blkAttrs.collect {
                        k, v -> k + "=" + v
                    }.join(", "))
                    sb.append(")")
                }
                // Now append the actual MDL block text
                sb.append(" {\n")
                sb.append(stmtList.toMDL())
                sb.append("${IDT}}")
                break
            case EBlockStatementType.NESTED_STATEMENTS:
                sb.append("--NESTED STATEMENTS HERE--")
                break;
        }
        sb.append("\n")
        
    }
    
    /**
     * Represents the three types of representation of a Block Statement.
     */
    static enum EBlockStatementType {
        SYMBOL_NAMES,
        STATEMENTS,
        NESTED_STATEMENTS,
        CONTENT
    }
    
}
