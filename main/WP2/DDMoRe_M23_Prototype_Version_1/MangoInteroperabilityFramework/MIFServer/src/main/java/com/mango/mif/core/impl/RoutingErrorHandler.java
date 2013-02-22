/* File			:  RoutingErrorHandler.java
 * Project		:  MIFServer
 * Created on	:  Jul 25, 2012
 */
package com.mango.mif.core.impl;

/**
 * @version $Revision: $ as of $Date: $
 * <p>SVN Entry : $HeadURL: $
 * <p>SVN ID    : $Id: $
 * <p>Last edited by : $Author: $
 * 
 * Classes implementing that interface are responsible for providing error handling strategy to message router
 */
public interface RoutingErrorHandler {
    /**
     * Invoked when a message destination queue could not be resolved
     * @param messageText
     */
    void destinationNotResolved(String messageText);
}
