/*******************************************************************************
 * Copyright (C) 2015 Mango Solutions Ltd - All rights reserved.
 ******************************************************************************/
package eu.ddmore.convertertoolbox.systemtest;

import java.io.File;
import java.io.IOException;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import org.apache.commons.io.FileUtils;
import org.apache.log4j.Logger;
import org.junit.AfterClass;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.Parameterized;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.google.common.base.Predicate;
import com.google.common.collect.Iterables;
import com.google.common.collect.Lists;


/**
 * Run MDL -> PharmML conversions over the testdata models within the "MDL" subdirectory.
 */
@RunWith(ParallelizedRunner.class)
public class MdlToPharmmlModelsTest extends ConverterATParent {
    private final static Logger LOG = Logger.getLogger(MdlToPharmmlModelsTest.class);
    final static String NAME = "MdlToPharmmlModelsTest";

    private final static String MODELS_SUBDIRECTORY = "MDL" + File.separator + FileType.MDL.getVersion();
    
    /**
     * The method that produces the parameters to be passed to each construction of the test class.
     * In this case, the {@link File}s that are the models for which to test the conversion.
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
    @Parameterized.Parameters(name= "{index}: Model {1}")
    public static Iterable<Object[]> getModelsToTest() throws Exception {
        LOG.info(String.format("Preparing parameters for %s.",MdlToPharmmlModelsTest.class));
        ModelsTestHelper.prepareTestSystemProperties();
        File atWd = ModelsTestHelper.resolveAcceptanceTestSuiteWorkingDirectory(NAME);
        atWd.mkdirs();
        Iterable<Object[]> result = filterOutMDLFilesWithMissingMOG(ModelsTestHelper.getModelsToTest(MODELS_SUBDIRECTORY, FileType.MDL.getExtension(),atWd));
        ObjectMapper objectMapper = new ObjectMapper();
        objectMapper.writeValue(new File(atWd,ModelsTestHelper.TEST_RECORD_FILE), Lists.newArrayList(result));
        return result;
    }

    private static Iterable<Object[]> filterOutMDLFilesWithMissingMOG(final Iterable<Object[]> discoveredMdlFiles) {
        return Iterables.filter(discoveredMdlFiles, new Predicate<Object[]>() {
            public boolean apply(final Object[] conversionRecord) {
                File testDataDir = new File(conversionRecord[2].toString());
                File mdlFile = new File(testDataDir,conversionRecord[1].toString());
                String mdlFileContent;
                try {
                    mdlFileContent = FileUtils.readFileToString(mdlFile);
                } catch (IOException ioe) {
                    throw new RuntimeException("Error reading MDL file " + mdlFile.getAbsolutePath(), ioe);
                }
                final Pattern mogobjPattern = Pattern.compile("[A-Za-z0-9_]+\\s*=\\s*mogObj\\s*\\{.+\\}", Pattern.DOTALL);
                final Matcher mogobjMatcher = mogobjPattern.matcher(mdlFileContent);
                final boolean containsMogObj = mogobjMatcher.find();
                if (!containsMogObj) {
                    LOG.warn("No mogobj block found in MDL file " + mdlFile + ", therefore no PharmML file will be generated. Skipping this model...");
                    return false;
                }
                return true;
            }
        });
    }

    /**
     * See {@link ConverterATParent}
     */
    public MdlToPharmmlModelsTest(File workingDirectory, String model, File testDataDir) {
        super(workingDirectory, model, testDataDir);
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
    public void convertsMdlToPharmML() throws IOException {
        final ConverterRunner runner = new ConverterRunner(
            getModelAbsoluteFile(), FileType.PharmML.getExtension(),
            FileType.MDL.name(), FileType.MDL.getVersion(), FileType.PharmML.name(), FileType.PharmML.getVersion(),
            new MdlToPharmMLOutputFailureChecker()
        );
        runner.run();
    }

    @AfterClass
    public static void tearDown() {
        collectResults(new File(System.getProperty(ModelsTestHelper.AT_WORKING_DIRECTORY_LOCATION_PROP),NAME).getAbsoluteFile());
    }
    
}
