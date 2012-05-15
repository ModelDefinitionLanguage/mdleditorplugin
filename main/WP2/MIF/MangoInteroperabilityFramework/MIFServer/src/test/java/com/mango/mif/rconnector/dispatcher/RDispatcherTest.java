/* File			:  RDispatcherTest.java
 * Project		:  MangoInteroperabilityFramework
 * Created on	:  Mar 14, 2012
 */
package com.mango.mif.rconnector.dispatcher;

import static org.hamcrest.CoreMatchers.equalTo;
import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.assertThat;
import static org.junit.Assert.fail;
import static org.mockito.Matchers.eq;
import static org.mockito.Mockito.inOrder;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.never;
import static org.mockito.Mockito.spy;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import java.io.StringWriter;
import java.util.UUID;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.ConcurrentMap;
import java.util.concurrent.ExecutionException;

import javax.jms.JMSException;
import javax.jms.TextMessage;
import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Marshaller;

import org.apache.log4j.Logger;
import org.junit.Before;
import org.junit.Test;
import org.mockito.ArgumentCaptor;
import org.mockito.InOrder;
import org.mockito.Mockito;
import org.springframework.core.task.AsyncTaskExecutor;
import org.springframework.jms.core.JmsTemplate;
import org.springframework.jms.core.MessageCreator;

import com.mango.mif.connector.TestsHelper;
import com.mango.mif.connector.dispatcher.domain.DispatcherExceptions;
import com.mango.mif.connector.dispatcher.domain.Job;
import com.mango.mif.connector.dispatcher.domain.JobStatus;
import com.mango.mif.connector.dispatcher.impl.Dispatcher;
import com.mango.mif.connector.runner.api.JobRunner;
import com.mango.mif.connector.runner.impl.DriverProcessingStatus;
import com.mango.mif.domain.ExecutionResponse;
import com.mango.mif.rconnector.dispatcher.impl.RDispatcher;
import com.mango.mif.rconnector.runner.impl.RJobRunnerFactory;
import com.mango.mif.utils.ExecutionUtilsTest;

/**
 * @version $Revision: $ as of $Date: $
 * <p>SVN Entry : $HeadURL: $
 * <p>SVN ID    : $Id: $
 * <p>Last edited by : $Author: $
 */
public class RDispatcherTest {
	final static Logger LOG = Logger.getLogger(RDispatcherTest.class);
	
	TextMessage message =  null;
	Dispatcher dispacther = null;
	RJobRunnerFactory jobRunFactory = null;
	private static final String RESPONSE_DEST = "R.JOB.RESPONSE";
	
	ConcurrentMap<String, JobRunner> jobRunnerRegistry ;
	ConcurrentMap<String, String> jobStatusRegistry ;
	
	String uuid = null;
	JobRunner jobRunner = null;
	AsyncTaskExecutor asyncTaskExecutor;
	String messageToBeSent =  null;
	String responseMessageFromJobRunners =  null;
	
	JmsTemplate jmsTemplate = null;
	
	@Before
	public void setUp()  throws JMSException, InterruptedException, ExecutionException {
		
		dispacther =  new RDispatcher();
		dispacther.setWorkingDirectory(TestsHelper.createTmpDirectory());
		UUID randomUUID = UUID.randomUUID();
		messageToBeSent = ExecutionUtilsTest.buildMessage(randomUUID);
		uuid =  randomUUID.toString();
		
		message = mock(TextMessage.class);
		when(message.getText()).thenReturn(messageToBeSent);
		
		asyncTaskExecutor =  mock(AsyncTaskExecutor.class);
		jobRunFactory = mock(RJobRunnerFactory.class);
		jmsTemplate = mock(JmsTemplate.class);
		
		dispacther.setJobRunnerFactory(jobRunFactory);
		dispacther.setTaskExecutor(asyncTaskExecutor);
		dispacther.setJmsTemplate(jmsTemplate);
		dispacther.setDestination(RESPONSE_DEST);
		
		
		jobRunnerRegistry = spy(new ConcurrentHashMap<String, JobRunner>());
		jobStatusRegistry = spy(new ConcurrentHashMap<String, String>());
		dispacther.setJobRunnerRegistry(jobRunnerRegistry);
		dispacther.setJobStatusRegistry(jobStatusRegistry);
		
		jobRunner = mock(JobRunner.class);
		
		when(jobRunFactory.createJobRunner(Mockito.<Job>anyObject())).thenReturn(jobRunner);
	}
	
	@Test
	public void shouldInvokeGetTextCallOnTheReceivedMessage() throws JMSException {
		dispacther.onMessage(message);
		verify(message).getText();
	}
	
	
	@Test
	public void shouldInvokeTheJobRunnerRegistryWhenMessageIsNotNull() throws JMSException {
		ArgumentCaptor<Job> argument = ArgumentCaptor.forClass(Job.class);
		
		dispacther.onMessage(message);
		
		verify(jobRunFactory).createJobRunner(argument.capture());
		assertThat("Message Must be equal ", argument.getValue().getExecutionRequestMsg(), is(equalTo(messageToBeSent)));
	}
	
	@Test
	public void shouldThrowARunTimeExceptionWhenNullMessageIsSent() throws JMSException {
		try {
			dispacther.onMessage(null);
			fail();
		} catch (RuntimeException runExp) {
			assertThat("Message is null", runExp.getMessage(), is(equalTo(DispatcherExceptions.INVALID_REQUEST_MESSAGE.getMessage())));
		}
		verify(jobRunFactory, never()).createJobRunner(Mockito.<Job>anyObject());
	}
	
