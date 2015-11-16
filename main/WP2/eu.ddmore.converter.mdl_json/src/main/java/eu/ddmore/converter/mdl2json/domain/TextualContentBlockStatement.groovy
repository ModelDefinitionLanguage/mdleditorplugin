/*******************************************************************************
 * Copyright (C) 2015 Mango Solutions Ltd - All rights reserved.
 ******************************************************************************/
package eu.ddmore.converter.mdl2json.domain


/**
 *
 */
public class TextualContentBlockStatement extends BlockStatement<String> {
    
    
    public TextualContentBlockStatement(final String blockName, final eu.ddmore.mdl.mdl.BlockTextBody blkBody) {
        setProperty(blockName, blkBody.getText())
    }
    
    public TextualContentBlockStatement(final String blockName, final String blockContent) {
        setProperty(blockName, blockContent)
    }
    
    @Override
    public String toMDL() {
        final StringBuffer sb = new StringBuffer()
        sb.append(IDT)
        sb.append(getBlockName())
        sb.append(getBlockRepresentation())
        sb.append("\n")
        sb.toString()
    }
        
}
