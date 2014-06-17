package eu.ddmore.converter.pharmml2nmtran.utils;

import static org.junit.Assert.*

import org.junit.Before;
import org.junit.Test;
import eu.ddmore.libpharmml.PharmMlFactory
import eu.ddmore.libpharmml.dom.PharmML
import org.apache.commons.io.FileUtils;

import static eu.ddmore.converter.pharmml2nmtran.MainTest.TEST_DATA_DIR
import static eu.ddmore.converter.pharmml2nmtran.MainTest.V_0_3_SUBDIR


class ParametersExample3Test {
    Parameters parameters

    @Before
    public void init() {
        final URL urlToFile = Parameters2008ThamJCCRTest.class.getResource(TEST_DATA_DIR + V_0_3_SUBDIR + "example3/example3.xml");

        def pmlAPI = PharmMlFactory.getInstance().createLibPharmML()
        def pmlDOM = pmlAPI.createDomFromResource(urlToFile.openStream()).getDom()
        parameters = new Parameters(pmlDOM)
        parameters.init()
    }

    @Test
    public void testThetas() {
        Set<String> expected = new HashSet<String>()
        expected.add("pop_CL")
        expected.add("b")
        expected.add("a")
        expected.add("pop_Tlag")
        expected.add("pop_ka")
        expected.add("pop_V")
        expected.add("beta_CL")
        expected.add("beta_V")
        assertEquals(expected, parameters.thetas.keySet())
    }

    @Test
    public void testOmegas() {
        Set<String> expected = new HashSet<String>()
        expected.add("omega_ka")
        expected.add("omega_CL")
        expected.add("omega_Tlag")
        expected.add("omega_V")
        assertEquals(expected, parameters.omegas.keySet())
    }

    @Test
    public void testNonConstantSigmas() {
        Set<String> expected = new HashSet<String>()
        expected.add("sigma")
        assertEquals(expected, parameters.sigmas.keySet())
    }

    @Test
    public void testEtas() {
        Set<String> expected = new HashSet<String>()
        expected.add("eta_CL")
        expected.add("eta_V")
        expected.add("eta_Tlag")
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
        assertEquals(expected, parameters.structuralVars.keySet())
    }

    @Test
    public void testEtaToOmegas() {
        Map<String, String> expected = new HashMap<String, String>()
        expected.put("eta_CL", "omega_CL")
        expected.put("eta_V", "omega_V")
        expected.put("eta_Tlag", "omega_Tlag")
        expected.put("eta_ka", "omega_ka")
        assertEquals(expected, parameters.etaToOmega)
    }
}
