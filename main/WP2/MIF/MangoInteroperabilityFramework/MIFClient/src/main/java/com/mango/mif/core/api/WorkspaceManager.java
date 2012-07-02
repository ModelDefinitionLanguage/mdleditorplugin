/* File			:  WorkspaceManager.java
 * Project		:  MangoInteroperabilityFramework
 * Created on	:  3 Apr 2012
 */
package com.mango.mif.core.api;

import java.io.File;

/**
 * 
 * A component used by connector's components to resolve resources stored in the workspace
 * 
 * @version $Revision: $ as of $Date: $
 * <p>SVN Entry : $HeadURL: $
 * <p>SVN ID    : $Id: $
 * <p>Last edited by : $Author: $
 */
public interface WorkspaceManager {
    /**
     * Resolves file specified by a relative path to its current absolute location. The path is resolved against working directory.
     * @param file
     * @return
     */
    File resolveFile(File file);
    /**
     * 
     * @return working directory
     */
    File getWorkingDirectory();
}
