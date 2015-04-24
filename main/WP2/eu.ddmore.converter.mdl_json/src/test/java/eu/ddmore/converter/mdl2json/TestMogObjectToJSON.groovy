package eu.ddmore.converter.mdl2json;

import static org.junit.Assert.*

import org.apache.log4j.Logger
import org.junit.Ignore
import org.junit.Test

import eu.ddmore.converter.mdl2json.domain.Mog
import eu.ddmore.converter.mdl2json.domain.Task

class TestMogObjectToJSON extends ConverterTestsParent {
	private static Logger logger = Logger.getLogger(TestMogObjectToJSON.class)
	
	@Test
    public void testObjectsBlock() {
        def json = getJsonFromMDLFile("Warfarin_MogObject.mdl")[0] // The [0] is because the JSON is enclosed within superfluous square brackets [...]
        
        def mogObject = json.warfarin_PK_ODE_mog
        
        def objects = mogObject[Mog.OBJECTS]
        
        logger.debug(objects)
        
        assertEquals("Checking number of imported objects", 4, objects.size())
        
        assertTrue("Checking that data object is imported", objects.containsKey("dObj"))
        assertEquals("Checking that the correct data object is imported", "warfarin_PK_ODE_dat", objects["dObj"])
        assertTrue("Checking that model object is imported", objects.containsKey("mObj"))
        assertEquals("Checking that the correct model object is imported", "warfarin_PK_ODE_mdl", objects["mObj"])
        assertTrue("Checking that parameter object is imported", objects.containsKey("pObj"))
        assertEquals("Checking that the correct parameter object is imported", "warfarin_PK_ODE_par", objects["pObj"])
        assertTrue("Checking that task object is imported", objects.containsKey("tObj"))
        assertEquals("Checking that the correct task object is imported", "warfarin_PK_ODE_task", objects["tObj"])
        
    }
    
    @Test
    public void testMappingBlock() {
        def json = getJsonFromMDLFile("Warfarin_MogObject.mdl")[0] // The [0] is because the JSON is enclosed within superfluous square brackets [...]
        
        def mogObject = json.warfarin_PK_ODE_mog
        
        def mappings = mogObject[Mog.MAPPING]
        
        logger.debug(mappings)
        
        assertEquals("Checking number of mappings", 3, mappings.size())
        assertTrue("Checking that data object's GUT variable is mapped", mappings.containsKey("dObj.GUT"))
        assertEquals("Checking that data object's GUT variable is mapped correctly", "mObj.GUT", mappings["dObj.GUT"])
        assertTrue("Checking that data object's Y variable is mapped", mappings.containsKey("dObj.Y"))
        assertEquals("Checking that data object's Y variable is mapped correctly", "mObj.Y", mappings["dObj.Y"])
        assertTrue("Checking that data object's WT variable is mapped", mappings.containsKey("dObj.WT"))
        assertEquals("Checking that data object's WT variable is mapped correctly", "mObj.WT", mappings["dObj.WT"])

    }
	
}
