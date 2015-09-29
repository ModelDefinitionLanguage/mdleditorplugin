/******************************************************************************* * Copyright (C) 2015 Mango Solutions Ltd - All rights reserved. ******************************************************************************/package eu.ddmore.converter.mdl2json.domainimport eu.ddmore.converter.mdl2json.interfaces.MDLPrintable;

/**
 * Represents {@link eu.ddmore.mdl.mdl.MclObject} for MDL <-> JSON conversion.
 */
public class MclObject extends Expando implements MDLPrintable {
    
    public static String PROPERTY_NAME = "name"
    public static String PROPERTY_TYPE = "type"
    public static String PROPERTY_BLOCKS = "blocks"

    public MclObject(final eu.ddmore.mdl.mdl.MclObject mclObj) {
        
        setProperty(PROPERTY_NAME, mclObj.getName())
        setProperty(PROPERTY_TYPE, mclObj.getMdlObjType())
        setProperty(PROPERTY_BLOCKS, BlockStatementList.fromMDL(mclObj.getBlocks()))
        
    }
    
    public MclObject(final Map json) {
        
        setProperty(PROPERTY_NAME, json[PROPERTY_NAME])
        setProperty(PROPERTY_TYPE, json[PROPERTY_TYPE])
        setProperty(PROPERTY_BLOCKS, BlockStatementList.fromJSON(json[PROPERTY_BLOCKS]))
        
    }    @Override    public String toMDL() {        final StringBuffer sb = new StringBuffer()        sb.append(getProperty(PROPERTY_NAME))        sb.append(" = ")        sb.append(getProperty(PROPERTY_TYPE))        sb.append(" {\n")        sb.append(getProperty(PROPERTY_BLOCKS).toMDL())        sb.append("}\n")        sb.toString()    }    
}
