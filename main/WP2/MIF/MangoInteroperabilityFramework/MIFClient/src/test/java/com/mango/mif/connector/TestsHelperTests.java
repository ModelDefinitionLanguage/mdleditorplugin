/* File			:  TestsHelperTests.java
 * Project		:  MangoInteroperabilityFramework-trunk
 * Created on	:  13 Apr 2012
 */
package com.mango.mif.connector;

import static org.junit.Assert.*;

import java.io.File;

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

    @Test
    public void testNamedTmpDirCreation() {
        File file = TestsHelper.createTmpDirectory("tmpDir");
        
        assertTrue(file.exists());
    }

}
