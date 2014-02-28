package eu.ddmore.convertertoolbox.domain;

import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.Test;

import eu.ddmore.convertertoolbox.api.domain.Version;

/**
 * Test for {@link VersionImpl}.
 */
public class VersionImplTest {

    private Version nonmem72Vers;

    @Before
    public void initialize() {
        nonmem72Vers = new VersionImpl(7, 2, 3, "7.2");
    }

    @Test
    public void shouldBeEqual() {
        Version nonmem72bVers = new VersionImpl(7, 2, 3, "7.2");

        assertEquals(nonmem72Vers, nonmem72bVers);
        assertEquals(nonmem72Vers.hashCode(), nonmem72bVers.hashCode());
    }

    @Test
    public void shouldNotBeEqual() {
        Version nonmem71Vers = new VersionImpl(7, 1, 3, null);
        assertNotEquals(nonmem71Vers, nonmem72Vers);
    }

    @Test
    public void shouldBeLatestVersion() {
        Version nonmem71Vers = new VersionImpl(7, 1, 3, null);
        assertTrue(nonmem72Vers.compareTo(nonmem71Vers) > 0);
    }

}
