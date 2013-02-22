package com.mango.mif.sgeconnector;

import static org.junit.Assert.assertNotNull;
import static org.mockito.Matchers.any;
import static org.mockito.Mockito.when;

import java.io.File;
import java.io.IOException;
import java.util.HashMap;
import java.util.Properties;
import java.util.UUID;

import org.apache.commons.io.FileUtils;
import org.junit.Before;
import org.junit.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import com.google.common.base.Preconditions;
import com.google.common.collect.Maps;
import com.mango.mif.connector.TestsHelper;
import com.mango.mif.connector.domain.Job;
import com.mango.mif.connector.runner.api.SCXMLDriver;
import com.mango.mif.connector.runner.impl.GenericShellBasedJobRunner;
import com.mango.mif.connector.runner.impl.GenericShellBasedStateHandler;
import com.mango.mif.connector.runner.impl.GenericShellCommandInvokerResultHandler;
import com.mango.mif.connector.runner.status.SummaryMessageBuilderTest;
import com.mango.mif.core.api.SharedLocationManager;
import com.mango.mif.core.exec.ExecutionException;
import com.mango.mif.core.exec.Invoker;
import com.mango.mif.core.exec.invoker.InvokerFactory;
import com.mango.mif.core.exec.template.ClasspathFreemarkerTemplateLocator;
import com.mango.mif.core.exec.template.JobAwareFreemarkerTemplateCommandBuilder;
import com.mango.mif.domain.ExecutionType;
import com.mango.mif.managers.JobManagementService;
import com.mango.mif.sge.SGE;
import com.mango.mif.utils.MIFProperties;
import com.mango.mif.utils.TestProperties;
import com.mango.mif.utils.encrypt.EncrypterFactory;

public class SGENONMEMTaskFinishedStateHandlerTemplateTest {
    /**
     * Scripts directory
     */
    private final static String TEMPLATES = "/runtime/templates";
    /**
     * tested template
     */
    private String templateName = "nonmemsgeconnector/validateNonmemOutputs.ftl";
    /**
     * user name
     */
    private String userName;
    /**
     * jsch port
     */
    private int port;
    /**
     * invoker
     */
    private Invoker invoker;
    /**
     * common scripts location
     */
    private String commonScripts;
    /**
     * generic scripts location
     */
    private String genericScripts;
    /**
     * nonmem sge connector scripts
     */
    private String connectorScripts;
    /**
     * Job runner
     */
    @Mock
    GenericShellBasedJobRunner jobRunner;
    /**
     * Job
     */
    @Mock
    Job job;
    /**
     * shared location
     */
    @Mock
    SharedLocationManager sharedLocationManager;
    /**
     * Tested instance
     */
    private GenericShellBasedStateHandler stateHandler;
    /**
     * Driver
     */
    @Mock
    SCXMLDriver driver;
    /**
     * Driver
     */
    @Mock
    JobManagementService jobManagementService;
    
    @Before
    public void setUp() throws ExecutionException, IOException {
        MockitoAnnotations.initMocks(this);

        Properties properties = new Properties();
        properties.load(SummaryMessageBuilderTest.class.getResourceAsStream("/com/mango/mif/sgeconnector/connector.properties"));
        MIFProperties.loadProperties(properties);
        
        userName = System.getProperty(TestProperties.MIF_ALTER_CLIENT_USER_NAME);
        String encryptedPassword = System.getProperty(TestProperties.MIF_ALTER_CLIENT_PASSWORD);
        String portProp = System.getProperty(TestProperties.MIF_JSCH_PORT);
        commonScripts = System.getProperty(TestProperties.MIF_COMMON_SCRIPTS_LOCATION);
        genericScripts = System.getProperty(TestProperties.MIF_GENERIC_SCRIPTS_LOCATION);
        connectorScripts = System.getProperty(TestProperties.MIF_NONMEMSGE_CONNECTOR_SCRIPTS_LOCATION);

        Preconditions.checkNotNull(userName, TestProperties.MIF_ALTER_CLIENT_USER_NAME + " property is not set.");
        Preconditions.checkNotNull(encryptedPassword, TestProperties.MIF_ALTER_CLIENT_PASSWORD + " property is not set.");
        Preconditions.checkNotNull(portProp, TestProperties.MIF_JSCH_PORT + " property is not set.");
        Preconditions.checkNotNull(commonScripts, TestProperties.MIF_COMMON_SCRIPTS_LOCATION + " property is not set.");
        Preconditions.checkNotNull(genericScripts, TestProperties.MIF_GENERIC_SCRIPTS_LOCATION + " property is not set.");
        Preconditions.checkNotNull(connectorScripts, TestProperties.MIF_NONMEMSGE_CONNECTOR_SCRIPTS_LOCATION + " property is not set.");

        port = Integer.parseInt(portProp);

        job = TestsHelper.createJob("TEST-JOB", UUID.randomUUID().toString());
        job.getExecutionRequest().setExecutionFile("EXECUTION-FILE.R");
        job.setUserName(userName);
        job.addToDataMap(SGE.SGE_JOB_ID_KEY, "SGE-JOB-ID");
        job.addToDataMap(SGE.SGE_SUBMIT_SCRIPT_KEY, "SGE-JOB-SCRIPT.sh");
        job.setSharedLocationManager(sharedLocationManager);

        InvokerFactory invokerFactory = new InvokerFactory().port(port);
        invoker = invokerFactory.createRunAsUserInvoker(userName, encryptedPassword);

        when(jobManagementService.getJob((String)any())).thenReturn(job);
        when(jobManagementService.saveJob(job)).thenReturn(job);
        when(jobRunner.getInvoker()).thenReturn(invoker);
        when(jobRunner.getJobId()).thenReturn(job.getJobId());
    }

