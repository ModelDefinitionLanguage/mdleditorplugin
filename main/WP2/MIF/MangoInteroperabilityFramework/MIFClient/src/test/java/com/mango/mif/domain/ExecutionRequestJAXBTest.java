/* File			:  ExecutionRequestJAXBTest.java
 * Project		:  MangoInteroperabilityFramework
 * Created on	:  Apr 4, 2012
 */
package com.mango.mif.domain;

import static org.hamcrest.CoreMatchers.equalTo;
import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.CoreMatchers.notNullValue;
import static org.junit.Assert.assertThat;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.UUID;

import javax.xml.bind.JAXBException;

import org.apache.log4j.Logger;
import org.junit.Test;

import com.google.common.collect.Lists;
import com.google.common.collect.Maps;
import com.mango.mif.utils.JAXBUtils;
import com.mango.mif.utils.jaxb.ExecutionJaxbUtils;

/**
 * Test for {@link ExecutionRequest}.
 * @version $Revision: $ as of $Date: $
 * <p>SVN Entry : $HeadURL: $
 * <p>SVN ID    : $Id: $
 * <p>Last edited by : $Author: $
 */
public class ExecutionRequestJAXBTest {
	
	private static final UUID REQUEST_ID = UUID.randomUUID();
	private static final String EXECUTE_R_SCRIPT = "executeRScript";
	final static Logger LOG = Logger.getLogger(ExecutionRequestJAXBTest.class);
    /**
     * Dataset parameter key
     */
    private final static String DATASET_KEY = "dataset";
    /**
     * R script parameter key
     */
    private final static String SCRIPT_KEY = "script";
    /**
     * result key
     */
    private final static String RESULT_KEY = "dataset-result";

	/**
	 * Test jaxb for execution request.
	 */
	@Test
	public void testJaxbForExecutionRequest() throws JAXBException {
		
		List<ParameterDefinition> parameterDefinitions = prepareParameterDefnitions();
		
		CommandDefinition commandDefinition = new CommandDefinition(EXECUTE_R_SCRIPT, parameterDefinitions);
		
		Map<String, Parameter> parameters = prepareParametersForRequest();
		
		ExecutionRequest startRequest = prepareExecutionRequest(commandDefinition,
				parameters);
		
			String marshallRequest = JAXBUtils.marshall(startRequest, ExecutionJaxbUtils.EXECUTION_REQUEST_CLASS_ARRAY);
			
			LOG.info("THE REQUEST IS NOW" + marshallRequest);
			
			ExecutionRequest reqUnmarshalled = (ExecutionRequest)JAXBUtils.unmarshall(marshallRequest, ExecutionJaxbUtils.EXECUTION_REQUEST_CLASS_ARRAY);
			assertThat("Unmarshalled request must not be null ",reqUnmarshalled, is(notNullValue()));
			assertThat("Unmarshalled request ID must match ",reqUnmarshalled.getRequestId(), is(equalTo(REQUEST_ID)));
			assertThat("Unmarshalled  request : CommandDefinition ,  must not be null ",reqUnmarshalled.getCommandDefinition(), is(notNullValue()));
			assertThat("Unmarshalled  request : Parameters ,  must not be null ",reqUnmarshalled.getParameters(), is(notNullValue()));
            assertThat("Unmarshalled  request : Parameter DATASET_KEY must not be null ",(Parameter)reqUnmarshalled.getParameters().get(DATASET_KEY), is(notNullValue()));
            assertThat("Unmarshalled  request : Parameters ,  must match ",reqUnmarshalled.getParameters().size(), is(equalTo(2)));
			assertThat("Unmarshalled  request : CommandDefinition Name ,  must match ",reqUnmarshalled.getCommandDefinition().getName(), is(equalTo(EXECUTE_R_SCRIPT)));
			assertThat("Unmarshalled  request : CommandDefinition Param Size ,  must match ",reqUnmarshalled.getCommandDefinition().getParameters().size(), is(equalTo(3)));
			
	}
	
