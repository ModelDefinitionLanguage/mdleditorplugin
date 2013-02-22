/* File			:  ExecutionHelper.java
 * Project		:  MIFServer
 * Created on	:  Apr 24, 2012
 */
package com.mango.mif.utils;

import java.io.File;

import org.apache.log4j.Logger;

import com.google.common.base.Preconditions;
import com.google.common.io.Files;

/**
 * Helper class during Execution.
 * @version $Revision: $ as of $Date: $
 * <p>SVN Entry : $HeadURL: $
 * <p>SVN ID    : $Id: $
 * <p>Last edited by : $Author: $
 */
public class ExecutionHelper {
    
    /** Logger. */
    public final static Logger LOG = Logger.getLogger(ExecutionHelper.class);
    
    
    /**
     * Creates the temporary directory .
     *
     * @return the file
     */
    public static File createTmpDirectory() {
        File dir = Files.createTempDir();
        LOG.info("Created temporary directory without name :" + dir);
        dir.deleteOnExit();
        return dir;
    }
	
    
    /**
     * Creates the temporary directory with the provided name.
     *
     * @param name the name : name of the Temporary Directory
     * @return the file: descriptor of the created Directory
     */
    public static File createTmpDirectory(String name) {
        Preconditions.checkNotNull(name);
        String tmpDirName = System.getProperty("java.io.tmpdir");
        LOG.info("Created temporary directory:" + tmpDirName);
        Preconditions.checkNotNull(tmpDirName);
        File tmpDir = new File(tmpDirName);
        File result = new File(tmpDir,name);
        LOG.info("Created temporary directory:" + result);
        result.mkdirs();
        result.deleteOnExit();
        return result;
    }

}
