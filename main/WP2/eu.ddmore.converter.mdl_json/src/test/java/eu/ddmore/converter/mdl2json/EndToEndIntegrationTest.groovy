/*******************************************************************************
 * Copyright (C) 2014-5 Mango Solutions Ltd - All rights reserved.
 ******************************************************************************/
package eu.ddmore.converter.mdl2json

import static org.junit.Assert.*

import static eu.ddmore.converter.mdl2json.testutils.MdlTestUtils.*

import org.apache.commons.lang.StringUtils
import org.apache.log4j.Logger
import org.junit.Test

import eu.ddmore.converter.mdl2json.domain.MCLFile

class EndToEndIntegrationTest extends ConverterTestsParent {
    private static Logger logger = Logger.getLogger(EndToEndIntegrationTest.class)
    
    /**
     * Tests for the the presence of the top-level objects in the JSON representation of an MDL file:
     * <ul>
     * <li>dataobj
     * <li>parobj
     * <li>mdlobj
     * <li>taskobj
     * <li>mogobj
     * </ul>
     */
    @Test
    public void topLevelObjectsMustBePresentInJsonRepresention() {
        def json = getJsonFromMDLFile("skeleton.mdl")
        
        assertTrue("Returned Json should be a List of length 1", json instanceof List && json.size() == 1)
        json = json[0]
        assertTrue("Returned Json should contain a single Map", json instanceof Map)
        assertTrue("Map representing the top-level objects in the MDL file should contain 5 Entries", json.size() == 5)

        assertTrue("Data object should be present", json['skeleton_data'].size() > 0)
        assertTrue("Data object should have its identifier", StringUtils.isNotEmpty(json['skeleton_data']['identifier']))
        assertTrue("Parameter object should be present", json['skeleton_par'].size() > 0)
        assertTrue("Parameter object should have its identifier", StringUtils.isNotEmpty(json['skeleton_par']['identifier']))
        assertTrue("Model object should be present", json['skeleton_mdl'].size() > 0)
        assertTrue("Model object should have its identifier", StringUtils.isNotEmpty(json['skeleton_mdl']['identifier']))
        assertTrue("Task object should be present", json['skeleton_task'].size() > 0)
        assertTrue("Task object should have its identifier", StringUtils.isNotEmpty(json['skeleton_task']['identifier']))
        assertTrue("Mog object should be present", json['skeleton_mog'].size() > 0)
        assertTrue("Mog object should have its identifier", StringUtils.isNotEmpty(json['skeleton_mog']['identifier']))
    }
    
    /**
     * Converting a MDL file to JSON then back to MDL should give rise to syntactically and
     * semantically equivalent blocks to those of the original MDL.
     * <p>
     * The MDL file "FullyPopulated.mdl" is a syntactically valid, but semantically invalid model,
     * created to try and cover as much of the conversion code with one model as possible.
     */
    @Test
    public void mdlFileConvertedToJsonAndBackAgainShouldBeEquivalentToOriginalMdlFile() {
        
        final File origMdlFile = getFile("FullyPopulated.mdl")
        
        def json = getJsonFromMDLFile(origMdlFile)
        
        def outputMdl = new MCLFile(json).toMDL()
        
        logger.debug(outputMdl)
        
        ALL_BLOCK_NAMES.each { blockName ->
            logger.info("About to process block " + blockName + "...")
            assertMDLBlockEqualityIgnoringWhitespaceAndComments(origMdlFile, blockName, outputMdl)
        }
    }

}
