package eu.ddmore.convertertoolbox.systemtest;

import static java.util.regex.Matcher.quoteReplacement;
import static org.junit.Assert.fail;

import java.io.File;
import java.io.IOException;
import java.util.Iterator;

import org.apache.commons.io.FileUtils;
import org.apache.log4j.Logger;
import org.junit.BeforeClass;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.Parameterized;

import com.google.common.base.Function;
import com.google.common.base.Preconditions;
import com.google.common.base.Predicate;
import com.google.common.collect.Iterables;

/**
 * Run PharmML -> NMTRAN conversions, but on PharmML files that were output by MDL -> PharmML
 * conversions rather than on hand-crafted PharmML.
 * <p>
 * {@link MdlToPharmmlModelsTest} runs the MDL -> PharmML conversions hence that test class needs to be
 * run before this test class; the {@link AllTests} test suite enforces this.
 */
@RunWith(Parameterized.class)
public class GeneratedPharmmlToNmtranModelsTest {

    private final static Logger LOGGER = Logger.getLogger(GeneratedPharmmlToNmtranModelsTest.class);

    private final static String ORIGINAL_MDL_MODELS_SUBDIRECTORY =
        quoteReplacement("MDL" + File.separator + FileType.MDL.getVersion()); // quoteReplacement() since the backslashes need to be escaped in this string
    private final static String GENERATED_PHARMML_MODELS_SUBDIRECTORY =
        quoteReplacement("PharmML-generated-from-MDL" + File.separator + FileType.MDL.getVersion()); // quoteReplacement() since the backslashes need to be escaped in this string

    /**
     * The method that produces the parameters to be passed to each construction of the test class.
     * In this case, the {@link File}s that are the <b>original</b> MDL models since the generated
     * PharmML model files haven't been created yet at the time the {@link AllTests} test suite is
     * initialised.
     * <p>
     * The actual constructions of this test class take place later, immediately before the tests
     * are actually run, at which point the generated PharmML model files <b>will</b> be present
     * ({@link AllTests} enforces {@link MdlToPharmmlModelsTest} to be run before this
     * {@link GeneratedPharmmlToNmtranModelsTest}), hence the constructor
     * {@link GeneratedPharmmlToNmtranModelsTest#GeneratedPharmmlModelsTest(File, String)} is responsible
     * for taking the MDL model file and obtaining the corresponding generated PharmML model file
     * on which the conversion will be run.
     * <p>
     * NB: The JUnit {@link Parameterized} framework requires the parameter-providing method to
     * return an {@link Iterable} of Arrays.
     * <p>
     * @return the original MDL model files, as {@link File} objects
     */
    @Parameterized.Parameters(name = "{index}: Model {1}")
    public static Iterable<Object[]> getModelsToTest() {
        return Iterables.transform(
            new ModelsDiscoverer(ORIGINAL_MDL_MODELS_SUBDIRECTORY, FileType.MDL.getExtension()).getAllModels(),
            new Function<File, Object[]>() {

                public Object[] apply(final File input) {
                    final String anticipatedGenPharmmlModelPath = getGeneratedPharrmlModelFilePathFromMdlModelFilePath(input.getPath());
                    final String anticipatedGenPharmmlModelShortPath = anticipatedGenPharmmlModelPath
                            .substring(ModelsDiscoverer.PATH_TO_MODELS_DIR.length());
                    return new Object[] { input, anticipatedGenPharmmlModelShortPath };
                };
            });
    }

    /**
     * @param mdlModelFilePath - file path to a MDL model file
     * @return file path to the corresponding generated PharmML model file (which may not actually exist yet),
     *         within the "PharmML-generated-from-MDL" directory structure into which the generated PharmML
     *         model files are copied by {@link #copyGeneratedPharmmlFiles()}
     */
    private static String getGeneratedPharrmlModelFilePathFromMdlModelFilePath(final String mdlModelFilePath) {
        return mdlModelFilePath.replaceFirst(ORIGINAL_MDL_MODELS_SUBDIRECTORY, GENERATED_PHARMML_MODELS_SUBDIRECTORY).replace(
            FileType.MDL.getExtension(), FileType.PharmML.getExtension());
    }

