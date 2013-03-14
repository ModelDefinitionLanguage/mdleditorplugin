/* File			:  MIFClientTestOnServer.java
 * Project		:  MangoInteroperabilityFramework
 * Created on	:  Apr 2, 2012
 */
package com.mango.mif.client.remote;

import static org.junit.Assert.*;

import java.io.File;
import java.io.IOException;
import java.net.MalformedURLException;
import java.nio.charset.Charset;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
import java.util.concurrent.CountDownLatch;
import java.util.concurrent.TimeUnit;

import javax.annotation.Resource;
import javax.xml.bind.JAXBException;

import org.apache.commons.io.FileUtils;
import org.apache.log4j.Logger;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.jms.core.JmsTemplate;
import org.springframework.test.annotation.DirtiesContext;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import com.google.common.base.Preconditions;
import com.google.common.collect.Lists;
import com.google.common.collect.Maps;
import com.mango.mif.client.api.TaskExecutionManager;
import com.mango.mif.client.api.TaskExecutionMessageHandler;
import com.mango.mif.client.api.rest.JobService;
import com.mango.mif.client.api.rest.MIFResponse;
import com.mango.mif.client.api.rest.ResponseStatus;
import com.mango.mif.connector.TestsHelper;
import com.mango.mif.core.api.ResourceCopierFactory;
import com.mango.mif.core.api.SharedLocationManager;
import com.mango.mif.core.exec.ExecutionException;
import com.mango.mif.core.exec.Invoker;
import com.mango.mif.core.exec.invoker.InvokerFactory;
import com.mango.mif.core.exec.invoker.InvokerHelper;
import com.mango.mif.core.exec.template.FreemarkerTemplateCommandBuilder;
import com.mango.mif.core.resource.BaseResourcePublisher;
import com.mango.mif.core.resource.ResourceCopier;
import com.mango.mif.core.resource.ResourceCopierParameters;
import com.mango.mif.core.resource.shell.PublisherParameters;
import com.mango.mif.core.resource.shell.ShellBasedResourcePublisher;
import com.mango.mif.domain.DetailedStatus;
import com.mango.mif.domain.ExecutionRequest;
import com.mango.mif.domain.ExecutionRequestBuilder;
import com.mango.mif.domain.ExecutionResponse;
import com.mango.mif.domain.ExecutionType;
import com.mango.mif.domain.JobStatus;
import com.mango.mif.domain.nonmemsgeconnector.NONMEMProcessingDetailedStatus;
import com.mango.mif.domain.psnsgeconnector.PsNBootstrapProcessingDetailedStatus;
import com.mango.mif.exception.MIFException;
import com.mango.mif.utils.JAXBUtils;
import com.mango.mif.utils.ExecutionRequestHelper;
import com.mango.mif.utils.TestProperties;
import com.mango.mif.utils.jaxb.ExecutionJaxbUtils;

/**
 * This test performs smoke tests on an external MIF instance.
 * 1. Schedules jobs of each supported execution type and waits for the results
 * 2. Schedules R job and Bootstrap job and cancels them once they have been started
 * 
 * 
 * @FIXME That class should be split into multiple Smoke Tests, it should also use Shell Based Resource Publisher to copy files to MIF SHARE directory
 * 
 * @version $Revision: $ as of $Date: $
 * <p>
 * SVN Entry : $HeadURL: $
 * <p>
 * SVN ID : $Id: $
 * <p>
 * Last edited by : $Author: $
 */
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration
public class MIFClientTestDataSetsSGETestOnServer implements TaskExecutionMessageHandler {

    /** The Constant LOG. */
    final static Logger LOG = Logger.getLogger(MIFClientTestDataSetsSGETestOnServer.class);

    @Resource(name = "taskExecutionManager")
    private TaskExecutionManager taskExecutionManager;

    /** The jms template. */
    @Resource(name = "jmsTemplate")
    JmsTemplate jmsTemplate;

