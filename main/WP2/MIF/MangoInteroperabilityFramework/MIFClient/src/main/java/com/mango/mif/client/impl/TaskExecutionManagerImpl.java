/* File			:  TaskExecutionManagerImpl.java
 * Project		:  MangoInteroperabilityFramework
 * Created on	:  Mar 27, 2012
 */
package com.mango.mif.client.impl;

import java.io.StringReader;
import java.util.Map;

import javax.jms.JMSException;
import javax.jms.Message;
import javax.jms.Session;
import javax.jms.TextMessage;
import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Unmarshaller;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.jms.core.JmsTemplate;
import org.springframework.jms.core.MessageCreator;
import org.springframework.stereotype.Component;

import com.google.common.base.Preconditions;
import com.google.common.base.Strings;
import com.mango.mif.client.api.TaskExecutionManager;
import com.mango.mif.client.api.TaskExecutionMessageHandler;
import com.mango.mif.client.err.TaskExecutorExceptions;
import com.mango.mif.domain.ExecutionRequest;
import com.mango.mif.domain.ExecutionResponse;
import com.mango.mif.utils.JAXBUtils;
import com.mango.mif.utils.jaxb.ExecutionJaxbUtils;

/**
 * This class would be responsible for managing the requests and responses that are submitted/received to the MIF. 
 * @version $Revision: $ as of $Date: $
 * <p>SVN Entry : $HeadURL: $
 * <p>SVN ID    : $Id: $
 * <p>Last edited by : $Author: $
 */
@Component
@Qualifier("taskExecutionManager")
public class TaskExecutionManagerImpl implements TaskExecutionManager {
	
	/** The Constant LOG. */
	static final Logger LOG = Logger.getLogger(TaskExecutionManagerImpl.class);
	
	/** The jms template. */
	@Autowired
	@Qualifier("jmsTemplate")
	JmsTemplate jmsTemplate;
	
	/** The List Of Queues for Connector Registry. */
	protected Map<String, String> connectorRegistry;

	/**
	 * Task Execution Message handler that is notified on the messages
	 */
	private TaskExecutionMessageHandler executionMessageHandler;
	/**
	 * Checks if the passed String Message is of correct ExecutionRequest format and also contains the request ID.
	 * Submits the message onto the destinationQueue.
	 *
	 * @param executionRequest the execution request
	 * @see com.mango.mif.client.TaskExecutionManager#submit(java.lang.String)
	 */
	public synchronized void submit(final String executionRequest) {
		Preconditions.checkNotNull(executionRequest, TaskExecutorExceptions.INVALID_REQUEST_MESSAGE.getMessage());
        Preconditions.checkNotNull(executionMessageHandler, "Task Execution Manager not configured.");
		LOG.info("Received Message "+ executionRequest );
		ExecutionRequest request = null;
		try {
			request = JAXBUtils.unmarshall(executionRequest, ExecutionJaxbUtils.EXECUTION_REQUEST_CLASS_ARRAY);
		} catch (JAXBException e) {
			LOG.error("JAXB Exception", e);
			throw new RuntimeException(TaskExecutorExceptions.INVALID_REQUEST_MESSAGE_FORMAT.getMessage());
		}
		
		submitRequest(executionRequest, request);
	}

	/**
	 * Marshalls the request object and creates a contains the request ID.
	 * Submits the message onto the destinationQueue.
	 *
	 * @param executionRequest the execution request
	 * @see com.mango.mif.client.TaskExecutionManager#submit(java.lang.String)
	 */
	@Override
	public synchronized void submit(final ExecutionRequest request) {
		Preconditions.checkNotNull(request, TaskExecutorExceptions.INVALID_REQUEST_MESSAGE.getMessage());
		String executionRequest = null;
		try {
			executionRequest = JAXBUtils.marshall(request, ExecutionJaxbUtils.EXECUTION_REQUEST_CLASS_ARRAY);
		} catch (JAXBException e) {
			LOG.error("JAXB Exception", e);
			throw new RuntimeException(TaskExecutorExceptions.INVALID_REQUEST_MESSAGE.getMessage());
		}
		submitRequest(executionRequest, request);
	}


	
	/**
	 * Handles result message from Execution Environment.
	 *
	 * @param responseReceived the response received
	 * @return the string
	 */
	public synchronized String handleResultMessage(final String responseReceived) {
		String uuid = null;
		Preconditions.checkNotNull(executionMessageHandler, "Task Execution Manager not configured.");
		Preconditions.checkNotNull(responseReceived, TaskExecutorExceptions.INVALID_RESPONSE_MESSAGE.getMessage());
		LOG.info("Response Message "+ responseReceived );
		uuid = getJobIdFromResponse(responseReceived);
		if (uuid == null) {
			throw new RuntimeException(TaskExecutorExceptions.REQUEST_ID_NOT_PRESENT.getMessage());
		}
		LOG.info("Sending the following response to navigator " + responseReceived);
		JAXBContext context;
        ExecutionResponse  response= null;
        try {
            context = JAXBContext.newInstance(ExecutionResponse.class);
            Unmarshaller um = context.createUnmarshaller();
            response = (ExecutionResponse)um.unmarshal(new StringReader(responseReceived));
        } catch (JAXBException e) {
            LOG.error("JAXB Exception " + e );
        }

        executionMessageHandler.handle(response);
        
		return uuid;
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
			throw new RuntimeException(TaskExecutorExceptions.INVALID_RESPONSE_MESSAGE_FORMAT.getMessage());
		}
		return (request !=null) ? request.getRequestId().toString(): null;
	}
	
	private void submitRequest(final String executionRequest,
			ExecutionRequest request) {
		if (request.getRequestId() == null) {
			throw new RuntimeException(TaskExecutorExceptions.REQUEST_ID_NOT_PRESENT.getMessage());
		}
		
		if(Strings.isNullOrEmpty(getConnectorRegistry().get(request.getType()))) {
			throw new RuntimeException(TaskExecutorExceptions.CONNECTOR_NOT_PRESENT.getMessage());
		}
		LOG.info("Submitting the Request With UID "+ request.getRequestId() + " for Execution to the Desination " + getConnectorRegistry().get(request.getType()));
		
		jmsTemplate.send(getConnectorRegistry().get(request.getType()), new MessageCreator() {
			public Message createMessage(Session session) throws JMSException {
				TextMessage message = session.createTextMessage();
				message.setText(executionRequest);
				return message;
			}
		});
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
	public void setJmsTemplate(JmsTemplate jmsTemplate) {
		this.jmsTemplate = jmsTemplate;
	}

	public Map<String, String> getConnectorRegistry() {
		return connectorRegistry;
	}

	public void setConnectorRegistry(Map<String, String> connectorRegistry) {
		this.connectorRegistry = connectorRegistry;
	}
	
	@Override
    public void setExecutionMessageHandler(TaskExecutionMessageHandler executionMessageHandler) {
        this.executionMessageHandler = executionMessageHandler;
    }
    
    public TaskExecutionMessageHandler getExecutionMessageHandler() {
        return executionMessageHandler;
    }
	
}
