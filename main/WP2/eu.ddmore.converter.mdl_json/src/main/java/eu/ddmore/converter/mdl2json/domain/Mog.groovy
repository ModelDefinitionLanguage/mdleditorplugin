/*******************************************************************************
 * Copyright (C) 2014-5 Mango Solutions Ltd - All rights reserved.
 ******************************************************************************/
package eu.ddmore.converter.mdl2json.domain

import org.ddmore.mdl.mdl.ImportObjectStatement
import org.ddmore.mdl.mdl.MOGObject
import org.ddmore.mdl.mdl.MOGObjectBlock

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

    public Mog(MOGObject mogObject) {

        setProperty(IDENTIFIER_PROPNAME, IDENTIFIER)

        for (MOGObjectBlock block : mogObject.getBlocks()) {

            if (block.getObjectBlock()) {
                def objectsReverseMap = [:]
                for (ImportObjectStatement importObject : block.getObjectBlock().getObjects()) {
                    if (importObject.getSymbolName()) {
                        // Import Object is aliased
                        objectsReverseMap.put(importObject.getObjectName().getName(), importObject.getSymbolName().getName())
                    } else {
                        // Import Object is not aliased
                        objectsReverseMap.put(importObject.getObjectName().getName(), null)
                    }
                }
                setProperty(OBJECTS, objectsReverseMap)
            }
        }
    }

    public Mog(Map json) {
        setProperty(IDENTIFIER_PROPNAME, IDENTIFIER)

        if (json[OBJECTS]) {
            setProperty(OBJECTS, json[OBJECTS])
        }
    }

    @Override
    public String toMDL() {
        final StringBuffer mdl = new StringBuffer()

        if (getProperties().containsKey(OBJECTS)) {
            final String objectsMdl = getProperty(OBJECTS).collect{ String objName, String aliasName ->
                if (aliasName) {
                    // Import Object is aliased
                    """${aliasName} = ${objName}"""
                } else {
                    // Import Object is not aliased
                    """${objName}"""
                }
            }.join("\n${IDT*2}")
            mdl.append("\n${IDT}OBJECTS {\n${IDT*2}${objectsMdl}\n${IDT}}\n")
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