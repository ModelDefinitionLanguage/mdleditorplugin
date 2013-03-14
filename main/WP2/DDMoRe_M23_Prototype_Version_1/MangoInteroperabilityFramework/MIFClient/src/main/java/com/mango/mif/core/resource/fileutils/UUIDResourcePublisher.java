/* File			:  UUIDResourcePublisher.java
 * Project		:  MIFClient
 * Created on	:  11 Jun 2012
 */
package com.mango.mif.core.resource.fileutils;

import java.io.File;
import java.io.IOException;
import java.util.UUID;

import org.apache.log4j.Logger;

import sun.reflect.generics.reflectiveObjects.NotImplementedException;

import com.google.common.base.Preconditions;
import com.mango.mif.core.api.ResourceUtils;
import com.mango.mif.core.resource.BaseResourcePublisher;

/**
 * Provides a two step, prepare directories and publish directories implementation.
 * @version $Revision: $ as of $Date: $
 * <p>SVN Entry : $HeadURL: $
 * <p>SVN ID    : $Id: $
 * <p>Last edited by : $Author: $
 */
public class UUIDResourcePublisher extends BaseResourcePublisher {

    public final static Logger LOG = Logger.getLogger(UUIDResourcePublisher.class);

    @Override
    public String publish() {
        throw new NotImplementedException();
    }
    
    public String prepare(UUID uuid) {
        
        Preconditions.checkNotNull(rootDirectory, "Root directory can't be null");
        Preconditions.checkNotNull(sharedLocationManager, "Shared location manager can't be null");
        return createRequestDirectory(uuid.toString());
    }

    public void publish(UUID uuid) {
        
        for (File file : filesToPublish) {
            File destFile = getRequestDestinationFile(file, uuid.toString());
            try {
                ResourceUtils.copyFile(file, destFile);
            } catch (IOException e) {
                LOG.error(e);
                throw new RuntimeException("Could not copy file " + file + " to " + destFile, e);
            }
        }
    }

    protected File getRequestDestinationFile(File file, String requestID) {
        
        String path = file.getAbsolutePath().replace(rootDirectory.getAbsolutePath() + File.separator, "");
        return new File(sharedLocationManager.getRequestInputDirectory(requestID), path);
    }

    /**
     * Creates a directory named with that unique ID
     * @return a request directory
     */
    public String createRequestDirectory(String uuid) {
        
        LOG.debug("Random UUID: " + uuid);
        File requestDir = new File(sharedLocationManager.getSharedLocationRoot(), uuid.toString());

        Preconditions.checkNotNull(requestDir);
        requestDir.mkdir();
        LOG.info("Created request directory: " + requestDir);

        File inputDir = sharedLocationManager.getRequestInputDirectory(requestDir.getName());
        inputDir.mkdirs();            
        LOG.info("Created request input directory: " + inputDir);

        File outputDir = sharedLocationManager.getRequestOutputDirectory(requestDir.getName());
        outputDir.mkdirs();            
        LOG.info("Created request output directory: " + outputDir);      
        
        return uuid.toString();
    }
}

