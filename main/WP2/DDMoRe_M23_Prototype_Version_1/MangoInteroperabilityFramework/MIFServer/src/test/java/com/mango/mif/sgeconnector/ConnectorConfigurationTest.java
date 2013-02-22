package com.mango.mif.sgeconnector;

import static org.junit.Assert.assertNotNull;

import java.io.File;
import java.util.UUID;

import javax.annotation.Resource;

import org.apache.log4j.Logger;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.test.annotation.DirtiesContext;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import com.google.common.collect.Lists;
import com.mango.mif.connector.TestsHelper;
import com.mango.mif.connector.domain.Job;
import com.mango.mif.connector.runner.api.JobRunnerFactory;
import com.mango.mif.connector.runner.impl.BaseGenericShellBasedStateHandler;
import com.mango.mif.connector.runner.impl.GenericShellBasedJobRunner;
import com.mango.mif.connector.runner.impl.JobInvokerProvider;
import com.mango.mif.connector.runner.impl.JobRunnerState;
import com.mango.mif.connector.runner.impl.SCXMLDriverImpl;
import com.mango.mif.core.api.SharedLocationManager;
import com.mango.mif.core.exec.ExecutionException;
import com.mango.mif.core.exec.template.JobAwareFreemarkerTemplateCommandBuilder;
import com.mango.mif.exception.MIFException;
import com.mango.mif.managers.JobManagementServiceImpl;
import com.mango.mif.sge.SGE;
/**
 * Test checking spring configuration of the RSGE connector
 */
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = {
        "/com/mango/mif/sgeconnector/SGEConnectorTest-context.xml",
        "classpath:sgeconnector-common-context.xml",
        "classpath:rsgeconnector-context.xml",
        "classpath:nonmemsgeconnector-context.xml",
        "classpath:cmd-sgeconnector-context.xml",
        "classpath:psnvpc-sgeconnector-context.xml",
        "classpath:psnscm-sgeconnector-context.xml",
        "classpath:psnbootstrap-sgeconnector-context.xml",
        "/spring/JmsTest-Config.xml"})
public class ConnectorConfigurationTest {
    
    private static final String	HERE	= new File(".").getAbsolutePath();
	
    private final static Logger LOG = Logger.getLogger(ConnectorConfigurationTest.class);

    @Resource(name="RSgeJobRunnerFactory")
    JobRunnerFactory rsgeJobRunnerFactory;

    @Resource(name="NONMEMSgeJobRunnerFactory")
    JobRunnerFactory nonmemsgeJobRunnerFactory;

    @Resource(name="CMDSgeJobRunnerFactory")
    JobRunnerFactory cmdsgeJobRunnerFactory;

    @Resource(name="PsNVPCSgeJobRunnerFactory")
    JobRunnerFactory psnvpcsgeJobRunnerFactory;

    @Resource(name="PsNSCMSgeJobRunnerFactory")
    JobRunnerFactory psnscmsgeJobRunnerFactory;

    @Resource(name="PsNBootstrapSgeJobRunnerFactory")
    JobRunnerFactory psnbootstrapsgeJobRunnerFactory;

    @Resource(name="sharedLocationManager")
    SharedLocationManager sharedLocationManager;

    @Resource(name = "jobManagementService")
    private JobManagementServiceImpl jobManagementService;
    
    private Job createJob() throws MIFException {
        Job testJob = TestsHelper.createJob("JOB_ID", UUID.randomUUID().toString());
        
        Job job = jobManagementService.createNewJob();
        job.setExecutionRequestMsg(testJob.getExecutionRequestMsg());
        return job;
    }
    
