/**
 * Copyright Mango Solutions, 2012.
 * <p>CVS Entry : $Header$
 * <p>Last edited by : $Author$
 * @version $Revision$ as of on $Date$
 */
package com.mango.mif.core.exec.ce;

import java.io.ByteArrayOutputStream;
import java.io.File;

import org.apache.commons.exec.CommandLine;
import org.apache.commons.exec.DefaultExecutor;
import org.apache.commons.exec.PumpStreamHandler;
import org.apache.commons.io.FileUtils;
import org.apache.kahadb.util.ByteArrayInputStream;
import org.apache.log4j.Logger;

import com.mango.mif.core.exec.ExecutionException;
import com.mango.mif.core.exec.Invoker;
import com.mango.mif.core.exec.InvokerResult;

/**
 * Invoke a command in a Commons Exec sort of way.
 */
public class CeInvoker implements Invoker {

    private static final Logger LOG = Logger.getLogger(CeInvoker.class);

    private static final String INVOKER_SHELL = System.getProperty("mango.mif.invoker.shell", "/bin/bash");

    @Override
    public InvokerResult execute(String command) throws ExecutionException {
        return execute(command, null);
    }

    @Override
    public InvokerResult execute(String command, String input) throws ExecutionException {
        InvokerResult results = null;
        try {
            ByteArrayOutputStream stdout = new ByteArrayOutputStream();
            ByteArrayOutputStream stderr = new ByteArrayOutputStream();
            PumpStreamHandler psh;

            if (input != null) {
                ByteArrayInputStream stdin = new ByteArrayInputStream(input.getBytes("UTF8"));
                psh = new PumpStreamHandler(stdout, stderr, stdin);
            } else {
                psh = new PumpStreamHandler(stdout, stderr);
            }

            File temp = null;
            try {
                temp = File.createTempFile(Long.toString(System.currentTimeMillis()), ".sh");
                temp.setExecutable(true);
                FileUtils.writeStringToFile(temp, command);

                //TODO which shell should be used here? ${SHELL} ${GRID_SHELL}? other?
                CommandLine commandLine = CommandLine.parse(INVOKER_SHELL + " " + temp.getAbsolutePath());
                DefaultExecutor exec = new DefaultExecutor();

                // We need this step, otherwise CE throws an exception when a command exits with any non zero value.
                exec.setExitValues(null);
                exec.setStreamHandler(psh);

                int exitCode = 0;
                try {
                    LOG.trace("Commons Exec command: " + commandLine);
                    exitCode = exec.execute(commandLine);
                } catch (Exception e) {
                    // Jonathan says "don't log and throw", but I've seen too many exceptions not logged to chance it.
                    String errorMessage = String.format("Exception using Commons Exec to execute: '%s'", command);
                    LOG.error(errorMessage, e);
                    throw new ExecutionException(errorMessage, e);
                }

                results = new CeInvokerResult(command, stdout.toString(), stderr.toString(), exitCode);

                LOG.trace("Commons Exec stdout: " + stdout);
                LOG.trace("Commons Exec stderr: " + stderr);

            } finally {
                if (temp != null) {
                    FileUtils.deleteQuietly(temp);
                }
            }

        } catch (Exception e) {
            throw new ExecutionException("Caught exception using commons exec to execute \"" + command + "\"", e);
        }

        return results;
    }

}
