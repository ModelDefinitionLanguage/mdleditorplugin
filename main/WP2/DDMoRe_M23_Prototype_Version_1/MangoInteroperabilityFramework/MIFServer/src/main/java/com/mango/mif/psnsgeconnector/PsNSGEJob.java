/* File			:  PsNSGEJob.java
 * Project		:  MIFServer
 * Created on	:  Aug 21, 2012
 */
package com.mango.mif.psnsgeconnector;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.log4j.Logger;

import com.google.common.collect.Lists;
import com.google.common.collect.Maps;
import com.mango.mif.connector.domain.Job;
import com.mango.mif.core.exec.CommandBuilder;
import com.mango.mif.core.exec.ExecutionException;
import com.mango.mif.core.exec.Invoker;
import com.mango.mif.core.exec.InvokerResult;
import com.mango.mif.core.exec.template.JobAwareFreemarkerTemplateCommandBuilder;
import com.mango.mif.domain.nonmemsgeconnector.NONMEMProcessingDetailedStatus;

/**
 * @version $Revision: $ as of $Date: $
 * <p>SVN Entry : $HeadURL: $
 * <p>SVN ID    : $Id: $
 * <p>Last edited by : $Author: $
 * 
 * This class is responsible for providing additional information on the PsN Job. Currently it provides child job details and child job ids.
 * 
 * This class was initially developed for DetailedStatus builders.
 */
public class PsNSGEJob {
    /**
     * Logger
     */
    protected static final Logger LOG = Logger.getLogger(PsNSGEJob.class);
    /**
     * 
     * @author mrogalski
     * constant keys expected in the output of the command retrieving child jobs details.
     * 
     */
    enum NONMEMRunProperties {
        jobId,
        jobName,
        runFileName,
        runWorkingDir,
        startTime,
        lastModificationTime
    }
    /**
     * Command builder used to create a command retrieving detailed information on child jobs
     */
    private JobAwareFreemarkerTemplateCommandBuilder retrieveChildJobsDetailsCommandBuilder;
    /**
     * Command builder used to create a command retrieving list of child job ids
     */
    private JobAwareFreemarkerTemplateCommandBuilder retrieveChildJobsIdsCommandBuilder;
    /**
     * Job being wrapped
     */
    private final Job job;
    
    /**
     * Default constructor
     * @param job being wrapped
     */
    public PsNSGEJob(Job job) {
        this.job = job;
    }
    /**
     * 
     * @param job
     * @return
     * @throws ExecutionException 
     */
    public List<String> getChildJobsIds() throws ExecutionException {
        retrieveChildJobsIdsCommandBuilder.setVariable(CommandBuilder.CONTEXT_NAME_JOB, job);
        String command = retrieveChildJobsIdsCommandBuilder.getCommand();
        InvokerResult invokerResult = getInvoker().execute(command);
        if(invokerResult.getExitStatus()!=0) {
            throw new ExecutionException("Could not retrieve list of child jobs ids. \n Error Stream:\n" + invokerResult.getErrorStream() + "\n Output Stream:\n" + invokerResult.getErrorStream());
        }
        return parseAsList(invokerResult.getOutputStream().trim());
    }
    /**
     * Parses the given string as list of strings
     * @param content
     * @return
     */
    private List<String> parseAsList(String content) {
        LOG.debug(content);
        if(content==null) {
            return Lists.newArrayList();
        }
        return Lists.newArrayList(content.split(" "));
        
    }
    /**
     * Gets a list of child PsN jobs of a given job
     * @param job
     * @return
     * @throws ExecutionException 
     */
    public List<NONMEMProcessingDetailedStatus> getChildJobsDetailed() throws ExecutionException {
        retrieveChildJobsDetailsCommandBuilder.setVariable(CommandBuilder.CONTEXT_NAME_JOB, job);
        String command = retrieveChildJobsDetailsCommandBuilder.getCommand();
        LOG.debug(command);
        InvokerResult invokerResult = getInvoker().execute(command);
        if(invokerResult.getExitStatus()!=0) {
            throw new ExecutionException("Could not retrieve list of child NONMEM runs. \n Error Stream:\n" + invokerResult.getErrorStream() + "\n Output Stream:\n" + invokerResult.getErrorStream());
        }
        return parseAsListOfNonmemRuns(invokerResult.getOutputStream());
    }
    /**
     * Parses a given string as a list of noneme processing detailed statuses
     * @param content
     * @return
     */
    private List<NONMEMProcessingDetailedStatus> parseAsListOfNonmemRuns(String content) {
        LOG.debug(content);
        String[] rows = content.split("\n");
        List<NONMEMProcessingDetailedStatus> nonmemJobStatuses = Lists.newArrayList();
        for(String row : rows) {
            Map<String,String> properties = parseMap(row);
            NONMEMProcessingDetailedStatus status = new NONMEMProcessingDetailedStatus();
            status.setControlFileName(properties.get(NONMEMRunProperties.runFileName));
            status.setGridJobId(properties.get(NONMEMRunProperties.jobId));
            status.setStartTime(properties.get(NONMEMRunProperties.startTime));
            status.setLastUpdated(properties.get(NONMEMRunProperties.lastModificationTime));
            status.setWorkingDirectory(properties.get(NONMEMRunProperties.runWorkingDir));
            nonmemJobStatuses.add(status);
        }
        return nonmemJobStatuses;
    }
    /**
     * Parses a map represented as csv of key:value pairs
     * @param row
     * @return
     */
    private Map<String, String> parseMap(String row) {
        HashMap<String,String> result = Maps.newHashMap();
        for(String en : row.split(";")) {
            String[] entry = en.split(":");
            String value="";
            if(entry.length>1) {
                value = entry[1];
            }
            result.put(entry[0],value);
        }
        return result;
    }
    /**
     * 
     * @return invoker
     */
    private Invoker getInvoker() {
        return job.getInvoker();
    }
    
    
    public void setRetrieveChildJobsDetailsCommandBuilder(JobAwareFreemarkerTemplateCommandBuilder retrieveChildJobsDetailsCommandBuilder) {
        this.retrieveChildJobsDetailsCommandBuilder = retrieveChildJobsDetailsCommandBuilder;
    }
    
    public JobAwareFreemarkerTemplateCommandBuilder getRetrieveChildJobsDetailsCommandBuilder() {
        return retrieveChildJobsDetailsCommandBuilder;
    }
    
    public void setRetrieveChildJobsIdsCommandBuilder(JobAwareFreemarkerTemplateCommandBuilder retrieveChildJobsIdsCommandBuilder) {
        this.retrieveChildJobsIdsCommandBuilder = retrieveChildJobsIdsCommandBuilder;
    }
    
    public JobAwareFreemarkerTemplateCommandBuilder getRetrieveChildJobsIdsCommandBuilder() {
        return retrieveChildJobsIdsCommandBuilder;
    }
}
