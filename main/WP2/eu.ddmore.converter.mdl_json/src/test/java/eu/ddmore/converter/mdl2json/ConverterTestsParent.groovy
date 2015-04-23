package eu.ddmore.converter.mdl2json

import static eu.ddmore.converter.mdl2json.interfaces.MDLPrintable.IDT
import static org.junit.Assert.*

import java.util.regex.Matcher

import org.apache.commons.io.FileUtils
import org.apache.commons.lang.StringUtils
import org.apache.log4j.Logger
import org.ddmore.mdl.mdl.Mcl

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
	 * The fields and methods below are used in testing a MDL->JSON->MDL pipeline.
	 * Some of these are imported and used by the converter toolbox "systemtest" project.
	 */
    
    
    public static List<String> allBlockNames = [
        Data.DECLARED_VARIABLES,
        Data.DATA_INPUT_VARIABLES,
        Data.DATA_DERIVED_VARIABLES,
        Data.SOURCE,
        Parameter.STRUCTURAL,
        Parameter.VARIABILITY,
        Model.COVARIATES,
        Model.VARIABILITY_LEVELS,
        Model.STRUCTURAL_PARAMETERS,
        Model.VARIABILITY_PARAMETERS,
        Model.RANDOM_VARIABLE_DEFINITION + /\(.+\)/, // note the regex matching for the parameters of the block name
        Model.INDIVIDUAL_VARIABLES,
        Model.MODEL_PREDICTION,
        Model.OBSERVATION,
        Model.GROUP_VARIABLES,
        Task.ESTIMATE,
        Task.SIMULATE,
        Task.EVALUATE,
        Task.OPTIMISE,
        Task.DATA,
        Task.MODEL,
        /\S+\s*=\s*/ + Mog.IDENTIFIER
    ]
	
    public static extractBlockFromOriginalMDLAndCompareIgnoringWhitespaceAndComments(final File origMdlFile, final String blockName, final File newMdlFile) {
        extractBlockFromOriginalMDLAndCompareIgnoringWhitespaceAndComments(origMdlFile, blockName, FileUtils.readFileToString(newMdlFile))
    }
    
	public static extractBlockFromOriginalMDLAndCompareIgnoringWhitespaceAndComments(final File origMdlFile, final String blockName, final String newMdlFileContent) {
		def String origMdlFileContent = readInAndStripComments(origMdlFile)
		
		def String origMdlFileBlockContent = putParameterListsIntoKnownOrder(extractSpecificBlock(origMdlFileContent, blockName))
		def String newMdlFileBlockContent = putParameterListsIntoKnownOrder(extractSpecificBlock(newMdlFileContent, blockName))
		
		if (!StringUtils.isEmpty(origMdlFileBlockContent) || !StringUtils.isEmpty(newMdlFileBlockContent)) { // Check that we actually have something to compare
			logger.info("Verifying block " + blockName + "...")
			
			// Special additional preprocessing for the "SOURCE" block:
			// The items within this block can be in any order so put the lines of the original and new blocks into a known order
			if (blockName == Data.SOURCE) {
				origMdlFileBlockContent = putSOURCEBlockContentInKnownOrder(origMdlFileBlockContent)
				newMdlFileBlockContent = putSOURCEBlockContentInKnownOrder(newMdlFileBlockContent)
			}
			// Special additional preprocessing for the "mog" block:
			// The items within this block can be in any order so put the lines of the original and new blocks into a known order
			if (blockName.endsWith(Mog.IDENTIFIER)) {
				origMdlFileBlockContent = putMogBlockContentInKnownOrderAndRenameMogToKnownName(blockName, origMdlFileBlockContent)
				newMdlFileBlockContent = putMogBlockContentInKnownOrderAndRenameMogToKnownName(blockName, newMdlFileBlockContent)
			}
		
			// Trim off whitespace from both the expected and the actual
			assertEquals("Checking the content of the block " + blockName,
				origMdlFileBlockContent.replaceAll(~/\s*/, "") /* REDUNDANT: .replaceAll(~/if\((.+?)\)\{(.*?)\}/, /if($1)$2/) */ ,
				newMdlFileBlockContent.replaceAll(~/\s*/, "") /* REDUNDANT: .replaceAll(~/if\((.+?)\)\{(.*?)\}/, /if($1)$2/) */
            )
		}
	}
	
	private static String readInAndStripComments(final File origMdlFile) {
		final BufferedReader rdr = new BufferedReader(new FileReader(origMdlFile));
		final StringBuffer strBuf = new StringBuffer();
		rdr.eachLine() { String str ->
			processHashChars(str, 0, strBuf)
		}
		strBuf.toString()
	}
	
	/**
	 * Strip off hash-character-commencing comment, if present, from the input string;
	 * but ignore hash characters appearing within quoted strings.
	 * <p>
	 * @param fullStr - the full line of text read in from the MDL file
	 * @param fromIndex - the zero-based index in the string at which to start processing
	 * @param strBuf - the string buffer to which to append the processed line of text
	 */
	private static void processHashChars(final String fullStr, final int fromIndex, final StringBuffer strBuf) {
		def hashCharPos = fullStr.indexOf("#", fromIndex)
		if (hashCharPos < 0) {
			strBuf.append(fullStr.substring(fromIndex))
			strBuf.append("\n")
		} else {
			// There is a # character somewhere in the line
			String subStr = fullStr.substring(fromIndex, hashCharPos)
			if (subStr.count("\"") % 2 == 1) {
				// Hash char is within a quoted string (may be multiple quoted strings on a single line)
				strBuf.append(subStr)
				def closingQuotePos = fullStr.indexOf("\"", hashCharPos)
				strBuf.append(fullStr.substring(hashCharPos, closingQuotePos + 1)) // Ensure we include the closing quote
				processHashChars(fullStr, closingQuotePos + 1, strBuf) // Repeat until the end of the string is reached
			} else {
				// Hash char is not within a quoted string so is most likely starting a comment
				if (!subStr.matches(~/\s*$/)) { // Not just whitespace
					strBuf.append(subStr)
					strBuf.append("\n")
				}
			}
		}
	}
	
	private static String extractSpecificBlock(final String mdlFileContent, final String blockName) {
		final StringBuffer strBuf = new StringBuffer()
		
		def found = false;
		def interestedIn = false
		def nestingLevel = -1
		mdlFileContent.eachLine { str ->
			if (str.matches("^\\s*" + blockName + "\\s*\\{.*")) {
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
		// so parameter definitions spread over multiple lines will match.
		// The \{\s*(.+?)\s*\} is to match the list of attributes of the parameter, in the { }
		// brackets; the "?" after the ".+" instructs the matcher to lazily match rather than
		// greedily match, otherwise the matcher would keep going until it found the last closing
		// bracket rather than the one that matched the opening bracket.
		def outStr1 = sortParameterList(str, ( str =~ /(?s)[A-Za-z0-9]+\s*\:\s*\{\s*(.+?)\s*\}/ ))
		// This regex is almost the same as the previous one but is to match the list of 'complex
		// attributes' i.e. for a distribution parameter VAR ~ (...).
		def outStr2 = sortParameterList(outStr1, ( outStr1 =~ /(?s)[A-Za-z0-9]+\s*\~\s*\{\s*(.+?)\s*\}/ ))
		
		outStr2
	}
	
	private static sortParameterList(final String str, Matcher matcher) {
		def outStr = str
		
		while (matcher.find()) {
			final String paramsStr = matcher.group(1)
			String[] params = paramsStr.split(/\s*,\s*/)
			outStr = outStr.replace(paramsStr, params.sort().join(","))
		}
		
		outStr
	}
	
	/**
	 * Special additional preprocessing for the "SOURCE" block.
	 * The <code>name = value</code> pairs within this block can be in any order so put the lines
	 * of the original and new blocks into a known order.
	 * <p>
	 * @param blockText - the string comprising the block name and its unordered content
	 * @return the string comprising the block name and its reordered content
	 */
	private static String putSOURCEBlockContentInKnownOrder(final String blockText) {
		putSOURCEBlockOrMogBlockContentInKnownOrder(Data.SOURCE, blockText, /\s*\S+\s*=\s*\S+\s*/)
	}
	
	/**
	 * Special additional preprocessing for the "mog" top-level block:
	 * <ol>
	 * <li>The object identifiers within this block can be in any order so put the lines of the
	 *     original and new blocks into a known order.
	 * <li>The name of the MOG in the written out MDL will in general be different to the name
	 *     of the MOG in the input MDL file, so replace the MOG name with a standard name
	 *     "theMog" for the purposes of comparison of the block text.
	 * </ol>
	 * <p>
	 * @param blockName - the name of the block, actually a regular expression
	 * @param blockText - the string comprising the block name and its unordered content
	 * @return the string comprising the block name and its reordered content
	 */
	private static String putMogBlockContentInKnownOrderAndRenameMogToKnownName(final String blockName, final String blockText) {
		putSOURCEBlockOrMogBlockContentInKnownOrder(blockName, blockText, /\s*\S+\s*/).toString().replaceFirst(/\S+\s*=\s*mog\s*\{/, "theMog = mog {")
	}
	
	/**
	 * Special additional preprocessing for the "SOURCE" block and the "mog" top-level block.
	 * The items within these blocks can be in any order so put the lines of the original and new blocks into a known order.
	 * <p>
	 * @param blockName - the name of the block, actually a regular expression
	 * @param blockText - the string comprising the block name and its unordered content
	 * @param itemsMatcher - the regular expression to extract the individual attributes / items within the content of the block
	 * @return the string comprising the block name and its reordered content
	 */ 
	private static String putSOURCEBlockOrMogBlockContentInKnownOrder(final String blockName, final String blockText, final String itemsRegex) {
		
		// Similar regex behaviour to those in putParameterListsIntoKnownOrder()
		final Matcher outerMatcher = ( blockText =~ /(?s)/ + blockName + /\s*\{(\s*.+?\s*)\}/ )
		
		def outStr = blockText
		
		while (outerMatcher.find()) { // Should only be one match
			final String blockContent = outerMatcher.group(1)
			
			final Matcher itemsMatcher = ( blockContent =~ itemsRegex )
			
			outStr = outStr.replace(blockContent, "\n${IDT*2}" + itemsMatcher.collect{ attrStr -> attrStr.trim() }.sort().join("\n${IDT*2}") + "\n${IDT}" )
		}
		
		outStr
	}
	
}
