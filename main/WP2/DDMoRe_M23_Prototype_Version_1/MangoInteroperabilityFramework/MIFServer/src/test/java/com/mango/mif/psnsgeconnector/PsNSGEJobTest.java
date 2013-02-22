/* File			:  PsNSGEJobTest.java
 * Project		:  MIFServer
 * Created on	:  Aug 21, 2012
 */
package com.mango.mif.psnsgeconnector;

import static org.junit.Assert.assertEquals;

import java.io.File;
import java.io.IOException;
import java.util.HashMap;
import java.util.List;
import java.util.Properties;

import org.apache.commons.io.FileUtils;
import org.junit.Before;
import org.junit.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import com.google.common.base.Preconditions;
import com.google.common.collect.Maps;
import com.mango.mif.connector.domain.Job;
import com.mango.mif.core.api.SharedLocationManager;
import com.mango.mif.core.exec.ExecutionException;
import com.mango.mif.core.exec.Invoker;
import com.mango.mif.core.exec.invoker.InvokerFactory;
import com.mango.mif.core.exec.template.ClasspathFreemarkerTemplateLocator;
import com.mango.mif.core.exec.template.JobAwareFreemarkerTemplateCommandBuilder;
import com.mango.mif.domain.ExecutionRequest;
import com.mango.mif.domain.ExecutionRequestBuilder;
import com.mango.mif.domain.nonmemsgeconnector.NONMEMProcessingDetailedStatus;
import com.mango.mif.sge.SGE;
import com.mango.mif.utils.MIFProperties;
import com.mango.mif.utils.TestProperties;

/**
 * @version $Revision: $ as of $Date: $
 * <p>SVN Entry : $HeadURL: $
 * <p>SVN ID    : $Id: $
 * <p>Last edited by : $Author: $
 * 
 * Tests PsNSGEJobTest
 */
public class PsNSGEJobTest {
    /**
     * Location of the templates
     */
    private final static String TEMPLATES_LOCATION = "/runtime/templates";
    /**
     * User name with which send the job to a grid
     */
    private String userName;
    /**
     * Job ID
     */
    private String jobId;
    /**
     * Invoker
     */
    private Invoker invoker;
    /**
     * Job
     */
    private Job job;
    /**
     * Encrypted user password
     */
    private String encryptedUserPassword;
    /**
     * jobManager
     */
    private PsNSGEJob psnSgeJob;
    /**
     * SCRIPT_UTILS path
     */
    private String connectorUtilScriptsLocationVariable;
    /**
     * MANGO_UTILS path
     */
    private String mangoUtilScriptsLocationVariable;
    /**
     * PsN connector Utils path
     */
    private String psnConnectorUtilScriptsLocationVariable;
    @Mock
    private SharedLocationManager sharedLocationManager;
    private String nonmemConnectorUtilScriptsLocationVariable;

    @Before
    public void setUp() throws Exception {
        MockitoAnnotations.initMocks(this);
        Preconditions.checkNotNull(System.getProperty(TestProperties.MIF_COMMON_SCRIPTS_LOCATION), TestProperties.MIF_COMMON_SCRIPTS_LOCATION + " property not set");
        Preconditions.checkNotNull(System.getProperty(TestProperties.MIF_GENERIC_SCRIPTS_LOCATION), TestProperties.MIF_GENERIC_SCRIPTS_LOCATION + " property not set");
        Preconditions.checkNotNull(System.getProperty(TestProperties.MIF_PSNSGE_CONNECTOR_SCRIPTS_LOCATION), TestProperties.MIF_PSNSGE_CONNECTOR_SCRIPTS_LOCATION + " property not set");
        connectorUtilScriptsLocationVariable = System.getProperty(TestProperties.MIF_GENERIC_SCRIPTS_LOCATION);
        mangoUtilScriptsLocationVariable = System.getProperty(TestProperties.MIF_COMMON_SCRIPTS_LOCATION);
        psnConnectorUtilScriptsLocationVariable = System.getProperty(TestProperties.MIF_PSNSGE_CONNECTOR_SCRIPTS_LOCATION);
        nonmemConnectorUtilScriptsLocationVariable = System.getProperty(TestProperties.MIF_NONMEMSGE_CONNECTOR_SCRIPTS_LOCATION);

        //loading properties
        Properties properties = new Properties();
        properties.load(PsNSGEJobTest.class.getResourceAsStream("/com/mango/mif/sgeconnector/connector.properties"));

        MIFProperties.loadProperties(properties);

        InvokerFactory invokerFactory = new InvokerFactory();

        invoker = invokerFactory.createRunAsNavplusInvoker();
        job = new Job();
        job.setInvoker(invoker);
        job.setUserName(userName);
        job.setPassword(encryptedUserPassword);
        job.setSharedLocationManager(sharedLocationManager);

        ExecutionRequestBuilder requestBuilder = new ExecutionRequestBuilder();
        ExecutionRequest er = requestBuilder.getExecutionRequest();
        er.setExecutionParameters("-directory=bs");
        er.setExecutionFile("scm_test.mod");
        job.setExecutionRequest(er);

        createSGEJob();
    }

    /**
     * creates Job Manager
     * @throws ExecutionException 
     */
    private void createSGEJob() throws ExecutionException {
        MIFProperties properties = MIFProperties.getInstance();
        psnSgeJob = new PsNSGEJob(job);
        ClasspathFreemarkerTemplateLocator templateLocator = new ClasspathFreemarkerTemplateLocator();
        templateLocator.setCommandTemplateDirectory(TEMPLATES_LOCATION);

        HashMap<String, Object> initialContext = Maps.newHashMap();
        initialContext.put("CONNECTOR_UTILS", connectorUtilScriptsLocationVariable);
        initialContext.put("MANGO_UTILS", mangoUtilScriptsLocationVariable);
        initialContext.put("PSN_SGECONNECTOR_UTILS", psnConnectorUtilScriptsLocationVariable);
        initialContext.put("NONMEMSGE_CONNECTOR_UTILS", nonmemConnectorUtilScriptsLocationVariable);

        JobAwareFreemarkerTemplateCommandBuilder commandBuilder = new JobAwareFreemarkerTemplateCommandBuilder(initialContext);
        commandBuilder.setJob(job);
        commandBuilder.setTemplateLocator(templateLocator);
        commandBuilder.setTemplate(properties.getProperty("sgeconnector.retrieveChildJobsDetailsScriptTemplate"));
        psnSgeJob.setRetrieveChildJobsDetailsCommandBuilder(commandBuilder);

        commandBuilder = new JobAwareFreemarkerTemplateCommandBuilder(initialContext);
        commandBuilder.setJob(job);
        commandBuilder.setTemplateLocator(templateLocator);
        commandBuilder.setTemplate(properties.getProperty("sgeconnector.retrieveChildJobsIdsScriptTemplate"));
        psnSgeJob.setRetrieveChildJobsIdsCommandBuilder(commandBuilder);
    }

    @Test
    public void shouldListChildJobsOfAJob() throws ExecutionException, IOException, InterruptedException {
        File workingDirectory = FileUtils.toFile(PsNSGEJobTest.class.getResource("/testData/outputs/PsN_Bootstrap/bootstrap-running-parallel"));
        job.setGridJobDirectory(workingDirectory.getAbsolutePath());
        job.addToDataMap(SGE.SGE_JOB_ID_KEY, jobId);
        job.setJobId("1000-1000-1000-1000");

        List<NONMEMProcessingDetailedStatus> jobs = psnSgeJob.getChildJobsDetailed();

        assertEquals(5, jobs.size());
    }
}
