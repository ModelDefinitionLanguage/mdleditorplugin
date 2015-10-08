/*******************************************************************************
 * Copyright (C) 2014-2015 Mango Solutions Ltd - All rights reserved.
 ******************************************************************************/
package eu.ddmore.converter.mdl2json

import static org.junit.Assert.*

import static eu.ddmore.converter.mdl2json.MdlAndJsonFileUtils.*
import static eu.ddmore.converter.mdl2json.testutils.MdlFileContentTestUtils.*

import eu.ddmore.converter.mdl2json.domain.Mcl
import org.apache.commons.lang.StringUtils
import org.apache.log4j.Logger
import org.junit.Test

class EndToEndIntegrationTest {
    private static final Logger LOGGER = Logger.getLogger(EndToEndIntegrationTest.class)
    
    /**
     * Tests for the the presence of the top-level objects in the JSON representation of an MDL file:
     * <ul>
     * <li>dataObj
     * <li>parObj
     * <li>mdlObj
     * <li>taskObj
     * <li>mogObj
     * </ul>
     */
    @Test
    public void topLevelObjectsMustBePresentInJsonRepresention() {
        def json = getJsonFromMDLFile("skeleton.mdl")
        
        assertTrue("Returned Json should be a List of length 5", json instanceof List && json.size() == 5)

        assertTrue("Each element in the top-level List should be a Map", ((List) json).every { it instanceof Map })
        
        def dataObj = json[0]
        def parObj = json[1]
        def mdlObj = json[2]
        def taskObj = json[3]
        def mogObj = json[4]
        
        assertEquals("Checking content of data object", ['name':'skeleton_data', 'blocks':[:], 'type':'dataObj'], dataObj)
        assertEquals("Checking content of parameter object", ['name':'skeleton_par', 'blocks':[:], 'type':'parObj'], parObj)
        assertEquals("Checking content of model object", ['name':'skeleton_mdl', 'blocks':[:], 'type':'mdlObj'], mdlObj)
        assertEquals("Checking content of task object", ['name':'skeleton_task', 'blocks':[:], 'type':'taskObj'], taskObj)
        assertEquals("Checking content of mog object", ['name':'skeleton_mog', 'blocks':[:], 'type':'mogObj'], mogObj)
    }
    
    /**
     * Converting a MDL file to JSON then back to MDL should give rise to syntactically and
     * semantically equivalent blocks to those of the original MDL.
     * <p>
     * The MDL file "FullyPopulated.mdl" is a syntactically valid, but semantically invalid model,
     * created to try and cover as much of the conversion code with one model as possible.
     * <p>
     * TODO: Re-write a FullyPopulated.mdl for this test
     */
    @Test
    public void mdlFileConvertedToJsonAndBackAgainShouldBeEquivalentToOriginalMdlFile() {
        
        // TODO: Re-write a FullyPopulated.mdl for this test
        final File origMdlFile = getFileFromModelsProject("Product4.1_newgrammar/UseCase1.mdl")
        
        def json = getJsonFromMDLFile(origMdlFile)
        
        def mclFromJson = new Mcl(json)
        def outputMdl = mclFromJson.toMDL()
        
        LOGGER.debug(outputMdl)
        
        ALL_BLOCK_NAMES.each { blockName ->
            LOGGER.info("About to process block " + blockName + "...")
            assertMDLBlockEqualityIgnoringWhitespaceAndComments(origMdlFile, blockName, outputMdl)
        }
    }

}
