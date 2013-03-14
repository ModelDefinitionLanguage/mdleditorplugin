package com.mango.mif.utils;

import java.io.File;
import java.util.Map;
import java.util.Properties;
import java.util.Set;
import java.util.TreeMap;

import org.apache.commons.lang.StringUtils;
import org.apache.log4j.Logger;
import org.springframework.beans.BeansException;
import org.springframework.beans.factory.config.ConfigurableListableBeanFactory;
import org.springframework.beans.factory.config.PropertyPlaceholderConfigurer;

import com.google.common.collect.Sets;

/**
 * MIF Placeholder configurer and validator.
 */
public class MIFPropertyPlaceholderConfigurer extends PropertyPlaceholderConfigurer {

    private static final Logger LOG = Logger.getLogger(MIFPropertyPlaceholderConfigurer.class);

    private boolean validatePropertiesOnStart = true;

    @Override
    protected void processProperties(ConfigurableListableBeanFactory beanFactory, Properties properties) throws BeansException {            
    	
        super.processProperties(beanFactory, properties);
        
        displayImportantAsciiLogo(displayVersion((String) properties.get("mif.version"), "PROJECT_VERSION"), 
        		displayVersion((String) properties.get("mif.svnrevision"), "SVN_REVISION"),
        		displayVersion((String) properties.get("mif.buildnumber"), "BUILD_NUMBER"));

        Map<Object, Object> sortedProps = new TreeMap<Object, Object>();
        sortedProps.putAll(properties);

        Map<String, String> dbProps = new TreeMap<String, String>();

        LOG.info("\n\n\n - MIF Application Properties - \n");
        LOG.info("Configuring from external properties " + properties.get("mif.configuration.dir"));
        for (Object obj : sortedProps.keySet()) {
            
            String key = (String) obj;
            String value = (String) sortedProps.get(key);
            
            if (!key.toLowerCase().contains("password")) {
                LOG.info("Configuration Property: " + key + ": " + value);
                if (key.startsWith("mif.hibernate")) {
                    dbProps.put(key, value);
                }
            } 
        }

        LOG.info("\n\n\n - MIF Database Properties - \n");
        for (String dbPropKey : dbProps.keySet()) {
            LOG.info(dbPropKey + ": " + dbProps.get(dbPropKey));
        }

        
        if (validatePropertiesOnStart) {            
            LOG.info("\n\n\n - Validating MIF properties - \n");
            Set<String> failedProperties = Sets.newHashSet();
            validateStateHandlersPoolSize(properties, failedProperties);
            validateMifConfigDir(properties, failedProperties);
            validateSharedDirectories(properties, failedProperties);
            checkExit(failedProperties);
        }

        MIFProperties.loadProperties(properties);
    }

	private String displayVersion(String property, String defaultValue) {
		if (StringUtils.isNotEmpty(property) && !property.equals(defaultValue)) {
			return property;
		} else {
			return " n/a ";
		}
	}

    private void validateStateHandlersPoolSize(Properties properties, Set<String> failedProperties) {
        
        String corePoolSize = (String) properties.get("connector.jobRunnerStateHandlersTaskExecutor.corePoolSize");
        if (Integer.valueOf(corePoolSize) > 1) {
            failedProperties.add("The state handler core pool size can never exceed a size of 1");
        }
    }

    private void displayImportantAsciiLogo(String version, String revision, String buildNumber) {
        
        StringBuilder logoVersion = new StringBuilder();
        logoVersion.append("\n __  __ ___ _____ \n");
        logoVersion.append("|  \\/  |_ _|  ___|\n");
        logoVersion.append("| |\\/| || || |_   \n");
        logoVersion.append("| |  | || ||  _|  \n");
        logoVersion.append("|_|  |_|___|_|    \n\n");
        logoVersion.append("Mango Interoperability Framework version: ");
        logoVersion.append(version);
        logoVersion.append(" revision: ");	
        logoVersion.append(revision);
        logoVersion.append(" build: ");
        logoVersion.append(buildNumber);
        logoVersion.append("\n");
        LOG.warn(logoVersion);
    }

    private void validateMifConfigDir(Properties properties, Set<String> failedProperties) {
        
        String mifConfigDir = properties.getProperty("mif.configuration.dir");
        if (!new File(mifConfigDir).exists()) {
            failedProperties.add("FATAL: Could not find the mif configuration file: " + properties.getProperty("mif.configuration.dir"));
        }
    }
    
    private void validateSharedDirectories(Properties properties, Set<String> failedProperties) {

        validateDirectory(properties, failedProperties, "mif.working.dir");
        validateDirectory(properties, failedProperties, "mif.templatesDirectory");
        validateDirectory(properties, failedProperties, "mif.commonScriptsDirectory");
        validateDirectory(properties, failedProperties, "mif.genericScriptsDirectory");
    }

    private void validateDirectory(Properties properties, Set<String> failedProperties, String property) {

        if (properties.getProperty(property) != null) {
            
            File dir = new File(properties.getProperty(property));
            if (!dir.exists() || !dir.canRead() || !dir.canWrite() || !dir.canExecute()) {
                
                failedProperties.add( String.format("Directory failed: %s - exists[%s] readable[%s], writeable[%s], executable[%s]", 
                                          properties.getProperty(property), 
                                          dir.exists(), dir.canRead(), dir.canWrite(), dir.canExecute()) );
            }
        }
    }

    private void checkExit(Set<String> failedProperties) {

        if (failedProperties.size() > 0) {
            
            for (String prop: failedProperties) {
                System.err.println(prop);
            }
            System.err.println("\nAborting MIF startup.  Please correct the above errors prior to starting the MIF application\n");
            System.exit(-1);
        } else {
            LOG.info("Validation SUCCESSFUL, continuing...\n\n");
        }
    }
    
    public boolean isValidatePropertiesOnStart() {
        return validatePropertiesOnStart;
    }

    public void setValidatePropertiesOnStart(boolean validatePropertiesOnStart) {
        this.validatePropertiesOnStart = validatePropertiesOnStart;
    }

}
