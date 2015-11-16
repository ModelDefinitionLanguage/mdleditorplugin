/*******************************************************************************
 * Copyright (C) 2014-2015 Mango Solutions Ltd - All rights reserved.
 ******************************************************************************/
package eu.ddmore.converter.mdl2json.legacy;

import static org.junit.Assert.*

import static eu.ddmore.converter.mdl2json.MdlAndJsonFileUtils.*

import org.apache.log4j.Logger
import org.junit.Ignore
import org.junit.Test

import eu.ddmore.converter.mdl2json.MdlAndJsonFileUtils;
import eu.ddmore.converter.mdl2json.domain.Mog
import eu.ddmore.converter.mdl2json.domain.Task

class MogObjectToJSONTest {
	private static final Logger LOGGER = Logger.getLogger(MogObjectToJSONTest.class)
	
    @Test
    public void testObjectsBlock() {
        def json = getJsonFromMDLFile("UseCase1_MogObject.mdl")[0] // The [0] is because the JSON is enclosed within superfluous square brackets [...]
        
        def mogObject = json.warfarin_PK_ODE_mog
        
        def objects = mogObject[Mog.OBJECTS]
        
        LOGGER.debug(objects)
        
        assertEquals("Checking number of imported objects", 4, objects.size())
        
        assertTrue("Checking that data object is imported", objects.containsKey("warfarin_PK_ODE_dat"))
        assertTrue("Checking that model object is imported", objects.containsKey("warfarin_PK_ODE_mdl"))
        assertTrue("Checking that parameter object is imported", objects.containsKey("warfarin_PK_ODE_par"))
        assertTrue("Checking that task object is imported", objects.containsKey("warfarin_PK_ODE_task"))
        
    }
    
	@Test
    @Ignore("Mappings block doesn't currently parse so UseCase20_MogObject.mdl is invalid and cannot be read")
    public void testObjectsBlockWhereUsingAliasing() {
        def json = getJsonFromMDLFile("UseCase20_MogObject.mdl")[0] // The [0] is because the JSON is enclosed within superfluous square brackets [...]
        
        def mogObject = json.warfarin_PK_SIM
        
        def objects = mogObject[Mog.OBJECTS]
        
        LOGGER.debug(objects)
        
        assertEquals("Checking number of imported objects", 4, objects.size())
        
        assertTrue("Checking that data object is imported", objects.containsKey("warfarin_design"))
        assertEquals("Checking the alias of the data object", "do", objects["warfarin_design"])
        assertTrue("Checking that model object is imported", objects.containsKey("warfarin_PK_SIM_mdl"))
        assertEquals("Checking the alias of the model object", "mo", objects["warfarin_PK_SIM_mdl"])
        assertTrue("Checking that parameter object is imported", objects.containsKey("warfarin_PK_SIM_par"))
        assertEquals("Checking the alias of the parameter object", "po", objects["warfarin_PK_SIM_par"])
        assertTrue("Checking that task object is imported", objects.containsKey("warfarin_PK_SIM_task"))
        assertEquals("Checking the alias of the task object", "to", objects["warfarin_PK_SIM_task"])
        
    }
    
    @Test
    @Ignore("Mappings block doesn't currently parse so UseCase20_MogObject.mdl is invalid and cannot be read")
    public void testMappingBlock() {
        def json = getJsonFromMDLFile("UseCase20_MogObject.mdl")[0] // The [0] is because the JSON is enclosed within superfluous square brackets [...]
        
        def mogObject = json.warfarin_PK_SIM_mog
        
        def mappings = mogObject[Mog.MAPPING]
        
        LOGGER.debug(mappings)
        
        assertEquals("Checking number of mappings", 1, mappings.size())
        assertTrue("Checking that data object's WT_MEAN variable is mapped", mappings.containsKey("do.WT_MEAN"))
        assertEquals("Checking that data object's WT_MEAN variable is mapped correctly", "mo.MEAN_WT", mappings["do.WT_MEAN"])

    }
	
}
