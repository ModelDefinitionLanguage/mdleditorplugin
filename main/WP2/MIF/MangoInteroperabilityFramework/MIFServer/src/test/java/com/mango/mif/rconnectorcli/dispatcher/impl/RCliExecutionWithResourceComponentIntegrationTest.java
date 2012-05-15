package com.mango.mif.rconnectorcli.dispatcher.impl;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;

import java.io.File;
import java.io.IOException;
import java.io.StringReader;
import java.util.HashMap;
import java.util.Map;
import java.util.Map.Entry;
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
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.jms.core.JmsTemplate;
import org.springframework.test.annotation.DirtiesContext;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import com.google.common.base.Preconditions;
import com.google.common.collect.Maps;
import com.mango.mif.connector.TestsHelper;
import com.mango.mif.core.api.ResourceComponentException;
import com.mango.mif.core.impl.DefaultResourceComponent;
import com.mango.mif.core.impl.DefaultWorkspaceManager;
import com.mango.mif.domain.ExecutionRequest;
import com.mango.mif.domain.ExecutionResponse;
import com.mango.mif.domain.Parameter;
import com.mango.mif.rconnector.dispatcher.RequestSender;
import com.mango.mif.rconnector.runner.impl.MockRExec;
import com.mango.mif.utils.ExecutionRequestHelper;
import com.mango.mif.utils.JAXBUtils;
import com.mango.mif.utils.jaxb.ExecutionJaxbUtils;

/**
 * Integration Test For R Execution.
 * Mocks the navigator Request.
 */
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = {
        "/com/mango/mif/rconnectorcli/dispatcher/impl/RCliExecutionWithResourceComponentIntegrationTest-context.xml",
        "/com/mango/mif/rconnectorcli/dispatcher/impl/Connector-context.xml",
        "/com/mango/mif/rconnectorcli/dispatcher/impl/StateHandlers-withResourceComponent-context.xml", "/spring/JmsTest-Config.xml" })
public class RCliExecutionWithResourceComponentIntegrationTest implements MessageListener {

    /**
     * Working directory of connector.
     */
    final static File connectorWorkingDirectory = TestsHelper.createTmpDirectory();
    final static Logger LOG = Logger.getLogger(RCliExecutionWithResourceComponentIntegrationTest.class);
    final String expectedMessage = "Sample message sent";

    @Resource(name = "rCliJmsTemplate")
    JmsTemplate jmsTemplate;

    @Resource(name = "resourceComponent")
    DefaultResourceComponent resourceComponent;

    //To track all threads are done
    private static final int THREAD_COUNT = 5;

    static Map<String, String> requestMap = new HashMap<String, String>(THREAD_COUNT);

    /**
     * Contains consumed Execution Responses 
     */
    static Map<String, ExecutionResponse> resultsMap = new HashMap<String, ExecutionResponse>(THREAD_COUNT);
    /** test data dir names and request UUID map. */
    static Map<String, String> testDataDirAndRequest = Maps.newHashMapWithExpectedSize(THREAD_COUNT);

    private File outputDirectory;
    /**
     * Used to synchronize test thread and requests handling
     */
    static CountDownLatch signal = new CountDownLatch(THREAD_COUNT);

    @Before
    public void setUp() {
        requestMap.clear();
        resultsMap.clear();
        testDataDirAndRequest.clear();
    }

    /**
     * The test initiates fixed no of execution request threads with unqiue ID( mocking the behaviour of Navigator issuing the request) onto a MIF Request Queue.
     * A consumer( Dispatcher component of the execution manager) consumes the request and creates a new JobRunner from the JObRunnerfactory to handle the requests.
     * The JobRunner starts the processing of the Job proceeds into different states once the job is finished, It then publishes the status of the job onto the job result queue.
     * The Dispatcher component JobResultListner consumes the message and post back the result to the JOb Response queue. 
     * This test which is registered as a Listener consumes the results from the JOb Response queue  
     * and verifies all the threads are completed by checking the ID on the response. 
     * @throws InterruptedException 
     * @throws ResourceComponentException 
     * @throws JAXBException 
     */
    @DirtiesContext
    @Test(timeout = 120000)
    public void testSendingMessage() throws InterruptedException, ResourceComponentException, JAXBException {
        LOG.info("Running test: " + this.getClass() + ".testSendingMessage");
        resourceComponent.setWorkspaceManager(new DefaultWorkspaceManager(TestsHelper.createTmpDirectory()));
        for (int i = 0; i < THREAD_COUNT; i++) {
            String randomUUID = resourceComponent.createRequestDirectory();
            String messageToBeSent = ExecutionRequestHelper.createExecutionRequestMsg(randomUUID, resourceComponent);
            new Thread(new RequestSender("R.JOB.REQUEST", messageToBeSent, jmsTemplate)).start();
            requestMap.put(randomUUID, messageToBeSent);
        }

        signal.await();

        checkResults();

    }


