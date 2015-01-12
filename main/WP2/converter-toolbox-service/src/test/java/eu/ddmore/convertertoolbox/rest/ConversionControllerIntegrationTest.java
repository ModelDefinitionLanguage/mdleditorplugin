/*******************************************************************************
 * Copyright (C) 2002 Mango Solutions Ltd - All rights reserved.
 ******************************************************************************/
package eu.ddmore.convertertoolbox.rest;

import static eu.ddmore.convertertoolbox.rest.ConversionTestFixturesHelper.createExternalTestConversion;
import static eu.ddmore.convertertoolbox.rest.ConversionTestFixturesHelper.createInternalTestConversion;
import static eu.ddmore.convertertoolbox.rest.ConversionTestFixturesHelper.from;
import static eu.ddmore.convertertoolbox.rest.ConversionTestFixturesHelper.to;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertTrue;
import static org.mockito.AdditionalMatchers.not;
import static org.mockito.Matchers.any;
import static org.mockito.Matchers.eq;
import static org.mockito.Mockito.doThrow;
import static org.mockito.Mockito.reset;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import java.io.File;
import java.io.IOException;
import java.util.Arrays;
import java.util.Collection;
import java.util.List;
import java.util.Map;

import org.apache.commons.io.FileUtils;
import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.TemporaryFolder;
import org.junit.runner.RunWith;
import org.mockito.invocation.InvocationOnMock;
import org.mockito.stubbing.Answer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.context.embedded.EmbeddedWebApplicationContext;
import org.springframework.boot.test.IntegrationTest;
import org.springframework.boot.test.SpringApplicationConfiguration;
import org.springframework.core.io.ByteArrayResource;
import org.springframework.core.io.FileSystemResource;
import org.springframework.hateoas.Link;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.web.WebAppConfiguration;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;
import org.springframework.web.client.HttpClientErrorException;
import org.springframework.web.client.RestTemplate;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.google.common.base.Optional;
import com.google.common.base.Predicate;
import com.google.common.collect.Collections2;

import eu.ddmore.convertertoolbox.TestPropertyMockingApplicationContextInitializer;
import eu.ddmore.convertertoolbox.domain.Conversion;
import eu.ddmore.convertertoolbox.domain.ConversionCapability;
import eu.ddmore.convertertoolbox.domain.ConversionStatus;
import eu.ddmore.convertertoolbox.domain.hal.ConversionResource;
import eu.ddmore.convertertoolbox.domain.hal.ConversionResources;
import eu.ddmore.convertertoolbox.domain.hal.LinkRelation;
import eu.ddmore.convertertoolbox.rest.converter.ConversionToStringConverter;
import eu.ddmore.convertertoolbox.service.ConversionCapabilitiesProvider;
import eu.ddmore.convertertoolbox.service.ConversionService;
import eu.ddmore.convertertoolbox.service.ExceededCapacity;
import eu.ddmore.convertertoolbox.service.impl.ServiceWorkingDirectory;

/**
 * Integration Tests the exposed REST service end points
 */
@RunWith(SpringJUnit4ClassRunner.class)
@SpringApplicationConfiguration(classes = {TestInstanceConfiguration.class, RestClientConfiguration.class}
, initializers = TestPropertyMockingApplicationContextInitializer.class)
@WebAppConfiguration
@IntegrationTest({"server.port=0", "management.port=0"}) //let the framework choose the port
public class ConversionControllerIntegrationTest {
    private static final String URL = "http://localhost";
    
    @Autowired
    private EmbeddedWebApplicationContext server;

    @Value("${local.server.port}") //get the dynamically assigned port
    private int port;
    
    @Autowired
    private RestTemplate restTemplate;

    @Autowired
    private ConversionService conversionService;
    
    @Autowired
    private ConversionCapabilitiesProvider capabilitiesProvider;
    
    @Autowired
    private ServiceWorkingDirectory serviceWorkingDirectory;
    
    @Rule
    public TemporaryFolder tempFolder = new TemporaryFolder();
    
