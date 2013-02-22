/* File			:  TaskExecutionManagerImpl.java
 * Project		:  MangoInteroperabilityFramework
 * Created on	:  Mar 27, 2012
 */
package com.mango.mif.client.impl;

import java.io.StringReader;
import java.util.Map;
import java.util.UUID;

import javax.jms.JMSException;
import javax.jms.Message;
import javax.jms.Session;
import javax.jms.TextMessage;
import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Unmarshaller;

import org.apache.commons.lang.StringUtils;
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
import com.mango.mif.client.domain.QueueNameLocators;
import com.mango.mif.client.err.TaskExecutorExceptions;
import com.mango.mif.domain.ExecutionRequest;
import com.mango.mif.domain.ExecutionResponse;
import com.mango.mif.exception.MIFException;
import com.mango.mif.utils.JAXBUtils;
import com.mango.mif.utils.jaxb.ExecutionJaxbUtils;

/**
 * This class would be responsible for managing the requests and responses that
 * are submitted/received to the MIF.
 * 
 * @version $Revision: $ as of $Date: $
 *          <p>
 *          SVN Entry : $HeadURL: $
 *          <p>
 *          SVN ID : $Id: $
 *          <p>
 *          Last edited by : $Author: $
 */
@Component
@Qualifier("taskExecutionManager")
public class TaskExecutionManagerImpl implements TaskExecutionManager {

	/** The Constant LOG. */
	static final Logger LOG = Logger.getLogger(TaskExecutionManagerImpl.class);

	/** The jms template. */
	@Autowired
	@Qualifier("jmsTemplate")
	private JmsTemplate jmsTemplate;

	/** The List Of Queues for Connector Registry. */
	protected Map<String, String> connectorRegistry;

	/**
	 * Task Execution Message handler that is notified on the messages
	 */
	private TaskExecutionMessageHandler executionMessageHandler;

	/**
	 * Checks if the passed String Message is of correct ExecutionRequest format
	 * and also contains the request ID. Submits the message onto the
	 * destinationQueue.
	 * 
	 * @param executionRequest
	 *            the execution request
	 * @see com.mango.mif.client.TaskExecutionManager#submit(java.lang.String)
	 */
	@Override
	public synchronized void submit(final String executionRequest)	throws MIFException {
		checkIfRequestMessageIsNull(executionRequest);
		checkIfExecutionMessageHandlerIsNull(executionMessageHandler);
		LOG.debug("Received Message " + executionRequest);
		ExecutionRequest request = null;
		try {
			request = JAXBUtils.unmarshall(executionRequest, ExecutionJaxbUtils.CONTEXT_CLASSES);
		} catch (JAXBException e) {
			throw new MIFException(TaskExecutorExceptions.INVALID_REQUEST_MESSAGE_FORMAT.getMessage(), e);
		}
		submitRequest(executionRequest, request);
	}

	/**
	 * Marshalls the request object and and submits the request.
	 * Submits the message onto the destinationQueue.
	 * 
	 * @param executionRequest
	 *            the execution request
	 * @throws MIFException
	 * @see com.mango.mif.client.TaskExecutionManager#submit(java.lang.String)
	 */
	@Override
	public synchronized void submit(final ExecutionRequest request) throws MIFException {
		checkIFRequestisNull(request);

		String executionRequest = null;
		try {
			executionRequest = JAXBUtils.marshall(request, ExecutionJaxbUtils.CONTEXT_CLASSES);
		} catch (JAXBException e) {
			throw new MIFException(TaskExecutorExceptions.INVALID_REQUEST_MESSAGE.getMessage(), e);
		}
		submitRequest(executionRequest, request);
	}

	/**
	 * Handles result message from Execution Environment.
	 * 
	 * @param responseReceived the response received
	 * @return job id from response
	 * @throws MIFException if the job id is null or there is an error in un-marshalling the response
	 */
	@Override
	public String handleResultMessage(final String responseReceived) throws MIFException {
		String uuid = null;
		checkIfExecutionMessageHandlerIsNull(executionMessageHandler);
		Preconditions.checkNotNull(responseReceived, TaskExecutorExceptions.INVALID_RESPONSE_MESSAGE.getMessage());
		LOG.info("Response Message " + responseReceived);
		uuid = getJobIdFromResponse(responseReceived);
		if (uuid == null) {
			throw new MIFException(TaskExecutorExceptions.REQUEST_ID_NOT_PRESENT.getMessage());
		}
		LOG.debug("Sending the following response to navigator " + responseReceived);
		ExecutionResponse response = null;
		try {
			JAXBContext context = JAXBContext.newInstance(ExecutionResponse.class);
			Unmarshaller um = context.createUnmarshaller();
			response = (ExecutionResponse) um.unmarshal(new StringReader(responseReceived));
		} catch (JAXBException e) {
			throw new MIFException(TaskExecutorExceptions.INVALID_RESPONSE_MESSAGE_FORMAT.getMessage(), e);
		}
		LOG.info(String.format("*** Execution Metrics : {ResponseHandling.2} Received the message [%s] and delegating the response [%s] to TaskExecutionMessageHandler", 
        		responseReceived, response.getRequestId()));
		executionMessageHandler.handle(response);
		return uuid;
	}

	private void checkIFRequestisNull(final ExecutionRequest request) throws MIFException {
		if (request == null) {
			throw new MIFException(TaskExecutorExceptions.INVALID_REQUEST_MESSAGE.getMessage());
		}
	}

	private void checkIfRequestMessageIsNull(final String executionRequest)	throws MIFException {
		if (StringUtils.isBlank(executionRequest)) {
			throw new MIFException(TaskExecutorExceptions.INVALID_REQUEST_MESSAGE.getMessage());
		}
	}