    /** The request map. */
    static Map<String, ExecutionRequest> requestMap = Maps.newConcurrentMap();
    
    /** The results map. */
    static Map<String, ExecutionResponse> resultsMap = Maps.newConcurrentMap();
    
    /** Map used for synchronization of test thread and messages. */
    static Map<String, CountDownLatch> signalsMap = Maps.newConcurrentMap();
    
    static Map<String, String> requestOutputDirs = Maps.newConcurrentMap();

    @Resource(name = "sharedLocationManager")
    private SharedLocationManager sharedLocationManager;
    
    private final static long DETAIL_STATUS_POLL_RATE = 5l;

    @Resource(name = "jobService")
    private JobService jobService;

    @Resource(name = "resourceCopierFactory")
    private ResourceCopierFactory resourceCopierFactory;
    
    @Resource(name = "resourceCopierCommandBuilder")
    private FreemarkerTemplateCommandBuilder resourceCopierCommandBuilder;

    @Resource(name = "resourcePublisherCommandBuilder")
    private FreemarkerTemplateCommandBuilder resourcePublisherCommandBuilder;
    
    private File outputDirectory;
    
    private String userName;
    /**
     * encrypted password
     */
    private String userPassword;
    /**
     * R executable location
     */
    private String rCommand;
    /**
     * NONMEM executable location
     */
    private String nonmemCommand;
    
    private Invoker invoker;
    
    private int port;
    
    private final static String MIF_METADATA_DIR = ".MIF";
    
    private final static String MIF_MODIFIED_FILES_LISTING = "MIF.modified.file.list";
    
    @Before
    public void setUp() throws ExecutionException {
    	userName = System.getProperty(TestProperties.MIF_CLIENT_USER_NAME);
    	userPassword = System.getProperty(TestProperties.MIF_CLIENT_PASSWORD);
        rCommand = System.getProperty(TestProperties.MIF_R_EXECUTABLE);
        nonmemCommand = System.getProperty(TestProperties.MIF_NONMEM_EXECUTABLE);
        String portProp = System.getProperty(TestProperties.MIF_JSCH_PORT);
        Preconditions.checkNotNull(rCommand, "The "+TestProperties.MIF_R_EXECUTABLE+" property is not set");
        Preconditions.checkNotNull(nonmemCommand, "The "+TestProperties.MIF_NONMEM_EXECUTABLE+" property is not set");
    	Preconditions.checkNotNull(userName, "The "+TestProperties.MIF_CLIENT_USER_NAME+" property is not set");
    	Preconditions.checkNotNull(userPassword, "The "+TestProperties.MIF_CLIENT_PASSWORD+" property is not set");
        Preconditions.checkNotNull(portProp, "The "+TestProperties.MIF_JSCH_PORT+" property is not set");
        taskExecutionManager.setExecutionMessageHandler(this);
        port = Integer.parseInt(portProp);
        InvokerFactory invokerFactory = new InvokerFactory();
        invokerFactory.setPort(port);
        invoker = invokerFactory.createRunAsUserInvoker(userName, userPassword);
        sharedLocationManager.setRunInRequestDirectory(Boolean.parseBoolean(System.getProperty("mif.runInRequestDir")));
    }

    @AfterClass
    public static void afterAllTests() throws Exception {
        File resultsDir = new File(TestsHelper.generateTempDirectoryPath("-MIFClientTestDataSetsSGETestOnServer"));
        for(Entry<String,String> en : requestOutputDirs.entrySet()) {
            File outputDir = new File(en.getValue());
            File requestResultDir = new File(resultsDir,outputDir.getName());
            FileUtils.copyDirectory(outputDir,requestResultDir);
            if(resultsMap.containsKey(en.getKey())) {
                FileUtils.writeStringToFile(new File(requestResultDir,
                        "executionResponse.xml"), JAXBUtils.marshall(resultsMap.get(en.getKey()),
                        ExecutionJaxbUtils.CONTEXT_CLASSES), "UTF-8");
            }
            FileUtils.writeStringToFile(new File(requestResultDir,
                    "executionRequest.xml"), JAXBUtils.marshall(requestMap.get(en.getKey()),
                    ExecutionJaxbUtils.CONTEXT_CLASSES), "UTF-8");

            LOG.info("Request  " + en.getKey() + " results stored in " + requestResultDir);
        }
    }
    

