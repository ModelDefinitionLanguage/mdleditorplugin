/* File			:  SubmitResultHandler.java
 * Project		:  MIFServer
 * Created on	:  2 Jul 2012
 */
package com.mango.mif.sgeconnector;

import org.apache.cxf.common.util.StringUtils;

import com.mango.mif.core.exec.ExecutionException;
import com.mango.mif.core.exec.InvokerResult;
import com.mango.mif.sge.SGE;
import com.mango.mif.sge.SGEOutput;

/**
 * @version $Revision: $ as of $Date: $
 * <p>SVN Entry : $HeadURL: $
 * <p>SVN ID    : $Id: $
 * <p>Last edited by : $Author: $
 */
/**
 * @author mrogalski
 * 
 * A handler that is responsible for handling results of qsub command execution
 *
 */
public class SubmitCommandResultHandler extends SGECommandResultHandler {
	/**
	 * 
	 * @param successEvent
	 * @param failureEvent
	 */
	public SubmitCommandResultHandler(String successEvent) {
		super(successEvent);
	}
	
	@Override
	public void handle(InvokerResult invokerResult) throws ExecutionException {
		super.handle(invokerResult);
		SGEOutput output = outputParser.parseSubmitMessage(invokerResult.getOutputStream());
		String gridJobId = output.get(SGEOutput.JOB_ID_KEY);
		if (gridJobId == null || StringUtils.isEmpty(gridJobId)) {
			throw new ExecutionException(buildErrorMessage("SGE job id not found in the output.",invokerResult));
		}
		job.addToDataMap(SGE.SGE_JOB_ID_KEY, gridJobId);
	}

}
