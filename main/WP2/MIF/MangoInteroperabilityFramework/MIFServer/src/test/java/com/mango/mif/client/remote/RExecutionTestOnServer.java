/* File			:  RExecutionTestOnServer.java
 * Project		:  MangoInteroperabilityFramework
 * Created on	:  Apr 2, 2012
 */
package com.mango.mif.client.remote;

import static org.mockito.Mockito.mock;

import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.CountDownLatch;

import javax.annotation.Resource;
import javax.jms.Message;
import javax.jms.MessageListener;
import javax.xml.bind.JAXBException;

import org.apache.log4j.Logger;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.jms.core.JmsTemplate;
import org.springframework.test.annotation.DirtiesContext;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import com.mango.mif.client.api.JobService;
import com.mango.mif.client.api.TaskExecutionManager;
import com.mango.mif.client.api.TaskExecutionMessageHandler;
import com.mango.mif.core.api.ResourceComponent;
import com.mango.mif.core.api.ResourceComponentException;
import com.mango.mif.rconnector.dispatcher.RStatusHelper;
import com.mango.mif.rconnector.dispatcher.RequestSender;
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
public class RExecutionTestOnServer implements MessageListener {

    /** The Constant LOG. */
    final static Logger LOG = Logger.getLogger(RExecutionTestOnServer.class);

    /** The jms template. */
    @Resource(name = "jmsTemplate")
    JmsTemplate jmsTemplate;

    @Resource(name = "jobService")
    JobService jobservice;

    //To track all threads are done
    /** The Constant THREAD_COUNT. */
    private static final int THREAD_COUNT = 5;

    @Resource(name = "taskExecutionManager")
    private TaskExecutionManager taskExecutionManager;

    /** The request map. */
    static Map<String, String> requestMap = new HashMap<String, String>(THREAD_COUNT);

    /** Used to synchronize test thread and requests handling. */
    static CountDownLatch signal = new CountDownLatch(THREAD_COUNT);

    /** Used to synchronize test thread and requests handling. */
    static CountDownLatch restServiceSignal = new CountDownLatch(THREAD_COUNT);

    @Resource(name = "resourceComponent")
    private ResourceComponent resourceComponent;

    /**
     * 
     * The test initiates fixed no of execution request threads with unqiue ID( mocking the behaviour of Navigator issuing the request) onto a MIF Request Queue.
     * A consumer( Dispatcher component of the execution manager) consumes the request and creates a new JobRunner from the JObRunnerfactory to handle the requests.
     * The JobRunner starts the processing of the Job proceeds into different states once the job is finished, It then publishes the status of the job onto the job result queue.
     * The Dispatcher component JobResultListner consumes the message and post back the result to the JOb Response queue.
     * This test queries the REST API exposed by MIF to check the status of each of the submitted Request to determine whether they are completed
     * by passing the unique request ID and .
     * 
     *
     * @throws InterruptedException the interrupted exception
     */

    @DirtiesContext
    @Test(timeout = 120000)
    public void testRCliConnectorWithResourceComponent() throws InterruptedException, ResourceComponentException, JAXBException {
        taskExecutionManager.setExecutionMessageHandler(mock(TaskExecutionMessageHandler.class));
        for (int i = 0; i < THREAD_COUNT; i++) {
            String requestUUID = resourceComponent.createRequestDirectory();

            String messageToBeSent = ExecutionRequestHelper.createExecutionRequestMsg(requestUUID, resourceComponent);;
            new Thread(new RequestSender("R.JOB.REQUEST", messageToBeSent, jmsTemplate)).start();
            requestMap.put(requestUUID, messageToBeSent);

            new Thread(new RStatusHelper(requestUUID, restServiceSignal, jobservice)).start();
        }
        restServiceSignal.await();
        signal.await();

    }

    @Override
    public void onMessage(Message msg) {
        LOG.info("Received messge: " + msg);
        signal.countDown();
        
    }

}
