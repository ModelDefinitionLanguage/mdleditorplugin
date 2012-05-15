/* File			:  ExecutionResponse.java
 * Project		:  MangoInteroperabilityFramework
 * Created on	:  Mar 19, 2012
 */
package com.mango.mif.domain;

import java.util.Map;
import java.util.UUID;

import javax.xml.bind.annotation.XmlElement;
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
	private UUID requestId;
	
	/** The status. */
	private String status;
	
    /** The result. */
    private String result;
    
    /** Input parameters values chosen by user. */
    private Map<String, Parameter> parameters;

    /**
     * Gets the request id.
     *
     * @return the request id
     */
    @XmlElement
	public UUID getRequestId() {
		return requestId;
	}

	/**
	 * Sets the request id.
	 *
	 * @param requestId the new request id
	 */
	public void setRequestId(UUID requestId) {
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
	 * Gets the parameters.
	 *
	 * @return the parameters
	 */
	public Map<String, Parameter> getParameters() {
		return parameters;
	}

	/**
	 * Sets the parameters.
	 *
	 * @param parameters the parameters
	 */
	public void setParameters(Map<String, Parameter> parameters) {
		this.parameters = parameters;
	}
}

