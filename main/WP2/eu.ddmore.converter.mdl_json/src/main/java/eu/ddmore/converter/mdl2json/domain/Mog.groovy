package eu.ddmore.converter.mdl2json.domain

import eu.ddmore.converter.mdl2json.interfaces.MDLAsJSON
import eu.ddmore.converter.mdl2json.interfaces.MDLPrintable
import eu.ddmore.converter.mdl2json.interfaces.TopLevelBlock;

import org.ddmore.mdl.mdl.MOGObject
import org.ddmore.mdl.mdl.ObjectName


/**
 * Class representing a MOG aggregation defined in the MDL file.
 * I.e. comprising references to one of each of a Model object, a Parameter object, a Data object and a Task object.
 */
public class Mog extends Expando implements MDLPrintable, MDLAsJSON, TopLevelBlock {
    
    static final String IDENTIFIER = "mog"
    static final BLOCKNAMES_PROPNAME = "blockNames"
    
    public Mog(MOGObject m) {
        setProperty(IDENTIFIER_PROPNAME, IDENTIFIER)
        setProperty(BLOCKNAMES_PROPNAME,
            m.getObjects().collect { ObjectName o -> o.getName() }
        )
    }
    
    public Mog(HashMap<String, List<String>> json) {
        setProperty(IDENTIFIER_PROPNAME, IDENTIFIER)
        setProperty(BLOCKNAMES_PROPNAME, json[BLOCKNAMES_PROPNAME])
    }

    @Override
    public String toMDL() {
        IDENTIFIER + " { " + getProperty(BLOCKNAMES_PROPNAME).collect().join(" ") + " }\n"
    }
    
    @Override
    public int getPrintedOrder() {
        return 10; // Sufficiently large to always appear at the end of the file
    }
    
}
