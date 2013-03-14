/* File			:  JobServiceImplTest.java
 * Project		:  MIFServer
 * Created on	:  Aug 31, 2012
 */
package com.mango.mif.rest.service;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertTrue;
import static org.mockito.Matchers.any;
import static org.mockito.Matchers.eq;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

import java.util.List;

import javax.xml.bind.JAXBException;

import org.apache.commons.io.FileUtils;
import org.apache.cxf.jaxrs.ext.MessageContext;
import org.apache.log4j.Logger;
import org.junit.Before;
import org.junit.Ignore;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.runners.MockitoJUnitRunner;

import com.google.common.collect.Lists;
import com.mango.mif.client.api.rest.BootstrapActiveChildJobs;
import com.mango.mif.client.api.rest.MIFResponse;
import com.mango.mif.client.api.rest.ResponseStatus;
import com.mango.mif.connector.domain.Job;
import com.mango.mif.core.exec.ExecutionException;
import com.mango.mif.core.exec.Invoker;
import com.mango.mif.core.exec.ce.CeInvoker;
import com.mango.mif.domain.ExecutionRequest;
import com.mango.mif.domain.ExecutionType;
import com.mango.mif.domain.nonmemsgeconnector.NONMEMProcessingDetailedStatus;
import com.mango.mif.domain.psnsgeconnector.PsNBootstrapProcessingDetailedStatus;
import com.mango.mif.exception.MIFException;
import com.mango.mif.managers.JobManagementService;
import com.mango.mif.sge.SGEJobCancellationRequestHandler;
import com.mango.mif.utils.JAXBUtils;
import com.mango.mif.utils.jaxb.ExecutionJaxbUtils;


/**
 * @version $Revision: $ as of $Date: $
 * <p>SVN Entry : $HeadURL: $
 * <p>SVN ID    : $Id: $
 * <p>Last edited by : $Author: $
 * 
 * Tests implementation of JobService
 * 
 */
@RunWith(MockitoJUnitRunner.class)
public class JobServiceImplTest {

    private final static Logger LOG = Logger.getLogger(JobServiceImplTest.class);
    
    @Mock JobManagementService jobManagementService;
    
    @Mock MessageContext messageContext;
    
    @Mock SGEJobCancellationRequestHandler sgeJobCancellationRequestHandler;
    
    @Mock Job job;
    
    @Mock ExecutionRequest executionRequest;
    
    JobServiceImpl jobService;
    
    @Before
    public void setUp() throws Exception {
        jobService = new JobServiceImpl();
        jobService.setJobManagementService(jobManagementService);
        jobService.setMessageContext(messageContext);
        jobService.setSgeJobCancellationRequestHandler(sgeJobCancellationRequestHandler);
        when(job.getExecutionRequest()).thenReturn(executionRequest);
        when(executionRequest.getType()).thenReturn(ExecutionType.PsN_Bootstrap.name());
        Invoker invoker = new CeInvoker();
        when(job.getInvoker()).thenReturn(invoker);
    }

    @Test
    public void shouldResultInFailureWhenJobIdIsNull() {
        List<String> sgeJobIds = Lists.newArrayList();
        sgeJobIds.add("1111");
        sgeJobIds.add("2222");
        sgeJobIds.add("3333");
        MIFResponse response = jobService.killPsNBootstrapChildSGEJobs(null, sgeJobIds);
        assertEquals(ResponseStatus.FAILURE,response.getStatus());
        assertNotNull(response.getErrorMessage());
    }

    @Test
    public void shouldResultInFailureWhenSgeChildJobsListIsNull() {
        MIFResponse response = jobService.killPsNBootstrapChildSGEJobs("1", null);
        assertEquals(ResponseStatus.FAILURE,response.getStatus());
        assertNotNull(response.getErrorMessage());
    }
    
    @Test
    public void shouldResultInFailureWhenJobWithSpecifiedIdDoesNotExist() {
        List<String> sgeJobIds = Lists.newArrayList();
        sgeJobIds.add("1111");
        sgeJobIds.add("2222");
        sgeJobIds.add("3333");
        when(jobManagementService.getJob("1")).thenReturn(null);
        MIFResponse response = jobService.killPsNBootstrapChildSGEJobs("1", sgeJobIds);
        assertEquals(ResponseStatus.FAILURE,response.getStatus());
        assertNotNull(response.getErrorMessage());
    }

    @Test
    public void shouldResultInSuccessAndIncludeErrorMessageWhenCancellationOfSpecifiedChildJobsFails() throws ExecutionException {
        List<String> sgeJobIds = Lists.newArrayList();
        sgeJobIds.add("1111");
        sgeJobIds.add("2222");
        sgeJobIds.add("3333");
        when(jobManagementService.getJob("1")).thenReturn(job);
        when(sgeJobCancellationRequestHandler.cancelChildSGEJobs(eq(job), (List<String>)any())).thenThrow(new ExecutionException("FAILED"));
        MIFResponse response = jobService.killPsNBootstrapChildSGEJobs("1", sgeJobIds);
        assertEquals(ResponseStatus.SUCCESS,response.getStatus());
        assertEquals("FAILED",response.getErrorMessage());
        
    }

