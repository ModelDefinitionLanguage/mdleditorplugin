/* File			:  Dispatcher.java
 * Project		:  MangoInteroperabilityFramework
 * Created on	:  Mar 19, 2012
 */
package com.mango.mif.connector.dispatcher.impl;

import java.io.File;
import java.util.List;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.ConcurrentMap;
import javax.annotation.PostConstruct;
import javax.jms.JMSException;
import javax.jms.Message;
import javax.jms.MessageListener;
import javax.jms.Session;
import javax.jms.TextMessage;
import javax.xml.bind.JAXBException;

import org.apache.commons.lang.StringUtils;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Required;
import org.springframework.core.task.AsyncTaskExecutor;
import org.springframework.jms.core.JmsTemplate;
import org.springframework.jms.core.MessageCreator;
import org.springframework.stereotype.Component;

import com.google.common.base.Preconditions;
import com.mango.mif.connector.dispatcher.dao.JobRepository;
import com.mango.mif.connector.dispatcher.domain.DispatcherExceptions;
import com.mango.mif.connector.dispatcher.domain.Job;
import com.mango.mif.connector.dispatcher.domain.JobStatus;
import com.mango.mif.connector.runner.api.JobRunner;
import com.mango.mif.connector.runner.api.JobRunnerFactory;
import com.mango.mif.core.impl.DefaultWorkspaceManager;
import com.mango.mif.domain.ExecutionRequest;
import com.mango.mif.domain.ExecutionResponse;
import com.mango.mif.exception.MIFException;
import com.mango.mif.utils.JAXBUtils;
import com.mango.mif.utils.jaxb.ExecutionJaxbUtils;

/**
 * Generic Dispatcher handles the incoming request from MIF clients and process the requests.
 * Its also responsible for publishing the response back to the clients. 
 * @version $Revision: $ as of $Date: $
 * <p>SVN Entry : $HeadURL: $
 * <p>SVN ID    : $Id: $
 * <p>Last edited by : $Author: $
 */
@Component
public class Dispatcher implements MessageListener{

	/** The Constant LOG. */
	static final Logger LOG = Logger.getLogger(Dispatcher.class);
	
	/**Default Destination for the response. Each sub class overrides it for its specific destination */
	protected String destination;
	
	/** JobRunnerFactory responsible for creating the Job Runners. */
	protected JobRunnerFactory jobRunnerFactory;
	
	/**JobRunner Registry responsible for maintaining the currently executing Job Runners. */
	protected ConcurrentMap<String, JobRunner> jobRunnerRegistry = new ConcurrentHashMap<String, JobRunner>();

	/** Spring Task executor which is responsible for  scheduling the execution of the Job Runner. */
	protected AsyncTaskExecutor taskExecutor;
	
	/** The working directory. */
	protected File workingDirectory;
	
	/** The jms template. */
	private JmsTemplate jmsTemplate;
	/**
	 * jobs repository
	 */
	protected JobRepository jobRepository;
	/**
	 * String used to clear up the password
	 */
	private final static String EMPTY_PASSWORD = "";

	/**
	 * Identifies unfinished jobs each time the dispatcher is started and re-runs them.
	 */
	@PostConstruct 
	public void doFailureRecovery(){

		LOG.info("Starting failover check for unfinished Dispatcher jobs.  Querying database...");
	
		List<Job> joblist = jobRepository.getUncompletedJobs(); 
		
		JobRunner runner = null;
			
		for (Job job: joblist){
			runner = jobRunnerFactory.createJobRunner(job);

			runner.setWorkspaceManager(new DefaultWorkspaceManager(getJobWorkingDirectory(job)));
			LOG.debug(runner);

			taskExecutor.submit(runner);
			jobRunnerRegistry.put(job.getJobId(), runner);
			LOG.info("Failover recovery:  Re-submitted the persisted job with id " + job.getJobId() +" to the execution service");
			
		}
	}