    @DirtiesContext
    @Test
    public void shouldCancelRJobOnMIF() throws InterruptedException, JAXBException, IOException, MIFException {
        outputDirectory = TestsHelper.createTmpDirectory("MIFClientTestDataSetsSGETestOnServer-shouldCancelRJobOnMIF");
        File[] testDataSetsDirs = TestsHelper.getRTestDataSetsDirectories();
        File dataSetDir = testDataSetsDirs[0];
        String requestUUID = sendRequest(ExecutionRequestHelper.createRExecutionRequestBuilderForInputDirectory(createResourcePublisher(dataSetDir), dataSetDir).setCommand(rCommand), ExecutionType.R_Script.name(),true);
        CountDownLatch runningStateSignal = new CountDownLatch(1);
        LOG.debug("Waiting for the request: "  + requestUUID + " to reach Running state");
        final JobStatusWaiter waiter = new JobStatusWaiter(requestUUID,JobStatus.RUNNING,runningStateSignal,jobService);
        new Thread() {
            public void run() {
                waiter.run();
            };
        }.start();
        
        runningStateSignal.await();
        LOG.debug("Cancelling the request: " + requestUUID);
        taskExecutionManager.cancelRequest(requestUUID);
        LOG.debug("Cancellation request sent");

        monitorProgress(requestUUID);
        
        assertEquals(JobStatus.CANCELLED.name(),resultsMap.get(requestUUID).getStatus());
    }

    @DirtiesContext
    @Test
    public void shouldCancelBootstrapJobOnMIF() throws InterruptedException, JAXBException, IOException, MIFException {
        outputDirectory = TestsHelper.createTmpDirectory("MIFClientTestDataSetsSGETestOnServer-shouldCancelBootstrapJobOnMIF");
        File scriptFile = FileUtils.toFile(MIFClientTestDataSetsSGETestOnServer.class.getResource("/testData/PsN_Bootstrap/scm_test.mod"));
        File dataSetDir = scriptFile.getParentFile();
        ExecutionRequestBuilder requestBuilder = ExecutionRequestHelper.createExecutionRequestBuilderForInputDirectory(createResourcePublisher(dataSetDir), dataSetDir,scriptFile.getName());
        requestBuilder.setExecutionParameters("-run_on_sge -samples=10 -threads=5 -directory=bs -crash_restarts=0")
                    .setCommand("bootstrap");
        String requestUUID = sendRequest(requestBuilder, ExecutionType.PsN_Bootstrap.name(),true);

        CountDownLatch runningStateSignal = new CountDownLatch(1);
        LOG.debug("Waiting for the request: "  + requestUUID + " to reach Running state");
        final JobStatusWaiter waiter = new JobStatusWaiter(requestUUID,JobStatus.RUNNING,runningStateSignal,jobService);
        new Thread() {
            public void run() {
                waiter.run();
            };
        }.start();
        
        runningStateSignal.await();
        LOG.debug("Cancelling the request: " + requestUUID);
        taskExecutionManager.cancelRequest(requestUUID);
        LOG.debug("Cancellation request sent");

        monitorProgress(requestUUID);
        
        assertEquals(JobStatus.CANCELLED.name(),resultsMap.get(requestUUID).getStatus());
    }
    @DirtiesContext
    @Test(timeout = 300000)
    public void shouldRunRTestDataSetsOnMIF() throws Exception {
        outputDirectory = TestsHelper.createTmpDirectory("MIFClientTestDataSetsSGETestOnServer-shouldRunRTestDataSetsOnMIF");
        File[] testDataSetsDirs = TestsHelper.getRTestDataSetsDirectories();
        File dataSetDir = testDataSetsDirs[0];
        String requestUUID = sendRequest(ExecutionRequestHelper.createRExecutionRequestBuilderForInputDirectory(createResourcePublisher(dataSetDir), dataSetDir).setCommand(rCommand), ExecutionType.R_Script.name(),true);
        requestOutputDirs.put(requestUUID,outputDirectory.getAbsolutePath());

        monitorProgress(requestUUID);
        
        copyResultsToOutputDirectory(requestUUID);
        LOG.info("Results are available in :" + outputDirectory);
        assertEquals(JobStatus.COMPLETED.name(),resultsMap.get(requestUUID).getStatus());
    }

