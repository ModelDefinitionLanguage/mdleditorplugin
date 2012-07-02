package com.mango.mif.rsgeconnector.runner.impl;

import static org.mockito.Mockito.verify;

import java.io.File;
import java.util.UUID;

import org.junit.Before;
import org.junit.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import com.mango.mif.connector.TestsHelper;
import com.mango.mif.connector.runner.impl.SCXMLDriverImpl;
import com.mango.mif.connector.runner.impl.StateHandlerException;
import com.mango.mif.rsgeconnector.internal.SGECancelCommandBuilder;
import com.mango.mif.rsgeconnector.internal.SGEExec;
import com.mango.mif.rsgeconnector.internal.SGEExecException;
import com.mango.mif.utils.encrypt.EncrypterFactory;

/**
 * Tests cancelled state handler if it produces and invokes correct command
 * @author mrogalski
 *
 */
public class CanceledStateHandlerTest {
	/**
	 * Instance being tested
	 */
	private CanceledStateHandler stateHandler;
	/**
	 * SGE working directory
	 */
	private File sgeWorkingDirectory;
	/**
	 * Mock grid exec
	 */
	@Mock private SGEExec gridExec;
	@Before
	public void setUp() throws Exception {
		MockitoAnnotations.initMocks(this);
		sgeWorkingDirectory = TestsHelper.createTmpDirectory("CanceledStateHandlerTest.setUp");
		stateHandler = new CanceledStateHandler("task-cancelled");
		stateHandler.setCancelCommandBuilder(new SGECancelCommandBuilder());
		stateHandler.setSCXMLDriver(new SCXMLDriverImpl());
		RSGEJobRunner jobRunner = new RSGEJobRunner();	
		jobRunner.setEncrypter(EncrypterFactory.getEncrypter());
		UUID jobUUID = UUID.randomUUID();
		jobRunner.setSgeExec(gridExec);
		jobRunner.setJob(TestsHelper.createJob("JOB_ID", jobUUID));
		jobRunner.getJob().addToDataMap(RSGEJobRunner.SGE_JOB_ID, "0001");
		jobRunner.getJob().addToDataMap(RSGEJobRunner.SGE_JOB_WORKING_DIRECTORY, sgeWorkingDirectory.getAbsolutePath());
		stateHandler.setJobRunner(jobRunner);
	
	}

	@Test
	public void shouldCreateAScriptThatIsGoingToBeExecutedToCancelAJob() throws StateHandlerException {
		stateHandler.doProcessing();
	}

	@Test
	public void shouldInvokeCancelOnSGEExec() throws StateHandlerException, SGEExecException {
		stateHandler.doProcessing();
		
		verify(gridExec).cancel();
	}
	

}
