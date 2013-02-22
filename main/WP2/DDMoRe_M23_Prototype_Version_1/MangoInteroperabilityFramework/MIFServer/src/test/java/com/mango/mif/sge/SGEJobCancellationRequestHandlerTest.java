/* File			:  SGEJobCancellationRequestHandlerTest.java
 * Project		:  MIFServer
 * Created on	:  Aug 30, 2012
 */
package com.mango.mif.sge;

import static org.junit.Assert.assertEquals;
import static org.mockito.Matchers.any;
import static org.mockito.Matchers.anyString;
import static org.mockito.Mockito.when;

import java.util.List;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.runners.MockitoJUnitRunner;
import static org.mockito.Mockito.*;

import com.google.common.collect.Lists;
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
 * Tests SGEJobCancellationRequestHandler
 */
@RunWith(MockitoJUnitRunner.class)
public class SGEJobCancellationRequestHandlerTest  {

    @Mock Job job;
    
    @Mock InvokerFactory invokerFactory;
    
    @Mock Invoker invoker;
    
    @Mock JobAwareFreemarkerTemplateCommandBuilder commandBuilder;
    
    @Mock InvokerResult invokerResult;
    
    SGEJobCancellationRequestHandler handler;
    
    List<String> sgeJobIds = Lists.newArrayList("1","2","3");
    @Before
    public void setUp() throws Exception {
        this.handler = new SGEJobCancellationRequestHandler();
        handler.setCommandBuilder(commandBuilder);
        handler.setInvokerFactory(invokerFactory);
        when(invokerFactory.createRunAsUserInvoker(anyString(), anyString())).thenReturn(invoker);
        when(invoker.execute((String)any())).thenReturn(invokerResult);
    }

    @Test(expected = NullPointerException.class)
    public void shouldThrowExceptionWhenCommandBuilderIsNotSet() throws ExecutionException {
        handler.setCommandBuilder(null);
        handler.cancelChildSGEJobs(job, sgeJobIds);
    }

    @Test(expected = NullPointerException.class)
    public void shouldThrowExceptionWhenInvokerFafctoryIsNotSet() throws ExecutionException {
        handler.setInvokerFactory(null);
        handler.cancelChildSGEJobs(job, sgeJobIds);
    }
    
    @Test(expected = NullPointerException.class)
    public void shouldThrowExceptionWhenChildJobsIdsAreNull() throws ExecutionException {
        handler.cancelChildSGEJobs(job, null);
    }

    @Test(expected = NullPointerException.class)
    public void shouldThrowExceptionWhenJobIsNotSet() throws ExecutionException {
        handler.cancelChildSGEJobs(null, sgeJobIds);
    }
    
    @Test(expected = ExecutionException.class)
    public void shouldThrowExceptionIfExecutionOfTheCancellationScriptFails() throws ExecutionException {
        when(invokerResult.getExitStatus()).thenReturn(1);
        when(invokerResult.getErrorStream()).thenReturn("This is an error");
        handler.cancelChildSGEJobs(job, sgeJobIds);
    }

    @Test
    public void shouldReturnOutputStreamContentWhenCancellationSuccessful() throws ExecutionException {
        String expected = "This is a successful message";
        when(invokerResult.getExitStatus()).thenReturn(0);
        when(invokerResult.getOutputStream()).thenReturn(expected);
        assertEquals(expected,handler.cancelChildSGEJobs(job, sgeJobIds));
        verify(commandBuilder).setJob(job);
        verify(commandBuilder).setVariable("sgeJobIDs",sgeJobIds);
    }

}
