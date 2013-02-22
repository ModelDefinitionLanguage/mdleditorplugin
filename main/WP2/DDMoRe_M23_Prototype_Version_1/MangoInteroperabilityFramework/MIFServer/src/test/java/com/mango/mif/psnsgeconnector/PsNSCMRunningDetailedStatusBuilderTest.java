/* File			:  PsNSCMLinearRunningDetailedStatusBuilderTest.java
 * Project		:  MIFServer
 * Created on	:  Aug 10, 2012
 */
package com.mango.mif.psnsgeconnector;

import static org.junit.Assert.*;

import java.io.File;
import java.util.HashMap;
import java.util.UUID;

import static org.hamcrest.CoreMatchers.equalTo;
import javax.xml.bind.JAXBException;


import org.apache.commons.io.FileUtils;
import org.junit.Before;
import org.junit.Test;

import static org.mockito.Matchers.any;
import static org.mockito.Mockito.*;


import com.google.common.base.Preconditions;
import com.google.common.collect.Maps;
import com.mango.mif.connector.TestsHelper;
import com.mango.mif.connector.domain.Job;
import com.mango.mif.connector.runner.status.SummaryMessageBuilderTest;
import com.mango.mif.core.exec.ExecutionException;
import com.mango.mif.core.exec.template.ClasspathFreemarkerTemplateLocator;
import com.mango.mif.core.exec.template.JobAwareFreemarkerTemplateCommandBuilder;
import com.mango.mif.domain.DetailedStatus;
import com.mango.mif.domain.ExecutionType;
import com.mango.mif.sge.SGE;
import com.mango.mif.utils.TestProperties;
/**
 * @version $Revision: $ as of $Date: $
 * <p>SVN Entry : $HeadURL: $
 * <p>SVN ID    : $Id: $
 * <p>Last edited by : $Author: $
 * 
 * Tests PsNSCMRunningDetailedStatusBuilder
 * 
 */
public class PsNSCMRunningDetailedStatusBuilderTest extends SummaryMessageBuilderTest {
    /**
     * Control file
     */
    private String ctlFileName = "scm_test.mod";
    /**
     * Summary message template
     */
    private String commandTemplate = "psnscmsgeconnector/running-summary-message.ftl";
    private String psnconnectorScripts;
   
    @Override
    @Before
    public void setUp() throws Exception {
        super.setUp();
        psnconnectorScripts = System.getProperty(TestProperties.MIF_PSNSGE_CONNECTOR_SCRIPTS_LOCATION);
        Preconditions.checkNotNull(psnconnectorScripts, TestProperties.MIF_PSNSGE_CONNECTOR_SCRIPTS_LOCATION + " property is not set.");
    }
    
    
    private Job prepareJob(String workingDirectory) {
        job = TestsHelper.createJob("TEST-JOB", UUID.randomUUID().toString());
        job.setUserName("USER NAME");
        job.setSharedLocationManager(sharedLocationManager);
        job.setInvoker(invoker);
        
        job.getExecutionRequest().setExecutionFile(ctlFileName);
        job.getExecutionRequest().setType(ExecutionType.PsN_SCM.name());
        job.addToDataMap(SGE.SGE_SUBMIT_SCRIPT_KEY,"scm-linear-test.sh");

        File workingDir = FileUtils.toFile(PsNSCMRunningDetailedStatusBuilderTest.class.getResource(workingDirectory));
        job.setGridJobDirectory(workingDir.getAbsolutePath());
        when(sharedLocationManager.getRequestInputDirectory((String) any())).thenReturn(workingDir);
        when(sharedLocationManager.getRequestOutputDirectory((String) any())).thenReturn(workingDir);
        return job;
    }
    
    private PsNSCMCompletedDetailedStatusBuilder prepareDetailedStatusBuilder() throws ExecutionException {
        //when
        PsNSCMCompletedDetailedStatusBuilder detailedStatusBuilder = new PsNSCMCompletedDetailedStatusBuilder();
        ClasspathFreemarkerTemplateLocator templateLocator = new ClasspathFreemarkerTemplateLocator();
        templateLocator.setCommandTemplateDirectory(TEMPLATES);

        HashMap<String, Object> initialContext = Maps.newHashMap();
        initialContext.put("CONNECTOR_UTILS", genericScripts);
        initialContext.put("MANGO_UTILS", commonScripts);
        initialContext.put("PSN_SGECONNECTOR_UTILS", psnconnectorScripts);
        initialContext.put("NONMEMSGE_CONNECTOR_UTILS", connectorScripts);
        initialContext.put("REQUEST_ATTRIBUTE_BLOCK", "");
        initialContext.put("job", job);

        JobAwareFreemarkerTemplateCommandBuilder summaryCommandBuilder = new JobAwareFreemarkerTemplateCommandBuilder(initialContext);
        summaryCommandBuilder.setTemplateLocator(templateLocator);
        summaryCommandBuilder.setTemplate(commandTemplate);
        detailedStatusBuilder.setSummaryCommandBuilder(summaryCommandBuilder);
        detailedStatusBuilder.setJob(job);
        return detailedStatusBuilder;
    }
    
    @Test
    public void shouldGenerateDetailedStatusForEstimateBaseModelRunningWithForwardAndBackward() throws ExecutionException, JAXBException {

        //Given
        prepareJob("/testData/outputs/PsN_SCM_NON_LIN/scm-non-linear-running-base-model");
        job.getExecutionRequest().setExecutionParameters("-directory=non_linear");
        PsNSCMCompletedDetailedStatusBuilder detailedStatusBuilder = prepareDetailedStatusBuilder();
        //when
        DetailedStatus summary = detailedStatusBuilder.getDetailedStatus();
        logger.debug(summary.asString());
        
        //then
        assertNotNull("Summary message produced was null",summary);
        assertThat(summary.getSummary(), equalTo("Base Model Running"));
    }
    
