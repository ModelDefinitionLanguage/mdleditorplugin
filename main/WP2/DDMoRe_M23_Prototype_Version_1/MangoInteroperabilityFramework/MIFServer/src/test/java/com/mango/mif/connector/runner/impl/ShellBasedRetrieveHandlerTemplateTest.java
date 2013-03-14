package com.mango.mif.connector.runner.impl;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;
import static org.mockito.Matchers.any;
import static org.mockito.Mockito.when;

import java.io.File;
import java.io.IOException;
import java.util.Properties;

import org.apache.log4j.Logger;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.runners.MockitoJUnitRunner;

import com.google.common.base.Preconditions;
import com.mango.mif.connector.TestsHelper;
import com.mango.mif.connector.domain.Job;
import com.mango.mif.connector.runner.api.SCXMLDriver;
import com.mango.mif.core.exec.ExecutionException;
import com.mango.mif.core.exec.Invoker;
import com.mango.mif.core.exec.invoker.InvokerFactory;
import com.mango.mif.core.exec.invoker.InvokerHelper;
import com.mango.mif.core.exec.template.DirectoryTemplateLocator;
import com.mango.mif.core.exec.template.JobAwareFreemarkerTemplateCommandBuilder;
import com.mango.mif.domain.ExecutionRequest;
import com.mango.mif.managers.JobManagementService;
import com.mango.mif.utils.MIFProperties;
import com.mango.mif.utils.TestProperties;

/**
 * Tests Shell Based Retrieve Handler
 */
@RunWith(MockitoJUnitRunner.class)
public class ShellBasedRetrieveHandlerTemplateTest {
    private final static Logger logger = Logger.getLogger(ShellBasedRetrieveHandlerTemplateTest.class);

    private static final String MIF_HIDDEN_DIR_NAME = ".MIF";

    private String gridJobOutputFileName = "MIF-output.txt";

    private String gridJobErrorFileName = "MIF-error.txt";
    
    private String finalStatusFileName = "MIF-final-status.txt";

    private String modifiedFilesFileName = "MIF-modified-list.txt";

    private String userName;

    private Invoker invoker;

    private InvokerHelper invokerHelper;

    private String commonScripts;

    private String genericScripts;

    private String retrieveScriptTemplate = "generic/retrieveHandler.ftl";

    @Mock
    JobManagementService jobManagementService;

    @Mock
    Job job;

    @Mock
    SCXMLDriver driver;

    private String templatesDirectory;
    
    private void loadMIFProperties() throws IOException {
        Properties properties = new Properties();
        properties.load(ShellBasedRetrieveHandlerTemplateTest.class.getResourceAsStream("/com/mango/mif/sgeconnector/connector.properties"));
        MIFProperties.loadProperties(properties);
    }
    
    @Before
    public void setUp() throws ExecutionException, IOException {
        TestsHelper.initProperties(this.getClass(),"/tests.properties");
        
        loadMIFProperties();
        
        templatesDirectory = System.getProperty(TestProperties.TEMPLATES_DIRECTORY_LOCATION);
        commonScripts = System.getProperty(TestProperties.MIF_COMMON_SCRIPTS_LOCATION);
        genericScripts = System.getProperty(TestProperties.MIF_GENERIC_SCRIPTS_LOCATION);
        

        userName = System.getProperty(TestProperties.MIF_ALTER_CLIENT_USER_NAME);
        String encryptedPassword = System.getProperty(TestProperties.MIF_ALTER_CLIENT_PASSWORD);
        String portProp = System.getProperty(TestProperties.MIF_JSCH_PORT);

        Preconditions.checkNotNull(userName, TestProperties.MIF_ALTER_CLIENT_USER_NAME + " property is not set.");
        Preconditions.checkNotNull(encryptedPassword, TestProperties.MIF_ALTER_CLIENT_PASSWORD + " property is not set.");
        Preconditions.checkNotNull(portProp, TestProperties.MIF_JSCH_PORT + " property is not set.");
        Preconditions.checkNotNull(commonScripts, TestProperties.MIF_COMMON_SCRIPTS_LOCATION + " property is not set.");
        Preconditions.checkNotNull(genericScripts, TestProperties.MIF_GENERIC_SCRIPTS_LOCATION + " property is not set.");

        InvokerFactory invokerFactory = new InvokerFactory();
        invoker = invokerFactory.createRunAsNavplusInvoker();
        invokerHelper = new InvokerHelper(invoker);
    }

    /**
     * Temporary directories must be created as the user, otherwise things screw up further along the line.
     * @param path The directory to create.
     * @param invoker The invoker to create it with.
     * @return File object containig path of directory.
     * @throws ExecutionException If we sc
     */
    private File createTmpDirectory(String path, InvokerHelper invokerHelper) throws ExecutionException {
        invokerHelper.mkdir(path, 0755);
        return new File(path);
    }

