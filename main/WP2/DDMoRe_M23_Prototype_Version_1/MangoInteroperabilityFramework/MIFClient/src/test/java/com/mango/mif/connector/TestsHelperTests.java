/* File			:  TestsHelperTests.java
 * Project		:  MangoInteroperabilityFramework-trunk
 * Created on	:  13 Apr 2012
 */
package com.mango.mif.connector;

import static org.junit.Assert.*;

import java.io.File;
import java.io.IOException;

import org.apache.commons.io.FileUtils;
import org.junit.After;
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

	File tmpDir;
	
	@After
	public void tearDown() throws IOException {
		FileUtils.deleteDirectory(tmpDir);
	}
	
    @Test
    public void shouldCreateATemporaryDirectory() {
        tmpDir = TestsHelper.createTmpDirectory("tmpDir");
        
        assertTrue(tmpDir.exists());
    }

}
