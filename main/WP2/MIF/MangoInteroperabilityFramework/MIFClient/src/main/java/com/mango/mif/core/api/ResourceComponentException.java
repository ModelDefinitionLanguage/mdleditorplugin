/* File			:  ResourceComponentException.java
 * Project		:  MangoInteroperabilityFramework
 * Created on	:  2 Apr 2012
 */
package com.mango.mif.core.api;

/**
 * 
 * Thrown by resource component
 * 
 * @version $Revision: $ as of $Date: $
 * <p>SVN Entry : $HeadURL: $
 * <p>SVN ID    : $Id: $
 * <p>Last edited by : $Author: $
 */
public class ResourceComponentException extends Exception {

    /**
     * 
     */
    private static final long serialVersionUID = 1L;

    public ResourceComponentException() {
    }

    public ResourceComponentException(String msg) {
        super(msg);
    }

    public ResourceComponentException(Throwable cause) {
        super(cause);
    }

    public ResourceComponentException(String msg, Throwable cause) {
        super(msg, cause);
    }

}
