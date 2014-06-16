package eu.ddmore.converter.pharmml2nmtran.utils;

import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.Test;
import eu.ddmore.libpharmml.PharmMlFactory
import eu.ddmore.libpharmml.dom.PharmML
import org.apache.commons.io.FileUtils;

import static eu.ddmore.converter.pharmml2nmtran.MainTest.TEST_DATA_DIR

class ParametersExample4Test {
    Parameters parameters
    
    @Before
    public void init() {
        
        final URL urlToFile = ParametersExample4Test.class.getResource(TEST_DATA_DIR + "example4/example4.xml");
        
        def pmlAPI = PharmMlFactory.getInstance().createLibPharmML()
        def pmlDOM = pmlAPI.createDomFromResource(urlToFile.openStream()).getDom()
        parameters = new Parameters(pmlDOM)
        parameters.init()
    }
    
    @Test
    public void testThetas() {
        Set<String> expected = new HashSet<String>()
        expected.add("pop_CL")
        expected.add("beta_V_TreatSeq")
        expected.add("b")
        expected.add("a")
        expected.add("beta_V_Treat")
        expected.add("beta_V_occ1")
        expected.add("beta_CL_occ1")
        expected.add("pop_ka")
        expected.add("pop_V")
        expected.add("beta_V")
        expected.add("beta_CL")
        assertEquals(expected, parameters.thetas.keySet())
    }

    @Test
    public void testOmegas() {
        Set<String> expected = new HashSet<String>()
        expected.add("omega_ka")
        expected.add("omega_CL")
        expected.add("gamma_V")
        expected.add("gamma_CL")
        expected.add("omega_V")
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
        expected.add("kappa_V")
        expected.add("eta_V")
        expected.add("eta_CL")
        expected.add("kappa_CL")
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
        expected.add("Cc")
        assertEquals(expected, parameters.structuralVars.keySet())
    }

    @Test
    public void testEtaToOmegas() {
        Map<String, String> expected = new HashMap<String, String>()
        expected.put("kappa_V", "gamma_V")
        expected.put("eta_V", "omega_V")
        expected.put("eta_CL", "omega_CL")
        expected.put("kappa_CL", "gamma_CL")
        expected.put("eta_ka", "omega_ka")
        assertEquals(expected, parameters.etaToOmega)
    }

}
