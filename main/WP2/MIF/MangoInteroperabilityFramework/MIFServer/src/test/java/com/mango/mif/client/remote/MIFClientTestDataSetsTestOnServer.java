/* File			:  MIFClientTestOnServer.java
 * Project		:  MangoInteroperabilityFramework
 * Created on	:  Apr 2, 2012
 */
package com.mango.mif.client.remote;

import static org.junit.Assert.assertNotNull;

import java.io.File;
import java.io.IOException;
import java.net.MalformedURLException;
import java.util.Map;
import java.util.Map.Entry;
import java.util.concurrent.CountDownLatch;

import javax.annotation.Resource;
import javax.xml.bind.JAXBException;

import org.apache.log4j.Logger;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.jms.core.JmsTemplate;
import org.springframework.test.annotation.DirtiesContext;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import com.google.common.collect.Maps;
import com.mango.mif.client.api.JobService;
import com.mango.mif.client.api.TaskExecutionManager;
import com.mango.mif.client.api.TaskExecutionMessageHandler;
import com.mango.mif.connector.TestsHelper;
import com.mango.mif.core.api.ResourceComponent;
import com.mango.mif.core.api.ResourceComponentException;
import com.mango.mif.domain.ExecutionRequest;
import com.mango.mif.domain.ExecutionResponse;
import com.mango.mif.domain.Parameter;
import com.mango.mif.rconnector.dispatcher.RStatusHelper;
import com.mango.mif.utils.ExecutionRequestHelper;

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
public class MIFClientTestDataSetsTestOnServer implements TaskExecutionMessageHandler {

    /** The Constant LOG. */
    final static Logger LOG = Logger.getLogger(MIFClientTestDataSetsTestOnServer.class);

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

    @Resource(name = "resourceComponent")
    private ResourceComponent resourceComponent;
    
    private File outputDirectory;

    @DirtiesContext
    @Test(timeout = 120000)
    public void testRunTestDataSetsOnMIF() throws InterruptedException, ResourceComponentException, JAXBException, IOException {
        taskExecutionManager.setExecutionMessageHandler(this);
        outputDirectory = TestsHelper.createTmpDirectory("MIFClientTestDataSetsTestOnServer-results");
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
        
        checkResults();
        copyResultsToOutputDirectory();
        LOG.info("Results are available in :" + outputDirectory);
        for(Entry<String,String> en : testDataDirAndRequest.entrySet()) {
            LOG.info("Request ID for test data set " + en.getKey() + " is " + en.getValue());
        }
    }
    /**
     * Checks if the result files specified by the result messages exist
     * @throws ResourceComponentException 
     */
    private void checkResults() throws ResourceComponentException {
        for(Entry<String,ExecutionResponse> en : resultsMap.entrySet()) {
            ExecutionResponse resp = en.getValue();
            for(Entry<String,Parameter> parEntry : resp.getParameters().entrySet()) {
                if(parEntry.getValue().getResourceUuid()!=null) {
                    assertNotNull("Resource specified by parameter: " + parEntry.getKey() + " does not exist.",resourceComponent.getResource(parEntry.getValue().getResourceUuid()));
                }
            }
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
            TestsHelper.dumpExecutionFiles(outputDirectory,resourceComponent, requestMap.get(resp.getRequestId().toString()), resp);
        }
    }
    /**
     * Prepares execution request and passes it to MIF-client components for submitting
     * @return
     * @throws ResourceComponentException
     * @throws JAXBException
     * @throws MalformedURLException 
     */
    private String sendRequest(File testDataSetDir) throws ResourceComponentException, JAXBException, MalformedURLException {
        String requestUUID = resourceComponent.createRequestDirectory();
        ExecutionRequest executionRequest = ExecutionRequestHelper.createExecutionRequest(requestUUID,testDataSetDir, resourceComponent);
        taskExecutionManager.submit(executionRequest);
        requestMap.put(requestUUID, executionRequest);
        return requestUUID;
    }


    @Override
    public void handle(ExecutionResponse executionResponse) {
        LOG.info("Received message for handling " + executionResponse.getRequestId() + " for handling.");
        signal.countDown();
        resultsMap.put(executionResponse.getRequestId().toString(), executionResponse);
    }

}
