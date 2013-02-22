/* File			:  MockJobRunner.java
 * Project		:  MangoInteroperabilityFramework
 * Created on	:  13 Mar 2012
 */
package com.mango.mif.connector.runner.impl;

import java.util.concurrent.Callable;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.Future;

import javax.annotation.Resource;

import org.apache.log4j.Logger;
import org.springframework.core.task.AsyncTaskExecutor;

import com.mango.mif.connector.runner.domain.JobMessage;

/**
 * Mocks up the Job Runner processing methods
 * 
 * @version $Revision: $ as of $Date: $
 * <p>SVN Entry : $HeadURL: $
 * <p>SVN ID    : $Id: $
 * <p>Last edited by : $Author: $
 * 
 */
public class MockJobRunner extends AbstractJobRunner {

    /**
     * Logger
     */
    public static Logger LOG = Logger.getLogger(MockJobRunner.class);
    
    /**
     * State handlers
     */
    public Callable<String> cancelHandler,
                             startHandler;

    /**
     * State handler to be used by the job runner to execute its state handlers
     */
    @Resource(name="stateHandlersExecutor")
    protected AsyncTaskExecutor stateHandlersExecutor;
    
    @Override
    public void cancelProcessing() {
        LOG.debug("Cancelling processing");
        Future<String> future = stateHandlersExecutor.submit(cancelHandler);
        //the cancel is handled synchronously
        try {
            future.get();
        } catch (InterruptedException e) {
            LOG.error(e);
        } catch (ExecutionException e) {
            LOG.error(e);
        }
    }
    
    @Override
    public void startProcessing() {
        LOG.debug("Starting processing");
        Future<String> future = stateHandlersExecutor.submit(startHandler);
      //the start processing is handled synchronously
        try {
            future.get();
        } catch (InterruptedException e) {
            LOG.error(e);
        } catch (ExecutionException e) {
            LOG.error(e);
        }
    }
    
    @Override
    public JobMessage call() throws Exception {
        JobMessage callMessage = new JobMessage();
        startProcessing();
        return callMessage;
    }

}
