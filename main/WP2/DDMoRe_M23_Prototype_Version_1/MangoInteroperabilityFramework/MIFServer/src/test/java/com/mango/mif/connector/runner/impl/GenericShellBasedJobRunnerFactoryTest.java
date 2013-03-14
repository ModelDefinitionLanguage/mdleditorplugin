/* File			:  GenericShellBasedJobRunnerFactoryTest.java
 * Project		:  MIFServer
 * Created on	:  4 Jul 2012
 */
package com.mango.mif.connector.runner.impl;

import static org.mockito.Matchers.anyString;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import org.junit.Before;
import org.junit.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.core.io.Resource;

import com.mango.mif.connector.domain.Job;
import com.mango.mif.connector.runner.api.SCXMLDriver;
import com.mango.mif.core.exec.ExecutionException;
import com.mango.mif.core.exec.invoker.InvokerFactory;
import com.mango.mif.domain.ExecutionRequest;

/**
 * @version $Revision: $ as of $Date: $
 * <p>SVN Entry : $HeadURL: $
 * <p>SVN ID    : $Id: $
 * <p>Last edited by : $Author: $
 */
/**
 * @author mrogalski
 * Tests job runner factory that creates Generic Shell based job runners
 */
public class GenericShellBasedJobRunnerFactoryTest {
    /**
     * tested instance
     */
    private GenericShellBasedJobRunnerFactory jobRunnerFactory;
    /**
     * invoker factory
     */
    @Mock InvokerFactory invokerFactory;
    /**
     * scxml definition
     */
    @Mock Resource scxmlDefinition;
    /**
     * job runner
     */
    @Mock GenericShellBasedJobRunner jobRunner;
    /**
     * job runner
     */
    @Mock SCXMLDriver scxmlDriver;
    
    @Mock Job job;
    /**
     * state handlers
     */
    List<DefaultStateHandler> stateHandlers = new ArrayList<DefaultStateHandler>();

    @Before
    public void setUp() throws IOException {
        MockitoAnnotations.initMocks(this);
        jobRunnerFactory = new GenericShellBasedJobRunnerFactory() {

            @Override
            public List<DefaultStateHandler> getStateHandlers() {
                return stateHandlers;
            }

            @Override
            protected GenericShellBasedJobRunner createJobRunner() {
                return jobRunner;
            }

            @Override
            public SCXMLDriver createDriver() {
                return scxmlDriver;
            }
        };
        jobRunnerFactory.setInvokerFactory(invokerFactory);
        jobRunnerFactory.setScxmlDefinition(scxmlDefinition);
        when(scxmlDefinition.getURL()).thenReturn(GenericShellBasedJobRunnerFactoryTest.class.getResource("/META-INF/JobRunnerWithFailover.scxml"));
        when(job.getExecutionRequest()).thenReturn(mock(ExecutionRequest.class));
        when(jobRunner.getJobId()).thenReturn("JOB_ID");
    }

    @Test(expected = NullPointerException.class)
    public void shouldThrownExceptionIfSCXMLDefinitionisNotSet() {
        jobRunnerFactory.setScxmlDefinition(null);
        jobRunnerFactory.buildDriver(jobRunner,job);
    }

    @Test(expected = NullPointerException.class)
    public void shouldThrowExceptionIfStateHandlersNotAvailable() {
        this.stateHandlers = null;
        jobRunnerFactory.buildDriver(jobRunner,job);
    }

    @Test(expected = RuntimeException.class)
    public void shouldThrowExceptionIfInvokerCreationFails() throws ExecutionException {
        when(invokerFactory.createRunAsUserInvoker(anyString(), anyString())).thenThrow(new ExecutionException("Test errror"));
        when(invokerFactory.createRunAsNavplusInvoker()).thenThrow(new ExecutionException("Test errror"));
        jobRunnerFactory.buildDriver(jobRunner,job);
    }
}
