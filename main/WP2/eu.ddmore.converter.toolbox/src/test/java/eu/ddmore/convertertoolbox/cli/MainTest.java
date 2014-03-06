package eu.ddmore.convertertoolbox.cli;

import static org.junit.Assert.*;

import java.io.IOException;

import org.junit.Test;

import eu.ddmore.convertertoolbox.api.domain.LanguageVersion;
import eu.ddmore.convertertoolbox.api.exception.ConverterNotFoundException;
import eu.ddmore.convertertoolbox.api.response.ConversionReport;
import eu.ddmore.convertertoolbox.api.response.ConversionDetail.Severity;
import eu.ddmore.convertertoolbox.api.response.ConversionReport.ConversionCode;


public class MainTest {

    @Test
    public void shouldCreateCorrectLanguageVersion() {
        String language = "MDL";
        String version = "5.0.8-qualm";
        LanguageVersion source = new Main().getLanguageVersion(language, version);
        assertEquals(language, source.getLanguage());
        assertEquals(5, source.getVersion().getMajor());
        assertEquals(0, source.getVersion().getMinor());
        assertEquals(8, source.getVersion().getPatch());
        assertEquals("qualm", source.getVersion().getQualifier());
    }
    
    @Test
    public void shouldCreateCorrectLanguageVersionMissingPatch() {
        String language = "MDL";
        String version = "5.0-qualm";
        LanguageVersion source = new Main().getLanguageVersion(language, version);
        assertEquals(language, source.getLanguage());
        assertEquals(5, source.getVersion().getMajor());
        assertEquals(0, source.getVersion().getMinor());
        assertEquals("qualm", source.getVersion().getQualifier());
    }

    @Test
    public void shouldCreateCorrectLanguageVersionDoubleHyphen() {
        String language = "MDL";
        String version = "5.0.8-some-qualm";
        LanguageVersion source = new Main().getLanguageVersion(language, version);
        assertEquals(language, source.getLanguage());
        assertEquals(5, source.getVersion().getMajor());
        assertEquals(0, source.getVersion().getMinor());
        assertEquals("some-qualm", source.getVersion().getQualifier());
    }

}
