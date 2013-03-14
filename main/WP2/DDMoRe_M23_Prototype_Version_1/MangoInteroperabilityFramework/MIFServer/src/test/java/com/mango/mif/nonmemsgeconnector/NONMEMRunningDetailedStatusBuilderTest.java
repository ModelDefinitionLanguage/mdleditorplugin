/* File			:  NONMEMRunningDetailedStatusBuilderTest.java
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
 * Tests NONMEMRunningDetailedStatusBuilder
 * 
 */
public class NONMEMRunningDetailedStatusBuilderTest extends NONMEMSummaryMessageBuilderTest {

    /**
     * Summary message template
     */
    private String messageTemplate = "nonmemsgeconnector/running-summary-message.ftl";

    @Override
    @Before
    public void setUp() throws Exception {
        super.setUp();

        textBuilder.setTemplate(messageTemplate);

    }

    private Job prepareJob(String testData) {
        File workingDir = FileUtils.toFile(NONMEMRunningDetailedStatusBuilderTest.class.getResource(testData));
        job.setGridJobDirectory(workingDir.getAbsolutePath());
        job.getExecutionRequest().setType(ExecutionType.NMFE.name());

        when(sharedLocationManager.getRequestInputDirectory((String) any())).thenReturn(workingDir);
        when(sharedLocationManager.getRequestOutputDirectory((String) any())).thenReturn(workingDir);
        return job;
    }

    @Test
    public void shouldBuildADetailedStatusForRedirectedBAYES() throws ExecutionException, JAXBException {
        final String expected = "IMP  INTER:-1144.404";
        String testData = "/testData/outputs/NONMEM/est/BAYES_redirected";
        String ctlFileName = "example1.ctl";
        logger.debug("expecting: " + expected);
        assertEquals(expected, createDetailedStatus(testData, ctlFileName,"").getStatus());
        
    }

    @Test
    public void shouldBuildADetailedStatusForCOND() throws ExecutionException, JAXBException {
        final String expected = "COND INTER:-1121.028";
        String testData = "/testData/outputs/NONMEM/est/COND";
        String ctlFileName = "example1.ctl";
        logger.debug("expecting: " + expected);
        assertEquals(expected, createDetailedStatus(testData, ctlFileName,"").getStatus());
        
    }

    @Test
    public void shouldBuildADetailedStatusForIMP() throws ExecutionException, JAXBException {
        final String expected = "IMP  INTER:-1143.844";
        String testData = "/testData/outputs/NONMEM/est/IMP";
        String ctlFileName = "example1.ctl";
        logger.debug("expecting: " + expected);
        assertEquals(expected, createDetailedStatus(testData, ctlFileName,"").getStatus());
        
    }

    @Test
    public void shouldBuildADetailedStatusForITS() throws ExecutionException, JAXBException {
        final String expected = "ITS INTER:-1120.270";
        String testData = "/testData/outputs/NONMEM/est/ITS";
        String ctlFileName = "example1.ctl";
        logger.debug("expecting: " + expected);
        assertEquals(expected, createDetailedStatus(testData, ctlFileName,"").getStatus());
        
    }

    @Test
    public void shouldBuildADetailedStatusForMAXEVAL0() throws ExecutionException, JAXBException {
        final String expected = "ITS INTER:-1121.028";
        String testData = "/testData/outputs/NONMEM/est/MAXEVAL_0";
        String ctlFileName = "example1-maxeval-0.ctl";
        logger.debug("expecting: " + expected);
        assertEquals(expected, createDetailedStatus(testData, ctlFileName,"").getStatus());
        
    }

    @Test
    public void shouldBuildADetailedStatusForNoEXT() throws ExecutionException, JAXBException {
        final String expected = "STARTUP";
        String testData = "/testData/outputs/NONMEM/est/no_ext";
        String ctlFileName = "example1.ctl";
        logger.debug("expecting: " + expected);
        assertEquals(expected, createDetailedStatus(testData, ctlFileName,"").getStatus());
        
    }

    @Test
    public void shouldBuildADetailedStatusForSAEM() throws ExecutionException, JAXBException {
        final String expected = "SAEM INTER:-2483.028";
        String testData = "/testData/outputs/NONMEM/est/SAEM";
        String ctlFileName = "example1.ctl";
        logger.debug("expecting: " + expected);
        assertEquals(expected, createDetailedStatus(testData, ctlFileName,"").getStatus());
        
    }

    @Test
    public void shouldGenerateStatusMessageForJustSim() throws ExecutionException, JAXBException {
        String testData = "/testData/outputs/NONMEM/sim/sim_only/running";
        String ctlFileName = "simonly.mod";
        final String expected = "Simulating (21 of 100)";
        logger.debug("expecting: " + expected);
        assertEquals(expected, createDetailedStatus(testData, ctlFileName,"-directory=.").getStatus());
    }

    @Test
    public void shouldGenerateStatusMessageForSimAndEst() throws ExecutionException, JAXBException {
        String testData = "/testData/outputs/NONMEM/sim/sim_est/running";
        String ctlFileName = "sim_est.mod";
        final String expected = "Simulating (3 of 5)";
        logger.debug("expecting: " + expected);
        assertEquals(expected, createDetailedStatus(testData, ctlFileName,"-directory=.").getStatus());
    }


    @Test
    public void shouldGenerateStatusMessageForSim() throws ExecutionException, JAXBException {
        String testData = "/testData/outputs/NONMEM/sim/sim/running";
        String ctlFileName = "sim.mod";
        final String expected = "Simulating (5 of 10)";
        logger.debug("expecting: " + expected);
        assertEquals(expected, createDetailedStatus(testData, ctlFileName,"-directory=.").getStatus());
    }

    @Test
    public void shouldGenerateStatusMessageForSimSubproblemsParam() throws ExecutionException, JAXBException {
        String testData = "/testData/outputs/NONMEM/sim/sim/running-subproblems";
        String ctlFileName = "sim.mod";
        final String expected = "Simulating (5 of 10)";
        logger.debug("expecting: " + expected);
        assertEquals(expected, createDetailedStatus(testData, ctlFileName,"-directory=.").getStatus());
    }

    @Test
    public void shouldGenerateStatusMessageForSimOnlyAndNoNSUB() throws ExecutionException, JAXBException {
        String testData = "/testData/outputs/NONMEM/sim/sim_only/running-no-nsub";
        String ctlFileName = "simonly.mod";
        final String expected = "Simulating (1 of 1)";
        logger.debug("expecting: " + expected);
        assertEquals(expected, createDetailedStatus(testData, ctlFileName,"-directory=.").getStatus());
    }

    private NONMEMProcessingDetailedStatus createDetailedStatus(String testData, String ctlFileName, String excecutionParameters) throws ExecutionException {
        //Given
        prepareJob(testData);
        job.getExecutionRequest().setExecutionFile(ctlFileName);
        job.getExecutionRequest().setExecutionParameters(excecutionParameters);

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
