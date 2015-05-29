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
import eu.ddmore.converter.mdlprinting.MdlPrinter


public class Task extends Expando implements MDLPrintable, MDLAsJSON, TopLevelBlock {

    public static final String IDENTIFIER = "taskobj"

    public static final String ESTIMATE = "ESTIMATE"
    public static final String SIMULATE = "SIMULATE"

    /**
     * Create a Task from a TaskObject
     * @param taskObj
     */
    public Task(TaskObject taskObj) {
        setProperty(IDENTIFIER_PROPNAME, taskObj.getIdentifier())

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
        setProperty(IDENTIFIER_PROPNAME, IDENTIFIER)

        json.each { k, v ->
            setProperty(k, v)
        }
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

    @Override
    public int getPrintedOrder() {
        return 4;
    }
}