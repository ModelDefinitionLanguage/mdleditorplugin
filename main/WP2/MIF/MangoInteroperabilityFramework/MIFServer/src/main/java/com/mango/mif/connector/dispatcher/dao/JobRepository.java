/* File			:  JobRepositoryHibernate.java
 * Project		:  persistencemanager
 * Created on	:  3 Feb 2012
 */
package com.mango.mif.connector.dispatcher.dao;

import java.util.List;
import org.springframework.stereotype.Repository;
import com.mango.mif.connector.dispatcher.domain.Job;
import com.mango.mif.connector.dispatcher.domain.JobStatus;
import com.mango.mif.core.dao.impl.GenericDaoHibernate;
import com.mango.mif.core.domain.ExecutionJobValue;

/**
 * This class interacts with Spring's HibernateTemplate to save/delete and
 * retrieve Job objects.
 * @version $Revision: $ as of $Date: $
 * <p>SVN Entry : $HeadURL: $
 * <p>SVN ID    : $Id: $
 * <p>Last edited by : $Author: $
 */
@Repository("jobRepository")
public class JobRepository extends GenericDaoHibernate<Job, String> {
    /**
     * Constructor that sets the entity to Job.class.
     */
    public JobRepository() {
        super(Job.class);
    }
    

    /**
     * Retrieve persisted job records whose state is not COMPLETED on a restart to provide
     * failover functionality.
     * @return List of persisted jobs that are not completed.
     */
    @SuppressWarnings("unchecked")
	public List<Job> getUncompletedJobs(){
    	
    		return getHibernateTemplate().find("from Job where clientRequestStatus !='" + JobStatus.COMPLETED + 
    										   "' and clientRequestStatus != '" + JobStatus.CANCELLED + "'");
    }
    
    /**
     * Reads a job record from the database, given it's job id.  This is required during
     * job state persistence. 
     * @param rejobId the job uid of the job that was passed to the connector dispatcher. 
     * @return Job corresponding to the specified job id.
     */
    public Job getJob(String jobId){
    	String query = "from Job where jobId ='" + jobId + "'";

    	List<Job> jobs = getHibernateTemplate().find(query);
    	
    	if (jobs.size() > 0){
    		return (Job)jobs.get(0);
    	}
    	
    	return null;
    }
    
    


}
