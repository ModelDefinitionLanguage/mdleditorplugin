package eu.ddmore.converter.mdl2json.domain;

import org.ddmore.mdl.mdl.DataDerivedBlock
import org.ddmore.mdl.mdl.DataInputBlock;
import org.ddmore.mdl.mdl.DataObject;
import org.ddmore.mdl.mdl.DataObjectBlock;
import org.ddmore.mdl.mdl.DesignBlock
import org.ddmore.mdl.mdl.SourceBlock;
import org.ddmore.mdl.mdl.TargetBlock
import org.eclipse.emf.common.util.URI;

import eu.ddmore.converter.mdl2json.domain.data.DataInputVariables;
import eu.ddmore.converter.mdl2json.domain.data.Source;
import eu.ddmore.converter.mdlprinting.MdlPrinter;
import groovy.util.Expando;

public class Data extends Expando implements MDLPrintable, MDLAsJSON {
	static final String IDENTIFIER = "dataobj"
	static final String SOURCE = "SOURCE"
	static final String DATA_INPUT_VARIABLES = "DATA_INPUT_VARIABLES"
	static final String TARGET_BLOCK = "TARGET_BLOCK"

	private static MdlPrinter mdlPrinter = MdlPrinter.getInstance()


	private SourceBlock sourceBlock
	private DataInputBlock dataInputBlock
	private DataDerivedBlock dataDerivedBlock
	private DesignBlock designBlock
	private TargetBlock targetBlock
	
	public Data(DataObject dataObject) {

		setProperty(IDENTIFIER_PROPNAME, IDENTIFIER )
		
		for( DataObjectBlock b :  dataObject.getBlocks() ) {
			sourceBlock = b.getSourceBlock() ?: sourceBlock
			dataInputBlock = b.getDataInputBlock() ?: dataInputBlock
			dataDerivedBlock = b.getDataDerivedBlock() ?: dataDerivedBlock
			designBlock = b.getDesignBlock() ?: designBlock
			targetBlock = b.getTargetBlock() ?: targetBlock
		}

		if(sourceBlock) {
			setProperty(SOURCE, new Source(sourceBlock))
		}
		
		if(dataInputBlock) {
			setProperty(DATA_INPUT_VARIABLES, new DataInputVariables(dataInputBlock))
		}
		
		if(targetBlock) {
			setProperty(TARGET_BLOCK, mdlPrinter.toStr(targetBlock))
		}
	}

	public Data(Object json) {
		setProperty(IDENTIFIER_PROPNAME, IDENTIFIER)
		if(json[SOURCE]) {
			setProperty(SOURCE, new Source(json[SOURCE]))
		}
		if(json[DATA_INPUT_VARIABLES]) {
			setProperty(DATA_INPUT_VARIABLES, new DataInputVariables(json[DATA_INPUT_VARIABLES]))
		}
		if(json[TARGET_BLOCK]) {
			setProperty(TARGET_BLOCK, json[TARGET_BLOCK])
		}
	}
	
	public String toMDL() {
		Properties p = getProperties()
		DataInputVariables div = p.get(DATA_INPUT_VARIABLES)
		Source source = p.get(SOURCE)
		
		return """${IDENTIFIER} {
    ${(div==null? "" : div.toMDL())}
    ${(source==null? "" : source.toMDL())}
}
"""
	}
}
