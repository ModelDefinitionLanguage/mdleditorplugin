/*******************************************************************************
 * Copyright (C) 2002 Mango Solutions Ltd - All rights reserved.
 ******************************************************************************/
package eu.ddmore.convertertoolbox.rest;

import static org.mockito.Mockito.mock;

import javax.servlet.MultipartConfigElement;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.context.embedded.MultipartConfigFactory;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.hateoas.UriTemplate;
import org.springframework.hateoas.hal.CurieProvider;
import org.springframework.hateoas.hal.DefaultCurieProvider;

import eu.ddmore.convertertoolbox.api.conversion.ConverterManager;
import eu.ddmore.convertertoolbox.service.ConversionCapabilitiesProvider;
import eu.ddmore.convertertoolbox.service.ConversionService;
import eu.ddmore.convertertoolbox.service.impl.ServiceWorkingDirectory;

/**
 * Configuration setting up mocks to be used by Rest Controllers during integration tests
 */
@EnableAutoConfiguration
@ComponentScan(basePackages = {"eu.ddmore.convertertoolbox.rest","eu.ddmore.convertertoolbox.domain"})
@Configuration
public class TestInstanceConfiguration {

    @Bean
    public ConversionCapabilitiesProvider mockConversionCapabilitiesProvider() {
        ConversionCapabilitiesProvider capabilitiesProvider =  mock(ConversionCapabilitiesProvider.class);
        return capabilitiesProvider;
    }

    @Bean
    public ConverterManager mockConverterManager() {
        ConverterManager converterManager =  mock(ConverterManager.class);
        return converterManager;
    }

    @Bean
    public ConversionService mockConversionService() {
        ConversionService conversionService =  mock(ConversionService.class);
        return conversionService;
    }

    @Bean
    public ServiceWorkingDirectory mockServiceWorkingDirectory() {
        ServiceWorkingDirectory serviceWorkingDirectory =  mock(ServiceWorkingDirectory.class);
        return serviceWorkingDirectory;
    }
    
    @Bean
    public CurieProvider curieProvider(@Value("${cts.linkRelation.prefix}") String prefix, @Value("${cts.linkRelation.template}") String template) {
        return new DefaultCurieProvider(prefix,
            new UriTemplate(template));
    }
    
    @Bean
    public MultipartConfigElement multipartConfigElement() {
        MultipartConfigFactory factory = new MultipartConfigFactory();
        factory.setMaxFileSize("128KB");
        factory.setMaxRequestSize("128KB");
        return factory.createMultipartConfig();
    }
}
