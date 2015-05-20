/*******************************************************************************
 * Copyright (C) 2014-5 Mango Solutions Ltd - All rights reserved.
 ******************************************************************************/
package eu.ddmore.converter.mdl2json

import static org.junit.Assert.*

import java.util.regex.Matcher

import org.apache.commons.io.FileUtils
import org.apache.commons.lang.StringUtils
import org.apache.log4j.Logger
import org.ddmore.mdl.mdl.Mcl

import com.google.common.base.Preconditions;

import eu.ddmore.converter.mdl2json.domain.Data
import eu.ddmore.converter.mdl2json.domain.Model
import eu.ddmore.converter.mdl2json.domain.Mog
import eu.ddmore.converter.mdl2json.domain.Parameter
import eu.ddmore.converter.mdl2json.domain.Source
import eu.ddmore.converter.mdl2json.domain.Task
import eu.ddmore.mdlparse.MdlParser
import groovy.json.JsonSlurper


// This class is public since some of its fields and methods are imported and used by the converter toolbox "systemtest" project.
public class ConverterTestsParent {

    final static String TEST_DATA_DIR = "./"
    final static String MODELS_PROJECT_TEST_DATA_DIR = "/test-models/"
    final static String WORKING_DIR = "target/MainTest_Working_Dir/"

    private static Logger logger = Logger.getLogger(ConverterTestsParent.class)

    private static final MDLToJSONConverter converter = new MDLToJSONConverter();

    public File getFile(final String pathToFile) {
        String path = TEST_DATA_DIR + pathToFile
        URL url = this.getClass().getResource(path)
        new File(url.getFile())
    }

    /**
     * Return a MDL {@link File} from the testdata models project.
     * <p>
     * @param relativePathToFile - the relative path to the MDL file within the directory /test-models/MDL/
     *                             within the Use Cases project
     * @return the MDL {@link File}
     * @throws <code>IllegalArgumentException</code> if the referenced file does not exist
     * @see #getFileFromModelsProject(String, String)
     */
    public File getFileFromModelsProject(final String relativePathToFile) {
        getFileFromModelsProject(relativePathToFile, "MDL")
    }

    /**
     * Return a model {@link File} from the testdata models project.
     * <p>
     * @param relativePathToFile - the relative path to the file within the directory /test-models/[modelType]/
     *                             within the Use Cases project
     * @param modelType - the model-type subdirectory (e.g. "MDL", "PharmML")
     * @return the {@link File}
     * @throws <code>IllegalArgumentException</code> if the referenced file does not exist
     */
    public File getFileFromModelsProject(final String relativePathToFile, final String modelType) {

        final URL urlToFile = ConverterTestsParent.class.getResource(MODELS_PROJECT_TEST_DATA_DIR + modelType + "/" + relativePathToFile)
        Preconditions.checkArgument(urlToFile != null,
            "Model file at relative file path %s does not exist in the testdata models project", modelType + "/" + relativePathToFile)

        File destFile = new File(WORKING_DIR + relativePathToFile)
        FileUtils.copyURLToFile(urlToFile, destFile)

        return destFile
    }

    public Object getJsonFromMDLFile(final String fileToConvert) {
        getJsonFromMDLFile(getFile(fileToConvert))
    }

    public Object getJsonFromMDLFile(final File srcFile) {

        MdlParser p = new MdlParser()
        Mcl mcl = p.parse(srcFile)

        Preconditions.checkArgument(mcl != null, "Unable to parse MDL file " + srcFile + "; check the log files for exceptions that might have been thrown")

        String jsonText = converter.toJSON(mcl)

        Preconditions.checkArgument(StringUtils.isNotBlank(jsonText), "Unable to parse MDL file " + srcFile + " into JSON; check the log files for exceptions that might have been thrown")

        logger.debug(jsonText)

        JsonSlurper slurper = new JsonSlurper();
        slurper.parseText(jsonText)
    }

    public Object getJson(String jsonText) {
        JsonSlurper slurper = new JsonSlurper();
        slurper.parseText(jsonText)
    }

}