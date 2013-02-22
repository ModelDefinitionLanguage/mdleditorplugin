/* File			:  TaskMonitoringStateHandlerTest.java
 * Project		:  MIFServer
 * Created on	:  Jul 30, 2012
 */
package com.mango.mif.sgeconnector;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;
import static org.junit.Assert.fail;

import java.io.File;
import java.io.FilenameFilter;
import java.io.IOException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Properties;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import javax.annotation.Resource;

import org.apache.commons.io.FileUtils;
import org.apache.log4j.Logger;
import org.junit.After;
import org.junit.Before;
import org.junit.Ignore;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.test.annotation.DirtiesContext;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import com.google.common.base.Preconditions;
import com.google.common.collect.Maps;
import com.mango.mif.connector.domain.Job;
import com.mango.mif.connector.runner.impl.GenericShellBasedJobRunner;
import com.mango.mif.connector.runner.impl.StateHandlerException;
import com.mango.mif.core.exec.ExecutionException;
import com.mango.mif.core.exec.Invoker;
import com.mango.mif.core.exec.InvokerResult;
import com.mango.mif.core.exec.invoker.InvokerFactory;
import com.mango.mif.core.exec.template.ClasspathFreemarkerTemplateLocator;
import com.mango.mif.core.exec.template.FreemarkerTemplateCommandBuilder;
import com.mango.mif.core.exec.template.JobAwareFreemarkerTemplateCommandBuilder;
import com.mango.mif.domain.ExecutionRequest;
import com.mango.mif.domain.ExecutionRequestBuilder;
import com.mango.mif.psnsgeconnector.PsNSGEJob;
import com.mango.mif.sge.SGE;
import com.mango.mif.sge.SGEOutputParser;
import com.mango.mif.utils.MIFProperties;
import com.mango.mif.utils.TestProperties;
import com.mango.mif.utils.encrypt.EncrypterFactory;

/**
 * @version $Revision: $ as of $Date: $
 * <p>SVN Entry : $HeadURL: $
 * <p>SVN ID    : $Id: $
 * <p>Last edited by : $Author: $
 * 
 * A test checking the implementation of cancelled state handler
 * 
 * The test:
 * 1. sends a job spawning child jobs to grid
 * 2. cancels the job
 * 3. checks if all jobs have been cancelled
 */
@RunWith(SpringJUnit4ClassRunner.class)
@DirtiesContext
@ContextConfiguration(locations = {
        "/com/mango/mif/sgeconnector/SGEConnectorTest-context.xml",
        "classpath:sgeconnector-common-context.xml",
        "classpath:rsgeconnector-context.xml",
        "classpath:nonmemsgeconnector-context.xml",
        "classpath:cmd-sgeconnector-context.xml",
        "classpath:psnvpc-sgeconnector-context.xml",
        "classpath:psnscm-sgeconnector-context.xml",
        "classpath:psnbootstrap-sgeconnector-context.xml",
        "/spring/JmsTest-Config.xml" })
@Ignore("ignored due to SGE problems")
public class SGECancelledStateHandlerTest {
    /**
     * Logger
     */
    private final static Logger LOG = Logger.getLogger(SGECancelledStateHandlerTest.class);
    /**
     * tested instance
     */
    @Resource(name = "NONMEMSgeCancelledStateHandler")
    CancelledStateHandler stateHandler;

