/* File			:  MockClientIntegrationTest.java
 * Project		:  MangoInteroperabilityFramework
 * Created on	:  Apr 17, 2012
 */
package com.mango.mif.client.impl;

import java.io.File;
import java.util.HashMap;
import java.util.Map;
import java.util.UUID;
import java.util.concurrent.CountDownLatch;

import javax.xml.bind.JAXBException;

import org.apache.log4j.Logger;
import org.junit.Before;
import org.junit.Test;
import org.springframework.test.annotation.DirtiesContext;

import com.google.common.base.Preconditions;
import com.mango.mif.client.api.TaskExecutionMessageHandler;
import com.mango.mif.connector.TestsHelper;
import com.mango.mif.core.api.ResourceComponentException;
import com.mango.mif.core.impl.DefaultResourceComponent;
import com.mango.mif.core.impl.DefaultWorkspaceManager;
import com.mango.mif.domain.ExecutionRequest;
import com.mango.mif.domain.ExecutionResponse;
import com.mango.mif.utils.ExecutionRequestHelper;
import com.mango.mif.utils.JAXBUtils;
import com.mango.mif.utils.jaxb.ExecutionJaxbUtils;

/**
 * This test is to verify the mock MIF behaviour that needs to be provided to the MIF clients.
 * @version $Revision: $ as of $Date: $
 * <p>SVN Entry : $HeadURL: $
 * <p>SVN ID    : $Id: $
 * <p>Last edited by : $Author: $
 */
public class MockClientIntegrationTest implements TaskExecutionMessageHandler {
	
	/** The Constant LOG. */
	final static Logger LOG = Logger.getLogger(MockClientIntegrationTest.class);
	
	//To track all threads are done
	/** The Constant THREAD_COUNT. */
	private static final int THREAD_COUNT = 5;

	/** The request map. */
	static Map<UUID, String> requestMap = new HashMap<UUID, String>(THREAD_COUNT); 
	
    /** Used to synchronize test thread and requests handling. */
    static CountDownLatch signal = new CountDownLatch(THREAD_COUNT);
    
    private DefaultResourceComponent resourceComponent;
    
    /**
	 * Contains consumed Execution Responses 
	 */
    static Map<UUID, ExecutionResponse> resultsMap = new HashMap<UUID, ExecutionResponse>(THREAD_COUNT); 
    
    /** The mock of task execution manager. */
    MockTaskExecutionManager mockTaskExecutionManager;
    
    @Before
    public void beforeTest(){
    	mockTaskExecutionManager = new  MockTaskExecutionManager();

    	//Setting up the resource components
    	resourceComponent  = new DefaultResourceComponent();
    	File tmpDirectory = TestsHelper.createTmpDirectory();
		resourceComponent.setWorkspaceManager(new DefaultWorkspaceManager(tmpDirectory));
        
        mockTaskExecutionManager.setWorkingDirectory(tmpDirectory);
    	mockTaskExecutionManager.setExecutionMessageHandler(this);
    }
    
	@DirtiesContext
    @Test(timeout=120000)
	public void shouldAddTheRequestToTheRegistry() throws ResourceComponentException, JAXBException, InterruptedException{
        for (int i = 0; i < THREAD_COUNT; i++) {
            String randomUUID = resourceComponent.createRequestDirectory();
            String messageToBeSent = ExecutionRequestHelper.createExecutionRequestMsg(randomUUID,resourceComponent);
            LOG.info("Sending the request:" + messageToBeSent);
            
            requestMap.put(UUID.fromString(randomUUID), messageToBeSent);
            ExecutionRequest reqUnmarshalled = (ExecutionRequest)JAXBUtils.unmarshall(messageToBeSent, ExecutionJaxbUtils.EXECUTION_REQUEST_CLASS_ARRAY);
            mockTaskExecutionManager.submit(reqUnmarshalled);
        }
        signal.await();
	}
	
	/**
	 * Checks if the response text contains the unique ID which are submitted as part of requests. 
	 * @param executionResponse
	 * @return
	 */
	private boolean checkIfTheReceivedTextContainsPartOfRequest(ExecutionResponse executionResponse) {
		Preconditions.checkNotNull(executionResponse, "Received text message should not be null");
		String uuid = executionResponse.getRequestId().toString();
		for(UUID key : requestMap.keySet()) {
			if(uuid.equals(key.toString())) {
                resultsMap.put(UUID.fromString(uuid), executionResponse);
				return true;
			}
		}
		return false;
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

	


}
