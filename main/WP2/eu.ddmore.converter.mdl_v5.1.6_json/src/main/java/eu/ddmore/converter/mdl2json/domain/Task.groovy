package eu.ddmore.converter.mdl2json.domain;

import org.ddmore.mdl.mdl.Argument
import org.ddmore.mdl.mdl.DataBlock
import org.ddmore.mdl.mdl.DataBlockStatement
import org.ddmore.mdl.mdl.ModelBlock
import org.ddmore.mdl.mdl.ModelBlockStatement
import org.ddmore.mdl.mdl.ParameterBlock
import org.ddmore.mdl.mdl.SymbolList
import org.ddmore.mdl.mdl.SymbolName
import org.ddmore.mdl.mdl.TargetBlock
import org.ddmore.mdl.mdl.TaskFunctionBlock
import org.ddmore.mdl.mdl.TaskFunctionDeclaration
import org.ddmore.mdl.mdl.TaskObject
import org.ddmore.mdl.mdl.TaskObjectBlock

import eu.ddmore.converter.mdl2json.domain.task.TaskSymbolDeclaration
import eu.ddmore.converter.mdl2json.utils.XtextWrapper
import eu.ddmore.converter.mdlprinting.MdlPrinter


public class Task extends Expando implements MDLPrintable, MDLAsJSON {
	
	static final String IDENTIFIER = "taskobj"
	
	private static MdlPrinter mdlPrinter = MdlPrinter.getInstance()
	
	/**
	 * Create a Task from a TaskObject
	 * @param taskObj
	 */
	public Task(TaskObject taskObj) {
		setProperty(IDENTIFIER_PROPNAME, taskObj.getIdentifier())
		StringBuffer buff = new StringBuffer()
		for(TaskObjectBlock block : taskObj.blocks ) {
			if(block.getDataBlock()) {
				buff.append(printIdentifiedBlock(block.getDataBlock(), dataBlockPrinter ))
			} else if(block.getFunctionDeclaration()) {
				buff.append(printFunctionDeclaration(block.getFunctionDeclaration()))			
			} else if(block.getModelBlock()) {
				buff.append(printIdentifiedBlock(block.getModelBlock(), modelBlockPrinter ))
			} else if (block.getParameterBlock()) {
				buff.append(printIdentifiedBlock(block.getParameterBlock(), parameterBlockPrinter ))
			} else if(block.getTargetBlock()) {
				buff.append(printIdentifiedBlock(block.getTargetBlock(), targetCodeBlockPrinter))
			} 
		}
		setProperty(CONTENT_PROPNAME, buff.toString())
	}
		
	/**
	 * Create a Task from a JSON object
	 * 
	 * @param json
	 */
	public Task(Object json) {
		setProperty(IDENTIFIER_PROPNAME, IDENTIFIER)
		setProperty(CONTENT_PROPNAME, json[CONTENT_PROPNAME])
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
		StringBuffer buff = new StringBuffer("\n")
		buff.append(identifiedBlock.getIdentifier())
		if(identifiedBlock.metaClass.hasProperty(identifiedBlock, "arguments")) {
			buff.append("(")
			buff.append(identifiedBlock.arguments.arguments.collect { Argument arg -> "${arg.argumentName.name}=${XtextWrapper.unwrap(arg.expression)}" }.join(","))
			buff.append(")")
		}
		buff.append("{")
		buff.append(blockPrinter(identifiedBlock))
		buff.append("}\n")
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
	 * Prints a function declaration. Joins the function name, with the arguments, and then the body.
	 * 
	 * @param fd
	 * @return
	 */
	String printFunctionDeclaration(TaskFunctionDeclaration fd) {
		StringBuffer functionBody = new StringBuffer()
		if(fd.functionBody) {
			for(TaskFunctionBlock tfb : fd.functionBody.getBlocks() ) {
				if(tfb.getEstimateBlock()) functionBody.append(printIdentifiedBlock(tfb.getEstimateBlock(), statementPrinter ))
				if(tfb.getExecuteBlock()) functionBody.append(printIdentifiedBlock(tfb.getExecuteBlock(), statementPrinter ))
				if(tfb.getSimulateBlock()) functionBody.append(printIdentifiedBlock(tfb.getSimulateBlock(), statementPrinter ))
			}
		}
		String functionSig = (fd.formalArguments?"=function(${fd.formalArguments.getArguments().collect{it.name}.join(",")})":"")
		"""
${fd.functionName.name}$functionSig {
${functionBody}
}
"""
	}
	
	/**
	 * Print this symbol list in the format list(symbolName1, symbolName2,...)
	 * 
	 * @param the list of Symbols
	 * @return
	 */
	String printSymbolList(SymbolList sl) {
		StringBuffer b = new StringBuffer()
		b.append(sl.getIdentifier()).append("=list(")
		b.append(sl.getSymbols().collect{ SymbolName sn -> sn.getName() }.join(","))
		b.append(")")
		b.toString()
	}

	/**
	 * Prints each statement held in this list of statements 
	 */
	def statementPrinter = { statementHolder ->
		"\n" + statementHolder.statements.collect{
			
			// Check each of the four possible components of a BlockStatement (the class of variable "it")
			if (it.targetBlock) {
				printIdentifiedBlock(it.targetBlock, targetCodeBlockPrinter)
			} else if (it.functionCall) {
				mdlPrinter.print(it.functionCall)
			} else if (it.statement) { // ConditionalStatement
				mdlPrinter.print(it.statement)
			} else if (it.symbol) {
				new TaskSymbolDeclaration(it.symbol).toMDL()
			}
			
		}.join("")
	}
	
	/**
	 * Print the contents of a DataBlock
	 */
	def dataBlockPrinter = { DataBlock db ->
		StringBuffer buff = new StringBuffer("\n")
		db.getStatements().each { statement ->
			if(statement.getAcceptList()) buff.append(printIfExpressionHolder(statement.getAcceptList()))
			if(statement.getDropList()) buff.append(printSymbolList(statement.getDropList()))
			if(statement.getIgnoreList()) buff.append(printIfExpressionHolder(statement.getIgnoreList()))
			buff.append("\n")
		}
		buff.toString()
	}
	
	/**
	 * Print the contents of a ModelBlock
	 */
	def modelBlockPrinter = { ModelBlock mb ->
		StringBuffer buff = new StringBuffer("\n")
		mb.getStatements().each { ModelBlockStatement mbs ->
			if(mbs.getAddList()) buff.append(printSymbolList(mbs.getAddList().getList()) + "\n")
			if(mbs.getRemoveList()) buff.append(printSymbolList(mbs.getRemoveList().getList()) + "\n")
			if(mbs.getStatement()) buff.append(mdlPrinter.print(mbs.getStatement()))
		}
		buff.toString()
	}
	
	def parameterBlockPrinter = { ParameterBlock pb ->
		
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
		return """ $IDENTIFIER {
${IDT}${getProperty(CONTENT_PROPNAME)}
}
"""
	}

}
