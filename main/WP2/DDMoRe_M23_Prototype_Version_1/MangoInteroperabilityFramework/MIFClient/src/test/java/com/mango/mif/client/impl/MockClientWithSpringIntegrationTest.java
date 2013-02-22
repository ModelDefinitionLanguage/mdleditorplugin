/* File			:  MockClientIntegrationTest.java
 * Project		:  MangoInteroperabilityFramework
 * Created on	:  Apr 17, 2012
 */
package com.mango.mif.client.impl;

import java.util.Map;
import java.util.UUID;
import java.util.concurrent.CountDownLatch;

import javax.annotation.Resource;
import javax.xml.bind.JAXBException;

import org.apache.log4j.Logger;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.test.annotation.DirtiesContext;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import com.google.common.base.Preconditions;
import com.google.common.collect.Maps;
import com.mango.mif.client.api.TaskExecutionManager;
import com.mango.mif.client.api.TaskExecutionMessageHandler;
import com.mango.mif.client.domain.MIFSoftwareNames;
import com.mango.mif.core.api.SharedLocationManager;
import com.mango.mif.domain.ExecutionRequest;
import com.mango.mif.domain.ExecutionRequestBuilder;
import com.mango.mif.domain.ExecutionResponse;
import com.mango.mif.exception.MIFException;

/**
 * This test is to verify the mock MIF behaviour that needs to be provided to the MIF clients.
 * @version $Revision: $ as of $Date: $
 * <p>SVN Entry : $HeadURL: $
 * <p>SVN ID    : $Id: $
 * <p>Last edited by : $Author: $
 */
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration
public class MockClientWithSpringIntegrationTest implements TaskExecutionMessageHandler {

    /** The Constant LOG. */
    final static Logger LOG = Logger.getLogger(MockClientWithSpringIntegrationTest.class);

    //To track all threads are done
    /** The Constant THREAD_COUNT. */
    private static final int THREAD_COUNT = 5;

    /** The request map. */
    static Map<String, String> requestMap = Maps.newConcurrentMap();

    /** Used to synchronize test thread and requests handling. */
    static CountDownLatch signal = new CountDownLatch(THREAD_COUNT);

    @Resource(name="taskExecutionManager")
    private TaskExecutionManager taskExecutionManager;


    @Resource(name="sharedLocationManager")
    private SharedLocationManager sharedLocationManager;


    /**
     * Contains consumed Execution Responses
     */
    static Map<String, ExecutionResponse> resultsMap = Maps.newConcurrentMap();


    @Before
    public void beforeTest(){
        //Setting up the resource components
        taskExecutionManager.setExecutionMessageHandler(this);
    }

    @DirtiesContext
    @Test(timeout=120000)
    public void shouldAddTheRequestToTheRegistry() throws JAXBException, InterruptedException, MIFException{

        for (int i = 0; i < THREAD_COUNT; i++) {

            ExecutionRequestBuilder requestBuilder = new ExecutionRequestBuilder();

            String requestID = UUID.randomUUID().toString();
            requestBuilder.setRequestId(requestID);
            requestBuilder.setExecutionFile("MOCK_R_SCRIPT_FILE");
            requestBuilder.setCommand("MOCK_R_EXECUTABLE");
            requestBuilder.setExecutionType(MIFSoftwareNames.R_COMMAND_LINE.name());
            ExecutionRequest request = requestBuilder.getExecutionRequest();
            String requestMsg = requestBuilder.getExecutionRequestMsg();
            LOG.info("Sending the request:" + requestMsg);

            requestMap.put(request.getRequestId(), requestMsg);
            taskExecutionManager.submit(request);

        }
        signal.await();
    }

    @Override
    public void handle(ExecutionResponse executionResponse) {
        if(checkIfTheReceivedTextContainsPartOfRequest(executionResponse)){
            LOG.info("Closing the request " +  executionResponse.getRequestId() );
            signal.countDown();
        } else {
            LOG.error("Received unexpected response.");
        }
    }

    /**
     * Checks if the response text contains the unique ID which are submitted as part of requests.
     * @param executionResponse
     * @return
     */
    private boolean checkIfTheReceivedTextContainsPartOfRequest(ExecutionResponse executionResponse) {
        Preconditions.checkNotNull(executionResponse, "Received text message should not be null");
        String uuid = executionResponse.getRequestId().toString();
        for(String key : requestMap.keySet()) {
            if(uuid.equals(key.toString())) {
                resultsMap.put(uuid, executionResponse);
                return true;
            }
        }
        return false;
    }

}
