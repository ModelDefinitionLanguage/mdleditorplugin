/* File			:  NONMEMRunningDetailedStatusBuilder.java
 * Project		:  MIFServer
 * Created on	:  Aug 13, 2012
 */
package com.mango.mif.nonmemsgeconnector;

import org.apache.log4j.Logger;

/**
 * @version $Revision: $ as of $Date: $
 * <p>SVN Entry : $HeadURL: $
 * <p>SVN ID    : $Id: $
 * <p>Last edited by : $Author: $
 * 
 * The class is responsible for generating summary messages when NONMEM is Running
 * 
 */
public class NONMEMRunningDetailedStatusBuilder extends NONMEMDetailedStatusBuilder {

    protected static final Logger logger = Logger.getLogger(NONMEMRunningDetailedStatusBuilder.class);

    // I don't think this class has anything to do, as everything is handled by
    // the parent class NONMEMDetailedStatusBuilder.
}
