/* File			:  MIFResponse.java
 * Project		:  MIFClient
 * Created on	:  Aug 23, 2012
 */
package com.mango.mif.client.api.rest;

import javax.xml.bind.annotation.XmlRootElement;

/**
 * @version $Revision: $ as of $Date: $
 * <p>SVN Entry : $HeadURL: $
 * <p>SVN ID    : $Id: $
 * <p>Last edited by : $Author: $
 * 
 * A super class for MIF REST responses
 * 
 */
@XmlRootElement(name = "mifResponse")
public class MIFResponse {
    /**
     * Error message (if any)
     */
    private String errorMessage;
    /**
     * Status of the execution
     */
    private ResponseStatus status  = ResponseStatus.SUCCESS;
    
    public void setErrorMessage(String errorMessage) {
        this.errorMessage = errorMessage;
    }
    
    public String getErrorMessage() {
        return errorMessage;
    }
    
    public void setStatus(ResponseStatus status) {
        this.status = status;
    }
    
    public ResponseStatus getStatus() {
        return status;
    }
}