    /**
     * output parser
     */
    @Resource(name = "sgeOutputParser")
    SGEOutputParser sgeOutputParser;
    /**
     * User name with which send the job to a grid
     */
    private String userName;
    /**
     * SGE user grid directory
     */
    private File sgeSharedDir;
    /**
     * port
     */
    private int port;
    /**
     * Job ID
     */
    private String jobId;
    /**
     * name of a file containing error stream of a job execution
     */
    private String errorStreamFile = "stdErr";
    /**
     * name of a file containing output stream of a job execution
     */
    private String outputStreamFile = "stdOut";
    /**
     * Invoker
     */
    private Invoker invoker;
    /**
     * directory holding test data
     */
    private File testDirectory;
    /**
     * Location of the templates
     */
    private final static String TEMPLATES_LOCATION = "/runtime/templates";
    /**
     * SCRIPT_UTILS path
     */
    private String connectorUtilScriptsLocationVariable;
    /**
     * MANGO_UTILS path
     */
    private String mangoUtilScriptsLocationVariable;
    /**
     * PsN connector Utils path
     */
    private String psnConnectorUtilScriptsLocationVariable;
    /**
     * Job
     */
    private Job job;
    /**
     * Encrypted password
     */
    private String encryptedUserPassword;
    private String nonmemConnectorUtilScriptsLocationVariable;
    private String qsubParameters;

    @Before
    public void setUp() throws Exception {
        Preconditions.checkNotNull(System.getProperty(TestProperties.SGE_SHARED_LOCATION), TestProperties.SGE_SHARED_LOCATION + " property not set");
        Preconditions.checkNotNull(System.getProperty(TestProperties.MIF_CLIENT_USER_NAME), TestProperties.MIF_CLIENT_USER_NAME + " property not set");
        Preconditions.checkNotNull(System.getProperty(TestProperties.MIF_CLIENT_PASSWORD), TestProperties.MIF_CLIENT_PASSWORD + " property not set");
        Preconditions.checkNotNull(System.getProperty(TestProperties.MIF_JSCH_PORT), TestProperties.MIF_JSCH_PORT + " property not set");
        Preconditions.checkNotNull(System.getProperty(TestProperties.SGE_QSUB_PARAMETERS), TestProperties.SGE_QSUB_PARAMETERS + " property not set");
        Preconditions.checkNotNull(System.getProperty(TestProperties.MIF_COMMON_SCRIPTS_LOCATION), TestProperties.MIF_COMMON_SCRIPTS_LOCATION + " property not set");
        Preconditions.checkNotNull(System.getProperty(TestProperties.MIF_GENERIC_SCRIPTS_LOCATION), TestProperties.MIF_GENERIC_SCRIPTS_LOCATION + " property not set");
        Preconditions.checkNotNull(System.getProperty(TestProperties.MIF_PSNSGE_CONNECTOR_SCRIPTS_LOCATION), TestProperties.MIF_PSNSGE_CONNECTOR_SCRIPTS_LOCATION + " property not set");
        Preconditions.checkNotNull(System.getProperty(TestProperties.MIF_NONMEMSGE_CONNECTOR_SCRIPTS_LOCATION), TestProperties.MIF_NONMEMSGE_CONNECTOR_SCRIPTS_LOCATION + " property not set");
        connectorUtilScriptsLocationVariable = System.getProperty(TestProperties.MIF_GENERIC_SCRIPTS_LOCATION);
        mangoUtilScriptsLocationVariable = System.getProperty(TestProperties.MIF_COMMON_SCRIPTS_LOCATION);
        psnConnectorUtilScriptsLocationVariable = System.getProperty(TestProperties.MIF_PSNSGE_CONNECTOR_SCRIPTS_LOCATION);
        nonmemConnectorUtilScriptsLocationVariable = System.getProperty(TestProperties.MIF_NONMEMSGE_CONNECTOR_SCRIPTS_LOCATION);
        sgeSharedDir = new File(System.getProperty(TestProperties.SGE_SHARED_LOCATION));
        userName = System.getProperty(TestProperties.MIF_CLIENT_USER_NAME);
        port = Integer.parseInt(System.getProperty(TestProperties.MIF_JSCH_PORT));
        encryptedUserPassword = System.getProperty(TestProperties.MIF_CLIENT_PASSWORD);
        qsubParameters = System.getProperty(TestProperties.SGE_QSUB_PARAMETERS);

        //loading properties
        Properties properties = new Properties();
        MIFProperties.loadProperties(properties);

        InvokerFactory invokerFactory = new InvokerFactory().port(port);
        invoker = invokerFactory.createRunAsUserInvoker(userName, encryptedUserPassword);

        job = new Job();
        job.setInvoker(invoker);
        job.setUserName(userName);

        ExecutionRequestBuilder requestBuilder = new ExecutionRequestBuilder();
        ExecutionRequest er = requestBuilder.getExecutionRequest();
        job.setExecutionRequest(er);

        GenericShellBasedJobRunner jobRunner = new GenericShellBasedJobRunner();
        jobRunner.setJobId(job.getJobId());
        jobRunner.setEncrypter(EncrypterFactory.getEncrypter());
        jobRunner.setInvoker(invoker);
        stateHandler.setJobRunner(jobRunner);
    }

