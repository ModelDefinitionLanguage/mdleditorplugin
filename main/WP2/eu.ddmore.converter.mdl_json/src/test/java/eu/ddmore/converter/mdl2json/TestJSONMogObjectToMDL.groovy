package eu.ddmore.converter.mdl2json;

import static org.junit.Assert.*

import java.util.regex.Matcher
import org.apache.log4j.Logger
import org.junit.Test

import eu.ddmore.converter.mdl2json.domain.Mog

class TestJSONMogObjectToMDL extends ConverterTestsParent {
	private static Logger logger = Logger.getLogger(TestJSONMogObjectToMDL.class)

    // Using slashy strings /.../ here so we don't have to escape anything other than forward slashes
    private String objectsBlockJson =
        / {"OBJECTS":{"dObj":"warfarin_PK_ODE_dat","mObj":"warfarin_PK_ODE_mdl","pObj":"warfarin_PK_ODE_par","tObj":"warfarin_PK_ODE_task"}} /
    private String mappingBlockJson =
        / {"MAPPING":{"dObj.GUT":"mObj.GUT","dObj.Y":"mObj.Y","dObj.WT":"mObj.WT"},"identifier":"mogobj"} /
        
    @Test
    public void testObjectsBlock() {
        
        def json = getJson(objectsBlockJson)

        def mogObj = new Mog(json)
        
        logger.debug(mogObj.toMDL())
        
        String expectedRegex = """mogobj \\{

    OBJECTS \\{
        ([A-Za-z]+) = ([A-Za-z0-9_]+)
        ([A-Za-z]+) = ([A-Za-z0-9_]+)
        ([A-Za-z]+) = ([A-Za-z0-9_]+)
        ([A-Za-z]+) = ([A-Za-z0-9_]+)
    \\}

\\}
"""
        final Matcher m = mogObj.toMDL() =~ expectedRegex
        assertTrue("Objects block should match the regular expression", m.matches())
        
        assertEquals("Should be 4 object imports", 4 * 2, m.groupCount())
        
        // Have to check the content of the block in this convoluted way since the object imports can be written out in any order
        final Map<String, String> objectsMap = [:]
        objectsMap.put(m.group(1), m.group(2))
        objectsMap.put(m.group(3), m.group(4))
        objectsMap.put(m.group(5), m.group(6))
        objectsMap.put(m.group(7), m.group(8))
        
        assertTrue("An object import for the data object should be present", objectsMap.containsKey("dObj"))
        assertEquals("Checking the object import for the data object", "warfarin_PK_ODE_dat", objectsMap["dObj"])
        assertTrue("An object import for the parameter object should be present", objectsMap.containsKey("pObj"))
        assertEquals("Checking the object import for the parameter object", "warfarin_PK_ODE_par", objectsMap["pObj"])
        assertTrue("An object import for the model object should be present", objectsMap.containsKey("mObj"))
        assertEquals("Checking the object import for the model object", "warfarin_PK_ODE_mdl", objectsMap["mObj"])
        assertTrue("An object import for the task object should be present", objectsMap.containsKey("tObj"))
        assertEquals("Checking the object import for the task object", "warfarin_PK_ODE_task", objectsMap["tObj"])

    }
    
    @Test
    public void testMappingBlock() {
        
        def json = getJson(mappingBlockJson)

        def mogObj = new Mog(json)
        
        logger.debug(mogObj.toMDL())
        
        String expectedRegex = """mogobj \\{

    MAPPING \\{
        ([A-Za-z]+\\.[A-Za-z]+) = ([A-Za-z]+\\.[A-Za-z]+)
        ([A-Za-z]+\\.[A-Za-z]+) = ([A-Za-z]+\\.[A-Za-z]+)
        ([A-Za-z]+\\.[A-Za-z]+) = ([A-Za-z]+\\.[A-Za-z]+)
    \\}

\\}
"""
        final Matcher m = mogObj.toMDL() =~ expectedRegex
        assertTrue("Mapping block should match the regular expression", m.matches())
        
        assertEquals("Should be 3 mappings", 3 * 2, m.groupCount())
        
        // Have to check the content of the block in this convoluted way since the mappings can be written out in any order
        final Map<String, String> mappingsMap = [:]
        mappingsMap.put(m.group(1), m.group(2))
        mappingsMap.put(m.group(3), m.group(4))
        mappingsMap.put(m.group(5), m.group(6))
        
        assertTrue("A mapping for data object's GUT variable should be present", mappingsMap.containsKey("dObj.GUT"))
        assertEquals("Checking the mapping for data object's GUT variable", "mObj.GUT", mappingsMap["dObj.GUT"])
        


    }
    	
	
}
