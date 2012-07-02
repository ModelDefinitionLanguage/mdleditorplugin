/* File			:  JobRepositoryHibernate.java
 * Project		:  MIFServer
 * Created on	:  15 May 2012
 */
package com.mango.mif.core.dao;

import java.util.List;
import org.springframework.stereotype.Repository;
import com.mango.mif.core.dao.impl.GenericDaoHibernate;
import com.mango.mif.core.domain.JobRuntimeData;

/**
 * This class interacts with Spring's HibernateTemplate to save/delete and
 * retrieve JobRuntimeData objects.
 * @version $Revision: $ as of $Date: $
 * <p>SVN Entry : $HeadURL: $
 * <p>SVN ID    : $Id: $
 * <p>Last edited by : $Author: $
 */
@Repository("executionJobRepository")
public class ExecutionJobRepository extends GenericDaoHibernate<JobRuntimeData, Long> {
    /**
     * Constructor that sets the entity to JobRuntimeData.class.
     */
    public ExecutionJobRepository() {
        super(JobRuntimeData.class);
    }
    

	public List<JobRuntimeData> getUncompletedJobs(){

    	return getHibernateTemplate().find("from JobRuntimeData where key = 'STATUS' and value not in ('FINISHED','CANCELLED','FAILED','PAUSED')");

    
    
    }
    
    /**
     * Reads an execution job record from the database, given it's request uid.  This is required during
     * job state persistence. NOTE:  We are using HibernateTemplate here still because we had issues with Session / transaction
     * binding and due to the urgency of this component being completed, we opted to use HibernateTemplate for now as it works.
	 * We have made a note to revisit this DAO later to refactor to use Session.
     * @param requestId the uuid of the execution job that was passed to the job runner
     * from the connector dispatcher. 
     * @return JobRuntimeData corresponding to the specified uuid.
     */
    public JobRuntimeData getExecutionJobByRequestUuid(String requestId, String key){

    	String query = "from JobRuntimeData where jobId ='" + requestId + "' and key = 'STATUS' ";
    	List<JobRuntimeData> jobs = getHibernateTemplate().find(query);
    	
    	if (jobs.size() > 0){
    		return (JobRuntimeData)jobs.get(0);
    	}
    	
    	return null;	
    }
}
