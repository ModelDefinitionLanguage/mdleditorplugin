/* File			:  PrefixIDNamingStrategyTest.java
 * Project		:  MIFServer
 * Created on	:  Nov 6, 2012
 */
package com.mango.mif.sge;

import static org.junit.Assert.*;

import java.io.File;

import org.junit.Test;
import static org.mockito.Mockito.*;

import com.mango.mif.connector.domain.Job;
import com.mango.mif.sge.GridJobDirectoryResolver;


/**
 * Unit tests naming stratgy
 * 
 * @version $Revision: $ as of $Date: $
 * <p>SVN Entry : $HeadURL: $
 * <p>SVN ID    : $Id: $
 * <p>Last edited by : $Author: $
 */
public class GridJobDirectoryResolverTest {
    String sgeSharedLocation = "GRID_SHARED_LOCATION";
    String requestID = "REQUEST_ID";
    String requestDir = "REQUEST_ID";
    @Test(expected = NullPointerException.class)
    public void shouldThrowExceptionIfJobIsNull() {
        GridJobDirectoryResolver namingStrategy = new GridJobDirectoryResolver();
        
        namingStrategy.setSgeSharedLocation(sgeSharedLocation);
        namingStrategy.getPath(null);
    }

    @Test(expected = NullPointerException.class)
    public void shouldThrowExceptionIfSgeSharedLocationIsNotSet() {
        GridJobDirectoryResolver namingStrategy = new GridJobDirectoryResolver();
        Job job = mock(Job.class);
        namingStrategy.getPath(job);
    }

    @Test
    public void shouldReturnGridJobDirectoryNameUnderGridShare() {
        GridJobDirectoryResolver namingStrategy = new GridJobDirectoryResolver();
        namingStrategy.setSgeSharedLocation(sgeSharedLocation);
        Job job = mock(Job.class);
        when(job.getRequestInputDirectory()).thenReturn(requestDir);
        when(job.getJobId()).thenReturn(requestID);
        when(job.isRunInRequestDirectory()).thenReturn(false);
        String name = namingStrategy.getPath(job);
        
        assertEquals(sgeSharedLocation+File.separator+namingStrategy.getPrefix()+requestID,name);
    }

    @Test
    public void shouldReturnGridJobDirectorySameAsRequestDirectory() {
        GridJobDirectoryResolver namingStrategy = new GridJobDirectoryResolver();
        namingStrategy.setSgeSharedLocation(sgeSharedLocation);
        Job job = mock(Job.class);
        when(job.getRequestInputDirectory()).thenReturn(requestDir);
        when(job.getJobId()).thenReturn(requestID);
        when(job.isRunInRequestDirectory()).thenReturn(true);
        String name = namingStrategy.getPath(job);
        
        assertEquals(requestDir,name);
    }
}
