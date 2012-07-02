/* File			:  SharedLocationManager.java
 * Project		:  MangoInteroperabilityFramework
 * Created on	:  30 Mar 2012
 */
package com.mango.mif.core.api;

import java.io.File;

/**
 * 
 * Component responsible for resources management in MIF.
 * 
 * @version $Revision: $ as of $Date: $
 * <p>SVN Entry : $HeadURL: $
 * <p>SVN ID    : $Id: $
 * <p>Last edited by : $Author: $
 */
public interface SharedLocationManager {
    /**
     * 
     * @param requestUUID
     * @return
     */
    File getRequestOutputDirectory(String requestUUID);
    /**
     * 
     * @param requestUUID
     * @return
     */
    File getRequestInputDirectory(String requestUUID);
    /**
     * 
     * @param requestUUID
     * @return
     */
    File getRequestDirectory(String requestUUID);

    /**
     * Creates uniquely named request directory and returns the generated id. The ID should be used as a requestUUID in the request message.
     * 
     * @return unique identifier
     */
    File getSharedLocationRoot();
}
