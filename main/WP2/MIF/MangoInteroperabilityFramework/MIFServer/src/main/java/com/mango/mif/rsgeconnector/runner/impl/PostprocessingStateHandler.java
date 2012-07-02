/* File			:  RScriptStateHandler.java
 * Project		:  MangoInteroperabilityFramework
 * Created on	:  20 Mar 2012
 */
package com.mango.mif.rsgeconnector.runner.impl;

import java.io.File;
import java.io.IOException;

import org.apache.commons.io.FileUtils;

import com.mango.mif.connector.runner.api.SCXMLDriver;
import com.mango.mif.core.domain.JobRuntimeData;
import com.mango.mif.core.domain.ExecutionJobKey;
import com.mango.mif.core.domain.ExecutionJobValue;

/**
 * Post Processing state handler
 * 
 * @version $Revision: $ as of $Date: $
 * <p>SVN Entry : $HeadURL: $
 * <p>SVN ID    : $Id: $
 * <p>Last edited by : $Author: $
 */
public class PostprocessingStateHandler extends RSGEStateHandler {

    /**
     * Constructs a state handler with no exit event
     * @param state
     */
    public PostprocessingStateHandler(String state) {
        super(state, SCXMLDriver.NULL_EVENT);
    }
    
    /**
     * Default Constructor
     * @param state
     * @param exitEvent
     */
    public PostprocessingStateHandler(String state, String exitEvent) {
        super(state, exitEvent);
    }
    @Override
    protected void doProcessing() {
//    	TODO Removing due to potential bug in AsUser environment, 
//    	Currently files will be automatically removed after 60 days b
//    	
//    	File file = new File(jobRunner.getWorkingDirectory());
//  	
//    	try {
//			FileUtils.deleteDirectory(file);
//		} catch (IOException e) {
//			LOG.error("Could not delete SGE working directory: " + file, e);
//		}
		
		// TODO: Cleanup the database entry (archive/delete) for the job?
		// Maybe this will be left to an external database script?
    }
    
    @Override
    public String complete() {
//    	TODO fix it, can't find job
//		DC - Need to create initial JobRuntimeData somewhere otherwise there is no record to update.  See PendingStateHandler.complete() 
//    	JobRuntimeData execJob = executionJobRepository.getExecutionJobByRequestUuid(jobRunner.getJob().getExecutionRequest().getRequestId().toString(),ExecutionJobKey.STATUS.getMessage());
//        execJob.setValue(ExecutionJobValue.POST_PROCESSING.getMessage());  
//        execJob = executionJobRepository.save(execJob);
//        LOG.info("Persisted Job during POST_PROCESSING state: " + execJob.toString());
        
    	return super.complete();
    }
}