	   @Test
	    public void testEmptyParameterDefinitionsAreAllowed() throws JAXBException {
	        
	        List<ParameterDefinition> parameterDefinitions = Lists.newArrayList();
	        
	        CommandDefinition commandDefinition = new CommandDefinition(EXECUTE_R_SCRIPT, parameterDefinitions);
	        
	        Map<String, Parameter> parameters = prepareParametersForRequest();
	        
	        ExecutionRequest startRequest = prepareExecutionRequest(commandDefinition,
	                parameters);
	        
	            String marshallRequest = JAXBUtils.marshall(startRequest, ExecutionJaxbUtils.EXECUTION_REQUEST_CLASS_ARRAY);
	            
	            LOG.info("THE REQUEST IS NOW" + marshallRequest);
	            
	            ExecutionRequest reqUnmarshalled = (ExecutionRequest)JAXBUtils.unmarshall(marshallRequest, ExecutionJaxbUtils.EXECUTION_REQUEST_CLASS_ARRAY);
	            assertThat("Unmarshalled request must not be null ",reqUnmarshalled, is(notNullValue()));
	            assertThat("Unmarshalled request ID must match ",reqUnmarshalled.getRequestId(), is(equalTo(REQUEST_ID)));
	            assertThat("Unmarshalled  request : CommandDefinition ,  must not be null ",reqUnmarshalled.getCommandDefinition(), is(notNullValue()));
	            assertThat("Unmarshalled  request : Parameters ,  must not be null ",reqUnmarshalled.getParameters(), is(notNullValue()));
                assertThat("Unmarshalled  request : Parameters ,  must match ",reqUnmarshalled.getParameters().size(), is(equalTo(2)));
	            assertThat("Unmarshalled  request : CommandDefinition Name ,  must match ",reqUnmarshalled.getCommandDefinition().getName(), is(equalTo(EXECUTE_R_SCRIPT)));
	            assertThat("Unmarshalled  request : CommandDefinition Param Size ,  must match ",reqUnmarshalled.getCommandDefinition().getParameters().size(), is(equalTo(0)));
	            
	    }

       @Test
        public void testEmptyParametersAreAllowed() throws JAXBException {

            List<ParameterDefinition> parameterDefinitions = prepareParameterDefnitions();
            CommandDefinition commandDefinition = new CommandDefinition(EXECUTE_R_SCRIPT, parameterDefinitions);
            
            Map<String, Parameter> parameters = Maps.newHashMap();
            
            ExecutionRequest startRequest = prepareExecutionRequest(commandDefinition,
                    parameters);
            
                String marshallRequest = JAXBUtils.marshall(startRequest, ExecutionJaxbUtils.EXECUTION_REQUEST_CLASS_ARRAY);
                
                LOG.info("THE REQUEST IS NOW" + marshallRequest);
                
                ExecutionRequest reqUnmarshalled = (ExecutionRequest)JAXBUtils.unmarshall(marshallRequest, ExecutionJaxbUtils.EXECUTION_REQUEST_CLASS_ARRAY);
                assertThat("Unmarshalled request must not be null ",reqUnmarshalled, is(notNullValue()));
                assertThat("Unmarshalled request ID must match ",reqUnmarshalled.getRequestId(), is(equalTo(REQUEST_ID)));
                assertThat("Unmarshalled  request : CommandDefinition ,  must not be null ",reqUnmarshalled.getCommandDefinition(), is(notNullValue()));
                assertThat("Unmarshalled  request : Parameters ,  must not be null ",reqUnmarshalled.getParameters(), is(notNullValue()));
                assertThat("Unmarshalled  request : Parameters ,  must match ",reqUnmarshalled.getParameters().size(), is(equalTo(0)));
                assertThat("Unmarshalled  request : CommandDefinition Name ,  must match ",reqUnmarshalled.getCommandDefinition().getName(), is(equalTo(EXECUTE_R_SCRIPT)));
                assertThat("Unmarshalled  request : CommandDefinition Param Size ,  must match ",reqUnmarshalled.getCommandDefinition().getParameters().size(), is(equalTo(3)));
                
        }

	private ExecutionRequest prepareExecutionRequest(
			CommandDefinition commandDefinition,
			Map<String, Parameter> parameters) {
		ExecutionRequest startRequest = new ExecutionRequest();
		startRequest.setCommandDefinition(commandDefinition);
		startRequest.setParameters(parameters);
		startRequest.setRequestId(REQUEST_ID);
		return startRequest;
	}

	private Map<String, Parameter> prepareParametersForRequest() {
		Map<String, Parameter> parameters = new HashMap<String, Parameter>();
		parameters.put(DATASET_KEY, new Parameter(DATASET_KEY, "data.csv", "SAMPLE_PARAM1_VALUE", ParameterType.File));
		parameters.put("SAMPLE2", new Parameter("SAMPLE_PARAM2", "data.csv", "SAMPLE_PARAM2_VALUE", ParameterType.File));
		return parameters;
	}

	private List<ParameterDefinition> prepareParameterDefnitions() {
		List<ParameterDefinition> parameterDefinitions = new ArrayList<ParameterDefinition>();
		ParameterDefinition e = new ParameterDefinition(ParameterType.File, ParameterDirection.IN, DATASET_KEY);
		parameterDefinitions.add(e);
		parameterDefinitions.add(new ParameterDefinition(ParameterType.File, ParameterDirection.IN, SCRIPT_KEY));
		parameterDefinitions.add(new ParameterDefinition(ParameterType.File, ParameterDirection.OUT, RESULT_KEY));
		return parameterDefinitions;
	}


}

