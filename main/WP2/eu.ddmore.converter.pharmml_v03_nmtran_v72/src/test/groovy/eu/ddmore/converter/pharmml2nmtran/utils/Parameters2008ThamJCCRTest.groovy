package eu.ddmore.converter.pharmml2nmtran.utils;

import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.Ignore;
import org.junit.Test;
import eu.ddmore.converter.pharmml2nmtran.model.Sigma
import eu.ddmore.libpharmml.PharmMlFactory
import eu.ddmore.libpharmml.dom.PharmML
import org.apache.commons.io.FileUtils;

import static eu.ddmore.converter.pharmml2nmtran.MainTest.PATH


class Parameters2008ThamJCCRTest {
    Parameters parameters
    
    @Before
    public void init() {
        File src = new File(Thread.currentThread().getContextClassLoader().getResource(PATH +'2008ThamJCCR.xml').getPath());
        def pmlAPI = PharmMlFactory.getInstance().createLibPharmML()
        def is = FileUtils.openInputStream(src)
        def pmlDOM = pmlAPI.createDomFromResource(is).getDom()
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