    /**
     * Test configuration
     * @throws MIFException
     */
    @DirtiesContext
    @Test
    public void shouldCreateValidJobRunnerAndStateHandlersForRsgeExecution() throws ExecutionException, MIFException {
    	jobManagementService.setJobInvokerProvider((JobInvokerProvider)rsgeJobRunnerFactory);
    	Job job = createJob();
        job.getExecutionRequest().setSubmitAsUserMode(true);
        job.getExecutionRequest().setExecutionFile("testFile.sh");
        job.setGridJobDirectory(HERE);
        job.addToDataMap(SGE.SGE_SUBMIT_SCRIPT_KEY, HERE);
        job.addToDataMap(SGE.SGE_JOB_ID_KEY, "1234");
        job.setSharedLocationManager(sharedLocationManager);
        GenericShellBasedJobRunner jobRunner = (GenericShellBasedJobRunner) rsgeJobRunnerFactory.createJobRunner(job);
        assertNotNull(jobRunner);
        assertNotNull(jobRunner.getInvoker());
        assertNotNull(jobRunner.getDriver());
        assertNotNull(jobRunner.getJobId());
        assertNotNull(job.getSharedLocationManager());
        assertNotNull(jobManagementService.getSharedLocationManager());
        SCXMLDriverImpl driver = (SCXMLDriverImpl)jobRunner.getDriver();

        verifyStateHandlers(driver,job, jobRunner);
    }

    /**
     * Test configuration
     * @throws MIFException
     */
    @DirtiesContext
    @Test
    public void shouldCreateValidJobRunnerAndStateHandlersForNONMEMsgeExecution() throws ExecutionException, MIFException {
    	jobManagementService.setJobInvokerProvider((JobInvokerProvider)nonmemsgeJobRunnerFactory);
    	Job job = createJob();
        job.getExecutionRequest().setSubmitAsUserMode(true);
        job.setGridJobDirectory(HERE);
        job.addToDataMap(SGE.SGE_SUBMIT_SCRIPT_KEY, HERE);
        job.addToDataMap(SGE.SGE_JOB_ID_KEY, "1234");
        job.getExecutionRequest().setExecutionFile("testFile.sh");
        job.setSharedLocationManager(sharedLocationManager);
        GenericShellBasedJobRunner jobRunner = (GenericShellBasedJobRunner) nonmemsgeJobRunnerFactory.createJobRunner(job);
        assertNotNull(jobRunner);
        assertNotNull(jobRunner.getInvoker());
        assertNotNull(jobRunner.getDriver());
        assertNotNull(jobRunner.getJobId());
        SCXMLDriverImpl driver = (SCXMLDriverImpl)jobRunner.getDriver();


        verifyStateHandlers(driver,job, jobRunner);
    }
    /**
     * Test configuration
     * @throws MIFException
     */
    @DirtiesContext
    @Test
    public void shouldCreateValidJobRunnerAndStateHandlersForCMDExecution() throws ExecutionException, MIFException {
    	jobManagementService.setJobInvokerProvider((JobInvokerProvider)cmdsgeJobRunnerFactory);
    	Job job = createJob();
        job.getExecutionRequest().setSubmitAsUserMode(true);
        job.setGridJobDirectory(HERE);
        job.addToDataMap(SGE.SGE_SUBMIT_SCRIPT_KEY, HERE);
        job.addToDataMap(SGE.SGE_JOB_ID_KEY, "1234");
        job.getExecutionRequest().setExecutionFile("testFile.sh");
        job.setSharedLocationManager(sharedLocationManager);
        GenericShellBasedJobRunner jobRunner = (GenericShellBasedJobRunner) cmdsgeJobRunnerFactory.createJobRunner(job);
        assertNotNull(jobRunner);
        assertNotNull(jobRunner.getInvoker());
        assertNotNull(jobRunner.getDriver());
        assertNotNull(jobRunner.getJobId());
        SCXMLDriverImpl driver = (SCXMLDriverImpl)jobRunner.getDriver();


        verifyStateHandlers(driver,job, jobRunner);
    }

