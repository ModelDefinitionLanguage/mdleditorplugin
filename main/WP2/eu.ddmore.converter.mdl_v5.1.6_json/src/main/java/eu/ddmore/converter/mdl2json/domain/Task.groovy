package eu.ddmore.converter.mdl2json.domain;

import org.ddmore.mdl.mdl.TargetBlock
import org.ddmore.mdl.mdl.TaskObject
import org.ddmore.mdl.mdl.TaskObjectBlock

import eu.ddmore.converter.mdl2json.interfaces.MDLAsJSON
import eu.ddmore.converter.mdl2json.interfaces.MDLPrintable
import eu.ddmore.converter.mdl2json.utils.XtextWrapper
import eu.ddmore.converter.mdlprinting.MdlPrinter


public class Task extends Expando implements MDLPrintable, MDLAsJSON {
	
	static final String IDENTIFIER = "taskobj"
	
	static final String ESTIMATE = "ESTIMATE"
	static final String SIMULATE = "SIMULATE"
	static final String EVALUATE = "EVALUATE"
	static final String OPTIMISE = "OPTIMISE"
	static final String DATA = "DATA"
	static final String MODEL = "MODEL"
	static final String TARGET_CODE = "TARGET_CODE"
	
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
    		if (block.getEvaluateBlock()) {
				setProperty(EVALUATE, printIdentifiedBlock(block.getEvaluateBlock(), statementPrinter))
    		}
    		if (block.getOptimiseBlock()) {
				setProperty(OPTIMISE, printIdentifiedBlock(block.getOptimiseBlock(), statementPrinter))
    		}
    		if (block.getDataBlock()) {
				setProperty(DATA, printIdentifiedBlock(block.getDataBlock(), statementPrinter))
    		}
    		if (block.getModelBlock()) {
				setProperty(MODEL, printIdentifiedBlock(block.getModelBlock(), statementPrinter))
    		}
    		if (block.getTargetBlock()) {
				setProperty(TARGET_CODE, printIdentifiedBlock(block.getTargetBlock(), statementPrinter))
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
	 * Prints a target code block, wrapped in the MDL comment character	
	 */
	def targetCodeBlockPrinter = { TargetBlock tb ->
		new StringBuffer().append("***").append(mdlPrinter.print(tb)).append("***").toString()
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

}
