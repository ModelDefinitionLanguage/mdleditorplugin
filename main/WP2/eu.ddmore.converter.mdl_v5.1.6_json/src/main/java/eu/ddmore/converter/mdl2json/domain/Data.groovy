package eu.ddmore.converter.mdl2json.domain;

import org.ddmore.mdl.mdl.DataInputBlock;
import org.ddmore.mdl.mdl.DataObject;
import org.ddmore.mdl.mdl.DataObjectBlock;
import org.ddmore.mdl.mdl.SourceBlock;
import org.eclipse.emf.common.util.URI;

import eu.ddmore.converter.mdl2json.domain.data.DataInputVariables;
import eu.ddmore.converter.mdl2json.domain.data.Source;
import groovy.util.Expando;

public class Data extends Expando {
	
	URI base;
	DataObject dataObject;
	Source source = null;
	DataInputVariables dataInputVariables = null;
		
	public Data(DataObject dataObject) {
		this.dataObject = dataObject;
		
		setProperty("SOURCE", makeSource());
		
		setProperty("DATA_INPUT_VARIABLES", makeDataInputVariables());
		
	}

	public Source makeSource() {
		if(source==null) {
			SourceBlock sourceBlock = getSourceBlock(dataObject);
			if(sourceBlock!=null) {
				source = new Source(sourceBlock);
			}
		}
		return source;
	}

	public DataInputVariables makeDataInputVariables() {
		if(dataInputVariables == null) {
			dataInputVariables = new DataInputVariables(getDataInputBlock(dataObject));
		}
		return dataInputVariables;
	}
	
	
	public DataInputBlock getDataInputBlock(DataObject dataObject) {
		for( DataObjectBlock b :  dataObject.getBlocks() ) {
			if (b.getDataInputBlock()!=null) return b.getDataInputBlock();
		}
		return null;
	}

	private SourceBlock getSourceBlock(DataObject dataObject) {
		for( DataObjectBlock b :  dataObject.getBlocks() ) {
			if (b.getSourceBlock()!=null) return b.getSourceBlock();
		}
		return null;
	}


	//	private FileBlock getFileBlock(DataObject dataObject) {
//		for( DataObjectBlock b :  dataObject.getBlocks() ) {
//			if (b.getFileBlock()!=null) return b.getFileBlock();
//		}
//		// TODO Throw exception???
//		return null;
//	}
}
