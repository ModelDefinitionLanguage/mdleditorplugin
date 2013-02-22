package com.mango.mif.sgeconnector;

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

import org.apache.commons.io.FileUtils;
import org.apache.log4j.Logger;
import org.junit.After;
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
import com.mango.mif.core.api.ResourceUtils;
import com.mango.mif.core.api.SharedLocationManager;
import com.mango.mif.core.impl.DefaultWorkspaceManager;
import com.mango.mif.domain.ExecutionRequest;
import com.mango.mif.domain.ExecutionRequestBuilder;
import com.mango.mif.domain.ExecutionResponse;
import com.mango.mif.domain.ExecutionType;
import com.mango.mif.exception.MIFException;
import com.mango.mif.utils.JAXBUtils;
import com.mango.mif.utils.ExecutionRequestHelper;
import com.mango.mif.utils.RequestSender;
import com.mango.mif.utils.TestProperties;
import com.mango.mif.utils.jaxb.ExecutionJaxbUtils;
import com.mango.mif.core.resource.UserSharedLocationManager;
import com.mango.mif.core.resource.fileutils.FileUtilsResourcePublisher;

/**
 * Integration Test For R Execution.
 * Mocks the navigator Request.
 */
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = {
        "/com/mango/mif/sgeconnector/SGEExecutionTestDataSetsIntegrationTest-context.xml",
        "classpath:sgeconnector-common-context.xml",
        "classpath:rsgeconnector-context.xml",
        "classpath:nonmemsgeconnector-context.xml",
        "classpath:cmd-sgeconnector-context.xml",
        "classpath:psnvpc-sgeconnector-context.xml",
        "classpath:psnscm-sgeconnector-context.xml",
        "classpath:psnbootstrap-sgeconnector-context.xml",
        "/spring/JmsTest-Config.xml" })
public class SGEExecutionTestDataSetsIntegrationTest implements MessageListener {
    /**
     * Working directory of connector.
     */
    final static File connectorWorkingDirectory = TestsHelper.createTmpDirectory();
    final static Logger LOG = Logger.getLogger(SGEExecutionTestDataSetsIntegrationTest.class);
    final String expectedMessage = "Sample message sent";

    @Resource(name = "connectorJmsTemplate")
    JmsTemplate jmsTemplate;

    @Resource(name = "sharedLocationManager")
    private SharedLocationManager sharedLocationManager;
    
    private final static String REQUEST_QUEUE = "R.SGE.JOB.REQUEST";

    //To track all threads are done
    private static final int THREAD_COUNT = 3;

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


    private String userName;

    /**
     * encrypted user password
     */
    private String userPassword;
    
    private File tmpSharedDirectory;

    /**
     * R command
     */
    private String rCommand;
    @Before
    public void setUp() {
        
    	userName = System.getProperty(TestProperties.MIF_CLIENT_USER_NAME);
    	userPassword = System.getProperty(TestProperties.MIF_CLIENT_PASSWORD);
        rCommand = System.getProperty(TestProperties.MIF_R_EXECUTABLE);
        Preconditions.checkNotNull(rCommand, "The "+TestProperties.MIF_R_EXECUTABLE+" property is not set");
    	Preconditions.checkNotNull(userName, "The "+TestProperties.MIF_CLIENT_USER_NAME+" property is not set");
    	Preconditions.checkNotNull(userPassword, "The "+TestProperties.MIF_CLIENT_PASSWORD+" property is not set");
    	tmpSharedDirectory = TestsHelper.createTmpDirectory();
       	((UserSharedLocationManager)sharedLocationManager).setWorkspaceManager(new DefaultWorkspaceManager(tmpSharedDirectory));
          	
    }
    
    @After
    public void tearDown() throws IOException {
        requestMap.clear();
        resultsMap.clear();
        testDataDirAndRequest.clear();
    	try {
			FileUtils.deleteDirectory(tmpSharedDirectory);
		} catch (IOException e) {
			LOG.error("Could not remove temporary shared directory", e);
		}
    }


    @DirtiesContext
    @Test(timeout = 500000)
    public void shouldRunDifferentTestDataSets() throws InterruptedException, JAXBException, IOException, MIFException {
        LOG.info("Running test: " + this.getClass() + ".testSendingMessage tmp dir = " + tmpSharedDirectory);
        outputDirectory = TestsHelper.createTmpDirectory("MIFClientTestDataSetsTestOnServer-testDataSetsExecution");
        outputDirectory.mkdirs();
        File[] testDataSetsDirs = TestsHelper.getRTestDataSetsDirectories();
        signal = new CountDownLatch(testDataSetsDirs.length);
        for (File dataSetDir : testDataSetsDirs) {
        	submitARequestForDataSetDir(ExecutionType.R_Script.name(),REQUEST_QUEUE,dataSetDir);
        }

        signal.await();

        copyResultsToOutputDirectory();
        
        LOG.info("Results are available in :" + outputDirectory);
        for(Entry<String,String> en : testDataDirAndRequest.entrySet()) {
            LOG.info("Request ID for test data set " + en.getValue() + " is " + en.getKey());
        }
    }
    
    /**
     * Creates a request for the given directory containing test data and submits it for execution to given connector
     * @param connectorId
     * @param requestQueue
     * @param dataSetDir
     * @throws JAXBException
     * @throws MIFException 
     */
    private void submitARequestForDataSetDir(String connectorId, String requestQueue, File dataSetDir) throws JAXBException, MIFException {

        FileUtilsResourcePublisher publisher = new FileUtilsResourcePublisher();
        publisher.setSharedLocationManager(sharedLocationManager);
        ExecutionRequestBuilder requestBuilder = ExecutionRequestHelper.createRExecutionRequestBuilderForInputDirectory(publisher,dataSetDir);
        requestBuilder.setExecutionType(connectorId);
        requestBuilder.setUserName(userName);
        requestBuilder.setCommand(rCommand).setUserPassword(userPassword).setSubmitAsUserMode(true);
        ExecutionRequest request = requestBuilder.getExecutionRequest();
        String requestMsg = requestBuilder.getExecutionRequestMsg();
        requestMap.put(request.getRequestId().toString(), requestMsg);
        testDataDirAndRequest.put(request.getRequestId().toString(), dataSetDir.getName());
        new Thread(new RequestSender(requestQueue, requestMsg, jmsTemplate)).start();
	}

	/**
     * Copies results to a output directory
     * 
     * @throws IOException 
     * @throws JAXBException 
     */
    private void copyResultsToOutputDirectory() throws IOException, JAXBException {
        for(Entry<String,ExecutionResponse> en : resultsMap.entrySet()) {
            ExecutionResponse resp = en.getValue();
            ResourceUtils.dumpExecutionFiles(outputDirectory,sharedLocationManager, (ExecutionRequest) JAXBUtils.unmarshall(requestMap.get(resp.getRequestId().toString()), ExecutionJaxbUtils.CONTEXT_CLASSES), resp);
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
