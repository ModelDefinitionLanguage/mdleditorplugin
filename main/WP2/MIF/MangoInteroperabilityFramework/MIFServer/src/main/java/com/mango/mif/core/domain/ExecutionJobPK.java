/* File			:  ExecutionJobPK.java
 * Project		:  MIFServer
 * Created on	:  5 Jun 2012
 */
package com.mango.mif.core.domain;

import java.io.Serializable;
import javax.persistence.Embeddable;
import javax.persistence.ManyToOne;


/**
 * Serves as the composite key for the JobRuntimeData 
 * @version $Revision: $ as of $Date: $
 * <p>SVN Entry : $HeadURL: $
 * <p>SVN ID    : $Id: $
 * <p>Last edited by : $Author: $
 */
@Embeddable
public class ExecutionJobPK implements Serializable {
	
	public ExecutionJobPK(){
		
	}

	public ExecutionJobPK(String jobId, String key) {
		this.jobId = jobId;
		this.key = key;
	}

	private static final long	serialVersionUID	= 1L;

	/**
	 * This is the uuid set by the dispatcher for the job.
	 */
	@ManyToOne
	private String				jobId;

	/**
	 * This is the job runtime data key, e.g. "STATUS"
	 */
	@ManyToOne
	private String				key;

	/**
	 * @return the jobId
	 */
	public String getJobId() {
		return jobId;
	}

	/**
	 * @param jobId the jobId to set
	 */
	public void setJobId(String jobId) {
		this.jobId = jobId;
	}

	/**
	 * @return the key
	 */
	public String getKey() {
		return key;
	}

	/**
	 * @param key the key to set
	 */
	public void setKey(String key) {
		this.key = key;
	}

	/* (non-Javadoc)
	 * @see java.lang.Object#hashCode()
	 */
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((jobId == null) ? 0 : jobId.hashCode());
		result = prime * result + ((key == null) ? 0 : key.hashCode());
		return result;
	}

	/* (non-Javadoc)
	 * @see java.lang.Object#equals(java.lang.Object)
	 */
	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		ExecutionJobPK other = (ExecutionJobPK) obj;
		if (jobId == null) {
			if (other.jobId != null)
				return false;
		} else if (!jobId.equals(other.jobId))
			return false;
		if (key == null) {
			if (other.key != null)
				return false;
		} else if (!key.equals(other.key))
			return false;
		return true;
	}
}