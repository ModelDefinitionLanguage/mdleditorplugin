/* File			:  MockTaskExecutionManager.java
 * Project		:  MangoInteroperabilityFramework
 * Created on	:  Apr 17, 2012
 */
package com.mango.mif.client.impl;

import java.io.File;
import java.util.Map;
import java.util.UUID;

import javax.xml.bind.JAXBException;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;

import com.google.common.base.Preconditions;
import com.google.common.collect.Maps;
import com.mango.mif.client.api.TaskExecutionManager;
import com.mango.mif.client.api.TaskExecutionMessageHandler;
import com.mango.mif.client.err.TaskExecutorExceptions;
import com.mango.mif.core.api.ResourceComponentException;
import com.mango.mif.core.impl.DefaultResourceComponent;
import com.mango.mif.core.impl.DefaultWorkspaceManager;
import com.mango.mif.domain.ExecutionRequest;
import com.mango.mif.domain.ExecutionResponse;
import com.mango.mif.domain.Parameter;
import com.mango.mif.domain.ParameterType;
import com.mango.mif.utils.JAXBUtils;
import com.mango.mif.utils.jaxb.ExecutionJaxbUtils;

/**
 * This class mocks the MIF clients behaviour which will be provided   
 * @version $Revision: $ as of $Date: $
 * <p>SVN Entry : $HeadURL: $
 * <p>SVN ID    : $Id: $
 * <p>Last edited by : $Author: $
 */
public class MockTaskExecutionManager implements TaskExecutionManager {
	
	/** The Constant LOG. */
	static final Logger LOG = Logger.getLogger(MockTaskExecutionManager.class);
	
	/** The Resource Component used for handling the resources*/
	DefaultResourceComponent defaultResourceComponent = new DefaultResourceComponent();
	
    /**
     * Test resource that is uploaded to resource component
     */
    private static String TEST_RESOURCE = "test.R";
    
    /** The working directory for the execution. */
    @Autowired
    File workingDirectory;
    
    /** The execution message handler. */
    @Autowired
    TaskExecutionMessageHandler executionMessageHandler;

	/** (non-Javadoc)
	 * @see com.mango.mif.client.api.TaskExecutionManager#handleResultMessage(java.lang.String)
	 */
	@Override
	public synchronized String handleResultMessage(final String responseReceived) {
		ExecutionResponse response = null;
		try {
			response = (ExecutionResponse)JAXBUtils.unmarshall(responseReceived, ExecutionJaxbUtils.EXECUTION_RESPONSE_CLASS_ARRAY);
		} catch (JAXBException e) {
			LOG.error("JAXBException", e);
		}
		executionMessageHandler.handle(response);
		return responseReceived;
	}

	/**
	 * This method mock the behaviour of the MIF client components which will post the request and consume the response 
	 * and then inform back the navigator component.  
	 */
	@Override
	public synchronized void submit(final ExecutionRequest request) {
		Preconditions.checkNotNull(request, TaskExecutorExceptions.INVALID_REQUEST_MESSAGE.getMessage());
		final UUID  requestId = request.getRequestId();
		if (requestId == null) {
			throw new RuntimeException(TaskExecutorExceptions.REQUEST_ID_NOT_PRESENT.getMessage());
		}
		
		new Thread( new Runnable() {

			@Override
			public void run() {
				
				LOG.info("Processing request for " + requestId);
				defaultResourceComponent.setWorkspaceManager(new DefaultWorkspaceManager(workingDirectory));
				 String resourceUUID = null;
				try {
					//Mocking the processing of the request.
					Thread.sleep(5000);
				    resourceUUID = defaultResourceComponent.addResponseResource(this.getClass().getResource(TEST_RESOURCE), requestId.toString(), TEST_RESOURCE).getUuid();
				} catch (ResourceComponentException e) {
					LOG.error("ResourceComponentException", e);
				} catch (InterruptedException e) {
					LOG.error("InterruptedException", e);
				}
				
				Map<String,Parameter> results = Maps.newHashMap();
				
				ExecutionResponse executionResponse = new ExecutionResponse();
			    executionResponse.setRequestId(requestId);
			    results.put(TEST_RESOURCE, new Parameter(TEST_RESOURCE, TEST_RESOURCE, resourceUUID,ParameterType.File));
			    executionResponse.setParameters(results);
			    String marshallRequest = null;    
			    try {
					marshallRequest = JAXBUtils.marshall(executionResponse, ExecutionJaxbUtils.EXECUTION_RESPONSE_CLASS_ARRAY);
				} catch (JAXBException e) {
					LOG.error("JAXBException", e);
				}
				//invoke the handle Result Message
				handleResultMessage(marshallRequest);
			}
		}).start();
	}

	@Override
	public void submit(String string) {
	}

	public File getWorkingDirectory() {
		return workingDirectory;
	}

	public void setWorkingDirectory(File workingDirectory) {
		this.workingDirectory = workingDirectory;
	}


	@Override
	public void setExecutionMessageHandler(
			TaskExecutionMessageHandler executionMessageHandler) {
		this.executionMessageHandler = executionMessageHandler;
	}

	public TaskExecutionMessageHandler getExecutionMessageHandler() {
		return executionMessageHandler;
	}


}
