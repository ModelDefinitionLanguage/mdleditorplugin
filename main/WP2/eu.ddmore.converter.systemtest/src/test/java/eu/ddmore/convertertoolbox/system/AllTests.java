package eu.ddmore.convertertoolbox.system;

import org.junit.runner.RunWith;
import org.junit.runners.Suite;
import org.junit.runners.Suite.SuiteClasses;


/**
 * Test Suite to run the different conversion tests e.g. MDL to PharmML, PharmML to NMTRAN.
 * <p>
 * The order is important since the conversion tests for the generated PharmML Models use the
 * output from the conversion tests for the MDL Models.
 */
@RunWith(Suite.class)
@SuiteClasses({
    MdlModelsTest.class, PharmmlModelsTest.class, GeneratedPharmmlModelsTest.class
})
public class AllTests {
}