	/**
	 * Consumes the request posted by Navigator , Creates a Job request, 
	 * it then creates a Job Runner to handle the request, It also adds the Job Runner 
	 * to the  Registry  and submits the Job to the Task Executor for execution.
	 * 
	 *
	 * @param message the message
	 */
	@Override
	public synchronized void onMessage(Message message) {
		Preconditions.checkNotNull(message, "Received null message");
		TextMessage msg = (TextMessage) message;
		String receivedText = null;
		try {
			receivedText = msg.getText();
		} catch (JMSException e) {
			LOG.error(e);
			throw new RuntimeException(DispatcherExceptions.INVALID_REQUEST_MESSAGE.getMessage());
		}
		Preconditions.checkArgument(StringUtils.isNotEmpty(receivedText), "Received empty request message");
		LOG.debug("Received the message from navigator" + receivedText);
		
		String uuid = retriveExternalJobIdFromRequest(receivedText);
		Job job = null;
		if (!jobExists(uuid)) {
			try {
				job = createJobForRequest(receivedText);
			} catch (MIFException e) {
				LOG.error(e);
				throw new RuntimeException("Invalid Execution Request message.", e);
			}
		} else {
			job = jobRepository.getJob(uuid);
			LOG.warn("A message with " + uuid + " has already been consumed. Job already exists.");
		}
		
		if(!jobRunnerScheduled(uuid)) {
			scheduleJob(job);
		} else {
			LOG.warn("A message with " + uuid + " has already been consumed. Job runner has already been scheduled.");
		}
		LOG.info("A message with " + uuid + " has been consumed.");
	}

	/**
	 * Creates a job working directory
	 * @param job
	 * @return
	 */
	File createJobWorkingDirectory(Job job) {
		File dir = getJobWorkingDirectory(job);
		if (!dir.mkdirs()) {
			throw new RuntimeException("Could not create Job Runner working directory: " + dir.getAbsolutePath());
		}
		return dir;
	}

	/**
	 * @param job
	 * @return
	 */
	File getJobWorkingDirectory(Job job) {
		return new File(workingDirectory, job.getJobId());
	}

	/**
	 * Creates a Job Request.
	 *
	 * @param receivedText the received text
	 * @return the job
	 * @throws MIFException the mIF exception
	 */
	protected Job createJobForRequest(String receivedText) throws MIFException {
		Job job = new Job();
		job.setExecutionRequestMsg(receivedText);
		job.setWorkingDirectory(job.getJobId());
		createJobWorkingDirectory(job);
		job.setClientRequestStatus(JobStatus.STARTED.name());
		jobRepository.save(job);
		LOG.debug("Persisted Job during STARTED state: " + job.toString());
		return job;
	}
	/**
	 * Schedules a job for execution
	 * @param executionRequest
	 */
	void scheduleJob(Job job) {
		JobRunner runner = jobRunnerFactory.createJobRunner(job);
		runner.setWorkspaceManager(new DefaultWorkspaceManager(getJobWorkingDirectory(job)));
		LOG.debug(runner);
		taskExecutor.submit(runner);
		jobRunnerRegistry.put(job.getJobId(), runner);
		job.setClientRequestStatus(JobStatus.RUNNING.name());
		jobRepository.save(job);
		LOG.debug("Persisted Job during RUNNING state: " + job.toString());
		LOG.info("Submitted the job with id " + job.getJobId() + " to the execution service");
	}

	/**
	 * This message is invoked by the JobRunnerResultListener which consumes the
	 * result message from the Job Runner and delegates the message to the Dispatcher.
	 *
	 * @param jobRunnerResponse the job runner response
	 * @return the string
	 */
	public synchronized String handleResultMessage(final String jobRunnerResponse) {
		String uuid = null;
		Preconditions.checkNotNull(jobRunnerResponse, "Response message is null");
		uuid = getJobIdFromResponse(jobRunnerResponse);
		Preconditions.checkNotNull(uuid, "Could not retrieve request id from the response message");

		LOG.info("Removing Runner handling the request" + uuid  + "from Registry");
		
		jobRunnerRegistry.remove(uuid);

		if (jobExists(uuid)) {
			completeJob(uuid);
		} else {
			String errorMsg = "Received a result message for a request that does not exist. Request ID:" + uuid;
			LOG.error(errorMsg);
			throw new RuntimeException(errorMsg);
		}
		LOG.debug("Sending the following response to navigator " + jobRunnerResponse);
		
		jmsTemplate.send(getDestination(), new MessageCreator() {
				public Message createMessage(Session session) throws JMSException {
					TextMessage message = session.createTextMessage();
					message.setText(jobRunnerResponse);
					return message;
				}
		});
		return uuid;
	}

