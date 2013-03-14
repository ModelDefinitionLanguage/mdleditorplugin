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

import com.google.common.base.Preconditions;
import com.google.common.base.Stopwatch;
import com.mango.mif.connector.domain.Job;
import com.mango.mif.core.exec.Invoker;
import com.mango.mif.core.exec.InvokerResult;
import com.mango.mif.core.exec.invoker.InvokerHelper;
import com.mango.mif.core.exec.template.JobAwareFreemarkerTemplateCommandBuilder;
import com.mango.mif.utils.JobFileResolver;

/**
 * The Generic Shell Based Prepare (State) Handler has the following three responsibilities:
 * <br>1. Use a template to create the script that will submit our stuff to the grid
 * <br>2. Expand the prepareHandler.ftl template and run the prepareHandler.sh script
 * files need to be copied back to the request folder.
 */
public class ShellBasedPrepareHandler extends BaseGenericShellBasedStateHandler {

    private String submitScriptName = "MIF-submitter.sh";
    private JobFileResolver gridJobDirectoryResolver;

    // As we are effectively expanding two templates - one for the "grid submit host"
    // and one for prepareHandler.ftl, we need two command builders.  Both must be
    // job aware.
    private JobAwareFreemarkerTemplateCommandBuilder gridSubmitScriptBuilder;

    /**
     * @param state
     */
    public ShellBasedPrepareHandler(String state) {
        super(state);
    }

    /**
     * @param state
     * @param exitEvent
     */
    public ShellBasedPrepareHandler(String state, String exitEvent) {
        super(state, exitEvent);
    }

    /**
     * Generate the script, run prepareHandler.
     */
    @Override
    protected void doProcessing() throws StateHandlerException {

        try {
            Preconditions.checkNotNull(commandBuilder, "Command builder not set");
            Preconditions.checkNotNull(gridSubmitScriptBuilder, "The grid submit script builder cannot be null at this point");
            Preconditions.checkNotNull(jobRunner, "The job runner cannot be null");
            Preconditions.checkNotNull(jobRunner.getInvoker(), "The job runner's invoker is not set");
            Preconditions.checkNotNull(invokerResultHandler, "Invoker result handler not set");

            // STEP 1.
            // Hopefully the grid submit script builder can get everything it needs from the job,
            // as everything else (paths to shell scripts) can be injected via spring.
            Invoker invoker = jobRunner.getInvoker();
            job.setGridJobDirectory(getGridJobDirectory(job));
            commandBuilder.setJob(job);
            String command = commandBuilder.getCommand();

            Stopwatch watch = new Stopwatch().start();
            InvokerResult results = invoker.execute(command);
            LOG.info("PrepareHandler (COPY TO GRIDSHARE) took: " + watch.elapsedTime(TimeUnit.SECONDS));

            if (InvokerHelper.failed(results)) {
                LOG.error("Failed command: " + command);
                LOG.error("exit status: " + InvokerHelper.exitStatus(results));
                LOG.error("stdout: " + InvokerHelper.output(results));
                LOG.error("stderr: " + InvokerHelper.errors(results));
                throw new StateHandlerException("ShellBasedPrepareHandler bad exit status from handling script. " + results.getStderr());
            }

            // STEP 2.
            gridSubmitScriptBuilder.setJob(job);

            String contents = gridSubmitScriptBuilder.getCommand();
            InvokerHelper invokerHelper = new InvokerHelper(invoker);
            File submitScript = new File(job.getGridJobDirectoryMifHiddenDir(), submitScriptName);
            if (!invokerHelper.createFileFromContents(contents, submitScript.getAbsolutePath(), 0755)) {
                throw new StateHandlerException("Failed to create submit file \""
                        + submitScript.getAbsolutePath()
                        + "\". See logs for details");
            }

        } catch (StateHandlerException she) {
            throw she;
        } catch (Exception e) {
            LOG.error(e);
            throw new StateHandlerException("GenericShellBasedPrepareHandler processing failed: " + e.getMessage(), e);
        }
    }
    
    protected String getGridJobDirectory(Job job) {
        return gridJobDirectoryResolver.getPath(job);
    }
    

    public JobAwareFreemarkerTemplateCommandBuilder getGridSubmitScriptBuilder() {
        return gridSubmitScriptBuilder;
    }

    public void setGridSubmitScriptBuilder(JobAwareFreemarkerTemplateCommandBuilder gridScriptBuilder) {
        this.gridSubmitScriptBuilder = gridScriptBuilder;
    }

    public String getSubmitScriptName() {
        return submitScriptName;
    }

    public void setSubmitScriptName(String submitScriptName) {
        this.submitScriptName = submitScriptName;
    }
    
    public void setGridJobDirectoryResolver(JobFileResolver gridJobDirectoryResolver) {
        this.gridJobDirectoryResolver = gridJobDirectoryResolver;
    }
    
    public JobFileResolver getGridJobDirectoryResolver() {
        return gridJobDirectoryResolver;
    }
}
