/*******************************************************************************
 * Copyright (C) 2015 Mango Solutions Ltd - All rights reserved.
 ******************************************************************************/
package eu.ddmore.converter.mdl2json.domain

import org.apache.log4j.Logger

import eu.ddmore.mdl.mdl.BlockArgument
import eu.ddmore.mdl.mdl.ValuePair
import eu.ddmore.mdl.utils.MdlExpressionConverter

/**
 * Represents {@link eu.ddmore.mdl.mdl.BlockStatement} for MDL <-> JSON conversion.
 * Is used as both a child object of top-level {@link MclObject} as well as being
 * used as a subclass of {@link eu.ddmore.mdl.mdl.Statement}.
 * <p>
 * The content of a block is represented by this class as a Map containing a single 'main'
 * Entry, the key being the block name and the value being the "block representation"
 * (explained below). There is also a property ({@link AbstractStatement#PROPERTY_SUBTYPE})
 * identifying the subtype of {@link AbstractStatement} that is represented, for use when
 * converting back from JSON (only used when this {@link BlockStatement} is representing
 * a nested block).
 * <p>
 * Note that when representing a top-level block, this class isn't actually incorporated
 * into the domain object graph, since this would introduce an unwanted extra layer
 * of nesting in the JSON representation. Instead, the block name and block
 * representation are extracted by {@link TopLevelBlockStatements} and stored as a Map
 * there instead. In other words, when representing a top-level block, instances of
 * this class are created and destroyed on the fly just as a means to access the
 * block representation that this class provides.
 * <p>
 * By "block representation", we mean the following three different 'types' of Statements
 * that a block can represent. These can be distinguishing in incoming JSON by the
 * structure of the JSON.
 * <h3>Statement List</h3>
 * List of {@link StatementList} (i.e. List of attribute Maps} The default form of
 * representation of a block.
 * <h3>Symbol List</h3>
 * List of Strings. Used where a block comprises a list of variable names / items which have
 * no attributes or other information that needs to be encoded in the JSON.
 * <h3>Content</h3>
 * String. Verbatim MDL, no parsing performed.
 * <p>
 */
public class BlockStatement extends AbstractStatement {
    private final Logger LOG = Logger.getLogger(BlockStatement.class)

