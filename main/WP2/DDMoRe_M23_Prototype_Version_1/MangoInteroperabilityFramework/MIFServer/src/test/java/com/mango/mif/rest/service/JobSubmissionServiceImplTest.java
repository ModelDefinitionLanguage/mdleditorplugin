/* File			:  JobSubmissionServiceImplTest.java
 * Project		:  MIFServer
 * Created on	:  Nov 20, 2012
 */
package com.mango.mif.rest.service;

import static org.junit.Assert.*;

import java.io.File;

import javax.xml.bind.JAXBException;

import org.apache.log4j.Logger;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import static org.mockito.Mockito.*;
import org.mockito.runners.MockitoJUnitRunner;

import com.mango.mif.connector.Connector;
import com.mango.mif.core.api.SharedLocationManager;
import com.mango.mif.core.exec.ExecutionException;
import com.mango.mif.core.exec.Invoker;
import com.mango.mif.core.exec.InvokerResult;
import com.mango.mif.core.exec.invoker.InvokerFactory;
import com.mango.mif.core.impl.ConnectorsRegistry;
import com.mango.mif.domain.ExecutionRequestBuilder;
import com.mango.mif.exception.MIFException;


/**
 * Tests JobSubmissionServiceImpl
 * 
 * @version $Revision: $ as of $Date: $
 * <p>SVN Entry : $HeadURL: $
 * <p>SVN ID    : $Id: $
 * <p>Last edited by : $Author: $
 */
@RunWith(MockitoJUnitRunner.class)
public class JobSubmissionServiceImplTest {
    private final static Logger LOG = Logger.getLogger(JobSubmissionServiceImplTest.class);
    
    private final static String MOCK_PATH = "PATH";
    
    JobSubmissionServiceImpl jobSubmissionService;
    
    @Mock SharedLocationManager sharedLocationManager;
    
    @Mock InvokerFactory invokerFactory;

    @Mock Invoker invoker;
    
    @Mock ConnectorsRegistry connectorsRegistry;
    
    @Mock InvokerResult successResult;
    
    @Mock Connector connector;
    
    @Before
    public void setUp() throws ExecutionException {
        jobSubmissionService = new JobSubmissionServiceImpl();
        jobSubmissionService.setConnectorsRegistry(connectorsRegistry);
        jobSubmissionService.setInvokerFactory(invokerFactory);
        jobSubmissionService.setSharedLocationManager(sharedLocationManager);
        
        when(successResult.getExitCode()).thenReturn(0);
        when(invokerFactory.createRunAsUserInvoker((String)any(),(String)any())).thenReturn(invoker);
        when(invoker.execute((String)any())).thenReturn(successResult);
        when(invoker.execute((String)any(), (String)any())).thenReturn(successResult);
        
        when(connectorsRegistry.getConnector("EXECUTION_TYPE")).thenReturn(connector);
        
        when(sharedLocationManager.getRequestDirectory((String)any())).thenReturn(new File(MOCK_PATH));
        when(sharedLocationManager.getRequestInputDirectory((String)any())).thenReturn(new File(MOCK_PATH));
        when(sharedLocationManager.getRequestOutputDirectory((String)any())).thenReturn(new File(MOCK_PATH));
    }
    @Test
    public void shouldPrepareRequest() throws MIFException {
        String user = "USER";
        String password = "PASSWORD";
        
        String response = jobSubmissionService.prepare(user, password);
        
        LOG.debug(response);
        assertNotNull(response);
    }

    @Test(expected=MIFException.class)
    public void shouldThrowExceptionIfUserNameIsNotSet() throws MIFException {
        String user = "";
        String password = "PASSWORD";

        jobSubmissionService.prepare(user, password);
    }
    
    @Test(expected=MIFException.class)
    public void shouldThrowExceptionIfUserPasswordIsNotSet() throws MIFException {
        String user = "USER";
        String password = "";

        jobSubmissionService.prepare(user, password);
    }
    @SuppressWarnings("unchecked")
    @Test(expected=MIFException.class)
    public void shouldThrowExceptionWhenRequestDirectoryCreationFailsWithException() throws MIFException, ExecutionException {
        String user = "USER";
        String password = "PASSWORD";
        when(invoker.execute((String)any())).thenThrow(ExecutionException.class);
        when(invoker.execute((String)any(), (String)any())).thenThrow(ExecutionException.class);
        jobSubmissionService.prepare(user, password);
    }
    
    @Test(expected=MIFException.class)
    public void shouldThrowExceptionWhenRequestDirectoryCreationFails() throws MIFException, ExecutionException {
        String user = "USER";
        String password = "PASSWORD";
        InvokerResult invokerResult = mock(InvokerResult.class);
        when(invokerResult.getExitCode()).thenReturn(1);
        when(invoker.execute((String)any())).thenReturn(invokerResult);
        when(invoker.execute((String)any(), (String)any())).thenReturn(invokerResult);
        jobSubmissionService.prepare(user, password);
    }
    
    @Test(expected=MIFException.class)
    public void shouldThrowExceptionIfExecuteRequestIsEmpty() throws MIFException, JAXBException {
        jobSubmissionService.execute("");
    }
    
    @Test
    public void shouldExecuteRequest() throws MIFException, JAXBException {
        String expected = "REQUEST_ID";
        ExecutionRequestBuilder requestBuilder = new ExecutionRequestBuilder();
        requestBuilder.setRequestId(expected).setExecutionType("EXECUTION_TYPE");
        
        String response = jobSubmissionService.execute(requestBuilder.getExecutionRequestMsg());
        
        assertEquals(expected,response);
        verify(connector).executeJobRequest((String)any(), (String)any());
    }

    @Test(expected=MIFException.class)
    public void shouldThorwExceptionIfConnectorForExecutionTypeDoesNotExist() throws MIFException, JAXBException {
        String expected = "REQUEST_ID";
        ExecutionRequestBuilder requestBuilder = new ExecutionRequestBuilder();
        requestBuilder.setRequestId(expected).setExecutionType("INVALID_EXECUTION_TYPE");
        
        jobSubmissionService.execute(requestBuilder.getExecutionRequestMsg());
    }
}
