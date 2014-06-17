package eu.ddmore.converter.pharmml2nmtran.utils;

import static org.junit.Assert.*

import org.junit.Before;
import org.junit.Test;
import eu.ddmore.converter.pharmml2nmtran.model.Sigma
import eu.ddmore.libpharmml.PharmMlFactory
import eu.ddmore.libpharmml.dom.PharmML
import org.apache.commons.io.FileUtils;

import static eu.ddmore.converter.pharmml2nmtran.MainTest.TEST_DATA_DIR
import static eu.ddmore.converter.pharmml2nmtran.MainTest.V_0_3_SUBDIR


class Parameters2008ThamJCCRTest {
    Parameters parameters

    @Before
    public void init() {
        final URL urlToFile = Parameters2008ThamJCCRTest.class.getResource(TEST_DATA_DIR + V_0_3_SUBDIR + "2008ThamCCR/2008ThamJCCR.xml");

        def pmlAPI = PharmMlFactory.getInstance().createLibPharmML()
        def pmlDOM = pmlAPI.createDomFromResource(urlToFile.openStream()).getDom()
        parameters = new Parameters(pmlDOM)
        parameters.init()
    }

    @Test
    public void testThetas() {
        Set<String> expected = new HashSet<String>()
        expected.add("pop_AE50")
        expected.add("b")
        expected.add("pop_TOVER")
        expected.add("a")
        expected.add("pop_SIZE0")
        expected.add("TEQ")
        assertEquals(expected, parameters.thetas.keySet())
    }

    @Test
    public void testOmegas() {
        Set<String> expected = new HashSet<String>()
        expected.add("omega_TOVER")
        expected.add("omega_AE50")
        expected.add("omega_SIZE0")
        assertEquals(expected, parameters.omegas.keySet())
    }

    @Test
    public void testNonConstantSigmas() {
        Set<String> expected = new HashSet<String>()
        expected.add("sigma_A2")
        assertEquals(expected, parameters.sigmas.keySet())
    }

    @Test
    public void testEtas() {
        Set<String> expected = new HashSet<String>()
        expected.add("eta_AE50")
        expected.add("eta_SIZE0")
        expected.add("eta_TOVER")
        assertEquals(expected, parameters.etas)
    }

    @Test
    public void testGroups() {
        Set<String> expected = new HashSet<String>()
        expected.add("WH")
        expected.add("MGMM")
        assertEquals(expected, parameters.groups.keySet())
    }

    @Test
    public void testStructuralVars() {
        Set<String> expected = new HashSet<String>()
        expected.add("A2")
        expected.add("A1")
        assertEquals(expected, parameters.structuralVars.keySet())
    }

    @Test
    public void testEtaToOmegas() {
        Map<String, String> expected = new HashMap<String, String>()
        expected.put("eta_AE50", "omega_AE50")
        expected.put("eta_SIZE0", "omega_SIZE0")
        expected.put("eta_TOVER", "omega_TOVER")
        assertEquals(expected, parameters.etaToOmega)
    }
}
