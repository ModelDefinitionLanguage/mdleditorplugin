package eu.ddmore.converter.mdl2json;

import static org.junit.Assert.*

import org.apache.log4j.Logger
import org.junit.Test
import org.junit.Ignore

import eu.ddmore.converter.mdl2json.domain.MCLFile

/**
 * "Real" MDL files from the testdata models project were run through the
 * MDL->JSON->R->JSON->MDL pipeline.
 * <p>
 * The JSON on the way out was dumped out and saved as corresponding .output.json files,
 * again into the testdata models project.
 * <p>
 * These tests convert these JSONs into MDL and compare each block of these against the corresponding
 * block taken from the corresponding original MDL.
 * <p>
 * <b>If any of the parts of the aforementioned conversion pipeline are modified then the updated JSONs
 * should be re-captured and re-saved.</b>
 * <p>
 * Ultimately this would be an automated integration test... DDMORE-845.
 */
class TestJSONToMDLConverter extends ConverterTestsParent {
	private static Logger logger = Logger.getLogger(TestJSONToMDLConverter.class)

	/**
	 * Original MDL file: ex_model7_prolactin_01July2014_OAM.mdl
	 * File containing the dumped out JSON: ex_model7_prolactin_01July2014_OAM.output.json
	 */
    @Test
	public void testRGeneratedMOG_Prolactin() {
		testRGeneratedMOG(
			"FribergCPT2009/ex_model7_prolactin_01July2014_OAM.output.json",		
			"FribergCPT2009/ex_model7_prolactin_01July2014_OAM.mdl"
		);
	}
	
	/**
	 * Original MDL file: tumour_size_01July2014_OAM.mdl
	 * File containing the dumped out JSON: tumour_size_01July2014_OAM.output.json
	 */
	@Test
	@Ignore("Absence of Ignore character gives \"ignore=[]\" in R->JSON conversion - this needs to be fixed")
	public void testRGeneratedMOG_Tumour() {
		testRGeneratedMOG(
			"ThamCCR2008/tumour_size_01July2014_OAM.output.json",
			"ThamCCR2008/tumour_size_01July2014_OAM.mdl"
		);
	}
	
	/**
	 * Original MDL file: drugX_trAbs_1Rep_EST001_ORG.mdl
	 * File containing the dumped out JSON: drugX_trAbs_1Rep_EST001_ORG.output.json
	 */
	@Test
	@Ignore("Absence of RANDOM_VARIABLE_DEFINITION block (and possibly others) produces an empty list in the output JSON which gives rise to an empty block in the MDL - this needs to be fixed")
	// Problematic empty blocks:-
	// RANDOM_VARIABLE_DEFINITION
	// LIBRARY
	// OBSERVATION
	// ESTIMATION
	public void testRGeneratedMOG_DrugX() {
		testRGeneratedMOG(
			"drugX/drugX_trAbs_1Rep_EST001_ORG.output.json",
			"drugX/drugX_trAbs_1Rep_EST001_ORG.mdl"
		);
	}
	
	/**
	 * Original MDL file: warfarin_PK_PRED.mdl
	 * File containing the dumped out JSON: warfarin_PK_PRED.output.json
	 */
	@Test
	@Ignore("Problematic empty blocks: MODEL_PREDICTION, ODE, LIBRARY, ESTIMATION")
	// Problematic empty blocks:-
	// MODEL_PREDICTION
	// ODE
	// LIBRARY
	// ESTIMATION
	public void testRGeneratedMOG_Warfarin() {
		testRGeneratedMOG(
			"warfarin_PK_PRED/warfarin_PK_PRED.output.json",
			"warfarin_PK_PRED/warfarin_PK_PRED.mdl"
		);
	}
	
	private void testRGeneratedMOG(final String pathToOutputJsonFile, final String pathToOrigMdlFile) {
		
		def File jsonFile = getFileFromModelsProject(pathToOutputJsonFile, "json")
		
		String rawJson = jsonFile.getText()
				
		def json = getJson(rawJson)
		
		MCLFile mclFile = new MCLFile(json)
		
		String mdl = mclFile.toMDL()
	
		logger.debug(mdl)
		
		def File origMdlFile = getFileFromModelsProject(pathToOrigMdlFile, "mdl")
		
		[
			"DATA_INPUT_VARIABLES",
			"SOURCE",
			"STRUCTURAL",
			"VARIABILITY",
			"MODEL_INPUT_VARIABLES",
			"STRUCTURAL_PARAMETERS",
			"VARIABILITY_PARAMETERS",
			"GROUP_VARIABLES",
			"RANDOM_VARIABLE_DEFINITION",
			"INDIVIDUAL_VARIABLES",
			"MODEL_PREDICTION",
			"ODE", // actually redundant, should be verified by MODEL_PREDICTION block
			"LIBRARY", // actually redundant, should be verified by MODEL_PREDICTION block
			"OBSERVATION",
			"ESTIMATION",
			"MODEL_OUTPUT_VARIABLES",
			"DATA",
			"MODEL",
			"EXECUTE",
			"ESTIMATE",
			"TARGET_CODE\\(.+\\)"
		].each { blockName ->
			extractBlockFromOriginalMDLAndCompareIgnoringWhitespaceAndComments(origMdlFile, blockName, mdl)
		}
		
	}
	
}
