package com.mango.mif.rsgeconnector.runner.impl;

import org.apache.log4j.Logger;
import com.google.common.base.Preconditions;
import com.mango.mif.connector.dispatcher.domain.Job;
import com.mango.mif.connector.runner.impl.DefaultJobRunner;
import com.mango.mif.connector.runner.impl.JobRunnerSates;
import com.mango.mif.rsgeconnector.internal.SGEExec;
import com.mango.mif.utils.encrypt.DesEncrypter;
import com.mango.mif.utils.encrypt.EncrypterFactory;
/**
 * Job Runner that is responsible for execution of R on grid
 * @author mrogalski
 *
 */
public class RSGEJobRunner extends DefaultJobRunner {
    /**
     * Logger
     */
    private final static Logger LOG = Logger.getLogger(RSGEJobRunner.class);
	/**
	 * A constant for a SGE Job working firectory
	 */
	public final static String SGE_JOB_WORKING_DIRECTORY = "SGE_WORKING_DIRECTORY";
	/**
	 * A constant of the script that is to be submitted to grid as a job
	 */
	public final static String SGE_SUBMIT_SCRIPT = "SGE_SUBMIT_SCRIPT";
	/**
	 * A constant of the R script to be executed
	 */
	public final static String EXEC_R_SCRIPT = "EXEC_R_SCRIPT";
	/**
	 * Job ID on SGE
	 */
	public static final String SGE_JOB_ID = "SGE_JOB_ID";
	
	/**
	 * responsible for executing sge commands, this instance is shared between different state handlers
	 */
	private SGEExec sgeExec;
	/**
	 * Cancelled state handler
	 */
	private RSGEStateHandler cancelledStateHandler;
	
	@Override
	public void cancelProcessing() {
		driver.fireEvent(JobRunnerSates.CANCELLED.getTriggeringEvent());
		try {
			cancelledStateHandler.call();
		} catch (Exception e) {
			LOG.error(e);
			throw new RuntimeException(e);
		}
	}

	public SGEExec getSgeExec() {
		return sgeExec;
	}

	public void setSgeExec(SGEExec sgeExec) {
		this.sgeExec = sgeExec;
	}

	/**
	 * @param handler
	 */
	public void setCancelledStateHandler(RSGEStateHandler handler) {
		this.cancelledStateHandler = handler;
	}

	@Override
	protected String getWorkingDirectory() {
		return getJob().getData().get(RSGEJobRunner.SGE_JOB_WORKING_DIRECTORY);
	}
	
	/* (non-Javadoc)
	 * @see com.mango.mif.connector.runner.impl.AbstractJobRunner#setJob(com.mango.mif.connector.dispatcher.domain.Job)
	 */
	@Override
	public void setJob(Job job) {
		Preconditions.checkNotNull(sgeExec);
		Preconditions.checkNotNull(job);
		Preconditions.checkNotNull(job.getExecutionRequest());
		super.setJob(job);
		sgeExec.setUserName(job.getExecutionRequest().getUserName());
		sgeExec.setUserPassword(encrypter.decrypt(job.getExecutionRequest().getUserPassword()));
	}
}
