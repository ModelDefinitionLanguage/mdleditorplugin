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
public class PsNRunningDetailedStatusBuilderTest extends NONMEMSummaryMessageBuilderTest {

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
        File workingDir = FileUtils.toFile(PsNCompletedDetailedStatusBuilderTest.class.getResource(testData));
        job.setGridJobDirectory(workingDir.getAbsolutePath());
        job.getExecutionRequest().setType(ExecutionType.PsN_EXEC.name());

        when(sharedLocationManager.getRequestInputDirectory((String) any())).thenReturn(workingDir);
        when(sharedLocationManager.getRequestOutputDirectory((String) any())).thenReturn(workingDir);
        return job;
    }

    @Test
    public void shouldGenerateAStatusMessageWith_0_OfRetries() throws ExecutionException, JAXBException {
        String testData = "/testData/outputs/NONMEM/warfar-running-psn";
        String ctlFileName = "warf_wt_on_V_est.ctl";
        final String expected = "ITS INTER:197.385 (0/5)";
        logger.debug("expecting: " + expected);
        assertEquals(expected, createDetailedStatus(testData, ctlFileName,"-blah=foobar -retries=5 -directory=modelfit_dir1 -rubbish=59").getStatus());
    }

    @Test
    public void shouldGenerateAStatusMessageWith_3_Retries() throws ExecutionException, JAXBException {
        String testData = "/testData/outputs/NONMEM/warfar-running-psn-3-retries";
        String ctlFileName = "warf_wt_on_V_est.ctl";
        final String expected = "ITS INTER:197.385 (3/5)";
        logger.debug("expecting: " + expected);
        assertEquals(expected, createDetailedStatus(testData, ctlFileName,"-blah=foobar -retries=5 -directory=modelfit_dir1 -rubbish=59").getStatus());
    }
    

    @Test
    public void shouldGenerateAStatusMessageStartup_0_5() throws ExecutionException, JAXBException {
        String testData = "/testData/outputs/NONMEM/warfar-startup-psn";
        String ctlFileName = "warf_wt_on_V_est.ctl";
        final String expected = "STARTUP (0/5)";
        logger.debug("expecting: " + expected);
        assertEquals(expected, createDetailedStatus(testData, ctlFileName,"-blah=foobar -retries=5 -directory=modelfit_dir1 -rubbish=59").getStatus());
    }

    @Test
    public void shouldGenerateStatusMessageForSimAndEst() throws ExecutionException, JAXBException {
        String testData = "/testData/outputs/NONMEM/sim/sim_est_psn/running";
        String ctlFileName = "sim_est.mod";
        final String expected = "Simulating (3 of 5)";
        logger.debug("expecting: " + expected);
        assertEquals(expected, createDetailedStatus(testData, ctlFileName,"-directory=modelfit_dir1").getStatus());
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
