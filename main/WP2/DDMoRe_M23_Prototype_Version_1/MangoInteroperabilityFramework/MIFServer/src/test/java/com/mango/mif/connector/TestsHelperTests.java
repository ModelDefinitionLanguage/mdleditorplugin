/* File			:  TestsHelperTests.java
 * Project		:  MangoInteroperabilityFramework-trunk
 * Created on	:  13 Apr 2012
 */
package com.mango.mif.connector;

import static org.junit.Assert.*;

import java.io.File;
import org.apache.log4j.Logger;
import org.junit.Test;


/**
 * Tests Helper class test
 * 
 * @version $Revision: $ as of $Date: $
 * <p>SVN Entry : $HeadURL: $
 * <p>SVN ID    : $Id: $
 * <p>Last edited by : $Author: $
 */
public class TestsHelperTests {
    private final static Logger LOG = Logger.getLogger(TestsHelperTests.class);
    @Test
    public void testNamedTmpDirCreation() {
        File file = TestsHelper.createTmpDirectory("tmpDir");
        
        assertTrue(file.exists());
    }

    @Test
    public void testListRtestDataSets() {
        File root = TestsHelper.getRTestDataSetsDir();
        LOG.info("Path " + root.getAbsolutePath());
        assertTrue(root.exists());
        
        File[] files = TestsHelper.getRTestDataSetsDirectories();
        for(File file : files) {
            LOG.info("R Test Data file set: " + file.getAbsolutePath());
        }
    }
}
