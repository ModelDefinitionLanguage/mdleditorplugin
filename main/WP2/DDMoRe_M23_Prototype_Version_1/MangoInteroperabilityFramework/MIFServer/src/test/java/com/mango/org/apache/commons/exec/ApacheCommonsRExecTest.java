/* File			:  ApacheCommonsExecTest.java
 * Project		:  MangoInteroperabilityFramework
 * Created on	:  28 Mar 2012
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
import org.junit.Ignore;
import org.junit.Test;

import com.google.common.io.Files;
import com.mango.mif.connector.TestsHelper;


/**
 * 
 * Test checking usability of Apache Commons Exec to execute R
 * 
 * @version $Revision: $ as of $Date: $
 * <p>SVN Entry : $HeadURL: $
 * <p>SVN ID    : $Id: $
 * <p>Last edited by : $Author: $
 */
@Ignore("Local R execution is not yet being targetted")
public class ApacheCommonsRExecTest {
    /**
     * Logger
     */
    public static final Logger LOG = Logger.getLogger(ApacheCommonsRExecTest.class);
    /**
     * R test script
     */
    private static final String DATASET = "data.csv";
    /**
     * working directory in which the script will be executed
     */
    private File workingDirectory;
    /**
     * script file that will be executed
     */
    private File scriptFile;
    /**
     * Dataset file
     */
    private File datasetFile;
    
    @Before
    public void setUp() throws Exception {
        workingDirectory = Files.createTempDir();
        workingDirectory.deleteOnExit();
        LOG.info("Working directory:" + workingDirectory.getAbsolutePath());
    }

    @After
    public void tearDown() throws Exception {
    }

    /**
     * Prepares input data for R
     * @throws IOException 
     */
    private void prepareRInputs(String rScript) throws IOException {
        scriptFile = new File(workingDirectory,rScript);
        datasetFile = new File(workingDirectory,DATASET);
        FileUtils.copyURLToFile(this.getClass().getResource(rScript), scriptFile);
        FileUtils.copyURLToFile(this.getClass().getResource(DATASET), datasetFile);
    }
    
    /**
     * Executes R script 
     * @throws ExecuteException
     * @throws IOException
     */
    @Test
    public void testExecuteRScript() throws ExecuteException, IOException {
        prepareRInputs("test.R");
        LOG.info("Script file:" + scriptFile.getAbsolutePath());
        LOG.info("Script file:" + datasetFile.getAbsolutePath());
        assertTrue("Script file does not exist.", scriptFile.exists());
        File rExec = TestsHelper.getRExecutable();
        LOG.info("Rscript executable:" + rExec.getAbsolutePath());
        CommandLine commandLine = new CommandLine(rExec);
        commandLine.addArgument("--vanilla");
        commandLine.addArgument("test.R");
        Executor executor = new DefaultExecutor();
        executor.setExitValue(0);
        executor.setWorkingDirectory(workingDirectory);
        
        int exitValue = executor.execute(commandLine);
        
        CustomExecuteResultHandler executionResultHandler = new CustomExecuteResultHandler();
        
        executionResultHandler.onProcessComplete(exitValue);

        assertTrue("Result file does not exist.", new File(workingDirectory,"result-test.csv").exists());
    }
    
    /**
     * Executes Long Running R script and after 4 seconds it kills the process. The test uses watchdog to trigger killing of the process.
     * @throws ExecuteException
     * @throws IOException
     * @throws InterruptedException 
     */
    @Test
    public void testKillLongRunningRScript() throws ExecuteException, IOException, InterruptedException {
        prepareRInputs("testLongRunningTask.R");
        assertTrue("Script file does not exist.", scriptFile.exists());
        String rExec = TestsHelper.getRExecutable().getName();
        CommandLine commandLine = new CommandLine(rExec);
        ExecuteWatchdog watchdog = new ExecuteWatchdog(4000);
        commandLine.addArgument("--vanilla");
        commandLine.addArgument("testLongRunningTask.R");
        Executor executor = new DefaultExecutor();
        executor.setExitValue(0);
        executor.setWorkingDirectory(workingDirectory);
        executor.setWatchdog(watchdog);
        
        CustomExecuteResultHandler executionResultHandler = new CustomExecuteResultHandler(watchdog);
        
        executor.execute(commandLine,executionResultHandler);
        
        Thread.sleep(5000);
        
        watchdog.destroyProcess();

        assertFalse("Result file should not exist.", new File(workingDirectory,"result-testLongRunningTask.csv").exists());
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
