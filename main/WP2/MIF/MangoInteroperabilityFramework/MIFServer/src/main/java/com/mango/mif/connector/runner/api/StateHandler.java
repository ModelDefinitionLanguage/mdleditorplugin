/* File			:  StateHandler.java
 * Project		:  MangoInteroperabilityFramework
 * Created on	:  14 Mar 2012
 */
package com.mango.mif.connector.runner.api;

import java.util.concurrent.Callable;

/**
 * 
 * A state handler that handles execution of business logic that should be invoked on the given state
 * 
 * @version $Revision: $ as of $Date: $
 * <p>SVN Entry : $HeadURL: $
 * <p>SVN ID    : $Id: $
 * <p>Last edited by : $Author: $
 * 
 */
public interface StateHandler extends Callable<String> {
    /**
     * State that the handler 
     * @return
     */
    String getState();
    /**
     * Sets the driver that marshals the processing
     * @param driver
     */
    void setSCXMLDriver(SCXMLDriver driver);
    /**
     * Commits any persisted data.
     * @return
     */
    String complete();
    
    /**
     * Processes failure
     * @return event that should be issued on failure
     */
    String failed();
    /**
     * 
     * @return exception beeing a cause of failure
     */
    Exception getException();
}
