/* File			:  MockMifConnector.java
 * Project		:  MangoInteroperabilityFramework
 * Created on	:  Mar 30, 2012
 */
package com.mango.mif.client.impl;

import java.util.Map;
import java.util.UUID;
import java.util.concurrent.ConcurrentMap;

import javax.jms.JMSException;
import javax.jms.Message;
import javax.jms.MessageListener;
import javax.jms.Session;
import javax.jms.TextMessage;
import javax.xml.bind.JAXBException;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.jms.core.JmsTemplate;
import org.springframework.jms.core.MessageCreator;
import org.springframework.stereotype.Component;

import com.google.common.collect.Maps;
import com.mango.mif.client.err.TaskExecutorExceptions;
import com.mango.mif.core.api.MIFResource;
import com.mango.mif.core.api.ResourceComponent;
import com.mango.mif.core.api.ResourceComponentException;
import com.mango.mif.domain.ExecutionRequest;
import com.mango.mif.domain.ExecutionResponse;
import com.mango.mif.domain.Parameter;
import com.mango.mif.utils.JAXBUtils;
import com.mango.mif.utils.jaxb.ExecutionJaxbUtils;

/**
 * @version $Revision: $ as of $Date: $
 * <p>SVN Entry : $HeadURL: $
 * <p>SVN ID    : $Id: $
 * <p>Last edited by : $Author: $
 */
@Component
public class MockMifConnector implements MessageListener {
	private static final Logger LOG = Logger.getLogger(MockMifConnector.class);

	private static String destinationQueue = "R.JOB.RESPONSE";
	private static String sharedLocation;
	private final static String MOCK_RESULT = "R_RESULT";
    private static String TEST_RESOURCE = "result-test.csv";
	
	/**JobRunner Registry responsible for maintaining the currently executing Job Runners. */
	protected ConcurrentMap<String, String> jobStatusRegistry =Maps.newConcurrentMap();


	@Autowired
	@Qualifier("jmsTemplateMifClient")
	JmsTemplate jmsTemplate;
	
	@Autowired
	@Qualifier("resourceComponent")
	private ResourceComponent resourceComponent;


	@Override
	public synchronized void onMessage(Message message) {
		if (message!= null) {
			TextMessage msg = (TextMessage) message;
			String receivedText = null;
			try {
				receivedText = msg.getText();
			} catch (JMSException e) {
				LOG.error(e);
				throw new RuntimeException(TaskExecutorExceptions.INVALID_REQUEST_MESSAGE.getMessage());
			}
			LOG.info("Received the message from navigator" + receivedText);
			
			String uuid = retriveExternalJobIdFromRequest(receivedText);
			
//			getJobStatusRegistry().put(uuid, JobStatus.STARTED.getMessage());
			
			//mocking the submission of the task to the job runner
			try {
				Thread.sleep(1000);

				//TODO update the DB with the status.
//				getJobStatusRegistry().put(uuid, JobStatus.RUNNING.getMessage());
		
				String processedMessage = processTheReceivedMessage(receivedText);
				//mocking the execution of the task;
				Thread.sleep(3000);
				sendMessage(processedMessage);
//				getJobStatusRegistry().put(uuid, JobStatus.COMPLETED.getMessage());
				
			} catch (InterruptedException e) {
				LOG.error("Interupted ", e);
			}
	
			
			
			
			
			
			
			LOG.info("Submitted the job with id " + uuid +" to the execution service");
		} else {
			throw new RuntimeException(TaskExecutorExceptions.INVALID_REQUEST_MESSAGE.getMessage());
		}
		
		
		try {
			LOG.info("MOCK MIF ::: STARTED CONSUME MESSAGE");
			TextMessage msg = (TextMessage) message;
			String receivedText = msg.getText();
			LOG.info("MOCK MIF ::: MESSSAGE RECEIVED" + receivedText);
			
			
		} catch (JMSException exp) {
			LOG.error("JMSException  " ,exp);
		}
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
			throw new RuntimeException(TaskExecutorExceptions.INVALID_REQUEST_MESSAGE_FORMAT.getMessage());
		}
		if(request.getRequestId() == null) {
			LOG.error("request id is not present in the request" + receivedText);
			throw new RuntimeException(TaskExecutorExceptions.REQUEST_ID_NOT_PRESENT.getMessage());
		}
		return request.getRequestId().toString();
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
			throw new RuntimeException(TaskExecutorExceptions.INVALID_RESPONSE_MESSAGE_FORMAT.getMessage());
		}
		return (request !=null) ? request.getRequestId().toString(): null;
	}

	private String processTheReceivedMessage(final String receivedMessage) {
		ExecutionRequest request = null;
		String executionResponseText = null;
		try {
			request = JAXBUtils.unmarshall(receivedMessage, ExecutionJaxbUtils.EXECUTION_REQUEST_CLASS_ARRAY);
		} catch (JAXBException e) {
			LOG.error("JAXB Exception for the string " +receivedMessage);
		}
		if(request.getRequestId() == null) {
			LOG.error("request id is not present in the request" + receivedMessage);
		}
		
		ExecutionResponse  executionResponse = new ExecutionResponse();
		executionResponse.setRequestId(request.getRequestId());
		executionResponse.setResult(MOCK_RESULT);
		

        Map<String,Parameter> results = Maps.newHashMap();
        try {
            MIFResource resource = resourceComponent.addResponseResource(this.getClass().getResource(TEST_RESOURCE),UUID.randomUUID().toString(), TEST_RESOURCE);
        } catch (ResourceComponentException e) {
            throw new RuntimeException(e);
        }
		
		try {
			executionResponseText = JAXBUtils.marshall(executionResponse);
		} catch (JAXBException e) {
			LOG.error("JAXB Exception for the string " +receivedMessage);
		}
		return executionResponseText;
	}
	
	public JmsTemplate getJmsTemplate() {
		return jmsTemplate;
	}

	public void setJmsTemplate(JmsTemplate jmsTemplate) {
		this.jmsTemplate = jmsTemplate;
	}
	
	private void sendMessage(final String receivedText) {
		jmsTemplate.send(destinationQueue, new MessageCreator() {
			@Override
			public Message createMessage(Session session) throws JMSException {
				TextMessage message1 = session.createTextMessage();
				message1.setText(receivedText);
				return message1;
			}
		});
	}


	public static String getSharedLocation() {
		return sharedLocation;
	}


	public static void setSharedLocation(String sharedLocation) {
		MockMifConnector.sharedLocation = sharedLocation;
	}


	public ConcurrentMap<String, String> getJobStatusRegistry() {
		return jobStatusRegistry;
	}


	public void setJobStatusRegistry(ConcurrentMap<String, String> jobStatusRegistry) {
		this.jobStatusRegistry = jobStatusRegistry;
	}


}

