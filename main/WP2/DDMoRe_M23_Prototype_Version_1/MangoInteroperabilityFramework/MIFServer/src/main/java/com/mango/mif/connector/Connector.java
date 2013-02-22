/* File			:  Connector.java
 * Project		:  MangoInteroperabilityFramework
 * Created on	:  Mar 19, 2012
 */
package com.mango.mif.connector;

import java.util.List;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.ConcurrentMap;
import java.util.concurrent.TimeUnit;

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
import com.google.common.base.Stopwatch;
import com.mango.mif.connector.domain.Job;
import com.mango.mif.connector.runner.api.JobRunner;
import com.mango.mif.connector.runner.api.JobRunnerFactory;
import com.mango.mif.core.impl.MessagingHelper;
import com.mango.mif.domain.ExecutionRequest;
import com.mango.mif.domain.ExecutionResponse;
import com.mango.mif.domain.JobStatus;
import com.mango.mif.exception.MIFException;
import com.mango.mif.managers.JobManagementService;
import com.mango.mif.utils.JAXBUtils;
import com.mango.mif.utils.jaxb.ExecutionJaxbUtils;

/**
 * Generic Connector handles the incoming request from MIF clients and process the requests.
 * Its also responsible for publishing the response back to the clients. 
 * @version $Revision: $ as of $Date: $
 * <p>SVN Entry : $HeadURL: $
 * <p>SVN ID    : $Id: $
 * <p>Last edited by : $Author: $
 */
@Component
public class Connector implements MessageListener{

	private static final Logger LOG = Logger.getLogger(Connector.class);
	
	private String responseQueue;
	private String requestQueue;	
	private String cancellationQueue;	
	private String connectorId;
	
	private JobRunnerFactory jobRunnerFactory;
	private ConcurrentMap<String, JobRunner> jobRunnerRegistry = new ConcurrentHashMap<String, JobRunner>();
	private AsyncTaskExecutor taskExecutor;
		
	private JmsTemplate jmsTemplate;	
	private JobManagementService jobManagementService;	

	/**
	 * Identifies unfinished jobs each time the connector is started and re-runs them.
	 */
	public void doFailureRecovery(){
		
	    Stopwatch watch = new Stopwatch();
	    watch.start();
	    
		LOG.info("Job Recovery (" + connectorId + "): Starting check for unfinished Connector jobs.  Querying database...");
		List<Job> joblist = jobManagementService.getUncompletedDispatcherJobs(getConnectorId()); 
		JobRunner runner = null;
			
		LOG.info("Job Recovery (" + connectorId + ": Found " + joblist.size() + " jobs)");
		
		for (Job job: joblist){
			runner = jobRunnerFactory.createJobRunner(job);

			LOG.debug(runner);

			taskExecutor.submit(runner);
			jobRunnerRegistry.put(job.getJobId(), runner);
			LOG.info("Job Recovery (" + connectorId + "):  Re-submitted persisted job with id " + job.getJobId());
		}			
		long timeTaken = watch.elapsedMillis();
        LOG.info("Job Recovery (" + connectorId + ") took " + timeTaken + " (ms) / " + TimeUnit.MILLISECONDS.toSeconds(timeTaken) + " (secs)");
		watch.stop();
	}

	/**
	 * Consumes the request posted by Navigator , Creates a Job request, 
	 * it then creates a Job Runner to handle the request, It also adds the Job Runner 
	 * to the  Registry  and submits the Job to the Task Executor for execution.
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
			throw new RuntimeException(ConnectorExceptions.INVALID_REQUEST_MESSAGE.getMessage());
		}
		Preconditions.checkArgument(StringUtils.isNotEmpty(receivedText), "Received empty request message");
		LOG.debug("Received the message from client" + receivedText);
		
		String uuid = retrieveExternalJobIdFromRequest(receivedText);
		
		LOG.info(String.format("*** Execution Metrics : {Scheduling and Preparation.1} Received the " +
				"execution request message with request id [%s]", uuid));
		
		executeJobRequest(receivedText, uuid);
	}
	/**
	 * Creates a job and schedules a job runner for the given execution request
	 * 
	 * @param receivedText
	 * @param uuid
	 */
    public void executeJobRequest(String receivedText, String uuid) {
        
        Job job = null;
		if (!jobExists(uuid)) {
			try {
				job = createJobForRequest(receivedText);
			} catch (MIFException e) {
				LOG.error(e);
				throw new RuntimeException("Invalid Execution Request message.", e);
			}
		} else {			
			LOG.warn("A message with " + uuid + " has already been consumed. Job already exists.");
			job = jobManagementService.getJob(uuid);
		}
		
		if(!jobRunnerScheduled(uuid)) {
			scheduleJob(job);
		} else {
			LOG.warn("A job message with " + uuid + " has ALREADY been consumed and is being processed.");
		}
		LOG.info("Job message id: " + uuid + " consumed.");
	}

