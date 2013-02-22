/* File			:  MyJobServiceControllerTest.java
 * Project		:  MIFServer
 * Created on	:  10 Jul 2012
 */
package com.mango.mif.rest.service;

import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.fail;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.junit.After;
import org.junit.Before;
import org.junit.Ignore;
import org.junit.Test;

import com.google.common.base.Preconditions;
import com.mango.mif.client.api.rest.FileList;
import com.mango.mif.client.api.rest.JobService;
import com.mango.mif.client.api.rest.JobServiceException;
import com.mango.mif.connector.dao.JobRepository;
import com.mango.mif.connector.domain.Job;
import com.mango.mif.core.exec.ExecutionException;
import com.mango.mif.core.exec.Invoker;
import com.mango.mif.core.exec.invoker.InvokerFactory;
import com.mango.mif.core.exec.invoker.InvokerHelper;
import com.mango.mif.domain.ExecutionRequest;
import com.mango.mif.exception.MIFException;
import com.mango.mif.managers.JobManagementService;

/**
 * @version $Revision: $ as of $Date: $
 *          <p>
 *          SVN Entry : $HeadURL: $
 *          <p>
 *          SVN ID : $Id: $
 *          <p>
 *          Last edited by : $Author: $
 */
//FIXME:  Commented out test for the time being as System properties not getting picked up during test run.  Possibly
//an issue with the pom.
@Ignore
public class JobServiceTest {

    protected static final Log logger = LogFactory.getLog(JobServiceTest.class);

    /**
     * Alter user name
     */
    public final static String MIF_ALTER_USER_NAME = "mif.alter.client.userName";
    /**
     * Alter user password
     */
    public final static String MIF_ALTER_USER_PASSWORD = "mif.alter.client.password";
    /**
     * Jsch port to use.
     */
    public final static String MIF_JSCH_PORT = "mif.jsch.port";
    /**
     * RESTful service under test.
     */
    private JobService jobService;
    /**
     * RESTful service implementation under test.
     */
    protected JobService jobServiceImpl;
    /**
     * user name that will be used
     */
    private String userName;
    /**
     * encrypted password that will be used
     */
    private String encryptedPassword;
    /**
     * The jsch port to use
     */
    private String portProp;
    /**
     * SSH port
     */
    private int port;

    /**
     * The name of the temp file we create in the /tmp folder to use
     * when testing the tail method of the service
     */
    private final String FILE_TO_TAIL = "fileToTail.txt";

    private InvokerFactory invokerFactory;

    @Before
    public void setUp() {

        jobServiceImpl = new MockCxfJobService();
        setJobService(jobServiceImpl);

        userName = System.getProperty(MIF_ALTER_USER_NAME);
        encryptedPassword = System.getProperty(MIF_ALTER_USER_PASSWORD);
        System.out.println(" --- PASSWORD BEING SET ON encryptedPassword:  " + encryptedPassword + " --- ");
        portProp = System.getProperty(MIF_JSCH_PORT);

        Preconditions.checkNotNull(userName, MIF_ALTER_USER_NAME + " property is not set.");
        Preconditions.checkNotNull(encryptedPassword, MIF_ALTER_USER_PASSWORD + " property is not set.");
        Preconditions.checkNotNull(portProp, MIF_JSCH_PORT + " property is not set.");

        port = Integer.parseInt(portProp);
        invokerFactory = new InvokerFactory() .port(port);

        // Setup the test file structure
        Invoker invoker;
        try {
            invoker = invokerFactory.createRunAsUserInvoker(userName, encryptedPassword);
            InvokerHelper invokerHelper = new InvokerHelper(invoker);
            invokerHelper.runAndReportFailures(buildCreateTestFilesCommand());
        } catch (ExecutionException e) {
            logger.error("Exception during test setup - problem with Invoker: ", e);
            fail("Exception during test setup: " + e.getMessage());
        }

        //Create a file to tail in /tmp
        try {
            invoker = invokerFactory.createRunAsUserInvoker(userName, encryptedPassword);
            InvokerHelper invokerHelper = new InvokerHelper(invoker);
            invokerHelper.runAndReportFailures("cd /tmp; mkdir job; cd job; mkdir 100; cd 100; ps -ef > " + FILE_TO_TAIL);
        } catch (ExecutionException e) {
            logger.error("Exception during test setup - problem with Invoker: ", e);
            fail("Exception during test setup: " + e.getMessage());
        }

    }

    @Test
    public void shouldReturnFileLinks() {
        try {
            FileList fileLinks = null;

            fileLinks = getJobService().listDir("jobId", "LD");

            assertNotNull(fileLinks);

        } catch (JobServiceException e) {
            logger.error("Exception while testing service: ", e);
            fail("Service call threw exception: " + e.getMessage());
        } catch (MIFException e) {
            fail("Service call threw exception: " + e.getMessage());
        }
    }

    @Test
    public void shouldReturnFileContent() {
        try {
            byte[] fileContent = null;

            fileContent = getJobService().getTailedFileContents("jobId",
                    "workingDir/file.txt");

            assertNotNull(fileContent);

        } catch (JobServiceException e) {
            logger.error("Exception while testing service: ", e);
            fail("Service call threw exception: " + e.getMessage());
        } catch (MIFException e) {
            fail("Service call threw exception: " + e.getMessage());
        }
    }

