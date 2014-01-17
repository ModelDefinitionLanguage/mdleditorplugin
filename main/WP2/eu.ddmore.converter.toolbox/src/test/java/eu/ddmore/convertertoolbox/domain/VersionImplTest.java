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
        nonmem72Vers = new VersionImpl();
        nonmem72Vers.setMajor(7);
        nonmem72Vers.setMinor(2);
        nonmem72Vers.setPatch(3);
        nonmem72Vers.setQualifier("7.2");
    }

    @Test
    public void shouldBeEqual() {
        Version nonmem72bVers = new VersionImpl();
        nonmem72bVers.setMajor(7);
        nonmem72bVers.setMinor(2);
        nonmem72bVers.setPatch(3);
        nonmem72bVers.setQualifier("7.2");
        
        assertEquals(nonmem72Vers, nonmem72bVers);
        assertEquals(nonmem72Vers.hashCode(), nonmem72bVers.hashCode());
    }

    @Test
    public void shouldNotBeEqual() {
        Version nonmem71Vers = new VersionImpl();
        nonmem71Vers.setMajor(7);
        nonmem71Vers.setMinor(1);
        nonmem71Vers.setPatch(3);
        
        assertNotEquals(nonmem71Vers, nonmem72Vers);
    }

    @Test
    public void shouldBeLatestVersion() {
        Version nonmem71Vers = new VersionImpl();
        nonmem71Vers.setMajor(7);
        nonmem71Vers.setMinor(1);
        nonmem71Vers.setPatch(3);
        
        Version nonmem72bVers = new VersionImpl();
        nonmem72bVers.setMajor(7);
        nonmem72bVers.setMinor(2);
        nonmem72bVers.setPatch(3);
        nonmem72bVers.setQualifier("7.2");

        assertTrue(nonmem72Vers.compareTo(nonmem71Vers) > 0);
    }

}
