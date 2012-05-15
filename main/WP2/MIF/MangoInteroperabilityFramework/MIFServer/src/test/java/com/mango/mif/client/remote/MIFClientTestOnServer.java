/* File			:  MIFClientTestOnServer.java
 * Project		:  MangoInteroperabilityFramework
 * Created on	:  Apr 2, 2012
 */
package com.mango.mif.client.remote;

import static org.junit.Assert.assertNotNull;

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
public class MIFClientTestOnServer implements TaskExecutionMessageHandler {

    /** The Constant LOG. */
    final static Logger LOG = Logger.getLogger(MIFClientTestOnServer.class);

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

    /** Used to synchronize test thread and requests handling. */
    static CountDownLatch signal = new CountDownLatch(THREAD_COUNT);

    /** Used to synchronize test thread and requests handling. */
    static CountDownLatch restServiceSignal = new CountDownLatch(THREAD_COUNT);

    @Resource(name = "resourceComponent")
    private ResourceComponent resourceComponent;

    
    /**
     * 
     * The test mocks Navigator invoking MIF-client components to perform processing specified by the execution requests.
     * The test schedules number of threads that are responsible for monitoring processing progress using MIFs REST service. 
     * Also the test registers itself as a request handler on the TaskExecutionManager.
     *
     * @throws InterruptedException the interrupted exception
     */

    @DirtiesContext
    @Test(timeout = 120000)
    public void testRCliConnectorWithResourceComponent() throws InterruptedException, ResourceComponentException, JAXBException {
        taskExecutionManager.setExecutionMessageHandler(this);
        for (int i = 0; i < THREAD_COUNT; i++) {
            String requestUUID = sendRequest();
            new Thread(new RStatusHelper(requestUUID, restServiceSignal, jobservice)).start();
        }
        restServiceSignal.await();
        signal.await();

        
        checkResults();
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
     * Prepares execution request and passes it to MIF-client components for submitting
     * @return
     * @throws ResourceComponentException
     * @throws JAXBException
     */
    private String sendRequest() throws ResourceComponentException, JAXBException {
        String requestUUID = resourceComponent.createRequestDirectory();
        ExecutionRequest executionRequest = ExecutionRequestHelper.createExecutionRequest(requestUUID, resourceComponent);
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
