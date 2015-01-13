/*******************************************************************************
 * Copyright (C) 2015 Mango Solutions Ltd - All rights reserved.
 ******************************************************************************/
package eu.ddmore.convertertoolbox.rest.converter;

import org.springframework.core.convert.converter.Converter;
import org.springframework.stereotype.Component;

import com.fasterxml.jackson.databind.ObjectMapper;

import eu.ddmore.convertertoolbox.domain.Conversion;

@Component
public class ConversionToStringConverter implements Converter<Conversion, String> {
    @Override
    public String convert(Conversion conversion) {
        ObjectMapper mapper = new ObjectMapper();
        try {
            return mapper.writeValueAsString(conversion);
        } catch (Exception e) {
            throw new IllegalArgumentException(String.format("Could not produce json for %s",conversion), e);
        }
    }
}
