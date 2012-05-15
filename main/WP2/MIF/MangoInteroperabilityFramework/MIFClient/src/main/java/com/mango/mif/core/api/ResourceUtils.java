/* File			:  ResourceUtils.java
 * Project		:  MangoInteroperabilityFramework
 * Created on	:  2 Apr 2012
 */
package com.mango.mif.core.api;

import java.io.File;
import java.io.IOException;

import org.apache.commons.io.FileUtils;

import com.google.common.base.Preconditions;

/**
 * 
 * Helper class for accessing and operating on resources
 * 
 * TODO write tests
 * 
 * @version $Revision: $ as of $Date: $
 * <p>SVN Entry : $HeadURL: $
 * <p>SVN ID    : $Id: $
 * <p>Last edited by : $Author: $
 */
public class ResourceUtils {
    /**
     * Copies the given resource to given location
     * @param from
     * @param to
     * @throws IOException 
     */
    public static void copy(MIFResource from, File to) throws IOException {
        Preconditions.checkNotNull(from, "From resource is null.");
        Preconditions.checkNotNull(to, "To file is null.");
        Preconditions.checkNotNull(from.getContent(), "From file is null.");
        FileUtils.copyURLToFile(from.getContent(), to);
    }
}
