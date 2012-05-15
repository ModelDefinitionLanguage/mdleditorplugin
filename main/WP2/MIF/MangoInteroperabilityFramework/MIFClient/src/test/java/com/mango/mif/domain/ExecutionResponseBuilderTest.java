/* File			:  ExecutionRequestBuilderTest.java
 * Project		:  MangoInteroperabilityFramework-trunk
 * Created on	:  5 Apr 2012
 */
package com.mango.mif.domain;

import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.fail;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;
import java.util.Map;
import java.util.UUID;
import javax.xml.bind.JAXBException;
import org.junit.Test;
import com.google.common.collect.Maps;
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

    /**
     * Builds a message
     * @throws JAXBException
     */
    @Test
    public void testGetExecutionResponseMsg() throws JAXBException {
        ExecutionResponseBuilder builder = new ExecutionResponseBuilder()
        .setRequestId(UUID.randomUUID()).setStatus("TEST_STATUS").
        setResult("TEST_RESULT").setParameters(createInputParameters());
        
        String msg = builder.getExecutionResponseMsg();

        assertNotNull(msg);
        ExecutionResponse reqUnmarshalled = (ExecutionResponse)JAXBUtils.unmarshall(msg, ExecutionJaxbUtils.EXECUTION_RESPONSE_CLASS_ARRAY);

        assertNotNull(reqUnmarshalled.getRequestId());
        assertNotNull(reqUnmarshalled.getParameters());
        assertNotNull((Parameter)reqUnmarshalled.getParameters().get("PARAMETER_1"));
    }
    /**
     * Builds a message
     * @throws JAXBException
     */
    @Test
    public void testGetExecutionResponse() throws JAXBException {
        ExecutionResponseBuilder builder = new ExecutionResponseBuilder()
        .setRequestId(UUID.randomUUID()).setStatus("TEST_STATUS").
        setResult("TEST_RESULT").setParameters(createInputParameters());
        
        ExecutionResponse reqUnmarshalled = builder.getExecutionResponse();

        assertNotNull(reqUnmarshalled.getRequestId());
        assertNotNull(reqUnmarshalled.getParameters());
        assertNotNull((Parameter)reqUnmarshalled.getParameters().get("PARAMETER_1"));
        
    }
    /**
     * Builds a message and unmarshalls it to an object
     * @throws JAXBException
     */
    @Test
    public void testUnMarshallResultMsg() throws JAXBException {
        ExecutionResponseBuilder builder = new ExecutionResponseBuilder()
        .setRequestId(UUID.randomUUID()).setStatus("TEST_STATUS").
        setResult("TEST_RESULT").setParameters(createInputParameters());
        
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
        .setRequestId(UUID.randomUUID()).setStatus("TEST_STATUS").
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
        .setRequestId(UUID.randomUUID()).setStatus("TEST_STATUS");
        
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
        .setRequestId(UUID.randomUUID()).
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
    /**
     * Creates mock input parameters
     * @return
     */
    private Map<String, Parameter> createInputParameters() {
        Map<String,Parameter> parameters = Maps.newHashMap();
        Parameter par = createMockParameter("PARAMETER_1","VALUE",UUID.randomUUID().toString());
        parameters.put(par.getName(), par);
        par = createMockParameter("PARAMETER_2","VALUE",UUID.randomUUID().toString());
        parameters.put(par.getName(), par);
        return parameters;
    }
    /**
     * Creates mock parameter
     * @param name
     * @param value
     * @param uuid
     * @return
     */
    private Parameter createMockParameter(String name, String value, String uuid) {
        Parameter pDef = mock(Parameter.class);
        when(pDef.getName()).thenReturn(name);
        when(pDef.getValue()).thenReturn(value);
        when(pDef.getResourceUuid()).thenReturn(uuid);
        return pDef;
    }
}
