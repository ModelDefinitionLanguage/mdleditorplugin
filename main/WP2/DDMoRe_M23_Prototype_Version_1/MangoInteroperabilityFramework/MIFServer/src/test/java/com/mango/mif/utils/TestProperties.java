/* File			:  TestProperties.java
 * Project		:  MIFServer
 * Created on	:  12 Jun 2012
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
 * 
 * Class holding parameters names used in tests
 */
public class TestProperties {
    /**
     * property storing MIF service client user name
     */
    public final static String MIF_CLIENT_USER_NAME = "mif.client.userName";
    /**
     * property storing MIF service client password
     */
    public final static String MIF_CLIENT_PASSWORD = "mif.client.password";
    /**
     * property storing MIF service client user name
     */
    public final static String MIF_ALTER_CLIENT_USER_NAME = "mif.alter.client.userName";
    /**
     * property storing MIF service client password
     */
    public final static String MIF_ALTER_CLIENT_PASSWORD = "mif.alter.client.password";
    /**
     * Alter user password
     */
    public final static String MIF_JSCH_PORT = "mif.jsch.port";
    /**
     * Jsch host name
     */
    public final static String MIF_JSCH_HOST = "mif.jsch.host";
    /**
     * SGE shared location
     */
    public final static String SGE_SHARED_LOCATION = "sgeconnector.sgeSharedLocation";
    /**
     * Parameter string passed to qsub in tests
     */
    public final static String SGE_QSUB_PARAMETERS = "mif.sge.qsubParameters";
    /**
     * R executable that should be use to run R script
     */
    public static final String MIF_R_EXECUTABLE = "rExecutable";
    /**
     * NONMEM executable that should be use to run NONMEM control file
     */
    public static final String MIF_NONMEM_EXECUTABLE = "nonmemExecutable";
    /**
     * Common scripts location
     */
    public static final String MIF_COMMON_SCRIPTS_LOCATION = "mif.commonScriptsDirectory";
    /**
     * Generic scripts location
     */
    public static final String MIF_GENERIC_SCRIPTS_LOCATION = "mif.genericScriptsDirectory";
    /**
     * Nonmem SGE connector scripts location
     */
    public static final String MIF_NONMEMSGE_CONNECTOR_SCRIPTS_LOCATION = "nonmemsgeconnector.connectorScriptsDirectory";
    /**
     * Nonmem SGE template location
     */
    public static final String MIF_NONMEMSGE_CONNECTOR_TEMPLATE_LOCATION = "nonmemsgeconnector.templateDirectory";
    /**
     * PsN SGE connector scripts location
     */
    public static final String MIF_PSNSGE_CONNECTOR_SCRIPTS_LOCATION = "psnsgeconnector.connectorScriptsDirectory";
    /**
     * A root directory of common resource files for MIFServer and MIFClient
     */
    public static final String MIF_RUNTIME_DIRECTORY = "mif.runtime.dir";
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
    /**
     * templates directory location (relative to SHARED_RESOURCES_DIRECTORY)
     */
    public static final String TEMPLATES_DIRECTORY_LOCATION = "mif.templatesDirectory";

}
