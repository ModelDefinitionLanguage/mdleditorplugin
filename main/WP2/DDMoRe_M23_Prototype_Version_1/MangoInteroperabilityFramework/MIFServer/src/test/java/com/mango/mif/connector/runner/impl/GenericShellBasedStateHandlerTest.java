/* File			:  GenericShellBasedStateHandlerTest.java
 * Project		:  MIFServer
 * Created on	:  29 Jun 2012
 */
package com.mango.mif.connector.runner.impl;

import static org.mockito.Matchers.any;
import static org.mockito.Mockito.doThrow;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import org.junit.Before;
import org.junit.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import com.mango.mif.connector.domain.Job;
import com.mango.mif.connector.runner.api.SCXMLDriver;
import com.mango.mif.core.exec.ExecutionException;
import com.mango.mif.core.exec.Invoker;
import com.mango.mif.core.exec.InvokerResult;
import com.mango.mif.core.exec.template.JobAwareFreemarkerTemplateCommandBuilder;
import com.mango.mif.exception.MIFException;
import com.mango.mif.managers.JobManagementService;
import com.mango.mif.utils.encrypt.Encrypter;


/**
 * @version $Revision: $ as of $Date: $
 * <p>SVN Entry : $HeadURL: $
 * <p>SVN ID    : $Id: $
 * <p>Last edited by : $Author: $
 */
/**
 * @author mrogalski
 * 
 * tests generic shell based state handler
 *
 */
public class GenericShellBasedStateHandlerTest {
    /**
     * Tested instance
     */
    private GenericShellBasedStateHandler stateHandler;
    /**
     * invoker
     */
    @Mock Invoker invoker;
    /**
     * result handler
     */
    @Mock JobProcessingAwareInvokerResultHandler invokerResultHandler;
    /**
     * Command builder
     */
    @Mock JobAwareFreemarkerTemplateCommandBuilder commandBuilder;
    /**
     * Job runner
     */
    @Mock GenericShellBasedJobRunner jobRunner;
    /**
     * Encrypter
     */
    @Mock Encrypter encrypter;
    /**
     * Driver
     */
    @Mock SCXMLDriver driver;
    /**
     * job repository
     */
    @Mock JobManagementService jobManagementService;

    @Before
    public void setUp() {
        MockitoAnnotations.initMocks(this);
        stateHandler = new GenericShellBasedStateHandler(SCXMLDriver.NULL_STATE, SCXMLDriver.NULL_EVENT);
        stateHandler.setCommandBuilder(commandBuilder);
        stateHandler.setEncrypter(encrypter);
        when(jobRunner.getInvoker()).thenReturn(invoker);
        stateHandler.setJobRunner(jobRunner);
        stateHandler.setInvokerResultHandler(invokerResultHandler);
        stateHandler.setSCXMLDriver(driver);
        stateHandler.setJobManagementService(jobManagementService);
    }

    @Test(expected=NullPointerException.class)
    public void shouldThrowExceptionIfCommandBuilderNotSet() throws StateHandlerException {
        stateHandler.setCommandBuilder(null);
        stateHandler.doProcessing();
    }

    @Test(expected=NullPointerException.class)
    public void shouldThrowExceptionIfInvokerIsNotSet() throws StateHandlerException {
        when(jobRunner.getInvoker()).thenReturn(null);
        stateHandler.doProcessing();
    }

    @Test(expected=NullPointerException.class)
    public void shouldThrowExceptionIfInvokerResultHanlderIsNotSet() throws StateHandlerException {
        stateHandler.setInvokerResultHandler(null);
        stateHandler.doProcessing();
    }

    @Test(expected=StateHandlerException.class)
    public void shouldThrowExceptionIfInvokerFailsToExecuteACommand() throws ExecutionException, StateHandlerException {
        when(invoker.execute((String) any())).thenThrow(new ExecutionException("Error message"));
        stateHandler.doProcessing();
    }

    @Test(expected=StateHandlerException.class)
    public void shouldThrowExceptionIfCommandBuilderFailsToBuildUpACommmand() throws ExecutionException, StateHandlerException, MIFException {
        when(commandBuilder.getCommand()).thenThrow(new ExecutionException("Error message"));
        stateHandler.doProcessing();
    }

    @Test(expected=StateHandlerException.class)
    public void shouldThrowExceptionIfInvokerResultHanlderFailsToHandleResults() throws StateHandlerException, ExecutionException {

        doThrow(new ExecutionException("Error message")).when(invokerResultHandler).handle((InvokerResult) any());
        stateHandler.doProcessing();
    }

    @Test(expected=StateHandlerException.class)
    public void shouldPersistJobOnlyOnComplete() throws StateHandlerException, ExecutionException {
        doThrow(new ExecutionException("Error message")).when(invokerResultHandler).handle((InvokerResult) any());
        stateHandler.doProcessing();
        stateHandler.complete();
        verify(jobManagementService.saveJob((Job) any()),times(1));
    }
}
