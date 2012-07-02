/* File         :  SCXMLDriverImplTest.java
 * Project      :  MangoInteroperabilityFramework
 * Created on   :  13 Mar 2012
 */
package com.mango.mif.connector.runner.impl;

import static org.junit.Assert.*;
import static org.mockito.Mockito.*;

import java.util.HashMap;
import java.util.Map;
import java.util.Map.Entry;
import java.util.concurrent.CountDownLatch;

import javax.annotation.Resource;

import org.apache.commons.scxml.SCXMLListener;
import org.apache.commons.scxml.model.Transition;
import org.apache.commons.scxml.model.TransitionTarget;
import org.apache.log4j.Logger;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.test.annotation.DirtiesContext;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import com.mango.mif.connector.runner.api.SCXMLDriver;
import com.mango.mif.connector.runner.api.StateHandler;

/**
 * SCXML driver implementation unit test
 * 
 * @version $Revision: $ as of $Date: $
 * <p>SVN Entry : $HeadURL: $
 * <p>SVN ID    : $Id: $
 * <p>Last edited by : $Author: $
 * 
 */
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration
public class SCXMLDriverImplTest {
    /**
     * Logger
     */
    private static Logger LOG = Logger.getLogger(SCXMLDriverImplTest.class);
    
    /**
     * Job runner factory producing spring-managed job runners
     */
    @Resource(name="scxmlDriver")
	private SCXMLDriverImpl scxmlDriver;
    
    
    @Autowired
    private ApplicationContext applicationContext;

    /**
     * States for which handlers will be created
     */
    private static String[] STATES = new String[] {
    		JobRunnerSates.PENDING.getStateName(), JobRunnerSates.RUNNING.getStateName(), 
    		JobRunnerSates.TASK_PENDING.getStateName(), JobRunnerSates.TASK_PREPARING.getStateName(), JobRunnerSates.TASK_SUBMITTING.getStateName(), 
    		JobRunnerSates.TASK_PROCESSING.getStateName(), JobRunnerSates.TASK_RETRIEVING.getStateName(), JobRunnerSates.TASK_POSTPROCESSING.getStateName(),
    		JobRunnerSates.TASK_FINISHED.getStateName(), 
            JobRunnerSates.FINISHED.getStateName(), JobRunnerSates.CANCELLED.getStateName()
            };
    /**
     * Events that will be sent at the end of the states
     */
    private static String[] EVENTS = new String[] {
    		SCXMLDriver.NULL_EVENT, SCXMLDriver.NULL_EVENT, 
    		JobRunnerSates.TASK_PREPARING.getTriggeringEvent(), JobRunnerSates.TASK_SUBMITTING.getTriggeringEvent(), JobRunnerSates.TASK_PROCESSING.getTriggeringEvent(), "task.retrieve", JobRunnerSates.TASK_POSTPROCESSING.getTriggeringEvent(), JobRunnerSates.TASK_FINISHED.getTriggeringEvent(),
    		JobRunnerSates.FINISHED.getTriggeringEvent(),SCXMLDriver.NULL_EVENT, SCXMLDriver.NULL_EVENT
    		};

    /**
     * Used to synchronise test thread and spring TaskExecutors
     */
    private CountDownLatch eventSignal;
    
