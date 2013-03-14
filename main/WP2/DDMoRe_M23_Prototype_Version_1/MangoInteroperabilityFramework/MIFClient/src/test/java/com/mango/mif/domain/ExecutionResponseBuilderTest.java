/* File			:  ExecutionRequestBuilderTest.java
 * Project		:  MangoInteroperabilityFramework-trunk
 * Created on	:  5 Apr 2012
 */
package com.mango.mif.domain;

import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.fail;

import java.util.UUID;

import javax.xml.bind.JAXBException;

import org.junit.Test;

import com.mango.mif.utils.JAXBUtils;
import com.mango.mif.utils.jaxb.ExecutionJaxbUtils;


/**
 * 
 * Execution Response Builder Test
 * 
 * @version $Revision: $ as of $Date: $
 * <p>SVN Entry : $HeadURL: $
 * <p>SVN ID    : $Id: $
 * <p>Last edited by : $Author: $
 */
public class ExecutionResponseBuilderTest {
    private final static String requestId = UUID.randomUUID().toString();
    /**
     * Builds a message
     * @throws JAXBException
     */
    @Test
    public void testGetExecutionResponseMsg() throws JAXBException {
        ExecutionResponseBuilder builder = new ExecutionResponseBuilder()
        .setRequestId(requestId).setStatus("TEST_STATUS").
        setResult("TEST_RESULT");
        
        String msg = builder.getExecutionResponseMsg();

        assertNotNull(msg);
        ExecutionResponse reqUnmarshalled = (ExecutionResponse)JAXBUtils.unmarshall(msg, ExecutionJaxbUtils.CONTEXT_CLASSES);

        assertNotNull(reqUnmarshalled.getRequestId());
    }
    /**
     * Builds a message
     * @throws JAXBException
     */
    @Test
    public void testGetExecutionResponse() throws JAXBException {
        ExecutionResponseBuilder builder = new ExecutionResponseBuilder()
        .setRequestId(requestId).setStatus("TEST_STATUS").
        setResult("TEST_RESULT");
        
        ExecutionResponse reqUnmarshalled = builder.getExecutionResponse();

        assertNotNull(reqUnmarshalled.getRequestId());
        
    }
    /**
     * Builds a message and unmarshalls it to an object
     * @throws JAXBException
     */
    @Test
    public void testUnMarshallResultMsg() throws JAXBException {
        ExecutionResponseBuilder builder = new ExecutionResponseBuilder()
        .setRequestId(requestId).setStatus("TEST_STATUS").
        setResult("TEST_RESULT");
        
        String msg = builder.getExecutionResponseMsg();
        
        ExecutionResponse request = JAXBUtils.unmarshall(ExecutionResponse.class, msg);
        
        assertNotNull(request);
    }
    /**
     * Builds a message with no command parameters definitions
     * @throws JAXBException
     */
    @Test
    public void testGetExecutionResponseMsgNoInputParametersDefinitions() throws JAXBException {
        ExecutionResponseBuilder builder = new ExecutionResponseBuilder()
        .setRequestId(requestId).setStatus("TEST_STATUS").
        setResult("TEST_RESULT");
        
        String msg = builder.getExecutionResponseMsg();
        
        assertNotNull(msg);
    }
    /**
     * Command Name must be set
     * @throws JAXBException
     */
    @Test
    public void testGetExecutionResponseMsgNoResult() throws JAXBException {
        ExecutionResponseBuilder builder = new ExecutionResponseBuilder()
        .setRequestId(UUID.randomUUID().toString()).setStatus("TEST_STATUS");
        
        String msg = builder.getExecutionResponseMsg();
        
        assertNotNull(msg);
    }
    /**
     * Connector ID must be set
     * @throws JAXBException
     */
    @Test(expected=NullPointerException.class)
    public void testGetExecutionRequestMsgNoStatus() throws JAXBException {
        ExecutionResponseBuilder builder = new ExecutionResponseBuilder()
        .setRequestId(requestId).
        setResult("TEST_RESULT");
        
        builder.getExecutionResponseMsg();
        fail("An exception should be thrown.");
    }
    /**
     * Request ID must be set
     * @throws JAXBException
     */
    @Test(expected=NullPointerException.class)
    public void testGetExecutionRequestMsgNoRequestID() throws JAXBException {
        ExecutionResponseBuilder builder = new ExecutionResponseBuilder()
        .setStatus("TEST_STATUS").
        setResult("TEST_RESULT");
        
        builder.getExecutionResponseMsg();
        fail("An exception should be thrown.");
    }
}