    /**
     * Before the tests in this class are run, clean out the "PharmML-generated-from-MDL"
     * directory and copy the PharmML files and their data files, as output by the
     * MDL->PharmML conversion tests, into this directory structure.
     * <p>
     * <b>Precondition:</b> The MDL Models conversion tests have already been run, in order that
     * the generated PharmML files are actually present.
     * <p>
     * The PharmML models generated from the MDL Models conversion are written to "MDL/[modelname]/output-xml" subdirectories.
     * Find all the actual PharmML files (i.e. with .xml extension) within these directories (there may be data files etc.
     * in there too), and copy each of their containing folders (i.e. including the associated data files), to a suitably
     * named directory (i.e. taken from the name of the model in the path to the PharmML file) under a
     * "PharmML-generated-from-MDL" directory.
     * <p>
     * This is in preparation for the {@link ModelsDiscoverer} to be run over these PharmML files in the
     * "PharmML-generated-from-MDL" directory.
     * <p>
     * @throws IOException if unable to delete the "PharmML-generated-from-MDL" directory
     */
    @BeforeClass
    public static void copyGeneratedPharmmlFiles() throws IOException {

        FileUtils.deleteDirectory(new File(ModelsDiscoverer.PATH_TO_MODELS_DIR + GENERATED_PHARMML_MODELS_SUBDIRECTORY));

        final Iterable<File> generatedPharmmlModelFiles = Iterables.filter(new ModelsDiscoverer(ORIGINAL_MDL_MODELS_SUBDIRECTORY,
                FileType.PharmML.getExtension()).getAllModels(), new Predicate<File>() {

            /**
             * Since .xml is a generic file extension, need to filter out any XML files that are not within a "MDL/[modelname]/output-xml" subdirectory.
             */
            public boolean apply(File input) {
                return input.getParentFile().getName().equals(ConverterRunner.OUTPUT_SUBDIRECTORY_BASENAME + FileType.PharmML.getExtension());
            };
        });

        for (final Iterator<File> it = generatedPharmmlModelFiles.iterator(); it.hasNext();) {
            final File srcDir = it.next().getParentFile();

            final File destDir = new File(srcDir
                    .getParentFile()
                    .getAbsolutePath()
                    .replace(new File(ModelsDiscoverer.PATH_TO_MODELS_DIR + ORIGINAL_MDL_MODELS_SUBDIRECTORY).getAbsolutePath(),
                        new File(ModelsDiscoverer.PATH_TO_MODELS_DIR + GENERATED_PHARMML_MODELS_SUBDIRECTORY).getPath()));

            LOGGER.info("Copying from " + srcDir + " to " + destDir);
            try {
                FileUtils.copyDirectory(srcDir, destDir);
            } catch (final IOException ioe) {
                fail("Unable to copy generated PharmML models from " + srcDir + " to " + destDir + " - Cause: " + ioe);
            }
        }

    }

    private final File generatedPharmmlModel;

    /**
     * Construct an instance of this test class for a particular model as taken from the list
     * provided by the {@link #getModelsToTest()} parameter-provider method. The model that is
     * provided as the constructor argument is the <b>original</b> MDL model {@link File} since
     * the generated PharmML model file hasn't been created yet at the time the {@link AllTests}
     * test suite is initialised, hence we derive and save away the generated PharmML model
     * {@link File}, as copied to the appropriate structure under the "PharmML-generated-from-MDL"
     * directory structure in {@link #copyGeneratedPharmmlFiles()} and upon which the conversion
     * will be run, within this constructor.
     * <p>
     * @param originalMdlModel - the original MDL model {@link File}, from which the generated PharmML model
     *                           {@link File} is obtained
     * @param modelShortPath - the path to the model with the "target/WorkingDir/test-models/"
     *                         prefix stripped off; this is incorporated into the display name of the test
     *                         but is otherwise unused
     */
    public GeneratedPharmmlToNmtranModelsTest(final File originalMdlModel, final String modelShortPath) {
        // Obtain generatedPharmmlModel from originalMdlModel
        this.generatedPharmmlModel = new File(getGeneratedPharrmlModelFilePathFromMdlModelFilePath(originalMdlModel.getPath()));
        Preconditions.checkState(this.generatedPharmmlModel.exists(), "Generated PharmML model \"%s\" was not found. Was MdlToPharmmlModelsTest run first?", this.generatedPharmmlModel);
    }

    /**
     * Test method that tests the conversion of a particular model file as provided by the
     * {@link File} parameter that was constructor-injected into this instance of the test class.
     */
    @Test
    public void testPharmMLToNMTRANConversionForPharmMLGeneratedByMdlToPharmMLConversion() {
        final ConverterRunner runner = new ConverterRunner(this.generatedPharmmlModel, FileType.NMTRAN.getExtension(),
                FileType.PharmML.name(), FileType.PharmML.getVersion(), FileType.NMTRAN.name(), FileType.NMTRAN.getVersion(),
                new NmTranFileEquivalenceChecker());
        runner.run();
    }

}
