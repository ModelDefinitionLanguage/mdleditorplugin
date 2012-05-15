/* File         :  SCXMLDriverImplTest.java
 * Project      :  MangoInteroperabilityFramework
 * Created on   :  13 Mar 2012
 */
package com.mango.mif.connector.runner.impl;

import static org.junit.Assert.assertNotNull;
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
    private static String[] STATES = new String[] {"pending", "running", "task-pending", "task-preparing", "task-submitting", "task-processing", "task-retrieving", "task-postprocessing",
            "task-finished", "finished"};
    /**
     * Events that will be sent at the end of the states
     */
    private static String[] EVENTS = new String[] {"NONE", "NONE", "task.prepare", "task.submit", "task.process", "task.retrieve", "task.postprocess", "task.finished",
        "done.state.running","NONE"};

    /**
     * Used to synchronise test thread and spring TaskExecutors
     */
    private CountDownLatch eventSignal = new CountDownLatch(1);
    
    /**
     * Tests scxml driver handlers execution
     * @throws Exception 
     */
    @DirtiesContext
    @Test
    public void testDriverStateHandlers() throws Exception {

        org.springframework.core.io.Resource resource =  applicationContext.getResource("classpath:META-INF/JobRunner.scxml");
        scxmlDriver.setSCXMLDocument(resource.getURL());
        registerNofifierOnState("finished");

        assertNotNull("SCXML document does not exist.",resource);
        Map<String,StateHandler> stateHandlers = createStateHanders();
        
        for(Entry<String,StateHandler> en : stateHandlers.entrySet()) {
            scxmlDriver.registerHandler(en.getValue());
        }
        
        ((SCXMLDriverImpl)scxmlDriver).fireEvent("runner.start");

        //thread that runs the test is notified when finished state is reached
        eventSignal.await();


        //Must ensure that the finished state handler has been invoked, if it is not invoked in 3 sec it is considered as failure
        Thread.sleep(3000);
        
        
        stateHandlers.remove("pending");
        for(Entry<String,StateHandler> en : stateHandlers.entrySet()) {
            verify(en.getValue()).call();
        }
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
