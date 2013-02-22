package com.mango.mif.sgeconnector;

import static org.junit.Assert.assertEquals;

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
import org.junit.BeforeClass;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.test.annotation.DirtiesContext;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import com.google.common.base.Preconditions;
import com.mango.mif.connector.TestsHelper;
import com.mango.mif.connector.domain.Job;
import com.mango.mif.connector.runner.impl.DriverProcessingStatus;
import com.mango.mif.connector.runner.impl.GenericShellBasedJobRunner;
import com.mango.mif.connector.runner.impl.GenericShellBasedJobRunnerFactory;
import com.mango.mif.connector.runner.impl.JobRunnerState;
import com.mango.mif.connector.runner.impl.SCXMLDriverImpl;
import com.mango.mif.core.api.SharedLocationManager;
import com.mango.mif.core.exec.ExecutionException;
import com.mango.mif.core.exec.invoker.InvokerHelper;
import com.mango.mif.core.impl.DefaultWorkspaceManager;
import com.mango.mif.core.resource.UserSharedLocationManager;
import com.mango.mif.core.resource.fileutils.FileUtilsResourcePublisher;
import com.mango.mif.domain.ExecutionRequestBuilder;
import com.mango.mif.domain.ExecutionType;
import com.mango.mif.exception.MIFException;
import com.mango.mif.managers.JobManagementService;
import com.mango.mif.utils.MIFProperties;
import com.mango.mif.utils.ExecutionRequestHelper;
import com.mango.mif.utils.TestProperties;
import com.mango.mif.utils.encrypt.EncrypterFactory;


/**
 * R SGE job runner Integration Test
 * 
 */
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = {
        "/com/mango/mif/sgeconnector/SGEJobRunnerTest-context.xml",
        "classpath:sgeconnector-common-context.xml",
        "classpath:rsgeconnector-context.xml",
        "classpath:nonmemsgeconnector-context.xml",
        "classpath:cmd-sgeconnector-context.xml",
        "classpath:psnvpc-sgeconnector-context.xml",
        "classpath:psnscm-sgeconnector-context.xml",
        "classpath:psnbootstrap-sgeconnector-context.xml",
        "/spring/JmsTest-Config.xml"})
public class SGEJobRunnerTest {
    final static Logger LOG = Logger.getLogger(SGEJobRunnerTest.class);

    @Resource(name="RSgeJobRunnerFactory")
    GenericShellBasedJobRunnerFactory jobRunnerFactory;

    @Resource(name = "jobManagementService")
    private JobManagementService jobManagementService;

    @Resource(name = "sharedLocationManager")
    private SharedLocationManager sharedLocationManager;

    /**
     * Used to synchronise test thread and spring TaskExecutors
     */
    private CountDownLatch eventSignal = new CountDownLatch(1);

    /**
     * User name
     */
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
    /**
     * R command
     */
    private String rCommand;

    private String sgeSharedDirectory;
    @BeforeClass
    public static void initializeProperties() throws IOException {
        TestsHelper.initProperties(SGEJobRunnerTest.class,"/tests.properties");
    }
    
    @Before
    public void setUp() {
        userName = System.getProperty(TestProperties.MIF_CLIENT_USER_NAME);
        userPassword = System.getProperty(TestProperties.MIF_CLIENT_PASSWORD);
        rCommand = System.getProperty(TestProperties.MIF_R_EXECUTABLE);
        sgeSharedDirectory = System.getProperty(MIFProperties.GRID_SHARED_LOCATION);
        Preconditions.checkNotNull(rCommand, "The "+TestProperties.MIF_R_EXECUTABLE+" property is not set");
        Preconditions.checkNotNull(userName, "The "+TestProperties.MIF_CLIENT_USER_NAME+" property is not set");
        Preconditions.checkNotNull(userPassword, "The "+TestProperties.MIF_CLIENT_PASSWORD+" property is not set");
        Preconditions.checkNotNull(sgeSharedDirectory, "The "+MIFProperties.GRID_SHARED_LOCATION + " property is not set");

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
        GenericShellBasedJobRunner jobRunner = prepareJobRunner();

        SCXMLDriverImpl driver = (SCXMLDriverImpl)jobRunner.getDriver();

        Set<String> states = new HashSet<String>();
        states.add(JobRunnerState.FINISHED.getStateName());
        states.add(JobRunnerState.FAILED.getStateName());
        registerNofifierOnState(driver, states, eventSignal);
        driver.fireEvent(JobRunnerState.RUNNING.getTriggeringEvent());

        eventSignal.await();

        while(!driver.isInFinalState()) {
            Thread.sleep(3000);
        }

        if(driver.getException()!=null) {
            throw driver.getException();
        }
    }
    