    /**
     * Prepares state handler
     * @throws ExecutionException
     */
    private void prepareStateHandler() throws ExecutionException {
        ClasspathFreemarkerTemplateLocator templateLocator = new ClasspathFreemarkerTemplateLocator();
        templateLocator.setCommandTemplateDirectory(TEMPLATES);

        HashMap<String, Object> initialContext = Maps.newHashMap();
        initialContext.put("sharedLocationManager", sharedLocationManager);
        initialContext.put("NONMEMSGE_CONNECTOR_UTILS", connectorScripts);
        initialContext.put("CONNECTOR_UTILS", genericScripts);
        initialContext.put("MANGO_UTILS", commonScripts);
        initialContext.put("scriptExecutablePath", "default.sh");
        initialContext.put("hiddenMifDirectoryName", ".MIF");
        initialContext.put("sgeJobStdErrFileName", "MIF.stderr");
        initialContext.put("sgeJobStdOutFileName", "MIF.stdout");

        JobAwareFreemarkerTemplateCommandBuilder commandBuilder = new JobAwareFreemarkerTemplateCommandBuilder(initialContext);
        commandBuilder.setTemplateLocator(templateLocator);
        commandBuilder.setTemplate(templateName);
        stateHandler = new GenericShellBasedStateHandler(SCXMLDriver.NULL_STATE);
        stateHandler.setCommandBuilder(commandBuilder);
        stateHandler.setJobRunner(jobRunner);

        stateHandler.setEncrypter(EncrypterFactory.getEncrypter());
        stateHandler.setInvokerResultHandler(new GenericShellCommandInvokerResultHandler(SCXMLDriver.NULL_EVENT));
        stateHandler.setSCXMLDriver(driver);
        stateHandler.setJobManagementService(jobManagementService);
    }

    @Test
    public void shouldFailIfPsNExecAndNoLSTfileGenerated() throws Exception {
        File testDir = FileUtils.toFile(SGENONMEMTaskFinishedStateHandlerTemplateTest.class.getResource("/com/mango/mif/sgeconnector/nonmemNoLst/"));

        when(sharedLocationManager.getRequestInputDirectory((String) any())).thenReturn(testDir);
        when(sharedLocationManager.getRequestOutputDirectory((String) any())).thenReturn(testDir);

        prepareStateHandler();
        job.getExecutionRequest().setExecutionFile("subfolder/run.ctl");

        job.getExecutionRequest().setType(ExecutionType.PsN_EXEC.name());

        stateHandler.call();
        assertNotNull(stateHandler.getStateHandlerException());
    }

    @Test
    public void shouldFailIfNMFEExecAndNoLSTfileGenerated() throws Exception {
        File testDir = FileUtils.toFile(SGENONMEMTaskFinishedStateHandlerTemplateTest.class.getResource("/com/mango/mif/sgeconnector/nonmemNoLst/"));

        when(sharedLocationManager.getRequestInputDirectory((String) any())).thenReturn(testDir);
        when(sharedLocationManager.getRequestOutputDirectory((String) any())).thenReturn(testDir);

        prepareStateHandler();
        job.getExecutionRequest().setExecutionFile("subfolder/run.ctl");
        job.getExecutionRequest().setType(ExecutionType.NMFE.name());

        stateHandler.call();
        assertNotNull(stateHandler.getStateHandlerException());
    }

    @Test
    public void shouldFailIfPsNExecAndNoOBJVInLSTfileGenerated() throws Exception {
        File testDir = FileUtils.toFile(SGENONMEMTaskFinishedStateHandlerTemplateTest.class.getResource("/com/mango/mif/sgeconnector/nonmemNoObjFuncInLst/"));

        prepareStateHandler();
        when(sharedLocationManager.getRequestInputDirectory((String) any())).thenReturn(testDir);
        when(sharedLocationManager.getRequestOutputDirectory((String) any())).thenReturn(testDir);
        job.getExecutionRequest().setExecutionFile("subfolder/run.ctl");

        job.getExecutionRequest().setType(ExecutionType.PsN_EXEC.name());

        stateHandler.call();

        assertNotNull(stateHandler.getStateHandlerException());
    }

    @Test
    public void shouldFailIfNMFEAndNoOBJVInLSTfileGenerated() throws Exception {
        File testDir = FileUtils.toFile(SGENONMEMTaskFinishedStateHandlerTemplateTest.class.getResource("/com/mango/mif/sgeconnector/nonmemNoObjFuncInLst/"));

        when(sharedLocationManager.getRequestInputDirectory((String) any())).thenReturn(testDir);
        when(sharedLocationManager.getRequestOutputDirectory((String) any())).thenReturn(testDir);

        prepareStateHandler();
        job.getExecutionRequest().setExecutionFile("subfolder/run.ctl");
        job.getExecutionRequest().setType(ExecutionType.NMFE.name());

        stateHandler.call();
        assertNotNull(stateHandler.getStateHandlerException());
    }

    @Test
    public void shouldFailIfFatalErrorInGridOutput() throws Exception {
        File testDir = FileUtils.toFile(SGENONMEMTaskFinishedStateHandlerTemplateTest.class.getResource("/com/mango/mif/sgeconnector/nonmemFatalError/"));

        when(sharedLocationManager.getRequestInputDirectory((String) any())).thenReturn(testDir);
        when(sharedLocationManager.getRequestOutputDirectory((String) any())).thenReturn(testDir);

        prepareStateHandler();
        job.getExecutionRequest().setExecutionFile("subfolder/run.ctl");

        job.getExecutionRequest().setType(ExecutionType.PsN_EXEC.name());

        stateHandler.call();
        assertNotNull(stateHandler.getStateHandlerException());
    }

}
