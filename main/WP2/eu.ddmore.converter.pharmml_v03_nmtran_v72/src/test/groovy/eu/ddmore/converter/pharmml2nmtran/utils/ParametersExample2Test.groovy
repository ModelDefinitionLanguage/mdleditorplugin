package eu.ddmore.converter.pharmml2nmtran.utils;

import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.Test;
import eu.ddmore.libpharmml.PharmMlFactory
import eu.ddmore.libpharmml.dom.PharmML
import org.apache.commons.io.FileUtils;

import static eu.ddmore.converter.pharmml2nmtran.MainTest.PATH

class ParametersExample2Test {
    Parameters parameters
    
    @Before
    public void init() {
        
        // TODO: Move the data to models project and reference from TEST_DATA_DIR base directory
        final URL urlToFile = ParametersExample4Test.class.getResource("/0.3/example2.xml");
        
        def pmlAPI = PharmMlFactory.getInstance().createLibPharmML()
        def pmlDOM = pmlAPI.createDomFromResource(urlToFile.openStream()).getDom()
        parameters = new Parameters(pmlDOM)
        parameters.init()
    }
    
    @Test
    public void testThetas() {
        Set<String> expected = new HashSet<String>()
        expected.add("pop_theta1")
        expected.add("theta2")
        expected.add("Ka")
        expected.add("pop_Weight")
        expected.add("tau")
        expected.add("pop_V")
        assertEquals(expected, parameters.thetas.keySet())
    }

    @Test
    public void testOmegas() {
        Set<String> expected = new HashSet<String>()
        expected.add("omega_theta1")
        expected.add("omega_V")
        expected.add("omega_Weight")
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
        expected.add("eta_V")
        expected.add("eta_theta1")
        assertEquals(expected, parameters.etas)
    }
    
    @Test
    public void testGroups() {
        Set<String> expected = new HashSet<String>()
        expected.add("CL")
        assertEquals(expected, parameters.groups.keySet())
    }

    @Test
    public void testStructuralVars() {
        Set<String> expected = new HashSet<String>()
        expected.add("k")
        expected.add("Css")
        assertEquals(expected, parameters.structuralVars.keySet())
    }

    @Test
    public void testEtaToOmegas() {
        Map<String, String> expected = new HashMap<String, String>()
        expected.put("eta_V", "omega_V")
        expected.put("eta_theta1", "omega_theta1")
        assertEquals(expected, parameters.etaToOmega)
    }

}
