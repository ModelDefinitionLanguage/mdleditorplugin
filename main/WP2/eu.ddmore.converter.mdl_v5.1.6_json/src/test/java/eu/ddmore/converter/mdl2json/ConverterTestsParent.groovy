package eu.ddmore.converter.mdl2json

import static org.junit.Assert.*

import org.apache.commons.io.FileUtils
import org.apache.commons.lang.StringUtils
import org.apache.log4j.Logger
import org.ddmore.mdl.mdl.Mcl

import eu.ddmore.mdlparse.MdlParser
import groovy.json.JsonSlurper

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
	
	/*
	 * The methods below are used in testing a MDL->JSON->MDL pipeline.
	 */
	
	public static extractBlockFromOriginalMDLAndCompareIgnoringWhitespaceAndComments(final File origMdlFile, final String blockName, final String newMdlFileContent) {
		def String origMdlFileContent = readInAndStripComments(origMdlFile)
		
		def String origMdlFileBlockContent = extractSpecificBlock(origMdlFileContent, blockName)
		def String newMdlFileBlockContent = extractSpecificBlock(newMdlFileContent, blockName)
		
		// Trim off whitespace from both the expected and the actual
		// Also drop any { } brackets around an "if" statement which are always added when writing out to MDL
		assertEquals("Checking the content of the block " + blockName,
			origMdlFileBlockContent.replaceAll(~/\s*/, ""),
			newMdlFileBlockContent.replaceAll(~/\s*/, "").replaceAll(~/if\((.+?)\)\{(.*?)\}/, /if($1)$2/))
		
	}
	
	private static String readInAndStripComments(final File origMdlFile) {
		final BufferedReader rdr = new BufferedReader(new FileReader(origMdlFile));
		final StringBuffer strBuf = new StringBuffer();
		rdr.eachLine() { String str ->
			str = str.substring(0, str.indexOf("#") >= 0 ? str.indexOf("#") : str.length()) // Trim off comments
			if (!str.matches(~/\s*$/)) { // Not just whitespace
				strBuf.append(str)
				strBuf.append("\n")
			}
		}
		
		strBuf.toString()
	}
	
	private static String extractSpecificBlock(final String mdlFileContent, final String blockName) {
		final StringBuffer strBuf = new StringBuffer()
		
		def interestedIn = false
		def nestingLevel = -1
		mdlFileContent.eachLine { str ->
			if (str.matches("^\\s*" + blockName + "\\s*\\{")) {
				interestedIn = true
				// There might be open curly brackets before the name of the block name; the '-' sign on the RHS is deliberate
				nestingLevel = - StringUtils.countMatches(str.split(blockName)[0], "{")
			}
			if (interestedIn) {
				// Append this line to the string to be returned
				strBuf.append(str)
				strBuf.append("\n")
				// Update how deep in the { } nesting we are so we know when to escape eventually
				nestingLevel = nestingLevel + StringUtils.countMatches(str, "{")
				nestingLevel = nestingLevel - StringUtils.countMatches(str, "}")
				
			}
			if (nestingLevel <= 0) { // End of block we're interested in has been reached
				interestedIn = false
				nestingLevel = -1;
			}
		}
		
		strBuf.toString()
	}
	
}