    @Test
    public void shouldSuccessfulyExecuteRetrieveScript() throws Exception {

        ShellBasedRetrieveHandler handler = new ShellBasedRetrieveHandler("task-retrieving", "task.postprocess");
        handler.setJobManagementService(jobManagementService);
        handler.setSCXMLDriver(driver);
        handler.setInvokerResultHandler(new GenericShellCommandInvokerResultHandler(SCXMLDriver.NULL_EVENT));

        DirectoryTemplateLocator templateLocator = new DirectoryTemplateLocator();
        templateLocator.setCommandTemplateDirectory(new File(templatesDirectory));
        JobAwareFreemarkerTemplateCommandBuilder commandBuilder = new JobAwareFreemarkerTemplateCommandBuilder();
        commandBuilder.setTemplateLocator(templateLocator);
        commandBuilder.setTemplate(retrieveScriptTemplate);
        File tmpGridShareDirectory = createTmpDirectory(TestsHelper.generateTempDirectoryPath("-SRC"), invokerHelper);
        File tmpMifOutputDirectory = createTmpDirectory(TestsHelper.generateTempDirectoryPath("-DEST"), invokerHelper);

        File tmpGridHiddenDir = new File(tmpGridShareDirectory,MIF_HIDDEN_DIR_NAME);
        invokerHelper.mkdir(tmpGridHiddenDir.getAbsolutePath(), 0755);

        logger.info("GRID share directory:  " + tmpGridShareDirectory);
        logger.info("Job output directory: " + tmpMifOutputDirectory);
        
        GenericShellBasedJobRunner jobRunner = new GenericShellBasedJobRunner();
        when(job.getJobId()).thenReturn("999");
        ExecutionRequest er = new ExecutionRequest();
        when(job.getExecutionRequest()).thenReturn(er);
        when(job.getInvoker()).thenReturn(invoker);
        when(job.getRequestOutputDirectory()).thenReturn(tmpMifOutputDirectory.getAbsolutePath());
        when(job.getGridJobDirectory()).thenReturn(tmpGridShareDirectory);
        when(job.getGridJobDirectoryMifHiddenDir()).thenReturn(tmpGridHiddenDir);
        when(jobManagementService.getJob((String) any())).thenReturn(job);
        when(jobManagementService.saveJob(job)).thenReturn(job);
        when(job.isRunInRequestDirectory()).thenReturn(false);

        jobRunner.setInvoker(invoker);
        jobRunner.setJobId(job.getJobId());

        final String TOO_OLD_1 = "tooOldToCopy1.txt";
        final String TOO_OLD_2 = "tooOldToCopy2.txt";
        final String SHOULD_COPY_1 = "shouldBeCopied1.txt";
        
        invokerHelper.createFileFromContents("This content should not be copied.", new File(tmpGridShareDirectory, TOO_OLD_1).getAbsolutePath(), 0644);
        invokerHelper.createFileFromContents("This content should not be copied either.", new File(tmpGridShareDirectory, TOO_OLD_2).getAbsolutePath(), 0644);
        invokerHelper.createFileFromContents("This content should be copied.", new File(tmpGridShareDirectory, SHOULD_COPY_1).getAbsolutePath(), 0644);
        invokerHelper.createFileFromContents("Output generated by command.", new File(tmpGridShareDirectory, "generatedOutput.txt").getAbsolutePath(), 0644);

        invokerHelper.createFileFromContents("./" + SHOULD_COPY_1 + "\n", new File(tmpGridHiddenDir, "MIF.list").getAbsolutePath(), 0644);
        invokerHelper.createFileFromContents("STDOUT of grid job.", new File(tmpGridHiddenDir, gridJobOutputFileName).getAbsolutePath(), 0644);
        invokerHelper.createFileFromContents("STDERR of grid job.", new File(tmpGridHiddenDir, gridJobErrorFileName).getAbsolutePath(), 0644);
        invokerHelper.createFileFromContents("Final QACCT call.", new File(tmpGridHiddenDir, finalStatusFileName).getAbsolutePath(), 0644);

        handler.setJobRunner(jobRunner);
        handler.setGridJobOutputFileName(gridJobOutputFileName);
        handler.setGridJobErrorFileName(gridJobErrorFileName);
        handler.setGridJobFinalStatusFileName(finalStatusFileName);

        handler.setGridModifiedFileListOuptutFileName(modifiedFilesFileName);
        commandBuilder.setVariable("CONNECTOR_UTILS", genericScripts);
        commandBuilder.setVariable("MANGO_UTILS", commonScripts);
        commandBuilder.setVariable("SCRIPT_PREAMBLE", "");

        handler.setCommandBuilder(commandBuilder);
        handler.call();

        boolean tooOldToCopy1Exists = invokerHelper.fileExists(new File(tmpMifOutputDirectory, TOO_OLD_1).getAbsolutePath());
        boolean tooOldToCopy2Exists = invokerHelper.fileExists(new File(tmpMifOutputDirectory, TOO_OLD_2).getAbsolutePath());
        boolean shouldBeCopied1Exists = invokerHelper.fileExists(new File(tmpMifOutputDirectory, SHOULD_COPY_1).getAbsolutePath());
        boolean outputInWrongPlaceExists = invokerHelper.fileExists(new File(tmpMifOutputDirectory, gridJobOutputFileName).getAbsolutePath());
        boolean errorInWrongPlaceExists = invokerHelper.fileExists(new File(tmpMifOutputDirectory, gridJobErrorFileName).getAbsolutePath());

        boolean outputInRightPlaceExists = invokerHelper.fileExists(new File(handler.getHiddenMifOutputDirectoryPath(), gridJobOutputFileName).getAbsolutePath());
        boolean errorInRightPlaceExists = invokerHelper.fileExists(new File(handler.getHiddenMifOutputDirectoryPath(), gridJobErrorFileName).getAbsolutePath());

        boolean modifiedFilesListFileExists = invokerHelper.fileExists(new File(handler.getHiddenMifOutputDirectoryPath(), modifiedFilesFileName).getAbsolutePath());

        assertFalse(tooOldToCopy1Exists);
        assertFalse(tooOldToCopy2Exists);
        assertTrue(shouldBeCopied1Exists);
        assertFalse(outputInWrongPlaceExists);
        assertFalse(errorInWrongPlaceExists);
        assertTrue(outputInRightPlaceExists);
        assertTrue(errorInRightPlaceExists);
        assertTrue(modifiedFilesListFileExists);
    }
}
