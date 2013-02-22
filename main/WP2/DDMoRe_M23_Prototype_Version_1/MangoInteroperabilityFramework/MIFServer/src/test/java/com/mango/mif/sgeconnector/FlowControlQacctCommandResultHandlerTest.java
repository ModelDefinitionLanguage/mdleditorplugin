/* File			:  FlowControlQacctCommandResultHandlerTest.java
 * Project		:  MIFServer
 * Created on	:  Jul 27, 2012
 */
package com.mango.mif.sgeconnector;

import static org.junit.Assert.*;
import static org.mockito.Matchers.any;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

import org.junit.Before;
import org.junit.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import com.mango.mif.connector.domain.Job;
import com.mango.mif.core.exec.ExecutionException;
import com.mango.mif.core.exec.InvokerResult;
import com.mango.mif.sge.SGEOutput;
import com.mango.mif.sge.SGEOutputParser;


/**
 * @version $Revision: $ as of $Date: $
 * <p>SVN Entry : $HeadURL: $
 * <p>SVN ID    : $Id: $
 * <p>Last edited by : $Author: $
 * 
 * Tests FlowControlQacctCommandResultHandler
 */
public class FlowControlQacctCommandResultHandlerTest {
    /**
     * Submit result handler
     */
    private SGECommandResultHandler resultHandler;
    /**
     * Output parser
     */
    @Mock SGEOutputParser outputParser;
    /**
     * job
     */
    @Mock Job job;
    /**
     * job
     */
    @Mock InvokerResult invokerResult;
    /**
     * Success event
     */
    String SUCCESS_EVENT = "SUCCESS.EVENT";
    /**
     * Success event
     */
    String FAILURE_EVENT = "FAILURE.EVENT";
    /**
     * @throws java.lang.Exception
     */
    @Before
    public void setUp() throws Exception {
        MockitoAnnotations.initMocks(this);
        resultHandler = new FlowControlQacctCommandResultHandler(SUCCESS_EVENT,FAILURE_EVENT);
        resultHandler.setOutputParser(outputParser);
        resultHandler.setJob(job);
    }

    @Test(expected=NullPointerException.class)
    public void shouldThrowExceptionIfOutputParserNotSet() throws ExecutionException {
        resultHandler.setOutputParser(null);
        resultHandler.handle(invokerResult);
    }

    @Test
    public void shouldNotThrowExceptionIfQacctReturnsNonZeroValue() throws ExecutionException {
        when(invokerResult.getExitCode()).thenReturn(1); //exit code
        SGEOutput output = mock(SGEOutput.class);
        when(outputParser.parseAccountingMessage((String)any())).thenReturn(output);
        resultHandler.handle(invokerResult);
    }

    @Test
    public void shouldResultInSuccessEventIfJobProcessingFinished() throws ExecutionException {
        when(invokerResult.getExitCode()).thenReturn(0); //exit code
        SGEOutput output = mock(SGEOutput.class);
        when(output.get(SGEOutput.SGE_COMMAND_RESULT_KEY)).thenReturn(SGEOutput.JOB_FINISHED);
        when(outputParser.parseAccountingMessage((String)any())).thenReturn(output);
        resultHandler.handle(invokerResult);
        assertEquals(SUCCESS_EVENT, resultHandler.getStateExitEvent());
    }
    
    @Test
    public void shouldResultInFailureEventIfJobProcessingHasntFinished() throws ExecutionException {
        when(invokerResult.getExitCode()).thenReturn(1); //exit code
        SGEOutput output = mock(SGEOutput.class);
        when(output.get(SGEOutput.SGE_COMMAND_RESULT_KEY)).thenReturn(SGEOutput.JOB_NOT_FOUND);
        when(outputParser.parseAccountingMessage((String)any())).thenReturn(output);
        resultHandler.handle(invokerResult);
        assertEquals(FAILURE_EVENT, resultHandler.getStateExitEvent());
    }

}
