/* File			:  RequestHandlerTest.java
 * Project		:  MangoInteroperabilityFramework
 * Created on	:  Mar 27, 2012
 */
package com.mango.mif.client.impl;

import static org.hamcrest.CoreMatchers.equalTo;
import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.assertThat;
import static org.junit.Assert.fail;
import static org.mockito.Matchers.eq;
import static org.mockito.Mockito.doThrow;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.spy;
import static org.mockito.Mockito.verify;

import java.io.StringWriter;
import java.util.HashMap;
import java.util.Map;
import java.util.UUID;

import javax.jms.JMSException;
import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Marshaller;

import org.apache.log4j.Logger;
import org.junit.Before;
import org.junit.Test;
import org.mockito.Mockito;
import org.springframework.jms.core.JmsTemplate;
import org.springframework.jms.core.MessageCreator;

import com.mango.mif.client.api.TaskExecutionMessageHandler;
import com.mango.mif.client.domain.MIFSoftwareNames;
import com.mango.mif.client.domain.QueueNameLocators;
import com.mango.mif.client.err.TaskExecutorExceptions;
import com.mango.mif.domain.ExecutionRequest;
import com.mango.mif.domain.ExecutionRequestBuilder;
import com.mango.mif.domain.ExecutionResponse;
import com.mango.mif.exception.MIFException;
import com.mango.mif.utils.JAXBUtils;

/**
 * Test for {@link TaskExecutionManagerImpl}.
 * 
 * 
 * @version $Revision: $ as of $Date: $
 * <p>SVN Entry : $HeadURL: $
 * <p>SVN ID    : $Id: $
 * <p>Last edited by : $Author: $
 */
public class TaskExecutionManagerImplTest {
	static final Logger LOG = Logger.getLogger(TaskExecutionManagerImplTest.class);

	private static final String JOB_REQUEST_QUEUE = "JOB.REQUEST";
	
	JmsTemplate jmsTemplate;
	
	String uuid = null;
	
	TaskExecutionManagerImpl taskExecutionHand = null;
	
	Map<String, String> connectorRegistry ;
	
	String executionRequestAsString =  null;
	
	ExecutionRequest executionRequest = null;
	
	@Before
	public void before() throws JAXBException {
	    uuid = UUID.randomUUID().toString();
		ExecutionRequestBuilder requestBuilder = new ExecutionRequestBuilder().setExecutionType(MIFSoftwareNames.R_COMMAND_LINE.name()).setRequestId(uuid);
		executionRequestAsString = requestBuilder.getExecutionRequestMsg();
		executionRequest = requestBuilder.getExecutionRequest();
		
		jmsTemplate = mock(JmsTemplate.class);
		connectorRegistry = spy(new HashMap<String, String>(2));
		
		taskExecutionHand = new TaskExecutionManagerImpl();
        taskExecutionHand.setExecutionMessageHandler(mock(TaskExecutionMessageHandler.class));

		taskExecutionHand.setJmsTemplate(jmsTemplate);
		taskExecutionHand.setConnectorRegistry(connectorRegistry);
		connectorRegistry.put(QueueNameLocators.REQUEST_QUEUE.getQueueName(), JOB_REQUEST_QUEUE);
	}

	@Test
	public void shouldSubmitTheRequestToTheCorrectDestination() throws MIFException {
		//Creating connector queues
		taskExecutionHand.setConnectorRegistry(connectorRegistry);
		
		taskExecutionHand.submit(executionRequestAsString);
		
		verify(jmsTemplate).send(eq(JOB_REQUEST_QUEUE), Mockito.<MessageCreator>anyObject());
	}