    /**
     * Tests scxml driver handlers execution
     * @throws Exception 
     */
    @DirtiesContext
    @Test(timeout = 30000)
    public void testDriverStateHandlers() throws Exception {
    	eventSignal = new CountDownLatch(1);
        org.springframework.core.io.Resource resource =  applicationContext.getResource("classpath:META-INF/JobRunnerWithFailover.scxml");
        scxmlDriver.setSCXMLDocument(resource.getURL());
        registerNofifierOnState(JobRunnerSates.FINISHED.getStateName());

        assertNotNull("SCXML document does not exist.",resource);
        Map<String,StateHandler> stateHandlers = createStateHanders();
        
        for(Entry<String,StateHandler> en : stateHandlers.entrySet()) {
            scxmlDriver.registerHandler(en.getValue());
        }
        
        ((SCXMLDriverImpl)scxmlDriver).fireEvent(JobRunnerSates.RUNNING.getTriggeringEvent());

        //thread that runs the test is notified when finished state is reached
        eventSignal.await();
        MockStateHandler handler = (MockStateHandler) stateHandlers.get(JobRunnerSates.FINISHED.getStateName());

        //Must ensure that the finished state handler has been invoked
        while(!handler.isDone()) {
        	Thread.sleep(1000);
        }
        
        
        stateHandlers.remove(JobRunnerSates.PENDING.getStateName());
        stateHandlers.remove(JobRunnerSates.CANCELLED.getStateName());
        for(Entry<String,StateHandler> en : stateHandlers.entrySet()) {
            verify(en.getValue()).call();
        }
    }
    /**
     * Tests scxml driver handlers execution
     * @throws Exception 
     */
    @DirtiesContext
    @Test(timeout = 30000)
    public void shouldMoveFromPendingStateToCancelledState() throws Exception {
    	eventSignal = new CountDownLatch(1);
        org.springframework.core.io.Resource resource =  applicationContext.getResource("classpath:META-INF/JobRunnerWithFailover.scxml");
        scxmlDriver.setSCXMLDocument(resource.getURL());
        registerNofifierOnState(JobRunnerSates.CANCELLED.getStateName());

        assertNotNull("SCXML document does not exist.",resource);
        Map<String,StateHandler> stateHandlers = createStateHanders();
        
        for(Entry<String,StateHandler> en : stateHandlers.entrySet()) {
            scxmlDriver.registerHandler(en.getValue());
        }
        
        ((SCXMLDriverImpl)scxmlDriver).fireEvent(JobRunnerSates.CANCELLED.getTriggeringEvent());
        
        //thread that runs the test is notified when finished state is reached
        eventSignal.await();

        MockStateHandler handler = (MockStateHandler)stateHandlers.remove(JobRunnerSates.CANCELLED.getStateName());
        
        //Must ensure that the finished state handler has been invoked, if it is not invoked in 3 sec it is considered as failure
        while(!handler.isDone()) {
        	Thread.sleep(1000);
        }

        assertTrue(scxmlDriver.inState(JobRunnerSates.CANCELLED.getStateName()));
    }
    /**
     * Tests scxml driver handlers execution
     * @throws Exception 
     */
    @DirtiesContext
    @Test(timeout = 30000)
    public void shouldBeStillInCanceledStateEvenIfStartEventIsSent() throws Exception {
    	eventSignal = new CountDownLatch(1);
        org.springframework.core.io.Resource resource =  applicationContext.getResource("classpath:META-INF/JobRunnerWithFailover.scxml");
        scxmlDriver.setSCXMLDocument(resource.getURL());
        registerNofifierOnState(JobRunnerSates.CANCELLED.getStateName());

        assertNotNull("SCXML document does not exist.",resource);
        Map<String,StateHandler> stateHandlers = createStateHanders();
        
        for(Entry<String,StateHandler> en : stateHandlers.entrySet()) {
            scxmlDriver.registerHandler(en.getValue());
        }
        
        ((SCXMLDriverImpl)scxmlDriver).fireEvent(JobRunnerSates.CANCELLED.getTriggeringEvent());

        //thread that runs the test is notified when finished state is reached
        eventSignal.await();
        
        MockStateHandler handler = (MockStateHandler)stateHandlers.remove(JobRunnerSates.CANCELLED.getStateName());
        
        while(!handler.isDone()) {
        	Thread.sleep(1000);
        }

        ((SCXMLDriverImpl)scxmlDriver).fireEvent(JobRunnerSates.RUNNING.getTriggeringEvent());
        
        assertTrue(scxmlDriver.inState(JobRunnerSates.CANCELLED.getStateName()));

    }
    /**
     * Registers a state machine listener that on given state notifies any threads that wait on semaphore
     * @param stateId
     */
    private void registerNofifierOnState(final String stateId) {
        scxmlDriver.getEngine().addListener(scxmlDriver.getEngine().getStateMachine(), new SCXMLListener() {

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
    
    
    /**
     * Creates mock state handlers 
     * @return
     */
    private Map<String,StateHandler> createStateHanders() {
        Map<String,StateHandler> stateHandlers = new HashMap<String,StateHandler>();
        for(int i=0;i<STATES.length;i++) {
            stateHandlers.put(STATES[i],createStateHandlerMock(STATES[i],EVENTS[i]));
        }
        return stateHandlers;
    }

    /**
     * Creates a single mock state handler that will be invoked on the given state and once it finishes it will send a given event to the state machine.
     * @param state
     * @param exitEvent
     * @return
     */
    private StateHandler createStateHandlerMock(String state, String exitEvent) {
        MockStateHandler mockHandler = new MockStateHandler(state, exitEvent);
        StateHandler stateHandler = spy(mockHandler);
        return stateHandler;
    }
}
