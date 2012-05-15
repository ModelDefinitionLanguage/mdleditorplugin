/* File			:  MIFException.java
 * Project		:  MangoInteroperabilityFramework-trunk
 * Created on	:  5 Apr 2012
 */
package com.mango.mif.connector.dispatcher.domain;

/**
 * 
 * Exception thrown by MIF components
 * 
 * @version $Revision: $ as of $Date: $
 * <p>SVN Entry : $HeadURL: $
 * <p>SVN ID    : $Id: $
 * <p>Last edited by : $Author: $
 */
public class MIFException extends Exception {

    /**
     * 
     */
    private static final long serialVersionUID = 1L;
    /**
     * Constructor
     * @param message
     * @param cause
     */
    public MIFException(String message, Throwable cause) {
        super(message, cause);
    }

}