	@Test
	public void shouldThrowMIFExceptionWhenThereIsProblemDuringSubmission() throws JMSException, MIFException {
		doThrow(new RuntimeException()).when(jmsTemplate).send( Mockito.<String>anyObject(),  Mockito.<MessageCreator>anyObject());
		
		taskExecutionHand.setConnectorRegistry(connectorRegistry);
		
		try {
		taskExecutionHand.submit(executionRequestAsString);
		} catch (MIFException runExp) {
			assertThat("Request Id must be present", runExp.getMessage(), is(equalTo(TaskExecutorExceptions.ERROR_DURING_SUBMISSION.getMessage())));
		}
				
	}	
	@Test
	public void shouldThrowExceptionWhenImproperFormatMessageIsSent() throws JMSException {
		String inCorrectRequest = "Improper message";
		try {
			taskExecutionHand.submit(inCorrectRequest);
			fail();
		} catch (MIFException runExp) {
			assertThat("Incorrect Request format", runExp.getMessage(), is(equalTo(TaskExecutorExceptions.INVALID_REQUEST_MESSAGE_FORMAT.getMessage())));
		}
	}
	
	@Test
	public void shouldThrowExceptionWhenRequestIdIsNotpresentInRequest() throws JMSException, JAXBException {
	    ExecutionRequest request = new ExecutionRequest();
	   
		executionRequestAsString = JAXBUtils.marshall(request);
		try {
			taskExecutionHand.submit(executionRequestAsString);
			fail();
		} catch (MIFException runExp) {
			assertThat("Request Id must be present", runExp.getMessage(), is(equalTo(TaskExecutorExceptions.REQUEST_ID_NOT_PRESENT.getMessage())));
		}
	}
	
	
	@Test
	public void shouldThrowExceptionWhenInvalidRequestIsSent() {
		try {
			taskExecutionHand.handleResultMessage("Some Invalid response");
			fail();
		} catch (MIFException runExp) {
			assertThat("Request Id must be present", runExp.getMessage(), is(equalTo(TaskExecutorExceptions.INVALID_RESPONSE_MESSAGE_FORMAT.getMessage())));
		}
	}
	
	
	@Test
	public void shouldThrowExceptionWhenNullMessageIsSent_submit() throws JMSException {
		ExecutionRequest inCorrectRequest = null;
		try {
			taskExecutionHand.submit(inCorrectRequest);
			fail();
		} catch (MIFException runExp) {
			assertThat("Invalid Message", runExp.getMessage(), is(equalTo(TaskExecutorExceptions.INVALID_REQUEST_MESSAGE.getMessage())));
		}
	}
    
    @Test(expected=MIFException.class)
    public void shouldThrowExceptionWhenNoExecutionManagerHandlerIsSet_submit() throws MIFException {
        ExecutionRequest inCorrectRequest = null;
        taskExecutionHand.setExecutionMessageHandler(null);
        taskExecutionHand.submit(inCorrectRequest);
    }
    
    @Test
	public void shouldHandleTheCancellation() throws MIFException {
    	String cancelQueue = QueueNameLocators.CANCEL_QUEUE.getQueueName();
		connectorRegistry.put(cancelQueue, cancelQueue);
		taskExecutionHand.setConnectorRegistry(connectorRegistry);
		
		taskExecutionHand.cancelRequest(UUID.fromString(uuid));
		
		verify(jmsTemplate).send(eq(cancelQueue), Mockito.<MessageCreator>anyObject());
	}
	
    @Test(expected=MIFException.class)
    public void shouldThrowExceptionWhenSoftwareNameIsNull() throws MIFException {
    	taskExecutionHand.cancelRequest(uuid);
    }
	
    
	/**
	 * Creates the Response Object. 
	 * Marshalls the Response to a string.
	 * @return
	 */
	public static String buildResponseMessage(String uuid)  {
		StringWriter sw = new StringWriter();
		ExecutionResponse response = new ExecutionResponse();
		response.setRequestId(uuid);
		response.setStatus("FINISHED");
		JAXBContext context;
		try {
			context = JAXBContext.newInstance(ExecutionResponse.class);
			Marshaller m = context.createMarshaller();
			m.setProperty(Marshaller.JAXB_FORMATTED_OUTPUT, Boolean.TRUE);
			
			m.marshal(response, sw);
			LOG.info("Sending Request " + sw.toString());
		} catch (JAXBException e) {
			LOG.error("JAXB Exception " ,e );
		}
		return sw.toString();
	}

	
	
	
}
