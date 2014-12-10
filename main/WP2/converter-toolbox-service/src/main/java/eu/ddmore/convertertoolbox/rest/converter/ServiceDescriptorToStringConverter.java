/*******************************************************************************
 * Copyright (C) 2002 Mango Solutions Ltd - All rights reserved.
 ******************************************************************************/
package eu.ddmore.convertertoolbox.rest.converter;

import org.springframework.core.convert.converter.Converter;
import org.springframework.stereotype.Component;

import com.fasterxml.jackson.databind.ObjectMapper;

import eu.ddmore.convertertoolbox.domain.ServiceDescriptor;

@Component
public class ServiceDescriptorToStringConverter implements Converter<ServiceDescriptor, String> {
    @Override
    public String convert(ServiceDescriptor serviceDescriptor) {
        ObjectMapper mapper = new ObjectMapper();
        try {
            return mapper.writeValueAsString(serviceDescriptor);
        } catch (Exception e) {
            throw new RuntimeException(String.format("Could not produce json for %s",serviceDescriptor), e);
        }
    }
}