    @After
    public void tearDown() throws Exception {
        if (jobId != null) {
            String qdel = buildQdelCommand(jobId);
            invoker.execute(qdel);
        }
        if (testDirectory != null) {
            try {
                FileUtils.deleteDirectory(testDirectory);
            } catch (Exception e) {
                LOG.error(e);
            }
        }
    }

    /**
     * creates Job Manager
     * @throws ExecutionException 
     */
    private PsNSGEJob createSGEJob() throws ExecutionException {
        MIFProperties properties = MIFProperties.getInstance();
        PsNSGEJob psnSgeJob = new PsNSGEJob(job);
        ClasspathFreemarkerTemplateLocator templateLocator = new ClasspathFreemarkerTemplateLocator();
        templateLocator.setCommandTemplateDirectory(TEMPLATES_LOCATION);

        HashMap<String, Object> initialContext = Maps.newHashMap();
        initialContext.put("CONNECTOR_UTILS", connectorUtilScriptsLocationVariable);
        initialContext.put("MANGO_UTILS", mangoUtilScriptsLocationVariable);
        initialContext.put("PSN_SGECONNECTOR_UTILS", psnConnectorUtilScriptsLocationVariable);
        initialContext.put("NONMEMSGE_CONNECTOR_UTILS", nonmemConnectorUtilScriptsLocationVariable);

        JobAwareFreemarkerTemplateCommandBuilder commandBuilder = new JobAwareFreemarkerTemplateCommandBuilder(initialContext);
        commandBuilder.setJob(job);
        commandBuilder.setTemplateLocator(templateLocator);
        commandBuilder.setTemplate(properties.getProperty("sgeconnector.retrieveChildJobsDetailsScriptTemplate"));
        psnSgeJob.setRetrieveChildJobsDetailsCommandBuilder(commandBuilder);

        commandBuilder = new JobAwareFreemarkerTemplateCommandBuilder(initialContext);
        commandBuilder.setJob(job);
        commandBuilder.setTemplateLocator(templateLocator);
        commandBuilder.setTemplate(properties.getProperty("sgeconnector.retrieveChildJobsIdsScriptTemplate"));
        psnSgeJob.setRetrieveChildJobsIdsCommandBuilder(commandBuilder);
        return psnSgeJob;
    }

