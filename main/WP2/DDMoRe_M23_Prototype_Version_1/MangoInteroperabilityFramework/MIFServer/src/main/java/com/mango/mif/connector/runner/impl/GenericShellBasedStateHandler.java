/* File			:  GenericShellBasedStateHandler.java
 * Project		:  MIFServer
 * Created on	:  29 Jun 2012
 */
package com.mango.mif.connector.runner.impl;

import com.google.common.base.Preconditions;
import com.mango.mif.core.exec.ExecutionException;
import com.mango.mif.core.exec.InvokerResult;
import com.mango.mif.core.exec.ValidationFailedException;

/**
 * @version $Revision: $ as of $Date: $
 * <p>SVN Entry : $HeadURL: $
 * <p>SVN ID    : $Id: $
 * <p>Last edited by : $Author: $
 */
/**
 * @author mrogalski
 * Generic shell based command builder that uses replaceable invoker to execute commands built with the command builder.
 * It also uses a replaceable results handler, to parse the outputs and reason on the next steps of the execution
 * 
 */
public class GenericShellBasedStateHandler extends BaseGenericShellBasedStateHandler {
    /**
     * @param state
     * @param exitEvent
     */
    public GenericShellBasedStateHandler(String state, String exitEvent) {
        super(state, exitEvent);
    }

    /**
     * @param state
     */
    public GenericShellBasedStateHandler(String state) {
        super(state);
    }

    @Override
    protected void doProcessing() throws StateHandlerException {
        Preconditions.checkNotNull(commandBuilder, "Command builder not set");
        Preconditions.checkNotNull(jobRunner.getInvoker(), "Invoker not set");
        Preconditions.checkNotNull(invokerResultHandler, "Invoker result handler not set");

        String command = null;
        try {
            commandBuilder.setJob(job);
            command = commandBuilder.getCommand();
        } catch (ExecutionException e) {
            throw new StateHandlerException("Could not generate command.", e);
        }

        if (command == null || command.length() == 0) {
            throw new StateHandlerException("Empty command scheduled for execution.");
        }

        LOG.debug("Command that will be executed:" + command);
        InvokerResult result = null;
        try {
            result = jobRunner.getInvoker().execute(command);
        } catch (ExecutionException e) {
            LOG.error("Execution of command threw exception: " + command);
            throw new StateHandlerException("Could not execute command.", e);
        }
        invokerResultHandler.setJob(job);

        try {
            invokerResultHandler.handle(result);
        } catch (ValidationFailedException efe) {
            throw new StateHandlerValidationException("Output validation failed.  Are there errors in the submitted script?",
                    efe.getStdout(),
                    efe.getStderr());
        } catch (ExecutionException e) {
            LOG.error("Invoker result handler threw exception while handling results for " + command);
            throw new StateHandlerException("Error when parsing command results for template "
                    + commandBuilder.getPath()
                    + ":\n",
                    e);
        }

    }

    @Override
    public String complete() {
        LOG.debug("Exit event: " + invokerResultHandler.getStateExitEvent());
        job = jobManagementService.saveJob(job);
        return invokerResultHandler.getStateExitEvent();
    }
}
