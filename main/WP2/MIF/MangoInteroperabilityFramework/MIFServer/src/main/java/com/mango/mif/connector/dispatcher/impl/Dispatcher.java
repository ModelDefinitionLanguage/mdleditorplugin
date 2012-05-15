/* File			:  Dispatcher.java
 * Project		:  MangoInteroperabilityFramework
 * Created on	:  Mar 19, 2012
 */
package com.mango.mif.connector.dispatcher.impl;

import java.io.File;
import java.util.UUID;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.ConcurrentMap;

import javax.annotation.PostConstruct;
import javax.jms.JMSException;
import javax.jms.Message;
import javax.jms.MessageListener;
import javax.jms.Session;
import javax.jms.TextMessage;
import javax.xml.bind.JAXBException;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Required;
import org.springframework.core.task.AsyncTaskExecutor;
import org.springframework.jms.core.JmsTemplate;
import org.springframework.jms.core.MessageCreator;

import com.google.common.base.Preconditions;
import com.mango.mif.connector.dispatcher.domain.DispatcherExceptions;
import com.mango.mif.connector.dispatcher.domain.Job;
import com.mango.mif.connector.dispatcher.domain.JobStatus;
import com.mango.mif.connector.dispatcher.domain.MIFException;
import com.mango.mif.connector.runner.api.JobRunner;
import com.mango.mif.connector.runner.api.JobRunnerFactory;
import com.mango.mif.core.impl.DefaultWorkspaceManager;
import com.mango.mif.domain.ExecutionRequest;
import com.mango.mif.domain.ExecutionResponse;
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
public class Dispatcher implements MessageListener{

	static final Logger LOG = Logger.getLogger(Dispatcher.class);
	
	/**Default Destination for the response. Each sub class overrides it for its specific destination */
	protected String destination;
	
	/**JobRunnerFactory responsible for creating the Job Runners */
	protected JobRunnerFactory jobRunnerFactory;
	
	/**JobRunner Registry responsible for maintaining the currently executing Job Runners. */
	protected ConcurrentMap<String, JobRunner> jobRunnerRegistry = new ConcurrentHashMap<String, JobRunner>();

	//TODO replace the logic with persistence
	/**JobRunner Registry responsible for maintaining the currently executing Job Runners. */
	protected ConcurrentMap<String, String> jobStatusRegistry = new ConcurrentHashMap<String, String>();

	/** Spring Task executor which is responsible for  scheduling the execution of the Job Runner. */
	protected AsyncTaskExecutor taskExecutor;
	
	protected File workingDirectory;
	
	JmsTemplate jmsTemplate;
	
	/**
	 * Consumes the request posted by Navigator , Creates a Job request, 
	 * it then creates a Job Runner to handle the request, It also adds the Job Runner 
	 * to the  Registry  and submits the Job to the Task Executor for execution.
	 * 
	 */
	@Override
	public synchronized void onMessage(Message message) {
		if (message!= null) {
			TextMessage msg = (TextMessage) message;
			String receivedText = null;
			try {
				receivedText = msg.getText();
			} catch (JMSException e) {
				LOG.error(e);
				throw new RuntimeException(DispatcherExceptions.INVALID_REQUEST_MESSAGE.getMessage());
			}
			LOG.info("Received the message from navigator" + receivedText);
			
			String uuid = retriveExternalJobIdFromRequest(receivedText);
			
			Job requestJob = null;
            try {
                requestJob = createJobRequest(receivedText);
            } catch (MIFException e) {
                throw new RuntimeException("Invalid Execution Request message.",e);
            }
			
			JobRunner runner = getJobRunnerFactory().createJobRunner(requestJob);
			File jobRunnerWD = new File(workingDirectory, requestJob.getJobId());
			if(!jobRunnerWD.mkdirs()) {
			    throw new RuntimeException("Could not create Job Runner working directory: " + jobRunnerWD.getAbsolutePath());
			}
			runner.setWorkspaceManager(new DefaultWorkspaceManager(jobRunnerWD));
			LOG.info(runner);
			
			//TODO update the DB with the status.
			getJobStatusRegistry().put(uuid, JobStatus.STARTED.getMessage());
			
			taskExecutor.submit(runner);

			getJobRunnerRegistry().put(uuid, runner);
			
			//TODO update the DB with the status.
			getJobStatusRegistry().put(uuid, JobStatus.RUNNING.getMessage());
	
			taskExecutor.submit(runner);
	
			LOG.info("Submitted the job with id " + uuid +" to the execution service");
		} else {
			throw new RuntimeException(DispatcherExceptions.INVALID_REQUEST_MESSAGE.getMessage());
		}
	}

	/**
	 * This message is invoked by the JobRunnerResultListener which consumes the
	 * result message from the Job Runner and delegates the message to the Dispatcher.  
	 * @param jobRunnerResponse
	 * @return
	 */
	public synchronized String handleResultMessage(final String jobRunnerResponse) {
		String uuid = null;
		if (jobRunnerResponse == null) {
			throw new RuntimeException(DispatcherExceptions.INVALID_RESPONSE_MESSAGE.getMessage());
		}
		uuid = getJobIdFromResponse(jobRunnerResponse);
		if (uuid == null) {
			throw new RuntimeException(DispatcherExceptions.REQUEST_ID_NOT_PRESENT.getMessage());
		}
		if (getJobRunnerRegistry().containsKey(uuid)) {
			LOG.info("Removing Runner handling the request" + uuid  + "from Registry");
			getJobRunnerRegistry().remove(uuid);
		}
		//TODO update the DB with the completion status.
		if (getJobStatusRegistry().containsKey(uuid)) {
			LOG.info("Adding the status of the request as completed for the " + uuid );
			getJobStatusRegistry().put(uuid, JobStatus.COMPLETED.getMessage());
		}
		LOG.info("Sending the following response to navigator " + jobRunnerResponse);
		
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
	 * Retrieves the Job ID from the Response of Job Runner.
	 * @param jobRunnerResponse
	 * @return
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
	 * Creates a Job Request.
	 * @param receivedText
	 * @return
	 * @throws MIFException 
	 */
	protected Job createJobRequest(String receivedText) throws MIFException {
		Job requestJob = new Job();
		requestJob.setJobId(UUID.randomUUID().toString());
		requestJob.setExecutionRequestMsg(receivedText);
		requestJob.setWorkingDirectory(requestJob.getJobId());
		return requestJob;
	}

	/**
	 * Retrieves the Job ID from the Request.
	 * @param receivedText
	 * @return
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

	public String getDestination() {
		return destination;
	}
    @Required
	public void setDestination(String destination) {
		this.destination = destination;
	}

	public JobRunnerFactory getJobRunnerFactory() {
		return jobRunnerFactory;
	}
    @Required
	public void setJobRunnerFactory(JobRunnerFactory jobRunnerFactory) {
		this.jobRunnerFactory = jobRunnerFactory;
	}

	public ConcurrentMap<String, String> getJobStatusRegistry() {
		return jobStatusRegistry;
	}

	public void setJobStatusRegistry(ConcurrentMap<String, String> jobStatusRegistry) {
		this.jobStatusRegistry = jobStatusRegistry;
	}
	@Required
    public void setWorkingDirectory(File workingDirectory) {
        this.workingDirectory = workingDirectory;
    }
}