    @DirtiesContext
    @Test(timeout = 300000)
    public void shouldRunRTestDataSetsOnMIFAsServiceUser() throws Exception {
        outputDirectory = TestsHelper.createTmpDirectory("MIFClientTestDataSetsSGETestOnServer-shouldRunRTestDataSetsOnMIFAsServiceUser");
        File[] testDataSetsDirs = TestsHelper.getRTestDataSetsDirectories();
        File dataSetDir = testDataSetsDirs[0];
        String requestUUID = sendRequest(ExecutionRequestHelper.createRExecutionRequestBuilderForInputDirectory(createResourcePublisher(dataSetDir), dataSetDir).setCommand(rCommand), ExecutionType.R_Script.name(),false);
        requestOutputDirs.put(requestUUID,outputDirectory.getAbsolutePath());

        monitorProgress(requestUUID);
        
        copyResultsToOutputDirectory(requestUUID);
        LOG.info("Results are available in :" + outputDirectory);
        assertEquals(JobStatus.COMPLETED.name(),resultsMap.get(requestUUID).getStatus());
    }
    
    @DirtiesContext
    @Test(timeout = 300000)
    public void shouldRunNONMEMTestDataSetsOnMIF() throws Exception {
        outputDirectory = TestsHelper.createTmpDirectory("MIFClientTestDataSetsSGETestOnServer-shouldRunNONMEMTestDataSetsOnMIF");
        File scriptFile = FileUtils.toFile(MIFClientTestDataSetsSGETestOnServer.class.getResource("/testData/NONMEM/TestData1.ctl"));
        File dataSetDir = scriptFile.getParentFile();
        String requestUUID = sendRequest(ExecutionRequestHelper.createExecutionRequestBuilderForInputDirectory(createResourcePublisher(dataSetDir), dataSetDir,scriptFile.getName()).setCommand(nonmemCommand), ExecutionType.NMFE.name(),true);
        requestOutputDirs.put(requestUUID,outputDirectory.getAbsolutePath());

        while(!signalsMap.get(requestUUID).await(DETAIL_STATUS_POLL_RATE, TimeUnit.SECONDS)) {
            LOG.debug("Retrieving DetailedStatus for request:" + requestUUID);
            DetailedStatus detailedStatus;
            try {
                detailedStatus = jobService.getNONMEMDetailedStatus(requestUUID);
            } catch (MIFException e) {
                LOG.error("Couldn't retrieve detailed status for a job " + requestUUID, e);
                continue;
            }
                if(detailedStatus!=null) {
                    LOG.debug(detailedStatus.asString());
                } else {
                    LOG.error("Detailed status for request " + requestUUID + " was null");
                }
        }
        
        copyResultsToOutputDirectory(requestUUID);
        LOG.info("Results are available in :" + outputDirectory);
        assertEquals(JobStatus.COMPLETED.name(),resultsMap.get(requestUUID).getStatus());
    }
    @DirtiesContext
    @Test(timeout = 700000)
    public void shouldRunPsNVPCTestDataSetsOnMIF() throws Exception {
        outputDirectory = TestsHelper.createTmpDirectory("MIFClientTestDataSetsSGETestOnServer-shouldRunPsNVPCTestDataSetsOnMIF");
        File scriptFile = FileUtils.toFile(MIFClientTestDataSetsSGETestOnServer.class.getResource("/testData/PsN_VPC/scm_test.mod"));
        File dataSetDir = scriptFile.getParentFile();
        ExecutionRequestBuilder requestBuilder = ExecutionRequestHelper.createExecutionRequestBuilderForInputDirectory(createResourcePublisher(dataSetDir), dataSetDir,scriptFile.getName());
        requestBuilder.setExecutionParameters("-samples=300 -run_on_sge -threads=5 -bin_by_count=1 -no_of_bins=10 -seed=12345 -directory=vpc -crash_restarts=0")
                    .setCommand("vpc");
        String requestUUID = sendRequest(requestBuilder, ExecutionType.PsN_VPC.name(),true);
        requestOutputDirs.put(requestUUID,outputDirectory.getAbsolutePath());

        while(!signalsMap.get(requestUUID).await(DETAIL_STATUS_POLL_RATE, TimeUnit.SECONDS)) {
            LOG.debug("Retrieving DetailedStatus for request:" + requestUUID);
            DetailedStatus detailedStatus;
            try {
                detailedStatus = jobService.getPsNVPCDetailedStatus(requestUUID);
            } catch (MIFException e) {
                LOG.error("Couldn't retrieve detailed status for a job " + requestUUID, e);
                continue;
            }
            if(detailedStatus!=null) {
                LOG.debug(detailedStatus.asString());
            } else {
                LOG.error("Detailed status for request " + requestUUID + " was null");
            }
        }
         
        copyResultsToOutputDirectory(requestUUID);
        LOG.info("Results are available in :" + outputDirectory);
        assertEquals(JobStatus.COMPLETED.name(),resultsMap.get(requestUUID).getStatus());
    }
    
