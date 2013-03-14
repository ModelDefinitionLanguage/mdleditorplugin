package com.mango.mif.sgeconnector;

import static org.mockito.Matchers.any;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import java.io.File;
import java.util.Properties;
import java.util.UUID;

import org.junit.Before;
import org.junit.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import com.mango.mif.connector.TestsHelper;
import com.mango.mif.connector.domain.Job;
import com.mango.mif.connector.runner.api.SCXMLDriver;
import com.mango.mif.connector.runner.impl.GenericShellBasedJobRunner;
import com.mango.mif.connector.runner.impl.StateHandlerException;
import com.mango.mif.connector.runner.status.SummaryMessageBuilderTest;
import com.mango.mif.core.exec.ExecutionException;
import com.mango.mif.core.exec.Invoker;
import com.mango.mif.core.exec.InvokerResult;
import com.mango.mif.core.exec.template.JobAwareFreemarkerTemplateCommandBuilder;
import com.mango.mif.managers.JobManagementService;
import com.mango.mif.sge.SGE;
import com.mango.mif.sge.SGEOutput;
import com.mango.mif.sge.SGEOutputParser;
import com.mango.mif.utils.MIFProperties;
import com.mango.mif.utils.encrypt.EncrypterFactory;

/**
 * Tests cancelled state handler if it produces and invokes correct command
 * @author mrogalski
 *
 */
public class CancelledStateHandlerTest {

    /**
     * Instance being tested
     */
    private CancelledStateHandler stateHandler;
    /**
     * SGE working directory
     */
    private File sgeWorkingDirectory;
    /**
     * Command builder
     */
    @Mock
    JobAwareFreemarkerTemplateCommandBuilder commandBuilder;
    /**
     * SGE output parser
     */
    @Mock
    SGEOutputParser sgeOutputParser;
    /**
     * Invoker
     */
    @Mock Invoker invoker;
    /**
     * Invoker
     */
    @Mock InvokerResult invokerResult;
    /**
     * Invoker
     */
    @Mock SGEOutput sgeOutput;
    
    @Mock JobManagementService jobManagementService;

    @Mock SCXMLDriver driver;

    @Before
    public void setUp() throws Exception {
        MockitoAnnotations.initMocks(this);
        Properties properties = new Properties();
        properties.load(SummaryMessageBuilderTest.class.getResourceAsStream("/com/mango/mif/sgeconnector/connector.properties"));
        MIFProperties.loadProperties(properties);
        sgeWorkingDirectory = TestsHelper.createTmpDirectory("CanceledStateHandlerTest.setUp");
        stateHandler = new CancelledStateHandler("task-cancelled");
        stateHandler.setQdelCommandBuilder(commandBuilder);
        stateHandler.setSgeOutputParser(sgeOutputParser);
        stateHandler.setSCXMLDriver(driver);
        GenericShellBasedJobRunner jobRunner = new GenericShellBasedJobRunner();
        jobRunner.setEncrypter(EncrypterFactory.getEncrypter());
        String jobUUID = UUID.randomUUID().toString();
        jobRunner.setInvoker(invoker);
        Job job = TestsHelper.createJob("JOB_ID", jobUUID);
        jobRunner.setJobId(job.getJobId());
        job.addToDataMap(SGE.SGE_JOB_ID_KEY, "0001");
        job.setGridJobDirectory(sgeWorkingDirectory.getAbsolutePath());
        stateHandler.setJobRunner(jobRunner);
        stateHandler.setJobManagementService(jobManagementService);

        when(invoker.execute((String)any())).thenReturn(invokerResult);
        when(sgeOutputParser.parseStatusMessage((String)any())).thenReturn(sgeOutput);
        when(sgeOutputParser.parseDeleteMessage((String)any())).thenReturn(sgeOutput);
        when(jobManagementService.getJob((String)any())).thenReturn(job);
        when(jobManagementService.saveJob(job)).thenReturn(job);
    }

    @Test
    public void shouldCallInvokerOnceTimes() throws StateHandlerException, ExecutionException {
        stateHandler.call();

        verify(invoker,times(1)).execute((String)any());
    }

}
