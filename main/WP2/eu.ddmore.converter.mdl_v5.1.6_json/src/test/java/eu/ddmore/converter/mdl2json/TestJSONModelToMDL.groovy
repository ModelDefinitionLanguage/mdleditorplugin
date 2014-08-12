package eu.ddmore.converter.mdl2json;

import static org.junit.Assert.*

import org.apache.log4j.Logger
import org.junit.Test

import eu.ddmore.converter.mdl2json.domain.MCLFile


class TestJSONModelToMDL extends ConverterTestsParent {
	private static final Logger logger = Logger.getLogger(TestJSONModelToMDL.class)
	
	
	// TODO: Check all other data blocks too (in other test classes)
	
	
	@Test
	public void testProlactin() {
		def mdlFile = getFile("prolactin_ModelObject.mdl")
		
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
	
}