    /**
     * Test configuration
     * @throws MIFException
     */
    @DirtiesContext
    @Test
    public void shouldCreateValidJobRunnerAndStateHandlersForPsNBootstrapExecution() throws ExecutionException, MIFException {
    	jobManagementService.setJobInvokerProvider((JobInvokerProvider)psnbootstrapsgeJobRunnerFactory);
    	Job job = createJob();
        job.getExecutionRequest().setSubmitAsUserMode(true);
        job.setGridJobDirectory(HERE);
        job.addToDataMap(SGE.SGE_SUBMIT_SCRIPT_KEY, HERE);
        job.addToDataMap(SGE.SGE_JOB_ID_KEY, "1234");
        job.getExecutionRequest().setExecutionFile("testFile.sh");
        job.setSharedLocationManager(sharedLocationManager);
        GenericShellBasedJobRunner jobRunner = (GenericShellBasedJobRunner) psnbootstrapsgeJobRunnerFactory.createJobRunner(job);
        assertNotNull(jobRunner);
        assertNotNull(jobRunner.getInvoker());
        assertNotNull(jobRunner.getDriver());
        assertNotNull(jobRunner.getJobId());
        SCXMLDriverImpl driver = (SCXMLDriverImpl)jobRunner.getDriver();


        verifyStateHandlers(driver,job, jobRunner);
    }

    /**
     * Test configuration
     * @throws MIFException
     */
    @DirtiesContext
    @Test
    public void shouldCreateValidJobRunnerAndStateHandlersForPsNSCMExecution() throws ExecutionException, MIFException {
    	jobManagementService.setJobInvokerProvider((JobInvokerProvider)psnscmsgeJobRunnerFactory);
    	Job job = createJob();
        job.getExecutionRequest().setSubmitAsUserMode(true);
        job.setGridJobDirectory(HERE);
        job.addToDataMap(SGE.SGE_SUBMIT_SCRIPT_KEY, HERE);
        job.addToDataMap(SGE.SGE_JOB_ID_KEY, "1234");
        job.getExecutionRequest().setExecutionFile("testFile.sh");
        job.setSharedLocationManager(sharedLocationManager);
        GenericShellBasedJobRunner jobRunner = (GenericShellBasedJobRunner) psnscmsgeJobRunnerFactory.createJobRunner(job);
        assertNotNull(jobRunner);
        assertNotNull(jobRunner.getInvoker());
        assertNotNull(jobRunner.getDriver());
        assertNotNull(jobRunner.getJobId());
        SCXMLDriverImpl driver = (SCXMLDriverImpl)jobRunner.getDriver();


        verifyStateHandlers(driver,job, jobRunner);
    }

