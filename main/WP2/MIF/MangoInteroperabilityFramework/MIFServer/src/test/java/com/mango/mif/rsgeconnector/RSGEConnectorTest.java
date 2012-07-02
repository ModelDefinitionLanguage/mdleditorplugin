package com.mango.mif.rsgeconnector;

import static org.junit.Assert.*;

import java.util.List;
import java.util.UUID;

import javax.annotation.Resource;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.test.annotation.DirtiesContext;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import com.mango.mif.connector.TestsHelper;
import com.mango.mif.rsgeconnector.runner.impl.RSGEJobRunnerFactory;
import com.mango.mif.rsgeconnector.runner.impl.RSGEStateHandler;
/**
 * Test checking spring configuration of the RSGE connector
 * @author mrogalski
 *
 */
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = {
		"/spring/rsgeconnectorTest/Connector-context.xml",
        "/com/mango/mif/rsgeconnector/StateHandlers-context.xml",
		"/spring/JmsTest-Config.xml"})
public class RSGEConnectorTest {

	@Resource(name="rSGEJobRunnerFactory")
	RSGEJobRunnerFactory jobRunnerFactory;
    
    @Resource(name="rSGEStateHandlers")
    List<RSGEStateHandler> rSGEStateHandlers;
    /**
     * Test configuration
     */
	@DirtiesContext
	@Test
	public void testConfiguration() {
	    assertNotNull(jobRunnerFactory.createJobRunner(TestsHelper.createJob("JOB_ID", UUID.randomUUID())));
	}
 
	@Test
    @DirtiesContext
	public void shouldFailIfInvalidDefinitionOfStateHandlers() {
		assertEquals(12,rSGEStateHandlers.size());
	}

}
