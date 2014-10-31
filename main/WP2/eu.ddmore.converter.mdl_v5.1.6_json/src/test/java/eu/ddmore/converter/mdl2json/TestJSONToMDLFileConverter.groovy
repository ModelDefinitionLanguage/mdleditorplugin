package eu.ddmore.converter.mdl2json;

import static org.junit.Assert.*

import org.apache.log4j.Logger
import org.junit.Test
import org.junit.Ignore

import eu.ddmore.converter.mdl2json.domain.MCLFile
import eu.ddmore.converter.mdl2json.domain.Data
import eu.ddmore.converter.mdl2json.domain.Parameter
import eu.ddmore.converter.mdl2json.domain.Model
import eu.ddmore.converter.mdl2json.domain.ModelPrediction
import eu.ddmore.converter.mdl2json.domain.Task

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
class TestJSONToMDLFileConverter extends ConverterTestsParent {
	private static Logger logger = Logger.getLogger(TestJSONToMDLFileConverter.class)
	
	static List<String> allBlockNames = [
			Data.SOURCE,
			Data.DATA_INPUT_VARIABLES,
			Data.DATA_DERIVED_VARIABLES,
			Parameter.STRUCTURAL,
			Parameter.VARIABILITY,
			Parameter.PRIOR,
			Model.STRUCTURAL_PARAMETERS,
			Model.VARIABILITY_PARAMETERS,
			Model.INDIVIDUAL_VARIABLES,
			Model.RANDOM_VARIABLE_DEFINITION,
			Model.MODEL_OUTPUT_VARIABLES,
			Model.MODEL_INPUT_VARIABLES,
			Model.OBSERVATION,
			Model.MODEL_PREDICTION,
			ModelPrediction.ODE, // actually redundant, should be verified by MODEL_PREDICTION block
			ModelPrediction.LIBRARY, // actually redundant, should be verified by MODEL_PREDICTION block
			Model.GROUP_VARIABLES,
			Model.ESTIMATION,
			Model.SIMULATION,
			Task.ESTIMATE,
			Task.SIMULATE,
			Task.EVALUATE,
			Task.OPTIMISE,
			Task.DATA,
			Task.MODEL,
			//"TARGET_CODE\\(.+\\)" // note the regex matching for the parameters of the block name
		]
	
	/**
	 * Converting a MDL file to JSON then back to MDL should give rise to syntactically and
	 * semantically equivalent blocks to those of the original MDL.
	 * <p>
	 * All the blocks of the MDL will be compared.
	 * However not all blocks will be present for all models; comparisons of absent blocks will silently pass.
	 */
	@Test
	public void testMDLToJSONToMDL() {
		
		def File origMdlFile = getFileFromModelsProject("warfarin_ODE/Warfarin-ODE-28Oct2014.mdl", "mdl")
		
		def json = getJsonFromMDLFile(origMdlFile)
		
		def outputMdl = new MCLFile(json).toMDL()
		
		allBlockNames.each { blockName ->
			extractBlockFromOriginalMDLAndCompareIgnoringWhitespaceAndComments(origMdlFile, blockName, outputMdl)
		}
	}
	
	/**
	 * Original MDL file: Warfarin-ODE-latest.mdl
	 * File containing the dumped out JSON: Warfarin-ODE-latest.output.json
	 */
	@Test
	public void testRGeneratedMOG_WarfarinODE28Oct2014() {
		testRGeneratedMOG(
			"warfarin_ODE/Warfarin-ODE-28Oct2014.output.json",
			"warfarin_ODE/Warfarin-ODE-28Oct2014.mdl"
		);
	}

	/**
	 * Original MDL file: ex_model7_prolactin_01July2014_OAM.mdl
	 * File containing the dumped out JSON: ex_model7_prolactin_01July2014_OAM.output.json
	 */
    @Test
	@Ignore("Out of date")
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
	@Ignore("Out of date")
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
	@Ignore("Out of date")
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
	@Ignore("Out of date")
	public void testRGeneratedMOG_Warfarin() {
		testRGeneratedMOG(
			"warfarin_PK_PRED/warfarin_PK_PRED.output.json",
			"warfarin_PK_PRED/warfarin_PK_PRED.mdl"
		);
	}
	
	/**
	 * All the blocks of the MDL will be compared.
	 * However not all blocks will be present for all models; comparisons of absent blocks will silently pass.
	 * <p>
	 * @param pathToOutputJsonFile
	 * @param pathToOrigMdlFile
	 */
	private void testRGeneratedMOG(final String pathToOutputJsonFile, final String pathToOrigMdlFile) {
		
		def File jsonFile = getFileFromModelsProject(pathToOutputJsonFile, "json")
		
		String rawJson = jsonFile.getText()
				
		def json = getJson(rawJson)
		
		MCLFile mclFile = new MCLFile(json)
		
		String mdl = mclFile.toMDL()
	
		logger.debug(mdl)
		
		def File origMdlFile = getFileFromModelsProject(pathToOrigMdlFile, "mdl")
		
		allBlockNames.each { blockName ->
			extractBlockFromOriginalMDLAndCompareIgnoringWhitespaceAndComments(origMdlFile, blockName, mdl)
		}
		
	}
	
}
