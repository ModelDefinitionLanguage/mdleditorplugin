/* File			:  QacctCommandResultHandler.java
 * Project		:  MIFServer
 * Created on	:  Jul 24, 2012
 */
package com.mango.mif.sgeconnector;

import com.mango.mif.core.exec.ExecutionException;
import com.mango.mif.core.exec.InvokerResult;
import com.mango.mif.core.exec.invoker.InvokerHelper;
import com.mango.mif.sge.SGEOutput;

/**
 * @version $Revision: $ as of $Date: $
 * <p>SVN Entry : $HeadURL: $
 * <p>SVN ID    : $Id: $
 * <p>Last edited by : $Author: $
 * 
 * A Qacct command results handler
 */
public class FlowControlQacctCommandResultHandler extends SGECommandResultHandler {
    /**
     * An event being returned if qacct results fails (i.e. a job is not found on a finished queue)
     */
    private String failureEvent;
    /**
     * success event
     */
    private final String successEvent;
    /**
     * Number of milliseconds to wait before re-running poll command
     */
    private Long pollingRate = 5000l;

    /**
     * Constructor
     * @param successEvent
     */
    public FlowControlQacctCommandResultHandler(String successEvent, String failureEvent) {
        super(successEvent);
        this.successEvent = successEvent;
        this.failureEvent = failureEvent;
    }

    @Override
    public void handle(InvokerResult invokerResult) throws ExecutionException {
        try {
            super.handle(invokerResult);
        } catch (ExecutionException e) {
            //we just invoke the parent to check preconditions
            //the ExecutionException here is normal when a job has just finished but has not yet been moved to the finished queue on SGE
        }
        SGEOutput output = null;
        if (InvokerHelper.success(invokerResult)) {
            output = outputParser.parseAccountingMessage(invokerResult.getOutputStream());
        } else {
            output = outputParser.parseAccountingMessage(invokerResult.getErrorStream());
        }
        if (SGEOutput.JOB_NOT_FOUND.equals(output.get(SGEOutput.SGE_COMMAND_RESULT_KEY))) {
            this.stateExitEvent = failureEvent;
            try {
                Thread.sleep(pollingRate);
            } catch (InterruptedException e) {
                LOG.error(e);
            }
        } else {
            this.stateExitEvent = successEvent;
        }
    }

    public void setPollingRate(Long pollingRate) {
        this.pollingRate = pollingRate;
    }
}
