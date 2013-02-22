/* File			:  NONMEMProcessingJobStatus.java
 * Project		:  MIFClient
 * Created on	:  Sep 5, 2012
 */
package com.mango.mif.domain.nonmemsgeconnector;

/**
 * @version $Revision: $ as of $Date: $
 * <p>SVN Entry : $HeadURL: $
 * <p>SVN ID    : $Id: $
 * <p>Last edited by : $Author: $
 * 
 * A status of a job that is running nonmem, used by bootstrap detailed status
 * 
 */
public enum NONMEMProcessingJobStatus {
    Pending,
    Running,
    Cancelled,
    Hanging,
    Completed
}
