/* File			:  ResourceComponent.java
 * Project		:  MangoInteroperabilityFramework
 * Created on	:  30 Mar 2012
 */
package com.mango.mif.core.api;

import java.net.URL;

/**
 * 
 * Component responsible for resources management in MIF.
 * 
 * Currently the interface only handles flat request directories (no subdirectories are allowed in the directories).  
 * 
 * @version $Revision: $ as of $Date: $
 * <p>SVN Entry : $HeadURL: $
 * <p>SVN ID    : $Id: $
 * <p>Last edited by : $Author: $
 */
public interface ResourceComponent {
    /**
     * Resolves the given string uuid to Resource 
     * @param uuid
     * @return
     * @throws ResourceComponentException 
     */
    MIFResource getResource(String uuid) throws ResourceComponentException;
    
    /**
     * Removes the resource with the given id. The returned resource's content is set to null and it's exists property
     * set to false
     * @param uuid
     * @return removed resource, null otherwise
     * @throws ResourceComponentException 
     */
    MIFResource removeResource(String uuid) throws ResourceComponentException;
    
    /**
     * Adds a resource being a result of job execution.
     * 
     * @param resource
     * @param requestUUID of the request that the uploaded resource is result of
     * @param fileName - file name 
     * @return a registered resource 
     * @throws ResourceComponentException
     */
    MIFResource addResponseResource(URL resource, String requestUUID, String fileName) throws ResourceComponentException;
    /**
     * Adds a resource being an input for a job with the given requestUUID.
     * 
     * @param resource
     * @param requestUUID of the request that the uploaded resource is input for
     * @param fileName - file name 
     * @return a registered resource 
     * @throws ResourceComponentException
     */
    MIFResource addRequestResource(URL resource, String requestUUID, String fileName) throws ResourceComponentException;

    /**
     * Creates uniquely named request directory and returns the generated id. The ID should be used as a requestUUID in the request message.
     * 
     * @return unique identifier
     */
    String createRequestDirectory();
    
}