    @DirtiesContext
    @Test(timeout = 120000)
    public void testDataSetsExecution() throws InterruptedException, ResourceComponentException, JAXBException, IOException {
        LOG.info("Running test: " + this.getClass() + ".testSendingMessage");
        resourceComponent.setWorkspaceManager(new DefaultWorkspaceManager(TestsHelper.createTmpDirectory()));
        outputDirectory = TestsHelper.createTmpDirectory("RCliExecutionWithResourceComponentIntegrationTest-results");
        File[] testDataSetsDirs = TestsHelper.getRTestDataSetsDirectories();
        signal = new CountDownLatch(testDataSetsDirs.length);
        for (File dataSetDir : testDataSetsDirs) {
            String randomUUID = resourceComponent.createRequestDirectory();
            testDataDirAndRequest.put(randomUUID, dataSetDir.getName());
            String messageToBeSent = ExecutionRequestHelper.createExecutionRequestMsg(randomUUID, dataSetDir, resourceComponent);
            new Thread(new RequestSender("R.JOB.REQUEST", messageToBeSent, jmsTemplate)).start();
            requestMap.put(randomUUID, messageToBeSent);
        }

        signal.await();

        checkResults();
        
        copyResultsToOutputDirectory();
        
        LOG.info("Results are available in :" + outputDirectory);
        for(Entry<String,String> en : testDataDirAndRequest.entrySet()) {
            LOG.info("Request ID for test data set " + en.getValue() + " is " + en.getKey());
        }
    }
    
    /**
     * Copies results to a output directory
     * 
     * @throws ResourceComponentException
     * @throws IOException 
     * @throws JAXBException 
     */
    private void copyResultsToOutputDirectory() throws ResourceComponentException, IOException, JAXBException {
        for(Entry<String,ExecutionResponse> en : resultsMap.entrySet()) {
            ExecutionResponse resp = en.getValue();
            TestsHelper.dumpExecutionFiles(outputDirectory,resourceComponent, (ExecutionRequest) JAXBUtils.unmarshall(requestMap.get(resp.getRequestId().toString()), ExecutionJaxbUtils.EXECUTION_REQUEST_CLASS_ARRAY), resp);
        }
    }
    /**
     * Checks if the R results are set and expected
     * @throws ResourceComponentException 
     */
    private void checkResults() throws ResourceComponentException {
        for (ExecutionResponse resp : resultsMap.values()) {
            assertEquals("Result specified in the message is unexpected " + resp.getRequestId() + ". For test data " + testDataDirAndRequest.get(resp.getRequestId().toString()), MockRExec.MOCK_RESULT, resp.getResult());
            assertNotNull("No parameters set in response message for request: " + resp.getRequestId(), resp.getParameters());
            Parameter resultParameter = resp.getParameters().entrySet().iterator().next().getValue();
            assertNotNull("Resource beeing a result for the request " + resp.getRequestId() + " not published in the resource component.",
                resourceComponent.getResource(resultParameter.getResourceUuid()));
            assertNotNull(resourceComponent.getResource(resultParameter.getResourceUuid()).getContent());
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
            LOG.error("JMSException" + e);
        }

        if (checkIfTheReceivedTextContainsPartOfRequest(receivedText)) {
            LOG.info("Closing the request " + receivedText);
            signal.countDown();
        }
    }

    /**
     * Checks if the response text contains the uinque ID which are submitted as part of requests. 
     * @param receivedText
     * @return
     */
    private boolean checkIfTheReceivedTextContainsPartOfRequest(String receivedText) {
        Preconditions.checkNotNull(receivedText, "Received text message should not be null");
        JAXBContext context;
        ExecutionResponse response = null;
        try {
            context = JAXBContext.newInstance(ExecutionResponse.class);
            Unmarshaller um = context.createUnmarshaller();
            response = (ExecutionResponse) um.unmarshal(new StringReader(receivedText));
        } catch (JAXBException e) {
            LOG.error("JAXB Exception " + e);
        }
        String uuid = response.getRequestId().toString();
        for (String key : requestMap.keySet()) {
            if (uuid.equals(key)) {
                resultsMap.put(uuid, response);
                return true;
            }
        }
        LOG.info("Reuqest with id: " + uuid + " not found.");
        return false;
    }

}
