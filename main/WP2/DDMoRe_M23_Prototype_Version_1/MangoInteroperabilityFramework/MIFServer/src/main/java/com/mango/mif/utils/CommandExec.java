/* File			:  CommandExec.java
 * Project		:  MIFServer
 * Created on	:  May 11, 2012
 */
package com.mango.mif.utils;

import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.IOException;
import java.util.UUID;

import org.apache.commons.exec.CommandLine;
import org.apache.commons.exec.DefaultExecuteResultHandler;
import org.apache.commons.exec.DefaultExecutor;
import org.apache.commons.exec.ExecuteException;
import org.apache.commons.exec.ExecuteWatchdog;
import org.apache.commons.exec.PumpStreamHandler;
import org.apache.commons.lang.StringUtils;
import org.apache.log4j.Logger;

import com.google.common.base.Preconditions;
import com.mango.mif.core.exec.ExecutionException;
import com.mango.mif.core.exec.Invoker;
import com.mango.mif.core.exec.InvokerResult;
import com.mango.mif.core.exec.invoker.InvokerFactory;
import com.mango.mif.core.exec.invoker.InvokerHelper;

/**
 * 
 * Class responsible for executing commands. It uses JSCH for the execution.  That's bad really.  It should be told which Invoker to use.
 * 
 * FIXME decide which invoker to use.
 * 
 * @version $Revision: $ as of $Date: $
 * <p>SVN Entry : $HeadURL: $
 * <p>SVN ID    : $Id: $
 * <p>Last edited by : $Author: $
 */
public class CommandExec {
    /**
     * Logger
     */
    protected static final Logger LOG = Logger.getLogger(CommandExec.class);
    /**
     * Default exit value returned by the process on successful execution
     */
    public final static int SUCCESS_EXIT_VALUE = 0;
    /**
     * working directory
     */
    private File workingDirectory;
    /**
     * Object holding handle to process running R
     */
    private ExecuteWatchdog watchdog;
    /**
     * Result Handler
     */
    protected DefaultExecuteResultHandler executeResultHandler;
    /**
     * Executor
     */
    protected DefaultExecutor executor;
    /**
     * Command line that is going to be executed
     */
    protected CommandLine commandLine;
    /**
     * A flag indicating that command has been executed
     */
    private volatile boolean executed = false;
    /**
     * Holds error output stream
     */
    private ByteArrayOutputStream erroutStream;
    /**
     * Holds standard output stream
     */
    private ByteArrayOutputStream stdoutStream;
    /**
     * Holds error output
     */
    private String errOut;
    /**
     * Holds standard output
     */
    private String stdOut;
    /**
     * Success Exit Value from the process
     */
    private int successExitValue = SUCCESS_EXIT_VALUE;
    /**
     * unique ID of the command execution
     */
    private String executionID = UUID.randomUUID().toString();

    /**
     * Exit code of the external process
     */
    private int exitCode;

    /**
     * FOR RUN AS USER
     */
    private String userName;
    private String encryptedPassword;
    private String host;
    private int port = 22;

    //    private JschResults results;

    public synchronized InvokerResult runAsUser() throws CommandExecException {
        Preconditions.checkArgument(StringUtils.isNotBlank(userName), "User name has not been set.");
        Preconditions.checkArgument(StringUtils.isNotBlank(encryptedPassword), "encrypted password has not been set.");
        Preconditions.checkNotNull(commandLine, "Command line has not been set.");

        try {
            InvokerFactory invokerFactory = new InvokerFactory()
            		//.userName(userName)
                    //.clearTextPassword(encryptedPassword)
                    .host(host)
                    .port(port);
            Invoker invoker = invokerFactory.createRunAsUserInvoker(userName, encryptedPassword);
            InvokerHelper helper = new InvokerHelper(invoker);

            String command = "";

            // There is some translation needed of the command string.  JSCH works in the user's home directory.
            // Thus we need to take account of the working directory and move there.
            if (workingDirectory != null) {
                command = "cd " + workingDirectory.getAbsolutePath() + "; ";
            }
            command = command + commandLine.toString();
            InvokerResult results = helper.run(command);
            this.stdOut = results.getStdout();
            this.errOut = results.getStderr();
            this.exitCode = results.getExitStatus();

            return results;
        } catch (ExecutionException ee) {
            throw new CommandExecException(ee.getMessage(), ee.getCause());
        } finally {
            executed = true;
        }
    }

