/*******************************************************************************
 * Copyright (C) 2014-2015 Mango Solutions Ltd - All rights reserved.
 ******************************************************************************/
package eu.ddmore.converter.mdl2json

import static org.junit.Assert.*

import org.apache.commons.io.FileUtils
import org.apache.commons.lang.StringUtils
import org.apache.log4j.Logger
import org.ddmore.mdl.mdl.Mcl

import com.google.common.base.Preconditions

import eu.ddmore.convertertoolbox.api.response.ConversionReport
import eu.ddmore.convertertoolbox.api.response.ConversionReport.ConversionCode
import eu.ddmore.convertertoolbox.domain.ConversionReportImpl
import eu.ddmore.mdlparse.MdlParser
import groovy.json.JsonSlurper


// This class is public since some of its fields and methods are imported and used by the converter toolbox "systemtest" project.
public class MdlAndJsonFileUtils {

    private final static String TEST_DATA_DIR = "./"
    private final static String MODELS_PROJECT_TEST_DATA_DIR = "/test-models/"
    private final static String WORKING_DIR = new File(FileUtils.getTempDirectory(), "MDL2JSON_Working_Dir")

    private static Logger logger = Logger.getLogger(MdlAndJsonFileUtils.class)

    public static File getFile(final String pathToFile) {
        String path = TEST_DATA_DIR + pathToFile
        URL url = MdlAndJsonFileUtils.getResource(path)
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
    public static File getFileFromModelsProject(final String relativePathToFile) {
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
    public static File getFileFromModelsProject(final String relativePathToFile, final String modelType) {

        final URL urlToFile = MdlAndJsonFileUtils.class.getResource(MODELS_PROJECT_TEST_DATA_DIR + modelType + "/" + relativePathToFile)
        Preconditions.checkArgument(urlToFile != null,
            "Model file at relative file path %s does not exist in the testdata models project", modelType + "/" + relativePathToFile)

        File destFile = new File(WORKING_DIR + relativePathToFile)
        FileUtils.copyURLToFile(urlToFile, destFile)

        return destFile
    }

    public static Object getJsonFromMDLFile(final String fileToConvert) {
        getJsonFromMDLFile(getFile(fileToConvert))
    }

    public static Object getJsonFromMDLFile(final File srcFile) {

        final ConversionReport report = new ConversionReportImpl()
        final Mcl mcl = new MdlParser().parse(srcFile, report)

        Preconditions.checkArgument(mcl != null,
            "Unable to parse MDL file " + srcFile + "; check the log files for exceptions that might have been thrown")
        Preconditions.checkArgument(!ConversionCode.FAILURE.equals(report.getReturnCode()),
            "Unable to parse MDL file " + srcFile + "; check the log files for exceptions that might have been thrown")

        String jsonText = new MDLToJSONConverter().toJSON(mcl)

        Preconditions.checkArgument(StringUtils.isNotBlank(jsonText), "Unable to parse MDL file " + srcFile + " into JSON; check the log files for exceptions that might have been thrown")

        logger.debug(jsonText)

        JsonSlurper slurper = new JsonSlurper();
        slurper.parseText(jsonText)
    }

    public static Object getJson(String jsonText) {
        JsonSlurper slurper = new JsonSlurper();
        slurper.parseText(jsonText)
    }

}
