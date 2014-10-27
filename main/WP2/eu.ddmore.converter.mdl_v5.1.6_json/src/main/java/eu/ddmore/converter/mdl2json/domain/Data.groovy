package eu.ddmore.converter.mdl2json.domain;

import org.ddmore.mdl.mdl.DataObject
import org.ddmore.mdl.mdl.DataObjectBlock

import eu.ddmore.converter.mdl2json.interfaces.MDLAsJSON;
import eu.ddmore.converter.mdl2json.interfaces.MDLPrintable;
import eu.ddmore.converter.mdlprinting.MdlPrinter

public class Data extends Expando implements MDLPrintable, MDLAsJSON {
	
	static final String IDENTIFIER = "dataobj"
	static final String SOURCE = "SOURCE"
	static final String DATA_INPUT_VARIABLES = "DATA_INPUT_VARIABLES"
	static final String DATA_DERIVED_VARIABLES = "DATA_DERIVED_VARIABLES"
	static final String TARGET_BLOCK = "TARGET_BLOCK"

	private static MdlPrinter mdlPrinter = MdlPrinter.getInstance()

	public Data(DataObject dataObject) {

		setProperty(IDENTIFIER_PROPNAME, IDENTIFIER)
		
		for (DataObjectBlock b : dataObject.getBlocks()) {

    		if (b.getSourceBlock()) {
    			setProperty(SOURCE, new Source(b.getSourceBlock()))
    		}
    		if (b.getDataInputBlock()) {
    			setProperty(DATA_INPUT_VARIABLES, new DataInputVariables(b.getDataInputBlock()))
    		}
    		if (b.getDataDerivedBlock()) {
    			setProperty(DATA_DERIVED_VARIABLES, new DataDerivedVariables(b.getDataDerivedBlock()))
    		}
    		if (b.getTargetBlock()) {
    			setProperty(TARGET_BLOCK, mdlPrinter.toStr(b.getTargetBlock()))
    		}
			
		}
	}

	public Data(Map json) {
		setProperty(IDENTIFIER_PROPNAME, IDENTIFIER)
		
		if (json[SOURCE]) {
			setProperty(SOURCE, new Source(json[SOURCE]))
		}
		if (json[DATA_INPUT_VARIABLES]) {
			setProperty(DATA_INPUT_VARIABLES, new DataInputVariables(json[DATA_INPUT_VARIABLES]))
		}
		if (json[DATA_DERIVED_VARIABLES]) {
			setProperty(DATA_DERIVED_VARIABLES, new DataDerivedVariables(json[DATA_DERIVED_VARIABLES]))
		}
		if (json[TARGET_BLOCK]) {
			setProperty(TARGET_BLOCK, json[TARGET_BLOCK])
		}
	}
	
	public String toMDL() {
		StringBuffer mdl = new StringBuffer()
		def normalProperties = getProperties().minus([(IDENTIFIER_PROPNAME):(IDENTIFIER)])
		normalProperties.each { String blockName, MDLPrintable obj ->
			mdl.append("\n${IDT}${blockName} {\n${IDT*2}${obj.toMDL()}\n${IDT}}\n")
		}
		return """${IDENTIFIER} {
${mdl.toString()}
}
""" // TODO: Target Block
	}
	
}