    /**
     * Starts external process
     * @throws CommandExecException
     */
    @Deprecated
    public synchronized void executeAsync() throws CommandExecException {
        Preconditions.checkArgument(!executed, "The command has already been executed.");
        Preconditions.checkNotNull(commandLine, "Command line is not set.");
        init();
        try {
            LOG.info("Executing command: " + commandLine.toString());
            LOG.info("Working directory: " + executor.getWorkingDirectory());
            executor.execute(commandLine, executeResultHandler);
        } catch (ExecuteException e) {
            throw new CommandExecException("Error when executing R command", e);
        } catch (IOException e) {
            throw new CommandExecException("Error when executing R command", e);
        } finally {
            executed = true;
        }
    }

    /**
     * Waits for external process to complete (blocks the invoking thread)
     * @throws CommandExecException
     */
    public synchronized void waitFor() throws CommandExecException {
        Preconditions.checkArgument(executed, "The command has not yet been executed.");
        try {
            executeResultHandler.waitFor();
        } catch (InterruptedException e) {
            LOG.error(e);
            throw new CommandExecException("Error when waiting for external process to finish.", e);
        } finally {
            dumpStreams();
            exitCode = executeResultHandler.getExitValue();
        }
    }

    /**
     * Executes Command synchronously
     * @throws CommandExecException
     */
    public synchronized void executeSync() throws CommandExecException {
        executeAsync();
        waitFor();
    }

    /**
     * Dumps streams to files
     * @throws CommandExecException
     */
    private void dumpStreams() throws CommandExecException {
        this.errOut = this.erroutStream.toString();
        this.stdOut = this.stdoutStream.toString();
    }

    /**
     * Initiates objects involved in execution
     */
    protected void init() {
        watchdog = new ExecuteWatchdog(ExecuteWatchdog.INFINITE_TIMEOUT);
        executeResultHandler = new CustomExecuteResultHandler(watchdog);
        initExecutor();
    }

    /**
     * Creates executor used to create R process
     * @return
     */
    private void initExecutor() {
        Preconditions.checkNotNull(workingDirectory, "Working directory not set.");
        Preconditions.checkNotNull(watchdog, "Watchdog not yet created.");
        executor = new DefaultExecutor();
        executor.setExitValue(successExitValue);
        executor.setWorkingDirectory(workingDirectory);
        executor.setWatchdog(watchdog);
        stdoutStream = new ByteArrayOutputStream();
        erroutStream = new ByteArrayOutputStream();
        PumpStreamHandler psh = new PumpStreamHandler(stdoutStream, erroutStream);
        executor.setStreamHandler(psh);
    }

    /**
     * 
     * @return standard output content
     */
    public String getStandardOutput() {
        return stdOut;
    }

    /**
     * 
     * @return error output content
     */
    public String getErrorOutput() {
        return errOut;
    }

    public void setCommandLine(CommandLine commandLine) {
        this.commandLine = commandLine;
    }

    public void setSuccessExitValue(int successExitValue) {
        this.successExitValue = successExitValue;
    }

    public void setExecutionID(String executionID) {
        Preconditions.checkNotNull(executionID);
        this.executionID = executionID;
    }

    public String getExecutionID() {
        return executionID;
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
        public void onProcessFailed(ExecuteException e) {
            if (watchdog != null && watchdog.killedProcess()) {
                LOG.error("Command " + commandLine.getExecutable() + " execution has timed out");
            }
            else {
                LOG.error("Command : " + commandLine.getExecutable() + " failed.", e);
            }
            super.onProcessFailed(e);
        }
    }

    /**
     * 
     * @return exit value of the external process
     */
    public int getExitCode() {
        Preconditions.checkArgument(executed, "Command has not yet been executed");
        return exitCode;
    }

    /**
     * @param workingDirectory the workingDirectory to set
     */
    public void setWorkingDirectory(File workingDirectory) {
        this.workingDirectory = workingDirectory;
    }

    //=========================================================================
    // JSCH specific stuff
    //=========================================================================
    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getEncryptedPassword() {
        return encryptedPassword;
    }

    public void setEncryptedPassword(String encryptedPassword) {
        this.encryptedPassword = encryptedPassword;
    }

    public String getHost() {
        return host;
    }

    public void setHost(String host) {
        this.host = host;
    }

    /**
     * Set the JSCH port.
     * @param port
     */
    public void setPort(int port) {
        this.port = port;
    }
}