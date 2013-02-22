/* File			:  PsNSCMProcessingDetailedStatusTest.java
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
 * Tests PsNBootstrapProcessingDetailedStatus
 */
public class PsNSCMProcessingDetailedStatusTest {
    /**
     * Logger
     */
    public final static Logger logger = Logger.getLogger(PsNSCMProcessingDetailedStatusTest.class);
    /**
     * Detailed Status
     */
    private PsNSCMProcessingDetailedStatus detailedStatus;
    
    @Before
    public void setUp() throws Exception {
        detailedStatus = new PsNSCMProcessingDetailedStatus();
        detailedStatus.setControlFileName("CONTROL_FILE");
        detailedStatus.setDuration("DURATION");
        detailedStatus.setUserName("USER_NAME");
        detailedStatus.setRequestId("REQUEST_ID");
        detailedStatus.setSummary("SUMMARY");
        detailedStatus.setParameterCovariateRelation("parameterCovariateRelation");
        detailedStatus.addModel(createPsNSCMModelProcessingStatus(1));
        detailedStatus.addModel(createPsNSCMModelProcessingStatus(2));
        detailedStatus.addModel(createPsNSCMModelProcessingStatus(3));
    }
    /**
     * Creates a child process instance
     */
    private PsNSCMModelProcessingStatus createPsNSCMModelProcessingStatus(int index) {
        PsNSCMModelProcessingStatus model = new PsNSCMModelProcessingStatus();
        model.setModel("MODEL " +index);
        model.setBaseOFV("baseOFV");
        model.setdDF("dDF");
        model.setGoal("goal");
        model.setNewOFV("newOFV");
        model.setpVAL("pVAL");
        model.setSignificant("significant");
        model.setTest("test");
        model.setTestOFV("testOFV");
        return model;
    }

    @Test
    public void shouldMarshallAndUnmarshallDetailedStatus() throws JAXBException {
        String message = JAXBUtils.marshall(detailedStatus, ExecutionJaxbUtils.CONTEXT_CLASSES);
        logger.debug(message);
        PsNSCMProcessingDetailedStatus unmarshalled = JAXBUtils.unmarshall(message, ExecutionJaxbUtils.CONTEXT_CLASSES);
        
        assertEquals(detailedStatus.getControlFileName(), unmarshalled.getControlFileName());
        assertEquals(detailedStatus.getDuration(), unmarshalled.getDuration());
        assertEquals(detailedStatus.getModels().size(),unmarshalled.getModels().size() );
    }

}