    /**
     * conversion registered in the mock ConversionService, exposed to tests in order to modify the entity
     */
    private eu.ddmore.convertertoolbox.domain.internal.Conversion a2conversion;
    
    @Before
    public void setUp() throws IOException {
        resetMocks();
        
        a2conversion = createInternalTestConversion("A2","A","B","input/file", ConversionStatus.Scheduled);
        when(conversionService.getConversions()).thenReturn(
            Arrays.asList(
                createInternalTestConversion("A1", "A","B","input/file", ConversionStatus.New),
                a2conversion,
                createInternalTestConversion("A3","A","B","input/file", ConversionStatus.Running),
                createInternalTestConversion("A4","A","B","input/file", ConversionStatus.Completed)));
        when(capabilitiesProvider.getCapabilities()).thenReturn(
            Arrays.asList(
                new ConversionCapability(from("A"), to("B", "C", "D")),
                new ConversionCapability(from("C"), to("B"))
                ));
        Optional<eu.ddmore.convertertoolbox.domain.internal.Conversion> response = Optional.of(a2conversion);
        Optional<eu.ddmore.convertertoolbox.domain.internal.Conversion> emptyResponse = Optional.absent();
        when(conversionService.getConversionForId(eq("A2"))).thenReturn(response);
        when(conversionService.getConversionForId(not(eq("A2")))).thenReturn(emptyResponse);
        
        File temp = tempFolder.newFolder();
        when(serviceWorkingDirectory.newDirectory(any(String.class))).thenReturn(temp);
    }
    /*
     * Tests share single spring context, we must make sure that mocks are reset beteween the tests
     */
    private void resetMocks() {
         reset(conversionService);
         reset(capabilitiesProvider);
    }
   
    private String generateEndpoint(String path) {
        return URL+":"+port+path;
    }
    
    @Test
    public void list_shouldGetAListOfConversions() {
        ResponseEntity<ConversionResources> response = restTemplate.getForEntity(generateEndpoint("/conversion"), ConversionResources.class);
        assertEquals(response.getBody().getContent().size(),4);
    }

    @Test
    public void find_NotCompleted_shouldGetAConversion() {
        ResponseEntity<ConversionResource> response = restTemplate.getForEntity(generateEndpoint("/conversion/A2"), ConversionResource.class);

        assertEquals(response.getBody().getContent().getId(),"A2");
        List<Link> links = response.getBody().getLinks();
        
        assertEquals(1,links.size());
        assertEquals(links.get(0).getRel(),LinkRelation.SELF.getRelation());
        
    }

    @Test
    public void find_CompletedAndNoResults_shouldGetAConversion() {
        a2conversion.setStatus(ConversionStatus.Completed);
        ResponseEntity<ConversionResource> response = restTemplate.getForEntity(generateEndpoint("/conversion/A2"), ConversionResource.class);
        assertEquals(response.getBody().getContent().getId(),"A2");

        List<Link> links = response.getBody().getLinks();
        
        assertEquals(2,links.size());
        assertEquals(links.get(0).getRel(),LinkRelation.SELF.getRelation());
        assertEquals(links.get(1).getRel(),LinkRelation.DELETE.getRelation());
    }

    @Test
    public void find_CompletedAndResults_shouldGetAConversion() {
        File outputArchive = FileUtils.toFile(ConversionControllerIntegrationTest.class.getResource("test-output.zip"));
        a2conversion.setOutputArchive(outputArchive);
        a2conversion.setStatus(ConversionStatus.Completed);
        
        ResponseEntity<ConversionResource> response = restTemplate.getForEntity(generateEndpoint("/conversion/A2"), ConversionResource.class);
        assertEquals(response.getBody().getContent().getId(),"A2");
        
        List<Link> links = response.getBody().getLinks();
        
        assertEquals(3, links.size());
        assertEquals(links.get(0).getRel(),LinkRelation.SELF.getRelation());
        assertEquals(links.get(1).getRel(),LinkRelation.RESULT.getRelation());
        assertEquals(links.get(2).getRel(),LinkRelation.DELETE.getRelation());
    }
    
