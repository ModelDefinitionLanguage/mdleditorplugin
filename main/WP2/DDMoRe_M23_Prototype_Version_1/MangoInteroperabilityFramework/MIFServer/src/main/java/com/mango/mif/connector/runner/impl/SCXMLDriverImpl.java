/* File			:  SCXMLDriverImpl.java
 * Project		:  MangoInteroperabilityFramework
 * Created on	:  14 Mar 2012
 */
package com.mango.mif.connector.runner.impl;

import java.net.URL;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Iterator;
import java.util.Map;
import java.util.Set;
import java.util.concurrent.ConcurrentMap;

import javax.jms.IllegalStateException;

import org.apache.commons.scxml.SCXMLListener;
import org.apache.commons.scxml.Status;
import org.apache.commons.scxml.model.Transition;
import org.apache.commons.scxml.model.State;
import org.apache.commons.scxml.model.TransitionTarget;
import org.springframework.core.task.AsyncTaskExecutor;

import com.google.common.base.Preconditions;
import com.google.common.collect.Maps;
import com.mango.mif.connector.runner.api.SCXMLDriver;
import com.mango.mif.connector.runner.api.StateHandler;

/**
 * Default implementation of the SCXMLDriver interface.
 * 
 * Uses SCXMLListener to schedule StateHandlers for execution using the State Handlers Task Executor
 * 
 * @version $Revision: $ as of $Date: $
 * <p>SVN Entry : $HeadURL: $
 * <p>SVN ID    : $Id: $
 * <p>Last edited by : $Author: $
 * 
 */
public class SCXMLDriverImpl extends AbstractStateMachine implements SCXMLDriver {
	
    private AsyncTaskExecutor stateHandlersExecutor;
    protected ConcurrentMap<String, StateHandler> stateHandlers = Maps.newConcurrentMap();
    private Exception exception;
    private volatile boolean finalState = false;
    private volatile StateHandler currentStateHandler;
    
    @Override
    public void setSCXMLDocument(URL scxmlDocument) throws IllegalStateException {
        if(engine!=null) {
            throw new IllegalStateException("State machine already initialized.");
        }

        initialize(scxmlDocument);
        getEngine().addListener(getEngine().getStateMachine(), new StateHandlerDispatcher());
    }
    
    /**
     * 
     * @param state
     * @return true if the state machine is in the given state 
     */
    public boolean inState(String state) {
        Status status = getEngine().getCurrentStatus();
        @SuppressWarnings("unchecked")
        Set<TransitionTarget> set = status.getStates();
        return inState(state,set );
    }
    
    /**
     * Test whether expected state is found in given state or its parents
     */
    private boolean inState(String expected, TransitionTarget state) {
        if(state==null) return false;
        if(state.getId().equals(expected)) return true;
        return inState(expected,state.getParent());
            
    }
    /**
     * Checks whether the given expected state is found in given set of states or their parents
     * @param expected
     * @param states
     * @return
     */
    private boolean inState(String expected, Set<TransitionTarget> states) {
        Iterator<TransitionTarget> iterator = states.iterator();
        while(iterator.hasNext()) {
            TransitionTarget st = iterator.next();
            if(inState(expected,st)) return true;
        }
        return false;
    }
    /**
     * Return all current states
     * @param states
     * @return
     */
    private Set<String> getStates(Set<TransitionTarget> states) {
        HashSet<String> result = new HashSet<String>();
        Iterator<TransitionTarget> iterator = states.iterator();
        while(iterator.hasNext()) {
            TransitionTarget st = iterator.next();
            result.addAll(getSubStates(st));
        }
        return result;
    }

    /**
     * Return all current states
     * @param states
     * @return
     */
    private Set<String> getSubStates(TransitionTarget state) {
        HashSet<String> result = new HashSet<String>();
        if(state==null) return result;
        result.add(state.getId());
        result.addAll(getSubStates((State)state.getParent()));
        return result;
        
    }
    @Override
    public synchronized void stateHandlerProcessingCompleted(final StateHandler sHandler) throws IllegalStateException {
        try {
            Status status = getEngine().getCurrentStatus();
            @SuppressWarnings("unchecked")
            Set<TransitionTarget> set = status.getStates();
            
            if(inState(sHandler.getState(),set )) {
                String exitEvent = sHandler.complete();
                if(exitEvent!=null) {
                    fireEventInternal(exitEvent);
                } else {
                    if(engine.getCurrentStatus().isFinal()) {
                        LOG.debug("Reached final state");
                        this.finalState = true;
                    } else {
                        LOG.debug("Exit Event is null but did not reach final state. Apparently some external component is expected to trigger transition to next state.");
                    }
                }
            } else {
                String errMsg = "The state machine is in different state than the one expected by state handler. Expected state = '"+sHandler.getState()+"'. State machine was in states: " + getStates(set);
                LOG.error(errMsg);
                throw new IllegalStateException(errMsg);
            }
        } finally {

            currentStateHandler = null;
        }
    }