	/**
	 * Creates a Job Request.
	 *
	 * @param receivedText the received text
	 * @return the job
	 * @throws MIFException the mIF exception
	 */
	protected Job createJobForRequest(String receivedText) throws MIFException {
		Job job = jobManagementService.createNewJob();
		job.setExecutionRequestMsg(receivedText);
		job.setClientRequestStatus(JobStatus.NEW.name());
		job.setConnectorId(getConnectorId());
		job = jobManagementService.saveJob(job);		
		return job;
	}
	/**
	 * Schedules a job for execution
	 * @param executionRequest
	 */
	void scheduleJob(Job job) {
		JobRunner jobRunner = jobRunnerFactory.createJobRunner(job);
		LOG.debug("Created runner for: " + jobRunner);
		
		
		job.setClientRequestStatus(JobStatus.RUNNING.name());
		job = jobManagementService.saveJob(job);
		jobRunner.setJobId(job.getJobId());
		
		LOG.info(String.format("*** Execution Metrics : {Scheduling and Preparation.2} " +
				"Created JobRunner and Job with jobId [%s]", job.getJobId()));
		
		LOG.debug("Persisted Job during RUNNING state: " + job.toString());
		LOG.info("Submitted the job with id " + job.getJobId() + " to the execution service");
        taskExecutor.submit(jobRunner);
        jobRunnerRegistry.put(job.getJobId(), jobRunner);
        
        LOG.info(String.format("*** Execution Metrics : {Scheduling and Preparation.3} " +
        		"Submitted JobRunner with jobId [%s] to task executor", job.getJobId()));
	}

	/**
	 * This message is invoked by the JobRunnerResultListener which consumes the
	 * result message from the Job Runner and delegates the message to the Dispatcher.
	 *
	 * @param jobRunnerResponse the job runner response
	 */
	public synchronized void handleResultMessage(final String jobRunnerResponse) {
		Preconditions.checkNotNull(jobRunnerResponse, "Response message is null");
		final ExecutionResponse response = parseExecutionResponse(jobRunnerResponse);
		String uuid = response.getRequestId();
		Preconditions.checkNotNull(uuid, "Could not retrieve request id from the response message");

		LOG.info("Removing Runner handling the request " + uuid  + " from Registry");
		jobRunnerRegistry.remove(uuid);

		if (jobExists(uuid)) {
			completeJob(response);
		} else {
			String errorMsg = "Received a result message for a request that does not exist. Request ID:" + uuid;
			LOG.error(errorMsg);
			throw new RuntimeException(errorMsg);
		}

		LOG.debug("Sending the following response to the client " + jobRunnerResponse);
		
		LOG.info(String.format("*** Execution Metrics : {ResultHandling.2} Received the message [%s] and " +
				"sending the result [%s] to TaskExecutionResultListener", jobRunnerResponse, uuid));
		
		jmsTemplate.send(getResponseQueue(), new MessageCreator() {
				public Message createMessage(Session session) throws JMSException {
					TextMessage message = session.createTextMessage();
                    message.setText(jobRunnerResponse);
					return message;
				}
		});
	}

