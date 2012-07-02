/* File			:  UserSharedLocationManager.java
 * Project		:  MIFClient
 * Created on	:  11 Jun 2012
 */
package com.mango.mif.core.resource;

import java.io.File;

import com.google.common.base.Preconditions;
import com.mango.mif.core.api.SharedLocationManager;
import com.mango.mif.core.api.WorkspaceManager;

/**
 * User request component. It basic implementation
 * 
 * @version $Revision: $ as of $Date: $
 * <p>SVN Entry : $HeadURL: $
 * <p>SVN ID    : $Id: $
 * <p>Last edited by : $Author: $
 */
/**
 * @author mrogalski
 *
 */
public class UserSharedLocationManager implements SharedLocationManager {

    /**
     * Workspace manager used to manage working directory
     */
    private WorkspaceManager	workspaceManager;
    /**
     * Request input directory
     */
    public final static String REQUEST_INPUT = "inputs";
    /**
     * Request output directory
     */
    public final static String REQUEST_OUTPUT = "outputs";

    @Override
    public File getRequestOutputDirectory(String requestUUID) {
        Preconditions.checkNotNull(workspaceManager);
        return workspaceManager.resolveFile(new File(requestUUID,REQUEST_OUTPUT));
    }

    @Override
    public File getRequestInputDirectory(String requestUUID) {
        Preconditions.checkNotNull(workspaceManager);
        return workspaceManager.resolveFile(new File(requestUUID,REQUEST_INPUT));
    }

    @Override
    public File getRequestDirectory(String requestUUID) {
        Preconditions.checkNotNull(workspaceManager);
        return workspaceManager.resolveFile(new File(requestUUID));
    }

    public void setWorkspaceManager(WorkspaceManager workspaceManager) {
        this.workspaceManager = workspaceManager;
    }

    public WorkspaceManager getWorkspaceManager() {
        return workspaceManager;
    }

    /* (non-Javadoc)
     * @see com.mango.mif.core.api.SharedLocationManager#getSharedLocationRoot()
     */
    @Override
    public File getSharedLocationRoot() {
        return workspaceManager.getWorkingDirectory();
    }
}
