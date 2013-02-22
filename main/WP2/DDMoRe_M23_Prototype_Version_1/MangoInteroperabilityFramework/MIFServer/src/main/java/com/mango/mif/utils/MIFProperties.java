package com.mango.mif.utils;

import java.util.Properties;

import com.google.common.base.Preconditions;

/**
 * Commonly used properties
 */
public class MIFProperties {
	/**
     * Mif job directory prefix
     */
    public static String MIF_JOB_DIRECTORY_PREFIX = "MIF-";
	/**
     * Mif hidden directory in the request directory
     */
    public static String MIF_HIDDEN_DIRECTORY = ".MIF";
    /**
     * name of a file holding the STD error stream from GRID
     */
    public static String GRID_STD_ERR_FILE_NAME = "MIF.stderr";
    /**
     * name of a file holding the STD out file name from GRID
     */
    public static String GRID_STD_OUT_FILE_NAME = "MIF.stdout";
    /**
     * GRID shared location
     */
    public static String GRID_SHARED_LOCATION = "sgeconnector.sgeSharedLocation";
    /**
     * extension of a nonmem lst file 
     */
    public static String NONMEM_LST_FILE_EXT = ".lst";
    /**
     * Extension of a nonmem model file.  Not quite sure what this is doing in
     * MIF properties...
     */
    public static String NONMEM_MODEL_FILE_EXT = ".mod";
    /**
     * MIF service account user name
     */
    public static String MIF_SERVICE_ACCOUNT_USER_NAME = "mif.serviceAccount.userName";
    /**
     * MIF service account user password
     */
    public static String MIF_SERVICE_ACCOUNT_USER_PASSWORD = "mif.serviceAccount.userPassword";
    /**
     * Singleton
     */
    private static MIFProperties instance;
    /**
     * Properties
     */
    private Properties properties;

    /**
     * Constructor
     * @param properties
     */
    private MIFProperties(Properties properties) {
        this.properties = properties;
        load();
    }

    /**
     * loads properties
     * @param properties
     * @return
     */
    public static synchronized MIFProperties loadProperties(Properties properties) {
        if (instance == null) {
            instance = new MIFProperties(properties);
        }
        return instance;
    }

    /**
     * singleton instance
     */
    public static synchronized MIFProperties getInstance() {
        return loadProperties(null);
    }

    void load() {
        Preconditions.checkNotNull(properties, "Properties source not set");
        MIF_HIDDEN_DIRECTORY = getProperty("sgeconnector.sgeHiddenMifDirectoryName");
        GRID_STD_ERR_FILE_NAME = getProperty("sgeconnector.sgeJobStdErrFileName");
        GRID_STD_OUT_FILE_NAME = getProperty("sgeconnector.sgeJobStdOutFileName");
        NONMEM_LST_FILE_EXT = getProperty("nonmemsgeconnector.lstFileExtension");
    }

    public String getProperty(String key) {
        return properties.getProperty(key);
    }
    
    public Properties getProperties() {
        return properties;
    }
}
