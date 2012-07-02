/* File			:  GridExecTest.java
 * Project		:  MIFServer
 * Created on	:  May 10, 2012
 */
package com.mango.mif.rsgeconnector.internal;

import static org.junit.Assert.assertNotNull;

import java.io.File;
import java.io.IOException;
import java.util.concurrent.CountDownLatch;

import org.apache.commons.io.FileUtils;
import org.junit.Before;
import org.junit.Test;

import com.google.common.base.Preconditions;
import com.google.common.io.Files;
import com.mango.mif.connector.TestsHelper;
import com.mango.mif.utils.TestProperties;
import com.mango.mif.utils.encrypt.EncrypterFactory;

/**
 * 
 * Test the SGE exec
 * 
 * @version $Revision: $ as of $Date: $
 * <p>SVN Entry : $HeadURL: $
 * <p>SVN ID    : $Id: $
 * <p>Last edited by : $Author: $
 */
public class SGEExecTest {

    /**
     * User name with which send the job to a grid
     */
    private String	userName;
    /**
     * User password
     */
    private String userPassword;
    /**
     * SGE user grid directory
     */
    private File sgeUserDir;

    private String sgeShContent;

    private SGEExec gridExec;

    @Before
    public void setUp() throws IOException {
        Preconditions.checkNotNull(System.getProperty("rSGEConnector.sgeSharedLocation"));
        Preconditions.checkNotNull(System.getProperty(TestProperties.MIF_CLIENT_USER_NAME));
        Preconditions.checkNotNull(System.getProperty(TestProperties.MIF_CLIENT_PASSWORD));

        sgeUserDir = new File(System.getProperty("rSGEConnector.sgeSharedLocation"));
        userName = System.getProperty(TestProperties.MIF_CLIENT_USER_NAME);
        userPassword = EncrypterFactory.getEncrypter().decrypt(System.getProperty(TestProperties.MIF_CLIENT_PASSWORD));
        sgeShContent = getSgeConfigAsString();
        gridExec = new SGEExec();

        SGEOutputParser sgeOutputParser = new SGEOutputParser();
        SGEOutputSearcher searcher = new SGEOutputSearcher();

        searcher.setJobAccountingNotFoundRegexp("error: job id \\d+ not found");
        searcher.setJobAccountingRegexp("([^\\s]+)\\s+(.*)");
        searcher.setJobDeleteNotFoundRegexp("does not exist");
        searcher.setJobDeletionRegexp("registered the job \\d+ for deletion");
        searcher.setJobStatusNotFoundRegexp("Following jobs do not exist.*");
        searcher.setJobStatusRegexp("([^:]+):\\s+(.+)");
        searcher.setJobSubmittedExtractJobIdRegexp("Your job ([\\d]+)([^\\d].+)?");

        sgeOutputParser.setSearcher(searcher);
        gridExec.setSgeOutputParser(sgeOutputParser);
    }

    @Test(expected = NullPointerException.class)
    public void shouldThrowExceptionIfWorkingDirectoryIsNotSet() throws SGEExecException {
        gridExec.setUserName(userName);
        gridExec.setUserPassword(userPassword);
        gridExec.setWorkingDirectory(TestsHelper.createTmpDirectory());
        gridExec.submit();
    }

    @Test(expected = NullPointerException.class)
    public void shouldThrowExceptionIfCommandIsNotSet() throws SGEExecException {
        gridExec.setUserName(userName);
        gridExec.setUserPassword(userPassword);
        gridExec.setSubmitCommand("mock command");
        gridExec.submit();
    }

    /**
     * @throws IOException
     * 
     */
    private void copySgeConfigToTestDirectory(File directory) throws IOException {
        File sge = new File(directory, "sge.sh");
        FileUtils.copyURLToFile(this.getClass().getResource("sge.sh"), sge);
    }

    @Test
    public void shouldSubmitAjobToGrid() throws IOException, SGEExecException {

        gridExec.setUserName(userName);
        gridExec.setUserPassword(userPassword);

        File testDirectory = new File(sgeUserDir, "SGEExecTest-shouldSubmitAjobToGrid");
        if (testDirectory.exists())
            FileUtils.deleteDirectory(testDirectory);
        testDirectory.mkdirs();
        //testDirectory.deleteOnExit();

        copySgeConfigToTestDirectory(testDirectory);
        File scriptFile = new File(testDirectory, "test.sh");
        FileUtils.copyURLToFile(this.getClass().getResource("test.sh"), scriptFile);
        scriptFile.setExecutable(true);

        SGESubmitCommandBuilder submitBuilder = new SGESubmitCommandBuilder();
        submitBuilder.setCommandFile(scriptFile).setJobName(scriptFile.getName()).setUser(userName).setQueue("all.q");
        String command = submitBuilder.getCommand();
        File runScript = new File(testDirectory, "run-submit.sh");
        FileUtils.writeStringToFile(runScript, sgeShContent+"\n qsub " + command + "\n");
        runScript.setExecutable(true);
        gridExec.setWorkingDirectory(testDirectory);
        gridExec.setSubmitCommand(runScript.getAbsolutePath());
        gridExec.submit();

        String jobID = gridExec.getJobID();

        assertNotNull(jobID);
    }

