/* File			:  SGEJobCancellationRequestHandler.java
 * Project		:  MIFServer
 * Created on	:  Aug 30, 2012
 */
package com.mango.mif.sge;

import java.util.List;

import com.google.common.base.Preconditions;
import com.mango.mif.connector.domain.Job;
import com.mango.mif.core.exec.ExecutionException;
import com.mango.mif.core.exec.Invoker;
import com.mango.mif.core.exec.InvokerResult;
import com.mango.mif.core.exec.invoker.InvokerFactory;
import com.mango.mif.core.exec.template.JobAwareFreemarkerTemplateCommandBuilder;

/**
 * @version $Revision: $ as of $Date: $
 * <p>SVN Entry : $HeadURL: $
 * <p>SVN ID    : $Id: $
 * <p>Last edited by : $Author: $
 * 
 * A class responsible for canceling a single job running on SGE
 * 
 */
public class SGEJobCancellationRequestHandler {
    
    
    private InvokerFactory invokerFactory;
    
    private JobAwareFreemarkerTemplateCommandBuilder commandBuilder;
    
    public String cancelChildSGEJobs(Job job, List<String> jobIds) throws ExecutionException {
        Preconditions.checkNotNull(jobIds, "SGE job ids is must be specified");
        Preconditions.checkNotNull(job, "Job context must be set");
        Preconditions.checkNotNull(commandBuilder,"Command builder is not set");
        Preconditions.checkNotNull(invokerFactory,"Invoker factory is not set");
        Invoker invoker = getInvoker(job);
        commandBuilder.setJob(job);
        commandBuilder.setVariable("sgeJobIDs", jobIds);
        String command = commandBuilder.getCommand();
        InvokerResult invokerResult = invoker.execute(command);
       
        if(invokerResult.getExitStatus()!=0) {
            throw new ExecutionException("Error when cancelling the SGE jobs " + jobIds + "\n Error Stream: " + invokerResult.getErrorStream() + "\n Output Stream: " + invokerResult.getOutputStream());
        }
        return invokerResult.getOutputStream();
    }
    
    private Invoker getInvoker(Job job) throws ExecutionException {
        return invokerFactory.createRunAsUserInvoker(
        							job.getUserName(),
        							job.getPassword()
        						);
    }

    public void setCommandBuilder(JobAwareFreemarkerTemplateCommandBuilder commandBuilder) {
        this.commandBuilder = commandBuilder;
    }
    
    public JobAwareFreemarkerTemplateCommandBuilder getCommandBuilder() {
        return commandBuilder;
    }
    
    public void setInvokerFactory(InvokerFactory invokerFactory) {
        this.invokerFactory = invokerFactory;
    }
    
    public InvokerFactory getInvokerFactory() {
        return invokerFactory;
    }
}
