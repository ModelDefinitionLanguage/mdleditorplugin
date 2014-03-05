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
        Version nonmem72Vers = new VersionImpl(7, 2, 0, "qual");
        nonmem72 = new LanguageVersionImpl("NMTRAN", nonmem72Vers);
    }

    @Test
    public void shouldBeEqual() {
        Version nonmem72bVers = new VersionImpl(7, 2, 0, "qual");
        LanguageVersion nonmem72b = new LanguageVersionImpl("NMTRAN", nonmem72bVers);

        assertEquals(nonmem72, nonmem72b);
        assertEquals(nonmem72.hashCode(), nonmem72b.hashCode());
    }

    @Test
    public void shouldBeEqualQual() {
        Version nonmem72bVers = new VersionImpl(7, 2, 0, "qual");
        LanguageVersion nonmem72b = new LanguageVersionImpl("nmtran", nonmem72bVers);

        assertNotEquals(nonmem72, nonmem72b);
    }

    @Test
    public void shouldNotBeEqual() {
        Version nonmem71Vers = new VersionImpl(7, 1, 0, "qual");
        LanguageVersion nonmem71 = new LanguageVersionImpl("NMTRAN", nonmem71Vers);

        assertNotEquals(nonmem71, nonmem72);
    }

}
