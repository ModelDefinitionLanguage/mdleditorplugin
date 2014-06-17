package eu.ddmore.converter.pharmml2nmtran.utils;

import static org.junit.Assert.*

import org.junit.Before;
import org.junit.Test;
import eu.ddmore.libpharmml.PharmMlFactory
import eu.ddmore.libpharmml.dom.PharmML
import org.apache.commons.io.FileUtils;
import static eu.ddmore.converter.pharmml2nmtran.MainTest.TEST_DATA_DIR
import static eu.ddmore.converter.pharmml2nmtran.MainTest.V_0_3_SUBDIR

class ParametersExample1Test {
    Parameters parameters

    @Before
    public void init() {

        final URL urlToFile = ParametersExample1Test.class.getResource(TEST_DATA_DIR + V_0_3_SUBDIR + "example1/example1.xml");

        def pmlAPI = PharmMlFactory.getInstance().createLibPharmML()
        def pmlDOM = pmlAPI.createDomFromResource(urlToFile.openStream()).getDom()
        parameters = new Parameters(pmlDOM)
        parameters.init()
    }

    @Test
    public void testThetas() {
        Set<String> expected = new HashSet<String>()
        expected.add("pop_Rin")
        expected.add("pop_Cl")
        expected.add("pop_Imax")
        expected.add("pop_IC50")
        expected.add("pop_kout")
        expected.add("pop_W")
        expected.add("beta_Cl")
        expected.add("pop_ka")
        expected.add("pop_V")
        expected.add("beta_V")
        assertEquals(expected, parameters.thetas.keySet())
    }

    @Test
    public void testOmegas() {
        Set<String> expected = new HashSet<String>()
        expected.add("omega_ka")
        expected.add("omega_IC50")
        expected.add("omega_kout")
        expected.add("omega_Imax")
        expected.add("omega_Cl")
        expected.add("omega_W")
        expected.add("omega_V")
        expected.add("omega_Rin")
        assertEquals(expected, parameters.omegas.keySet())
    }

    @Test
    public void testNonConstantSigmas() {
        Set<String> expected = new HashSet<String>()
        assertEquals(expected, parameters.sigmas.keySet())
    }

    @Test
    public void testEtas() {
        Set<String> expected = new HashSet<String>()
        expected.add("eta_IC50")
        expected.add("eta_Rin")
        expected.add("eta_Cl")
        expected.add("eta_Imax")
        expected.add("eta_V")
        expected.add("eta_kout")
        expected.add("eta_ka")
        assertEquals(expected, parameters.etas)
    }

    @Test
    public void testGroups() {
        Set<String> expected = new HashSet<String>()
        assertEquals(expected, parameters.groups.keySet())
    }

    @Test
    public void testStructuralVars() {
        Set<String> expected = new HashSet<String>()
        expected.add("E")
        expected.add("Ac")
        expected.add("Ad")
        expected.add("Cc")
        expected.add("k")
        assertEquals(expected, parameters.structuralVars.keySet())
    }

    @Test
    public void testEtaToOmegas() {
        Map<String, String> expected = new HashMap<String, String>()
        expected.put("eta_IC50", "omega_IC50")
        expected.put("eta_Rin", "omega_Rin")
        expected.put("eta_Cl", "omega_Cl")
        expected.put("eta_Imax", "omega_Imax")
        expected.put("eta_V", "omega_V")
        expected.put("eta_kout", "omega_kout")
        expected.put("eta_ka", "omega_ka")
        assertEquals(expected, parameters.etaToOmega)
    }

}
