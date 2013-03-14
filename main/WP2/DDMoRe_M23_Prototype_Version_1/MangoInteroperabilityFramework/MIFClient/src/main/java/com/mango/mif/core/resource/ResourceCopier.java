package com.mango.mif.core.resource;

import java.io.File;
import java.util.List;

import com.mango.mif.core.exec.ExecutionException;
import com.mango.mif.exception.MIFException;

/**
 * Interface Responsible for copying Resources from Source Directory to the Destination Directory.
 * This should also support list of files and the folder to be ignored during copy.
 *  
 * @version $Revision: $ as of $Date: $
 * <p>SVN Entry : $HeadURL: $
 * <p>SVN ID    : $Id: $
 * <p>Last edited by : $Author: $
 */
public interface ResourceCopier {

    /**
     * Set the job id for logging purposes.
     * @param id The job id.
     */
    void setJobId(String id) throws ExecutionException;

    /**
     * Copy the files from source directory to Destination.
     * If the List of files is provided then the copying process would 
     * ONLY copy the files that are been provided , from the source to the destination Directory.
     *
     * @param filesList the files list :
     * @throws MIFException the mIF exception
     */
    void copy(List<File> filesList) throws ExecutionException;
}