    @Override
    public synchronized void fireEvent(String event) {
        fireEventInternal(event);
    }

    @Override
    public void registerHandler(StateHandler stateHandler) {
        stateHandlers.put(stateHandler.getState(), stateHandler);
        stateHandler.setSCXMLDriver(this);
    }

    @Override
    public StateHandler unregisterHandler(StateHandler stateHandler) {
        return stateHandlers.remove(stateHandler.getState());
    }

    @Override
    public StateHandler unregisterHandler(String state) {
        return stateHandlers.remove(state);
    }
    /**
     * Handler responsible for handling transition events and scheduling appropriate
     * Status Handlers
     * @author mrogalski
     *
     */
    class StateHandlerDispatcher implements SCXMLListener {

        @Override
        public void onEntry(TransitionTarget state) {
            LOG.debug(getSubStates(state));
            dispatch(state.getId());
        }

        @Override
        public void onExit(TransitionTarget state) {
        }

        @Override
        public void onTransition(TransitionTarget from, TransitionTarget to, Transition transition) {
        }
        
    }
    
    /**
     * Dispatch the execution to the appropriate handler
     * @param state
     */
    private synchronized void dispatch(String state) {
        LOG.debug("Handle state: " + state);

        if(JobRunnerState.CANCELLED.getStateName().equals(state)) {
            LOG.info("transition to " + state + " - INTERCEPTED.");
            return;
        }
        
        StateHandler handler = stateHandlers.get(state);
        Preconditions.checkNotNull(handler,"Handler for state " + state + " not found!");
        
        LOG.debug("Dispatching execution to the state handler " + handler.getState() + ".");
        stateHandlersExecutor.submit(handler);
    }

    /**
     * Returns copy of the internal state handlers registry
     * @return
     */
    public Map<String, StateHandler> getStateHandlers() {
        HashMap<String, StateHandler> result = Maps.newHashMap();
        result.putAll(stateHandlers);
        return result;
    }

    @Override
    public DriverProcessingStatus getProcessingStatus() {
        if(!engine.getCurrentStatus().isFinal()) return DriverProcessingStatus.RUNNING;
        @SuppressWarnings("unchecked")
        Set<State> set = getEngine().getCurrentStatus().getStates();
        State state = set.iterator().next();
        if(state.getId().equals(JobRunnerState.FAILED.getStateName())) return DriverProcessingStatus.FAILED;
        if(state.getId().equals(JobRunnerState.CANCELLED.getStateName())) return DriverProcessingStatus.CANCELLED;
        return DriverProcessingStatus.FINISHED;
    }

    @Override
    public void stateHandlerProcessingFailed(StateHandler stateHandler) {
        try {
            exception = stateHandler.getStateHandlerException();
            String exitEvent = stateHandler.failed();
            if(exitEvent!=null) {
                fireEventInternal(exitEvent);
            } else {
                if(engine.getCurrentStatus().isFinal()) {
                    LOG.debug("Reached final state");
                    this.finalState = true;
                } else {
                    LOG.debug("Exit Event is null but did not reach final state. Apparently some external component is expected to trigger transition to next state.");
                }
            }
        } finally {
            currentStateHandler = null;
        }
    }
    
    public void setStateHandlersExecutor(AsyncTaskExecutor stateHandlersExecutor) {
        this.stateHandlersExecutor = stateHandlersExecutor;
    }
    
    @Override
    public Exception getException() {
        return exception;
    }
    
    @Override
    public boolean isInFinalState() {
        return finalState;
    }

    @Override
    public void stateHandlerProcessingStarted(StateHandler abstractStateHandler) {
        currentStateHandler = abstractStateHandler;
    }

    @Override
    public synchronized void cancel() {
        try {
            if(currentStateHandler!=null) {
                currentStateHandler.cancelProcessing();
            }
            StateHandler cancellationHandler = stateHandlers.get(JobRunnerState.CANCELLED.getStateName());
            
            fireEvent(JobRunnerState.CANCELLED.getTriggeringEvent());
            if(cancellationHandler!=null) {
                try {
                    cancellationHandler.call();
                } catch (Exception e) {
                    LOG.error("Exception thrown ", e);
                }
            } else {
                LOG.warn("Cancellation handler was not found");
            }
        } catch(Exception e) {
            LOG.error("Error when running cancellation handler");
            exception = e;
        } finally {
            finalState = true;
        }
    }

}
