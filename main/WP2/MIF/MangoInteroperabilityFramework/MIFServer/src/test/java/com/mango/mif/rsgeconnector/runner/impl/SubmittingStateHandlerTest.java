/* File			:  SubmittingStateHandlerTest.java
 * Project		:  MIFServer
 * Created on	:  27 Jun 2012
 */
package com.mango.mif.rsgeconnector.runner.impl;

import static org.mockito.Mockito.when;

import java.util.concurrent.ConcurrentHashMap;

import org.junit.Before;
import org.junit.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import com.mango.mif.connector.dispatcher.domain.Job;
import com.mango.mif.connector.runner.api.SCXMLDriver;
import com.mango.mif.connector.runner.impl.StateHandlerException;
import com.mango.mif.rsgeconnector.internal.SGEExec;
import com.mango.mif.rsgeconnector.internal.SGESubmitCommandBuilder;


/**
 * @version $Revision: $ as of $Date: $
 * <p>SVN Entry : $HeadURL: $
 * <p>SVN ID    : $Id: $
 * <p>Last edited by : $Author: $
 */
/**
 * @author mrogalski
 * 
 * Tests submitting state handler
 *
 */
public class SubmittingStateHandlerTest {
	/**
	 * Tested instance
	 */
	private SubmittingStateHandler submittingStateHandler;
	/**
	 * Command Builder
	 */
	@Mock SGESubmitCommandBuilder commandBuilder;
	/**
	 * Mock grid exec
	 */
	@Mock private SGEExec gridExec;
	/**
	 * SCXML driver
	 */
	@Mock private SCXMLDriver scxmlDriver;
	/**
	 * job runner
	 */
	@Mock RSGEJobRunner jobRunner;
	/**
	 * Job
	 */
	@Mock Job job;
	/**
	 * Data 
	 */
	@Mock ConcurrentHashMap<String,String> data;
	
	@Before 
	public void setUp() {
		MockitoAnnotations.initMocks(this);
		submittingStateHandler = new SubmittingStateHandler("NONE","NONE");
		submittingStateHandler.setSCXMLDriver(scxmlDriver);
		submittingStateHandler.setSubmitCommandBuilder(commandBuilder);
		submittingStateHandler.setJobRunner(jobRunner);
		when(job.getData()).thenReturn(data);
		when(data.get(RSGEJobRunner.SGE_JOB_WORKING_DIRECTORY)).thenReturn("tmpDir");
		when(jobRunner.getJob()).thenReturn(job);
		when(jobRunner.getSgeExec()).thenReturn(gridExec);
	}
	
	@Test(expected=NullPointerException.class)
	public void shouldThrowExceptionIfCommandBuilderNotSet() throws StateHandlerException {
		submittingStateHandler.setSubmitCommandBuilder(commandBuilder);
		submittingStateHandler.doProcessing();
	}
	
}
