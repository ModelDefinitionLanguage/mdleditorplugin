/*******************************************************************************
 * Copyright (C) 2014-5 Mango Solutions Ltd - All rights reserved.
 ******************************************************************************/
package eu.ddmore.converter.mdl2json;

import static org.junit.Assert.*

import org.apache.log4j.Logger
import org.junit.Test
import org.junit.Ignore

import eu.ddmore.converter.mdl2json.domain.MCLFile
import java.io.File;

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
class JSONToMDLFileConverterTest extends ConverterTestsParent {
    private static Logger logger = Logger.getLogger(JSONToMDLFileConverterTest.class)
    
    /**
     * Converting a MDL file to JSON then back to MDL should give rise to syntactically and
     * semantically equivalent blocks to those of the original MDL.
     * <p>
     * All the blocks of the MDL will be compared.
     * However not all blocks will be present for all models; comparisons of absent blocks will silently pass.
     */
    @Test
    public void testMDLToJSONToMDL_Warfarin() {
        
        def File origMdlFile = getFileFromModelsProject("6.0.7/Warfarin_ODE/Warfarin-ODE-latest.mdl", "mdl")
        
        def json = getJsonFromMDLFile(origMdlFile)
        
        def outputMdl = new MCLFile(json).toMDL()
        
        logger.debug(outputMdl)
        
        allBlockNames.each { blockName ->
            extractBlockFromOriginalMDLAndCompareIgnoringWhitespaceAndComments(origMdlFile, blockName, outputMdl)
        }
    }
    
    /**
     * Converting a MDL file to JSON then back to MDL should give rise to syntactically and
     * semantically equivalent blocks to those of the original MDL.
     * <p>
     * All the blocks of the MDL will be compared.
     * However not all blocks will be present for all models; comparisons of absent blocks will silently pass.
     */
    @Test
    public void testMDLToJSONToMDL_Nock() {
        
        def File origMdlFile = getFileFromModelsProject("6.0.7/Nock_2013_Carb_PK/Nock_2013_Carboplatin_PK.mdl", "mdl")
        
        def json = getJsonFromMDLFile(origMdlFile)
        
        def outputMdl = new MCLFile(json).toMDL()
        
        logger.debug(outputMdl)
        
        allBlockNames.each { blockName ->
            extractBlockFromOriginalMDLAndCompareIgnoringWhitespaceAndComments(origMdlFile, blockName, outputMdl)
        }
    }
    
    /**
     * Original MDL file: Warfarin-ODE-latest.mdl
     * File containing the dumped out JSON: Warfarin-ODE-latest.output.json
     */
    @Test
    public void testRGeneratedMOG_WarfarinODE_MDL607() {
        testRGeneratedMOG(
            "6.0.7/Warfarin_ODE/Warfarin-ODE-latest.output.json",
            "6.0.7/Warfarin_ODE/Warfarin-ODE-latest.mdl"
        );
    }
    
    /**
     * Original MDL file: Nock_2013_Carboplatin_PK.mdl
     * File containing the dumped out JSON: Nock_2013_Carboplatin_PK.output.json
     */
    @Test
    public void testRGeneratedMOG_Nock2013Carboplatin_MDL607() {
        testRGeneratedMOG(
            "6.0.7/Nock_2013_Carb_PK/Nock_2013_Carboplatin_PK.output.json",
            "6.0.7/Nock_2013_Carb_PK/Nock_2013_Carboplatin_PK.mdl"
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
