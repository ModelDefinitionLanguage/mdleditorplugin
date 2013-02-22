/* File			:  DetailedStatus.java
 * Project		:  MIFServer
 * Created on	:  Aug 15, 2012
 */
package com.mango.mif.domain;

import javax.xml.bind.JAXBException;
import javax.xml.bind.annotation.XmlRootElement;

import com.mango.mif.utils.JAXBUtils;
import com.mango.mif.utils.jaxb.ExecutionJaxbUtils;

/**
 * @version $Revision: $ as of $Date: $
 * <p>SVN Entry : $HeadURL: $
 * <p>SVN ID    : $Id: $
 * <p>Last edited by : $Author: $
 * 
 * A super class of all detailed statuses
 */
@XmlRootElement(name = "detailedStatus")
public class DetailedStatus {
    /**
     * Request ID
     */
    private String requestId;
    /**
     * Summary
     */
    private String summary;
    
    public String getRequestId() {
        return requestId;
    }
    
    public void setRequestId(String requestId) {
        this.requestId = requestId;
    }

    public String getSummary() {
        return summary;
    }

    public void setSummary(String summary) {
        this.summary = summary;
    }
    
    public String asString() throws JAXBException {
        return JAXBUtils.marshall(this, ExecutionJaxbUtils.CONTEXT_CLASSES);
    }
}
