/* File			:  RScriptConfig.java
 * Project		:  MangoInteroperabilityFramework
 * Created on	:  29 Mar 2012
 */
package com.mango.mif.rconnectorcli.internal;

import java.io.File;
import java.util.HashSet;
import java.util.Set;

/**
 * 
 * Collects properties of R execution environment
 * @version $Revision: $ as of $Date: $
 * <p>SVN Entry : $HeadURL: $
 * <p>SVN ID    : $Id: $
 * <p>Last edited by : $Author: $
 */
public class RScriptConfig {
    /**
     * R Script executable location
     */
    private File executable;
    /**
     * Parameters that should be passed to R
     */
    private Set<String> attributes = new HashSet<String>();
    
    public File getExecutable() {
        return executable;
    }
    
    public void setExecutable(File executable) {
        this.executable = executable;
    }
    
    public void addAttribute(String attribute) {
        attributes.add(attribute);
    }
    
    public Set<String> getAttributes() {
        return attributes;
    }
    
    
    public void setAttributes(Set<String> attributes) {
        this.attributes = attributes;
    }
}
