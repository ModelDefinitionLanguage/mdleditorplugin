package com.mango.mif.rconnectorcli;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertTrue;
import static org.mockito.Mockito.spy;
import static org.mockito.Mockito.verify;

import java.io.File;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.UUID;
import java.util.concurrent.CountDownLatch;

import javax.annotation.Resource;
import javax.jms.Message;
import javax.jms.MessageListener;
import javax.xml.bind.JAXBException;

import org.apache.commons.scxml.SCXMLListener;
import org.apache.commons.scxml.model.Transition;
import org.apache.commons.scxml.model.TransitionTarget;
import org.apache.log4j.Logger;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.test.annotation.DirtiesContext;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import com.google.common.collect.Lists;
import com.google.common.collect.Sets;
import com.google.common.io.Files;
import com.mango.mif.connector.TestsHelper;
import com.mango.mif.connector.dispatcher.domain.Job;
import com.mango.mif.connector.runner.impl.DriverProcessingStatus;
import com.mango.mif.connector.runner.impl.SCXMLDriverImpl;
import com.mango.mif.core.api.MIFResource;
import com.mango.mif.core.api.ResourceComponent;
import com.mango.mif.core.api.ResourceComponentException;
import com.mango.mif.core.impl.DefaultResourceComponent;
import com.mango.mif.core.impl.DefaultWorkspaceManager;
import com.mango.mif.domain.ExecutionRequestBuilder;
import com.mango.mif.domain.Parameter;
import com.mango.mif.domain.ParameterDefinition;
import com.mango.mif.domain.ParameterDirection;
import com.mango.mif.domain.ParameterType;
import com.mango.mif.rconnectorcli.internal.RScriptConfig;
import com.mango.mif.rconnectorcli.internal.RScriptExec;
import com.mango.mif.rconnectorcli.runner.impl.RCliJobRunner;
import com.mango.mif.rconnectorcli.runner.impl.RCliJobRunnerFactory;


/**
 * R Cli job runner Configuration test
 * 
 */
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = {
		"/spring/rconnectorcliTest/Connector-context.xml",
        "/com/mango/mif/rconnectorcli/StateHandlers-context.xml",
        "/com/mango/mif/rconnectorcli/RCliConnectorIntegrationTest-context.xml",
		"/spring/JmsTest-Config.xml"})
public class RCliConnectorIntegrationTest implements MessageListener {
    /**
     * Logger
     */
	final static Logger LOG = Logger.getLogger(RCliConnectorIntegrationTest.class);
	
	@Resource(name="rCliJobRunnerFactory")
	RCliJobRunnerFactory jobRunnerFactory;

    @Resource(name="rScriptConfig")
	RScriptConfig rScriptConfig;

    @Resource(name="testResourceComponent")
    ResourceComponent resourceComponent;

