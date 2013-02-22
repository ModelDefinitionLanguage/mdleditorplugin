/* File			:  RequestRoutingErrorHandler.java
 * Project		:  MIFServer
 * Created on	:  Jul 25, 2012
 */
package com.mango.mif.core.impl;

import org.springframework.jms.core.JmsTemplate;

/**
 * @version $Revision: $ as of $Date: $
 * <p>SVN Entry : $HeadURL: $
 * <p>SVN ID    : $Id: $
 * <p>Last edited by : $Author: $
 * 
 * Implements error handling for Cancellation requests router.
 */
public class CancellationRequestRoutingErrorHandler extends BaseRoutingErrorHandler {
    /**
     * Constructor
     * @param responseQueue
     * @param jmsTemplate
     */
    public CancellationRequestRoutingErrorHandler(String responseQueue, JmsTemplate jmsTemplate) {
        super(responseQueue, jmsTemplate);
    }

    @Override
    public void destinationNotResolved(String messageText) {
        super.destinationNotResolved(messageText);
        /*
         * @ToDo implement a handling for this type of situation
         * Add a job with a cancelled state?
         * 
         * Do nothing for now
         */
    }
}