    @DirtiesContext
    @Test(timeout = 300000)
    public void shouldRunPsNSCMTestDataSetsOnMIF() throws Exception {
        outputDirectory = TestsHelper.createTmpDirectory("MIFClientTestDataSetsSGETestOnServer-shouldRunPsNSCMTestDataSetsOnMIF");
        File scriptFile = FileUtils.toFile(MIFClientTestDataSetsSGETestOnServer.class.getResource("/testData/PsN_SCM/scm_test.mod"));
        File dataSetDir = scriptFile.getParentFile();
        ExecutionRequestBuilder requestBuilder = ExecutionRequestHelper.createExecutionRequestBuilderForInputDirectory(createResourcePublisher(dataSetDir), dataSetDir,scriptFile.getName());
        requestBuilder.setExecutionParameters("-config_file=scm_linear.config -run_on_sge -threads=5 -directory=linear -crash_restarts=0")
                    .setCommand("scm");
        
        String requestUUID = sendRequest(requestBuilder, ExecutionType.PsN_SCM.name(),true);
        requestOutputDirs.put(requestUUID,outputDirectory.getAbsolutePath());

        while(!signalsMap.get(requestUUID).await(DETAIL_STATUS_POLL_RATE, TimeUnit.SECONDS)) {
            LOG.debug("Retrieving DetailedStatus for request:" + requestUUID);
            DetailedStatus detailedStatus;
            detailedStatus = jobService.getPsNSCMDetailedStatus(requestUUID);

            if (detailedStatus != null) {
                LOG.debug(detailedStatus.asString());
            } else {
                LOG.error("Detailed status for request " + requestUUID + " was null");
            }
            byte[] scmLogFile = jobService.getSCMLogFileContent(requestUUID);
            if(scmLogFile!=null) {
                LOG.debug(new String(scmLogFile, Charset.forName("UTF-8")));
            } else {
                LOG.debug("Could not retrieve scmlog content");
            }
        }
        
        copyResultsToOutputDirectory(requestUUID);
        LOG.info("Results are available in :" + outputDirectory);
        assertEquals(JobStatus.COMPLETED.name(),resultsMap.get(requestUUID).getStatus());
    }
    @DirtiesContext
    @Test(timeout = 400000)
    public void shouldRunPsNBootstrapTestDataSetsOnMIF() throws Exception {
        outputDirectory = TestsHelper.createTmpDirectory("MIFClientTestDataSetsSGETestOnServer-shouldRunPsNBootstrapTestDataSetsOnMIF");
        File scriptFile = FileUtils.toFile(MIFClientTestDataSetsSGETestOnServer.class.getResource("/testData/PsN_Bootstrap/scm_test.mod"));
        File dataSetDir = scriptFile.getParentFile();

        ExecutionRequestBuilder requestBuilder = ExecutionRequestHelper.createExecutionRequestBuilderForInputDirectory(createResourcePublisher(dataSetDir), dataSetDir,scriptFile.getName());
        requestBuilder.setExecutionParameters("-run_on_sge -samples=10 -threads=5 -directory=bs -crash_restarts=0")
                    .setCommand("bootstrap");
        String requestUUID = sendRequest(requestBuilder, ExecutionType.PsN_Bootstrap.name(),true);
        requestOutputDirs.put(requestUUID,outputDirectory.getAbsolutePath());

        while (!signalsMap.get(requestUUID).await(DETAIL_STATUS_POLL_RATE, TimeUnit.SECONDS)) {
            LOG.debug("Retrieving DetailedStatus for request:" + requestUUID);
            DetailedStatus detailedStatus;

            detailedStatus = jobService.getPsNBootstrapDetailedStatus(requestUUID);

            if (detailedStatus != null) {
                LOG.debug(detailedStatus.asString());
            } else {
                LOG.error("Detailed status for request " + requestUUID + " was null");
            }
        }
        
        copyResultsToOutputDirectory(requestUUID);
        LOG.info("Results are available in :" + outputDirectory);
        assertEquals(JobStatus.COMPLETED.name(),resultsMap.get(requestUUID).getStatus());
    }
    
