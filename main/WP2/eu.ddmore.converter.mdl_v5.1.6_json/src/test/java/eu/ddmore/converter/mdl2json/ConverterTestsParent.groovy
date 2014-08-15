package eu.ddmore.converter.mdl2json

import static org.junit.Assert.*

import org.apache.commons.io.FileUtils
import org.apache.commons.lang.StringUtils
import org.apache.log4j.Logger
import org.ddmore.mdl.mdl.Mcl

import eu.ddmore.mdlparse.MdlParser
import groovy.json.JsonSlurper
import java.util.regex.Matcher

class ConverterTestsParent {

    final static String TEST_DATA_DIR = "./"
    final static String MODELS_PROJECT_TEST_DATA_DIR = "/eu/ddmore/testdata/models/"
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
	 * @param relativePathToFile - the relative path to the MDL file within the directory /eu/ddmore/testdata/models/mdl/
	 * 							   within the testdata models project
	 * @return the MDL {@link File}
	 * @throws <code>NullPointerException</code> if the referenced file does not exist
	 * @see #getFileFromModelsProject(String, String)
	 */
    public File getFileFromModelsProject(final String relativePathToFile) {
		getFileFromModelsProject(relativePathToFile, "mdl")
    }
	
	/**
	 * Return a model {@link File} from the testdata models project.
	 * <p>
	 * @param relativePathToFile - the relative path to the file within the directory /eu/ddmore/testdata/models/[modelType]/
	 * 							   within the testdata models project
	 * @param modelType - the model-type subdirectory (e.g. "mdl", "ctl")
	 * @return the {@link File}
	 * @throws <code>NullPointerException</code> if the referenced file does not exist
	 */
	public File getFileFromModelsProject(final String relativePathToFile, final String modelType) {
		
		final URL urlToFile = ConverterTestsParent.class.getResource(MODELS_PROJECT_TEST_DATA_DIR + modelType + "/" + relativePathToFile)
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
		
		def String origMdlFileBlockContent = putParameterListsIntoKnownOrder(extractSpecificBlock(origMdlFileContent, blockName))
		def String newMdlFileBlockContent = putParameterListsIntoKnownOrder(extractSpecificBlock(newMdlFileContent, blockName))
		
		if (!StringUtils.isEmpty(origMdlFileBlockContent) || !StringUtils.isEmpty(newMdlFileBlockContent)) { // Check that we actually have something to compare
			logger.info("Verifying block " + blockName + "...")
		
			// Trim off whitespace from both the expected and the actual
			// Also drop any { } brackets around an "if" statement which are always added when writing out to MDL
			// TODO: Better way of dealing with this, since incorrect bracketing wouldn't be picked up as things stand
			assertEquals("Checking the content of the block " + blockName,
				origMdlFileBlockContent.replaceAll(~/\s*/, "").replaceAll(~/if\((.+?)\)\{(.*?)\}/, /if($1)$2/),
				newMdlFileBlockContent.replaceAll(~/\s*/, "").replaceAll(~/if\((.+?)\)\{(.*?)\}/, /if($1)$2/))
		}
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
		
		def found = false;
		def interestedIn = false
		def nestingLevel = -1
		mdlFileContent.eachLine { str ->
			if (str.matches("^\\s*" + blockName + "\\s*\\{")) {
				found = true
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
		
		if (!found) {
			logger.info("Block \"" + blockName + "\" was not found in the MDL")
		}
		
		strBuf.toString()
	}
	
	/**
	 * Find all occurrences of lists of parameters (i.e. <code>use=covariate, type=continuous</code>
	 * in the line of MDL <code>RATE=list(use=covariate,type=continuous)</code>), within the given
	 * MDL fragment, and rewrite these lists to be alphabetically ordered by key in order to facilitate
	 * comparison of the MDL fragment.
	 * <p>
	 * @param str - original fragment of MDL
	 * @return fragment of MDL that is identical save for the ordering of aforementioned lists of parameters
	 */
	private static String putParameterListsIntoKnownOrder(final String str) {
		
		// Some explanation of this regex needed!
		// The (?s) is the "dot-all" instruction to the matcher to match newline characters,
		// so "list(...)"s spread over multiple lines will match.
		// "(?:=list|\~)" is for matching either "=list(...)" or "~ (...)"; the "?:" instructs
		// the matcher to not actually capture this group.
		// The remainder of the regex is to match the parameter list in the ( ) brackets; the
		// "?" after the ".+" instructs the matcher to lazily match rather than greedily match,
		// otherwise the matcher would keep going until it found the last closing bracket rather
		// than the one that matched the opening bracket.
		final Matcher matcher = ( str =~ /(?s)(?:=list|\~)\s*\(\s*(.+?)\s*\)/)
		
		def outStr = str
		
		while (matcher.find()) {
			String[] params = matcher.group(1).split(/\s*,\s*/)
			outStr = outStr.replace(matcher.group(1), params.sort().join(","))
		}
		
		outStr
	}
	
}
