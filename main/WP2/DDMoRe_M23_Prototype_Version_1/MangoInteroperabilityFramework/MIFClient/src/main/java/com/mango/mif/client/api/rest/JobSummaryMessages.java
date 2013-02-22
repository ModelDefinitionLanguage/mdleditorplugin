/* File			:  JobSummaryMessages.java
 * Project		:  MIFClient
 * Created on	:  Aug 23, 2012
 */
package com.mango.mif.client.api.rest;

import java.util.Map;

import javax.xml.bind.annotation.XmlRootElement;



/**
 * @version $Revision: $ as of $Date: $
 * <p>SVN Entry : $HeadURL: $
 * <p>SVN ID    : $Id: $
 * <p>Last edited by : $Author: $
 * 
 * A REST response containing a list of summary messages of running jobs
 */
@XmlRootElement(name = "jobSummaryMessagesResponse")
public class JobSummaryMessages extends MIFResponse {
    /**
     * Messages based on request id: summary
     */
    private Map<String,String> summaryMessages;
    
    public void setSummaryMessages(Map<String, String> summaryMessages) {
        this.summaryMessages = summaryMessages;
    }
    
    public Map<String, String> getSummaryMessages() {
        return summaryMessages;
    }
}
