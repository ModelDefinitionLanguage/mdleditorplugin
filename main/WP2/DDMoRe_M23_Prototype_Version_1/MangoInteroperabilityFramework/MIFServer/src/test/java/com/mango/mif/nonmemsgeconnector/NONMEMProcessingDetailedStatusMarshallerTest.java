/* File			:  NONMEMProcessingDetailedStatusMarshallerTest.java
 * Project		:  MIFServer
 * Created on	:  Sep 4, 2012
 */
package com.mango.mif.nonmemsgeconnector;

import static org.junit.Assert.*;

import java.util.Map;

import org.apache.log4j.Logger;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.runners.MockitoJUnitRunner;

import static org.mockito.Mockito.*;

import com.google.common.collect.Maps;
import com.mango.mif.domain.nonmemsgeconnector.NONMEMProcessingDetailedStatus;


/**
 * @version $Revision: $ as of $Date: $
 * <p>SVN Entry : $HeadURL: $
 * <p>SVN ID    : $Id: $
 * <p>Last edited by : $Author: $
 * 
 * Tests NONMEMProcessingDetailedStatusMarshaller
 */
@RunWith(MockitoJUnitRunner.class)
public class NONMEMProcessingDetailedStatusMarshallerTest {

    private final static Logger logger = Logger.getLogger(NONMEMProcessingDetailedStatusMarshallerTest.class);
    
    Map<String,NONMEMProcessingDetailedStatus> nonmemJobStatuses = Maps.newHashMap();
    String expected;
    @Before
    public void setUp() throws Exception {
        NONMEMProcessingDetailedStatus detailedStatus = prepareDetailedStatusMock("1");
        nonmemJobStatuses.put(detailedStatus.getGridJobId(), detailedStatus);
        detailedStatus = prepareDetailedStatusMock("2");
        nonmemJobStatuses.put(detailedStatus.getGridJobId(), detailedStatus);
        
        expected = "jobId:2;modFileName:CONTROL_FILE;runName:RUN_NAME;runWorkingDir:WORKING_DIR;startTime:START_TIME;duration:DURATION;outputFile:OUTPUT_FILE;description:DESCRIPTION;objectiveFunctionValue:OBJECTIVE: FUNCTION_VALUE;estimationMethod:ESTIMATION_METHOD;summaryMessage:SUMMARY_MESSAGE;status:STATUS\n"
                    + "jobId:1;modFileName:CONTROL_FILE;runName:RUN_NAME;runWorkingDir:WORKING_DIR;startTime:START_TIME;duration:DURATION;outputFile:OUTPUT_FILE;description:DESCRIPTION;objectiveFunctionValue:OBJECTIVE: FUNCTION_VALUE;estimationMethod:ESTIMATION_METHOD;summaryMessage:SUMMARY_MESSAGE;status:STATUS\n";
    }

    private NONMEMProcessingDetailedStatus prepareDetailedStatusMock(String jobID) {
        NONMEMProcessingDetailedStatus detailedStatus = mock(NONMEMProcessingDetailedStatus.class);
        when(detailedStatus.getGridJobId()).thenReturn(jobID);
        when(detailedStatus.getRunName()).thenReturn("RUN_NAME");
        when(detailedStatus.getControlFileName()).thenReturn("CONTROL_FILE");
        when(detailedStatus.getStartTime()).thenReturn("START_TIME");
        when(detailedStatus.getDuration()).thenReturn("DURATION");
        when(detailedStatus.getWorkingDirectory()).thenReturn("WORKING_DIR");
        when(detailedStatus.getDescription()).thenReturn("DESCRIPTION");
        when(detailedStatus.getEstimationMethod()).thenReturn("ESTIMATION_METHOD");
        when(detailedStatus.getObjectiveFunctionValue()).thenReturn("OBJECTIVE: FUNCTION_VALUE");
        when(detailedStatus.getOutputFile()).thenReturn("OUTPUT_FILE");
        when(detailedStatus.getSummary()).thenReturn("SUMMARY_MESSAGE");
        when(detailedStatus.getStatus()).thenReturn("STATUS");
        return detailedStatus;
    }
    
    @Test
    public void shouldMarshallNonmemJobStatuses() {
        String marshalled = NONMEMProcessingDetailedStatusMarshaller.marshallNonmemRuns(nonmemJobStatuses);
        logger.debug(marshalled);
        assertNotNull(marshalled);
        assertEquals(expected,marshalled);
    }

    @Test
    public void shouldUnMarshallNonmemJobStatuses() {
        Map<String,NONMEMProcessingDetailedStatus> unmarshalled = NONMEMProcessingDetailedStatusMarshaller.unmarshallNonmemRuns(expected);
        assertEquals(2,unmarshalled.size());
        assertEquals("OBJECTIVE: FUNCTION_VALUE",unmarshalled.get("2").getObjectiveFunctionValue());
        assertEquals("SUMMARY_MESSAGE",unmarshalled.get("2").getSummary());
    }
    
}
