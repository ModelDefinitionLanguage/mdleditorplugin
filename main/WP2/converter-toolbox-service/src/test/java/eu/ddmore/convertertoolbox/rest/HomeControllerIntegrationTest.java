/*******************************************************************************
 * Copyright (C) 2015 Mango Solutions Ltd - All rights reserved.
 ******************************************************************************/
package eu.ddmore.convertertoolbox.rest;

import static eu.ddmore.convertertoolbox.rest.ConversionTestFixturesHelper.from;
import static eu.ddmore.convertertoolbox.rest.ConversionTestFixturesHelper.to;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertTrue;
import static org.mockito.Mockito.when;

import java.util.Arrays;

import org.apache.commons.lang.StringUtils;
import org.apache.log4j.Logger;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.context.embedded.EmbeddedWebApplicationContext;
import org.springframework.boot.test.IntegrationTest;
import org.springframework.boot.test.SpringApplicationConfiguration;
import org.springframework.http.ResponseEntity;
import org.springframework.mock.env.MockPropertySource;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.web.WebAppConfiguration;
import org.springframework.web.client.RestTemplate;

import eu.ddmore.convertertoolbox.TestPropertyMockingApplicationContextInitializer;
import eu.ddmore.convertertoolbox.domain.ConversionCapability;
import eu.ddmore.convertertoolbox.domain.hal.LinkRelation;
import eu.ddmore.convertertoolbox.domain.hal.ServiceDescriptorResource;
import eu.ddmore.convertertoolbox.service.ConversionCapabilitiesProvider;

/**
 * Integration Tests the exposed REST service endpoints
 */
@RunWith(SpringJUnit4ClassRunner.class)
@SpringApplicationConfiguration(classes = {TestInstanceConfiguration.class, RestClientConfiguration.class}
, initializers = HomeControllerIntegrationTest.ApplicationContextInitializer.class)
@WebAppConfiguration
@IntegrationTest({"server.port=0", "management.port=0"}) //let the framework choose the port
public class HomeControllerIntegrationTest {
    /**
     * Introduces test runtime properties
     */
    public static class ApplicationContextInitializer extends TestPropertyMockingApplicationContextInitializer {
        @Override
        protected MockPropertySource createPropertySource() {
            return super.createPropertySource().
                    withProperty("cts.support.url", "http://mock.host:mock.port");
        }
    }
    private static final Logger LOG = Logger.getLogger(HomeControllerIntegrationTest.class);

    private static final String URL = "http://localhost";
    @Autowired
    private EmbeddedWebApplicationContext server;

    @Value("${local.server.port}") //get the dynamically assigned port
    private int port;
    
    @Autowired
    private RestTemplate restTemplate;

    @Autowired
    private ConversionCapabilitiesProvider capabilitiesProvider;

    @Test
    public void shouldGetServiceDescriptorWithEmbeddedHypertextLinks() {
        
        when(capabilitiesProvider.getCapabilities()).thenReturn(
            Arrays.asList(
                new ConversionCapability(from("A"), to("B", "C", "D")),
                new ConversionCapability(from("C"), to("B"))
                ));
        
        ResponseEntity<ServiceDescriptorResource> response = restTemplate.getForEntity(URL+":"+port, ServiceDescriptorResource.class);
        
        LOG.debug(response.getBody());

        assertNotNull(response.getBody().getContent());
        assertNotNull(response.getBody().getLinks());
        assertEquals(3, response.getBody().getLinks().size());
        assertEquals(LinkRelation.SELF.getRelation(), response.getBody().getLinks().get(0).getRel());
        assertEquals(LinkRelation.SUBMIT.getRelation(), response.getBody().getLinks().get(1).getRel());
        assertEquals(LinkRelation.SUPPORT.getRelation(), response.getBody().getLinks().get(2).getRel());
        
        assertTrue(response.getBody().getContent().getCapabilities().size()==2);
        assertTrue(StringUtils.isNotBlank(response.getBody().getContent().getName()));
        assertTrue(StringUtils.isNotBlank(response.getBody().getContent().getVersion()));
    }

}
