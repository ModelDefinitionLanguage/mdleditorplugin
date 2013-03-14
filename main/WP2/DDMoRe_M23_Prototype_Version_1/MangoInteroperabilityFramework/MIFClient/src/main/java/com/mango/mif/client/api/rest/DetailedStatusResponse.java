/* File			:  JobSummaryMessages.java
 * Project		:  MIFClient
 * Created on	:  Aug 23, 2012
 */
package com.mango.mif.client.api.rest;

import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;

import com.mango.mif.domain.DetailedStatus;



/**
 * @version $Revision: $ as of $Date: $
 * <p>SVN Entry : $HeadURL: $
 * <p>SVN ID    : $Id: $
 * <p>Last edited by : $Author: $
 * 
 * A REST response containing a detailed status of a job
 */
@XmlRootElement(name = "detailedStatusResponse")
public class DetailedStatusResponse extends MIFResponse {
    /**
     * detailed status
     */
    private DetailedStatus detailedStatus;
    
    public void setDetailedStatus(DetailedStatus detailedStatus) {
        this.detailedStatus = detailedStatus;
    }

    @XmlElement
    public DetailedStatus getDetailedStatus() {
        return detailedStatus;
    }
}
