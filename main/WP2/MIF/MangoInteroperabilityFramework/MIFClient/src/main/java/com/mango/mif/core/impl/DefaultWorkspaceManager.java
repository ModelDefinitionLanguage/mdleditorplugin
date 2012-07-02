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
     * Default constructor, turn checking on.
     * @param workingDirectory
     */
    public DefaultWorkspaceManager(File workingDirectory) {
        this(workingDirectory, true);
    }

    /**
     * Other constructor. Allow checking to be turned on or off.
     * @param workingDirectory the working directory
     * @param check if true, check for existence of working directory
     */
    public DefaultWorkspaceManager(File workingDirectory, boolean check) {
        Preconditions.checkNotNull(workingDirectory);
        if (check) {
            Preconditions.checkArgument(workingDirectory.exists(), "Working directory " + workingDirectory + " does not exist.");
            Preconditions.checkArgument(workingDirectory.canRead(),"Working directory " + workingDirectory + " is not readable.");
            Preconditions.checkArgument(workingDirectory.canWrite(),"Working directory " + workingDirectory + " is not writeable.");
            Preconditions.checkArgument(workingDirectory.canExecute(),"Working directory " + workingDirectory + " is not searchable.");
        }
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
