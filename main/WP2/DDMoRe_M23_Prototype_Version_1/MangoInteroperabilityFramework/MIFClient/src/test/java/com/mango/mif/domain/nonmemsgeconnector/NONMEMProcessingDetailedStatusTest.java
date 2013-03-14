/* File			:  NONMEMProcessingDetailedStatusTest.java
 * Project		:  MIFClient
 * Created on	:  Aug 15, 2012
 */
package com.mango.mif.domain.nonmemsgeconnector;

import static org.junit.Assert.*;

import javax.xml.bind.JAXBException;

import org.apache.log4j.Logger;
import org.junit.Before;
import org.junit.Test;

import com.mango.mif.utils.JAXBUtils;
import com.mango.mif.utils.jaxb.ExecutionJaxbUtils;


/**
 * @version $Revision: $ as of $Date: $
 * <p>SVN Entry : $HeadURL: $
 * <p>SVN ID    : $Id: $
 * <p>Last edited by : $Author: $
 * 
 * Tests NONMEMProcessingDetailedStatus
 */
public class NONMEMProcessingDetailedStatusTest {
    /**
     * Logger
     */
    public final static Logger logger = Logger.getLogger(NONMEMProcessingDetailedStatusTest.class);
    /**
     * Detailed Status
     */
    private NONMEMProcessingDetailedStatus detailedStatus;
    
    @Before
    public void setUp() throws Exception {
        detailedStatus = new NONMEMProcessingDetailedStatus();
        
        detailedStatus.setControlFileName("CONTROL_FILE");
        detailedStatus.setDataFileName("DATA_FILE");
        detailedStatus.setDuration("DURATION");
        detailedStatus.setProblem("PROBLEM");
        detailedStatus.setUserName("USER_NAME");
        detailedStatus.setRequestId("REQUEST_ID");
        detailedStatus.setSummary("SUMMARY");
        detailedStatus.setStatus("STATUS");
    }

    @Test
    public void shouldMarshallAndUnmarshallDetailedStatus() throws JAXBException {
        String message = JAXBUtils.marshall(detailedStatus, ExecutionJaxbUtils.CONTEXT_CLASSES);
        logger.debug(message);
        NONMEMProcessingDetailedStatus unmarshalled = JAXBUtils.unmarshall(message, ExecutionJaxbUtils.CONTEXT_CLASSES);
        
        assertEquals(detailedStatus.getControlFileName(), unmarshalled.getControlFileName());
        assertEquals(detailedStatus.getDataFileName(), unmarshalled.getDataFileName());
    }

}
