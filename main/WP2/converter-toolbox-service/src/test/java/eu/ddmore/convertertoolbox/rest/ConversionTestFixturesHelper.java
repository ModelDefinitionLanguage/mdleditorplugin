/*******************************************************************************
 * Copyright (C) 2002 Mango Solutions Ltd - All rights reserved.
 ******************************************************************************/
package eu.ddmore.convertertoolbox.rest;

import java.util.ArrayList;
import java.util.Collection;

import eu.ddmore.convertertoolbox.domain.Conversion;
import eu.ddmore.convertertoolbox.domain.ConversionStatus;
import eu.ddmore.convertertoolbox.domain.LanguageVersion;
import eu.ddmore.convertertoolbox.domain.Version;

/**
 * Helper class used by REST endpoints Integration Tests
 */
public class ConversionTestFixturesHelper {
    public static LanguageVersion from(String languageName) {
        return to(languageName).iterator().next();
    }

    public static Collection<LanguageVersion> to(String... languageNames) {
        Collection<LanguageVersion> result = new ArrayList<LanguageVersion>();
        for(String language : languageNames) {
            result.add(new LanguageVersion(language, new Version(1,0,0,"Q")));
        }
        return result;
    }

    public static Conversion createTestConversion(String id, String form, String to, String inputFile, ConversionStatus status) {
        Conversion conversion = new Conversion().setId(id).
                setFrom(new LanguageVersion(form,new Version(1, 0, 0,"Q"))).
                setTo(new LanguageVersion(to,new Version(1, 0, 0,"Q"))).
                setInputFileName(inputFile);
        conversion.setStatus(status);
        return conversion;
    }
}
