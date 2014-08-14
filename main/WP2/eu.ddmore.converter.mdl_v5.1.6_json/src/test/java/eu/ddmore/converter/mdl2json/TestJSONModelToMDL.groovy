package eu.ddmore.converter.mdl2json;

import static org.junit.Assert.*

import org.apache.log4j.Logger
import org.junit.Test

import eu.ddmore.converter.mdl2json.domain.MCLFile
import eu.ddmore.converter.mdl2json.domain.Model
import groovy.json.JsonSlurper


class TestJSONModelToMDL extends ConverterTestsParent {
	private static final Logger logger = Logger.getLogger(TestJSONModelToMDL.class)
	
	@Test
	public void testModelInputVariablesBlock() {

		File jsonFile = getFile("prolactin_ModelObject_ModelInputVariables.json")
		
		String content = jsonFile.getText()
		
		def json = getJson(content)
		
		Model m = new Model(json)
		
		logger.debug(m.toMDL())
		
		extractBlockFromOriginalMDLAndCompareIgnoringWhitespaceAndComments(getFile("prolactin_ModelObject.mdl"), "MODEL_INPUT_VARIABLES", m.toMDL());
				
	}
	
	@Test
	public void testStructuralParametersBlock() {

		File jsonFile = getFile("drugX_ModelObject_StructuralParameters.json")
		
		String content = jsonFile.getText()
		
		def json = getJson(content)
		
		Model m = new Model(json)
		
		logger.debug(m.toMDL())
		
		extractBlockFromOriginalMDLAndCompareIgnoringWhitespaceAndComments(getFile("drugX_ModelObject.mdl"), "STRUCTURAL_PARAMETERS", m.toMDL());
				
	}
	
	@Test
	public void testVariabilityParametersBlock() {
		File jsonFile = getFile("drugX_ModelObject_VariabilityParameters.json")
		
		String content = jsonFile.getText()
		
		def json = getJson(content)
		
		Model m = new Model(json)
		
		logger.debug(m.toMDL())
		
		extractBlockFromOriginalMDLAndCompareIgnoringWhitespaceAndComments(getFile("drugX_ModelObject.mdl"), "VARIABILITY_PARAMETERS", m.toMDL());
	}
	
	@Test
	public void testRandomVariableDefinitionBlock() {

		File jsonFile = getFile("drugX_ModelObject_RandomVariableDefinition.json")
		
		String content = jsonFile.getText()
		
		def json = getJson(content)
		
		Model m = new Model(json)
		
		logger.debug(m.toMDL())
		
		extractBlockFromOriginalMDLAndCompareIgnoringWhitespaceAndComments(getFile("drugX_ModelObject.mdl"), "RANDOM_VARIABLE_DEFINITION", m.toMDL());
				
	}
	
	
	// TODO: Check all other data blocks too (in other test classes)
	
	
	@Test
	public void testProlactin() {
		def mdlFile = getFile("prolactin_ModelObject.mdl")
		
		def json = getJsonFromMDLFile(mdlFile)
		
		MCLFile mclFile = new MCLFile(json)
		
		logger.debug(mclFile.toMDL())
		
		extractBlockFromOriginalMDLAndCompareIgnoringWhitespaceAndComments(mdlFile, "MODEL_INPUT_VARIABLES", mclFile.toMDL())
		extractBlockFromOriginalMDLAndCompareIgnoringWhitespaceAndComments(mdlFile, "INDIVIDUAL_VARIABLES", mclFile.toMDL())
		extractBlockFromOriginalMDLAndCompareIgnoringWhitespaceAndComments(mdlFile, "GROUP_VARIABLES", mclFile.toMDL())
		extractBlockFromOriginalMDLAndCompareIgnoringWhitespaceAndComments(mdlFile, "STRUCTURAL_PARAMETERS", mclFile.toMDL())
		extractBlockFromOriginalMDLAndCompareIgnoringWhitespaceAndComments(mdlFile, "VARIABILITY_PARAMETERS", mclFile.toMDL())
		extractBlockFromOriginalMDLAndCompareIgnoringWhitespaceAndComments(mdlFile, "OBSERVATION", mclFile.toMDL())
		extractBlockFromOriginalMDLAndCompareIgnoringWhitespaceAndComments(mdlFile, "ESTIMATION", mclFile.toMDL()) // Actually empty since it doesn't exist in the MDL file
		extractBlockFromOriginalMDLAndCompareIgnoringWhitespaceAndComments(mdlFile, "MODEL_OUTPUT_VARIABLES", mclFile.toMDL())
		extractBlockFromOriginalMDLAndCompareIgnoringWhitespaceAndComments(mdlFile, "MODEL_PREDICTION", mclFile.toMDL())
		// Other blocks to check:
		//RANDOM_VARIABLE_DEFINITION
		
	}
	
	@Test
	public void testDrugX() {
		def mdlFile = getFile("drugX_ModelObject.mdl")
		
		def json = getJsonFromMDLFile(mdlFile)
		
		MCLFile mclFile = new MCLFile(json)
		
		logger.debug(mclFile.toMDL())
		
		extractBlockFromOriginalMDLAndCompareIgnoringWhitespaceAndComments(mdlFile, "MODEL_INPUT_VARIABLES", mclFile.toMDL())
		extractBlockFromOriginalMDLAndCompareIgnoringWhitespaceAndComments(mdlFile, "INDIVIDUAL_VARIABLES", mclFile.toMDL())
		extractBlockFromOriginalMDLAndCompareIgnoringWhitespaceAndComments(mdlFile, "GROUP_VARIABLES", mclFile.toMDL())
		extractBlockFromOriginalMDLAndCompareIgnoringWhitespaceAndComments(mdlFile, "STRUCTURAL_PARAMETERS", mclFile.toMDL())
		extractBlockFromOriginalMDLAndCompareIgnoringWhitespaceAndComments(mdlFile, "VARIABILITY_PARAMETERS", mclFile.toMDL())
		extractBlockFromOriginalMDLAndCompareIgnoringWhitespaceAndComments(mdlFile, "OBSERVATION", mclFile.toMDL()) // Actually empty since it doesn't exist in the MDL file
		extractBlockFromOriginalMDLAndCompareIgnoringWhitespaceAndComments(mdlFile, "ESTIMATION", mclFile.toMDL())
		extractBlockFromOriginalMDLAndCompareIgnoringWhitespaceAndComments(mdlFile, "MODEL_OUTPUT_VARIABLES", mclFile.toMDL())
		extractBlockFromOriginalMDLAndCompareIgnoringWhitespaceAndComments(mdlFile, "MODEL_PREDICTION", mclFile.toMDL())
		// Other blocks to check:
		//RANDOM_VARIABLE_DEFINITION
		
	}
	
}