    @Test(timeout = 120000)
    public void shouldSubmitScriptToGridAndWait() throws IOException, SGEExecException {

        gridExec.setUserName(userName);
        gridExec.setUserPassword(userPassword);

        File testDirectory = new File(sgeUserDir, "SGEExecTest-shouldSubmitScriptToGridAndWait");
        if (testDirectory.exists())
            FileUtils.deleteDirectory(testDirectory);
        testDirectory.mkdirs();
        testDirectory.deleteOnExit();
        copySgeConfigToTestDirectory(testDirectory);
        File scriptFile = new File(testDirectory, "test.sh");
        FileUtils.copyURLToFile(this.getClass().getResource("test.sh"), scriptFile);
        scriptFile.setExecutable(true);

        // Submission
        SGESubmitCommandBuilder submitBuilder = new SGESubmitCommandBuilder().setCommandFile(scriptFile).setJobName(scriptFile.getName())
                .setUser(userName).setQueue("all.q");
        String command = submitBuilder.getCommand();
        File runScript = new File(testDirectory, "run-submit.sh");
        String submitCommand = sgeShContent+"\n qsub " + command + "\n";
        FileUtils.writeStringToFile(runScript, submitCommand);
        runScript.setExecutable(true);
        gridExec.setWorkingDirectory(scriptFile.getParentFile());
        gridExec.setSubmitCommand(submitCommand);
        gridExec.submit();

        String jobID = gridExec.getJobID();
        assertNotNull(jobID);
        // Polling
        SGEStatusCommandBuilder pollBuilder = new SGEStatusCommandBuilder().setJobID(jobID);
        command = pollBuilder.getCommand();
        runScript = new File(testDirectory, "run-poll.sh");
        String pollCommand = sgeShContent+"\n qstat " + command + "\n";
        FileUtils.writeStringToFile(runScript, pollCommand);
        runScript.setExecutable(true);
        gridExec.setStatusCommand(pollCommand);

        //Accounting/Summary
        runScript = new File(testDirectory, "run-finish.sh");
        String acctCommand = sgeShContent+"\n" + "qacct -j " + jobID + "\n";
        FileUtils.writeStringToFile(runScript, acctCommand);
        runScript.setExecutable(true);
        gridExec.setAccountingCommand(acctCommand);

        gridExec.setWorkingDirectory(testDirectory);
        gridExec.poll();

        gridExec.dumpStreams();

        assertNotNull(jobID);
    }

    @Test(timeout = 120000)
    public void shouldSubmitScriptToGridAndCancel() throws IOException, SGEExecException, InterruptedException {

        gridExec.setUserName(userName);
        gridExec.setUserPassword(userPassword);
        File testDirectory = new File(sgeUserDir, "SGEExecTest-shouldSubmitScriptToGridAndCancel");
        if (testDirectory.exists())
            FileUtils.deleteDirectory(testDirectory);

        testDirectory.mkdirs();
        testDirectory.deleteOnExit();
        copySgeConfigToTestDirectory(testDirectory);

        File scriptFile = new File(testDirectory, "test-long.sh");
        FileUtils.copyURLToFile(this.getClass().getResource("test-long.sh"), scriptFile);
        scriptFile.setExecutable(true);

        // Submission
        SGESubmitCommandBuilder submitBuilder = new SGESubmitCommandBuilder().setCommandFile(scriptFile).setJobName(scriptFile.getName())
                .setUser(userName).setQueue("all.q");
        String command = submitBuilder.getCommand();
        File runScript = new File(testDirectory, "run-submit.sh");
        String submitCommand = sgeShContent+"\n qsub " + command + "\n";
        FileUtils.writeStringToFile(runScript, submitCommand);
        runScript.setExecutable(true);
        gridExec.setWorkingDirectory(testDirectory);
        gridExec.setSubmitCommand(submitCommand);
        gridExec.submit();

        String jobID = gridExec.getJobID();
        assertNotNull(jobID);
        Thread.sleep(2000);
        // Cancelling
        command = "qdel " + jobID;
        runScript = new File(testDirectory, "run-delete.sh");
        String delCommand = sgeShContent+"\n" + command + "\n";
        FileUtils.writeStringToFile(runScript, delCommand);
        runScript.setExecutable(true);
        gridExec.setCancelCommand(delCommand);

        gridExec.setWorkingDirectory(testDirectory);
        gridExec.cancel();

        assertNotNull(jobID);
    }

