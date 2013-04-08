/* File			:  PsNVPCRunningDetailedStatusBuilderTest.java
 * Project		:  MIFServer
 * Created on	:  Aug 10, 2012
 */
package com.mango.mif.psnsgeconnector;

import static org.hamcrest.CoreMatchers.equalTo;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertThat;
import static org.mockito.Matchers.any;
import static org.mockito.Mockito.when;

import java.io.File;
import java.util.HashMap;
import java.util.UUID;

import javax.xml.bind.JAXBException;

import org.apache.commons.io.FileUtils;
import org.junit.Before;
import org.junit.Test;

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
 * Tests PsNVPCRunningDetailedStatusBuilder
 * 
 */
public class PsNVPCRunningDetailedStatusBuilderTest extends SummaryMessageBuilderTest {
    /**
     * Test data
     */
    private String testData = "/testData/outputs/PsN_VPC/vpc-running";
    /**
     * Control file
     */
    private String ctlFileName = "scm_test.mod";
    /**
     * Summary message template
     */
    private String commandTemplate = "psnvpcsgeconnector/running-summary-message.ftl";
    private String psnconnectorScripts;
    
    @Override
    @Before
    public void setUp() throws Exception {
        super.setUp();
        psnconnectorScripts = System.getProperty(TestProperties.MIF_PSNSGE_CONNECTOR_SCRIPTS_LOCATION);
        Preconditions.checkNotNull(psnconnectorScripts, TestProperties.MIF_PSNSGE_CONNECTOR_SCRIPTS_LOCATION + " property is not set.");
    }
    
    private Job prepareJob() {
        job = TestsHelper.createJob("TEST-JOB", UUID.randomUUID().toString());
        job.setUserName("USER NAME");
        job.setSharedLocationManager(sharedLocationManager);
        job.setInvoker(invoker);
        
        job.getExecutionRequest().setExecutionFile(ctlFileName);
        job.getExecutionRequest().setType(ExecutionType.PsN_VPC.name());
        job.getExecutionRequest().setExecutionParameters("-directory=vpc");
        job.addToDataMap(SGE.SGE_SUBMIT_SCRIPT_KEY,"vpc-test.sh");
        File workingDir = FileUtils.toFile(PsNVPCRunningDetailedStatusBuilderTest.class.getResource(this.testData));
        job.setGridJobDirectory(workingDir.getAbsolutePath());
        job.getRunName(); //TODO: why are we calling this here?
        when(sharedLocationManager.getRequestInputDirectory((String)any())).thenReturn(workingDir);
        when(sharedLocationManager.getRequestOutputDirectory((String)any())).thenReturn(workingDir);
        return job;
    }    

    @Test
    public void shouldGenerateDetailedStatusForCompletedVPC() throws ExecutionException, JAXBException {
        //Given
        prepareJob();
        job.getExecutionRequest().setExecutionParameters("-directory=vpc");
        String testData = "/testData/outputs/PsN_VPC/vpc-running";
        File workingDir = FileUtils.toFile(PsNVPCCompletedDetailedStatusBuilderTest.class.getResource(testData));
        job.setGridJobDirectory(workingDir.getAbsolutePath());
        when(sharedLocationManager.getRequestInputDirectory((String) any())).thenReturn(workingDir);
        when(sharedLocationManager.getRequestOutputDirectory((String) any())).thenReturn(workingDir);
        //when
        PsNVPCRunningDetailedStatusBuilder messageBuilder = new PsNVPCRunningDetailedStatusBuilder();
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
        messageBuilder.setSummaryCommandBuilder(summaryCommandBuilder);
        messageBuilder.setJob(job);
        DetailedStatus summary = messageBuilder.getDetailedStatus();
        
        logger.debug(summary.asString());
        
        //then
        assertNotNull("Summary message produced was null",summary);
        assertThat(summary.getSummary(), equalTo("Simulating (48/1000)"));
    }
}