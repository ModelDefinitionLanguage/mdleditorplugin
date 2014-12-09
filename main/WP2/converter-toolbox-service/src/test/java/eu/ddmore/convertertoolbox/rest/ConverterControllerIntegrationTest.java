package eu.ddmore.convertertoolbox.rest;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;
import static org.mockito.Mockito.when;

import java.util.Arrays;
import java.util.Collection;

import org.apache.log4j.Logger;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.context.embedded.EmbeddedWebApplicationContext;
import org.springframework.boot.test.IntegrationTest;
import org.springframework.boot.test.SpringApplicationConfiguration;
import org.springframework.hateoas.Link;
import org.springframework.http.ResponseEntity;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.web.WebAppConfiguration;
import org.springframework.web.client.RestTemplate;

import com.google.common.base.Optional;
import com.google.common.base.Predicate;
import com.google.common.collect.Collections2;

import eu.ddmore.convertertoolbox.domain.Conversion;
import eu.ddmore.convertertoolbox.domain.ConversionResource;
import eu.ddmore.convertertoolbox.domain.ConversionResources;
import eu.ddmore.convertertoolbox.domain.ConversionStatus;
import eu.ddmore.convertertoolbox.domain.LanguageVersion;
import eu.ddmore.convertertoolbox.domain.Version;
import eu.ddmore.convertertoolbox.service.ConversionService;

/**
 * Integration Tests the exposed REST service endpoints
 */
@RunWith(SpringJUnit4ClassRunner.class)
@SpringApplicationConfiguration(classes = {TestInstanceConfiguration.class, RestClientConfiguration.class})
@WebAppConfiguration
@IntegrationTest({"server.port=0", "management.port=0"}) //let the framework choose the port
public class ConverterControllerIntegrationTest {
    private static final String URL = "http://localhost";
    
    private static final Logger LOG = Logger.getLogger(ConverterControllerIntegrationTest.class);
    @Autowired
    private EmbeddedWebApplicationContext server;

    @Value("${local.server.port}") //get the dynamically assigned port
    private int port;
    
    @Autowired
    private RestTemplate restTemplate;
    

    @Autowired
    private ConversionService conversionService;
    
    
    @Before
    public void setUp() {
        Conversion a2conversion = createTestConversion("A2","A2","B2","input/file", ConversionStatus.Scheduled);
        when(conversionService.getConversions()).thenReturn(
            Arrays.asList(
                createTestConversion("A1", "A1","B1","input/file", ConversionStatus.New),
                a2conversion,
                createTestConversion("A3","A3","B3","input/file", ConversionStatus.Running),
                createTestConversion("A4","A4","B4","input/file", ConversionStatus.Completed)));
        
        Optional<Conversion> response = Optional.of(a2conversion);
        when(conversionService.getConversionForId("A2")).thenReturn(response);
    }
    

    private Conversion createTestConversion(String id, String form, String to, String inputFile, ConversionStatus status) {
        Conversion conversion = new Conversion().setId(id).
                setFrom(new LanguageVersion(form,new Version(1, 0, 0,"Q"))).
                setTo(new LanguageVersion(to,new Version(1, 0, 0,"Q"))).
                setInputFileName(inputFile);
        conversion.setStatus(status);
        return conversion;
    }
    
    @Test
    public void shouldGetAListOfConversions() {
        ResponseEntity<ConversionResources> response = restTemplate.getForEntity(URL+":"+port+"/conversion", ConversionResources.class);
        assertEquals(response.getBody().getContent().size(),4);
    }

    @Test
    public void shouldGetAConversion() {
        ResponseEntity<ConversionResource> response = restTemplate.getForEntity(URL+":"+port+"/conversion/A2", ConversionResource.class);
        assertEquals(response.getBody().getContent().getId(),"A2");
    }
    

    @Test
    public void shouldGetAListOfConversionsAndFollowAlinkToEntity() {
        ResponseEntity<ConversionResources> response = restTemplate.getForEntity(URL+":"+port+"/conversion", ConversionResources.class);
        Collection<ConversionResource> candidates = Collections2.filter(response.getBody().getContent(), new Predicate<ConversionResource>() {
            public boolean apply(ConversionResource candidate) {
                return "A2".equals(candidate.getContent().getId());
            }
        });
        
        assertTrue("Too many matches, check test fixtures setup!", candidates.size()==1);
        
        ConversionResource conversionResource = candidates.iterator().next();
        
        Link self = conversionResource.getLink("self");
        
        ResponseEntity<ConversionResource> selfResponse = restTemplate.getForEntity(self.getHref(), ConversionResource.class);
        assertEquals(selfResponse.getBody().getContent().getId(),"A2");
    }
}
