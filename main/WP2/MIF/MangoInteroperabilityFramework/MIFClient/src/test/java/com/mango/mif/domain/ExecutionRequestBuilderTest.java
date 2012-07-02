/* File			:  ExecutionRequestBuilderTest.java
 * Project		:  MangoInteroperabilityFramework-trunk
 * Created on	:  5 Apr 2012
 */
package com.mango.mif.domain;

import static org.hamcrest.CoreMatchers.equalTo;
import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertThat;
import static org.junit.Assert.fail;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

import java.util.List;
import java.util.Map;
import java.util.UUID;

import javax.xml.bind.JAXBException;

import org.junit.Test;

import com.google.common.collect.Lists;
import com.google.common.collect.Maps;
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
	/**
     * Builds a message
     * @throws JAXBException
     */
    @Test
    public void testGetExecutionRequestMsg() throws JAXBException {
        ExecutionRequestBuilder builder = new ExecutionRequestBuilder()
        .setCommandName("Command Name").setConnectorID("CONNECTOR_ID").
        setRequestId(UUID.randomUUID()).setInputParameters(createInputParameters()).
        setCommandParameterDefinitions(createCommandParameterDefinitions()).setUserName(USER_NAME).setUserPassword(TEST_PASSWORD);
        
        String msg = builder.getExecutionRequestMsg();

        assertNotNull(msg);
        
        ExecutionRequest reqUnmarshalled = (ExecutionRequest)JAXBUtils.unmarshall(msg, ExecutionJaxbUtils.EXECUTION_REQUEST_CLASS_ARRAY);

        assertNotNull(reqUnmarshalled.getCommandDefinition());
        assertNotNull(reqUnmarshalled.getParameters());
        assertNotNull((Parameter)reqUnmarshalled.getParameters().get("PARAMETER_1"));
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
        .setCommandName("Command Name").setConnectorID("CONNECTOR_ID").
        setRequestId(UUID.randomUUID()).setInputParameters(createInputParameters()).
        setCommandParameterDefinitions(createCommandParameterDefinitions());
        
        ExecutionRequest reqUnmarshalled = builder.getExecutionRequest();

        assertNotNull(reqUnmarshalled.getCommandDefinition());
        assertNotNull(reqUnmarshalled.getParameters());
        assertNotNull((Parameter)reqUnmarshalled.getParameters().get("PARAMETER_1"));
        
    }
    /**
     * Builds a message and unmarshalls it to an object
     * @throws JAXBException
     */
    @Test
    public void testUnMarshallRequestMsg() throws JAXBException {
        ExecutionRequestBuilder builder = new ExecutionRequestBuilder()
        .setCommandName("Command Name").setConnectorID("CONNECTOR_ID").
        setRequestId(UUID.randomUUID()).setInputParameters(createInputParameters()).
        setCommandParameterDefinitions(createCommandParameterDefinitions());
        
        String msg = builder.getExecutionRequestMsg();
        
        ExecutionRequest request = JAXBUtils.unmarshall(ExecutionRequest.class, msg);
        
        assertNotNull(request);
    }
    /**
     * Builds a message with no input parameters
     * @throws JAXBException
     */
    @Test
    public void testGetExecutionRequestMsgNoInputParameters() throws JAXBException {
        ExecutionRequestBuilder builder = new ExecutionRequestBuilder()
        .setCommandName("Command Name").setConnectorID("CONNECTOR_ID").
        setRequestId(UUID.randomUUID()).
        setCommandParameterDefinitions(createCommandParameterDefinitions());
        
        String msg = builder.getExecutionRequestMsg();
        
        assertNotNull(msg);
    }
    /**
     * Builds a message with no command parameters definitions
     * @throws JAXBException
     */
    @Test
    public void testGetExecutionRequestMsgNoCommandParametersDefinitions() throws JAXBException {
        ExecutionRequestBuilder builder = new ExecutionRequestBuilder()
        .setCommandName("Command Name").setConnectorID("CONNECTOR_ID").
        setRequestId(UUID.randomUUID()).setInputParameters(createInputParameters());
        
        String msg = builder.getExecutionRequestMsg();
        
        assertNotNull(msg);
    }
    /**
     * Command Name must be set
     * @throws JAXBException
     */
    @Test(expected=NullPointerException.class)
    public void testGetExecutionRequestMsgNoCommandName() throws JAXBException {
        ExecutionRequestBuilder builder = new ExecutionRequestBuilder()
        .setConnectorID("CONNECTOR_ID").
        setRequestId(UUID.randomUUID()).setInputParameters(createInputParameters()).
        setCommandParameterDefinitions(createCommandParameterDefinitions());
        
        builder.getExecutionRequestMsg();
        
        fail("An exception should be thrown.");
        
    }
    /**
     * Connector ID must be set
     * @throws JAXBException
     */
    @Test(expected=NullPointerException.class)
    public void testGetExecutionRequestMsgNoConnectorID() throws JAXBException {
        ExecutionRequestBuilder builder = new ExecutionRequestBuilder()
        .setCommandName("Command Name").
        setRequestId(UUID.randomUUID()).setInputParameters(createInputParameters()).
        setCommandParameterDefinitions(createCommandParameterDefinitions());
        
        builder.getExecutionRequestMsg();

        fail("An exception should be thrown.");
    }
    /**
     * Request ID must be set
     * @throws JAXBException
     */
    @Test(expected=NullPointerException.class)
    public void testGetExecutionRequestMsgNoRequestID() throws JAXBException {
        ExecutionRequestBuilder builder = new ExecutionRequestBuilder()
        .setCommandName("Command Name").setConnectorID("CONNECTOR_ID").setInputParameters(createInputParameters()).
        setCommandParameterDefinitions(createCommandParameterDefinitions());
        
        builder.getExecutionRequestMsg();

        fail("An exception should be thrown.");
    }
    /**
     * Creates command parameters defintions
     * @return
     */
    private List<ParameterDefinition> createCommandParameterDefinitions() {
        List<ParameterDefinition> parameters = Lists.newArrayList();
        parameters.add(createMockParameterDefinition(ParameterDirection.IN,ParameterType.Scalar,"PARAMETER_1"));
        parameters.add(createMockParameterDefinition(ParameterDirection.IN,ParameterType.Scalar,"PARAMETER_2"));
        parameters.add(createMockParameterDefinition(ParameterDirection.OUT,ParameterType.Scalar,"PARAMETER_3"));
        return parameters;
    }
    /**
     * Creates a mock parameter definition
     * @param direction
     * @param type
     * @param name
     * @return
     */
    private ParameterDefinition createMockParameterDefinition(ParameterDirection direction, ParameterType type, String name) {
        ParameterDefinition pDef = mock(ParameterDefinition.class);
        when(pDef.getDirection()).thenReturn(direction);
        when(pDef.getType()).thenReturn(type);
        when(pDef.getName()).thenReturn(name);
        return pDef;
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
