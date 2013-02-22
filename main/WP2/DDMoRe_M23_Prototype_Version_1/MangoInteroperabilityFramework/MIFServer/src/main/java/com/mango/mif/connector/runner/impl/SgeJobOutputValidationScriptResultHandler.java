/**
 * Copyright Mango Solutions, 2012.
 * <p>SVN Entry: $HeadURL: $
 * <p>SVN ID: $Id: $
 * <p>Last edited by : $Author$
 * @author Tony Bamford &lt;tbamford@mango-solutions.com&gt;
 * @version $Revision$ as of on $Date$
 */
package com.mango.mif.connector.runner.impl;

import com.google.common.base.Preconditions;
import com.mango.mif.connector.domain.Job;
import com.mango.mif.core.exec.ExecutionException;
import com.mango.mif.core.exec.InvokerResult;
import com.mango.mif.core.exec.ValidationFailedException;
import com.mango.mif.core.exec.invoker.InvokerHelper;
import com.mango.mif.core.exec.template.JobAwareFreemarkerTemplateCommandBuilder;

/**
 * Validate SGE Job output.
 */
public class SgeJobOutputValidationScriptResultHandler extends ShellCommandInvokerResultHandler implements JobProcessingAwareInvokerResultHandler {
    /**
     * A job
     */
    protected Job job;
    /**
     * state exit event
     */
    protected String stateExitEvent;
    /**
     * A command builder template that will give us all of the jobs output.
     */
    protected JobAwareFreemarkerTemplateCommandBuilder jobOutputCommandBuilder;

    /**
     * Constructor
     * @param exitEvent
     */
    public SgeJobOutputValidationScriptResultHandler(String stateExitEvent) {
        this.stateExitEvent = stateExitEvent;
    }

    @Override
    public void setJob(Job job) {
        this.job = job;
    }

    @Override
    public String getStateExitEvent() {
        return stateExitEvent;
    }

    @Override
    public void handle(InvokerResult invokerResult) throws ExecutionException {
        Preconditions.checkNotNull(job);
        Preconditions.checkNotNull(invokerResult);

        if (InvokerHelper.failed(invokerResult)) {
            LOG.info("The job validation script returned " + invokerResult.getExitCode() + " (INVALID OUTPUT)");
            LOG.info("Output Stream: " + invokerResult.getOutputStream());

            throw new ValidationFailedException("The job validation script exited with " + invokerResult.getExitCode() + ". OUTPUT IS INVALID.",
                    invokerResult.getStdout(),
                    invokerResult.getStderr());
        } else {
            LOG.debug("The job validation script returned \"true\", the output is VALID.");
        }
    }

    public JobAwareFreemarkerTemplateCommandBuilder getJobOutputCommandBuilder() {
        return jobOutputCommandBuilder;
    }

    public void setJobOutputCommandBuilder(JobAwareFreemarkerTemplateCommandBuilder jobOutputCommandBuilder) {
        this.jobOutputCommandBuilder = jobOutputCommandBuilder;
    }

}
