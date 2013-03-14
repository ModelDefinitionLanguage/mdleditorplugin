/* File			:  RScriptExecTest.java
 * Project		:  MangoInteroperabilityFramework
 * Created on	:  29 Mar 2012
 */
package com.mango.mif.r;

import static org.junit.Assert.*;

import java.io.File;
import java.io.IOException;

import org.apache.commons.io.FileUtils;
import org.apache.log4j.Logger;
import org.junit.After;
import org.junit.Before;
import org.junit.Ignore;
import org.junit.Test;

import com.google.common.io.Files;
import com.mango.mif.connector.TestsHelper;
import com.mango.mif.r.RScriptConfig;
import com.mango.mif.r.RScriptExec;
import com.mango.mif.utils.CommandExecException;


/**
 * 
 * R script exec unit test
 * 
 * @version $Revision: $ as of $Date: $
 * <p>SVN Entry : $HeadURL: $
 * <p>SVN ID    : $Id: $
 * <p>Last edited by : $Author: $
 */
@Ignore("Local R execution is not yet being targetted")
public class RScriptExecTest {
    /**
     * Logger
     */
    public static final Logger LOG = Logger.getLogger(RScriptExecTest.class);
    /**
     * Arguments passed to Rscript
     */
    private String[] arguments = new String[] {"--vanilla"};
    
    @Before
    public void setUp() throws Exception {
    }

    @After
    public void tearDown() throws Exception {
    }

    /**
     * Runs R script
     * @throws IOException
     * @throws CommandExecException
     */
    @Test
    public void testRScriptExec() throws IOException, CommandExecException {
        RScriptConfig rScriptConfig = new RScriptConfig();
        File rExecutable = TestsHelper.getRExecutable();
        assertTrue("RScript executable does not exist. Check if correct R version is installed.",rExecutable.exists());
        rScriptConfig.setExecutable(rExecutable);
        for(String arg : arguments) {
           rScriptConfig.addAttribute(arg);
        }
        File workingDirectory = createWorkingDirectory();
        prepareRInputs(workingDirectory);
        
        RScriptExec rExec = new RScriptExec();
        rExec.setWorkingDirectory(workingDirectory);
        rExec.setConfig(rScriptConfig);
        rExec.setRscriptFile(new File("test.R"));
        rExec.executeAsync();
        
        rExec.waitFor();
        
        String stdOutput = rExec.getStandardOutput();
        String errOutput = rExec.getErrorOutput();
        LOG.info("Standard output: " + stdOutput);
        LOG.info("Error output: " + errOutput);
        assertTrue("Result file does not exist.", new File(workingDirectory,"result-test.csv").exists());
        assertNotNull("Standard ouput not read.", stdOutput);
        assertNotNull("Error ouput not read.", errOutput);
        
    }
    /**
     * Creates working directory
     * @return
     */
    private File createWorkingDirectory() {
        File dir = Files.createTempDir();
        dir.deleteOnExit();
        return dir;
    }
    /**
     * Prepares R inputs
     * @param workingDirectory
     * @throws IOException
     */
    private void prepareRInputs(File workingDirectory) throws IOException {
        FileUtils.copyURLToFile(this.getClass().getResource("/testData/basic/test.R"), new File(workingDirectory,"test.R"));
        FileUtils.copyURLToFile(this.getClass().getResource("/testData/basic/data.csv"), new File(workingDirectory,"data.csv"));

    }
}
