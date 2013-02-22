/* File			:  RequestQueueResolverTest.java
 * Project		:  MIFServer
 * Created on	:  Jul 10, 2012
 */
package com.mango.mif.core.impl;

import static org.junit.Assert.*;

import java.util.UUID;

import javax.xml.bind.JAXBException;

import org.apache.commons.lang.StringUtils;
import org.junit.Before;
import org.junit.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import static org.mockito.Mockito.*;

import com.mango.mif.connector.Connector;
import com.mango.mif.connector.TestsHelper;
import com.mango.mif.connector.domain.Job;
import com.mango.mif.core.impl.ConnectorsRegistry;
import com.mango.mif.core.impl.RequestQueueResolver;
import com.mango.mif.utils.JAXBUtils;
import com.mango.mif.utils.jaxb.ExecutionJaxbUtils;

/**
 * @version $Revision: $ as of $Date: $
 * <p>SVN Entry : $HeadURL: $
 * <p>SVN ID    : $Id: $
 * <p>Last edited by : $Author: $
 * 
 * Tests Execution Request queue resolver
 */
public class RequestQueueResolverTest {
	/**
	 * Tested instance
	 */
	private RequestQueueResolver queueResolver;
	/**
	 * connectors registry
	 */
	@Mock ConnectorsRegistry connectorsRegistry;
	/**
	 * job
	 */
	Job job;
	/**
	 * connector
	 */
	@Mock Connector connector;
	/**
	 * jobID
	 */
	String jobID;
	
	@Before
	public void setUp() throws Exception {
		MockitoAnnotations.initMocks(this);
		queueResolver = new RequestQueueResolver();
		queueResolver.setConnectorsRegistry(connectorsRegistry);
		jobID = UUID.randomUUID().toString();
		job = TestsHelper.createJob(jobID.toString(),jobID );
		job.getExecutionRequest().setType("TEST-EXECUTION-TYPE");
		when(connectorsRegistry.getConnector("TEST-EXECUTION-TYPE")).thenReturn(connector);
		when(connector.getRequestQueue()).thenReturn("MOCK-QUEUE");
	}

	@Test
	public void shouldResolveToAConnectorSpecificQueue() throws JAXBException {
		String queue = queueResolver.resolve(JAXBUtils.marshall(job.getExecutionRequest(), ExecutionJaxbUtils.CONTEXT_CLASSES));
		assertEquals("MOCK-QUEUE",queue);
	}

	@Test
	public void shouldResolveToEmptyQueueNameIfExecutionTypeNotFound() throws JAXBException {
		job.getExecutionRequest().setType("TEST-EXECUTION-TYPE-DOES-NOT-EXIST");
		String queue = queueResolver.resolve(JAXBUtils.marshall(job.getExecutionRequest(), ExecutionJaxbUtils.CONTEXT_CLASSES));
		assertTrue(StringUtils.isEmpty(queue));
	}
}