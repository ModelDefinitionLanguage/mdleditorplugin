package com.mango.mif.utils;

import static org.junit.Assert.assertTrue;

import java.io.File;

import org.apache.commons.exec.CommandLine;
import org.apache.commons.io.FileUtils;
import org.apache.log4j.Logger;
import org.junit.Before;
import org.junit.Test;

import com.google.common.base.Preconditions;
import com.google.common.io.Files;
import com.mango.mif.core.exec.InvokerResult;
import com.mango.mif.core.exec.invoker.InvokerHelper;

/**
 * Command Exec class unit test
 * @author mrogalski
 *
 */
public class CommandExecTest {

    /**
     * Logger
     */
    public static final Logger LOG = Logger.getLogger(CommandExecTest.class);
    /**
     * Test script file for windows
     */
    private static final String WIN_SCRIPT = "run.bat";
    /**
     * Test script file for linux
     */
    private static final String LINUX_SCRIPT = "run.sh";
    /**
     * working directory in which the script will be executed
     */
    private File workingDirectory;
    /**
     * script file that will be executed
     */
    private File scriptFile;
    /**
     * user name
     */
    private String userName;
    /**
     * port
     */
    private int port = 22;


    //=========================================================================
    //=========================================================================
    //=========================================================================

    @Before
    public void setUp() throws Exception {
        workingDirectory = Files.createTempDir();
        workingDirectory.deleteOnExit();
        String scriptFileName = getScriptFile();
        scriptFile = new File(workingDirectory,scriptFileName);
        FileUtils.copyURLToFile(this.getClass().getResource(scriptFileName), scriptFile);
        LOG.info("Working directory:" + workingDirectory.getAbsolutePath());
        LOG.info("Script file:" + scriptFile.getAbsolutePath());

    }

    //=========================================================================
    //=========================================================================
    //=========================================================================

    @Test
    public void shouldExecuteExternalProcessSynchronously() throws CommandExecException {
        CommandExec cExec = new CommandExec();
        cExec.setCommandLine(buildCommand());
        cExec.setWorkingDirectory(workingDirectory);

        cExec.executeSync();

        assertTrue("Result file does not exist.", new File(workingDirectory,"run-result.txt").exists());
    }

    @Test
    public void shouldExecuteExternalProcessAsynchronously() throws CommandExecException {
        CommandExec cExec = new CommandExec();
        cExec.setCommandLine(buildCommand());
        cExec.setWorkingDirectory(workingDirectory);

        cExec.executeAsync();

        cExec.waitFor();

        assertTrue("Result file does not exist.", new File(workingDirectory,"run-result.txt").exists());
    }

    @Test
    public void shouldRunAsUserOnUnix() throws CommandExecException {
        if(!System.getProperty("os.name").contains("Win")) {

            userName = System.getProperty(TestProperties.MIF_ALTER_CLIENT_USER_NAME);
            String encryptedPassword = System.getProperty(TestProperties.MIF_ALTER_CLIENT_PASSWORD);
            String portProp = System.getProperty(TestProperties.MIF_JSCH_PORT);
            Preconditions.checkNotNull(userName, TestProperties.MIF_ALTER_CLIENT_USER_NAME + " property is not set.");
            Preconditions.checkNotNull(encryptedPassword, TestProperties.MIF_ALTER_CLIENT_PASSWORD + " property is not set.");
            Preconditions.checkNotNull(port, TestProperties.MIF_JSCH_PORT + " property is not set.");
            port = Integer.parseInt(portProp);
            CommandExec cExec = new CommandExec();
            CommandLine commandLine = new CommandLine("ls -l");
            cExec.setCommandLine(commandLine);
            cExec.setWorkingDirectory(new File("/etc"));
            cExec.setUserName(userName);
            cExec.setEncryptedPassword(encryptedPassword);
            cExec.setPort(port);
            InvokerResult results = cExec.runAsUser();
            assertTrue(InvokerHelper.success(results));
            assertTrue("Expected file in output not found, the command output was: " + results.getStdout(),results.getStdout().indexOf("passwd") > 0);
        }
    }

    /**
     * 
     * @return test script to invoke depending on operating system
     */
    private String getScriptFile() {
        String os = System.getProperty("os.name");
        LOG.info("System OS is: " + os);
        if(os.contains("Win")) {
            return WIN_SCRIPT;
        }
        return LINUX_SCRIPT;
    }
    /**
     * Builds command for windows
     * @return
     */
    private CommandLine buildCommandWindows() {
        CommandLine commandLine = new CommandLine(scriptFile);
        return commandLine;
    }
    /**
     * Builds command for linux
     * @return
     */
    private CommandLine buildCommandLinux() {
        CommandLine commandLine = new CommandLine("sh");
        commandLine.addArgument(scriptFile.getName());
        return commandLine;
    }
    /**
     * Builds command
     * @return
     */
    private CommandLine buildCommand() {
        if(System.getProperty("os.name").contains("Win")) {
            return buildCommandWindows();
        } else {
            return buildCommandLinux();
        }
    }
}
