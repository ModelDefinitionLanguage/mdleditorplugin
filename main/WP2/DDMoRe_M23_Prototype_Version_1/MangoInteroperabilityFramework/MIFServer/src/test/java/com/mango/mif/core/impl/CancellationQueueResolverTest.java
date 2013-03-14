/* File			:  CancellationQueueResolverTest.java
 * Project		:  MIFServer
 * Created on	:  Jul 10, 2012
 */
package com.mango.mif.core.impl;

import static org.junit.Assert.*;

import java.util.UUID;

import org.apache.commons.lang.StringUtils;
import org.junit.Before;
import org.junit.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import static org.mockito.Mockito.*;

import com.mango.mif.connector.Connector;
import com.mango.mif.connector.TestsHelper;
import com.mango.mif.connector.domain.Job;
import com.mango.mif.core.impl.CancellationQueueResolver;
import com.mango.mif.core.impl.ConnectorsRegistry;
import com.mango.mif.managers.JobManagementService;

/**
 * @version $Revision: $ as of $Date: $
 * <p>SVN Entry : $HeadURL: $
 * <p>SVN ID    : $Id: $
 * <p>Last edited by : $Author: $
 * 
 * Tests cancellation queue resolver
 */
public class CancellationQueueResolverTest {
	/**
	 * Tested instance
	 */
	private CancellationQueueResolver queueResolver;
	/**
	 * Job repository
	 */
	@Mock JobManagementService jobManagementService;
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
		queueResolver = new CancellationQueueResolver();
		queueResolver.setJobManagementService(jobManagementService);
		queueResolver.setConnectorsRegistry(connectorsRegistry);
		jobID = UUID.randomUUID().toString();
		job = TestsHelper.createJob(jobID.toString(),jobID );
		job.getExecutionRequest().setType("TEST-EXECUTION-TYPE");
		when(jobManagementService.getJob(jobID.toString())).thenReturn(job);
		when(connectorsRegistry.getConnector("TEST-EXECUTION-TYPE")).thenReturn(connector);
		when(connector.getCancellationQueue()).thenReturn("MOCK-CANCELLATION-QUEUE");
		
	}

	@Test
	public void shouldResolveToAConnectorSpecificCancellationQueue() {
		String queue = queueResolver.resolve(jobID.toString());
		assertNotNull(queue);
		assertTrue(!StringUtils.isEmpty(queue));
	}

	@Test
	public void shouldResolveToEmptyQueueNameIfJobWithTheGivenIdNotFound() {
		String queue = queueResolver.resolve("NOT-EXISTING-JOB-ID");
		assertNotNull(queue);
		assertTrue(StringUtils.isEmpty(queue));
	}
}