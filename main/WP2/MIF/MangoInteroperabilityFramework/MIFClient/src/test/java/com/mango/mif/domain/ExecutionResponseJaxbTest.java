/* File			:  ExecutionResponseJaxbTest.java
 * Project		:  MangoInteroperabilityFramework
 * Created on	:  Apr 5, 2012
 */
package com.mango.mif.domain;

import static org.hamcrest.CoreMatchers.equalTo;
import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.CoreMatchers.notNullValue;
import static org.junit.Assert.assertThat;

import java.util.HashMap;
import java.util.Map;
import java.util.UUID;

import javax.xml.bind.JAXBException;

import org.apache.log4j.Logger;
import org.junit.Test;

import com.mango.mif.utils.JAXBUtils;
import com.mango.mif.utils.jaxb.ExecutionJaxbUtils;

/**
 * Test for {@link ExecutionResponse}.
 * Jaxb test for ExecutionResponse.
 * @version $Revision: $ as of $Date: $
 * <p>SVN Entry : $HeadURL: $
 * <p>SVN ID    : $Id: $
 * <p>Last edited by : $Author: $
 */
public class ExecutionResponseJaxbTest {
	final static Logger LOG = Logger.getLogger(ExecutionResponseJaxbTest.class);
	
	private static final String REQ_STATUS = "SUCCESS";
	private static final UUID REQ_ID = UUID.randomUUID();
	


	/**
	 * Test jaxb for execution request.
	 *
	 * @throws JAXBException the jAXB exception
	 */
	@Test
	public void testJaxbForExecutionRequest() throws JAXBException {
		 Map<String, Parameter> parameters = new HashMap<String, Parameter>();
	        String samleKey = "SAMPLE_PARAM1";
			parameters.put("RESULT1", new Parameter(samleKey, "data.csv", "SAMPLE_PARAM1_VALUE", ParameterType.File));
	        parameters.put("SAMPLE2", new Parameter("SAMPLE_PARAM2", "output.jpg", "SAMPLE_PARAM2_VALUE", ParameterType.File));

		   ExecutionResponse executionResponse = new ExecutionResponse();
	       executionResponse.setRequestId(REQ_ID);
	       executionResponse.setParameters(parameters);
	       executionResponse.setStatus(REQ_STATUS);
	       
	       String marshallRequest = JAXBUtils.marshall(executionResponse, ExecutionJaxbUtils.EXECUTION_RESPONSE_CLASS_ARRAY);
	       
	       ExecutionResponse reqUnmarshalled = (ExecutionResponse)JAXBUtils.unmarshall(marshallRequest, ExecutionJaxbUtils.EXECUTION_RESPONSE_CLASS_ARRAY);
	       
	       LOG.info("THE REQUEST IS NOW" + marshallRequest);
	       
	        assertThat("Unmarshalled response must not be null ",reqUnmarshalled, is(notNullValue()));
			assertThat("Unmarshalled response ID must match ",reqUnmarshalled.getRequestId(), is(equalTo(REQ_ID)));
			assertThat("Unmarshalled  response : Status ,  must not be null ",reqUnmarshalled.getStatus(), is(notNullValue()));
			assertThat("Unmarshalled  response : Status ,  must match ",reqUnmarshalled.getStatus(), is(equalTo(REQ_STATUS)));
			assertThat("Unmarshalled  response : Parameters ,  must not be null ",reqUnmarshalled.getParameters(), is(notNullValue()));
			assertThat("Unmarshalled  response : Parameters Size ,  must match ",reqUnmarshalled.getParameters().size(), is(equalTo(2)));
	}
	
}
