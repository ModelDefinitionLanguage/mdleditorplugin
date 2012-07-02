/* File			:  SubmittingStateHandler.java
 * Project		:  MangoInteroperabilityFramework
 * Created on	:  14 MAy 2012
 */
package com.mango.mif.rsgeconnector.runner.impl;


import java.io.File;
import java.io.IOException;

import org.apache.commons.io.FileUtils;

import com.google.common.base.Preconditions;
import com.google.common.io.Files;
import com.mango.mif.connector.dispatcher.domain.Job;
import com.mango.mif.connector.runner.impl.StateHandlerException;
import com.mango.mif.rsgeconnector.internal.SGEExec;
import com.mango.mif.rsgeconnector.internal.SGEExecException;
import com.mango.mif.rsgeconnector.internal.SGESubmitCommandBuilder;

/**
 * Submitting state handler - responsible for starting the actual execution 
 * 
 * @version $Revision: $ as of $Date: $
 * <p>SVN Entry : $HeadURL: $
 * <p>SVN ID    : $Id: $
 * <p>Last edited by : $Author: $
 */
public class SubmittingStateHandler extends RSGEStateHandler {

	/**
	 * responsible for building SGE submit command
	 */
	private SGESubmitCommandBuilder submitCommandBuilder;
    /**
     * SGE job ID
     */
	private String jobID;
    /**
     * Default Constructor
     * @param state
     * @param exitEvent
     */
    public SubmittingStateHandler(String state, String exitEvent) {
        super(state, exitEvent);
    }
    
    @Override
    protected void doProcessing() throws StateHandlerException {
		SGEExec gridExec = jobRunner.getSgeExec();
		
		gridExec.setWorkingDirectory(getSgeJobWorkingDirectory());
		
		String submitCommand = null;
		try {
			submitCommand = buildSGECommand(new File(jobRunner.getJob().getData().get(RSGEJobRunner.EXEC_R_SCRIPT)));
		} catch (IOException e1) {
            LOG.error(e1);
            throw new StateHandlerException("Error when submitting command to grid",e1);
		}
		try {		
			gridExec.setSubmitCommand(submitCommand);
        	gridExec.submit();
        	jobID = gridExec.getJobID();
		} catch (SGEExecException e) {
			LOG.error(e);
            throw new StateHandlerException("Error when submitting command to grid",e);
		} 
    }
	/**
     * Builds SGE command
     * @param scriptFile
     * @return
	 * @throws IOException 
     */
    private String buildSGECommand(File scriptFile) throws IOException {
    	Preconditions.checkNotNull(submitCommandBuilder, "SGE submit command builder must be set.");
    	submitCommandBuilder.setCommandFile(scriptFile).setJobName(scriptFile.getName()).setProlog(getSgeConfigAsString())
    	.setUser(jobRunner.getJob().getExecutionRequest().getUserName())
    	.setQueue("all.q"); 
    	return submitCommandBuilder.getCommand();
	}
    
    @Override
    public String complete() {

//		TODO Fix it! Retruns null!
//		DC - Need to create initial JobRuntimeData somewhere otherwise there is no record to update.  See PendingStateHandler.complete() 
//    	JobRuntimeData execJob = executionJobRepository.getExecutionJobByRequestUuid(jobRunner.getJob().getJobId(), ExecutionJobKey.STATUS.getMessage());
//        execJob.setValue(ExecutionJobValue.SUBMITTING.getMessage());  
//        execJob = executionJobRepository.save(execJob);
//        
//        LOG.info("Persisted Job during SUBMITTING state: " + execJob.toString());

        Job job = jobRunner.getJob();
        job.addToDataMap(RSGEJobRunner.SGE_JOB_ID, jobID);
    	
    	return super.complete();
    }

    public void setSubmitCommandBuilder(SGESubmitCommandBuilder submitCommandBuilder) {
		this.submitCommandBuilder = submitCommandBuilder;
	}
    
	/**
	 * @throws IOException 
	 * 
	 */
	private String getSgeConfigAsString() throws IOException {
		File dir = Files.createTempDir();
        dir.deleteOnExit();
		File sge = new File(dir, "sge.sh");
		try {
			FileUtils.copyURLToFile(this.getClass().getResource("/com/mango/mif/rsgeconnector/internal/sge.sh"), sge);
		} catch (IOException e) {
			throw new RuntimeException("Could not compy SGE config file to working directory.", e);
		}
		return FileUtils.readFileToString(sge);
	}
}