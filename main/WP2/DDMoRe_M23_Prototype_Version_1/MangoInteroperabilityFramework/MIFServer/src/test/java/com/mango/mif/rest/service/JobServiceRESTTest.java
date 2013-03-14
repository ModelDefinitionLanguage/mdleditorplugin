/* File			:  JobServiceRESTTest.java
 * Project		:  MIFServer
 * Created on	:  10 Jul 2012
 */
package com.mango.mif.rest.service;

import org.junit.After;
import org.junit.Before;
import org.junit.Ignore;
import org.junit.Test;

import com.mango.mif.client.api.rest.JobService;
import com.mango.mif.client.api.rest.JobServiceExceptionMapper;
import com.mango.mif.client.api.rest.JobServiceResponseExceptionMapper;
import com.mango.mif.rest.service.JaxRSServiceEndpointHarness;
import com.mango.mif.rest.service.MockCxfJobService;

/**
 * @version $Revision: $ as of $Date: $
 * <p>SVN Entry : $HeadURL: $
 * <p>SVN ID    : $Id: $
 * <p>Last edited by : $Author: $
 */
// FIXME:  Commented out test for the time being as System properties not getting picked up during test run.  Possibly
// an issue with the pom.
@Ignore
public class JobServiceRESTTest extends JobServiceTest{

		private JaxRSServiceEndpointHarness endpoint = new JaxRSServiceEndpointHarness();

		@Override
		@Test
		public void shouldReturnFileLinks() {
			super.shouldReturnFileLinks();
		}

		@Override
		@After
		public void tearDown() {
			if (endpoint != null) {
				endpoint.tearDown();
			}
		}

		@Override
		@Before	
		public void setUp(){
			jobServiceImpl = endpoint.getProxy(JobService.class, new MockCxfJobService(), new JobServiceExceptionMapper(), new JobServiceResponseExceptionMapper());
			setJobService(jobServiceImpl);
			
			super.setUp();
		}

}
