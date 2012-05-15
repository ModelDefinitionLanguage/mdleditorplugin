package com.mango.mif.rconnectorcli;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertTrue;
import static org.mockito.Mockito.spy;
import static org.mockito.Mockito.verify;

import java.io.File;
import java.io.IOException;
import java.util.UUID;
import java.util.concurrent.CountDownLatch;

import javax.annotation.Resource;

import org.apache.commons.io.FileUtils;
import org.apache.commons.scxml.SCXMLListener;
import org.apache.commons.scxml.model.Transition;
import org.apache.commons.scxml.model.TransitionTarget;
import org.apache.log4j.Logger;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.test.annotation.DirtiesContext;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import com.google.common.io.Files;
import com.mango.mif.connector.TestsHelper;
import com.mango.mif.connector.runner.impl.SCXMLDriverImpl;
import com.mango.mif.rconnectorcli.internal.RScriptConfig;
import com.mango.mif.rconnectorcli.internal.RScriptExec;
import com.mango.mif.rconnectorcli.runner.impl.RCliJobRunner;
import com.mango.mif.rconnectorcli.runner.impl.RCliJobRunnerFactory;


/**
 * R cli job runner test 
 * 
 */
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = {
        "/com/mango/mif/rconnectorcli/RCliJobRunnerTest-context.xml",
		"/spring/rconnectorcliTest/Connector-context.xml",
        "/com/mango/mif/rconnectorcli/StateHandlers-noResourceComponent-context.xml",
		"/spring/JmsTest-Config.xml"})
public class RCliJobRunnerTest {
	final static Logger LOG = Logger.getLogger(RCliJobRunnerTest.class);
	
	@Resource(name="rCliJobRunnerFactory")
	RCliJobRunnerFactory jobRunnerFactory;

    @Resource(name="rScriptConfig")
	RScriptConfig rScriptConfig;

    /**
     * Used to synchronise test thread and spring TaskExecutors
     */
    private CountDownLatch eventSignal = new CountDownLatch(1);
    
    /**
     * Test configuration
     */
	@DirtiesContext
	@Test
	public void testConfiguration() {
	    assertNotNull(jobRunnerFactory.createJobRunner(TestsHelper.createJob("JOB_ID", UUID.randomUUID())));
	    assertNotNull("R executable not set.",rScriptConfig.getExecutable());
        assertTrue("R executable not set.",!rScriptConfig.getExecutable().getName().equals(""));
        LOG.info("R Location: " + rScriptConfig.getExecutable());

        assertNotNull(rScriptConfig.getAttributes());
        assertEquals(1,rScriptConfig.getAttributes().size());
        

        assertTrue(rScriptConfig.getAttributes().contains("--vanilla"));
	}
    /**
     * Check whether R cli job runner handlers invoke RScriptExec methods
     * @throws Exception 
     */
    @DirtiesContext
    @Test
    public void testExecuteRCliJobRunnerDriver() throws Exception {
        RCliJobRunner jobRunner = (RCliJobRunner)jobRunnerFactory.createJobRunner(TestsHelper.createJob("R-job",UUID.randomUUID()));
        rScriptConfig.setExecutable(TestsHelper.getRExecutable());
        File wd = createWorkingDirectory();
        prepareRInputs(wd);
        
        jobRunner.getExec().setWorkingDirectory(wd);
        jobRunner.getExec().setScriptFile(new File("test.R"));
        
        RScriptExec exec = spy(jobRunner.getExec());
        jobRunner.setExec(exec);
        
        SCXMLDriverImpl driver = (SCXMLDriverImpl)jobRunner.getDriver();
        
        registerNofifierOnState(driver, "finished");

        driver.fireEvent("runner.start");

        //thread that runs the test is notified when finished state is reached
        eventSignal.await();

        //Must ensure that the finished state handler has been invoked, if it is not invoked in 3 sec it is considered as failure
        Thread.sleep(3000);
        
        verify(exec).submit();
        verify(exec).waitFor();

    }

    /**
     * Creates working directory
     * @return
     */
    private File createWorkingDirectory() {
        File dir = Files.createTempDir();
        dir.deleteOnExit();
        return dir;
    }
    /**
     * Prepares R inputs
     * @param workingDirectory
     * @throws IOException
     */
    private void prepareRInputs(File workingDirectory) throws IOException {
        FileUtils.copyURLToFile(this.getClass().getResource("test.R"), new File(workingDirectory,"test.R"));
        FileUtils.copyURLToFile(this.getClass().getResource("data.csv"), new File(workingDirectory,"data.csv"));

    }

    /**
     * Registers a state machine listener that on given state notifies a thread that waits on semaphore
     * @param stateId
     */
    private void registerNofifierOnState(SCXMLDriverImpl driver, final String stateId) {
        driver.getEngine().addListener(driver.getEngine().getStateMachine(), new SCXMLListener() {

            @Override
            public void onEntry(TransitionTarget state) {
                LOG.debug("onEntry " + state.getId());
                if(state.getId().equals(stateId)) {
                    eventSignal.countDown();
                }
            }

            @Override
            public void onExit(TransitionTarget state) {
            }

            @Override
            public void onTransition(TransitionTarget from, TransitionTarget to, Transition transition) {
            }
            
        });
    }
}
