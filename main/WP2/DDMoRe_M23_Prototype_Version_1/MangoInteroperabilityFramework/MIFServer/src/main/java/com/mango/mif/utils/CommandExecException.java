/* File			:  RScriptExecException.java
 * Project		:  MangoInteroperabilityFramework
 * Created on	:  29 Mar 2012
 */
package com.mango.mif.utils;

/**
 * 
 * An exception that is thrown if there is a problem with running R.
 * 
 * @version $Revision: $ as of $Date: $
 * <p>SVN Entry : $HeadURL: $
 * <p>SVN ID    : $Id: $
 * <p>Last edited by : $Author: $
 */
public class CommandExecException extends Exception {

    /**
     * Default constructor
     * @param msg
     * @param e
     */
    public CommandExecException(String msg, Throwable e) {
        super(msg,e);
    }

    /**
     * 
     */
    private static final long serialVersionUID = 1L;
    
}
