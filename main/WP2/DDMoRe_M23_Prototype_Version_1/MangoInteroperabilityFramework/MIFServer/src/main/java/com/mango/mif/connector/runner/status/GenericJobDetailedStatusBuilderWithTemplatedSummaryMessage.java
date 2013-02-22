/* File			:  GenericJobDetailedStatusBuilderWithTemplatedSummaryMessage.java
 * Project		:  MIFServer
 * Created on	:  Aug 13, 2012
 */
package com.mango.mif.connector.runner.status;

import com.mango.mif.connector.domain.Job;
import com.mango.mif.core.exec.ExecutionException;
import com.mango.mif.core.exec.Invoker;
import com.mango.mif.core.exec.template.JobAwareFreemarkerTemplateCommandBuilder;
import com.mango.mif.domain.DetailedStatus;

/**
 * @version $Revision: $ as of $Date: $
 * <p>SVN Entry : $HeadURL: $
 * <p>SVN ID    : $Id: $
 * <p>Last edited by : $Author: $
 * 
 * Freemarker template based summary message builder
 */
public class GenericJobDetailedStatusBuilderWithTemplatedSummaryMessage extends GenericJobDetailedStatusBuilder {

    /**
     * Text builder, which gathers the information we want into a pretty string
     */
    protected JobAwareFreemarkerTemplateCommandBuilder summaryMessageBuilder;

    /**
     * A handy invoker for invoking command execution;
     */
    private Invoker invoker;

    @Override
    public void setJob(Job job) throws ExecutionException {
        super.setJob(job);
        invoker = job.getInvoker();
        summaryMessageBuilder.setJob(job);
    }

    @Override
    public DetailedStatus getDetailedStatus() throws ExecutionException {
        DetailedStatus status = super.getDetailedStatus();
        status.setSummary(summaryMessageBuilder.getCommand());
        return status;
    }

    public void setSummaryMessageBuilder(JobAwareFreemarkerTemplateCommandBuilder summaryMessageBuilder) {
        this.summaryMessageBuilder = summaryMessageBuilder;
    }

    public Invoker getInvoker() {
        return invoker;
    }
}