    @Test
    public void shouldResultInFailureWhenCancellationOfNonPsNBootstrapChildJobs() throws ExecutionException {
        List<String> sgeJobIds = Lists.newArrayList();
        sgeJobIds.add("1111");
        sgeJobIds.add("2222");
        sgeJobIds.add("3333");
        when(executionRequest.getType()).thenReturn(ExecutionType.COMMAND_LINE.name());
        when(jobManagementService.getJob("1")).thenReturn(job);
        when(sgeJobCancellationRequestHandler.cancelChildSGEJobs(eq(job), (List<String>)any())).thenThrow(ExecutionException.class);
        MIFResponse response = jobService.killPsNBootstrapChildSGEJobs("1", sgeJobIds);
        assertEquals(ResponseStatus.FAILURE,response.getStatus());
    }
    @Test
    public void shouldResultInSuccessWhenCancellationOfChildJobsSucceeds() throws ExecutionException {
        List<String> sgeJobIds = Lists.newArrayList();
        sgeJobIds.add("1111");
        sgeJobIds.add("2222");
        sgeJobIds.add("3333");
        when(jobManagementService.getJob("1")).thenReturn(job);
        when(sgeJobCancellationRequestHandler.cancelChildSGEJobs(eq(job), (List<String>)any())).thenReturn("Cancellation SUCCESSFUL");
        MIFResponse response = jobService.killPsNBootstrapChildSGEJobs("1", sgeJobIds);
        assertEquals(ResponseStatus.SUCCESS,response.getStatus());
    }

    @Test
    public void shouldResultInFailureWhenRequestedBootstrapActiveChildJobsOfAJobThatDoesNotExist() throws ExecutionException {
        when(jobManagementService.getJob("1")).thenReturn(null);
        MIFResponse response = jobService.getActiveJobsOfBootstrap("1");
        assertEquals(ResponseStatus.FAILURE,response.getStatus());
    }

    @Test
    public void shouldResultInFailureWhenRequestedBootstrapActiveChildJobsOfAJobThatIsNotBootstrap() throws ExecutionException {
        when(executionRequest.getType()).thenReturn(ExecutionType.COMMAND_LINE.name());
        when(jobManagementService.getJob("1")).thenReturn(job);
        MIFResponse response = jobService.getActiveJobsOfBootstrap("1");
        assertEquals(ResponseStatus.FAILURE,response.getStatus());
    }

    @Test
    public void shouldResultInFailureWhenRequestedBootstrapActiveChildJobsAndNoDetailedStatusIsAvailable() throws ExecutionException, MIFException {
        when(executionRequest.getType()).thenReturn(ExecutionType.PsN_Bootstrap.name());
        when(job.getDetailedStatus()).thenReturn(null);
        when(jobManagementService.getJob("1")).thenReturn(job);
        MIFResponse response = jobService.getActiveJobsOfBootstrap("1");
        assertEquals(ResponseStatus.FAILURE,response.getStatus());
    }

    @Test
    public void shouldReturnBootstrapActiveChildJobs() throws ExecutionException, MIFException, JAXBException {
        when(executionRequest.getType()).thenReturn(ExecutionType.PsN_Bootstrap.name());
        PsNBootstrapProcessingDetailedStatus mockDetailedStatus = mock(PsNBootstrapProcessingDetailedStatus.class);
        List<NONMEMProcessingDetailedStatus> listOfActiveChildjobs = Lists.newArrayList();
        when(mockDetailedStatus.getActiveChildNONMEMProcesses()).thenReturn(listOfActiveChildjobs);
        when(job.getDetailedStatus()).thenReturn(mockDetailedStatus);
        when(jobManagementService.getJob("1")).thenReturn(job);
        BootstrapActiveChildJobs response = jobService.getActiveJobsOfBootstrap("1");
        assertEquals(ResponseStatus.SUCCESS,response.getStatus());
        assertNotNull(response.getActiveChildJobs());
        LOG.debug("Detailed status response " + JAXBUtils.marshall(response, ExecutionJaxbUtils.CONTEXT_CLASSES));
        
    }

    @Test
    public void shouldReturnDetailedStatusOfAPsNBootstrap() throws ExecutionException, MIFException, JAXBException {
        PsNBootstrapProcessingDetailedStatus mockDetailedStatus = new PsNBootstrapProcessingDetailedStatus();
        mockDetailedStatus.setRequestId("requestID");
        mockDetailedStatus.setSummary("Some summary");
        when(job.getDetailedStatus()).thenReturn(mockDetailedStatus);
        when(job.getExecutionType()).thenReturn(ExecutionType.PsN_Bootstrap);
        when(jobManagementService.getJob("1")).thenReturn(job);
        PsNBootstrapProcessingDetailedStatus response = jobService.getPsNBootstrapDetailedStatus("1");
        String marshalled = JAXBUtils.marshall(response, ExecutionJaxbUtils.CONTEXT_CLASSES);
        LOG.debug("Detailed status response " + marshalled);
    }

    //FIXME:  Change test to check that error message written to result object
    @Ignore
    @Test(expected=MIFException.class)
    public void shouldThrowAnExceptionWhenNoDetailedStatusAvailable() throws ExecutionException, MIFException {
        when(jobManagementService.getDetailedStatus("1")).thenReturn(null);
        jobService.getPsNBootstrapDetailedStatus("1");
    }

    @Test
    public void shouldReturnContentsOfSCMLogFile() throws ExecutionException, MIFException {
        when(executionRequest.getType()).thenReturn(ExecutionType.PsN_SCM.name());
        when(executionRequest.getExecutionParameters()).thenReturn("-command=parameter -directory=linear -other=property");
        when(jobManagementService.getJob("1")).thenReturn(job);
        when(job.getPsNSCMLogFile()).thenReturn(FileUtils.toFile(JobServiceImplTest.class.getResource("/testData/outputs/PsN_SCM_LIN/scm-linear-completed/linear/scmlog1.txt")));
        byte[] contents = jobService.getSCMLogFileContent("1");
        assertTrue(contents.length>0);
    }
}
