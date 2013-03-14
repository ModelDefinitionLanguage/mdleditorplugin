/* File			:  GenericShellBasedJobRunnerFactory.java
 * Project		:  MIFServer
 * Created on	:  3 Jul 2012
 */
package com.mango.mif.connector.runner.impl;

import java.io.IOException;
import java.util.List;

import javax.jms.IllegalStateException;

import org.apache.log4j.Logger;
import org.springframework.core.io.Resource;

import com.google.common.base.Preconditions;
import com.mango.mif.connector.domain.Job;
import com.mango.mif.connector.runner.api.JobRunner;
import com.mango.mif.connector.runner.api.SCXMLDriver;
import com.mango.mif.core.exec.ExecutionException;
import com.mango.mif.core.exec.Invoker;
import com.mango.mif.core.exec.invoker.InvokerFactory;
import com.mango.mif.utils.encrypt.Encrypter;

/**
 * @version $Revision: $ as of $Date: $
 * <p>SVN Entry : $HeadURL: $
 * <p>SVN ID    : $Id: $
 * <p>Last edited by : $Author: $
 */
/**
 * @author mrogalski
 * A factory responsible for creating shell based job runners
 */
public abstract class GenericShellBasedJobRunnerFactory extends BasicJobRunnerFactory implements JobInvokerProvider {
    /**
     * Logger
     */
    public final static Logger LOG = Logger.getLogger(GenericShellBasedJobRunnerFactory.class);
    /**
     * Invoker factory
     */
    private InvokerFactory invokerFactory;
    /**
     * SCXML definition file
     */
    private Resource scxmlDefinition;
    /**
     * Encrypter
     */
    protected Encrypter encrypter;

    @Override
    protected abstract GenericShellBasedJobRunner createJobRunner();
    /**
     * Method injected by spring
     * @return new instances of drivers
     */
    public abstract SCXMLDriver createDriver();
    /**
     * 
     * @return new instances of a list of state handlers
     */
    public abstract List<DefaultStateHandler> getStateHandlers();

    @Override
    public JobRunner createJobRunner(Job job) {
        GenericShellBasedJobRunner jobRunner = (GenericShellBasedJobRunner)super.createJobRunner(job);
        buildDriver(jobRunner, job);
        return jobRunner;
    }
    /**
     * Registers handlers
     */
    void buildDriver(GenericShellBasedJobRunner jobRunner, Job job) {
        List<DefaultStateHandler> stateHandlers = getStateHandlers();
        Preconditions.checkNotNull(stateHandlers, "Driver factory incorrectly configured - no state handlers registered.");
        Preconditions.checkNotNull(invokerFactory, "Invoker factory not set.");
        Preconditions.checkNotNull(scxmlDefinition, "Driver factory incorrectly configured - SCXML definition not set.");

        SCXMLDriver driver = createDriver();
        try {
            driver.setSCXMLDocument(scxmlDefinition.getURL());
        } catch (IOException e) {
            LOG.error(e);
            throw new RuntimeException("Could not load JobRunner SCXML definition file",e);
        } catch (IllegalStateException e) {
            //the driver is defined as a prototype, so the exception should never be thrown, if so it is runtime exception
            LOG.error(e);
            throw new RuntimeException("Could not initialize the driver.",e);
        }
        for(DefaultStateHandler handler : stateHandlers) {
            handler.setJobRunner(jobRunner);
            driver.registerHandler(handler);
        }
        jobRunner.setDriver(driver);
        try {
            jobRunner.setInvoker(createInvoker(job));
        } catch (ExecutionException e) {
            LOG.error(e);
            throw new RuntimeException("Could not create invoker.",e);
        }
    }
    /* (non-Javadoc)
	 * @see com.mango.mif.connector.runner.impl.JobInvokerProvider#createInvoker(com.mango.mif.connector.domain.Job)
	 */
    public synchronized Invoker createInvoker(Job job) throws ExecutionException {
        Invoker result = invokerFactory.createRunAsUserInvoker(
			job.getUserName(),
			job.getPassword()
		);
        job.setInvoker(result);
		return result;
    }

    /**
     * @param scxmlDefinition the scxmlDefinition to set
     */
    public void setScxmlDefinition(Resource scxmlDefinition) {
        this.scxmlDefinition = scxmlDefinition;
    }

    /**
     * @param invokerFactory the invokerFactory to set
     */
    public void setInvokerFactory(InvokerFactory invokerFactory) {
        this.invokerFactory = invokerFactory;
    }

    /**
     * @param encrypter the encrypter to set
     */
    public void setEncrypter(Encrypter encrypter) {
        this.encrypter = encrypter;
    }
}
