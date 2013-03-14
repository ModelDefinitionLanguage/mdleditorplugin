/* File			:  ShellCommandInvokerResultHandler.java
 * Project		:  MIFServer
 * Created on	:  2 Jul 2012
 */
package com.mango.mif.connector.runner.impl;

import org.apache.commons.lang.StringUtils;
import org.apache.log4j.Logger;

import com.google.common.base.Preconditions;
import com.mango.mif.core.exec.ExecutionException;
import com.mango.mif.core.exec.InvokerResult;
import com.mango.mif.core.exec.InvokerResultHandler;
import com.mango.mif.core.exec.invoker.InvokerHelper;

/**
 * @version $Revision: $ as of $Date: $
 * <p>SVN Entry : $HeadURL: $
 * <p>SVN ID    : $Id: $
 * <p>Last edited by : $Author: $
 */
/**
 * @author mrogalski
 * 
 * Generic result handler implementation that checks the exit code of the invoker result and
 * if it does not represent successful execution it throws an exception with a message
 * from the error stream property
 */
public class ShellCommandInvokerResultHandler implements InvokerResultHandler {
    /**
     * Logger
     */
    protected final static Logger LOG = Logger.getLogger(ShellCommandInvokerResultHandler.class);

    @Override
    public void handle(InvokerResult invokerResult) throws ExecutionException {
        Preconditions.checkNotNull(invokerResult);
        LOG.debug("Command: " + invokerResult.getCommand());
        LOG.debug("Return code: " + invokerResult.getExitCode());
        LOG.debug("Error Stream: " + invokerResult.getErrorStream());
        LOG.debug("Output Stream: " + invokerResult.getOutputStream());
        if (InvokerHelper.failed(invokerResult)) {
            throw new ExecutionException(buildErrorMessage("An external process exited with code " + invokerResult.getExitCode() + ". ", invokerResult));
        }
    }

    /**
     * Builds error message
     * @param message
     * @param invokerResult
     * @return
     */
    protected String buildErrorMessage(String message, InvokerResult invokerResult) {
        StringBuilder sb = new StringBuilder();
        if (StringUtils.isNotBlank(invokerResult.getCommand())) {
            message = "The following command failed with status "
                    + invokerResult.getExitCode()
                    + ": "
                    + invokerResult.getCommand();
        } else {
            sb.append(message);
        }
        sb.append("\n");

        if (StringUtils.isNotBlank(invokerResult.getOutputStream())) {
            sb.append("Output stream\n")
                    .append(invokerResult.getOutputStream())
                    .append("\n");
        } else {
            sb.append("[stdout empty]\n");
        }

        if (StringUtils.isNotBlank(invokerResult.getErrorStream())) {
            sb.append("Error stream\n")
                    .append(invokerResult.getErrorStream())
                    .append("\n");
        } else {
            sb.append("[stderr empty]\n");
        }
        return sb.toString();
    }
}