    @DirtiesContext
    @Test(timeout = 400000)
    public void shouldCancelPsNBootstrapChildJob() throws Exception {
        outputDirectory = TestsHelper.createTmpDirectory("MIFClientTestDataSetsSGETestOnServer-shouldCancelPsNBootstrapChildJob");
        File scriptFile = FileUtils.toFile(MIFClientTestDataSetsSGETestOnServer.class.getResource("/testData/PsN_Bootstrap/scm_test.mod"));
        File dataSetDir = scriptFile.getParentFile();
        MIFResponse childJobsKillRequestResponse = null;
        ExecutionRequestBuilder requestBuilder = ExecutionRequestHelper.createExecutionRequestBuilderForInputDirectory(createResourcePublisher(dataSetDir), dataSetDir,scriptFile.getName());
        requestBuilder.setExecutionParameters("-run_on_sge -samples=10 -threads=5 -directory=bs -crash_restarts=0")
                    .setCommand("bootstrap");
        String requestUUID = sendRequest(requestBuilder, ExecutionType.PsN_Bootstrap.name(),true);
        requestOutputDirs.put(requestUUID,outputDirectory.getAbsolutePath());
        
        boolean done = false;
        
        while (!signalsMap.get(requestUUID).await(DETAIL_STATUS_POLL_RATE, TimeUnit.SECONDS) && !done) {
            LOG.debug("Retrieving DetailedStatus for request:" + requestUUID);
            PsNBootstrapProcessingDetailedStatus detailedStatus;

            detailedStatus = jobService.getPsNBootstrapDetailedStatus(requestUUID);

            if (detailedStatus != null) {
                LOG.debug(detailedStatus.asString());
                
                if(detailedStatus.getActiveChildNONMEMProcesses().size()>0) {
                    List<String> sgeJobIds = Lists.newArrayList();
                    for(NONMEMProcessingDetailedStatus childNONMEMRun : detailedStatus.getActiveChildNONMEMProcesses()) {
                        sgeJobIds.add(childNONMEMRun.getGridJobId());
                    }

                    LOG.debug("Cancelling bootstrap SGE child jobs : " + sgeJobIds);
                    childJobsKillRequestResponse = jobService.killPsNBootstrapChildSGEJobs(requestUUID, sgeJobIds);
                    LOG.debug("Cancellation response : " + childJobsKillRequestResponse.getStatus() + " error message " + childJobsKillRequestResponse.getErrorMessage());
                    done = true;
                }
                
            } else {
                LOG.error("Detailed status for request " + requestUUID + " was null");
            }
        }
        
        //kill the parent job
        if(done) {
            taskExecutionManager.cancelRequest(requestUUID);
            if(ResponseStatus.FAILURE.equals(childJobsKillRequestResponse.getStatus())) {
                fail("Cancellation of child bootstrap jobs failed, reason: " + childJobsKillRequestResponse.getErrorMessage() + " - mind that, it might have failed because requested job had been finished by the time killing request was received.");
            }
        } else {
            fail("FAILED to kill bootstrap child jobs - no child have been spawned by the bootsrap");
        }
        
    }

