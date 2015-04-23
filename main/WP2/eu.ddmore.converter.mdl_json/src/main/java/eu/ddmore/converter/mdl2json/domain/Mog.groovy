package eu.ddmore.converter.mdl2json.domain

import org.ddmore.mdl.mdl.FullyQualifiedSymbolName;
import org.ddmore.mdl.mdl.ImportObjectStatement
import org.ddmore.mdl.mdl.MOGObject
import org.ddmore.mdl.mdl.MOGObjectBlock
import org.ddmore.mdl.mdl.MappingBlockStatement

import eu.ddmore.converter.mdl2json.interfaces.MDLAsJSON
import eu.ddmore.converter.mdl2json.interfaces.MDLPrintable
import eu.ddmore.converter.mdl2json.interfaces.TopLevelBlock


/**
 * Class representing a MOG aggregation defined in the MDL file.
 * I.e. comprising references to one of each of a Model object, a Parameter object, a Data object and a Task object.
 */
public class Mog extends Expando implements MDLPrintable, MDLAsJSON, TopLevelBlock {
	
	static final String IDENTIFIER = "mogobj"
    
    static final String OBJECTS = "OBJECTS"
    static final String MAPPING = "MAPPING"
    
	public Mog(MOGObject mogObject) {
        
		setProperty(IDENTIFIER_PROPNAME, IDENTIFIER)
        
        for (MOGObjectBlock block : mogObject.getBlocks()) {
            
            if (block.getObjectBlock()) {
                def objectsMap = [:]
                for (ImportObjectStatement importObject : block.getObjectBlock().getObjects()) {
                    objectsMap.put(importObject.getSymbolName().getName(), importObject.getObjectName().getName())
                }
                setProperty(OBJECTS, objectsMap)
            }
            if (block.getMappingBlock()) {
                def mappingsMap = [:]
                for (MappingBlockStatement mapping : block.getMappingBlock().getMappings()) {
                    final FullyQualifiedSymbolName obj1 = mapping.getObj1()
                    final FullyQualifiedSymbolName obj2 = mapping.getObj2()
                    mappingsMap.put(obj1.getParent().getName() + "." + obj1.getSymbolName().getName(), obj2.getParent().getName() + "." + obj2.getSymbolName().getName())
                }
                setProperty(MAPPING, mappingsMap)
            }
            
        }
	}
    
    public Mog(Map json) {
        setProperty(IDENTIFIER_PROPNAME, IDENTIFIER)
        
        if (json[OBJECTS]) {
            setProperty(OBJECTS, json[OBJECTS])
        }
        if (json[MAPPING]) {
            setProperty(MAPPING, json[MAPPING])
        }
    }


	@Override
    public String toMDL() {
        final StringBuffer mdl = new StringBuffer()
        
        if (getProperties().containsKey(OBJECTS)) {
            final String objectsMdl = getProperty(OBJECTS).collect{ String id, String name ->
                """${id} = ${name}"""
            }.join("\n${IDT*2}")
            mdl.append("\n${IDT}OBJECTS {\n${IDT*2}${objectsMdl}\n${IDT}}\n")
        }
        
        if (getProperties().containsKey(MAPPING)) {
            final String mappingMdl = getProperty(MAPPING).collect{ String obj1, String obj2 ->
                """${obj1} = ${obj2}"""
            }.join("\n${IDT*2}")
            mdl.append("\n${IDT}MAPPING {\n${IDT*2}${mappingMdl}\n${IDT}}\n")
        }
        
        return """${IDENTIFIER} {
${mdl.toString()}
}
"""
    }
	
	@Override
	public int getPrintedOrder() {
		return 10; // Sufficiently large to always appear at the end of the file
	}
	
}
