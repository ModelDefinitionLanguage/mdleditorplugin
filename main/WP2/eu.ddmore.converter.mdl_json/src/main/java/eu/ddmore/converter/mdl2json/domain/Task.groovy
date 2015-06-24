/*******************************************************************************
 * Copyright (C) 2014-2015 Mango Solutions Ltd - All rights reserved.
 ******************************************************************************/
package eu.ddmore.converter.mdl2json.domain;

import org.ddmore.mdl.mdl.TaskObject
import org.ddmore.mdl.mdl.TaskObjectBlock

import eu.ddmore.converter.mdl2json.interfaces.MDLAsJSON
import eu.ddmore.converter.mdl2json.interfaces.MDLPrintable
import eu.ddmore.converter.mdl2json.interfaces.TopLevelBlock
import eu.ddmore.converter.mdl2json.utils.XtextWrapper


public class Task extends Expando implements MDLPrintable, MDLAsJSON, TopLevelBlock {

    public static final TopLevelBlock.Identifier IDENTIFIER = TopLevelBlock.Identifier.taskobj

    public static final String ESTIMATE = "ESTIMATE"
    public static final String SIMULATE = "SIMULATE"

    /**
     * Create a Task from a TaskObject
     * @param taskObj
     */
    public Task(TaskObject taskObj) {
        setProperty(IDENTIFIER_PROPNAME, IDENTIFIER)

        for (TaskObjectBlock block : taskObj.blocks) {

            if (block.getEstimateBlock()) {
                setProperty(ESTIMATE, statementPrinter(block.getEstimateBlock()))
            }
            if (block.getSimulateBlock()) {
                setProperty(SIMULATE, statementPrinter(block.getSimulateBlock()))
            }
        }
    }

    /**
     * Create a Task from a JSON object.
     * 
     * @param json
     */
    public Task(Map json) {

        json.each { k, v ->
            setProperty(k, v)
        }
        
        // Set the identifier property after the setting of the other properties
        // because "identifier" itself is one of those properties and we want to
        // set it to the appropriate TopLevelBlock.Identifier enum value rather
        // than its string value in the JSON.
        // This way round, it doesn't get overwritten.
        setProperty(IDENTIFIER_PROPNAME, IDENTIFIER)
    }

    /**
     * Prints each statement held in this list of statements.
     */
    def statementPrinter = { statementHolder ->
        statementHolder.statements.collect{
            it.getPropertyName().getName() + "=" + XtextWrapper.unwrap(it.getExpression())
        }.join("\n")
    }
    
    /**
     * {@inheritDoc}
     */
    public TopLevelBlock.Identifier getIdentifier() {
        return IDENTIFIER
    }

    /**
     * {@inheritDoc}
     */
    public String toMDL() {

        final StringBuffer mdl = new StringBuffer();
        getProperties().minus([(IDENTIFIER_PROPNAME):(IDENTIFIER)]).each { blockName, content ->
            content = content.split("\n").join("\n${IDT*2}")
            mdl.append("\n${IDT}${blockName} {\n${IDT*2}${content}\n${IDT}}\n")
        }
        return """${IDENTIFIER} {
${mdl.toString()}
}
"""
    }

}