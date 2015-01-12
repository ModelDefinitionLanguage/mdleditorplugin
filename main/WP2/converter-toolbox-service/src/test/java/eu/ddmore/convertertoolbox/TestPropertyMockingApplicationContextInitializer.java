/*******************************************************************************
 * Copyright (C) 2002 Mango Solutions Ltd - All rights reserved.
 ******************************************************************************/
package eu.ddmore.convertertoolbox;

import java.io.File;

import org.springframework.context.ApplicationContextInitializer;
import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.core.env.MutablePropertySources;
import org.springframework.core.env.StandardEnvironment;
import org.springframework.mock.env.MockPropertySource;

import com.google.common.io.Files;

/**
 * A Context initialiser that adds support for test runtime properties
 */
public class TestPropertyMockingApplicationContextInitializer implements ApplicationContextInitializer<ConfigurableApplicationContext> {

    /**
     * Extending classes should not override this method, override the createPropertySource method instead to add test-specific properties to the environment.
     */
    @Override
    public void initialize(ConfigurableApplicationContext applicationContext) {
        MutablePropertySources propertySources = applicationContext.getEnvironment().getPropertySources();
        MockPropertySource mockEnvVars = createPropertySource();
        propertySources.replace(StandardEnvironment.SYSTEM_ENVIRONMENT_PROPERTY_SOURCE_NAME, mockEnvVars);
    }
    
    /**
     * Extending classes should invoke parent class' createPropertySource() and then add their properties.
     * 
     * @return property source with additional properties
     */
    protected MockPropertySource createPropertySource() {
        return new MockPropertySource().withProperty("cts.workingDirectory", ctsWorkingDirectory());
    }

    private String ctsWorkingDirectory() {
        File tmpDir = Files.createTempDir();
        tmpDir.deleteOnExit();
        return tmpDir.getAbsolutePath();
    }
}