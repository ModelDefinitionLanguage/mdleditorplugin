/* File         :  JobRunnerExecutionTest.java
 * Project      :  MangoInteroperabilityFramework
 * Created on   :  13 Mar 2012
 */
package com.mango.mif.connector.runner.impl;

import static org.junit.Assert.assertNotNull;

import java.util.UUID;
import java.util.concurrent.Callable;
import java.util.concurrent.Future;

import org.apache.log4j.Logger;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.core.task.AsyncTaskExecutor;
import org.springframework.test.annotation.DirtiesContext;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import com.mango.mif.connector.TestsHelper;
import com.mango.mif.connector.runner.api.JobRunnerFactory;
import com.mango.mif.connector.runner.domain.JobMessage;
import static org.mockito.Mockito.*;
/**
 * Basic Job Runner Integration test
 * 
 * @version $Revision: $ as of $Date: $
 * <p>SVN Entry : $HeadURL: $
 * <p>SVN ID    : $Id: $
 * <p>Last edited by : $Author: $
 * 
 */
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration
public class JobRunnerExecutionIntegrationTest {
    /**
     * Logger
     */
    public static Logger LOG = Logger.getLogger(JobRunnerExecutionIntegrationTest.class);
    
    /**
     * A factory producing spring-managed job runners
     */
    @Autowired
    @Qualifier("jobRunnerFactory")
	private JobRunnerFactory jobRunnerFactory;
    
    /**
     * the task executor that executes job runners
     */
    @Autowired
    @Qualifier("jobRunnersTaskExecutor")
    private AsyncTaskExecutor jobRunnersTaskExecutor;
    
    /**
     * Sends 10 messages to LOG
     * @return
     */
    private static String mockCallMethod() {
                for(int i=0;i<10;i++) {
                    LOG.info("Mocked processing request " + i);
                }
                return "DONE";
    }
    
    /**
     * Creates a mock job runner
     * @return 
     */
    private MockJobRunner createJobRunner() {
        MockJobRunner jobRunner = (MockJobRunner)jobRunnerFactory.createJobRunner(TestsHelper.createJob("JOB_1", UUID.randomUUID()));
        Callable<String> cancelHandler = new Callable<String>() {
            @Override
            public String call() throws Exception {
                return "CANCEL_" + mockCallMethod();
            }
        };

        
        Callable<String> startHandler = new Callable<String>() {
            @Override
            public String call() throws Exception {
                return "START_" + mockCallMethod();
            }
        };

        Callable<String> cancelHandlerSpy = spy(cancelHandler);
        Callable<String> startHandlerSpy = spy(startHandler);
        
        // setting spies as handlers to catch the calls
        jobRunner.cancelHandler = cancelHandlerSpy;
        jobRunner.startHandler = startHandlerSpy;
        return jobRunner;
    }
    
    /**
     * Tests if the Start Handler is correctly scheduled for execution to handlers Task Executor
     */
    @DirtiesContext
    @Test
    public void testJobRunnerExecution() throws Exception {
        MockJobRunner jobRunner = createJobRunner();
        Future<JobMessage> result = jobRunnersTaskExecutor.submit(jobRunner);
        assertNotNull("The returned future is null.",result);
        JobMessage jobMessage = result.get();
        assertNotNull("The returned future result is null.",jobMessage);
        verify(jobRunner.startHandler).call();
    }

    /**
     * Tests if the Cancel Handler is correctly scheduled for execution to handlers Task Executor
     */
    @DirtiesContext
    @Test
    public void testCancelHandlerExecution() throws Exception {
        MockJobRunner jobRunner = createJobRunner();
        Future<JobMessage> result = jobRunnersTaskExecutor.submit(jobRunner);
        result.get();
        jobRunner.cancelProcessing();
        assertNotNull("Aquired job runner instance is null.",jobRunner);
        assertNotNull("Job Runner's job is null.",jobRunner.getJob());
        verify(jobRunner.cancelHandler).call();
    }
}
