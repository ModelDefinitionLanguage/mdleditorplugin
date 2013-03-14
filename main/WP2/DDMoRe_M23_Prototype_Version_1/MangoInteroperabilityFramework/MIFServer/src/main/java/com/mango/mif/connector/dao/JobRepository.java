/* File			:  JobRepositoryHibernate.java
 * Project		:  persistencemanager
 * Created on	:  3 Feb 2012
 */
package com.mango.mif.connector.dao;

import java.util.Calendar;
import java.util.List;
import org.hibernate.Query;
import org.springframework.stereotype.Repository;
import com.mango.mif.connector.domain.Job;
import com.mango.mif.connector.runner.impl.JobRunnerState;
import com.mango.mif.core.dao.impl.GenericDaoImpl;
import com.mango.mif.domain.JobStatus;

/**
 * This class interacts with Spring's HibernateTemplate to save/delete and
 * retrieve Job objects.
 * @version $Revision: $ as of $Date: $
 * <p>SVN Entry : $HeadURL: $
 * <p>SVN ID    : $Id: $
 * <p>Last edited by : $Author: $
 */
@Repository("jobRepository")
public class JobRepository extends GenericDaoImpl<Job, String> {	
	
    private static final int MILLIS_PER_DAY = 86400000;
    
    /**
     * The number of days over which to lookup acknowledged jobs.
     */
    private static final int ACKNOWLEDGED_JOBS_DAY_RANGE = 10;

	/**
     * Constructor that sets the entity to Job.class.
     */
    public JobRepository() {
        super(Job.class);
    }
   
    @Override
    public Job get(String id) {
    	return getJob(id);
    }
    
    /**
     * Retrieve persisted job records whose state is not COMPLETED on a restart to provide
     * failover functionality.
     * @return List of persisted jobs that are not completed.
     */
    @SuppressWarnings("unchecked")
	public List<Job> getUncompletedDispatcherJobs(){
    	
    	List<Job> jobs = null;

		String queryString = "from Job where clientRequestStatus != ? and clientRequestStatus != ? and clientRequestStatus != ? ";

		Query query = getSessionFactory().getCurrentSession().createQuery(queryString);
		
		query.setParameter(0, JobStatus.COMPLETED.toString());
		query.setParameter(1, JobStatus.CANCELLED.toString());
        query.setParameter(2, JobStatus.FAILED.toString());
		
		jobs = query.list();
		    	
    	return jobs;
    }
    
    
    /**
     * Retrieve persisted job records whose state is not COMPLETED on a restart to provide failure recovery.
     * @return List of persisted jobs that are not completed.
     */
    @SuppressWarnings("unchecked")

	public List<Job> getUncompletedDispatcherJobs(String connectorId){

    	List<Job> jobs = null;

		String queryString = "from Job where clientRequestStatus != ? and clientRequestStatus != ? and clientRequestStatus != ? and connectorId = ?";

		Query query = getSessionFactory().getCurrentSession().createQuery(queryString);
		
		query.setParameter(0, JobStatus.COMPLETED.toString());
		query.setParameter(1, JobStatus.CANCELLED.toString());
        query.setParameter(2, JobStatus.FAILED.toString());
		query.setParameter(3, connectorId);
		
		jobs = query.list();
		
    	return jobs;
    }

    /**
     * Get uncompleted job runner jobs.
     * @return a list of uncompleted jobs
     */
    @SuppressWarnings("unchecked")
	public List<Job> getUncompletedJobRunnerJobs(){
    	
    	List<Job> jobs = null;
		String queryString = "from Job job where job.data['STATUS'] not in (?,?,?)";
		Query query = getSessionFactory().getCurrentSession().createQuery(queryString);
		query.setParameter(0, JobRunnerState.FAILED.toString());
		query.setParameter(1, JobRunnerState.FINISHED.toString());
		query.setParameter(2, JobRunnerState.CANCELLED.toString());
		jobs = query.list();
		return jobs;
    }
	    
