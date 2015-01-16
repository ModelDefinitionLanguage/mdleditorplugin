/*******************************************************************************
 * Copyright (C) 2015 Mango Solutions Ltd - All rights reserved.
 ******************************************************************************/
package eu.ddmore.convertertoolbox;

import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.TemporaryFolder;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.IntegrationTest;
import org.springframework.boot.test.SpringApplicationConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.web.WebAppConfiguration;

import eu.ddmore.convertertoolbox.rest.ConversionController;
import eu.ddmore.convertertoolbox.rest.HomeController;
import eu.ddmore.convertertoolbox.service.ConversionCapabilitiesProvider;
import eu.ddmore.convertertoolbox.service.ConversionService;
import eu.ddmore.convertertoolbox.service.impl.ConversionReaper;
import eu.ddmore.convertertoolbox.service.impl.ServiceWorkingDirectory;


/**
 * Integration Test for whole application, this test should not test business logic.
 * 
 * This test is just to verify that the instance can be started up and the beans are correctly initialized
 */
@RunWith(SpringJUnit4ClassRunner.class)
@SpringApplicationConfiguration(classes = {Application.class}
, initializers = TestPropertyMockingApplicationContextInitializer.class)
@WebAppConfiguration
@IntegrationTest({"server.port=0", "management.port=0"}) //let the framework choose the port
public class ApplicationIntegrationTest {

    @Rule
    public TemporaryFolder temporaryFolder = new TemporaryFolder();

    @Autowired(required=true)
    private ConversionService conversionService;
    
    @Autowired(required=true)
    private ConversionCapabilitiesProvider capabilitiesProvider;

    @Autowired(required=true)
    private ConversionReaper conversionReaper;
    
    @Autowired(required=true)
    private ConversionController conversionController;
    
    @Autowired(required=true)
    private HomeController homeController;
    
    @Autowired(required=true)
    private ServiceWorkingDirectory serviceWorkingDirectory;
    
    @Test
    public void shouldStartUpTheFrameworkAndDeployBeansIntoContainer() {
        //Empty, no implementation required, we just test if spring configuration of the runtime application starts up
    }

}
