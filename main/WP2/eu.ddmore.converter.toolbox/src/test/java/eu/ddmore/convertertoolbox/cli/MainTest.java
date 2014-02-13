package eu.ddmore.convertertoolbox.cli;

import static org.junit.Assert.*;

import org.junit.Test;

import eu.ddmore.convertertoolbox.api.domain.LanguageVersion;


public class MainTest {

    @Test
    public void shouldCreateCorrectLanguageVersion() {
        String language = "MDL";
        String version = "5.0.8-qualm";
        LanguageVersion source = new Main().getLanguageVersion(language, version);
        assertEquals(source.getLanguage(), language);
        assertEquals(source.getVersion().getMajor(), 5);
        assertEquals(source.getVersion().getMinor(), 0);
        assertEquals(source.getVersion().getPatch(), 8);
        assertEquals("qualm", source.getVersion().getQualifier());
    }
    
    @Test
    public void shouldCreateCorrectLanguageVersionMissingPatch() {
        String language = "MDL";
        String version = "5.0-qualm";
        LanguageVersion source = new Main().getLanguageVersion(language, version);
        assertEquals(source.getLanguage(), language);
        assertEquals(source.getVersion().getMajor(), 5);
        assertEquals(source.getVersion().getMinor(), 0);
        assertEquals("qualm", source.getVersion().getQualifier());
    }

}
