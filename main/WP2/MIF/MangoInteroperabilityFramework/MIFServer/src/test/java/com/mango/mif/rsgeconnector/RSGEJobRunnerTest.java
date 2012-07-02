package com.mango.mif.rsgeconnector;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.fail;

import java.io.File;
import java.io.IOException;
import java.util.HashSet;
import java.util.Set;
import java.util.concurrent.CountDownLatch;

import javax.annotation.Resource;
import javax.xml.bind.JAXBException;

import org.apache.commons.io.FileUtils;
import org.apache.commons.scxml.SCXMLListener;
import org.apache.commons.scxml.model.Transition;
import org.apache.commons.scxml.model.TransitionTarget;
import org.apache.log4j.Logger;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.test.annotation.DirtiesContext;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import com.google.common.base.Preconditions;
import com.mango.mif.connector.TestsHelper;
import com.mango.mif.connector.dispatcher.domain.Job;
import com.mango.mif.connector.runner.impl.DriverProcessingStatus;
import com.mango.mif.connector.runner.impl.JobRunnerSates;
import com.mango.mif.connector.runner.impl.SCXMLDriverImpl;
import com.mango.mif.core.api.SharedLocationManager;
import com.mango.mif.core.impl.DefaultWorkspaceManager;
import com.mango.mif.core.resource.UserSharedLocationManager;
import com.mango.mif.domain.ExecutionRequestBuilder;
import com.mango.mif.exception.MIFException;
import com.mango.mif.rsgeconnector.runner.impl.RSGEJobRunner;
import com.mango.mif.rsgeconnector.runner.impl.RSGEJobRunnerFactory;
import com.mango.mif.utils.NewExecutionRequestHelper;
import com.mango.mif.utils.TestProperties;
import com.mango.mif.utils.encrypt.EncrypterFactory;


/**
 * R SGE job runner Integration Test
 * 
 */
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = {
        "/com/mango/mif/rsgeconnector/RSGEJobRunnerTest-context.xml",
        "/spring/rsgeconnectorTest/Connector-context.xml",
        "/com/mango/mif/rsgeconnector/StateHandlers-context.xml",
"/spring/JmsTest-Config.xml"})
public class RSGEJobRunnerTest {
    final static Logger LOG = Logger.getLogger(RSGEJobRunnerTest.class);

    @Resource(name="rSGEJobRunnerFactory")
    RSGEJobRunnerFactory jobRunnerFactory;

    @Resource(name = "sharedLocationManager")
    private SharedLocationManager sharedLocationManager;

    /**
     * Used to synchronise test thread and spring TaskExecutors
     */
    private CountDownLatch eventSignal = new CountDownLatch(1);

    private String	userName;

    /**
     * encrypted user password
     */
    private String	userPassword;

    /**
     * Working directory of connector.
     */
    private File connectorWorkingDirectory;
    /**
     * Directory in which resources are kept
     */
    private File resourcesDirectory;

    @Before
    public void setUp() {

        userName = System.getProperty(TestProperties.MIF_CLIENT_USER_NAME);
        userPassword = System.getProperty(TestProperties.MIF_CLIENT_PASSWORD);
        Preconditions.checkNotNull(userName, "The "+TestProperties.MIF_CLIENT_USER_NAME+" property is not set");
        Preconditions.checkNotNull(userPassword, "The "+TestProperties.MIF_CLIENT_PASSWORD+" property is not set");


        connectorWorkingDirectory  = TestsHelper.createTmpDirectory();
        resourcesDirectory = TestsHelper.createTmpDirectory();
        ((UserSharedLocationManager)sharedLocationManager).setWorkspaceManager(new DefaultWorkspaceManager(resourcesDirectory));
    }

