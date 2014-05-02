package eu.ddmore.converter.pharmml2nmtran.loader;

import static org.junit.Assert.*;

import java.util.List;

import org.junit.Before;
import org.junit.Test;
import eu.ddmore.libpharmml.PharmMlFactory
import eu.ddmore.libpharmml.dom.PharmML
import eu.ddmore.pharmacometrics.model.trialdesign.Subject
import eu.ddmore.pharmacometrics.model.trialdesign.math.BinaryExpression
import eu.ddmore.pharmacometrics.model.trialdesign.math.Constant
import eu.ddmore.pharmacometrics.model.trialdesign.math.Variable
import eu.ddmore.pharmacometrics.model.trialdesign.math.BinaryExpression.Operator;
import eu.ddmore.pharmacometrics.model.trialdesign.math.Expression
import eu.ddmore.pharmacometrics.model.trialdesign.structure.Activity
import eu.ddmore.pharmacometrics.model.trialdesign.structure.Arm;
import eu.ddmore.pharmacometrics.model.trialdesign.structure.Bolus
import eu.ddmore.pharmacometrics.model.trialdesign.structure.Cell
import eu.ddmore.pharmacometrics.model.trialdesign.structure.DosingTimes
import eu.ddmore.pharmacometrics.model.trialdesign.structure.DosingTimesConstant
import eu.ddmore.pharmacometrics.model.trialdesign.structure.DosingTimesSequence
import eu.ddmore.pharmacometrics.model.trialdesign.structure.Epoch
import eu.ddmore.pharmacometrics.model.trialdesign.structure.Segment


import org.apache.commons.io.FileUtils;
import static eu.ddmore.converter.pharmml2nmtran.MainTest.path

class TrialDesignLoaderExample3Test {
    TrialDesignLoader loader;

    @Before
    public void init() {
        File src = new File(Thread.currentThread().getContextClassLoader().getResource(path +'example3.xml').getPath());
        
        def pmlAPI = PharmMlFactory.getInstance().createLibPharmML()
        def is = FileUtils.openInputStream(src)
        def pmlDOM = pmlAPI.createDomFromResource(is).getDom()
        loader = new TrialDesignLoader("trialDesign":pmlDOM.trialDesign)
        loader.load()
    }

    @Test
    public void testArms() {
        List<Arm> arms = new ArrayList<Arm>()
        Arm a1 = new Arm("arm1", 1)
        a1.add(new Subject("i1", 1))
        a1.add(new Subject("i2", 2))
        a1.add(new Subject("i3", 3))
        a1.add(new Subject("i4", 4))
        a1.add(new Subject("i5", 5))
        a1.add(new Subject("i33", 6))
        arms.add(a1)
        assertEquals(arms, loader.structure.getArms())
    }

    @Test
    public void testEpochs() {
        List<Epoch> expected = new ArrayList<Epoch>()
        Epoch epoch = new Epoch("start":0, "end":180)
        expected.add(epoch)
        assertEquals(expected, loader.structure.getEpochs())
    }
    

    @Test
    public void testCells() {
        Epoch e1 = new Epoch("start":0, "end":180)
        
        DosingTimes dosingtimes = new DosingTimesConstant(0);
        Expression doseAmount1 = new Constant(100.0)
        Bolus d1 = new Bolus(doseAmount1, dosingtimes, false)

        Segment ta = new Segment()
        ta.addActivity(d1);

        List arms = new ArrayList<Arm>()
        arms.add(new Arm("a1", 1))

        List sa = new ArrayList<Segment>()
        sa.add(ta)

        Cell c1 = new Cell(e1, arms, sa)

        List<Epoch> expected = new ArrayList<Cell>()
        expected.add(c1)

        int i=0
        for (Cell cell : loader.structure.getCells()) {
            assertEquals(expected[i].epoch, cell.epoch)
            assertSegments(expected[i++].segments, cell.segments)
        }
    }
    
    private void assertSegments(List<Segment> expected, List<Segment> found) {
        assertEquals(expected.size(), found.size());
        for (int i=0; i<expected.size(); i++) {
            assertSegment(expected[i], found[i]);
        }
    }
    
    private void assertSegment(Segment expected, Segment found) {
        assertEquals(expected.activities.size(), found.activities.size());
        for (int i=0; i<expected.activities.size(); i++) {
            assertActivity(expected.activities[i], found.activities[i]);
        }
    }
    
    private void assertActivity(Bolus expected, Bolus found) {
        assertEquals(expected.dosingTimes, found.dosingTimes)
        assertEquals(expected.doseAmount, found.doseAmount)
        assertEquals(expected.assignedToODE, found.assignedToODE)
    }
}
