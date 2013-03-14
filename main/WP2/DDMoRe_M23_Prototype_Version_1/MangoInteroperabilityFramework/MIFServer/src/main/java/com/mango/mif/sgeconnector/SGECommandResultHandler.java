/* File			:  SGECommandResultHandler.java
 * Project		:  MIFServer
 * Created on	:  Jul 24, 2012
 */
package com.mango.mif.sgeconnector;

/**
 * super class for SGE Command result handlers
 * 
 * @version $Revision: $ as of $Date: $
 * <p>SVN Entry : $HeadURL: $
 * <p>SVN ID    : $Id: $
 * <p>Last edited by : $Author: $
 */
import com.google.common.base.Preconditions;
import com.mango.mif.connector.runner.impl.GenericShellCommandInvokerResultHandler;
import com.mango.mif.core.exec.ExecutionException;
import com.mango.mif.core.exec.InvokerResult;
import com.mango.mif.sge.SGEOutputParser;

public class SGECommandResultHandler extends
		GenericShellCommandInvokerResultHandler {

	/**
	 * Output parser
	 */
	protected SGEOutputParser outputParser;
	/**
	 * Constructor
	 * @param stateExitEvent
	 */
	public SGECommandResultHandler(String stateExitEvent) {
		super(stateExitEvent);
	}

	/**
	 * @param outputParser the outputParser to set
	 */
	public void setOutputParser(SGEOutputParser outputParser) {
		this.outputParser = outputParser;
	}
	@Override
	public void handle(InvokerResult invokerResult) throws ExecutionException {
		Preconditions.checkNotNull(invokerResult);
		super.handle(invokerResult);
	}
}