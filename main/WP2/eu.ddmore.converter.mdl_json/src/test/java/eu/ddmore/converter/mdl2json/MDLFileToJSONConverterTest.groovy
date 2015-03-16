/*******************************************************************************
 * Copyright (C) 2014-5 Mango Solutions Ltd - All rights reserved.
 ******************************************************************************/
package eu.ddmore.converter.mdl2json;

import static org.junit.Assert.*

import org.apache.commons.lang.StringUtils
import org.apache.log4j.Logger
import org.junit.Test

import eu.ddmore.converter.mdl2json.domain.Mog

class MDLFileToJSONConverterTest extends ConverterTestsParent {
    private static Logger logger = Logger.getLogger(MDLFileToJSONConverterTest.class)
    
    private final static String MDL_FILE_WARFARIN = "6.0.7/Warfarin_ODE/Warfarin-ODE-latest.mdl"
    private final static String MDL_FILE_NOCK_CARBOPLATIN = "6.0.7/Nock_2013_Carb_PK/Nock_2013_Carboplatin_PK.mdl"

    @Test
    public void testTopLevelBlocksPresent() {
        def Map json = getFileFromModelsProjectAndVerifyTopLevelStructure(MDL_FILE_WARFARIN)
        
        def blah = json['warfarin_PK_ODE_dat']
        assertTrue("Data object should be present", json['warfarin_PK_ODE_dat'].size() > 0)
        assertTrue("Data object should have its identifier", StringUtils.isNotEmpty(json['warfarin_PK_ODE_dat']['identifier']))
        assertTrue("Parameter object should be present", json['warfarin_PK_ODE_par'].size() > 0)
        assertTrue("Parameter object should have its identifier", StringUtils.isNotEmpty(json['warfarin_PK_ODE_par']['identifier']))
        assertTrue("Model object should be present", json['warfarin_PK_ODE_mdl'].size() > 0)
        assertTrue("Model object should have its identifier", StringUtils.isNotEmpty(json['warfarin_PK_ODE_mdl']['identifier']))
        assertTrue("Task object should be present", json['warfarin_PK_ODE_task'].size() > 0)
        assertTrue("Task object should have its identifier", StringUtils.isNotEmpty(json['warfarin_PK_ODE_task']['identifier']))
    }
    
    @Test
    public void testDataObjectBlocksPresent() {
        def Map json = getFileFromModelsProjectAndVerifyTopLevelStructure(MDL_FILE_WARFARIN)
        
        assertTrue("DATA_INPUT_VARIABLES block should be present", json['warfarin_PK_ODE_dat']['DATA_INPUT_VARIABLES'].size() > 0)
        assertTrue("SOURCE block should be present", json['warfarin_PK_ODE_dat']['SOURCE'].size() > 0)
    }
    
    @Test
    public void testParameterObjectBlocksPresent() {
        def Map json = getFileFromModelsProjectAndVerifyTopLevelStructure(MDL_FILE_WARFARIN)
        
        assertTrue("STRUCTURAL block should be present", json['warfarin_PK_ODE_par']['STRUCTURAL'].size() > 0)
        assertTrue("VARIABILITY block should be present", json['warfarin_PK_ODE_par']['VARIABILITY'].size() > 0)
    }
    
    @Test
    public void testModelObjectBlocksPresent() {
        def Map json = getFileFromModelsProjectAndVerifyTopLevelStructure(MDL_FILE_WARFARIN)
        
        assertTrue("MODEL_INPUT_VARIABLES block should be present", json['warfarin_PK_ODE_mdl']['MODEL_INPUT_VARIABLES'].size() > 0)
        assertTrue("STRUCTURAL_PARAMETERS block should be present", json['warfarin_PK_ODE_mdl']['STRUCTURAL_PARAMETERS'].size() > 0)
        assertTrue("VARIABILITY_PARAMETERS block should be present", json['warfarin_PK_ODE_mdl']['VARIABILITY_PARAMETERS'].size() > 0)
        assertTrue("RANDOM_VARIABLE_DEFINITION block should be present", json['warfarin_PK_ODE_mdl']['RANDOM_VARIABLE_DEFINITION'].size() > 0)
        assertTrue("INDIVIDUAL_VARIABLES block should be present", json['warfarin_PK_ODE_mdl']['INDIVIDUAL_VARIABLES'].size() > 0)
        assertTrue("MODEL_PREDICTION block should be present", json['warfarin_PK_ODE_mdl']['MODEL_PREDICTION'].size() > 0)
        assertTrue("OBSERVATION block should be present", json['warfarin_PK_ODE_mdl']['OBSERVATION'].size() > 0)
        assertTrue("MODEL_OUTPUT_VARIABLES block should be present", json['warfarin_PK_ODE_mdl']['MODEL_OUTPUT_VARIABLES'].size() > 0)
        
        // GROUP_VARIABLES block is not present in Warfarin model so test with Nock model
        def Map json2 = getFileFromModelsProjectAndVerifyTopLevelStructure(MDL_FILE_NOCK_CARBOPLATIN)
        assertTrue("GROUP_VARIABLES block should be present", json2['Nock_2013_Carboplatin_PK_mdl']['GROUP_VARIABLES'].size() > 0)
    }
    
    @Test
    public void testTaskObjectBlocksPresent() {
        def Map json = getFileFromModelsProjectAndVerifyTopLevelStructure(MDL_FILE_WARFARIN)
        
        assertTrue("ESTIMATE block should be present", json['warfarin_PK_ODE_task']['ESTIMATE'].size() > 0)
    }
    
    @Test
    public void testMogDefinition() {
        def Map json = getFileFromModelsProjectAndVerifyTopLevelStructure(MDL_FILE_WARFARIN)

        assertEquals("Checking the identifier of the Mog Definition within the JSON", Mog.IDENTIFIER, json['warf_mog'][Mog.IDENTIFIER_PROPNAME])
        assertEquals("Checking the Warfarin MOG definition", ["warfarin_PK_ODE_mdl", "warfarin_PK_ODE_par", "warfarin_PK_ODE_dat", "warfarin_PK_ODE_task"], json['warf_mog'][Mog.BLOCKNAMES_PROPNAME])
    }
    
    private Map getFileFromModelsProjectAndVerifyTopLevelStructure(final String mdlFileRelPath) {
        def File mdlFile = getFileFromModelsProject(mdlFileRelPath)
        def json = getJsonFromMDLFile(mdlFile)
        assertTrue("Returned Json should be a List of length 1", json instanceof List && json.size() == 1)
        json = json[0]
        assertTrue("Returned Json should contain a single Map", json instanceof Map)
        return json
    }
    
}