	/**
	 * Retrieves the Job ID from the Response of Job Runner.
	 * 
	 * @param jobRunnerResponse
	 *            the job runner response
	 * @return the job id from response
	 * @throws MIFException
	 */
	protected String getJobIdFromResponse(final String jobRunnerResponse)
			throws MIFException {
		ExecutionResponse request = null;
		try {
			request = JAXBUtils.unmarshall(ExecutionResponse.class,	jobRunnerResponse);
		} catch (JAXBException e) {
			throw new MIFException(TaskExecutorExceptions.INVALID_RESPONSE_MESSAGE_FORMAT.getMessage(), e);
		}
		return (request != null) ? request.getRequestId().toString() : null;
	}

	/**
	 * Submit request.
	 *
	 * @param executionRequest the execution request
	 * @param request the request
	 * @throws MIFException the mIF exception
	 */
	private void submitRequest(final String executionRequest,
			ExecutionRequest request) throws MIFException {
	    
	    if (jmsTemplate == null) {
	        throw new MIFException("JMSTemplate is null, cannot execute task");	        
	    }
	    
		if (request.getRequestId() == null) {
			throw new MIFException(TaskExecutorExceptions.REQUEST_ID_NOT_PRESENT.getMessage());
		}
		
		String requestQueueName = QueueNameLocators.REQUEST_QUEUE.getQueueName();

		LOG.info("Submitting the Request With UID " + request.getRequestId()
				+ " for Execution to the Desination " + getConnectorRegistry().get(requestQueueName));

		checkIfConnectorIsPresent(requestQueueName);
		
		LOG.info(String.format("*** Execution Metrics : {Submission.5} Sending the execution request message with request id [%s] to [%s]", 
        		request.getRequestId(), requestQueueName));
		
		try {
			jmsTemplate.send(getConnectorRegistry().get(requestQueueName),
					new MessageCreator() {
						@Override
						public Message createMessage(Session session)
								throws JMSException {
							TextMessage message = session.createTextMessage();
							message.setText(executionRequest);
							return message;
						}
			});
		} catch( Exception exp) {
			throw new MIFException(TaskExecutorExceptions.ERROR_DURING_SUBMISSION.getMessage(), exp);
		}
	}

	/**
	 * Check if connector is present.
	 *
	 * @param requestQueueName the request queue name
	 * @throws MIFException the mIF exception
	 */
	private void checkIfConnectorIsPresent(String requestQueueName)	throws MIFException {
		if (Strings.isNullOrEmpty(getConnectorRegistry().get(requestQueueName))) {
			throw new MIFException(TaskExecutorExceptions.CONNECTOR_NOT_PRESENT.getMessage());
		}
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
	 * @param jmsTemplate
	 *            the new jms template
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
	public void setExecutionMessageHandler(
			TaskExecutionMessageHandler executionMessageHandler) {
		this.executionMessageHandler = executionMessageHandler;
	}

	public TaskExecutionMessageHandler getExecutionMessageHandler() {
		return executionMessageHandler;
	}

	@Override
	public void cancelRequest(final UUID requestUid) throws MIFException{
		if(requestUid == null) {
			throw new MIFException("Provided Request UID is null");
		}
		String cancelQueueName = getConnectorRegistry().get(QueueNameLocators.CANCEL_QUEUE.getQueueName());
		if(StringUtils.isBlank(cancelQueueName)) {
			throw new MIFException("Queue Name cannot be located for the Cancellation for the request " + requestUid);
		}
		jmsTemplate.send(cancelQueueName, new MessageCreator() {
				@Override
				public Message createMessage(Session session) throws JMSException {
					TextMessage message = session.createTextMessage();
					message.setText(requestUid.toString());
					return message;
				}
			}
		);
	}

    @Override
    public void cancelRequest(final String requestUid) throws MIFException {
        if(requestUid == null) {
            throw new MIFException("Provided Request UID is null");
        }
        String cancelQueueName = getConnectorRegistry().get(QueueNameLocators.CANCEL_QUEUE.getQueueName());
        if(StringUtils.isBlank(cancelQueueName)) {
            throw new MIFException("Queue Name cannot be located for the Cancellation for the request " + requestUid);
        }
        jmsTemplate.send(cancelQueueName, new MessageCreator() {
                @Override
                public Message createMessage(Session session) throws JMSException {
                    TextMessage message = session.createTextMessage();
                    message.setText(requestUid);
                    return message;
                }
            }
        );
    }

    @Override
    public void acknowledgeResponse(final String requestUid) throws MIFException {
        if(requestUid == null) {
            throw new MIFException("Provided Request UID is null");
        }
        String ackQueueName = getConnectorRegistry().get(QueueNameLocators.ACK_QUEUE.getQueueName());
        if(StringUtils.isBlank(ackQueueName)) {
            throw new MIFException("Queue Name cannot be located for the acknowledgement of the response for request " + requestUid);
        }
        jmsTemplate.send(ackQueueName, new MessageCreator() {
                @Override
                public Message createMessage(Session session) throws JMSException {
                    TextMessage message = session.createTextMessage();
                    message.setText(requestUid);
                    return message;
                }
            }
        );
    }
	/**
	 * Check if execution message handler is null.
	 *
	 * @param execuMessageHandler the execu message handler
	 * @throws MIFException the mIF exception
	 */
	private void checkIfExecutionMessageHandlerIsNull(TaskExecutionMessageHandler execuMessageHandler) throws MIFException {
		if (execuMessageHandler == null) {
			throw new MIFException("Task Execution Manager not configured");
		}

	}

}
