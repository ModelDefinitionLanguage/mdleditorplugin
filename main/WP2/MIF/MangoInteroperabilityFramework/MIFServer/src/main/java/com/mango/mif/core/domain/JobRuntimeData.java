/* File			:  JobRuntimeData.java
 * Project		:  MangoInteroperabilityFramework
 * Created on	:  13 Mar 2012
 */
package com.mango.mif.core.domain;

import java.io.Serializable;
import javax.persistence.AttributeOverride;
import javax.persistence.AttributeOverrides;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.Id;
import javax.persistence.IdClass;
import javax.persistence.OneToOne;
import javax.persistence.Table;
import javax.persistence.Transient;

import com.google.common.base.Objects;

/**
 * The execution job that is to be run by the JobRunner.
 * 
 * @version $Revision: $ as of $Date: $
 * <p>SVN Entry : $HeadURL: $
 * <p>SVN ID    : $Id: $
 * <p>Last edited by : $Author: $
 * 
 */
//FIXME:  Move the composite key metadata into the new Job.data Map and get rid of this entity/table.
@Entity
@Table(name = "JOB_RUNTIME_DATA")
@IdClass(ExecutionJobPK.class)
public class JobRuntimeData implements Serializable {

	private static final long	serialVersionUID	= 1L;

	/**
	 * This gets populated with the original generated uuid from the dispatcher.
	 */

    private String jobId;

    /**
     * This specifies the type of Key used to map the jobs.  This will vary from external tool
     * to tool.  For R Execution we will use STATE.  This means that the values we store 
     * against the Keys for a job will be populated with the current state of the jobs.
     */
    private String key;

    /**
     * This gets populated with the current state of the job.   Different tools will use
     * different keys.  R Execution  will just use STATE (of the job_) as the key.  The values
     * could be PREPARING, FINISHING etc...
     */
    private String value;
   
    public JobRuntimeData(){
    	
    }
    
    public JobRuntimeData(ExecutionJobPK pk) {
		jobId = pk.getJobId();
		key = pk.getKey();
	}
    
	@Id
	@AttributeOverrides({
	@AttributeOverride(name = "jobId",
	column = @Column(name="JOB_ID")),
	@AttributeOverride(name = "key",
	column = @Column(name="KEY"))
	})
	
	/**
	 * @return the jobId
	 */
	public String getJobId() {
		return this.jobId;
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
		return this.key;
	}

	/**
	 * @param key the key to set
	 */
	public void setKey(String key) {
		this.key = key;
	}

	/**
	 * @return the value
	 */
	public String getValue() {
		return value;
	}

	/**
	 * @param value the value to set
	 */
	public void setValue(String value) {
		this.value = value;
	}

	@Override
	public int hashCode() {
		return Objects.hashCode(jobId, key, value);
	}
	
	@Override
	public boolean equals(final Object obj){
	    if(obj instanceof JobRuntimeData){
	        final JobRuntimeData other = (JobRuntimeData) obj;
	        return Objects.equal(jobId, other.jobId)
	            && Objects.equal(key, other.key)
	            && Objects.equal(value, other.value);
	    } else{
	        return false;
	    }
	}

	@Override
    public String toString() {

		StringBuilder result = new StringBuilder();
		result.append("JobRuntimeData {\n");
		//result.append("id = ").append(this.id).append(",\n");
		result.append("jobId = ").append(this.jobId).append(",\n");
		result.append("key = ").append(this.key).append(",\n");
		result.append("value = ").append(this.value).append(",\n");
		result.append(" }");
		return result.toString();
	}

}
