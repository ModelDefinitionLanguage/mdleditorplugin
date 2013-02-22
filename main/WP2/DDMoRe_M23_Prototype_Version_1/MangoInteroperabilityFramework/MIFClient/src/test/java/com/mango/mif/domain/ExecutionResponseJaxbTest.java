/* File			:  ExecutionResponseJaxbTest.java
 * Project		:  MangoInteroperabilityFramework
 * Created on	:  Apr 5, 2012
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
	private static final String REQ_ID = UUID.randomUUID().toString();
	


	/**
	 * Test jaxb for execution request.
	 *
	 * @throws JAXBException the jAXB exception
	 */
	@Test
	public void testJaxbForExecutionRequest() throws JAXBException {

	        List<String> test = new ArrayList<String>();
	        test.add("file1");
	        test.add("file2");
	        test.add("file3");
	        
		   ExecutionResponse executionResponse = new ExecutionResponse();
	       executionResponse.setRequestId(REQ_ID);
	       executionResponse.setStatus(REQ_STATUS);
	       executionResponse.setJobFiles(test);
	       
	       String marshallRequest = JAXBUtils.marshall(executionResponse, ExecutionJaxbUtils.CONTEXT_CLASSES);
	       
	       ExecutionResponse reqUnmarshalled = (ExecutionResponse)JAXBUtils.unmarshall(marshallRequest, ExecutionJaxbUtils.CONTEXT_CLASSES);
	       
	       LOG.info("THE REQUEST IS NOW" + marshallRequest);
	       
	        assertThat("Unmarshalled response must not be null ",reqUnmarshalled, is(notNullValue()));
			assertThat("Unmarshalled response ID must match ",reqUnmarshalled.getRequestId(), is(equalTo(REQ_ID)));
			assertThat("Unmarshalled  response : Status ,  must not be null ",reqUnmarshalled.getStatus(), is(notNullValue()));
			assertThat("Unmarshalled  response : Status ,  must match ",reqUnmarshalled.getStatus(), is(equalTo(REQ_STATUS)));
			assertThat("Unmarshalled  response : Files Size ,  must match ",reqUnmarshalled.getJobFiles().size(), is(equalTo(3)));
	}
	
}