    /**
     * Directory in which resources are kept
     */
    final static File resourcesDirectory = createWorkingDirectory();
    /**
     * Working directory of connector.
     */
    final static File connectorWorkingDirectory  = createWorkingDirectory();
    
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
     * Used to synchronise test thread and spring TaskExecutors
     */
    private CountDownLatch eventSignal = new CountDownLatch(1);
    /**
     * Tests configuration
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
     * Tests positive execution of R Cli runner using junit test's thread
     * @throws Exception 
     */
    @DirtiesContext
    @Test
    public void testExecuteRCliRunner() throws Exception {
        ((DefaultResourceComponent)resourceComponent).setWorkspaceManager(new DefaultWorkspaceManager(TestsHelper.createTmpDirectory()));
        Job job = createJob();
        rScriptConfig.setExecutable(TestsHelper.getRExecutable());
        
        String executionRequest = prepareExecutionRequest();
        job.setExecutionRequestMsg(executionRequest);

        RCliJobRunner jobRunner = (RCliJobRunner)jobRunnerFactory.createJobRunner(job);
        jobRunner.setWorkspaceManager(new DefaultWorkspaceManager(connectorWorkingDirectory));

        File wd = createWorkingDirectory();
        jobRunner.getExec().setWorkingDirectory(wd);
        
        RScriptExec exec = spy(jobRunner.getExec());
        jobRunner.setExec(exec);
        
        SCXMLDriverImpl driver = (SCXMLDriverImpl)jobRunner.getDriver();
        
        registerNofifierOnState(driver, Sets.newHashSet("finished","failed"));

        driver.fireEvent("runner.start");

        //thread that runs the test is notified when finished state is reached
        eventSignal.await();

        //Must ensure that the finished state handler has been invoked, if it is not invoked in 3 sec it is considered as failure
        Thread.sleep(3000);

        if(driver.getProcessingStatus().equals(DriverProcessingStatus.FAILED) && driver.getException() != null) throw driver.getException();
        
        verify(exec).submit();
        verify(exec).waitFor();
        
        assertNotNull(jobRunner.getResults());
        assertNotNull(jobRunner.getResults().get(RESULT_KEY));
        assertNotNull(resourceComponent.getResource(jobRunner.getResults().get(RESULT_KEY).getResourceUuid()).getContent());
    }
    /**
     * 
     * Test r cli job runner call method, the result message placed by the job runner is not expected to be consumed
     * 
     * @throws Exception 
     */
    @DirtiesContext
    @Test
    public void testRCliRunnerCall() throws Exception {
        ((DefaultResourceComponent)resourceComponent).setWorkspaceManager(new DefaultWorkspaceManager(TestsHelper.createTmpDirectory()));

        Job job = createJob();
        rScriptConfig.setExecutable(TestsHelper.getRExecutable());
        
        String executionRequest = prepareExecutionRequest();
        job.setExecutionRequestMsg(executionRequest);
        
        RCliJobRunner jobRunner = (RCliJobRunner)jobRunnerFactory.createJobRunner(job);
        jobRunner.setWorkspaceManager(new DefaultWorkspaceManager(connectorWorkingDirectory));
        
        File wd = createWorkingDirectory();
        jobRunner.getExec().setWorkingDirectory(wd);
        
        RScriptExec exec = spy(jobRunner.getExec());
        jobRunner.setExec(exec);
        
        SCXMLDriverImpl driver = (SCXMLDriverImpl)jobRunner.getDriver();
        
        registerNofifierOnState(driver, Sets.newHashSet("finished","failed"));
        
        jobRunner.call();
        
        verify(exec).submit();
        verify(exec).waitFor();
        
        if(driver.getProcessingStatus().equals(DriverProcessingStatus.FAILED)) throw driver.getException();
        
        assertNotNull(jobRunner.getResults());
        assertNotNull(jobRunner.getResults().get(RESULT_KEY));
        assertNotNull(resourceComponent.getResource(jobRunner.getResults().get(RESULT_KEY).getResourceUuid()).getContent());
    }
    /**
     * Creates working directory
     * @return
     */
    private static File createWorkingDirectory() {
        File dir = Files.createTempDir();
        dir.deleteOnExit();
        return dir;
    }
    /**
     * Registers a state machine listener that on given state notifies a thread that waits on semaphore
     * @param stateId
     */
    private void registerNofifierOnState(SCXMLDriverImpl driver, final Set<String> states) {
        driver.getEngine().addListener(driver.getEngine().getStateMachine(), new SCXMLListener() {

            @Override
            public void onEntry(TransitionTarget state) {
                LOG.debug("onEntry " + state.getId());
                if(states.contains(state.getId())) {
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
    /**
     * Creates a job with start task request
     * @return 
     * @throws JAXBException 
     */
    private Job createJob() {
        Job result = new Job();
        result.setJobId("TEST_JOB_ID");
        File jobWorkingDir = new File(connectorWorkingDirectory,"TEST_JOB_ID");
        jobWorkingDir.mkdir();
        result.setWorkingDirectory(jobWorkingDir.getName());
        return result;
    }

    /**
     * Prepares execution request and its inputs
     * @return
     * @throws ResourceComponentException 
     * @throws JAXBException 
     */
    private String prepareExecutionRequest() throws ResourceComponentException, JAXBException {
        String requestID = resourceComponent.createRequestDirectory();
        MIFResource rScript = resourceComponent.addRequestResource(this.getClass().getResource("test.R"),requestID, "test.R");
        MIFResource dataset = resourceComponent.addRequestResource(this.getClass().getResource("data.csv"),requestID, "data.csv");
        Map<String, Parameter> parameters = new HashMap<String, Parameter>();
        parameters.put(DATASET_KEY, new Parameter(DATASET_KEY, "data.csv", dataset.getUuid().toString(), ParameterType.File));
        parameters.put(SCRIPT_KEY, new Parameter(SCRIPT_KEY, "test.R", rScript.getUuid().toString(), ParameterType.File));
        
        ExecutionRequestBuilder builder = new ExecutionRequestBuilder();
        builder.setCommandName("executeRScript").setConnectorID("RCli")
                .setRequestId(UUID.fromString(requestID))
                .setCommandParameterDefinitions(createParameterDefinitions())
                .setInputParameters(parameters);
        return builder.getExecutionRequestMsg();
    }
    /**
     * Creates a command definition that describes a task of executing a script against a dataset and returning a dataset.
     * @return
     */
    private List<ParameterDefinition> createParameterDefinitions() {
        List<ParameterDefinition> parameterDefinitions = Lists.newArrayList();
        parameterDefinitions.add(new ParameterDefinition(ParameterType.File, ParameterDirection.IN, DATASET_KEY));
        parameterDefinitions.add(new ParameterDefinition(ParameterType.File, ParameterDirection.IN, SCRIPT_KEY));
        parameterDefinitions.add(new ParameterDefinition(ParameterType.File, ParameterDirection.OUT, RESULT_KEY));
        
        return parameterDefinitions;
    }
    
    @Override
    public void onMessage(Message arg0) {
        LOG.debug(arg0);
    }
    
}
