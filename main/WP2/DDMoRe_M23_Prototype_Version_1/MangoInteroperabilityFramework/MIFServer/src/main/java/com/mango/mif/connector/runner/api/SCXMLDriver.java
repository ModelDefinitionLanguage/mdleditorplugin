/* File			:  SCXMLDriver.java
 * Project		:  MangoInteroperabilityFramework
 * Created on	:  14 Mar 2012
 */
package com.mango.mif.connector.runner.api;

import java.net.URL;

import javax.jms.IllegalStateException;

import com.mango.mif.connector.runner.impl.DriverProcessingStatus;

/**
 * Classes implementing the interface are responsible for marshalling the execution of a state machine.
 * 
 * @version $Revision: $ as of $Date: $
 * <p>SVN Entry : $HeadURL: $
 * <p>SVN ID    : $Id: $
 * <p>Last edited by : $Author: $
 * 
 */
public interface SCXMLDriver {
	/**
	 * A constant used for a null event
	 */
	public final static String NULL_EVENT = "NONE";
	/**
	 * A constant used for a null state
	 */
	public final static String NULL_STATE = "NONE";
    /**
     * fires an event that will result in transition of the state machine from one state to another
     * @param event
     */
    void fireEvent(String event);
    
    /**
     * Registers a state handler
     * @param stateHandler
     */
    void registerHandler(StateHandler stateHandler);
    
    /**
     * Unregisters a state handler
     * @param stateHandler
     * @return state handler if it has been removed, null otherwise
     */
    StateHandler unregisterHandler(StateHandler stateHandler);
    /**
     * Unregisters a state handler associated with the given state
     * @param state
     * @return unregistered handler if it has been removed, null otherwise
     */
    StateHandler unregisterHandler(String state);
    /**
     * Sets the SCXML document which defines state machine
     * @param scxmlDocument
     * @throws IllegalStateException 
     */
    void setSCXMLDocument(URL scxmlDocument) throws IllegalStateException;
    /**
     * Handles completion of the state handler, ensures that handling the completion of the handler is atomic
     * @param sHandler
     * @throws IllegalStateException 
     */
    void stateHandlerProcessingCompleted(StateHandler sHandler) throws IllegalStateException;
    /**
     * Returns final status
     * @return 
     */
    DriverProcessingStatus getProcessingStatus();
    /**
     * Handles failure of the given state handler
     * @param abstractStateHandler
     */
    void stateHandlerProcessingFailed(StateHandler stateHandler);
    /**
     * 
     * @return exception being the cause of the failure
     */
    Exception getException();
    /**
     * 
     * @return true if in final state and state handler procesisng for the state completed
     */
    boolean isInFinalState();
    /**
     * Invoked by state handler before any processing starts.
     * @param abstractStateHandler
     */
    void stateHandlerProcessingStarted(StateHandler abstractStateHandler);
    /**
     * cancels state machine execution
     */
    void cancel();
}
