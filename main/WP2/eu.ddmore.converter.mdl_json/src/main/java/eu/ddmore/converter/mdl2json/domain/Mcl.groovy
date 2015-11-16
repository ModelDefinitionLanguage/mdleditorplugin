/*******************************************************************************
 * Copyright (C) 2015 Mango Solutions Ltd - All rights reserved.
 ******************************************************************************/
package eu.ddmore.converter.mdl2json.domain

import eu.ddmore.converter.mdl2json.interfaces.MDLPrintable

/**
 * Represents {@link eu.ddmore.mdl.mdl.Mcl} for MDL <-> JSON conversion.
 */
public class Mcl extends ArrayList<MclObject> implements MDLPrintable {

    public Mcl(final eu.ddmore.mdl.mdl.Mcl mcl) {
        
        super(mcl.getObjects().collect { new MclObject(it) })
        
    }
    
    public Mcl(final List<Map> json) {
        
        super(json.collect { new MclObject(it) })
        
    }

    @Override
    public String toMDL() {
        collect { MclObject it ->
            it.toMDL()
        }.join("\n")
    }
    
}
