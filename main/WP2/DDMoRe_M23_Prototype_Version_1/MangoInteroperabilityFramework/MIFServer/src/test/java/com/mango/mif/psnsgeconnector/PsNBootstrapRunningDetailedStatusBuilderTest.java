/* File			:  PsNBootstrapRunningDetailedStatusBuilderTest.java
 * Project		:  MIFServer
 * Created on	:  Aug 10, 2012
 */
package com.mango.mif.psnsgeconnector;

import static org.hamcrest.CoreMatchers.equalTo;
import static org.junit.Assert.assertThat;
import static org.mockito.Matchers.any;
import static org.mockito.Mockito.when;

import java.io.File;
import java.util.HashMap;
import java.util.UUID;

import javax.annotation.Nullable;

import org.apache.commons.io.FileUtils;
import org.junit.Test;

import com.google.common.base.Predicate;
import com.google.common.collect.Iterables;
import com.google.common.collect.Maps;
import com.mango.mif.connector.TestsHelper;
import com.mango.mif.connector.domain.Job;
import com.mango.mif.connector.runner.status.SummaryMessageBuilderTest;
import com.mango.mif.core.exec.ExecutionException;
import com.mango.mif.core.exec.template.ClasspathFreemarkerTemplateLocator;
import com.mango.mif.core.exec.template.JobAwareFreemarkerTemplateCommandBuilder;
import com.mango.mif.domain.ExecutionType;
import com.mango.mif.domain.nonmemsgeconnector.NONMEMProcessingDetailedStatus;
import com.mango.mif.domain.psnsgeconnector.PsNBootstrapProcessingDetailedStatus;
import com.mango.mif.nonmemsgeconnector.NONMEMRunningDetailedStatusBuilderTest;
import com.mango.mif.utils.TestProperties;

/**
 * @version $Revision: $ as of $Date: $
 * <p>SVN Entry : $HeadURL: $
 * <p>SVN ID    : $Id: $
 * <p>Last edited by : $Author: $
 * 
 * Tests PsNBootstrapRunningDetailedStatusBuilder
 * 
 */
public class PsNBootstrapRunningDetailedStatusBuilderTest extends SummaryMessageBuilderTest {

    private Job prepareJob(String testData) {
        File workingDir = FileUtils.toFile(NONMEMRunningDetailedStatusBuilderTest.class.getResource(testData));
        job = TestsHelper.createJob("TEST-JOB", UUID.randomUUID().toString());
        job.setGridJobDirectory(workingDir.getAbsolutePath());
        job.setUserName("USER NAME");
        job.setSharedLocationManager(sharedLocationManager);
        job.setInvoker(invoker);

        job.getExecutionRequest().setType(ExecutionType.PsN_Bootstrap.name());

        when(sharedLocationManager.getRequestInputDirectory((String) any())).thenReturn(workingDir);
        when(sharedLocationManager.getRequestOutputDirectory((String) any())).thenReturn(workingDir);
        
        return job;
    }

    @Test
    public void shouldPopulateDetailedStatusStructureWithRequiredInformationWhen_2_Completed_2_Hanging_1_Pending() throws Exception {
        String testData = "/testData/outputs/PsN_Bootstrap/bootstrap-running-parallel";
        String ctlFileName = "scm_test.mod";
        PsNBootstrapProcessingDetailedStatus detailedStatus = createDetailedStatus(testData,ctlFileName, "-directory=bs -retries=2");

        logger.debug("summary " + detailedStatus.asString());

        assertThat(detailedStatus.getSummary(), equalTo("2 (0.0% Failed) of 10 / 2 hanging"));
        assertThat(detailedStatus.getActiveChildNONMEMProcesses().size(), equalTo(2));
        
        NONMEMProcessingDetailedStatus childRunStatus = Iterables.find(detailedStatus.getActiveChildNONMEMProcesses(), new Predicate<NONMEMProcessingDetailedStatus>() {
            @Override
            public boolean apply(@Nullable
            NONMEMProcessingDetailedStatus childRun) {
                return childRun.getGridJobId().equals("119180");
            }
        });
        assertThat(childRunStatus.getStatus(), equalTo("Hanging"));
        assertThat(childRunStatus.getSummary(), equalTo("FOCE INTER: 4015.863 (1/2)"));
        
        childRunStatus = Iterables.find(detailedStatus.getActiveChildNONMEMProcesses(), new Predicate<NONMEMProcessingDetailedStatus>() {
            @Override
            public boolean apply(@Nullable
            NONMEMProcessingDetailedStatus childRun) {
                return childRun.getGridJobId().equals("119182");
            }
        });
        assertThat(childRunStatus.getStatus(), equalTo("Hanging"));
        assertThat(childRunStatus.getSummary(), equalTo("FOCE INTER: 3019.459 (0/2)"));
    }
    
    
    @Test
    public void shouldPopulateDetailedStatusStructureWithRequiredInformationWhenAllChildJobCompleted() throws Exception {
        String testData = "/testData/outputs/PsN_Bootstrap/bootstrap-completed-parallel";
        String ctlFileName = "scm_test.mod";
        PsNBootstrapProcessingDetailedStatus detailedStatus = createDetailedStatus(testData,ctlFileName, "-directory=bs -retries=2");

        logger.debug("Summary " + detailedStatus.asString());

        assertThat(detailedStatus.getSummary(), equalTo("10 (0.0% Failed) of 10 / 0 hanging"));
        assertThat(detailedStatus.getActiveChildNONMEMProcesses().size(), equalTo(0));
    }
    
