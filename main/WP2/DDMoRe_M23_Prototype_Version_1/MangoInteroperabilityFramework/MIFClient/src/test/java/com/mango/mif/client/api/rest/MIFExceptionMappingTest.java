/* File			:  MIFExceptionMappingTest.java
 * Project		:  MIFClient
 * Created on	:  Sep 22, 2012
 */
package com.mango.mif.client.api.rest;

import static org.junit.Assert.*;
import javax.ws.rs.core.Response;

import org.apache.log4j.Logger;
import org.junit.Before;
import org.junit.Test;

import com.mango.mif.exception.MIFException;


/**
 * @version $Revision: $ as of $Date: $
 * <p>SVN Entry : $HeadURL: $
 * <p>SVN ID    : $Id: $
 * <p>Last edited by : $Author: $
 */
public class MIFExceptionMappingTest {
    
    private final static Logger logger = Logger.getLogger(MIFExceptionMappingTest.class);
    
    MIFExceptionResponseMapper responseMapper;
    MIFExceptionMapper exceptionMapper;
    
    
    @Before
    public void setUp() throws Exception {
        responseMapper = new MIFExceptionResponseMapper();
        exceptionMapper = new MIFExceptionMapper();
    }

    @Test
    public void shouldMapAndUnmapExceptionFromResponse() {
        MIFException exception = new MIFException("Error message");
        
        Response response = exceptionMapper.toResponse(exception);

        MIFException unmarshalled = responseMapper.fromResponse(response);
        
        assertEquals(exception.getMessage(), unmarshalled.getMessage());
    }

}
