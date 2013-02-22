/* File			:  JobTest.java
 * Project		:  MIFServer
 * Created on	:  Sep 3, 2012
 */
package com.mango.mif.connector.domain;

import static org.junit.Assert.assertEquals;
import static org.mockito.Mockito.when;

import java.io.File;
import java.util.Properties;

import org.apache.log4j.Logger;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.runners.MockitoJUnitRunner;

import com.mango.mif.connector.runner.status.SummaryMessageBuilderTest;
import com.mango.mif.domain.ExecutionRequest;
import com.mango.mif.utils.MIFProperties;


/**
 * @version $Revision: $ as of $Date: $
 * <p>SVN Entry : $HeadURL: $
 * <p>SVN ID    : $Id: $
 * <p>Last edited by : $Author: $
 * 
 * Tests a shorthand methods on Job
 */
@RunWith(MockitoJUnitRunner.class)
public class JobTest {
    
    final static Logger logger = Logger.getLogger(JobTest.class);
    
    @Mock ExecutionRequest executionRequest;
    
    Job job;
    
    @Before
    public void setUp() throws Exception {
        Properties properties = new Properties();
        properties.load(SummaryMessageBuilderTest.class.getResourceAsStream("/com/mango/mif/sgeconnector/connector.properties"));
        MIFProperties.loadProperties(properties);
        job = new Job();
        job.setExecutionRequest(executionRequest);
        job.setGridJobDirectory("/");
    }

    @Test
    public void shouldReturnAPsNOutputDirectory() {
        when(executionRequest.getExecutionParameters()).thenReturn("-some=property -some_other=property -directory=test-dir -other=property");
        File outputDir = job.getPsNOutputDir();
        
        logger.debug(outputDir);
        assertEquals("test-dir",outputDir.getName());
    }

    @Test
    public void shouldReturnAPsNOutputDirectoryWhenInQuotes() {
        when(executionRequest.getExecutionParameters()).thenReturn("-some=property -some_other=property -directory=\"test-dir\" -other=property");
        File outputDir = job.getPsNOutputDir();
        
        logger.debug(outputDir);
        assertEquals("test-dir",outputDir.getName());
    }

    @Test
    public void shouldReturnAPsNOutputDirectoryWithSpacesWhenInQuotes() {
        when(executionRequest.getExecutionParameters()).thenReturn("-some=property -some_other=property -directory=\"test dir\" -other=property");
        File outputDir = job.getPsNOutputDir();
        
        logger.debug(outputDir);
        assertEquals("test dir",outputDir.getName());
    }

    @Test
    public void shouldReturnAPsNOutputDirectoryWithSpacesWhenInQuotesAtTheEndAndColons() {
        when(executionRequest.getExecutionParameters()).thenReturn("-some=property -some_other=property -other=property -directory=\"test dir : \"");
        File outputDir = job.getPsNOutputDir();
        
        logger.debug(outputDir);
        assertEquals("test dir : ",outputDir.getName());
    }
    
    @Test
    public void shouldReturnPsNSCMLogFile() {
        when(executionRequest.getExecutionParameters()).thenReturn("-some=property -some_other=property -directory=test-dir -other=property");
        File psnLogFile = job.getPsNSCMLogFile();
        
        logger.debug(psnLogFile);
        StringBuilder logPath = new StringBuilder(File.separator).append("test-dir").append(File.separator).append("scmlog1.txt");
        assert(psnLogFile.getAbsolutePath().endsWith(logPath.toString()));
    }
}