	/**
	 * Completes the job
	 * @param uuid
	 */    
	void completeJob(ExecutionResponse executionResponse) {
		
		String uuid = executionResponse.getRequestId().toString();
		
        Job job = jobManagementService.getJob(uuid);
        LOG.debug("Finalizing the " + job.toString() + " with status " + job.getClientRequestStatus());
        JobStatus jobStatus = JobStatus.valueOf(executionResponse.getStatus());
        job.setClientRequestStatus(jobStatus.name());
		if(!jobStatus.isFinal()) {
			job.setClientRequestStatus(JobStatus.FAILED.name());
			executionResponse.setStatus(JobStatus.FAILED.name());
			executionResponse.setResult("Job has been completed not in final state. [ " + jobStatus.name() + " ].");
		}
		//FIXME:  ID 10461:  Temporarily commented this out as workaround for
		//REST service accessing filesystem after job completion - see ticket.
		//jobResponse.setPassword(EMPTY_PASSWORD);
		ExecutionRequest executionRequest = job.getExecutionRequest();
		//FIXME:  ID 10461:  Temporarily commented this out as workaround for
		//REST service accessing filesystem after job completion - see ticket.
		//executionRequest.setUserPassword(EMPTY_PASSWORD);
		try {
			job.setExecutionRequestMsg(JAXBUtils.marshall(executionRequest, ExecutionJaxbUtils.CONTEXT_CLASSES));
		} catch (MIFException e) {
			LOG.error(e);
			throw new RuntimeException("Could not remove user password from execution message", e);
		} catch (JAXBException e) {
			LOG.error(e);
			throw new RuntimeException("Could not remove user password from execution message", e);
		}
		
		// TODO: Do we really want to save the job here or just update the status, or specific parts
		jobManagementService.saveJob(job);
		LOG.debug("The job " + job.toString() + " processing has been finished with status " + job.getClientRequestStatus());
	}

	/**
	 * Checks the database for a job, given the uuid.
	 * @param uuid the uuid of the job passed to the connector in the request message.
	 * @return boolean to indicate if the job is in the database.
	 */
	private boolean jobExists(String uuid) {
		return null != jobManagementService.getJob(uuid);
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
	 * @param requestID the request uid.
	 */
	public synchronized void handleCancelMessage(final String requestID) {
        Preconditions.checkNotNull(requestID, "Requested cancellation of a request with null id");
        LOG.info("Cancelling Job Runner handling the request " + requestID + ".");

        cancelJob(requestID);

        JobRunner jobRunner = getJobRunnerForTheRequest(requestID);
        if (jobRunner == null) {
            LOG.warn("Job Runner for the request " + requestID + " does not exist.");
            return;
        }

        jobRunner.cancelProcessing();
	}

    private void cancelJob(String requestID) {
        Job job = jobManagementService.getJob(requestID);
        job.setClientRequestStatus(JobStatus.CANCELLED.name());
        job = jobManagementService.saveJob(job);

        LOG.debug("Request status has been updated to CANCELLED " + job);
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
	 * Parses execution response
	 * @param jobRunnerResponse
	 * @return
	 */
	private ExecutionResponse parseExecutionResponse(String jobRunnerResponse) {
        return MessagingHelper.parseExecutionResponse(jobRunnerResponse);
	}

	/**
	 * Retrieves the Job ID from the Request.
	 * @param receivedText the received text
	 * @return the request id
	 */
	protected String retrieveExternalJobIdFromRequest(String receivedText) {
		ExecutionRequest request = MessagingHelper.parseExecutionRequest(receivedText);
		return request.getRequestId().toString();
	}
	
	public ConcurrentMap<String, JobRunner> getJobRunnerRegistry() {
		return jobRunnerRegistry;
	}

	public void setJobRunnerRegistry(ConcurrentMap<String, JobRunner> jobRunnerRegistry) {
		this.jobRunnerRegistry = jobRunnerRegistry;
	}
	
    public AsyncTaskExecutor getTaskExecutor() {
        return taskExecutor;
    }

    @Required
    public void setTaskExecutor(AsyncTaskExecutor taskExecutor) {
        this.taskExecutor = taskExecutor;
    }

    public JmsTemplate getJmsTemplate() {
		return jmsTemplate;
	}
    
    @Required
	public void setJmsTemplate(JmsTemplate jmsTemplate) {
		this.jmsTemplate = jmsTemplate;
	}

	public String getResponseQueue() {
		return responseQueue;
	}
    
    @Required
	public void setResponseQueue(String destination) {
		this.responseQueue = destination;
	}

	
	public JobManagementService getJobManagementService() {
		return jobManagementService;
	}

	@Required
	public void setJobManagementService(JobManagementService jobManagementService) {
		this.jobManagementService = jobManagementService;
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

    @Required
	public void setCancellationQueue(String cancellationQueue) {
		this.cancellationQueue = cancellationQueue;
	}

    @Required
	public void setRequestQueue(String requestQueue) {
		this.requestQueue = requestQueue;
	}
	
	public String getCancellationQueue() {
		return cancellationQueue;
	}
	
	public String getRequestQueue() {
		return requestQueue;
	}

    @Required
	public void setConnectorId(String connectorId) {
		this.connectorId = connectorId;
	}
	
	public String getConnectorId() {
		return connectorId;
	}
}
