/* File			:  DetailedStatusBuilderWithCommandBuilder.java
 * Project		:  MIFServer
 * Created on	:  Sep 6, 2012
 */
package com.mango.mif.psnsgeconnector;

/**
 * @version $Revision: $ as of $Date: $
/**
 * @version $Revision: $ as of $Date: $
 * <p>SVN Entry : $HeadURL: $
 * <p>SVN ID    : $Id: $
 * <p>Last edited by : $Author: $
 * 
 * An abstract implementation of a DetailedStatus builder with command builder
 */
import com.mango.mif.connector.domain.Job;
import com.mango.mif.connector.runner.status.JobDetailedStatusBuilder;
import com.mango.mif.core.exec.ExecutionException;
import com.mango.mif.core.exec.template.JobAwareFreemarkerTemplateCommandBuilder;

public abstract class DetailedStatusBuilderWithCommandBuilder implements JobDetailedStatusBuilder  {

    protected JobAwareFreemarkerTemplateCommandBuilder summaryCommandBuilder;
    protected Job job;

    public DetailedStatusBuilderWithCommandBuilder() {
        super();
    }

    @Override
    public void setJob(Job job) throws ExecutionException {
        this.job = job;
        summaryCommandBuilder.setJob(job);
    }

    public JobAwareFreemarkerTemplateCommandBuilder getSummaryCommandBuilder() {
        return summaryCommandBuilder;
    }

    public void setSummaryCommandBuilder(JobAwareFreemarkerTemplateCommandBuilder summaryCommandBuilder) {
        this.summaryCommandBuilder = summaryCommandBuilder;
    }

}