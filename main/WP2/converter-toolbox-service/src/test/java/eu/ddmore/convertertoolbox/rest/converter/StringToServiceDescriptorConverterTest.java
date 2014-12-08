package eu.ddmore.convertertoolbox.rest.converter;

import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertTrue;

import java.util.ArrayList;
import java.util.Arrays;

import org.apache.log4j.Logger;
import org.junit.Test;

import eu.ddmore.convertertoolbox.domain.ConversionCapability;
import eu.ddmore.convertertoolbox.domain.LanguageVersion;
import eu.ddmore.convertertoolbox.domain.ServiceDescriptor;
import eu.ddmore.convertertoolbox.domain.Version;

/**
 * Tests { @link StringToServiceDescriptorConverter }
 */
public class StringToServiceDescriptorConverterTest {
    private static final Logger LOG = Logger.getLogger(StringToServiceDescriptorConverterTest.class);
    private final StringToServiceDescriptorConverter converter = new StringToServiceDescriptorConverter();
    @Test
    public void shouldParseServiceDescriptorWithNoCapabilities() {
        ServiceDescriptor serviceDescriptor = new ServiceDescriptor("NAME","SERVICE",new ArrayList<ConversionCapability>());
        
        ServiceDescriptor result = converter.convert("{\"name\":\"NAME\",\"version\":\"SERVICE\",\"capabilities\":[]}");
        
        LOG.debug(result);
        assertNotNull(serviceDescriptor);
        assertTrue(serviceDescriptor.equals(result));
    }
    @Test
    public void shouldParseServiceDescriptorWithMoreThanOneCapability() {
        LanguageVersion languageA = new LanguageVersion("A",new Version(1,0,0,null));
        LanguageVersion languageB = new LanguageVersion("B",new Version(1,0,0,null));
        LanguageVersion languageC = new LanguageVersion("C",new Version(1,0,0,null));
        ServiceDescriptor serviceDescriptor = new ServiceDescriptor("NAME","SERVICE",
            Arrays.asList(
                new ConversionCapability(languageA, Arrays.asList(languageB, languageC)),
                new ConversionCapability(languageC, Arrays.asList(languageB))
                )
                );
        
        
        ServiceDescriptor result = converter.convert("{\"name\":\"NAME\",\"version\":\"SERVICE\",\"capabilities\":[{\"source\":{\"language\":\"A\",\"version\":{\"major\":1,\"minor\":0,\"patch\":0,\"qualifier\":null}},\"target\":[{\"language\":\"B\",\"version\":{\"major\":1,\"minor\":0,\"patch\":0,\"qualifier\":null}},{\"language\":\"C\",\"version\":{\"major\":1,\"minor\":0,\"patch\":0,\"qualifier\":null}}]},{\"source\":{\"language\":\"C\",\"version\":{\"major\":1,\"minor\":0,\"patch\":0,\"qualifier\":null}},\"target\":[{\"language\":\"B\",\"version\":{\"major\":1,\"minor\":0,\"patch\":0,\"qualifier\":null}}]}]}");
        
        LOG.debug(result);
        assertNotNull(serviceDescriptor);
        assertTrue(serviceDescriptor.equals(result));
    }
}
