/* File			:  ExecutionRequestBuilderTest.java
 * Project		:  MangoInteroperabilityFramework-trunk
 * Created on	:  5 Apr 2012
 */
package com.mango.mif.domain;

import static org.hamcrest.CoreMatchers.equalTo;
import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertThat;

import java.util.UUID;

import javax.xml.bind.JAXBException;

import org.junit.Test;

import com.mango.mif.utils.JAXBUtils;
import com.mango.mif.utils.jaxb.ExecutionJaxbUtils;


/**
 * 
 * Execution Request Builder
 * 
 * @version $Revision: $ as of $Date: $
 * <p>SVN Entry : $HeadURL: $
 * <p>SVN ID    : $Id: $
 * <p>Last edited by : $Author: $
 */
public class ExecutionRequestBuilderTest {

    private static final String TEST_PASSWORD = "test password";
	private static final String USER_NAME = "userName";
	private static final String NM_VERSION = "-nm_version";
	
	private static final String REQUEST_ID = UUID.randomUUID().toString();
	/**
     * Builds a message
     * @throws JAXBException
     */
    @Test
    public void testGetExecutionRequestMsg() throws JAXBException {
        ExecutionRequestBuilder builder = new ExecutionRequestBuilder()
        .setCommand("Command Name").setExecutionType("CONNECTOR_ID").
        setRequestId(REQUEST_ID).setUserName(USER_NAME).setUserPassword(TEST_PASSWORD);
        
        String msg = builder.getExecutionRequestMsg();

        assertNotNull(msg);
        
        ExecutionRequest reqUnmarshalled = (ExecutionRequest)JAXBUtils.unmarshall(msg, ExecutionJaxbUtils.CONTEXT_CLASSES);

    	assertThat("user name must match", reqUnmarshalled.getUserName(), is(equalTo(USER_NAME)));
		assertThat("user password must match", reqUnmarshalled.getUserPassword(), is(equalTo(TEST_PASSWORD)));
        
        
    }
    /**
     * Builds a message
     * @throws JAXBException
     */
    @Test
    public void testGetExecutionRequest() throws JAXBException {
        ExecutionRequestBuilder builder = new ExecutionRequestBuilder()
        .setCommand("Command Name").setExecutionType("CONNECTOR_ID").
        setRequestId(REQUEST_ID).setExecutionParameters(NM_VERSION);
        
        ExecutionRequest reqUnmarshalled = builder.getExecutionRequest();

        assertThat("Unmarshalled  request : Execution Parameters ,  must match ",reqUnmarshalled.getExecutionParameters(), is(equalTo(NM_VERSION)));
        
    }
    /**
     * Execution type must be set
     * @throws JAXBException
     */
    @Test(expected=NullPointerException.class)
    public void shouldThrowExceptionWhenExecutionTypeIsNotSet() throws JAXBException {
        ExecutionRequestBuilder builder = new ExecutionRequestBuilder()
            .setRequestId(REQUEST_ID);
        
        builder.getExecutionRequestMsg();

    }
    /**
     * Request ID must be set
     * @throws JAXBException
     */
    @Test(expected=NullPointerException.class)
    public void shouldThrowExceptionWhenExecutionRequestIdIsNotSet() throws JAXBException {
        ExecutionRequestBuilder builder = new ExecutionRequestBuilder()
        .setExecutionType("CONNECTOR_ID");
        
        builder.getExecutionRequestMsg();

    }
}
