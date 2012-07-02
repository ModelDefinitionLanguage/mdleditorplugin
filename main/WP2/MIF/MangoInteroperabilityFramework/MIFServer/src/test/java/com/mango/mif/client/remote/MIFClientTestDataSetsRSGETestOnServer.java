/* File			:  MIFClientTestOnServer.java
 * Project		:  MangoInteroperabilityFramework
 * Created on	:  Apr 2, 2012
 */
package com.mango.mif.client.remote;

import java.io.File;
import java.io.IOException;
import java.net.MalformedURLException;
import java.util.Map;
import java.util.Map.Entry;
import java.util.concurrent.CountDownLatch;

import javax.annotation.Resource;
import javax.xml.bind.JAXBException;

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
import com.mango.mif.client.api.JobService;
import com.mango.mif.client.api.TaskExecutionManager;
import com.mango.mif.client.api.TaskExecutionMessageHandler;
import com.mango.mif.connector.TestsHelper;
import com.mango.mif.core.api.ResourceUtils;
import com.mango.mif.core.api.SharedLocationManager;
import com.mango.mif.domain.ExecutionRequest;
import com.mango.mif.domain.ExecutionRequestBuilder;
import com.mango.mif.domain.ExecutionResponse;
import com.mango.mif.exception.MIFException;
import com.mango.mif.utils.NewExecutionRequestHelper;
import com.mango.mif.utils.RStatusHelper;
import com.mango.mif.utils.TestProperties;
import com.mango.mif.utils.encrypt.DesEncrypter;

/**
 * This Integration test requires the server to be started (ideally through Maven)
 * It tests the R execution by firing request to MIF and verify the request has been executed by MIF. 
 *
 * @version $Revision: $ as of $Date: $
 * <p>
 * SVN Entry : $HeadURL: $
 * <p>
 * SVN ID : $Id: $
 * <p>
 * Last edited by : $Author: $
 */
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration
public class MIFClientTestDataSetsRSGETestOnServer implements TaskExecutionMessageHandler {

    /** The Constant LOG. */
    final static Logger LOG = Logger.getLogger(MIFClientTestDataSetsRSGETestOnServer.class);

    @Resource(name = "taskExecutionManager")
    private TaskExecutionManager taskExecutionManager;

    /** The jms template. */
    @Resource(name = "jmsTemplate")
    JmsTemplate jmsTemplate;

    @Resource(name = "jobService")
    JobService jobservice;

    //To track all threads are done
    /** The Constant THREAD_COUNT. */
    private static final int THREAD_COUNT = 5;

	private static final String	CONNECTOR_ID	= "R_SGE";

    /** The request map. */
    static Map<String, ExecutionRequest> requestMap = Maps.newHashMapWithExpectedSize(THREAD_COUNT);
    
    /** The results map. */
    static Map<String, ExecutionResponse> resultsMap = Maps.newHashMapWithExpectedSize(THREAD_COUNT);
    /** test data dir names and request UUID map. */
    static Map<String, String> testDataDirAndRequest = Maps.newHashMapWithExpectedSize(THREAD_COUNT);

    /** Used to synchronize test thread and requests handling. */
    static CountDownLatch signal = new CountDownLatch(THREAD_COUNT);

    /** Used to synchronize test thread and requests handling. */
    static CountDownLatch restServiceSignal = new CountDownLatch(THREAD_COUNT);

    @Resource(name = "sharedLocationManager")
    private SharedLocationManager sharedLocationManager;
    
    private File outputDirectory;
    
    private String userName;
    /**
     * encrypted password
     */
    private String userPassword;
    
    @Before
    public void setUp() {
    	userName = System.getProperty(TestProperties.MIF_CLIENT_USER_NAME);
    	userPassword = System.getProperty(TestProperties.MIF_CLIENT_PASSWORD);
    	Preconditions.checkNotNull(userName, "The "+TestProperties.MIF_CLIENT_USER_NAME+" property is not set");
    	Preconditions.checkNotNull(userPassword, "The "+TestProperties.MIF_CLIENT_PASSWORD+" property is not set");
    }

    @DirtiesContext
    @Test(timeout = 300000)
    public void testRunTestDataSetsOnMIF() throws InterruptedException, JAXBException, IOException, MIFException {
        taskExecutionManager.setExecutionMessageHandler(this);
        outputDirectory = TestsHelper.createTmpDirectory("MIFClientTestDataSetsTestOnServerRSGE-testRunTestDataSetsOnMIF");
        File[] testDataSetsDirs = TestsHelper.getRTestDataSetsDirectories();
        signal = new CountDownLatch(testDataSetsDirs.length);
        restServiceSignal = new CountDownLatch(testDataSetsDirs.length);
        for(File dataSetDir : testDataSetsDirs) {
            String requestUUID = sendRequest(dataSetDir);
            testDataDirAndRequest.put(dataSetDir.getName(), requestUUID);
            new Thread(new RStatusHelper(requestUUID, restServiceSignal, jobservice)).start();
        }
        restServiceSignal.await();
        signal.await();
        
        copyResultsToOutputDirectory();
        LOG.info("Results are available in :" + outputDirectory);
        for(Entry<String,String> en : testDataDirAndRequest.entrySet()) {
            LOG.info("Request ID for test data set " + en.getKey() + " is " + en.getValue());
        }
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
            ResourceUtils.dumpExecutionFiles(outputDirectory,sharedLocationManager, requestMap.get(resp.getRequestId().toString()), resp);
        }
    }
    /**
     * Prepares execution request and passes it to MIF-client components for submitting
     * @return
     * @throws JAXBException
     * @throws MalformedURLException 
     * @throws MIFException 
     */
    private String sendRequest(File testDataSetDir) throws JAXBException, MalformedURLException, MIFException {
        ExecutionRequestBuilder executionRequestBuilder = NewExecutionRequestHelper.createRExecutionRequestBuilderForInputDirectory(sharedLocationManager, testDataSetDir);
        executionRequestBuilder.setConnectorID(CONNECTOR_ID);
        executionRequestBuilder.setUserName(userName);
        executionRequestBuilder.setUserPassword(userPassword);
        ExecutionRequest executionRequest = executionRequestBuilder.getExecutionRequest();
        taskExecutionManager.submit(executionRequest);
        requestMap.put(executionRequest.getRequestId().toString(), executionRequest);
        return executionRequest.getRequestId().toString();
    }


    @Override
    public void handle(ExecutionResponse executionResponse) {
        if(!requestMap.containsKey(executionResponse.getRequestId().toString())) {
            LOG.info("Received message that was not expected, ID " + executionResponse.getRequestId() + ".");
        	return;
        }
        LOG.info("Received message for handling " + executionResponse.getRequestId() + " for handling.");
        signal.countDown();
        resultsMap.put(executionResponse.getRequestId().toString(), executionResponse);
    }

}
