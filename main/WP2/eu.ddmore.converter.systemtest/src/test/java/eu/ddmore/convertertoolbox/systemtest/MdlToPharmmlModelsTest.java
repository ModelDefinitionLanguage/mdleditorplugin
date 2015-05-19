package eu.ddmore.convertertoolbox.systemtest;

import java.io.File;
import java.io.IOException;

import org.apache.commons.io.FileUtils;
import org.apache.commons.io.filefilter.SuffixFileFilter;
import org.apache.log4j.Logger;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.Parameterized;

import eu.ddmore.convertertoolbox.systemtest.FileType;


/**
 * Run MDL -> PharmML conversions over the testdata models within the "MDL" subdirectory.
 */
@RunWith(Parameterized.class)
public class MdlToPharmmlModelsTest {
    
    private final static Logger LOGGER = Logger.getLogger(MdlToPharmmlModelsTest.class);

    private final static String MODELS_SUBDIRECTORY = "MDL" + File.separator + FileType.MDL.getVersion();
    
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
        return ModelsTestHelper.getModelsToTest(MODELS_SUBDIRECTORY, FileType.MDL.getExtension());
    }
    
    private final File model;
    
    /**
     * Construct an instance of this test class for a particular model as taken from the list
     * provided by the {@link #getModelsToTest()} parameter-provider method.
     * <p>
     * @param model - the model {@link File}
     * @param modelShortPath - the path to the model with the "target/WorkingDir/test-models/"
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
            this.model, FileType.PharmML.getExtension(),
            FileType.MDL.name(), FileType.MDL.getVersion(), FileType.PharmML.name(), FileType.PharmML.getVersion(),
            new MdlToPharmMLOutputFailureChecker()
        );
        runner.run();
		// Copy the data file
        FileUtils.copyDirectory(this.model.getParentFile(), runner.getOutputDirectory(), new SuffixFileFilter(".csv"));
    }
    
}
