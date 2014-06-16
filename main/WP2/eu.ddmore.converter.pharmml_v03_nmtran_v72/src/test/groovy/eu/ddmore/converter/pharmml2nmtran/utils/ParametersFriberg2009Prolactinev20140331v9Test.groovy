package eu.ddmore.converter.pharmml2nmtran.utils;

import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.Test;
import eu.ddmore.converter.pharmml2nmtran.model.Sigma
import eu.ddmore.libpharmml.PharmMlFactory
import eu.ddmore.libpharmml.dom.PharmML
import org.apache.commons.io.FileUtils

import static eu.ddmore.converter.pharmml2nmtran.MainTest.TEST_DATA_DIR_v_0_3


class ParametersFriberg2009Prolactinev20140331v9Test {
    Parameters parameters
    
    @Before
    public void init() {
        final URL urlToFile = Parameters2008ThamJCCRTest.class.getResource(TEST_DATA_DIR_v_0_3 + "Friberg2009Prolactin/Friberg2009Prolactinev20140331v9.xml");
        
        def pmlAPI = PharmMlFactory.getInstance().createLibPharmML()
        def pmlDOM = pmlAPI.createDomFromResource(urlToFile.openStream()).getDom()
        parameters = new Parameters(pmlDOM)
        parameters.init()
    }
    
    @Test
    public void testThetas() {
        Set<String> expected = new HashSet<String>()
        expected.add("b")
        expected.add("c")
        expected.add("a")
        expected.add("pop_PRL0ST101F")
        expected.add("pop_PRL0")
        expected.add("pop_AMP1")
        expected.add("pop_KOUT")
        expected.add("pop_UPDA")
        expected.add("pop_DPHS")
        expected.add("pop_KI")
        expected.add("pop_KDA")
        expected.add("pop_AMP2")
        expected.add("pop_PRL0PATF")
        expected.add("pop_PHS2")
        expected.add("pop_PHS1")
        expected.add("pop_PRL0PATM")
        expected.add("pop_PRL0ST101M")
        assertEquals(expected, parameters.thetas.keySet())
    }

    @Test
    public void testOmegas() {
        Set<String> expected = new HashSet<String>()
        expected.add("omega_KI")
        expected.add("omega_KOUT")
        expected.add("omega_PRL0")
        expected.add("gamma_PRL0")
        expected.add("omega_PHS2")
        expected.add("omega_AMP1")
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
        expected.add("eta_PRL0")
        expected.add("eta_AMP1")
        expected.add("eta_PHS2")
        expected.add("eta_KOUT")
        expected.add("kappa_PRL0")
        expected.add("eta_KI")
        assertEquals(expected, parameters.etas)
    }
    
    @Test
    public void testGroups() {
        Set<String> expected = new HashSet<String>()
        expected.add("F2")
        expected.add("pop_PRL0")
        expected.add("D2")
        expected.add("pop_PHS2")
        expected.add("PHS1")
        assertEquals(expected, parameters.groups.keySet())
    }

    @Test
    public void testStructuralVars() {
        Set<String> expected = new HashSet<String>()
        expected.add("Adp")
        expected.add("KINM")
        expected.add("Apr")
        expected.add("DIU")
        expected.add("Ac")
        expected.add("Ad")
        expected.add("KINB")
        expected.add("CP")
        expected.add("Ap")
        assertEquals(expected, parameters.structuralVars.keySet())
    }

    @Test
    public void testEtaToOmegas() {
        Map<String, String> expected = new HashMap<String, String>()
        expected.put("eta_PRL0", "omega_PRL0")
        expected.put("eta_AMP1", "omega_AMP1")
        expected.put("eta_PHS2", "omega_PHS2")
        expected.put("eta_KOUT", "omega_KOUT")
        expected.put("kappa_PRL0", "gamma_PRL0")
        expected.put("eta_KI", "omega_KI")
        assertEquals(expected, parameters.etaToOmega)
    }

}
