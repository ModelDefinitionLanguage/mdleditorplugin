package eu.ddmore.converter.mdl2json.domain;

import org.ddmore.mdl.mdl.Argument
import org.ddmore.mdl.mdl.DataBlock
import org.ddmore.mdl.mdl.ModelBlock
import org.ddmore.mdl.mdl.PropertyDeclaration
import org.ddmore.mdl.mdl.SymbolName
import org.ddmore.mdl.mdl.TargetBlock
import org.ddmore.mdl.mdl.TaskObject
import org.ddmore.mdl.mdl.TaskObjectBlock

import eu.ddmore.converter.mdl2json.interfaces.MDLAsJSON;
import eu.ddmore.converter.mdl2json.interfaces.MDLPrintable;
import eu.ddmore.converter.mdl2json.utils.XtextWrapper
import eu.ddmore.converter.mdlprinting.MdlPrinter


public class Task extends Expando implements MDLPrintable, MDLAsJSON {
	
	static final String IDENTIFIER = "taskobj"
	
	static final String DATA = "DATA"
	static final String ESTIMATE = "ESTIMATE"
	static final String EVALUATE = "EVALUATE"
	static final String MODEL = "MODEL"
	static final String OPTIMISE = "OPTIMISE"
	static final String SIMULATE = "SIMULATE"
	static final String TARGET_BLOCK = "TARGET_BLOCK"
	
	private static MdlPrinter mdlPrinter = MdlPrinter.getInstance()
	
	/**
	 * Create a Task from a TaskObject
	 * @param taskObj
	 */
	public Task(TaskObject taskObj) {
		setProperty(IDENTIFIER_PROPNAME, taskObj.getIdentifier())

		for (TaskObjectBlock block : taskObj.blocks) {
		
    		if (block.getEstimateBlock()) {
				setProperty(ESTIMATE, printIdentifiedBlock(block.getEstimateBlock(), newStatementPrinter))
    		}
    		if (block.getSimulateBlock()) {
				setProperty(SIMULATE, printIdentifiedBlock(block.getSimulateBlock(), newStatementPrinter))
    		}
    		if (block.getEvaluateBlock()) {
				setProperty(EVALUATE, printIdentifiedBlock(block.getEvaluateBlock(), newStatementPrinter))
    		}
    		if (block.getOptimiseBlock()) {
				setProperty(OPTIMISE, printIdentifiedBlock(block.getOptimiseBlock(), newStatementPrinter))
    		}
    		if (block.getDataBlock()) {
				setProperty(DATA, printIdentifiedBlock(block.getDataBlock(), newStatementPrinter))
    		}
    		if (block.getModelBlock()) {
				setProperty(MODEL, printIdentifiedBlock(block.getModelBlock(), newStatementPrinter))
    		}
    		if (block.getTargetBlock()) {
				setProperty(TARGET_BLOCK, printIdentifiedBlock(block.getTargetBlock(), newStatementPrinter))
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
	def newStatementPrinter = { statementHolder ->
		statementHolder.statements.collect{
			it.getPropertyName().getName() + "=" + XtextWrapper.unwrap(it.getExpression())
		}.join("\n")
	}
	
	/**
	 * Print a block with an identifier that contains an IF expression
	 *  
	 * @param expressionHolder. Must have an attribute "expression"
	 * 
	 * @return
	 */
	String printIfExpressionHolder(expressionHolder) {
		StringBuffer b = new StringBuffer()
		b.append(expressionHolder.getIdentifier()).append("=if(")
		b.append(XtextWrapper.unwrap(expressionHolder.getExpression()))
		b.append(")")
		b.toString()
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
""" // TODO: Target Block
	}

}
