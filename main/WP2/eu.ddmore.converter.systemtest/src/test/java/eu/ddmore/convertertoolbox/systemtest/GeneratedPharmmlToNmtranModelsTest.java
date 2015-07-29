package eu.ddmore.convertertoolbox.systemtest;

import java.io.File;
import java.util.List;

import org.apache.commons.io.FilenameUtils;
import org.apache.log4j.Logger;
import org.junit.AfterClass;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.Parameterized;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.google.common.base.Function;
import com.google.common.base.Preconditions;
import com.google.common.collect.Lists;

/**
 * Run PharmML -> NMTRAN conversions, but on PharmML files that were output by MDL -> PharmML
 * conversions rather than on hand-crafted PharmML.
 * <p>
 * {@link MdlToPharmmlModelsTest} runs the MDL -> PharmML conversions hence that test class needs to be
 * run before this test class; the {@link AllTests} test suite enforces this.
 */
@RunWith(ParallelizedRunner.class)
public class GeneratedPharmmlToNmtranModelsTest extends ConverterATParent {
    private final static String NAME = "GeneratedPharmmlToNmtranModelsTest";
    private final static Logger LOG = Logger.getLogger(GeneratedPharmmlToNmtranModelsTest.class);

    /**
     * The method that produces the parameters to be passed to each construction of the test class.
     * It uses a record file produced by the {@link MdlToPharmmlModelsTest} to get the files which will be attempted to 
     * be converted by {@link MdlToPharmmlModelsTest}
     * <p>
     * NB: The JUnit {@link Parameterized} framework requires the parameter-providing method to
     * return an {@link Iterable} of Arrays.
     * <p>
     * @return the models to convert as Iterable of Object[] arrays with the following elements:
     *                  <ol>
     *                      <li>{@link File} - test case's working directory</li>
     *                      <li>String - relative path to a model file</li>
     *                      <li>{@link File} - a path of the source test data directory</li>
     *                   </ol>
     * @throws Exception if collecting models to test failed
     */
    @Parameterized.Parameters(name = "{index}: Model {1}")
    public static Iterable<Object[]> getModelsToTest() throws Exception {
        ModelsTestHelper.prepareTestSystemProperties();
        LOG.info(String.format("Preparing parameters for %s.",GeneratedPharmmlToNmtranModelsTest.class));
        final File mdlToPharmMLATRoot = ModelsTestHelper.resolveAcceptanceTestSuiteWorkingDirectory(MdlToPharmmlModelsTest.NAME);
        Preconditions.checkState(mdlToPharmMLATRoot.exists(), String.format("The directory %s does not exist, did %s run first?", mdlToPharmMLATRoot, MdlToPharmmlModelsTest.class));
        
        final File atWd = ModelsTestHelper.resolveAcceptanceTestSuiteWorkingDirectory(NAME);
        atWd.mkdirs();
        ObjectMapper objectMapper = new ObjectMapper();
        List<Object[]> models = objectMapper.readValue(new File(mdlToPharmMLATRoot,ModelsTestHelper.TEST_RECORD_FILE), new TypeReference<List<String[]>>() {});
        
        Iterable<Object[]> result = Lists.transform(
            models,
            new Function<Object[], Object[]>() {
                public Object[] apply(Object[] conversionRecord) {
                    File mdlToPharmMLAt = new File(conversionRecord[0].toString());
                    File pharmmlToNmTranAT = new File(atWd, mdlToPharmMLAt.getName());
                    final String anticipatedGenPharmmlModelShortPath = FilenameUtils.removeExtension(conversionRecord[1].toString()) +"."+ FileType.PharmML.getExtension();
                    return new Object[] { pharmmlToNmTranAT, anticipatedGenPharmmlModelShortPath, mdlToPharmMLAt };
                }
            }
            );
        objectMapper.writeValue(new File(atWd,ModelsTestHelper.TEST_RECORD_FILE), Lists.newArrayList(result));
        return result;
    }

    /**
     * See {@link ConverterATParent}
     */
    public GeneratedPharmmlToNmtranModelsTest(File workingDirectory, String model, File testDataDir) {
        super(workingDirectory, model, testDataDir);
    }
    
    @Test
    public void convertPharmMLGeneratedByMdlToPharmMLConversionToNMTRAN() {
        File modelFile = getModelAbsoluteFile();
        Preconditions.checkState(modelFile.exists(), "Generated PharmML model \"%s\" was not found. Was MdlToPharmmlModelsTest run first?", modelFile);

        final ConverterRunner runner = new ConverterRunner(modelFile, FileType.NMTRAN.getExtension(),
                FileType.PharmML.name(), FileType.PharmML.getVersion(), FileType.NMTRAN.name(), FileType.NMTRAN.getVersion(),
                new NmTranCompilationChecker(System.getProperty(ModelsTestHelper.NONMEM_COMPILER_PARAMETERS_PROP),new File(System.getProperty(ModelsTestHelper.NONMEM_COMPILER_EXECUTABLE_PROP)).getAbsoluteFile()));
        runner.run();
    }

    @AfterClass
    public static void tearDown() {
        collectResults(ModelsTestHelper.resolveAcceptanceTestSuiteWorkingDirectory(NAME));
    }
}
