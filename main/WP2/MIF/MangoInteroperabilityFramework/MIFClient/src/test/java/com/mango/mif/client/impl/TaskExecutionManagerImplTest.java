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
import com.mango.mif.client.err.TaskExecutorExceptions;
import com.mango.mif.domain.ExecutionRequest;
import com.mango.mif.domain.ExecutionResponse;
import com.mango.mif.utils.ExecutionUtilsTest;

/**
 * Test for {@link TaskExecutionManagerImpl}.
 * @version $Revision: $ as of $Date: $
 * <p>SVN Entry : $HeadURL: $
 * <p>SVN ID    : $Id: $
 * <p>Last edited by : $Author: $
 */
public class TaskExecutionManagerImplTest {
	static final Logger LOG = Logger.getLogger(TaskExecutionManagerImplTest.class);

	private static final String R_SOFTWARE = "R";
	private static final String JOB_REQUEST_QUEUE = "JOB.REQUEST";
	
	JmsTemplate jmsTemplate;
	
	String uuid = null;
	
	TaskExecutionManagerImpl taskExecutionHand = null;
	
	Map<String, String> connectorRegistry ;
	
	String executionRequestAsString =  null;
	
	ExecutionRequest executionRequest = null;
	
	@Before
	public void before() {
		UUID randomUUID = UUID.randomUUID();
		executionRequestAsString = ExecutionUtilsTest.buildMessage(randomUUID);
		executionRequest = ExecutionUtilsTest.getExecutionReq(randomUUID, null);
		uuid =  randomUUID.toString();
		
		jmsTemplate = mock(JmsTemplate.class);
		connectorRegistry = spy(new HashMap<String, String>(2));
		
		taskExecutionHand = new TaskExecutionManagerImpl();
        taskExecutionHand.setExecutionMessageHandler(mock(TaskExecutionMessageHandler.class));

		taskExecutionHand.setJmsTemplate(jmsTemplate);
		taskExecutionHand.setConnectorRegistry(connectorRegistry);
		connectorRegistry.put(R_SOFTWARE, JOB_REQUEST_QUEUE);
	}

	@Test
	public void shouldSubmitTheRequestToTheCorrectDestination() {
		//Creating connector queues
		taskExecutionHand.setConnectorRegistry(connectorRegistry);
		
		taskExecutionHand.submit(executionRequestAsString);
		
		verify(jmsTemplate).send(eq(JOB_REQUEST_QUEUE), Mockito.<MessageCreator>anyObject());
	}

	@Test
	public void shouldThrowExceptionIfTheConnectorRegistryDoesNotHaveTheValueForTheKeyProvided() {
		//Build the request with Invalid Software
		String invalidSoftwareType = "INVALID SOFTAWARE";
		UUID randomUUID = UUID.randomUUID();
		executionRequestAsString = ExecutionUtilsTest.buildMessage(randomUUID, invalidSoftwareType);
		
		taskExecutionHand.setConnectorRegistry(connectorRegistry);
		
		//SUT
		try {
			taskExecutionHand.submit(executionRequestAsString);
			fail();
		} catch (RuntimeException runExp) {
			assertThat("Message is null", runExp.getMessage(), is(equalTo(TaskExecutorExceptions.CONNECTOR_NOT_PRESENT.getMessage())));
		}
	}
	
	
	@Test
	public void shouldThrowExceptionWhenImproperFormatMessageIsSent() throws JMSException {
		String inCorrectRequest = "Improper message";
		try {
			taskExecutionHand.submit(inCorrectRequest);
			fail();
		} catch (RuntimeException runExp) {
			assertThat("Incorrect Request format", runExp.getMessage(), is(equalTo(TaskExecutorExceptions.INVALID_REQUEST_MESSAGE_FORMAT.getMessage())));
		}
	}
	
	@Test
	public void shouldThrowExceptionWhenRequestIdIsNotpresentInRequest() throws JMSException {
		executionRequestAsString = ExecutionUtilsTest.buildMessage(null, null);
		try {
			taskExecutionHand.submit(executionRequestAsString);
			fail();
		} catch (RuntimeException runExp) {
			assertThat("Request Id must be present", runExp.getMessage(), is(equalTo(TaskExecutorExceptions.REQUEST_ID_NOT_PRESENT.getMessage())));
		}
	}
	
	
	@Test
	public void shouldThrowExceptionWhenInvalidRequestIsSent() {
		try {
			taskExecutionHand.handleResultMessage("Some Invalid response");
			fail();
		} catch (RuntimeException runExp) {
			assertThat("Request Id must be present", runExp.getMessage(), is(equalTo(TaskExecutorExceptions.INVALID_RESPONSE_MESSAGE_FORMAT.getMessage())));
		}
	}
	
	
	@Test
	public void shouldThrowExceptionWhenNullMessageIsSent_submit() throws JMSException {
		ExecutionRequest inCorrectRequest = null;
		try {
			taskExecutionHand.submit(inCorrectRequest);
			fail();
		} catch (RuntimeException runExp) {
			assertThat("Invalid Message", runExp.getMessage(), is(equalTo(TaskExecutorExceptions.INVALID_REQUEST_MESSAGE.getMessage())));
		}
	}
    
    @Test(expected=NullPointerException.class)
    public void shouldThrowExceptionWhenNoExecutionManagerHandlerIsSet_submit() throws JMSException {
        ExecutionRequest inCorrectRequest = null;
        taskExecutionHand.setExecutionMessageHandler(null);
        taskExecutionHand.submit(inCorrectRequest);
    }
	
	
	/**
	 * Creates the Response Object. 
	 * Marshalls the Response to a string.
	 * @return
	 */
	public static String buildResponseMessage(UUID uuid)  {
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
