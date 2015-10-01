/*******************************************************************************
 * Copyright (C) 2015 Mango Solutions Ltd - All rights reserved.
 ******************************************************************************/
package eu.ddmore.convertertoolbox.systemtest;

import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.Map;
import java.util.concurrent.TimeUnit;

import org.apache.commons.exec.CommandLine;
import org.apache.commons.exec.DefaultExecuteResultHandler;
import org.apache.commons.exec.DefaultExecutor;
import org.apache.commons.exec.ExecuteWatchdog;
import org.apache.commons.exec.PumpStreamHandler;
import org.apache.commons.io.FileUtils;
import org.apache.commons.io.output.TeeOutputStream;
import org.apache.commons.lang.time.StopWatch;
import org.apache.log4j.Logger;

import com.google.common.base.Preconditions;
import com.google.common.collect.Maps;

/**
 * Instances of this class are responsible for executing given command.
 */
public class CommandRunner {
    private static final Logger LOG = Logger.getLogger(CommandRunner.class);
    private static final String STDOUT_FILE_EXT = "stdout";
    private static final String STDERR_FILE_EXT = "stderr";
    private static final String PID_FILE_EXT = "PID";
    private String name = "execution";
    private File workingDirectory;
    private CommandLine commandLine;
    private Long processTimeout = TimeUnit.SECONDS.toMillis(30);
    private boolean dryRun = false;
    
    public CommandRunner setName(String name) {
        this.name = name;
        return this;
    }

    public CommandRunner setWorkingDirectory(File workingDirectory) {
        this.workingDirectory = workingDirectory;
        return this;
    }
    
    public CommandRunner setCommand(String command) {
        commandLine = CommandLine.parse(command);
        return this;
    }
    
    public CommandRunner setProcessTimeout(Long processTimeout) {
        this.processTimeout = processTimeout;
        return this;
    }
    
    public CommandRunner setDryRun(boolean dryRun) {
        this.dryRun = dryRun;
        return this;
    }
    
    public File getStdOut() {
        Preconditions.checkState(workingDirectory!=null, "Working Directory must be set before standard output file path can be produced");
        Preconditions.checkState(name!=null, "Working Directory must be set before standard output file path can be produced");
        return new File(workingDirectory, name + "." + STDOUT_FILE_EXT);
    }
    
    public File getStdErr() {
        Preconditions.checkState(workingDirectory!=null, "Working Directory must be set before standard error file path can be produced");
        Preconditions.checkState(name!=null, "Working Directory must be set before standard error file path can be produced");
        return new File(workingDirectory, name + "." + STDERR_FILE_EXT);
    }
    
    public void run() throws Exception {
        Preconditions.checkNotNull(name, "Name of the executor can't be null.");
        Preconditions.checkNotNull(workingDirectory, "Working directory can't be null.");
        Preconditions.checkNotNull(commandLine, "Command to be executed can't be null.");
        LOG.debug(String.format("Executing command %s in %s", commandLine, workingDirectory));
        Map<String, String> env = Maps.newHashMap();
        env.putAll(System.getenv());

        DefaultExecutor executor = createCommandExecutor();
        executor.setWorkingDirectory(workingDirectory);
        File stdoutFile = getStdOut();
        File stderrFile = getStdErr();
        File pidFile = new File(workingDirectory, name + "." + PID_FILE_EXT);
        try (BufferedOutputStream stdoutOS = new BufferedOutputStream(new TeeOutputStream(new FileOutputStream(stdoutFile), System.out));
                    BufferedOutputStream stderrOS = new BufferedOutputStream(new TeeOutputStream(new FileOutputStream(stderrFile),
                            System.err))) {
                PumpStreamHandler pumpStreamHandler = new PumpStreamHandler(stdoutOS, stderrOS);
                executor.setStreamHandler(pumpStreamHandler);
                StopWatch stopWatch = new StopWatch();
                stopWatch.start();
                DefaultExecuteResultHandler resultHandler = new DefaultExecuteResultHandler();
                try {
                    if (dryRun) {
                        LOG.debug("Skipping execution.");
                    } else {
                        executor.execute(commandLine, env, resultHandler);
                        monitorProgress(executor, resultHandler, pidFile);
                    }
                } finally {
                    stopWatch.stop();
                    LOG.info(String.format("Execution of %s took %s s.", commandLine,
                        TimeUnit.MILLISECONDS.toSeconds(stopWatch.getTime())));
                }
        } catch (Exception e) {
            throw new Exception(String.format("Error when executing %s.\n STDOUT: [%s]\n STDERR: [%s] ", commandLine,
                FileUtils.readFileToString(stdoutFile), FileUtils.readFileToString(stderrFile)), e);
        }
    }

    /**
     * This method performs monitoring of the external process. Since WatchDog can't be relied on this method actively polls
     * for running process and enforces process destruction if timeout is reached.
     * This approach is even suggested by https://commons.apache.org/proper/commons-exec/apidocs/org/apache/commons/exec/ExecuteWatchdog.html
     * @param pidFile 
     * @param externalProcessInput 
     */
    private void monitorProgress(DefaultExecutor executor, DefaultExecuteResultHandler resultHandler, File pidFile) throws Exception {
        boolean monitor = true;
        long waitedSoFar = 0l;
        long step = TimeUnit.SECONDS.toMillis(20);
        while (monitor) {
            waitedSoFar += step;
            resultHandler.waitFor(step);
            if (resultHandler.hasResult()) {
                break;
            }
            if (waitedSoFar > processTimeout) {
                LOG.error("Attempting to destroy external process...");
                /* We invoke destroyProcess method, but this is not guaranteed to work and sometimes results in a detachment from the external process.
                 * There is also no easy way of sending SIGINT signal to an external process on Windows platform hence we can't guarantee that
                 * the external process actually stops.
                 * 
                 * Here we can only give some time to an external process to shut down. We accept this resource leak just because
                 * in this particular case even if the Rscript being run was killed underlying NONMEM execution would still run anyway
                 * (since DDMoRe connectors do not support cancellation).
                 */
                executor.getWatchdog().destroyProcess();
                if (waitedSoFar > (processTimeout + step)) {
                    monitor = false;
                    LOG.error("The external process did not stop using Commons Exec.");
                    killProcess(pidFile);
                }
            }
        }
        if (executor.getWatchdog().killedProcess()) {
            throw new IllegalStateException("The process timed out.");
        } else {
            if (executor.isFailure(resultHandler.getExitValue())) {
                throw new Exception("External process exited with non-zero exit value.");
            }
        }
    }

    private void killProcess(File pidFile) {
        String pid;
        try {
            pid = FileUtils.readFileToString(pidFile);
        } catch (IOException e1) {
            throw new RuntimeException(String.format("Could not read process PID file %s.", pidFile));
        }
        String killCommand = String.format("powershell Stop-Process %s", pid);
        LOG.info(String.format("Attempting to kill process using command [%s].", killCommand));
        try {
            DefaultExecutor executor = createCommandExecutor();
            int exitCode = executor.execute(CommandLine.parse(killCommand));
            LOG.info(String.format("Kill command returned %s", exitCode));
        } catch (Exception e) {
            throw new RuntimeException(String.format("Could not stop process with PID [%s].", pid));
        }
    }

    private DefaultExecutor createCommandExecutor() {
        DefaultExecutor executor = new DefaultExecutor();
        executor.setExitValue(0);
        ExecuteWatchdog watchdog = new ExecuteWatchdog(ExecuteWatchdog.INFINITE_TIMEOUT);
        executor.setWatchdog(watchdog);
        return executor;
    }
}
