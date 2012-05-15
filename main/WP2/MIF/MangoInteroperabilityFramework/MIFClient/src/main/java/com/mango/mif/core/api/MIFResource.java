/* File			:  MIFResource.java
 * Project		:  MangoInteroperabilityFramework
 * Created on	:  30 Mar 2012
 */
package com.mango.mif.core.api;

import java.net.URL;

/**
 * 
 * Represents a resource used/produced by job runners 
 * 
 * @version $Revision: $ as of $Date: $
 * <p>SVN Entry : $HeadURL: $
 * <p>SVN ID    : $Id: $
 * <p>Last edited by : $Author: $
 */
public class MIFResource {
    /**
     * UUID of the resource
     */
    private final String uuid;
    /**
     * Content
     */
    private final URL content;
    /**
     * name of the resource
     */
    private final String name;
    /**
     * Flag indicating if the resource exists
     */
    private final boolean exists;
    /**
     * 
     * @param uuid
     * @param content
     * @param name
     */
    public MIFResource(String uuid, URL content, String name) {
        super();
        this.uuid = uuid;
        this.content = content;
        this.name = name;
        this.exists = true;
    }
    /**
     * 
     * @param uuid
     * @param content
     * @param name
     * @param exists
     */
    public MIFResource(String uuid, URL content, String name, boolean exists) {
        super();
        this.uuid = uuid;
        this.content = content;
        this.name = name;
        this.exists = exists;
    }
    
    public String getUuid() {
        return uuid;
    }
    
    public String getName() {
        return name;
    }
    
    public URL getContent() {
        if(!exists) throw new RuntimeException("The resource has been removed, the content is not available.");
        return content;
    }
    /**
     * 
     * @return true if the resource exists, false otherwise
     */
    public boolean exists() {
        return exists;
    }
}