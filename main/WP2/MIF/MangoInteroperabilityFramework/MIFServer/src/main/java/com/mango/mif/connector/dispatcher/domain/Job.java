/* File			:  Job.java
 * Project		:  MangoInteroperabilityFramework
 * Created on	:  13 Mar 2012
 */
package com.mango.mif.connector.dispatcher.domain;

import java.util.HashMap;
import java.util.Map;


import javax.persistence.Column;

import javax.persistence.Entity;
import javax.persistence.Id;

import javax.persistence.Lob;
import javax.persistence.Table;
import javax.persistence.Transient;
import javax.xml.bind.JAXBException;
import javax.xml.bind.annotation.XmlTransient;

import org.hibernate.annotations.CollectionOfElements;

import com.google.common.base.Objects;

import com.mango.mif.domain.ExecutionRequest;
import com.mango.mif.exception.MIFException;
import com.mango.mif.utils.JAXBUtils;
import com.mango.mif.utils.jaxb.ExecutionJaxbUtils;

/**
 * The job that is to be run by the Connector Dispatcher.
 * 
 * @version $Revision: $ as of $Date: $
 * <p>SVN Entry : $HeadURL: $
 * <p>SVN ID    : $Id: $
 * <p>Last edited by : $Author: $
 * 
 */
@Entity
@Table(name = "JOBS")
public class Job {

	public static final int		EXECUTION_MSG_MAX_LEN	= 5000;

	@Id
	@Column(name = "job_id")
	@XmlTransient
	private String				jobId;

	@CollectionOfElements
	private Map<String,String> data = new HashMap<String, String>();

	/**
	 * The message that contains details of the Job
	 */
	@Lob
	@Column(name = "execution_request_message", length = EXECUTION_MSG_MAX_LEN)
	protected String			executionRequestMsg;

	/**
	 * Status of the request
	 */
	private String				clientRequestStatus;

	/**
	 * working directory assigned to the Job
	 */
	private String				workingDirectory;

	/**
	 * Execution request
	 */
	@Transient
	private ExecutionRequest	executionRequest;
	
	/**
	 * Username associated with the Job.
	 */
	private String				userName;
	
	/**
	 * Password associated with the job.
	 */
	private String				password;


	public String getJobId() {
		return jobId;
	}

	public void setJobId(String jobId) {
		this.jobId = jobId;
	}

	public String getExecutionRequestMsg() {
		return executionRequestMsg;
	}

	public void setExecutionRequestMsg(String executionRequestMsg) throws MIFException {
		this.executionRequestMsg = executionRequestMsg;
		try {
			executionRequest = JAXBUtils.unmarshall(executionRequestMsg, ExecutionJaxbUtils.EXECUTION_REQUEST_CLASS_ARRAY);
		} catch (JAXBException e) {
			throw new MIFException("Could not unmarshall execution message.", e);
		}
		
		setJobId(executionRequest.getRequestId().toString());
		setUserName(executionRequest.getUserName());
		setPassword(executionRequest.getUserPassword());
	}

	@Column(name = "request_status")
	public String getClientRequestStatus() {
		return clientRequestStatus;
	}

	public void setClientRequestStatus(String clientRequestStatus) {
		this.clientRequestStatus = clientRequestStatus;
	}

	public String getWorkingDirectory() {
		return workingDirectory;
	}

	public void setWorkingDirectory(String workingDirectory) {
		this.workingDirectory = workingDirectory;
	}

	public ExecutionRequest getExecutionRequest() {
		if(executionRequest==null) {
			try {
				executionRequest = JAXBUtils.unmarshall(executionRequestMsg, ExecutionJaxbUtils.EXECUTION_REQUEST_CLASS_ARRAY);
			} catch (JAXBException e) {
				throw new RuntimeException("Could not unmarshal request!",e);
			}
		}
		return executionRequest;
	}

	/**
	 * @return the userName
	 */
	@Column(name = "user_name")
	public String getUserName() {
		return userName;
	}

	
	/**
	 * @param userName the userName to set
	 */
	public void setUserName(String userName) {
		this.userName = userName;
	}

	
	/**
	 * @return the password
	 */
	@Column(name = "password")
	public String getPassword() {
		return password;
	}

	
	/**
	 * @param password the password to set
	 */
	public void setPassword(String password) {
		this.password = password;
	}

	/**
	 * @return the data
	 */
	public Map<String, String> getData() {
		return data;
	}

	/**
	 * @param data the data to set
	 */
	public void setData(Map<String, String> data) {
		this.data = data;
	}

	/**
	 * @param data the data to set
	 */
	public void addToDataMap(String key, String value) {
		this.data.put(key, value);
	}

	@Override
	public int hashCode() {
		return Objects.hashCode(executionRequestMsg, clientRequestStatus, workingDirectory, userName);
	}

	@Override
	public boolean equals(final Object obj) {
		if (obj instanceof Job) {
			final Job other = (Job) obj;
			return Objects.equal(executionRequestMsg, other.executionRequestMsg)
				&& Objects.equal(clientRequestStatus, other.clientRequestStatus) 
				&& Objects.equal(workingDirectory, other.workingDirectory)
				&& Objects.equal(userName, other.userName);
		} else {
			return false;
		}
	}

	@Override
	public String toString() {

		StringBuilder result = new StringBuilder();
		result.append("Job {\n");
		result.append("jobId = ").append(this.jobId).append(",\n");
		result.append("executionRequestMsg = ").append(this.executionRequestMsg).append(",\n");
		result.append("clientRequestStatus = ").append(this.clientRequestStatus).append(",\n");
		result.append("workingDirectory = ").append(this.workingDirectory).append(",\n");
		result.append("userName = ").append(this.userName).append(",\n");
		result.append(" }");
		return result.toString();
	}

}