    @Test
    public void shouldGenerateDetailedStatusForBackwardRunningWithForward() throws ExecutionException, JAXBException {

        //Given
        prepareJob("/testData/outputs/PsN_SCM_NON_LIN/scm-non-linear-running-backward");
        job.getExecutionRequest().setExecutionParameters("-directory=non_linear");
        PsNSCMCompletedDetailedStatusBuilder detailedStatusBuilder = prepareDetailedStatusBuilder();
        //when
        DetailedStatus summary = detailedStatusBuilder.getDetailedStatus();
        logger.debug(summary.asString());
        
        //then
        assertNotNull("Summary message produced was null",summary);
        assertThat(summary.getSummary(), equalTo("Base Model Completed - Forward Search Completed - Backward Search Running"));
    }
    
    @Test
    public void shouldGenerateDetailedStatusForwardRunning() throws ExecutionException, JAXBException {

        //Given
        prepareJob("/testData/outputs/PsN_SCM_LIN/scm-linear-running");
        job.getExecutionRequest().setExecutionParameters("-directory=linear");
        PsNSCMCompletedDetailedStatusBuilder detailedStatusBuilder = prepareDetailedStatusBuilder();
        //when
        DetailedStatus summary = detailedStatusBuilder.getDetailedStatus();
        logger.debug(summary.asString());
        
        //then
        assertNotNull("Summary message produced was null",summary);
        assertThat(summary.getSummary(), equalTo("Base Model Completed - Forward Search Running"));
    }

    @Test
    public void shouldGenerateDetailedStatusForForwardComplete() throws ExecutionException, JAXBException {

        //Given
        prepareJob("/testData/outputs/PsN_SCM_NON_LIN/scm-non-linear-running-forward-complete");
        job.getExecutionRequest().setExecutionParameters("-directory=non_linear");
        PsNSCMCompletedDetailedStatusBuilder detailedStatusBuilder = prepareDetailedStatusBuilder();
        //when
        DetailedStatus summary = detailedStatusBuilder.getDetailedStatus();
        logger.debug(summary.asString());
        
        //then
        assertNotNull("Summary message produced was null",summary);
        assertThat(summary.getSummary(), equalTo("Base Model Completed - Forward Search Completed"));
    }
    

    @Test
    public void shouldGenerateDetailedStatusForBackwardCompleteWithForward() throws ExecutionException, JAXBException {

        //Given
        prepareJob("/testData/outputs/PsN_SCM_NON_LIN/scm-non-linear-completed");
        job.getExecutionRequest().setExecutionParameters("-directory=non_linear");
        PsNSCMCompletedDetailedStatusBuilder detailedStatusBuilder = prepareDetailedStatusBuilder();
        //when
        DetailedStatus summary = detailedStatusBuilder.getDetailedStatus();
        logger.debug(summary.asString());
        
        //then
        assertNotNull("Summary message produced was null",summary);
        assertThat(summary.getSummary(), equalTo("Base Model Completed - Forward Search Completed - Backward Search Completed"));
    }
    
    @Test
    public void shouldGenerateDetailedStatusForRunningWhenErrStreamFileIsStillEmpty() throws ExecutionException, JAXBException {

        //Given
        prepareJob("/testData/outputs/PsN_SCM_NON_LIN/scm-non-linear-running-empty-stderr");
        job.getExecutionRequest().setExecutionParameters("-directory=non_linear");
        PsNSCMCompletedDetailedStatusBuilder detailedStatusBuilder = prepareDetailedStatusBuilder();
        //when
        DetailedStatus summary = detailedStatusBuilder.getDetailedStatus();
        logger.debug(summary.asString());
        
        //then
        assertNotNull("Summary message produced was null",summary);
        assertThat(summary.getSummary(), equalTo("STARTUP"));
    }

    @Test
    public void shouldGenerateDetailedStatusForBackwardRunningWithoutForward() throws ExecutionException, JAXBException {

        //Given
        prepareJob("/testData/outputs/PsN_SCM/onlyBackwards/backwardsRunning");
        job.getExecutionRequest().setExecutionParameters("-directory=nonLinear");
        PsNSCMCompletedDetailedStatusBuilder detailedStatusBuilder = prepareDetailedStatusBuilder();
        //when
        DetailedStatus summary = detailedStatusBuilder.getDetailedStatus();
        logger.debug(summary.asString());
        
        //then
        assertNotNull("Summary message produced was null",summary);
        assertThat(summary.getSummary(), equalTo("Base Model Completed - Backward Search Running"));
    }

    @Test
    public void shouldGenerateDetailedStatusForBaseModelRunningWithBackwardOnly() throws ExecutionException, JAXBException {

        //Given
        prepareJob("/testData/outputs/PsN_SCM/onlyBackwards/baseModel");
        job.getExecutionRequest().setExecutionParameters("-directory=non_linear");
        PsNSCMCompletedDetailedStatusBuilder detailedStatusBuilder = prepareDetailedStatusBuilder();
        //when
        DetailedStatus summary = detailedStatusBuilder.getDetailedStatus();
        logger.debug(summary.asString());
        
        //then
        assertNotNull("Summary message produced was null",summary);
        assertThat(summary.getSummary(), equalTo("Base Model Running"));
    }
}
