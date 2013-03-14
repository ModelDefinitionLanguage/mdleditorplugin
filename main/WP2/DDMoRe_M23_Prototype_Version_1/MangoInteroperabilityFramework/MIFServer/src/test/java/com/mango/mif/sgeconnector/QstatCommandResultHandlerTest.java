/* File			:  QstatCommandResultHandlerTest.java
 * Project		:  MIFServer
 * Created on	:  Jul 27, 2012
 */
package com.mango.mif.sgeconnector;

import static org.junit.Assert.*;
import static org.mockito.Matchers.any;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

import java.text.ParseException;
import java.util.Map;
import java.util.Properties;

import org.junit.Before;
import org.junit.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import com.google.common.collect.Maps;
import com.mango.mif.connector.domain.Job;
import com.mango.mif.connector.runner.status.SummaryMessageBuilderTest;
import com.mango.mif.core.exec.ExecutionException;
import com.mango.mif.core.exec.InvokerResult;
import com.mango.mif.sge.QstatJobOutput;
import com.mango.mif.sge.SGEOutput;
import com.mango.mif.sge.SGEOutputParser;
import com.mango.mif.utils.CommonUtils;
import com.mango.mif.utils.MIFProperties;


/**
 * @version $Revision: $ as of $Date: $
 * <p>SVN Entry : $HeadURL: $
 * <p>SVN ID    : $Id: $
 * <p>Last edited by : $Author: $
 * 
 * Tests QstatCommandResultHandler
 */
public class QstatCommandResultHandlerTest {
    /**
     * Submit result handler
     */
    private QstatCommandResultHandler resultHandler;
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
        Properties properties = new Properties();
        properties.load(SummaryMessageBuilderTest.class.getResourceAsStream("/com/mango/mif/sgeconnector/connector.properties"));
        MIFProperties.loadProperties(properties);
        resultHandler = new QstatCommandResultHandler(SUCCESS_EVENT,FAILURE_EVENT);
        resultHandler.setOutputParser(outputParser);
        resultHandler.setJob(job);
        resultHandler.setErrorPropertyKeyPattern(".*error.*");
    }

    @Test(expected=NullPointerException.class)
    public void shouldThrowExceptionIfOutputParserNotSet() throws ExecutionException {
        resultHandler.setOutputParser(null);
        resultHandler.handle(invokerResult);
    }

    @Test
    public void shouldNotThrowExceptionIfQstatReturnsNonZeroValue() throws ExecutionException {
        when(invokerResult.getExitCode()).thenReturn(1); //exit code
        SGEOutput output = mock(SGEOutput.class);
        when(outputParser.parseStatusMessage((String)any())).thenReturn(output);
        resultHandler.handle(invokerResult);
    }

    @Test
    public void shouldResultInSuccessEventIfJobProcessingFinished() throws ExecutionException {
        when(invokerResult.getExitCode()).thenReturn(1); //exit code
        SGEOutput output = mock(SGEOutput.class);
        when(output.get(SGEOutput.SGE_COMMAND_RESULT_KEY)).thenReturn(SGEOutput.JOB_NOT_FOUND);
        when(outputParser.parseStatusMessage((String)any())).thenReturn(output);
        resultHandler.handle(invokerResult);
        assertEquals(SUCCESS_EVENT, resultHandler.getStateExitEvent());
    }
    
    @Test
    public void shouldResultInFailureEventIfJobProcessingHasntFinished() throws ExecutionException {
        when(invokerResult.getExitCode()).thenReturn(0); //exit code
        SGEOutput output = mock(SGEOutput.class);
        when(output.get(SGEOutput.SGE_COMMAND_RESULT_KEY)).thenReturn(SGEOutput.JOB_PROCESSING);
        when(output.get(QstatJobOutput.SUBMISSION_TIME.getToken())).thenReturn("Sat May 12 20:07:00 2012");
        when(outputParser.parseStatusMessage((String)any())).thenReturn(output);
        resultHandler.handle(invokerResult);
        assertEquals(FAILURE_EVENT, resultHandler.getStateExitEvent());
    }

    @Test(expected=ExecutionException.class)
    public void shouldThrowExceptionIfErrorsFoundInTheOutput() throws ExecutionException {
        when(invokerResult.getExitCode()).thenReturn(0); //exit code
        SGEOutput output = mock(SGEOutput.class);
        when(output.get(QstatJobOutput.SUBMISSION_TIME.getToken())).thenReturn("Sat May 12 20:07:00 2012");
        when(output.get(SGEOutput.SGE_COMMAND_RESULT_KEY)).thenReturn(SGEOutput.JOB_PROCESSING);
        Map<String,String> data = Maps.newHashMap();
        data.put("error reason    1","Some error");
        when(output.getData()).thenReturn(data);
        when(outputParser.parseStatusMessage((String)any())).thenReturn(output);
        resultHandler.handle(invokerResult);
    }

    @Test
    public void shouldParseADate() throws ExecutionException, ParseException {
        CommonUtils.convertToMilliseconds("Sat May 12 20:07:00 2012");
    }
    
}
