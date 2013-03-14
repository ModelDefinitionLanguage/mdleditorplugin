/* File			:  TaskMonitoringStateHandlerTest.java
 * Project		:  MIFServer
 * Created on	:  Jul 30, 2012
 */
package com.mango.mif.sgeconnector;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.fail;

import java.io.File;
import java.io.IOException;
import java.util.Map;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import javax.annotation.Resource;

import org.apache.commons.io.FileUtils;
import org.apache.log4j.Logger;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.test.annotation.DirtiesContext;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import com.google.common.base.Preconditions;
import com.google.common.collect.Maps;
import com.mango.mif.connector.domain.Job;
import com.mango.mif.connector.runner.impl.GenericShellBasedStateHandler;
import com.mango.mif.core.exec.ExecutionException;
import com.mango.mif.core.exec.Invoker;
import com.mango.mif.core.exec.InvokerResult;
import com.mango.mif.core.exec.invoker.InvokerFactory;
import com.mango.mif.core.exec.template.JobAwareFreemarkerTemplateCommandBuilder;
import com.mango.mif.domain.ExecutionRequest;
import com.mango.mif.domain.ExecutionRequestBuilder;
import com.mango.mif.sge.SGE;
import com.mango.mif.sge.SGEOutput;
import com.mango.mif.sge.SGEOutputParser;
import com.mango.mif.utils.TestProperties;

/**
 * @version $Revision: $ as of $Date: $
 * <p>SVN Entry : $HeadURL: $
 * <p>SVN ID    : $Id: $
 * <p>Last edited by : $Author: $
 * 
 * A test checking the implementation of monitoring state handler
 * 
 * The test:
 * 1. Mocks a job
 * 2. Prepares job's inputs
 * 3. sends a job to a grid
 * 4. generates a command using the builder configured in spring
 * 5. executes the command
 * 6. checks the output
 */
@DirtiesContext
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
        "/spring/JmsTest-Config.xml" })
public class SGETaskMonitoringStateHandlerTemplateTest {
    /**
     * Logger
     */
    private final static Logger LOG = Logger.getLogger(SGETaskMonitoringStateHandlerTemplateTest.class);
    /**
     * tested instance
     */
    @Resource(name = "task-monitoringStateHandler")
    GenericShellBasedStateHandler stateHandler;

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
    private String errorStreamFile;
    /**
     * name of a file containing output stream of a job execution
     */
    private String outputStreamFile;
    /**
     * Invoker
     */
    private Invoker invoker;
    /**
     * directory holding test data
     */
    private File testDirectory;

    @Before
    public void setUp() throws Exception {
        Preconditions.checkNotNull(System.getProperty(TestProperties.SGE_SHARED_LOCATION), TestProperties.SGE_SHARED_LOCATION + " property not set");
        Preconditions.checkNotNull(System.getProperty(TestProperties.MIF_CLIENT_USER_NAME), TestProperties.MIF_CLIENT_USER_NAME + " property not set");
        Preconditions.checkNotNull(System.getProperty(TestProperties.MIF_CLIENT_PASSWORD), TestProperties.MIF_CLIENT_PASSWORD + " property not set");
        Preconditions.checkNotNull(System.getProperty(TestProperties.MIF_JSCH_PORT), TestProperties.MIF_JSCH_PORT + " property not set");

        sgeSharedDir = new File(System.getProperty(TestProperties.SGE_SHARED_LOCATION));
        userName = System.getProperty(TestProperties.MIF_CLIENT_USER_NAME);
        port = Integer.parseInt(System.getProperty(TestProperties.MIF_JSCH_PORT));

        InvokerFactory invokerFactory = new InvokerFactory().port(port);
        invoker = invokerFactory.createRunAsUserInvoker(userName, System.getProperty(TestProperties.MIF_CLIENT_PASSWORD));
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

    @Test
    public void shouldExecuteQstatOnArunningJob() throws IOException, ExecutionException {
        testDirectory = new File(sgeSharedDir, "TaskMonitoringStateHandler-shouldExecuteQstatOnArunningJob");
        testDirectory.mkdirs();
        testDirectory.deleteOnExit();

        JobAwareFreemarkerTemplateCommandBuilder commandBuilder = stateHandler.getCommandBuilder();

        File sge = new File(testDirectory, "sge.sh");
        FileUtils.copyURLToFile(this.getClass().getResource("/com/mango/mif/sge/sge.sh"), sge);

        // given there is a job running on a grid
        File submitter = new File(testDirectory, "testScriptExecutedOnGrid.sh");
        FileUtils.copyURLToFile(this.getClass().getResource("testScriptExecutedOnGrid.sh"), submitter);
        File scriptFile = new File(testDirectory, "testScriptExecutedOnGrid.sh");

        String qsub = buildQsubCommand(testDirectory, scriptFile);
        InvokerResult result = invoker.execute(qsub);

        if (result.getExitStatus() != 0) {
            fail("Submission of a job failed." + result.getErrorStream());
        }

        String[] matches = match("Your job ([\\d]+)([^\\d].+)?", result.getOutputStream().trim());

        if (matches == null || matches.length < 2) {
            fail("Submission of a job failed." + result.getOutputStream());
            fail("Could not match a job id in qsub results");
        }
        jobId = matches[1];

        Job job = new Job();

        Map<String, String> data = Maps.newHashMap();
        job.setJobId("1000-1000-1000-1000");
        data.put(SGE.SGE_JOB_ID_KEY, jobId);
        job.setData(data);

        ExecutionRequestBuilder requestBuilder = new ExecutionRequestBuilder();
        ExecutionRequest er = requestBuilder.getExecutionRequest();
        job.setExecutionRequest(er);

        //when a command built from the template is executed
        commandBuilder.setJob(job);
        String command = commandBuilder.getCommand();
        InvokerResult invokerResult = invoker.execute(command);

        if (invokerResult.getExitStatus() != 0) {
            fail("The command execution failed. Error stream:" + invokerResult.getErrorStream());
        }
        //then it returns qstat results
        SGEOutput sgeOutput = sgeOutputParser.parseStatusMessage(invokerResult.getOutputStream());
        assertEquals(SGEOutput.JOB_PROCESSING, sgeOutput.get(SGEOutput.SGE_COMMAND_RESULT_KEY));

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
     * builds a qsub command
     * @param testDirectory
     * @param scriptFile
     * @return
     * @throws IOException
     */
    private String buildQsubCommand(File testDirectory, File scriptFile) throws IOException {
        return "source sge.sh \ncd \"" + testDirectory.getAbsolutePath()
                + "\"\nqsub -u " + userName + " -e " + errorStreamFile + " -o " + outputStreamFile + " -cwd " + scriptFile.getAbsolutePath() + "\n";
    }
}
