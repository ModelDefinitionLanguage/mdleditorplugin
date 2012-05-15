/* File			:  MockRJobRunnerFactory.java
 * Project		:  MangoInteroperabilityFramework
 * Created on	:  20 Mar 2012
 */
package com.mango.mif.rconnector.runner.impl;

import static org.mockito.Mockito.spy;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;
import java.util.Map.Entry;

import javax.jms.IllegalStateException;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.core.io.Resource;

import com.mango.mif.connector.dispatcher.domain.Job;
import com.mango.mif.connector.runner.api.JobRunner;
import com.mango.mif.connector.runner.api.SCXMLDriver;
import com.mango.mif.connector.runner.api.StateHandler;
import com.mango.mif.connector.runner.impl.AbstractStateHandler;
import com.mango.mif.connector.runner.impl.DefaultStateHandler;
import com.mango.mif.connector.runner.impl.JobRunnerFactoryImpl;
import com.mango.mif.connector.runner.impl.SCXMLDriverImpl;
import com.mango.mif.rconnector.runner.impl.internal.ProcessingStateHandler;
import com.mango.mif.rconnector.runner.impl.internal.RetrievingStateHandler;
import com.mango.mif.rconnector.runner.impl.internal.SubmittingStateHandler;

/**
 * 
 * Job Runner Factory that is responsible for creating a Job Runners mocking execution of R
 * 
 * @version $Revision: $ as of $Date: $
 * <p>SVN Entry : $HeadURL: $
 * <p>SVN ID    : $Id: $
 * <p>Last edited by : $Author: $
 */
public abstract class MockRJobRunnerFactory extends JobRunnerFactoryImpl {
    /**
     * Logger
     */
    static Logger LOG = Logger.getLogger(MockRJobRunnerFactory.class);

    /**
     * States for which handlers will be created
     */
    private static String[] STATES = new String[] {"pending", "running", "task-pending", "task-preparing", "task-submitting", "task-processing", "task-retrieving", "task-postprocessing",
            "task-finished", "finished"};
    /**
     * Events that will be sent at the end of the states
     */
    private static String[] EVENTS = new String[] {AbstractStateHandler.NULL_EVENT, AbstractStateHandler.NULL_EVENT, "task.prepare", "task.submit", "task.process", "task.retrieve", "task.postprocess", "task.finished",
        "done.state.running",AbstractStateHandler.NULL_EVENT};

    @Autowired
    private ApplicationContext applicationContext;
    
    @Override
    public JobRunner createJobRunner(Job job) {
        MockRJobRunner jobRunner = (MockRJobRunner)super.createJobRunner(job);
        buildDriver(jobRunner);
        return jobRunner;
    }
    
    /**
     * Builds the driver
     * @param jobRunner
     */
    protected void buildDriver(MockRJobRunner jobRunner) {
        MockRJobRunner rJobRunner = (MockRJobRunner)jobRunner;
        SCXMLDriverImpl driver = (SCXMLDriverImpl)createDriver();
        
        rJobRunner.setRExec(new MockRExec("100+200"));
        
        Resource resource =  applicationContext.getResource("classpath:META-INF/JobRunner.scxml");
        try {
            driver.setSCXMLDocument(resource.getURL());
        } catch (IOException e) {
            LOG.error(e);
            throw new RuntimeException("Could not load JobRunner SCXML definition file",e);
        } catch (IllegalStateException e) {
            //the driver is defined as a prototype, so the exception should never be thrown, if so it is runtime exception
            LOG.error("Could not initialize the driver.",e);
            throw new RuntimeException(e);
        }
        Map<String,StateHandler> stateHandlers = createStateHanders(rJobRunner);
        for(Entry<String,StateHandler> en : stateHandlers.entrySet()) {
            driver.registerHandler(en.getValue());
        }
        jobRunner.setDriver(driver);
    }
    /**
     * Creates mock state handlers 
     * @return
     */
    private Map<String,StateHandler> createStateHanders(MockRJobRunner jobRunner) {
        Map<String,StateHandler> stateHandlers = new HashMap<String,StateHandler>();
        for(int i=0;i<STATES.length;i++) {
            stateHandlers.put(STATES[i],createStateHandler(jobRunner, STATES[i],EVENTS[i]));
        }
        return stateHandlers;
    }

    /**
     * Creates a single R mock state handler that will be invoked on the given state and once it finishes it will send a given event to the state machine.
     * @param state
     * @param exitEvent
     * @return
     */
    private StateHandler createStateHandler(MockRJobRunner jobRunner, String state, String exitEvent) {
        AbstractStateHandler stateHandler = null;
        if(state.equals("task-submitting")) {
            stateHandler = new SubmittingStateHandler(state, exitEvent);
            ((SubmittingStateHandler)stateHandler).setRJobRunner(jobRunner);
        } else if(state.equals("task-processing")) {
            stateHandler = new ProcessingStateHandler(state, exitEvent);
            ((ProcessingStateHandler)stateHandler).setRJobRunner(jobRunner);
        } else if(state.equals("task-retrieving")) {
            stateHandler = new RetrievingStateHandler(state, exitEvent);
            ((RetrievingStateHandler)stateHandler).setRJobRunner(jobRunner);
        } else {
            stateHandler = new DefaultStateHandler(state, exitEvent);
        }
        stateHandler = spy(stateHandler);
        return stateHandler;
    }
    
    /**
     * Method injected by Spring returning new instances of scxml driver
     * @return
     */
    public abstract SCXMLDriver createDriver();
}
