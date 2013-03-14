/* File			:  BasicJobInvokerProvider.java
 * Project		:  MIFServer
 * Created on	:  Sep 5, 2012
 */
package com.mango.mif.connector.runner.impl;

import com.mango.mif.connector.domain.Job;
import com.mango.mif.core.exec.ExecutionException;
import com.mango.mif.core.exec.Invoker;
import com.mango.mif.core.exec.invoker.InvokerFactory;

/**
 * @version $Revision: $ as of $Date: $
 * <p>SVN Entry : $HeadURL: $
 * <p>SVN ID    : $Id: $
 * <p>Last edited by : $Author: $
 * 
 * basic implementation of job invoker interface
 */
public class BasicJobInvokerProvider implements JobInvokerProvider {
    /**
     * invoker factory
     */
    private InvokerFactory invokerFactory;

    @Override
    public synchronized Invoker createInvoker(Job job) throws ExecutionException {
        Invoker result = invokerFactory.createRunAsUserInvoker(
        									job.getUserName(),
        									job.getPassword()
        								);
        job.setInvoker(result);
        return result;
    }

    
    public InvokerFactory getInvokerFactory() {
        return invokerFactory;
    }
    
    
    public void setInvokerFactory(InvokerFactory invokerFactory) {
        this.invokerFactory = invokerFactory;
    }
}
