/* File			:  PsNBootstrapRunningDetailedStatusBuilder.java
 * Project		:  MIFServer
 * Created on	:  Aug 13, 2012
 */
package com.mango.mif.psnsgeconnector;

import java.util.Date;
import java.util.Map;
import java.util.Map.Entry;

import javax.annotation.Nullable;

import org.apache.commons.lang.StringUtils;
import org.apache.log4j.Logger;

import com.google.common.base.Preconditions;
import com.google.common.base.Predicate;
import com.google.common.collect.Lists;
import com.google.common.collect.Maps;
import com.mango.mif.core.exec.ExecutionException;
import com.mango.mif.core.exec.InvokerResult;
import com.mango.mif.core.exec.invoker.InvokerHelper;
import com.mango.mif.core.exec.template.JobAwareFreemarkerTemplateCommandBuilder;
import com.mango.mif.domain.nonmemsgeconnector.NONMEMProcessingDetailedStatus;
import com.mango.mif.domain.nonmemsgeconnector.NONMEMProcessingJobStatus;
import com.mango.mif.domain.psnsgeconnector.PsNBootstrapProcessingDetailedStatus;
import com.mango.mif.nonmemsgeconnector.NONMEMProcessingDetailedStatusMarshaller;


/**
 * @version $Revision: $ as of $Date: $
 * <p>SVN Entry : $HeadURL: $
 * <p>SVN ID    : $Id: $
 * <p>Last edited by : $Author: $
 * 
 * The class is responsible for generating summary messages during PsN Bootstrap execution
 * 
 */
public class PsNBootstrapRunningDetailedStatusBuilder extends 
DetailedStatusBuilderWithCommandBuilder {

    private static final Logger LOG = Logger.getLogger(PsNBootstrapRunningDetailedStatusBuilder.class);
    
    private static final String GRID_BOOTSTRAP_INFO = "bsinfo";
    
    private JobAwareFreemarkerTemplateCommandBuilder infoBuilder;
    
    @Override
    public PsNBootstrapProcessingDetailedStatus getDetailedStatus() throws ExecutionException {
        Preconditions.checkNotNull(job);
        
        PsNBootstrapProcessingDetailedStatus detailedStatus = new PsNBootstrapProcessingDetailedStatus();
        
        infoBuilder.setJob(job);
        summaryCommandBuilder.setJob(job);
        
        InvokerHelper helper = new InvokerHelper(job.getInvoker());
        String command = infoBuilder.getCommand();
        InvokerResult infoResult = helper.run(command);
        
        if (infoResult.getExitStatus()!=0) {
            throw new ExecutionException("Could not retrieve job info summary. \n Error Stream:\n" 
                    + infoResult.getErrorStream() 
                    + "\n Output Stream:\n" 
                    + infoResult.getOutputStream());
        }
        String bsInfo = StringUtils.trimToEmpty(infoResult.getOutputStream());
        LOG.debug("Found bootstrap jobs: " + bsInfo);
        
        
        Map<String,NONMEMProcessingDetailedStatus> childJobs = NONMEMProcessingDetailedStatusMarshaller.unmarshallNonmemRuns(bsInfo);
        
        Map<String,NONMEMProcessingDetailedStatus> activeJobs = Maps.filterEntries(childJobs, new Predicate<Entry<String,NONMEMProcessingDetailedStatus>>() {

            @Override
            public boolean apply(@Nullable
            Entry<String, NONMEMProcessingDetailedStatus> entry) {
                return (NONMEMProcessingJobStatus.Running.name().equals(entry.getValue().getStatus())
                        || NONMEMProcessingJobStatus.Hanging.name().equals(entry.getValue().getStatus()));
            }
        });
        
        summaryCommandBuilder.setVariable(GRID_BOOTSTRAP_INFO, bsInfo);
        
        command = summaryCommandBuilder.getCommand();
        LOG.debug(command);
        InvokerResult bootstrapResult = helper.run(command);        
        
        if (bootstrapResult.getExitStatus()!=0) {
            throw new ExecutionException("Could not retrieve bootstrap summary. \n Error Stream:\n" 
                    + bootstrapResult.getErrorStream() 
                    + "\n Output Stream:\n" 
                    + bootstrapResult.getOutputStream());
        } else {
            String summary = StringUtils.chomp(bootstrapResult.getOutputStream());            
            detailedStatus.setSummary(summary);            
        }               
        detailedStatus.setGridJobId(job.getSGEJobID());
        detailedStatus.setControlFileName(job.getExecutionFileName());
        detailedStatus.setUserName(job.getRequestUserName());
        long startTime = Long.parseLong(job.getSGEStartTime());
        long duration = new Date().getTime();
        if(startTime<0) {
            duration = 0;
        } else {
            duration-=startTime;
        }
        
        detailedStatus.setDuration(Long.toString(duration));
        detailedStatus.setActiveChildNONMEMProcesses(Lists.newArrayList(activeJobs.values()));
        return detailedStatus;
    }
    
    public JobAwareFreemarkerTemplateCommandBuilder getJobInfoBuilder() {
        return infoBuilder;
    }
    
    public void setJobInfoBuilder(JobAwareFreemarkerTemplateCommandBuilder infoBuilder) {
        this.infoBuilder = infoBuilder;
    }
}
