/* File			:  DefaultWorkspaceManager.java
 * Project		:  MangoInteroperabilityFramework
 * Created on	:  3 Apr 2012
 */
package com.mango.mif.core.impl;

import java.io.File;

import org.apache.log4j.Logger;

import com.google.common.base.Preconditions;
import com.mango.mif.core.api.WorkspaceManager;


/**
 * Default implementation of workspace manager
 * 
 * @version $Revision: $ as of $Date: $
 * <p>SVN Entry : $HeadURL: $
 * <p>SVN ID    : $Id: $
 * <p>Last edited by : $Author: $
 */
public class DefaultWorkspaceManager implements WorkspaceManager {
	 /**
     * Logger
     */
    private final static Logger LOG = Logger.getLogger(DefaultWorkspaceManager.class);
    /**
     * Working directory
     */
    private File workingDirectory;
    
    /**
     * Default constructor
     * @param workingDirectory
     */
    public DefaultWorkspaceManager(File workingDirectory) {
        Preconditions.checkNotNull(workingDirectory);
        Preconditions.checkArgument(workingDirectory.exists(), "Working directory does not exist.");
        this.workingDirectory = workingDirectory;
        LOG.debug("Creating the DefaultWorkSpaceManager working directory in" + workingDirectory);
    }

    @Override
    public File resolveFile(File file) {
        Preconditions.checkNotNull(file);
        return new File(workingDirectory,file.getPath());
    }

    @Override
    public File getWorkingDirectory() {
        return workingDirectory;
    }

    
}