    /**
     * Test configuration
     * @throws MIFException
     */
    @DirtiesContext
    @Test
    public void shouldCreateValidJobRunnerAndStateHandlersForPsNVPCExecution() throws ExecutionException, MIFException {
    	jobManagementService.setJobInvokerProvider((JobInvokerProvider)psnvpcsgeJobRunnerFactory);
    	Job job = createJob();
        job.getExecutionRequest().setSubmitAsUserMode(true);
        job.setGridJobDirectory(HERE);
        job.addToDataMap(SGE.SGE_SUBMIT_SCRIPT_KEY, HERE);
        job.addToDataMap(SGE.SGE_JOB_ID_KEY, "1234");
        job.getExecutionRequest().setExecutionFile("testFile.sh");
        job.setSharedLocationManager(sharedLocationManager);
        GenericShellBasedJobRunner jobRunner = (GenericShellBasedJobRunner) psnvpcsgeJobRunnerFactory.createJobRunner(job);
        assertNotNull(jobRunner);
        assertNotNull(jobRunner.getInvoker());
        assertNotNull(jobRunner.getDriver());
        assertNotNull(jobRunner.getJobId());
        SCXMLDriverImpl driver = (SCXMLDriverImpl)jobRunner.getDriver();


        verifyStateHandlers(driver,job, jobRunner);
    }
    /**
     * Verifies that the state handlers have been correctly configured
     * @param driver
     * @param job
     * @throws ExecutionException
     */
    private void verifyStateHandlers(SCXMLDriverImpl driver, Job job, GenericShellBasedJobRunner jobRunner) throws ExecutionException {

        BaseGenericShellBasedStateHandler monitoringStateHandler = ((BaseGenericShellBasedStateHandler)driver.getStateHandlers().get(JobRunnerState.TASK_MONITORING.getStateName()));
        assertNotNull(monitoringStateHandler.getCommandBuilder());
        monitoringStateHandler.getCommandBuilder().setJob(job);
        String statusCommand = monitoringStateHandler.getCommandBuilder().getCommand();
        LOG.debug(statusCommand);
        assertNotNull(statusCommand);
        assertNotNull(monitoringStateHandler.getInvokerResultHandler());

        BaseGenericShellBasedStateHandler postMonitoringStateHandler = ((BaseGenericShellBasedStateHandler)driver.getStateHandlers().get(JobRunnerState.TASK_POST_MONITORING.getStateName()));
        assertNotNull(monitoringStateHandler.getCommandBuilder());
        postMonitoringStateHandler.getCommandBuilder().setJob(job);
        String qacctCommand = postMonitoringStateHandler.getCommandBuilder().getCommand();
        LOG.debug(qacctCommand);
        assertNotNull(qacctCommand);
        assertNotNull(postMonitoringStateHandler.getInvokerResultHandler());

        BaseGenericShellBasedStateHandler submittingStateHandler = ((BaseGenericShellBasedStateHandler)driver.getStateHandlers().get(JobRunnerState.TASK_SUBMITTING.getStateName()));
        assertNotNull(submittingStateHandler.getCommandBuilder());
        submittingStateHandler.getCommandBuilder().setJob(job);
        String submitCommand = submittingStateHandler.getCommandBuilder().getCommand();
        LOG.debug(submitCommand);
        assertNotNull(submitCommand);
        assertNotNull(submittingStateHandler.getInvokerResultHandler());

        BaseGenericShellBasedStateHandler retrievingStateHandler = ((BaseGenericShellBasedStateHandler)driver.getStateHandlers().get(JobRunnerState.TASK_RETRIEVING.getStateName()));
        assertNotNull(retrievingStateHandler.getCommandBuilder());
        JobAwareFreemarkerTemplateCommandBuilder commandBuilder = retrievingStateHandler.getCommandBuilder();
        commandBuilder.setJob(job);
        commandBuilder.setVariable("mifOutputDirectory", "REQUEST_OUTPUT_DIRECTORY");
        commandBuilder.setVariable("mifHiddenDirectory", "HIDDEN_MIF_DIRECTORY");
        commandBuilder.setVariable("mifModifiedListFilename", "TIMESTAMP_FILE");
        commandBuilder.setVariable("gridOutputFileName", "STD_OUT");
        commandBuilder.setVariable("gridErrorFileName", "STD_ERR");
        commandBuilder.setVariable("gridJobFinalStatusFileName", "QACCT_FILE");
        commandBuilder.setVariable("sharedDirectory", "SHARED_DIRECTORY_PATH");
        commandBuilder.setVariable("filesToCopy", Lists.newArrayList("A","B","C"));
        String retrievingCommand = commandBuilder.getCommand();
        LOG.debug(retrievingCommand);
        assertNotNull(retrievingCommand);
        assertNotNull(retrievingStateHandler.getInvokerResultHandler());

        CancelledStateHandler cancelledStateHandler = (CancelledStateHandler) driver.getStateHandlers().get(JobRunnerState.CANCELLED.getStateName());
        assertNotNull(cancelledStateHandler.getQdelCommandBuilder());
        assertNotNull(cancelledStateHandler.getSgeOutputParser());
        assertNotNull(cancelledStateHandler.getDetailedStatusBuilder());

    }
}
