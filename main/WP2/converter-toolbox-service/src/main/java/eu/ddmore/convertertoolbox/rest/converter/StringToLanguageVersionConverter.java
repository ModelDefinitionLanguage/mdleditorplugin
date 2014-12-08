/*******************************************************************************
 * Copyright (C) 2002 Mango Solutions Ltd - All rights reserved.
 ******************************************************************************/
package eu.ddmore.convertertoolbox.rest.converter;

import org.springframework.core.convert.converter.Converter;
import org.springframework.stereotype.Component;

import com.fasterxml.jackson.databind.ObjectMapper;

import eu.ddmore.convertertoolbox.domain.ServiceDescriptor;


@Component
public class StringToLanguageVersionConverter implements Converter<String, ServiceDescriptor> {
    @Override
    public ServiceDescriptor convert(String serviceDescriptor) {
        ObjectMapper mapper = new ObjectMapper();
        try {
            return mapper.readValue(serviceDescriptor,ServiceDescriptor.class);
        } catch (Exception e) {
            throw new RuntimeException(String.format("Couldn't parse json %s",serviceDescriptor), e);
        }
    }

}
