/* File			:  ExecutionResponse.java
 * Project		:  MangoInteroperabilityFramework
 * Created on	:  Mar 19, 2012
 */
package com.mango.mif.domain;

import java.util.List;

import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlElementWrapper;
import javax.xml.bind.annotation.XmlRootElement;

/**
 * The Class ExecutionResponse.
 *
 * @version $Revision: $ as of $Date: $
 * <p>SVN Entry : $HeadURL: $
 * <p>SVN ID    : $Id: $
 * <p>Last edited by : $Author: $
 */
@XmlRootElement(name = "executionResponse")
public class ExecutionResponse {

	/** The request id. */
	private String requestId;
	
	/** The status. */
	private String status;
	
    /** The result. */
    private String result;
    
    /** detailed result message */
    private String resultDetails;
    
    /** Final Summary Message */
    private String summaryMessage;
    
    /** 
     * The files contained in a grid folder.  
     */
    private List<String> jobFiles;

    /**
     * Gets the request id.
     *
     * @return the request id
     */
    @XmlElement
	public String getRequestId() {
		return requestId;
	}

	/**
	 * Sets the request id.
	 *
	 * @param requestId the new request id
	 */
	public void setRequestId(String requestId) {
		this.requestId = requestId;
	}

	/**
	 * Gets the status.
	 *
	 * @return the status
	 */
	@XmlElement
	public String getStatus() {
		return status;
	}

	/**
	 * Sets the status.
	 *
	 * @param status the new status
	 */
	public void setStatus(String status) {
		this.status = status;
	}
	 
 	/**
 	 * Gets the result.
 	 *
 	 * @return the result
 	 */
 	@XmlElement
    public String getResult() {
        return result;
    }
    
    /**
     * Sets the result.
     *
     * @param result the new result
     */
    public void setResult(String result) {
        this.result = result;
    }

	
	/**
	 * @return the jobFiles
	 */
	@XmlElementWrapper(name="jobfiles")
	@XmlElement(name="jobfile")
	public List<String> getJobFiles() {
		return jobFiles;
	}

	
	/**
	 * @param jobFiles the jobFiles to set
	 */
	public void setJobFiles(List<String> jobFiles) {
		this.jobFiles = jobFiles;
	}

	@XmlElement
	public String getResultDetails() {
		return resultDetails;
	}

	public void setResultDetails(String resultDetails) {
		this.resultDetails = resultDetails;
	}

	/**
	 * @return the final summary message for this execution
	 */
	@XmlElement
	public String getSummaryMessage() {
		return summaryMessage;
	}

	/**
	 * @param summaryMessage The final summary message for this execution
	 */
	public void setSummaryMessage(String summaryMessage) {
		this.summaryMessage = summaryMessage;
	}	
}