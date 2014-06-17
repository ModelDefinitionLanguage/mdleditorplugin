package eu.ddmore.converter.pharmml2nmtran.loader;

import static org.junit.Assert.*

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
import eu.ddmore.pharmacometrics.model.trialdesign.structure.DosingTimesSequence
import eu.ddmore.pharmacometrics.model.trialdesign.structure.Epoch
import eu.ddmore.pharmacometrics.model.trialdesign.structure.Segment

import org.apache.commons.io.FileUtils;

import static eu.ddmore.converter.pharmml2nmtran.MainTest.TEST_DATA_DIR
import static eu.ddmore.converter.pharmml2nmtran.MainTest.V_0_3_SUBDIR

class TrialDesignLoaderExample1Test {
    TrialDesignLoader loader;

    @Before
    public void init() {

        final URL urlToFile = TrialDesignLoaderExample1Test.class.getResource(TEST_DATA_DIR + V_0_3_SUBDIR + "example1/example1.xml");

        def pmlAPI = PharmMlFactory.getInstance().createLibPharmML()
        def pmlDOM = pmlAPI.createDomFromResource(urlToFile.openStream()).getDom()
        loader = new TrialDesignLoader("trialDesign":pmlDOM.trialDesign)
        loader.load()
    }

    @Test
    public void testArms() {
        List<Arm> arms = new ArrayList<Arm>()
        Arm a1 = new Arm("a1", 1)
        for (int i=1; i<=20; i++) {
            Subject subject = new Subject("i1"+i, i)
            a1.add(subject)
        }
        Arm a2 = new Arm("a2", 2)
        for (int i=1; i<=20; i++) {
            Subject subject = new Subject("i2"+i, 20+i)
            a2.add(subject)
        }
        Arm a3 = new Arm("a3", 3)
        for (int i=1; i<=40; i++) {
            Subject subject = new Subject("i3"+i, 40+i)
            a3.add(subject)
        }
        Arm a4 = new Arm("a4", 4)
        for (int i=1; i<=40; i++) {
            Subject subject = new Subject("i4"+i, 80+i)
            a4.add(subject)
        }
        arms.add(a1)
        arms.add(a2)
        arms.add(a3)
        arms.add(a4)
        assertEquals(arms, loader.structure.getArms())
    }

    @Test
    public void testEpochs() {
        List<Epoch> expected = new ArrayList<Epoch>()
        Epoch epoch = new Epoch("start":0, "end":300)
        expected.add(epoch)
        assertEquals(expected, loader.structure.getEpochs())
    }

    @Test
    public void testCells() {
        Epoch e1 = new Epoch("start":0, "end":300)

        DosingTimes dosingTimes1_3 = new DosingTimesSequence(0, 24, 192)
        DosingTimes dosingTimes2_4 = new DosingTimesSequence(0, 48, 192)

        Expression doseAmount1 = new BinaryExpression(new Constant(0.25), new Variable("W"), Operator.TIMES)
        Expression doseAmount2 = new BinaryExpression(new Constant(0.5), new Variable("W"), Operator.TIMES)
        Expression doseAmount3 = new BinaryExpression(new Constant(0.5), new Variable("W"), Operator.TIMES)
        Expression doseAmount4 = new BinaryExpression(new Constant(1), new Variable("W"), Operator.TIMES)

        Bolus d1 = new Bolus(doseAmount1, dosingTimes1_3, true)
        Bolus d2 = new Bolus(doseAmount2, dosingTimes2_4, true)
        Bolus d3 = new Bolus(doseAmount3, dosingTimes1_3, true)
        Bolus d4 = new Bolus(doseAmount4, dosingTimes2_4, true)

        Segment ta = new Segment()
        ta.addActivity(d1);

        Segment tb = new Segment()
        tb.addActivity(d2);

        Segment tc = new Segment()
        tc.addActivity(d3);

        Segment td = new Segment()
        td.addActivity(d4);

        List arms = new ArrayList<Arm>()
        arms.add(new Arm("arm1", 1))

        List sa = new ArrayList<Segment>()
        sa.add(ta)
        List sb = new ArrayList<Segment>()
        sb.add(tb)
        List sc = new ArrayList<Segment>()
        sc.add(tc)
        List sd = new ArrayList<Segment>()
        sd.add(td)

        Cell c1 = new Cell(e1, arms, sa)
        Cell c2 = new Cell(e1, arms, sb)
        Cell c3 = new Cell(e1, arms, sc)
        Cell c4 = new Cell(e1, arms, sd)

        List<Epoch> expected = new ArrayList<Cell>()
        expected.add(c1)
        expected.add(c2)
        expected.add(c3)
        expected.add(c4)

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