    @DirtiesContext
    @Test(timeout = 300000)
    public void shouldRunCMDTestDataSetsOnMIF() throws Exception {
        outputDirectory = TestsHelper.createTmpDirectory("MIFClientTestDataSetsSGETestOnServer-shouldRunCMDTestDataSetsOnMIF");
        File file = FileUtils.toFile(MIFClientTestDataSetsSGETestOnServer.class.getResource("/testData/CMD/listFilesInEtcDir.sh"));
        String command = FileUtils.readFileToString(file);
        file.setExecutable(true); //make sure that it is executable
        File dataSetDir = file.getParentFile();
        String requestUUID = sendRequest(ExecutionRequestHelper.createExecutionRequestBuilderForInputDirectory(createResourcePublisher(dataSetDir), dataSetDir, "").setExecutionParameters(command), ExecutionType.COMMAND_LINE.name(),true);
        requestOutputDirs.put(requestUUID,outputDirectory.getAbsolutePath());

        monitorProgress(requestUUID);
        
        copyResultsToOutputDirectory(requestUUID);
        LOG.info("Results are available in :" + outputDirectory);
        
        assertEquals(JobStatus.COMPLETED.name(),resultsMap.get(requestUUID).getStatus());
    }
    
    /**
     * Polls the job service for detailed status updates for a given job
     * @param requestUUID
     * @throws InterruptedException
     * @throws JAXBException 
     */
    private void monitorProgress(String requestUUID) throws InterruptedException, JAXBException {
        while (!signalsMap.get(requestUUID).await(DETAIL_STATUS_POLL_RATE, TimeUnit.SECONDS)) {
            LOG.debug("Retrieving DetailedStatus for request:" + requestUUID);
            DetailedStatus detailedStatus;
            try {
                detailedStatus = jobService.getDetailedStatus(requestUUID);
            } catch (MIFException e) {
                LOG.error("Couldn't retrieve detailed status for a job " + requestUUID, e);
                continue;
            }
            if (detailedStatus != null) {
                LOG.debug(detailedStatus.asString());
            } else {
                LOG.error("Detailed status for request " + requestUUID + " was null");
            }
        }
    }


    /**
     * Copies results to a output directory
     * 
     * @throws IOException 
     * @throws JAXBException 
     * @throws ExecutionException 
     */
    private void copyResultsToOutputDirectory(String requestUUID) throws Exception {
        createResourceCopier(requestMap.get(requestUUID)).copy(null);
    }
    
