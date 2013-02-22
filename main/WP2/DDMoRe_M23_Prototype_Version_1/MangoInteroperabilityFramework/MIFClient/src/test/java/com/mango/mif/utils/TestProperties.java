/* File			:  TestProperties.java
 * Project		:  MIFClient
 * Created on	:  21 Jun 2012
 */
package com.mango.mif.utils;

/**
 * @version $Revision: $ as of $Date: $
 * <p>SVN Entry : $HeadURL: $
 * <p>SVN ID    : $Id: $
 * <p>Last edited by : $Author: $
 */
/**
 * @author mrogalski
 * A class holding common properties used
 */
public class TestProperties {
    /**
     * Alter user name
     */
    public final static String MIF_ALTER_USER_NAME = "mif.alter.client.userName";
    /**
     * Alter user password
     */
    public final static String MIF_ALTER_USER_PASSWORD = "mif.alter.client.password";
    /**
     * Alter user name
     */
    public final static String MIF_OTHER_USER_NAME = "mif.other.client.userName";
    /**
     * Alter user password
     */
    public final static String MIF_OTHER_USER_PASSWORD = "mif.other.client.password";
    /**
     * Port
     */
    public final static String MIF_JSCH_PORT = "mif.jsch.port";
    /**
     * Jsch host name
     */
    public final static String MIF_JSCH_HOST = "mif.jsch.host";
    /**
     * Common scripts location (relative to SHARED_RESOURCES_DIRECTORY)
     */
    public static final String MIF_COMMON_SCRIPTS_LOCATION = "mif.commonScriptsDirectory";
    /**
     * Generic scripts location (relative to SHARED_RESOURCES_DIRECTORY)
     */
    public static final String MIF_GENERIC_SCRIPTS_LOCATION = "mif.genericScriptsDirectory";
    /**
     * A root directory of common resource files for MIFServer and MIFClient
     */
    public static final String MIF_RUNTIME_DIRECTORY = "mif.runtime.dir";
    /**
     * templates directory location (relative to SHARED_RESOURCES_DIRECTORY)
     */
    public static final String TEMPLATES_DIRECTORY_LOCATION = "mif.templatesDirectory";
    /**
     * A path of the 'scripts/common' scripts directory relative to MIF_RUNTIME_DIRECTORY
     */
    public static final String MIF_COMMON_SCRIPTS_DIRECTORY_PATH = "mif.test.commonScriptsDirectoryPath";
    /**
     * A path of the 'scripts/generic' scripts directory relative to MIF_RUNTIME_DIRECTORY
     */
    public static final String MIF_GENERIC_SCRIPTS_DIRECTORY_PATH = "mif.test.genericScriptsDirectoryPath";
    /**
     * A path of the 'templates' directory relative to MIF_RUNTIME_DIRECTORY
     */
    public static final String MIF_TEMPLATES_DIRECTORY_PATH = "mif.test.templatesDirectoryPath";
    
   
}