    @Test
    public void find_shouldResultIn_404_responseIfNoConversionFoundForGivenId() {
        try {
            restTemplate.getForEntity(generateEndpoint("/conversion/A3"), ConversionResource.class);
        } catch (HttpClientErrorException ex ) {
            assertEquals(HttpStatus.NOT_FOUND,ex.getStatusCode());
        }
    }

    @Test
    public void shouldGetAListOfConversionsAndFollowAlinkToEntity() {
        ResponseEntity<ConversionResources> response = restTemplate.getForEntity(generateEndpoint("/conversion"), ConversionResources.class);
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

    @Test
    public void post_shouldAddConversionIfSupported() throws ExceededCapacity {
        File inputFile =FileUtils.toFile(ConversionControllerIntegrationTest.class.getResource("test-input.zip"));
        Conversion conversion = createExternalTestConversion(null, "A", "B", "test-input.txt" /* resides in test-input.zip */, ConversionStatus.New);
        
        when(conversionService.add(any(eu.ddmore.convertertoolbox.domain.internal.Conversion.class))).thenAnswer(new Answer<Conversion>() {
            @Override
            public Conversion answer(InvocationOnMock invocation) throws Throwable {
                eu.ddmore.convertertoolbox.domain.internal.Conversion conversion = (eu.ddmore.convertertoolbox.domain.internal.Conversion)invocation.getArguments()[0];
                conversion.setId("MOCK-ID");
                return conversion;
            }
        });
        
        
        MultiValueMap<String,Object> requestParams = new LinkedMultiValueMap<String,Object>();
        requestParams.add("file", new FileSystemResource(inputFile));
        requestParams.add("conversion", new ConversionToStringConverter().convert(conversion));
        
        ResponseEntity<ConversionResource> response = restTemplate.postForEntity(generateEndpoint("/conversion"), requestParams, ConversionResource.class);

        verify(conversionService).schedule(any(eu.ddmore.convertertoolbox.domain.internal.Conversion.class));
        assertEquals(HttpStatus.CREATED, response.getStatusCode());
        assertNotNull(response.getBody());
    }

    @Test
    public void post_shouldResultIn_400_IfConversionNotSupported() throws Exception {
        File inputFile =FileUtils.toFile(ConversionControllerIntegrationTest.class.getResource("test-input.zip"));
        Conversion conversion = createExternalTestConversion(null, "C", "D", "test-input.txt" /* resides in test-input.zip */, ConversionStatus.New);
        
        MultiValueMap<String,Object> requestParams = new LinkedMultiValueMap<String,Object>();
        requestParams.add("file", new FileSystemResource(inputFile));
        requestParams.add("conversion", new ConversionToStringConverter().convert(conversion));

        try {
            restTemplate.postForEntity(generateEndpoint("/conversion"), requestParams, ConversionResource.class);
        } catch(HttpClientErrorException ex) {
            assertEquals(HttpStatus.BAD_REQUEST,ex.getStatusCode());
            ObjectMapper mapper = new ObjectMapper();
            try {
                Map<?,?> body = mapper.readValue(ex.getResponseBodyAsString(),Map.class);
                assertEquals("Requested conversion from C 1.0.0-Q to D 1.0.0-Q is not supported",body.get("message"));
            } catch (Exception e) {
                throw e;
            }
        }
    }

    @Test
    public void post_shouldResultIn_429_IfTooManyRequests() throws ExceededCapacity {
        File inputFile =FileUtils.toFile(ConversionControllerIntegrationTest.class.getResource("test-input.zip"));
        Conversion conversion = createExternalTestConversion(null, "A", "D", "test-input.txt" /* resides in test-input.zip */, ConversionStatus.New);
        
        MultiValueMap<String,Object> requestParams = new LinkedMultiValueMap<String,Object>();
        requestParams.add("file", new FileSystemResource(inputFile));
        requestParams.add("conversion", new ConversionToStringConverter().convert(conversion));

        doThrow(ExceededCapacity.class).when(conversionService).add(any(eu.ddmore.convertertoolbox.domain.internal.Conversion.class));
        try {
            restTemplate.postForEntity(generateEndpoint("/conversion"), requestParams, ConversionResource.class);
        } catch(HttpClientErrorException ex) {
            assertEquals(HttpStatus.TOO_MANY_REQUESTS,ex.getStatusCode());
        }
    }
    
    @Test
    public void getOutputs_shouldReturnTheResultsArchive() {
        File outputArchive =FileUtils.toFile(ConversionControllerIntegrationTest.class.getResource("test-output.zip"));
        a2conversion.setOutputArchive(outputArchive);
        a2conversion.setStatus(ConversionStatus.Completed);
        
        ResponseEntity<ByteArrayResource> response = restTemplate.getForEntity(generateEndpoint("/conversion/A2/result"), ByteArrayResource.class);
        assertNotNull(response.getBody());
        assertEquals(response.getBody().contentLength(), outputArchive.length());
    }

    @Test
    public void getOutputs_shouldReturn_404_ifInvalidConversionId() {
        File outputArchive =FileUtils.toFile(ConversionControllerIntegrationTest.class.getResource("test-output.zip"));
        a2conversion.setOutputArchive(outputArchive);
        a2conversion.setStatus(ConversionStatus.Completed);
        try {
            restTemplate.getForEntity(generateEndpoint("/conversion/A100/result"), FileSystemResource.class);
        } catch(HttpClientErrorException ex) {
            assertEquals(HttpStatus.NOT_FOUND,ex.getStatusCode());
        }
        
    }

    @Test
    public void getOutputs_shouldReturn_409_ifConversionNotCompleted() {
        File outputArchive =FileUtils.toFile(ConversionControllerIntegrationTest.class.getResource("test-output.zip"));
        a2conversion.setOutputArchive(outputArchive);
        a2conversion.setStatus(ConversionStatus.Scheduled);
        try {
            restTemplate.getForEntity(generateEndpoint("/conversion/A2/result"), FileSystemResource.class);
        } catch(HttpClientErrorException ex) {
            assertEquals(HttpStatus.CONFLICT,ex.getStatusCode());
        }
    }

    @Test
    public void getOutputs_shouldReturn_404_ifOutputArchiveNotAvailable() {
        a2conversion.setStatus(ConversionStatus.Completed);
        try {
            restTemplate.getForEntity(generateEndpoint("/conversion/A2/result"), FileSystemResource.class);
        } catch(HttpClientErrorException ex) {
            assertEquals(HttpStatus.NOT_FOUND,ex.getStatusCode());
        }
    }

    @Test
    public void getOutputs_shouldReturn_404_ifNoOutputArchiveDoesNotExist() {
        File outputArchive =new File("not/existing/mock/file");
        a2conversion.setOutputArchive(outputArchive);
        a2conversion.setStatus(ConversionStatus.Completed);
        try {
            restTemplate.getForEntity(generateEndpoint("/conversion/A2/result"), FileSystemResource.class);
        } catch(HttpClientErrorException ex) {
            assertEquals(HttpStatus.NOT_FOUND,ex.getStatusCode());
        }
    }

    @Test
    public void delete_shouldDeleteTheConversionResults() {
        a2conversion.setStatus(ConversionStatus.Completed);
        restTemplate.delete(generateEndpoint("/conversion/A2"));
        verify(conversionService).delete(a2conversion);
    }

    @Test
    public void delete_shouldReturn_404_ifInvalidConversionId() {
        try {
            restTemplate.delete(generateEndpoint("/conversion/A100"));
        } catch(HttpClientErrorException ex) {
            assertEquals(HttpStatus.NOT_FOUND,ex.getStatusCode());
        }
    }

    @Test
    public void delete_shouldReturn_409_ifConversionNotCompleted() {
        a2conversion.setStatus(ConversionStatus.Scheduled);
        try {
            restTemplate.delete(generateEndpoint("/conversion/A2"));
        } catch(HttpClientErrorException ex) {
            assertEquals(HttpStatus.CONFLICT,ex.getStatusCode());
        } 
    }
}