    private ResourceCopier createResourceCopier(ExecutionRequest executionRequest) throws Exception {
        ResourceCopierParameters resourceCopierParameters = new ResourceCopierParameters();
        resourceCopierParameters.setDestDirectory(new File(requestOutputDirs.get(executionRequest.getRequestId())));
        resourceCopierParameters.setInvoker(invoker);
        resourceCopierParameters.setSourceDirectory(sharedLocationManager.getRequestOutputDirectory(executionRequest.getRequestId()));
        File mifMetadataDir = new File(sharedLocationManager.getRequestOutputDirectory(executionRequest.getRequestId()),MIF_METADATA_DIR);
        File modifiedFilesListing = new File(mifMetadataDir,MIF_MODIFIED_FILES_LISTING);
        if(!new InvokerHelper(invoker).fileExists(modifiedFilesListing.getAbsolutePath())) throw new Exception("File " + modifiedFilesListing + " does not exist.");
        resourceCopierParameters.setFilesListing(modifiedFilesListing);
        resourceCopierCommandBuilder.populateCommandBuilderContext(executionRequest.getRequestAttributes(), executionRequest.getSubmitHostPreamble(), executionRequest.getGridHostPreamble());
        resourceCopierParameters.setCommandBuilder(resourceCopierCommandBuilder);
        ArrayList<String> directoriesToIgnoreList = new ArrayList<String>();
        directoriesToIgnoreList.add(MIF_METADATA_DIR);
        resourceCopierParameters.setDirectoryIgnoreList(directoriesToIgnoreList);

        return resourceCopierFactory.create(resourceCopierParameters);
    }
    

    private BaseResourcePublisher createResourcePublisher(File rootDirectory) {
        PublisherParameters parameters = new PublisherParameters();
        parameters.setSharedLocationManager(sharedLocationManager);
        parameters.setInvoker(invoker);
        parameters.setSubmitHostPreamble("");
        Map<String,String> requestAttributes = Maps.newHashMap();
        parameters.setRequestAttributes(requestAttributes);
        parameters.setRootDirectory(rootDirectory);
        parameters.setCommandBuilder(resourcePublisherCommandBuilder);
        
        return new ShellBasedResourcePublisher(parameters);
    }
    
    /**
     * Prepares execution request and passes it to MIF-client components for submitting
     * @return
     * @throws JAXBException
     * @throws MalformedURLException 
     * @throws MIFException 
     */
    private String sendRequest(ExecutionRequestBuilder executionRequestBuilder, String executionType, boolean submitAsUser) throws JAXBException, MalformedURLException, MIFException {
        executionRequestBuilder.setExecutionType(executionType);
        executionRequestBuilder.setUserName(userName);
        executionRequestBuilder.setUserPassword(userPassword).setSubmitAsUserMode(submitAsUser);
        executionRequestBuilder.setGridHostPreamble(System.getProperty("mif.grid.preamble"));
        Map<String,String> requestAttributes = Maps.newHashMap();
        requestAttributes.put("QSUB_PARAMETERS",System.getProperty("mif.sge.qsubParameters"));
        executionRequestBuilder.setRequestAttributes(requestAttributes);
        ExecutionRequest executionRequest = executionRequestBuilder.getExecutionRequest();
        signalsMap.put(executionRequest.getRequestId().toString(), new CountDownLatch(1));
        taskExecutionManager.submit(executionRequest);
        requestMap.put(executionRequest.getRequestId().toString(), executionRequest);
        return executionRequest.getRequestId().toString();
    }


    @Override
    public void handle(ExecutionResponse executionResponse) {
        if(!requestMap.containsKey(executionResponse.getRequestId().toString())) {
            LOG.info("Received message that was not expected, ID " + executionResponse.getRequestId() + ".");
        	return;
        }
        LOG.info("Received message for handling " + executionResponse.getRequestId() + " for handling.");
        resultsMap.put(executionResponse.getRequestId().toString(), executionResponse);
        signalsMap.get(executionResponse.getRequestId().toString()).countDown();
    }

}