    @Test(timeout = 120000)
    public void shouldTryToCancelAJobThatDoesNotExistAndEndGracefuly() throws IOException, SGEExecException, InterruptedException {

        gridExec.setUserName(userName);
        gridExec.setUserPassword(userPassword);
        File testDirectory = new File(sgeUserDir, "SGEExecTest-shouldTryToCancelAJobThatDoesNotExistAndEndGracefuly");
        if (testDirectory.exists())
            FileUtils.deleteDirectory(testDirectory);
        testDirectory.mkdirs();
        testDirectory.deleteOnExit();
        copySgeConfigToTestDirectory(testDirectory);

        String jobID = "0001";
        // Cancelling
        String command = "qdel " + jobID;
        File runScript = new File(testDirectory, "run-delete.sh");
        String delCommand = sgeShContent+"\n" + command + "\n";
        FileUtils.writeStringToFile(runScript, delCommand);
        runScript.setExecutable(true);
        gridExec.setCancelCommand(delCommand);
        gridExec.setJobID(jobID);
        gridExec.setWorkingDirectory(testDirectory);
        gridExec.cancel();

        assertNotNull(jobID);
    }

    @Test
    public void shouldCancelAJobThatIsMonitoredByAnotherThread() throws IOException, SGEExecException, InterruptedException {

        gridExec.setUserName(userName);
        gridExec.setUserPassword(userPassword);
        final CountDownLatch countDown = new CountDownLatch(1);
        File testDirectory = new File(sgeUserDir, "SGEExecTest-shouldCancelAJobThatIsMonitoredByAnotherThread");
        if (testDirectory.exists())
            FileUtils.deleteDirectory(testDirectory);

        testDirectory.mkdirs();
        testDirectory.deleteOnExit();

        copySgeConfigToTestDirectory(testDirectory);

        File scriptFile = new File(testDirectory, "test-long.sh");
        FileUtils.copyURLToFile(this.getClass().getResource("test-long.sh"), scriptFile);
        scriptFile.setExecutable(true);

        // Submission
        SGESubmitCommandBuilder submitBuilder = new SGESubmitCommandBuilder().setCommandFile(scriptFile).setJobName(scriptFile.getName())
                .setUser(userName).setQueue("all.q");
        String command = submitBuilder.getCommand();
        File runScript = new File(testDirectory, "run-submit.sh");
        String submitCommand = sgeShContent+"\n qsub " + command + "\n";
        FileUtils.writeStringToFile(runScript, submitCommand);
        runScript.setExecutable(true);
        gridExec.setWorkingDirectory(testDirectory);
        gridExec.setSubmitCommand(submitCommand);
        gridExec.submit();

        String jobID = gridExec.getJobID();
        assertNotNull(jobID);

        // Polling
        SGEStatusCommandBuilder pollBuilder = new SGEStatusCommandBuilder().setJobID(jobID);
        command = pollBuilder.getCommand();
        runScript = new File(testDirectory, "run-poll.sh");
        String pollCommand = sgeShContent+"\n qstat " + command + "\n";
        FileUtils.writeStringToFile(runScript, pollCommand);
        runScript.setExecutable(true);
        gridExec.setStatusCommand(pollCommand);

        // Accounting/Summary
        runScript = new File(testDirectory, "run-finish.sh");
        String acctCommand = sgeShContent+"\n" + "qacct -j " + jobID + "\n";
        FileUtils.writeStringToFile(runScript, acctCommand);
        runScript.setExecutable(true);
        gridExec.setAccountingCommand(runScript.getAbsolutePath());

        gridExec.setWorkingDirectory(testDirectory);

        Thread poller = new Thread() {

            public void run() {
                try {
                    gridExec.poll();
                    countDown.countDown();
                } catch (SGEExecException e) {
                    throw new RuntimeException(e);
                }
            }
        };
        poller.start();

        // Cancelling
        command = "qdel " + jobID;
        runScript = new File(testDirectory, "run-delete.sh");
        String delCommand = sgeShContent+"\n" + command + "\n";
        FileUtils.writeStringToFile(runScript, delCommand);
        runScript.setExecutable(true);
        gridExec.setCancelCommand(delCommand);

        gridExec.setWorkingDirectory(testDirectory);
        gridExec.cancel();

        countDown.await();

    }
    /**
     * @throws IOException
     * 
     */
    private String getSgeConfigAsString() throws IOException {
        File dir = Files.createTempDir();
        dir.deleteOnExit();
        File sge = new File(dir, "sge.sh");
        try {
            FileUtils.copyURLToFile(this.getClass().getResource("/com/mango/mif/rsgeconnector/internal/sge.sh"), sge);
        } catch (IOException e) {
            throw new RuntimeException("Could not compy SGE config file to working directory.", e);
        }
        return FileUtils.readFileToString(sge);
    }
}