    public BlockStatement(final eu.ddmore.mdl.mdl.BlockStatement blkStatement) {
        final String blockName = blkStatement.getIdentifier()
        setProperty(PROPERTY_SUBTYPE, EStatementSubtype.BlockStmt.getIdentifierString())
        final eu.ddmore.mdl.mdl.BlockBody blkBody = blkStatement.getBody()
        final Map<String, String> blkAttrsMap = getBlockArgumentsAsMap(blkStatement)
        if (blkBody instanceof eu.ddmore.mdl.mdl.BlockTextBody) {
            setProperty(blockName, ((eu.ddmore.mdl.mdl.BlockTextBody) blkBody).getText())
        } else { // instance of BlockStatementBody
            StatementList stmtList = StatementList.fromMDL(((eu.ddmore.mdl.mdl.BlockStatementBody) blkBody).getStatements())
            stmtList.setBlockAttributesOnIndividualItems(blkAttrsMap)
            if (stmtList.every { AbstractStatement s -> s.hasSimplifiedJsonRepresentation() }) {
                setProperty(blockName, stmtList.collect { AbstractStatement stmt -> stmt.getSimplifiedJsonRepresentation() })
            } else {
                setProperty(blockName, stmtList)
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
            final Map.Entry<String, Object> blockNameAndItsRepresentation = getBlockNameAndItsRepresentation()
            final String blockName = blockNameAndItsRepresentation.getKey()
            final Object blockRepresentation = blockNameAndItsRepresentation.getValue()
            setProperty(blockName, StatementList.fromJSON(json[blockName]))
        }
    }
    
    EBlockStatementType getRepresentationType() {
        final Object blockRepresentation = getBlockNameAndItsRepresentation().getValue()
        if (blockRepresentation instanceof List) {
            if (!blockRepresentation.isEmpty() && blockRepresentation.get(0) instanceof String) {
                return EBlockStatementType.SYMBOL_NAMES
            } else {
                return EBlockStatementType.STATEMENTS
            }
//        } else if (blkRepresentation instanceof Map) {
//            return EBlockStatementType.NESTED_STATEMENTS
        } else if (blockRepresentation instanceof String) {
            return EBlockStatementType.CONTENT
        }
    }
    
    public Map.Entry<String, Object> getBlockNameAndItsRepresentation() {
        getProperties().find{ k, v -> !k.equals(PROPERTY_SUBTYPE) }
    }
    
    @Override
    public String toMDL() {
        final Map.Entry<String, Object> blockNameAndItsRepresentation = getBlockNameAndItsRepresentation()
        final String blockName = blockNameAndItsRepresentation.getKey()
        final Object blockRepresentation = blockNameAndItsRepresentation.getValue()

        final StringBuffer sb = new StringBuffer()
        
        switch (getRepresentationType()) {
            case EBlockStatementType.CONTENT:
                sb.append(IDT)
                sb.append(blockName)
                sb.append(blockRepresentation)
                break
            case EBlockStatementType.SYMBOL_NAMES:
                sb.append(IDT)
                sb.append(blockName)
                sb.append(" {\n")
                sb.append(blockRepresentation.collect {
                    IDT + IDT + it
                }.join("\n"))
                sb.append("\n${IDT}}")
                break
            case EBlockStatementType.STATEMENTS:
                sb.append(stmtListToMDL())
                break
        }
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
        final Map.Entry<String, Object> blockNameAndItsRepresentation = getBlockNameAndItsRepresentation()
        final String blockName = blockNameAndItsRepresentation.getKey()
        final Object blockRepresentation = blockNameAndItsRepresentation.getValue()
        
        blockRepresentation.splitByBlockAttributesFromIndividualItems().collect { StatementList stmtList ->
            final StringBuffer sb1 = new StringBuffer()
            sb1.append(IDT)
            sb1.append(blockName)
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

    /**
     * Overloaded method to merge two {@link BlockStatement}s' representations.
     * <p>
     * This version merges two {@link StatementList}s of Statements, i.e. for block
     * statement 'type' {@link EBlockStatementType#STATEMENTS}.
     * <p>
     * Used where multiple blocks appear in an MDL file at the top-level.
     * The JSON representation at this level is a Map keyed by block name,
     * hence multiple blocks will overwrite each other in the JSON representation.
     * If there are any block-level attributes then these are assumed to have already been
     * propagated onto the individual variables/items
     * (see {@link StatementList#setBlockAttributesOnIndividualItems(Map)} and related
     * methods) at the point at which this method is called.
     * <p>
     * The method is used by {@link TopLevelBlockStatements} constructors.
     * <p>
     * @param thisStmtList {@link StatementList}
     * @param otherStmtList {@link StatementList}, can be null in which case no modification will be performed
     * @return the modified <code>thisStmtList</code>
     */
    public static StatementList merge(final StatementList thisStmtList, StatementList otherStmtList) {
        if (otherStmtList != null) {
            thisStmtList.addAll(otherStmtList)
        }
        return thisStmtList
    }
    
    /**
     * Overloaded method to merge two {@link BlockStatement}s' representations.
     * <p>
     * This version merges two {@link List}s of String symbol names, i.e. for block
     * statement 'type' {@link EBlockStatementType#SYMBOL_NAMES}.
     * <p>
     * Used where multiple blocks appear in an MDL file at the top-level.
     * The JSON representation at this level is a Map keyed by block name,
     * hence multiple blocks will overwrite each other in the JSON representation.
     * <p>
     * The method is used by {@link TopLevelBlockStatements} constructors.
     * <p>
     * @see {@link #merge(StatementList, StatementList)}
     * <p>
     * @param thisSymbolList {@link List} of {@link String}s
     * @param otherSymbolList {@link List} of {@link String}s, can be null in which case no modification will be performed
     * @return the modified <code>thisSymbolList</code>
     */
    public static List<String> merge(final List<String> thisSymbolList, List<String> otherSymbolList) {
        if (otherSymbolList != null) {
            thisSymbolList.addAll(otherSymbolList)
        }
        return thisSymbolList
    }
    
    /**
     * Overloaded method to merge two {@link BlockStatement}s' representations.
     * <p>
     * This version merges two {@link String}s of content, i.e. for block
     * statement 'type' {@link EBlockStatementType#CONTENT}.
     * <p>
     * Used where multiple blocks appear in an MDL file at the top-level.
     * The JSON representation at this level is a Map keyed by block name,
     * hence multiple blocks will overwrite each other in the JSON representation.
     * <p>
     * The method is used by {@link TopLevelBlockStatements} constructors.
     * <p>
     * @see {@link #merge(StatementList, StatementList)}
     * <p>
     * @param thisContent {@link String}
     * @param otherContent {@link String}, can be null in which case no modification will be performed
     * @return the modified <code>thisContent</code>
     */
    public static String merge(final String thisContent, String otherContent) {
        if (otherContent != null) {
            return thisContent.concat(otherContent)
        }
        return thisContent
    }
    
    /**
     * Represents the three types of representation of a Block Statement.
     */
    static enum EBlockStatementType {
        SYMBOL_NAMES,
        STATEMENTS,
        CONTENT
    }
    
}