	/**
	 * Completes the job
	 * @param uuid
	 */
	void completeJob(String uuid) {
		LOG.info("Setting the status of the request as completed for the " + uuid );

		Job jobResponse = jobRepository.getJob(uuid);
		if(!JobStatus.valueOf(jobResponse.getClientRequestStatus()).isFinal()) {
			jobResponse.setClientRequestStatus(JobStatus.COMPLETED.name());
		}
		jobResponse.setPassword(EMPTY_PASSWORD);
		ExecutionRequest executionRequest = jobResponse.getExecutionRequest();
		executionRequest.setUserPassword(EMPTY_PASSWORD);
		try {
			jobResponse.setExecutionRequestMsg(JAXBUtils.marshall(executionRequest, ExecutionJaxbUtils.EXECUTION_REQUEST_CLASS_ARRAY));
		} catch (MIFException e) {
			LOG.error(e);
			throw new RuntimeException("Could not remove user password from execution message", e);
		} catch (JAXBException e) {
			LOG.error(e);
			throw new RuntimeException("Could not remove user password from execution message", e);
		}
		
		jobRepository.save(jobResponse);
		LOG.debug("Updated Job in database on COMPLETED state: " + jobResponse.toString());
	}

	/**
	 * Checks the database for a job, given the uuid.
	 * @param uuid the uuid of the job passed to the dispatcher in the request message.
	 * @return boolean to indicate if the job is in the database.
	 */
	private boolean jobExists(String uuid) {
		return null != jobRepository.getJob(uuid);
	}

	/**
	 * @param uuid
	 * @return
	 */
	private boolean jobRunnerScheduled(String uuid) {
		return null != getJobRunnerForTheRequest(uuid);
	}
	
	/**
	 * Handles cancel message.
	 *
	 * @param requestUid the request uid.
	 */
	public synchronized void handleCancelMessage(final String requestUid) {
		Preconditions.checkNotNull(requestUid, "Requested cancellation of a request with null id");
		
		JobRunner jobRunner = getJobRunnerForTheRequest(requestUid);
		if(jobRunner==null) {
			LOG.warn("Job Runner for the request " + requestUid + " does not exist.");
			return; 
		}
		
		LOG.info("Cancelling Runner handling the request" + requestUid  + "from Registry");
		
		Job job = jobRepository.getJob(requestUid);
		job.setClientRequestStatus(JobStatus.CANCELLED.name());
		jobRepository.save(job);
		
		jobRunner.cancelProcessing();
		
	}

	/**
	 * Gets the job runner for the request.
	 *
	 * @param requestUid the request uid
	 * @return the job runner for the request
	 */
	private JobRunner getJobRunnerForTheRequest(final String requestUid) {
		Preconditions.checkNotNull(requestUid);
		return jobRunnerRegistry.get(requestUid);
	}
	
	/**
	 * Retrieves the Job ID from the Response of Job Runner.
	 *
	 * @param jobRunnerResponse the job runner response
	 * @return the job id from response
	 */
	protected String getJobIdFromResponse(final String jobRunnerResponse) {
		ExecutionResponse request = null;
		try {
			request = JAXBUtils.unmarshall(ExecutionResponse.class , jobRunnerResponse);
		} catch (JAXBException e) {
			LOG.error("JAXB Exception for the string " + jobRunnerResponse);
			throw new RuntimeException(DispatcherExceptions.INVALID_RESPONSE_MESSAGE_FORMAT.getMessage());
		}
		return (request !=null) ? request.getRequestId().toString(): null;
	}


