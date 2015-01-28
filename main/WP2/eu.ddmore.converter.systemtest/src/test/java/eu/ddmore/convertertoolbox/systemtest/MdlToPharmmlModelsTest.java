package eu.ddmore.convertertoolbox.systemtest;

import java.io.File;
import java.io.IOException;

import org.apache.commons.io.FileUtils;
import org.apache.commons.io.filefilter.SuffixFileFilter;
import org.apache.log4j.Logger;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.Parameterized;


/**
 * Run MDL -> PharmML conversions over the testdata models within the "mdl" subdirectory.
 */
@RunWith(Parameterized.class)
public class MdlToPharmmlModelsTest {
    
    private final static Logger LOGGER = Logger.getLogger(MdlToPharmmlModelsTest.class);

    private final static String MDL_VERSION = "6.0.7";
    private final static String PHARMML_VERSION = "0.3.1";

    private final static String MODELS_SUBDIRECTORY = "mdl" + File.separator + MDL_VERSION;
    private final static String MODELS_FILE_EXTENSION = "mdl";
    private final static String OUTPUT_FILE_EXTENSION = "xml";
    
    // We'll consider a conversion to have failed if the converted output file has a size that is less than this number of bytes
    private final static int PHARMML_FILE_SIZE_THRESHOLD = 638;
    
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
    public MdlToPharmmlModelsTest(final File model, final String modelShortPath) {
        this.model = model;
    }
    
    /**
     * Test method that tests the conversion of a particular model file as provided by the
     * {@link File} parameter that was constructor-injected into this instance of the test class.
     * <p>
     * Also, specifically for this MDL -> PharmML conversion, copy any data file(s)
     * (unintelligently, any *.csv) into the PharmML-generated-from-MDL output directory,
     * since the subsequent MDL -> NMTRAN conversion tests will fail if models' data files
     * are not present.
     * <p>
     * @throws IOException - if an error occurred trying to copy data files
     */
    @Test
    public void testMdlToPharmMLConversion() throws IOException {
        final ConverterRunner runner = new ConverterRunner(
            this.model, OUTPUT_FILE_EXTENSION, "MDL", MDL_VERSION, "PharmML", PHARMML_VERSION,
            new DefaultConverterOutputFailureChecker(PHARMML_FILE_SIZE_THRESHOLD)
        );
        runner.run();
		// Copy the data file
        FileUtils.copyDirectory(this.model.getParentFile(), runner.getOutputDirectory(), new SuffixFileFilter(".csv"));
    }
    
}
