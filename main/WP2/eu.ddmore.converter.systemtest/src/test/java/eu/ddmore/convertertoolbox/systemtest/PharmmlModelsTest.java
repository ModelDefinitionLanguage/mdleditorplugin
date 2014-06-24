package eu.ddmore.convertertoolbox.systemtest;

import static org.junit.Assert.assertTrue;

import java.io.File;

import org.apache.log4j.Logger;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.Parameterized;

import eu.ddmore.convertertoolbox.systemtest.ConverterRunner;


/**
 * Run PharmML -> NMTRAN conversions over the testdata models within the "pharmml" subdirectory.
 */
@RunWith(Parameterized.class)
public class PharmmlModelsTest {
    
    private final static Logger LOGGER = Logger.getLogger(PharmmlModelsTest.class);
    
    private final static String MODELS_SUBDIRECTORY = "pharmml";
    private final static String MODELS_FILE_EXTENSION = "xml";
    private final static String OUTPUT_FILE_EXTENSION = ".ctl";
    
    // We'll consider a conversion to have failed if the converted output file has a size that is less than this number of bytes
    private final static int NMTRAN_FILE_SIZE_THRESHOLD = 30; // Required: $PROB $INPUT $DATA
    
    /**
     * The method that produces the parameters to be passed to each construction of the test class.
     * In this case, the {@link File}s that are the models for which to test the conversion.
     * <p>
     * NB: The JUnit {@link Parameterized} framework requires the parameter-providing method to
     * return an {@link Iterable} of Arrays, even though we only have one parameter and hence
     * would much prefer to return an {@link Iterable} of {@link File}s.
     * <p>
     * @return the models to convert, as {@link File} objects
     */
    @Parameterized.Parameters(name= "{index}: Model {1}")
    public static Iterable<Object[]> getModelsToTest() {
        return ModelsTestHelper.getModelsToTest(MODELS_SUBDIRECTORY, MODELS_FILE_EXTENSION);
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
    public PharmmlModelsTest(final File model, final String modelShortPath) {
        this.model = model;
    }
    
    /**
     * Test method that tests the conversion of a particular model file as provided by the
     * {@link File} parameter that was constructor-injected into this instance of the test class.
     */
    @Test
    public void testPharmMLToNMTRANConversion() {
        final ConverterRunner runner = new ConverterRunner(
            this.model, OUTPUT_FILE_EXTENSION, "PharmML", "0.3.0", "NMTRAN", "7.2",
            new ConverterOutputFailureChecker(NMTRAN_FILE_SIZE_THRESHOLD));
        final boolean success = runner.run();
        assertTrue("Conversion should not error out", success);
    }
    
}