	@Test
	public void shouldThrowExceptionWhenImproperFormatMessageIsSent() throws JMSException {
		String inCorrectRequest = "Improper message";
		when(message.getText()).thenReturn(inCorrectRequest);
		try {
			dispacther.onMessage(message);
			fail();
		} catch (RuntimeException runExp) {
			assertThat("Incorrect Request format", runExp.getMessage(), is(equalTo(DispatcherExceptions.INVALID_REQUEST_MESSAGE_FORMAT.getMessage())));
		}
		verify(jobRunFactory, never()).createJobRunner(Mockito.<Job>anyObject());
	}
	
	@Test
	public void shouldThrowExceptionWhenRequestIdIsNotpresentInRequest() throws JMSException {
		messageToBeSent = ExecutionUtilsTest.buildMessage(null);
		when(message.getText()).thenReturn(messageToBeSent);
		try {
			dispacther.onMessage(message);
			fail();
		} catch (RuntimeException runExp) {
			assertThat("Request Id must be present", runExp.getMessage(), is(equalTo(DispatcherExceptions.REQUEST_ID_NOT_PRESENT.getMessage())));
		}
	}
	
	@Test
	public void shouldAddTheJobRunnerToTheRegistryAndUpdateTheStatusRegistry() throws JMSException {
		assertThat("Job Status Registry Size Before the Job Execution", jobStatusRegistry.size(), is(equalTo(0)));
		assertThat("Job Runenr Registry Size Before the Job Execution", jobRunnerRegistry.size(), is(equalTo(0)));
		when(jobRunFactory.createJobRunner(Mockito.<Job>any())).thenReturn(jobRunner);
		
		//SUT
		dispacther.onMessage(message);
		
		InOrder inorder = inOrder(jobRunnerRegistry, jobStatusRegistry, asyncTaskExecutor);
		inorder.verify(jobStatusRegistry).put(uuid, JobStatus.STARTED.getMessage());
		inorder.verify(asyncTaskExecutor).submit(jobRunner);
		inorder.verify(jobRunnerRegistry).put(uuid, jobRunner);
		inorder.verify(jobStatusRegistry).put(uuid, JobStatus.RUNNING.getMessage());
		
		assertThat("Registry Size After the Job", jobRunnerRegistry.size(), is(equalTo(1)));
		assertThat("Job Status Registry Size After the Job", jobStatusRegistry.size(), is(equalTo(1)));
	}
	
	
	
	@Test
	public void shouldRemoveTheJobRunnerFromRegistryOnItsCompletion() throws JMSException, InterruptedException, ExecutionException {
		UUID randomUuid = UUID.randomUUID(); 
		when(jobRunnerRegistry.containsKey(randomUuid.toString())).thenReturn(true);
		when(jobStatusRegistry.containsKey(randomUuid.toString())).thenReturn(true);
		
		String handleResultMessage = dispacther.handleResultMessage(buildResponseMessage(randomUuid));
		
		verify(jobRunnerRegistry).remove(randomUuid.toString());
		verify(jobStatusRegistry).put(randomUuid.toString(), JobStatus.COMPLETED.getMessage());
		
		verify(jmsTemplate).send(eq(RESPONSE_DEST), Mockito.<MessageCreator>any());
		assertThat(handleResultMessage, is(equalTo(randomUuid.toString())));
		assertThat("Registry Size After the Job", jobRunnerRegistry.size(), is(equalTo(0)));
	}
	
	@Test
	public void shouldThrowExceptionWhenInvalidMessageIsSent() throws JMSException, InterruptedException, ExecutionException {
		try {
			dispacther.handleResultMessage(null);
			fail();
		} catch (RuntimeException runExp) {
			assertThat("Request Id must be present", runExp.getMessage(), is(equalTo(DispatcherExceptions.INVALID_RESPONSE_MESSAGE.getMessage())));
		}
	}
	
	
	@Test
	public void shouldThrowExceptionWhenRequestIdIsNotPresent() throws JMSException, InterruptedException, ExecutionException {
		try {
			String jobRunnerResponse = "Incorrect response";
			dispacther.handleResultMessage(jobRunnerResponse);
			fail();
		} catch (RuntimeException runExp) {
			assertThat("Request Id must be present", runExp.getMessage(), is(equalTo(DispatcherExceptions.INVALID_RESPONSE_MESSAGE_FORMAT.getMessage())));
		}
	}


	
	
	
	/**
	 * Creates the Response Object. 
	 * Marshalls the Response to a string.
	 * @return
	 */
	private static String buildResponseMessage(UUID uuid)  {
		StringWriter sw = new StringWriter();
		ExecutionResponse response = new ExecutionResponse();
		response.setRequestId(uuid);
		response.setStatus(DriverProcessingStatus.FINISHED.getMessage());
		
		JAXBContext context;
		try {
			context = JAXBContext.newInstance(ExecutionResponse.class);
			Marshaller m = context.createMarshaller();
			m.setProperty(Marshaller.JAXB_FORMATTED_OUTPUT, Boolean.TRUE);
			
			m.marshal(response, sw);
			LOG.info("Sending Request " + sw.toString());
		} catch (JAXBException e) {
			LOG.error("JAXB Exception " + e );
		}
		return sw.toString();
	}
}
