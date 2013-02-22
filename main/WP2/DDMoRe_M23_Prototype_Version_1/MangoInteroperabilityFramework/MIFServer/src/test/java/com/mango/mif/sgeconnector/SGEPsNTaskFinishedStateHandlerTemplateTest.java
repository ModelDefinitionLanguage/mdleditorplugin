package com.mango.mif.sgeconnector;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.mockito.Matchers.any;
import static org.mockito.Mockito.when;

import java.io.File;
import java.io.IOException;
import java.util.HashMap;
import java.util.UUID;

import org.apache.commons.io.FileUtils;
import org.apache.log4j.Logger;
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
import com.mango.mif.core.api.SharedLocationManager;
import com.mango.mif.core.exec.ExecutionException;
import com.mango.mif.core.exec.Invoker;
import com.mango.mif.core.exec.invoker.InvokerFactory;
import com.mango.mif.core.exec.template.ClasspathFreemarkerTemplateLocator;
import com.mango.mif.core.exec.template.JobAwareFreemarkerTemplateCommandBuilder;
import com.mango.mif.domain.ExecutionType;
import com.mango.mif.managers.JobManagementService;
import com.mango.mif.sge.SGE;
import com.mango.mif.utils.TestProperties;
import com.mango.mif.utils.encrypt.EncrypterFactory;

public class SGEPsNTaskFinishedStateHandlerTemplateTest {

    private final static Logger LOG = Logger.getLogger(SGEPsNTaskFinishedStateHandlerTemplateTest.class);
    private final static String TEMPLATES = "/runtime/templates";
    private String templateName = "psnsgeconnector/validatePsNOutputs.ftl";
    private String userName;
    private int port;
    private Invoker invoker;
    private String commonScripts;
    private String genericScripts;
    
    @Mock GenericShellBasedJobRunner jobRunner;
    
    @Mock Job job;
    
    @Mock SharedLocationManager sharedLocationManager;
    
    private GenericShellBasedStateHandler stateHandler;
    
    @Mock SCXMLDriver driver;
    
    @Mock JobManagementService jobManagementService;

    @Before
    public void setUp() throws ExecutionException, IOException {
        MockitoAnnotations.initMocks(this);

        userName = System.getProperty(TestProperties.MIF_ALTER_CLIENT_USER_NAME);
        String encryptedPassword = System.getProperty(TestProperties.MIF_ALTER_CLIENT_PASSWORD);
        String portProp = System.getProperty(TestProperties.MIF_JSCH_PORT);
        commonScripts = System.getProperty(TestProperties.MIF_COMMON_SCRIPTS_LOCATION);
        genericScripts = System.getProperty(TestProperties.MIF_GENERIC_SCRIPTS_LOCATION);

        Preconditions.checkNotNull(userName, TestProperties.MIF_ALTER_CLIENT_USER_NAME + " property is not set.");
        Preconditions.checkNotNull(encryptedPassword, TestProperties.MIF_ALTER_CLIENT_PASSWORD + " property is not set.");
        Preconditions.checkNotNull(portProp, TestProperties.MIF_JSCH_PORT + " property is not set.");
        Preconditions.checkNotNull(commonScripts, TestProperties.MIF_COMMON_SCRIPTS_LOCATION + " property is not set.");
        Preconditions.checkNotNull(genericScripts, TestProperties.MIF_GENERIC_SCRIPTS_LOCATION + " property is not set.");

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
        when(jobRunner.getInvoker()).thenReturn(invoker);
        when(jobManagementService.saveJob(job)).thenReturn(job);
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
    public void shouldFailIfFatalErrorInGridOutput() throws Exception {
        File testDir = FileUtils.toFile(SGEPsNTaskFinishedStateHandlerTemplateTest.class.getResource("/com/mango/mif/sgeconnector/nonmemFatalError/"));

        when(sharedLocationManager.getRequestInputDirectory((String) any())).thenReturn(testDir);
        when(sharedLocationManager.getRequestOutputDirectory((String) any())).thenReturn(testDir);

        prepareStateHandler();
        job.getExecutionRequest().setExecutionFile("subfolder/run.ctl");

        job.getExecutionRequest().setType(ExecutionType.PsN_EXEC.name());

        stateHandler.call();
        assertNotNull(stateHandler.getStateHandlerException());
    }

    @Test
    public void shouldNotFailIfNoFatalErrorInGridOutput() throws Exception {
        File testDir = FileUtils.toFile(SGEPsNTaskFinishedStateHandlerTemplateTest.class.getResource("/com/mango/mif/sgeconnector/psnNoFatalError/"));

        when(sharedLocationManager.getRequestInputDirectory((String) any())).thenReturn(testDir);
        when(sharedLocationManager.getRequestOutputDirectory((String) any())).thenReturn(testDir);

        prepareStateHandler();
        job.getExecutionRequest().setExecutionFile("subfolder/run.ctl");

        job.getExecutionRequest().setType(ExecutionType.PsN_EXEC.name());

        stateHandler.call();
        LOG.error(stateHandler.getStateHandlerException());
        assertEquals(null, stateHandler.getStateHandlerException());
    }
}
