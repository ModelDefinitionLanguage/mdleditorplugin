/* File         :  ApacheCommonsExecTest.java
 * Project      :  MangoInteroperabilityFramework
 * Created on   :  28 Mar 2012
 */
package com.mango.org.apache.commons.exec;

import static org.junit.Assert.*;

import java.io.File;
import java.io.IOException;

import org.apache.commons.exec.CommandLine;
import org.apache.commons.exec.DefaultExecuteResultHandler;
import org.apache.commons.exec.DefaultExecutor;
import org.apache.commons.exec.ExecuteException;
import org.apache.commons.exec.ExecuteWatchdog;
import org.apache.commons.exec.Executor;
import org.apache.commons.io.FileUtils;
import org.apache.log4j.Logger;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import com.google.common.io.Files;

/**
 * 
 * Test checking usability of Apache Commons Exec
 * 
 * TODO bring back watchdog timeout and watchdog destroy tests and investigate why they don't work on linux
 * 
 * @version $Revision: $ as of $Date: $
 * <p>SVN Entry : $HeadURL: $
 * <p>SVN ID    : $Id: $
 * <p>Last edited by : $Author: $
 */
public class ApacheCommonsExecTest {
    /**
     * Logger
     */
    public static final Logger LOG = Logger.getLogger(ApacheCommonsExecTest.class);
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

    @After
    public void tearDown() throws Exception {
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
    
    private CommandLine buildCommandWindows() {
        CommandLine commandLine = new CommandLine(scriptFile);
        return commandLine;
    }
    
    private CommandLine buildCommandLinux() {
        CommandLine commandLine = new CommandLine("sh");
        commandLine.addArgument(scriptFile.getName());
        return commandLine;
    }
    
    private CommandLine buildCommand() {
        if(System.getProperty("os.name").contains("Win")) {
            return buildCommandWindows();
        } else {
            return buildCommandLinux();
        }
    }
    @Test
    public void testExecuteScript() throws ExecuteException, IOException {
        assertTrue("Script file does not exist.", scriptFile.exists());
        CommandLine commandLine = buildCommand();
        Executor executor = new DefaultExecutor();
        executor.setExitValue(0);
        executor.setWorkingDirectory(workingDirectory);
        
        int exitValue = executor.execute(commandLine);
        
        CustomExecuteResultHandler executionResultHandler = new CustomExecuteResultHandler();
        
        executionResultHandler.onProcessComplete(exitValue);

        assertTrue("Result file does not exist.", new File(workingDirectory,"run-result.txt").exists());
    }
    
    /**
     * A custom execution handler
     * @author mrogalski
     *
     */
    public class CustomExecuteResultHandler extends DefaultExecuteResultHandler {
        ExecuteWatchdog watchdog;
        
        public CustomExecuteResultHandler() {
            
        }
        
        public CustomExecuteResultHandler(ExecuteWatchdog watchdog) {
                this.watchdog = watchdog;
        }
        
        public void onProcessFailed(ExecuteException e){
                if(watchdog != null && watchdog.killedProcess()) {
                    LOG.error("Command execution has timed out");
                }
                else {
                    LOG.error("Command : ",e);
                }
                super.onProcessFailed(e);
            }
    }
}
