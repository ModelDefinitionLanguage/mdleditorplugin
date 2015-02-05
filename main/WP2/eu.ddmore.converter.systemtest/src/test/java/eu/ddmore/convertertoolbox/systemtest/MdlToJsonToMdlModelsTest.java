package eu.ddmore.convertertoolbox.systemtest;

import static org.junit.Assert.assertTrue;

import java.io.File;
import java.io.IOException;

import org.apache.log4j.Logger;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.Parameterized;

/**
 * Run MDL -> JSON -> MDL conversions over the testdata models within the "mdl" subdirectory,
 * The generated MDL file is tested for syntactic and semantic equivalence to the original MDL file.
 * Minimal checks are done on the MDL -> JSON bit of the pipeline since there is a separate
 * test class, {@link MdlToJsonModelsTest}, for this.
 */
@RunWith(Parameterized.class)
public class MdlToJsonToMdlModelsTest {

    private final static Logger LOGGER = Logger.getLogger(MdlToJsonToMdlModelsTest.class);

    private final static String MDL_VERSION = "6.0.7";
    private final static String JSON_VERSION = "6.0.7";

    private final static String MODELS_SUBDIRECTORY = "mdl" + File.separator + MDL_VERSION;
    private final static String MDL_FILE_EXTENSION = "mdl";
    private final static String JSON_FILE_EXTENSION = "json";

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
    @Parameterized.Parameters(name = "{index}: Model {1}")
    public static Iterable<Object[]> getModelsToTest() {
        return ModelsTestHelper.getModelsToTest(MODELS_SUBDIRECTORY, MDL_FILE_EXTENSION);
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
    public MdlToJsonToMdlModelsTest(final File model, final String modelShortPath) {
        this.model = model;
    }

    /**
     * Test method that tests the conversion of a particular model file as provided by the
     * {@link File} parameter that was constructor-injected into this instance of the test class.
     */
    @Test
    public void testMdlToJsonToMdlConversion() throws IOException {
        final File mdlModelFile = this.model;
            
        final ConverterRunner runner1 = new ConverterRunner(mdlModelFile, JSON_FILE_EXTENSION, "MDL", MDL_VERSION, "JSON", JSON_VERSION,
                new ConverterJsonOutputFailureChecker());
        runner1.run();
        
        final ConverterRunner runner2 = new ConverterRunner(jsonModelFile, MDL_FILE_EXTENSION, "JSON", JSON_VERSION, "MDL", MDL_VERSION,
            new MdlFileEquivalenceChecker(mdlModelFile));
        runner2.run();
        
    }

}
