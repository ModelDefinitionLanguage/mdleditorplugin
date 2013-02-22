/**
 * Copyright Mango Solutions, 2012.
 * <p>SVN Entry: $HeadURL: $
 * <p>SVN ID: $Id: $
 * <p>Last edited by : $Author$
 * @author Tony Bamford &lt;tbamford@mango-solutions.com&gt;
 * @version $Revision$ as of on $Date$
 */
package com.mango.mif.connector.runner.impl;

import java.io.File;
import java.util.concurrent.TimeUnit;

import com.google.common.annotations.VisibleForTesting;
import com.google.common.base.Preconditions;
import com.google.common.base.Stopwatch;
import com.mango.mif.core.exec.Invoker;

/**
 * The Shell Based Retrieve (State) Handler.  This is responsible for copying files from the
 * grid share directory to the MIF output directory.
 */
public class ShellBasedRetrieveHandler extends GenericShellBasedStateHandler {

    private String hiddenMifOutputDirectoryName = ".MIF";

    private String hiddenMifOutputDirectoryPath;

    private String gridJobOutputFileName;

    private String gridJobErrorFileName;

    private String gridJobFinalStatusFileName;

    private String gridModifiedFileListOuptutFileName;

    /**
     * @param state
     */
    public ShellBasedRetrieveHandler(String state) {
        super(state);
    }

    /**
     * @param state
     * @param exitEvent
     */
    public ShellBasedRetrieveHandler(String state, String exitEvent) {
        super(state, exitEvent);
    }

    /**
     * Most of the hard work is delegated to the retrieveHandler.sh shell script, invoked via the
     * retrieveHandler.ftl template.  We don't even create directories here, because if we did, they
     * may have the wrong mode (creating them via the shell script will give them a mode influenced
     * by the user's umask).
     */
    @Override
    protected void doProcessing() throws StateHandlerException {

        String command = null;
        try {
            Preconditions.checkNotNull(invokerResultHandler, "Invoker result handler not set");
            Preconditions.checkNotNull(commandBuilder, "Command builder not set");
            Preconditions.checkNotNull(jobRunner, "The job runner is not set");
            Preconditions.checkNotNull(jobRunner.getInvoker(), "The job runner's invoker is not set");

            Preconditions.checkNotNull(gridJobOutputFileName, "The grid output file name is not set");
            Preconditions.checkNotNull(gridJobErrorFileName, "The grid error file name is not set");

            Invoker invoker = jobRunner.getInvoker();

            String gridSharedDirectoryPath = job.getGridJobDirectory().getAbsolutePath();

            File hiddenMifDirectory = new File(job.getRequestOutputDirectory(), hiddenMifOutputDirectoryName);
            hiddenMifOutputDirectoryPath = hiddenMifDirectory.getAbsolutePath();

            // Place the output directory path and the hidden MIF directory path into the context so the
            // template can go about copying the output and error files from the grid directory into the
            // hidden directory
            commandBuilder.setVariable("mifOutputDirectory", job.getRequestOutputDirectory());
            commandBuilder.setVariable("mifHiddenDirectory", hiddenMifDirectory.getAbsolutePath());
            commandBuilder.setVariable("mifModifiedListFilename", gridModifiedFileListOuptutFileName);

            commandBuilder.setJob(job);

            commandBuilder.setVariable("gridOutputFileName", gridJobOutputFileName);
            commandBuilder.setVariable("gridErrorFileName", gridJobErrorFileName);
            commandBuilder.setVariable("gridJobFinalStatusFileName", gridJobFinalStatusFileName);
            commandBuilder.setVariable("sharedDirectory", gridSharedDirectoryPath);

            // All that is left to do now is build the command and execute it.
            command = commandBuilder.getCommand();

            invokerResultHandler.setJob(job);

            Stopwatch watch = new Stopwatch().start();
            invokerResultHandler.handle(invoker.execute(command));
            LOG.info("PrepareHandler (COPY FROM GRIDSHARE) took: " + watch.elapsedTime(TimeUnit.SECONDS));

        } catch (Exception e) {
            if (command != null) {
                LOG.error("State Handler Excecution FAILED while executing command\n" + command);
            }
            throw new StateHandlerException("Retrieving state handler processing failed", e);
        }
    }

    public String getHiddenMifOutputDirectoryName() {
        return hiddenMifOutputDirectoryName;
    }

    @VisibleForTesting
    String getHiddenMifOutputDirectoryPath() {
        return hiddenMifOutputDirectoryPath;
    }

    public void setHiddenMifOutputDirectoryName(String hiddenMifOutputDirectoryName) {
        this.hiddenMifOutputDirectoryName = hiddenMifOutputDirectoryName;
    }

    public String getGridJobOutputFileName() {
        return gridJobOutputFileName;
    }

    public void setGridJobOutputFileName(String gridOutputFileName) {
        this.gridJobOutputFileName = gridOutputFileName;
    }

    public String getGridJobErrorFileName() {
        return gridJobErrorFileName;
    }

    public void setGridJobErrorFileName(String gridErrorFileName) {
        this.gridJobErrorFileName = gridErrorFileName;
    }

    public String getGridModifiedFileListOuptutFileName() {
        return gridModifiedFileListOuptutFileName;
    }

    public void setGridModifiedFileListOuptutFileName(String gridModifiedListFileName) {
        this.gridModifiedFileListOuptutFileName = gridModifiedListFileName;
    }

    public String getGridJobFinalStatusFileName() {
        return gridJobFinalStatusFileName;
    }

    public void setGridJobFinalStatusFileName(String gridJobFinalStatusFileName) {
        this.gridJobFinalStatusFileName = gridJobFinalStatusFileName;
    }
}
