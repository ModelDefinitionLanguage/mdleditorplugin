/* File			:  PsNVPCProcessingDetailedStatus.java
 * Project		:  MIFServer
 * Created on	:  Aug 15, 2012
 */
package com.mango.mif.domain.psnsgeconnector;

import javax.xml.bind.annotation.XmlRootElement;

import com.mango.mif.domain.DetailedStatus;


/**
 * @version $Revision: $ as of $Date: $
 * <p>SVN Entry : $HeadURL: $
 * <p>SVN ID    : $Id: $
 * <p>Last edited by : $Author: $
 * 
 * Objects of this class hold detailed status information of VPC processing
 */
@XmlRootElement(name = "PsNVPCProcessingDetailedStatus")
public class PsNVPCProcessingDetailedStatus extends DetailedStatus {
    /**
     * username who started the job
     */
    private String userName;
    
    public void setUserName(String userName) {
        this.userName = userName;
    }
    
    public String getUserName() {
        return userName;
    }
}
