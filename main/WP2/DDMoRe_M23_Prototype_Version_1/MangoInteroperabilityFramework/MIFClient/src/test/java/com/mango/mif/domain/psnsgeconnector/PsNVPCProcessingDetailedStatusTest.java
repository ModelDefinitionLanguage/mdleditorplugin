/* File			:  PsNVPCProcessingDetailedStatusTest.java
 * Project		:  MIFClient
 * Created on	:  Aug 15, 2012
 */
package com.mango.mif.domain.psnsgeconnector;

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
 * Tests PsNVPCProcessingDetailedStatus
 */
public class PsNVPCProcessingDetailedStatusTest {
    /**
     * Logger
     */
    public final static Logger logger = Logger.getLogger(PsNVPCProcessingDetailedStatusTest.class);
    /**
     * Detailed Status
     */
    private PsNVPCProcessingDetailedStatus detailedStatus;
    
    @Before
    public void setUp() throws Exception {
        detailedStatus = new PsNVPCProcessingDetailedStatus();
        detailedStatus.setUserName("USER_NAME");
        detailedStatus.setRequestId("REQUEST_ID");
        detailedStatus.setSummary("SUMMARY");
    }

    @Test
    public void shouldMarshallAndUnmarshallDetailedStatus() throws JAXBException {
        String message = JAXBUtils.marshall(detailedStatus, ExecutionJaxbUtils.CONTEXT_CLASSES);
        logger.debug(message);
        PsNVPCProcessingDetailedStatus unmarshalled = JAXBUtils.unmarshall(message, ExecutionJaxbUtils.CONTEXT_CLASSES);
        
        assertEquals(detailedStatus.getRequestId(), unmarshalled.getRequestId());
        assertEquals(detailedStatus.getSummary(), unmarshalled.getSummary());
    }

}
