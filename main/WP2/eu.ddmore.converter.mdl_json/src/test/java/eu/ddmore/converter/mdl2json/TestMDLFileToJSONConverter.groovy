package eu.ddmore.converter.mdl2json;

import static org.junit.Assert.*

import org.apache.log4j.Logger
import org.junit.Test

import eu.ddmore.converter.mdl2json.domain.Mog

class TestMDLFileToJSONConverter extends ConverterTestsParent {
	private static Logger logger = Logger.getLogger(TestMDLFileToJSONConverter.class)

    @Test
	public void testTopLevelBlocksPresent() {
        def File mdlFile = getFileFromModelsProject("warfarin_ODE/Warfarin-ODE-28Oct2014.mdl")
        def json = getJsonFromMDLFile(mdlFile)
		
		assertNotNull("Data object should be present", json.warfarin_PK_ODE_dat)
		assertNotNull("Data object should have its identifier", json.warfarin_PK_ODE_dat.IDENTIFIER)
		assertNotNull("Parameter object should be present", json.warfarin_PK_ODE_par)
		assertNotNull("Parameter object should have its identifier", json.warfarin_PK_ODE_par.IDENTIFIER)
		assertNotNull("Model object should be present", json.warfarin_PK_ODE_mdl)
		assertNotNull("Model object should have its identifier", json.warfarin_PK_ODE_mdl.IDENTIFIER)
		assertNotNull("Task object should be present", json.warfarin_PK_ODE_task)
		assertNotNull("Task object should have its identifier", json.warfarin_PK_ODE_task.IDENTIFIER)
	}
	
	@Test
	public void testDataObjectBlocksPresent() {
		def File mdlFile = getFileFromModelsProject("warfarin_ODE/Warfarin-ODE-28Oct2014.mdl")
		def json = getJsonFromMDLFile(mdlFile)
		
		assertNotNull("DATA_INPUT_VARIABLES block should be present", json.warfarin_PK_ODE_dat.DATA_INPUT_VARIABLES)
		//assertNotNull("DATA_DERIVED_VARIABLES block should be present", json.warfarin_PK_ODE_dat.DATA_DERIVED_VARIABLES)
		assertNotNull("SOURCE block should be present", json.warfarin_PK_ODE_dat.SOURCE)
	}
	
	@Test
	public void testParameterObjectBlocksPresent() {
		def File mdlFile = getFileFromModelsProject("warfarin_ODE/Warfarin-ODE-28Oct2014.mdl")
		def json = getJsonFromMDLFile(mdlFile)
		
		assertNotNull("STRUCTURAL block should be present", json.warfarin_PK_ODE_par.STRUCTURAL)
		assertNotNull("VARIABILITY block should be present", json.warfarin_PK_ODE_par.VARIABILITY)
	}
	
	@Test
	public void testModelObjectBlocksPresent() {
		def File mdlFile = getFileFromModelsProject("warfarin_ODE/Warfarin-ODE-28Oct2014.mdl")
		def json = getJsonFromMDLFile(mdlFile)
		
		assertNotNull("MODEL_INPUT_VARIABLES block should be present", json.warfarin_PK_ODE_mdl.MODEL_INPUT_VARIABLES)
		assertNotNull("STRUCTURAL_PARAMETERS block should be present", json.warfarin_PK_ODE_mdl.STRUCTURAL_PARAMETERS)
		assertNotNull("VARIABILITY_PARAMETERS block should be present", json.warfarin_PK_ODE_mdl.VARIABILITY_PARAMETERS)
		assertNotNull("RANDOM_VARIABILITY_DEFINITION block should be present", json.warfarin_PK_ODE_mdl.RANDOM_VARIABILITY_DEFINITION)
		assertNotNull("INDIVIDUAL_VARIABLES block should be present", json.warfarin_PK_ODE_mdl.INDIVIDUAL_VARIABLES)
		assertNotNull("MODEL_PREDICTION block should be present", json.warfarin_PK_ODE_mdl.MODEL_PREDICTION)
		assertNotNull("OBSERVATION block should be present", json.warfarin_PK_ODE_mdl.OBSERVATION)
		assertNotNull("MODEL_OUTPUT_VARIABLES block should be present", json.warfarin_PK_ODE_mdl.MODEL_OUTPUT_VARIABLES)
	}
	
	@Test
	public void testTaskObjectBlocksPresent() {
		def File mdlFile = getFileFromModelsProject("warfarin_ODE/Warfarin-ODE-28Oct2014.mdl")
		def json = getJsonFromMDLFile(mdlFile)
		
		assertNotNull("ESTIMATE block should be present", json.warfarin_PK_ODE_task.ESTIMATE)
	}
	
	@Test
	public void testMogDefinition() {
		def File mdlFile = getFileFromModelsProject("warfarin_ODE/Warfarin-ODE-28Oct2014.mdl")
		def json = getJsonFromMDLFile(mdlFile)
		def warf_mog = json[0].warf_mog // Remove the extra layer of nesting
		assertNotNull("Warfarin MOG definition should be present", warf_mog)
		assertEquals("Checking the identifier of the Mog Definition within the JSON", Mog.IDENTIFIER, warf_mog[Mog.IDENTIFIER_PROPNAME])
		assertEquals("Checking the Warfarin MOG definition", ["warfarin_PK_ODE_mdl", "warfarin_PK_ODE_par", "warfarin_PK_ODE_dat", "warfarin_PK_ODE_task"], warf_mog[Mog.BLOCKNAMES_PROPNAME])
	}
    
}
