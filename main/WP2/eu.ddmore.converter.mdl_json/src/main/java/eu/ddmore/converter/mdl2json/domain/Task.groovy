/*******************************************************************************
 * Copyright (C) 2014-5 Mango Solutions Ltd - All rights reserved.
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

    private static MdlPrinter mdlPrinter = MdlPrinter.getInstance()

    /**
     * Create a Task from a TaskObject
     * @param taskObj
     */
    public Task(TaskObject taskObj) {
        setProperty(IDENTIFIER_PROPNAME, taskObj.getIdentifier())

        for (TaskObjectBlock block : taskObj.blocks) {

            if (block.getEstimateBlock()) {
                setProperty(ESTIMATE, printIdentifiedBlock(block.getEstimateBlock(), statementPrinter))
            }
            if (block.getSimulateBlock()) {
                setProperty(SIMULATE, printIdentifiedBlock(block.getSimulateBlock(), statementPrinter))
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
     * Print out the block that has an identifier. Prints out the identifer, possibly any arguments to this identified block,
     * and then delegates printing of the contents of the block to the supplied blockPrinter
     * 
     * @param identifiedBlock The block that has an identifier
     * @param blockPrinter The closure that will print the block contents
     * @return
     */
    String printIdentifiedBlock(identifiedBlock, blockPrinter) {
//		StringBuffer buff = new StringBuffer("\n")
//		buff.append(identifiedBlock.getIdentifier())
//		if(identifiedBlock.metaClass.hasProperty(identifiedBlock, "arguments")) {
//			buff.append("(")
//			buff.append(identifiedBlock.arguments.arguments.collect { Argument arg -> "${arg.argumentName.name}=${XtextWrapper.unwrap(arg.expression)}" }.join(","))
//			buff.append(")")
//		}
//		buff.append("{")
//		buff.append(blockPrinter(identifiedBlock))
//		buff.append("}\n")

        blockPrinter(identifiedBlock)
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
     * Prints this object back out to MDL
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