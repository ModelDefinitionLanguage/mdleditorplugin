/* File			:  SCXMLDriverProcessingStatus.java
 * Project		:  MangoInteroperabilityFramework
 * Created on	:  21 Mar 2012
 */
package com.mango.mif.connector.runner.impl;

/**
 * 
 * Represents the SCXML Processing status. That is Runner status (Not Job Status)
 * 
 * Why not just use JobStatus? When there is a failure recovery, a Job might be in state Running, but a Job Runner for such a job is just being created.
 * 
 * @version $Revision: $ as of $Date: $
 * <p>SVN Entry : $HeadURL: $
 * <p>SVN ID    : $Id: $
 * <p>Last edited by : $Author: $
 */
public enum DriverProcessingStatus {
    /**
     * The processing is performed
     */
    RUNNING("RUNNING",false),
    /**
     * The processing reached the cancelled state
     */
    CANCELLED("CANCELLED",true),
    /**
     * The processing reached the failed state
     */
    FAILED("FAILED",true),
    /**
     * The processing finished
     */
    FINISHED("FINISHED",true);

    /**
     * is the status final
     */
    private final boolean isFinal;
    /**
     * A message associated with the state
     */
    private final String message;
    
    DriverProcessingStatus(String message, boolean isFinal) {
        this.message = message;
        this.isFinal = isFinal;
    }
    
    public boolean isFinal() {
        return isFinal;
    }

    public String getMessage() { 
        return message; 
        }
}
