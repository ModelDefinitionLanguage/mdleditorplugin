/* File         :  GenericShellBasedStateHandler.java
 * Project      :  MIFServer
 * Created on   :  29 Jun 2012
 */
package com.mango.mif.connector.runner.impl;

import javax.xml.bind.JAXBException;

import com.mango.mif.connector.runner.status.JobDetailedStatusBuilder;
import com.mango.mif.core.exec.ExecutionException;
import com.mango.mif.core.exec.Invoker;
import com.mango.mif.core.exec.template.JobAwareFreemarkerTemplateCommandBuilder;
import com.mango.mif.domain.DetailedStatus;
import com.mango.mif.exception.MIFException;
import com.mango.mif.utils.MIFProperties;

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
public class BaseGenericShellBasedStateHandler extends DefaultStateHandler {
    /**
     * Command builder
     */
    protected JobAwareFreemarkerTemplateCommandBuilder commandBuilder;
    /**
     * Result handler
     */
    protected JobProcessingAwareInvokerResultHandler invokerResultHandler;
    /**
     * Summary message provider
     */
    protected JobDetailedStatusBuilder detailedStatusBuilder;

    /**
     * @param state
     * @param exitEvent
     */
    public BaseGenericShellBasedStateHandler(String state) {
        super(state);
    }

    /**
     * @param state
     * @param exitEvent
     */
    public BaseGenericShellBasedStateHandler(String state, String exitEvent) {
        super(state, exitEvent);
    }

    /**
     * @param commandBuiler the commandBuiler to set
     */
    public void setCommandBuilder(JobAwareFreemarkerTemplateCommandBuilder commandBuilder) {
        this.commandBuilder = commandBuilder;
    }

    /**
     * @param invokerResultHandler the invokerResultHandler to set
     */
    public void setInvokerResultHandler(JobProcessingAwareInvokerResultHandler invokerResultHandler) {
        this.invokerResultHandler = invokerResultHandler;
    }

    public JobAwareFreemarkerTemplateCommandBuilder getCommandBuilder() {
        return commandBuilder;
    }

    public JobProcessingAwareInvokerResultHandler getInvokerResultHandler() {
        return invokerResultHandler;
    }

    protected Invoker getInvoker() {
        return jobRunner.getInvoker();
    }


    public void setDetailedStatusBuilder(JobDetailedStatusBuilder detailedStatusBuilder) {
        this.detailedStatusBuilder = detailedStatusBuilder;
    }

    public JobDetailedStatusBuilder getDetailedStatusBuilder() {
        return detailedStatusBuilder;
    }

    /**
     * A method that is responsible for handling creation of the state summary message
     * @throws ExecutionException 
     * @throws JAXBException 
     */
    protected void summarizeState() throws JAXBException, ExecutionException {
        if (detailedStatusBuilder != null) {
            detailedStatusBuilder.setJob(getJob());
            DetailedStatus detailedStatus = detailedStatusBuilder.getDetailedStatus();
            jobRunner.setSummaryMessage(detailedStatus.getSummary());
            String detailedStatusMarshalled = detailedStatus.asString();
            LOG.debug("Detailed status generated for job " + getJob().getJobId() + ".\n" + detailedStatusMarshalled);
            getJob().setDetailedStatusMsg(detailedStatusMarshalled);
        } else {
            try {
                if (getJob().getDetailedStatus() == null) {
                    setDefaultSummaryOnTheJob(MIFProperties.getInstance().getProperty("sgeconnector.startupSummaryMessage"));
                }
            } catch (Exception e) {
                LOG.error("summarizeState caught exception while generating the default summary: ", e);
                setDefaultSummaryOnTheJob(MIFProperties.getInstance().getProperty("sgeconnector.unavailableSummaryMessage"));
            }
        }
    }

    @Override
    protected void doPreprocessing() throws StateHandlerException {
        super.doPreprocessing();
		job.setJobRunnerState(JobRunnerState.byStateName(handledState));
		this.job = jobManagementService.saveJob(job);
    }
    
    private void setDefaultSummaryOnTheJob(String summaryMessage) {
        DetailedStatus detailedStatus = new DetailedStatus();
        detailedStatus.setRequestId(getJob().getJobId());
        detailedStatus.setSummary(summaryMessage);
        try {
            String detailedStatusMsg = detailedStatus.asString();
            LOG.debug("Detailed status generated for job " + getJob().getJobId() + ".\n" + detailedStatusMsg);
            getJob().setDetailedStatusMsg(detailedStatusMsg);
        } catch (JAXBException e1) {
            LOG.error("Error when generating default summary", e1);
        }
    }

    @Override
    protected void doPostProcessing() throws StateHandlerException {
        super.doPostProcessing();
        try {
            summarizeState();
        } catch (Exception e) {
            LOG.error("Could not generate detailed status for job " + job.getJobId() + ".", e);
            try {
                if(getJob().getDetailedStatus()==null) {
                    setDefaultSummaryOnTheJob(MIFProperties.getInstance().getProperty("sgeconnector.unavailableSummaryMessage"));
                }
            } catch (MIFException e1) {
                LOG.error("Error retrieving the current detailed status", e1);
                setDefaultSummaryOnTheJob(MIFProperties.getInstance().getProperty("sgeconnector.unavailableSummaryMessage"));
            }
        }
    }

    @Override
    public String complete() {
        job = jobManagementService.saveJob(job);
        return super.complete();
    }
}
