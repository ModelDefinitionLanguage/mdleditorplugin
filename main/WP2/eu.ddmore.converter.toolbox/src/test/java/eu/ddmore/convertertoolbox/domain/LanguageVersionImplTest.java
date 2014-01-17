package eu.ddmore.convertertoolbox.domain;

import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.Test;

import eu.ddmore.convertertoolbox.api.domain.LanguageVersion;
import eu.ddmore.convertertoolbox.api.domain.Version;
import eu.ddmore.convertertoolbox.cli.ConverterCLIImpl;

/**
 * Test for {@link LanguageVersionImpl}.
 */
public class LanguageVersionImplTest {

    private LanguageVersion nonmem72;

    @Before
    public void initialize() {
        nonmem72 = new LanguageVersionImpl();
        nonmem72.setLanguage("NONMEM");
        Version nonmem72Vers = new VersionImpl();
        nonmem72Vers.setMajor(7);
        nonmem72Vers.setMinor(2);
        nonmem72Vers.setQualifier("7.2");
        nonmem72.setVersion(nonmem72Vers);

    }

    @Test
    public void shouldBeEqual() {
        LanguageVersion nonmem72b = new LanguageVersionImpl();
        nonmem72b.setLanguage("NONMEM");
        Version nonmem72bVers = new VersionImpl();
        nonmem72bVers.setMajor(7);
        nonmem72bVers.setMinor(2);
        nonmem72bVers.setQualifier("7.2");
        nonmem72b.setVersion(nonmem72bVers);
        
        assertEquals(nonmem72, nonmem72b);
        assertEquals(nonmem72.hashCode(), nonmem72b.hashCode());
    }

    @Test
    public void shouldNotBeEqual() {
        LanguageVersion nonmem71 = new LanguageVersionImpl();
        nonmem71.setLanguage("NONMEM");
        Version nonmem71Vers = new VersionImpl();
        nonmem71Vers.setMajor(7);
        nonmem71Vers.setMinor(1);
        nonmem71.setVersion(nonmem71Vers);
        
        assertNotEquals(nonmem71, nonmem72);
    }

}
