package com.mango.mif.connector.runner;

import static org.junit.Assert.assertNotNull;

import java.util.UUID;

import javax.annotation.Resource;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.test.annotation.DirtiesContext;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import com.mango.mif.connector.TestsHelper;
import com.mango.mif.connector.runner.api.JobRunner;
import com.mango.mif.connector.runner.api.JobRunnerFactory;

/**
 * Basic Job Runner Factory unit test
 */
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = {
		"/spring/JobRunner-context.xml"})
public class JobRunnerFactoryTest {
    
    /**
     * Job runner factory producing spring-managed job runners
     */
    @Resource(name="jobRunnerFactory")
	private JobRunnerFactory jobRunnerFactory;
    
	/**
	 * Tests if the JobRunnerFactory correctly instantiates JobRunner
	 */
    @DirtiesContext
    @Test
	public void testJobRunnerFactoryResult() {
        JobRunner jobRunner = jobRunnerFactory.createJobRunner(TestsHelper.createJob("JOB_1", UUID.randomUUID()));

        assertNotNull("Aquired job runner instance is null.",jobRunner);
        assertNotNull("Job Runner's job is null.",jobRunner.getJob());
	}


}
