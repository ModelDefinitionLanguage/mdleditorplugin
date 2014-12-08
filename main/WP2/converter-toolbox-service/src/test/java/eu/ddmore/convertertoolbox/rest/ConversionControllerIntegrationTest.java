package eu.ddmore.convertertoolbox.rest;

import static org.junit.Assert.assertTrue;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.context.embedded.EmbeddedWebApplicationContext;
import org.springframework.boot.test.IntegrationTest;
import org.springframework.boot.test.SpringApplicationConfiguration;
import org.springframework.boot.test.TestRestTemplate;
import org.springframework.http.ResponseEntity;
import org.springframework.http.converter.HttpMessageConverter;
import org.springframework.http.converter.json.MappingJackson2HttpMessageConverter;
import org.springframework.http.converter.json.MappingJacksonHttpMessageConverter;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.web.WebAppConfiguration;
import org.springframework.web.client.DefaultResponseErrorHandler;
import org.springframework.web.client.RestTemplate;

import eu.ddmore.convertertoolbox.Application;
import eu.ddmore.convertertoolbox.domain.ServiceDescriptor;

@RunWith(SpringJUnit4ClassRunner.class)
@SpringApplicationConfiguration(classes = Application.class)
@WebAppConfiguration
@IntegrationTest({"server.port=0", "management.port=0"})
public class ConversionControllerIntegrationTest {
    @Autowired
    EmbeddedWebApplicationContext server;

    @Value("${local.server.port}")
    int port;
    
    RestTemplate restTemplate = new TestRestTemplate();
    
    private static final String URL = "http://localhost";
    
    @Before
    public void setUp() {
        List<HttpMessageConverter<?>> messageConverters = new ArrayList<HttpMessageConverter<?>>();
        messageConverters.add(new MappingJackson2HttpMessageConverter());
        restTemplate.setMessageConverters(messageConverters);
        restTemplate.setErrorHandler(new DefaultResponseErrorHandler());
    }
    
    
    @Test
    public void shouldHaveThePortSetToValueOtherThan0() {
        assertTrue(port>0);
    }
    
    @Test
    public void shouldGet404ForNonexistingConversion() {
        ResponseEntity<ServiceDescriptor> serviceDescriptor = restTemplate.getForEntity(URL+":"+port, ServiceDescriptor.class);
    }
}
