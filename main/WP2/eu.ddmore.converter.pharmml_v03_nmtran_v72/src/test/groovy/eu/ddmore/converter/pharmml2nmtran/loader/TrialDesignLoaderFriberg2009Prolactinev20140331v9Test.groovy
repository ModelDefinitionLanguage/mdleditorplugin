package eu.ddmore.converter.pharmml2nmtran.loader;

import static org.junit.Assert.*

import org.junit.Before
import org.junit.Test

import eu.ddmore.libpharmml.PharmMlFactory
import eu.ddmore.pharmacometrics.model.trialdesign.Subject
import eu.ddmore.pharmacometrics.model.trialdesign.structure.Arm
import eu.ddmore.pharmacometrics.model.trialdesign.structure.Epoch

import static eu.ddmore.converter.pharmml2nmtran.MainTest.TEST_DATA_DIR
import static eu.ddmore.converter.pharmml2nmtran.MainTest.V_0_3_SUBDIR

class TrialDesignLoaderFriberg2009Prolactinev20140331v9Test {
    TrialDesignLoader loader;

    @Before
    public void init() {
        final URL urlToFile = TrialDesignLoaderFriberg2009Prolactinev20140331v9Test.class.getResource(TEST_DATA_DIR + V_0_3_SUBDIR + "FribergCPT2009/Friberg2009Prolactinev20140331v9.xml");

        def pmlAPI = PharmMlFactory.getInstance().createLibPharmML()
        def pmlDOM = pmlAPI.createDomFromResource(urlToFile.openStream()).getDom()
        loader = new TrialDesignLoader("trialDesign":pmlDOM.trialDesign)
        loader.load()
    }

    @Test
    public void testArms() {
        List<Arm> arms = new ArrayList<Arm>()
        Arm arm1 = new Arm("arm1", 1)
        for (int i=1; i<=21; i++) {
            Subject subject = new Subject("i"+i, i)
            arm1.add(subject)
        }
        arms.add(arm1)
        assertEquals(arms, loader.structure.getArms())
    }

    @Test
    public void testEpochs() {
        List<Epoch> expected = new ArrayList<Epoch>()
        Epoch epoch = new Epoch("start":0, "end":1200)
        expected.add(epoch)
        assertEquals(expected, loader.structure.getEpochs())
    }
}