    @After
    public void tearDown() throws IOException {
        try {
            FileUtils.deleteDirectory(connectorWorkingDirectory);
        } catch (Exception ignored) {
        }
        try {
            FileUtils.deleteDirectory(resourcesDirectory);
        } catch (Exception ignored) {
        }
    }
    /**
     * Check whether R SGE job runner handles the request
     * @throws Exception
     */
    @DirtiesContext
    @Test
    public void shouldExecuteSGEAndComplete() throws Exception {
        RSGEJobRunner jobRunner = prepareJobRunner();

        SCXMLDriverImpl driver = (SCXMLDriverImpl)jobRunner.getDriver();

        Set<String> states = new HashSet<String>();
        states.add(JobRunnerSates.FINISHED.getStateName());
        states.add(JobRunnerSates.FAILED.getStateName());
        registerNofifierOnState(driver, states, eventSignal);
        driver.fireEvent(JobRunnerSates.RUNNING.getTriggeringEvent());

        eventSignal.await();

        while(!driver.isInFinalState()) {
            Thread.sleep(3000);
        }

        if(driver.getException()!=null) {
            LOG.error(driver.getException());
            fail();
        }
    }
    @DirtiesContext
    @Test(timeout = 120000)
    public void shouldCancelALongRunningTask() throws InterruptedException, JAXBException, IOException, MIFException {

        Job job = createJob();
        File testDataDir = FileUtils.toFile(RSGEExecutionIntegrationTest.class.getResource("/testData/longRunningRScript"));
        ExecutionRequestBuilder executionRequestBuilder = NewExecutionRequestHelper.createRExecutionRequestBuilderForInputDirectory(sharedLocationManager,testDataDir);
        executionRequestBuilder.setUserName(userName);
        executionRequestBuilder.setUserPassword(userPassword);
        job.setExecutionRequestMsg(executionRequestBuilder.getExecutionRequestMsg());
        RSGEJobRunner jobRunner = (RSGEJobRunner) jobRunnerFactory.createJobRunner(job);
        jobRunner.setWorkspaceManager(new DefaultWorkspaceManager(connectorWorkingDirectory));


        eventSignal = new CountDownLatch(1);

        SCXMLDriverImpl driver = (SCXMLDriverImpl) jobRunner.getDriver();
        Set<String> states = new HashSet<String>();
        states.add(JobRunnerSates.FINISHED.getStateName());
        states.add(JobRunnerSates.FAILED.getStateName());
        states.add("task-processing");
        registerNofifierOnState(driver, states, eventSignal);

        driver.fireEvent(JobRunnerSates.RUNNING.getTriggeringEvent());

        eventSignal.await();

        Thread.sleep(5000);

        eventSignal = new CountDownLatch(1);
        states = new HashSet<String>();
        states.add(JobRunnerSates.FINISHED.getStateName());
        states.add(JobRunnerSates.FAILED.getStateName());
        states.add(JobRunnerSates.CANCELLED.getStateName());
        registerNofifierOnState(driver, states, eventSignal);

        jobRunner.cancelProcessing();

        eventSignal.await();

        assertEquals(DriverProcessingStatus.CANCELLED, driver.getProcessingStatus());
    }


    /**
     * Prepares a R execution request and invokes SGE job runner for that request
     * @return
     * @throws MIFException
     * @throws JAXBException
     */
    private RSGEJobRunner prepareJobRunner() throws MIFException, JAXBException {
        ExecutionRequestBuilder executionRequestBuilder = NewExecutionRequestHelper.createBasicRExecutionRequestBuilder(sharedLocationManager);
        executionRequestBuilder.setUserName(userName);
        executionRequestBuilder.setUserPassword(userPassword);
        Job job = createJob();
        job.setExecutionRequestMsg(executionRequestBuilder.getExecutionRequestMsg());
        RSGEJobRunner jobRunner = (RSGEJobRunner)jobRunnerFactory.createJobRunner(job);
        jobRunner.setWorkspaceManager(new DefaultWorkspaceManager(connectorWorkingDirectory));
        jobRunner.setEncrypter(EncrypterFactory.getEncrypter());

        return jobRunner;
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
     * Registers a state machine listener that on given state notifies a thread that waits on semaphore
     * @param eventSignal
     * @param stateId
     */
    private void registerNofifierOnState(SCXMLDriverImpl driver, final Set<String> statesId, final CountDownLatch eventSignal) {
        driver.getEngine().addListener(driver.getEngine().getStateMachine(), new SCXMLListener() {

            @Override
            public void onEntry(TransitionTarget state) {
                LOG.debug("onEntry " + state.getId());
                if(statesId.contains(state.getId())) {
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
