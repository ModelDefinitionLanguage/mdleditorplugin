/*******************************************************************************
 * Copyright (C) 2014-5 Mango Solutions Ltd - All rights reserved.
 ******************************************************************************/
package eu.ddmore.converter.mdl2json.domain;

import org.ddmore.mdl.mdl.DataObject
import org.ddmore.mdl.mdl.DataObjectBlock

import eu.ddmore.converter.mdl2json.interfaces.MDLAsJSON
import eu.ddmore.converter.mdl2json.interfaces.MDLPrintable
import eu.ddmore.converter.mdl2json.interfaces.TopLevelBlock
import eu.ddmore.converter.mdlprinting.MdlPrinter

public class Data extends Expando implements MDLPrintable, MDLAsJSON, TopLevelBlock {

    static final String IDENTIFIER = "dataobj"

    static final String SOURCE = "SOURCE"
    static final String DECLARED_VARIABLES = "DECLARED_VARIABLES"
    static final String DATA_INPUT_VARIABLES = "DATA_INPUT_VARIABLES"
    static final String DATA_DERIVED_VARIABLES = "DATA_DERIVED_VARIABLES"

    private static MdlPrinter mdlPrinter = MdlPrinter.getInstance()

    public Data(DataObject dataObject) {

        setProperty(IDENTIFIER_PROPNAME, IDENTIFIER)

        for (DataObjectBlock block : dataObject.getBlocks()) {

            if (block.getSourceBlock()) {
                setProperty(SOURCE, new Source(block.getSourceBlock()))
            }
            if (block.getDeclaredVariables()) {
                setProperty(DECLARED_VARIABLES, VariablesList.buildFromSymbolDeclarations(block.getDeclaredVariables().getVariables()))
            }
            if (block.getDataInputBlock()) {
                setProperty(DATA_INPUT_VARIABLES, VariablesList.buildFromSymbolDeclarations(block.getDataInputBlock().getVariables()))
            }
            if (block.getDataDerivedBlock()) {
                setProperty(DATA_DERIVED_VARIABLES, VariablesList.buildFromSymbolDeclarations(block.getDataDerivedBlock().getVariables()))
            }
            if (block.getTargetBlock()) {
                throw new UnsupportedOperationException("Target Code block within Data Object not supported yet")
            }
        }
    }

    public Data(Map json) {
        setProperty(IDENTIFIER_PROPNAME, IDENTIFIER)

        if (json[SOURCE]) {
            setProperty(SOURCE, new Source(json[SOURCE]))
        }
        if (json[DECLARED_VARIABLES]) {
            setProperty(DECLARED_VARIABLES, VariablesList.buildFromJSON(json[DECLARED_VARIABLES]))
        }
        if (json[DATA_INPUT_VARIABLES]) {
            setProperty(DATA_INPUT_VARIABLES, VariablesList.buildFromJSON(json[DATA_INPUT_VARIABLES]))
        }
        if (json[DATA_DERIVED_VARIABLES]) {
            setProperty(DATA_DERIVED_VARIABLES, VariablesList.buildFromJSON(json[DATA_DERIVED_VARIABLES]))
        }
        if (json[TARGET_CODE]) {
            throw new UnsupportedOperationException("Target Code block within Data Object not supported yet")
        }
    }

    public String toMDL() {
        StringBuffer mdl = new StringBuffer()
        def blocks = getProperties().minus([(IDENTIFIER_PROPNAME):(IDENTIFIER)])
        blocks.each { String blockName, MDLPrintable obj ->
            mdl.append("\n${IDT}${blockName} {\n${IDT*2}${obj.toMDL()}\n${IDT}}\n")
        }
        return """${IDENTIFIER} {
${mdl.toString()}
}
"""
    }

    @Override
    public int getPrintedOrder() {
        return 1;
    }

}