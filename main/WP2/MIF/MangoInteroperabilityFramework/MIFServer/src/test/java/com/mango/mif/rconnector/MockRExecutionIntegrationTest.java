package com.mango.mif.rconnector;

import static org.junit.Assert.assertEquals;

import java.io.StringReader;
import java.util.HashMap;
import java.util.Map;
import java.util.UUID;
import java.util.concurrent.CountDownLatch;

import javax.annotation.Resource;
import javax.jms.JMSException;
import javax.jms.Message;
import javax.jms.MessageListener;
import javax.jms.TextMessage;
import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Unmarshaller;

import org.apache.log4j.Logger;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.jms.core.JmsTemplate;
import org.springframework.test.annotation.DirtiesContext;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import com.google.common.base.Preconditions;
import com.mango.mif.domain.ExecutionResponse;
import com.mango.mif.rconnector.dispatcher.RequestSender;
import com.mango.mif.rconnector.runner.impl.MockRExec;
import com.mango.mif.utils.ExecutionUtilsTest;


/**
 * Integration Test For R Execution.
 * Mocks the navigator Request.
 */
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = {
		"/spring/Dispatcher-context.xml",
		"/com/mango/mif/rconnector/runner/impl/MockRJobRunnerTest-context.xml",
		"/spring/JmsTest-Config.xml"})
public class MockRExecutionIntegrationTest  implements MessageListener {
	final static Logger LOG = Logger.getLogger(MockRExecutionIntegrationTest.class);
	final String expectedMessage = "Sample message sent";

	@Resource(name="jmsTemplate")
	JmsTemplate jmsTemplate;
	
	//To track all threads are done
	private static final int THREAD_COUNT = 5;

	static Map<UUID, String> requestMap = new HashMap<UUID, String>(THREAD_COUNT); 
	
	/**
	 * Contains consumed Execution Responses 
	 */
    static Map<UUID, ExecutionResponse> resultsMap = new HashMap<UUID, ExecutionResponse>(THREAD_COUNT); 
    
    /**
     * Used to synchronize test thread and requests handling
     */
    static CountDownLatch signal = new CountDownLatch(THREAD_COUNT);
 
	
	/**
	 * The test initiates fixed no of execution request threads with unqiue ID( mocking the behaviour of Navigator issuing the request) onto a MIF Request Queue.
	 * A consumer( Dispatcher component of the execution manager) consumes the request and creates a new JobRunner from the JObRunnerfactory to handle the requests.
	 * The JobRunner starts the processing of the Job proceeds into different states once the job is finished, It then publishes the status of the job onto the job result queue.
	 * The Dispatcher component JobResultListner consumes the message and post back the result to the JOb Response queue. 
	 * This test which is registered as a Listener consumes the results from the JOb Response queue  
	 * and verifies all the threads are completed by checking the ID on the response. 
	 * @throws InterruptedException 
	 */
	@DirtiesContext
	@Test(timeout=120000)
	public void testSendingMessage() throws InterruptedException {
		for (int i = 0; i < THREAD_COUNT; i++) {
			UUID randomUUID = UUID.randomUUID();
			String messageToBeSent = ExecutionUtilsTest.buildMessage(randomUUID);
			new Thread(new RequestSender("R.JOB.REQUEST",messageToBeSent, jmsTemplate)).start();	
			requestMap.put(randomUUID, messageToBeSent);
		}
		
		signal.await();
		
		checkResults();
		
	}

	/**
	 * Checks if the R results are set and expected
	 */
	private void checkResults() {
	    for(ExecutionResponse resp : resultsMap.values()) {
	        assertEquals(MockRExec.MOCK_RESULT,resp.getResult());
	    }
	}
	
	/**
	 * Subscriber to consume the messages from the MIF.
	 */
	 
	@Override
	public void onMessage(Message message) {
		LOG.info("The Response is got by navigator");
		TextMessage msg = (TextMessage) message;
		String receivedText = null;
		try {
			receivedText = msg.getText();
		} catch (JMSException e) {
			LOG.error("JMSException" + e );
		}

		if(checkIfTheReceivedTextContainsPartOfRequest(receivedText)){
			LOG.info("Closing the request " +  receivedText );
		    signal.countDown();
		} else {
		    LOG.error("Received unexpected response.");
		}
	}

	/**
	 * Checks if the response text contains the unique ID which are submitted as part of requests. 
	 * @param receivedText
	 * @return
	 */
	private boolean checkIfTheReceivedTextContainsPartOfRequest(String receivedText) {
		Preconditions.checkNotNull(receivedText, "Received text message should not be null");
		JAXBContext context;
		ExecutionResponse  response= null;
		try {
			context = JAXBContext.newInstance(ExecutionResponse.class);
			Unmarshaller um = context.createUnmarshaller();
			response = (ExecutionResponse)um.unmarshal(new StringReader(receivedText));
		} catch (JAXBException e) {
			LOG.error("JAXB Exception " + e );
		}
		String uuid = response.getRequestId().toString();
		for(UUID key : requestMap.keySet()) {
			if(uuid.equals(key.toString())) {
                resultsMap.put(UUID.fromString(uuid), response);
				return true;
			}
		}
		return false;
	}
	
}
