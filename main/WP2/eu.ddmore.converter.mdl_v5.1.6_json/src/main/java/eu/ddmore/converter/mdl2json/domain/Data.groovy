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

public class Data extends Expando {
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

		setProperty("identifier", IDENTIFIER )
		
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
		setProperty("identifier", IDENTIFIER)
		
	}
	
	public String toMDL() {
		return """${IDENTIFIER} {
			}

		"""
	}
}
