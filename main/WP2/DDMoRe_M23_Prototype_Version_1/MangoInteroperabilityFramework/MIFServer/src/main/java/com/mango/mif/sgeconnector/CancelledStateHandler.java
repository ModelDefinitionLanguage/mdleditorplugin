package com.mango.mif.sgeconnector;

import com.google.common.base.Preconditions;
import com.mango.mif.connector.runner.impl.BaseGenericShellBasedStateHandler;
import com.mango.mif.connector.runner.impl.StateHandlerException;
import com.mango.mif.core.exec.ExecutionException;
import com.mango.mif.core.exec.InvokerResult;
import com.mango.mif.core.exec.template.JobAwareFreemarkerTemplateCommandBuilder;
import com.mango.mif.sge.QstatJobOutput;
import com.mango.mif.sge.SGEOutput;
import com.mango.mif.sge.SGEOutputParser;
import com.mango.mif.utils.CommandExec;

/**
 * Handler responsible for handling cancelled processing state
 * @author mrogalski
 *
 */
public class CancelledStateHandler extends BaseGenericShellBasedStateHandler {

    /**
     * SGE job id
     */
    private String jobID;
    /**
     * Command builder used to create a qdel command
     */
    private JobAwareFreemarkerTemplateCommandBuilder qdelCommandBuilder;
    /**
     * the output parser.  The SGE version, of course.
     */
    private SGEOutputParser sgeOutputParser;

    /**
     * Default constructor
     * @param state
     */
    public CancelledStateHandler(String state) {
        super(state);
    }

    @Override
    protected void doProcessing() throws StateHandlerException {
        Preconditions.checkNotNull(qdelCommandBuilder, "Cancel command builder must be set.");
        Preconditions.checkNotNull(sgeOutputParser, "The SGE output parser must be set.");

        jobID = getSgeJobID();

        if (jobID == null) {
            LOG.warn("Job " + getJob().getJobId() + " might have not yet been submitted to a grid");
            return;
        }

        try {
            qdelCommandBuilder.setJob(job);
        } catch (ExecutionException e) {
            throw new StateHandlerException("Error preparing command builders used by cancelled state hanlder", e);
        }
        
        try {
            cancelSgeJob();
        } catch (ExecutionException e) {
            LOG.error(e);
            throw new StateHandlerException("Cancellation of job failed.", e);
        }

    }

    /**
     * Cancels a job running on grid and its children
     * @throws ExecutionException
     * 
     */
    void cancelSgeJob() throws ExecutionException {
        SGEOutput output = null;
        output = invokeQdel(qdelCommandBuilder.getCommand());
        if (!SGEOutput.JOB_DELETED.equals(output.get(SGEOutput.SGE_COMMAND_RESULT_KEY))) {
            LOG.error("Job " + jobID + " was not deleted, because it was not found.");
        }

        LOG.info("Job " + jobID + " deleted from SGE.");
    }
    /**
     * executes deletion command
     * @return
     * @throws ExecutionException
     */
    private SGEOutput invokeQdel(String command) throws ExecutionException {
        InvokerResult result = getInvoker().execute(command);
        LOG.debug("qdel ERR stream: " + result.getErrorStream());
        LOG.debug("qdel STD stream: " + result.getOutputStream());
        if(result.getExitCode() != CommandExec.SUCCESS_EXIT_VALUE) {
            return sgeOutputParser.parseDeleteMessage(result.getErrorStream());
        }
        return sgeOutputParser.parseDeleteMessage(result.getOutputStream());
    }


    /**
     * Checks if given Qstat output is of a child job of parent job's qstat output
     * @param childQstat
     * @param parentQstat
     * @return
     */
    boolean isChildJob(SGEOutput childQstat, SGEOutput parentQstat) {
        String parentWd = parentQstat.get(QstatJobOutput.WORKING_DIRECTORY.getToken());
        String childWd = childQstat.get(QstatJobOutput.WORKING_DIRECTORY.getToken());
        if (parentWd == null || childWd == null)
            return false;
        return childWd.startsWith(parentWd);
    }

    /**
     * SGE Job id
     * @return
     */
    protected String getSgeJobID() {
        return job.getSGEJobID();
    }

    public void setQdelCommandBuilder(JobAwareFreemarkerTemplateCommandBuilder qdelCommandBuilder) {
        this.qdelCommandBuilder = qdelCommandBuilder;
    }

    public void setSgeOutputParser(SGEOutputParser sgeOutputParser) {
        this.sgeOutputParser = sgeOutputParser;
    }

    public JobAwareFreemarkerTemplateCommandBuilder getQdelCommandBuilder() {
        return qdelCommandBuilder;
    }

    public SGEOutputParser getSgeOutputParser() {
        return sgeOutputParser;
    }

}
