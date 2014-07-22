package eu.ddmore.converter.mdl2json

import eu.ddmore.mdlparse.MdlParser

import groovy.json.JsonSlurper
import java.io.File;

import org.apache.log4j.Logger;
import org.ddmore.mdl.mdl.Mcl

class ConverterTestsParent {

    final static String TEST_DATA_DIR = "./"
    final static String MODELS_PROJECT_TEST_DATA_DIR = "/eu/ddmore/testdata/models/mdl/"
    final static String WORKING_DIR = "target/MainTest_Working_Dir/"

    private static Logger logger = Logger.getLogger(ConverterTestsParent.class)

    private static final MDLToJSONConverter converter = new MDLToJSONConverter();

    public File getFile(final String pathToFile) {
        String path = TEST_DATA_DIR + pathToFile
        URL url = this.getClass().getResource(path)
        new File(url.getFile())
    }

    public File getFileFromModelsProject(final String relativePathToFile) {

        final URL urlToFile = ConverterTestsParent.class.getResource(MODELS_PROJECT_TEST_DATA_DIR + relativePathToFile)
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

        String jsonText = converter.toJSON(mcl)

        logger.debug(jsonText)

        JsonSlurper slurper = new JsonSlurper();
        slurper.parseText(jsonText)
    }

    public Object getJson(String jsonText) {
        JsonSlurper slurper = new JsonSlurper();
        slurper.parseText(jsonText)
    }
}
