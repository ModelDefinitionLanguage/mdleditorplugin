/* File			:  NONMEMCompletedDetailedStatusBuilderTest.java
 * Project		:  MIFServer
 * Created on	:  Aug 10, 2012
 */
package com.mango.mif.nonmemsgeconnector;

import static org.junit.Assert.assertEquals;
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
import com.mango.mif.domain.ExecutionType;
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
public class PsNCompletedDetailedStatusBuilderTest extends NONMEMSummaryMessageBuilderTest {
    /**
     * Summary message template
     */
    private String messageTemplate = "nonmemsgeconnector/completed-summary-message.ftl";

    @Override
    @Before
    public void setUp() throws Exception {
        super.setUp();

        textBuilder.setTemplate(messageTemplate);
    }

    private Job prepareJob(String testData) {
        File workingDir = FileUtils.toFile(PsNCompletedDetailedStatusBuilderTest.class.getResource(testData));
        job.setGridJobDirectory(workingDir.getAbsolutePath());
        job.getExecutionRequest().setType(ExecutionType.PsN_EXEC.name());

        when(sharedLocationManager.getRequestInputDirectory((String) any())).thenReturn(workingDir);
        when(sharedLocationManager.getRequestOutputDirectory((String) any())).thenReturn(workingDir);
        return job;
    }

    @Test
    public void shouldGenerateAStatusMessageForMultipleEstimateBlock() throws ExecutionException, JAXBException {

        String testData = "/com/mango/mif/nonmemsgeconnector/testData/multipleEstBlocks";
        String ctlFileName = "warf_wt_on_V_est.ctl";
        final String expected = "SAEM INTER:-181.131;STOCHASTIC PORTION COMPLETED;$COV successful";
        logger.debug("expecting: " + expected);
        assertEquals(expected, createDetailedStatus(testData, ctlFileName).getStatus());
    }

    @Test
    public void shouldGenerateAStatusMessageForMultilineEstimateBlock() throws ExecutionException, JAXBException {

        String testData = "/com/mango/mif/nonmemsgeconnector/testData/multilineEstBlock";
        String ctlFileName = "JTT1ST37.ctl";
        final String expected = "FOCE INTER:-4474.541;MINIMIZATION SUCCESSFUL;No $COV";
        assertEquals(expected, createDetailedStatus(testData, ctlFileName).getStatus());
    }

    @Test
    public void shouldGenerateAStatusMessageForEstimateMethodAtTheEndOfEstBlock() throws ExecutionException, JAXBException {

        String testData = "/com/mango/mif/nonmemsgeconnector/testData/estMethodAtTheEnd";
        String ctlFileName = "run240a.mod";
        final String expected = "FOCE:-266.944;MINIMIZATION SUCCESSFUL;$COV failed";
        logger.debug("expecting: " + expected);
        assertEquals(expected, createDetailedStatus(testData, ctlFileName).getStatus());
    }

    @Test
    public void shouldGenerateADetailedStatusWhenThereAreSpacesInPath() throws ExecutionException, JAXBException {

        String testData = "/com/mango/mif/nonmemsgeconnector/testData/spacesInPath/path with space";
        String ctlFileName = "IMP.ctl";
        final String expected = "IMP:116.014;OPTIMIZATION NOT TESTED;No $COV";
        logger.debug("expecting: " + expected);
        assertEquals(expected, createDetailedStatus(testData, ctlFileName).getStatus());
    }

    @Test
    public void shouldGenerateADetailedStatusForCompletedSimulationWithEstAndSim() throws ExecutionException, JAXBException {

        String testData = "/testData/outputs/NONMEM/sim/sim_est/completed";
        String ctlFileName = "sim_est.mod";
        final String expected = "Simulation Completed";
        logger.debug("expecting: " + expected);
        assertEquals(expected, createDetailedStatus(testData, ctlFileName).getStatus());
    }

    @Test
    public void shouldGenerateADetailedStatusForCompletedSimulationWithSIMOnly() throws ExecutionException, JAXBException {

        String testData = "/testData/outputs/NONMEM/sim/sim_only/completed";
        String ctlFileName = "simonly.mod";
        final String expected = "Simulation Completed";
        logger.debug("expecting: " + expected);
        assertEquals(expected, createDetailedStatus(testData, ctlFileName).getStatus());
    }

    
    private NONMEMProcessingDetailedStatus createDetailedStatus(String testData, String ctlFileName) throws ExecutionException {
        //Given
        prepareJob(testData);
        job.getExecutionRequest().setExecutionFile(ctlFileName);

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
        return npds;
    }

}