    @Test
    public void shouldPopulateDetailedStatusStructureWithRequiredInformationWhenSomeChildAreCancelled() throws Exception {

        String testData = "/testData/outputs/PsN_Bootstrap/bootstrap-with-cancelled";
        String ctlFileName = "scm_test.mod";
        PsNBootstrapProcessingDetailedStatus detailedStatus = createDetailedStatus(testData,ctlFileName, "-directory=bs -retries=2");

        logger.debug("Summary " + detailedStatus.asString());

        assertThat(detailedStatus.getSummary(), equalTo("3 (30.0% Failed) of 10 / 0 hanging"));
        assertThat(detailedStatus.getActiveChildNONMEMProcesses().size(), equalTo(1));
    }

    private PsNBootstrapProcessingDetailedStatus createDetailedStatus(String testData, String ctlFileName, String excecutionParameters) throws ExecutionException {
        //Given
        prepareJob(testData);
        job.getExecutionRequest().setExecutionFile(ctlFileName);
        job.getExecutionRequest().setExecutionParameters(excecutionParameters);


        ClasspathFreemarkerTemplateLocator templateLocator = new ClasspathFreemarkerTemplateLocator();
        templateLocator.setCommandTemplateDirectory(TEMPLATES);

        HashMap<String, Object> initialContext = Maps.newHashMap();
        initialContext.put("CONNECTOR_UTILS", System.getProperty(TestProperties.MIF_GENERIC_SCRIPTS_LOCATION));
        initialContext.put("MANGO_UTILS", System.getProperty(TestProperties.MIF_COMMON_SCRIPTS_LOCATION));
        initialContext.put("MANGO_TMP_DIR", "/tmp");
        initialContext.put("PSN_SGECONNECTOR_UTILS", System.getProperty(TestProperties.MIF_PSNSGE_CONNECTOR_SCRIPTS_LOCATION));
        initialContext.put("NONMEMSGE_CONNECTOR_UTILS", System.getProperty(TestProperties.MIF_NONMEMSGE_CONNECTOR_SCRIPTS_LOCATION));
        initialContext.put("REQUEST_ATTRIBUTE_BLOCK", "");
        initialContext.put("job", job);

        JobAwareFreemarkerTemplateCommandBuilder infoBuilder = new JobAwareFreemarkerTemplateCommandBuilder(initialContext);
        infoBuilder.setTemplateLocator(templateLocator);
        infoBuilder.setTemplate("generic/retrieveChildJobsDetails.ftl");

        JobAwareFreemarkerTemplateCommandBuilder summaryBuilder = new JobAwareFreemarkerTemplateCommandBuilder(initialContext);
        summaryBuilder.setTemplateLocator(templateLocator);
        summaryBuilder.setTemplate("psnbootstrapsgeconnector/running-summary-message.ftl");

        PsNBootstrapRunningDetailedStatusBuilder statusBuilder = new PsNBootstrapRunningDetailedStatusBuilder();
        statusBuilder.setJobInfoBuilder(infoBuilder);
        statusBuilder.setSummaryCommandBuilder(summaryBuilder);
        statusBuilder.setJob(job);
        PsNBootstrapProcessingDetailedStatus detailedStatus = statusBuilder.getDetailedStatus();
        return detailedStatus;
    }
}
