/* File			:  RScriptExec.java
 * Project		:  MangoInteroperabilityFramework
 * Created on	:  29 Mar 2012
 */
package com.mango.mif.rconnectorcli.internal;

import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.IOException;
import java.util.Set;

import org.apache.commons.exec.CommandLine;
import org.apache.commons.exec.DefaultExecuteResultHandler;
import org.apache.commons.exec.DefaultExecutor;
import org.apache.commons.exec.ExecuteException;
import org.apache.commons.exec.ExecuteWatchdog;
import org.apache.commons.exec.PumpStreamHandler;
import org.apache.commons.io.FileUtils;
import org.apache.log4j.Logger;

import com.google.common.base.Preconditions;
import com.mango.mif.domain.Parameter;
import com.mango.mif.domain.ParameterType;

/**
 * 
 * Instances Are responsible for executing R script using R executable specified in Config object.
 * 
 * @version $Revision: $ as of $Date: $
 * <p>SVN Entry : $HeadURL: $
 * <p>SVN ID    : $Id: $
 * <p>Last edited by : $Author: $
 */
public class RScriptExec {
    /**
     * Logger
     */
    private final static Logger LOG = Logger.getLogger(RScriptExec.class);
    /**
     * Contains Execution environment properties
     */
    private RScriptConfig config;
    /**
     * working directory
     */
    private File workingDirectory;
    /**
     * script file that should be executed
     */
    private File scriptFile;
    /**
     * Object holding handle to process running R
     */
    private ExecuteWatchdog watchdog;
    /**
     * Handles external process results
     */
    private static int SUCCESS_EXIT_VALUE = 0;
    /**
     * Result Handler
     */
    private DefaultExecuteResultHandler executeResultHandler;
    /**
     * Executor
     */
    private DefaultExecutor executor;
    /**
     * Command line that is going to be executed
     */
    private CommandLine commandLine;
    /**
     * A flag indicating that R processing has been started.
     */
    private volatile boolean submitted = false;
    /**
     * Holds error output stream from R 
     */
    private ByteArrayOutputStream errout;
    /**
     * Holds standard output stream from R 
     */
    private ByteArrayOutputStream stdout;
    /**
     * Starts external process
     * @throws RScriptExecException
     */
    public synchronized void submit() throws RScriptExecException {
        Preconditions.checkArgument(!submitted, "R script already executed.");
        init();
        try {
            LOG.info("Executing command: " + commandLine.toString());
            LOG.info("Working directory: " + executor.getWorkingDirectory());
            executor.execute(commandLine,executeResultHandler);
        } catch (ExecuteException e) {
            throw new RScriptExecException("Error when executing R command",e);
        } catch (IOException e) {
            throw new RScriptExecException("Error when executing R command",e);
        }
        submitted = true;
    }
    
    /**
     * Waits for R process to complete (blocks the invoking thread)
     * @throws RScriptExecException 
     */
    public synchronized void waitFor() throws RScriptExecException {
        Preconditions.checkArgument(submitted, "R script has not yet been submitted.");
        try {
            executeResultHandler.waitFor();

            FileUtils.writeStringToFile(new File(executor.getWorkingDirectory(),"MIF_R_ERROR_STREAM"), getErrorOutput());
            FileUtils.writeStringToFile(new File(executor.getWorkingDirectory(),"MIF_R_OUTPUT_STREAM"), getStandardOutput());
        } catch (InterruptedException e) {
            LOG.error(e);
            throw new RScriptExecException("Error when waiting for external process to finish.", e);
        } catch (IOException e) {
            LOG.error(e);
            throw new RScriptExecException("Error when waiting for external process to finish.", e);
        }
    }
    
    public RScriptConfig getConfig() {
        return config;
    }

    public void setConfig(RScriptConfig config) {
        this.config = config;
    }

    public File getWorkingDirectory() {
        return workingDirectory;
    }
    
    public void setWorkingDirectory(File workingDirectory) {
        this.workingDirectory = workingDirectory;
    }

    
    public File getScriptFile() {
        return scriptFile;
    }

    
    public void setScriptFile(File scriptFile) {
        this.scriptFile = scriptFile;
    }

    /**
     * Initiates objects involved in execution
     */
    private void init() {
        watchdog = new ExecuteWatchdog(ExecuteWatchdog.INFINITE_TIMEOUT);
        executeResultHandler = new CustomExecuteResultHandler(watchdog);
        createExecutor();
        createCommandLine();
        
    }
    
    /**
     * Creates executor used to create R process
     * @return
     */
    private void createExecutor() {
        Preconditions.checkNotNull(workingDirectory, "Working directory not set.");
        Preconditions.checkNotNull(watchdog, "Watchdog not yet created.");
        executor = new DefaultExecutor();
        executor.setExitValue(SUCCESS_EXIT_VALUE);
        executor.setWorkingDirectory(workingDirectory);
        executor.setWatchdog(watchdog);
        stdout = new ByteArrayOutputStream();
        errout = new ByteArrayOutputStream();
        PumpStreamHandler psh = new PumpStreamHandler(stdout,errout);
        executor.setStreamHandler(psh);
    }
    /**
     * create R execution command line
     * @return
     */
    private void createCommandLine() {
        Preconditions.checkNotNull(config.getExecutable(), "R executable not set");
        Preconditions.checkNotNull(scriptFile, "R script file not set");
        Set<String> attributes = config.getAttributes();
        commandLine = new CommandLine(config.getExecutable());
        commandLine.addArguments(attributes.toArray(new String[attributes.size()]));
        commandLine.addArgument(scriptFile.getName());
    }
    /**
     * 
     * @return standard output content
     */
    public String getStandardOutput() {
        return stdout.toString();
    }
    /**
     * 
     * @return error output content
     */
    public String getErrorOutput() {
        return errout.toString();
    }

    /**
     * A custom execution handler
     * @author mrogalski
     *
     */
    public class CustomExecuteResultHandler extends DefaultExecuteResultHandler {
        /**
         * Watchdog object
         */
        private ExecuteWatchdog watchdog;
        /**
         * Default constructor
         * @param watchdog
         */
        public CustomExecuteResultHandler(ExecuteWatchdog watchdog) {
                Preconditions.checkNotNull(watchdog, "Watchdog must not be null.");
                this.watchdog = watchdog;
        }
        @Override
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
