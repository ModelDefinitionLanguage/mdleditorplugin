/*******************************************************************************
 * Copyright (C) 2015 Mango Solutions Ltd - All rights reserved.
 ******************************************************************************/
package eu.ddmore.convertertoolbox.systemtest;

import java.io.File;
import java.io.IOException;

import org.apache.log4j.Logger;
import org.junit.AfterClass;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.Parameterized;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.google.common.collect.Lists;

/**
 * Run MDL -> JSON -> MDL conversions over the testdata models within the "MDL" subdirectory,
 * The generated MDL file is tested for syntactic and semantic equivalence to the original MDL file.
 * Minimal checks are done on the MDL -> JSON bit of the pipeline since there is a separate
 * test class, {@link MdlToJsonModelsTest}, for this.
 */
@RunWith(ParallelizedRunner.class)
public class MdlToJsonToMdlModelsTest extends ConverterATParent {
    private final static Logger LOG = Logger.getLogger(MdlToJsonToMdlModelsTest.class);
    private final static String NAME = "MdlToJsonToMdlModelsTest";

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
     * @throws Exception 
     */
    @Parameterized.Parameters(name = "{index}: Model {1}")
    public static Iterable<Object[]> getModelsToTest() throws Exception {
        ModelsTestHelper.prepareTestSystemProperties();
        LOG.info(String.format("Preparing parameters for %s.",MdlToJsonToMdlModelsTest.class));
        File atWd = ModelsTestHelper.resolveAcceptanceTestSuiteWorkingDirectory(NAME);
        atWd.mkdirs();
        Iterable<Object[]> result = ModelsTestHelper.getModelsToTest(MODELS_SUBDIRECTORY, FileType.MDL.getExtension(),atWd);
        
        ObjectMapper objectMapper = new ObjectMapper();
        objectMapper.writeValue(new File(atWd,ModelsTestHelper.TEST_RECORD_FILE), Lists.newArrayList(result));
        return result;
    }

    /**
     * See {@link ConverterATParent}
     */
    public MdlToJsonToMdlModelsTest(File workingDirectory, String model, File testDataDir) {
        super(workingDirectory, model, testDataDir);
    }
    
    @Test
    public void convertsMdlToJsonAndThenBackToMdl() throws IOException {
        final File mdlModelFile = getModelAbsoluteFile();
            
        final ConverterRunner runner1 = new ConverterRunner(mdlModelFile, FileType.JSON.getExtension(),
                FileType.MDL.name(), FileType.MDL.getVersion(), FileType.JSON.name(), FileType.JSON.getVersion(),
                new ConverterJsonOutputFailureChecker());
        runner1.run();
        
        final File jsonModelFile = new File(mdlModelFile.getParentFile(), mdlModelFile.getName().replace(FileType.MDL.getExtension(), FileType.JSON.getExtension()));
        
        final ConverterRunner runner2 = new ConverterRunner(jsonModelFile, FileType.MDL.getExtension(),
                FileType.JSON.name(), FileType.JSON.getVersion(), FileType.MDL.name(), FileType.MDL.getVersion(),
                new MdlFileEquivalenceChecker(mdlModelFile));
        runner2.run();
        
    }

    @AfterClass
    public static void tearDown() {
        collectResults(new File(System.getProperty(ModelsTestHelper.AT_WORKING_DIRECTORY_LOCATION_PROP),NAME).getAbsoluteFile());
    }
}
