/* File			:  JobSummaryMessages.java
 * Project		:  MIFClient
 * Created on	:  Aug 23, 2012
 */
package com.mango.mif.client.api.rest;

import java.util.List;

import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlElementWrapper;
import javax.xml.bind.annotation.XmlRootElement;

import com.mango.mif.domain.nonmemsgeconnector.NONMEMProcessingDetailedStatus;



/**
 * @version $Revision: $ as of $Date: $
 * <p>SVN Entry : $HeadURL: $
 * <p>SVN ID    : $Id: $
 * <p>Last edited by : $Author: $
 * 
 * A REST response containing a list of summary messages of running jobs
 */
@XmlRootElement(name = "bootstrapActiveChildJobsResponse")
public class BootstrapActiveChildJobs extends MIFResponse {
    /**
     * Active child jobs
     */
    private List<NONMEMProcessingDetailedStatus> activeChildJobs;

    @XmlElementWrapper(name="activeChildJobs")
    @XmlElement(name="activeChildJob")
    public List<NONMEMProcessingDetailedStatus> getActiveChildJobs() {
        return activeChildJobs;
    }
    
    public void setActiveChildJobs(List<NONMEMProcessingDetailedStatus> activeChildJobs) {
        this.activeChildJobs = activeChildJobs;
    }
}