	/**
	 * Retrieves the Job ID from the Request.
	 *
	 * @param receivedText the received text
	 * @return the string
	 */
	protected String retriveExternalJobIdFromRequest(String receivedText) {
		ExecutionRequest request = null;
		try {
			request = JAXBUtils.unmarshall(receivedText, ExecutionJaxbUtils.EXECUTION_REQUEST_CLASS_ARRAY);
		} catch (JAXBException e) {
			LOG.error("JAXB Exception for the string " , e);
			throw new RuntimeException(DispatcherExceptions.INVALID_REQUEST_MESSAGE_FORMAT.getMessage());
		}
		if(request.getRequestId() == null) {
			LOG.error("request id is not present in the request" + receivedText);
			throw new RuntimeException(DispatcherExceptions.REQUEST_ID_NOT_PRESENT.getMessage());
		}
		return request.getRequestId().toString();
	}
	
	/**
	 * Gets the job runner registry.
	 *
	 * @return the job runner registry
	 */
	public ConcurrentMap<String, JobRunner> getJobRunnerRegistry() {
		return jobRunnerRegistry;
	}

	/**
	 * Sets the job runner registry.
	 *
	 * @param jobRunnerRegistry the job runner registry
	 */
	public void setJobRunnerRegistry(ConcurrentMap<String, JobRunner> jobRunnerRegistry) {
		this.jobRunnerRegistry = jobRunnerRegistry;
	}
	
    /**
     * Gets the task executor.
     *
     * @return the task executor
     */
    public AsyncTaskExecutor getTaskExecutor() {
        return taskExecutor;
    }

    /**
     * Sets the task executor.
     *
     * @param taskExecutor the new task executor
     */
    @Required
    public void setTaskExecutor(AsyncTaskExecutor taskExecutor) {
        this.taskExecutor = taskExecutor;
    }

    /**
     * Gets the jms template.
     *
     * @return the jms template
     */
    public JmsTemplate getJmsTemplate() {
		return jmsTemplate;
	}
    
    /**
     * Sets the jms template.
     *
     * @param jmsTemplate the new jms template
     */
    @Required
	public void setJmsTemplate(JmsTemplate jmsTemplate) {
		this.jmsTemplate = jmsTemplate;
	}

	/**
	 * Gets the destination.
	 *
	 * @return the destination
	 */
	public String getDestination() {
		return destination;
	}
    
    /**
     * Sets the destination.
     *
     * @param destination the new destination
     */
    @Required
	public void setDestination(String destination) {
		this.destination = destination;
	}

	/**
	 * Gets the job runner factory.
	 *
	 * @return the job runner factory
	 */
	public JobRunnerFactory getJobRunnerFactory() {
		return jobRunnerFactory;
	}
    
    /**
     * Sets the job runner factory.
     *
     * @param jobRunnerFactory the new job runner factory
     */
    @Required
	public void setJobRunnerFactory(JobRunnerFactory jobRunnerFactory) {
		this.jobRunnerFactory = jobRunnerFactory;
	}

	/**
	 * Sets the working directory.
	 *
	 * @param workingDirectory the new working directory
	 */
	@Required
    public void setWorkingDirectory(File workingDirectory) {
		Preconditions.checkNotNull(workingDirectory, "Working directory can't be null.");
		if(!workingDirectory.exists()) {
			LOG.warn("Working directory :" + workingDirectory + " does not exist. Creating it.");
			Preconditions.checkArgument(workingDirectory.getParentFile().canWrite(),"Can't create working directory " + workingDirectory + " - can't write to parent directory.");
			workingDirectory.mkdirs();
		}
		Preconditions.checkArgument(workingDirectory.canWrite(),"Can't write to working directory " + workingDirectory);
        this.workingDirectory = workingDirectory;
    }

	/**
	 * @return the jobRepository
	 */
	public JobRepository getJobRepository() {
		return jobRepository;
	}

	/**
	 * @param jobRepository the jobRepository to set
	 */
	@Required
	public void setJobRepository(JobRepository jobRepository) {
		this.jobRepository = jobRepository;
	}

}
