package eu.ddmore.converter.pharmml2nmtran.utils;

import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.Test;
import eu.ddmore.converter.pharmml2nmtran.model.Sigma
import eu.ddmore.libpharmml.PharmMlFactory
import eu.ddmore.libpharmml.dom.PharmML
import org.apache.commons.io.FileUtils;

import static eu.ddmore.converter.pharmml2nmtran.MainTest.TEST_DATA_DIR

class ParametersExample5Test {
    Parameters parameters

    @Before
    public void init() {
        final URL urlToFile = Parameters2008ThamJCCRTest.class.getResource(TEST_DATA_DIR + "example5/example5.xml");

        def pmlAPI = PharmMlFactory.getInstance().createLibPharmML()
        def pmlDOM = pmlAPI.createDomFromResource(urlToFile.openStream()).getDom()
        parameters = new Parameters(pmlDOM)
        parameters.init()
    }

    @Test
    public void testThetas() {
        Set<String> expected = new HashSet<String>()

        expected.add("C0")
        expected.add("pop_P0")
        expected.add("pop_Q0")
        expected.add("a")
        expected.add("pop_LAMBDAP")
        expected.add("pop_KDE")
        expected.add("pop_KQPP")
        expected.add("pop_KPQ")
        expected.add("pop_GAMMA")
        expected.add("pop_DELTAQP")
        assertEquals(expected, parameters.thetas.keySet())
    }

    @Test
    public void testOmegas() {
        Set<String> expected = new HashSet<String>()
        expected.add("omega_P0")
        expected.add("omega_Q0")
        expected.add("omega_LAMBDAP")
        expected.add("omega_KDE")
        expected.add("omega_KQPP")
        expected.add("omega_KPQ")
        expected.add("omega_GAMMA")
        expected.add("omega_DELTAQP")
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
        expected.add("eta_DELTAQP")
        expected.add("eta_Q0")
        expected.add("eta_P0")
        expected.add("eta_KDE")
        expected.add("eta_KQPP")
        expected.add("eta_KPQ")
        expected.add("eta_GAMMA")
        expected.add("eta_LAMBDAP")
        assertEquals(expected, parameters.etas)
    }

    @Test
    public void testGroups() {
        Set<String> expected = new HashSet<String>()
        expected.add("QP0")
        expected.add("K")
        assertEquals(expected, parameters.groups.keySet())
    }

    @Test
    public void testStructuralVars() {
        Set<String> expected = new HashSet<String>()
        expected.add("PT")
        expected.add("PSTAR")
        expected.add("Q")
        expected.add("C")
        expected.add("QP")
        assertEquals(expected, parameters.structuralVars.keySet())
    }

    @Test
    public void testEtaToOmegas() {
        Map<String, String> expected = new HashMap<String, String>()
        expected.put("eta_DELTAQP", "omega_DELTAQP")
        expected.put("eta_Q0", "omega_Q0")
        expected.put("eta_P0", "omega_P0")
        expected.put("eta_KDE", "omega_KDE")
        expected.put("eta_KQPP", "omega_KQPP")
        expected.put("eta_KPQ", "omega_KPQ")
        expected.put("eta_GAMMA", "omega_GAMMA")
        expected.put("eta_LAMBDAP", "omega_LAMBDAP")
        assertEquals(expected, parameters.etaToOmega)
    }
}
