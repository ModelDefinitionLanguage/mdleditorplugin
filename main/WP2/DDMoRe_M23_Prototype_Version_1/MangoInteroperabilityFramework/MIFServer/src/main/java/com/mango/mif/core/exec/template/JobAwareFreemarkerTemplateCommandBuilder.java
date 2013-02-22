/**
 * Copyright Mango Solutions, 2012.
 * <p>SVN Entry: $HeadURL: $
 * <p>SVN ID: $Id: $
 * <p>Last edited by : $Author$
 * @author Tony Bamford &lt;tbamford@mango-solutions.com&gt;
 * @version $Revision$ as of on $Date$
 */
package com.mango.mif.core.exec.template;

import java.util.Map;

import com.google.common.base.Preconditions;
import com.mango.mif.connector.domain.Job;
import com.mango.mif.core.exec.ExecutionException;
import com.mango.mif.domain.ExecutionRequest;

/**
 * As the name implies, this freemarker template command builder is aware of jobs.
 * The (only) reason this class exists (thus far) is to provide one central point at which
 * information from the execution request attributes can be transferred to the command
 * builder - i.e at the point at which the job is set (the job having access to the
 * aforementioned execution request attributes)
 */
public class JobAwareFreemarkerTemplateCommandBuilder extends FreemarkerTemplateCommandBuilder {

    public JobAwareFreemarkerTemplateCommandBuilder() throws ExecutionException {
        super();
    }

    public JobAwareFreemarkerTemplateCommandBuilder(Map<String, Object> map) throws ExecutionException {
        super(map);
    }

    /**
     * This set job function is the only reason this class exists.  We live firmly in the Server,
     * because Jobs simply don't exist in MIFClient.  Not only do we push the job into the freemarker
     * context, but we also further initialise the context with values from the request attributes,
     * together with the submit and grid host preamble.
     * 
     * @param job The job to set within the freemarker context (and a lot else besides)
     * @throws ExecutionException If we can't set stuff.
     */
    public void setJob(Job job) throws ExecutionException {
        Preconditions.checkNotNull(job, "The job cannot be null");

        super.setVariable(CONTEXT_NAME_JOB, job);
        ExecutionRequest executionRequest = job.getExecutionRequest();

        populateCommandBuilderContext(executionRequest.getRequestAttributes(),
                executionRequest.getGridHostPreamble(),
                executionRequest.getSubmitHostPreamble());
    }
}