    @Ignore
    @Test
    public void testGetTailedFileContents() {

        JobServiceImpl js = new JobServiceImpl();
        js.setRootFolder("/tmp");
        js.setTailFileMaxSizeInBytes(1000000000);

        JobManagementService jr = mock(JobManagementService.class);
        Job job = mock(Job.class);
        ExecutionRequest req = mock(ExecutionRequest.class);
        when(req.getUserName()).thenReturn(userName);
        when(req.getUserPassword()).thenReturn(encryptedPassword);
        when(job.getExecutionRequest()).thenReturn(req);
        when(jr.getJob("100")).thenReturn(job);
        js.setJobManagementService(jr);

        byte[] content = null;

        try {
            content = js.getTailedFileContents("100", FILE_TO_TAIL);

        } catch (MIFException e) {
            logger.error("Exception while testing service: ", e);
            fail("Service call threw exception: " + e.getMessage());
        }

        assertNotNull(content);
    }

    /*    @Ignore
    	@Test
    	public void testGetFilesNonRecursively() {

    			JobServiceImpl js = new JobServiceImpl();
    			js.setRootFolder("/tmp");
    			js.setPort(22);
    			JschSessionPool pool = new JschSessionPool();

    			JobRepository jr = mock(JobRepository.class);
    			Job job = mock(Job.class);
    			ExecutionRequest req = mock(ExecutionRequest.class);
    			when(req.getUserName()).thenReturn(userName);
    			when(req.getUserPassword()).thenReturn(encryptedPassword);
    			when(job.getExecutionRequest()).thenReturn(req);
    			when(jr.getJob("100")).thenReturn(job);
    			js.setJobRepository(jr);

    			FileList fileList = null;

    			try {
    				fileList = js.getFileLinksNonRecursively("100",
    						"folder1/folder2");
    			} catch (MIFException e) {
    				logger.error("Exception while testing service: ", e);
    				fail("Service call threw exception: " + e.getMessage());
    			}

    			assertTrue(fileList.getFiles().size() == 3);

    	}*/

    /*@Ignore
    @Test
    public void testGetFilesRecursively() {

    		JobServiceImpl js = new JobServiceImpl();
    		js.setRootFolder("/tmp");
    		js.setPort(22);
    		JobRepository jr = mock(JobRepository.class);
    		Job job = mock(Job.class);
    		ExecutionRequest req = mock(ExecutionRequest.class);
    		when(req.getUserName()).thenReturn(userName);
    		when(req.getUserPassword()).thenReturn(encryptedPassword);
    		when(job.getExecutionRequest()).thenReturn(req);
    		when(jr.getJob("100")).thenReturn(job);
    		js.setJobRepository(jr);

    		FileList fileList = null;

    		try {
    			fileList = js.getFileLinks("100",
    					"folder1/folder2");
    		} catch (MIFException e) {
    			logger.error("Exception while testing service: ", e);
    			fail("Service call threw exception: " + e.getMessage());
    		}

    		assertTrue(fileList.getFiles().size() == 6);
    }
     */
    private String buildCreateTestFilesCommand() {

        StringBuilder sb = new StringBuilder();
        sb.append("cd \"");
        sb.append("/tmp");
        sb.append("\"; mkdir \"job");
        sb.append("\"; cd \"");
        sb.append("job");
        sb.append("\"; mkdir \"100");
        sb.append("\"; cd \"");
        sb.append("100");
        sb.append("\"; mkdir \"folder1");
        sb.append("\"; cd \"");
        sb.append("folder1");
        sb.append("\"; mkdir \"folder2");
        sb.append("\"; cd \"");
        sb.append("folder2");
        sb.append("\"; dir > \"2aFile");
        sb.append("\"; dir > \"2bFile");
        sb.append("\"; dir > \"2cFile");
        sb.append("\"; mkdir \"folder3");
        sb.append("\"; cd \"");
        sb.append("folder3");
        sb.append("\"; dir > \"3aFile");
        sb.append("\"; dir > \"3bFile");
        sb.append("\"; dir > \"3cFile\";");

        return sb.toString();
    }

    private String buildDeleteTestFilesCommand() {

        StringBuilder sb = new StringBuilder();
        sb.append("cd \"");
        sb.append("/tmp");
        sb.append("\"; rm -Rf \"job\"");

        return sb.toString();
    }

    @After
    public void tearDown() {
        //Destroy test file structure and /tmp file that's used to test the tail method of the service
        Invoker invoker;
        try {
            invoker = invokerFactory.createRunAsUserInvoker(userName, encryptedPassword);
            InvokerHelper invokerHelper = new InvokerHelper(invoker);
            invokerHelper.runAndReportFailures(buildDeleteTestFilesCommand());
            invokerHelper.runAndReportFailures("cd /tmp; rm -Rf job");

        } catch (ExecutionException e) {
            logger.error(
                    "Exception during test teardown - problem with Invoker: ", e);
            fail("Exception during test teardown: " + e.getMessage());
        }

    }

    public void setJobService(JobService jobService) {
        this.jobService = jobService;
    }

    public JobService getJobService() {
        return jobService;
    }

}
