package eu.ddmore.convertertoolbox.systemtest;

import java.io.File;

import org.apache.log4j.Logger;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.Parameterized;

import eu.ddmore.convertertoolbox.systemtest.FileType;


/**
 * Run PharmML -> NMTRAN conversions over the testdata models within the "pharmml" subdirectory.
 */
@RunWith(Parameterized.class)
public class PharmmlToNmtranModelsTest {
    
    private final static Logger LOGGER = Logger.getLogger(PharmmlToNmtranModelsTest.class);

    private final static String MODELS_SUBDIRECTORY = "PharmML" + File.separator + FileType.PHARMML.getVersion();
    
    /**
     * The method that produces the parameters to be passed to each construction of the test class.
     * In this case, the {@link File}s that are the models for which to test the conversion.
     * <p>
     * NB: The JUnit {@link Parameterized} framework requires the parameter-providing method to
     * return an {@link Iterable} of Arrays.
     * <p>
     * @return the models to convert, as {@link File} objects
     */
    @Parameterized.Parameters(name= "{index}: Model {1}")
    public static Iterable<Object[]> getModelsToTest() {
        return ModelsTestHelper.getModelsToTest(MODELS_SUBDIRECTORY, FileType.PHARMML.getExtension());
    }
    
    private final File model;
    
    /**
     * Construct an instance of this test class for a particular model as taken from the list
     * provided by the {@link #getModelsToTest()} parameter-provider method.
     * <p>
     * @param model - the model {@link File}
     * @param modelShortPath - the path to the model with the "target/WorkingDir/eu/ddmore/testdata/models/"
     *                         prefix stripped off; this is incorporated into the display name of the test
     *                         but is otherwise unused
     */
    public PharmmlToNmtranModelsTest(final File model, final String modelShortPath) {
        this.model = model;
    }
    
    /**
     * Test method that tests the conversion of a particular model file as provided by the
     * {@link File} parameter that was constructor-injected into this instance of the test class.
     */
    @Test
    public void testPharmMLToNMTRANConversion() {
        new ConverterRunner(
            this.model, FileType.NMTRAN.getExtension(), "PharmML", FileType.PHARMML.getVersion(), "NMTRAN", FileType.NMTRAN.getVersion(),
            new ConverterNmTranOutputFailureChecker()
        ).run();
    }
    
}
