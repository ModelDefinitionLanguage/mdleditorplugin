/* File			:  NONMEMCompletedDetailedStatusBuilder.java
 * Project		:  MIFServer
 * Created on	:  Aug 10, 2012
 */
package com.mango.mif.nonmemsgeconnector;

import org.apache.log4j.Logger;

/**
 * @version $Revision: $ as of $Date: $
 * <p>SVN Entry : $HeadURL: $
 * <p>SVN ID    : $Id: $
 * <p>Last edited by : $Author: $
 * 
 * Generates a summary message for NONMEM run that is in completed state
 * 
 */
public class NONMEMCompletedDetailedStatusBuilder extends NONMEMDetailedStatusBuilder {

    protected static final Logger logger = Logger.getLogger(NONMEMCompletedDetailedStatusBuilder.class);

    // I don't believe this class has anything to do above and beyond what the parent
    // NONMEMDetailedStatusBuilder already does.
}
