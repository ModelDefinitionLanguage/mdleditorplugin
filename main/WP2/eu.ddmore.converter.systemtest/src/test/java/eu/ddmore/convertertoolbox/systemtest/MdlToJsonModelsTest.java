package eu.ddmore.convertertoolbox.systemtest;

import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.fail;

import java.io.File;
import java.io.IOException;

import org.apache.log4j.Logger;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.Parameterized;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;

/**
 * Run MDL -> JSON conversions over the testdata models within the "mdl" subdirectory.
 */
@RunWith(Parameterized.class)
public class MdlToJsonModelsTest {

    private final static Logger LOGGER = Logger.getLogger(MdlToJsonModelsTest.class);

    private final static String MDL_VERSION = "6.0.7";
    private final static String JSON_VERSION = "6.0.7";

    private final static String MODELS_SUBDIRECTORY = "mdl" + File.separator + MDL_VERSION;
    private final static String MODELS_FILE_EXTENSION = "mdl";
    private final static String OUTPUT_FILE_EXTENSION = "json";

    // We'll consider a conversion to have failed if the converted output file has a size that is less than this number of bytes.
    // This is derived from the following minimal skeleton JSON representation:
    // [{"d":{"identifier":"dataobj"},"p":{"identifier":"parobj"},"m":{"identifier":"mdlobj"},"t":{"identifier":"taskobj"}}]
    private final static int JSON_FILE_SIZE_THRESHOLD = 117;

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
    public MdlToJsonModelsTest(final File model, final String modelShortPath) {
        this.model = model;
    }

    /**
     * Test method that tests the conversion of a particular model file as provided by the
     * {@link File} parameter that was constructor-injected into this instance of the test class.
     */
    @Test
    public void testMdlToJsonConversion() throws IOException {
        final ConverterRunner runner = new ConverterRunner(this.model, OUTPUT_FILE_EXTENSION, "MDL", MDL_VERSION, "JSON", JSON_VERSION,
                new ConverterJsonOutputFailureCheckerImpl(JSON_FILE_SIZE_THRESHOLD));
        runner.run();
    }

    /**
     * A Converter Output Failure Checker that enhances the standard Converter Output Failure Checker
     * to also check that the generated JSON is valid i.e. can be parsed successfully.
     */
    private static class ConverterJsonOutputFailureCheckerImpl extends ConverterOutputFailureCheckerImpl {

        ConverterJsonOutputFailureCheckerImpl(int outputFileSizeThreshhold) {
            super(outputFileSizeThreshhold);
        }

        @Override
        public void check(File expectedOutputFile, File stdoutFile, File stderrFile) {
            super.check(expectedOutputFile, stdoutFile, stderrFile);
            try {
                final ObjectMapper mapper = new ObjectMapper();
                final JsonNode jsonAsTree = mapper.readTree(expectedOutputFile);
                assertNotNull("Checking that the JSON from file " + expectedOutputFile
                    + " was valid JSON i.e. was able to be parsed successfully", jsonAsTree);
                MdlToJsonModelsTest.LOGGER.debug(jsonAsTree);
            } catch (Exception e) {
                MdlToJsonModelsTest.LOGGER.error(
                    "Exception \"" + e.getMessage() + "\" thrown parsing JSON from file " + expectedOutputFile, e);
                fail("Error parsing JSON from file " + expectedOutputFile);
            }
        }

    }

}