    @DirtiesContext
    @Test(timeout = 120000)
    public void shouldCancelALongRunningTask() throws InterruptedException, JAXBException, IOException, MIFException {

        FileUtilsResourcePublisher publisher = new FileUtilsResourcePublisher();
        publisher.setSharedLocationManager(sharedLocationManager);
        Job job = createJob();
        File testDataDir = FileUtils.toFile(SGEJobRunnerTest.class.getResource("/testData/longRunningRScript"));
        ExecutionRequestBuilder executionRequestBuilder = ExecutionRequestHelper.createRExecutionRequestBuilderForInputDirectory(publisher,testDataDir);
        executionRequestBuilder.setUserName(userName);
        executionRequestBuilder.setCommand(rCommand).setUserPassword(userPassword).setExecutionType(ExecutionType.R_Script.name()).setSubmitAsUserMode(true);
        job.setExecutionRequestMsg(executionRequestBuilder.getExecutionRequestMsg());
        jobManagementService.saveJob(job);
        GenericShellBasedJobRunner jobRunner = (GenericShellBasedJobRunner) jobRunnerFactory.createJobRunner(job);


        eventSignal = new CountDownLatch(1);

        SCXMLDriverImpl driver = (SCXMLDriverImpl) jobRunner.getDriver();
        Set<String> states = new HashSet<String>();
        states.add(JobRunnerState.FINISHED.getStateName());
        states.add(JobRunnerState.FAILED.getStateName());
        states.add("task-processing");
        registerNofifierOnState(driver, states, eventSignal);

        driver.fireEvent(JobRunnerState.RUNNING.getTriggeringEvent());
        LOG.debug("Waiting for a RUNNING event");
        eventSignal.await();

        Thread.sleep(5000);

        eventSignal = new CountDownLatch(1);
        states = new HashSet<String>();
        states.add(JobRunnerState.FINISHED.getStateName());
        states.add(JobRunnerState.FAILED.getStateName());
        states.add(JobRunnerState.CANCELLED.getStateName());
        registerNofifierOnState(driver, states, eventSignal);

        LOG.debug("Cancelling the Job Runner");
        jobRunner.cancelProcessing();

        LOG.debug("Waiting for one of the final states");
        eventSignal.await();

        LOG.debug("Waiting the final state handler to complete its processing");
        while(!driver.isInFinalState()) {
            Thread.sleep(1000);
        }

        assertEquals(DriverProcessingStatus.CANCELLED, driver.getProcessingStatus());
    }

    /**
     * Prepares a R execution request and invokes SGE job runner for that request
     * @return
     * @throws MIFException
     * @throws JAXBException
     * @throws ExecutionException 
     */
    private GenericShellBasedJobRunner prepareJobRunner() throws MIFException, JAXBException, ExecutionException {
        FileUtilsResourcePublisher publisher = new FileUtilsResourcePublisher();
        publisher.setSharedLocationManager(sharedLocationManager);
        ExecutionRequestBuilder executionRequestBuilder = ExecutionRequestHelper.createBasicRExecutionRequestBuilder(publisher);
        executionRequestBuilder.setCommand(rCommand).setUserName(userName).setUserPassword(userPassword).setExecutionType(ExecutionType.R_Script.name()).setSubmitAsUserMode(true);
        Job job = createJob();
        job.setExecutionRequestMsg(executionRequestBuilder.getExecutionRequestMsg());
        jobManagementService.saveJob(job);
        GenericShellBasedJobRunner jobRunner = (GenericShellBasedJobRunner)jobRunnerFactory.createJobRunner(job);
        jobRunner.setEncrypter(EncrypterFactory.getEncrypter());
        String gridJobWorkingDirectory = new File(sgeSharedDirectory,"TEST_JOB_ID").getAbsolutePath();
        if(new InvokerHelper(job.getInvoker()).mkdir(gridJobWorkingDirectory, 777)) {
            job.setGridJobDirectory(gridJobWorkingDirectory);
        } else {
            throw new MIFException("Could not create grid job working directory");
        }

        return jobRunner;
    }
    /**
     * Creates a job with start task request
     * @return
     * @throws JAXBException
     */
    private Job createJob() {
        Job result = jobManagementService.createNewJob();
        result.setJobId("TEST_JOB_ID");
        File jobWorkingDir = new File(connectorWorkingDirectory,"TEST_JOB_ID");
        jobWorkingDir.mkdir();
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
