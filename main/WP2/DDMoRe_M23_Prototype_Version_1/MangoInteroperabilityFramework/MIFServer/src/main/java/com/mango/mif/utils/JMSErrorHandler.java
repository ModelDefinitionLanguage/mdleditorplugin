/* File			:  JMSErrorHandler.java
 * Project		:  MIFServer
 * Created on	:  Sep 24, 2012
 */
package com.mango.mif.utils;

import org.apache.log4j.Logger;
import org.springframework.util.ErrorHandler;


/**
 * @version $Revision: $ as of $Date: $
 * <p>SVN Entry : $HeadURL: $
 * <p>SVN ID    : $Id: $
 * <p>Last edited by : $Author: $
 * 
 * An error handler to be invoked by JMS to handle message conumption failure
 */
public class JMSErrorHandler implements ErrorHandler {
    
    private final static Logger logger = Logger.getLogger(JMSErrorHandler.class);
    
    @Override
    public void handleError(Throwable error) {
        logger.error(error);
    }

}
