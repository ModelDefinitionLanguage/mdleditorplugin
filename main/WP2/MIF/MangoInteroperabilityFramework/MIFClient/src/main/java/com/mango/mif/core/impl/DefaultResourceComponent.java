/* File			:  DefaultResourceComponent.java
 * Project		:  MangoInteroperabilityFramework
 * Created on	:  3 Apr 2012
 */
package com.mango.mif.core.impl;

import java.io.File;
import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.UUID;

import org.apache.commons.io.FileUtils;
import org.apache.log4j.Logger;

import com.google.common.base.Preconditions;
import com.mango.mif.core.api.MIFResource;
import com.mango.mif.core.api.ResourceComponent;
import com.mango.mif.core.api.ResourceComponentException;
import com.mango.mif.core.api.WorkspaceManager;


/**
 * 
 * Default implementation of resource component
 * 
 * @version $Revision: $ as of $Date: $
 * <p>SVN Entry : $HeadURL: $
 * <p>SVN ID    : $Id: $
 * <p>Last edited by : $Author: $
 */
public class DefaultResourceComponent implements ResourceComponent {
    /**
     * Logger
     */
    private final static Logger LOG = Logger.getLogger(DefaultResourceComponent.class);
    /**
     * Workspace manager used to manage working directory
     */
    private WorkspaceManager workspaceManager;
    
    @Override
    public synchronized MIFResource getResource(String uuid) throws ResourceComponentException {
        Preconditions.checkNotNull(workspaceManager,"Resource Component not configured.");
        Preconditions.checkNotNull(uuid, "Unique identifier can't be null.");
        File file = convertToSystemFile(uuid);
        File resolved = workspaceManager.resolveFile(file);
        LOG.debug("The uuid " + uuid + " has been resolved to: " + resolved.getAbsolutePath());
        
        if(resolved.exists()) {
            try {
                return new MIFResource(uuid, resolved.toURI().toURL(), resolved.getName());
            } catch (MalformedURLException e) {
                throw new ResourceComponentException("Can't access the resource",e);
            }
        }
        LOG.debug("The resource " + uuid + " does not exist.");
        return null;
    }

    private MIFResource addResource(URL resource, File file) throws ResourceComponentException {
        Preconditions.checkNotNull(resource);
        Preconditions.checkNotNull(file);
        Preconditions.checkNotNull(workspaceManager, "Resource Component not configured.");
        File resourceFile = workspaceManager.resolveFile(file);
        resourceFile.getParentFile().mkdirs();
        
        LOG.debug("The resource will be saved to: " + resourceFile.getAbsolutePath());
        URL url = null;
        try {
            FileUtils.copyURLToFile(resource, resourceFile);
            url = resourceFile.toURI().toURL();
        } catch (IOException e) {
            throw new ResourceComponentException("Could not upload file.", e);
        }
        LOG.debug("File has been copied to working directory, UUID: " + convertToUnifiedPath(file));
        MIFResource mifResource = new MIFResource(convertToUnifiedPath(file), url, resourceFile.getName());

        return mifResource;
    }

    @Override
    public synchronized MIFResource addRequestResource(URL resource, String requestUUID, String name) throws ResourceComponentException {
        File requestDir = getRequestDirectory(requestUUID);

        Preconditions.checkArgument(workspaceManager.resolveFile(requestDir).getParentFile().exists(), "Directory for the requestUUID " + requestUUID + " does not exist. The directory should be created first invoking the createRequestDirectory method.");
            
        return addResource(resource, new File(requestDir,name));
    }
    
    /**
     * Builds results directory path, the path is relative to the root directory
     * @param requestUUID
     * @return
     */
    private File getResultsDirectory(String requestUUID) {
        return new File(requestUUID,"result");
    }

    /**
     * Builds results directory path, the path is relative to the root directory
     * @param requestUUID
     * @return
     */
    private File getRequestDirectory(String requestUUID) {
        return new File(requestUUID,"request");
    }

    private String convertToUnifiedPath(File path) {
        return path.toString().replace(File.separatorChar, '/');
    }

    private File convertToSystemFile(String path) {
        return new File(path.replace('/', File.separatorChar));
    }
    
    @Override
    public synchronized MIFResource addResponseResource(URL resource, String requestUUID, String fileName) throws ResourceComponentException {
        File requestDir = getResultsDirectory(requestUUID);

        Preconditions.checkArgument(workspaceManager.resolveFile(requestDir).getParentFile().exists(), "Directory for the requestUUID " + requestUUID + " does not exist. The directory should be created first invoking the createRequestDirectory method.");

        return addResource(resource, new File(requestDir,fileName));
    }

    public void setWorkspaceManager(WorkspaceManager workspaceManager) {
        this.workspaceManager = workspaceManager;
    }
    
    public WorkspaceManager getWorkspaceManager() {
        return workspaceManager;
    }

    @Override
    public MIFResource removeResource(String uuid) throws ResourceComponentException {
        Preconditions.checkNotNull(uuid);
        File resolved = workspaceManager.resolveFile(convertToSystemFile(uuid));
        if(!resolved.exists()) {
            throw new ResourceComponentException("File " + uuid + " does not exist.");
        }
        try {
            resolved.delete();
        } catch(Exception e) {
            throw new ResourceComponentException("File " + uuid + " could not be deleted.", e);
        }
        return new MIFResource(uuid,null,resolved.getName(),false);
    }

    /**
     * Generates a unique identifier and creates a directory for that 
     */
    @Override
    public String createRequestDirectory() {
        UUID uuid = null;
        boolean done = false;
        while(!done) {
            uuid = UUID.randomUUID();
            File dir = new File(uuid.toString());
            if(dir.exists()) {
                uuid = UUID.randomUUID();
                continue;
            }
            if(workspaceManager.resolveFile(dir).mkdir()) {
                done = true; 
            }
        }
        return uuid.toString();
    }

}
