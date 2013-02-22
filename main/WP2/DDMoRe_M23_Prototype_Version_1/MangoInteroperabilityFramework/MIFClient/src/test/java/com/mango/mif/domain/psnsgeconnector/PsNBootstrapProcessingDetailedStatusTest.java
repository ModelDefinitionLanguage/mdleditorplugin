/* File			:  PsNBootstrapProcessingDetailedStatusTest.java
 * Project		:  MIFClient
 * Created on	:  Aug 15, 2012
 */
package com.mango.mif.domain.psnsgeconnector;

import static org.junit.Assert.*;

import javax.xml.bind.JAXBException;

import org.apache.log4j.Logger;
import org.junit.Before;
import org.junit.Test;

import com.mango.mif.domain.nonmemsgeconnector.NONMEMProcessingDetailedStatus;
import com.mango.mif.utils.JAXBUtils;
import com.mango.mif.utils.jaxb.ExecutionJaxbUtils;


/**
 * @version $Revision: $ as of $Date: $
 * <p>SVN Entry : $HeadURL: $
 * <p>SVN ID    : $Id: $
 * <p>Last edited by : $Author: $
 * 
 * Tests PsNBootstrapProcessingDetailedStatus
 */
public class PsNBootstrapProcessingDetailedStatusTest {
    /**
     * Logger
     */
    public final static Logger logger = Logger.getLogger(PsNBootstrapProcessingDetailedStatusTest.class);
    /**
     * Detailed Status
     */
    private PsNBootstrapProcessingDetailedStatus detailedStatus;
    
    @Before
    public void setUp() throws Exception {
        detailedStatus = new PsNBootstrapProcessingDetailedStatus();
        detailedStatus.setControlFileName("CONTROL_FILE");
        detailedStatus.setDuration("DURATION");
        detailedStatus.setUserName("USER_NAME");
        detailedStatus.setRequestId("REQUEST_ID");
        detailedStatus.setSummary("SUMMARY");
        detailedStatus.addActiveChildNONMEMProcess(createNONMEMChildProcess(1));
        detailedStatus.addActiveChildNONMEMProcess(createNONMEMChildProcess(2));
        detailedStatus.addActiveChildNONMEMProcess(createNONMEMChildProcess(3));
    }
    /**
     * Creates a child process instance
     */
    private NONMEMProcessingDetailedStatus createNONMEMChildProcess(int index) {
        NONMEMProcessingDetailedStatus childProcess = new NONMEMProcessingDetailedStatus();
        childProcess.setControlFileName("CONTROL_FILE" +index);
        childProcess.setDataFileName("DATA_FILE");
        childProcess.setDuration("DURATION");
        childProcess.setProblem("PROBLEM");
        childProcess.setUserName("USER_NAME");
        childProcess.setRequestId("REQUEST_ID");
        childProcess.setSummary("SUMMARY");
        childProcess.setStatus("STATUS");
        return childProcess;
    }

    @Test
    public void shouldMarshallAndUnmarshallDetailedStatus() throws JAXBException {
        String message = JAXBUtils.marshall(detailedStatus, ExecutionJaxbUtils.CONTEXT_CLASSES);
        logger.debug(message);
        PsNBootstrapProcessingDetailedStatus unmarshalled = JAXBUtils.unmarshall(message, ExecutionJaxbUtils.CONTEXT_CLASSES);
        
        assertEquals(detailedStatus.getControlFileName(), unmarshalled.getControlFileName());
        assertEquals(detailedStatus.getDuration(), unmarshalled.getDuration());
        assertEquals(detailedStatus.getActiveChildNONMEMProcesses().size(),unmarshalled.getActiveChildNONMEMProcesses().size() );
    }

}
