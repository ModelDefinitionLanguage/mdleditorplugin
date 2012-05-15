/* File         :  SCXMLDriverImplTest.java
 * Project      :  MangoInteroperabilityFramework
 * Created on   :  13 Mar 2012
 */
package com.mango.mif.rconnector.runner.impl;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.mockito.Matchers.anyString;
import static org.mockito.Mockito.spy;
import static org.mockito.Mockito.verify;

import java.util.Map;
import java.util.UUID;
import java.util.Map.Entry;
import java.util.concurrent.CountDownLatch;

import javax.annotation.Resource;

import org.apache.commons.scxml.SCXMLListener;
import org.apache.commons.scxml.model.Transition;
import org.apache.commons.scxml.model.TransitionTarget;
import org.apache.log4j.Logger;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.core.task.AsyncTaskExecutor;
import org.springframework.test.annotation.DirtiesContext;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import com.mango.mif.connector.TestsHelper;
import com.mango.mif.connector.runner.api.JobRunner;
import com.mango.mif.connector.runner.api.StateHandler;
import com.mango.mif.connector.runner.impl.SCXMLDriverImpl;

/**
 * Mock R Job Runner implementation unit test
 * 
 * @version $Revision: $ as of $Date: $
 * <p>SVN Entry : $HeadURL: $
 * <p>SVN ID    : $Id: $
 * <p>Last edited by : $Author: $
 * 
 */
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = {
		"/com/mango/mif/rconnector/runner/impl/MockRJobRunnerTest-context.xml",
		"/spring/JmsTest-Config.xml"})
public class MockRJobRunnerTest {
    /**
     * Logger
     */
    private static Logger LOG = Logger.getLogger(MockRJobRunnerTest.class);
    
    /**
     * Job runner factory producing spring-managed job runners
     */
    @Resource(name="mockRJobRunnerFactory")
    private MockRJobRunnerFactory jobRunnerFactory;
    /**
     * the task executor that executes job runners
     */
    @Resource(name="jobRunnersTaskExecutor")
    private AsyncTaskExecutor jobRunnersTaskExecutor;
    /**
     * Used to synchronise test thread and spring TaskExecutors
     */
    private CountDownLatch eventSignal = new CountDownLatch(1);
    /**
     * Tests if the Job Runner factory correctly creates mock R job runners
     * @throws Exception 
     */
    @DirtiesContext
    @Test
    public void testDriverAttributeSetJobRunner() throws Exception {
        JobRunner jobRunner = jobRunnerFactory.createJobRunner(TestsHelper.createJob("R-job",UUID.randomUUID()));
        assertNotNull("Driver not set on runner", ((MockRJobRunner)jobRunner).getDriver());
    }

    /**
     * Tests mock R runner's scxml driver state handlers execution
     * @throws Exception 
     */
    @DirtiesContext
    @Test
    public void testExecuteMockRRunnerDriver() throws Exception {
        JobRunner jobRunner = jobRunnerFactory.createJobRunner(TestsHelper.createJob("R-job",UUID.randomUUID()));
        SCXMLDriverImpl driver = (SCXMLDriverImpl)((MockRJobRunner)jobRunner).getDriver();

        
        registerNofifierOnState(driver, "finished");

        driver.fireEvent("runner.start");

        //thread that runs the test is notified when finished state is reached
        eventSignal.await();

        //Must ensure that the finished state handler has been invoked, if it is not invoked in 3 sec it is considered as failure
        Thread.sleep(3000);

        Map<String,StateHandler> handlers = driver.getStateHandlers();
        handlers.remove("pending");
        for(Entry<String,StateHandler> en : handlers.entrySet()) {
            verify(en.getValue()).call();
        }
        
        assertEquals(MockRExec.MOCK_RESULT,((MockRJobRunner)jobRunner).getRExec().getResult());
    }

    /**
     * Executes the Mock R Job Runner using the TaskExecutor service
     * @throws Exception 
     */
    @DirtiesContext
    @Test
    public void testExecuteRJobRunner() throws Exception {
        MockRJobRunner jobRunner = (MockRJobRunner)jobRunnerFactory.createJobRunner(TestsHelper.createJob("R-job",UUID.randomUUID()));
        SCXMLDriverImpl driver = (SCXMLDriverImpl)((MockRJobRunner)jobRunner).getDriver();

        
        registerNofifierOnState(driver, "finished");

        jobRunner = spy(jobRunner);
        
        jobRunnersTaskExecutor.submit(jobRunner);
        
        //thread that runs the test is notified when finished state is reached
        eventSignal.await();
        
        //Must ensure that the finished state handler has been invoked, if it is not invoked in 3 sec it is considered as failure
        Thread.sleep(3000);
        
        Map<String,StateHandler> handlers = driver.getStateHandlers();
        handlers.remove("pending");
        for(Entry<String,StateHandler> en : handlers.entrySet()) {
            verify(en.getValue()).call();
        }

        if(jobRunner.getDriver().getException()!=null) throw jobRunner.getDriver().getException();
        
        Thread.sleep(5000);
        
        assertEquals(MockRExec.MOCK_RESULT,jobRunner.getRExec().getResult());
        
        verify(jobRunner).publishResults(anyString());
    }
    /**
     * Registers a state machine listener that on given state notifies a thread that waits on semaphore
     * @param stateId
     */
    private void registerNofifierOnState(SCXMLDriverImpl driver, final String stateId) {
        driver.getEngine().addListener(driver.getEngine().getStateMachine(), new SCXMLListener() {

            @Override
            public void onEntry(TransitionTarget state) {
                LOG.debug("onEntry " + state);
                if(state.getId().equals(stateId)) {
                    eventSignal.countDown();
                }
            }

            @Override
            public void onExit(TransitionTarget state) {
            }

            @Override
            public void onTransition(TransitionTarget from, TransitionTarget to, Transition transition) {
            }
            
        });
    }
}
