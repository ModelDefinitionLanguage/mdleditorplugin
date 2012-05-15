package com.mango.mif.rconnector;

import static org.junit.Assert.*;

import java.util.UUID;

import javax.annotation.Resource;

import org.apache.log4j.Logger;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.test.annotation.DirtiesContext;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import com.mango.mif.connector.TestsHelper;
import com.mango.mif.rconnector.runner.impl.RJobRunnerFactory;


/**
 * Configuration test
 * 
 */
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = {
		"/spring/rconnector/Connector-context.xml",
		"/spring/JmsTest-Config.xml",
		"/com/mango/mif/rconnector/RConnectorSpringConfigIntegrationTest-context.xml"})
public class RConnectorSpringConfigIntegrationTest {
	final static Logger LOG = Logger.getLogger(RConnectorSpringConfigIntegrationTest.class);
	
	@Resource(name="rJobRunnerFactory")
	RJobRunnerFactory jobRunnerFactory;
	
	
	@DirtiesContext
	@Test
	public void testConfiguration() {
	    assertNotNull(jobRunnerFactory.createJobRunner(TestsHelper.createJob("JOB_ID", UUID.randomUUID())));
	}

}
