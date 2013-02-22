/* File			:  SubmitResultHandlerTest.java
 * Project		:  MIFServer
 * Created on	:  2 Jul 2012
 */
package com.mango.mif.sgeconnector;

import java.util.Properties;

import org.junit.Before;
import org.junit.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import static org.mockito.Mockito.*;

import com.mango.mif.connector.domain.Job;
import com.mango.mif.connector.runner.status.SummaryMessageBuilderTest;
import com.mango.mif.core.exec.ExecutionException;
import com.mango.mif.core.exec.Invoker;
import com.mango.mif.core.exec.InvokerResult;
import com.mango.mif.sge.QacctOutputJobProperty;
import com.mango.mif.sge.SGEOutput;
import com.mango.mif.sge.SGEOutputParser;
import com.mango.mif.utils.MIFProperties;


/**
 * @version $Revision: $ as of $Date: $
 * <p>SVN Entry : $HeadURL: $
 * <p>SVN ID    : $Id: $
 * <p>Last edited by : $Author: $
 */
/**
 * @author mrogalski
 * Submit result handler test
 */
public class QacctCommandResultHandlerTest {
	/**
	 * Submit result handler
	 */
	private QacctCommandResultHandler resultHandler;
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
    @Mock Invoker invoker;
	/**
	 * job
	 */
	@Mock InvokerResult invokerResult;
	/**
	 * @throws java.lang.Exception
	 */
	@Before
	public void setUp() throws Exception {
        MockitoAnnotations.initMocks(this);
        Properties properties = new Properties();
        properties.load(SummaryMessageBuilderTest.class.getResourceAsStream("/com/mango/mif/sgeconnector/connector.properties"));
        MIFProperties.loadProperties(properties);
		resultHandler = new QacctCommandResultHandler("TEST.EVENT");
		resultHandler.setOutputParser(outputParser);
		resultHandler.setJob(job);
		job.setInvoker(invoker);
		when(invoker.execute((String) any())).thenReturn(invokerResult);
		resultHandler.setMifHiddenDirectory("MIF_HIDDEN_DIR");
		resultHandler.setSgeJobStdErrFileName("STD_ERR");
        resultHandler.setSgeJobStdOutFileName("STD_OUT");
	}

	@Test(expected=NullPointerException.class)
	public void shouldThrowExceptionIfOutputParserNotSet() throws ExecutionException {
		resultHandler.setOutputParser(null);
		resultHandler.handle(invokerResult);
	}

	@Test(expected=ExecutionException.class)
	public void shouldThrowExceptionIfExitStatusInQacctOutputIsNull() throws ExecutionException {
		when(invokerResult.getExitCode()).thenReturn(0); //exit code
		SGEOutput output = mock(SGEOutput.class);
        when(output.get(QacctOutputJobProperty.END_TIME.getToken())).thenReturn("Sat May 12 20:07:00 2012");
		when(output.get(QacctOutputJobProperty.EXIT_STATUS.getToken())).thenReturn(null);
		when(output.get(QacctOutputJobProperty.FAILED.getToken())).thenReturn("0");
		when(outputParser.parseAccountingMessage((String)any())).thenReturn(output);
		resultHandler.handle(invokerResult);
	}
	
	@Test(expected=ExecutionException.class)
	public void shouldThrowExceptionIfFailureStatusInQacctOutputIsNull() throws ExecutionException {
		when(invokerResult.getExitCode()).thenReturn(0); //exit code
		SGEOutput output = mock(SGEOutput.class);
        when(output.get(QacctOutputJobProperty.END_TIME.getToken())).thenReturn("Sat May 12 20:07:00 2012");
		when(output.get(QacctOutputJobProperty.EXIT_STATUS.getToken())).thenReturn("0");
		when(output.get(QacctOutputJobProperty.FAILED.getToken())).thenReturn(null);
		when(outputParser.parseAccountingMessage((String)any())).thenReturn(output);
		resultHandler.handle(invokerResult);
	}

	@Test(expected=ExecutionException.class)
	public void shouldThrowExceptionIfFailureCodeInQacctOutputIsDifferentThanZero() throws ExecutionException {
		when(invokerResult.getExitCode()).thenReturn(0); //exit code
		SGEOutput output = mock(SGEOutput.class);
        when(output.get(QacctOutputJobProperty.END_TIME.getToken())).thenReturn("Sat May 12 20:07:00 2012");
		when(output.get(QacctOutputJobProperty.EXIT_STATUS.getToken())).thenReturn("0");
		when(output.get(QacctOutputJobProperty.FAILED.getToken())).thenReturn("100 - some grid error");
		when(outputParser.parseAccountingMessage((String)any())).thenReturn(output);
		resultHandler.handle(invokerResult);
	}

    @Test(expected=ExecutionException.class)
    public void shouldThrowExceptionIfExitCodeInQacctOutputIsDifferentThanZero() throws ExecutionException {
        when(invokerResult.getExitCode()).thenReturn(0); //exit code
        SGEOutput output = mock(SGEOutput.class);
        when(output.get(QacctOutputJobProperty.END_TIME.getToken())).thenReturn("Sat May 12 20:07:00 2012");
        when(output.get(QacctOutputJobProperty.EXIT_STATUS.getToken())).thenReturn("1");
        when(outputParser.parseAccountingMessage((String)any())).thenReturn(output);
        resultHandler.handle(invokerResult);
    }
    
	@Test
	public void shouldHandleResult() throws ExecutionException {
		when(invokerResult.getExitCode()).thenReturn(0); //exit code
		SGEOutput output = mock(SGEOutput.class);
        when(output.get(QacctOutputJobProperty.END_TIME.getToken())).thenReturn("Sat May 12 20:07:00 2012");
		when(output.get(QacctOutputJobProperty.EXIT_STATUS.getToken())).thenReturn("0");
		when(output.get(QacctOutputJobProperty.FAILED.getToken())).thenReturn("0");
		when(outputParser.parseAccountingMessage((String)any())).thenReturn(output);
		resultHandler.handle(invokerResult);
	}
}
