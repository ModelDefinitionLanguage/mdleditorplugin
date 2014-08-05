package eu.ddmore.converter.mdl2json;

import static org.junit.Assert.*

import java.io.FileReader
import java.io.BufferedReader
import org.apache.commons.io.FileUtils
import org.apache.commons.lang.StringUtils
import org.apache.log4j.Logger
import org.junit.Test

import eu.ddmore.converter.mdl2json.domain.MCLFile


class TestJSONModelToMDL extends ConverterTestsParent {
	private static final Logger logger = Logger.getLogger(TestJSONModelToMDL.class)
	
	@Test
	public void testProlactin() {
		def mdlFile = getFile("prolactinModel.mdl")
		
		def json = getJsonFromMDLFile(mdlFile)
		
		MCLFile mclFile = new MCLFile(json)
		
		logger.debug(mclFile.toMDL())
		
		extractBlockFromOriginalMDLAndCompareIgnoringWhitespaceAndComments(mdlFile, "INDIVIDUAL_VARIABLES", mclFile.toMDL())
		extractBlockFromOriginalMDLAndCompareIgnoringWhitespaceAndComments(mdlFile, "GROUP_VARIABLES", mclFile.toMDL())
		extractBlockFromOriginalMDLAndCompareIgnoringWhitespaceAndComments(mdlFile, "OBSERVATION", mclFile.toMDL())
		extractBlockFromOriginalMDLAndCompareIgnoringWhitespaceAndComments(mdlFile, "ESTIMATION", mclFile.toMDL()) // Actually empty since it doesn't exist in the MDL file
		// Other blocks to check:
		//MODEL_INPUT_VARIABLEs
		//STRUCTURAL_PARAMETERS
		//VARIABILITY_PARAMETERS
		//RANDOM_VARIABLE_DEFINITION
		//MODEL_PREDICTION::ODE
		//MODEL_PREDICTION::LIBRARY
		//MODEL_PREDICTION::content
		//MODEL_OUTPUT_VARIABLES
		
	}
	
	@Test
	public void testDrugX() {
		def mdlFile = getFile("drugX_ModelObject.mdl")
		
		def json = getJsonFromMDLFile(mdlFile)
		
		MCLFile mclFile = new MCLFile(json)
		
		logger.debug(mclFile.toMDL())
		
		extractBlockFromOriginalMDLAndCompareIgnoringWhitespaceAndComments(mdlFile, "INDIVIDUAL_VARIABLES", mclFile.toMDL())
		extractBlockFromOriginalMDLAndCompareIgnoringWhitespaceAndComments(mdlFile, "GROUP_VARIABLES", mclFile.toMDL())
		extractBlockFromOriginalMDLAndCompareIgnoringWhitespaceAndComments(mdlFile, "OBSERVATION", mclFile.toMDL()) // Actually empty since it doesn't exist in the MDL file
		extractBlockFromOriginalMDLAndCompareIgnoringWhitespaceAndComments(mdlFile, "ESTIMATION", mclFile.toMDL())
		// Other blocks to check:
		//MODEL_INPUT_VARIABLEs
		//STRUCTURAL_PARAMETERS
		//VARIABILITY_PARAMETERS
		//RANDOM_VARIABLE_DEFINITION
		//MODEL_PREDICTION::ODE
		//MODEL_PREDICTION::LIBRARY
		//MODEL_PREDICTION::content
		//MODEL_OUTPUT_VARIABLES
		
	}
	
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
			if (str.contains(blockName)) {
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
