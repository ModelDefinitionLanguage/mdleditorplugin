/*******************************************************************************
 * Copyright (C) 2015 Mango Solutions Ltd - All rights reserved.
 ******************************************************************************/
package eu.ddmore.converter.mdl2json.domain

import com.google.common.base.Preconditions

import eu.ddmore.converter.mdl2json.domain.BlockStatement.EBlockStatementType
import eu.ddmore.mdl.mdl.BlockArgument
import eu.ddmore.mdl.mdl.ValuePair
import eu.ddmore.mdl.utils.MdlExpressionConverter


/**
 * Creates instances of subclasses of {@link BlockStatement}.
 */
public class BlockStatementFactory {

    public static BlockStatement fromMDL(final eu.ddmore.mdl.mdl.BlockStatement blkStatement) {
        final String blockName = blkStatement.getIdentifier()
        final eu.ddmore.mdl.mdl.BlockBody blkBody = blkStatement.getBody()
        final Map<String, String> blkAttrsMap = getBlockArgumentsAsMap(blkStatement)
        
        switch (getRepresentationType(blockName)) {
            case EBlockStatementType.SYMBOL_NAMES:
                return new SymbolListBlockStatement(blockName, blkBody)
            case EBlockStatementType.STATEMENTS:
                return new StatementListBlockStatement(blockName, blkBody, blkAttrsMap)
            case EBlockStatementType.CONTENT:
                return new TextualContentBlockStatement(blockName, blkBody)
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
    
    public static BlockStatement fromJSON(final Map<String, Object> json) {
        json.remove(BlockStatement.PROPERTY_SUBTYPE) // Its work is done
        Preconditions.checkState(json.size() == 1, "JSON representation of BlockStatement encountered that does not have exactly one Entry in its Map (excluding the subtype property)")
        final String blockName = json.keySet().first() // Single Entry in the Map
                
        switch (getRepresentationType(blockName)) {
            case EBlockStatementType.SYMBOL_NAMES:
                if (json[blockName] instanceof String) {
                    // This is to cater for the fact that R cannot distinguish between a string and a character vector
                    // of length 1, hence a variable-name list (vector when read in to R) containing one variable gets
                    // translated to JSON as a string rather than a list of strings containing one element
                    return new SymbolListBlockStatement(blockName, [json[blockName]])
                }
                return new SymbolListBlockStatement(blockName, ((List<String>) json[blockName]))
            case EBlockStatementType.STATEMENTS:
                return new StatementListBlockStatement(blockName, ((List<Map>) json[blockName]))
            case EBlockStatementType.CONTENT:
                return new TextualContentBlockStatement(blockName, (String) json[blockName])
        }
    }
    
    /**
     * @param blockName - String name of the block to determine block representation type
     * @return the {@link EBlockStatementType} indicated by the specified block name
     */
    private static EBlockStatementType getRepresentationType(final String blockName) {
        switch (blockName) {
            case "IDV":
            case "STRUCTURAL_PARAMETERS":
            case "VARIABILITY_PARAMETERS":
                return EBlockStatementType.SYMBOL_NAMES
                break
            default:
                return EBlockStatementType.STATEMENTS
            break
        }
    }
    
}