    @Test
    public void shouldSubmitAJobSpawningChildJobsToGridAndThenCancel() throws IOException, ExecutionException, InterruptedException, StateHandlerException {
        testDirectory = new File(sgeSharedDir, "SGECancelledStateHandlerTest-shouldSubmitAJobSpawningChildJobsToGridAndThenCancel");
        testDirectory.mkdirs();
        testDirectory.deleteOnExit();

        File sge = new File(testDirectory, "sge.sh");
        FileUtils.copyURLToFile(this.getClass().getResource("/com/mango/mif/sge/sge.sh"), sge);

        // given there is a job running on a grid
        File scriptFile = new File(testDirectory, "test-spawn.sh");

        ClasspathFreemarkerTemplateLocator templateLocator = new ClasspathFreemarkerTemplateLocator();
        templateLocator.setCommandTemplateDirectory("/com/mango/mif/sge");

        FreemarkerTemplateCommandBuilder commandBuilder = new FreemarkerTemplateCommandBuilder();
        commandBuilder.setTemplateLocator(templateLocator);

        commandBuilder.setTemplate("test-spawn.ftl");

        commandBuilder.setVariable("userName", userName);
        commandBuilder.setVariable("workingDir", testDirectory.getAbsolutePath());
        FileUtils.writeStringToFile(scriptFile, commandBuilder.getCommand());
        scriptFile.setExecutable(true);

        // Submission
        String submitCommand = buildQsubCommand(testDirectory, scriptFile);
        LOG.debug("Submission command: " + submitCommand);
        InvokerResult result = invoker.execute(submitCommand);

        if (result.getExitStatus() != 0) {
            fail("Submission of a job failed." + result.getErrorStream());
        }

        String[] matches = match("Your job ([\\d]+)([^\\d].+)?", result.getOutputStream().trim());

        if (matches == null || matches.length < 2) {
            fail("Submission of a job failed." + result.getOutputStream());
            fail("Could not match a job id in qsub results");
        }
        jobId = matches[1];

        Map<String, String> data = Maps.newHashMap();
        data.put(SGE.SGE_JOB_ID_KEY, jobId);
        job.setData(data);
        job.setGridJobDirectory(testDirectory.getAbsolutePath());
        job.getExecutionRequest().setExecutionFile("test-spawn.sh");

        //the spawning script creates 4 folders for child jobs named "A", "B", "C", "D"
        while (testDirectory.list(new FilenameFilter() {
            @Override
            public boolean accept(File dir, String name) {
                return "A".equals(name) || "B".equals(name) || "C".equals(name) || "D".equals(name);
            }
        }).length != 4) {
            Thread.sleep(1000);
        }

        PsNSGEJob psnSgeJob = createSGEJob();
        List<String> ids = psnSgeJob.getChildJobsIds();
        for (String id : ids) {
            LOG.debug("Child job id: " + id);
        }
        stateHandler.doProcessing();

        // Wait for the jobs to die on the grid.
        Thread.sleep(10000);

        assertEquals(4, ids.size());

        //we just check if the jobs have been qdel'ed

        for (String id : ids) {
            String command = buildQstatCommand(id);
            InvokerResult invokerResult = invoker.execute(command);
            assertTrue("Job " + id + " was found by qstat. " + invokerResult.getOutputStream(), 0 != invokerResult.getExitStatus());
        }
    }

    /**
     * Match a regexp against some input and return an array of the matches.  Note that in the returned results,
     * the element at position zero contains the group zero, i.e. the entire matched string.  If you want the
     * value of group 1, you have to look at position 1.
     *
     * @param regexp the regexp to look for
     * @param input the input to search in
     * @return array of matches, zero being group zero, one being group one, etc.
     */
    private String[] match(String regexp, String input) {
        Pattern pattern = Pattern.compile(regexp);
        Matcher matcher = pattern.matcher(input);

        if (!matcher.matches()) {
            return null;
        }

        // The pattern matches, there are no groups, therefore we must have matched the whole input string.
        if (matcher.groupCount() == 0) {
            return new String[] { input };
        }
        String[] result = new String[matcher.groupCount() + 1];
        for (int i = 0; i <= matcher.groupCount(); i++) {
            result[i] = matcher.group(i);
        }

        return result;
    }

    /**
     * builds a qdel command
     * @param testDirectory
     * @param scriptFile
     * @return
     * @throws IOException
     */
    private String buildQdelCommand(String jobId) throws IOException {
        return "source sge.sh\nqdel \"" + jobId + "\n";
    }

    /**
     * Builds qstat command
     * @param id
     * @return
     */
    private String buildQstatCommand(String id) {
        return "source sge.sh\nqstat -j \"" + id + "\n";
    }

    /**
     * builds a qsub command
     * @param testDirectory
     * @param scriptFile
     * @return
     */
    private String buildQsubCommand(File testDirectory, File scriptFile) {
        return "source sge.sh \n" +
                "\nqsub -u " + userName + " " + qsubParameters + " -e " + errorStreamFile + " -o " + outputStreamFile + " -wd \""
                + testDirectory.getAbsolutePath() + "\" " + scriptFile.getAbsolutePath() + "\n";
    }
}