    /**
     * Reads a job record from the database, given it's job id.  This is required during
     * job state persistence. 
     * @param jobId the job uid of the job that was passed to the connector dispatcher. 
     * @return Job corresponding to the specified job id.
     */
    @SuppressWarnings("unchecked")
	public Job getJob(String jobId){
    	
    	Job job = null;
		String queryString = "from Job where jobId = ?";
		Query query = getSessionFactory().getCurrentSession().createQuery(queryString);
		query.setParameter(0, jobId);
		List<Job> jobs = query.list();
    	
    	if (jobs.size() > 0){
    		job = (Job)jobs.get(0);    		
    		return job;
    	}
    	
    	return null;
    }
    
    /**
     * Acknowledge a job by setting the acknowledged property on the Job.
     * @param jobId
     */
    public void acknowledgeJob(String jobId) {
    	
    	Job job = getJob(jobId);
    	
    	if (null != job){
    		job.setAcknowledged(Calendar.getInstance().getTimeInMillis());
    		save(job);
    	}
    }
    
    /**
     * Gets all jobs whose acknowledged field is not null and has an acknowledged
     * date before the date provided but after upToTime minus a configurable number of days.
     * @return List of acknowledged jobs identified within the time frame
     */
    public String[] getAcknowledgedJobsToDate(Long uptoTime){

    	Long fromDate = uptoTime - (ACKNOWLEDGED_JOBS_DAY_RANGE * MILLIS_PER_DAY);
	    String queryString = "select JOB_ID from JOBS where TIME_ACKNOWLEDGED is not null and TIME_ACKNOWLEDGED < ? and TIME_ACKNOWLEDGED > ?";
	    Query query = getSessionFactory().getCurrentSession().createSQLQuery(queryString);
	    query.setParameter(0, uptoTime);
		query.setParameter(1, fromDate );
		
		List<Object> results = query.list();
		String[] jobIds = new String[results.size()];
		jobIds = (String[]) query.list().toArray(jobIds);

	    return jobIds;
	        
    }
    
    /**
     *  Takes a date and return all jobs whos acknowledged field is not null.
     * @param dateTimeInMillis the date on which we look for acknowledged jobs.
     * @return List of acknowledged jobs for date specified.
     */
    public List<Job> getAcknowledgedJobsForDate(Long dateTimeInMillis){

    	long timeInMillisAtMidnight = getDayStart(dateTimeInMillis);
    	
    	List<Job> jobs = null;
		String queryString = "from Job job where job.acknowledged is not null and job.acknowledged > ? and job.acknowledged < ?";
		Query query = getSessionFactory().getCurrentSession().createQuery(queryString);
		query.setParameter(0, timeInMillisAtMidnight);
		query.setParameter(1, timeInMillisAtMidnight + MILLIS_PER_DAY);
		jobs = query.list();
		return jobs;
    }

    /**
     * Gets the time at midnight of the day of the linux datetime in millis passed in.
     * @param dateTimeInMillis the datetime in millis for which we want the datetime 
     * in millis at midnight the same day.
     * @return long linus datetime in millis at midnight.
     */
	private long getDayStart(Long dateTimeInMillis) {
		//Get the time at mignight of this linux datetime
    	Calendar dateTime = Calendar.getInstance();
    	dateTime.setTimeInMillis(dateTimeInMillis);
    	dateTime.set(Calendar.HOUR_OF_DAY, 0);
    	dateTime.set(Calendar.MINUTE, 0);
    	dateTime.set(Calendar.SECOND, 0);
    	dateTime.set(Calendar.MILLISECOND, 0);
    	long timeInMillisAtMidnight = dateTime.getTimeInMillis();
		return timeInMillisAtMidnight;
	}
    
    @Override
    public Job save(Job job) {
    	return super.save(job);
    }         
}
