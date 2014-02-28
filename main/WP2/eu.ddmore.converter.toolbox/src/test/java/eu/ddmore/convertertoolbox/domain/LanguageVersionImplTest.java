package eu.ddmore.convertertoolbox.domain;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotEquals;

import org.junit.Before;
import org.junit.Test;

import eu.ddmore.convertertoolbox.api.domain.LanguageVersion;
import eu.ddmore.convertertoolbox.api.domain.Version;

/**
 * Test for {@link LanguageVersionImpl}.
 */
public class LanguageVersionImplTest {

    private LanguageVersion nonmem72;

    @Before
    public void initialize() {
        nonmem72 = new LanguageVersionImpl();
        nonmem72.setLanguage("NMTRAN");
        Version nonmem72Vers = new VersionImpl(7, 2, 0, "qual");
        nonmem72.setVersion(nonmem72Vers);
    }

    @Test
    public void shouldBeEqualHyphen() {
        LanguageVersion nonmem72b = new LanguageVersionImpl();
        nonmem72b.setLanguage("NM-TRAN");
        Version nonmem72bVers = new VersionImpl(7, 2, 0, "qual");
        nonmem72b.setVersion(nonmem72bVers);

        assertEquals(nonmem72, nonmem72b);
        assertEquals(nonmem72.hashCode(), nonmem72b.hashCode());
    }

    @Test
    public void shouldBeEqualLowerCase() {
        LanguageVersion nonmem72b = new LanguageVersionImpl();
        nonmem72b.setLanguage("nmtran");
        Version nonmem72bVers = new VersionImpl(7, 2, 0, "qual");
        nonmem72b.setVersion(nonmem72bVers);

        assertEquals(nonmem72, nonmem72b);
        assertEquals(nonmem72.hashCode(), nonmem72b.hashCode());
    }

    @Test
    public void shouldNotBeEqual() {
        LanguageVersion nonmem71 = new LanguageVersionImpl();
        nonmem71.setLanguage("NMTRAN");
        Version nonmem71Vers = new VersionImpl(7, 1, 0, "qual");
        nonmem71.setVersion(nonmem71Vers);

        assertNotEquals(nonmem71, nonmem72);
    }

}
