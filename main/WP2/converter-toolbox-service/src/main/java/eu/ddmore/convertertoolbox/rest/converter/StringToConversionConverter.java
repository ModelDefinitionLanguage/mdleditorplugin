/*******************************************************************************
 * Copyright (C) 2002 Mango Solutions Ltd - All rights reserved.
 ******************************************************************************/
package eu.ddmore.convertertoolbox.rest.converter;

import org.springframework.core.convert.converter.Converter;
import org.springframework.stereotype.Component;

import com.fasterxml.jackson.databind.ObjectMapper;

import eu.ddmore.convertertoolbox.domain.Conversion;


@Component
public class StringToConversionConverter implements Converter<String, Conversion> {
    @Override
    public Conversion convert(String conversion) {
        ObjectMapper mapper = new ObjectMapper();
        try {
            return mapper.readValue(conversion,Conversion.class);
        } catch (Exception e) {
            throw new RuntimeException(String.format("Couldn't parse json %s",conversion), e);
        }
    }

}
