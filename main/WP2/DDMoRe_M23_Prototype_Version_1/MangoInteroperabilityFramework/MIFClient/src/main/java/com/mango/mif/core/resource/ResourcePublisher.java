/* File			:  ResourcePublisher.java
 * Project		:  MIFClient
 * Created on	:  7 Jun 2012
 */
package com.mango.mif.core.resource;

import java.io.File;
import java.util.List;
import java.util.Map;

import com.mango.mif.core.exec.ExecutionException;
import com.mango.mif.exception.MIFException;

/**
 * 
 * Resource Publisher is responsible for a process of publishing files to the shared location
 * 
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
public interface ResourcePublisher extends SharedLocationOperation {

    /**
     * Publishes files to the shared location and returns a unique ID of the request directory
     * @return
     */
    String publish() throws MIFException;

    /**
     * 
     * @return a request ID generated when files have been published
     */
    void setRootDirectory(File rootDirectory);

    /**
     * Adds the file.
     *
     * @param file the file
     * @param root the root
     */
    void addFile(File file, File root);

    /**
     * Adds the file.
     *
     * @param file the file
     */
    void addFile(File file);

    /**
     * Adds the files.
     *
     * @param files the files
     */
    void addFiles(List<File> files);

    /**
     * Gets the request id.
     *
     * @return the request id
     */
    String getRequestID();

    /**
     * Sets the script preamble.
     *
     * @param submitHostPreamble the new script preamble
     */
    void setSubmitHostPreamble(String submitHostPreamble);

    /**
     * Sets the resource copier.
     *
     * @param resourceCopier the new resource copier
     */
    void setResourceCopier(ResourceCopier resourceCopier);

    /**
     * Sets the execution request attributes
     * 
     * @param requestAttributes The execution request attributes to set.
     * @throws ExecutionException If we screw up.
     */
    void setCommandBuilderContext(Map<String, String> requestAttributes) throws ExecutionException;

    /**
     * Sets the execution request attributes
     * 
     * @param requestAttributes The execution request attributes to set.
     * @param submitHostPreamble The submit host preamble.
     * @param gridHostPreamble The grid host preamble.
     * @throws ExecutionException If we screw up.
     */
    void setTheCommandBuilderContext(Map<String, String> requestAttributes, String submitHostPreamble, String gridHostPreamble) throws ExecutionException;
}
