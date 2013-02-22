/* File			:  NONMEMCompletedDetailedStatusBuilderTest.java
 * Project		:  MIFServer
 * Created on	:  Aug 10, 2012
 */
package com.mango.mif.nonmemsgeconnector;

import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertTrue;
import static org.mockito.Matchers.any;
import static org.mockito.Mockito.when;

import java.io.File;

import javax.xml.bind.JAXBException;

import org.apache.commons.io.FileUtils;
import org.junit.Before;
import org.junit.Test;

import com.mango.mif.connector.domain.Job;
import com.mango.mif.core.exec.ExecutionException;
import com.mango.mif.domain.DetailedStatus;
import com.mango.mif.domain.nonmemsgeconnector.NONMEMProcessingDetailedStatus;

/**
 * @version $Revision: $ as of $Date: $
 * <p>SVN Entry : $HeadURL: $
 * <p>SVN ID    : $Id: $
 * <p>Last edited by : $Author: $
 * 
 * Tests NONMEMCompletedDetailedStatusBuilder
 * 
 * Summary message:
 * 
 * Objective Function value prefixed with Status: "FOCE, SAEM, FO, IMPMAP, minimization/optimization/evaluation (termination status) and $COV status 
 * 
 * FOCE:1029; minimization successful; NO $COV
 * FOCE:1029; minimization terminated;  $COV failed
 * ITS:1029; OPTIMIZATION COMPLETED; $COV successful
 * 
 */
public class NONMEMCompletedDetailedStatusBuilderTest extends NONMEMSummaryMessageBuilderTest {
    /**
     * Test data
     */
    private String testData = "/testData/outputs/NONMEM/warfar-completed";
    /**
     * Control file
     */
    private String ctlFileName = "warf_wt_on_V_est.ctl";
    /**
     * Summary message template
     */
    private String messageTemplate = "nonmemsgeconnector/completed-summary-message.ftl";

    @Override
    @Before
    public void setUp() throws Exception {
        super.setUp();
        textBuilder.setTemplate(messageTemplate);
        job.getExecutionRequest().setExecutionFile(ctlFileName);
    }

    private Job prepareJob() {
        File workingDir = FileUtils.toFile(NONMEMCompletedDetailedStatusBuilderTest.class.getResource(this.testData));
        job.setGridJobDirectory(workingDir.getAbsolutePath());

        when(sharedLocationManager.getRequestInputDirectory((String) any())).thenReturn(workingDir);
        when(sharedLocationManager.getRequestOutputDirectory((String) any())).thenReturn(workingDir);
        return job;
    }

    @Test
    public void shouldGenerateAStatusMessage() throws ExecutionException, JAXBException {

        final String expected = "SAEM INTER:-181;STOCHASTIC PORTION COMPLETED;$COV successful";

        //Given
        prepareJob();

        //when
        NONMEMRunningDetailedStatusBuilder messageBuilder = new NONMEMRunningDetailedStatusBuilder();
        messageBuilder.setSummaryMessageBuilder(textBuilder);
        messageBuilder.setJob(job);
        textBuilder.setJob(job);

        DetailedStatus summary = messageBuilder.getDetailedStatus();

        //when
        assertNotNull("Summary message produced was null", summary);
        assertTrue("Return type was not as expected", summary instanceof NONMEMProcessingDetailedStatus);
        NONMEMProcessingDetailedStatus npds = (NONMEMProcessingDetailedStatus) summary;

        logger.debug("grid job id: " + npds.getGridJobId());
        logger.debug("controlfilename: " + npds.getControlFileName());
        logger.debug("workingdirectory: " + npds.getWorkingDirectory());
        logger.debug("problem: " + npds.getProblem());
        logger.debug("datafilename: " + npds.getDataFileName());
        logger.debug("duration: " + npds.getDuration());
        logger.debug("status: " + npds.getStatus());
        logger.debug("starttime: " + npds.getStartTime());
        logger.debug("lastupdated: " + npds.getLastUpdated());

        logger.debug("got      : " + npds.getStatus());
        logger.debug("expecting: " + expected);
    }
}
