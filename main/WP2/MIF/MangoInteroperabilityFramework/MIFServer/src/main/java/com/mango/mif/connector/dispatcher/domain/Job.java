/* File			:  Job.java
 * Project		:  MangoInteroperabilityFramework
 * Created on	:  13 Mar 2012
 */
package com.mango.mif.connector.dispatcher.domain;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Lob;
import javax.persistence.Table;
import javax.persistence.Transient;
import javax.xml.bind.JAXBException;
import javax.xml.bind.annotation.XmlTransient;

import com.mango.mif.domain.ExecutionRequest;
import com.mango.mif.utils.JAXBUtils;
import com.mango.mif.utils.jaxb.ExecutionJaxbUtils;

/**
 * The job that is to be run.
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
	
	@Id
	@Column(name = "ID")
	@GeneratedValue
	@XmlTransient
	private Integer id;
    /**
     * Job Id
     */
    private String jobId;
    /**
     * The message that contains details of the Job
     */

    @Lob
    @Column(columnDefinition="TEXT (4000)")
    protected String executionRequestMsg;
    /**
     * Status of the request
     */
    private String clientRequestStatus;
    /**
     * working directory assigned to the Job
     */
    private String workingDirectory;
    /**
     * Execution request
     */
    @Transient
    private ExecutionRequest executionRequest;
    
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
            executionRequest = JAXBUtils.unmarshall(executionRequestMsg,ExecutionJaxbUtils.EXECUTION_REQUEST_CLASS_ARRAY);
        } catch (JAXBException e) {
            throw new MIFException("Could not unmarshall execution message.",e);
        }
    }
    
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

	public ExecutionRequest getExecutionRequest()  {
		return executionRequest;
	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}
    
}
